package com.example.library_management_system;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    Connection conn = null;

    public static Connection connectDB(){
        try{
//            Class.forName("org.postgresql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Testing", "postgres","0000");
            JOptionPane.showMessageDialog(null, "Connection Established");
            return  conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
}
