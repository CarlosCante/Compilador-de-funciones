/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class Nodo {

    public String token;
    public String lexema;
    public int fila;
    public int columna;
    public ArrayList<Nodo> hijos;
    
    public int estado;
    public String dato;
    
    public Nodo(String token, String lexema, int fila, int columna){
        this.token = token;
        this.lexema = lexema;
        this.fila = fila;
        this.columna = columna;
        this.hijos = new ArrayList<Nodo>();
        //String.valueOf(fila);
        //hijos.get(fila)
        
    }
    
    public Nodo(String dato, int estado){
        this.estado = estado;
        this.dato = dato;
        this.hijos = new ArrayList<Nodo>();
        //String.valueOf(fila);
    }
    
    public void AddHijo(Nodo nuevo){
        this.hijos.add(nuevo);
    }

    public void GraficarSintactico(){
        String grafica = "Digraph Arbol_Sintactico{\n\n" + GraficaNodos(this, "0") + "\n\n}";        
        GenerarDot(grafica);
    }

    private String GraficaNodos(Nodo nodo, String i){
        int k=0; 
        String r = "";
        String nodoTerm = nodo.token;
        nodoTerm = nodoTerm.replace("\"", "");
        r= "node" + i + "[label = \"" + nodoTerm + "\"];\n";
        
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r = r + "node" + i + " -> node" + i + k + "\n";
            r= r + GraficaNodos(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        
        if( !(nodo.lexema.equals("")) ){
            String nodoToken = nodo.lexema;
            nodoToken = nodoToken.replace("\"", "");
            r += "node" + i + "c[label = \"" + nodoToken + "\"];\n";
            r += "node" + i + " -> node" + i + "c\n";
        }
        
        return r;
    }
    
    public void GenerarBat(){
        String fic = "Arbol_Sintactico.bat";
        String dotPath  = "dot.exe";
        String fileInputPath = "Arbol_Sintactico.dot";
        String fileOutputPath = "Arbol_Sintactico.jpg";
        String tParam = "-Tjpg";
        String tOParam = "-o";
        
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
            fichero = new FileWriter(fic);
            escritor = new PrintWriter(fichero);
            escritor.println("@echo off");
            escritor.println(dotPath + " " + tParam + " " + fileInputPath + " " + tOParam + " " + fileOutputPath);
            escritor.println("exit");
            escritor.close();
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void GenerarJpg(){
        GenerarBat();
        Runtime aplicacion = Runtime.getRuntime(); 
        try{
            Thread.sleep(2000);
            aplicacion.exec("Arbol_Sintactico.bat");
            System.out.println("Ejecuto bat");
            try{
                Thread.sleep(10000);
                try {
                    Desktop.getDesktop().open(new File("Arbol_Sintactico.jpg"));
                    System.out.println("Genero jpg");
                } catch (IOException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    

    private void GenerarDot(String cadena){
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
            fichero = new FileWriter("Arbol_Sintactico.dot");
            escritor = new PrintWriter(fichero);
            escritor.println(cadena);
            escritor.close();
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
