/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// importations pour l'affichage
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import java.util.regex.Pattern;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

/**
 *
 * @author p1607434
 */
public class VueSudoku extends Application {
    
            
    // création du modèle
    Modele m;
    // affiche la saisie et le résultat
    Text affichage;
        
    @Override
    public void start(Stage primaryStage){

        // initialisation du modèle que l'on souhaite utiliser
        m = new Modele();
        
        // gestion du placement (permet de placer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        // permet de placer les différents boutons dans une grille
        GridPane gPane = new GridPane();
        
        // nombre de colonne, utile pour la disposition dans la grille
        int column = 0;
        
        // nom de ligne, utile pour la disposition dans la grille
        int row = 0;
        
        
        affichage = new Text("");
        affichage.setFont(Font.font ("Verdana", 20));
        affichage.setFill(Color.RED);
        border.setTop(affichage);

        
        // création des bouton et placement dans la grille
        //for (String s : new String[]{"5", "3", " ", " ", "7", " ", " ", " ", " ", "6", " ", " ", "1", "9", "5", " ", " ", " ", "6", " ", " ", "1", "9", "5", " ", " ", " ", "6", " ", " ", "1", "9", "5", " ", " ", " ", "6", " ", " ", "1", "9", "5", " ", " ", " ", "6", " ", " ", "1", "9", "5", " ", " ", " ","6", " ", " ", "1", "9", "5", " ", " ", " ", "6", " ", " ", "1", "9", "5", " ", " ", " ", "6", " ", " ", "1", "9", "5", " ", " ", " "}) {
        for (int i=0 ; i<81 ; i++) {
            //final Text t = new Text(s);
            
            /* ************************* */
            // test boutons
            Button button = new Button("7");
            
            /* ****************************  */
            
            
            
            // texte qui sera ajouté dans le sudoku
            //final Text t = new Text(" 0 ");
            final TextField t = new TextField();
            t.setPromptText(" ");
            
            // pour TextField
            t.setPrefWidth(50);
            
            
            // largeur des cases, que pour le type Text
            //t.setWrappingWidth(30);
            
            t.setFont(Font.font ("Verdana", 20));
            
            // que pour le type TEXT
            //t.setTextAlignment(TextAlignment.CENTER);
            
            // ajout des éléments dans le grid
            gPane.add(button, column++, row);
            
            // retour à la ligne dès qu'il y a 9 valeurs dans une ligne
            if (column > 8) {
                column = 0;
                row++;
            }
            
            // un controleur (EventHandler) par bouton, dès que l'utilisateur clique sur une case, ça déclenche la fonction suivante
            t.setOnMouseClicked((MouseEvent event) -> {
                //affichage.setText(affichage.getText() + t.getText());
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
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
