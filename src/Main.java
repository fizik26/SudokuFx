/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Package à importer afin d'utiliser l'objet File
import java.io.File;
/**
 *
 * @author p1607434
 */
public class Main {
    public static void main(String[] args) {
        //Création de l'objet File
        //File f = new File("\\\\teraetu\\homeetu\\b\\p1607434\\Mes documents\\NetBeansProjects\\SudokuFx\\src\\test.txt");
        File f = new File("test.txt");
        System.out.println("p");
        System.out.println(f.length());
    }
}
