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
public class Listas {
    private ArrayList<Conjunto> conjuntos;
    private ArrayList<Sentencia> sentencias;
    private ArrayList<Retorno> retAux;//es para agregar las funciones reservadas
    private Erors err;
    
    public Listas(){
        //Integer.parseInt();
        //conjuntos.get(0).toString();
        conjuntos = new ArrayList<>();
        sentencias = new ArrayList<>();
        retAux = new ArrayList<>();
    }
        
    public void inicializarLista(){
        conjuntos.clear();
        sentencias.clear();
        retAux.clear();
//        conjuntos = new ArrayList();
//        sentencias = new ArrayList();
        err = null;
    }
    
    public void AddConjuntos(Conjunto c){
        conjuntos.add(c);
    }
    
    public void AddSentencias(Sentencia s){
        sentencias.add(s);
    }

    /**
     * @return the conjuntos
     */
    public ArrayList<Conjunto> getConjuntos() {
        return conjuntos;
    }

    /**
     * @param conjuntos the conjuntos to set
     */
    public void setConjuntos(ArrayList<Conjunto> conjuntos) {
        this.conjuntos = conjuntos;
    }

    /**
     * @return the sentencias
     */
    public ArrayList<Sentencia> getSentencias() {
        return sentencias;
    }

    /**
     * @param sentencias the sentencias to set
     */
    public void setSentencias(ArrayList<Sentencia> sentencias) {
        this.sentencias = sentencias;
    }

    /**
     * @return the err
     */
    public Erors getErr() {
        return err;
    }

    /**
     * @param err the err to set
     */
    public void setErr(Erors err) {
        this.err = err;
    }

    /**
     * @return the retAux
     */
    public ArrayList<Retorno> getRetAux() {
        return retAux;
    }

    /**
     * @param retAux the retAux to set
     */
    public void setRetAux(ArrayList<Retorno> retAux) {
        this.retAux = retAux;
    }
}
