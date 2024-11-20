package com.example.library_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button bttn_login;

    @FXML
    private Button bttn_signup;

    @FXML
    private AnchorPane pane_login;

    @FXML
    private AnchorPane pane_signup;

    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_password_sign;

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_username_sign;

    @FXML
    private ComboBox type;

    @FXML
    private ComboBox type_up;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    private void  Login(ActionEvent event) throws Exception{
    conn = DbUtil.connectDB();
    String sql = "Select * from patrons where username = ? and password = ? and type = ? ";
    try{
        pst = conn.prepareStatement(sql);
        pst.setString(1, txt_username.getText());
        pst.setString(2, txt_password.getText());
        pst.setString(3, type.getValue().toString());
        rs = pst.executeQuery();

        if(rs.next()){
            JOptionPane.showMessageDialog(null, "Username and Password are correct");
            bttn_login.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(AddBookController.class.getResource("add-book-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 913, 600);
            Stage mainStage = new Stage();
//            Scene scene = new Scene(root);
            mainStage.setTitle("Dashboard!");
            mainStage.setScene(scene);
            mainStage.show();

        }else{
            JOptionPane.showMessageDialog(null, "Password did not match with the Username");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    }


    public void addPatron(ActionEvent event){
        conn = DbUtil.connectDB();
        String sqlInsert = "Insert into patrons (username, password, type, email) values(?, ?, ?, ?)";
        try{
            pst = conn.prepareStatement(sqlInsert);
            pst.setString(1, txt_username_sign.getText());
            pst.setString(2, txt_password_sign.getText());
            pst.setString(3, type_up.getValue().toString());
            pst.setString(4, txt_email.getText());
            pst.executeQuery();

            JOptionPane.showMessageDialog(null, "SignUp Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }








    public void loginPaneShow(){
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    }

    public void signUpPaneShow(){
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type_up.getItems().addAll("Admin","Patron","Student");
        type.getItems().addAll("Admin","Patron","Student");

    }
}
