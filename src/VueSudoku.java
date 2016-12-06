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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

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
        
        BorderPane border = new BorderPane();
        
        // création de la box qui contiendra les boutons
        HBox boxMenu = new HBox();

        boxMenu.setAlignment(Pos.TOP_LEFT);        
        
        Button buttonCharger = new Button("Charger un sudoku");
        Button buttonSauvegarder = new Button("Sauvegarder la partie");
        Button buttonResoudre = new Button("Résoudre le sudoku");
        
        boxMenu.getChildren().add(buttonCharger);
        boxMenu.getChildren().add(buttonSauvegarder);
        boxMenu.getChildren().add(buttonResoudre);
                
        
        // permet de placer les différentes cases dans une grille
        gPane = new GridPane();

        // nombre de colonne, utile pour la disposition dans la grille
        int column = 0;
        
        // nom de ligne, utile pour la disposition dans la grille
        int row;
        row = 0;
        
        // positionnement de la box contenant le menu
        border.setTop(boxMenu);

        // création des bouton et placement dans la grille
        for (int i=0 ; i<81 ; i++) {        
            // texte qui sera ajouté dans le sudoku
            TextField t = new TextField();
            //final Text t = new Text();
            
            // si il y a un 0 , on affiche une case vide
            if(nombresSudoku[i].equals("0"))
                nombresSudoku[i] = " ";
            else
                t.setDisable(true);
            
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
        
        
                
        // ****************   évènements sur les boutons 
        
            // évènement bouton charger
            buttonCharger.setOnAction((ActionEvent e) -> {
                System.out.println("Accepted");
                File test = ouvrirFichier(scene);
                try {
                    m.init(test.getAbsolutePath());
                    System.out.println("OUIIIIIIIIIIIIIIIII");
                } catch (IOException ex) {
                    Logger.getLogger(VueSudoku.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            
            
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
    
    public File ouvrirFichier(Scene scene)
    {
        FileChooser fileChooser	= new FileChooser();
        fileChooser.setTitle("FileChooserExample");
        File homeDir = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(homeDir);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(scene.getWindow());

        return file;
    }
}
