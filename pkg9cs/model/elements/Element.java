/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.elements;

/**
 *
 * @author sarah
 */
public abstract class Element {
    private final String name;

    public Element(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
