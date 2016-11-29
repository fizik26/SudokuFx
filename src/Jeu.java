/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.String;
/**
 *
 * @author p1607434
 */
public class Jeu {

    public void init(String data)
    {
        Groupe ligne[] = new Groupe[9];
        Groupe colonne[] = new Groupe[9];
        Groupe carre[][] = new Groupe[3][3];
        
        String[] td = data.split(" ");
        
        for(int i=0 ; i<td.length ; i++)
        {
            Case c;
            
            if(td[i] == "0")
                c = new CaseNonBloquante(0);
            else
            {
                int valeurSplit =  Integer.parseInt(td[i]);
                c = new CaseBloquante(valeurSplit);
            }
            
            int numL = i/9; // numéro de la ligne
            int numC = i%9; // numéro de la colonne
        }
    }
}
