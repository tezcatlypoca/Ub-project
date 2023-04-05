class Calcul {
static int npile= 0;
static int  pile[]= new int[100];
public static void main (String[] args)
{
        int n= args.length - 1 ;
	npile=0;;
	eval( args, pile);
}
public static void  eval( String[] args, int[] pile)
{
	int n= args.length ;
	npile= 0;
	for( int i=0; i<n; i++)
	{
		System.out.print( "i=" + i + " commandes= "); 
		for( int k=i; k<n; k++) System.out.print( args[k] + " "); System.out.println();
		if (args[i].equals( "+"))
		 	if( npile > 1) { int a= pile[npile-1]; int b= pile[npile-2]; 
					 pile[npile-2]= a+b; npile= npile-1; }
			else { System.out.println( "erreur, pile insuffisante\n"); return; }
		else if (args[i].equals( "*"))
		       if( npile > 1) { int a= pile[npile-1]; int b=  pile[npile-2]; 
				        pile[npile-2]= a*b; npile= npile-1; }
                        else { System.out.println( "erreur, pile insuffisante\n"); return; }
		else if (args[i].equals( "-"))
                       if( npile > 0)  { int a= pile[npile-1]; pile[npile-1]= 0 - a; }
			else { System.out.println( "erreur, pile insuffisante\n"); return; }
		else { int entier = Integer.parseInt( args[i]);
		       	pile[npile]= entier; npile++ ; 	
		     }
		System.out.print( "PILE= ");
		for( int k=npile-1; k>=0 ; k--) System.out.print( pile[k] + " " ); System.out.println();
	}
}
}

