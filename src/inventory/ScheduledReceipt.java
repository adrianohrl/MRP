/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import dao.Codeable;
import inventory.util.Unit;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author adrianohrl
 */
@Entity
public class ScheduledReceipt implements Serializable, Codeable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar receiptDate = new GregorianCalendar();
    private double quantity;
    @ManyToOne
    private Unit unit;

    public ScheduledReceipt() {
    }

    public ScheduledReceipt(Calendar receiptDate, double quantity, Unit unit) {
        this.receiptDate = receiptDate;
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

    public Calendar getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Calendar receiptDate) {
        this.receiptDate = receiptDate;
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
