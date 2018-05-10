/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.boardspaces;

import pkg9cs.model.elements.Element;

/**
 *
 * @author sarah
 */
public class Square extends BoardSpace{
    
    public Square() {
        super(null);
    }

    public Square(Element el) {
        super(el);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Square Space!\n");

        return sb.toString();
    }
    
}
