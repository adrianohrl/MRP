/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import dao.Codeable;
import inventory.orders.AbstractOrder;
import inventory.util.Unit;
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
public class GrossRequirement implements Serializable, Codeable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    @ManyToOne
    private AbstractOrder order;
    private double quantity;
    @ManyToOne
    private Unit unit;

    public GrossRequirement() {
    }

    public GrossRequirement(AbstractOrder order, double quantity, Unit unit) {
        this.order = order;
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public void setCode(long code) {
        this.code = code;
    }

    public AbstractOrder getOrder() {
        return order;
    }

    public void setOrder(AbstractOrder order) {
        this.order = order;
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
    
}
