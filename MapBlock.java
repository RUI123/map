
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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
public class MapBlock extends GridPane{
    private String TypeOfBuild="land";
    
    
    private Label level;
    private Label owner;
    private Label soldier;
    private ImageView landV;
    
    private int levelNum=1;
    private int soldierNum=0;

    public MapBlock(int location){
        
        
        Label localNum=new Label((Integer.toString(location)));
        localNum.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 15));
        localNum.setStyle("-fx-text-fill: #c2c2a3;-fx-background-color: white");
        
        level=new Label("");
        setLableStyle(level);
        
        soldier=new Label("");
        setLableStyle(soldier);
        
        owner=new Label("");
        setLableStyle(owner);
        
        setVgap(5);
        setPrefSize(100,100);
        setStyle("-fx-border-width:6;-fx-border-color: #737373;-fx-background-color: #e5ffe5");
        
        if (location==10||location==13||location==18||location==24){
            setLand();
            
            add(level, 1,1);
            add(soldier,1,2);
            add(owner, 1,3);
            
            
        }
        add(localNum,0,0);
        
        
        
    }
    void setLableStyle(Label all){
        all.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 10));
        all.setStyle("-fx-text-fill: black;-fx-background-color: #f2f2f2");
        
    }
    void setLand(){
        landV=new ImageView(new Image("image/land.jpg"));
        landV.setFitHeight(88);
        landV.setFitWidth(88);
        add(landV,0,0,3,5);

    }
    void setHotel(){
        this.TypeOfBuild="hotel";
        landV.setImage(new Image("image/hotel.jpg"));
    }
    void setFactory(){
        this.TypeOfBuild="factory";
        landV.setImage(new Image("image/factory.gif"));
    }
    void setCastle(){
        this.TypeOfBuild="castle";
        landV.setImage(new Image("image/castle.jpg"));
    }
    
    String getBuildType(){
        return this.TypeOfBuild;
    }
    
    void setOwner(String name){
        
        owner.setText(name);
    }

    void setLevel(int level){
        
        this.levelNum=level;
        this.level.setText("Level "+level);
    }
    void setSoldier(int soldierNum){
        
        this.soldierNum=soldierNum;
        this.owner.setText("Soldiers "+soldierNum);
        
    }
    String getOwner(){
        return owner.getText();
    }
    int getLevel(){
        return this.levelNum;
    }
    
    int getSoldier(){
        return this.soldierNum;
    }
    




    
}
