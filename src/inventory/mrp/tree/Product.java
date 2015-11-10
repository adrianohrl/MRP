/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.mrp.tree;

import inventory.GlobalInventoryItem;
import javax.persistence.Entity;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Product extends MRPTreeNode<ProductComponent> {

    public Product() {
    }

    public Product(GlobalInventoryItem node) {
        super(node);
    }

    @Override
    public String toString() {
        String str = super.toString() + ":";
        for (ProductComponent component : getChildren()) {
            str += "\n\t" + component;
        }
        return str;
    }
    
}
