package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Book;
import model.BookManagementModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controller.BookManagementController;

public class BookManagementView extends JFrame {

	private JPanel contentPane;
	public BookManagementModel model;
	public JTextField textFieldID;
	public JTextField textFieldTitle;
	public JTextField textFieldPrice;
	public JTextField textFieldAuthor;
	public JTextField textFieldPublisher;
	public JTextField textFieldPublicationTime;
	public TableRowSorter<DefaultTableModel> sorter;
	private JTextField textFieldSearch;
	private JTable table;

	DefaultTableModel listBookTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManagementView frame = new BookManagementView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookManagementView() {
		this.model = new BookManagementModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1104, 729);
		setTitle("Book Management System - 0.0.1");

		Action action = new BookManagementController(this);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Fira Code", Font.PLAIN, 16));
		menuBar.add(menuFile);

		JMenuItem menuImport = new JMenuItem("Import");
		menuImport.addActionListener(action);
		menuImport.setFont(new Font("Fira Code", Font.PLAIN, 16));
		menuFile.add(menuImport);

		JMenuItem menuExport = new JMenuItem("Export");
		menuExport.addActionListener(action);
		menuExport.setFont(new Font("Fira Code", Font.PLAIN, 16));
		menuFile.add(menuExport);

		JSeparator separator = new JSeparator();
		menuFile.add(separator);

		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(action);
		menuExit.setFont(new Font("Fira Code", Font.PLAIN, 16));
		menuFile.add(menuExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelList = new JLabel("List of Book");
		labelList.setBounds(10, 291, 134, 20);
		contentPane.add(labelList);
		labelList.setFont(new Font("Fira Code", Font.PLAIN, 18));

		JLabel labelInformation = new JLabel("Book Information");
		labelInformation.setBounds(10, 80, 179, 20);
		contentPane.add(labelInformation);
		labelInformation.setFont(new Font("Fira Code", Font.PLAIN, 18));

		JLabel labelFilter = new JLabel("Filter");
		labelFilter.setBounds(10, 10, 67, 20);
		contentPane.add(labelFilter);
		labelFilter.setFont(new Font("Fira Code", Font.PLAIN, 18));

		textFieldID = new JTextField();
		textFieldID.setBounds(132, 136, 245, 28);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);

		JLabel labelID = new JLabel("ID");
		labelID.setFont(new Font("Fira Code", Font.PLAIN, 16));
		labelID.setBounds(46, 136, 80, 28);
		contentPane.add(labelID);

		JLabel labelTitle = new JLabel("Title");
		labelTitle.setFont(new Font("Fira Code", Font.PLAIN, 16));
		labelTitle.setBounds(46, 173, 80, 28);
		contentPane.add(labelTitle);

		textFieldTitle = new JTextField();
		textFieldTitle.setColumns(10);
		textFieldTitle.setBounds(132, 173, 245, 28);
		contentPane.add(textFieldTitle);

		JLabel labelPrice = new JLabel("Price");
		labelPrice.setFont(new Font("Fira Code", Font.PLAIN, 16));
		labelPrice.setBounds(46, 211, 80, 28);
		contentPane.add(labelPrice);

		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(132, 211, 245, 28);
		contentPane.add(textFieldPrice);

		JLabel labelAuthor = new JLabel("Author");
		labelAuthor.setFont(new Font("Fira Code", Font.PLAIN, 16));
		labelAuthor.setBounds(417, 134, 192, 28);
		contentPane.add(labelAuthor);

		textFieldAuthor = new JTextField();
		textFieldAuthor.setColumns(10);
		textFieldAuthor.setBounds(605, 134, 246, 28);
		contentPane.add(textFieldAuthor);

		JLabel labelPublisher = new JLabel("Publisher");
		labelPublisher.setFont(new Font("Fira Code", Font.PLAIN, 16));
		labelPublisher.setBounds(417, 171, 192, 28);
		contentPane.add(labelPublisher);

		textFieldPublisher = new JTextField();
		textFieldPublisher.setColumns(10);
		textFieldPublisher.setBounds(605, 171, 246, 28);
		contentPane.add(textFieldPublisher);

		JLabel labelPublicationTime = new JLabel("Publication Time");
		labelPublicationTime.setFont(new Font("Fira Code", Font.PLAIN, 16));
		labelPublicationTime.setBounds(417, 209, 192, 28);
		contentPane.add(labelPublicationTime);

		textFieldPublicationTime = new JTextField();
		textFieldPublicationTime.setColumns(10);
		textFieldPublicationTime.setBounds(605, 211, 246, 28);
		contentPane.add(textFieldPublicationTime);

		textFieldSearch = new JTextField();
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(628, 42, 246, 31);
		contentPane.add(textFieldSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(action);
		btnSearch.setFont(new Font("Verdana", Font.PLAIN, 17));
		btnSearch.setBackground(SystemColor.controlHighlight);
		btnSearch.setBounds(884, 42, 106, 31);
		contentPane.add(btnSearch);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(action);
		btnClear.setFont(new Font("Verdana", Font.PLAIN, 17));
		btnClear.setBackground(SystemColor.controlHighlight);
		btnClear.setBounds(1000, 42, 80, 31);
		contentPane.add(btnClear);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(action);
		btnInsert.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnInsert.setBackground(SystemColor.controlHighlight);
		btnInsert.setBounds(886, 170, 91, 31);
		contentPane.add(btnInsert);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(action);
		btnDelete.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnDelete.setBackground(SystemColor.controlHighlight);
		btnDelete.setBounds(886, 210, 91, 31);
		contentPane.add(btnDelete);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(action);
		btnUpdate.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnUpdate.setBackground(SystemColor.controlHighlight);
		btnUpdate.setBounds(987, 170, 91, 31);
		contentPane.add(btnUpdate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(action);
		btnCancel.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnCancel.setBackground(SystemColor.controlHighlight);
		btnCancel.setBounds(987, 208, 91, 31);
		contentPane.add(btnCancel);

		JButton btnUpload = new JButton("Upload File");
		btnUpload.addActionListener(action);
		btnUpload.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnUpload.setBackground(SystemColor.controlHighlight);
		btnUpload.setBounds(884, 133, 194, 31);
		contentPane.add(btnUpload);

		JSeparator separator1 = new JSeparator();
		separator1.setBackground(new Color(255, 255, 255));
		separator1.setBounds(87, 20, 993, 2);
		contentPane.add(separator1);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(197, 90, 885, 2);
		contentPane.add(separator2);

		JSeparator separator2_1 = new JSeparator();
		separator2_1.setBounds(154, 302, 926, 2);
		contentPane.add(separator2_1);

		table = new JTable();
		table.setFont(new Font("Fira Code Retina", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				// { "01", "Cuon Sach Thu Nhat", 11.0f, "Nguyen Tuan Kiet", "NXB Bao Dom",
				// "15/12/2022" },
				// { "02", "Cuon Sach Thu Hai", 12.0f, "Dang Van Tri Minh", "NXB Lien Minh",
				// "16/12/2022" },
				// { "03", "Cuon Sach Thu Ba", 13.0f, "Huynh Cong Chien", "NXB So Vo",
				// "21/12/2022" },
				},
				new String[] {
						"ID", "Title", "Price", "Author", "Publisher", "Publication Time"
				}));
		table.getColumnModel().getColumn(0).setPreferredWidth(38);
		table.getColumnModel().getColumn(1).setPreferredWidth(132);
		table.getColumnModel().getColumn(2).setPreferredWidth(39);
		table.getColumnModel().getColumn(3).setPreferredWidth(114);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableMouseClicked(evt);
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 321, 1070, 300);
		contentPane.add(scrollPane);

		JLabel labelRef = new JLabel("by KieTuaNguyen.");
		labelRef.setBackground(SystemColor.desktop);
		labelRef.setText("<html><a href='https://github.com/KieTuaNguyen'>by KieTuaNguyen.</a></html>");
		labelRef.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelRef.setFont(new Font("Fira Code Medium", Font.PLAIN, 11));
		labelRef.setBounds(497, 629, 112, 28);
		contentPane.add(labelRef);
		labelRef.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/KieTuaNguyen"));
				} catch (IOException | URISyntaxException ex) {
					ex.printStackTrace();
				}
			}
		});
		this.setVisible(true);
	}

	public void tableMouseClicked(java.awt.event.MouseEvent evt) {
		int i = table.getSelectedRow();
		if (sorter != null) {
			int rowInModel = sorter.convertRowIndexToModel(i);
			i = rowInModel;
		}
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		textFieldID.setText(model.getValueAt(i, 0).toString());
		textFieldTitle.setText(model.getValueAt(i, 1).toString());
		textFieldPrice.setText(model.getValueAt(i, 2).toString());
		textFieldAuthor.setText(model.getValueAt(i, 3).toString());
		textFieldPublisher.setText(model.getValueAt(i, 4).toString());
		textFieldPublicationTime.setText(model.getValueAt(i, 5).toString());
	}

	public void resetForm() {
		textFieldID.setText("");
		textFieldTitle.setText("");
		textFieldPrice.setText("");
		textFieldAuthor.setText("");
		textFieldPublisher.setText("");
		textFieldPublicationTime.setText("");
	}

	public Book getBookfromTable() {
		String ID = textFieldID.getText();
		String title = textFieldTitle.getText();
		String author = textFieldAuthor.getText();
		Date publicationTime = new Date(textFieldPublicationTime.getText());
		String publisher = textFieldPublisher.getText();
		float price = 0;
		try {
			price = Float.parseFloat(textFieldPrice.getText());
			if (price < 0) {
				JOptionPane.showMessageDialog(null, "The price must be greater than 0.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "The price must be a number.");
		}
		return new Book(ID, title, price, author, publicationTime, publisher);
	}

	public void insert(Book book) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if (this.model.isExist(book)) {
			JOptionPane.showMessageDialog(null, "The book is already exist.");
		} else {
			try {
				model.addRow(new Object[] { book.getID(),
						book.getTitle(),
						book.getPrice(),
						book.getAuthor(),
						book.getPublisher(),
						book.getPublicationTime() });
				this.model.insert(book);
				JOptionPane.showMessageDialog(null, "Insert successfully");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Insert failed");
			}
		}
	}

	public void addBook() {
		String ID = textFieldID.getText();
		String title = textFieldTitle.getText();
		String author = textFieldAuthor.getText();
		Date publicationTime = null;
		try {
			publicationTime = new Date(textFieldPublicationTime.getText());
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "The publication time must be in the format dd/MM/yyyy.");
			return;
		}
		String publisher = textFieldPublisher.getText();
		float price = 0;
		try {
			price = Float.parseFloat(textFieldPrice.getText());
			if (price < 0) {
				JOptionPane.showMessageDialog(null, "The price must be greater than 0.");
				return;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "The price must be a number.");
			return;
		}
		Book book = new Book(ID, title, price, author, publicationTime, publisher);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if (this.model.isExist(book)) {
			JOptionPane.showMessageDialog(null, "The book is already exist.");
		} else {
			try {
				model.addRow(new Object[] { book.getID(),
						book.getTitle(),
						book.getPrice(),
						book.getAuthor(),
						book.getPublisher(),
						book.getPublicationTime() });
				this.model.insert(book);
				JOptionPane.showMessageDialog(null, "Insert successfully");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Insert failed");
			}
		}

	}

	public void update() {
		DefaultTableModel modelTable = (DefaultTableModel) table.getModel();
		int i = table.getSelectedRow();
		if (sorter != null) {
			int rowInModel = sorter.convertRowIndexToModel(i);
			i = rowInModel;
		}
		if (i < 0) {
			return;
		} else {
			Book book = getBookfromTable();
			modelTable.setValueAt(book.getID(), i, 0);
			modelTable.setValueAt(book.getTitle(), i, 1);
			modelTable.setValueAt(book.getPrice(), i, 2);
			modelTable.setValueAt(book.getAuthor(), i, 3);
			modelTable.setValueAt(book.getPublisher(), i, 4);
			modelTable.setValueAt(book.getPublicationTime(), i, 5);
			model.updateBook(book, i);
			JOptionPane.showMessageDialog(this, "Update successfully.");
		}
	}

	public void delete() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int[] rows = table.getSelectedRows();
		if (rows.length == 0) {
			JOptionPane.showMessageDialog(this, "Please select a row to delete.");
			return;
		}
		if (sorter != null) {
			for (int i = 0; i < rows.length; i++) {
				int rowDiff = sorter.convertRowIndexToModel(rows[i]);
				rows[i] = rowDiff;
			}
		}
		int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete this book?");
		if (option == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(this, "Delete failed.");
		} else if (option == JOptionPane.YES_OPTION) {
			for (int i = 0; i < rows.length; i++) {
				model.removeRow(rows[i] - i);
			}
			JOptionPane.showMessageDialog(this, "Delete successfully.");
		}
		resetForm();
	}

	public void search() {
		outer: if (textFieldSearch.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "Please enter the specified ID.");
			textFieldSearch.requestFocus();
			break outer;
		} else {
			String search = textFieldSearch.getText();
			RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter(search, 0);
			DefaultTableModel model_table = (DefaultTableModel) table.getModel();
			sorter = new TableRowSorter<>(model_table);
			sorter.setRowFilter(rf);
			table.setRowSorter(sorter);
		}
	}

	public void clearFilter() {
		textFieldSearch.setText("");
		RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("", 0);
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		sorter = new TableRowSorter<>(model_table);
		sorter.setRowFilter(rf);
		table.setRowSorter(sorter);
	}

	public void exit() {
		int option = JOptionPane.showConfirmDialog(this, "Do you want to exit this session?", "Exit",
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
			int returnVal = fc.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				saveFile(file.getAbsolutePath());
			}
		}
	}

	public void importFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			openFile(file);
			reloadTable();
		}
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
			DefaultTableModel model_table = (DefaultTableModel) table.getModel();
			int numRow = model_table.getRowCount();
			if (numRow == 0)
				break;
			else
				try {
					model_table.removeRow(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	public void alert() {
		outer: if (textFieldID.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "ID is not empty");
			textFieldID.requestFocus();
			break outer;
		} else if (textFieldTitle.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "Title is not empty");
			textFieldTitle.requestFocus();
			break outer;
		} else if (textFieldPrice.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "Price is not empty");
			textFieldPrice.requestFocus();
			break outer;
		} else if (textFieldAuthor.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "Author is not empty");
			textFieldAuthor.requestFocus();
			break outer;
		} else if (textFieldPublisher.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "Publisher is not empty");
			textFieldPublisher.requestFocus();
			break outer;
		} else if (textFieldPublicationTime.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "Publication time is not empty");
			textFieldPublicationTime.requestFocus();
			break outer;
		} else {
			return;
		}
	}
}
