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
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author adrianohrl
 */
@Entity
public class FromSupplierToSector extends ToSector {
    
    @ManyToOne
    private Supplier sourceSupplier;

    public FromSupplierToSector() {
    }

    public FromSupplierToSector(Responsible responsible, Supplier sourceSupplier, SectorInventory destination) {
        super(responsible, null, destination);
        this.sourceSupplier = sourceSupplier;
    }

    public FromSupplierToSector(Responsible responsible, AbstractOrder dueToOrder, Supplier sourceSupplier, SectorInventory destination) {
        super(responsible, dueToOrder, null, destination);
        this.sourceSupplier = sourceSupplier;
    }

    public FromSupplierToSector(Responsible responsible, Supplier sourceSupplier, SectorInventory destination, Calendar movementDate) {
        super(responsible, null, destination, movementDate);
        this.sourceSupplier = sourceSupplier;
    }

    public FromSupplierToSector(Responsible responsible, AbstractOrder dueToOrder, Supplier sourceSupplier, SectorInventory destination, Calendar movementDate) {
        super(responsible, dueToOrder, null, destination, movementDate);
        this.sourceSupplier = sourceSupplier;
    }
    
    @Override
    public boolean isValid() {
        return getResponsible() != null && sourceSupplier != null && getDestination() != null && getItems() != null && !isEmpty();
    }
    
    @Override
    protected boolean outputFromSource() {
        return true;
    }
    
    @Override
    protected boolean inputFromSource() {
        return true;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss"); 
        return "Inventory Movement on " + formatter.format(getMovementDate().getTime()) + " by " + getResponsible() + " from " + sourceSupplier + " to " + getDestination().getAgent();
    }

    public Supplier getSourceSupplier() {
        return sourceSupplier;
    }

    public void setSourceSupplier(Supplier sourceSupplier) {
        this.sourceSupplier = sourceSupplier;
    }
    
}
