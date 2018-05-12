/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model;

import java.io.Serializable;
import pkg9cs.model.boardspaces.BoardSpace;
import pkg9cs.model.elements.Element;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sarah
 */
public abstract class Track implements Serializable {

    protected static final int NUM_SPACES = 5;
    protected static final int NUM_SPACES_INV_TRACK = 4;

    protected List<BoardSpace> track;

    public Track() {
        track = new ArrayList<>();
    }

    /**
     * Vai à procura da posição do elemento na Lista de BoardSpaces
     *
     * @see BoardSpace
     * @see EnemyBoard
     * @see StatusBoard
     * @return Indíce onde se encontra o elemento na track
     */
    public int whereIsElement() {
        for (BoardSpace boardSpace : track) {
            if (!boardSpace.isEmpty()) {
                return track.indexOf(boardSpace);
            }
        }
        //TODO exception
        return -1;
    }

    /**
     * Fazer avançar um Elemento na respetiva Track (no sentido decrescente)
     *
     * @see Element
     */
    public void advanceElement() {
        int index;

        if ((index = whereIsElement()) > 0) {
            track.get(index - 1).setElement(track.get(index).getElement());
            track.get(index).setElement(null);
        }
    }

    /**
     * Fazer recuar um Elemento na respetiva Track (no sentido crescente)
     *
     * @see Element
     */
    public void retreatElement() {
        int index;

        if ((index = whereIsElement()) < track.size() - 1) {
            track.get(index + 1).setElement(track.get(index).getElement());
            track.get(index).setElement(null);
        }
    }

    /**
     * Percorrer a Track para permitir descobrir o tipo do Elemento que está
     * nessa track
     *
     * @return Objecto do tipo Elemento que está contido na track
     */
    public Element getElementType() {
        for (BoardSpace boardSpace : track) {
            if (!boardSpace.isEmpty()) {
                return boardSpace.getElement();
            }
        }
        return null;
    }

    public BoardSpace getBoardspaceType() {
        for (BoardSpace boardSpace : track) {
            if (!boardSpace.isEmpty()) {
                return boardSpace;
            }
        }
        return null;
    }

    public void removeElement() {
        for (BoardSpace boardSpace : track) {
            if (!boardSpace.isEmpty()) {
                boardSpace.setElement(null);
            }
        }
    }

    public boolean onStartingSpace() {
        return (whereIsElement() == (track.size() - 1));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getElementType());
        str.append(" is on position ").append(whereIsElement());
        str.append("\t(").append(getBoardspaceType()).append(")\n");
        return str.toString();
    }

}
