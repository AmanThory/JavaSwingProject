import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CollegeWebsite extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
////					CollegeWebsite frame = new CollegeWebsite();
////					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public CollegeWebsite(Map<String, String> user_data) {
		
//		System.out.print(user_data);
		
		setResizable(false);
		setTitle("NIIT College");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1215, 779);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(46, 139, 87));
		contentPane.setBorder(new LineBorder(SystemColor.menu));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("window"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(245, 245, 245)));
		panel_1.setBackground(new Color(245, 245, 245));
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1207, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(8))
		);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon("F:\\eclipse oxygen workspace\\Authentication\\src\\niit.gif"));
		
		JLabel lblNewLabel_1 = new JLabel("Welcome !! "+user_data.get("name")+"  Your are the "+user_data.get("role")+" of NIIT");
		lblNewLabel_1.setForeground(new Color(60, 179, 113));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		

		JButton btnNewButton = new JButton(user_data.get("role") + " Panel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(user_data.get("role").equals("Admin")) {
					
					setVisible(false);
					Dashboard dash = new Dashboard(user_data);
					dash.setVisible(true);
					
				}else if(user_data.get("role").equals("Student")) {				
						
					
				}else if(user_data.get("role").equals("Teacher")) {
					
				
				}else if(user_data.get("role").equals("H.O.D")) {
					
					
				}else {
					
				}
			}
		});

		btnNewButton.setForeground(new Color(60, 179, 113));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(UIManager.getColor("Button.highlight"));
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 992, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(12)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 807, GroupLayout.PREFERRED_SIZE)
					.addGap(67))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(984, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(113, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNiitCollege_1 = new JLabel("NIIT College \u00A9 2019");
		lblNiitCollege_1.setForeground(new Color(46, 139, 87));
		lblNiitCollege_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblDevelopedByAman = new JLabel("Developed by Aman Thory");
		lblDevelopedByAman.setForeground(new Color(46, 139, 87));
		lblDevelopedByAman.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNiitCollege_1, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 397, Short.MAX_VALUE)
					.addComponent(lblDevelopedByAman)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblNiitCollege_1, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addComponent(lblDevelopedByAman, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNiitCollege = new JLabel("NIIT College");
		lblNiitCollege.setForeground(new Color(46, 139, 87));
		lblNiitCollege.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(comboBox.getSelectedItem().equals("Log out")) {
					
					setVisible(false);
					Login login = new Login();
					login.setVisible(true);
					
				}else if(comboBox.getSelectedItem().equals("Profile")) {
					
//					setVisible(false);
//					Profile profile = new Profile();
//					profile.setVisible(true);
//					
				}else {
					
				}
					
				
			}
		});
		comboBox.setForeground(new Color(46, 139, 87));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {(String)user_data.get("name"), "Profile", "Log out"}));
		comboBox.setBackground(SystemColor.window);
		
		JLabel lblAboutUs = new JLabel("About Us");
		lblAboutUs.setForeground(new Color(46, 139, 87));
		lblAboutUs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblContactUs = new JLabel("Contact Us");
		lblContactUs.setForeground(new Color(46, 139, 87));
		lblContactUs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNiitCollege, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAboutUs, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblContactUs, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 669, Short.MAX_VALUE)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblNiitCollege, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
					.addComponent(lblAboutUs, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblContactUs, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
