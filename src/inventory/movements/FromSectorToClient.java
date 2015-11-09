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
import javax.persistence.Entity;

/**
 *
 * @author adrianohrl
 */
@Entity
public class FromSectorToClient extends FromSector<ClientInventory> {

    public FromSectorToClient() {
    }

    public FromSectorToClient(Responsible responsible, SectorInventory source, ClientInventory destination) {
        super(responsible, source, destination);
    }

    public FromSectorToClient(Responsible responsible, AbstractOrder dueToOrder, SectorInventory source, ClientInventory destination) {
        super(responsible, dueToOrder, source, destination);
    }

    public FromSectorToClient(Responsible responsible, SectorInventory source, ClientInventory destination, Calendar movementDate) {
        super(responsible, source, destination, movementDate);
    }

    public FromSectorToClient(Responsible responsible, AbstractOrder dueToOrder, SectorInventory source, ClientInventory destination, Calendar movementDate) {
        super(responsible, dueToOrder, source, destination, movementDate);
    }
    
}
