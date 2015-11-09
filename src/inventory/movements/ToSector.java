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
 * @param <S>
 */
@Entity
public abstract class ToSector<S extends Inventory> extends Movement<S, SectorInventory> {

    public ToSector() {
    }

    public ToSector(Responsible responsible, S source, SectorInventory destination) {
        super(responsible, source, destination);
    }

    public ToSector(Responsible responsible, AbstractOrder dueToOrder, S source, SectorInventory destination) {
        super(responsible, dueToOrder, source, destination);
    }

    public ToSector(Responsible responsible, S source, SectorInventory destination, Calendar movementDate) {
        super(responsible, source, destination, movementDate);
    }

    public ToSector(Responsible responsible, AbstractOrder dueToOrder, S source, SectorInventory destination, Calendar movementDate) {
        super(responsible, dueToOrder, source, destination, movementDate);
    }
    
}
