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
public class ProductItem extends ProductComponent {

    public ProductItem() {
    }

    /**
     * Usado em momentos que o parent ainda não foi instanciado, mas está prestes a ser instanciado
     * @param node
     * @param requiredQuantity
     * @param quantityUnit 
     */
    public ProductItem(GlobalInventoryItem node, double requiredQuantity, Unit quantityUnit) {
        super(node, null, requiredQuantity, quantityUnit, true);
    }

    public ProductItem(GlobalInventoryItem node, MRPTreeNode parent, double requiredQuantity, Unit quantityUnit) {
        super(node, parent, requiredQuantity, quantityUnit, true);
    }
    
}
