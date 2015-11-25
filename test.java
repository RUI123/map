/*
 * NOTE: 
 * copy IniMap.sql in to project root folder since it's the current running directory.
 * create an empty database (in this example, database name is game) before run.
 * the map table will be created at run time.
 * the ScriptRunner.java and Table.java are used to read sql scripts. 
 ***issue: looks like it can't handle loop or comments
 *
 * What this does now: 
 * initilize layout and place map blocks.
 * initilize map table in database.
 * What needs to be done
 * dice randomly generates number generater) to select block (row) in map table.
 * put indicater (like flag or color change) on selected block.
 * assign block to the player who first steps on it. 
 * 
 */

package maptest;

/**
 *
 * @author xhf
 */
import java.io.*;
import java.sql.*;
import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MapTest extends Application {
  // Statement for executing queries
  private Statement stmt;
  /*
  private TextField tfSSN = new TextField();
  private TextField tfCourseId = new TextField();
  private Label lblStatus = new Label();
  */
  final int blockNum=40;
  private GridPane[] blockArray; 
  private Label turnLabel;// display who's turn is in current round
  private Label diceResult;//display result of dice rolling
  private Button rollDice;
  
  private int location1=0;
  private int location2=0;
  private int locationRepeat=0;
  public static int diceNum;
  private boolean turn;//indicate which player's turn
  
  
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Initialize database connection and create a Statement object
    //initializeDB();
   
      //initialize variables
      Button b1;
      BorderPane borderPane = new BorderPane();
      
      BorderPane p1=new BorderPane (b1=new Button("kk"));
      
     blockArray = new GridPane[blockNum];
      borderPane.setCenter(p1);
    //Initialize user interface   
   
  b1.setOnAction(e->{
        
    
    borderPane.getChildren().remove(p1);
    borderPane.setTop(getTop());
    borderPane.setRight(getRight());
    borderPane.setBottom(getBottom());
    borderPane.setLeft(getLeft());
    if (location1==location2)
           blockArray[location2%30].setStyle("-fx-background-color: #33cc33;");
    borderPane.setCenter(getCenter());
    borderPane.setStyle("-fx-background-color: #FFFFFF;");
    
    });
    
    //btEnlarge.setOnAction(new EnlargeHandler());
   //
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane,1000,700);
    primaryStage.setTitle("Game"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage   
    
    location1 = 0;
    location2=0;
    diceNum=0;
    turn = false;
  }
  
  private HBox getTop(){
      HBox hBox = new HBox();
      for (int i=0;i<10;i++){
          blockArray[i] = new GridPane();
          blockArray[i].setPadding(new Insets(10, 10, 10, 10));
          blockArray[i].setPrefSize(100,100);
          blockArray[i].setStyle("-fx-border-color: black");
          blockArray[i].add(new Label("Block ID"+ (i+1)), 0, 0);
          blockArray[i].add(new Label("Building ID:"), 0, 1);
          blockArray[i].add(new Label("Land Type:"), 0, 2);
          hBox.getChildren().add(blockArray[i]);
      }
      return hBox;
  }
  
   private HBox getBottom(){
      HBox hBox = new HBox();
      for (int i=24;i>14;i--){
          blockArray[i] = new GridPane();
          blockArray[i].setPadding(new Insets(10, 10, 10, 10));
          blockArray[i].setPrefSize(100,100);
          blockArray[i].setStyle("-fx-border-color: black");
          blockArray[i].add(new Label("Block ID"+ (i+1)), 0, 0);
          blockArray[i].add(new Label("Building ID:"), 0, 1);
          blockArray[i].add(new Label("Land Type:"), 0, 2);
          hBox.getChildren().add(blockArray[i]);
      }
      return hBox;
  }
  
  private VBox getRight(){
      VBox vBox = new VBox();
      for (int i=10;i<15;i++){
          blockArray[i] = new GridPane();
          blockArray[i].setPadding(new Insets(10, 10, 10, 10));
          blockArray[i].setPrefSize(100,100);
          blockArray[i].setStyle("-fx-border-color: black");
          blockArray[i].add(new Label("Block ID"+ (i+1)), 0, 0);
          blockArray[i].add(new Label("Building ID:"), 0, 1);
          blockArray[i].add(new Label("Land Type:"), 0, 2);
          vBox.getChildren().add(blockArray[i]);
      }
      return vBox;
  }
  
  private VBox getLeft(){
      VBox vBox = new VBox();
      for (int i=29;i>24;i--){
          blockArray[i] = new GridPane();
          blockArray[i].setPadding(new Insets(10, 10, 10, 10));
          blockArray[i].setPrefSize(100,100);
          blockArray[i].setStyle("-fx-border-color: black");
          blockArray[i].add(new Label("Block ID"+ (i+1)), 0, 0);
          blockArray[i].add(new Label("Building ID:"), 0, 1);
          blockArray[i].add(new Label("Land Type:"), 0, 2);
          vBox.getChildren().add(blockArray[i]);
      }
      return vBox;
  }
  
  private BorderPane getCenter(){
      BorderPane pane = new BorderPane();
      pane.setCenter(getDice());
      return pane;
  }
  
  private GridPane getDice(){
      GridPane pane= new GridPane();
      turnLabel = new Label("Player's turn");
      diceResult = new Label("default");
      rollDice = new Button("Roll Dice");
      pane.add(turnLabel, 0, 0, 2,1);
      pane.add(rollDice, 0, 1, 1,1);
      pane.add(diceResult, 1, 1, 1,1);
    //  try{
      rollDice.setOnAction(e -> {
              
              roll();}
      );
   //   }
     // catch(SQLException e){e.printStackTrace();}
    //  catch(ClassNotFoundException e){e.printStackTrace();}
      
      return pane;
  }
  
   /*private void initializeDB() {
    try {
      // Load the JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");
     
      // Establish a connection
      Connection connection = DriverManager.getConnection
        ("jdbc:mysql://localhost/game", "root", "bhcc1234"); 
    //sub with your own database name,  username and password

      System.out.println("Database connected");
      
      //Call ScriptRunner to run IniMap.sql

      String aSQLScriptFilePath = "IniMap.sql";

      System.out.println("Script run completed");

      // Create a statement
      stmt = connection.createStatement();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }*/
  
   public static void main(String[] args) {
    launch(args);
  }
   
   private int roll() { 
       //roll dice
       diceNum=(int)(Math.random()*6+1);
       
       diceResult.setText(Integer.toString(diceNum));
   
       if (turn==false){      
           
       //update location & display on map
            blockArray[location1%30].setStyle("-fx-background-color: #FFFFFF;");
            if (location1==location2)
           {
         
           blockArray[location2%30].setStyle("-fx-background-color: #00FFFF;");
           }
            location1=location1+diceNum;
            blockArray[location1 %30].setStyle("-fx-background-color: #FFFF00;");
            
            turn=true;
            
       }     
       else{
            blockArray[location2%30].setStyle("-fx-background-color: #FFFFFF;");
             if (location1==location2)
           {
           
           blockArray[location2%30].setStyle("-fx-background-color: #FFFF00;");
           }
            location2=location2+diceNum;
            blockArray[location2 %30].setStyle("-fx-background-color: #00FFFF;");
            turn=false;

       }
       if (location1==location2)
           {
        
           blockArray[location2%30].setStyle("-fx-background-color: #33cc33;");
           
      
       }
       
       return diceNum ;
       
   }
   
   
}
