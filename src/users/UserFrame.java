package users;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.jfree.ui.RefineryUtilities;

import DB.Ideas;
import DB.List;
import DB.Project;
import DB.TableOperate;
import DB.Task;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class UserFrame extends JFrame implements ActionListener {

	/**
	 * 用户使用界面
	 */
	private static final long serialVersionUID = 1L;

	String userEmail = null;
	private int[] array = new int[4];
	TableOperate operator = new TableOperate();

	private CardLayout card = null;
	private JPanel contentPane = null;
	private JButton button_11 = null;
	private JButton button_21 = null;
	private JButton button_12_1 = null;
	private JButton button_12_2 = null;
	private JButton button_22_1 = null;
	private JButton button_22_2 = null;
	private JButton button_31 = null;
	private JButton button_41 = null;
	private JButton button_32_1 = null;
	private JButton button_32_2 = null;
	private JButton button_32_3 = null;
	private JButton button_32_4 = null;
	private JButton button_42_1 = null;
	private JButton button_42_2 = null;
	private JTextArea textArea_11 = null;
	private JTextArea textArea_12 = null;
	private JTextArea textArea_21 = null;
	private JTextArea textArea_31 = null;
	private JTextArea textArea_41 = null;
	private JTextArea textArea_42 = null;
	private JTextField textField_21 = null;
	private JTextField textField_22 = null;
	private JTextField textField_31_1 = null;
	private JTextField textField_31_2 = null;
	private JTextField textField_32 = null;
	private JTextField textField_41 = null;
	private JTextField textField_42_1 = null;
	private JTable table_12 = null;
	private JTable table_22 = null;
	private JTable table_32 = null;
	private JTable table_42 = null;
	private JTable table_43 = null;
	private Vector<String> list_32 = null;
	private JComboBox<Vector<String>> comboBox_32 = null;
	private Vector<String> titleVector_12 = new Vector<String>();
	private Vector<String> titleVector_22 = new Vector<String>();
	private Vector<String> titleVector_32 = new Vector<String>();
	private Vector<String> titleVector_42 = new Vector<String>();
	private Vector<String> titleVector_43 = new Vector<String>();
	private Vector<Vector<String>> cellsVector_12 = new Vector<Vector<String>>();
	private Vector<Vector<String>> cellsVector_22 = new Vector<Vector<String>>();
	private Vector<Vector<String>> cellsVector_32 = new Vector<Vector<String>>();
	private Vector<Vector<String>> cellsVector_42 = new Vector<Vector<String>>();
	private Vector<Vector<String>> cellsVector_43 = new Vector<Vector<String>>();

	public UserFrame(String name) {
		userEmail = name;

		setTitle("用户_" + name);
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\ATech\\Codes\\WorkPlace\\PersonalScheduelManage\\images\\Logo_2.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int winWidth = 1160;
		int winHeight = 725;
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds((width - winWidth) / 2, (height - winHeight) / 2, winWidth, winHeight);
		setVisible(true);

		// 添加卡片布局
		card = new CardLayout();
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setLayout(card);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 20));
		setJMenuBar(menuBar);

		// --------------------------------1.清单菜单栏-----------------------
		JMenu mnNewMenu_1 = new JMenu("清单");
		mnNewMenu_1.setForeground(Color.GRAY);
		mnNewMenu_1.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 25));
		menuBar.add(mnNewMenu_1);

		JMenuItem MenuItem_11 = new JMenuItem("新建清单");
		MenuItem_11.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		MenuItem_11.addActionListener(this);
		mnNewMenu_1.add(MenuItem_11);

		JMenuItem menuItem_12 = new JMenuItem("查询清单及更新");
		menuItem_12.setBackground(Color.WHITE);
		menuItem_12.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_12.addActionListener(this);
		mnNewMenu_1.add(menuItem_12);

		// --------------------------------2.项目菜单栏-----------------------
		JMenu mnNewMenu_2 = new JMenu("项目");
		mnNewMenu_2.setForeground(Color.GRAY);
		mnNewMenu_2.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 25));
		menuBar.add(mnNewMenu_2);

		JMenuItem MenuItem_21 = new JMenuItem("新建项目");
		MenuItem_21.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		MenuItem_21.addActionListener(this);
		mnNewMenu_2.add(MenuItem_21);

		JMenuItem menuItem_22 = new JMenuItem("查询项目及更新");
		menuItem_22.setBackground(Color.WHITE);
		menuItem_22.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_22.addActionListener(this);
		mnNewMenu_2.add(menuItem_22);

		// --------------------------------3.任务菜单栏-----------------------
		JMenu mnNewMenu_3 = new JMenu("任务");
		mnNewMenu_3.setForeground(Color.GRAY);
		mnNewMenu_3.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 25));
		menuBar.add(mnNewMenu_3);

		JMenuItem MenuItem_31 = new JMenuItem("新建任务");
		MenuItem_31.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		MenuItem_31.addActionListener(this);
		mnNewMenu_3.add(MenuItem_31);

		JMenuItem menuItem_32 = new JMenuItem("查询任务及更新");
		menuItem_32.setBackground(Color.WHITE);
		menuItem_32.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_32.addActionListener(this);
		mnNewMenu_3.add(menuItem_32);

		// --------------------------------4.心得菜单栏-----------------------
		JMenu mnNewMenu_4 = new JMenu("心得");
		mnNewMenu_4.setForeground(Color.GRAY);
		mnNewMenu_4.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 25));
		menuBar.add(mnNewMenu_4);

		JMenuItem MenuItem_41 = new JMenuItem("新建心得");
		MenuItem_41.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		MenuItem_41.addActionListener(this);
		mnNewMenu_4.add(MenuItem_41);

		JMenuItem menuItem_42 = new JMenuItem("查询心得及更新");
		menuItem_42.setBackground(Color.WHITE);
		menuItem_42.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_42.addActionListener(this);
		mnNewMenu_4.add(menuItem_42);

		JMenuItem menuItem_43 = new JMenuItem("查询用户分享心得");
		menuItem_43.setBackground(Color.WHITE);
		menuItem_43.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_43.addActionListener(this);
		mnNewMenu_4.add(menuItem_43);

		// --------------------------------5.统计分析菜单栏-----------------------
		JMenu mnNewMenu_5 = new JMenu("统计分析");
		mnNewMenu_5.setForeground(Color.GRAY);
		mnNewMenu_5.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 25));
		menuBar.add(mnNewMenu_5);

		JMenuItem MenuItem_51 = new JMenuItem("查看项目完成情况");
		MenuItem_51.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		MenuItem_51.addActionListener(this);
		mnNewMenu_5.add(MenuItem_51);

		// --------------------------------5.统计分析菜单栏-----------------------
		JMenu mnNewMenu_6 = new JMenu("设置");
		mnNewMenu_6.setForeground(Color.GRAY);
		mnNewMenu_6.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 25));
		menuBar.add(mnNewMenu_6);

		JMenuItem MenuItem_61 = new JMenuItem("修改密码");
		MenuItem_61.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		MenuItem_61.addActionListener(this);
		mnNewMenu_6.add(MenuItem_61);

		// ---------------------- 创建各个菜单栏对应的卡片--------------------------

		// 新建清单
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		contentPane.add(panel_11, "1_1");

		JLabel label_11 = new JLabel("清单内容:");
		label_11.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_11.setBounds(0, 0, 149, 31);
		panel_11.add(label_11);

		textArea_11 = new JTextArea();
		textArea_11.setFont(new Font("幼圆", Font.PLAIN, 25));
		textArea_11.setToolTipText("请输入你的清单");

		JScrollPane scrollPane_1 = new JScrollPane(textArea_11);
		scrollPane_1.setBounds(0, 37, 1132, 527);

		panel_11.add(scrollPane_1);

		button_11 = new JButton("保存");
		button_11.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_11.setBounds(1005, 577, 113, 37);
		button_11.addActionListener(this);
		panel_11.add(button_11);

		// 查询清单及更新
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		contentPane.add(panel_12, "1_2");

		titleVector_12.add("时间");
		titleVector_12.add("内容");
		updateCellsVector_12();
		table_12 = new JTable(cellsVector_12, titleVector_12);
		table_12.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		table_12.setRowHeight(30);
		DefaultTableCellRenderer r_12 = new DefaultTableCellRenderer();
		r_12.setHorizontalAlignment(JLabel.CENTER);
		table_12.setDefaultRenderer(Object.class, r_12);

		JScrollPane scrollPane_12_1 = new JScrollPane(table_12);
		scrollPane_12_1.setBounds(14, 13, 1104, 340);
		panel_12.add(scrollPane_12_1);

		JLabel label_12 = new JLabel("内容：");
		label_12.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_12.setBounds(14, 386, 105, 35);
		panel_12.add(label_12);

		textArea_12 = new JTextArea();
		textArea_12.setFont(new Font("幼圆", Font.PLAIN, 25));
		JScrollPane scrollPane_12_2 = new JScrollPane(textArea_12);
		scrollPane_12_2.setBounds(145, 384, 973, 83);
		panel_12.add(scrollPane_12_2);

		button_12_1 = new JButton("修改所选记录");
		button_12_1.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_12_1.setBounds(180, 548, 217, 40);
		button_12_1.addActionListener(this);
		panel_12.add(button_12_1);

		button_12_2 = new JButton("删除所选记录");
		button_12_2.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_12_2.setBounds(611, 548, 217, 40);
		button_12_2.addActionListener(this);
		panel_12.add(button_12_2);

		// 新建项目
		JPanel panel_21 = new JPanel();
		panel_21.setLayout(null);
		contentPane.add(panel_21, "2_1");

		JLabel label_21_1 = new JLabel("名称：");
		label_21_1.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_21_1.setBounds(234, 52, 102, 30);
		panel_21.add(label_21_1);

		JLabel label_21_2 = new JLabel("备注：");
		label_21_2.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_21_2.setBounds(234, 157, 102, 30);
		panel_21.add(label_21_2);

		textArea_21 = new JTextArea();
		textArea_21.setFont(new Font("幼圆", Font.PLAIN, 25));
		textArea_21.setBounds(314, 200, 561, 209);
		panel_21.add(textArea_21);

		button_21 = new JButton("保存");
		button_21.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_21.setBounds(932, 535, 130, 40);
		button_21.addActionListener(this);
		panel_21.add(button_21);

		textField_21 = new JTextField();
		textField_21.setFont(new Font("幼圆", Font.PLAIN, 25));
		textField_21.setBounds(314, 49, 561, 36);
		panel_21.add(textField_21);
		textField_21.setColumns(10);

		// 查询项目及更新
		JPanel panel_22 = new JPanel();
		panel_22.setLayout(null);
		contentPane.add(panel_22, "2_2");

		titleVector_22.add("项目Id");
		titleVector_22.add("项目名");
		titleVector_22.add("状态");
		titleVector_22.add("备注");
		updateCellsVector_22();
		table_22 = new JTable(cellsVector_22, titleVector_22);
		table_22.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		table_22.setRowHeight(30);

		DefaultTableCellRenderer r_22 = new DefaultTableCellRenderer();
		r_22.setHorizontalAlignment(JLabel.CENTER);
		table_22.setDefaultRenderer(Object.class, r_22);

		JScrollPane scrollPane_22 = new JScrollPane(table_22);
		scrollPane_22.setBounds(14, 13, 1104, 351);
		panel_22.add(scrollPane_22);

		JLabel label_22_1 = new JLabel("状态：");
		label_22_1.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_22_1.setBounds(63, 416, 109, 35);
		panel_22.add(label_22_1);

		textField_22 = new JTextField();
		textField_22.setFont(new Font("幼圆", Font.PLAIN, 25));
		textField_22.setBounds(155, 416, 184, 35);
		panel_22.add(textField_22);
		textField_22.setColumns(10);

		JLabel label_22_2 = new JLabel("（'已完成','推迟','搁置','进行中'）");
		label_22_2.setFont(new Font("幼圆", Font.PLAIN, 20));
		label_22_2.setBounds(374, 425, 744, 29);
		panel_22.add(label_22_2);

		button_22_1 = new JButton("保存状态修改");
		button_22_1.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_22_1.setBounds(256, 543, 196, 35);
		button_22_1.addActionListener(this);
		panel_22.add(button_22_1);

		button_22_2 = new JButton("删除所选记录");
		button_22_2.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_22_2.setBounds(602, 543, 210, 35);
		button_22_2.addActionListener(this);
		panel_22.add(button_22_2);

		// 新建任务
		JPanel panel_31 = new JPanel();
		panel_31.setLayout(null);
		contentPane.add(panel_31, "3_1");

		JLabel label_31_1 = new JLabel("所属的项目ID：");
		label_31_1.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_31_1.setBounds(64, 92, 189, 39);
		panel_31.add(label_31_1);

		JLabel label_31_2 = new JLabel("任务名称：");
		label_31_2.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_31_2.setBounds(424, 92, 149, 39);
		panel_31.add(label_31_2);

		JLabel label_31_3 = new JLabel("备注：");
		label_31_3.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_31_3.setBounds(64, 234, 149, 39);
		panel_31.add(label_31_3);

		textField_31_1 = new JTextField();
		textField_31_1.setFont(new Font("幼圆", Font.PLAIN, 25));
		textField_31_1.setBounds(233, 92, 107, 39);
		panel_31.add(textField_31_1);
		textField_31_1.setColumns(10);

		textField_31_2 = new JTextField();
		textField_31_2.setFont(new Font("幼圆", Font.PLAIN, 25));
		textField_31_2.setColumns(10);
		textField_31_2.setBounds(542, 92, 255, 39);
		panel_31.add(textField_31_2);

		textArea_31 = new JTextArea();
		textArea_31.setFont(new Font("幼圆", Font.PLAIN, 25));
		textArea_31.setBounds(142, 245, 656, 224);
		panel_31.add(textArea_31);

		button_31 = new JButton("保存");
		button_31.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_31.setBounds(920, 544, 134, 39);
		button_31.addActionListener(this);
		panel_31.add(button_31);

		// 查询任务及更新
		JPanel panel_32 = new JPanel();
		panel_32.setLayout(null);
		contentPane.add(panel_32, "3_2");

		JLabel label_32_1 = new JLabel("选择项目");
		label_32_1.setFont(new Font("幼圆", Font.PLAIN, 32));
		label_32_1.setBounds(24, 13, 152, 37);
		panel_32.add(label_32_1);

		button_32_1 = new JButton("查询");
		button_32_1.setFont(new Font("幼圆", Font.PLAIN, 32));
		button_32_1.setBounds(496, 15, 119, 42);
		button_32_1.addActionListener(this);
		panel_32.add(button_32_1);

		comboBox_32 = new JComboBox<Vector<String>>();
		comboBox_32.setBounds(171, 17, 288, 39);
		list_32 = operator.getProjName(name);
		comboBox_32.setModel(new DefaultComboBoxModel(list_32));
		comboBox_32.setForeground(Color.DARK_GRAY);
		comboBox_32.setBackground(Color.WHITE);
		comboBox_32.setFont(new Font("幼圆", Font.PLAIN, 25));
		panel_32.add(comboBox_32);

		JLabel label_32_2 = new JLabel("项目的任务信息");
		label_32_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_32_2.setFont(new Font("幼圆", Font.PLAIN, 28));
		label_32_2.setBounds(450, 70, 245, 37);
		panel_32.add(label_32_2);

		titleVector_32.add("任务ID");
		titleVector_32.add("标题");
		titleVector_32.add("状态");
		titleVector_32.add("备注");
		updateCellsVector_32();
		table_32 = new JTable(cellsVector_32, titleVector_32);
		table_32.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		table_32.setRowHeight(30);
		DefaultTableCellRenderer r_32 = new DefaultTableCellRenderer();
		r_32.setHorizontalAlignment(JLabel.CENTER);
		table_32.setDefaultRenderer(Object.class, r_32);

		JScrollPane scrollPane_32 = new JScrollPane(table_32);
		scrollPane_32.setBounds(0, 109, 1128, 336);
		panel_32.add(scrollPane_32);

		JLabel label_32_4 = new JLabel("（'已完成','推迟','搁置','进行中'）");
		label_32_4.setFont(new Font("幼圆", Font.PLAIN, 20));
		label_32_4.setBounds(281, 476, 364, 37);
		panel_32.add(label_32_4);

		JLabel label_32_3 = new JLabel("状态");
		label_32_3.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_32_3.setBounds(50, 475, 126, 37);
		panel_32.add(label_32_3);

		button_32_2 = new JButton("保存修改");
		button_32_2.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_32_2.setBounds(322, 568, 171, 37);
		button_32_2.addActionListener(this);
		panel_32.add(button_32_2);

		button_32_3 = new JButton("删除所选记录");
		button_32_3.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_32_3.setBounds(634, 568, 193, 37);
		button_32_3.addActionListener(this);
		panel_32.add(button_32_3);

		textField_32 = new JTextField();
		textField_32.setFont(new Font("幼圆", Font.PLAIN, 25));
		textField_32.setBounds(115, 474, 152, 37);
		panel_32.add(textField_32);
		textField_32.setColumns(10);

		button_32_4 = new JButton("查看分析图表");
		button_32_4.setFont(new Font("幼圆", Font.PLAIN, 32));
		button_32_4.setBounds(796, 15, 259, 42);
		button_32_4.addActionListener(this);
		panel_32.add(button_32_4);

		// 新建心得
		JPanel panel_41 = new JPanel();
		panel_41.setLayout(null);
		contentPane.add(panel_41, "4_1");

		JLabel label_4_11 = new JLabel("标题:");
		label_4_11.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_4_11.setBounds(0, 0, 113, 37);
		panel_41.add(label_4_11);

		textField_41 = new JTextField();
		textField_41.setFont(new Font("幼圆", Font.PLAIN, 25));
		textField_41.setBounds(71, 0, 284, 37);
		panel_41.add(textField_41);
		textField_41.setColumns(10);

		JLabel label_4_12 = new JLabel("内容:");
		label_4_12.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_4_12.setBounds(0, 38, 113, 37);
		panel_41.add(label_4_12);

		textArea_41 = new JTextArea();
		textArea_41.setFont(new Font("幼圆", Font.PLAIN, 25));
		textArea_41.setToolTipText("请输入你的心得");

		JScrollPane scrollPane_4 = new JScrollPane(textArea_41);
		scrollPane_4.setBounds(0, 78, 1132, 486);

		panel_41.add(scrollPane_4);

		button_41 = new JButton("保存");
		button_41.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_41.setBounds(1005, 577, 113, 37);
		button_41.addActionListener(this);
		panel_41.add(button_41);

		// 新建心得查询及更新
		JPanel panel_42 = new JPanel();
		panel_42.setLayout(null);
		contentPane.add(panel_42, "4_2");

		titleVector_42.add("时间");
		titleVector_42.add("标题");
		titleVector_42.add("内容");
		updateCellsVector_42();
		table_42 = new JTable(cellsVector_42, titleVector_42);
		table_42.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		table_42.setRowHeight(30);
		DefaultTableCellRenderer r_42 = new DefaultTableCellRenderer();
		r_42.setHorizontalAlignment(JLabel.CENTER);
		table_42.setDefaultRenderer(Object.class, r_42);

		JScrollPane scrollPane_42_1 = new JScrollPane(table_42);
		scrollPane_42_1.setBounds(14, 13, 1104, 302);
		panel_42.add(scrollPane_42_1);

		JLabel label_42_1 = new JLabel("标题：");
		label_42_1.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_42_1.setBounds(14, 347, 86, 28);
		panel_42.add(label_42_1);

		JLabel label_42_3 = new JLabel("内容：");
		label_42_3.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_42_3.setBounds(529, 347, 86, 28);
		panel_42.add(label_42_3);

		textField_42_1 = new JTextField();
		textField_42_1.setFont(new Font("幼圆", Font.PLAIN, 25));
		textField_42_1.setBounds(93, 342, 328, 38);
		panel_42.add(textField_42_1);
		textField_42_1.setColumns(10);

		textArea_42 = new JTextArea();
		textArea_42.setFont(new Font("幼圆", Font.PLAIN, 25));
		JScrollPane scrollPane_42_2 = new JScrollPane(textArea_42);
		scrollPane_42_2.setBounds(606, 344, 512, 124);
		panel_42.add(scrollPane_42_2);

		button_42_1 = new JButton("修改所选记录");
		button_42_1.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_42_1.setBounds(268, 541, 186, 38);
		button_42_1.addActionListener(this);
		panel_42.add(button_42_1);

		button_42_2 = new JButton("删除所选记录");
		button_42_2.setFont(new Font("幼圆", Font.PLAIN, 25));
		button_42_2.setBounds(585, 541, 186, 38);
		button_42_2.addActionListener(this);
		panel_42.add(button_42_2);

		// 新建查询用户分享心得
		JPanel panel_43 = new JPanel();
		panel_43.setLayout(null);
		contentPane.add(panel_43, "4_3");

		titleVector_43.add("用户");
		titleVector_43.add("时间");
		titleVector_43.add("标题");
		titleVector_43.add("内容");
		updateCellsVector_43();
		table_43 = new JTable(cellsVector_43, titleVector_43);
		table_43.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		table_43.setRowHeight(30);
		DefaultTableCellRenderer r_43 = new DefaultTableCellRenderer();
		r_43.setHorizontalAlignment(JLabel.CENTER);
		table_43.setDefaultRenderer(Object.class, r_43);
		
		JScrollPane scrollPane_43 = new JScrollPane(table_43);
		scrollPane_43.setBounds(14, 13, 1104, 600);
		panel_43.add(scrollPane_43);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object source = e.getSource();

		if (e.getActionCommand() == "新建清单") {
			card.show(contentPane, "1_1");
		}

		if (e.getActionCommand() == "查询清单及更新") {
			card.show(contentPane, "1_2");
			updateCellsVector_12();
			table_12.updateUI();
		}

		if (e.getActionCommand() == "新建项目") {
			card.show(contentPane, "2_1");
		}

		if (e.getActionCommand() == "查询项目及更新") {
			card.show(contentPane, "2_2");
			updateCellsVector_22();
			table_22.updateUI();
		}

		if (e.getActionCommand() == "新建任务") {
			card.show(contentPane, "3_1");
		}

		if (e.getActionCommand() == "查询任务及更新") {
			card.show(contentPane, "3_2");
			updateCellsVector_32();
			table_32.updateUI();
		}

		if (e.getActionCommand() == "新建心得") {
			card.show(contentPane, "4_1");
		}

		if (e.getActionCommand() == "查询心得及更新") {
			card.show(contentPane, "4_2");
			updateCellsVector_42();
			table_42.updateUI();
		}

		if (e.getActionCommand() == "查询用户分享心得") {
			card.show(contentPane, "4_3");
			updateCellsVector_43();
			table_43.updateUI();
		}
		if (e.getActionCommand() == "查看项目完成情况") {
			array = operator.getArray(userEmail);
			ChartDemo demo = new ChartDemo(array);
			demo.pack();
			RefineryUtilities.centerFrameOnScreen(demo);
			demo.setVisible(true);
		}

		if (e.getActionCommand() == "修改密码") {
			Login_Change Lg = new Login_Change(userEmail);
		}

		// 保存清单
		if (source == button_11) {
			String content = textArea_11.getText().trim();
			operator.insertList(userEmail, content);
			textArea_11.setText("");
		}

		// 保存清单记录的修改
		if (source == button_12_1) {
			String content = textArea_12.getText().trim();
			String cTime = cellsVector_12.get(table_12.getSelectedRow()).get(0);

			operator.updateList(content, userEmail, cTime);
			updateCellsVector_12();
			table_12.updateUI();

			textArea_12.setText("");

		}

		// 删除所选清单记录
		if (source == button_12_2) {

			String cTime = cellsVector_12.get(table_12.getSelectedRow()).get(0);
			operator.delList(cTime, userEmail);
			updateCellsVector_12();
			table_12.updateUI();
		}

		// 保存项目
		if (source == button_21) {
			String name = textField_21.getText().trim();
			String remark = textArea_21.getText().trim();
			operator.insertProject(userEmail, name, remark);
			textArea_21.setText("");
			textField_21.setText("");
		}

		// 保存项目状态记录的修改
		if (source == button_22_1) {
			String projStatus = textField_22.getText().trim();
			int projId = Integer.parseInt(cellsVector_22.get(table_22.getSelectedRow()).get(0));

			operator.updateProj(projStatus, projId);
			updateCellsVector_22();
			table_22.updateUI();

			textField_22.setText("");

		}

		// 删除所选项目记录
		if (source == button_22_2) {
			int projId = Integer.parseInt(cellsVector_22.get(table_22.getSelectedRow()).get(0));
			operator.delProj(projId);
			updateCellsVector_22();
			table_22.updateUI();
		}

		// 保存任务
		if (source == button_31) {
			int projId = Integer.parseInt(textField_31_1.getText().trim());
			String taskname = textField_31_2.getText().trim();
			String remark = textArea_31.getText().trim();

			operator.insertTask(projId, taskname, remark);

			textField_31_1.setText("");
			textField_31_2.setText("");
			textArea_31.setText("");

		}

		// 查询项目中的任务
		if (source == button_32_1) {
			updateCellsVector_32();
			table_32.updateUI();
		}

		// 保存任务修改
		if (source == button_32_2) {
			String taskStatus = textField_32.getText().trim();
			int row = table_32.getSelectedRow();
			int taskId = Integer.parseInt(cellsVector_32.get(row).get(0));

			operator.updateTask(taskId, taskStatus);
			updateCellsVector_32();
			table_32.updateUI();

			textField_32.setText("");
		}

		// 删除所选任务
		if (source == button_32_3) {
			int row = table_32.getSelectedRow();
			int taskId = Integer.parseInt(cellsVector_32.get(row).get(0));

			operator.delTask(taskId);
			updateCellsVector_32();
			table_32.updateUI();
		}

		// 查看任务完成情况图表
		if (source == button_32_4) {
			String projname = (String) comboBox_32.getSelectedItem();
			array = operator.getArray_2(projname);
			ChartDemo demo = new ChartDemo(array);
			demo.pack();
			RefineryUtilities.centerFrameOnScreen(demo);
			demo.setVisible(true);
		}
		// 保存新建心得
		if (source == button_41) {
			String title = textField_41.getText().trim();
			String content = textArea_41.getText().trim();

			operator.insertIdeas(userEmail, title, content);

			textField_41.setText("");
			textArea_41.setText("");

		}

		// 保存心得记录的修改
		if (source == button_42_1) {

			String cTime = cellsVector_42.get(table_42.getSelectedRow()).get(0);
			String title = textField_42_1.getText().trim();
			String content = textArea_42.getText().trim();

			operator.updateIdeas(title, content, userEmail, cTime);
			updateCellsVector_42();
			table_42.updateUI();

			textField_42_1.setText("");
			textArea_42.setText("");
		}

		// 删除所选心得记录
		if (source == button_42_2) {

			String cTime = cellsVector_42.get(table_42.getSelectedRow()).get(0);
			operator.delIdeas(cTime, userEmail);
			updateCellsVector_42();
			table_42.updateUI();
		}

	}

	void updateCellsVector_12() {
		cellsVector_12.clear();
		ArrayList<List> alist = operator.selectList(userEmail);
		List list = null;

		for (int i = 0; i < alist.size(); i++) {
			list = alist.get(i);
			Vector<String> v = new Vector<String>();
			v.add(list.getcTime());
			v.add(list.getContent());
			cellsVector_12.add(v);
		}
	}

	void updateCellsVector_22() {
		cellsVector_22.clear();
		ArrayList<Project> alist = operator.selectProj(userEmail);
		Project proj = null;

		for (int i = 0; i < alist.size(); i++) {
			proj = alist.get(i);
			Vector<String> v = new Vector<String>();
			// String.valueOf()
			v.add(proj.getProjId() + "");
			v.add(proj.getProjName());
			v.add(proj.getProjStatus());
			v.add(proj.getRemark());
			cellsVector_22.add(v);
		}
	}

	void updateCellsVector_32() {
		cellsVector_32.clear();
		String projname = (String) comboBox_32.getSelectedItem();

		ArrayList<Task> alist = operator.selectTask(projname);
		Task task = null;

		for (int i = 0; i < alist.size(); i++) {
			task = alist.get(i);
			Vector<String> v = new Vector<String>();
			// String.valueOf()
			v.add(task.getTaskId() + "");
			v.add(task.getTaskName());
			v.add(task.getTaskStatus());
			v.add(task.getRemark());
			cellsVector_32.add(v);
		}
	}

	void updateCellsVector_42() {
		cellsVector_42.clear();
		ArrayList<Ideas> alist = operator.selectIdeas(userEmail);
		Ideas ideas = null;

		for (int i = 0; i < alist.size(); i++) {
			ideas = alist.get(i);
			Vector<String> v = new Vector<String>();
			v.add(ideas.getcTime());
			v.add(ideas.getTitle());
			v.add(ideas.getContent());
			cellsVector_42.add(v);
		}
	}

	void updateCellsVector_43() {
		cellsVector_43.clear();
		ArrayList<Ideas> alist = operator.selectIdeas();
		Ideas ideas = null;

		for (int i = 0; i < alist.size(); i++) {
			ideas = alist.get(i);
			Vector<String> v = new Vector<String>();
			v.add(ideas.getEmail());
			v.add(ideas.getcTime());
			v.add(ideas.getTitle());
			v.add(ideas.getContent());
			cellsVector_43.add(v);
		}
	}
}
