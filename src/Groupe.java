/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p1607434
 */
public class Groupe {
    Case tab[];
    
    Case ligne[]; //une ligne
    Case colonne[]; //une colonne
    Case region[][]; //une region
    
    public Groupe()
    {
        tab = new Case[9];
        ligne = new Case[9];
        colonne = new Case[9]; 
        region = new Case[3][3];
                
        //initialisation de la ligne et la colonne
        for (int i = 0; i<9; i++)
        {
            tab[i]=new Case(0,this,false);
            ligne[i]=new Case(0,this,false);
            colonne[i]=new Case(0,this,false);
        }
        
        //initialisation de la region
        for (int i = 0; i<3; i++)
        {
            for(int j = 0; j<3; j++)
            region[i][j] = new Case(0,this,false);
        }
        
    }
    
    public void ajouter(Case c)
    {
        int i = 0;
        // tant que l'on trouve pas un null.
        while (this.tab[i] != null)
            i++;
        this.tab[i] = c;

        c.setGroupe(this);
    }
    
    public boolean estEnConflitLigne(Case c)
    {
        Case[] cLigne = this.ligne;

        for(int i = 0 ; i < cLigne.length ; i++)       
        {
            /*
            System.out.println("valeur de c = " + c.getValeur());
            System.out.println("cLigne[i].getValeur() = " + cLigne[i].getValeur());
            System.out.println("adresse cLigne[i] = " + cLigne[i]);  
            System.out.println("adresse c = " + c);           
            */
            
            if((c != cLigne[i]) && (c.getValeur() == cLigne[i].getValeur()))
            {
                c.conflit = true;
                return true;
            }
        }
        c.conflit = false;
        return false;
    }
    
    public boolean estEnConflitColonne(Case c)
    {
        Case[] cColonne = this.colonne;
        
        for(int i = 0 ; i < cColonne.length ; i++)       
        {
            if((c != cColonne[i]) && (c.getValeur() == cColonne[i].getValeur() ))
            {
                c.conflit = true;
                return true;
            }
        }
        c.conflit = false;
        return false;
    }
    
    public boolean estEnConflitRegion(int row, int col, Case c)
    {
        Case[][] cRegion = this.region; 
        //row = (row / 3) * 3 ;
        //col = (col / 3) * 3 ;
        
        for(int i = 0 ; i < cRegion.length ; i++)        //ligne
        {
            for(int j = 0 ; j < cRegion[i].length ; j++)    //colonne
            {
                System.out.println("valeur de c = " + c.getValeur());
                System.out.println("cRegion[i][j] = " + cRegion[i][j].getValeur());
                //System.out.println("adresse cRegion[i][j] = " + cRegion[i][j]);  
                //System.out.println("adresse c = " + c);           
                
                if((c != cRegion[i][j]) && (c.getValeur() == cRegion[i][j].getValeur() ))
                {
                    c.conflit = true;
                    return true;
                }
            }
        }
        c.conflit = false;
        return false;
    }
    
    
    
    //accesseur : retourne la case de coordonée i
    public Case getCase(int i)
    {
        return tab[i];
    }
    
    //retourne la case de coordonnée i dans ligne[]
    public Case getCaseLigne(int i)
    {
        return ligne[i];
    }
    
    public Case getCaseColonne(int i)
    {
        return colonne[i];
    }
    
    public Case getCaseRegion(int i, int j)
    {
        return region[i][j];
    }


    //setter
    public void setCase(int i, Case c)
    {
        tab[i].setValeur(c.getValeur());
        ligne[i].setValeur(c.getValeur());
        colonne[i].setValeur(c.getValeur());
        
    }
    
    public void setCaseRegion(int i, int j, Case c)
    {
        region[i][j].setValeur(c.getValeur());
    }
    
    //retourne la valeur de la case i, j
    public int getCaseValeur(int i)
    {
        return tab[i].getValeur();
    }
    
    public int getCaseValeurLigne(int i)
    {
        return ligne[i].getValeur();
    }
    
    public int getCaseValeurColonne(int i)
    {
        return colonne[i].getValeur();
    }
    
    public int getCaseValeurRegion(int i, int j)
    {
        return region[i][j].getValeur();
    }
    
    
    //setter : met la valeur c à la case de coord i
    public void setCaseValeur(int i, int val)
    {
        tab[i].setValeur(val);
        ligne[i].setValeur(val);
        colonne[i].setValeur(val);
    }
    
    public void setCaseValeurRegion(int i, int j, int val)
    {
        region[i][j].setValeur(val);
    }
}
