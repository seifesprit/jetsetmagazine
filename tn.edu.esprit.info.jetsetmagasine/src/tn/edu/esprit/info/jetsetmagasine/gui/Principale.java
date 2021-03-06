package tn.edu.esprit.info.jetsetmagasine.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import tn.edu.esprit.info.jetsetmagasine.domain.Actuality;
import tn.edu.esprit.info.jetsetmagasine.domain.Category;
import tn.edu.esprit.info.jetsetmagasine.gui.utilities.About;
import tn.edu.esprit.info.jetsetmagasine.gui.utilities.ConnectFacebook;
import tn.edu.esprit.info.jetsetmagasine.gui.utilities.GoogleDrive;
import tn.edu.esprit.info.jetsetmagasine.gui.utilities.LoginConfiguration;
import tn.edu.esprit.info.jetsetmagasine.services.business.impl.ActualityBusiness;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.ActualityDao;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.CategoryDao;

import javax.swing.JEditorPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Principale extends JFrame {

	private JPanel contentPane;
	private JTextField textField_recherche;
	private JLabel name_fb;
	private JLabel lblDate;
	private JLabel lblRecherche;
	private JPanel panel_right;
	private JLabel lblActuality;
	private JTabbedPane tabbedPane;
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
		setTitle("Crystalium Magasine");

		current = this;
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		connectFacebook = new ConnectFacebook(current);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		lblDate = new JLabel("Date");

		contentPane.setLayout(new BorderLayout(0, 0));

		panel_right = new JPanel();
		panel_right.setPreferredSize(new Dimension(650, 600));

		contentPane.add(panel_right, BorderLayout.EAST);
		panel_right.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
					islogin = false;
					panel_operation.setVisible(false);

				} else {

					connectFacebook.connect();

				}
			}
		});

		btnActuliser = new JButton("refresh");
		btnActuliser.setIcon(new ImageIcon(".\\resource\\refresh.png"));
		btnActuliser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshtable();
			}
		});
		toolBar.add(btnActuliser);

		JLabel lblJetsetmagasineDate = new JLabel(
				" CrystaliumMagasine     Date : ");
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
		btn_config.setIcon(new ImageIcon(".\\resource\\configuration.png"));

		JButton btnabout = new JButton("?");
		btnabout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setVisible(true);
			}
		});
		btnabout.setFont(new Font("Tahoma", Font.BOLD, 12));
		toolBar.add(btnabout);

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

		JLabel label_1 = new JLabel("");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(".\\resource\\2.png");
		Image scaledImage = image.getScaledInstance(300, 60,
				Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(scaledImage);
		label_1.setIcon(icon);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addGap(25)
						.addGroup(
								gl_panel_1
										.createParallelGroup(Alignment.LEADING)
										.addComponent(email_fb,
												GroupLayout.DEFAULT_SIZE, 69,
												Short.MAX_VALUE)
										.addComponent(name_fb,
												GroupLayout.DEFAULT_SIZE, 69,
												Short.MAX_VALUE))
						.addGap(312)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 301,
								GroupLayout.PREFERRED_SIZE)));
		gl_panel_1.setVerticalGroup(gl_panel_1
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel_1
								.createSequentialGroup()
								.addGap(5)
								.addComponent(name_fb,
										GroupLayout.PREFERRED_SIZE, 14,
										GroupLayout.PREFERRED_SIZE)
								.addGap(3)
								.addComponent(email_fb,
										GroupLayout.PREFERRED_SIZE, 14,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(62, Short.MAX_VALUE))
				.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 98,
						Short.MAX_VALUE));
		panel_1.setLayout(gl_panel_1);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				lblDate.setText(new Date().toLocaleString() + " \t");
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 1000);

		refreshtable();
		swingDescriptionActuality();
	}

	private JLabel lblTitre;
	private JLabel lblimage;
	private JEditorPane dtrpnDescription;
	private JPanel panel_operation;
	
	
	public void setValueActuality(Actuality actuality){
		
		InputStream is;
		try {
			is = GoogleDrive.downloadFile(actuality
					.getImage());
			Image image = ImageIO.read(is);
			ImageIcon icon = new ImageIcon(image);

			lblimage.setIcon(icon);
			
			lblTitre.setText(actuality.getTitre());
			
			dtrpnDescription.setText(actuality.getDescription());
			
		} catch (IOException ee) {
			JOptionPane.showMessageDialog(null,
					"Erreur Google Drive", "Erreur",
					JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	public JPanel getPanel_operation(){
		return panel_operation;
	}
	
	public void swingDescriptionActuality(){
		
		JPanel panel_center = new JPanel();
		contentPane.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));
		
		panel_operation = new JPanel();
		FlowLayout fl_panel_operation = (FlowLayout) panel_operation.getLayout();
		fl_panel_operation.setAlignment(FlowLayout.RIGHT);
		panel_center.add(panel_operation, BorderLayout.SOUTH);
		
		JButton btnComment = new JButton("Comment");
		panel_operation.add(btnComment);
		
		JButton btnLike = new JButton("Like");
		panel_operation.add(btnLike);
		
		JPanel panel_2 = new JPanel();
		panel_center.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		lblTitre = new JLabel();
		lblTitre.setFont(new Font("Arial", Font.BOLD, 14));
		panel_2.add(lblTitre, BorderLayout.NORTH);
		
		
		dtrpnDescription = new JEditorPane();
		dtrpnDescription.setBackground(SystemColor.control);
		dtrpnDescription.setEditable(false);
		panel_2.add(dtrpnDescription, BorderLayout.CENTER);
		
		lblimage = new JLabel("");
		panel_center.add(lblimage, BorderLayout.NORTH);
		
		JScrollBar scrollBar = new JScrollBar();
		panel_center.add(scrollBar, BorderLayout.EAST);
		
		contentPane.add(panel_center,BorderLayout.CENTER);
		panel_operation.setVisible(false);
	}
	
	
	public void refreshtable() {
		tabbedPane.removeAll();
		List<Category> categorys = CategoryDao.getInstanceof().findAll();

		for (int i = 0; i < categorys.size(); i++) {
			addTablePan(categorys.get(i));
		}

	}

	public void addTablePan(Category category) {

		try {

			JLayeredPane layeredPane = new JLayeredPane();

			tabbedPane.addTab(category.getLibelle(), null, layeredPane, null);
			layeredPane.setLayout(new BorderLayout(0, 0));
			final JTable table = new JTable();

			List<Actuality> actualities = ActualityBusiness.getIntanceof()
					.findByCategory(category.getId_auto());

			Object[][] objects = new Object[actualities.size()][6];
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

				i++;
			}
			table.setModel(new DefaultTableModel(objects, new String[] { "id",
					"Titre", "image", "Date / Heure", "Type"
					}) {
				Class[] columnTypes = new Class[] { Object.class, Object.class,
						ImageIcon.class, Object.class, Object.class
						};

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}

				boolean[] columnEditables = new boolean[] { false, false,
						false, false, false};

				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
				
			});

			table.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						JTable target = (JTable) e.getSource();
						int row = target.getSelectedRow();
						int column = target.getSelectedColumn();
						

							Actuality actuality = ActualityDao.getInstanceof()
									.findById(
											Integer.parseInt(table.getValueAt(
													row, 0).toString()));
							setValueActuality(actuality);
							
							
						
					}
				}
			});

			layeredPane.add(table, BorderLayout.CENTER);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			layeredPane.add(table.getTableHeader(), BorderLayout.PAGE_START);
			TableColumn column = table.getColumnModel().getColumn(1);
			column.setPreferredWidth(400);	
			column = table.getColumnModel().getColumn(0);
			column.setPreferredWidth(20);	
		} catch (Exception ee) {

		}
	}
}
