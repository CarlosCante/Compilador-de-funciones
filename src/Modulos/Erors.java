/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos;

import Thompson.Automata;

/**
 *
 * @author jhonny
 */
public class Erors {
    private String id;
    private Automata exp;
    private Automata exafd;
    private String yyText;
    private String yyRow;
    private String yyCol;
    private boolean exist;//verifica si existe o no
    
    public Erors(String id, Automata exp, String yyText, String yyRow, String yyCol){
        this.id = id;
        this.exp = exp;
        this.yyText = yyText;
        this.yyRow = yyRow;
        this.yyCol = yyCol;
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
     * @return the exp
     */
    public Automata getExp() {
        return exp;
    }

    /**
     * @param exp the exp to set
     */
    public void setExp(Automata exp) {
        this.exp = exp;
    }

    /**
     * @return the yyText
     */
    public String getYyText() {
        return yyText;
    }

    /**
     * @param yyText the yyText to set
     */
    public void setYyText(String yyText) {
        this.yyText = yyText;
    }

    /**
     * @return the yyRow
     */
    public String getYyRow() {
        return yyRow;
    }

    /**
     * @param yyRow the yyRow to set
     */
    public void setYyRow(String yyRow) {
        this.yyRow = yyRow;
    }

    /**
     * @return the yyCol
     */
    public String getYyCol() {
        return yyCol;
    }

    /**
     * @param yyCol the yyCol to set
     */
    public void setYyCol(String yyCol) {
        this.yyCol = yyCol;
    }

    /**
     * @return the exist
     */
    public boolean isExist() {
        return exist;
    }

    /**
     * @param exist the exist to set
     */
    public void setExist(boolean exist) {
        this.exist = exist;
    }

    /**
     * @return the exafd
     */
    public Automata getExafd() {
        return exafd;
    }

    /**
     * @param exafd the exafd to set
     */
    public void setExafd(Automata exafd) {
        this.exafd = exafd;
    }
}
