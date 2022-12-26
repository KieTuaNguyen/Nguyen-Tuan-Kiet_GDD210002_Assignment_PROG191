package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BookManagementModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.Desktop;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controller.BookManagementController;
import javax.swing.SwingConstants;

public class BookManagementView extends JFrame {

	public BookManagementModel model;
	public BookManagementController controller;
	public JPanel contentPane;
	public JTextField textFieldID;
	public JTextField textFieldTitle;
	public JTextField textFieldPrice;
	public JTextField textFieldAuthor;
	public JTextField textFieldPublisher;
	public JTextField textFieldPublicationTime;
	public TableRowSorter<DefaultTableModel> sorter;
	public JTextField textFieldSearch;
	public JTable table;

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
		setTitle("Book Management System - 0.2.4");

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
		textFieldSearch.setBounds(454, 40, 246, 31);
		contentPane.add(textFieldSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(action);
		btnSearch.setFont(new Font("Verdana", Font.PLAIN, 17));
		btnSearch.setBackground(SystemColor.controlHighlight);
		btnSearch.setBounds(710, 40, 106, 31);
		contentPane.add(btnSearch);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(action);
		btnClear.setFont(new Font("Verdana", Font.PLAIN, 17));
		btnClear.setBackground(SystemColor.controlHighlight);
		btnClear.setBounds(826, 40, 80, 31);
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

				},
				new String[] {
						"ID", "Title", "Price (VND)", "Author", "Publisher", "Publication Time"
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

		JButton btnSumOfPrice = new JButton("Total Price");
		btnSumOfPrice.addActionListener(action);
		btnSumOfPrice.setFont(new Font("Verdana", Font.PLAIN, 17));
		btnSumOfPrice.setBackground(SystemColor.controlHighlight);
		btnSumOfPrice.setBounds(924, 40, 154, 31);
		contentPane.add(btnSumOfPrice);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(914, 40, 2, 31);
		contentPane.add(separator_1);
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
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		textFieldID.setText(model_table.getValueAt(i, 0).toString());
		textFieldTitle.setText(model_table.getValueAt(i, 1).toString());
		textFieldPrice.setText(model_table.getValueAt(i, 2).toString());
		textFieldAuthor.setText(model_table.getValueAt(i, 3).toString());
		textFieldPublisher.setText(model_table.getValueAt(i, 4).toString());
		textFieldPublicationTime.setText(model_table.getValueAt(i, 5).toString());
	}

}
