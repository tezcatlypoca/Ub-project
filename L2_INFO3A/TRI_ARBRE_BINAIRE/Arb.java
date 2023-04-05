

class L {
   public int elt;
   public L next;
   static public L cons( int x, L tail) 
	{ L cell=new L(); cell.elt=x; cell.next=tail; return cell;}
   static public L nil= null; 
   static public boolean isempty( L liste) { return liste==nil ; }
   static public int head( L liste) { return liste.elt; }
   static public L tail(  L liste) { return liste.next; }
   static int fib( int x) { if (x < 2) return x; return fib(x-1) + fib(x-2); }
   static public int fact( int x) { if (x < 2) return 1; return x * fact(x-1); }

// concat de 2 listes
   static public L concat( L l1, L l2)
   { if (isempty( l1) ) return l2;
     if (isempty( l2) ) return l1;
     return cons( head( l1), concat( tail( l1), l2)); }

// generer une liste aleatoire; je ne connais pas de methode random en java, mais ca doit exister...
   static public int random( int x ) //{ return (311 * x) % 1001; }
   { return (int) (100000. * Math.random() ); }

   static public L lalea( int n) { if (n==0) return nil; return cons( random(n), lalea(n-1)); }
   static public L lalea2( int n) { L l=nil; 
	for (int i=0; i<n; i++) l=cons( random(i), l);
	return l; }
   static public int length( L l)
   { int n; for (n=0; ! isempty(l); n++, l= tail( l));
     return n; }

 // rend la liste des elements de rag pair (le premier est de rang 0 donc pair) 
   static public L pairs( L l)
   { if (isempty(l)) return nil;
     if (isempty( tail( l))) return cons( head( l), nil);
     return cons( head( l), pairs( tail( tail( l))));
   }
 // rend la liste des elements de rang impair 
   static public L impairs( L l) { if (isempty(l)) return nil; return pairs( tail(l)); }
 // fusion de 2 listes triees
   static public L fusion( L l1, L l2)
   { if (isempty( l1)) return l2;
     if (isempty( l2)) return l1;
     int e1= head( l1); int e2= head( l2);
     if (e1 <= e2) return cons( e1, fusion( tail( l1), l2)); 
     return cons( e2, fusion( l1, tail( l2)));
   }
   static public  L mergesort( L l)
   { if (isempty( l) || isempty( tail( l))) return l;
     return fusion( mergesort( pairs( l)), mergesort( impairs( l)));
   }

   // QUICKSORT
   // filtre les elts + petits que key dans une liste l :
   static public L smallerthan( L l, int key)
   { if (isempty( l)) return nil;
     if (head( l) < key) return cons( head( l), smallerthan( tail( l), key));
     return smallerthan( tail( l), key); }
   // filtre les elts + grands que key dans une liste l :
   static public L greaterthan( L l, int key)
   { if (isempty( l)) return nil;
     if (head( l) > key) return cons( head( l), greaterthan( tail( l), key));
     return greaterthan( tail( l), key); }
   // filtre les elts equal to key dans une liste l :
   static public L equalto( L l, int key)
   { if (isempty( l)) return nil;
     if (head( l) == key) return cons( head( l), equalto( tail( l), key));
     return equalto( tail( l), key); }
   static public L quicksort( L l)
   {  if (isempty( l) || isempty( tail( l))) return l; 
      int key = head( l); L tl= tail( l);
      return concat( quicksort( smallerthan( tl, key)),
                     concat( equalto( l, key), 
                             quicksort( greaterthan( tl, key))));
   }
	
   // TRI NAIF
   static int min( int a, int b) { if (a<=b) return a; return b; }
   // minimm d'une liste :
   static public int minimum( L l, int sivide)
   { if ( isempty( l)) return sivide;
     return minimum( tail( l), min( head( l), sivide));
   }
   static public L remove( L l, int key)
   { if ( isempty( l)) return l;
     if (key == head( l)) return tail( l);
     return cons( head( l), remove( tail( l), key)); }
   static public L slowsort( L l)
   {
	if ( isempty( l) || isempty( tail( l))) return l ;
	int key = minimum( l, head( l));
        return cons( key, slowsort( remove( l, key))); 
   }

   public static void printL( L l)
   { int n=0;
     for ( L c=l; ! isempty( c) && n<100; c=tail(c), n++) System.out.print( c.elt + "  "); 
     System.out.println(); }

   public static void main (String[] args)
   {
    {
    L l= cons( 1, cons( 2, cons( 3, nil)));
    for (L c=l; c != nil; c=c.next) System.out.print( c.elt + "  ");
    System.out.println();
    for (L c=l; ! isempty( c); c= tail( c)) System.out.print( head(c) + "; ");
    System.out.println();
    }

    System.out.println("Hello World");
    System.out.println("Fib(5)=");
    System.out.println( fib(5));
    for( int i=0; i<14; i++)
    {
	    System.out.print("Fib(");
	    System.out.print(i);
	    System.out.print(")=");
	    System.out.println( fib(i));
    }

    { // test de mergesort
    L l= lalea2( 4000);
    System.out.print( "\nLa liste non triee:\n");
    printL( l);
    System.out.print( "\nLa liste triee par mergesort :\n");
    printL( mergesort( l));
    System.out.print( "\nLa liste triee par quicksort :\n");
    printL( quicksort( l));
    System.out.print( "\nLa liste triee par slowsort :\n");
    printL( slowsort( l));
    }
   }
}

public class Arb
{
	public Arb fg, fd; public int key;	
	public static Arb tnil= null;
        public static boolean estVide( Arb a) { return tnil==a; }
	public static Arb arbre( Arb g, int val, Arb d) 
	{ Arb t=new Arb(); t.fg=g; t.fd=d; t.key=val; return t; }
        public static Arb insert1( int v, Arb tree)
	{ if (estVide( tree)) return arbre( tnil, v, tnil);
	  if (v <= tree.key) return arbre( insert1( v, tree.fg), tree.key, tree.fd);
	  return arbre( tree.fg, tree.key, insert1( v, tree.fd));
        }
	public static L aplatir( Arb t, L liste)
        { if (estVide( t)) return liste;
	  return aplatir( t.fg, L.cons( t.key, aplatir( t.fd, liste)));
        }
        public static int maxi( int a, int b) { return a <= b ? b : a ;}
        public static int mini( int a, int b) { return a <= b ? a : b ;}
        public static int height( Arb t)
	{	if (estVide( t)) return 0;
		return 1 + maxi( height( t.fg), height( t.fd));	
	}
        public static int heightmin( Arb t)
        {       if (estVide( t)) return 0;
                return 1 + mini( heightmin( t.fg), heightmin( t.fd));
        }

        public static Arb insertn( L liste, Arb tree)
        { if (L.isempty( liste)) return tree;
	  return insertn( L.tail( liste), insert1( L.head(liste), tree));
	}
        public static L sort_tree( L l) { return aplatir( insertn( l, tnil), L.nil); }
        public static void main (String[] args)
        {
		L l=L.lalea2( 100000);
                Arb t= insertn( l, tnil);
                System.out.println( "NB=" + L.length( l));
                System.out.println( "LOG_2 NB=" + (Math.log( (double) L.length( l))/Math.log(2.)));
		System.out.println( "HAUTEUR=" + height( t));
		System.out.println( "HAUTEUR MIN=" + heightmin( t));
		System.out.print( "LISTE TRIEE PAR ARBRE BINAIRE\n");
	 	L.printL( aplatir( t, L.nil));
        }
} 

