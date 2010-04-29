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
	sentencia[HashMap vars]: /*((*/(declaracion[vars]|eliminacion[vars]/*|funcion*/) FIN_INSTRUCCION/*)|estructura_condicional)*/ (sentencia[vars]| /* nothing */);
	
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
	
	
	