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
 * ��¼/ע�����
 */
public class LogIn_up extends JFrame {

	private static final long serialVersionUID = 1L;

	// �����ж������ʽ ****@***.***
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
		setTitle("��½/ע��");
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

		label1 = new JLabel("���룺");
		label1.setFont(new Font("��Բ", Font.PLAIN, 20));
		label1.setBounds(28, 227, 72, 24);
		contentPane.add(label1);

		label2 = new JLabel("���䣺");
		label2.setFont(new Font("��Բ", Font.PLAIN, 20));
		label2.setBounds(28, 185, 72, 24);
		contentPane.add(label2);

		passdField_1 = new JPasswordField();
		passdField_1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		passdField_1.setToolTipText("����");
		passdField_1.setBounds(89, 227, 218, 35);
		contentPane.add(passdField_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		textField_2.setToolTipText("����");
		textField_2.setBounds(89, 184, 218, 35);
		contentPane.add(textField_2);

		button_2 = new JButton("��½");
		button_2.setForeground(Color.RED);
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setFont(new Font("��Բ", Font.PLAIN, 25));
		button_2.setBounds(17, 340, 127, 30);
		contentPane.add(button_2);

		button_1 = new JButton("ע��");
		button_1.setForeground(Color.RED);
		button_1.setFont(new Font("��Բ", Font.PLAIN, 25));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(207, 339, 127, 30);
		contentPane.add(button_1);

		setVisible(true);
		setResizable(false);
	}

	public void listener() {

		// ע��
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Connection con = null;
				PreparedStatement pst = null;
				ResultSet rs = null;

				/*
				 * ע���ڲ��಻��ʹ���ⲿ���еķ�final���� ���������email��pasd �ȱ�����Ȼ��Button_2��ҲҪʹ�ã�
				 * ��������MouseAdapter����ͳһ���塣
				 */
				String email = textField_2.getText();
				char[] pass = passdField_1.getPassword();
				String pasd = new String(pass);

				if (email.length() < 1 || pasd.length() < 1) {
					JOptionPane.showMessageDialog(null, "���估���벻��Ϊ�գ����������룡", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				} else if (!email.matches(regex_mail)) {
					JOptionPane.showMessageDialog(null, "�����ʽ����ȷ�����������룡", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						con = ConnectDB.getConnection();
						String sql = "select * from users where email=?";
						pst = con.prepareStatement(sql);
						pst.setObject(1, email);
						rs = pst.executeQuery();
						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "���û���ע����Ϣ�����������룡", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);

							ConnectDB.closeAll(rs, pst, con);
						} else {
							if (!rs.next()) {
								sql = "insert into users values(?,?)";
								PreparedStatement ps = con.prepareStatement(sql);
								ps.setObject(1, email);
								ps.setObject(2, pasd);
								ps.executeUpdate();

								JOptionPane.showMessageDialog(null, "ע��ɹ�,���¼��", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);

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

		// ��¼
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
					JOptionPane.showMessageDialog(null, "���估����Ϊ�գ����������룡", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				} else {
					con = ConnectDB.getConnection();

					if (email.equals("admin")) {
						try {
							sql = "select paswd from users where email = 'admin'";
							pst = con.prepareStatement(sql);
							rs = pst.executeQuery();

							// ��Ϊ�Ѿ�Ԥ�����ԱΪadmin,�����Ʋ��ɸ��ģ���rs.next()һ���᷵��true
							// ����rs��ʼ���õ�λ���ڽ������ǰһ�У��ʱ������һ��rs.next()��
							// �������ݿ��� paswd������Ϊchar(20),����Ҫ��trim()ȥ��ϵͳ�Զ����Ŀո�
							if (rs.next() && pasd.equals(rs.getString(1).trim())) {
								new AdminFrame();
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "�����������������", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
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
								JOptionPane.showMessageDialog(null, "����/�û������������������", "ϵͳ��ʾ",
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