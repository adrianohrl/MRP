/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.movements;

import inventory.ClientInventory;
import inventory.SectorInventory;
import inventory.agents.Responsible;
import inventory.orders.AbstractOrder;
import java.util.Calendar;

/**
 *
 * @author adrianohrl
 */
public class FromClientToSector extends ToSector<ClientInventory> {

    public FromClientToSector() {
    }

    public FromClientToSector(Responsible responsible, ClientInventory source, SectorInventory destination) {
        super(responsible, source, destination);
    }

    public FromClientToSector(Responsible responsible, AbstractOrder dueToOrder, ClientInventory source, SectorInventory destination) {
        super(responsible, dueToOrder, source, destination);
    }

    public FromClientToSector(Responsible responsible, ClientInventory source, SectorInventory destination, Calendar movementDate) {
        super(responsible, source, destination, movementDate);
    }

    public FromClientToSector(Responsible responsible, AbstractOrder dueToOrder, ClientInventory source, SectorInventory destination, Calendar movementDate) {
        super(responsible, dueToOrder, source, destination, movementDate);
    }
    
}
