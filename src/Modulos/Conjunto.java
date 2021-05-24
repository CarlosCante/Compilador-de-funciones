/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos;

import java.util.ArrayList;

/**
 *
 * @author jhonny
 */
public class Conjunto {
    
    private ArrayList conj;
    private String lexema;
    private String id;
    
    public Conjunto(String lexema,String id){
        this.lexema = lexema;
        this.id = id;
        this.conj = new ArrayList();
        obtenerConj(lexema);
    }
    
    public void obtenerConj(String lexema){
        if(lexema.length() == 3){
            char in = lexema.charAt(0);
            int no = in;
            
            if(lexema.charAt(1) == 126){// si es igual a (~) entonces es un conjunto 
                if((no >= 65 && no <= 90) || (no >= 97 && no <= 122) || (no >= 48 && no <= 57)){//entonces es letra o digito
                    
                    char l1 = lexema.charAt(0);
                    char l2 = lexema.charAt(2);

                    while(l1 <= l2){
                        conj.add(l1);
                        l1 ++;
                    }
                    System.out.println(conj.size());
                }else{
                    //entonces recorrer el resto desde 33 a 125 para el conjunto
                    int l1 = lexema.charAt(0);
                    int l2 = lexema.charAt(2);
                    
                    while(l1 <= l2){
                        if(!((l1 >= 65 && l1 <= 90) || (l1 >= 97 && l1 <= 122) || (l1 >= 48 && l1 <= 57))){
                            conj.add(l1);
                        }
                        l1 ++;
                    }
                    System.out.println(conj.size());
                }
            }else{//si contiene comas
                String[] con = lexema.split(",");
                for(int i = 0; i < con.length; i++){
                    conj.add(con[i]);
                }
                System.out.println(conj.size());
            }
        }else{
            String[] con = lexema.split(",");
                for(int i = 0; i < con.length; i++){
                    conj.add(con[i]);
                }
                System.out.println(conj.size());
        }
    }
    
//        String c = String.valueOf(lexema.charAt(0));
//        for(int i = 0; i < lexema.length(); i++){
//	}
    /**
     * @return the lexema
     */
    public String getLexema() {
        return lexema;
    }

    /**
     * @param lexema the lexema to set
     */
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the conj
     */
    public ArrayList getConj() {
        return conj;
    }

    /**
     * @param conj the conj to set
     */
    public void setConj(ArrayList conj) {
        this.conj = conj;
    }
}
