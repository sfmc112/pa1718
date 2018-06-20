/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs.ui.gui;

import java.awt.Component;
import java.awt.Dimension;

/**
 *
 * @author sarah
 */
public abstract class DimensionClass implements Constants{

    public static void setAllSizes(Component component, int width, int height) {
        Dimension d = new Dimension(width,height);
        
        component.setMinimumSize(d);
        component.setMaximumSize(d);
        component.setPreferredSize(d);
    }
    
    public static void setMinAndPreferredSize(Component component, int width, int height) {
        Dimension d = new Dimension(width,height);
        
        component.setMinimumSize(d);
        component.setPreferredSize(d);
    }
}
