class Tas {
double tab[];
int nb;

Tas( int taille) { tab=new double[ taille+1]; nb=0 ; }
 
double minimum() { assert( nb != 0); return tab[1]; }

void retablir_d( int k) // retablir le tas en descendant
{
	int fg= 2*k; int fd= fg+1;
	if (fg > nb) return; // k n'a pas de fils
	if (fg == nb) // k a seulement un fils gauche
        {	if( tab[fg] < tab[k]) { swap( k, fg); retablir_d( fg); }
		return;
	}
	// k a bien 2 fils
	if (tab[k] <= tab[fg] && tab[k] <= tab[fd]) return;
	// sinon on echange k avec son fils le plus petit
	if (tab[fg] < tab[fd]) 
		{ swap( k, fg); retablir_d( fg); }
	else    { swap( k, fd); retablir_d( fd); }
}

void supprimer() 
{ 	assert( nb != 0); 
	tab[1] =  tab[ nb];
	nb = nb - 1;
	retablir_d( 1);
}

void swap( int i, int j) { double tmp= tab[i]; tab[i]= tab[j]; tab[j]=tmp; }

void retablir_m( int k) // retablir le tas en  montant 
{	
	int papa = k / 2;
	if (0 == papa) return; // c'est ok
	if (tab[papa] > tab[k]) { swap( k, papa); retablir_m( papa); }
}

void ecrire()
{	for (int i=1; i<=nb; i++) System.out.print( tab[i] + " ");
	System.out.println();
}
	
void inserer( double v)
{
	assert( nb + 1 < tab.length);
	nb = nb + 1;
	tab[ nb ] = v;
	retablir_m( nb); 
}

public static void main (String[] args)
{
	{
		int n= 12;
		Tas tas= new Tas( n);
		for( int i=0; i < n-1; i++)  
		{	double v= Math.floor( 10000. * Math.random());
			tas.inserer( v );
			tas.ecrire();
		}
		System.out.println("FIN DES INSERTIONS\n");		
		for(  ; tas.nb > 0; )
		{	double v= tas.minimum();
			System.out.println( v );
			tas.supprimer();
		}
	}

	{
		int n=1000000;
		double [] trie= new double[n];
		Tas tas= new Tas( n);
		for( int i=0; i < n-1; i++)
		{       double v= 10000. * Math.random();
			tas.inserer( v );
		}
		for( int i=0  ; tas.nb > 0; i++ )
		{       trie[ i] =tas.minimum();
			tas.supprimer();
			assert( i==0 || trie[i-1]<=trie[i]);
		}
		System.out.println("\n J'AI TRIE AVEC SUCCES " + n + " FLOTTANTS\n");
	}
}
}
