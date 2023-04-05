import java.awt.*;
import java.math.*;

class Pt {	int x, y; 
		static Pt point( int u, int v) { Pt p=new Pt();  p.x=u; p.y=v; return p; } }

class LP {   // GERER LES LISTES DE POINTS
   Pt elt; LP next;
   static  LP cons( Pt x, LP tail)
        { LP cell=new LP(); cell.elt=x; cell.next=tail; return cell;}
   static  boolean isempty( LP liste) { return liste==null ; }
   static  Pt head( LP liste) { return liste.elt; }
   static  LP tail(  LP liste) { return liste.next; } }

public class Convexe extends Frame {
	int width, height;
        Pt p0=null; // on a besoin de ce pointeur pour trier les points...
	LP lpts=null;
	LP convex=null;
static public int determinant( Pt p1, Pt p2, Pt p3)
{
	int x1= p1.x; int y1= p1.y; int x2=p2.x; int y2= p2.y; int x3=p3.x;  int y3 = p3.y ;
	return x1*(y2-y3) - x2*(y1-y3) + x3*(y1-y2);
}
   
// rend la liste des elements de rang pair (le premier est de rang 0 donc pair)
static public LP pairs( LP l)
{ 	if (null==l) return null;
     	if (LP.isempty( LP.tail( l))) return LP.cons( LP.head( l), null);
     	return LP.cons( LP.head( l), pairs( LP.tail( LP.tail( l))));
}
// rend la liste des elements de rang impair
static public LP impairs( LP l) 
	{ if (LP.isempty(l)) return null; return pairs( LP.tail(l)); }
// fusion de 2 listes de points triees
public LP fusion( LP l1, LP l2)
{	 if (LP.isempty( l1)) return l2;
	if (LP.isempty( l2)) return l1;
	Pt e1= LP.head( l1); Pt e2= LP.head( l2);
	if /* (e1.x <= e2.x || (e1.x==e2.x && e1.y <= e2.y)) */ 
	(determinant( p0, e1, e2) > 0)
	return LP.cons( e1, fusion( LP.tail( l1), l2));
	return LP.cons( e2, fusion( l1, LP.tail( l2))); }
public  LP mergesort( LP l)
{	 if (LP.isempty( l) || LP.isempty( LP.tail( l))) return l;
	return fusion( mergesort( pairs( l)), mergesort( impairs( l))); }
static boolean equalpt( Pt a, Pt b) { return a.x==b.x && a.y==b.y ;}
static LP enlever( LP ls, Pt p)
{    	LP l= null; 
	for( ; null != ls; ls=LP.tail( ls))
	if( ! equalpt( LP.head( ls) , p)) l= LP.cons( LP.head( ls), l);
	return l; }
void generer( int n )
{
	lpts= null;
	for( int i=1 ; i<n;  )
	{	int x=  (int) Math.floor( 500. * Math.random());
		int y=  (int) Math.floor( 500. * Math.random());
		if ((x-250)*(x-250) + (y-250)*(y-250) < 220*220 )
		{ lpts= LP.cons( Pt.point( x, y), lpts); i++ ; }
	}
	{ // on determine p0 le point de base; necessaire pour pouvoir trier
	p0 = LP.head( lpts);
	for( LP l2= lpts; l2 != null; l2= LP.tail( l2)) 
		{ Pt p2= LP.head( l2) ; 
		  if (p2.x < p0.x || (p2.x == p0.x && p2.y < p0.y)) p0 = p2;
		}
	}

	lpts= LP.cons( p0, mergesort( enlever( lpts, p0)));
	convex = grahamWalk( LP.cons( p0, null), lpts); }
public LP grahamWalk( LP ec, LP reste)
{
	if (reste==null) return ec; 
	Pt c= LP.head( reste); LP qreste=LP.tail( reste);
	if ( null== ec) return grahamWalk( LP.cons( c, null), qreste);
        Pt b= LP.head( ec); LP qec= LP.tail( ec);
        if (c.x == b.x && c.y == b.y ) return grahamWalk( ec, qreste); // jeter c car b==c
	if ( null== qec ) return grahamWalk( LP.cons( c, ec), qreste); 
        Pt a=LP.head( qec);  
	if (determinant( a, b, c) > 0) return grahamWalk( LP.cons( c, ec), qreste); 
	else return grahamWalk( qec, reste); }  
public void init() {
      width = getSize().width;
      height = getSize().height;
      setBackground( Color.white ); }
public void paint( Graphics g ) 
{  
	g.setColor( Color.black );
	LP l=lpts; int i=0;
	for(  ; l != null; l=LP.tail(l), i++)
	{
	Pt a= LP.head( l);
	g.fillRect( a.x, a.y, 1, 1);
	//g.drawString(  Integer.toString( i), a.x+2, a.y);
	}
	g.setColor( Color.red);
	for( l= convex; l != null; l=LP.tail(l))
	{
	Pt a= LP.head( l);
	LP tmp= LP.tail( l);
	if (tmp==null) break; 
	Pt b= LP.head( tmp);
	g.drawLine( a.x, a.y, b.x, b.y);
	}
	{ Pt a= LP.head( convex); g.drawLine( a.x, a.y, p0.x, p0.y); } }
public static void main (String args[]) 
{	 Convexe f = new Convexe();
	  f.init();
	  int n= 1000;
	  if (args.length==1) n=Integer.parseInt(args[0]);
	  f.generer( n); // argument = nb de points generes
	  f.setBounds(0, 0, 500, 500);
	  f.setVisible(true); }
}

