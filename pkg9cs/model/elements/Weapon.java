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
public abstract class Weapon extends Element {

    protected final int strength;

    public Weapon(String name, int strength) {
        super(name);
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }
}
