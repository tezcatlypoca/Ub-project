import java.util.*;
class tp0 {
static int[] calcul( int E[])
{
	System.out.println("calcul commence");
	int n=E.length;
	if( n < 100)
	{
		System.out.print("E ="); for( int i=0; i<n; i++) System.out.print( E[i] + "\t");
		System.out.println();
		System.out.print("LT="); for( int i=0; i<n; i++) System.out.print(  LT[i] + "\t");
		System.out.println();
	}
// A COMPLETER : CALCULER LT et indice imaxi du dernier elt de la PLSC
	affiche_DM( E, LT, LT[imaxi]);
	return LT;
}

// rend la longueur X de la plus longue sequence dont le dernier elt V[X] est <= valeur 
// rend 0 en cas d'absence
static int dicho( int valeur, int V[], int g, int d)
{ // A COMPLETER
}

static int[] quick( int E[])
{
	// A COMPLETER 
	return LT;
}

// affiche la sequence de longueur l, qui se termine a l'indice le plus grand possible
static void affiche_DM( int[] E, int[] LT, int l)
{
        int i;
        System.out.println("Affichage PLSC:");
        for (i= E.length-1; i>=0 && LT[i]!=l ; i--);
        if(i<0) return;
        System.out.print( "\tE[" + i + "]=" + E[i] + "; ");
        for (l=l-1; l >= 1; l--)
        {
		int ii=i;
                for( ; i>=0 && (LT[i]!=l || E[i]>E[ii]) ; i--) ;
                if(i<0) return;
                System.out.print( "\tE[" + i + "]=" + E[i] + "; ");
        }
        System.out.println("\n");
}

static void  exemple( int n)
{
	Random generator = new Random( n );
	int[] tab=new int[n];
	for (int i=0; i< n; i++) tab[i]= ((int) (100000. * generator.nextDouble())) % (4*n) ;
	int[] lt1=calcul( tab );
	int[] lt2=quick( tab);
	for( int i=0; i<n; i++) assert( lt1[i]==lt2[i]);
	System.out.println("oui, les 2 methodes rendent le meme resultat avec n="+ n);
	int maxi=0;
        int imaxi=0;
	for (int i=0; i< n; i++) if (lt2[i]>maxi) { maxi=lt2[i]; imaxi=i; }
	System.out.println("La SCLPL est de longueur " + maxi + " et se termine en indice " + imaxi);
	affiche_DM( tab, lt2, maxi);
	System.out.println("***************************************************************************************************");
}

public static void main (String[] args)
{
        exemple(10);
        exemple(20);
        exemple(20);
        exemple(20);
        exemple(20);
	exemple( 100);
	exemple( 10000);
//	exemple( 100000);
}

}

