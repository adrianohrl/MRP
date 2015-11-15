/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.orders;

import inventory.SectorInventory;
import inventory.agents.Sector;
import java.util.Calendar;

/**
 *
 * @author adrianohrl
 */
public class ManufacturingOrder extends AbstractOrder<Sector, Sector> {

    public ManufacturingOrder() {
    }

    public ManufacturingOrder(String reference, SectorInventory inventory, Calendar orderDate, Calendar deadline, Sector supplier, Sector client) {
        super(reference, inventory, orderDate, deadline, supplier, client);
    }
    
    // tratar cancelamentos, entregas e devolućões (com relaćão aos setores)
    
    // quando realizada uma nova entrega de compra, verificar se pode ser gerada uma nova order de fabricaćão
    
    // quando realizada uma nova entrega de fabricaćão, verificar se pode ser gerada uma nova ordem de fabricaćão

    @Override
    public void order() {
        getInventory().add(this);
    }
    
}
