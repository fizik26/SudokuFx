/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p1607434
 */
public class Case {
    int valeur;
    Groupe tabGroupe[]; //pour savoir la case appartient à quel groupe (groupe = ligne + colonne + région)
    boolean conflit;
    
    public Case(int _valeur)
    {
        this.valeur = _valeur;
        conflit = false;
        
        tabGroupe = new Groupe[3]; //[0] : ligne, [1] : colonne, [2] : région 
    }
    
    public Case()
    {
        conflit = false;
        valeur = 0;
        tabGroupe = new Groupe[3]; //[0] : ligne, [1] : colonne, [2] : région 
    }
    
    //vérifie si la case est en conflit avec sa ligne, sa colonne et sa région
    public boolean verifieConflitCase()
    {
        return (this.tabGroupe[0].estEnConflit(this)) && (this.tabGroupe[1].estEnConflit(this)) && (this.tabGroupe[2].estEnConflit(this));
    }
    
    /*public Case(int _valeur , Groupe tab, boolean conflit)
    {
        this.valeur = _valeur;
        this.tab = tab;
        this.conflit = false;
    }*/
    
    /*public void maj(int val)
    {
        this.conflit = false;

        for (int i = 0; i < tab.length; ++i) {
            if (tab[i].estEnConflit(this)){
                conflit = true;
            }
        }
    }*/
    
    // récupération du groupe de la case
    public Groupe[] getGroupe()
    {
        return tabGroupe;
    }

    // donne un groupe à la case
    public void setGroupe(Groupe tabLigne, Groupe tabColonne, Groupe tabRegion )
    {
        this.tabGroupe[0] = tabLigne;
        this.tabGroupe[1] = tabColonne;
        this.tabGroupe[2] = tabRegion;
        
    }
    
    //accesseur : retourne la valeur de la case
    public int getValeur()
    {
        return valeur;
    }
    
    //setter : met la case à la valeur n
    public void setValeur(int n)
    {
        valeur = n;
    }
    
    public boolean getConflit()
    {
        return conflit;
    }
    
    
    public void setConflit(boolean conflit)
    {
        this.conflit = conflit;
    }
}
