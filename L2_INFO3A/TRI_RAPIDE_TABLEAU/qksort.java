
interface Comp { public int compare( Object x, Object y); }
class DoubleCmp implements Comp
{ public int compare( Object a, Object b)
  { double fa= ((Double) a).doubleValue();
    double fb= ((Double) b).doubleValue();
    if (fa < fb) return -1;
    if (fa > fb) return 1;
    return 0;
  }
}
class InvCmp implements Comp
{ public int compare( Object a, Object b)
  { double fa= ((Double) a).doubleValue();
    double fb= ((Double) b).doubleValue();
    if (fa < fb) return 1;
    if (fa > fb) return -1;
    return 0;
  }
}

	
class Qksort 
{

   static public double random( double x ) 
   { return (x * Math.random() ); }

public static void swap( Object[] tab, int a, int b) 
   { Object tmp=tab[a]; tab[a]=tab[b]; tab[b]=tmp;}

public static boolean check( Object[] tab, Comp cmp)
{
	boolean okay=true;
	for( int i=0; i+1 < tab.length; i++)
		okay= okay && (cmp.compare( tab[i],  tab[i+1]) <= 0);
	return okay;
}

public static void qksort( Object[] tab, int deb, int fin, Comp cmp)
{	if (fin <= deb ) return;

	double nb = fin-deb+ 1.;
	double piv= nb * Math.random();
	int pivot= deb + (int) piv;

//int pivot= deb;
	swap( tab, pivot, deb);
	int npetits= 0; int ngrands=0; int negaux= 0;
	for (int i= deb+1; i <= fin; i++)
	{
		int cas= cmp.compare( tab[deb], tab[i]);
/*
		if (cmp.compare( tab[deb], tab[i]) < 0) ngrands++;
		else if (0==ngrands) npetits++;
		     else { swap( tab, deb+npetits+1, i); npetits++; }
*/
		if (-1 == cas) ngrands++;
		else if (0 == cas) {if (0<ngrands) swap(tab, i, deb+npetits+negaux+1); 
			            negaux++; }
		else { swap( tab, i, deb+npetits+negaux+1);
                       swap( tab,deb+npetits+negaux+1, deb+npetits+1);
		       npetits++;
		      }
	}
	swap( tab, deb, deb + npetits);

	//System.out.println( "npetits=" + npetits + " ngrands= " +ngrands );
	qksort( tab, deb, deb+npetits-1, cmp);
	qksort( tab, deb+npetits+negaux+1, fin, cmp);
}
		
public static void main (String[] args)
   { 
	//int n=1000000;
	int n=100;
	Object tab[]= new Object[n];
	for (int i=0; i<n; i++) { tab[i] = new Double( Math.floor(100000. * Math.random())); }

//for (int i=0; i<n; i++) { tab[i] = 1. ; }
	for (int i=0; i<  Math.min(n, 100); i++)  System.out.print( tab[i].toString() + " "); 
	System.out.println();

	qksort( tab, 0, n-1, new DoubleCmp() );

	for (int i=0; i< Math.min(n, 100); i++)  System.out.print( tab[i].toString() + " "); 
	System.out.println();

	if (check( tab, new DoubleCmp() )) System.out.println( "C'EST TRIE\n"); else System.out.println( "ERREUR!\n");

	qksort( tab, 0, n-1, new InvCmp());
	for (int i=0; i< Math.min(n, 100); i++)  System.out.print( tab[i].toString() + " ");
	System.out.println();
	if (check( tab, new InvCmp())) System.out.println( "C'EST TRIE INVERSE"); else System.out.println( "ERREUR!");

  }
}
