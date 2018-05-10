/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.model.boardspaces;

/**
 *
 * @author sarah
 */
public class Circle extends BoardSpace{
    
    public Circle() {
        super(null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Circle Space!\n");

        return sb.toString();
    }
    
}
