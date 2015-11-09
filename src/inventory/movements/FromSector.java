/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.movements;

import inventory.Inventory;
import inventory.SectorInventory;
import inventory.agents.Responsible;
import inventory.orders.AbstractOrder;
import java.util.Calendar;
import javax.persistence.Entity;

/**
 *
 * @author adrianohrl
 * @param <D>
 */
@Entity
public abstract class FromSector<D extends Inventory> extends Movement<SectorInventory, D> {

    public FromSector() {
    }

    public FromSector(Responsible responsible, SectorInventory source, D destination) {
        super(responsible, source, destination);
    }

    public FromSector(Responsible responsible, AbstractOrder dueToOrder, SectorInventory source, D destination) {
        super(responsible, dueToOrder, source, destination);
    }

    public FromSector(Responsible responsible, SectorInventory source, D destination, Calendar movementDate) {
        super(responsible, source, destination, movementDate);
    }

    public FromSector(Responsible responsible, AbstractOrder dueToOrder, SectorInventory source, D destination, Calendar movementDate) {
        super(responsible, dueToOrder, source, destination, movementDate);
    }
    
}
