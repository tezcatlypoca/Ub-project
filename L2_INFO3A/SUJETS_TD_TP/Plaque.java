/**
 *
 * @author Elis
 */

public class Plaque {
// classe avec attributs publics
  private int valeur;
  private String histoire;

  public Plaque( int val, String h)
  { valeur=val;
    histoire = h;
  }

  public boolean equals(Plaque p)
  { return (valeur==p.getValeur() && histoire.equals(p.getHistoire()));  }

  public String getHistoire() { return histoire;    }

  public int getValeur() { return valeur;   }

  public void setValeur(int val) { this.valeur = valeur;   }

  public String toString()
  { String res = "";
    res += valeur + " = { "+histoire+" }\n";
    return res;
  }

}
