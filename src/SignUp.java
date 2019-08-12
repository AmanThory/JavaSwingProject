import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SignUp extends JFrame {
	
	Connection con;
	PreparedStatement pst ;
	ResultSet rs ;
	String sql;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField textField_2;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SignUp frame = new SignUp();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public SignUp() {
		
		super("Sign Up");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 612);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 128, 128), 3), "Sign_Up", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textField_2 = new JPasswordField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setColumns(10);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblSequir = new JLabel("Security Question");
		lblSequir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_3 = new JLabel("Answer");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_5.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "H.O.D", "Teacher", "Student"}));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					con = Database.getConnection();
					
					if(textField.getText().trim().equals("") || textField_1.getText().trim().equals("") || textField_2.getText().trim().equals("") || textField_5.getText().trim().equals(""))
					{
						JOptionPane.showMessageDialog(null, "You can not leave Input Field Empty Please check Input Field Properly !!");
						
					}else {
					
						sql = "select * from user where user_name = '"+textField_1.getText()+"' ";
						pst = con.prepareStatement(sql);
						rs = pst.executeQuery();
						
						if(rs.next()) {
							
							JOptionPane.showMessageDialog(null, "Username is already Exist in Database try something else .");
							textField_1.setText("");
						
						}else {
							
							sql = "insert into user(name,user_name,password,role,secu_q,answer,status) values(?,?,?,?,?,?,?)";
							pst = con.prepareStatement(sql);
							pst.setString(1, textField.getText());
							pst.setString(2, textField_1.getText());
							pst.setString(3, textField_2.getText());
							pst.setString(4, (String) comboBox.getSelectedItem());
							pst.setString(5, (String) comboBox_1.getSelectedItem());
							pst.setString(6, textField_5.getText());
							pst.setString(7, "PENDING");
							pst.execute();
							
							JOptionPane.showMessageDialog(null, "Account has been created successfully\n "
									+ "You just need to wait until we verify your details so till now your account status is pending "
									+ "After verify your account details we will active your account status");
							
							con.close();
							
							setVisible(false);
							Login login = new Login();
							login.setVisible(true);	
						}
						
					}
					
				}catch(Exception e) {
				
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnLoginHere = new JButton("Login Here");
		btnLoginHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
				Login login = new Login();
				login.setVisible(true);
				
			}
		});
		btnLoginHere.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"what is your mother toungue ?", "what is your nick name ?", "what is the name of your childhood friend ?", "what is your school name ?"}));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSequir)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(lblRole)
								.addComponent(lblNewLabel_3))
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, 250, 250, 250))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
										.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_1, 250, 250, 250)
										.addComponent(textField_2, 250, 250, 250)
										.addComponent(comboBox, Alignment.TRAILING, 0, 250, Short.MAX_VALUE)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnSubmit)
							.addPreferredGap(ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
							.addComponent(btnLoginHere)))
					.addGap(228))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRole)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSequir)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmit)
						.addComponent(btnLoginHere))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
