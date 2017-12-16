package App.controllers;


import App.configrations.DBConnection;
import App.models.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {

    public BorderPane content;
    public TableView<Patient> tableview;
    public TableColumn<Patient,Integer> colId;
    public TableColumn<Patient,String> colFirstName;
    public TableColumn<Patient,String> colLastName;
    public TableColumn<Patient,Integer> colAge;
    public TableColumn<Patient,String> colDisease;
    public TableColumn<Patient,String> colRoom;
    public TableColumn<Patient,String> colDoctor;

    @FXML
    void initialize(){
        colId.setCellValueFactory(
                new PropertyValueFactory<Patient,Integer>("id"));
        colAge.setCellValueFactory(
                new PropertyValueFactory<Patient,Integer>("age"));
        colFirstName.setCellValueFactory(
                new PropertyValueFactory<Patient,String>("firstname"));
        colLastName.setCellValueFactory(
                new PropertyValueFactory<Patient,String>("lastname"));
        colDisease.setCellValueFactory(
                new PropertyValueFactory<Patient,String>("disease"));
        colRoom.setCellValueFactory(
                new PropertyValueFactory<Patient,String>("room"));
        colDoctor.setCellValueFactory(
                new PropertyValueFactory<Patient,String>("doctor"));

        buildData();
    }

    private ObservableList<Patient> collectionList;

    public void buildData() {

        collectionList = FXCollections.observableArrayList();
        DBConnection connectionFactory = new DBConnection();

        try {
            Connection con = connectionFactory.getConnection();
            String SQL = "Select * from patient Order By id";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while (rs.next()) {

                Patient patient = new Patient();
                patient.id.set(rs.getInt("id"));
                patient.age.set(rs.getInt("age"));
                patient.firstname.set(rs.getString("firstname"));
                patient.lastname.set(rs.getString("lastname"));
                patient.doctor.set(rs.getString("doctor"));
                patient.disease.set(rs.getString("disease"));
                patient.room.set(rs.getString("room"));

                collectionList.add(patient);

            }
            tableview.setItems(collectionList);


        }
        catch(ClassNotFoundException ce){
            System.out.println("ClassNotFoundException");
        }
        catch(SQLException ce){
            System.out.println("SQLException");
        }
        catch (Exception e) {
            System.out.println("Error on Building Data");
        }
    }

    public void view(ActionEvent actionEvent) {
        content.getChildren().clear();
        try {
            content.getChildren().add((Node) FXMLLoader.load(getClass().getResource("../views/showItemView.fxml")));
        }
        catch (IOException e){

        }

    }

    public void viewAll(ActionEvent actionEvent) {
        content.getChildren().clear();
        try {
            content.getChildren().add((Node) FXMLLoader.load(getClass().getResource("../views/showAllItems.fxml")));
        }
        catch (IOException e){

        }

    }

    public void add(ActionEvent actionEvent) {
        content.getChildren().clear();
        try {
            content.getChildren().add((Node) FXMLLoader.load(getClass().getResource("../views/addView.fxml")));
        }
        catch (IOException e){

        }

    }

    public void delete(ActionEvent actionEvent) {
        content.getChildren().clear();
        try {
            content.getChildren().add((Node) FXMLLoader.load(getClass().getResource("../views/deleteView.fxml")));
        }
        catch (IOException e){

        }

    }

    public void edit(ActionEvent actionEvent) {
        content.getChildren().clear();
        try {
            content.getChildren().add((Node) FXMLLoader.load(getClass().getResource("../views/editView.fxml")));
        }
        catch (IOException e){

        }

    }
}
