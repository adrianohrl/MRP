/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import dao.Codeable;
import inventory.util.Unit;
import inventory.util.UnitConverter;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author adrianohrl
 */
@Entity
public class InventoryItem implements Serializable, Codeable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    @ManyToOne
    private GlobalInventoryItem globalItem;
    private double boundQuantity = 0;
    private double quantityOnHand = 0;
    @ManyToOne
    private Unit unit; 

    public InventoryItem() {
    }

    public InventoryItem(GlobalInventoryItem globalItem) {
        this.globalItem = globalItem;
        this.unit = globalItem.getQuantityUnit();
    }

    public InventoryItem(GlobalInventoryItem globalItem, double quantityOnHand, double boundQuantity, Unit unit) {
        this.globalItem = globalItem;
        this.quantityOnHand = Math.abs(quantityOnHand);
        this.boundQuantity = Math.abs(boundQuantity);
        this.unit = unit;
    }
    
    protected boolean unbound(InventoryItem item) {
        if (!isValidUnbound(item)) {
            return false;
        }
        if (!globalItem.unbound(item.getBoundQuantity(globalItem.getQuantityUnit()))) {
            throw new RuntimeException("Global or some other inventory has bee corrupted!!!");
        }
        quantityOnHand += item.getBoundQuantity(unit);
        boundQuantity -= item.getBoundQuantity(unit);
        return true;
    }
    
    protected boolean bound(InventoryItem item) {
        if (!isValidBound(item)) {
            return false;
        }
        if (!globalItem.bound(item.getQuantityOnHand(globalItem.getQuantityUnit()))) {
            throw new RuntimeException("Global or some other inventory has bee corrupted!!!");
        }
        quantityOnHand -= item.getQuantityOnHand(unit);
        boundQuantity += item.getQuantityOnHand(unit);
        return true;
    }
    
    protected boolean input(InventoryItem item) {
        if (!isValidInput(item)) {
            return false;
        }
        if (!globalItem.input(item.getQuantityOnHand(globalItem.getQuantityUnit()), item.getBoundQuantity(globalItem.getQuantityUnit()))) {
            throw new RuntimeException("Global or some other inventory has bee corrupted!!!");
        }
        quantityOnHand += item.getQuantityOnHand(unit);
        boundQuantity += item.getBoundQuantity(unit);
        return true;
    }
    
    protected boolean output(InventoryItem item) {
        if (!isValidOutput(item)) {
            return false;
        }
        if (!globalItem.output(item.getQuantityOnHand(globalItem.getQuantityUnit()), item.getBoundQuantity(globalItem.getQuantityUnit()))) {
            throw new RuntimeException("Global or some other inventory has bee corrupted!!!");
        }
        quantityOnHand -= item.getQuantityOnHand(unit);
        boundQuantity -= item.getBoundQuantity(unit);
        return true;
    }
    
    public boolean isValidUnbound(InventoryItem item) {
        return equals(item) && item.getBoundQuantity(unit) >= 0 && boundQuantity >= item.getBoundQuantity(unit);
    }
    
    public boolean isValidBound(InventoryItem item) {
        return equals(item) && item.getQuantityOnHand(unit) >= 0 && quantityOnHand >= item.getQuantityOnHand(unit);
    }
    
    public boolean isValidInput(InventoryItem item) {
        return equals(item) && item.getQuantityOnHand(unit) >= 0 && item.getBoundQuantity(unit) >= 0;
    }
    
    public boolean isValidOutput(InventoryItem item) {
        return equals(item) && quantityOnHand >= item.getQuantityOnHand(unit) && boundQuantity >= item.getBoundQuantity(unit);
    }

    public double getQuantityOnHand(Unit unit) {
        UnitConverter converter = new UnitConverter(this.unit, unit);
        return converter.convert(quantityOnHand);
    }

    public double getBoundQuantity(Unit unit) {
        UnitConverter converter = new UnitConverter(this.unit, unit);
        return converter.convert(boundQuantity);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof InventoryItem && equals((InventoryItem) obj);
    }
    
    public boolean equals(InventoryItem item) {
        return item != null & equals(item.globalItem);
    }
    
    public boolean equals(GlobalInventoryItem item) {
        return item != null & globalItem.equals(item);
    }
    
    @Override
    public String toString() {
        return globalItem.toString() + " (on hand: " + quantityOnHand + " [" + unit + "], bound: " + boundQuantity + " [" + unit + "])";
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public void setCode(long code) {
        this.code = code;
    }

    public GlobalInventoryItem getGlobalItem() {
        return globalItem;
    }

    public void setGlobalItem(GlobalInventoryItem globalItem) {
        this.globalItem = globalItem;
    }

    public double getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(double quantity) {
        this.quantityOnHand = quantity;
    }

    public double getBoundQuantity() {
        return boundQuantity;
    }

    public void setBoundQuantity(double boundQuantity) {
        this.boundQuantity = boundQuantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
}
