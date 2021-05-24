/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos;

import Thompson.Automata;
import Thompson.Tabla;
import java.util.ArrayList;

/**
 *
 * @author jhonny
 */
public class Sentencia {
    private String id;
    private Automata expAFN;
    private Automata expAFD;
    private Tabla trans;
    private Retorno ret;//solo 1 retorno por sentencia
    private ArrayList<Retorno> reservada;//puede que este vacio
    
//    public static void main(String[] args) {
////        Retorno a = new Retorno();
////        Object obj = a;
////        Sentencia s = (Sentencia) obj;
//    }
    
    public Sentencia(String id, Automata afn, Automata afd, Tabla tran){
        this.reservada = new ArrayList();
        this.id = id;
        this.expAFN = afn;
        this.expAFD = afd;
        this.trans = tran;
    }
    
    public void addReservada(Retorno r){      
        getReservada().add(r);
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
     * @return the expAFN
     */
    public Automata getExpAFN() {
        return expAFN;
    }

    /**
     * @param expAFN the expAFN to set
     */
    public void setExpAFN(Automata expAFN) {
        this.expAFN = expAFN;
    }

    /**
     * @return the expAFD
     */
    public Automata getExpAFD() {
        return expAFD;
    }

    /**
     * @param expAFD the expAFD to set
     */
    public void setExpAFD(Automata expAFD) {
        this.expAFD = expAFD;
    }

    /**
     * @return the ret
     */
    public Retorno getRet() {
        return ret;
    }

    /**
     * @param ret the ret to set
     */
    public void setRet(Retorno ret) {
        this.ret = ret;
    }

    /**
     * @return the reservada
     */
    public ArrayList<Retorno> getReservada() {
        return reservada;
    }

    /**
     * @param reservada the reservada to set
     */
    public void setReservada(ArrayList<Retorno> reservada) {
        this.reservada = reservada;
    }

    /**
     * @return the trans
     */
    public Tabla getTrans() {
        return trans;
    }

    /**
     * @param trans the trans to set
     */
    public void setTrans(Tabla trans) {
        this.trans = trans;
    }
}
