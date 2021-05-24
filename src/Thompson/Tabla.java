/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thompson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author jhonny
 */
public class Tabla {//revisar crear untomata
        
    private ArrayList<Estado> estCerradura = new ArrayList<>();
    private ArrayList<Transicion> terminales = new ArrayList<>();
    private Queue<Estado> mueves = new LinkedList<>();
    
    //private ArrayList<Estado> estCerraduraOptimo = new ArrayList<>();
    
    private Automata afn;
        
    public Tabla(Automata afn){//se crea una tabla donde tendran los metodos de cerradura y mueve
        this.afn = afn;
        this.estCerradura = new ArrayList<>();
        this.terminales = new ArrayList<>();
        this.mueves = new LinkedList<>();
    }
    
    public Tabla(){
        this.estCerradura = new ArrayList<>();
        this.terminales = new ArrayList<>();
        this.mueves = new LinkedList<>();
    }
    
    /**
     * Construccion del afd no optimizado
     * Falta verificar y asignar si es de aceptacion
     * autor jhonny
     * @param afn 
     */
    public void ConstruirAFD(Automata afn){
        this.afn = afn;
        //obtener tabla de terminales que serian las transiciones en la variable "terminales"
        this.ObtenerSimbolos(afn);
        //aplicar metodo de cerradura para el 1er estado
        Estado in = new Estado("0");
        System.out.println("cerradura 0");
        this.Cerradura(afn.getInicial(), in.getConj());
        //se agrega a la cola para realizar su respectivo mueve
        this.mueves.add(in);//solo cerraduras se agregan a la cola
        this.getEstCerradura().add(in);
        
        this.printConjL(in.getConj());
        
        int contEstado = 1;
        
        while(!this.mueves.isEmpty()){
            //se obtiene el estado que esta encolado que tiene conjuntos por el metodo de cerradura
            Estado cerradura = this.mueves.poll();
            
            //se realiza su respectivo mueve por cada simbolo
            for(int i = 0; i < this.getTerminales().size(); i++){
                //se crea un estado que retorne el conjunto de nuevo estado para encolar
                Transicion t = this.getTerminales().get(i); //se obtiene la transicion del simbolo
                
                ArrayList<Estado> conj = this.Mueve(cerradura.getConj(), t.getSimbolo());//mueve por cada simbolo del conjunto del estado
                //conj puede tener 2 estados
                //imprimir mueve
//                String varM = "";
//                for(int k = 0; k < conj.size(); k++){
//                    if(conj.get(k) != null){
//                        varM += conj.get(k).getId() + ", ";
//                    }
//                }
//                System.out.println("Mueve: " + varM);
                
                ArrayList<Estado> cErradura = new ArrayList<>(); //lista temporal donde se almacena los conjuntos de cerradura
                //aplicando cerradura al conjunto
                for(int j = 0; j < conj.size(); j++){
                    if(conj.get(j) != null){
                        this.Cerradura(conj.get(j), cErradura); //se aplica el metodo de cerradura
                    }
                }
                
                //verificar si existe la cerradura en la lista
                if(!existeConjL(cErradura)){
                    if(!cErradura.isEmpty()){
                        //si no existe entonces guardar en la cola y en la lista
                        Estado n = new Estado(String.valueOf(contEstado));
                        //System.out.println("cerradura " + contEstado);
                        contEstado ++;

                        n.setConj(cErradura);
                        this.printConjL(n.getConj());

                        this.mueves.add(n);//se encola el estado que tiene conjuntos
                        this.getEstCerradura().add(n); //se agrega a la lista de cerraduras
                        //se le coloca el fin de la transicion del simbolo
                        //t.setFin(n);
                        t.getListEnd().add(n);
                    }else{
                        t.getListEnd().add(null);
                    }
                    
                }else{
                    //cuando existe pasar la transicion
                    Estado ex = existConjL(cErradura);
                    
                    if(ex != null){
                        //System.out.println("cerradura existe " + ex.getId());
                        t.getListEnd().add(ex);
                        //t.setFin(ex);
                    }
                }
            }
        }
        String nombreFin = afn.getFin().getId();
        for(int i = 0; i < this.getEstCerradura().size(); i ++){
            Estado es = this.getEstCerradura().get(i);
            
            for(int j = 0; j < es.getConj().size(); j++){
                if(es.getConj().get(j).getId().equals(nombreFin)){
                    es.setAceptacion(true);
                    //System.out.println(es.getId() +  " es un estado de apceptacion");
                    break;
                }
            }
        }
    }
        
    /**
     * El conjunto es desde que biene del metodo de cerradura
     * @param conjunto
     * @param simbolo
     * @return 
     */
    public ArrayList<Estado> Mueve(ArrayList<Estado> conjunto, String simbolo){
        ArrayList<Estado> alcanzado = new ArrayList();//estados en la cual se encontro transicion con el simbolo
        
        for(int i = 0; i < conjunto.size(); i++){//recorre los estados quie tiene la lista de conjunto de estados
            Estado temp = conjunto.get(i);
            for(int j = 0; j < temp.getTransiciones().size(); j++){ //recorre las transiciones del estado
                Transicion t = temp.getTransiciones().get(j);//obtenemos temporalmente la transicion
                String s = t.getSimbolo();//obtenemos el simbolo de transicion
                if(s.equals(simbolo)){//verificamos si es igual al simbolo que se quiere hacer el mueve
                    //verificar si ya contiente el estado
                    if(!existeEstado(alcanzado, t.getFin())){
                         alcanzado.add(t.getFin()); //se guarda el estado fin
                    }
                }
            }
        }
        return alcanzado;
    }
    
    /**
     * Cerradura se le manda param conj que
     * guardar en forma recursiva los estados
     * que tienen transicion con epsilon
     * @param e
     * @param conj 
     */
    public void Cerradura(Estado e, ArrayList<Estado> conj){
        
        if(!existeEstado(e, conj)){
            conj.add(e);
        }
        
        for(int i = 0; i < e.getTransiciones().size(); i++){
            if(e.getTransiciones().get(i).getSimbolo().equals(ConstruirAFN.EPSILON)){
                Estado fin = e.getTransiciones().get(i).getFin();
                
                if(!existeEstado(fin, conj)){
                    conj.add(fin);
                    Cerradura(fin, conj);
                }
            }
        }        
    }
    
    public Automata CreateAFD(){
        Automata afd = new Automata();
        for(int i = 0; i < this.getEstCerradura().size(); i++){//recorrer estados de cerradura
            Estado est = this.getEstCerradura().get(i);
            for(int j = 0; j < this.getTerminales().size(); j++){//recorrer los terminales en todos los estados
               Transicion term = this.getTerminales().get(j);
               if(term.getListEnd().get(i) != null){//crear nueva transicion
                   Transicion nuevo = new Transicion(est, term.getListEnd().get(i), term.getSimbolo());
                   est.AddTransicion(nuevo);
               }
            }
            afd.AddEstados(est);
        }
        
        afd.setInicial(this.getEstCerradura().get(0));
        afd.setFin(this.getEstCerradura().get(this.getEstCerradura().size() - 1));
        
        return afd;
    }
    
    public void ObtenerSimbolos(Automata afn){
        for(int i = 0; i < afn.getEstados().size(); i++){
            
            for(int j = 0; j < afn.getEstados().get(i).getTransiciones().size(); j++){
                String s = afn.getEstados().get(i).getTransiciones().get(j).getSimbolo();
                
                if(!existSymbolo(s) && !s.equals(ConstruirAFN.EPSILON)){
                    getTerminales().add(new Transicion(s));
                }
            }
        }
    }
    
    public boolean existSymbolo(String s){
        for(int i = 0; i < this.getTerminales().size(); i++){
            Transicion temp = this.getTerminales().get(i);
            if(temp.getSimbolo().equals(s)){
                return true;
            }
        }
        return false;
    }
    
    public boolean existeEstado(Estado id, ArrayList<Estado> conj){
        for(int i = 0; i < conj.size(); i++){
            if(id.equals(conj.get(i))){
                return true;
            }
        }
        return false;
    }
    
    public boolean existeConjL(ArrayList<Estado> e){
        for(int i = 0; i < this.getEstCerradura().size(); i++){
            Estado temp = getEstCerradura().get(i);
            if(temp.getConj().equals(e)){
                return true;
            }
        }
        return false;
    }
    
    public Estado existConjL(ArrayList<Estado> e){
        for(int i = 0; i < this.getEstCerradura().size(); i++){
            Estado temp = getEstCerradura().get(i);
            if(temp.getConj().equals(e)){
                return temp;
            }
        }
        return null;
    }
    
    public Estado existEstC(Estado e){
        for(int i = 0; i < this.getEstCerradura().size(); i++){
            Estado temp = this.getEstCerradura().get(i);
            if(e.getId().equals(temp.getId())){
                return temp;
            }
        }
        return null;
    }
    
    public boolean existeEstado(ArrayList<Estado> estados, Estado e){
        for(int i = 0; i < estados.size(); i++){
            if(estados.get(i).equals(e)){
                return true;
            }
        }
        return false;
    }
    
    public void mostrar_Tabla(){
        for(int i = 0; i < this.getEstCerradura().size(); i++){
            String s = this.getEstCerradura().get(i).getId() + "...";
            for(int j = 0; j < this.getTerminales().size(); j ++){
                try{
                    Estado fn = this.getTerminales().get(j).getListEnd().get(i);
                    if(fn != null){
                        s += fn.getId() + "...";
                    }else{
                        s += " - " + "...";
                    }
                }catch(Exception ex){}
            }
            System.out.println(s);
            System.out.println("");
        }
    }
           
    public void printConjL(ArrayList<Estado> ce){
        String var = "";
        for(int i = 0; i < ce.size(); i++){
            var += ce.get(i).getId() + ", " ;
        }
        System.out.println(var);
    }
    
    public void printSymbol(){
        for(int i = 0; i < getTerminales().size(); i++){
            System.out.println(getTerminales().get(i).getSimbolo());
        }
    }
      
    public void printConj(ArrayList<Estado> conj){
        for(int i = 0; i < conj.size(); i++){
            System.out.println(conj.get(i).getId());
        }
    }

//    public static void main(String[] args) {
//        
//        int s0 = 0;
//        Estado in = new Estado(String.valueOf(s0));
//        s0 += 1;
//        Estado fin = new Estado(String.valueOf(s0));
//        s0 += 1;
//        Transicion t = new Transicion(in, fin, "a");
//
//        in.AddTransicion(t);
//
//        Automata a = new Automata();
//        a.setInicial(in);
//        a.setFin(fin);
//        a.AddEstados(in);
//        a.AddEstados(fin);
//        
//        Estado in2 = new Estado("0");
//        Estado fi2 = new Estado("1");
//        
//        Transicion t2 = new Transicion(in2, fi2, "b");
//        in2.AddTransicion(t2);
//        
//        Automata b = new Automata();
//        b.setInicial(in2);
//        b.setFin(fi2);
//        b.AddEstados(in2);
//        b.AddEstados(fi2);
//        
//        Estado in3 = new Estado("0");
//        Estado fi3 = new Estado("1");
//        
//        Transicion t3 = new Transicion(in3, fi3, "c");
//        in2.AddTransicion(t3);
//        
//        Automata c = new Automata();
//        c.setInicial(in2);
//        c.setFin(fi2);
//        c.AddEstados(in2);
//        c.AddEstados(fi2);
//        
//        Automata or = ConstruirAFN.ObtenerAFN("|", a, b);
//        
//        Automata or2 = ConstruirAFN.ObtenerAFN("|", or, c);
//        or2.CreateDot("mainor");
//        //Automata kleene = ConstruirAFN.ObtenerAFN("*", or, null);
//        
//        //Automata kleene2 = ConstruirAFN.ObtenerAFN("*", kleene, null);
//        
//        ArrayList<Estado> conj = new ArrayList();
//        
//        
//        Tabla tab = new Tabla(or);
//        
//        //tab.ObtenerSimbolos(or);
//        //tab.printSymbol();
//        
//        if(or2.getEstados().size() > 0){
//            tab.ConstruirAFD(or2);
//            tab.mostrar_Tabla();
//            tab.CreateAFD().CreateDotAFD("main");
//            //tab.CreateAFD();
////            tab.Cerradura(kleene2.getEstados().get(0), conj);
////            tab.printConj(conj);
//            
////            ArrayList<Estado> m = tab.Mueve(conj, "a");
//            
////            for(int i = 0; i < m.size(); i++){
////                System.out.println("donde se mueve a");
////                System.out.println(m.get(i).getId());
////            }
//        }
//    }

    /**
     * @return the terminales
     */
    public ArrayList<Transicion> getTerminales() {
        return terminales;
    }

    /**
     * @param terminales the terminales to set
     */
    public void setTerminales(ArrayList<Transicion> terminales) {
        this.terminales = terminales;
    }

    /**
     * @return the estCerradura
     */
    public ArrayList<Estado> getEstCerradura() {
        return estCerradura;
    }
    
}

//    public void ObtenerSimbolos(Automata afn){
//        for(int i = 0; i < afn.getEstados().size(); i++){
//            
//            for(int j = 0; j < afn.getEstados().get(i).getTransiciones().size(); j++){
//                String s = afn.getEstados().get(i).getTransiciones().get(j).getSimbolo();
//                
//                if(!existeSimbolo(s) && !s.equals(ConstruirAFN.EPSILON)){
//                    simb.add(s);
////                    simbolo.add(s);
//                }
//            }
//        }
//    }


//    public boolean existeSimbolo(String s){
//        for(int i = 0; i < simb.size(); i++){
//            if(simb.get(i).equals(s)){
//                return true;
//            }
//        }
//        return false;
//    }
//       

//    public boolean existeConj(ArrayList<Estado> conj){
//        for(int i = 0; i < estado.size(); i++){
//            if(estado.get(i).equals(conj)){
//                return true;
//            }
//        }
//        return false;
//    }

//
//
//    public void construirAFD(Automata afn){
//        ArrayList<Estado> c = new ArrayList();
//        this.Cerradura(afn.getInicial(), c);//aplicando cerradura al estado inicial
//        estado.add(c);// se guarda 1er conjunto
//        conjuntos.add(c);
//        
//        for(int i = 0; i < c.size(); i++){
//            Estado t = c.get(i);
//            if(t.getId().equals(afn.getFin())){
//                t.setAceptacion(true);
//            }
//        }
//        
//        
//    }
//    
//    public void crearTabla(Automata afn){
        //se realiza la cerradura siempre desde el estado inicial
//        ObtenerSimbolos(afn);
//        ArrayList<Estado> conj = new ArrayList();//conjunto de estados
//        Cerradura(afn.getInicial(), conj);//se hace metodo de cerradura y se le envia la lista de conj para llenarlo
//        
//        for(int i = 0; i < conj.size(); i++){//se verifica si tiene estado de aceptacion
//            Estado t = conj.get(i);
//            
//            if(t.equals(afn.getFin())){
//                t.setAceptacion(true);
//            }
//        }
//        conjuntos.add(conj);
//        estado.add(conj);
        
        
//    }

//    public void Cerradura(Estado e, ArrayList<Integer> conj){
//        int id = Integer.parseInt(e.getId());
//        
//        if(!existeEstado(id, conj)){
//            conj.add(id);
//        }
//        
//        for(int i = 0; i < e.getTransiciones().size(); i++){
//            if(e.getTransiciones().get(i).getSimbolo().equals(ConstruirAFN.EPSILON)){
//                Estado fin = e.getTransiciones().get(i).getFin();
//                int tid = Integer.parseInt(fin.getId());
//                
//                if(!existeEstado(tid, conj)){
//                    conj.add(tid);
//                    Cerradura(fin, conj);
//                }
//            }
//        }        
//    }    
//    public boolean existeEstado(int id, ArrayList<Integer> conj){
//        for(int i = 0; i < conj.size(); i++){
//            if(id == conj.get(i)){
//                return true;
//            }
//        }
//        return false;
//    }