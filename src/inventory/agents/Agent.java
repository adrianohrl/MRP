/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.agents;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author adrianohrl
 */
@Entity
public abstract class Agent implements Serializable {
    
    @Id
    private String name;

    public Agent() {
    }

    public Agent(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Agent && equals((Agent) obj);
    }
    
    public boolean equals(Agent agent) {
        return agent != null && name.equals(agent.name);
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
