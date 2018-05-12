/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.events;

import java.io.Serializable;
import pkg9cs.model.GameData;

/**
 *
 * @author sarah
 */

public interface Event extends Serializable {

    public void executeEvent(GameData game);

    public int getSabotageDRM();

    public int getMoraleDRM();

    public int getCircleSpaceDRM();

    public int getCloseCombatSpaceDRM();

    public int getSupplyRaidDRM();

    public int getCoupureDRM();

    public int getLadderDRM();

    public int getRamDRM();

    public int getSiegeTowerDRM();

}
