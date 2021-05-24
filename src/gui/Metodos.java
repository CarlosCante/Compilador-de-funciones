/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author jhonny
 */
public class Metodos {
    
    public static String quitComillas(String dato){
        String val = "";
        
        for(int i = 0; i < dato.length(); i++){
            if(dato.charAt(i) != '"'){
                    val += dato.charAt(i);
            }
        }
        return val;
    }
    
    public static Image icono(String url, int width, int heigth){
        ImageIcon ico = new ImageIcon(url);
        Image img = ico.getImage();
        Image imagen = img.getScaledInstance(width, heigth, Image.SCALE_SMOOTH);
        return imagen;
    }
    
}
