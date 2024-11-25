package com.labs.test_processing_tool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import com.labs.test_processing_tool.RegexUtil;

import javax.swing.*;

public class MainController {


    @FXML
    private Button bttn_clear;

    @FXML
    private Button bttn_match;

    @FXML
    private Button bttn_replace;

    @FXML
    private Button bttn_search;

    @FXML
    private TextArea txtA_input_text;

    @FXML
    private TextArea txtA_result;

    @FXML
    private TextField txt_regular_expres;

    @FXML
    private TextField txt_replace_text;



    @FXML
    void handleClear(ActionEvent event) {
            txt_replace_text.clear();
            txtA_result.clear();
            txt_regular_expres.clear();
            txtA_input_text.clear();
            txtA_result.setDisable(true);
    }

    @FXML
    void handleMatchPattern(ActionEvent event) {
        String pattern = txt_regular_expres.getText();
        String text = txtA_input_text.getText();
        boolean is_match = RegexUtil.isMatch(pattern, text);
        String result = is_match ? "The text matches the pattern" : "The text does not match the pattern";
        txtA_result.setText(result);
        txtA_result.setDisable(true);

    }

    @FXML
    void handleReplace(ActionEvent event) {
        if(txt_replace_text.getText() == null){
            JOptionPane.showMessageDialog(null, "Replace text can't be null");}
        String textToReplace = txt_replace_text.getText();
        String text = txtA_input_text.getText();
        String pattern = txt_regular_expres.getText();
        String matches = RegexUtil.replaceText(pattern, text, textToReplace);
        txtA_result.setText(matches);
        txtA_result.setDisable(true);


    }

    @FXML
    void handleSearch(ActionEvent event) {

        String pattern = txt_regular_expres.getText();
        String text = txtA_input_text.getText();
        RegexUtil RegexUtils = null;
        String matches = RegexUtils.findMatches(pattern, text);
        txtA_result.setText(matches);
        txtA_result.setDisable(true);

    }

}