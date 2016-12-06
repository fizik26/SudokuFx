/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author p1607434
 */
public class CaseBloquante extends Case {
    public CaseBloquante(int _valeur) {
        
        this.valeur = _valeur;
       // this.tab = new Groupe();
       // this.conflit = false;
    }
    
    public CaseBloquante getCaseBloquante()
    {
        return this;
    }
    
    public void setCaseBloquante(int n)
    {
        valeur = n;
    }
}
