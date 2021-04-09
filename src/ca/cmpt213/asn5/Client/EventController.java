package ca.cmpt213.asn5.Client;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This is class that handles most of events that are initiated
 * @author Dennis Huynh
 * @author 3013279204
 */

public class EventController {

    public void postData(Button submit, TextField[] textFields, Label results){
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    URL url = new URL("http://localhost:8080/api/tokimon/add");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");

                    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                    int id;
                    double weight;
                    double height;
                    double strength;

                    try {
                        id = Integer.parseInt(textFields[0].getText());
                    }catch (NumberFormatException e){
                        id=0;
                    }

                    try {
                        weight = Double.parseDouble(textFields[2].getText());
                    }catch (NumberFormatException e){
                        weight = 0;
                    }
                    try {
                        height = Double.parseDouble(textFields[3].getText());

                    }catch (NumberFormatException e){
                        height = 0;
                    }

                    try {
                        strength = Double.parseDouble(textFields[5].getText());
                    }catch (NumberFormatException e){
                        strength =0;
                    }


                    wr.write("{\"id\":"+id+",\"name\":\""+textFields[1].getText()+"\",\"weight\":"+weight+"," +
                            "\"height\":"+ height+",\"ability\":\""+ textFields[4].getText()+"\",\"strength\":"+strength+"," +
                            "\"color\":\""+textFields[6].getText()+"\"}");

                    results.setText("Tokimon was created!");
                    wr.flush();
                    wr.close();
                    connection.connect();
                    System.out.println(connection.getResponseCode());
                    connection.disconnect();
                    for (int i =0; i<textFields.length;i++){
                        textFields[i].setText("");
                    }

                }
                catch (IOException e){

                }

            }
        });


    }

    public void getDataAll(Button button, TableView<Tokimon> tokiTable){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try{
                    URL url = new URL("http://localhost:8080/api/tokimon/all");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));

                    String output;

                    Gson gson = new Gson();
                    List<Tokimon> tokimonList = new ArrayList<>();
                    while((output = br.readLine()) != null){
                        Tokimon[] tokiArray = gson.fromJson(output,Tokimon[].class);

                        for(int i=0;i<tokiArray.length;i++){
                            tokimonList.add(tokiArray[i]);
                        }
                    }
                    ObservableList<Tokimon> observableTokiList = FXCollections.observableArrayList(tokimonList);
                    tokiTable.setItems(observableTokiList);


                    connection.disconnect();
                }
                catch (IOException e){

                }

            }
        });

    }

    public void getDataOne(Button button, TableView<Tokimon> tokiTable, TextField idField){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                int id;
                try{
                    id = Integer.parseInt(idField.getText());
                }catch (NumberFormatException e){
                    id = 0;
                }

                try{
                    URL url = new URL("http://localhost:8080/api/tokimon/"+ id);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));

                    String output;

                    Gson gson = new Gson();
                    List<Tokimon> tokimonList = new ArrayList<>();
                    while((output = br.readLine()) != null){
                        Tokimon tokimon = gson.fromJson(output,Tokimon.class);

                        tokimonList.add(tokimon);
                    }
                    ObservableList<Tokimon> observableTokiList = FXCollections.observableArrayList(tokimonList);
                    tokiTable.setItems(observableTokiList);


                    connection.disconnect();
                    idField.setText("");
                }
                catch (IOException e){

                }

            }
        });


    }

    public void deleteData(Button button, TextField idField, Label results){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                int id;
                try{
                     id = Integer.parseInt(idField.getText());
                }catch (NumberFormatException e){
                     id = 0;
                }

                try{
                    URL url = new URL("http://localhost:8080/api/tokimon/"+ id);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("DELETE");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");

                    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                    wr.write(id);
                    wr.flush();
                    wr.close();
                    results.setText("Tokimon was Deleted!");
                    connection.connect();
                    System.out.println(connection.getResponseCode());
                    connection.disconnect();
                    idField.setText("");
                }
                catch (IOException e){

                }
            }
        });

    }

    public List<Tokimon> getTokimonArray(){

        try{
            URL url = new URL("http://localhost:8080/api/tokimon/all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String output;

            Gson gson = new Gson();
            List<Tokimon> tokimonList = new ArrayList<>();
            while((output = br.readLine()) != null){
                Tokimon[] tokiArray = gson.fromJson(output,Tokimon[].class);

                for(int i=0;i<tokiArray.length;i++){
                    tokimonList.add(tokiArray[i]);
                }
            }


            connection.disconnect();
            return tokimonList;
        }
        catch (IOException e){

        }
        return new ArrayList<>();
    }



}
