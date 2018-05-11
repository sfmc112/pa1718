/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model;

import pkg9cs.model.boardspaces.Castle;
import pkg9cs.model.boardspaces.EnemyLine;
import pkg9cs.model.boardspaces.Tunnel;
import pkg9cs.model.elements.Element;
import pkg9cs.model.elements.Soldiers;

/**
 *
 * @author sarah
 */
public class InvadingTrack extends Track{

    public InvadingTrack(Element e) {
        super();
        
        track.add(new Castle(e));
        track.add(new Tunnel());
        track.add(new Tunnel());
        track.add(new EnemyLine());
    }
    
    public void captureSoldiers(){
        removeElement();
        track.get(0).setElement(new Soldiers()); //Adicionar novos soldados no Castle Space
    }
    
    public boolean areSoldiersOnEnemyLines(){
        return getBoardspaceType() instanceof EnemyLine;
    }

    @Override
    public boolean onStartingSpace() {
        return getBoardspaceType() instanceof Castle;
    }
    
    
}
