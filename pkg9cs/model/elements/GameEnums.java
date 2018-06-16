/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.elements;

import java.io.Serializable;

/**
 *
 * @author sarah
 */
public interface GameEnums extends Serializable{

    enum Enemies {
        LADDER, BATTERING_RAM, SIEGE_TOWER
    }

    enum Status {
        WALL, MORALE, SUPPLY
    }
}
