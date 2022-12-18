package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JOptionPane;

import model.Book;
import view.BookManagementView;

public class BookManagementController implements Action {
	public BookManagementView view;

	public BookManagementController(BookManagementView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		switch (cm) {
			case "Insert":
				this.view.addBook();
				break;
			case "Update":
				this.view.update();
				break;
			case "Delete":
				this.view.delete();
				break;
			case "Cancel":
				this.view.resetForm();
				break;
			case "Search":
				this.view.search();
				break;
			case "Clear":
				this.view.clearFilter();
				break;
			case "Import":
				this.view.importFile();
				break;
			case "Upload File":
				this.view.importFile();
				break;
			case "Export":
				this.view.exportFile();
				break;
			case "Exit":
				this.view.exit();
				break;
			default:
				break;
		}
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

}
