header {
	package stickmotion.antlr;
}

class SMParser extends Parser;
	{
		Token e1= new Token();
	}
	
	sentences :
	   (expression ";")* 
	   {
	   		System.out.println("expresiones reconocidas");
	   };
   
	expression: exp_mult (("+"|"-") exp_mult)* ;
	
	exp_mult : exp_base (("*"|"/") exp_base)* ;
	
	exp_base : e1:REAL {System.out.println(e1);}
          | "(" expression ")"
          ;	
          
