/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
/**
 *
 * @author p1607434
 */
public class Jeu extends Observable{
    
    public Groupe[][] jeu;    
    
    double lastValue;
    boolean err = false;


    //construction d'une grille vide
    public Jeu()
    {
        jeu = new Groupe[9][9];
        for(int i =0; i< 9; i++)
        {
            for(int j =0; j<9;j++)
            {
                jeu[i][j] = new Groupe();
            }
        }
    }
    
    public void init() throws IOException
    {
       // Groupe ligne[] = new Groupe[9]; //9 lignes
       // Groupe colonne[] = new Groupe[9]; // 9 colonnes
       // Groupe carre[][] = new Groupe[3][3]; //9 regions
        
        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        String ligne;
        int j=0;
        while(j<9) 
        {
            while ((ligne = br.readLine()) != null) 
            {
                String[] td = ligne.split(" ");
              // System.out.println(Arrays.toString(td));
                for(int i=0 ; i<td.length ; i++)
                {
                    Case c = null;

                    if("0".equals(td[i])) //si c'est à 0, c'est une case non bloquante 
                    {
                        c = new CaseNonBloquante(0);
                        jeu[j][i].setCase(j, c); //on ajoute c à la case correspondant au j (groupe ligne), i la colonne
                    }
                    else //sinon c'est une case bloquante et on lui met la valeur présente dans td[i]
                    {
                        int valeurSplit =  Integer.parseInt(td[i]);
                        c = new CaseBloquante(valeurSplit);
                        jeu[j][i].setCase(j, c);

                        }

                    //int numL = i/9; // numéro de la ligne
                    //int numC = i%9; // numéro de la colonne

                        //on ajoute la case aux groupes correspondant??
                        //ligne[numL].ajouter(c);
                        //colonne[numC].ajouter(c);
                        //carre[numL/3][numC/3].ajouter(c);
                }
                j=j+1;
            }
        }
        br.close();
        setChanged();
        notifyObservers();
}
    

    
    //retourne le groupe de coordonnée i j
    public Groupe getGroupe(int i, int j)
    {
        return jeu[i][j];
    }

    
    public void setGroupe(Groupe[][] g)
    {
        jeu = g ;
    }
    
    public void maj(int row, int column) {
        // modifier le fichier .txt en remplaçant le 0 par le nombre rentré par l'utilisateur
        
        
      setChanged();
      notifyObservers();
    }

   public boolean getErr() {
        return err;
    }
    
    public double getValue() {
        return lastValue;
    }

}
