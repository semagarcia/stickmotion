header {
	package sticky;
	import java.util.HashMap;
	import tablasimbolos.TablaSimbolos;
	import java.util.ArrayList;
	import error.Error;
}

class Anasint extends Parser;
	options {
		k=1; }
	{
		TablaSimbolos tablaSimbolos= new TablaSimbolos();
	}

	instrucciones 
	{
		Object x=null;
		System.out.println("...INICIANDO STICKY...");		
	} 
	:(declaracion)* (asignacion)* fin_interprete;
	 

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
				System.out.println("entra2");	
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
	
	(IDENT OP_ASIG expr_aritmetica FIN_INSTRUCCION) => 
	i:IDENT OP_ASIG (respuesta = expr_aritmetica)//| respuesta = func_dev[ejecutar, nombreRegion])
	punto:FIN_INSTRUCCION
	{
			System.out.println("asignacion: "+respuesta);
			//if(ejecutar)
			tablaSimbolos.set(i,respuesta);	

	}

	;
//////////////////////////////////////////////////////////////////////////////////


//////////////////////////// EXPRESIONES BOOLEANAS /////////////////////////////////////////

//Evaluamos la expresion logica correspondiente empezando por la mas baja en la jerarquia: OR AND NOT.
//Pasos:
//1. Evaluar las expresiones anteriores a la OR, que tienen mayor prioridad.
//2. Evaluar las expresiones posteriores a la OR que tienen tambien mayor prioridad.
//3. Comprobar que esas dos expresiones anteriores tienen fundamento, es decir, son apropiadas para una funcion OR de este tipo.

expr_booleana returns [Object respuesta=null] {Object exp; Object exp2;}:
	exp =  expresionOR {respuesta = exp;}		//1
	(linea: OP_O exp2 = expresionOR 			//2
		{
			if(exp2 != null && respuesta!=null) //3
			{
				if(exp2 instanceof Boolean && respuesta instanceof Boolean)
				{
					boolean var1 = new Boolean(respuesta.toString()).booleanValue();
					boolean var2 = new Boolean(exp2.toString()).booleanValue();
					var1 = var1 || var2;
					respuesta = new Boolean(var1);	
				}
				else
				{
						Error.errorExpresion(1,linea.getLine());
				}	
			}
		}
		)*;
		
//Evaluamos la expresion logica siguiente de menor prioridad AND.
//Pasos:
//1. Evaluar las expresiones anteriores a la AND, que tienen mayor prioridad.
//2. Evaluar las expresiones posteriores a la AND que tienen tambisn mayor prioridad.
//3. Comprobar que esas dos expresiones anteriores tienen fundamento, es decir, son apropiadas para una funcisn AND de este tipo.

expresionOR returns [Object respuesta=null] {Object exp; Object exp2;}:
	exp =  expresionAND {respuesta = exp;}	//1
	(linea: OP_O exp2 = expresionAND //2
		{
			if(exp2 != null && respuesta!=null) //3
			{
				if(exp2 instanceof Boolean && respuesta instanceof Boolean)
				{
					boolean var1 = new Boolean(respuesta.toString()).booleanValue();
					boolean var2 = new Boolean(exp2.toString()).booleanValue();
					var1 = var1 && var2;
					respuesta = new Boolean(var1);	
				}
				else
				{
						Error.errorExpresion(1,linea.getLine());
				}	
			}
		}
		)*;

//Evaluar la expresion lsgica siguiente de menor prioridad: NOT, y como ya se han evaluado todas las expresiones lsgicas. La siguiente expresisn 
//de mayor prioridad sersa la expresisn relacional. La evaluamos (debe ir despuss de NOT.
//Pasos:
//1. Evaluar las expresiones posteriores a la NOT que tienen tambisn mayor prioridad 
//       (expresiones relacionales)
//2. Comprobar que esa expresisn tiene fundamento, es decir, es 
//      apropiada para una funcisn NOT de este tipo.

expresionAND returns [Object respuesta=null] {Object exp; Object exp2;}:
	linea: OP_NO exp2= expr_relacional 		// 1)
	{
			if(exp2 != null) 				// 2)
			{
				if(exp2 instanceof Boolean)	
				{
					boolean var1 = new Boolean(exp2.toString()).booleanValue();
					respuesta = new Boolean(!var1);	
				}
				else
				{
						Error.errorExpresion(1,linea.getLine());
				}	
			}
		}
		|
		exp = expr_relacional {respuesta=exp;};
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////// EXPRESIONES RELACIONALES /////////////////////////////////

expr_relacional returns [Object respuesta = null]
{Object e1; Object e2;}:
	e1 = expr_aritmetica (linea:OP_MAYOR e2 = expr_aritmetica 
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
	);
	
/////////////////// EXPRESIONES ARITMETICAS //////////////////////////////////////////////
expr_aritmetica returns [Object resultado = null]
{Object e1; Object e2;}: 
	e1=expr_mod
	{
		resultado = e1;	
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

	e1=expr_base {resultado = e1;}
	
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
		n1:NUMERO {resultado = new Integer(n1.getText());}
		|n2:REAL {resultado = new Double(n2.getText());}
		|n3:VERDADERO {resultado = new Boolean(true);}
		|n4:FALSO {resultado = new Boolean(false);}/*
		|resultado = func_ent[true, nombreRegion]
		|resultado = func_flot[true, nombreRegion]
		|resultado = func_str[true, nombreRegion]
		//|resultado = func_bool[true, nombreRegion]
		*/
		|id:IDENT 
		{
			
			if(tablaSimbolos.existeSimbolo(id.getText()))
			{
				String contenido = tablaSimbolos.getContenidoSimbolo(id.getText());
				
				if(contenido==null)
				{
					Error.visualizarError(1,id.getLine(),"ERROR: La variable no tiene asignado ningun valor "+id.getText());	
				}/*
				else
				{
						
						if(tipoDato.compareTo("real")==0)
						{
							resultado = new Double(contenido.toString()).doubleValue();
						}
						else if(tipoDato.compareTo("int")==0)
						{
							resultado = new Integer(contenido);
						}
						else // expresiones booleanas
							Error.visualizarError(1,id.getLine(),"ERROR: No se pueden realizar operaciones aritmeticas con expresiones booleanas");
				}*/
				
				if(contenido.matches("[0-9~.]"))
	  				{
	  				System.out.println("Entero");
	  				resultado = new Integer(contenido.toString()).intValue();
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
		| PAR_IZQ resultado = expr_aritmetica PAR_DER
		;
		
	
/*
	//Eliminacion de variables
	eliminacion [HashMap vars]: SUP i:IDENT
		{
		vars.remove(i.getText());
		};
		
	// Javi: Definición de estructuras condicionales.
	/*estrCondicional [HashMap vars]: sentenciaIf[vars] /*| sentenciaSwitch instrucciones[vars];*/
	/*
	sentenciaIf [HashMap vars]: IF PAR_IZQ PAR_DER LLAVE_IZQ instrucciones[vars] LLAVE_DER
		{
			System.out.println("Reconocido. IF ");
		};

	/*
	condicional [HashMap vars]: cond:IF
		{
			vars.put(cond.getText(),vars.get(cond.getText()));
			System.out.println("Reconocido. IF ");
		};
	*/
	
	//Definicion de funciones
/*	funcion {HashMap vars=new HashMap();}: DEF IDENT PAR_IZQ (declaracion[vars] (SEPARA declaracion[vars])*)? PAR_DER LLAVE_IZQ instrucciones[vars] LLAVE_DER;
*/

fin_interprete:
	{
		System.out.println("...FINALIZANDO STICKY...");	
	}
	FIN_INTERPRETE
	{
		consumeUntil(Token.EOF_TYPE);
		consume();
	};
	
	