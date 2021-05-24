/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos;

/**
 *
 * @author jhonny
 */
public class Retorno {
    private String id;
    private String token;
    private String valor;
    private String linea;
    private String col;
    
    public Retorno(String valor, String linea, String col){
        this.valor = valor;
        this.linea = linea;
        this.col = col;
    }
    
    public Retorno(String token, String valor, String linea, String col){
        this.token = token;
        this.valor = valor;
        this.linea = linea;
        this.col = col;
    }
    
    public Retorno(String id, String token, String valor, String linea, String col){
        this.id = id;
        this.token = token;
        this.valor = valor;
        this.linea = linea;
        this.col = col;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the linea
     */
    public String getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }

    /**
     * @return the col
     */
    public String getCol() {
        return col;
    }

    /**
     * @param col the col to set
     */
    public void setCol(String col) {
        this.col = col;
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
}
