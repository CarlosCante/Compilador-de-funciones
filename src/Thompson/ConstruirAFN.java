/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thompson;

import java.lang.reflect.Field;

/**
 *
 * @author jhonny
 */
public class ConstruirAFN {
    
    public static String EPSILON = "ε";
    
    /**
     * el parametro op recibe lo que realiza la expresion
     * regular. Para cerradura se usa solo el paramtero a
     * @param op
     * @param a
     * @param b
     * @return 
     */
    public static Automata ObtenerAFN(String op, Automata a, Automata b){        
        switch(op){
            case "*":
                return cerradura(a);
            case "|":
                return or(a, b);
            case ".":
                return por(a, b);
            default:
                return null;
        }
    }
    
    public static Automata autDif(Automata a){//crea un automata clonado
        Automata afn = new Automata();
        
        for(int i = 0; i < a.getEstados().size(); i ++){
            Estado temp = a.getEstados().get(i);
            afn.AddEstados(temp);
        }
        
        afn.setInicial(a.getInicial());
        afn.setFin(a.getFin());
                
        return afn;
    }
    
    public static Automata clonar(Automata a){//prueba de clonar automata1
        Automata b = new Automata();
        
        Estado in = new Estado(a.getEstados().get(0).getId());
        b.AddEstados(in);
        //se crea un estado inicial y se agrega
        Estado tin = in;
        Estado end = null;
        //se agrega todos los estados y se cambia el id
        for(int i = 0; i < a.getEstados().size() - 1; i++){
            end = new Estado(a.getEstados().get(i + 1).getId());
            
            for(int j = 0; j < a.getEstados().get(i).getTransiciones().size(); j++){
                tin.AddTransicion(new Transicion(tin, end, a.getEstados().get(i).getTransiciones().get(j).getSimbolo()));
            }
            
            tin = end;
            b.AddEstados(end);
        }
        
        b.setFin(end);
        
        return b;
    }
    
    public static Automata clonar1(Automata a){
        Automata afn = new Automata();
        
        for(int i = 0; i < a.getEstados().size(); i ++){
            Estado temp = a.getEstados().get(i);
            afn.AddEstados(temp);
        }
        
        afn.setInicial(a.getInicial());
        afn.setFin(a.getFin());
                
        return afn;
    }
    
    public static Automata Clonar(Automata object){
        Automata clon = null;
        
        try{
            clon = (Automata) object.getClass().newInstance();
        }catch(InstantiationException ex){
            ex.printStackTrace();
        }catch(IllegalAccessException ex){
            ex.fillInStackTrace();
        }
        
        for(Class obj = object.getClass(); !obj.equals(Object.class); obj = obj.getSuperclass()){
            for(Field field : obj.getDeclaredFields()){
                field.setAccessible(true);
                try{
                    field.set(clon, field.get(object));
                }catch(Throwable ex){
                }
            }
        }
        return clon;
    }
        
    public static Automata cerradura(Automata a){ //verificar cerradura de kleene
        Automata afn = new Automata();
        //es la cerradura de kleene
        Estado inicio = new Estado("0");
        afn.setInicial(inicio);
        afn.AddEstados(inicio);
        
        //recorrer estados y modificar el id que es el numero que lo identifica
        for(int i = 0; i < a.getEstados().size(); i++){
            Estado temp = a.getEstados().get(i);
            temp.setId(String.valueOf(i + 1));
            afn.AddEstados(temp);
        }
        
        Estado fin = new Estado(String.valueOf(a.getEstados().size() + 1));
        afn.AddEstados(fin);
        
        Estado antinicio = a.getInicial(); //se le agrega las transiciones con epsilon
        inicio.AddTransicion(new Transicion(inicio, antinicio, EPSILON));
        inicio.AddTransicion(new Transicion(inicio, fin, EPSILON));
        
        a.getFin().AddTransicion(new Transicion(a.getFin(), a.getInicial(), EPSILON));
        a.getFin().AddTransicion(new Transicion(a.getFin(), fin, EPSILON));
        
        afn.setFin(fin);
        
        return afn;
    }
    
    public static Automata por(Automata a, Automata b){
        Automata afn = new Automata();
        
        int i = 0; //contador de estados
        
        for (i = 0; i < a.getEstados().size(); i++){ //se le agrega los estados del automata a y se le cambia el id
            //toma un nuevo id el estado
            Estado temp = a.getEstados().get(i);
            temp.setId(String.valueOf(i));
            
            if(i == 0){//se le agrega el estado inicial
                afn.setInicial(temp);
            }
            
            if(i == a.getEstados().size() - 1){ //si esta en el estado final de a entonces agregar transicion con epsilon al automata b
                temp.AddTransicion(new Transicion(a.getFin(), b.getInicial(), ConstruirAFN.EPSILON));
            }
            afn.AddEstados(temp);
        }
        
        for(int j = 0; j < b.getEstados().size(); j++){ //se agrega los estados del autmata b
            Estado tmp = (Estado) b.getEstados().get(j);
            tmp.setId(String.valueOf(i));
            
            if(b.getEstados().size() - 1 == j){
                afn.setFin(tmp);
            }
            
            afn.AddEstados(tmp);
            i++;
        }
        
        return afn;
    }
    
    public static Automata or(Automata a, Automata b){
        Automata afn = new Automata();
        //es el nuevo estado del afn
        Estado in = new Estado(String.valueOf(0));
        //se crea una transicion con epsilon
        in.AddTransicion(new Transicion(in, a.getInicial(), ConstruirAFN.EPSILON));
        //se agrega el estado in
        //se establece como estado inicial
        afn.AddEstados(in);
        afn.setInicial(in);
        
        //agregar estados del primer automata al afn
        int i = 0;
        
        for(i = 0; i < a.getEstados().size(); i++){
            Estado temp = a.getEstados().get(i);
            temp.setId(String.valueOf(i + 1));
            afn.AddEstados(temp);
        }
        //agregar el estado final
        
        //agregar estados del segundo automata al afn
        for (int j=0; j < b.getEstados().size(); j++) {
            Estado tmp = (Estado) b.getEstados().get(j);
            tmp.setId(String.valueOf(i + 1));
            afn.AddEstados(tmp);
            i++;
        }        
        
        Estado nuevoFin = new Estado(String.valueOf(i + 1));
        i++;
        afn.setFin(nuevoFin);
        
        afn.AddEstados(nuevoFin);
        a.getFin().AddTransicion(new Transicion(a.getFin(), nuevoFin, ConstruirAFN.EPSILON));
        b.getFin().AddTransicion(new Transicion(b.getFin(), nuevoFin, ConstruirAFN.EPSILON));
        
        in.AddTransicion(new Transicion(in, b.getInicial(), ConstruirAFN.EPSILON));       
        
        return afn;
    }
}
