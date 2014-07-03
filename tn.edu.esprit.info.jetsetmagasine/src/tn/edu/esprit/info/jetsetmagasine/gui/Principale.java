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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	
	public JLabel getName_fb() {
		return name_fb;
	}

	public JLabel getPicture_fb() {
		return picture_fb;
	}

	public void setName_fb(JLabel name_fb) {
		this.name_fb = name_fb;
	}

	public void setPicture_fb(JLabel picture_fb) {
		this.picture_fb = picture_fb;
	}

	private String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};
	
    private Object[][] data = {
		    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		lblDate = new JLabel("Date");
		TimerTask task = new TimerTask()
		{
			@Override
			public void run() 
			{
				lblDate.setText(new Date().toLocaleString());
			}	
		};
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 1000);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_top = new JPanel();
		panel_top.setPreferredSize(new Dimension(450, 50));
		contentPane.add(panel_top, BorderLayout.NORTH);
		
		name_fb = new JLabel("");
		picture_fb = new JLabel("");
		
		
		textField_recherche = new JTextField();
		textField_recherche.setToolTipText("Recherche");
		textField_recherche.setColumns(10);
		
		JButton btnloginfb = new JButton("Login");
		btnloginfb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user = ConnectFacebook.getInstancesof(current).connect();
				
			}
		});
		
		lblRecherche = new JLabel("Recherche :");
		
		
		GroupLayout gl_panel_top = new GroupLayout(panel_top);
		gl_panel_top.setHorizontalGroup(
			gl_panel_top.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_top.createSequentialGroup()
					.addGap(9)
					.addComponent(picture_fb)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(name_fb)
					.addPreferredGap(ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
					.addComponent(lblDate)
					.addGap(18)
					.addComponent(lblRecherche)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_recherche, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnloginfb, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_top.setVerticalGroup(
			gl_panel_top.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_top.createSequentialGroup()
					.addGroup(gl_panel_top.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate)
						.addComponent(lblRecherche)
						.addComponent(textField_recherche, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnloginfb)
						.addComponent(name_fb)
						.addComponent(picture_fb))
					.addContainerGap())
		);
		panel_top.setLayout(gl_panel_top);
		
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
				
	}

	@Override
	public void tableChanged(TableModelEvent e) {

		int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        
        System.out.println(columnName +" : "+data);
		
	}
}
