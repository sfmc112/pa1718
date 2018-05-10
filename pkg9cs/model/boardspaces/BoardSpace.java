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
public abstract class BoardSpace {

    private Element el = null;

    public BoardSpace(Element el) {
        this.el = el;
    }

    public Element getElement() {
        return el;
    }

    public void setElement(Element el) {
        this.el = el;
    }
    
    public boolean isEmpty(){
        return el == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("This is a BoardSpace!\n");

        return sb.toString();
    }

}
