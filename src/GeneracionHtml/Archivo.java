/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneracionHtml;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author jhonny
 */
public class Archivo {
    
    private static String filename = "tablas.html";
    
    public static void crearHtml(ArrayList<Token> lisToken, ArrayList<Eror> lisError)
    {
            String cuerpo = "";
            try
            {
                cuerpo += "<table align=" + (char)34 + "center" + (char)34 + "border=" + (char)34 + "1" + (char)34 + "width=1000>";
                String fin = "</table>";
                int cambio = 1;
                String cuerpoTabla = "";
                cuerpoTabla += "<tr bgcolor=" + (char)34 + "#4682B4" + (char)34 + ">";
                cuerpoTabla += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>#</center></td>";
                cuerpoTabla += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>LEXEMA</center></td>";
                cuerpoTabla += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>FILA</center></td>";
                cuerpoTabla += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>COLUMNA</center></td>";
                cuerpoTabla += "</tr>";
                for (int i = 0; i < lisToken.size(); i++)
                {
                    Token temp = lisToken.get(i);
                    if (cambio == 0)
                    {
                        cuerpoTabla += "<tr bgcolor=" + (char)34 + "#4682B4" + (char)34 + ">";
                        cuerpoTabla += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>" + i + "</center></td>";
                        cuerpoTabla += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>" + temp.getLexema() + "</center></td>";
                        cuerpoTabla += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>" + temp.getYyline() + "</center></td>";
                        cuerpoTabla += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>" + temp.getYycolum() + "</center></td>";
                        cuerpoTabla += "</tr>";
                        cambio = 1;
                    }
                    else
                    {
                        cuerpoTabla += "<tr bgcolor=" + (char)34 + "#E6E6E6" + (char)34 + ">";//#fdfefe
                        cuerpoTabla += "<td><font color=" + (char)34 + "#17202a" + (char)34 + "><Font size=4><center>" + i + "</center></td>";
                        cuerpoTabla += "<td><font color=" + (char)34 + "#17202a" + (char)34 + "><Font size=4><center>" + temp.getLexema() + "</center></td>";
                        cuerpoTabla += "<td><font color=" + (char)34 + "#17202a" + (char)34 + "><Font size=4><center>" + temp.getYyline() + "</center></td>";
                        cuerpoTabla += "<td><font color=" + (char)34 + "#17202a" + (char)34 + "><Font size=4><center>" + temp.getYycolum() + "</center></td>";
                        cuerpoTabla += "</tr>";
                        cambio = 0;
                    }
                }
                cambio = 1;
                cuerpo += cuerpoTabla + fin;
                String cuerpoError = "";
                cuerpo += "<h1><center>TABLA DE ERRORES</center></h1><br><br>";
                cuerpo += "<table align=" + (char)34 + "center" + (char)34 + "border=" + (char)34 + "1" + (char)34 + "width=1000>";

                cuerpoError += "<tr bgcolor=" + (char)34 + "#4682B4" + (char)34 + ">";
                cuerpoError += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>#</center></td>";
                cuerpoError += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>TIPO</center></td>";
                cuerpoError += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>TOKEN</center></td>";
                cuerpoError += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>FILA</center></td>";
                cuerpoError += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>COLUMNA</center></td>";
                cuerpoError += "</tr>";
                for (int i = 0; i < lisError.size(); i++)
                {
                    Eror temp = lisError.get(i);
                    if (cambio == 0)
                    {
                        cuerpoError += "<tr bgcolor=" + (char)34 + "#4682B4" + (char)34 + ">";
                        cuerpoError += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>" + i + "</center></td>";
                        cuerpoError += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>" + temp.getTipo() + "</center></td>";
                        cuerpoError += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>" + temp.getYytext() + "</center></td>";
                        cuerpoError += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>" + temp.getYyline() + "</center></td>";
                        cuerpoError += "<td><font color=" + (char)34 + "#FFFFFF" + (char)34 + "><Font size=4><center>" + temp.getYycolum() + "</center></td>";
                        cuerpoError += "</tr>";
                        cambio = 1;
                    }
                    else
                    {
                        cuerpoError += "<tr bgcolor=" + (char)34 + "#E6E6E6" + (char)34 + ">";
                        cuerpoError += "<td><font color=" + (char)34 + "#17202a" + (char)34 + "><Font size=4><center>" + i + "</center></td>";
                        cuerpoError += "<td><font color=" + (char)34 + "#17202a" + (char)34 + "><Font size=4><center>" + temp.getTipo() + "</center></td>";
                        cuerpoError += "<td><font color=" + (char)34 + "#17202a" + (char)34 + "><Font size=4><center>" + temp.getYytext() + "</center></td>";
                        cuerpoError += "<td><font color=" + (char)34 + "#17202a" + (char)34 + "><Font size=4><center>" + temp.getYyline() + "</center></td>";
                        cuerpoError += "<td><font color=" + (char)34 + "#17202a" + (char)34 + "><Font size=4><center>" + temp.getYycolum() + "</center></td>";
                        cuerpoError += "</tr>";
                        cambio = 0;
                    }
                }
                cuerpo += cuerpoError + fin;
            }
            catch (Exception e)
            {

            }
            String pag = "<html><head><title>TABLA DE TOKENS Y ERRORES</title></head><body>"
                + "<body style=" + (char)34 + "text-align:justify;" + (char)34 + ">"
                + "<h1><center>TABLA DE TOKENS</center></h1><br><br>" + cuerpo + "</body></html>";
            
            File f = new File(filename);//"C:\\Users\\usuario\\Desktop\\ejemplo.txt");//, nodos + "}");
            try{
                    FileWriter fw = new FileWriter(f, false);
                    BufferedWriter br = new BufferedWriter(fw);
                    br.write(pag);
                    br.close();
                    fw.close();
                    Desktop.getDesktop().open(f);
            }catch(Exception e){

            }
  
        }
}
