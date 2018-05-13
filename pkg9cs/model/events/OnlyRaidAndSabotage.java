/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.events;

import pkg9cs.RaidAndSabotageException;
import pkg9cs.model.GameData;

/**
 *
 * @author sarah
 */
public class OnlyRaidAndSabotage extends EventAdapter {

    @Override
    public void executeEvent(GameData game) throws RaidAndSabotageException {
        throw new RaidAndSabotageException();
    }
}
