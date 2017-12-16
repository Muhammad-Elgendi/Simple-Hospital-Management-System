package App.controllers;

import App.configrations.DBConnection;
import App.models.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class viewController {

    public JFXButton searchbtn;
    public JFXTextField textLastName;
    public JFXTextField textFirstName;
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
    }

    private ObservableList<Patient> collectionList;

    public void search(){

        collectionList = FXCollections.observableArrayList();
        DBConnection connectionFactory = new DBConnection();

        String firstName =textFirstName.getText();
        String lastName =textLastName.getText();

        try {
            Connection con = connectionFactory.getConnection();
            String SQL = "select * from patient where firstname =? and lastname=?";

            PreparedStatement preparedStatement =con.prepareStatement(SQL);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            ResultSet rs = preparedStatement.executeQuery();
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
            System.out.println("ClassNotFoundException in View controller");
        }
        catch(SQLException e){
            System.out.println("SQLException in View controller");
        }
        catch (Exception e) {
            System.out.println("Error on Building Data in View controller");
        }
    }
}
