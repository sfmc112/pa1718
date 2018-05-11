/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model;

import pkg9cs.model.boardspaces.Square;
import pkg9cs.model.boardspaces.CloseCombat;
import pkg9cs.model.boardspaces.Circle;
import pkg9cs.model.elements.Weapon;

/**
 *
 * @author sarah
 */
public class EnemyTrack extends Track {

    public EnemyTrack(Weapon w) {
        super();

        track.add(new CloseCombat());
        track.add(new Circle());
        track.add(new Square());
        track.add(new Square());
        track.add(new Square(w));
    }
  
    public boolean getEnemyOnCircleSpace(){
        return getBoardspaceType() instanceof Circle;
    }
    
    public boolean getEnemyOnCloseCombatSpace(){
        return getBoardspaceType() instanceof CloseCombat;
    }

}
