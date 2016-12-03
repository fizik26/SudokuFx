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
    
    public Groupe lignes[]; //les 9 lignes
    public Groupe colonnes[]; //les 9 colonnes
    public Groupe regions[][]; //les 9 regions
    
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
        
        //sudoku avec des ligne
        lignes = new Groupe[9];
        colonnes = new Groupe[9];
        for(int a =0; a< 9; a++)
        {
            lignes[a] = new Groupe();
            colonnes[a] = new Groupe();
        }
        
        regions = new Groupe[3][3];
        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                regions[a][b] = new Groupe();
            }
            
        }
    }
    
    public void init(String data) throws IOException
    {        
        BufferedReader br = new BufferedReader(new FileReader(data));

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
                        jeu2[j][i].setValeur(c.getValeur());
                        lignes[j].setCase(i, c);
                        colonnes[j].setCase(i, c);
                        //regions[j][i].setCaseRegion(j, i, c);
                        this.setCaseNumRegion(j, i, c.getValeur());
                        //regions[j/3][i/3].setCaseValeurRegion(j%9, i%9, c.getValeur());
                    }
                    else //sinon c'est une case bloquante et on lui met la valeur présente dans td[i]
                    {
                        int valeurSplit =  Integer.parseInt(td[i]);
                        c = new CaseBloquante(valeurSplit);
                        jeu[j][i].setCase(j, c);
                        jeu2[j][i].setValeur(c.getValeur());
                        lignes[j].setCase(i, c);
                        colonnes[j].setCase(i, c);
                        //regions[j][i].setCaseRegion(j, i, c);
                        this.setCaseNumRegion(j, i, c.getValeur());
                       // regions[j/3][i/3].setCaseValeurRegion(j%9, i%9, c.getValeur());

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
    

    
     //regarde si la case est en conflit avec sa ligne, col et region
    public boolean estConflit(Case c, int ligne, int col)
    {
        int i,x,y;
        
        // On vérifie d'abord pour la ligne
        for(i=0;i<9;i++) 
          {
            if(jeu2[ligne][i].valeur!=0) 
              {
                if(i!=col)
                  {
                    if(jeu2[ligne][i].valeur==c.getValeur())
                      return true;
                  }
              }
          }

        // On verifie maintenant pour la colonne

        for(i=0;i<9;i++) 
          {
            if(jeu2[i][col].valeur!=0)
              {
                if(i!=ligne)
                  {
                    if(jeu2[i][col].valeur==c.getValeur())
                      return true;
                  }
              }
          }

        // On verifie maintenant la région

        x=(ligne/3)*3;
        y=(col/3)*3;

        for(i=y;i<y+3;i++)  
          {
            if(jeu2[x][i].valeur!=0)
              {
                if(i!=col || x!=ligne)
                  {
                    if(jeu2[x][i].valeur==c.getValeur())
                      return true;
                  }
              }
          }

        for(i=y;i<y+3;i++)
          {
            if(jeu2[x+1][i].valeur!=0)
              {
                if(i!=col || x+1!=ligne)
                  {
                    if(jeu2[x+1][i].valeur==c.getValeur())
                      return true;
                  }
              }
          }

        for(i=y;i<y+3;i++)
          {
            if(jeu2[x+2][i].valeur!=0)
              {
                if(i!=col || x+2!=ligne)
                  {
                    if(jeu2[x+2][i].valeur==c.getValeur())
                      return true;
                  }
              } 
          }

        return false;
        
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
    

    public Groupe getGroupeRegion(int i, int j)
    {
        return regions[i][j];
    }
    
    //retourne la region dans laquelle se trouve la case de coord i j
    public Groupe getRegionCase(int i, int j)
    {
        return regions[(int)(i/3)][(int)(j/3)];
    }
    
    public void setRegion(Groupe[][] g)
    {
        regions = g;
    }
    
    public int getCaseValeurRegion(int i, int j)
    {
        return regions[(int)(i/3)][(int)(j/3)].getCaseValeurRegion(i%3, j%3);
    }
    
    public void setCaseNumRegion(int i, int j, int val)
    {
        regions[(int)(i/3)][(int)(j/3)].setCaseValeurRegion(i%3, j%3, val);
    }
    
    public void setCaseRegion(int i,int j, Case c)
    {
        regions[(int)(i/3)][(int)(j/3)].setCaseRegion(i, j, c);
    }
    
    public void maj(int row, int column, char numero, String contenuFichier) throws IOException {
        // modifier le fichier .txt en remplaçant le 0 par le nombre rentré par l'utilisateur
        //System.out.println(contenuFichier.length());
        /*for(int i=0 ; i<contenuFichier.length() ; i++)
        {
            System.out.println(" !!!  "+contenuFichier.charAt(i));
        }*/
        System.out.println(" colonne envoyée dans maj     "+column+"  "+row);
        
        if(column == 0)
            column = 9;
        // on trouve le bon caractère à changer puisque les espaces et les sauts de lignes comptent comme des caractères
        System.out.println(" ligne de la modif    "+row+"   colonne de la modif   "+column);
        int numeroCaracAChanger = (row)*19+(column-1)*2;
        System.out.println("Numéro char   "+numeroCaracAChanger);
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
    
           /** Checks if num is an acceptable value for the box around row and col */
   protected boolean checkBox( int row, int col, int num )
   {
      row = (row / 3) * 3 ;
      col = (col / 3) * 3 ;

      for( int r = 0; r < 3; r++ )
         for( int c = 0; c < 3; c++ )
         if( jeu2[row+r][col+c].getValeur() == num )
            return false ;

      return true ;
   }
   
   

   public int resoudre(int col)
   {
        System.out.println("col resoudre = " + col);
        if(col > 8)
        {
            System.out.println("il n y a plus rien a resoudre");
            return 0;
        }
        else
        {   //resoudre une ligne
            if( (lignes[0].getCaseValeurLigne(col) != 0))
            {
                System.out.println("col if pas 0 = " + col);
                return resoudre(col+1) ;
            }
            else if(lignes[0].getCaseValeurLigne(col) == 0) //case vide
            {
                for( int num = 1; num < 10; ++num )
                {
                    System.out.println("num = " + num);
                    lignes[0].ligne[col].setValeur(num);  //on met la ligne avec le chiffre
                    if(!(lignes[0].estEnConflitLigne(lignes[0].getCaseLigne(col)))) //on teste si c'est en conflit avec la ligne
                    {
                        
                        //lignes[0].ligne[col].setValeur(num);  //on laisse le chiffre
                        System.out.println("col if 0 = " + col);
                        return resoudre(col+1) ;
                    }
                    else //si un conflit
                    {
                        lignes[0].ligne[col].setValeur(0); //on enleve le chiffre
                    }
                 }
                // lignes[0].ligne[col].setValeur(0); //pas de valeur trouvé, on le remet à 0 pour refaire
             }
             
             for(int i=0; i<9; i++) //affichage de la ligne 
             {
                System.out.print(lignes[0].ligne[i].getValeur() + " ");
                if(i==8) System.out.println("");
             }
             //return 0;
        }
        return 0;
   }
   
   public int suivant(int col)
   {
        if( col < 8 )
        {
            System.out.println("suivant");
            System.out.println("col suivant = " + col);
            resoudre(col+1) ;
        }
        return 0;
   }

   
   
   
   public int resoudre(int row, int col)
   {
       System.out.println("row resoudre = " + row);
        System.out.println("col resoudre = " + col);
        
        if(col >= 8 && row >= 8)
        {
            System.out.println("il n y a plus rien a resoudre");
            return 0;
        }
        else
        {
             if( lignes[row].getCaseValeurLigne(col) != 0 )
             {
                 System.out.println("row if pas 0 = " + row);
                 System.out.println("col if pas 0 = " + col);
                 return suivant(row,col) ;
             }
             else if(lignes[row].getCaseValeurLigne(col) == 0) //case vide
             {
                 for( int num = 1; num < 10; ++num )
                 {
                     System.out.println("num = " + num);
                     lignes[row].ligne[col].setValeur(num);  //on met la ligne avec le chiffre
                     colonnes[row].colonne[col].setValeur(num);
                     this.setCaseNumRegion(row, col, num);
                    if( !(lignes[row].estEnConflitLigne(lignes[row].getCaseLigne(col))) && !(colonnes[row].estEnConflitColonne(colonnes[row].getCaseColonne(col))) && !(regions[row/3][col/3].estEnConflitRegion(0,0, regions[row/3][col/3].getCaseRegion(row%3, col%3)))) //on teste si c'est en conflit avec la ligne
                    {
                        //on laisse le chiffre
                        System.out.println("row if 0 = " + row);
                        System.out.println("col if 0 = " + col);
                        return suivant(row,col) ;
                    }
                    else
                    {
                        //on enleve le chiffre
                        lignes[row].ligne[col].setValeur(0); 
                        colonnes[row].colonne[col].setValeur(num);
                        this.setCaseNumRegion(row, col, 0);
                    }
                 }
                // lignes[0].ligne[col].setValeur(0); //pas de valeur trouvé, on le remet à 0 pour refaire
             }
             
        }
        
        System.out.println("Affichage des lignes");
            for(int i=0; i<9; i++)
            {
                for(int j=0; j<9; j++)
                {
                    System.out.print(lignes[i].ligne[j].getValeur() + " ");
                    if(j==8) System.out.println("");
                }
            }
            
            System.out.println("Affichage des colonnes");
            for(int i=0; i<9; i++)
            {
                for(int j=0; j<9; j++)
                {
                    System.out.print(colonnes[i].colonne[j].getValeur() + " ");
                    if(j==8) System.out.println("");
                }
            }
            
            System.out.println("Affichage des régions");
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    if(j==0) System.out.print("\n");
                    System.out.print(regions[i/3][j/3].region[i%3][j%3].getValeur() +" ");
                }
                if(i==8) System.out.print("\n");
            }
        return 0;
   }
   
   public int suivant(int row, int col)
   {
        if( col <= 8 )
        {
            System.out.println("suivant");
            System.out.println("row suivant = " + row);
            System.out.println("col suivant = " + col);
            return resoudre(row,col+1) ;

        }
        else if(col > 9)
        {
            System.out.println("ligne suivante");
            return resoudre(row+1,0) ; //on passe à la ligne suivante
        }
        return 0;
   }   
    
}