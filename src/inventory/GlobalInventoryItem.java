package inventory;

import inventory.mrp.tree.MRPTreeNode;
import inventory.mrp.tree.ProductComponent;
import inventory.util.Unit;
import inventory.util.UnitConverter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author adrianohrl
 */
@Entity
public class GlobalInventoryItem implements Serializable {
    
     @Id
    private String reference;
    private double quantityOnHand = 0;
    private double boundQuantity = 0;
    private double safetyQuantity = 0;
    private double leadTime = 0;
    @ManyToOne
    private Unit quantityUnit; // onHand, bound, safety, and netRequirement unit
    @ManyToOne
    private Unit timeUnit; // in seconds, minutes, hours, days, weeks, months, years, etc.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduledReceipt> scheduledReceipts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GrossRequirement> grossRequirements = new ArrayList<>();
    @OneToOne
    private MRPTreeNode treeNode = null;
    
    public GlobalInventoryItem() {
    }

    public GlobalInventoryItem(String reference, double safetyQuantity, Unit quantityUnit, double leadTime, Unit timeUnit) {
        this.reference = reference;
        this.safetyQuantity = Math.abs(safetyQuantity);
        this.quantityUnit = quantityUnit;
        this.leadTime = Math.abs(leadTime);
        this.timeUnit = timeUnit;
    }
    
    protected boolean unbound(double quantity) {
        if (quantity < 0 || boundQuantity < quantity) {
            return false;
        }
        quantityOnHand += quantity;
        boundQuantity -= quantity;
        return true;
    }
    
    protected boolean bound(double quantity) {
        if (quantity < 0 || quantityOnHand < quantity) {
            return false;
        }
        quantityOnHand -= quantity;
        boundQuantity += quantity;
        return true;
    }
    
    protected boolean input(double quantityOnHand, double boundQuantity) {
        if (quantityOnHand < 0 || boundQuantity < 0) {
            return false;
        }
        this.quantityOnHand += quantityOnHand;
        this.boundQuantity += boundQuantity;
        return true;
    }
    
    protected boolean output(double quantityOnHand, double boundQuantity) {
        if (this.quantityOnHand < quantityOnHand || this.boundQuantity < boundQuantity) {
            return false;
        }
        this.quantityOnHand -= quantityOnHand;
        this.boundQuantity -= boundQuantity;
        return true;
    }
    
    public void addGrossRequirement(GrossRequirement grossRequirement) {
        double previousNetRequirement = getNetRequirement();
        grossRequirements.add(grossRequirement);
        double currentNetRequirement = getNetRequirement();
        if (treeNode.isLeaf() || getNetRequirement() == 0) {
            return;
        }
        Iterator<ProductComponent> children = treeNode.children();
        while (children.hasNext()) {
            ProductComponent child = children.next();
            GlobalInventoryItem item = child.getNode();
            GrossRequirement childGrossRequirement = new GrossRequirement(grossRequirement.getOrder(), (currentNetRequirement - previousNetRequirement) * child.getRequiredQuantity(), child.getQuantityUnit());
            item.addGrossRequirement(childGrossRequirement);
        }
    }
    
    public double getTotalGrossRequirement() {
        return getTotalGrossRequirement(quantityUnit);
    }
    
    public double getTotalGrossRequirement(Unit unit) {
        double total = 0;
        UnitConverter converter = new UnitConverter(null, unit);
        for (GrossRequirement grossRequirement : grossRequirements) {
            converter.setFrom(grossRequirement.getUnit());
            total += converter.convert(grossRequirement.getQuantity());
        }
        return total;
    }
    
    public void update() {
        Calendar now = new GregorianCalendar();
        for (int i = 0; i < scheduledReceipts.size(); i++) {
            ScheduledReceipt scheduledReceipt = scheduledReceipts.get(i);
            if (now.after(scheduledReceipt.getReceiptDate())) {
                UnitConverter converter = new UnitConverter(scheduledReceipt.getUnit(), quantityUnit);
                quantityOnHand += converter.convert(scheduledReceipt.getQuantity());
                scheduledReceipts.remove(i);
                i--;
            }
        }
    }
    
    public void addScheduledReceipt(ScheduledReceipt scheduledReceipt) {
        scheduledReceipts.add(scheduledReceipt);
        update();
    }
    
    public double getTotalScheduledReceipt() {
        return getTotalScheduledReceipt(quantityUnit);
    }
    
    public double getTotalScheduledReceipt(Calendar deadline) {
        return getTotalScheduledReceipt(quantityUnit, deadline);
    }
    
    public double getTotalScheduledReceipt(Unit unit) {
        return getTotalScheduledReceipt(unit, null);
    }
    
    public double getTotalScheduledReceipt(Unit unit, Calendar deadline) {
        double total = 0;
        UnitConverter converter = new UnitConverter(null, unit);
        for (ScheduledReceipt scheduledReceipt : scheduledReceipts) {
            if (deadline == null || deadline.after(scheduledReceipt.getReceiptDate())) {
                converter.setFrom(scheduledReceipt.getUnit());
                total += converter.convert(scheduledReceipt.getQuantity());
            }
        }
        return total;
    }

    public double getQuantityOnHand(Unit unit) {
        UnitConverter converter = new UnitConverter(quantityUnit, unit);
        return converter.convert(quantityOnHand);
    }

    public double getBoundQuantity(Unit unit) {
        UnitConverter converter = new UnitConverter(quantityUnit, unit);
        return converter.convert(boundQuantity);
    }
    
    public double getSafetyQuantity(Unit unit) {
        UnitConverter converter = new UnitConverter(quantityUnit, unit);
        return converter.convert(safetyQuantity);
    }
    
    public double getLeadTime(Unit unit) {
        UnitConverter converter = new UnitConverter(timeUnit, unit);
        return converter.convert(leadTime);
    }
    
    public double getNetRequirement() {
        return getNetRequirement(quantityUnit);
    }
    
    public double getNetRequirement(Unit unit) {
        double netRequirement = getTotalGrossRequirement(unit) - getTotalScheduledReceipt(unit) - getQuantityOnHand(unit) - getSafetyQuantity(unit);
        if (netRequirement < 0) {
            netRequirement = 0;
        }
        if (getQuantityOnHand(unit) < getSafetyQuantity(unit)) {
            netRequirement += getSafetyQuantity(unit);
        }
        return netRequirement;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof InventoryItem && equals((GlobalInventoryItem) obj);
    }
    
    public boolean equals(GlobalInventoryItem item) {
        return item != null & item.reference.equals(reference);
    }
    
    @Override
    public String toString() {
        return reference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(double quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public double getBoundQuantity() {
        return boundQuantity;
    }

    public void setBoundQuantity(double boundQuantity) {
        this.boundQuantity = boundQuantity;
    }

    public double getSafetyQuantity() {
        return safetyQuantity;
    }

    public void setSafetyQuantity(double safetyQuantity) {
        this.safetyQuantity = safetyQuantity;
    }

    public double getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(double leadTime) {
        this.leadTime = leadTime;
    }

    public Unit getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(Unit quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public Unit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(Unit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public List<ScheduledReceipt> getScheduledReceipts() {
        return scheduledReceipts;
    }

    public void setScheduledReceipts(List<ScheduledReceipt> scheduledReceipts) {
        this.scheduledReceipts = scheduledReceipts;
    }

    public List<GrossRequirement> getGrossRequirements() {
        return grossRequirements;
    }

    public void setGrossRequirements(List<GrossRequirement> grossRequirements) {
        this.grossRequirements = grossRequirements;
    }

    public MRPTreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(MRPTreeNode treeNode) {
        this.treeNode = treeNode;
    }
    
}
