package model;

import java.util.ArrayList;
import java.util.Date;

public class BookManagementModel {
	private ArrayList<Book> books;
	private String option;

	private String fileName;

	public BookManagementModel() {
		this.books = new ArrayList<Book>();
		this.option = "";
		this.fileName = "";
	}

	public BookManagementModel(ArrayList<Book> books) {
		this.books = books;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void insert(Book book) {
		this.books.add(book);
	}

	public void updateBook(Book book, int i) {
		books.set(i, book);
	}

	public void delete(Book book) {
		this.books.remove(book);
	}

	public boolean isExist(Book book) {
		for (Book bk : this.books) {
			if (bk.getID().equals(book.getID())) {
				return true;
			}
		}
		return false;
	}
}