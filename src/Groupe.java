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
    Case tab[]; //une ligne
    boolean conflitGroupe;
    
    public Groupe()
    {
        //initialisation de la ligne et la colonne et la région
        tab = new Case[9];
        conflitGroupe = false; //il faudra vérifier à chaque fois qu'un groupe est juste ou pas, meme s'il est tout rempli dès le départ
    }
    
    //insere une case tant que c'est null
    public void insertionCase(Case c)
    {
        int i=0;
        while(tab[i] != null)
        {
            i++;
        }
        tab[i] = c;
            
    }
    
    public boolean estEnConflit(Case c)
    {
        Case[] cTab = this.tab;
        //System.out.println("valeur de c = " + c.getValeur());
        /*System.out.print("Tableau de valeurs du groupe ");
        for(int i = 0 ; i < cTab.length ; i++)       
        {
                System.out.print(cTab[i].getValeur() + " ");
        }
        System.out.println("");*/
        
        for(int i = 0 ; i < cTab.length ; i++)       
        {
            if((c != cTab[i]) && (c.getValeur() == cTab[i].getValeur()))
            {
                c.setConflit(true);
                this.conflitGroupe = true;
                return true;
            }
        }
        c.setConflit(false);
        this.conflitGroupe = false;
        return false;
    }
    
   
    
    /**************************** GET et SET ***********************************/
    
    
    public Case getCase(int i)
    {
        return tab[i];
    }
    
    //retourne la case de coordonnée i dans ligne[]
   

    public void setCaseLigne(int i, Case c)
    {
        tab[i].setValeur(c.getValeur());
    }

    
    //retourne la valeur de la case i, j
    public int getCaseValeur(int i)
    {
        return tab[i].getValeur();
    }

    
    //setter : met la valeur c à la case de coord i
    public void setCaseValeur(int i, int val)
    {
        tab[i].setValeur(val);
    }
    
    public boolean getCaseConflit(int i)
    {
        return tab[i].getConflit();
    }
    
    public void setCaseConflit(int i, boolean conflit)
    {
        tab[i].setConflit(conflit);
    }
    
    public boolean getGroupeConflit()
    {
        return this.conflitGroupe;
    }
    
    public void setCaseConflit(boolean conflit)
    {
        this.conflitGroupe = conflit;
    }
}
