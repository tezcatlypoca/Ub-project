import java.awt.*;
import java.math.*;



public class Dragon extends Frame 
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
        double xm= x1 + dx/2.; double ym=y1 + dy/2.;
        double xt= xm-dy/2., yt=ym+dx/2.;
	dessine( g, n-1, x1, y1, xt, yt);
	dessine( g, n-1, x2, y2, xt, yt); 
}
public void paint( Graphics g ) 
{  
	g.setColor( Color.black );
	dessine( g, niveau, 130., 250., 490., 250.); 
        g.setColor( Color.red);
	dessine( g, 3, 130., 250., 490., 250.);
        g.setColor( Color.green);
	dessine( g, 2, 130., 250., 490., 250.);
        g.setColor( Color.magenta);
	dessine( g, 1, 130., 250., 490., 250.);
}
public static void main (String args[]) 
{	 Dragon f = new Dragon();
	  f.init(16);
	  f.setBounds(0, 0, 500, 500);
	  f.setVisible(true); 
}
}

