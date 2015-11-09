/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.movements;

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
public class FromSectorToSector extends Movement<SectorInventory, SectorInventory> {

    public FromSectorToSector() {
    }

    public FromSectorToSector(Responsible responsible, SectorInventory source, SectorInventory destination) {
        super(responsible, source, destination);
    }

    public FromSectorToSector(Responsible responsible, AbstractOrder dueToOrder, SectorInventory source, SectorInventory destination) {
        super(responsible, dueToOrder, source, destination);
    }

    public FromSectorToSector(Responsible responsible, SectorInventory source, SectorInventory destination, Calendar movementDate) {
        super(responsible, source, destination, movementDate);
    }

    public FromSectorToSector(Responsible responsible, AbstractOrder dueToOrder, SectorInventory source, SectorInventory destination, Calendar movementDate) {
        super(responsible, dueToOrder, source, destination, movementDate);
    }
    
}
