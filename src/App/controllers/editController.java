package App.controllers;

import App.configrations.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class editController {

    public JFXButton searchbtn;
    public JFXTextField textId;
    public BorderPane content;


   public void viewToEdit(){
        DBConnection connectionFactory = new DBConnection();

        try {
            Connection con = connectionFactory.getConnection();
            String SQL = "select * from patient where id =?";

            PreparedStatement preparedStatement =con.prepareStatement(SQL);
            preparedStatement.setInt(1,Integer.parseInt(textId.getText()));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                viewUpdate(rs.getInt("id"));
            }
        }
        catch(ClassNotFoundException ce){
            Notifications.create().text("ClassNotFoundException in Edit controller").darkStyle().showWarning();
        }
        catch(SQLException e){
            Notifications.create().text("SQLException in Edit controller").darkStyle().showWarning();
        }
        catch (Exception e) {
            Notifications.create().text("Exception in Edit controller").darkStyle().showWarning();
        }
    }


    public void viewUpdate(int id) {
        content.getChildren().clear();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/updateView.fxml"));
            /*
            * Remember first time you print the passing value you will get null,
            * You can use it after your windows loaded ,
            * same for everything you want to code for any other component.
            * */
            Node root = (Node) fxmlLoader.load();
            updateController controller = fxmlLoader.<updateController>getController();
            controller.setId(id);
            content.getChildren().add(root);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
