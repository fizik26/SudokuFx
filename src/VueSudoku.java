/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// importations pour l'affichage
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;

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
        int row = 0;
                
        affichage = new Text("");
        affichage.setFont(Font.font ("Verdana", 20));
        affichage.setFill(Color.RED);
        border.setTop(affichage);

        // création des bouton et placement dans la grille
        for (int i=0 ; i<81 ; i++) {        
            // texte qui sera ajouté dans le sudoku
            final TextField t = new TextField();
            
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
                row++;
            }

            final int c = column;
            final int r = row;
            t.setOnKeyPressed (new EventHandler<KeyEvent>(){
                public void handle(KeyEvent ke){
                    System.out.print(ke.getText());
                    
                    // renvoie à la mise à jour du modèle le numéro de la ligne et le numéro de la colonne
                    m.maj(r, c);
                }
            });
        }
        
        // affichage des bordures
        gPane.setGridLinesVisible(true);
        gPane.setStyle("-fx-background-color: white; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
        
        border.setCenter(gPane);
 
        Scene scene = new Scene(border);

        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args , String[] sud) {
        VueSudoku.nombresSudoku = sud;
        launch(args);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("     On est dans VueSudoku.update()       ");
        MAJ();
    }
}
