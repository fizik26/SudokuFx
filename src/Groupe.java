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
    Case tab[] = new Case[9];
    
    
    public void ajouter(Case c)
    {
        int i = 0;
        // tant que l'on trouve pas un null.
        while (this.tab[i] != null)
            i++;
        this.tab[i] = c;

        c.setGroupe(this);
    }
    
    public boolean estEnConflit(Case c)
    {
        Case[] c2 = this.tab;
        for(int i = 0 ; i < c2.length ; i++)       
        {
            if((c != c2[i]) && (c.valeur == c2[i].valeur ))
            {
                return true;
            }
        }
        return false;
    }
}