package test.java;

import view.BookManagementView;

import org.junit.jupiter.api.Test;

import model.Book;
import model.BookManagementModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.swing.JOptionPane;

class BookManagementViewTest {
  // test for the case when the user inputs the blank value for all the fields

  public BookManagementModel model;

  @Test
  void testSearching() {
    BookManagementView bookManagementView = new BookManagementView();
    String id = "01";
    String title = "Cuon sach thu nhat";
    float price = 12.5f;
    String author = "Test Author";
    Date publicationTime = new Date(2022, 12, 12);
    String publisher = "Test Publisher";
    Book book = new Book(id, title, price, author, publicationTime, publisher);
    this.model.insert(book);
    // assert gi do
  }
}
