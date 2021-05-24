/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneracionHtml;

/**
 *
 * @author jhonny
 */
public class Token {
    private String nombre;
    private String lexema; //valor token
    private int yycolum;
    private int yyline;
    
    public Token(String lexema, int yyline, int yycolum){
        this.lexema = lexema;
        this.yycolum = yycolum;
        this.yyline = yyline;
    }
    
    public Token(String nombre, String lexema, int yyline, int yycolum){
        this.nombre = nombre;
        this.lexema = lexema;
        this.yyline = yyline;
        this.yycolum = yycolum;
    }

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
     * @return the yycolum
     */
    public int getYycolum() {
        return yycolum;
    }

    /**
     * @param yycolum the yycolum to set
     */
    public void setYycolum(int yycolum) {
        this.yycolum = yycolum;
    }

    /**
     * @return the yyline
     */
    public int getYyline() {
        return yyline;
    }

    /**
     * @param yyline the yyline to set
     */
    public void setYyline(int yyline) {
        this.yyline = yyline;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
