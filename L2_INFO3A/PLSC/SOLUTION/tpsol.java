import java.util.*;
class tpsol 
{	// calcul calcule en O(n^2) LT[i], la longueur de la PLSC qui se termine en E[i]
	static int[] calcul( int E[])
	{ 	System.out.println("calcul commence");
		int n=E.length;
		int LT[] = new int[n];
		LT[0]=1; 
		for( int i=1; i<n; i++)
		{ 	LT[i]=1; 
			for( int k=0; k<= i-1; k++) if (E[k] <= E[i] && LT[i] <= LT[k]+1)  { LT[i]= LT[k]+1; }
		}
		if( n < 100)
		{ 	System.out.print("E ="); for( int i=0; i<n; i++) System.out.print( E[i] + "\t");
			System.out.println();
			System.out.print("LT="); for( int i=0; i<n; i++) System.out.print(  LT[i] + "\t");
			System.out.println();
		}
		int imaxi=LT[0];
		for (int i=0; i<n; i++) if (LT[i]>=LT[imaxi]) imaxi = i;
		affiche( E, LT, LT[imaxi]);
		System.out.println("calcul a termine");
		return LT;
	}
	// dicho() rend la longueur X de la plus longue sequence dont le dernier elt: V[X] est <= valeur 
	static int dicho( int valeur, int V[], int g, int d)
	{ 	if( d<g) return 0;
		if( V[d]<= valeur) return d;
		if (g==d) return 0;
		int m= (g+d-1)/2;
		if (V[m] > valeur) return dicho( valeur, V, g, m-1);
		return dicho( valeur, V, m, d-1);
	}
	// calcule LT[ i ] rapidement. Doit donner les memes resultats que calcul
	static int[] quick( int E[])
	{ 	System.out.println("quick commence");
		int n=E.length; int LT[] = new int[n]; 
		int V[] = new int[n+1]; for( int i=0; i<n; i++) V[i]= -999;
		V[1]=E[0]; int L=1; LT[0]=1;
		for( int i=1; i<n; i++)
		{ 	LT[i]=1 + dicho( E[i], V, 1, L);
			if (LT[i] > L) L=LT[i];
			V[ LT[i] ] = E[i];
		}
		if( n < 100)
		{ 	System.out.print("E ="); for( int i=0; i<n; i++) System.out.print( E[i] + "\t");
			System.out.println();
		  	System.out.print("LT="); for( int i=0; i<n; i++) System.out.print(  LT[i] + "\t");
			System.out.println();
		  	System.out.print("V ="); for( int i=0; i<n; i++) System.out.print(  V[i] + "\t");
			System.out.println();
		}
		int imaxi=LT[0];
		for (int i=0; i<n; i++) if (LT[i]>=LT[imaxi]) imaxi = i;
		affiche( E, LT, LT[imaxi]);
		System.out.println("quick a termine");
		return LT;
	}
	static void affiche( int[] E, int[] LT, int l)
	{ 	System.out.println("n=" + E.length + "; plus longue sequence de longueur " + l + " :");
		int i; for (i= E.length-1; i>=0 && LT[i]!=l ; i--); // chercher i maxi tq LT[i]=l
		if(i<0) return;
		System.out.print( "\tE[" + i + "]=" + E[i] + "; ");
		for (l=l-1; l >= 1; l--)
		{ 	int ii=i;
			for( ; i>=0 && (LT[i]!=l || E[i]>E[ii]) ; i--) ;
			if(i<0) return;
			System.out.print( "\tE[" + i + "]=" + E[i] + "; ");
		}
		System.out.println("");
	}
	static void  exemple( int n)
	{ 	Random generator = new Random( n );
		int[] tab=new int[n];
		for (int i=0; i< n; i++) tab[i]= ((int) (123. + 103417. * generator.nextDouble())) % (7*n) ;
		int[] lt1=calcul( tab ); int[] lt2=quick( tab);
		for( int i=0; i<n; i++) assert( lt1[i]==lt2[i]);
		System.out.println("oui, les 2 methodes rendent le meme resultat avec n="+ n);
		System.out.println("****************************************************************************************************");
	}
	public static void main (String[] args)
	{ 	exemple( 8);
		exemple(10);
		exemple(20);
		exemple(30);
		exemple(40);
		exemple( 100);
		exemple( 10000);
		exemple( 100000);
	}
	static void exemple_10() 
	{ 
	  int[]  E= { 61, 44, 15, 28, 31, 20, 57, 4, 10, 28};
	  int[] LT= {  1,  1,  1,  2,  3,  2,  4, 1,  2,  3};
	}
}

