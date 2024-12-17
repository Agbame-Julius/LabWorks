package com.example.library_management_system;
import model.Transaction;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void testParameterizedConstructor() {
        // Initialize test data
        int transactionId = 101;
        int bookId = 202;
        int patronId = 303;
        Date issueDate = Date.valueOf("2024-12-01");
        Date dueDate = Date.valueOf("2024-12-15");
        Date returnDate = Date.valueOf("2024-12-10");

        // Create a Transaction object using the parameterized constructor
        Transaction transaction = new Transaction(transactionId, bookId, patronId, issueDate, dueDate, returnDate);

        // Verify the values
        assertEquals(transactionId, transaction.getTransactionid());
        assertEquals(bookId, transaction.getBookid());
        assertEquals(patronId, transaction.getPatronid());
        assertEquals(issueDate, transaction.getIssuedate());
        assertEquals(dueDate, transaction.getDuedate());
        assertEquals(returnDate, transaction.getReturndate());
    }

    @Test
    public void testSettersAndGetters() {
        // Create a Transaction object
        Transaction transaction = new Transaction(0, 0, 0, null, null, null);

        // Set values using setters
        transaction.setTransactionid(111);
        transaction.setBookid(222);
        transaction.setPatronid(333);
        Date issueDate = Date.valueOf("2024-11-01");
        Date dueDate = Date.valueOf("2024-11-15");
        Date returnDate = Date.valueOf("2024-11-10");
        transaction.setIssuedate(issueDate);
        transaction.setDuedate(dueDate);
        transaction.setReturndate(returnDate);

        // Verify values using getters
        assertEquals(111, transaction.getTransactionid());
        assertEquals(222, transaction.getBookid());
        assertEquals(333, transaction.getPatronid());
        assertEquals(issueDate, transaction.getIssuedate());
        assertEquals(dueDate, transaction.getDuedate());
        assertEquals(returnDate, transaction.getReturndate());
    }

    @Test
    public void testNullValues() {
        // Create a Transaction object with null dates
        Transaction transaction = new Transaction(101, 202, 303, null, null, null);

        // Verify that null values are handled correctly
        assertNull(transaction.getIssuedate());
        assertNull(transaction.getDuedate());
        assertNull(transaction.getReturndate());
    }

    @Test
    public void testEdgeCases() {
        // Test with edge case dates (e.g., far future date)
        Date issueDate = Date.valueOf("3000-01-01");
        Date dueDate = Date.valueOf("3000-01-15");
        Date returnDate = Date.valueOf("3000-01-10");
        Transaction transaction = new Transaction(1, 1, 1, issueDate, dueDate, returnDate);

        // Verify values
        assertEquals(issueDate, transaction.getIssuedate());
        assertEquals(dueDate, transaction.getDuedate());
        assertEquals(returnDate, transaction.getReturndate());
    }
}

