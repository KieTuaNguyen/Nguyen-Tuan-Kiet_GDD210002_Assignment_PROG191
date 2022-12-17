package test;

import java.util.*;
import javax.swing.*;
import view.BookManagementView;

public class Test {
  public static void main(String[] args) {
    BookManagementView bookManagementView = new BookManagementView();
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}