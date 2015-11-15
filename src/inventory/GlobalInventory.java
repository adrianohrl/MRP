/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import inventory.agents.Filial;
import inventory.orders.AbstractOrder;
import inventory.movements.Movement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author adrianohrl
 */
@Entity
public class GlobalInventory implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    @OneToOne
    private Filial filial;
    @ManyToMany
    private List<GlobalInventoryItem> items = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventory> inventories = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AbstractOrder> orders = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movement> movements = new ArrayList<>();
   
    public GlobalInventory() {
    }

    public GlobalInventory(Filial filial) {
        this.filial = filial;
    }
    
    public boolean contains(GlobalInventoryItem item) {
        return items.contains(item);
    }
    
    public boolean contains(Inventory inventory) {
        return inventories.contains(inventory);
    }
    
    public int size() {
        return items.size();
    }
    
    public void add(GlobalInventoryItem item) {
        if (contains(item)) {
            return;
        }
        for (Inventory inventory : inventories) {
            inventory.add(new InventoryItem(item));
        }
        items.add(item);
    }
    
    public void add(Inventory inventory) {
        if (contains(inventory)) {
            return;
        }
        for (GlobalInventoryItem item : items) {
            inventory.add(new InventoryItem(item));
        }
        inventories.add(inventory);
    }
    
    public void add(AbstractOrder order) {
        orders.add(order);
    }
    
    public void add(Movement movement) {
        movements.add(movement);
    }
    
    public void addAllItems(List<GlobalInventoryItem> items) {
        for (GlobalInventoryItem item : items) {
            add(item);
        }
    }
    
    public void addAllInventories(List<Inventory> inventories) {
        for (Inventory inventory : inventories) {
            add(inventory);
        }
    }
    
    public void addAllOrders(List<AbstractOrder> orders) {
        for (AbstractOrder order : orders) {
            add(order);
        }
    }
    
    public void addAllMovements(List<Movement> movements) {
        for (Movement movement : movements) {
            add(movement);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof GlobalInventory && equals((GlobalInventory) obj);
    }
    
    public boolean equals(GlobalInventory globalInventory) {
        return globalInventory != null && filial.equals(filial);
    }
    
    @Override
    public String toString() {
        String str = filial + " Global Inventory:";
        str += "\n\tItems: (" + items.size() + ")";
        for (GlobalInventoryItem item : items) {
            str += "\n\t\t" + item + ":";
            str += "\n\t\t\tGross Requeriment: " + item.getTotalGrossRequirement() + " [" + item.getQuantityUnit() + "]";
            str += "\n\t\t\tSchedule Receipt: " + item.getTotalScheduledReceipt() + " [" + item.getQuantityUnit() + "]";
            str += "\n\t\t\tQuantity on Hand: " + item.getQuantityOnHand() + " [" + item.getQuantityUnit() + "]";
            str += "\n\t\t\tBound Quantity: " + item.getBoundQuantity() + " [" + item.getQuantityUnit() + "]";
            str += "\n\t\t\tNet Requeriment: " + item.getNetRequirement() + " [" + item.getQuantityUnit() + "]";
        }
        str += "\n\tInventories: (" + inventories.size() + ")";
        for (Inventory inventory : inventories) {
            str += "\n\t\t" + inventory + " items: (" + inventory.size() + ")";
            List<InventoryItem> inventoryItems = inventory.getItems();
            for (InventoryItem item : inventoryItems) {
                str += "\n\t\t\t" + item;
            }
        }
        str += "\n\tOrders: (" + orders.size() + ")";
        for (AbstractOrder order : orders) {
            str += "\n\t\t" + order.getReference();
        }
        str += "\n\tMovements: (" + movements.size() + ")";
        for (Movement movement : movements) {
            str += "\n\t\t" + movement;
        }
        return str;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public List<GlobalInventoryItem> getItems() {
        return items;
    }

    public void setItems(List<GlobalInventoryItem> items) {
        this.items = items;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public List<AbstractOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<AbstractOrder> orders) {
        this.orders = orders;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }
    
}
