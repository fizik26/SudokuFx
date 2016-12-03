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
      sudoku.init("test.txt");
      
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
        
        //Case c2;
        //c2 = new Case(1);
        //sudoku.jeu2[0][2].setValeur(1);
        /*System.out.println("autre grille");
        for(int i=0; i<sudoku.jeu2.length ;i++) //ligne
        {
          for(int j=0; j<sudoku.jeu2.length ; j++) //colonne
          {
              System.out.print(sudoku.jeu2[i][j].getValeur()+" ");
              if(j==(sudoku.jeu2.length-1)) System.out.print("\n");
              //s[cpt] = Integer.toString(sudoku.jeu2[i][j].getValeur());
              //cpt++;
          }*/
        
        /*Case c3;
        c3 = new Case(5);
        sudoku.lignes[0].setCase(2, c3);

        sudoku.lignes[1].setCase(2, c3);
*/
       /*
       System.out.println("TestLigne et Colonne");
       for(int x=0; x<sudoku.lignes.length ;x++) //ligne
        {
          for(int j=0; j<sudoku.lignes.length ; j++) //colonne
          {
              System.out.print(sudoku.lignes[x].getCaseValeurLigne(j)+" ");
              //System.out.print(sudoku.colonnes[x].getCaseValeurColonne(j)+" ");
            
              if(j==(sudoku.lignes.length-1)) System.out.print("\n");
              //s[cpt] = Integer.toString(sudoku.colonnes[i][j].getValeurColonne());
              //cpt++;
          }   
        }
       
       */
      
         
       /*
       System.out.println("Test Region");
       
       sudoku.setCaseNumRegion(3, 8, 8);
       Case c4;
       c4 = new Case(5);
       sudoku.setCaseRegion(0, 0, c4); //fonctionne pas si >=3
       for(int i = 0; i < 9; i++)
       {
           for(int j = 0; j < 9; j++)
           {
               if(j==0) System.out.print("\n");
               System.out.print(sudoku.getCaseValeurRegion(i, j)+" ");
           }
           if(i==8) System.out.print("\n");
       }
       
       
       //test est en conflit region
       
       System.out.println("Case d'une région"); // changer les coords apres "regions"
        for(int i = 0; i<3; i++)
        {
            for(int j = 0; j<3; j++)
            {
                System.out.print(sudoku.regions[2][2].getCaseValeurRegion(i, j) + " ");
                if(j==2)  System.out.println("");
            }
        }
        

        //la région [2][2] avec la case à la coord [2,2]dans la region 
            if(sudoku.regions[2][2].estEnConflitRegion(0,1,sudoku.regions[2][2].getCaseRegion(0,1)))
            {
                 System.out.println("je suis en conflit");
            }
            else 
            {
                System.out.println("je ne suis pas en conflit");
            }
       
     
       */
       
       
       /*
       for(int x=0; x<sudoku.regions.length ;x++) //ligne
        {
          for(int j=0; j<sudoku.regions[x].length ; j++) //colonne
          {
            System.out.print(sudoku.regions[x][j].getCaseValeurRegion(x, j)+" ");
              if(j==(3-1)) System.out.print("\n");
              //s[cpt] = Integer.toString(sudoku.jeu2[i][j].getValeurRegion());
              //cpt++;
          }   
      }
       */
       
       /*
       //test est en conflit ligne
       for(int i = 0; i<9; i++)
       {
            System.out.print(sudoku.lignes[0].getCaseValeur(i)+"");

       }
       
       if(sudoku.lignes[0].estEnConflitLigne(sudoku.lignes[0].getCaseLigne(2)))
       {
            System.out.println("je suis en conflit");
       }
       else 
       {
           System.out.println("je ne suis pas en conflit");
       }
       */
      
      
        //sudoku.resoudre(0);
         sudoku.resoudre(0,0);
         
        System.out.println("Apres resoudre ligne 0");
       for(int x=0; x<sudoku.lignes.length ;x++) //ligne
        {
          for(int j=0; j<sudoku.lignes.length ; j++) //colonne
          {
              System.out.print(sudoku.lignes[x].getCaseValeurLigne(j)+" ");
              //System.out.print(sudoku.colonnes[x].getCaseValeurColonne(j)+" ");
            
              if(j==(sudoku.lignes.length-1)) System.out.print("\n");
              //s[cpt] = Integer.toString(sudoku.jeu2[i][j].getValeurColonne());
              //cpt++;
          }   
        } 
         
         
        VueSudoku.initVal(s);
        VueSudoku.main(args);
    }
}
