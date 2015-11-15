/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.orders;

import inventory.GlobalInventoryItem;
import inventory.util.Unit;
import java.io.Serializable;
import java.text.DecimalFormat;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author adrianohrl
 */
@Entity
public abstract class AbstractTransaction implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    @ManyToMany
    private List<OrderItem> items = new ArrayList<>();
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar transactionDate = new GregorianCalendar();
    private String reason = "";
    private float percentage = 0;

    public AbstractTransaction() {
    }
    
    public AbstractTransaction(String reason) {
        this.reason = reason;
    }
    
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
    
    public OrderItem get(OrderItem item) {
        for (OrderItem i : items) {
            if (i.equals(item)) {
                return i;
            }
        }
        return null;
    }
    
    public OrderItem get(GlobalInventoryItem item) {
        for (OrderItem i : items) {
            if (item.equals(i.getItem())) {
                return i;
            }
        }
        return null;
    }
    
    public int size() {
        return items.size();
    }
    
    protected void add(GlobalInventoryItem item, double quantity, Unit unit) {
        items.add(new OrderItem(item, quantity, unit));
    }
    
    protected void add(OrderItem item) {
        items.add(item);
    }
    
    public boolean isValid() {
        for (OrderItem item : items) {
            if (!item.isValid()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean contains(GlobalInventoryItem item) {
        return contains(new OrderItem(item, 0, null));
    }
    
    public boolean contains(OrderItem item) {
        return items.contains(item);
    }
    
    @Override
    public String toString() {
        DecimalFormat decimalFormatter = new DecimalFormat("0.00");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");  
        return this.getClass().getSimpleName() + " on " + dateFormatter.format(transactionDate.getTime()) + " of " + size() + " items (" + decimalFormatter.format(100 * percentage) + " %)";
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Calendar getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Calendar transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
    
}
