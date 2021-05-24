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
public class Eror {
    private String tipo;
    private String yytext;
    private int yyline;
    private int yycolum;
    
    public Eror(String tipo, String yytext, int yyline, int yycolum){
        this.tipo = tipo;
        this.yytext = yytext;
        this.yyline = yyline;
        this.yycolum = yycolum;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the yytext
     */
    public String getYytext() {
        return yytext;
    }

    /**
     * @param yytext the yytext to set
     */
    public void setYytext(String yytext) {
        this.yytext = yytext;
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
}
