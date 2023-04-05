
public class Bezout 
{
   public int u, v, gcd;
   static public Bezout euclide( int a, int b)
   {
	if (0==a) { Bezout bez= new Bezout(); bez.u=0; bez.v=1; bez.gcd=b; return bez; } 
        Bezout bez= euclide( b % a, a);
        Bezout result= new Bezout();
	result.u = bez.v - (b / a) * bez.u;
        result.v = bez.u ;
        result.gcd = bez.gcd; 
        return result;
   }
 
   static public void go( int a, int b)
   { 
	Bezout bz= euclide( a, b);
        System.out.println( "[" + a + "; " + b + "] . [" + bz.u + "; " + bz.v + "]=" + bz.gcd);
   } 
   public static void main (String[] args)
   {
    go( 12, 182);
    go( 1325, 18294);
    go( 1325, 18294);
    go( 325, 8294);
   }
}
