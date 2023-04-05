import java.awt.*;
import java.math.*;



public class Vonkoch extends Frame 
{
	int width, height; int niveau;
   
public void init( int n) 
{
      niveau=n;
      width = getSize().width;
      height = getSize().height;
      setBackground( Color.white ); 
}
public void dessine( Graphics g, int n, double x1, double y1, double x2, double y2)
{
	if (0==n) { g.drawLine( (int) x1, (int) y1, (int) x2, (int) y2); return; }
	double dx= x2-x1; double dy=y2-y1;	
	double xp=x1+dx/3., yp=y1+dy/3. ;
        double xq=x1+2.*dx/3., yq=y1+2.*dy/3.;
        double  xm= x1 + dx/2.; double ym=y1 + dy/2.;
        double xt=xm-dy/Math.sqrt(12.), yt=ym+dx/Math.sqrt(12.);
	dessine( g, n-1, x1, y1, xp, yp);
	dessine( g, n-1, xp, yp, xt, yt); 
	dessine( g, n-1, xt, yt, xq, yq);
	dessine( g, n-1, xq, yq, x2, y2);
}
public void paint( Graphics g ) 
{  
	g.setColor( Color.black );
	dessine( g, niveau, 10., 30., 490., 30.); 
	g.setColor( Color.blue);
	dessine( g, niveau, 490., 30., 490., 490.); 
	g.setColor( Color.magenta);
	dessine( g, niveau, 490., 490., 10., 490.); 
	g.setColor( Color.red);
	dessine( g, niveau, 10., 490.,  10., 30.); 
	g.setColor( Color.black );
}
public static void main (String args[]) 
{	 Vonkoch f = new Vonkoch();
	  f.init(5);
	  f.setBounds(0, 0, 500, 500);
	  f.setVisible(true); 
}
}

