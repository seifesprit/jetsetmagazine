package tn.edu.esprit.info.jetsetmagasine.gui.model;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;

public class ModelActuality extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModelActuality frame = new ModelActuality();
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
	public ModelActuality() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_center = new JPanel();
		contentPane.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_center.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnComment = new JButton("Comment");
		panel_1.add(btnComment);
		
		JButton btnLike = new JButton("Like");
		panel_1.add(btnLike);
		
		JPanel panel_2 = new JPanel();
		panel_center.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitre = new JLabel("Titre");
		panel_2.add(lblTitre, BorderLayout.NORTH);
		
		JEditorPane dtrpnDescription = new JEditorPane();
		dtrpnDescription.setEditable(false);
		dtrpnDescription.setText("description");
		panel_2.add(dtrpnDescription, BorderLayout.CENTER);
		
		JLabel lblimage = new JLabel("New label");
		panel_center.add(lblimage, BorderLayout.NORTH);
		
		JScrollBar scrollBar = new JScrollBar();
		panel_center.add(scrollBar, BorderLayout.EAST);
	}

}
