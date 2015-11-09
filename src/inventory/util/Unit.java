/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.util;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Unit implements Serializable {
    
    @Id
    private String abbreviation;
    private String name;
    private boolean fractionary;

    public Unit() {
    }

    public Unit(String abbreviation, String name, boolean fractionary) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.fractionary = fractionary;
    }

    @Override
    public String toString() {
        return abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFractionary() {
        return fractionary;
    }

    public void setFractionary(boolean fractionary) {
        this.fractionary = fractionary;
    }
    
}
