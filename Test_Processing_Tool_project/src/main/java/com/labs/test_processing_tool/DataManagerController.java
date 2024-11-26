package com.labs.test_processing_tool;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class DataManagerController {

    @FXML
    private Button bttn_add; 

    @FXML
    private Button bttn_back;

    @FXML
    private Button bttn_delete;

    @FXML
    private Button bttn_update;

    @FXML
    private TableView<DataManager> table_data;

    @FXML
    private TableColumn<DataManager, String> table_key;

    @FXML
    private TableColumn<DataManager, String> table_value;


    @FXML
    private TextField txt_key;

    @FXML
    private TextField txt_value;

    private ObservableList<DataManager> dataList;


    /**
     * Initializes the controller.
     * Sets up the TableView columns and binds the data list to the TableView.
     */
    @FXML
    public void initialize() {

        dataList = FXCollections.observableArrayList();
        table_key.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getKey()));
        table_value.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getValue()));

        // Set data to the table
        table_data.setItems(dataList);
    }




    /**
     *
     * Adds a new entry to the TableView based on the values provided in the text fields.
     * Clears the input fields after adding.
     *
     * @param event the ActionEvent triggered by clicking the "Add" button
     */
    @FXML
    void handleAddDAta(ActionEvent event) {
        String key = txt_key.getText();
        String value = txt_value.getText();
        if (key.isEmpty() || value.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Both key and value must be provided.");
            return;
        }

        DataManager newData = new DataManager(key, value);
        dataList.add(newData);

        // Clear input fields
        txt_key.clear();
        txt_value.clear();
    }

    /**
     * Handles the "Back" button click event.
     * Navigates back to the main UI by loading the "MainUI.fxml" file.
     *
     * @param event the ActionEvent triggered by clicking the "Back" button
     */
    @FXML
    void handleBackAction(ActionEvent event) {
        try {
            // Load the Transaction FXML
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("MainUI.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Text Processing");
            stage.show();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }



    /**
     * Handles the "Delete" button click event.
     * Deletes the selected entry from the TableView.
     *
     * @param event the ActionEvent triggered by clicking the "Delete" button
     */
    @FXML
    void handleDeleteData(ActionEvent event) {
        DataManager selected = table_data.getSelectionModel().getSelectedItem();

        if (selected == null) {
            JOptionPane.showMessageDialog(null, "Please select an item to delete.");
            return;
        }

        dataList.remove(selected);


    }

    /**
     * Handles the "Update" button click event.
     * Updates the selected entry in the TableView with the values provided in the text fields.
     * Refreshes the TableView to display updated data and clears the input fields.
     *
     * @param event the ActionEvent triggered by clicking the "Update" button
     */
    @FXML
    void handleUpdateData(ActionEvent event) {
        DataManager selected = table_data.getSelectionModel().getSelectedItem();

        if (selected == null) {
            JOptionPane.showMessageDialog(null,"Please select an item to update.");
            return;
        }

        String key = txt_key.getText();
        String value = txt_value.getText();

        if (key.isEmpty() || value.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Both key and value must be provided.");
            return;
        }

        // Update selected data
        selected.setKey(key);
        selected.setValue(value);

        // Refresh table to show updated data
        table_data.refresh();

        // Clear input fields
        txt_key.clear();
        txt_value.clear();

    }

}

