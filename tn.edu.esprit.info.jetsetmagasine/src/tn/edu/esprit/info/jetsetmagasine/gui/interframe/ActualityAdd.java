package tn.edu.esprit.info.jetsetmagasine.gui.interframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import tn.edu.esprit.info.jetsetmagasine.domain.Actuality;
import tn.edu.esprit.info.jetsetmagasine.domain.Category;
import tn.edu.esprit.info.jetsetmagasine.gui.Principale;
import tn.edu.esprit.info.jetsetmagasine.gui.PrincipaleTwo;
import tn.edu.esprit.info.jetsetmagasine.gui.utilities.GoogleDrive;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.ActualityDao;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.CategoryDao;

public class ActualityAdd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_titre;
	private JLabel label_image;
	private String IMG_PATH = null;
	private JTextField textField_source;
	private JRadioButton rdbtnSon;
	private JRadioButton rdbtnVideo;
	private JRadioButton rdbtnImage;
	private JSpinner spinner_date;
	private JTextPane text_description;
	private JComboBox comboBox_category;
	private String id_image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActualityAdd frame = new ActualityAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public ActualityAdd() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {

				Category category = (Category) comboBox_category
						.getSelectedItem();

				comboBox_category.removeAllItems();
				List<Category> categories = CategoryDao.getInstanceof()
						.findAll();
				for (Category category2 : categories) {
					comboBox_category.addItem(category2);
				}

				if (category != null) {
					comboBox_category.setSelectedItem(category);
				}
			}
		});

		setBounds(100, 100, 559, 471);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		JLabel lblTitre = new JLabel("Titre : ");
		textField_titre = new JTextField();
		textField_titre.setColumns(10);
		JLabel lblDescription = new JLabel("Description :");

		text_description = new JTextPane();

		JLabel lblCategory = new JLabel("Category");

		comboBox_category = new JComboBox();

		JLabel lblImage = new JLabel("Image : ");

		JButton btnParcourir = new JButton("Choose image");
		btnParcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"ALL Images", "jpg", "gif", "png");
				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showOpenDialog(contentPanel);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					// This is where a real application would open the file.
					label_image.setText(file.getName()
							+ "");
					IMG_PATH = file.getPath();
				} else {
					IMG_PATH = null;
					label_image.setText("no image");
				}
			}
		});

		label_image = new JLabel("");

		JButton btnShowImage = new JButton("show image");
		btnShowImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage img;
				try {
					if (IMG_PATH != null) {
						
						img = ImageIO.read(new File(IMG_PATH));
						ImageIcon icon = new ImageIcon(img);
						JLabel label = new JLabel(icon);
						JOptionPane.showMessageDialog(null, label);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JLabel lblSource = new JLabel("Source :");

		textField_source = new JTextField();
		textField_source.setColumns(10);

		rdbtnSon = new JRadioButton("Son");

		rdbtnVideo = new JRadioButton("Video");

		rdbtnImage = new JRadioButton("image");

		rdbtnImage.setSelected(true);

		ButtonGroup buttonGroup = new ButtonGroup();

		buttonGroup.add(rdbtnImage);
		buttonGroup.add(rdbtnVideo);
		buttonGroup.add(rdbtnSon);

		JLabel lblType = new JLabel("Type :");

		spinner_date = new JSpinner();
		spinner_date.setModel(new SpinnerDateModel(new Date(), null, null,
				Calendar.DAY_OF_YEAR));

		JLabel lblDateHeure = new JLabel("Date / Heure :");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGap(1)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblImage)
									.addComponent(lblType))
								.addPreferredGap(ComponentPlacement.RELATED, 477, Short.MAX_VALUE))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblDateHeure)
											.addComponent(lblCategory))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(rdbtnImage)
												.addGap(13)
												.addGap(54)
												.addComponent(rdbtnVideo)
												.addGap(43)
												.addComponent(rdbtnSon))
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(spinner_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_source, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
												.addComponent(comboBox_category, 0, 374, Short.MAX_VALUE))
											.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(label_image, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnParcourir)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnShowImage))))
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblDescription)
											.addComponent(lblTitre))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(textField_titre, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
											.addComponent(text_description, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))))
								.addGap(42)))
						.addComponent(lblSource))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitre)
						.addComponent(textField_titre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescription)
						.addComponent(text_description, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_category, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategory))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnShowImage)
								.addComponent(btnParcourir)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_image, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblImage))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSource)
						.addComponent(textField_source, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(rdbtnImage)
						.addComponent(rdbtnSon)
						.addComponent(rdbtnVideo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDateHeure)
						.addComponent(spinner_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(119, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						String type = "";
						if (rdbtnImage.isSelected())
							type = "image";
						if (rdbtnSon.isSelected())
							type = "son";
						if (rdbtnVideo.isSelected())
							type = "video";
						
						try {
							
							id_image = GoogleDrive.uplodeGoogleDrive(IMG_PATH);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						Actuality actuality = new Actuality(0, textField_titre
								.getText(), text_description.getText(), type,
								new Date(),(Date) spinner_date.getValue(), false, id_image,
								PrincipaleTwo.leader,
								(Category) comboBox_category.getSelectedItem(),
								textField_source.getText());
						System.out.println(actuality);

						ActualityDao.getInstanceof().add(actuality);
						
						dispose();

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
