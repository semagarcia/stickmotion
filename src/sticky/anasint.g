header {
	package sticky;
	import java.util.HashMap;
	import sticky.symboltable.SymbolTable;
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
		SymbolTable tablaSimbolos= new SymbolTable();
		double const_pi = Math.PI;
		//Exception function
		void mostrarExcepcion(RecognitionException re)
		{
			Processor.println(0,"En línea " + re.getLine() + " : " + re.getMessage());
			reportError(re);
			try {
    			consume(); //Consume the token problem
	    		consumeUntil(FIN_INSTRUCCION);
			} catch (Exception e) {
			}
		}
	}

	instrucciones 
	{
		Object x=null;
		Processor.println(-1,"...INICIANDO STICKY...");		
	} 
	
	: (sentencia)* fin_interprete; //One or more sentences, and then it ends
	sentencia: (simple fi:FIN_INSTRUCCION) | bucle; //Either one simple sentence with ; or loop, that no needs it;
	exception
 		catch [RecognitionException re] {
	    	mostrarExcepcion(re);
		 }

	simple {String valor; Object valor2;}: declaracion | asignacion | eliminar_var | funcion_sticky | valor=imprimir { Processor.println(-1,valor); } | valor2=expr_incremento; //Esto permitirá usar ; para salir del greedy, ya que ; no se pide aquí

	bucle: sentencia_if | sentencia_while | sentencia_for | sentencia_switch;

	exception
 			catch [RecognitionException re] {
 				mostrarExcepcion(re);
		}
	

	//In order to declare variables we have three options
	//1. Declare variable without initialization
	//2. Declare a variable with initialization
	//3. Declare more than one variable
	//NOTE: NOT allowed variable initialization while declaring more than one.
declaracion {Object x = null; ArrayList lista = new ArrayList();}:
	(	//Option 1
		(var1:VAR IDENT FIN_INSTRUCCION) => VAR i1:IDENT
		{ 
		  boolean res=tablaSimbolos.put(i1);
		  if(res)
		  	Processor.println(1, "Linea "+i1.getLine()+": Variable \""+i1.getText()+"\" ha sido declarada");
		  else
		  	Processor.println(0, "Linea "+i1.getLine()+": Variable \""+i1.getText()+"\" no ha sido declarada, ya existe");
		}
		//Option 2
		|(VAR IDENT OP_ASIG expr_or) =>VAR i3:IDENT OP_ASIG (x=expr_or) 

			{			
				boolean res = tablaSimbolos.put(i3,x);	//Insert the value in the symboltable
				if(res)
		  			Processor.println(1, "Linea "+i3.getLine()+": Variable \""+i3.getText()+"\" ha sido declarada con valor "+x);
		  		else 
		  			Processor.println(0, "Linea "+i3.getLine()+": Variable \""+i3.getText()+"\" no ha sido declarada, ya existe");
		
			}
		//Option 3
		|(VAR IDENT (SEPARA IDENT)*) => var4:VAR i4:IDENT (SEPARA i3_alt:IDENT {lista.add(i3_alt);} )* 
					{
						// We should insert each identifier found in the symbols table
						boolean res = tablaSimbolos.put(i4);
						if(res)
		  					Processor.println(1,"Linea "+i4.getLine()+": Variable \""+i4.getText()+"\" ha sido declarada");
		  				else 
		  					Processor.println(0,"Linea "+i4.getLine()+": Variable \""+i4.getText()+"\" no ha sido declarada, ya existe");
						Token tok;
						for(int j=0; j < lista.size(); j++)
						{
								tok = (Token)lista.get(j); //Gets a identifier from de list
								res = tablaSimbolos.put(tok);
								if(res)
		  							Processor.println(1, "Linea "+tok.getLine()+": Variable \""+tok.getText()+"\" ha sido declarada");
		  						else 
		  							Processor.println(0, "Linea "+tok.getLine()+": Variable \""+tok.getText()+"\" no ha sido declarada, ya existe");
						}
					}

					
	);
	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
		 }

		

asignacion 
	{ Object respuesta; Object respuesta2;}:

	i2:IDENT OP_ASIG (respuesta = expr_or)
	{		
			if(tablaSimbolos.set(i2,respuesta))	
				Processor.println(1, "Linea "+i2.getLine()+": Asignacion a la variable \""+i2.getText()+"\": "+respuesta);
			else 
				Processor.println(0, "Linea "+i2.getLine()+": Asignacion no realizada, no existe la variable \""+i2.getText()+"\"");
	} ;

	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
		 }


	
expr_aritmetica returns[Object resultado = null]
{Object e1 = null; Object e2 = null; Object e3 = null;}:
	e1=expr_mod
	{
		//This is in case of var =- 2; The char - at the top makes the first operand null and the second 2
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
			Processor.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
		}
		
		if(e1 instanceof Boolean || e2 instanceof Boolean)
		{
			Processor.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
		}
		
	})*
	;
	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		 }
	

expr_mod returns [Object resultado = null]
{Object e1; Object e2; Object e3; Object e4;}: 
	e1= expr
	{
		//This is in case of var =- 2; The char - at the top makes the first operand null and the second 2
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
	  				Processor.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  			}
	  		}
	  		else
	  		{
	  			if(e1 instanceof Boolean || e2 instanceof Boolean)
	  				Processor.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
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
	  			Processor.println(0,"Linea "+linea2.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  		}
	  		else
	  		{
	  			if(e1 instanceof Boolean || e3 instanceof Boolean)
	  				Processor.println(0,"Linea "+linea2.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
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
		//This is in case of var =- 2; The char - at the top makes the first operand null and the second 2
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
	  			Processor.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  	}
	  	
	  	if(e1 instanceof Boolean || e2 instanceof Boolean)
	  	{
	  			Processor.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
	  	}
	  }
	  | linea2:OP_DIV e3 = expr_mult
	  {
	  	if(e1 instanceof Integer && e3 instanceof Integer)
	  	{
	  		double valor1 = new Double(e1.toString()).floatValue();
	  		double valor2 = new Double(e3.toString()).floatValue();
	  		
	  		if((valor2 !=0) && (valor1 != 0))
	  		{
	  				
	  				resultado = new Double(valor1/valor2); 
	  				e1=resultado;
	  		}
	  		else {
	  			Processor.println(0,"Linea "+linea2.getLine()+": División por 0");
	  			resultado = null;
	  		}
	  	}
	  	else
	  	{
	  		
	  		if(e1 instanceof String || e3 instanceof String)
	  		{
	  			Processor.println(0,"Linea "+linea2.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  		}
	  		else
	  		{
	  			if(e1 instanceof Boolean || e3 instanceof Boolean)
	  				Processor.println(0,"Linea "+linea2.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
	  			else
	  			{	
	  				//else floats
	  				double valor1 = new Double(e1.toString()).doubleValue();
	  				double valor2 = new Double(e3.toString()).doubleValue();
	  			
	  				if((valor2 !=0)&&(valor1 != 0))
	  				{	
	  					resultado = new Double(valor1/valor2);
	  					e1=resultado;
	  				}
	  				else
	  					Processor.println(0,"Linea "+linea2.getLine()+": División por 0");
	  			}
	  		}
	  			
	  	}	
	  })*;
	  

expr_mult returns [Object resultado = null]
{Object e1; Object e2;}:
	
	e1=expr_raiz
	{
		//This is in case of var =- 2; The char - at the top makes the first operand null and the second 2
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
				Processor.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
			}
			else
			{
			
				if(e1 instanceof String || e2 instanceof String)
					Processor.println(0,"Linea "+linea1.getLine()+": No se pueden realizar operaciones aritmeticas con valores booleanos");
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
		//This is in case of var =- 2; The char - at the top makes the first operand null and the second 2
		if(exp1 == null)
			exp1 = 0; 
			resultado = exp1;
		}
	|
	( linea:OP_RAIZ exp2=expr_base
	  {
	  if(exp2 != null) 
			{
				
			if(exp2 instanceof Boolean)
				{
				Processor.println(0,"Linea "+linea.getLine()+": No se pueden realizar operaciones aritmeticas con booleanos.");
				}
			else
				{
				if(exp2 instanceof String)
					{
					Processor.println(0,"Linea "+linea.getLine()+": No se pueden realizar operaciones aritmeticas con cadenas de caracteres.");
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
		|n6:PI { resultado = new Double(const_pi); Processor.println(2,"PI declarada: "+ resultado); } //PI
		|(IDENT) => id:IDENT
		{
			
			if(tablaSimbolos.existeSimbolo(id.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id.getText());
				
				if(contenido.compareTo("") == 0)
				{
					Processor.println(0,"Linea "+id.getLine()+": La variable "+id.getText()+" no tiene asignado ningun valor.");	
				}
				else {
				//If there is a string (if it takes " 'matches' returns false)
				if(contenido.matches("(-)?[0-9.]+|true|false"))
	  				{
	  				//If false o true is a boolean
	  				if(contenido.matches("true|false"))
	  					{
	  					resultado = new Boolean(contenido.toString()).booleanValue();
	  					}
	  				//If takes only numeric values
	  				else if(contenido.matches("[0-9]+"))
	  					{
	  					resultado = new Integer(contenido.toString()).intValue();
	  					}
	  				//Else is a integer
	  				else {
	  					resultado = new Float(contenido.toString()).floatValue();
	  					}
	  				}
	  			else //is a string
	  			{
	  				resultado = new String(contenido.toString());
	  			}
				}
			}
			else 
				Processor.println(0,"Linea "+id.getLine()+": la variable "+ id.getText() +" no ha sido declarada.");

				
		}
		| resultado = expr_incremento
		| (PAR_IZQ expr_aritmetica PAR_DER) => PAR_IZQ (resultado = expr_aritmetica) PAR_DER
		| (PAR_IZQ expr_or PAR_DER) => PAR_IZQ (resultado = expr_or) PAR_DER
		;
		exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
		 }


expr_incremento returns [Object resultado = null]:
(IDENT OP_INC) => id2:IDENT OP_INC
		{
			if(tablaSimbolos.existeSimbolo(id2.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id2.getText());
				
				if(contenido.compareTo("") == 0)
				{
					Processor.println(0,"Linea "+id2.getLine()+": La variable "+id2.getText()+" no tiene asignado ningun valor.");	
				}
				else {
				//If there is a string (if it takes " 'matches' returns false)
				if(contenido.matches("[0-9.]+|true|false"))
	  				{
	  				//If takes true o false is a boolean
	  				if(contenido.matches("true|false"))
	  					{
	  					Processor.println(0,"Linea "+id2.getLine()+": la variable "+id2.getText()+" es un booleano y por tanto no se puede incrementar.");
	  					}
	  				//If takes only numeric values
	  				else if(contenido.matches("[0-9]+"))
	  					{
	  					resultado = new Integer(contenido.toString()).intValue()+1;
	  					tablaSimbolos.set(id2,resultado);
	  					}
	  				//And else is a integer
	  				else {
	  					resultado = new Float(contenido.toString()).floatValue()+1;
	  					tablaSimbolos.set(id2,resultado);
	  					}
	  				}
	  			else //is a string
	  			{
	  				Processor.println(0,"Linea "+id2.getLine()+": la variable "+id2.getText()+" es una cadena y por tanto no se puede incrementar.");
	  			}
				}
			}
			else 
				Processor.println(0,"Linea "+id2.getLine()+": la variable "+id2.getText()+" no ha sido declarada.");

				
		}
		| (IDENT OP_DEC) => id3:IDENT OP_DEC
		{
			if(tablaSimbolos.existeSimbolo(id3.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id3.getText());
				
				if(contenido.compareTo("") == 0)
				{
					Processor.println(0,"Linea "+id3.getLine()+": La variable "+id3.getText()+" no tiene asignado ningun valor.");	
				}
				else {
				//If there is a string (if it takes " 'matches' returns false)
				if(contenido.matches("[0-9.]+|true|false"))
	  				{
	  				//If takes true or false is a boolean
	  				if(contenido.matches("true|false"))
	  					{
	  					Processor.println(0,"Linea "+id3.getLine()+": la variable "+id3.getText()+" es un booleano y por tanto no se puede decrementar.");
	  					}
	  				//If takes only numeric values
	  				else if(contenido.matches("[0-9]+"))
	  					{
	  					resultado = new Integer(contenido.toString()).intValue()-1;
	  					tablaSimbolos.set(id3,resultado);
	  					}
	  				//And else is a integer
	  				else {
	  					resultado = new Float(contenido.toString()).floatValue()-1;
	  					tablaSimbolos.set(id3,resultado);
	  					}
	  				}
	  			else //It's a string
	  			{
	  				Processor.println(0,"Linea "+id3.getLine()+": la variable "+id3.getText()+" es una cadena y por tanto no se puede decrementar.");
	  			}
				}
			}
			else 
				Processor.println(0,"Linea "+id3.getLine()+": la variable "+id3.getText()+" no ha sido declarada. ");
				
		}	;


expr_or returns [Object resultado = null]

{Object exp1 = null; Object exp2=null; Object exp3=null;}: 
	exp1= expr_xor
	{
		resultado = exp1;
	}
	(
	linea:OP_O exp2=expr_xor
	{
	  if(exp2 != null) 
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
						Processor.println(0,"Linea "+linea.getLine()+": Sólo se pueden realizar operaciones lógicas con booleanos.");
				}
			
			}
	  }	)*
	;
	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
		 }

expr_xor returns [Object resultado = null]
{Object exp1 = null; Object exp2=null; Object exp3=null;}: 
	exp1= expr_and
	{
		resultado = exp1;
	}
	( linea:OP_OX exp3=expr_and
	{
	  if(exp3 != null)
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
						Processor.println(0,"Linea "+linea.getLine()+": Sólo se pueden realizar operaciones lógicas con booleanos.");
				}
			
			}
	  } )* 
	  ;

expr_and returns [Object resultado = null]
{Object exp1 = null ; Object exp2 = null;}: 

	exp1=expr_not {resultado = exp1;}
	( linea:OP_Y exp2=expr_not
	  {
	  if(exp2 != null) 
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
						Processor.println(0,"Linea "+linea.getLine()+": Sólo se pueden realizar operaciones lógicas con booleanos.");
				}
			
			}
	  }
	  )*;
	 
expr_not returns [Object resultado = null]
{Object exp1 = null ; Object exp2 = null;}: 

	(expr_relacional) => exp1=expr_relacional { resultado = exp1;}
	|
	( linea:OP_NO exp2=expr_relacional
	  {
	  if(exp2 != null) 
			{
				
			if(exp2 instanceof Integer)
				{
				Processor.println(0,"Linea "+linea.getLine()+": No se pueden realizar operaciones booleanas con enteros.");
				}
			else
				{
				if(exp2 instanceof String)
					{
					Processor.println(0,"Linea "+linea.getLine()+": No se pueden realizar operaciones booleanas con cadenas de caracteres.");
					}
				else
					{
			
					if(exp2 instanceof Double)
						Processor.println(0,"Linea "+linea.getLine()+": No se pueden realizar operaciones booleanas con valores flotantes.");
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
			Processor.println(0,"Linea "+linea.getLine()+": No se puede realizar expresiones relacionales con cadenas");
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
			Processor.println(0,"Linea "+linea2.getLine()+": No se puede realizar expresiones relacionales con cadenas");
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
			Processor.println(0,"Linea "+linea3.getLine()+": No se puede realizar expresiones relacionales con cadenas");
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
			Processor.println(0,"Linea "+linea4.getLine()+": No se puede realizar expresiones relacionales con cadenas");
		}
	}
	)*;

evaluar_expr returns [Object respuesta = null]: 
	respuesta = expr_or {Processor.println(2, "Evaluar expresion: "+ respuesta);};
	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
		 }


///////////////////////////If sentences/////////////////////////////////////
//1 -> if in the form si (VERDAD) { hola = 1; } sino { hola = 2; var otra; }
//2 -> if in the form si (VERDAD) hola = 1; sino hola=2;
//3 -> if in the form si (VERDAD) {hola = 1; } sino hola=2;
//4 -> if in the form si (VERDAD) hola = 1; sino { hola=2; }
	sentencia_if {Object o; boolean b=false;} : 
		(IF PAR_IZQ (evaluar_expr) PAR_DER LLAVE_IZQ) => //1
		IF id:PAR_IZQ (o=evaluar_expr) PAR_DER LLAVE_IZQ
        {
                if (o.getClass() == Boolean.class)
                           b = ((Boolean)o).booleanValue();
                else Processor.println(0, "Linea "+id.getLine()+": La expresión de condición de SI debe ser booleana");
        }
        ({b==true}? (sentencia)* LLAVE_DER
        | {b==false}? (options{greedy=false;}:.)* LLAVE_DER)
        
        ((ELSE LLAVE_IZQ) => ELSE LLAVE_IZQ
        ({b==false}? (sentencia)* LLAVE_DER
        | {b==true}? (options{greedy=false;}:.)* LLAVE_DER
        )
        |
        (ELSE ~LLAVE_IZQ) => ELSE 							//3
        ({b==false}? sentencia
        | {b==true}? (options{greedy=false;}:.)* FIN_INSTRUCCION
        )
        )?
        |
        (IF PAR_IZQ (evaluar_expr) PAR_DER) => 			//2
         id2: IF PAR_IZQ (o=evaluar_expr) PAR_DER 
        {
                if (o.getClass() == Boolean.class)
                           b = ((Boolean)o).booleanValue();
                else Processor.println(0, "Linea "+id2.getLine()+": La expresión de condición de SI debe ser booleana");
        }
        ({b==true}? sentencia 
        | {b==false}? (options{greedy=false;}:.)* FIN_INSTRUCCION)
        
        (
        (ELSE ~LLAVE_IZQ) => ELSE
        ({b==false}? sentencia
        | {b==true}? (options{greedy=false;}:.)* FIN_INSTRUCCION	
        )
        |
        (ELSE LLAVE_IZQ) => ELSE LLAVE_IZQ					//4
        ({b==false}? (sentencia)* LLAVE_DER
        | {b==true}? (options{greedy=false;}:.)+ LLAVE_DER
        )
        )?
        ;
       	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
		 }
		
    

	sentencia_switch {Object resultado; int flag; int acumulador = 0; int contador = 0;} :
	SWITCH PAR_IZQ (resultado=expr_or) PAR_DER LLAVE_IZQ
	(flag = casos_switch[resultado] {acumulador += flag; contador++;})* (DEFAULT DOBLE_PUNTO {Processor.println(2, "Valor de acumulador: "+acumulador);} LLAVE_IZQ

	({ acumulador == contador}? (sentencia)* LLAVE_DER //if flag is equal to contador has not run any case
    |{ acumulador < contador }? (options{greedy=false;}:.)+ LLAVE_DER )
	END_CASE FIN_INSTRUCCION)? LLAVE_DER
	;
	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
		 }
	
	
	casos_switch [Object resultado] returns [int n=0] {Object res_eva; String cadena1 = null; String cadena2 = null;}: CASE (res_eva = expr_or) 
	DOBLE_PUNTO LLAVE_IZQ {
		//Instances of differents data types
		Object numero = (Integer)2;
		Object bool = (Boolean)true;
		Object cadena = (String)"cadena";
		Object flotante = (Double)2.2;
		
		//Convert to string the results of the two evaluations (Only for compare then before of the greedy, in order to avoid the error while obtaining the datatype of an undetermined object
		if(resultado.getClass() == numero.getClass())
			cadena1 = String.valueOf(((Integer)resultado).intValue());
		else if(resultado.getClass() == bool.getClass())
			cadena1 = String.valueOf(((Boolean)resultado).booleanValue());
		else if(resultado.getClass() == flotante.getClass())
			cadena1 = String.valueOf(((Double)resultado).floatValue());
		else 
			cadena1 = resultado.toString();
		
		if(res_eva.getClass() == numero.getClass())
			cadena2 = String.valueOf(((Integer)res_eva).intValue());
		else if(res_eva.getClass() == bool.getClass())
			cadena2 = String.valueOf(((Boolean)res_eva).booleanValue());
		else if(res_eva.getClass() == flotante.getClass())
			cadena2 = String.valueOf(((Double)res_eva).floatValue());
		else 
			cadena2 = res_eva.toString();
	
	}
	
	({(cadena2.toString()).compareTo(cadena1.toString()) == 0}? (sentencia)* {n=0;} LLAVE_DER
    |{(cadena2.toString()).compareTo(cadena1.toString()) != 0}? (options{greedy=false;}:.)+ {n=1;} LLAVE_DER )	
	END_CASE FIN_INSTRUCCION ;
	
//1 -> while in the form mientras (VERDAD) { hola = 1; hola = 2; var otra; }
//2 -> while in the form mientras (VERDAD) hola = 1;
sentencia_while
    {
        Boolean b = null; 
        Object expresion = null; 
        int marker = mark();
    } :
	
	(B_WHILE PAR_IZQ evaluar_expr PAR_DER LLAVE_IZQ) =>   //1
	B_WHILE PAR_IZQ expresion = evaluar_expr PAR_DER LLAVE_IZQ
	{
		try {
			if (expresion != null)
				b = ((Boolean)expresion).booleanValue();
			else
				b = false;
		}	catch (Exception e) {
		 Processor.println(0, "En línea " + ": " + e.toString() );
		}			
	} 
	({b==true}? (sentencia)* LLAVE_DER {rewind(marker);}
    | {b==false}? (options{greedy=false;}:.)* LLAVE_DER) 
	|
	
	(B_WHILE PAR_IZQ evaluar_expr PAR_DER) =>   //2
	B_WHILE PAR_IZQ expresion = evaluar_expr PAR_DER
	{
		try {
			if (expresion != null)
				b = ((Boolean)expresion).booleanValue();
			else
				b = false;
		}	catch (Exception e) {
		 Processor.println(0, "En línea " + ": " + e.toString() );
		}		
				
	} 
		({b==true}? sentencia {rewind(marker);}
        | {b==false}? (options{greedy=false;}:.)+ FIN_INSTRUCCION )
	;
	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
		 }
		

//Recognizes bucles-for in the form:	
//Option 1: para(identifier;boolean expression; integer) { intruction; intruction2; }
//Option 2: para(identifier;boolean expression; integer)  intruction; 
//NOTE: The identificador value is altered after the execution of the loop.
sentencia_for
    {
        Boolean b = null; 
        Boolean hecho = false;
        Object expresion = null; 
        int marker = mark();
    	int numero = 0;
    } :
    	//1
	(B_FOR PAR_IZQ IDENT FIN_INSTRUCCION evaluar_expr FIN_INSTRUCCION ENTERO PAR_DER LLAVE_IZQ) =>
	B_FOR PAR_IZQ id:IDENT FIN_INSTRUCCION expresion = evaluar_expr FIN_INSTRUCCION n:ENTERO PAR_DER LLAVE_IZQ
	{		
		try {
			b = ((Boolean)expresion).booleanValue();
		} catch(Exception e) {
			Processor.println(0,"Linea "+id.getLine()+": La expresión de condición del PARA debe ser booleana");
		}
		
		if(hecho == false) {
		//Get value from variable id
		if(tablaSimbolos.existeSimbolo(id.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id.getText());
				
				if(contenido==null)
				{
					Processor.println(0,"Linea "+id.getLine()+": La variable no tiene asignado ningun valor "+id.getText());	
				}
				
				if(contenido.matches("[0-9~.]*"))
	  				{
	  				numero = new Integer(contenido.toString()).intValue();
	  				hecho = true;
	  				}	
			}
			else 
				Processor.println(0,"Linea "+id.getLine()+": la variable no ha sido declarada "+id.getText());
			
			}
			
		numero = numero + Integer.parseInt(n.getText());
			
		//Saves the value
		tablaSimbolos.set(id, numero);
	} 
	({b==true}? (sentencia)* LLAVE_DER {rewind(marker);} 
    |{b==false}? (options{greedy=false;}:.)* LLAVE_DER)
	
	|
		//2
	(B_FOR PAR_IZQ IDENT FIN_INSTRUCCION evaluar_expr FIN_INSTRUCCION ENTERO PAR_DER) => 
	B_FOR PAR_IZQ id2:IDENT FIN_INSTRUCCION expresion = evaluar_expr FIN_INSTRUCCION n2:ENTERO PAR_DER
	{
		b = ((Boolean)expresion).booleanValue();
		
		if(hecho == false) {
		//Get value for id variable
		if(tablaSimbolos.existeSimbolo(id2.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id2.getText());
				
				if(contenido==null)
				{
					Processor.println(0,"Linea "+id2.getLine()+": La variable no tiene asignado ningun valor "+id2.getText());	
				}
				
				if(contenido.matches("[0-9~.]*"))
	  				{
	  				numero = new Integer(contenido.toString()).intValue();
	  				hecho = true;
	  				}
			}
			else 
				Processor.println(0,"Linea "+id2.getLine()+": la variable no ha sido declarada "+id2.getText());
			
			}
			
		numero = numero + Integer.parseInt(n2.getText());
			
		//Saves the value
		tablaSimbolos.set(id2, numero);
	} 
	({b==true}? sentencia {rewind(marker);}
    | {b==false}? (options{greedy=false;}:.)* FIN_INSTRUCCION) 
	
	;
	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
		 }

eliminar_var {String res;}: SUP id:IDENT
	{
		try {
		  res=tablaSimbolos.delSimbolo(id);
		  if(res.compareTo(id.getText()) == 0)
		  	Processor.println(1, "Linea "+id.getLine()+": Variable \""+id.getText()+"\" ha sido eliminada");
		  else 
		  	Processor.println(0, "Linea "+id.getLine()+": Variable \""+id.getText()+"\" no ha sido eliminada, no existe");
		} catch(Exception e) {
			Processor.println(0, "En línea " + id.getLine() + ": " + e.toString() );
		}
	};
	exception
 		catch [RecognitionException re] {
	    	mostrarExcepcion(re);
		 }
	
funcion_sticky: f_tiempo | f_mover | f_flexionar | f_girar;

f_tiempo {Object res1;}: tiempo:TIEMPO (est:ESTABLECE|AVANZA) PAR_IZQ res1=expr_aritmetica PAR_DER
{
	// Convert to correct format in order to call the function
	String cadena1 = res1.toString();
	try {
		float res1_float = Float.parseFloat(cadena1);
		int res1_int = Math.round(res1_float);

		Processor.println(1,"Entrando tiempo sticky. "+"res1_int: "+ res1_int);

		//If option is ESTABLECE, function "tiempo establece" is called.
		if(est != null) {
			Processor.println(1,"FSticky -> tiempo establece.");
			gui.StickMotion.scene.setTime(res1_int);
		}// Else "tiempo avanza" is called.
		else {
			Processor.println(1,"FSticky -> tiempo avanza.");
			gui.StickMotion.scene.addTime(res1_int);
		}
	}catch (NumberFormatException nfe) {
		 Processor.println(0, "En línea " + tiempo.getLine() + ": El parámetro debe ser un número." );
	} 
	catch (Exception e) {
		 Processor.println(0, "En línea " + tiempo.getLine() + ": " + e.toString() );
	}
};
	exception
 		catch [RecognitionException re] {
	    	mostrarExcepcion(re);
		 }


f_mover {Object res1; Object res2; Object res3; Object res4;}: mover:MOVER STICKMAN PAR_IZQ
		res1=expr_aritmetica SEPARA
		res2=expr_aritmetica SEPARA
		res3=expr_aritmetica SEPARA
		res4=expr_aritmetica PAR_DER 
{
	Processor.println(1,"Entrando mover sticky.");
	
	// Convert to correct format in order to call the function
	// Exception in Java.
	try {
		String cadena1 = res1.toString(); 
		float res1_float = Float.parseFloat(cadena1);
		String cadena2 = res2.toString(); 
		float res2_float = Float.parseFloat(cadena2);
		String cadena3 = res3.toString(); 
		float res3_float = Float.parseFloat(cadena3);
		String cadena4 = res4.toString(); 
		float res4_float = Float.parseFloat(cadena4);
		int res4_int = Math.round(res4_float);
	
		gui.StickMotion.scene.moveStickman(res1_float, res2_float, res3_float, res4_int);
	}catch (NumberFormatException nfe) {
		 Processor.println(0, "En línea " + mover.getLine() + ": El parámetro debe ser un número." );
	} 
	catch (Exception e) {
		 Processor.println(0, "En línea " + mover.getLine() + ": " + e.toString() );
	}
};	// Exception in ANTLR.
	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
	}

f_flexionar {Object res1; Object res2;}: flex:FLEXIONAR (brazo:BRAZO | PIERNA) (der:DER | IZQ) 
		PAR_IZQ res1=expr_aritmetica SEPARA res2=expr_aritmetica PAR_DER
{
	Processor.println(1,"Entrando flexionar sticky.");
	
	try {
		// Convert to correct format in order to call the function
		String cadena1 = res1.toString(); 
		float res1_float = Float.parseFloat(cadena1);
		String cadena2 = res2.toString(); 
		float res2_float = Float.parseFloat(cadena2);
		int res2_int = Math.round(res2_float);


	//if BRAZO is selected, stiky's amr is moved, else PIERNA has been written and it will be moved.
	if(brazo != null) {
		//if DER is chosen, right arm is moved
		if (der != null) {
			Processor.println(1,"FSticky --> Brazo Derecho "+res1_float+"º / "+res2_int+" secs.");
			gui.StickMotion.scene.flexRArm(res1_float, res2_int);
		}
		else {
			Processor.println(1,"FSticky --> Brazo Izquierdo "+res1_float+"rad / "+res2_int+" secs.");
			gui.StickMotion.scene.flexLArm(res1_float, res2_int);
		}
	}
	else {
		//if DER is chosen, right leg is moved
		if (der != null) {
			Processor.println(1,"FSticky --> Pierna Derecha "+res1_float+"º / "+res2_int+" secs.");
			gui.StickMotion.scene.flexRLeg(res1_float, res2_int);
		}
		else { 
			Processor.println(1,"FSticky --> Pierna Izquierda "+res1_float+"º / "+res2_int+" secs.");
			gui.StickMotion.scene.flexLLeg(res1_float, res2_int);
		}	
	}
	}catch (NumberFormatException nfe) {
		 Processor.println(0, "En línea " + flex.getLine() + ": El parámetro debe ser un número." );
	} 
	catch (Exception e) {
		 Processor.println(0, "En línea " + flex.getLine() + ": " + e.toString() );
	}
};
	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
	}

f_girar {Object res1; Object res2; Object res3;}: 
		girar:GIRAR (stick:STICKMAN | cab:CABEZA | bra:BRAZO | PIERNA) (der:DER | IZQ)?
		PAR_IZQ res1=expr_aritmetica SEPARA res2=expr_aritmetica SEPARA	res3=expr_aritmetica PAR_DER
		
{
	try {
		// Convert to correct format in order to call the function
		String cadena1 = res1.toString(); 
		float res1_float = Float.parseFloat(cadena1);
		String cadena2 = res2.toString(); 
		float res2_float = Float.parseFloat(cadena2);
		String cadena3 = res3.toString(); 
		float res3_float = Float.parseFloat(cadena3);
		int res3_int = Math.round(res3_float);


	Processor.println(1,"Entrando girar sticky.");

	

	if(stick != null) {
		Processor.println(1,"Gira Stickman ("+res1_float+","+res2_float+")rad / "+res3_int+" secs.");
		gui.StickMotion.scene.rotateStickman(res1_float, res2_float, res3_int);
	} else if(cab != null) {
		Processor.println(1,"Gira Cabeza ("+res1_float+","+res2_float+")rad / "+res3_int+" secs.");
		gui.StickMotion.scene.rotateHead(res1_float, res2_float, res3_int);
	} else if(bra != null)
		if( der != null ) {
			Processor.println(1,"Gira Brazo Derecho ("+res1_float+","+res2_float+")rad / "+res3_int+" secs.");
			gui.StickMotion.scene.rotateRArm(res1_float, res2_float, res3_int);
		} else {
			Processor.println(1,"Gira Brazo Izquierdo ("+res1_float+","+res2_float+")rad / "+res3_int+" secs.");
			gui.StickMotion.scene.rotateLArm(res1_float, res2_float, res3_int);
		} else 
			if( der != null ) {
				Processor.println(1,"Gira Pierna Derecha ("+res1_float+","+res2_float+")rad / "+res3_int+" secs.");
				gui.StickMotion.scene.rotateRLeg(res1_float, res2_float, res3_int);
			} else {
				Processor.println(1,"Gira Pierna Izquierda ("+res1_float+","+res2_float+")rad / "+res3_int+" secs.");
				gui.StickMotion.scene.rotateLLeg(res1_float, res2_float, res3_int);
			}
	}catch (NumberFormatException nfe) {
		 Processor.println(0, "En línea " + girar.getLine() + ": El parámetro debe ser un número." );
	} 
	catch (Exception e) {
		 Processor.println(0, "En línea " + girar.getLine() + ": " + e.toString() );
	}
};
	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
	}

fin_interprete:
	fin: FIN_INTERPRETE
	{
		consumeUntil(Token.EOF_TYPE);
		consume();
		Processor.println(-1,"...FINALIZANDO STICKY...");
	};
	exception[fin]
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
		 }


imprimir returns [String respuesta=null]
{ String expr1; String expr2;}:
	impr:IMPRIMIR PAR_IZQ
	expr1 = impr_base { respuesta = expr1;}
	(OP_SUM expr2 = impr_base
	{
		respuesta = new String(expr1+expr2);
		expr1 = respuesta;
	
	})* PAR_DER
;// ANTLR Exception.
	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
	}


impr_base returns [String respuesta=null]
{Object e;}:
	(n1:CADENA {
		//Delete quotes
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
			//Check that variable is declared
			if(tablaSimbolos.existeSimbolo(id.getText()))
			{
				//Variable content
				String contenido = tablaSimbolos.getContenidoSimbolo(id.getText());
				
				if(contenido.compareTo("") == 0)
				{
					Processor.println(0,"Linea "+id.getLine()+": La variable "+id.getText()+" no tiene asignado ningun valor.");	
				}
				else
				{
					//Delete quotes
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
			else // Is not declared
				Processor.println(0,"Linea "+id.getLine()+": la variable no ha sido declarada "+id.getText());
	} )
	;	// ANTLR Exception.
	exception
 		catch [RecognitionException re] {
 			mostrarExcepcion(re);
 		}