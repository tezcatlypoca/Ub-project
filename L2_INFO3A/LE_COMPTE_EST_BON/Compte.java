class L { public Compte elt; public L next; }
class Plaque { int valeur; String histoire; 
	void ecrire() { System.out.println( "PLAQUE: "  + valeur + " = " + histoire); } }

class Compte {
Plaque  plaques[] ; int but;

static public L nil= null;
static public L cons( Compte x, L tail) { L cell=new L(); cell.elt=x; cell.next=tail; return cell;}
static public boolean isempty( L liste) { return liste==nil ; }
static public Compte head( L liste) { return liste.elt; }
static public L tail(  L liste) { return liste.next; }

public static Plaque plaque( int v, String h)
{ Plaque p=new Plaque(); p.valeur=v; p.histoire = new String( h) ; return p; }

void ecrire() { System.out.println( "ETAT: but=" + but);
		for (int i=0; i< plaques.length; i++) plaques[i].ecrire(); }

Compte genere( int a, int b, int valab, String operation) 
{ // la plaque numero a et la plaque numero b sont remplacees par une plaque a+b, a-b,a/b :
	int n, idest, isrc;
	idest=0; isrc=0;
	n= this.plaques.length;
	assert( n >= 2);
	Compte e = new Compte();
	e.but = this.but; e.plaques = new Plaque[n-1]; 
	// recopie l'ancien dans le nouveau, sauf a et b :
	for( idest=0, isrc=0 ; isrc < n; isrc++) if (isrc != a && isrc != b)
	{	e.plaques[idest] = plaque( this.plaques[isrc].valeur, this.plaques[isrc].histoire);
		idest ++; }
	assert( n-2 == idest );
	e.plaques[idest] = plaque( valab, 
                       this.plaques[a].histoire + this.plaques[b].histoire + operation);
	return e;
}
        
L fistons(  L stack)  // fils de this
{ 	int n= this.plaques.length;
	if (n<=1) return stack; // on ne peut pas creer de nouvelle plaque
	for( int a=0; a < n-1; a++) for( int b=a+1; b<n; b++)
	{
		int va= plaques[a].valeur; int vb=plaques[b].valeur;
		stack= cons( this.genere( a, b, va*vb, "* "), stack); 
		stack= cons( this.genere( a, b, va+vb, "+ "), stack); 
		if ( va > vb) 
			stack=cons( this.genere( a, b, va-vb, "- "), stack); 
		if ( vb > va) 
			stack=cons( this.genere( b, a, vb-va, "- "), stack); 
		if (vb != 0 && 0==va % vb) 
			stack=cons( this.genere( a, b, va/vb, "/ "), stack); 
		if (va != 0 && 0==vb % va) 
			stack=cons( this.genere( b, a, vb/va, "/ "), stack); 
	}
	return stack;
}

Plaque solve() 
    {
        Plaque bestplaque= this.plaques[0];
	L stack= cons( this, nil);
	for( ; ! isempty( stack ) ; )
	{	Compte coco= head( stack); stack= tail( stack); // Compte courant :))
		for( int p=0; p < coco.plaques.length; p++)
			if  (Math.abs( coco.plaques[p].valeur - coco.but ) 
			   < Math.abs( bestplaque.valeur - coco.but ))
			  { bestplaque= coco.plaques[p]; 
			    System.out.print( "La meilleure solution courante devient: ");
			    bestplaque.ecrire();
			  }
		stack= coco.fistons( stack);
        }
	bestplaque.ecrire();
	return bestplaque;
     }

public static void main (String[] args) 
{    
	int n= args.length - 1 ;
	Compte e=new Compte();
	if (n >= 2)
	{
		e.but = Integer.parseInt(args[0]);
		e.plaques= new Plaque[n];
		for( int i=0; i< n; i++)  
			e.plaques[i] = plaque( Integer.parseInt(args[i+1]), args[i+1]+" "); 
	}
	else
	{ 	e.but = 100; e.plaques= new Plaque[2];
		  e.plaques[0]= plaque( 25, "25 ");
		  e.plaques[1]= plaque( 4, "4 ");
	}
	e.ecrire();
        Plaque best = e.solve(); 
   }
}
