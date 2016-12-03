/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Package à importer afin d'utiliser l'objet File
import java.io.IOException;
/**
 *
 * @author p1607434
 */
public class Main {
    public static void main(String[] args) throws IOException  {
         
      
      Jeu sudoku;
      sudoku = new Jeu();
      sudoku.init();
      
      Case c;
        c = new Case(7);
        sudoku.jeu[0][2].setCase(0, c); // on met c à la case [0][2]
      
        String[] s = new String[81];
        int cpt = 0;
        //Affichage d'un sudoku
        for(int i=0; i<sudoku.jeu.length ;i++) //ligne
        {
            for(int j=0; j<sudoku.jeu.length ; j++) //colonne
            {
                //System.out.print(sudoku.jeu[i][j].getCaseValeur(i)+" ");
                //if(j==(sudoku.jeu.length-1)) System.out.print("\n");
                s[cpt] = Integer.toString(sudoku.jeu[i][j].getCaseValeur(i));
                cpt++;
            }   
        }
        
        /*System.out.println("autre grille");
        for (Case[] jeu2 : sudoku.jeu2) //ligne
        {
            for (int j = 0; j<sudoku.jeu2.length; j++) //colonne
            {
                System.out.print(jeu2[j].getValeur() + " ");
                if(j==(sudoku.jeu2.length-1)) System.out.print("\n");
            }   
        }*/
        VueSudoku.initVal(s);
        VueSudoku.main(args);
    }
}
