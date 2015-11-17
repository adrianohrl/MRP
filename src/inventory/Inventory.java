/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import dao.Codeable;
import inventory.agents.Agent;
import inventory.orders.OrderItem;
import inventory.mrp.tree.ProductComponent;
import inventory.orders.AbstractOrder;
import inventory.util.Unit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author adrianohrl
 * @param <A>
 */
@Entity
public abstract class Inventory<A extends Agent> implements Serializable, Codeable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    @ManyToOne
    private GlobalInventory globalInventory;
    @OneToOne
    private A agent;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryItem> items = new ArrayList<>();

    public Inventory() {
    }

    public Inventory(GlobalInventory globalInventory, A agent) {
        this.globalInventory = globalInventory;
        this.agent = agent;
    }   
    
    public void add(GrossRequirement grossRequirement, GlobalInventoryItem item) {
        globalInventory.add(grossRequirement, item);
    }
    
    public void add(ScheduledReceipt scheduledReceipt, GlobalInventoryItem item) {
        globalInventory.add(scheduledReceipt, item);
    }
    
    public void add(AbstractOrder order) {
        globalInventory.add(order);
    }
    
    public boolean unbound(List<InventoryItem> items) {
        if (!isValidUnbounds(items)) {
            return false;
        }
        for (InventoryItem item : items) {
            if (!unbound(item)) {
                throw new RuntimeException("Unexpected error while unbounding inventory item!!!");
            }
        }
        return true;
    }
    
    public boolean unbound(InventoryItem item) {
        InventoryItem inventoryItem = get(item);
        return inventoryItem != null && inventoryItem.unbound(item);
    }
    
    public boolean bound(List<InventoryItem> items) {
        if (!isValidBounds(items)) {
            return false;
        }
        for (InventoryItem item : items) {
            if (!bound(item)) {
                throw new RuntimeException("Unexpected error while bounding inventory item!!!");
            }
        }
        return true;
    }
    
    public boolean bound(InventoryItem item) {
        InventoryItem inventoryItem = get(item);
        return inventoryItem != null && inventoryItem.bound(item);
    }
    
    public boolean input(List<InventoryItem> items) {
        if (!isValidInputs(items)) {
            return false;
        }
        for (InventoryItem item : items) {
            if (!input(item)) {
                throw new RuntimeException("Unexpected error while inputing inventory item!!!");
            }
        }
        return true;
    }
    
    public boolean input(InventoryItem item) {
        InventoryItem inventoryItem = get(item);
        return inventoryItem != null && inventoryItem.input(item);
    }
    
    public boolean output(List<InventoryItem> items) {
        if (!isValidOutputs(items)) {
            return false;
        }
        for (InventoryItem item : items) {
            if (!output(item)) {
                throw new RuntimeException("Unexpected error while outputing inventory item!!!");
            }
        }
        return true;
    }
    
    public boolean output(InventoryItem item) {
        InventoryItem inventoryItem = get(item);
        return inventoryItem != null && inventoryItem.output(item);
    }
    
    public boolean transform(List<InventoryItem> items) {
        if (!isValidTransformations(items)) {
            return false;
        }
        for (InventoryItem item : items) {
            if (!transform(item)) {
                throw new RuntimeException("Unexpected error while transforming inventory items!!!");
            }
        }
        return true;
    }
    
    public boolean transform(InventoryItem item) {
        if (!isValidTransformation(item)) {
            return false;
        }
        List<InventoryItem> neededItems = new ArrayList<>();
        Iterator<ProductComponent> children = item.getGlobalItem().getTreeNode().children();
        while (children.hasNext()) {
            ProductComponent child = children.next();
            GlobalInventoryItem globalItem = child.getNode();
            double requiredQuantity = child.getRequiredQuantity();
            neededItems.add(new InventoryItem(globalItem, item.getQuantityOnHand() * requiredQuantity, item.getBoundQuantity() * requiredQuantity, item.getUnit()));            
        }
        if (!output(neededItems)) {
            return false;
        }
        if (!input(item)) {
            input(neededItems);
            return false;
        }
        return true;
    }
    
    public boolean isValidUnbounds(List<InventoryItem> items) {
        if (items.isEmpty()) {
            return false;
        }
        for (InventoryItem item : items) {
            if (!isValidUnbound(item)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidUnbound(InventoryItem item) {
        InventoryItem inventoryItem = get(item);
        return inventoryItem != null && inventoryItem.isValidUnbound(item);
    }
    
    public boolean isValidBounds(List<InventoryItem> items) {
        if (items.isEmpty()) {
            return false;
        }
        for (InventoryItem item : items) {
            if (!isValidBound(item)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidBound(InventoryItem item) {
        InventoryItem inventoryItem = get(item);
        return inventoryItem != null && inventoryItem.isValidBound(item);
    }
    
    public boolean isValidInputs(List<InventoryItem> items) {
        if (items.isEmpty()) {
            return false;
        }
        for (InventoryItem item : items) {
            if (!isValidInput(item)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidInput(InventoryItem item) {
        InventoryItem inventoryItem = get(item);
        return inventoryItem != null && inventoryItem.isValidInput(item);
    }
    
    public boolean isValidOutputs(List<InventoryItem> items) {
        if (items.isEmpty()) {
            return false;
        }
        for (InventoryItem item : items) {
            if (!isValidOutput(item)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidOutput(InventoryItem item) {
        InventoryItem inventoryItem = get(item);
        return inventoryItem != null && inventoryItem.isValidOutput(item);
    }
    
    public boolean isValidTransformations(List<InventoryItem> items) {
        if (items.isEmpty()) {
            return false;
        }
        for (InventoryItem item : items) {
            if (!isValidTransformation(item)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidTransformation(InventoryItem item) {
        if (!isValidInput(item)) {
            return false;
        }
        Iterator<ProductComponent> children = item.getGlobalItem().getTreeNode().children();
        while (children.hasNext()) {
            ProductComponent child = children.next();
            GlobalInventoryItem globalItem = child.getNode();
            double requiredQuantity = child.getRequiredQuantity();
            InventoryItem neededItem = new InventoryItem(globalItem, item.getQuantityOnHand() * requiredQuantity, item.getBoundQuantity() * requiredQuantity, item.getUnit());
            if (!isValidOutput(neededItem)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean contains(List<OrderItem> items) {
        return contains(items, true);
    }
    
    public boolean contains(List<OrderItem> items, boolean onHand) {
        for (OrderItem item : items) {
            if (!contains(item, onHand)) {
                return false;
            } 
        }
        return true;
    }
    
    public boolean contains(GlobalInventoryItem item, double quantity, Unit unit) {
        return contains(new OrderItem(item, quantity, unit), true);
    }
    
    public boolean contains(GlobalInventoryItem item, double quantity, Unit unit, boolean onHand) {
        return contains(new OrderItem(item, quantity, unit), onHand);
    }
    
    public boolean contains(OrderItem item) {
        return contains(item, true);
    }
    
    public boolean contains(OrderItem item, boolean onHand) {
        if (!contains(item.getItem())) {
            return false;
        }
        for (InventoryItem inventoryItem : items) {
            if (inventoryItem.equals(item.getItem())) {
                double inventoryItemQuantity;
                if (onHand) {
                    inventoryItemQuantity = inventoryItem.getQuantityOnHand(item.getUnit());
                } else {
                    inventoryItemQuantity = inventoryItem.getBoundQuantity(item.getUnit());
                }
                if (inventoryItemQuantity > item.getQuantity()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean contains(GlobalInventoryItem item) {
        return contains(new InventoryItem(item));
    }
    
    public boolean contains(InventoryItem item) {
        return items.contains(item);
    }
    
    public InventoryItem get(String itemReference) {
        return get(new GlobalInventoryItem(itemReference, 0, null, 0, null));
    }
    
    public InventoryItem get(GlobalInventoryItem item) {
        return get(new InventoryItem(item));
    }
    
    public InventoryItem get(InventoryItem item) {
        for (InventoryItem inventoryItem : items) {
            if (item.equals(inventoryItem)) {
                return inventoryItem;
            }
        }
        return null;
    }
    
    public boolean isEmpty() {
        if (items.isEmpty()) {
            return true;
        }
        for (InventoryItem item : items) {
            if (item.getQuantityOnHand() != 0) {
                return false;
            }
        }
        return true;
    }
    
    public int size() {
        return items.size();
    }
    
    protected void add(InventoryItem item) {
        if (items.contains(item)) {
            return;
        }
        items.add(item);
    }
    
    protected void addAll(List<InventoryItem> items) {
        for (InventoryItem item : items) {
            add(item);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Inventory && equals((Inventory) obj);
    }
    
    public boolean equals(Inventory inventory) {
        return inventory != null && globalInventory.equals(inventory.globalInventory) && agent.equals(inventory.agent);
    }
    
    @Override
    public String toString() {
        return agent + " Inventory (" + items.size() + ")";
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public void setCode(long code) {
        this.code = code;
    }

    public GlobalInventory getGlobalInventory() {
        return globalInventory;
    }

    public void setGlobalInventory(GlobalInventory globalInventory) {
        this.globalInventory = globalInventory;
    }

    public A getAgent() {
        return agent;
    }

    public void setAgent(A agent) {
        this.agent = agent;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }
    
}
