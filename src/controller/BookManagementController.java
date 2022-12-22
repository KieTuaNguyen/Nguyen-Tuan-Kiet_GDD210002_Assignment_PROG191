package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.Book;
import model.BookManagementModel;
import view.BookManagementView;

public class BookManagementController implements Action {
	public static BookManagementView view;
	public TableRowSorter<DefaultTableModel> sorter;
	public BookManagementModel model;

	public BookManagementController(BookManagementView view) {
		this.view = view;
		this.model = view.model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		switch (cm) {
			case "Insert":
				alert();
				addBook();
				break;
			case "Update":
				update();
				break;
			case "Delete":
				delete();
				break;
			case "Cancel":
				resetForm();
				break;
			case "Search":
				search();
				break;
			case "Clear":
				clearFilter();
				break;
			case "Total Price":
				totalPrice();
				break;
			case "Import":
				importFile();
				break;
			case "Upload File":
				importFile();
				break;
			case "Export":
				exportFile();
				break;
			case "Exit":
				exit();
				break;
			default:
				break;
		}
	}

	public void alert() {
		if (view.textFieldID.getText().length() == 0
				&& view.textFieldTitle.getText().length() == 0
				&& view.textFieldPrice.getText().length() == 0
				&& view.textFieldAuthor.getText().length() == 0
				&& view.textFieldPublisher.getText().length() == 0
				&& view.textFieldPublicationTime.getText().length() == 0) {
			JOptionPane.showMessageDialog(view, "All fields are not empty");
			view.textFieldID.requestFocus();
			return;
		} else if (view.textFieldID.getText().length() == 0) {
			JOptionPane.showMessageDialog(view, "ID is not empty");
			view.textFieldID.requestFocus();
			return;
		} else if (view.textFieldTitle.getText().length() == 0) {
			JOptionPane.showMessageDialog(view, "Title is not empty");
			view.textFieldTitle.requestFocus();
			return;
		} else if (view.textFieldPrice.getText().length() == 0) {
			JOptionPane.showMessageDialog(view, "Price is not empty");
			view.textFieldPrice.requestFocus();
			return;
		} else if (view.textFieldAuthor.getText().length() == 0) {
			JOptionPane.showMessageDialog(view, "Author is not empty");
			view.textFieldAuthor.requestFocus();
			return;
		} else if (view.textFieldPublisher.getText().length() == 0) {
			JOptionPane.showMessageDialog(view, "Publisher is not empty");
			view.textFieldPublisher.requestFocus();
			return;
		} else if (view.textFieldPublicationTime.getText().length() == 0) {
			JOptionPane.showMessageDialog(view, "Publication time is not empty");
			view.textFieldPublicationTime.requestFocus();
			return;
		} else {
			return;
		}
	}

	public Book getBookfromTable() {
		String ID = view.textFieldID.getText();
		String title = view.textFieldTitle.getText();
		String author = view.textFieldAuthor.getText();
		Date publicationTime = new Date(view.textFieldPublicationTime.getText());
		String publisher = view.textFieldPublisher.getText();
		float price = 0;
		try {
			price = Float.parseFloat(view.textFieldPrice.getText());
			if (price < 0) {
				JOptionPane.showMessageDialog(null, "The price must be greater than 0.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "The price must be a number.");
		}
		return new Book(ID, title, price, author, publicationTime, publisher);
	}

	public void addBook() {
		try {
			String ID = view.textFieldID.getText();
			String title = view.textFieldTitle.getText();
			String author = view.textFieldAuthor.getText();
			Date publicationTime = null;
			try {
				publicationTime = new Date(view.textFieldPublicationTime.getText());
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "The publication time must be in the format dd/MM/yyyy.");
				return;
			}
			String publisher = view.textFieldPublisher.getText();
			float price = 0;
			try {
				price = Float.parseFloat(view.textFieldPrice.getText());
				if (price < 0) {
					JOptionPane.showMessageDialog(null, "The price must be greater than 0.");
					return;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "The price must be a number.");
			}
			Book book = new Book(ID, title, price, author, publicationTime, publisher);
			DefaultTableModel model_Table = (DefaultTableModel) view.table.getModel();
			if (model.isExist(book)) {
				JOptionPane.showMessageDialog(view, "The book already exists.");
				return;
			} else {
				try {
					model_Table.addRow(new Object[] { book.getID(),
							book.getTitle(),
							book.getPrice(),
							book.getAuthor(),
							book.getPublisher(),
							book.getPublicationTime() });
					model.insert(book);
					JOptionPane.showMessageDialog(view, "Add book successfully.");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(view, "Add book failed.");
				}
			}
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(view, "Add book failed.");
		}
	}

	public void update() {
		try {
			DefaultTableModel model_Table = (DefaultTableModel) view.table.getModel();
			int i = view.table.getSelectedRow();
			int[] rows = view.table.getSelectedRows();
			if (rows.length == 0) {
				JOptionPane.showMessageDialog(view, "Please select a row to update.");
				return;
			}
			if (sorter != null) {
				int rowInModel = sorter.convertRowIndexToModel(i);
				i = rowInModel;
			}
			if (i < 0) {
				JOptionPane.showMessageDialog(view, "Please select a row to update.");
				return;
			} else {
				Book book = getBookfromTable();
				model_Table.setValueAt(book.getID(), i, 0);
				model_Table.setValueAt(book.getTitle(), i, 1);
				model_Table.setValueAt(book.getPrice(), i, 2);
				model_Table.setValueAt(book.getAuthor(), i, 3);
				model_Table.setValueAt(book.getPublisher(), i, 4);
				model_Table.setValueAt(book.getPublicationTime(), i, 5);
				model.updateBook(book, i);
				JOptionPane.showMessageDialog(view, "Update successfully.");
			}
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "The publication time must be in the format dd/MM/yyyy.");
			return;
		}
	}

	public void delete() {
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		int[] rows = this.view.table.getSelectedRows();
		if (rows.length == 0) {
			JOptionPane.showMessageDialog(view, "Please select a row to delete.");
			return;
		}
		if (sorter != null) {
			for (int i = 0; i < rows.length; i++) {
				int rowDiff = sorter.convertRowIndexToModel(rows[i]);
				rows[i] = rowDiff;
			}
		}
		int option = JOptionPane.showConfirmDialog(view, "Are you sure to delete this book?");
		if (option == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(view, "Delete failed.");
		} else if (option == JOptionPane.YES_OPTION) {
			for (int i = 0; i < rows.length; i++) {
				model_table.removeRow(rows[i] - i);
				model.deleteBook(rows[i] - i);
			}
			JOptionPane.showMessageDialog(view, "Delete successfully.");
		}
		resetForm();
	}

	public void search() {
		if (view.textFieldSearch.getText().length() == 0) {
			JOptionPane.showMessageDialog(view, "Please enter the specified ID.");
			view.textFieldSearch.requestFocus();
		} else {
			String search = view.textFieldSearch.getText();
			RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter(search, 0);
			DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
			sorter = new TableRowSorter<>(model_table);
			sorter.setRowFilter(rf);
			view.table.setRowSorter(sorter);
		}
	}

	public void clearFilter() {
		view.textFieldSearch.setText("");
		RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("", 0);
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		sorter = new TableRowSorter<>(model_table);
		sorter.setRowFilter(rf);
		view.table.setRowSorter(sorter);
	}

	public void exit() {
		int option = JOptionPane.showConfirmDialog(view, "Do you want to exit this session?", "Exit",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void saveFile(String path) {
		try {
			this.model.setFileName(path);
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (Book book : this.model.getBooks()) {
				oos.writeObject(book);
			}
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportFile() {
		if (this.model.getFileName().length() > 0) {
			saveFile(this.model.getFileName());
		} else {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				saveFile(file.getAbsolutePath());
			}
		}
	}

	public void importFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(view);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			openFile(file);
			reloadTable();
		}
		JOptionPane.showMessageDialog(view, "Import file successfully!");
	}

	public void openFile(File file) {
		ArrayList<Book> book = new ArrayList<Book>();
		try {
			this.model.setFileName(file.getAbsolutePath());
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Book bk = null;
			while ((bk = (Book) ois.readObject()) != null) {
				book.add(bk);
			}
			ois.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.model.setBooks(book);
	}

	public void reloadTable() {
		while (true) {
			DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
			int rowNum = model_table.getRowCount();
			if (rowNum == 0)
				break;
			else
				try {
					model_table.removeRow(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		for (Book book : this.model.getBooks()) {
			try {
				model_table.addRow(new Object[] { book.getID(),
						book.getTitle(),
						book.getPrice(),
						book.getAuthor(),
						book.getPublisher(),
						book.getPublicationTime() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void totalPrice() {
		try {
			double total = 0;
			for (Book book : this.model.getBooks()) {
				total += book.getPrice();
			}
			if (total == 0) {
				JOptionPane.showMessageDialog(view, "Please import data!");
				return;
			} else {
				JOptionPane.showMessageDialog(view, "Total price: " + total);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resetForm() {
		view.textFieldID.setText("");
		view.textFieldTitle.setText("");
		view.textFieldPrice.setText("");
		view.textFieldAuthor.setText("");
		view.textFieldPublisher.setText("");
		view.textFieldPublicationTime.setText("");
		RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("", 0);
		DefaultTableModel model_table = (DefaultTableModel) this.view.table.getModel();
		sorter = new TableRowSorter<>(model_table);
		sorter.setRowFilter(rf);
		view.table.setRowSorter(sorter);
	}

	public static void addBookToTable(Book book) {
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		model_table.addRow(new Object[] { book.getID(),
				book.getTitle(),
				book.getPrice(),
				book.getAuthor(),
				book.getPublisher(),
				book.getPublicationTime() });
	}

	public static void updateToTable(Book book) {
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		model_table.setValueAt(book.getID(), view.table.getSelectedRow(), 0);
		model_table.setValueAt(book.getTitle(), view.table.getSelectedRow(), 1);
		model_table.setValueAt(book.getPrice(), view.table.getSelectedRow(), 2);
		model_table.setValueAt(book.getAuthor(), view.table.getSelectedRow(), 3);
		model_table.setValueAt(book.getPublisher(), view.table.getSelectedRow(), 4);
		model_table.setValueAt(book.getPublicationTime(), view.table.getSelectedRow(), 5);
	}

	public static void deleteBook() {
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		model_table.removeRow(view.table.getSelectedRow());
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	public void updateBook(Book book) {
		for (Book bk : this.model.getBooks()) {
			if (bk.getID().equals(book.getID())) {
				bk.setTitle(book.getTitle());
				bk.setPrice(book.getPrice());
				bk.setAuthor(book.getAuthor());
				bk.setPublisher(book.getPublisher());
				bk.setPublicationTime(new Date(view.textFieldPublicationTime.getText()));
			}
		}
		reloadTable();
	}

}