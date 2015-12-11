
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Map extends BorderPane{
    
    final int blockNum=30;
    private MapBlock[] blockArray= new MapBlock[blockNum]; 
  
    private PlayerBlock player1;
    private PlayerBlock player2;

    private Button rollDice;
  
    private int location1=0;
    private int location2=0;
 
    public static int diceNum;
    private boolean turn=false;//indicate which player's turn
    public int gold=50000;
    public int building=0;
    public int soldiers=200;
    
    public BottomButton control;
    
    public Map(){
        
        TextArea text=new TextArea();
        FlowPane inside=new FlowPane();
        text.setPrefColumnCount(12);
        text.setPrefRowCount(3);
        text.setEditable(false);
        
        control=new BottomButton();
        
        inside.setHgap(150);
        inside.setVgap(20);
        inside.setPadding(new Insets(20,0,20,80));
        inside.setStyle("-fx-background-image: url('image/game.jpg')");
        
        Button b1 = new Button("Enter Game");
        StartPage start=new StartPage(b1);
        setCenter(start);
        
        
        
        b1.setOnAction(e->{
            
           
            
            
            if(!start.t1.getText().isEmpty()){
                
                player1=new PlayerBlock(start.getUserId(),gold,building,soldiers);
                player2=new PlayerBlock(start.getUserId(),gold,building,soldiers);
                
                inside.getChildren().addAll(player1,player2,control,text);
                
                
                getChildren().remove(start);
                
                
                
                
                setTop(getTopNode());
                setRight(getRightNode());
                setBottom(getBottomNode());
                setLeft(getLeftNode());
                setCenter(inside);
                setStyle("-fx-background-color: #FFFFFF;");
                
                
                
            }
            
        });
        
        control.getRollButton().setOnAction(e->{
            control.diceResult.setPrefSize(30, 30);
            control.turnLabel.setPrefHeight(30);
            roll();
                });
        
    }
    private HBox getTopNode(){
      HBox hBox = new HBox();
      for (int i=0;i<10;i++){
          blockArray[i] = new MapBlock(i+1);
          hBox.getChildren().add(blockArray[i]);
      }
      return hBox;
  }
  
   private HBox getBottomNode(){
      HBox hBox = new HBox();
      for (int i=24;i>14;i--){
          blockArray[i] = new MapBlock(i+1);
          
          hBox.getChildren().add(blockArray[i]);
      }
      return hBox;
  }
  
  private VBox getRightNode(){
      VBox vBox = new VBox();
      for (int i=10;i<15;i++){
          blockArray[i] = new MapBlock(i+1);
          vBox.getChildren().add(blockArray[i]);
      }
      return vBox;
  }
  
  private VBox getLeftNode(){
      VBox vBox = new VBox();
      for (int i=29;i>24;i--){
          blockArray[i] = new MapBlock(i+1);
          vBox.getChildren().add(blockArray[i]);
      }
      return vBox;
  }
   
   
  private int roll() { 
       //roll dice
       diceNum=(int)(Math.random()*6+1);
       
       control.setDice(diceNum);
   
       if (turn==false){      
           
       //update location & display on map
           
           blockArray[location1 %30].setStyle("-fx-border-width:6;-fx-border-color: #737373;-fx-background-color: #e5ffe5");
           
            if (location1==location2&&location2!=0)
                blockArray[location2%30].setStyle("-fx-border-width:6;-fx-border-color: #00FFFF;-fx-background-color: #e5ffe5");
           
            if(location1==0)
                location1-=1;

            location1=location1+diceNum;
            blockArray[location1 %30].setStyle("-fx-border-width:6;-fx-border-color: #FFFF00;-fx-background-color: #e5ffe5");
            
            turn=true;
            control.setTurn("Player one");
            
       }     
       else{
            blockArray[location2%30].setStyle("-fx-border-width:6;-fx-border-color: #737373;-fx-background-color: #e5ffe5");
            
             if (location1==location2)
                blockArray[location2%30].setStyle("-fx-border-width:6;-fx-border-color: #FFFF00;-fx-background-color: #e5ffe5");
             
             if(location2==0)
                 location2-=1;
             
            location2=location2+diceNum;
            blockArray[location2 %30].setStyle("-fx-border-width:6;-fx-border-color: #00FFFF;-fx-background-color: #e5ffe5");
            
            turn=false;
            control.setTurn("Player Two");
       }
       
       if (location1==location2)
           blockArray[location2%30].setStyle("-fx-border-width:6;-fx-border-color: #33cc33;-fx-background-color: #e5ffe5");
     
       
       return diceNum ;
       
   }
    
}
