package tn.edu.esprit.info.jetsetmagasine.gui.interframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import tn.edu.esprit.info.jetsetmagasine.domain.Actuality;
import tn.edu.esprit.info.jetsetmagasine.gui.utilities.GoogleDrive;
import tn.edu.esprit.info.jetsetmagasine.services.business.impl.ActualityBusiness;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.ActualityDao;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ActualityList extends JInternalFrame {
	private JTextField textField_filtre;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActualityList frame = new ActualityList();
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
	public ActualityList() {
		setMaximizable(true);
		setResizable(true);

		setClosable(true);
		setBounds(100, 100, 707, 496);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		panel.add(toolBar, BorderLayout.NORTH);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualityAdd actualityAdd = new ActualityAdd();
				actualityAdd.setVisible(true);
			}
		});
		toolBar.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		toolBar.add(btnUpdate);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(table.getSelectedRowCount() == 0)
						return;
					ActualityDao.getInstanceof().remove(
							new Actuality(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()),
									"", "", "", new Date() ,new Date(),false,"",null,null,""));
					refreshTable();
				} catch (Exception ee) {

				}
			}
		});
		toolBar.add(btnRemove);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount() == 0)
					return;
				ActualityBusiness.getIntanceof().changeValide(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()), ! Boolean.parseBoolean(table.getValueAt(table.getSelectedRow(), 6).toString()));
				refreshTable();
			}
		});
		toolBar.add(btnValider);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_1, BorderLayout.SOUTH);

		JLabel lblFiltre = new JLabel("Filtre :");

		textField_filtre = new JTextField();
		textField_filtre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refreshTable();
			}
		});
		textField_filtre.setColumns(10);
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.add(lblFiltre, BorderLayout.WEST);
		panel_1.add(textField_filtre);

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
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					if (column == 2) {

						Actuality actuality = ActualityDao.getInstanceof().findById(Integer.parseInt(table.getValueAt(row, 0).toString()));
						System.out.println("id ="+ table.getValueAt(row, 0).toString());
						InputStream is;
						try {
							is = GoogleDrive.downloadFile(actuality.getImage());
							Image image = ImageIO.read(is);
							ImageIcon icon = new ImageIcon(image);
							JLabel label = new JLabel(icon);
							JOptionPane.showMessageDialog(null, label);
							
						} catch (IOException ee) {
							JOptionPane.showMessageDialog(null,
								    "Erreur Google Drive",
								    "Erreur",
								    JOptionPane.WARNING_MESSAGE);
						}		
					}
				}
			}

		});

	}

	public void refreshTable() {

		System.out.println("Refresh table");
		table.removeAll();

		List<Actuality> actualities = ActualityBusiness.getIntanceof().findByWordKey(textField_filtre.getText());

		Object[][] objects = new Object[actualities.size()][7];
		int i = 0;
		ImageIcon icon = new ImageIcon(".\\resource\\image.png");
		JLabel label = new JLabel(icon);
		for (Actuality actuality : actualities) {

			objects[i][0] = actuality.getId_auto();
			objects[i][1] = actuality.getTitre();
			objects[i][2] = icon;
			objects[i][3] = actuality.getDate_redaction();
			objects[i][4] = actuality.getType();
			objects[i][5] = actuality.getCategory();
			objects[i][6] = actuality.isValide();
			i++;
		}
		table.setModel(new DefaultTableModel(objects,
				new String[] { "id", "Titre", "image", "Date / Heure", "Type",
						"Categorie", "Valide" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class,
					ImageIcon.class, Object.class, Object.class, Object.class,
					Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});
	}

}
