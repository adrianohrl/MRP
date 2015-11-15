/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.orders;

import inventory.GlobalInventoryItem;
import inventory.SectorInventory;
import inventory.agents.Agent;
import inventory.util.CalendarManipulator;
import inventory.util.Unit;
import inventory.util.UnitConverter;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author adrianohrl
 * @param <S>
 * @param <C>
 */
@Entity
public abstract class AbstractOrder<S extends Agent, C extends Agent> implements Serializable {
    
    @Id
    private String reference;
    @ManyToOne
    private SectorInventory inventory;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar orderDate = new GregorianCalendar();
    private float deliveryPercentage = 0;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar deadline = new GregorianCalendar();
    @ManyToOne
    private S supplier;
    @ManyToOne
    private C client;
    @ManyToMany
    private List<OrderItem> items = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Delivery> deliveries = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Devolution> devolutions = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cancellation> cancellations = new ArrayList<>();

    public AbstractOrder() {
    }

    public AbstractOrder(String reference, SectorInventory inventory, Calendar orderDate, Calendar deadline, S supplier, C client) {
        this.reference = reference;
        this.inventory = inventory;
        this.orderDate = orderDate;
        this.deadline = deadline;
        this.supplier = supplier;
        this.client = client;
    }
    
    public abstract void order();
    
    public boolean insert(GlobalInventoryItem item, double quantity, Unit unit) {
        return insert(new OrderItem(item, quantity, unit));
    }
    
    public boolean insert(OrderItem item) {
        if (!item.isValid()) {
            return false;
        }
        add(item);
        return true;
    }
    
    public boolean deliver(Delivery delivery) {
        if (!delivery.isValid()) {
            return false;
        }
        add(delivery);
        return true;
    }
    
    public boolean devolve(Devolution devolution) {
        if (!devolution.isValid()) {
            return false;
        }
        add(devolution);
        return true;
    }
    
    public boolean cancel(Cancellation cancellation) {
        if (!cancellation.isValid()) {
            return false;
        }
        add(cancellation);
        return true;
    }
    
    public double getItemDeliveredQuantity(GlobalInventoryItem item, Unit unit) {
        return getItemDeliveredQuantity(new OrderItem(item, 0, unit));
    }
    
    public double getItemDeliveredQuantity(OrderItem item) {
        double quantity = 0;
        for (Delivery delivery : deliveries) {
            if (delivery.contains(item)) {
                OrderItem i = delivery.get(item);
                UnitConverter converter = new UnitConverter(i.getUnit(), item.getUnit());
                quantity += converter.convert(i.getQuantity());
            }
        }
        for (Devolution devolution : devolutions) {
            if (devolution.contains(item)) {
                OrderItem i = devolution.get(item);
                UnitConverter converter = new UnitConverter(i.getUnit(), item.getUnit());
                quantity -= converter.convert(i.getQuantity());
            }
        }
        return quantity;
    }
    
    protected OrderItem get(OrderItem item) {
        for (OrderItem i : items) {
            if (i.equals(item)) {
                return i;
            }
        }
        return null;
    }
    
    protected void add(GlobalInventoryItem item, double quantity, Unit unit) {
        add(new OrderItem(item, quantity, unit));
    }
    
    protected void add(OrderItem item) {
        if (contains(item)) {
            for (OrderItem i : items) {
                if (i.equals(item)) {
                    i.add(this, item);
                    return;
                }
            }
        } else if (item.getQuantity() > 0) {
            item.setDeliveryPercentage(0);
            items.add(item);
        }
    }
    
    protected void add(Delivery delivery) {
        deliveries.add(delivery);
        for (OrderItem deliveryItem : delivery.getItems()) {
            for (OrderItem item : items) {
                if (item.equals(deliveryItem)) {
                    item.deliver(delivery, deliveryItem);
                    break;
                }
            }
        }
        delivery.setPercentage(delivery.getPercentage() / size());
        deliveryPercentage += delivery.getPercentage();
    }
    
    protected void add(Devolution devolution) {
        devolutions.add(devolution);
        for (OrderItem devolutionItem : devolution.getItems()) {
            for (OrderItem item : items) {
                 if (item.equals(devolutionItem)) {
                    item.devolve(devolution, devolutionItem);
                 }
            }
        }
        devolution.setPercentage(devolution.getPercentage() / size());
        deliveryPercentage -= devolution.getPercentage();
    }
    
    protected void add(Cancellation cancellation) {
        cancellations.add(cancellation);
        for (OrderItem cancellationItem : cancellation.getItems()) {
            for (int i = size() - 1; i >= 0; i--) {
                OrderItem item = items.get(i);
                if (item.equals(cancellationItem)) {
                   item.cancel(cancellation, cancellationItem);
                   if (!item.isValid()) {
                       items.remove(i);
                   }
                }
            }
        }
        if (!isEmpty()) {
            cancellation.setPercentage(cancellation.getPercentage() / size());
        } else {
            cancellation.setPercentage(1);
        }
        if (cancellation.getPercentage() != 1) {
            deliveryPercentage /= (1 - cancellation.getPercentage());
        } else if (deliveryPercentage != 0) {
            deliveryPercentage = 1;
        } else {
            deliveryPercentage = 0;
        }
    }
    
    protected void realocate(AbstractOrder destination, OrderItem item) {
        OrderItem orderItem = get(item);
        if (orderItem == null) {
            return;
        }
        UnitConverter converter = new UnitConverter(orderItem.getUnit(), item.getUnit());
        double undeliveredQuantity = converter.convert(orderItem.getQuantity()) - getItemDeliveredQuantity(item);
        if (undeliveredQuantity <= 0) {
            return;
        }
        destination.add(new OrderItem(orderItem.getItem(), undeliveredQuantity, item.getUnit()));
        Cancellation cancellation = new Cancellation("Realocating to another order!!!");
        cancellation.add(item);
        cancel(cancellation);
        cancellations.remove(cancellation);
    }
    
    public boolean isEmpty() {
        if (items.isEmpty()) {
            return true;
        }
        for (OrderItem item : items) {
            if (item.isValid()) {
                return false;
            }
        }
        return true;
    }
    
    public int size() {
        return items.size();
    }
    
    public boolean contains(OrderItem item) {
        return items.contains(item);
    }

    public boolean isDelivered() {
        return Math.round(10000 * deliveryPercentage) / 100 >= 100; 
    }
    
    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("0.00");
        String str = "Order " + reference + ":";
        str += "\nOrdered on: " + CalendarManipulator.formatHourAndDate(orderDate);
        str += "\nDeadline: " + CalendarManipulator.formatHourAndDate(deadline);
        for (OrderItem item : items) {
            str += "\n\tItem " + item + ": Delivered " + getItemDeliveredQuantity(item) + " [" + item.getUnit() + "] of " + item.getQuantity() + " [" + item.getUnit() + "] (" + formatter.format(100 * item.getDeliveryPercentage()) + " %)";
        }
        str += "\n\tTotal Delivery Percentage: " + formatter.format(100 * deliveryPercentage) + " %";
        str += "\n\tDeliveries: " + deliveries.size();
        for (Delivery delivery : deliveries) {
            str += "\n\t\t" + delivery;
            for (OrderItem item : delivery.getItems()) {
                str += "\n\t\t\tItem " + item + ": Delivered " + item.getQuantity() + " [" + item.getUnit() + "]";
            }
        }
        str += "\n\tDevolutions:" + devolutions.size();
        for (Devolution devolution : devolutions) {
            str += "\n\t\t" + devolution;
            for (OrderItem item : devolution.getItems()) {
                str += "\n\t\t\tItem " + item + ": Devolved " + item.getQuantity() + " [" + item.getUnit() + "]";
            }
        }
        str += "\n\tCancellations:" + cancellations.size();
        for (Cancellation cancellation : cancellations) {
            str += "\n\t\t" + cancellation;
            for (OrderItem item : cancellation.getItems()) {
                str += "\n\t\t\tItem " + item + ": Cancelled " + item.getQuantity() + " [" + item.getUnit() + "]";
            }
        }
        return str;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public SectorInventory getInventory() {
        return inventory;
    }

    public void setInventory(SectorInventory inventory) {
        this.inventory = inventory;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public float getDeliveryPercentage() {
        return deliveryPercentage;
    }

    public void setDeliveryPercentage(float deliveryPercentage) {
        this.deliveryPercentage = deliveryPercentage;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public S getSupplier() {
        return supplier;
    }

    public void setSupplier(S supplier) {
        this.supplier = supplier;
    }

    public C getClient() {
        return client;
    }

    public void setClient(C client) {
        this.client = client;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public List<Devolution> getDevolutions() {
        return devolutions;
    }

    public void setDevolutions(List<Devolution> devolutions) {
        this.devolutions = devolutions;
    }

    public List<Cancellation> getCancellations() {
        return cancellations;
    }

    public void setCancellations(List<Cancellation> cancellations) {
        this.cancellations = cancellations;
    }
    
}
