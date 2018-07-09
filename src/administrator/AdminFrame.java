package administrator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import DB.Ideas;
import DB.TableOperate;

public class AdminFrame extends JFrame implements ActionListener {

	/**
	 * 管理员窗口界面
	 */
	private static final long serialVersionUID = 1L;

	TableOperate operator = new TableOperate();

	private JPanel contentPane = null;
	private JPanel panel = null;
	private JScrollPane scrollPane = null;
	private JButton button_3 = null;
	private JButton button_2 = null;
	private JButton button_1 = null;
	private JTable table = null;
	private JLabel label_1 = null;
	private JLabel label_2 = null;
	private Vector<String> list = null;
	private JComboBox<Vector<String>> comboBox = null;
	private Vector<String> titleVector = new Vector<String>();
	private Vector<Vector<String>> cellsVector = new Vector<Vector<String>>();
	

	public AdminFrame() {

		setTitle("系统管理员界面");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\ATech\\Codes\\WorkPlace\\PersonalScheduelManage\\images\\Logo_2.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int winWidth = 1160;
		int winHeight = 725;
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds((width - winWidth) / 2, (height - winHeight) / 2, winWidth, winHeight);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, 852, 0);
		getContentPane().add(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 1141, 81);
		getContentPane().add(panel);
		panel.setLayout(null);

		label_1 = new JLabel("选择用户");
		label_1.setBounds(14, 25, 128, 48);
		label_1.setBackground(Color.WHITE);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 32));
		panel.add(label_1);

		comboBox = new JComboBox<Vector<String>>();
		comboBox.setBounds(156, 35, 335, 34);
		list = operator.getUserEmailList();
		comboBox.setModel(new DefaultComboBoxModel(list));
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("幼圆", Font.PLAIN, 25));
		panel.add(comboBox);

		button_3 = new JButton("删除该用户");
		button_3.setBounds(882, 32, 202, 39);
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		button_3.addActionListener(this);
		panel.add(button_3);

		button_2 = new JButton("查询");
		button_2.setBounds(522, 32, 95, 39);
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		button_2.addActionListener(this);
		panel.add(button_2);

	    titleVector.add("时间");
		titleVector.add("标题");
		titleVector.add("内容");
		updateCellsVector();
		table = new JTable(cellsVector,titleVector);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		table.setRowHeight(30);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 121, 1141, 491);
		getContentPane().add(scrollPane);

		label_2 = new JLabel("用户的分享信息");
		label_2.setBackground(Color.WHITE);
		label_2.setBounds(0, 79, 1141, 43);
		getContentPane().add(label_2);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		
		button_1 = new JButton("删除所选心得记录");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		button_1.setBounds(829, 625, 287, 39);
		button_1.addActionListener(this);
		getContentPane().add(button_1);

		setVisible(true);

	}
	
	void updateCellsVector() {
		cellsVector.clear();   	
		String email = (String) comboBox.getSelectedItem();
		ArrayList<Ideas> alist = operator.selectIdeas(email);
		Ideas ideas = null;
		
		for (int i =0; i< alist.size(); i++) {
		     ideas = alist.get(i);
		     Vector<String> v = new Vector<String>();
		     v.add(ideas.getcTime());
		     v.add(ideas.getTitle());
		     v.add(ideas.getContent());
		     cellsVector.add(v);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object source = e.getSource();
		String email = (String) comboBox.getSelectedItem();

		// 执行查询用户分享的信息的操作
		if (source == button_2) {
		 updateCellsVector();
		 table.updateUI();
		}
		
		//执行删除所选心得记录
		if (source == button_1) {
			int row = table.getSelectedRow();
			String cTime = cellsVector.get(row).get(0);
			operator.delIdeas(cTime, email);
			updateCellsVector();
			table.updateUI();
		}

		// 执行删除选中用户操作
		if (source == button_3) {

			operator.delUser(email);
			// 更新用户列表
			list = operator.getUserEmailList();
			comboBox.setModel(new DefaultComboBoxModel(list));
			comboBox.updateUI();
		}
	}
}
