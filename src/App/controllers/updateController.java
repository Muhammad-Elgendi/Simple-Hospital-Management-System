package App.controllers;

import App.configrations.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class updateController {

    public ResultSet rs;
    public JFXTextField textFirstName;
    public JFXTextField textLastName;
    public JFXTextField textAge;
    public JFXTextField textDisease;
    public JFXTextField textRoom;
    public JFXTextField textDoctor;
    public JFXButton updatebtn;
    public int id;


    public void setId(int id){
        this.id=id;
        this.getRecord();
    }

    public void getRecord(){
        DBConnection connectionFactory = new DBConnection();
        try {

            Connection con = connectionFactory.getConnection();
            String SQL = "select * from patient where id = ?";
            PreparedStatement preparedStatement =con.prepareStatement(SQL);
            preparedStatement.setInt(1,this.id);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()) {
                textFirstName.setText(rs.getString("firstname"));
                textLastName.setText(rs.getString("lastname"));
                textAge.setText(String.valueOf(rs.getInt("age")));
                textDisease.setText(rs.getString("disease"));
                textRoom.setText(rs.getString("room"));
                textDoctor.setText(rs.getString("doctor"));
            }
        }
        catch(ClassNotFoundException ce){
            System.out.println("ClassNotFoundException in Update controller (Get record)");
        }
        catch(SQLException e){
            System.out.println("SQLException in Update controller (Get record)");
        }
        catch (Exception e) {
            System.out.println("Error on Building Data in Update controller (Get record)");
        }
    }

    public void update(){
        DBConnection connectionFactory = new DBConnection();
        try {

            Connection con = connectionFactory.getConnection();
            String SQL = "update patient set firstname = ? , lastname = ? , age =? , disease = ? , room = ? , doctor = ? where id = ?";
            PreparedStatement preparedStatement =con.prepareStatement(SQL);
            preparedStatement.setString(1,textFirstName.getText());
            preparedStatement.setString(2,textLastName.getText());
            preparedStatement.setInt(3,Integer.parseInt(textAge.getText()));
            preparedStatement.setString(4,textDisease.getText());
            preparedStatement.setString(5,textRoom.getText());
            preparedStatement.setString(6,textDoctor.getText());
            preparedStatement.setInt(7,id);
            preparedStatement.executeUpdate();
            Notifications.create().text("The Patient's data Has Been Successfully Updated").darkStyle().showInformation();
            textFirstName.setText("");
            textLastName.setText("");
            textAge.setText("");
            textDisease.setText("");
            textRoom.setText("");
            textDoctor.setText("");


        }
        catch(ClassNotFoundException ce){
            Notifications.create().text("ClassNotFoundException in Update controller").darkStyle().showWarning();
        }
        catch(SQLException e){
            Notifications.create().text("SQLException in Update controller").darkStyle().showWarning();
        }
        catch (Exception e) {
            Notifications.create().text("Exception in Update controller").darkStyle().showWarning();
        }
    }
}
