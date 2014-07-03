package tn.edu.esprit.info.jetsetmagasine.gui.interframe;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import tn.edu.esprit.info.jetsetmagasine.domain.Subscriber;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.SubscriberDao;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubscriberList extends JInternalFrame {
	private JTextField textField_filtre;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubscriberList frame = new SubscriberList();
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
	public SubscriberList() {
		setBounds(100, 100, 707, 496);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		panel.add(toolBar, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblFiltre = new JLabel("Filtre :");
		panel_1.add(lblFiltre);
		
		textField_filtre = new JTextField();
		panel_1.add(textField_filtre);
		textField_filtre.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		
		panel_2.add(table, BorderLayout.CENTER);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_2.add(table.getTableHeader(), BorderLayout.PAGE_START);
		refreshTable();

		getContentPane().add(panel_2, BorderLayout.CENTER);
		
	}
	
	public void refreshTable() {

		System.out.println("Refresh table");
		table.removeAll();

		List<Subscriber> subscribers = SubscriberDao.getInstanceof().findAll();

		Object[][] objects = new Object[subscribers.size()][4];
		int i = 0;

		for (Subscriber subscriber : subscribers) {

			objects[i][0] = subscriber.getId_auto();
			objects[i][1] = subscriber.getNom_prenom();
			objects[i][2] = subscriber.getEmail();
			objects[i][3] = subscriber.getId_fb();
			i++;
		}
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Id", "Nom prenom", "Email", "Id fb"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}

}
