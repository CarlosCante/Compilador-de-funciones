/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import GeneracionHtml.Eror;
import GeneracionHtml.Token;
import Modulos.Conjunto;
import Modulos.Retorno;
import Modulos.Sentencia;
import Thompson.Estado;
import Thompson.Transicion;
import gui.sistemaAnalitico;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jhonny
 */
public class Conexion {
    private final int PUERTO = 5000;
    public static final String HOST = "localhost";
    private boolean conect;
    private sistemaAnalitico sa;
    
    private String mensajeRecibido;
    private ArrayList<String> palabras;
    private ArrayList<Token> token;
    private ArrayList<Eror> error;
    private ServerSocket ss;
    private Socket sc;
    private DataOutputStream salidaServidor; //flujo de entrada
    
    public Conexion(sistemaAnalitico sa){
        this.sa = sa;
        mensajeRecibido = "";
        palabras = new ArrayList<>();
        token = new ArrayList<>();
        error = new ArrayList<>();
    }

    public void iniciarConexion(ArrayList<Sentencia> s, ArrayList<Conjunto> c){
       token.clear();
       error.clear();
        BufferedReader entrada;
        try{
            ss = new ServerSocket(PUERTO);
            sc = new Socket();
            System.out.println("Esperando una conexion:");
            
            sc = ss.accept();
            System.out.println("Un cliente se ha conectado.");
            
            entrada = new BufferedReader(new InputStreamReader(sc.getInputStream()));//flujo de entrada desde el cliente
            salidaServidor = new DataOutputStream(sc.getOutputStream());//salida de los datos
            System.out.println("Confirmando conexion al cliente...");
            
            int fila = 1;
            lectura(entrada, s, c, fila);//analizar para encontrar tokens y guardar tokens o errores
            
            String tok = prueba();//crearXmlTokens(); //path del xml tokens
            String err = crearXmlError();  //path del xml errores
            
            this.sa.setError(error);
            this.sa.setToken(token);
            
            JOptionPane.showMessageDialog(null, "Analisis terminado");
            
            try{
                Thread.sleep(1000);
            }catch(Exception ex){}
            salidaServidor.writeUTF(tok + "_" + err + "_enviado");//enviando al sistema transaccional
            System.out.println("Cerrando conexion...");
            
            ss.close();
        }catch(Exception ex){}
    }    
    
    public String crearXmlError(){
        String rretu = "";
        try{
            
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fact.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            Element errores = doc.createElement("errores");
            doc.appendChild(errores);
            
            for(int i = 0; i < error.size(); i++){
                Eror temp = error.get(i);
                Element errorn = doc.createElement("error");
                
                Element valor = doc.createElement("valor");
                valor.appendChild(doc.createTextNode(temp.getYytext()));
                errorn.appendChild(valor);
                
                Element yyrow = doc.createElement("yyrow");
                yyrow.appendChild(doc.createTextNode(String.valueOf(temp.getYyline())));
                errorn.appendChild(yyrow);
                
                Element yycol = doc.createElement("yycol");
                yycol.appendChild(doc.createTextNode(String.valueOf(temp.getYycolum())));
                errorn.appendChild(yycol);
                
                errores.appendChild(errorn);
            }
            
            TransformerFactory facto = TransformerFactory.newInstance();
            Transformer transform = facto.newTransformer();
            rretu = "C:\\xml\\errores.xml";  
            
            Source source = new DOMSource(doc);
            File file = new File("C:\\xml\\errores.xml");
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            
            Result result = new StreamResult(pw);
            
            transform.transform(source, result);
            
            fw.close();
            pw.close();
        }catch(Exception ex){
            
        }
        return rretu;
    }
    
    public String prueba(){
        
        String ret = "";
        try{
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fact.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            Element tokens = doc.createElement("tokens");
            doc.appendChild(tokens);
            
            for(int i = 0; i < token.size(); i++){
                Token temp = token.get(i);
                Element tk = doc.createElement("token");
                
                Element nameToken = doc.createElement("nombre");
                nameToken.appendChild(doc.createTextNode(temp.getNombre()));
                tk.appendChild(nameToken);
                
                Element valor = doc.createElement("valor");
                valor.appendChild(doc.createTextNode(temp.getLexema()));
                tk.appendChild(valor);
                
                Element yyrow = doc.createElement("yyrow");
                yyrow.appendChild(doc.createTextNode(String.valueOf(temp.getYyline())));
                tk.appendChild(yyrow);
                
                Element yycol = doc.createElement("yycol");
                yycol.appendChild(doc.createTextNode(String.valueOf(temp.getYycolum())));
                tk.appendChild(yycol);
                
                tokens.appendChild(tk);
            }
            
            TransformerFactory facto = TransformerFactory.newInstance();
            Transformer transform = facto.newTransformer();
            
            Source source = new DOMSource(doc);
            ret = "C:\\xml\\tokens.xml";
            File file = new File("C:\\xml\\tokens.xml");
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            
            Result result = new StreamResult(pw);
            
            transform.transform(source, result);
            
            fw.close();
            pw.close();
            
        }catch(Exception ex){
            
        }
        return ret;
    }
    
    public void lectura(BufferedReader br, ArrayList<Sentencia> s, ArrayList<Conjunto> c, int fila){ // analizar para encontrar tokens
        try{
            if(br.ready()){
                mensajeRecibido += br.readLine() + " "; //analizar por lineas
                
                if(!((int)mensajeRecibido.charAt(0) == 32 && mensajeRecibido.length() == 1)){
                    identificar(mensajeRecibido, s, c, fila);
                }
                mensajeRecibido = "";
                fila += 1;
                lectura(br, s, c, fila);
            }else{
                System.out.println("no esta listo");
            }
        }catch(Exception ex){}
    }
    
    public void identTokens(String entrada, ArrayList<Sentencia> s, ArrayList<Conjunto> c){
        //verificar si esta pertenece como un conjunto
        
        boolean aceptado = false;
        boolean tranEncontrado = false;
        String lexema = "";
        for(int z = 0; z < s.size(); z++){//recorre las sentencias
            Sentencia sen = s.get(z);//obtener sentencias
            
            Estado in = sen.getExpAFD().getInicial(); //para analizar desde el estado inicial
            
            int i = 0;
            // entrada = crear (ABC, 2000.00);
            while(i < entrada.length()){//recorrer la entrada por caracter
                String l = String.valueOf(entrada.charAt(i));
                lexema += l;
                //recorrer todos los conjuntos
                String idCon = null;//si es igual a null entonces no pertenece a ningun conjunto si no obtener el id del conjunto
                for(int j = 0; j < c.size(); j ++){
                    Conjunto con = c.get(j);
                    //recorre el conjunto que esta asociado
                    for(int k = 0; k < con.getConj().size(); k++){
                        if(l.equals(String.valueOf(con.getConj().get(k)))){
                            idCon = con.getId();
                            break;
                        }
                    }
                    if(idCon != null){
                        break;
                    }
                }
                if(idCon != null){//buscar la primera transicion
                    for(int k = 0; k < in.getTransiciones().size(); k++){//recorre las transiciones del automataa afd
                        Transicion t = in.getTransiciones().get(k);
                        if(idCon.equals(t.getSimbolo())){//si el idCon es igual al simbolo entonces el estado cambia para el fin de esa
                            //transicion
                            in = t.getFin();
                            tranEncontrado = true;
                            break;
                        }
                    }
                }else{//si no buscar transicion con la letra o numero de "l"
                    for(int k = 0; k < in.getTransiciones().size(); k++){//recorre las transiciones del automataa afd
                        Transicion t = in.getTransiciones().get(k);
                        if(l.equals(t.getSimbolo())){//si el idCon es igual al simbolo entonces el estado cambia para el fin de esa
                            //transicion
                            in = t.getFin();
                            tranEncontrado = true;
                            break;
                        }
                    }
                }
//                                (i+1) == entrada.length()
                if(entrada.charAt(i + 1) == 32){//verificar si la palabra es acepatado //mod verificar si encuentra un espacio
                    if(in.isAceptacion()){
                        JOptionPane.showMessageDialog(null, lexema + " aceptado");
                        //reinice la busquedad con sentencia
                        i++;
                        aceptado = true;
                        break;
                    }else{
                        JOptionPane.showMessageDialog(null, lexema + " error");
                        aceptado = false;
                    }
                }
                
//                if(!tranEncontrado){
//                    break;
//                }else{
//                    tranEncontrado = false;
//                }
                i++;
            }
            
//            if(aceptado){
//                //reconocer el tipo de token para guardar ¿es una reservada y la forma de retorno?
//                //deneter el analisis de sentencias
//                JOptionPane.showMessageDialog(null, lexema + " token aceptado identificador");
//                lexema = "";
//                //buscar el tipo de retorno
//                //break;
//            }else{
//                tranEncontrado = false;
//                aceptado = false;
//            }
        }
//        if(!aceptado){
//            JOptionPane.showMessageDialog(null, "token no aceptado");//entonces meter en el metodo de error
//        }
    }
    
    public void identificar(String entrada, ArrayList<Sentencia> s, ArrayList<Conjunto> c, int fila){
        boolean aceptado = false; boolean hayTransicion = false; boolean pasoTodo = false;
        String lexema = "";
        int posError = -10;
        int i = 0; //inicia el contador de la entrada.....i es la columna
        for(int z = 0; z < s.size(); z++){//recorre las sentencias
            Sentencia sen = s.get(z);//obtener sentencias
            Estado in = sen.getExpAFD().getInicial(); //para analizar desde el estado inicial
            
            while(i < entrada.length()){//recorrer la entrada por caracter
                String l = String.valueOf(entrada.charAt(i));
                lexema += l;
                //recorrer todos los conjuntos
                String idCon = null;//si es igual a null entonces no pertenece a ningun conjunto si no obtener el id del conjunto
                for(int j = 0; j < c.size(); j ++){
                    Conjunto con = c.get(j);
                    //recorre el conjunto que esta asociado
                    for(int k = 0; k < con.getConj().size(); k++){
                        if(l.equals(String.valueOf(con.getConj().get(k)))){
                            idCon = con.getId();
                            break;
                        }
                    }
                    if(idCon != null){
                        break;
                    }
                }
                if(idCon != null){//buscar la primera transicion
                    if(!pasoTodo){
                        for(int k = 0; k < in.getTransiciones().size(); k++){//recorre las transiciones del automataa afd
                            Transicion t = in.getTransiciones().get(k);
                            if(t.getSimbolo().equals("[:todo:]")){
                                if(!sen.getId().equals("error")){
                                    posError = i;
                                }
                                hayTransicion = true;
                                in = t.getFin();
                                pasoTodo = true;
                                break;
                            }else{
                                if(idCon.equals(t.getSimbolo())){//si el idCon es igual al simbolo entonces el estado cambia para el fin de esa
                                    //transicion
                                    if(!sen.getId().equals("error")){
                                        posError = i;
                                    }
                                    hayTransicion = true;
                                    in = t.getFin();
                                    break;
                                }
                            }
                        }
                    }else{
                        hayTransicion = true;
                    }
                }else{//si no buscar transicion con la letra o numero de "l"
                    if(!pasoTodo){
                        for(int k = 0; k < in.getTransiciones().size(); k++){//recorre las transiciones del automataa afd
                            Transicion t = in.getTransiciones().get(k);
                            if(t.getSimbolo().equals("[:todo:]")){
                                if(!sen.getId().equals("error")){
                                    posError = i;
                                }
                                hayTransicion = true;
                                in = t.getFin();
                                pasoTodo = true;
                                break;
                            }else{
                                if(l.equals(t.getSimbolo())){//si el idCon es igual al simbolo entonces el estado cambia para el fin de esa
                                    //transicion
                                    if(!sen.getId().equals("error")){
                                        posError = i;
                                    }
                                    hayTransicion = true;
                                    in = t.getFin();
                                    break;
                                }
                            }
                        }
                    }else{
                        hayTransicion = true;
                    }
                }
                    if(!hayTransicion){
                            i = i - (lexema.length() - 1);
                            lexema = "";
                            break;
                    }else{
                        hayTransicion = false;
                    }
                    
                if(entrada.charAt(i + 1) == 32){//verificar si la palabra es acepatado //mod verificar si encuentra un espacio
                    
                        if(in.isAceptacion()){
                            //verificar si existe palabras reservadas
                            if(!sen.getId().equals("error")){
                                boolean isReservada = false;

                                if(sen.getReservada() != null){
                                    for(int y = 0; y < sen.getReservada().size(); y++){
                                        Retorno rety = sen.getReservada().get(y);
                                        if(rety.getId().equals(lexema)){
                                            String nombreToken = rety.getToken();
                                            Token tA = new Token(nombreToken, lexema, fila, (i+1));
                                            getToken().add(tA);
                                            isReservada = true;
                                            break;
                                        }
                                    }
                                }

                                if(!isReservada){
                                    Retorno ret = sen.getRet();
                                    String nombreToken = ret.getToken();

                                    Token tA = new Token(nombreToken, lexema, fila, (i+1));

                                    getToken().add(tA);
                                }
                                //JOptionPane.showMessageDialog(null, lexema + "token aceptado fila, colum  ( " + fila + ", " + (i + 1));
                                //reinice la busquedad con sentencia
                                
                            }else{
                                Eror n = null;
                                if(posError == -10){
                                    n = new Eror("Lexico", String.valueOf(lexema.charAt(0)), fila, (i-(lexema.length()-1)+1));
                                    error.add(n);
                                }else{
                                    n = new Eror("Lexico", String.valueOf(entrada.charAt(posError + 1)), fila, (posError + 2));
                                    error.add(n);
                                }
                                posError = -10;
                                Token tA = new Token("tDesconocido", lexema, fila, (i+1));
                                token.add(tA);
                                //JOptionPane.showMessageDialog(null, n.getYytext() + " error " + tA.getLexema() + " desconocido");
                            }
                            pasoTodo = false;
                            lexema = "";
                            i++;
                            aceptado = true;
                        }else{
                            i = i - (lexema.length() - 1);
                            lexema = "";
                            aceptado = false;
                        }
                    
                    break;
                }
                i++;
            }
            if(aceptado){
                z = -1; //empezar desde la sentencia de inicio para la siguiente palabra
                aceptado = false;
                i++;
            }
            if (i == entrada.length()){
                break;
            }
            if(z+1 == s.size()){
                    pasoTodo = false;
                z = -1;
            }
        }
    }
    
    /**
     * @return the conect
     */
    public boolean isConect() {
        return conect;
    }

    /**
     * @param conect the conect to set
     */
    public void setConect(boolean conect) {
        this.conect = conect;
    }

    /**
     * @return the mensajeRecibido
     */
    public String getMensajeRecibido() {
        return mensajeRecibido;
    }

    /**
     * @return the palabras
     */
    public ArrayList<String> getPalabras() {
        return palabras;
    }

    /**
     * @param palabras the palabras to set
     */
    public void setPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }

    /**
     * @return the token
     */
    public ArrayList<Token> getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(ArrayList<Token> token) {
        this.token = token;
    }

    /**
     * @return the error
     */
    public ArrayList<Eror> getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(ArrayList<Eror> error) {
        this.error = error;
    }
}