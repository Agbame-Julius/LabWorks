package com.example.library_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Book;
import model.Transaction;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {

    @FXML
    private TableColumn<Transaction, Integer> col_BookID;

    @FXML
    private TableColumn<Transaction, Date> col_IssueDate;

    @FXML
    private TableColumn<Transaction, Integer> col_PatronID;

    @FXML
    private TableColumn<Transaction, Date> col_Return;

    @FXML
    private TableColumn<Transaction, Date> col_dueDate;

    @FXML
    private TableColumn<Transaction, Integer> col_transactionID;

    @FXML
    private TableView<Transaction> transaction_table;

    private Button bttn_book_manager;



    @FXML
    void handleBookManagement(ActionEvent event) {
        try {
            // Load the Transaction FXML
            FXMLLoader loader = new FXMLLoader(AddBookController.class.getResource("add-book-view.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Transactions");
            stage.show();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            showTransactions();
    }

    public ObservableList<Transaction> getTransactionList(){
        ObservableList<Transaction> transactionList = FXCollections.observableArrayList();
        Connection conn = DbUtil.connectDB();
        String query = "Select * from transactions";
        PreparedStatement pst;
        ResultSet rs;
        try{
            pst = conn.prepareStatement(query);
            rs  = pst.executeQuery();
            Transaction transactions;
            while (rs.next()){
                transactions = new Transaction(rs.getInt("transactionid"), rs.getInt("bookid"), rs.getInt("patronid"), rs.getDate("issuedate"), rs.getDate("duedate"), rs.getDate("returndate"));
                transactionList.add(transactions);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return  transactionList;
    }

    //for displaying the transactions
    public void showTransactions(){
        ObservableList<Transaction> t_list = getTransactionList();
        col_transactionID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("transactionid"));
        col_BookID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("bookid"));
        col_PatronID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("patronid"));
        col_IssueDate.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("issuedate"));
        col_dueDate.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("duedate"));
        col_Return.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("returndate"));

        transaction_table.setItems(t_list);
    }



}
