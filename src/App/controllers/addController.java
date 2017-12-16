package App.controllers;

import App.configrations.DBConnection;
import com.jfoenix.controls.JFXTextField;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addController {

    public JFXTextField textFirstName;
    public JFXTextField textLastName;
    public JFXTextField textAge;
    public JFXTextField textDisease;
    public JFXTextField textRoom;
    public JFXTextField textDoctor;

    public void add(){

        DBConnection connectionFactory = new DBConnection();

        try {

            Connection con = connectionFactory.getConnection();
            String SQL = "insert into patient (firstname,lastname,age,disease,room,doctor) values (?,?,?,?,?,?)";

            PreparedStatement preparedStatement =con.prepareStatement(SQL);
            preparedStatement.setString(1,textFirstName.getText());
            preparedStatement.setString(2,textLastName.getText());
            preparedStatement.setInt(3,Integer.parseInt(textAge.getText()));
            preparedStatement.setString(4,textDisease.getText());
            preparedStatement.setString(5,textRoom.getText());
            preparedStatement.setString(6,textDoctor.getText());
            preparedStatement.executeUpdate();
            Notifications.create().text("The Patient's data Has Been Successfully Saved").darkStyle().showInformation();
            textFirstName.setText("");
            textLastName.setText("");
            textAge.setText("");
            textDisease.setText("");
            textRoom.setText("");
            textDoctor.setText("");

        }
        catch(ClassNotFoundException ce){
            Notifications.create().text("ClassNotFoundException in Add controller").darkStyle().showWarning();
        }
        catch(SQLException e){
            Notifications.create().text("SQLException in Add controller").darkStyle().showWarning();
        }
        catch (Exception e) {
            Notifications.create().text("Exception in Add controller").darkStyle().showWarning();
        }
    }
}
