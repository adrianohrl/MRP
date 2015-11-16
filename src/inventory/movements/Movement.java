/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.movements;

import dao.Codeable;
import inventory.GlobalInventoryItem;
import inventory.Inventory;
import inventory.InventoryItem;
import inventory.agents.Responsible;
import inventory.orders.AbstractOrder;
import inventory.util.Unit;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author adrianohrl
 * @param <S>
 * @param <D>
 */
@Entity
public abstract class Movement<S extends Inventory, D extends Inventory> implements Serializable, Codeable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    @ManyToOne
    private Responsible responsible;
    @ManyToOne
    private AbstractOrder dueToOrder = null;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar movementDate = new GregorianCalendar();
    private String observation = "";
    @ManyToOne(targetEntity = Inventory.class)
    private S source;
    @ManyToOne(targetEntity = Inventory.class)
    private D destination;
    @ManyToMany
    private List<InventoryItem> items = new ArrayList<>();

    public Movement() {
    }

    public Movement(Responsible responsible, S source, D destination) {
        this.responsible = responsible;
        this.source = source;
        this.destination = destination;
    }

    public Movement(Responsible responsible, AbstractOrder dueToOrder, S source, D destination) {
        this(responsible, source, destination);
        this.dueToOrder = dueToOrder;
    }

    public Movement(Responsible responsible, S source, D destination, Calendar movementDate) {
        this(responsible, source, destination);
        if (movementDate != null) {
            this.movementDate = movementDate;
        }
    }

    public Movement(Responsible responsible, AbstractOrder dueToOrder, S source, D destination, Calendar movementDate) {
        this(responsible, source, destination, movementDate);
        this.dueToOrder = dueToOrder;
    }
    
    public boolean isValid() {
        return responsible != null && source != null && destination != null && !source.equals(destination) && items != null && !isEmpty();
    }
    
    public boolean move() {
        if (!isValid() || !outputFromSource() ) {
            return false;
        }
        if (!inputToDestination()) {
            inputFromSource();
            return false;
        }
        return true;
    }
    
    protected boolean outputFromSource() {
        return source.output(items);
    }
    
    protected boolean inputFromSource() {
        return source.input(items);
    }
    
    protected boolean inputToDestination() {
        return destination.input(items);
    }
    
    public boolean contains(InventoryItem item) {
        return items.contains(item);
    }
    
    public void add(GlobalInventoryItem item, double quantityOnHand, double boundQuantity, Unit unit) {
        add(new InventoryItem(item, quantityOnHand, boundQuantity, unit));
    }
    
    public void add(InventoryItem item) {
        items.add(item);
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss"); 
        return "Inventory movement on " + formatter.format(movementDate.getTime()) + " by " + responsible + " from " + source.getAgent() + " to " + destination.getAgent();
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public void setCode(long code) {
        this.code = code;
    }

    public Responsible getResponsible() {
        return responsible;
    }

    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
    }

    public AbstractOrder getDueToOrder() {
        return dueToOrder;
    }

    public void setDueToOrder(AbstractOrder dueToOrder) {
        this.dueToOrder = dueToOrder;
    }

    public Calendar getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Calendar movementDate) {
        this.movementDate = movementDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public S getSource() {
        return source;
    }

    public void setSource(S source) {
        this.source = source;
    }

    public D getDestination() {
        return destination;
    }

    public void setDestination(D destination) {
        this.destination = destination;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }
    
}
