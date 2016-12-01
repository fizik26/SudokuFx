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
    Groupe tab[];
    boolean conflit;
    
    public Case(int _valeur)
    {
        this.valeur = _valeur;
    }
    
    public Case()
    {
        conflit = false;
        valeur = 0;
        tab = new Groupe[3];
    }
    
    public Case(int _valeur , Groupe[] tab, boolean conflit)
    {
        this.valeur = _valeur;
        this.tab = tab;
        this.conflit = false;
    }
    
    public void maj(int val)
    {
        this.conflit = false;

        for (int i = 0; i < tab.length; ++i) {
            if (tab[i].estEnConflit(this)){
                conflit = true;
            }
        }
    }
    
    // récupération du groupe de la case
    public Groupe[] getGroupe()
    {
        return tab;
    }

    // donne un groupe à la case
    public void setGroupe(Groupe groupe)
    {
        int i = 0;

        while (this.tab[i] != null)
            i++;
        this.tab[i] = groupe;
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
}
