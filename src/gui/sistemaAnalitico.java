/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Conexion.Conexion;
import GeneracionHtml.Eror;
import GeneracionHtml.Token;
import Modulos.Sentencia;
import Thompson.Estado;
import Thompson.Tabla;
import Thompson.Transicion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
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
public class sistemaAnalitico extends javax.swing.JFrame {
    /**
     * Creates new form sistemaAnalitico
     */
    private boolean conectado;
    private boolean analizado = false;
    private ArrayList<Token> token;
    private ArrayList<Eror> error;
    
    public sistemaAnalitico() {
        initComponents();
        conectado = true;
        mantenerConexion();
    }
    
    public void mantenerConexion(){
        sistemaAnalitico temp = this;
                        Conexion con = new Conexion(temp);
        Thread hilo = new Thread(new Runnable(){
            @Override
            public void run(){
                while(conectado){
                    if(analizado){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(sistemaAnalitico.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(!con.isConect()){
                            con.iniciarConexion(AnalizadorSintactico.listaS.getSentencias(), AnalizadorSintactico.listaS.getConjuntos());
                            token = con.getToken();
                            error = con.getError();
                        } 
                        
                    }else{
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(sistemaAnalitico.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        hilo.start();
    }
    
    public void cargarComboBox(){
        //this.combo.removeAllItems();
        for(int i = 0; i < AnalizadorSintactico.listaS.getSentencias().size(); i++){
            Sentencia t = AnalizadorSintactico.listaS.getSentencias().get(i);
            
            this.combo.addItem(t.getId());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        combo = new javax.swing.JComboBox();
        tabControl = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        etiImgAFN = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        etiImgAFD = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        abrir = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        guardar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        guardatToken = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        reporte = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema analitico");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel1.setText("Automata Finito Determinista");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel2.setText("Tabla de Transiciones");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Estados", "Transiciones"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        combo.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(etiImgAFN, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(etiImgAFN, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );

        tabControl.addTab("Automata AFN", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(etiImgAFD, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(etiImgAFD, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );

        tabControl.addTab("Automata AFD", jPanel3);

        abrir.setText("Archivo");

        jMenuItem1.setText("Abrir D-ER");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirctionPerformed(evt);
            }
        });
        abrir.add(jMenuItem1);

        guardar.setText("Guardar D-ER");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        abrir.add(guardar);

        menuBar.add(abrir);

        jMenu2.setText("Herramientas");

        jMenuItem3.setText("Cargar Thompson");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarThompsonActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        guardatToken.setText("Guardar Token's");
        guardatToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardatTokenActionPerformed(evt);
            }
        });
        jMenu2.add(guardatToken);

        jMenuItem5.setText("Guardar Errores");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        reporte.setText("Ver Reporte");
        reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteActionPerformed(evt);
            }
        });
        jMenu2.add(reporte);

        jMenuItem2.setText("Analizar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizar(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabControl))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabControl, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirctionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirctionPerformed
        // TODO add your handling code here:
        JFileChooser open = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos D-ER", "der");
        
        open.setFileFilter(filtro);
        
        if(open.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File f = open.getSelectedFile();
            String aux = "";
            String texto = "";
            try{
                if(f != null){
                    FileReader fr=new FileReader(f);
                    BufferedReader br=new BufferedReader(fr);
                    while((aux=br.readLine())!=null){
                       texto += aux+ "\n";
                    }
                    this.textArea.setText(texto);
                    br.close();
                }
            }catch(Exception ex){
                
            }
        }
    }//GEN-LAST:event_abrirctionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        JFileChooser save = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos D-ER", "der");
        
        save.setFileFilter(filtro);
        
        if(save.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            String path = save.getSelectedFile().getPath() + ".der";
            File f = new File(path);
                        
            if(!textArea.getText().equals("")){
                try{
                    FileWriter fw = new FileWriter(f, false);
                    BufferedWriter br = new BufferedWriter(fw);
                    br.write(textArea.getText());
                    br.close();
                    fw.close();
                }catch(Exception e){

                }
            }
            JOptionPane.showMessageDialog(null, "Archivo guardado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void cargarThompsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarThompsonActionPerformed
        // TODO add your handling code here:
        this.cargarComboBox();
        //revisar por que cadena del archivo de entrada del aux no lo crea
    }//GEN-LAST:event_cargarThompsonActionPerformed

    private void analizar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizar
        // TODO add your handling code here:
        if(!textArea.getText().equals("")){
            AnalizadorSintactico.limpiarLista();
            AnalizadorSintactico.listaS.inicializarLista();
            String text = textArea.getText();
            String path = this.generarTxt("entrada", text);
            
            String []archivo = {"entrada.txt"};
            AnalizadorSintactico.raiz = null;   // Reinicia el arbol
            AnalizadorSintactico.AnalizarGramatica(archivo);
            System.out.println(path);
            this.combo.removeAllItems();
            this.limpiarTabla();
            analizado = true;
            
//        Conexion con = new Conexion();
//        con.iniciarConexion(AnalizadorSintactico.listaS.getSentencias(), AnalizadorSintactico.listaS.getConjuntos()); 
        }
    }//GEN-LAST:event_analizar

    private void guardatTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardatTokenActionPerformed
        // TODO add your handling code here:
        if(token != null){
            this.crearXmlTokens();
            JOptionPane.showMessageDialog(null, "Tokens guardados en xml");
        }else{
            JOptionPane.showMessageDialog(null, "se ha producido un error al guardar el archivo", "Informacion", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_guardatTokenActionPerformed

    private void reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteActionPerformed
        // TODO add your handling code here:
        AnalizadorLexico.crearHtml();
    }//GEN-LAST:event_reporteActionPerformed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        // TODO add your handling code here:
//        System.out.println(this.combo.getSelectedItem().toString());
        String id = "";//this.combo.getSelectedItem().toString();
        try{
            id = this.combo.getSelectedItem().toString();
        }catch(Exception ex){
            
        }
        for(int i = 0; i < AnalizadorSintactico.listaS.getSentencias().size(); i++){
            Sentencia t = AnalizadorSintactico.listaS.getSentencias().get(i);
            if(t.getId().equals(id)){
                this.etiImgAFN.setIcon(new ImageIcon(Metodos.icono(t.getExpAFN().getPathImg(), this.etiImgAFN.getWidth(), this.etiImgAFN.getHeight())));
                this.etiImgAFD.setIcon(new ImageIcon(Metodos.icono(t.getExpAFD().getPathImgAFD(), this.etiImgAFD.getWidth(), this.etiImgAFD.getHeight())));
                //colocar tabla de transiciones
                this.colocarTabla(t.getTrans());
                break;
            }            
        }
    }//GEN-LAST:event_comboActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        conectado = false;
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        if(error != null){
            this.crearXmlError();
            JOptionPane.showMessageDialog(null, "Errores guardados en xml");
        }else{
            JOptionPane.showMessageDialog(null, "se ha producido un error al guardar el archivo", "Informacion", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private DefaultTableModel modelo;
    
    public void colocarTabla(Tabla t){
        //mostrar cabecera
        String cab[] = {"Estado/Terminal"};
        modelo = new DefaultTableModel(cab, 0);
        this.jTable1.setModel(modelo);
        
        for(int i = 0; i < t.getTerminales().size(); i++){
            Transicion term = t.getTerminales().get(i);
            modelo.addColumn(term.getSimbolo());
        }
        
        for(int i = 0; i < t.getEstCerradura().size(); i++){
            String vecRow[] = new String[t.getTerminales().size() + 1];
            
            //verificar si es de aceptacion el estado
            Estado temp = t.getEstCerradura().get(i);
            
            if(temp.isAceptacion()){
                vecRow[0] = "(??) " + temp.getId();
            }else{
                vecRow[0] = "( ) " + temp.getId();
            }
            
            for(int j = 0; j < t.getTerminales().size(); j ++){
                Estado fn = t.getTerminales().get(j).getListEnd().get(i);
                
                try{
                    if(fn != null){
                        vecRow[j + 1] = fn.getId();
                    }else{
                        vecRow[j + 1] = "-";
                    }
                }catch(Exception ex){}
            }
            modelo.addRow(vecRow);
        }
    }
    
    public void limpiarTabla(){
        if(modelo != null){
            for(int i = 0; i < modelo.getRowCount(); i++){
                modelo.removeRow(i);
            }
        }
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(sistemaAnalitico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sistemaAnalitico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sistemaAnalitico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sistemaAnalitico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        String archLexico = "alexico.flex";
        String archSintactico = "asintactico.cup";

        String[] alexico = {archLexico};
        String[] asintactico = {"-parser", "AnalizadorSintactico", archSintactico};
        
        jflex.Main.main(alexico);
        System.out.println("Genero lexico");
        try {
            java_cup.Main.main(asintactico);
            System.out.println("Genero sintactico");
        } catch (Exception ex) {
            System.out.println("******************** ERROR *************************\nNo se genero el AnalizadorSintactico");
        }

        /*********** Movemos los archivos generados *************/
        boolean mvAL = MoverArchivo("AnalizadorLexico.java");
        boolean mvAS = MoverArchivo("AnalizadorSintactico.java");
        boolean mvSym = MoverArchivo("sym.java");
        if(mvAL && mvAS && mvSym){
            System.out.println("Se movieron todos los archivos");
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sistemaAnalitico().setVisible(true);
            }
        });
    }
    
    public static boolean MoverArchivo(String archNombre) {
        boolean efectuado = false;
        File arch = new File(archNombre);
        if (arch.exists()) {
            Path currentRelativePath = Paths.get("");
            String nuevoDir =   currentRelativePath.toAbsolutePath().toString() + File.separator 
                                + "src" + File.separator
                                + "gui" + File.separator 
                                + arch.getName();
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if (arch.renameTo(new File(nuevoDir))) {
                System.out.println("Se movio " + archNombre);
                efectuado = true;
            } else {
                System.out.println("No se movio " + archNombre);
            }

        } else {
            System.out.println("El archivo no se puede mover: "+archNombre);
        }
        return efectuado;
    }
    
    public String generarTxt(String path, String text){
        File f = new File(path + ".txt");
        try{
                FileWriter fw = new FileWriter(f, false);
                BufferedWriter br = new BufferedWriter(fw);
                br.write(text);
                br.close();
                fw.close();
        }catch(Exception e){
            System.out.println("**************Problemas al crear archivo*************");
        }
        return f.getPath();
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
                Element error = doc.createElement("error");
                
                Element valor = doc.createElement("valor");
                valor.appendChild(doc.createTextNode(temp.getYytext()));
                error.appendChild(valor);
                
                Element yyrow = doc.createElement("yyrow");
                yyrow.appendChild(doc.createTextNode(String.valueOf(temp.getYyline())));
                error.appendChild(yyrow);
                
                Element yycol = doc.createElement("yycol");
                yycol.appendChild(doc.createTextNode(String.valueOf(temp.getYycolum())));
                error.appendChild(yycol);
                
                errores.appendChild(error);
            }
            
            TransformerFactory facto = TransformerFactory.newInstance();
            Transformer transform = facto.newTransformer();
            rretu = "C:\\xml\\errores.xml";
            Source source = new DOMSource(doc);
            File file = new File(rretu);
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            
            Result result = new StreamResult(pw);
            
            transform.transform(source, result);
            
        }catch(Exception ex){
            
        }
        return rretu;
    }
    
    public String crearXmlTokens(){
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
        }catch(Exception ex){
            
        }
        return ret;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu abrir;
    private javax.swing.JComboBox combo;
    private javax.swing.JLabel etiImgAFD;
    private javax.swing.JLabel etiImgAFN;
    private javax.swing.JMenuItem guardar;
    private javax.swing.JMenuItem guardatToken;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem reporte;
    private javax.swing.JTabbedPane tabControl;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

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
