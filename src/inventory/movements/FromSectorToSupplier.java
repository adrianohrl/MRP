/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.movements;

import inventory.SectorInventory;
import inventory.agents.Responsible;
import inventory.agents.Supplier;
import inventory.orders.AbstractOrder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.ManyToOne;

/**
 *
 * @author adrianohrl
 */
public class FromSectorToSupplier extends FromSector {
    
    @ManyToOne
    private Supplier destinedSupplier;

    public FromSectorToSupplier() {
    }

    public FromSectorToSupplier(Responsible responsible, SectorInventory source, Supplier destinedSupplier) {
        super(responsible, source, null);
        this.destinedSupplier = destinedSupplier;
    }

    public FromSectorToSupplier(Responsible responsible, AbstractOrder dueToOrder, SectorInventory source, Supplier destinedSupplier) {
        super(responsible, dueToOrder, source, null);
        this.destinedSupplier = destinedSupplier;
    }

    public FromSectorToSupplier(Responsible responsible, SectorInventory source, Supplier destinedSupplier, Calendar movementDate) {
        super(responsible, source, null, movementDate);
        this.destinedSupplier = destinedSupplier;
    }

    public FromSectorToSupplier(Responsible responsible, AbstractOrder dueToOrder, SectorInventory source, Supplier destinedSupplier, Calendar movementDate) {
        super(responsible, dueToOrder, source, null, movementDate);
        this.destinedSupplier = destinedSupplier;
    }
    
    @Override
    public boolean isValid() {
        return getResponsible() != null && getSource() != null && destinedSupplier != null && getItems() != null && !isEmpty();
    }
    
    @Override
    protected boolean inputToDestination() {
        return true;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss"); 
        return "Inventory Movement on " + formatter.format(getMovementDate().getTime()) + " by " + getResponsible() + " from " + getSource().getAgent() + " to " + destinedSupplier;
    }

    public Supplier getDestinedSupplier() {
        return destinedSupplier;
    }

    public void setDestinedSupplier(Supplier destinedSupplier) {
        this.destinedSupplier = destinedSupplier;
    }
    
}
