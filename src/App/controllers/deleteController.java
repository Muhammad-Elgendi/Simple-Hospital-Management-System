package App.controllers;

import App.configrations.DBConnection;
import com.jfoenix.controls.JFXTextField;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteController {

    public JFXTextField textId;

    public void delete(){

        DBConnection connectionFactory = new DBConnection();

        try {

            Connection con = connectionFactory.getConnection();
            String SQL = "delete from patient where id = ?";

            PreparedStatement preparedStatement =con.prepareStatement(SQL);
            preparedStatement.setInt(1,Integer.parseInt(textId.getText()));
            preparedStatement.executeUpdate();
            Notifications.create().text("The Patient's data Has Been Successfully Removed").darkStyle().showInformation();

        }
        catch(ClassNotFoundException ce){
            Notifications.create().text("ClassNotFoundException in delete controller").darkStyle().showWarning();
        }
        catch(SQLException e){
            Notifications.create().text("SQLException in delete controller").darkStyle().showWarning();
        }
        catch (Exception e) {
            Notifications.create().text("Exception in delete controller").darkStyle().showWarning();
        }
    }
}
