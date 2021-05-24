/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thompson;

import Modulos.Archivo;
import java.util.ArrayList;

/**
 *
 * @author jhonny
 */
public class Automata {//Automata AFN
    private String pathTxT; //path del automata png
    private String pathTxtAFD;
    private String pathImg;//path del AFN
    private String pathImgAFD;
    private Estado inicial;
    private Estado fin;
    private ArrayList<Estado> estados;
    
    public Automata(){
        estados = new ArrayList();
    }
    
    public void AddEstados(Estado e){
        this.estados.add(e);
    }
    
    public String DotToString(){
        String val = "";
        for(int i = 0; i < estados.size(); i++){
            for(int j = 0; j < estados.get(i).getTransiciones().size(); j++){
                val += estados.get(i).getTransiciones().get(j).DOT_String() + "\n";
            }
        }
        return val;
    }
    
    public void CreateDot(String p){
//        String in = "digraph afn { \n rankdir=LR; \n" + DotToString() + "}";
        String in = "digraph afn { \n rankdir=LR; \n ";
        in += "node [shape = doublecircle]; " + fin.getId() + ";\n";  //se establece el estado final de aceptacion
        in += "node [shape = circle];\n"; //se establece de como biene los sig. estados
        in += "secret_node [style=invis]; " + "\n secret_node -> 0 [label=" +  (char)34 + "" + (char)34 + "];";
        //in += fin.getId() + "[style=filled peripheries=2];";
        in += DotToString() + "}";
        
        setPathTxT(Archivo.generarTxt(p, in));
        setPathImg(Archivo.generarImagen(pathTxT, p));
    }
    
    public void CreateDotAFD(String p){
//        String in = "digraph afn { \n rankdir=LR; \n" + DotToString() + "}";
        String in = "digraph afd { \n rankdir=LR; \n ";
        //in += "node [shape = doublecircle]; " + fin.getId() + ";\n";  //se establece el estado final de aceptacion
        in += "node [shape = circle];\n"; //se establece de como biene los sig. estados
        in += "secret_node [style=invis]; " + "\n secret_node -> 0 [label=" +  (char)34 + "" + (char)34 + "];";
        //in += fin.getId() + "[style=filled peripheries=2];";
        for(int i = 0; i < this.estados.size(); i++){
            if(this.estados.get(i).isAceptacion()){
                in += this.estados.get(i).getId() + "[style=filled peripheries=2];";
            }
        }
        
        in += DotToString() + "}";
        
        setPathTxtAFD(Archivo.generarTxt(p, in));
        setPathImgAFD(Archivo.generarImagen(pathTxtAFD, p));
    }
    
    public String getImagPath(String path, String name){
        return Archivo.generarImagen(path, name);
    }
        
    /**
     * @return the inicial
     */
    public Estado getInicial() {
        return inicial;
    }

    /**
     * @param inicial the inicial to set
     */
    public void setInicial(Estado inicial) {
        this.inicial = inicial;
    }

    /**
     * @return the estados
     */
    public ArrayList<Estado> getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
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
     * @return the pathTxT
     */
    public String getPathTxT() {
        return pathTxT;
    }

    /**
     * @param pathTxT the pathTxT to set
     */
    public void setPathTxT(String pathTxT) {
        this.pathTxT = pathTxT;
    }

    /**
     * @return the pathImg
     */
    public String getPathImg() {
        return pathImg;
    }

    /**
     * @param pathImg the pathImg to set
     */
    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    /**
     * @return the pathTxtAFD
     */
    public String getPathTxtAFD() {
        return pathTxtAFD;
    }

    /**
     * @param pathTxtAFD the pathTxtAFD to set
     */
    public void setPathTxtAFD(String pathTxtAFD) {
        this.pathTxtAFD = pathTxtAFD;
    }

    /**
     * @return the pathImgAFD
     */
    public String getPathImgAFD() {
        return pathImgAFD;
    }

    /**
     * @param pathImgAFD the pathImgAFD to set
     */
    public void setPathImgAFD(String pathImgAFD) {
        this.pathImgAFD = pathImgAFD;
    }
}
