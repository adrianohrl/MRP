/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.movements;

import inventory.SectorInventory;
import inventory.agents.Responsible;
import inventory.orders.AbstractOrder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author adrianohrl
 */
public class InsideTheSector extends FromSectorToSector {

    public InsideTheSector() {
    }

    public InsideTheSector(Responsible responsible, SectorInventory sector) {
        super(responsible, sector, sector);
    }

    public InsideTheSector(Responsible responsible, AbstractOrder dueToOrder, SectorInventory sector) {
        super(responsible, dueToOrder, sector, sector);
    }

    public InsideTheSector(Responsible responsible, SectorInventory sector, Calendar movementDate) {
        super(responsible, sector, sector, movementDate);
    }

    public InsideTheSector(Responsible responsible, AbstractOrder dueToOrder, SectorInventory sector, Calendar movementDate) {
        super(responsible, dueToOrder, sector, sector, movementDate);
    }
    
    @Override
    public boolean isValid() {
        return getResponsible() != null && getSource() != null && getSource().equals(getDestination()) && getItems() != null && !isEmpty();
    }

    @Override
    public boolean move() {
        return false; // pq não é válido fazer movimentaćão dentro do mesmo sector
    }
    
    public boolean unbound() {
        return isValid() && getSource().unbound(getItems());
    }
    
    public boolean bound() {
        return isValid() && getDueToOrder() != null && getSource().bound(getItems());
    }
    
    public boolean transform() {
        return isValid() && getSource().transform(getItems());
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss"); 
        return "Inventory Movement on " + formatter.format(getMovementDate().getTime()) + " by " + getResponsible() + " inside " + getSource().getAgent();
    }
    
}
