package test;

import view.BookManagementView;

public class RunFile {
  public static void main(String[] args) {
    try {
      BookManagementView bookManagementView = new BookManagementView();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}