/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneracionHtml;

import java.util.ArrayList;

/**
 *
 * @author jhonny
 */
public class Lista {//por aparete los errores lexicos y sintaticos
    public ArrayList<Token> lt = new ArrayList<>();
    public ArrayList<Eror> le = new ArrayList<>();
    
    public Lista(){
        
    }
    
    public void AddToken(Token t){
        lt.add(t);
    }
    
    public void AddError(Eror e){
        le.add(e);
    }
}
