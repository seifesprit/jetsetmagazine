package tn.edu.esprit.info.jetsetmagasine.gui.utilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
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
	public About() {
		setTitle("About Crystalium Magasine");
		setResizable(false);
		setBounds(100, 100, 618, 243);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Aymen\\git\\jetsetmagazine\\tn.edu.esprit.info.jetsetmagasine\\resource\\logo_about.png"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		JLabel lblCrystaliumMagasine = new JLabel("Crystalium Magasine");
		lblCrystaliumMagasine.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblVersion = new JLabel("Version :");
		lblVersion.setFont(new Font("Arial", Font.BOLD, 11));
		
		JLabel lblTeam = new JLabel("Team :");
		lblTeam.setFont(new Font("Arial", Font.BOLD, 11));
		
		JLabel lblCm = new JLabel("1.0.0 CM");
		
		JLabel lblCrystaliumEsprit = new JLabel("Crystalium Esprit");
		
		JEditorPane dtrpnJetsetMagazineInfluences = new JEditorPane();
		dtrpnJetsetMagazineInfluences.setEditable(false);
		dtrpnJetsetMagazineInfluences.setFont(new Font("Arial", dtrpnJetsetMagazineInfluences.getFont().getStyle(), 11));
		dtrpnJetsetMagazineInfluences.setText("Jetset Magazine influences the readers on their purchasing decisions significantly. With reviews on subjects such as real estate, travel, fashion, jewelry, aviation, boating and cars, Jetset guide readers through a virtual experience in print and online .");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(dtrpnJetsetMagazineInfluences, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblTeam, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCrystaliumEsprit))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblVersion)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCm))
						.addComponent(lblCrystaliumMagasine))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCrystaliumMagasine)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVersion)
						.addComponent(lblCm))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTeam)
						.addComponent(lblCrystaliumEsprit))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dtrpnJetsetMagazineInfluences, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(label, BorderLayout.WEST);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_2.add(btnNewButton);
	}
}
