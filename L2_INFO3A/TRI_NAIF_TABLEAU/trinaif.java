interface Comp { public int compare( Object x, Object y); }
class DoubleCmp implements Comp
{ public int compare( Object a, Object b)
  { double fa= ((Double) a).doubleValue();
    double fb= ((Double) b).doubleValue();
    if (fa < fb) return -1;
    if (fa > fb) return 1;
    return 0; }
}
class InvCmp implements Comp
{ public int compare( Object a, Object b)
  { double fa= ((Double) a).doubleValue();
    double fb= ((Double) b).doubleValue();
    if (fa < fb) return 1;
    if (fa > fb) return -1;
    return 0; }
}
class Trinaif
{  static public double random( double x ) 
   	{ return (x * Math.random() ); }
   public static void swap( Object[] tab, int a, int b) 
	{ Object tmp=tab[a]; tab[a]=tab[b]; tab[b]=tmp;}
   public static boolean check( Object[] tab, Comp cmp)
	{ boolean okay=true;
	for( int i=0; i+1 < tab.length; i++)
		okay= okay && (cmp.compare( tab[i],  tab[i+1]) <= 0);
	return okay; }
   public static void trinaif( Object[] tab, Comp cmp)
	{	int fin= tab.length-1;
		for( int i=0; i<fin; i++)
		{	int smallest= i;
			for (int j=i+1; j<= fin; j++)
			if (cmp.compare( tab[j], tab[smallest])== -1) 
				smallest = j;
			swap( tab, i, smallest);
		}
		if (! check( tab, cmp)) System.out.println("BUG\n"); 
	}
    public static void main (String[] args)
    {   int n=20;
	Object tab[]= new Object[n];
	for (int i=0; i<n; i++) 
	{ tab[i] = new Double( Math.floor(100000. * Math.random())); }
        for (int i=0; i<  Math.min(n, 10); i++)  
		System.out.print( tab[i].toString() + " "); 
	System.out.println("------------------\n");
	trinaif( tab, new DoubleCmp() );
	for (int i=0; i< Math.min(n, 20); i++)  
		System.out.print( tab[i].toString() + " "); 
	System.out.println("------------------\n");
	trinaif( tab, new InvCmp() );
	for (int i=0; i< Math.min(n, 20); i++)  
		System.out.print( tab[i].toString() + " ");
	System.out.println();
  }
}
