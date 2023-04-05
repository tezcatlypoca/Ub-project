import java.util.*;
class tp0 {


static int[] calcul( int Elts[], int Prec[])
{
	System.out.println("calcul commence");
	int n=Elts.length;
	int LT[] = new int[n];
	LT[0]=1; Prec[0] = -1;
	for( int i=1; i<n; i++)
	{
		LT[i]=1; Prec[i]= -1;
		for( int k=0; k<= i-1; k++) 
			if (Elts[k] <= Elts[i] && LT[i] <= LT[k]+1)  
				{ LT[i]= LT[k]+1; Prec[i]=k; }
	}
	if( n < 100)
	{
	System.out.print("E ="); for( int i=0; i<n; i++) System.out.print( Elts[i] + "\t");
	System.out.println();
	System.out.print("LT="); for( int i=0; i<n; i++) System.out.print(  LT[i] + "\t");
	System.out.println();
	}
	int imaxi=LT[0];
	for (int i=0; i<n; i++) if (LT[i]>=LT[imaxi]) imaxi = i;
	System.out.println("PLSC: "); 
	for( int i=imaxi; i >= 0; i=Prec[i]) System.out.print( "\tE[" + i + "]=" + Elts[i] + " ");
	System.out.println("\ncalcul a fini\n");
	affiche_DM( Elts, LT, LT[imaxi]);
	return LT;
}

// rend la longueur X de la plus longue sequence dont le dernier elt V[X] est <= valeur 
static int dicho( int valeur, int V[], int g, int d)
{
	if( d<g) return 0;
	if( V[d]<= valeur) return d;
        if (g==d) return 0;
	int m= (g+d-1)/2;
	if (V[m] > valeur) return dicho( valeur, V, g, m-1);
	return dicho( valeur, V, m, d-1);
}
static int[] quick( int Elts[])
{
	System.out.println("quick commence");
	int n=Elts.length;
        int Prec[] = new int[n];
        int LT[] = new int[n];
	int V[] = new int[n+1];
	int IV[] = new int[n+1]; // idem V, mais l'indice dans E
	V[1]=Elts[0]; IV[1]=0; 
	int L=1;
	LT[0]=1;
	Prec[0]= -1;
	for( int i=1; i<n; i++)
        {
		Prec[i] = -1;
		int k=dicho( Elts[i], V, 1, L);
                LT[i]=1 + k;
		if (k>0) Prec[i] = IV[ k];
		if (LT[i] > L) L=LT[i];
		V[ LT[i] ] = Elts[i];
		IV[ LT[i] ] = i;
        }
	if( n < 100)
	{
	System.out.print("LT="); for( int i=0; i<n; i++) System.out.print(  LT[i] + " ");
	System.out.println();
	}
	System.out.println("quick PLSC=");
	for( int i=IV[ L]; i>= 0; i=Prec[i]) System.out.print( "\tE[" + i + "]=" + Elts[i] + " ");
	System.out.println("\nquick a fini");
	return LT;
}

static void affiche_DM( int[] E, int[] LT, int l)
{
        int i;
        System.out.println("Affiche domi: ");
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
	int[]  Prec=new int[n];
	int[] lt1=calcul( tab , Prec);
	int[] lt2=quick( tab);
	for( int i=0; i<n; i++) assert( lt1[i]==lt2[i]);
	System.out.println("oui, les 2 methodes rendent le meme resultat avec n="+ n);
	int maxi=0;
        int imaxi=0;
	for (int i=0; i< n; i++) if (lt2[i]>maxi) { maxi=lt2[i]; imaxi=i; }
	System.out.println("lgr maxi="+ maxi + " se termine en indice " + imaxi);
	affiche_DM( tab, lt2, maxi);
}

public static void main (String[] args)
{
/*
	exemple1();
	System.out.println();
	exemple2();
	System.out.println();
*/
        exemple(20);
        exemple(20);
        exemple(20);
        exemple(20);
	exemple( 100);
	exemple( 10000);
//	exemple( 100000);
}

}

