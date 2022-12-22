package test.java;

import view.BookManagementView;
import controller.BookManagementController;
import model.Book;

import model.BookManagementModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.table.DefaultTableModel;

class BookManagementViewTest {
  public BookManagementModel model = new BookManagementModel();
  public static BookManagementView view = new BookManagementView();
  public BookManagementController controller = view.controller;

  @BeforeEach
  public void setUp() {
    Book expectedBook1 = new Book("1", "Test Case 1", 100, "No. 01", new Date(12 / 12 / 2022), "NXB C1");
    BookManagementController.addBookToTable(expectedBook1);
    Book expectedBook2 = new Book("2", "Test Case 2", 200, "No. 02", new Date(12 / 12 / 2022), "NXB C2");
    BookManagementController.addBookToTable(expectedBook2);
  }

  @Test
  void addBookTest() {
    DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
    view.table.setRowSelectionInterval(0, 0);
    Book expectedBook3 = new Book("3", "Test Case 3", 300, "No. 03", new Date(12 / 12 / 2022), "NXB C3");
    BookManagementController.addBookToTable(expectedBook3);
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
    BookManagementController.addBookToTable(expectedBook3);
    expectedBook3.setID("1");
    expectedBook3.setTitle("Test Case 1");
    expectedBook3.setPrice(100);
    expectedBook3.setAuthor("No. 01");
    expectedBook3.setPublisher("NXB C1");
    expectedBook3.setPublicationTime(new Date(12 / 12 / 2022));
    BookManagementController.updateToTable(expectedBook3);
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
    BookManagementController.deleteBook();
    assertEquals(1, model_table.getRowCount());
  }

}