/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Package à importer afin d'utiliser l'objet File
import java.io.File;
import java.io.IOException;
/**
 *
 * @author p1607434
 */

//L'utilisateur tape un chiffre à mettre dans une case.                 FAIT
//On regarde si ce qu'il a écrit est en conflit avec la ligne, la colonne et la région.         FAIT
//on vérifie également si les autres cases de la ligne, la colonne et la région sont en conflit avec la case ajoutée
// si c'est en conflit, la(les) case(s) est(sont) en rouge.
//Fin : si tous les chiffres sont en vert, donc pas conflit, et toute la grille remplie, la grille est juste.


public class Main {
    public static void main(String[] args) throws IOException  {
         
        String[] s = new String[81];
        int cpt = 0;
        
        Jeu sudoku;
        sudoku = new Jeu();
        sudoku.init("test4.txt");
      
        sudoku.solve(0, 0); //résoudre le sudoku.
         
        System.out.println("Lignes");
        for(int i=0; i<sudoku.tabLigne.length ;i++) //ligne
        {
            //System.out.print(sudoku.tabLigne[i].getGroupeConflit());
            for(int j=0; j<sudoku.tabLigne.length ; j++) //colonne
            {
                System.out.print(sudoku.tabLigne[i].getCaseValeur(j)+" ");
                if(j==(sudoku.tabLigne.length-1)) System.out.print("\n");
                s[cpt] = Integer.toString(sudoku.tabLigne[i].getCaseValeur(j));
                cpt++;
            }
        }
        
        System.out.println("Colonnes");
        for(int i=0; i<sudoku.tabCol.length ;i++) //ligne
        {
            //System.out.print(sudoku.tabCol[i].getGroupeConflit());
            for(int j=0; j<sudoku.tabCol.length ; j++) //colonne
            {
                System.out.print(sudoku.tabCol[i].getCaseValeur(j)+" ");
                if(j==(sudoku.tabCol.length-1)) System.out.print("\n");
                
            }   
        }
        
        System.out.println("Region");
        
        for(int a=0; a<3 ;a++)
        {
            for(int b=0; b<3 ;b++)
            {
                for(int i=0; i<9 ;i++) //ligne
                {
                    System.out.print(sudoku.tabRegion[a][b].getCaseValeur(i)+" ");
                    if(i==8) System.out.print("\n");
                }
            }
        }
        
        sudoku.gagne(); 
         
        VueSudoku.initVal(s);
        VueSudoku.main(args);
    }
}
