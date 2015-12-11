
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ct-gen
 */
public class BottomButton extends FlowPane{
    
    private ImageView diceV=new ImageView(new Image("image/dice.jpg"));
    
    private Button exit=new Button("Exit Game");
    private Button roll=new Button(null,diceV);
    
    Label turnLabel = new Label(null);
    Label diceResult = new Label(null);
    
    
    public BottomButton(){
        
        diceResult.setFont(Font.font("Times", FontWeight.BOLD, 20));
        diceResult.setStyle("-fx-background-color: white");
        diceResult.setAlignment(Pos.CENTER);
        
        
        turnLabel.setStyle("-fx-background-color: white");
        turnLabel.setAlignment(Pos.CENTER);
        
        
        exit.setStyle("-fx-border-color: red ;-fx-border-width: 3");
        roll.setStyle("-fx-border-color: green ;-fx-border-width: 3");
        
        diceV.setFitHeight(30);
        diceV.setFitWidth(30);
        
        setHgap(80);
        setVgap(10);
        
        getChildren().addAll(exit,roll,turnLabel,diceResult);
        
    }
    public Button getRollButton(){
        return roll;
    }
    public Button getExitButton(){
        return exit;
    }
    void setDice(int num){
        diceResult.setText((Integer.toString(num)));
    }
    void setTurn(String name){
        turnLabel.setText("Player "+name+" Turn");
    }
    
    
}
