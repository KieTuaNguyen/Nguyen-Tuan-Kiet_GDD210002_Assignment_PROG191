package test;

import java.util.*;
import javax.swing.*;
import view.BookManagementView;

public class Test {
  public static void main(String[] args) {
    try {
      BookManagementView bookManagementView = new BookManagementView();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}