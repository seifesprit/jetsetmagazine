package tn.edu.esprit.info.jetsetmagasine.gui.interframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ActualityAdd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel label_image;
	private String IMG_PATH = null;

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
		setBounds(100, 100, 499, 466);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		JLabel lblTitre = new JLabel("Titre : ");
		textField = new JTextField();
		textField.setColumns(10);
		JLabel lblDescription = new JLabel("Description :");

		JTextPane textPane = new JTextPane();

		JLabel lblCategory = new JLabel("Category");

		JComboBox comboBox = new JComboBox();

		JLabel lblImage = new JLabel("image : ");

		JButton btnParcourir = new JButton("Parcourir");
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
					label_image.setText(file.getPath() + " " + file.getName()
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
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblDescription)
																						.addComponent(
																								lblTitre))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								textPane)
																						.addComponent(
																								textField,
																								GroupLayout.DEFAULT_SIZE,
																								295,
																								Short.MAX_VALUE)))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblCategory)
																						.addComponent(
																								lblImage))
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addGap(18)
																										.addGroup(
																												gl_contentPanel
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																label_image)
																														.addComponent(
																																comboBox,
																																0,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)))
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_contentPanel
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																btnShowImage)
																														.addComponent(
																																btnParcourir))))))
										.addContainerGap(104, Short.MAX_VALUE)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblTitre)
														.addComponent(
																textField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblDescription)
														.addComponent(
																textPane,
																GroupLayout.PREFERRED_SIZE,
																85,
																GroupLayout.PREFERRED_SIZE))
										.addGap(12)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblCategory)
														.addComponent(
																comboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblImage)
														.addComponent(
																label_image)
														.addComponent(
																btnParcourir))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnShowImage)
										.addContainerGap(168, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
