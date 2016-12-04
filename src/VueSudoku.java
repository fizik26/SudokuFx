/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// importations pour l'affichage
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author p1607434
 */
public class VueSudoku extends Application implements Observer{
    
    public static String[] nombresSudoku;       
    // création du modèle
    Jeu m;
    // affiche la saisie et le résultat
    Text affichage;
        
    private GridPane gPane;
    
    private void MAJ() {
        ObservableList lst = gPane.getChildren();
        System.out.println("     On est dans VueSudoku.MAJ()       ");
    }
            
            
    @Override
    public void start(Stage primaryStage){

        // initialisation du modèle que l'on souhaite utiliser
        m = new Jeu();
        m.addObserver(this);
        // gestion du placement (permet de placer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        // permet de placer les différents boutons dans une grille
        gPane = new GridPane();

        // nombre de colonne, utile pour la disposition dans la grille
        int column = 0;
        
        // nom de ligne, utile pour la disposition dans la grille
        int row;
        row = 0;
                
        affichage = new Text("");
        affichage.setFont(Font.font ("Verdana", 20));
        affichage.setFill(Color.RED);
        border.setTop(affichage);
        /*HBox hbox = new HBox();
        Button boutonSauvegarder = new Button("Sauvegarder");
        hbox.setAlignment(Pos.LEFT);
        hbox.getChildren().add(boutonSauvegarder);
        border.setBottom(hbox);*/

        // création des bouton et placement dans la grille
        for (int i=0 ; i<81 ; i++) {        
            // texte qui sera ajouté dans le sudoku
            TextField t = new TextField();
            //final Text t = new Text();
            
            // si il y a un 0 , on affiche une case vide
            if(nombresSudoku[i].equals("0"))
                nombresSudoku[i] = " ";
            
            t.setPromptText(nombresSudoku[i]);
            // pour TextField
            t.setPrefWidth(50);
            
            t.setFont(Font.font ("Verdana", 20));
            
            // ajout des éléments dans le grid
            gPane.add(t, column++, row);
            
            // retour à la ligne dès qu'il y a 9 valeurs dans une ligne
            if (column > 8) {
                column = 0;
            }

            final int c = column;
            final int r = row;
            t.setOnKeyTyped ((KeyEvent ke) -> {
                // affichage de la touche sélectionnée
                //System.out.print(" iiiiiii " +ke.getCharacter());
                
                // test si le charactere entré est un int compris entre 1 et 9
                String valChar = ke.getCharacter();

                if("1".equals(valChar) || "2".equals(valChar) || "3".equals(valChar) || "4".equals(valChar) || "5".equals(valChar) || "6".equals(valChar) || "7".equals(valChar) || "8".equals(valChar) || "9".equals(valChar))
                {
                System.out.print(" iiiiiii " +ke.getCharacter());
                    try {
                        // renvoie à la mise à jour du modèle le numéro de la ligne et le numéro de la colonne
                        File file = new File("test.txt");
                        // on place le contenu du fichier texte dans contenuFichier
                        String contenuFichier = m.loadFile(file);
                        m.maj(r, c, ke.getCharacter().charAt(0) , contenuFichier);
                    } catch (IOException ex) {
                        Logger.getLogger(VueSudoku.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            if(column == 0)
            {
                row++;
            }
        }
        
        

        // affichage des bordures
        gPane.setGridLinesVisible(true);
        gPane.setStyle("-fx-background-color: white; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
        
        border.setCenter(gPane);
 
        Scene scene = new Scene(border , Color.WHITE);

        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void initVal(String[] sud)
    {
        VueSudoku.nombresSudoku = sud;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("     On est dans VueSudoku.update()       ");
        MAJ();
    }
}
