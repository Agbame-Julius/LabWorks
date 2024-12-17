package com.example.library_management_system;
import model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testBookConstructor() {
        // Create a Book object using the parameterized constructor
        Book book = new Book(1, "Title", "Author", "ISBN123", "Genre", "Available");

        // Verify values are initialized correctly
        assertEquals(1, book.getBookID());
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals("ISBN123", book.getIsbn());
        assertEquals("Genre", book.getGenre());
        assertEquals("Available", book.getAvailability());
    }

    @Test
    public void testDefaultConstructorAndSetters() {
        // Create a Book object using the no-argument constructor
        Book book = new Book();

        // Set values using setters
        book.setBookID(2);
        book.setTitle("Another Title");
        book.setAuthor("Another Author");
        book.setIsbn("ISBN456");
        book.setGenre("Another Genre");
        book.setAvailability("Unavailable");

        // Verify values using getters
        assertEquals(2, book.getBookID());
        assertEquals("Another Title", book.getTitle());
        assertEquals("Another Author", book.getAuthor());
        assertEquals("ISBN456", book.getIsbn());
        assertEquals("Another Genre", book.getGenre());
        assertEquals("Unavailable", book.getAvailability());
    }

    @Test
    public void testNullValues() {
        // Create a Book object and set null values
        Book book = new Book();
        book.setTitle(null);
        book.setAuthor(null);

        // Verify that null values are handled correctly
        assertNull(book.getTitle());
        assertNull(book.getAuthor());
    }

    @Test
    @DisplayName("Edge case running")
    public void testEdgeCases() {
        // Test an edge case (e.g., very long title or invalid characters)
        String longTitle = "A".repeat(1000);
        Book book = new Book();
        book.setTitle(longTitle);

        // Verify the long title is handled
        assertEquals(longTitle, book.getTitle());
    }
}

