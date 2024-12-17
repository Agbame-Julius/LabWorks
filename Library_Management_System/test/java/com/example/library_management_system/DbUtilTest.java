package com.example.library_management_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DbUtilTest {

    private MockedStatic<DriverManager> mockedDriverManager;
    private MockedStatic<JOptionPane> mockedJOptionPane;

    @BeforeEach
    void setUp() {
        // Mock static classes
        mockedDriverManager = mockStatic(DriverManager.class);
        mockedJOptionPane = mockStatic(JOptionPane.class);
    }

    @Test
    void testConnectDBSuccess() throws Exception {
        // Mocking a successful connection
        Connection mockConnection = mock(Connection.class);
        mockedDriverManager.when(() -> DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/Testing",
                        "postgres",
                        "0000"))
                .thenReturn(mockConnection);

        // Simulate JOptionPane message
        mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(null, "Connection Established"))
                .thenAnswer(invocation -> null);

        // Call the method
        Connection connection = DbUtil.connectDB();

        // Assertions
        assertNotNull(connection, "Connection should not be null");
        mockedDriverManager.verify(() -> DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Testing",
                "postgres",
                "0000"), times(1));
        mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(null, "Connection Established"), times(1));
    }

    @Test
    void testConnectDBFailure() throws Exception {
        // Mocking a failed connection
        mockedDriverManager.when(() -> DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/Testing",
                        "postgres",
                        "0000"))
                .thenThrow(new RuntimeException("Database connection failed"));

        // Simulate JOptionPane message
        mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(any(), anyString()))
                .thenAnswer(invocation -> null);

        // Call the method
        Connection connection = DbUtil.connectDB();

        // Assertions
        assertNull(connection, "Connection should be null on failure");
        mockedDriverManager.verify(() -> DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Testing",
                "postgres",
                "0000"), times(1));
        mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(null, "java.lang.RuntimeException: Database connection failed"), times(1));
    }
}
