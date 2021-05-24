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
public class Estado {
    
    private ArrayList<Estado> conj; //obtiene el conjunto de estados donde hay cerradura
    private boolean aceptacion;
    private String id; //estado tiene un id nombre del estado
    private ArrayList<Transicion> transiciones;//multiples transiciones que pueda tener
    private ArrayList<Estado> estadoAcept;//posiblemente se agregan estados de aceptacion para probar en la tabla
    
    public Estado(String id, ArrayList<Transicion> transiciones){
        this.id = id;
        this.transiciones = transiciones;
        this.conj = new ArrayList();
        this.estadoAcept = new ArrayList();
    }
    
    public Estado(){
        this.transiciones = new ArrayList();
        this.conj = new ArrayList();
        this.estadoAcept = new ArrayList();
    }
    
    public void AddTransicion(Transicion t){
        this.transiciones.add(t);
    }
    
    public Estado(String id){
        this.id = id;
        this.transiciones = new ArrayList();
        this.conj = new ArrayList();
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
     * @return the transiciones
     */
    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }

    /**
     * @param transiciones the transiciones to set
     */
    public void setTransiciones(ArrayList<Transicion> transiciones) {
        this.transiciones = transiciones;
    }
    
    @Override
    public String toString(){
        return this.id;
    }

    /**
     * @return the conj
     */
    public ArrayList<Estado> getConj() {
        return conj;
    }

    /**
     * @param conj the conj to set
     */
    public void setConj(ArrayList<Estado> conj) {
        this.conj = conj;
    }

    /**
     * @return the aceptacion
     */
    public boolean isAceptacion() {
        return aceptacion;
    }

    /**
     * @param aceptacion the aceptacion to set
     */
    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    /**
     * @return the estadoAcept
     */
    public ArrayList<Estado> getEstadoAcept() {
        return estadoAcept;
    }

    /**
     * @param estadoAcept the estadoAcept to set
     */
    public void setEstadoAcept(ArrayList<Estado> estadoAcept) {
        this.estadoAcept = estadoAcept;
    }
}
