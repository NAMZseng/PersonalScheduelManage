package login_up;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * 系统主页界面
 */
public class HomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel label1;
	private JLabel label2;

	public HomePage() {
		initialize();
		listener();
	}
	
	private void initialize() {
		
		setTitle("个人时间管理系统");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\ATech\\Codes\\WorkPlace\\PersonalScheduelManage\\images\\Logo_2.png"));
		int winWidth = 1160;
		int winHeight = 725;
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds((width-winWidth)/2, (height-winHeight)/2, winWidth, winHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//设置背景图片
		Image iamge = new ImageIcon("F:\\ATech\\Codes\\WorkPlace\\PersonalScheduelManage\\images\\HomePage.jpg").getImage();
        contentPane = new JImagePane(iamge, JImagePane.SCALED);
        this.add(contentPane, BorderLayout.CENTER);
     
        contentPane.setLayout(null);
        
	    label1 = new JLabel("Welcome!");
		label1.setFont(new Font("Ink Free", Font.BOLD | Font.ITALIC, 55));
		label1.setBounds(850, 400, 226, 127);
		contentPane.add(label1);
		
		label2 = new JLabel("登陆/注册");
		label2.setBounds(850, 520, 280, 120);
		label2.setFont(new Font("幼圆", Font.PLAIN, 28));
		label2.setIcon(new ImageIcon("F:\\ATech\\Codes\\WorkPlace\\PersonalScheduelManage\\images\\login.png"));
		contentPane.add(label2);
	}
	
	private void listener() {
		
		//设置登录/注册Label的鼠标监听器
		label2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LogIn_up();
			}
		});
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
