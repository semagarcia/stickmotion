header {
	package sticky;
	import java.util.HashMap;
	import sticky.tablasimbolos.TablaSimbolos;
	import java.io.FileInputStream;
	import java.util.ArrayList;
	import antlr.*;
	import antlr.InputBuffer;
}

class Anasint extends Parser;
	options {
		k=2; 
	}
	{
		TablaSimbolos tablaSimbolos= new TablaSimbolos();
	}

	instrucciones 
	{
		Object x=null;
		Procesador.println(-1,"...INICIANDO STICKY...");		
	} 
	
	: (sentencia)* fin_interprete; //Una o varias sentencias, y finaliza
	
	
	sentencia: (simple FIN_INSTRUCCION) | bucle ; //O una sentencia simple con ; o un bucle, que no lleva ; para acabar (si sus intrucciones)
	
	simple {String valor; Object valor2;}: declaracion | asignacion | eliminar_var | valor=imprimir { Procesador.println(-1,valor); } | valor2=expr_incremento; //Esto permitirá usar ; para salir del greedy, ya que ; no se pide aquí
	bucle: sentenciaIF | sentenciaWHILE | sentenciaFOR;
	
	//Para declarar variables hay diferentes alternativas:
	//1. Se declara una variable sin inicializarse.
	//2. Se declara una variable y si inicializa.
	//3. Se declara más de una variable.
	// NOTA: NO se permite inicialización de variables mientras se declara más de una.

declaracion {String mensaje;Object x = null; ArrayList lista = new ArrayList();}:
	(	//Alternativa 1
		(VAR IDENT FIN_INSTRUCCION) => VAR i1:IDENT
		{ 
		  boolean res=tablaSimbolos.put(i1);
		  if(res)
		  	Procesador.println(1, "Variable \""+i1.getText()+"\" ha sido declarada");
		  else
		  	Procesador.println(0, "Linea "+i1.getLine()+": Variable \""+i1.getText()+"\" no ha sido declarada, ya existe");
		}
		
		//Alternativa 2
		|(VAR IDENT OP_ASIG expresionOR) =>VAR i3:IDENT OP_ASIG (x=expresionOR) 
			{			
				boolean res = tablaSimbolos.put(i3,x);	// modifico el valor en la tabla de simbolos
				if(res)
		  			Procesador.println(1, "Variable \""+i3.getText()+"\" ha sido declarada con valor "+x);
		  		else 
		  			Procesador.println(0, "Linea "+i3.getLine()+": Variable \""+i3.getText()+"\" no ha sido declarada, ya existe");
		
			}
		//Alternativa 3
		|(VAR IDENT (SEPARA IDENT)*) => VAR i4:IDENT (SEPARA i3_alt:IDENT {lista.add(i3_alt);} )* 
					{
						// Tenemos que insertar cada identificador encontrado en la tabla de simbolos
						boolean res = tablaSimbolos.put(i4);
						if(res)
		  					Procesador.println(1, "Variable \""+i4.getText()+"\" ha sido declarada");
		  				else 
		  					Procesador.println(0,"Linea "+i4.getLine()+": Variable \""+i4.getText()+"\" no ha sido declarada, ya existe");
						Token tok;
						for(int j=0; j < lista.size(); j++)
						{
								tok = (Token)lista.get(j); // obtengo un identificador de la lista
								res = tablaSimbolos.put(tok);
								if(res)
		  							Procesador.println(1, "Variable \""+tok.getText()+"\" ha sido declarada");
		  						else 
		  							Procesador.println(0, "Linea "+tok.getLine()+": Variable \""+tok.getText()+"\" no ha sido declarada, ya existe");
						}
					}
					
	);


///////////////////// REGLAS PARA LAS ASIGNACIONES DE VARIABLES //////////////////

asignacion 
	{ String mensaje = new String(); Object respuesta; Object respuesta2;}:

	i2:IDENT OP_ASIG (respuesta = expresionOR)
	
	{		
			if(tablaSimbolos.set(i2,respuesta))	
				Procesador.println(1, "Asignacion a la variable \""+i2.getText()+"\": "+respuesta);
			else 
				Procesador.println(0, "Linea "+i2.getLine()+": Asignacion no realizada, no existe la variable \""+i2.getText()+"\"");
	} ;
//////////////////////////////////////////////////////////////////////////////////



/////////////////// EXPRESIONES ARITMETICAS //////////////////////////////////////////////

	
expr_aritmetica returns[Object resultado = null]
{Object e1 = null; Object e2 = null; Object e3 = null;}:
	e1=expr_mod
	{
		//Esto es por si sucede algo así var=-2; El signo - al principio hace que e1 primer operando sea null y el segundo 2
		if(e1 == null)
			e1 = 0;
			
		resultado = e1;
	}
	(linea1:OP_MOD e2 = expr_mod
	{
		if(e1 instanceof Integer && e2 instanceof Integer)
		{
			int valor1 = new Integer(e1.toString()).intValue();
			int valor2 = new Integer(e2.toString()).intValue();
			
			resultado = new Integer(valor1%valor2);
		}
		
		if((e1 instanceof Double || e2 instanceof Double) && !(e1 instanceof String || e2 instanceof String || e1 instanceof Boolean || e2 instanceof Boolean))
		{
			double valor1 = new Double(e1.toString()).doubleValue();
			double valor2 = new Double(e2.toString()).doubleValue();
			
			resultado = new Double(valor1%valor2);
		}
		
		if(e1 instanceof String || e2 instanceof String)
		{
			Procesador.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
		}
		
		if(e1 instanceof Boolean || e2 instanceof Boolean)
		{
			Procesador.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
		}
		
	})*
	;
	

expr_mod returns [Object resultado = null]
{Object e1; Object e2; Object e3; Object e4;}: 
	e1= expr
	{
		//Esto es por si sucede algo así var=-2; El signo - al principio hace que e1 primer operando sea null y el segundo 2
		if(e1 == null)
			e1 = 0;

		resultado = e1;
	}
	(linea1:OP_SUM e2=expr
	{
		if(e1 instanceof Integer && e2 instanceof Integer)
	  	{
	  		int valor1 = new Integer(e1.toString()).intValue();
	  		int valor2 = new Integer(e2.toString()).intValue();
	  		
	  		resultado = new Integer(valor1+valor2);
	  		
	  		e1=resultado;
	  	}
	  	else
	  	{
	  		if(e1 instanceof String || e2 instanceof String)
	  		{
	  			if(e1 instanceof String && e2 instanceof String)
	  			{
	  				String valor1 = new String(e1.toString());
	  				String valor2 = new String(e2.toString());
	  				resultado = new String(valor1+valor2);
	  			}
	  			else
	  			{
	  				Procesador.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  			}
	  		}
	  		else
	  		{
	  			if(e1 instanceof Boolean || e2 instanceof Boolean)
	  				Procesador.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
	  			else
	  			{
	  				double valor1 = new Double(e1.toString()).doubleValue();
	  				double valor2 = new Double(e2.toString()).doubleValue();
	  				resultado = new Double(valor1+valor2);
	  				
	  				e1=resultado;
	  			}
	  		}
	  	}	
	}
	| linea2:OP_RES e3 = expr
	{
		if(e1 instanceof Integer && e3 instanceof Integer)
	  	{
	  		int valor1 = new Integer(e1.toString()).intValue();
	  		int valor2 = new Integer(e3.toString()).intValue();
	  		resultado = new Integer(valor1-valor2);
	  		
	  		e1=resultado;
	  	}
	  	else
	  	{
	  		if(e1 instanceof String || e3 instanceof String)
	  		{
	  			Procesador.println(0,"Linea "+linea2.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  		}
	  		else
	  		{
	  			if(e1 instanceof Boolean || e3 instanceof Boolean)
	  				Procesador.println(0,"Linea "+linea2.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
	  			else
	  			{
	  				double valor1 = new Double(e1.toString()).doubleValue();
	  				double valor2 = new Double(e3.toString()).doubleValue();
	  				resultado = new Double(valor1-valor2);
	  				
	  				e1=resultado;
	  			}
	  		}
	  	}
	}
	)*;

expr returns [Object resultado = null]
{Object e1; Object e2; Object e3;}: 
	
	e1=expr_mult {
		//Esto es por si sucede algo así var=-2; El signo - al principio hace que e1 primer operando sea null y el segundo 2
		if(e1 == null)
			e1 = 0;
			
		resultado = e1;
		}

	( linea1:OP_MUL e2=expr_mult
	  {
	  	
	  	if(e1 instanceof Integer && e2 instanceof Integer)
	  	{
	  		int valor1 = new Integer(e1.toString()).intValue();
	  		int valor2 = new Integer(e2.toString()).intValue();
	  		
	  		resultado = new Integer(valor1*valor2);
	  		
	  		e1=resultado;
	  	}
	  	
	  	if((e1 instanceof Double || e2 instanceof Double) && !(e1 instanceof String || e2 instanceof String || e1 instanceof Boolean || e2 instanceof Boolean))
	  	{
	  		double valor1 = new Double(e1.toString()).doubleValue();
	  		double valor2 = new Double(e2.toString()).doubleValue();
	  		
	  		resultado = new Double(valor1*valor2);
	  		
	  		e1=resultado;
	  	}
	  	
	  	if(e1 instanceof String || e2 instanceof String)
	  	{
	  			Procesador.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  	}
	  	
	  	if(e1 instanceof Boolean || e2 instanceof Boolean)
	  	{
	  			Procesador.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
	  	}
	  }
	  | linea2:OP_DIV e3 = expr_mult
	  {
	  	if(e1 instanceof Integer && e3 instanceof Integer)
	  	{
	  		int valor1 = new Integer(e1.toString()).intValue();
	  		int valor2 = new Integer(e3.toString()).intValue();
	  		
	  		if((valor2 !=0) && (valor1 != 0))
	  		{
	  				resultado = new Integer(valor1/valor2);
	  				e1=resultado;
	  		}
	  		else
	  			Procesador.println(0,"Linea "+linea2.getLine()+": División por 0");
	  	}
	  	else
	  	{
	  		if(e1 instanceof String || e3 instanceof String)
	  		{
	  			Procesador.println(0,"Linea "+linea2.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  		}
	  		else
	  		{
	  			if(e1 instanceof Boolean || e3 instanceof Boolean)
	  				Procesador.println(0,"Linea "+linea2.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
	  			else
	  			{	
	  				//Si no son flotantes
	  				double valor1 = new Double(e1.toString()).doubleValue();
	  				double valor2 = new Double(e3.toString()).doubleValue();
	  			
	  				if((valor2 !=0)&&(valor1 != 0))
	  				{	
	  					resultado = new Double(valor1/valor2);
	  					e1=resultado;
	  				}
	  				else
	  					Procesador.println(0,"Linea "+linea2.getLine()+": División por 0");
	  			}
	  		}
	  			
	  	}	
	  })*;
	  

expr_mult returns [Object resultado = null]
{Object e1; Object e2;}:
	
	e1=expr_raiz
	{
		//Esto es por si sucede algo así var=-2; El signo - al principio hace que e1 primer operando sea null y el segundo 2
		if(e1 == null)
			e1 = 0;
			
		resultado = e1;	
	}
	(linea1:OP_POT e2 = expr_raiz
	{
		
		if(e1 instanceof Integer && e2 instanceof Integer)
		{
			int valor1 = new Integer(e1.toString()).intValue();
			int valor2 = new Integer(e2.toString()).intValue();
			
			resultado = new Double(Math.pow(valor1, valor2));
		}
		else
		{
			if(e1 instanceof String || e2 instanceof String)
			{
				Procesador.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
			}
			else
			{
			
				if(e1 instanceof String || e2 instanceof String)
					Procesador.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
				else
				{
					double valor1 = new Double(e1.toString()).doubleValue();
					int valor2 = new Integer(e2.toString()).intValue();
					resultado = new Double(Math.pow(valor1, valor2));
				}
			}
		}
	})*;
	
	
expr_raiz returns [Object resultado = null]
{Object exp1 = null ; Object exp2 = null;}: 

	exp1=expr_base {
		//Esto es por si sucede algo así var=-2; El signo - al principio hace que e1 primer operando sea null y el segundo 2
		if(exp1 == null)
			exp1 = 0;
			 
		resultado = exp1;
		}
	|
	( linea:OP_RAIZ exp2=expr_base
	  {
	  if(exp2 != null) 				// 2
			{
				
			if(exp2 instanceof Boolean)
				{
				Procesador.println(0,"Linea "+linea.getLine()+": No se pueden realizar operaciones aritmeticas con booleanos.");
				}
			else
				{
				if(exp2 instanceof String)
					{
					Procesador.println(0,"Linea "+linea.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres.");
					}
				else
					{
					Double var = new Double(exp2.toString()).doubleValue();
					var = Math.sqrt(var);
					resultado = var;
					}
				}
	
			}
	  }
	  )* ;



expr_base returns [Object resultado = null]: 
		n1:ENTERO {resultado = new Integer(n1.getText());}
		|n2:REAL {resultado = new Double(n2.getText());}
		|n3:VERDADERO {resultado = new Boolean(true);}
		|n4:FALSO {resultado = new Boolean(false);}
		|n5:CADENA {resultado = new String( n5.getText()); }
		|(IDENT) => id:IDENT
		{
			if(tablaSimbolos.existeSimbolo(id.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id.getText());
				
				if(contenido.compareTo("") == 0)
				{
					Procesador.println(0,"Linea "+id.getLine()+": La variable "+id.getText()+" no tiene asignado ningun valor.");	
				}
				else {
				//Si no es una cadena (si lleva " matches devuelve false)
				if(contenido.matches("[0-9.]+|true|false"))
	  				{
	  				//Si lleva true o false es un booleano
	  				if(contenido.matches("true|false"))
	  					{
	  					resultado = new Boolean(contenido.toString()).booleanValue();
	  					}
	  				//Si lleva solo numeros de 0 a 9
	  				else if(contenido.matches("[0-9]+"))
	  					{
	  					resultado = new Integer(contenido.toString()).intValue();
	  					}
	  				//Y si no es un entero
	  				else {
	  					resultado = new Float(contenido.toString()).floatValue();
	  					}
	  				}
	  			else //es una cadena
	  			{
	  				resultado = new String(contenido.toString());
	  			}
				}
			}
			else 
				Procesador.println(0,"Linea "+id.getLine()+": la variable "+ id.getText() +" no ha sido declarada.");

				
		}
		| resultado = expr_incremento
		| (PAR_IZQ expr_aritmetica PAR_DER) => PAR_IZQ (resultado = expr_aritmetica) PAR_DER
		| (PAR_IZQ expresionOR PAR_DER) => PAR_IZQ (resultado = expresionOR) PAR_DER
		;

expr_incremento returns [Object resultado = null]:
(IDENT OP_INC) => id2:IDENT OP_INC
		{
			if(tablaSimbolos.existeSimbolo(id2.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id2.getText());
				
				if(contenido.compareTo("") == 0)
				{
					Procesador.println(0,"Linea "+id2.getLine()+": La variable "+id2.getText()+" no tiene asignado ningun valor.");	
				}
				else {
				//Si no es una cadena (si lleva " matches devuelve false)
				if(contenido.matches("[0-9.]+|true|false"))
	  				{
	  				//Si lleva true o false es un booleano
	  				if(contenido.matches("true|false"))
	  					{
	  					Procesador.println(0,"Linea "+id2.getLine()+": la variable "+id2.getText()+" es un booleano y por tanto no se puede incrementar.");
	  					}
	  				//Si lleva solo numeros de 0 a 9
	  				else if(contenido.matches("[0-9]+"))
	  					{
	  					resultado = new Integer(contenido.toString()).intValue()+1;
	  					tablaSimbolos.set(id2,resultado);
	  					}
	  				//Y si no es un entero
	  				else {
	  					resultado = new Float(contenido.toString()).floatValue()+1;
	  					tablaSimbolos.set(id2,resultado);
	  					}
	  				}
	  			else //es una cadena
	  			{
	  				Procesador.println(0,"Linea "+id2.getLine()+": la variable "+id2.getText()+" es una cadena y por tanto no se puede incrementar.");
	  			}
				}
			}
			else 
				Procesador.println(0,"Linea "+id2.getLine()+": la variable "+id2.getText()+" no ha sido declarada.");

				
		}
		| (IDENT OP_DEC) => id3:IDENT OP_DEC
		{
			if(tablaSimbolos.existeSimbolo(id3.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id3.getText());
				
				if(contenido.compareTo("") == 0)
				{
					Procesador.println(0,"Linea "+id3.getLine()+": La variable "+id3.getText()+" no tiene asignado ningun valor.");	
				}
				else {
				//Si no es una cadena (si lleva " matches devuelve false)
				if(contenido.matches("[0-9.]+|true|false"))
	  				{
	  				//Si lleva true o false es un booleano
	  				if(contenido.matches("true|false"))
	  					{
	  					Procesador.println(0,"Linea "+id3.getLine()+": la variable "+id3.getText()+" es un booleano y por tanto no se puede decrementar.");
	  					}
	  				//Si lleva solo numeros de 0 a 9
	  				else if(contenido.matches("[0-9]+"))
	  					{
	  					resultado = new Integer(contenido.toString()).intValue()-1;
	  					tablaSimbolos.set(id3,resultado);
	  					}
	  				//Y si no es un entero
	  				else {
	  					resultado = new Float(contenido.toString()).floatValue()-1;
	  					tablaSimbolos.set(id3,resultado);
	  					}
	  				}
	  			else //es una cadena
	  			{
	  				Procesador.println(0,"Linea "+id3.getLine()+": la variable "+id3.getText()+" es una cadena y por tanto no se puede decrementar.");
	  			}
				}
			}
			else 
				Procesador.println(0,"Linea "+id3.getLine()+": la variable "+id3.getText()+" no ha sido declarada. ");
				
		}	;

expresionOR returns [Object resultado = null]
{Object exp1 = null; Object exp2=null; Object exp3=null;}: 
	exp1= expresionXOR
	{
		resultado = exp1;
	}
	(
	linea:OP_O exp2=expresionXOR
	{
	  if(exp2 != null) 				// 2
			{
				if(exp2 instanceof Boolean && exp1 instanceof Boolean)	
				{
					boolean var1 = new Boolean(exp1.toString()).booleanValue();
					boolean var2 = new Boolean(exp2.toString()).booleanValue();
					var1 = var1 || var2;
					resultado = new Boolean(var1);	
					
				}
				else
				{
						Procesador.println(0,"Linea "+linea.getLine()+": Sólo se pueden realizar operaciones lógicas con booleanos.");
				}
			
			}
	  }	)*
	;

expresionXOR returns [Object resultado = null]
{Object exp1 = null; Object exp2=null; Object exp3=null;}: 
	exp1= expresionAND
	{
		resultado = exp1;
	}
	( linea:OP_OX exp3=expresionAND
	{
	  if(exp3 != null) 				// 2
			{
				if(exp3 instanceof Boolean && exp1 instanceof Boolean)	
				{
					boolean var1 = new Boolean(exp1.toString()).booleanValue();
					boolean var2 = new Boolean(exp3.toString()).booleanValue();
					if((var1 || var2) && (var1 != var2))
						resultado = new Boolean(true);	
					else 
						resultado = new Boolean(false);
					
				}
				else
				{
						Procesador.println(0,"Linea "+linea.getLine()+": Sólo se pueden realizar operaciones lógicas con booleanos.");
				}
			
			}
	  } )* 
	  ;

		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

expresionAND returns [Object resultado = null]
{Object exp1 = null ; Object exp2 = null;}: 

	exp1=expresionNOT {resultado = exp1;}
	( linea:OP_Y exp2=expresionNOT
	  {
	  if(exp2 != null) 				// 2
			{
				
				if(exp1 instanceof Boolean && exp2 instanceof Boolean )	
				{
					boolean var1 = new Boolean(exp1.toString()).booleanValue();
					boolean var2 = new Boolean(exp2.toString()).booleanValue();
					var1 = var1 && var2;
					resultado = new Boolean(var1);	
					
				}
				else
				{
						Procesador.println(0,"Linea "+linea.getLine()+": Sólo se pueden realizar operaciones lógicas con booleanos.");
				}
			
			}
	  }
	  )*;
	 
expresionNOT returns [Object resultado = null]
{Object exp1 = null ; Object exp2 = null;}: 

	(expr_relacional) => exp1=expr_relacional { resultado = exp1;}
	|
	( linea:OP_NO exp2=expr_relacional
	  {
	  if(exp2 != null) 				// 2
			{
				
			if(exp2 instanceof Integer)
				{
				Procesador.println(0,"Linea "+linea.getLine()+": No se pueden realizar operaciones booleanas con enteros.");
				}
			else
				{
				if(exp2 instanceof String)
					{
					Procesador.println(0,"Linea "+linea.getLine()+": No se pueden realizar operaciones booleanas con cadenas de caracteres.");
					}
				else
					{
			
					if(exp2 instanceof Double)
						Procesador.println(0,"Linea "+linea.getLine()+": No se pueden realizar operaciones booleanas con valores flotantes.");
					else
						{
						Boolean var = new Boolean(exp2.toString()).booleanValue();
						resultado = !var;
						}
					}
				}
	
			}
	  }
	  )*;

////////////////// EXPRESIONES RELACIONALES /////////////////////////////////

expr_relacional returns [Object respuesta = null]
{Object e1; Object e2;}:
	e1 = expr_aritmetica {
		respuesta = e1;
	}
	(linea:OP_MAYOR e2 = expr_aritmetica
	{
		if(e1 instanceof Integer && e2 instanceof Integer)
		{
			int num1 = new Integer(e1.toString()).intValue();
			int num2 = new Integer(e2.toString()).intValue();
			respuesta = new Boolean(num1>num2);
		}
		
		if(e1 instanceof Integer && e2 instanceof Double)
		{
			int num1 = new Integer(e1.toString()).intValue();
			double num2 = new Double(e2.toString()).doubleValue();
			respuesta = new Boolean(num1>num2);
		}
		
		if(e1 instanceof Double && e2 instanceof Double)
		{
			double num1 = new Double(e1.toString()).doubleValue();
			int num2 = new Integer(e2.toString()).intValue();
			respuesta = new Boolean(num1>num2);
		}
		
		if(e1 instanceof Double && e2 instanceof Double)
		{
			double num1 = new Double(e1.toString()).doubleValue();
			double num2 = new Double(e2.toString()).doubleValue();
			respuesta = new Boolean(num1>num2);
		}
		
		
		if(((e1 instanceof String == true) && (e2 instanceof String == false))
			||((e1 instanceof String == false)&&(e2 instanceof String == true)))
		{
			Procesador.println(0,"Linea "+linea.getLine()+": No se puede realizar expresiones relacionales con datos de distinto tipo");
			respuesta = new Boolean(false);
		}
		
			
	}
	|linea2:OP_MENOR e2 = expr_aritmetica
	{
		if(e1 instanceof Integer && e2 instanceof Integer)
		{
			int num1 = new Integer(e1.toString()).intValue();
			int num2 = new Integer(e2.toString()).intValue();
			respuesta = new Boolean(num1<num2);
		}
		
		if(e1 instanceof Integer && e2 instanceof Double)
		{
			int num1 = new Integer(e1.toString()).intValue();
			double num2 = new Double(e2.toString()).doubleValue();
			respuesta = new Boolean(num1<num2);
		}
		
		if(e1 instanceof Double && e2 instanceof Double)
		{
			double num1 = new Double(e1.toString()).doubleValue();
			int num2 = new Integer(e2.toString()).intValue();
			respuesta = new Boolean(num1<num2);
		}
		
		if(e1 instanceof Double && e2 instanceof Double)
		{
			double num1 = new Double(e1.toString()).doubleValue();
			double num2 = new Double(e2.toString()).doubleValue();
			respuesta = new Boolean(num1<num2);
		}
		
		
		if(((e1 instanceof String == true) && (e2 instanceof String == false))
			||((e1 instanceof String == false)&&(e2 instanceof String == true)))
		{
			Procesador.println(0,"Linea "+linea2.getLine()+": No se puede realizar expresiones relacionales con datos de distinto tipo");
			respuesta = new Boolean(false);
		}
	}
	|linea3:OP_IG e2 = expr_aritmetica
	{
		if(e1 instanceof Integer && e2 instanceof Integer)
		{
			int num1 = new Integer(e1.toString()).intValue();
			int num2 = new Integer(e2.toString()).intValue();
			respuesta = new Boolean(num1==num2);
		}
		
		if(e1 instanceof Integer && e2 instanceof Double)
		{
			int num1 = new Integer(e1.toString()).intValue();
			double num2 = new Double(e2.toString()).doubleValue();
			respuesta = new Boolean(num1==num2);
		}
		
		if(e1 instanceof Double && e2 instanceof Double)
		{
			double num1 = new Double(e1.toString()).doubleValue();
			int num2 = new Integer(e2.toString()).intValue();
			respuesta = new Boolean(num1==num2);
		}
		
		if(e1 instanceof Double && e2 instanceof Double)
		{
			double num1 = new Double(e1.toString()).doubleValue();
			double num2 = new Double(e2.toString()).doubleValue();
			respuesta = new Boolean(num1==num2);
		}
		
		
		if(((e1 instanceof String == true) && (e2 instanceof String == false))
			||((e1 instanceof String == false)&&(e2 instanceof String == true)))
		{
			Procesador.println(0,"Linea "+linea3.getLine()+": No se puede realizar expresiones relacionales con datos de distinto tipo");
			respuesta = new Boolean(false);
		}
	}
	|linea4:OP_DIST e2 = expr_aritmetica
	{
		if(e1 instanceof Integer && e2 instanceof Integer)
		{
			int num1 = new Integer(e1.toString()).intValue();
			int num2 = new Integer(e2.toString()).intValue();
			respuesta = new Boolean(num1!=num2);
		}
		
		if(e1 instanceof Integer && e2 instanceof Double)
		{
			int num1 = new Integer(e1.toString()).intValue();
			double num2 = new Double(e2.toString()).doubleValue();
			respuesta = new Boolean(num1!=num2);
		}
		
		if(e1 instanceof Double && e2 instanceof Double)
		{
			double num1 = new Double(e1.toString()).doubleValue();
			int num2 = new Integer(e2.toString()).intValue();
			respuesta = new Boolean(num1!=num2);
		}
		
		if(e1 instanceof Double && e2 instanceof Double)
		{
			double num1 = new Double(e1.toString()).doubleValue();
			double num2 = new Double(e2.toString()).doubleValue();
			respuesta = new Boolean(num1!=num2);
		}
		
		
		if(((e1 instanceof String == true) && (e2 instanceof String == false))
			||((e1 instanceof String == false)&&(e2 instanceof String == true)))
		{
			Procesador.println(0,"Linea "+linea4.getLine()+": No se puede realizar expresiones relacionales con datos de distinto tipo");
		}
	}
	)*;

////////////////////// REGLAS PARA LA EVALUACION DE EXPRESIONES ///////////////////

evaluarExpresion returns [Object respuesta = null]: 
	respuesta = expresionOR {Procesador.println(2, "Evaluar expresion: "+ respuesta);};

////////////////////// SENTENCIAS IF //////////////////////////////////////////////
//1 -> if de la forma si (VERDAD) { hola = 1; } sino { hola = 2; var otra; }
//2 -> if de la forma si (VERDAD) hola = 1; sino hola=2;
//3 -> if de la forma si (VERDAD) {hola = 1; } sino hola=2;
//4 -> if de la forma si (VERDAD) hola = 1; sino { hola=2; }
	sentenciaIF {Object o; boolean b=false;} : 
		(IF PAR_IZQ (evaluarExpresion) PAR_DER LLAVE_IZQ) => //1
		IF PAR_IZQ (o=evaluarExpresion) PAR_DER LLAVE_IZQ
        {
                if (o.getClass() == Boolean.class)
                           b = ((Boolean)o).booleanValue();
                else Procesador.println(0, " IF");
        }
        ({b==true}? (sentencia)* LLAVE_DER
        | {b==false}? (options{greedy=false;}:.)+ LLAVE_DER) 
        
        ((ELSE LLAVE_IZQ) => ELSE LLAVE_IZQ
        ({b==false}? (sentencia)* LLAVE_DER
        | {b==true}? (options{greedy=false;}:.)+ LLAVE_DER
        )
        |
        (ELSE ~LLAVE_IZQ) => ELSE 							//3
        ({b==false}? sentencia
        | {b==true}? (options{greedy=false;}:.)+ FIN_INSTRUCCION
        )
        )?
        |
        (IF PAR_IZQ (evaluarExpresion) PAR_DER) => 			//2
         IF PAR_IZQ (o=evaluarExpresion) PAR_DER 
        {
                if (o.getClass() == Boolean.class)
                           b = ((Boolean)o).booleanValue();
                else Procesador.println(0, " IF");
        }
        ({b==true}? sentencia 
        | {b==false}? (options{greedy=false;}:.)+ FIN_INSTRUCCION)
        
        (
        (ELSE ~LLAVE_IZQ) => ELSE
        ({b==false}? sentencia
        | {b==true}? (options{greedy=false;}:.)+ FIN_INSTRUCCION	
        )
        |
        (ELSE LLAVE_IZQ) => ELSE LLAVE_IZQ					//4
        ({b==false}? (sentencia)* LLAVE_DER
        | {b==true}? (options{greedy=false;}:.)+ LLAVE_DER
        )
        )?
        ;
    
    
	/*
	sentenciaSwitch {Object resultado;} :
	SWITCH PAR_IZQ resultado=expresionOR PAR_DER LLAVE_IZQ 
	(casosSwitch[resultado])*
	LLAVE_DER
	{
	System.out.println("switch");
	};

	
	casosSwitch [Object resultado] {Object res_eva;}: CASE res_eva = expresionOR DOBLE_PUNTO LLAVE_IZQ 
	({resultado == res_eva}? (sentencia)* LLAVE_DER
    |{resultado != res_eva}? (options{greedy=false;}:.)+ LLAVE_DER
    )	
	END_CASE FIN_INSTRUCCION ;
	*/

eliminar_var {String res;}: SUP id:IDENT
	{
		  res=tablaSimbolos.delSimbolo(id);
		  if(res.compareTo(id.getText()) == 0)
		  	Procesador.println(1, "Variable \""+id.getText()+"\" ha sido eliminada");
		  else 
		  	Procesador.println(0, "Variable \""+id.getText()+"\" no ha sido eliminada, no existe");
	};

fin_interprete:
	{
		Procesador.println(-1,"...FINALIZANDO STICKY...");	
	}
	FIN_INTERPRETE
	{
		consumeUntil(Token.EOF_TYPE);
		consume();
		
	};


//1 -> while de la forma mientras (VERDAD) { hola = 1; hola = 2; var otra; }
//2 -> while de la forma mientras (VERDAD) hola = 1; sino hola=2;

sentenciaWHILE
    {
        Boolean b = null; 
        Object expresion = null; 
        int marker = mark();
    } :
	
	(B_WHILE PAR_IZQ evaluarExpresion PAR_DER LLAVE_IZQ) =>   //1
	B_WHILE PAR_IZQ expresion = evaluarExpresion PAR_DER LLAVE_IZQ
	{
		b = ((Boolean)expresion).booleanValue();
				
	} 
	({b==true}? (sentencia)* LLAVE_DER {rewind(marker);}
    | {b==false}? (options{greedy=false;}:.)+ LLAVE_DER) 
	|
	
	(B_WHILE PAR_IZQ evaluarExpresion PAR_DER) =>   //2
	B_WHILE PAR_IZQ expresion = evaluarExpresion PAR_DER
	{
		b = ((Boolean)expresion).booleanValue();
				
	} 
		({b==true}? sentencia {rewind(marker);}
        | {b==false}? (options{greedy=false;}:.)+ FIN_INSTRUCCION )
	;


//Permite bucles-for de la forma:	
//Alternativa 1: para(identificador;expresion booleana; entero) { intruccion; intruccion2; }
//Alternativa 2: para(identificador;expresion booleana; entero)  intruccion; 
//NOTA: El valor de identificador queda alterado después de la ejecución del bucle.

sentenciaFOR
    {
        Boolean b = null; 
        Boolean hecho = false;
        Object expresion = null; 
        int marker = mark();
    	int numero = 0;
    } :
    	//1
	(B_FOR PAR_IZQ IDENT FIN_INSTRUCCION evaluarExpresion FIN_INSTRUCCION ENTERO PAR_DER LLAVE_IZQ) =>
	B_FOR PAR_IZQ id:IDENT FIN_INSTRUCCION expresion = evaluarExpresion FIN_INSTRUCCION n:ENTERO PAR_DER LLAVE_IZQ
	{
		b = ((Boolean)expresion).booleanValue();
		
		if(hecho == false) {
		//Obtiene el valor de la variable id
		if(tablaSimbolos.existeSimbolo(id.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id.getText());
				
				if(contenido==null)
				{
					Procesador.println(0,"Linea "+id.getLine()+": La variable no tiene asignado ningun valor "+id.getText());	
				}
				
				if(contenido.matches("[0-9~.]*"))
	  				{
	  				numero = new Integer(contenido.toString()).intValue();
	  				hecho = true;
	  				}	
			}
			else 
				Procesador.println(0,"Linea "+id.getLine()+": la variable no ha sido declarada "+id.getText());
			
			}
			
		numero = numero + Integer.parseInt(n.getText());
			
		//Guarda el valor 
		tablaSimbolos.set(id, numero);
	} 
	({b==true}? (sentencia)* LLAVE_DER {rewind(marker);}
    | {b==false}? (options{greedy=false;}:.)+ LLAVE_DER) 
	
	|
		//2
	(B_FOR PAR_IZQ IDENT FIN_INSTRUCCION evaluarExpresion FIN_INSTRUCCION ENTERO PAR_DER) => 
	B_FOR PAR_IZQ id2:IDENT FIN_INSTRUCCION expresion = evaluarExpresion FIN_INSTRUCCION n2:ENTERO PAR_DER
	{
		b = ((Boolean)expresion).booleanValue();
		
		if(hecho == false) {
		//Obtiene el valor de la variable id
		if(tablaSimbolos.existeSimbolo(id2.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id2.getText());
				
				if(contenido==null)
				{
					Procesador.println(0,"Linea "+id2.getLine()+": La variable no tiene asignado ningun valor "+id2.getText());	
				}
				
				if(contenido.matches("[0-9~.]*"))
	  				{
	  				numero = new Integer(contenido.toString()).intValue();
	  				hecho = true;
	  				}	
			}
			else 
				Procesador.println(0,"Linea "+id2.getLine()+": la variable no ha sido declarada "+id2.getText());
			
			}
			
		numero = numero + Integer.parseInt(n2.getText());
			
		//Guarda el valor 
		tablaSimbolos.set(id2, numero);
	} 
	({b==true}? sentencia {rewind(marker);}
    | {b==false}? (options{greedy=false;}:.)+ FIN_INSTRUCCION) 
	
	;
	
imprimir returns [String respuesta=null]
{ String expr1; String expr2;}:
IMPRIMIR PAR_IZQ
expr1 = impr_base { respuesta = expr1;}
(OP_SUM expr2 = impr_base
{
	respuesta = new String(expr1+expr2);
	expr1 = respuesta;
	
})* PAR_DER
;

impr_base returns [String respuesta=null]
{Object e;}:
	(n1:CADENA {
		//Elimina las comillas 
		int longitud = (n1.getText()).length();
		char [] cadena1 = (n1.getText()).toCharArray();
		String cadena2 = new String();
		
		for(int i = 0; i < (n1.getText()).length(); i++)
		{
			if(cadena1[i] != '"')
				cadena2 += cadena1[i];
		}
		respuesta = cadena2; 
	}
	|id:IDENT
	{
			//Comprobamos que la variable este declarada
			if(tablaSimbolos.existeSimbolo(id.getText()))
			{
				//Contenido de la variable
				String contenido = tablaSimbolos.getContenidoSimbolo(id.getText());
				
				if(contenido.compareTo("") == 0)
				{
					Procesador.println(0,"Linea "+id.getLine()+": La variable "+id.getText()+" no tiene asignado ningun valor.");	
				}
				else
				{
					//Elimina las comillas 
					int longitud = contenido.length();
					char [] cadena1 = contenido.toCharArray();
					String cadena2 = new String();
		
					for(int i = 0; i < contenido.length(); i++)
						{
						if(cadena1[i] != '"')
						cadena2 += cadena1[i];
						}
					respuesta = cadena2;		
				}
			}
			else // No está declarado
				Procesador.println(0,"Linea "+id.getLine()+": la variable no ha sido declarada "+id.getText());
	} )
	;

		