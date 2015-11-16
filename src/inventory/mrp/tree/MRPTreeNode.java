/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.mrp.tree;

import dao.Codeable;
import inventory.GlobalInventoryItem;
import inventory.util.Unit;
import inventory.util.UnitConverter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author adrianohrl
 * @param <C>
 */
@Entity
public abstract class MRPTreeNode<C extends ProductComponent> implements Serializable, Codeable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    @ManyToOne
    private GlobalInventoryItem node;
    @ManyToOne
    private MRPTreeNode parent = null;
    private double leadTime = 0;
    @ManyToOne
    private Unit timeUnit;
    private boolean leaf;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<C> children = new ArrayList<>();

    public MRPTreeNode() {
    }
    
    private MRPTreeNode(GlobalInventoryItem node, boolean leaf) {
        this.node = node;
        this.leadTime = node.getLeadTime();
        this.timeUnit = node.getTimeUnit();
        this.leaf = leaf;
        if (leaf) {
            children = null;
        }
    }

    /**
     * Este construtor deve ser usado apenas quando está se criando o GlobalInventoryItem e quando leaf for false
     * @param node
     */
    protected MRPTreeNode(GlobalInventoryItem node) {
        this(node, false);
        this.node.setTreeNode(this);
    }

    protected MRPTreeNode(GlobalInventoryItem node, MRPTreeNode parent, boolean leaf) {
        this(node, leaf);
        this.parent = parent;
    }
    
    public boolean isRoot() {
        return parent == null;
    }
    
    public double getLeadTime(Unit timeUnit) {
        UnitConverter converter = new UnitConverter(this.timeUnit, timeUnit);
        return converter.convert(leadTime);
    }
    
    public void addChild(C child) {
        child.setParent(this);
        children.add(child);
    }
    
    public void addChildren(List<C> children) {
        for (C child : children) {
            addChild(child);
        }
    }

    @Override
    public String toString() {
        return node.toString() + ": " + leadTime + " [" + timeUnit + "]";
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public void setCode(long code) {
        this.code = code;
    }

    public GlobalInventoryItem getNode() {
        return node;
    }

    public void setNode(GlobalInventoryItem node) {
        this.node = node;
    }

    public MRPTreeNode getParent() {
        return parent;
    }

    public void setParent(MRPTreeNode parent) {
        this.parent = parent;
    }

    public double getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(double leadTime) {
        this.leadTime = leadTime;
    }

    public Unit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(Unit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public List<C> getChildren() {
        return children;
    }

    public void setChildren(List<C> children) {
        this.children = children;
    }
    
    public Iterator<C> children() {
        return children.iterator();
    }
    
}
