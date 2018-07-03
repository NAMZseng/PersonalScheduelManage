package users;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import DB.TableOperate;

import java.awt.Color;

public class Login_Change extends JFrame implements ActionListener {

	static final long serialVersionUID = 1L;

	private JPanel contentPane = null;
	private String userEmail = null;
	private TableOperate operator = new TableOperate();

	private JLabel label0 = null;
	private JLabel label1 = null;
	private JLabel label2 = null;
	private JPasswordField passdField_1 = null;
	private JPasswordField passdField_2 = null;
	private JButton button = null;

	public Login_Change(String userEmail) {
		this.userEmail = userEmail;

		setTitle("ÐÞ¸ÄµÇÂ½ÐÅÏ¢");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\ATech\\Codes\\WorkPlace\\PersonalScheduelManage\\images\\Logo_2.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 290, 372, 493);
		setVisible(true);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		label0 = new JLabel("");
		label0.setBounds(137, 13, 100, 85);
		label0.setIcon(new ImageIcon("F:\\ATech\\Codes\\WorkPlace\\PersonalScheduelManage\\images\\Logo.png"));
		contentPane.add(label0);

		label1 = new JLabel("ÃÜÂë£º");
		label1.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		label1.setBounds(28, 185, 72, 24);
		contentPane.add(label1);

		label2 = new JLabel("È·ÈÏÃÜÂë£º");
		label2.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		label2.setBounds(14, 227, 100, 24);
		contentPane.add(label2);

		passdField_1 = new JPasswordField();
		passdField_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		passdField_1.setBounds(109, 179, 218, 35);
		contentPane.add(passdField_1);

		passdField_2 = new JPasswordField();
		passdField_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		passdField_2.setBounds(109, 221, 218, 35);
		contentPane.add(passdField_2);

		button = new JButton("±£´æ");
		button.setBackground(Color.LIGHT_GRAY);
		button.setForeground(Color.RED);
		button.setFont(new Font("Ó×Ô²", Font.PLAIN, 25));
		button.setBounds(124, 353, 113, 35);
		button.addActionListener(this);
		contentPane.add(button);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if (source == button) {
			char[] pass_1 = passdField_1.getPassword();
			String paswd = new String(pass_1).trim();
			char[] pass_2 = passdField_2.getPassword();
			String check_paswd = new String(pass_2).trim();

			operator.updateUser(userEmail, paswd, check_paswd);
		}
	}
}
