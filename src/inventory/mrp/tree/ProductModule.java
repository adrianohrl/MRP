/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.mrp.tree;

import inventory.GlobalInventoryItem;
import inventory.util.Unit;
import javax.persistence.Entity;

/**
 *
 * @author adrianohrl
 */
@Entity
public class ProductModule extends ProductComponent<ProductItem> { /// nesse caso específico 

    public ProductModule() {
    }

    /**
     * Somente para a criação da árvore que será associada a um GlobalInventoryItem
     * @param node
     * @param requiredQuantity
     * @param quantityUnit 
     */
    public ProductModule(GlobalInventoryItem node, double requiredQuantity, Unit quantityUnit) {
        super(node, requiredQuantity, quantityUnit);
    }

    public ProductModule(GlobalInventoryItem node, MRPTreeNode parent, double requiredQuantity, Unit quantityUnit) {
        super(node, parent, requiredQuantity, quantityUnit, false);
    }
    
    /**
     * Somente para a criação da árvore que será associada a um GlobalInventoryItem
     * @param module
     * @param requiredQuantity
     * @param quantityUnit 
     */
    public ProductModule(ProductModule module, double requiredQuantity, Unit quantityUnit) {
        super(module.getNode(), requiredQuantity, quantityUnit);
        getChildren().addAll(module.getChildren());
    }
    
    public ProductModule(ProductModule module, MRPTreeNode parent, double requiredQuantity, Unit quantityUnit) {
        super(module.getNode(), parent, requiredQuantity, quantityUnit, false);
        addChildren(module.getChildren());
    }

    @Override
    public String toString() {
        String str = super.toString() + ":";
        for (ProductItem item : getChildren()) {
            str += "\n\t\t" + item + "";
        }
        return str;
    }
    
}
