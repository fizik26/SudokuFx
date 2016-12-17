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
    
    public Groupe tabLigne[];
    public Groupe tabCol[];
    public Groupe tabRegion[][];
    public String[] nombresSudoku;
    
    public Case grilleCase9x9[][];

    boolean err;
    double lastValue;


    //construction d'une grille vide
    public Jeu()
    {
        nombresSudoku = new String[81];
        tabRegion = new Groupe[3][3];
        for(int i=0;i<3;i++)
		{
            for(int j=0;j<3;j++)
            {
                tabRegion[i][j] = new Groupe();
            }
        }
            
        tabLigne = new Groupe[9];
        for(int i=0;i<tabLigne.length;i++)
	{
            tabLigne[i] = new Groupe();
        }
        
        tabCol = new Groupe[9];
        for(int i=0;i<tabCol.length;i++)
	{
            tabCol[i] = new Groupe();
        }
        
        grilleCase9x9 = new Case[9][9];
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
                for(int i=0 ; i<td.length ; i++)
                {
                    Case c = null;

                    if("0".equals(td[i])) //si c'est à 0, c'est une case non bloquante 
                    {
                        c = new CaseNonBloquante(0);
                    }
                    else //sinon c'est une case bloquante et on lui met la valeur présente dans td[i]
                    {
                        int valeurSplit =  Integer.parseInt(td[i]); 
                        c = new CaseBloquante(valeurSplit);
                    }

                    tabLigne[j].insertionCase(c);   //on ajoute les cases aux groupes
                    tabCol[i].insertionCase(c);
                    tabRegion[(int)(j/3)][(int)(i/3)].insertionCase(c);
                    c.setGroupe(tabLigne[j],tabCol[i],tabRegion[(int)(j/3)][(int)(i/3)]); //on ajoute les groupes aux cases
                    
                }
                j=j+1;
            }
        }
        br.close();
        
        // on rempli la variable qui servira à remplir les textField de la vue
        nombresSudoku = initVals();             
        
}
  
    public void maj(int row, int column, char numero) throws IOException 
    {
        if(column == 0)
            column = 9;
        
    // **************************************************** modification fichier lorsque l'utilisateur rentre un chiffre    
        // il faut passé le contenu du fichier dans la fonction pour réalisé cela

        // on trouve le bon caractère à changer puisque les espaces et les sauts de lignes comptent comme des caractères
        /*int numeroCaracAChanger = (row)*19+(column-1)*2;
        
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
        }*/
    // **********************************************************

       
        tabLigne[row].setCaseValeur(column-1,Character.getNumericValue(numero)); // on ajoute la valeur à la case
        Case c = tabLigne[row].getCase(column-1); // c est égale à l'adresse de la case à laquelle on a ajouté une valeur
        
        if( ! tabLigne[row].estEnConflit(c) && ! tabCol[column-1].estEnConflit(c) && ! tabRegion[row/3][(column-1)/3].estEnConflit(c))
            System.out.println("On modifie bien sans conflit");
        else
        {System.out.println("Erreur, conflit !");}
        
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

    public void ajouterCase(int row, int col, int val)
    {
        this.tabLigne[row].setCaseValeur(col, val); //pas besoin de faire la meme chose pour la colonne et la région, car ça le fait déjà "automatiquement"
    }
        
    public boolean solve(int i, int j)
    {
        //si j (colonne) = 9, on passe à la ligne suivante i et on remet j à 0
        if (j == 9) 
        {
            j=0;
            if (++i == 9)
                return true;
        }
        
        if (tabLigne[i].getCaseValeur(j) != 0) //si la case a déjà une valeur au départ, on passe à la case suivante
            return solve(i,j+1);
		
	//on regarde si on peut placer une valeur
	for (int val = 1; val <= 9; ++val)
	{
            tabLigne[i].setCaseValeur(j, val);
            Case c = tabLigne[i].getCase(j);
            if( !((tabLigne[i].estEnConflit(c)) || (tabCol[j].estEnConflit(c)) || (tabRegion[i/3][j/3].estEnConflit(c))) ) //si l'un d'eux est à vrai, il y a un conflit			
            {  //si pas de conflit
                //on laisse la case telle qu'elle est et on passe au suivant
                if(solve(i,j+1))
                return true;	
            }
        }
        tabLigne[i].setCaseValeur(j, 0);
	return false;
    }
    
    
    //on regarde le champ conflit de chaque case, si tout est à faux, il n'y a pas de conflit donc on a gagné
    public boolean gagne()
    {
        boolean gagne = tabLigne[0].getCaseConflit(0); //on le met égal au premier bool conflit du sudoku
        for(int i=0; i<this.tabLigne.length ;i++) //ligne
        {
            for(int j=0; j<this.tabLigne.length ; j++) //colonne
            {
                if(tabLigne[i].getCaseValeur(j) == 0) //si une case est vide, donc à 0
                {
                    System.out.println("Gagné ? = false !!!");
                    return false;
                }
                gagne = gagne && tabLigne[i].getCaseConflit(j); 
            }
        }
        
        System.out.println("Gagné ? = " + !gagne + " !!!");
        return !gagne; //si tous les conflits sont à faux, on a gagné
    }
    
    public boolean EstEnConflitLigneColonneRegion(Case c)
    {
        if(c.getGroupe()[0].estEnConflit(c) || c.getGroupe()[0].estEnConflit(c) || c.getGroupe()[0].estEnConflit(c))
        {
            return true;
        }
        return false;
    }
    
     public void sauverGrille(File fichierCourant)
    {
	String nomFic = new String("");
	try
	{
            nomFic = fichierCourant.getAbsolutePath();
            try
            {
		FileWriter fichier = new FileWriter(nomFic);
                for(int i=0;i<9;i++)
		{
                    for(int j=0;j<9;j++)
                    {
			fichier.write(this.tabLigne[i].getCaseValeur(j)+"");
                        if(j<8) fichier.write(" "); //pour avoir un espace entre chaque chiffre
        		if(j==8) fichier.write(13); //pour avoir un saut de ligne
                    }
			fichier.write("\n");		
		}
		fichier.close();			
            }	
            catch(IOException e)
            {
                System.out.println("impossible d'ecrire dans le fichier");
            }
	}
	catch(NullPointerException e)
	{
            e.printStackTrace();
	}
    }
     
    public String[] initVals()
    {
        String[] s = new String[81];
        int cpt = 0;
        
        for (Groupe tabLigne1 : tabLigne) //ligne
        {
            for (int j = 0; j<tabLigne.length; j++) //colonne
            {
                s[cpt] = Integer.toString(tabLigne1.getCaseValeur(j));
                cpt++;
            }
        }
        
        return s;
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }
}