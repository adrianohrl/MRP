/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.orders;

import inventory.SectorInventory;
import inventory.agents.Sector;
import inventory.agents.Supplier;
import java.util.Calendar;
import javax.persistence.Entity;

/**
 *
 * @author adrianohrl
 */
@Entity
public class PurchaseOrder extends AbstractOrder<Supplier, Sector> {

    public PurchaseOrder() {
    }

    public PurchaseOrder(String reference, SectorInventory inventory, Calendar orderDate, Calendar deadline, Supplier supplier, Sector client) {
        super(reference, inventory, orderDate, deadline, supplier, client);
    }
    
    // tratar cancelamentos, entregas e devolućões (com relaćão aos fornecedores)

    @Override
    public void order() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
