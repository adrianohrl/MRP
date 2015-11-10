/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.orders;

import inventory.GlobalInventoryItem;
import inventory.InventoryItem;
import inventory.SectorInventory;
import inventory.agents.Client;
import inventory.agents.Sector;
import inventory.mrp.tree.MRPTreeNode;
import inventory.mrp.tree.ProductComponent;
import inventory.util.Unit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author adrianohrl
 */
@Entity
public class SaleOrder extends AbstractOrder<Sector, Client> {
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrder> purchaseOrders = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ManufacturingOrder> manufacturingOrders = new ArrayList<>();

    public SaleOrder() {
    }

    public SaleOrder(String reference, SectorInventory inventory, Calendar orderDate, Calendar deadline, Sector supplier, Client client) {
        super(reference, inventory, orderDate, deadline, supplier, client);
    }
    
    // tratar cancelamentos, entregas e devolućões (com relaćão aos clientes)
    
    // quando realizada uma nova entrega de fabricaćão, verificar se pode ser feita uma nova entrega do pedido de venda
    // ou se pode ser gerada uma nova ordem de fabricaćão
    
        // (sempre tentando respeitar o prazo) 
        
        // tentar colocar os passos 1 e 2 dentro do while tbm
        // utilizar recursividade
        
        // 1) verificar quais PF já estão disponíveis para entrega
        // 2) se sim, reservá-lo para esta ordem
        // 3) se não foi concluído a ordem ainda, verificar se todos os módulos e itens (children) para fabricaćão deste nó já estão disponíveis
        // 4) se sim, reservá-los para esta ordem e gerar ordem de fabricaćão deste elemento (PF ou WIP)
        // 5) se não, reservar itens 
        // 6) se ainda existe item indisponível, gerar ordem de compra para os itens em falta (dentro do prazo)
        // 7) se ainda existe módulo insdisponível, repetir passos a partir de 3)
        
        // 8) quando chegar na folha, voltar agentando ordens de compra e fabricaćão de acordo com o prazo final e com os tempos de espera
        
    @Override
    public void order() {
        for (OrderItem item : getItems()) {
            order(item);
        }
        for (PurchaseOrder purchaseOrder : purchaseOrders) { /////////agrupar ordens de compra de 5 em 5 dias
            ///// tbm agrupar itens de mesma referencia (prevalecendo a data mais antiga dos itens)
        }
        for (ManufacturingOrder manufacturingOrder : manufacturingOrders) { /// agrupar ordens de fabricaćão por dia 
            
        }
    }
    
    private void order(OrderItem item) {
        order(item.getItem(), item.getQuantity(), item.getUnit());
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
            if (child.isLeaf()) {
                System.out.println("It's necessary to do a purchase order of " + childRemainingQuantity + " [" + child.getQuantityUnit() + "] of " + child.getNode().getReference() + " at least " + leadTime + " [" + child.getTimeUnit() + "] before the deadline!!!");
                Calendar now = new GregorianCalendar();
                Calendar deadline = new GregorianCalendar();
                deadline.add(Calendar.HOUR, Math.round((float) leadTime * 7 * 24));
                PurchaseOrder purchaseOrder = new PurchaseOrder(getReference(), getInventory(), now, deadline, null, getSupplier()); /// qual é o deadline
                purchaseOrder.addItem(new OrderItem(item, childRemainingQuantity, child.getQuantityUnit()));
                purchaseOrders.add(purchaseOrder);
            } else if (remainingQuantity != 0) {
                System.out.println("It's necessary to do a manufacturing order of " + childRemainingQuantity + " [" + unit + "] of " + child.getNode().getReference() + " at least " + leadTime + " [" + child.getTimeUnit() + "] before the deadline!!!");
                Calendar now = new GregorianCalendar();
                Calendar deadline = new GregorianCalendar();
                deadline.add(Calendar.HOUR, Math.round((float) leadTime * 7 * 24));
                ManufacturingOrder manufacturingOrder = new ManufacturingOrder(getReference(), getInventory(), now, deadline, null, getSupplier()); /// qual é o deadline
                manufacturingOrder.addItem(new OrderItem(item, childRemainingQuantity, unit));
                manufacturingOrders.add(manufacturingOrder);
            }
        }
        return remainingQuantity;
    }
    
    /**
     * 
     * @param inventory
     * @param item
     * @param desiredQuantity
     * @param unit
     * @return the remaining quantity
     */
    private double reserve(GlobalInventoryItem item, double desiredQuantity, Unit unit) {
        SectorInventory inventory = getInventory();
        double availableQuantity = inventory.get(item).getQuantityOnHand(unit);
        double quantity = desiredQuantity > availableQuantity ? availableQuantity : desiredQuantity;
        if (!inventory.bound(new InventoryItem(item, quantity, 0, unit))) {
            throw new RuntimeException("Unable to reserve " + quantity + " [" + unit + "] of " + item + " in " + inventory.getAgent());
        }
        return desiredQuantity - quantity;
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
