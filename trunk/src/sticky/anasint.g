header {
	package sticky;
	import java.util.HashMap;
	import sticky.tablasimbolos.TablaSimbolos;
	import java.util.ArrayList;
	import sticky.error.Error;
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
		System.out.println("...INICIANDO STICKY...");		
	} 
	
	: (sentencia)* fin_interprete;
	
	sentencia: stickcommand | declaracion | asignacion | eliminar_var | sentenciaIF;



stickcommand {Object a,i,d;}:
	(
		(GIRAR STICKMAN (a = expr_aritmetica) (i = expr_aritmetica) (d = expr_aritmetica) FIN_INSTRUCCION)
		{
			System.out.println("a:"+a);
		}
	);


	//Para declarar variables hay diferentes alternativas:
	//1. Se declara una variable sin inicializarse.
	//2. Se declara una variable y si inicializa.
	//3. Se declara más de una variable.
	// NOTA: NO se permite inicialización de variables mientras se declara más de una.

declaracion {String mensaje;Object x = null; ArrayList lista = new ArrayList();}:
	(	//Alternativa 1
		(VAR IDENT FIN_INSTRUCCION) => VAR i1:IDENT FIN_INSTRUCCION 
		{ 
		  boolean res=tablaSimbolos.put(i1);
		  if(res)
		  	System.out.println("Variable \""+i1.getText()+"\" ha sido declarada");
		  else
		  	System.out.println("Variable \""+i1.getText()+"\" no ha sido declarada");
		}
		
		//Alternativa 2
		
		|(VAR IDENT OP_ASIG) =>VAR i2:IDENT OP_ASIG (x=expr_aritmetica) punto2:FIN_INSTRUCCION
			{			
				boolean res = tablaSimbolos.put(i2,x);	// modifico el valor en la tabla de simbolos
				if(res)
		  			System.out.println("Variable \""+i2.getText()+"\" ha sido declarada con valor "+x);
		  		else 
		  			System.out.println("Variable \""+i2.getText()+"\" no ha sido declarada");
		
			}
		//Alternativa 3
		|(VAR IDENT (SEPARA IDENT)*) => VAR i3:IDENT (SEPARA i3_alt:IDENT {lista.add(i3_alt);} )* punto3:FIN_INSTRUCCION
					{
						// Tenemos que insertar cada identificador encontrado en la tabla de simbolos
						boolean res = tablaSimbolos.put(i3);
						if(res)
		  					System.out.println("Variable \""+i3.getText()+"\" ha sido declarada");
		  				else 
		  					System.out.println("Variable \""+i3.getText()+"\" no ha sido declarada");
						Token tok;
						for(int j=0; j < lista.size(); j++)
						{
								tok = (Token)lista.get(j); // obtengo un identificador de la lista
								res = tablaSimbolos.put(tok);
								if(res)
		  							System.out.println("Variable \""+tok.getText()+"\" ha sido declarada");
		  						else 
		  							System.out.println("Variable \""+tok.getText()+"\" no ha sido declarada");
						}
					}
					
	);


///////////////////// REGLAS PARA LAS ASIGNACIONES DE VARIABLES //////////////////

asignacion 
	{ String mensaje = new String(); Object respuesta; Object respuesta2;}:

	i:IDENT OP_ASIG (respuesta = expr_aritmetica) //| respuesta = func_dev[ejecutar, nombreRegion])
	punto:FIN_INSTRUCCION
	{		System.out.println("asignacion aritmetica");
			//if(ejecutar)
			if(tablaSimbolos.set(i,respuesta))	
				System.out.println("asignacion a la variable \""+i.getText()+"\": "+respuesta);
			else 
				System.out.println("asignacion no realizada, no existe la variable \""+i.getText()+"\"");
	} ;
//////////////////////////////////////////////////////////////////////////////////



/////////////////// EXPRESIONES ARITMETICAS //////////////////////////////////////////////
expr_aritmetica returns [Object resultado = null]
{Object e1; Object e2;}: 
	e1=expr_mod
	{
		resultado = e1;	
		System.out.println("exp_aritmetica " + resultado);
	}
	(linea1:OP_POT e2 = expr_mod
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
				Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
			}
			else
			{
			
				if(e1 instanceof String || e2 instanceof String)
					Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
				else
				{
					double valor1 = new Double(e1.toString()).doubleValue();
					int valor2 = new Integer(e2.toString()).intValue();
					resultado = new Double(Math.pow(valor1, valor2));
				}
			}
		}
	})*;
	
expr_mod returns[Object resultado = null]
{Object e1; Object e2;}:
	e1=expr
	{
		System.out.println("expr_mod "+e1);
		resultado = e1;
	}
	(linea1:OP_MOD e2 = expr
	{
		if(e1 instanceof Integer && e2 instanceof Integer)
		{
			int valor1 = new Integer(e1.toString()).intValue();
			int valor2 = new Integer(e2.toString()).intValue();
			
			resultado = new Integer(valor1%valor2);
		}
		
		if(e1 instanceof Integer && e2 instanceof Double)
		{
			int valor1 = new Integer(e1.toString()).intValue();
			double valor2 = new Double(e2.toString()).doubleValue();
			
			resultado = new Double(valor1%valor2);
		}
		
		if(e1 instanceof Double && e2 instanceof Integer)
		{
			double valor1 = new Double(e1.toString()).doubleValue();
			int valor2 = new Integer(e2.toString()).intValue();
			
			resultado = new Double(valor1%valor2);
		}
		
		if(e1 instanceof Double && e2 instanceof Double)
		{
			double valor1 = new Double(e1.toString()).doubleValue();
			double valor2 = new Double(e2.toString()).doubleValue();
			
			resultado = new Double(valor1%valor2);
		}
		
		if(e1 instanceof String || e2 instanceof String)
		{
			Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
		}
		
		if(e1 instanceof Boolean || e2 instanceof Boolean)
		{
			Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
		}
		
	})*
	;
	

expr returns [Object resultado = null]
{Object e1; Object e2; Object e3; Object e4;}: 
	e1= expr_mult
	{
		System.out.println("expr "+e1);
		resultado = e1;
	}
	(linea1:OP_SUM e2=expr_mult
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
	  				Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  			}
	  		}
	  		else
	  		{
	  			if(e1 instanceof Boolean || e2 instanceof Boolean)
	  				Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
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
	| linea2:OP_RES e3 = expr_mult
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
	  			Error.visualizarError(1,linea2.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  		}
	  		else
	  		{
	  			if(e1 instanceof Boolean || e3 instanceof Boolean)
	  				Error.visualizarError(1,linea2.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
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

expr_mult returns [Object resultado = null]
{Object e1; Object e2; Object e3;}: 

	e1=expr_base {resultado = e1; System.out.println("expr_mult "+resultado);}
	
	( linea1:OP_MUL e2=expr_base
	  {
	  	if(e1 instanceof Integer && e2 instanceof Integer)
	  	{
	  		int valor1 = new Integer(e1.toString()).intValue();
	  		int valor2 = new Integer(e2.toString()).intValue();
	  		
	  		resultado = new Integer(valor1*valor2);
	  		
	  		e1=resultado;
	  	}
	  	
	  	if(e1 instanceof Integer && e2 instanceof Double)
	  	{
	  		int valor1 = new Integer(e1.toString()).intValue();
	  		double valor2 = new Double(e2.toString()).doubleValue();
	  		
	  		resultado = new Double(valor1*valor2);
	  		
	  		e1=resultado;
	  	}
	  	
	  	if(e1 instanceof Double && e2 instanceof Integer)
	  	{
	  		double valor1 = new Double(e1.toString()).doubleValue();
	  		int valor2 = new Integer(e2.toString()).intValue();
	  		
	  		resultado = new Double(valor1*valor2);
	  		
	  		e1=resultado;
	  	}
	  	
	  	if(e1 instanceof Double && e2 instanceof Double)
	  	{
	  		double valor1 = new Double(e1.toString()).doubleValue();
	  		double valor2 = new Double(e2.toString()).doubleValue();
	  		
	  		resultado = new Double(valor1*valor2);
	  		
	  		e1=resultado;
	  	}
	  	
	  	if(e1 instanceof String || e2 instanceof String)
	  	{
	  			Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  	}
	  	
	  	if(e1 instanceof Boolean || e2 instanceof Boolean)
	  	{
	  			Error.visualizarError(1,linea1.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
	  	}
	  }
	  | linea2:OP_DIV e3 = expr_base
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
	  			Error.visualizarError(1,linea2.getLine(),"ERROR: por 0");
	  	}
	  	else
	  	{
	  		if(e1 instanceof String || e3 instanceof String)
	  		{
	  			Error.visualizarError(1,linea2.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con cadenas de caracteres");
	  		}
	  		else
	  		{
	  			if(e1 instanceof Boolean || e3 instanceof Boolean)
	  				Error.visualizarError(1,linea2.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con valores booleanos");
	  			else
	  			{	
	  				double valor1 = new Double(e1.toString()).doubleValue();
	  				double valor2 = new Double(e3.toString()).doubleValue();
	  			
	  				if((valor2 !=0)&&(valor1 != 0))
	  				{	
	  					resultado = new Double(valor1/valor2);
	  					e1=resultado;
	  				}
	  				else
	  					Error.visualizarError(1,linea2.getLine(),"ERROR: por 0");
	  			}
	  		}
	  			
	  	}	
	  })*;

expr_base returns [Object resultado = null]: 
		n1:ENTERO {resultado = new Integer(n1.getText());}
		|n2:REAL {resultado = new Double(n2.getText());}
		|n3:VERDADERO {System.out.println("encontrado verdad"); resultado = new Boolean(true);}
		|n4:FALSO {System.out.println("encontrado falso"); resultado = new Boolean(false);}
		|id:IDENT
		{
			
			if(tablaSimbolos.existeSimbolo(id.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id.getText());
				
				if(contenido==null)
				{
					Error.visualizarError(1,id.getLine(),"ERROR: La variable no tiene asignado ningun valor "+id.getText());	
				}
				
				if(contenido.matches("[0-9~.]"))
	  				{
	  				System.out.println("Entero");
	  				resultado = new Integer(contenido.toString()).intValue();
	  				}
	  			else if(contenido.matches("true") || contenido.matches("false"))
	  				{
	  				System.out.println("boolean");
	  				resultado = new Boolean(contenido.toString()).booleanValue();
	  				}
	  			else if((Float)Float.parseFloat(contenido) instanceof Float)
	  				{
	  				System.out.println("flotante");
	  				resultado = new Float(contenido.toString()).floatValue();
	  				}
			
			}
			else 
				Error.visualizarError(1,id.getLine(),"ERROR: la variable no ha sido declarada "+id.getText());
			
				
		}
		| (PAR_IZQ expr_aritmetica PAR_DER) => PAR_IZQ (resultado = expr_aritmetica) PAR_DER
		| (PAR_IZQ expresionOR PAR_DER) => PAR_IZQ (resultado = expresionOR) PAR_DER
		;
		

expresionOR returns [Object resultado = null]
{Object exp1 = null; Object exp2=null;}: 
	exp1= expresionAND
	{
		System.out.println("expresionOR "+exp1);
		resultado = exp1;
		
	}
	(linea:OP_O exp2=expresionAND
	{
	  System.out.println("entra en or con OP_O");
	  if(exp2 != null) 				// 2
			{
				if(exp2 instanceof Boolean)	
				{
					boolean var1 = new Boolean(exp1.toString()).booleanValue();
					boolean var2 = new Boolean(exp2.toString()).booleanValue();
					var1 = var1 || var2;
					resultado = new Boolean(var1);	
					
				}
				else
				{
						Error.errorExpresion(1,linea.getLine());
				}
			
			}
	  }
	)*
	;
	


		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

expresionAND returns [Object resultado = null]
{Object exp1 = null ; Object exp2 = null;}: 

	exp1=expr_relacional { System.out.println("expresionAND " + exp1); resultado = exp1;}
	( linea:OP_Y exp2=expr_relacional
	  {
	  if(exp2 != null) 				// 2
			{
				if(exp2 instanceof Boolean)	
				{
					boolean var1 = new Boolean(exp1.toString()).booleanValue();
					boolean var2 = new Boolean(exp2.toString()).booleanValue();
					var1 = var1 && var2;
					resultado = new Boolean(var1);	
					
				}
				else
				{
						Error.errorExpresion(1,linea.getLine());
				}
			
			}
	  }
	  )*;
	  

////////////////// EXPRESIONES RELACIONALES /////////////////////////////////

expr_relacional returns [Object respuesta = null]
{Object e1; Object e2;}:
	e1 = expr_aritmetica {
		respuesta = e1;
		System.out.println("exp_relacional " + respuesta);
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
			Error.visualizarError(1,linea.getLine(), "No se puede realizar expresiones relacionales con datos de distinto tipo");
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
			Error.visualizarError(1,linea2.getLine(), "No se puede realizar expresiones relacionales con datos de distinto tipo");
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
			Error.visualizarError(1,linea3.getLine(), "No se puede realizar expresiones relacionales con datos de distinto tipo");
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
			Error.visualizarError(1,linea4.getLine(), "No se puede realizar expresiones relacionales con datos de distinto tipo");
		}
	}
	)*;

////////////////////// REGLAS PARA LA EVALUACION DE EXPRESIONES ///////////////////

evaluarExpresion returns [Object respuesta = null]: 
	respuesta = expresionOR {System.out.println("Evaluar expresion: "+ respuesta);};

	// Javi: definición de sentencia IF
	// David: Está bien la sentencia IF, pero creo que si la "expr_booleana"
	// (que devuelve un valor true o false) no se cumple, no debería ejecutarse 
	// la regla "sentencias". Tal como está se evalua aunque la expresión booleana no
	// se cumpla. Quizá haya que mandarle un valor flag a la regla "sentencias"
	// para que se ejecute si flag=true y no se ejecute si flag=false.
	sentenciaIF {Object o; boolean b=false;} : 
		IF PAR_IZQ (o=evaluarExpresion) PAR_DER LLAVE_IZQ
        {
                if (o.getClass() == Boolean.class)
                           b = ((Boolean)o).booleanValue();
                else System.out.println("Error IF");
                
                System.out.println("Entra en IF. Evaluación: "+o);
        }
        ({b==true}? (sentencia)* LLAVE_DER
        | {b==false}? (options{greedy=false;}:.)+ LLAVE_DER)
        
        (ELSE LLAVE_IZQ 
        ({b==false}? (sentencia)* LLAVE_DER
        | {b==true}? (options{greedy=false;}:.)+ LLAVE_DER
        ))?
        ;
    
    
	
	//Javi: Condiciones usadas en estructuras condicionales o bucles.
	
	
/*
	// Javi: Definición de sentencia SWITCH-CASE
	sentenciaSwitch [HashMap vars]: SWITCH PAR_IZQ variable PAR_DER LLAVE_IZQ (casosSwitch[vars])* LLAVE_DER
	{
		System.out.println("Switch-Case");
	} ;
	
	casosSwitch [HashMap vars]: (CASE variable | DEFAULT ) DOBLE_PUNTO LLAVE_IZQ instrucciones[vars] LLAVE_DER END_CASE FIN_INSTRUCCION;
	
	//Definicion de funciones
/*	funcion {HashMap vars=new HashMap();}: DEF IDENT PAR_IZQ (declaracion[vars] (SEPARA declaracion[vars])*)? PAR_DER LLAVE_IZQ instrucciones[vars] LLAVE_DER;
<<<<<<< .mine
*/

eliminar_var {String res;}: SUP id:IDENT FIN_INSTRUCCION 
	{
		  res=tablaSimbolos.delSimbolo(id);
		  if(res.compareTo(id.getText()) == 0)
		  	System.out.println("Variable \""+id.getText()+"\" ha sido eliminada");
		  else 
		  	System.out.println("Variable \""+id.getText()+"\" no ha sido eliminada");
	};

fin_interprete:
	{
		System.out.println("...FINALIZANDO STICKY...");	
	}
	FIN_INTERPRETE
	{
		consumeUntil(Token.EOF_TYPE);
		consume();
	};
	
sentenciaWHILE
	{boolean ejecutar = false; Object expresion = null;
	Object valor = null; int marca = 0;
	}:
	
	linea:B_WHILE PAR_IZQ expresion = evaluarExpresion PAR_DER
	{
		boolean exp = new Boolean(expresion.toString()).booleanValue();
		
		System.out.println("Resultado evaluacion while :"+exp);
				
	}
	LLAVE_IZQ 
		(sentencia)*
	LLAVE_DER
	;

	