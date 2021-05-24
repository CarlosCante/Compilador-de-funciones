/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thompson;

import java.util.ArrayList;

/**
 *
 * @author jhonny
 */
public class Transicion {
    
    private Estado inicio;
    private Estado fin;
    private String simbolo;
    private ArrayList<Estado> listEnd; // es para el funcionamiento en la tabla del afd no optimo
    
    public Transicion(Estado inicio, Estado fin, String simbolo){
        this.inicio = inicio;
        this.fin = fin;
        this.simbolo = simbolo;
        this.listEnd = new ArrayList<>();
    }
    
    public Transicion(String simbolo){
        this.simbolo = simbolo;
        this.listEnd = new ArrayList<>();
    }
    
    /**
     * Mostrar la transicion
     * @return String toString
     */
    @Override
    public String toString(){
        return "(" + getInicio().getId() +"-" + getSimbolo()  +"-"+getFin().getId()+")";
    }
    
    public String DOT_String(){
        return (this.getInicio()+" -> "+this.getFin()+" [label=\""+this.getSimbolo()+"\"];");
    }

    /**
     * @return the inicio
     */
    public Estado getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Estado inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fin
     */
    public Estado getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(Estado fin) {
        this.fin = fin;
    }

    /**
     * @return the simbolo
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * @param simbolo the simbolo to set
     */
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    /**
     * @return the listEnd
     */
    public ArrayList<Estado> getListEnd() {
        return listEnd;
    }

    /**
     * @param listEnd the listEnd to set
     */
    public void setListEnd(ArrayList<Estado> listEnd) {
        this.listEnd = listEnd;
    }
}
