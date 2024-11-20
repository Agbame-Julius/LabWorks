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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Book;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

    @FXML
    private Button bttn_insert;

    @FXML
    private Button bttn_return;
    @FXML
    private Button bttn_reserve;

    @FXML
    private Button bttn_update;
    @FXML
    private Button bttn_delete;

    @FXML
    private DatePicker date_issue;

    @FXML
    private DatePicker re_date;

    @FXML
    private TableColumn<Book,Integer> colid;

    @FXML
    private TableColumn<Book, String> col_title;
    @FXML
    private TableColumn<Book, String> col_author;

    @FXML
    private TableColumn<Book, String> col_genre;

    @FXML
    private TableColumn<Book, String> col_isbn;

    @FXML
    private TableColumn<Book, String> col_available;


    @FXML
    private DatePicker dt_returnDate;

    @FXML
    private TableView<Book> tvbooks;

    @FXML
    private TextField txt_author;
    @FXML
    private TextField txt_title;

    @FXML
    private TextField txt_available;

    @FXML
    private TextField txt_R_bookid;

    @FXML
    private TextField txt_R_patronid;

    @FXML
    private TextField txt_bookid;

    @FXML
    private TextField txt_Lbookid;

    @FXML
    private TextField txt_LpatronID;

    @FXML
    private Button txt_delete;

    @FXML
    private TextField txt_genre;

    @FXML
    private TextField txt_isbn;




    @FXML
    void handleTransitionToTransactions(ActionEvent event) {
        try {
            // Load the Transaction FXML
            FXMLLoader loader = new FXMLLoader(TransactionController.class.getResource("transaction-view.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Transactions");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void handleDeleteAction(ActionEvent event) {
        if (event.getSource() == bttn_delete) {
            deletBook();
        }
        showBooks();

    }

    @FXML
    void handleInsertAction(ActionEvent event) {
        if(event.getSource() == bttn_insert){
            insertRecord();
        }

    }

    @FXML
    void handleUpdateAction(ActionEvent event) {
        if(event.getSource() == bttn_update){
            updateBook();
        }

    }

    @FXML
    void handleReturnBook(ActionEvent event) {
        if(event.getSource() == bttn_return){
            returnBook();
        }
    }


    @FXML
    void handleBookReservation(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBooks();
    }

    public ObservableList<Book> getBookList(){
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        Connection conn = DbUtil.connectDB();
        String query = "Select * from books";
        PreparedStatement pst;
        ResultSet rs;
        try{
            pst = conn.prepareStatement(query);
            rs  = pst.executeQuery();
            Book books;
            while (rs.next()){
              books = new Book(rs.getInt("bookid"), rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getString("genre"), rs.getString("availability"));
              bookList.add(books);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return  bookList;
    }

    public void showBooks(){
        ObservableList<Book> list = getBookList();
        colid.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookID"));
        col_title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        col_author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        col_isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        col_genre.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
        col_available.setCellValueFactory(new PropertyValueFactory<Book, String>("availability"));

        tvbooks.setItems(list);
    }

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    PreparedStatement pstReturnDate = null;

    private void insertRecord(){
        conn = DbUtil.connectDB();
        String sqlInsert = "Insert into books (title, author, isbn, genre, availability) values(?, ?, ?, ?, ?)";
        try{
            pst = conn.prepareStatement(sqlInsert);
            pst.setString(1, txt_title.getText());
            pst.setString(2, txt_author.getText());
            pst.setString(3, txt_isbn.getText());
            pst.setString(4, txt_genre.getText());
            pst.setString(5, txt_available.getText());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0)
                JOptionPane.showMessageDialog(null, "Inserted Successfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        showBooks();
    }

//    private void upDateBook(){
////       String query = "Update books Set title = " + txt_title.getText()+", author = " + txt_author.getText() + ", isbn = " + txt_isbn.getText() + ", genre = " + txt_genre.getText() +",  isavailable = " +txt_available.getText()+" where bookID = "+Integer.parseInt(txt_bookid.getText())+" )";
////       executeQuery(query);
//
//
//       showBooks();
//    }


    public void updateBook() {
        conn = DbUtil.connectDB();

        // SQL query to update the books table
        String updateBookSql = "UPDATE books SET title = ?, author = ?, isbn = ?, genre = ?, availability = ? WHERE bookid = ?";

        try {
            // Validate inputs
            String bookIdInput = txt_bookid.getText();
            String titleInput = txt_title.getText();
            String authorInput = txt_author.getText();
            String isbn = txt_isbn.getText();
            String genre = txt_genre.getText();
            String availabilityInput = txt_available.getText();

            if (bookIdInput == null || bookIdInput.trim().isEmpty() ||
                    titleInput == null || titleInput.trim().isEmpty() ||
                    authorInput == null || authorInput.trim().isEmpty() ||
                    availabilityInput == null || availabilityInput.trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "All fields must be filled out.");
                return;
            }

            int bookId = Integer.parseInt(bookIdInput); // Parse book ID to integer

            // Prepare the SQL statement
            pst = conn.prepareStatement(updateBookSql);
            pst.setString(1, titleInput); // Set title
            pst.setString(2, authorInput); // Set author
            pst.setString(3, isbn); // Set author
            pst.setString(4, genre); // Set author
            pst.setString(5, availabilityInput); // Set availability
            pst.setInt(6, Integer.parseInt(bookIdInput)); // Set book ID in WHERE clause

            // Execute the update
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No book found with the provided ID.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Book ID. Please enter a numeric value.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating book: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        showBooks();
    }


    private void deletBook(){
        conn = DbUtil.connectDB();
        String query = "Delete from books where bookID = ?";
        try{
            pst = conn.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(txt_bookid.getText()));
            int row =  pst.executeUpdate();
            if(row > 0)
                JOptionPane.showMessageDialog(null, "Updated");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        showBooks();

    }


    private void executeQuery(String query) {
        Connection conn = DbUtil.connectDB();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void handleLendBook(ActionEvent event) {
        conn = DbUtil.connectDB();
        //queries
        String validateBookSql = "SELECT * FROM books WHERE bookid = ?"; // CHANGES: Validation query for bookid
        String validatePatronSql = "SELECT * FROM patrons WHERE patronid = ?";
        String sql = "INSERT INTO transactions (bookid, patronid, issuedate, duedate) VALUES (?, ?, ?, ?)";
        String updateBookAvailabilitySql = "UPDATE books SET availability = 'Lent' WHERE bookid = ?";

        try{
            // Step 1: Validate if bookid exists
            PreparedStatement validateBookStmt = conn.prepareStatement(validateBookSql); // CHANGES: Added book validation
            validateBookStmt.setInt(1, Integer.parseInt(txt_Lbookid.getText()));
            ResultSet bookRs = validateBookStmt.executeQuery();
            if (!bookRs.next()) {
                JOptionPane.showMessageDialog(null, "Error: Book ID not found in the books table.");
                return; // Exit the method if bookid is invalid
            }

            //this is checking the availability of the existed
            String availability = bookRs.getString("availability"); // Reuse the result set to get the availability
            if ("Lent".equalsIgnoreCase(availability)) {
                JOptionPane.showMessageDialog(null, "Error: Book unavailable. It is already lent.");
                return; // Exit the method if the book is unavailable
            }

            // Step 2: Validate if patronid exists
            PreparedStatement validatePatronStmt = conn.prepareStatement(validatePatronSql); // CHANGES: Added patron validation
            validatePatronStmt.setInt(1, Integer.parseInt(txt_LpatronID.getText()));
            ResultSet patronRs = validatePatronStmt.executeQuery();
            if (!patronRs.next()) {
                JOptionPane.showMessageDialog(null, "Error: Patron ID not found in the patrons table.");
                return; // Exit the method if patronid is invalid
            }



            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txt_Lbookid.getText()));
            pst.setInt(2, Integer.parseInt(txt_LpatronID.getText()));
            pst.setDate(3, Date.valueOf(LocalDate.now()));
            pst.setDate(4, Date.valueOf(re_date.getValue()));

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0)
                JOptionPane.showMessageDialog(null, "Lent Successfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        try{
            pst = conn.prepareStatement(updateBookAvailabilitySql);
            pst.setInt(1, Integer.parseInt(txt_Lbookid.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0)
                JOptionPane.showMessageDialog(null, "availability changed Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        showBooks();
    }


    public void returnBook(){

            conn = DbUtil.connectDB();

            // SQL queries
            String validateTransactionSql = "SELECT * FROM transactions WHERE bookid = ? AND patronid = ?";
            String updateBookAvailabilitySql = "UPDATE books SET availability = 'Available' WHERE bookid = ?";
            String updateReturnDateSql = "UPDATE transactions SET returndate = ? WHERE bookid = ?";

            try {
                // Step 1: Validate that the bookid and patronid match in the transactions table
                PreparedStatement validateTransactionStmt = conn.prepareStatement(validateTransactionSql);
                validateTransactionStmt.setInt(1, Integer.parseInt(txt_R_bookid.getText()));
                validateTransactionStmt.setInt(2, Integer.parseInt(txt_R_patronid.getText()));
                ResultSet rs = validateTransactionStmt.executeQuery();

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Error: Book ID and Patron ID do not match or are not in the transactions table.");
                    return;
                }

                // Step 2: Update the book's availability
                PreparedStatement updateAvailabilityStmt = conn.prepareStatement(updateBookAvailabilitySql);
                updateAvailabilityStmt.setInt(1, Integer.parseInt(txt_R_bookid.getText()));
                int rowsAffected = updateAvailabilityStmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Book availability updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Book ID not found in the books table.");
                    return;
                }

                // Step 3: Update the return date in the transactions table
                PreparedStatement updateReturnDateStmt = conn.prepareStatement(updateReturnDateSql);
                updateReturnDateStmt.setDate(1, Date.valueOf(dt_returnDate.getValue()));
                updateReturnDateStmt.setInt(2, Integer.parseInt(txt_R_bookid.getText()));
                int returnDateRows = updateReturnDateStmt.executeUpdate();
                if (returnDateRows > 0) {
                    JOptionPane.showMessageDialog(null, "Return date updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Failed to update the return date.");
                }

                // Refresh the books table view
                showBooks();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Invalid input. Please enter valid numeric IDs.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }






    // for transaction



}

