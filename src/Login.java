import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Login extends JFrame {

	Connection con;
//	PreparedStatement pst ;
	Statement st ;
	ResultSet rs ;
	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					
////				If we want to change the look and feel of Application You need to uncomment below lines both line has different 
//					meaning first line return green theme and second line return black theme. 
					
//					com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "my company");
//					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		super("Login");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 470);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 128, 128), 3), "Login", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 596, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(32, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		
		JLabel lblFirstName = new JLabel("User Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textField_1 = new JPasswordField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					con = Database.getConnection();
					
					if(textField.getText().trim().equals("") || textField_1.getText().trim().equals("") )
					{
						JOptionPane.showMessageDialog(null, "You can not leave Input Field Empty Please check Input Field Properly !!");
						
					}else {
						
						
						String sql = "select * from user where"
								+ " user_name ='"+textField.getText()+"' and password ='"+textField_1.getText()+"' and "
										+ "role = '"+(String)comboBox.getSelectedItem()+"' " ;	
						
						st = con.createStatement();
						rs = st.executeQuery(sql);
						
						if(rs.next()) {
							
							
							if(rs.getString("status").equals("BLOCK")) {
								
								JOptionPane.showMessageDialog(null, "You account has been Blocked Please contact with your admin for this issue!!");
								textField.setText("");
								textField_1.setText("");
								
							}else if(rs.getString("status").equals("PENDING") || rs.getString("status").equals("ACTIVE") ){
								
								
								if(rs.getString("status").equals("PENDING"))
										JOptionPane.showMessageDialog(null, "You account status is pending Please Contact Your Admin to"
												+ " activate your account !!");
								
								Map<String, String> user_data = new HashMap<String, String>();
								
								user_data.put("name",rs.getString("name"));
								user_data.put("user_name",rs.getString("user_name"));
								user_data.put("password",rs.getString("password"));
								user_data.put("role",rs.getString("role"));
								user_data.put("sec_q",rs.getString("secu_q"));
								user_data.put("answer",rs.getString("answer"));
								user_data.put("status",rs.getString("status"));
								
	//							System.out.print(map);
								
								setVisible(false);
								
								CollegeWebsite college = new CollegeWebsite(user_data);
								college.setVisible(true);
							}
			
						
						}else {
							JOptionPane.showMessageDialog(null, "!! Username and password are incorrect ");
							textField.setText("");
							textField_1.setText("");
						}
						
						rs.close();
						st.close();
					
					 }
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				setVisible(false);
				SignUp signup = new SignUp();
				signup.setVisible(true);
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNewButton_1 = new JButton("Forget Password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
				ForgetPassword forget = new ForgetPassword();
				forget.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 20));
		

		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "H.O.D", "Teacher", "Student"}));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(241, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(162))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFirstName)
						.addComponent(lblRole, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, 0, 278, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
							.addComponent(btnSignUp))
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
						.addComponent(textField_1))
					.addGap(110))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPassword)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addComponent(lblRole))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(comboBox)))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSignUp)
						.addComponent(btnNewButton))
					.addGap(26)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
