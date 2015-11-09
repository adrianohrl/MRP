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

    public ProductModule(GlobalInventoryItem node, double requiredQuantity, Unit quantityUnit, double leadTime, Unit timeUnit) {
        super(node, null, requiredQuantity, quantityUnit, leadTime, timeUnit, false);
    }

    public ProductModule(GlobalInventoryItem node, MRPTreeNode parent, double requiredQuantity, Unit quantityUnit, double leadTime, Unit timeUnit) {
        super(node, parent, requiredQuantity, quantityUnit, leadTime, timeUnit, false);
    }
    
    public ProductModule(ProductModule module, double requiredQuantity, Unit quantityUnit, double leadTime, Unit timeUnit) {
        super(module.getNode(), null, requiredQuantity, quantityUnit, leadTime, timeUnit, false);
        getChildren().addAll(module.getChildren());
    }
    
    public ProductModule(ProductModule module, MRPTreeNode parent, double requiredQuantity, Unit quantityUnit, double leadTime, Unit timeUnit) {
        super(module.getNode(), parent, requiredQuantity, quantityUnit, leadTime, timeUnit, false);
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
