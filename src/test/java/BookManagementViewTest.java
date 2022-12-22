package test.java;

import view.BookManagementView;

import org.junit.jupiter.api.Test;

import model.Book;
import model.BookManagementModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class BookManagementViewTest {

  public BookManagementModel model;
  public BookManagementView view;

  @Test
  void addBook() {
    // String ID = "01";
    // String title = "Cuon sach thu nhat";
    // float price = 12.5f;
    // String author = "Test Author";
    // Date publicationTime = new Date(2022, 12, 12);
    // String publisher = "Test Publisher";
    // Book expectedBook = new Book(ID, title, price, author, publicationTime,
    // publisher);
    Book expectedBook = new Book("1", "Test Case 1", 100, "Nguyen Van A", new Date(12 / 12 / 2022), "NXB A");
    DefaultTableModel model_table = (DefaultTableModel) this.view.table.getModel();
    model_table.addRow(new Object[] { expectedBook.getID(),
        expectedBook.getTitle(),
        expectedBook.getPrice(),
        expectedBook.getAuthor(),
        expectedBook.getPublisher(),
        expectedBook.getPublicationTime() });
    this.model.insert(expectedBook);
    assertSame(expectedBook, this.model.getBooks().get(0));
  }

  @Test
  void deleteBook() {
    Book expectedBook = new Book("1", "Test Case 1", 100, "Nguyen Van A", new Date(12 / 12 / 2022), "NXB A");
    DefaultTableModel model_table = (DefaultTableModel) this.view.table.getModel();
    model_table.addRow(new Object[] { expectedBook.getID(),
        expectedBook.getTitle(),
        expectedBook.getPrice(),
        expectedBook.getAuthor(),
        expectedBook.getPublisher(),
        expectedBook.getPublicationTime() });
    this.model.insert(expectedBook);
    this.model.delete(expectedBook);
    assertSame(0, this.model.getBooks().size());
  }

  @Test
  void updateBook() {
    Book expectedBook = new Book("1", "Test Case 1", 100, "Nguyen Van A", new Date(12 / 12 / 2022), "NXB A");
    DefaultTableModel model_table = (DefaultTableModel) this.view.table.getModel();
    model_table.addRow(new Object[] { expectedBook.getID(),
        expectedBook.getTitle(),
        expectedBook.getPrice(),
        expectedBook.getAuthor(),
        expectedBook.getPublisher(),
        expectedBook.getPublicationTime() });
    this.model.insert(expectedBook);
    expectedBook.setPrice(200);
    this.model.updateBook(expectedBook, 0);
    assertSame(expectedBook, this.model.getBooks().get(0));
  }

  @Test
  void addBlankBook() {
    Book expectedBook = new Book("", "", 0, "", new Date(12 / 12 / 2022), "");
    DefaultTableModel model_table = (DefaultTableModel) this.view.table.getModel();
    model_table.addRow(new Object[] { expectedBook.getID(),
        expectedBook.getTitle(),
        expectedBook.getPrice(),
        expectedBook.getAuthor(),
        expectedBook.getPublisher(),
        expectedBook.getPublicationTime() });
    this.model.insert(expectedBook);
    assertSame(expectedBook, this.model.getBooks().get(0));
  }
}