/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.orders;

import inventory.GlobalInventoryItem;
import inventory.GrossRequirement;
import inventory.InventoryItem;
import inventory.SectorInventory;
import inventory.agents.Client;
import inventory.agents.Sector;
import inventory.mrp.tree.MRPTreeNode;
import inventory.mrp.tree.ProductComponent;
import inventory.util.CalendarManipulator;
import inventory.util.Unit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

/**
 *
 * @author adrianohrl
 */
@Entity
public class SaleOrder extends AbstractOrder<Sector, Client> {
    
    @ManyToMany
    @JoinColumn(insertable = false, updatable = false) 
    private List<PurchaseOrder> purchaseOrders = new ArrayList<>();
    @ManyToMany
    @JoinColumn(insertable = false, updatable = false) 
    private List<ManufacturingOrder> manufacturingOrders = new ArrayList<>();

    public SaleOrder() {
    }

    public SaleOrder(String reference, SectorInventory inventory, Calendar orderDate, Calendar deadline, Sector supplier, Client client) {
        super(reference, inventory, orderDate, deadline, supplier, client);
    }
    
    // tratar cancelamentos, entregas e devolućões (com relaćão aos clientes)
    
    // quando realizada uma nova entrega de fabricaćão, verificar se pode ser feita uma nova entrega do pedido de venda
    // ou se pode ser gerada uma nova ordem de fabricaćão
        
    @Override
    public void order() {
        for (OrderItem item : getItems()) {
            order(item);
        }
        getInventory().add(this);
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            purchaseOrder.order();
        }
        for (ManufacturingOrder manufacturingOrder : manufacturingOrders) {
            manufacturingOrder.order();
        }
    }
    
    private void order(OrderItem item) {
        GrossRequirement grossRequirement = new GrossRequirement(this, item.getQuantity(), item.getUnit());
        getInventory().add(grossRequirement, item.getItem());
        double remainingQuantity = order(item.getItem(), item.getQuantity(), item.getUnit());
        GlobalInventoryItem globalItem = item.getItem();
        Calendar deadline = CalendarManipulator.subtract(getDeadline(), globalItem.getLeadTime(), globalItem.getTimeUnit());
        String orderName = getReference() + ": " + globalItem.getReference() + " (" + CalendarManipulator.formatDate(deadline) + ")";
        ManufacturingOrder manufacturingOrder = new ManufacturingOrder(orderName, getInventory(), new GregorianCalendar(), deadline, null, getSupplier());
        manufacturingOrder.add(new OrderItem(globalItem, remainingQuantity, item.getUnit()));
        add(manufacturingOrder);
    }
    
    private double order(GlobalInventoryItem item, double quantity, Unit unit) {
        double remainingQuantity = reserve(item, quantity, unit);
        MRPTreeNode node = item.getTreeNode();
        if (remainingQuantity == 0 || node == null || node.isLeaf()) {
            return remainingQuantity;
        }
        Iterator<ProductComponent> children = node.children();
        while (children.hasNext()) {
            ProductComponent child = children.next();
            double childRemainingQuantity = order(child.getNode(), remainingQuantity * child.getRequiredQuantity(), child.getQuantityUnit());
            double leadTime = child.getNode().getLeadTime();
            MRPTreeNode parent = child.getParent();
            while (parent != null) {
                leadTime += parent.getNode().getLeadTime(child.getTimeUnit());
                parent = parent.getParent();
            }
            Calendar deadline = CalendarManipulator.subtract(getDeadline(), leadTime, child.getTimeUnit());
            String orderName = getReference() + ": " + child.getNode().getReference() + " (" + CalendarManipulator.formatDate(deadline) + ")";
            if (child.isLeaf()) {
                PurchaseOrder purchaseOrder = new PurchaseOrder(orderName, getInventory(), new GregorianCalendar(), deadline, null, getSupplier());
                purchaseOrder.add(new OrderItem(child.getNode(), childRemainingQuantity, child.getQuantityUnit()));
                add(purchaseOrder);
            } else if (remainingQuantity != 0) {
                ManufacturingOrder manufacturingOrder = new ManufacturingOrder(orderName, getInventory(), new GregorianCalendar(), deadline, null, getSupplier());
                manufacturingOrder.add(new OrderItem(child.getNode(), childRemainingQuantity, unit));
                add(manufacturingOrder);
            }
        }
        return remainingQuantity;
    }
    
    private double reserve(GlobalInventoryItem item, double desiredQuantity, Unit unit) {
        SectorInventory inventory = getInventory();
        double availableQuantity = inventory.get(item).getQuantityOnHand(unit);
        double quantity = desiredQuantity > availableQuantity ? availableQuantity : desiredQuantity;
        if (!inventory.bound(new InventoryItem(item, quantity, 0, unit))) {
            throw new RuntimeException("Unable to reserve " + quantity + " [" + unit + "] of " + item + " in " + inventory.getAgent());
        }
        return desiredQuantity - quantity;
    }
    
    private void add(PurchaseOrder order) {
        if (order == null) {
            return;
        }
        for (int i = order.size() - 1; i >= 0; i--) {
            OrderItem item = order.getItems().get(i);
            GlobalInventoryItem globalItem = item.getItem();
            Calendar calendar = CalendarManipulator.subtract(order.getDeadline(), globalItem.getLeadTime(), globalItem.getTimeUnit());
            for (PurchaseOrder purchaseOrder : purchaseOrders) {
                Calendar purchaseDeadline = purchaseOrder.getDeadline();
                if (calendar.before(purchaseDeadline) && purchaseOrder.contains(item)) {
                    Cancellation cancellation = new Cancellation("Allocating this item to another order.");
                    cancellation.add(item);
                    if (purchaseDeadline.before(order.getDeadline())) {
                        order.realocate(purchaseOrder, item);
                    } else {
                        purchaseOrder.realocate(order, item);
                        if (purchaseOrder.isEmpty()) {
                            purchaseOrders.remove(purchaseOrder);
                        }
                    }
                    break;
                }
            }
        }
        if (order.isEmpty()) {
            return;
        }
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            if (CalendarManipulator.happenOnTheSameDay(order.getDeadline(), purchaseOrder.getDeadline())) {
                for (OrderItem item : order.getItems()) {
                    if (item.isValid()) {
                        purchaseOrder.add(item);
                    }
                }
                return;
            }
        }
        if (!order.isEmpty()) {
            purchaseOrders.add(order);
        }
    }
    
    private void add(ManufacturingOrder order) {
        if (order == null) {
            return;
        }
        for (int i = order.size() - 1; i >= 0; i--) {
            OrderItem item = order.getItems().get(i);
            GlobalInventoryItem globalItem = item.getItem();
            Calendar calendar = CalendarManipulator.subtract(order.getDeadline(), globalItem.getLeadTime(), globalItem.getTimeUnit());
            for (ManufacturingOrder manufacturingOrder : manufacturingOrders) {
                Calendar manufacturingDeadline = manufacturingOrder.getDeadline();
                if (calendar.before(manufacturingDeadline) && manufacturingOrder.contains(item)) {
                    if (manufacturingDeadline.before(order.getDeadline())) {
                        order.realocate(manufacturingOrder, item);
                    } else {
                        manufacturingOrder.realocate(order, item);
                        if (manufacturingOrder.isEmpty()) {
                            manufacturingOrders.remove(manufacturingOrder);
                        }
                    }
                    break;
                }
            }
        }
        if (order.isEmpty()) {
            return;
        }
        for (ManufacturingOrder manufacturingOrder : manufacturingOrders) {
            if (CalendarManipulator.happenOnTheSameDay(order.getDeadline(), manufacturingOrder.getDeadline())) {
                for (OrderItem item : order.getItems()) {
                    if (item.isValid()) {
                        manufacturingOrder.add(item);
                    }
                }
                return;
            }
        }
        if (!order.isEmpty()) {
            manufacturingOrders.add(order);
        }
    }

    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public List<ManufacturingOrder> getManufacturingOrders() {
        return manufacturingOrders;
    }

    public void setManufacturingOrders(List<ManufacturingOrder> manufacturingOrders) {
        this.manufacturingOrders = manufacturingOrders;
    }
    
}
