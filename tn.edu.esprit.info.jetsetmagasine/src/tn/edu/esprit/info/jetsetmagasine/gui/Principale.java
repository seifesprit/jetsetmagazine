package tn.edu.esprit.info.jetsetmagasine.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.util.Date;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.JTable;

import com.facebook.halo.application.types.User;

import tn.edu.esprit.info.jetsetmagasine.gui.model.MyTableModel;
import tn.edu.esprit.info.jetsetmagasine.gui.utilities.ConnectFacebook;
import tn.edu.esprit.info.jetsetmagasine.gui.utilities.LoginConfiguration;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JToolBar;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.border.LineBorder;

public class Principale extends JFrame implements TableModelListener {

	private JPanel contentPane;
	private JTextField textField_recherche;
	private JLabel name_fb;
	private JLabel lblDate;
	private JLabel lblRecherche;
	private JPanel panel_right;
	private JLabel lblActuality;
	private JTabbedPane tabbedPane;
	private JLayeredPane layeredPane;
	private JTable table;
	private Principale current;
	private JLabel picture_fb;
	private JButton btnLogin;
	private boolean islogin = false;
	private ConnectFacebook connectFacebook;

	
	public JLabel getEmail_fb() {
		return this.email_fb;
	}

	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JLabel getName_fb() {
		return name_fb;
	}

	public JLabel getPicture_fb() {
		return picture_fb;
	}

	private String[] columnNames = { "First Name", "Last Name", "Sport",
			"# of Years", "Vegetarian" };

	private Object[][] data = {
			{ "Kathy", "Smith", "Snowboarding", new Integer(5),
					new Boolean(false) },
			{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
			{ "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
			{ "Jane", "White", "Speed reading", new Integer(20),
					new Boolean(true) },
			{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };
	private JLabel email_fb;
	private JPanel panel_1;
	private JButton btn_config;
	private JButton btnActuliser;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principale frame = new Principale();
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
	public Principale() {
		
		current = this;
		connectFacebook = new ConnectFacebook(current);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		lblDate = new JLabel("Date");

		contentPane.setLayout(new BorderLayout(0, 0));

		panel_right = new JPanel();
		panel_right.setPreferredSize(new Dimension(450, 600));

		contentPane.add(panel_right, BorderLayout.EAST);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		layeredPane = new JLayeredPane();
		tabbedPane.addTab("tab1", null, layeredPane, null);
		layeredPane.setLayout(new BorderLayout(0, 0));

		table = new JTable(new MyTableModel());
		table.getModel().addTableModelListener(this);

		TableColumn sportColumn = table.getColumnModel().getColumn(2);

		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Snowboarding");
		comboBox.addItem("Rowing");
		comboBox.addItem("Chasing toddlers");
		comboBox.addItem("Speed reading");
		comboBox.addItem("Teaching high school");
		comboBox.addItem("None");
		sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

		layeredPane.add(table, BorderLayout.CENTER);

		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("tab2", null, layeredPane_1, null);
		panel_right.setLayout(new BorderLayout(0, 0));
		panel_right.add(tabbedPane, BorderLayout.CENTER);

		lblActuality = new JLabel("Actuality");
		lblActuality.setFont(new Font("Arial", Font.BOLD, 14));
		panel_right.add(lblActuality, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		panel.add(toolBar, BorderLayout.NORTH);

		btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(SystemColor.control);
		btnLogin.setIcon(new ImageIcon(
				".\\resource\\1404396201_678128-social-facebook.png"));
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (islogin) {
					
					connectFacebook.disconnect();
					name_fb.setText("");
					picture_fb.setIcon(null);
					email_fb.setText("");
					btnLogin.setText("Log in");
					islogin= false;
					
				} else {
															
					connectFacebook	.connect();

				}
			}
		});
		
		btnActuliser = new JButton("refresh");
		btnActuliser.setIcon(new ImageIcon(
				".\\resource\\refresh.png"));
		btnActuliser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(btnActuliser);

		JLabel lblJetsetmagasineDate = new JLabel("  JetSetMagasine     Date : ");
		toolBar.add(lblJetsetmagasineDate);

		toolBar.add(lblDate);

		JLabel lblTunisia = new JLabel("Tunisia");
		toolBar.add(lblTunisia);

		JLabel label = new JLabel("          ");
		toolBar.add(label);

		lblRecherche = new JLabel("Recherche :  ");
		toolBar.add(lblRecherche);

		textField_recherche = new JTextField();
		toolBar.add(textField_recherche);
		textField_recherche.setToolTipText("Recherche");
		textField_recherche.setColumns(10);
		toolBar.add(btnLogin);
		
		btn_config = new JButton("");
		btn_config.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginConfiguration.getinstanceof(current).setVisible(true);
			}
		});
		toolBar.add(btn_config);
		btn_config.setIcon(new ImageIcon(
				".\\resource\\configuration.png"));
		
		JPanel panel_top = new JPanel();
		panel.add(panel_top);
		panel_top.setPreferredSize(new Dimension(450, 50));
		picture_fb = new JLabel("");
		panel_top.setLayout(new BorderLayout(0, 0));
		panel_top.add(picture_fb, BorderLayout.WEST);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		panel_top.add(panel_1, BorderLayout.CENTER);
		
				name_fb = new JLabel("");
				name_fb.setBackground(Color.WHITE);
				
				email_fb = new JLabel("");
				GroupLayout gl_panel_1 = new GroupLayout(panel_1);
				gl_panel_1.setHorizontalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(email_fb, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(name_fb, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
							.addGap(362))
				);
				gl_panel_1.setVerticalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(name_fb, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(email_fb, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(14))
				);
				panel_1.setLayout(gl_panel_1);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				lblDate.setText(new Date().toLocaleString() + " \t");
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 1000);
	}

	@Override
	public void tableChanged(TableModelEvent e) {

		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel) e.getSource();
		String columnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);

		System.out.println(columnName + " : " + data);

	}
}
