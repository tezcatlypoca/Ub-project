
public class Sequence {
 static int [] E={0,300,100,200,1000,400,500,1100, 900, 800, 600,700,-100};
 static int [] LT;

public static void initLT(int n)
// calcule la plus grande sequence croissante 
 { LT = new int[n];  
   // a completer
        LT[0]=1;
        for( int i=1; i<n; i++)
        {
                LT[i]=1;
                for( int k=0; k<= i-1; k++)
                        if (E[k] <= E[i] && LT[i] < LT[k]+1)
                                LT[i]= LT[k]+1;
        }
        if( n < 100)
        {
        for( int i=0; i<n; i++) System.out.print( E[i] + " ");
        System.out.println();
        for( int i=0; i<n; i++) System.out.print(  LT[i] + " ");
        System.out.println();
        }
        System.out.println("calcul a fini");
 }

private static void initLTsecours(int n)
// methode a utiliser pour remplacer initLT
 { LT = new int[n];
   LT[0] =1; LT[1]=LT[2]=2; LT[3]=3; LT[4]=LT[5]=4; LT[6]=5;
   LT[7]=LT[8]=LT[9]=LT[10]=6;LT[11]=7;LT[12]=1;
 }

 public static void affichSeq(int l)
 // affiche la première séquence de longueur l quand elle existe
 { if (l>=0 && l<E.length) 
         affichSeqRec(1, l);
 }

 private static void affichSeqRec(int li, int lg)
 // affichage recursif de la plus grande sequence croissante de longueur lg
 //  en commencant a la longueur li
  { if (li==lg)
    { // positionnement sur la premiere valeur egale a li dans LT
      int i=0;while (i<E.length && LT[i]<li) i++;
      // i = position de la premiere valeur li ou hors du tableau
      // affichage si premiere valeur de li
      if (i<E.length) System.out.println(" "+E[i]);
    }
    else
    { // positionnement apres la derniere valeur egale a li dans LT
      int i=0;while (i<E.length && LT[i]<=li) i++;
      // i = position de la premiere valeur apres li ou hors du tableau
      // i-1 = position de la derniere valeur egale a li
      if (i<E.length) System.out.print(" "+E[i-1]);
      // appel recursif avec li+1
      affichSeqRec(li+1, lg);
    }
  }

static void affiche_DM( int[] E, int[] LT, int l)
{
	int i;
	System.out.print("Affiche domi: ");
	for (i= E.length-1; i>=0 && LT[i]!=l ; i--);
	if(i<0) return;
	System.out.print( E[i] + "; ");
	for (l=l-1; l >= 1; l--)
	{
		for( ; i>=0 && LT[i]!=l ; i--) ;
		if(i<0) return;
		System.out.print( E[i] + " ");
	}
	System.out.println();
}
/*
  public static String affichSeqTous(int lg)
  { assert (lg>=0 && lg<E.length);
    return affichRecTous(1, lg, "sequence de taille "+lg+"\n");
  }
*/

/*
  private static String affichRecTous(int li, int lg, String res)
  { // a completer

  }
*/

    public static void main(String[] args) {
        // a completer
initLT( E.length);
int maxi=0;
for (int i=0; i< E.length; i++) if (LT[i]>maxi) maxi=LT[i];
affichSeq( maxi);
affiche_DM( E, LT, maxi );
}
}
