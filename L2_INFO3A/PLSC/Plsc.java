class Plsc {


static int[] calcul( int Elts[])
{
	System.out.println("calcul commence");
	int n=Elts.length;
	int LT[] = new int[n];
	LT[0]=1;
	for( int i=1; i<n; i++)
	{
		LT[i]=1;
		for( int k=0; k<= i-1; k++) 
			if (Elts[k] <= Elts[i] && LT[i] < LT[k]+1)  
				LT[i]= LT[k]+1;
	}
	if( n < 100)
	{
	for( int i=0; i<n; i++) System.out.print( Elts[i] + " ");
	System.out.println();
	for( int i=0; i<n; i++) System.out.print(  LT[i] + " ");
	System.out.println();
	}
	System.out.println("calcul a fini");
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
        int LT[] = new int[n];
	int V[] = new int[n+1];
	V[1]=Elts[0];
	int L=1;
	LT[0]=1;
	for( int i=1; i<n; i++)
        {
                LT[i]=1 + dicho( Elts[i], V, 1, L);
		if (LT[i] > L) L=LT[i];
		V[ LT[i] ] = Elts[i];
        }
	if( n < 100)
	{
	for( int i=0; i<n; i++) System.out.print(  LT[i] + " ");
	System.out.println();
	}
	System.out.println("quick a fini");
	return LT;
}

static void affiche_DM( int[] E, int[] LT, int l)
{
        int i;
        System.out.print("Affiche domi: ");
        for (i= E.length-1; i>=0 && LT[i]!=l ; i--);
        if(i<0) return;
        System.out.print( E[i] + "; ");
        for (l=l-1; l >= 1; l--)
        {
		int ii=i;
                for( ; i>=0 && (LT[i]!=l || E[i]>E[ii]) ; i--) ;
                if(i<0) return;
                System.out.print( E[i] + " ");
        }
        System.out.println("\n");
}

static void  exemple1()
{
	int tab[]= {1, 300, 100, 200, 1000, 400, 500, 1100, 900, 800 , 600 , 700, 0 };
	int n= tab.length;
	int[] LT1= calcul( tab );
	int maxi=0;
	for (int i=0; i< tab.length; i++) if (LT1[i]>maxi) maxi= LT1[i];
	affiche_DM( tab, LT1, maxi);
	int[] LT2= quick( tab);
	affiche_DM( tab, LT2, maxi);
	for( int i=0; i<n; i++) assert( LT1[i]==LT2[i]);
	System.out.println("oui, les 2 methodes rendent le meme resultat avec n="+ tab.length);
	System.out.println("\n");
}

static void  exemple2()
{
        int tab[]= {0, 300, 100, 200, 1000, 400, 500, 1100, 900, 800 , 600 , 700, -100, 340, 1200, 666, 555, 222, 333, 444, 111, 1111, 999, 9999, 11, 30 };
	int n= tab.length;
        int[] LT1= calcul( tab );
        int[] LT2= quick( tab);
	int maxi=0; for (int i=0; i< tab.length; i++) if (LT1[i]>maxi) maxi= LT1[i];
	affiche_DM( tab, LT1, maxi);
	affiche_DM( tab, LT2, maxi);
	for( int i=0; i<n; i++) assert( LT1[i]==LT2[i]);
	System.out.println("oui, les 2 methodes rendent le meme resultat avec n="+ tab.length);
	System.out.println("\n");
}

static void  exemple( int n)
{
	int[] tab=new int[n];
	for (int i=0; i< n; i++) tab[i]= (int) (100000. * Math.random()) ;
	int[] lt1=calcul( tab );
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
	exemple1();
	System.out.println();
	exemple2();
	System.out.println();
        exemple(10);
        exemple(20);
	exemple( 10000);
	exemple( 100000);
}

}

