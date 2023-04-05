class L {
   public Etat elt;
   public L next;
   static public L cons( Etat x, L tail)
        { L cell=new L(); cell.elt=x; cell.next=tail; return cell;}
   static public L nil= new L();  // j'aimerai bien mettre NULL, 0, ou 1 truc comme ca...
   static public boolean isempty( L liste) { return liste==nil ; }
   static public Etat head( L liste) { return liste.elt; }
   static public L tail(  L liste) { return liste.next; }
}

class Etat {
   int [] pos; int nbposees;
   public static Etat  echiquier( int n) 
   { 	Etat e=new Etat(); e.pos= new int[n]; e.nbposees=0; return e; }
   static public boolean possible( Etat etat,  int l)
   {    int [] pos= etat.pos; int nbposees=etat.nbposees;
        if (nbposees == pos.length) return false;
        for( int c=0; c < nbposees; c++)
		if (pos[c]==l || Math.abs(nbposees-c) == Math.abs( pos[c]-l)) return false;
	return true;
   }   
   public static Etat poser( Etat ech,  int col, int lig)
   {   Etat e= echiquier( ech.pos.length );
       e.nbposees = ech.nbposees + 1;
       for (int i=0; i< ech.nbposees; i++) e.pos[i] = ech.pos[i];
       e.pos[ col] = lig;
       return e;
   }
   public L fistons(  L stack)
   { Etat s=this;
     int n = s.pos.length;
     if (s.nbposees == n) return stack;
     for (int l=0; l < n ; l++ ) if (possible( s, l)) stack = L.cons( Etat.poser( s, s.nbposees, l), stack); 
     return stack;
   }
   public static void print( Etat e)
   { for( int i=0; i < e.pos.length; i++) System.out.print( e.pos[i] + " ");
     System.out.println();
   }
}




public class Reine {
   public static L solve( int n)
   {
	L stack= L.cons( Etat.echiquier( n), L.nil );
        L solutions = L.nil;
        for( ; ! L.isempty( stack); )
        {
		Etat e = L.head( stack); stack = L.tail( stack);
                if (e.nbposees == n) solutions= L.cons( e, solutions);
		else stack= e.fistons( stack) ;
	}
        return solutions;
   }   
   public static void main (String[] args)
   {	int n= 8;
        for (int i=0; i<args.length; i++) System.out.println( "args[" + i + "]=" + args[i]);
        if (args.length==1) n=Integer.parseInt(args[0]); 
        L solutions=solve( n); 
	L l = solutions ;
	for( int num=1; ! L.isempty( l); l= L.tail(l), num++) 
	{ Etat sol= L.head( l); System.out.print( num + " : "); Etat.print( sol); } 
   }
}
