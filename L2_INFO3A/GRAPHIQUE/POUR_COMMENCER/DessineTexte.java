import java.awt.*;
import java.lang.*;

public class DessineTexte extends Frame {

public void paint(Graphics g) { 
 String s = new String(" Ce texte va etre affiche ondule ! "); 
 double ya,  degre = 0; 
 double rad = Math.PI/180;
 int x= 10 , y = 100;
 g.setFont(new Font("TimesRoman", Font.BOLD ,15));
 
 //for ( int i=0 ; i < /*s.length */ 10; i++) { 
 for ( int i=0 ; i < s.length() ; i++) { 
   ya=10 * Math.sin(degre * rad);
   y += (int) ya ;
   g.drawString(s.charAt(i)+" ", x, y) ;
   x += 10 ;
   degre += 15 ;
 }
}
public static void main (String arg[]) {
 DessineTexte f = new DessineTexte();
  f.setBounds(0, 100, 450, 300);
  f.setVisible(true);
 }
}
