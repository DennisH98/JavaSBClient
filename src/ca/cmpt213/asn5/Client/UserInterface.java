package ca.cmpt213.asn5.Client;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates and organizes the User Interface
 * @author Dennis Huynh
 * @author 3013279204
 */


public class UserInterface {
    List<Tokimon> tokimonList;

    public Scene mainScene(){


        TabPane tabPane = new TabPane();
        Tab createTab = new Tab("Create Tokimon");
        Tab viewTab = new Tab("View Tokimon");
        Tab deleteTab = new Tab("Delete Tokimon");
        Tab shapeTab = new Tab("Shape of Tokimon");
        createTab.setClosable(false);
        viewTab.setClosable(false);
        deleteTab.setClosable(false);
        shapeTab.setClosable(false);


        createTab.setContent(createAddTokiScene());
        viewTab.setContent(createViewScene());
        deleteTab.setContent(deleteTokiScene());
        //shapeTab.setContent(createShapeScene());

        shapeTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                shapeTab.setContent(createShapeScene());
            }
        });

        tabPane.getTabs().addAll(createTab,viewTab,deleteTab,shapeTab);
        Scene scene = new Scene(tabPane,600,300);


        return scene;
    }

    public VBox createAddTokiScene(){

        Label label = new Label("Please enter in data for new Tokimon");
        Label labelResults = new Label();

        TextField idTextField= new TextField();
        TextField nameTextField= new TextField();
        TextField weightTextField= new TextField();
        TextField heightTextField= new TextField();
        TextField abilityTextField= new TextField();
        TextField strengthField= new TextField();
        TextField colorField= new TextField();

        idTextField.setPromptText("ID");
        nameTextField.setPromptText("Name");
        weightTextField.setPromptText("Weight");
        heightTextField.setPromptText("Height");
        abilityTextField.setPromptText("Ability");
        strengthField.setPromptText("Strength");
        colorField.setPromptText("Color");

        GridPane gridPane = new GridPane();
        gridPane.add(idTextField,0,0);
        gridPane.add(nameTextField,1,0);
        gridPane.add(weightTextField,2,0);
        gridPane.add(heightTextField,3,0);
        gridPane.add(abilityTextField,4,0);
        gridPane.add(strengthField,5,0);
        gridPane.add(colorField,6,0);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(15));


        TextField[] textFieldArray = new TextField[7];
        textFieldArray[0] =idTextField;
        textFieldArray[1] =nameTextField;
        textFieldArray[2] =weightTextField;
        textFieldArray[3] =heightTextField;
        textFieldArray[4] =abilityTextField;
        textFieldArray[5] =strengthField;
        textFieldArray[6] =colorField;

        Button createButton = new Button("Create Tokimon");
        EventController ec = new EventController();
        ec.postData(createButton,textFieldArray,labelResults);


        VBox vBox = new VBox(25, label,gridPane,labelResults,createButton);
        vBox.setAlignment(Pos.CENTER);

        return vBox;

    }

    public VBox createViewScene(){
        TableView<Tokimon> tableView = new TableView<>();

        TableColumn<Tokimon,Integer> idCol= new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Tokimon,String> nameCol= new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Tokimon,Double> weightCol= new TableColumn<>("Weight");
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<Tokimon,Double> heightCol= new TableColumn<>("Height");
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Tokimon,String> abilityCol= new TableColumn<>("Ability");
        abilityCol.setCellValueFactory(new PropertyValueFactory<>("ability"));

        TableColumn<Tokimon,Double> strengthCol= new TableColumn<>("Strength");
        strengthCol.setCellValueFactory(new PropertyValueFactory<>("strength"));

        TableColumn<Tokimon,String> colorCol= new TableColumn<>("Color");
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));

        tableView.getColumns().addAll(idCol,nameCol,weightCol,heightCol,abilityCol,strengthCol,colorCol);

        Button btnAll = new Button("View all");
        Button btnOne = new Button("Find by ID");
        TextField idTextField = new TextField();
        idTextField.setPromptText("Enter ID");
        HBox hBox = new HBox(btnOne, idTextField);
        VBox vBox = new VBox(10, btnAll, hBox,tableView);

        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);


        EventController ec = new EventController();
        ec.getDataAll(btnAll,tableView);
        ec.getDataOne(btnOne,tableView,idTextField);

        return  vBox;
    }

    public VBox deleteTokiScene(){
        Label label = new Label("Enter the ID of the Tokimon you want to Delete");
        Label labelResults = new Label();
        TextField idTextField = new TextField();
        idTextField.setPromptText("Enter ID");

        Button deleteButton= new Button("Delete Tokimon");

        VBox vBox = new VBox(15, label,idTextField,labelResults,deleteButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(100));
        EventController ec = new EventController();
        ec.deleteData(deleteButton,idTextField,labelResults);

        return vBox;
    }

    public GridPane createShapeScene(){


        GridPane grid =new GridPane();

        EventController ec = new EventController();


        tokimonList= ec.getTokimonArray();




        List<Rectangle> rectList = new ArrayList<>();
        List<Label> labelList = new ArrayList<>();

        for(int i=0; i<tokimonList.size();i++){
            Rectangle rect = new Rectangle(tokimonList.get(i).getWeight()+100,tokimonList.get(i).getHeight()+100);
            rect.setFill(Color.BLACK);
            rectList.add(rect);

            labelList.add(new Label(tokimonList.get(i).getName()));
        }

        for(int i=0; i<rectList.size();i++){
            grid.add(labelList.get(i),i,0);
            grid.add(rectList.get(i),i,1);
        }
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);






        return grid;
    }


}
