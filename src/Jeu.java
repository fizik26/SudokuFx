/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Observable;
/**
 *
 * @author p1607434
 */
public class Jeu extends Observable{
    
    public Groupe[][] jeu;
    public Case[][] jeu2;
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
        
        jeu2 = new Case[9][9];
        for(int a =0; a< 9; a++)
        {
            for(int b =0; b<9;b++)
            {
                jeu2[a][b] = new Case(0);

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
    
    public void maj(int row, int column, char numero, String contenuFichier) throws IOException {
        // modifier le fichier .txt en remplaçant le 0 par le nombre rentré par l'utilisateur
        //System.out.println(contenuFichier.length());
        /*for(int i=0 ; i<contenuFichier.length() ; i++)
        {
            System.out.println(" !!!  "+contenuFichier.charAt(i));
        }*/
        
        // on trouve le bon caractère à changer puisque les espaces et les sauts de lignes comptent comme des caractères
        int numeroCaracAChanger = (row)*19+(column-1)*2;
        StringBuilder myName = new StringBuilder(contenuFichier);
        myName.setCharAt(numeroCaracAChanger, numero);

        //System.out.println(myName);
        
        contenuFichier = myName.toString();
        
        try {
            File file = new File("test.txt");

            if (!file.exists()) {
                file.createNewFile();
            }
            
            // remplace le fichier en effaçant son contenu et en écrivant dedans
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(contenuFichier);
            }


        } catch (IOException e) {
            System.out.println("Erreur fichier maj");
        }
        
      setChanged();
      notifyObservers();
    }

   public boolean getErr() {
        return err;
    }
    
    public double getValue() {
        return lastValue;
    }

    public static String loadFile(File f) {
        try {
           BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
           StringWriter out = new StringWriter();
           int b;
           while ((b=in.read()) != -1)
               out.write(b);
           out.flush();
           out.close();
           in.close();
           return out.toString();
        }
        catch (IOException ie)
        {
            System.out.println("Erreur loadFile");
        }
        return null;
    }
}