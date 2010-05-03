header {
	package sticky;
	import java.util.HashMap;
}

class Anasint extends Parser;
	options {
		k=2;
	}

	
	instrucciones[HashMap vars]: (sentencia[vars])? {System.out.println("Reconocido. HashMap: "+vars);};
	
	//comentado para javi
	sentencia[HashMap vars]: (((declaracion[vars]|eliminacion[vars]/*|funcion*/) FIN_INSTRUCCION) | estructuraCondicional[vars]) (sentencia[vars])?;

	//Declaracion de variables
	declaracion [HashMap vars]: VAR i:IDENT (OP_ASIG (c:CADENA|e:ENTERO|r:REAL|v:VERDADERO|f:FALSO|ii:IDENT))?
		{
			if(c!=null)
					vars.put(i.getText(),c.getText());  //se asigna una cadena
			else
				if(e!=null)
					vars.put(i.getText(),new Integer(e.getText()));  //se asigna un entero
				else
					if(r!=null)
						vars.put(i.getText(),new Float(r.getText()));  //se asigna un real
					else
						if(v!=null)
							vars.put(i.getText(),true);  //se asigna V
						else
							if(f!=null)
								vars.put(i.getText(),false);  //se asigna F
							else
								if(ii!=null)
									vars.put(i.getText(),vars.get(ii.getText()));  // asigna el valor de otra variable
								else
									vars.put(i.getText(),null);  // asigna el valor de otra variable
		};

	//Eliminacion de variables
 	eliminacion [HashMap vars]: SUP i:IDENT
		{
		vars.remove(i.getText());
		};
		
	// Javi: Definición de estructuras condicionales.
	estructuraCondicional [HashMap vars]: sentenciaIf[vars] | sentenciaSwitch[vars] /*instrucciones[vars]*/;
	
	// Javi: definición de sentencia IF
	sentenciaIf [HashMap vars]: IF PAR_IZQ condicion PAR_DER LLAVE_IZQ instrucciones[vars] LLAVE_DER
								(ELSE PAR_IZQ condicion PAR_DER LLAVE_IZQ instrucciones[vars] LLAVE_DER)?
		{
			System.out.println("Reconocido. IF ");
		};
	
	//Javi: Condiciones usadas en estructuras condicionales o bucles.
	
	condicion: expOLogico;

	expOLogico : expYLogico (OP_O expYLogico)*;

	expYLogico : expOXLogico (OP_Y expOXLogico)*;
	
	expOXLogico : expComparacion (OP_OX expComparacion)*;
	
	expComparacion : expBaseCond ( ( operaAritmeticos ) expBaseCond )*
	{
		System.out.println("Condicion IF");
	} ;

	operaAritmeticos : OP_IG | OP_DIST | OP_MAYOR | OP_MENOR | OP_MAYOR_IG | OP_MENOR_IG;
	
	expBaseCond: (OP_NO)? (variable | PAR_IZQ condicion PAR_DER);
	
	variable: IDENT | CADENA | ENTERO | REAL | VERDADERO | FALSO;
	
	// Javi: Definición de sentencia SWITCH-CASE
	sentenciaSwitch [HashMap vars]: SWITCH PAR_IZQ variable PAR_DER LLAVE_IZQ (casosSwitch[vars])* LLAVE_DER
	{
		System.out.println("Switch-Case");
	} ;
	
	casosSwitch [HashMap vars]: (CASE variable | DEFAULT ) DOBLE_PUNTO LLAVE_IZQ instrucciones[vars] LLAVE_DER END_CASE FIN_INSTRUCCION;
	
	//Definicion de funciones
/*	funcion {HashMap vars=new HashMap();}: DEF IDENT PAR_IZQ (declaracion[vars] (SEPARA declaracion[vars])*)? PAR_DER LLAVE_IZQ instrucciones[vars] LLAVE_DER;
*/