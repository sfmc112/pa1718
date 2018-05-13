/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model;

import pkg9cs.model.boardspaces.Square;
import pkg9cs.model.elements.Element;

/**
 *
 * @author sarah
 */
public class StatusTrack extends Track {

    public StatusTrack(Element e) {
        super();

        int i;

        for (i = 0; i < NUM_SPACES - 1; i++) {
            track.add(new Square());
        }

        track.add(new Square(e));

    }

    public boolean checkSurrender() {
        return whereIsElement() == 0;
    }

}
