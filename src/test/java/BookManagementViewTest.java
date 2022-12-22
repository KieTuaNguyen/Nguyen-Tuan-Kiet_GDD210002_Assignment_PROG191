package test.java;

import view.BookManagementView;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookManagementController;
import model.Book;
import model.BookManagementModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class BookManagementViewTest {
  public BookManagementModel model = new BookManagementModel();
  public static BookManagementView view = new BookManagementView();
  public BookManagementController controller = view.controller;

  public static void addBookToTable(Book book) {
    DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
    model_table.addRow(new Object[] { book.getID(),
        book.getTitle(),
        book.getPrice(),
        book.getAuthor(),
        book.getPublisher(),
        book.getPublicationTime() });
  }

  private void updateToTable(Book book) {
    DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
    model_table.setValueAt(book.getID(), view.table.getSelectedRow(), 0);
    model_table.setValueAt(book.getTitle(), view.table.getSelectedRow(), 1);
    model_table.setValueAt(book.getPrice(), view.table.getSelectedRow(), 2);
    model_table.setValueAt(book.getAuthor(), view.table.getSelectedRow(), 3);
    model_table.setValueAt(book.getPublisher(), view.table.getSelectedRow(), 4);
    model_table.setValueAt(book.getPublicationTime(), view.table.getSelectedRow(), 5);
  }

  private void deleteBook() {
    DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
    model_table.removeRow(view.table.getSelectedRow());
  }

  @BeforeEach
  public void setUp() {
    Book expectedBook1 = new Book("1", "Test Case 1", 100, "No. 01", new Date(12 / 12 / 2022), "NXB C1");
    addBookToTable(expectedBook1);
    Book expectedBook2 = new Book("2", "Test Case 2", 200, "No. 02", new Date(12 / 12 / 2022), "NXB C2");
    addBookToTable(expectedBook2);
  }

  @Test
  void addBookTest() {
    DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
    view.table.setRowSelectionInterval(0, 0);
    Book expectedBook3 = new Book("3", "Test Case 3", 300, "No. 03", new Date(12 / 12 / 2022), "NXB C3");
    addBookToTable(expectedBook3);
    assertEquals(expectedBook3.getID(), model_table.getValueAt(2, 0));
    assertEquals(expectedBook3.getTitle(), model_table.getValueAt(2, 1));
    assertEquals(expectedBook3.getPrice(), model_table.getValueAt(2, 2));
    assertEquals(expectedBook3.getAuthor(), model_table.getValueAt(2, 3));
    assertEquals(expectedBook3.getPublisher(), model_table.getValueAt(2, 4));
    assertEquals(expectedBook3.getPublicationTime(), model_table.getValueAt(2, 5));
  }

  @Test
  void updateTest() {
    DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
    view.table.setRowSelectionInterval(0, 0);
    Book expectedBook3 = new Book("3", "Test Case 3", 300, "No. 03", new Date(12 / 12 / 2022), "NXB C3");
    addBookToTable(expectedBook3);
    expectedBook3.setID("1");
    expectedBook3.setTitle("Test Case 1");
    expectedBook3.setPrice(100);
    expectedBook3.setAuthor("No. 01");
    expectedBook3.setPublisher("NXB C1");
    expectedBook3.setPublicationTime(new Date(12 / 12 / 2022));
    updateToTable(expectedBook3);
    assertEquals(expectedBook3.getID(), model_table.getValueAt(0, 0));
    assertEquals(expectedBook3.getTitle(), model_table.getValueAt(0, 1));
    assertEquals(expectedBook3.getPrice(), model_table.getValueAt(0, 2));
    assertEquals(expectedBook3.getAuthor(), model_table.getValueAt(0, 3));
    assertEquals(expectedBook3.getPublisher(), model_table.getValueAt(0, 4));
    assertEquals(expectedBook3.getPublicationTime(), model_table.getValueAt(0, 5));
  }

  @Test
  void deleteTest() {
    DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
    view.table.setRowSelectionInterval(0, 0);
    deleteBook();
    assertEquals(1, model_table.getRowCount());
  }
}