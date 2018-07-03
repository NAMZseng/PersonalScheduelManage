package login_up;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DB.ConnectDB;
import adminstrator.AdminFrame;
import users.UserFrame;

/*
 * 登录/注册界面
 */
public class LogIn_up extends JFrame {

	private static final long serialVersionUID = 1L;

	// 用于判断邮箱格式 ****@***.***
	private String regex_mail = "\\w+\\x40\\w+\\x2e\\w+";
	private JPanel contentPane;

	private JLabel label0;
	private JLabel label1;
	private JLabel label2;

	private JPasswordField passdField_1;
	private JTextField textField_2;

	private JButton button_1;
	private JButton button_2;

	public LogIn_up() {
		initialize();
		listener();
	}

	private void initialize() {
		setTitle("登陆/注册");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("F:\\ATech\\Codes\\WorkPlace\\PersonalScheduelManage\\images\\Logo_2.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 290, 372, 493);
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);

		label0 = new JLabel("");
		label0.setBounds(137, 13, 100, 85);
		label0.setIcon(new ImageIcon("F:\\ATech\\Codes\\WorkPlace\\PersonalScheduelManage\\images\\Logo.png"));
		contentPane.add(label0);

		label1 = new JLabel("密码：");
		label1.setFont(new Font("幼圆", Font.PLAIN, 20));
		label1.setBounds(28, 227, 72, 24);
		contentPane.add(label1);

		label2 = new JLabel("邮箱：");
		label2.setFont(new Font("幼圆", Font.PLAIN, 20));
		label2.setBounds(28, 185, 72, 24);
		contentPane.add(label2);

		passdField_1 = new JPasswordField();
		passdField_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		passdField_1.setToolTipText("密码");
		passdField_1.setBounds(89, 227, 218, 35);
		contentPane.add(passdField_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		textField_2.setToolTipText("邮箱");
		textField_2.setBounds(89, 184, 218, 35);
		contentPane.add(textField_2);

		button_2 = new JButton("登陆");
		button_2.setForeground(Color.RED);
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_2.setBounds(17, 340, 127, 30);
		contentPane.add(button_2);

		button_1 = new JButton("注册");
		button_1.setForeground(Color.RED);
		button_1.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(207, 339, 127, 30);
		contentPane.add(button_1);

		setVisible(true);
		setResizable(false);
	}

	public void listener() {

		// 注册
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Connection con = null;
				PreparedStatement pst = null;
				ResultSet rs = null;

				/*
				 * 注：内部类不能使用外部类中的非final变量 所以下面的email、pasd 等变量虽然在Button_2中也要使用，
				 * 但不可在MouseAdapter类外统一定义。
				 */
				String email = textField_2.getText();
				char[] pass = passdField_1.getPassword();
				String pasd = new String(pass);

				if (email.length() < 1 || pasd.length() < 1) {
					JOptionPane.showMessageDialog(null, "邮箱及密码不能为空，请重新输入！", "系统提示", JOptionPane.ERROR_MESSAGE);
				} else if (!email.matches(regex_mail)) {
					JOptionPane.showMessageDialog(null, "邮箱格式不正确，请重新输入！", "系统提示", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						con = ConnectDB.getConnection();
						String sql = "select * from users where email=?";
						pst = con.prepareStatement(sql);
						pst.setObject(1, email);
						rs = pst.executeQuery();
						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "该用户已注册信息，请重新输入！", "系统提示", JOptionPane.ERROR_MESSAGE);

							ConnectDB.closeAll(rs, pst, con);
						} else {
							if (!rs.next()) {
								sql = "insert into users values(?,?)";
								PreparedStatement ps = con.prepareStatement(sql);
								ps.setObject(1, email);
								ps.setObject(2, pasd);
								ps.executeUpdate();

								JOptionPane.showMessageDialog(null, "注册成功,请登录！", "提示消息", JOptionPane.PLAIN_MESSAGE);

								ConnectDB.closeAll(rs, pst, con);
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					} finally {
						ConnectDB.closeAll(rs, pst, con);
					}
				}
			}
		});

		// 登录
		button_2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				String email = textField_2.getText();
				char[] pass = passdField_1.getPassword();
				String pasd = new String(pass);

				Connection con = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				String sql = null;

				if (email.length() < 1 || pasd.length() < 1) {
					JOptionPane.showMessageDialog(null, "邮箱及密码为空，请重新输入！", "系统提示", JOptionPane.ERROR_MESSAGE);
				} else {
					con = ConnectDB.getConnection();

					if (email.equals("admin")) {
						try {
							sql = "select paswd from users where email = 'admin'";
							pst = con.prepareStatement(sql);
							rs = pst.executeQuery();

							// 因为已经预设管理员为admin,且名称不可更改，故rs.next()一定会返回true
							// 但因rs初始引用的位置在结果集的前一行，故必须调用一次rs.next()；
							// 又因数据库中 paswd的类型为char(20),所以要用trim()去掉系统自动填充的空格。
							if (rs.next() && pasd.equals(rs.getString(1).trim())) {
								new AdminFrame();
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "密码错误，请重新输入", "系统提示", JOptionPane.ERROR_MESSAGE);
							}
							ConnectDB.closeAll(rs, pst, con);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							ConnectDB.closeAll(rs, pst, con);
						}
					} else {
						try {
							sql = "select * from  users where email = ? and paswd = ?";
							pst = con.prepareStatement(sql);
							pst.setObject(1, email);
							pst.setObject(2, pasd);
							rs = pst.executeQuery();

							if (rs.next()) {
								new UserFrame(email);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "密码/用户邮箱错误，请重新输入", "系统提示",
										JOptionPane.ERROR_MESSAGE);
							}
							ConnectDB.closeAll(rs, pst, con);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							ConnectDB.closeAll(rs, pst, con);
						}
					}
				}
			}
		});
	}
}