package gui;

import java_cup.runtime.*;
import java.io.Reader;
import javax.swing.JOptionPane;
import GeneracionHtml.*;    

%%

%class AnalizadorLexico

%line
%column 
//%ignorecase 

%cup


%{

    /***************************************  Generamos un java_cup.symbol para guardar el tipo de token encontrado **********************************************/
    
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    /*************************************** Generamos un symbol para el tipo de token encontrado junto con su valor *********************************************/

    private Symbol symbol(int type, Object value) {
        lista.AddToken(new Token(value.toString(), yyline, yycolumn));
        return new Symbol(type, yyline, yycolumn, value);
    }

    /******************************************************* Se crea la clase para guardar tokens y errores ******************************************************/

    public static void crearHtml(){
        Archivo.crearHtml(lista.lt, lista.le);
    }

    public static void limpiarLista(){
        lista.lt.clear();
        lista.le.clear();
    }

    public static Lista lista = new Lista();
%}



/*********************************************************** Expresiones Regulares ***********************************************************/

Letra=          [a-zA-ZñÑ]
number=         [0-9]
digito=			{number}({number})*
Caracter=		[\"][^\"\n][\"]
Especiales=		[\\]("n" | [\'] | [\"] | "t")
CaracterEspecial= [\"]{Especiales}[\"]
cadena=         [\"][^\'\"\n]+[\"]                        
id=             {Letra}({Letra}|{number}|_)*    
expConj=        {Letra}\~{Letra}|{number}\~{number}|{digito}([\,][\ ]?{digito}[\ ]?)*|{Letra}([\,][\ ]?{Letra}[\ ]?)*        
todo=			\[ \: "todo" \: \]
simbolo=		(\!|\"|\#|\$|\%|\&|\-|\/|\<|\=|\>|\@|\\|\^|\_|\`|\{|\})
separador=		\~

%%

/**'************************************************************* TOKENS *********************************************************************/

<YYINITIAL> {

    
"%%"                    {System.out.print("%% "); return symbol(sym.tDoblePor,yytext()); }
"CONJ"                  {System.out.print("CONJ "); return symbol(sym.tConj,yytext()); }
"yytext"				{System.out.print("yytext "); return symbol(sym.tYyText,yytext()); }
"RESERV"				{System.out.print("RESERV "); return symbol(sym.tReserv,yytext()); }
"yyrow"					{System.out.print("yyrow "); return symbol(sym.tYyRow,yytext()); }
"yycol"					{System.out.print("yycol "); return symbol(sym.tYyCol,yytext()); }
"retorno"				{System.out.print("retorno "); return symbol(sym.tRetorno,yytext()); }
"error"					{System.out.print("error "); return symbol(sym.tError,yytext()); }
";"                     {System.out.print("; "); return symbol(sym.tPuntoComa,yytext()); }
":"                     {System.out.print(": "); return symbol(sym.tDosPuntos,yytext()); }
"["						{System.out.print("[ "); return symbol(sym.tCorcheteA,yytext()); }
"]"						{System.out.print("] "); return symbol(sym.tCorcheteC,yytext()); }
"("						{System.out.print("( "); return symbol(sym.tParentesisA,yytext()); }
")"						{System.out.print(") "); return symbol(sym.tParentesisC,yytext()); }	
"->"                    {System.out.print("-> "); return symbol(sym.tFlecha,yytext()); }
","						{System.out.print(", "); return symbol(sym.tComa,yytext()); }
"|"						{System.out.print("| "); return symbol(sym.tOr,yytext()); }
"."						{System.out.print(". "); return symbol(sym.tAnd,yytext()); }
"*"						{System.out.print("* "); return symbol(sym.tPor,yytext()); }
"+"						{System.out.print("+ "); return symbol(sym.tMas,yytext()); }
"?"						{System.out.print("? "); return symbol(sym.tSignoI,yytext()); }

[ \t\r\f]               { }

[\n]                    {System.out.print("\n"); }

[\']                    { }

{Caracter}				{System.out.print(yytext() + " "); return symbol(sym.tCaracter,yytext()); }

{CaracterEspecial}		{System.out.print(yytext() + " "); return symbol(sym.tEspecial,yytext()); }

{digito}				{System.out.print(yytext() + " "); return symbol(sym.tDigito,yytext()); }

{id}                    {System.out.print(yytext() + " "); return symbol(sym.tId,yytext()); }

{todo}					{System.out.print(yytext() + " "); return symbol(sym.tTodo,yytext()); }

{expConj}               {System.out.print(yytext() + " "); return symbol(sym.tExpConj,yytext()); }

{cadena}				{System.out.print(yytext() + " "); return symbol(sym.tCadena,yytext()); }

{separador}				{System.out.print(yytext() + " "); return symbol(sym.tSep,yytext()); }

{simbolo}				{System.out.print(yytext() + " "); return symbol(sym.tSimbolo,yytext()); }

.                       {
                            lista.AddError(new Eror("Lexico", yytext(), yyline, yycolumn));
                            JOptionPane.showMessageDialog(null, "Error Lexico vino : "+yytext()+" en la linea "+(yyline+1)+" y columna "+(yychar+1));
                        }

}