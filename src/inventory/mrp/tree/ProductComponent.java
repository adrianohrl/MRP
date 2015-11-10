/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.mrp.tree;

import inventory.GlobalInventoryItem;
import inventory.util.Unit;
import inventory.util.UnitConverter;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author adrianohrl
 * @param <C>
 */
@Entity
public abstract class ProductComponent<C extends ProductComponent> extends MRPTreeNode<C> {
    
    private double requiredQuantity;
    @ManyToOne
    private Unit quantityUnit;

    public ProductComponent() {
    }

    /**
     * Somente para a criação da árvore que será associada a um GlobalInventoryItem
     * @param node
     * @param requiredQuantity
     * @param quantityUnit 
     */
    protected ProductComponent(GlobalInventoryItem node, double requiredQuantity, Unit quantityUnit) {
        super(node);
        this.requiredQuantity = Math.abs(requiredQuantity);
        this.quantityUnit = quantityUnit;
    }

    protected ProductComponent(GlobalInventoryItem node, MRPTreeNode parent, double requiredQuantity, Unit quantityUnit, boolean leaf) {
        super(node, parent, leaf);
        this.requiredQuantity = Math.abs(requiredQuantity);
        this.quantityUnit = quantityUnit;
    }
    
    public double getRequiredQuantity(Unit unit) {
        UnitConverter converter = new UnitConverter(quantityUnit, unit);
        return converter.convert(requiredQuantity);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + requiredQuantity + " [" + quantityUnit + "]";
    }

    public double getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(double requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public Unit getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(Unit quantityUnit) {
        this.quantityUnit = quantityUnit;
    }
    
}
