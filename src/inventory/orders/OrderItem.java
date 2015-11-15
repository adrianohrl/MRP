/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.orders;

import inventory.GlobalInventoryItem;
import inventory.util.Unit;
import inventory.util.UnitConverter;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author adrianohrl
 */
@Entity
public class OrderItem implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    @ManyToOne
    private GlobalInventoryItem item;
    private double quantity;
    @ManyToOne
    private Unit unit;
    private double deliveryPercentage = 0;

    public OrderItem() {
    }

    public OrderItem(GlobalInventoryItem item, double quantity, Unit unit) {
        this.item = item;
        this.quantity = quantity;
        this.unit = unit;
    }
    
    protected void deliver(Delivery delivery, OrderItem item) {
        if (equals(item)) {
            deliver(delivery, item.quantity, item.unit);
        }
    }
    
    protected void deliver(Delivery delivery, double quantity, Unit unit) {
        if (quantity > 0) {
            UnitConverter converter = new UnitConverter(unit, this.unit);
            float percentage = (float) (converter.convert(quantity) / this.quantity);
            delivery.setPercentage(delivery.getPercentage() + percentage);
            deliveryPercentage += percentage;
        }
    }
    
    protected void devolve(Devolution devolution, OrderItem item) {
        if (equals(item)) {
            devolve(devolution, item.quantity, item.unit);
        }
    }
    
    protected void devolve(Devolution devolution, double quantity, Unit unit) {
        if (quantity > 0) {
            UnitConverter converter = new UnitConverter(unit, this.unit);
            float percentage = (float) (converter.convert(quantity) / this.quantity);
            devolution.setPercentage(devolution.getPercentage() + percentage);
            deliveryPercentage -= percentage;
        }
    }
    
    protected void cancel(Cancellation cancellation, OrderItem item) {
        if (equals(item)) {
            cancel(cancellation, item.quantity, item.unit);
        }
    }
    
    protected void cancel(Cancellation cancellation, double quantity, Unit unit) {
        if (quantity > 0) {
            UnitConverter converter = new UnitConverter(unit, this.unit);
            float percentage = (float) (converter.convert(quantity) / this.quantity);
            cancellation.setPercentage(cancellation.getPercentage() + percentage);
            if (percentage != 1) {
                deliveryPercentage /= (1 - percentage);
            } else if (deliveryPercentage != 0) {
                deliveryPercentage = 1;
            } else {
                deliveryPercentage = 0;
            }
            this.quantity -= quantity;            
        }
    }
    
    protected void add(AbstractOrder order, OrderItem item) {
        if (equals(item)) {
            add(order, item.quantity, item.unit);
        }
    }
    
    protected void add(AbstractOrder order, double quantity, Unit unit) {
        UnitConverter converter = new UnitConverter(unit, this.unit);
        deliveryPercentage /= (1 + converter.convert(quantity) / this.quantity);
        this.quantity += quantity;
    }
    
    public boolean isValid() {
        return item != null && quantity > 0 && unit != null;
    }

    public boolean isDeliveried() {
        return deliveryPercentage >= 1;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof OrderItem && equals((OrderItem) obj);
    }
    
    public boolean equals(OrderItem item) {
        return item != null && this.item.equals(item.getItem());
    }
    
    @Override
    public String toString() {
        return item.toString();
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public GlobalInventoryItem getItem() {
        return item;
    }

    public void setItem(GlobalInventoryItem item) {
        this.item = item;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public double getDeliveryPercentage() {
        return deliveryPercentage;
    }

    public void setDeliveryPercentage(double deliveryPercentage) {
        this.deliveryPercentage = deliveryPercentage;
    }
    
}
