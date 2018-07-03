
package users;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

class ChartDemo extends ApplicationFrame {
	
	private static final long serialVersionUID = 1L;
	// ��������
	private static final Font font = new Font("simsun", Font.ITALIC, 22);

	public ChartDemo(int[] array) {
		super("�û���Ŀ/��������������ͼ");
		super.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\ATech\\Codes\\WorkPlace\\PersonalScheduelManage\\images\\Logo_2.png"));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(new ChartPanel(getChart(array)));
	}

	/*
	 * ��װ��״ͼ��������ݼ�����DefaultCategoryDataset
	 */
	private static DefaultCategoryDataset getDataset(int[] array) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(array[0], "�����", "�����");
		dataset.setValue(array[1], "�Ƴ�", "�Ƴ�");
		dataset.setValue(array[2], "����", "����");
		dataset.setValue(array[3], "������", "������");
		return dataset;
	}
	public JFreeChart getChart(int[] array) {
		final DefaultCategoryDataset dataset = getDataset(array);
	
		JFreeChart chart = ChartFactory.createBarChart3D("�û���Ŀ/��������������ͼ", "���״̬", "����", dataset, PlotOrientation.VERTICAL,
				true, false, false);

		/*
		 * ����JFreeChart���� JFreeChart������֧�ֲ��ã� �����漰���ֵĲ��֣����������������壬������ʾ����
		 */
		chart.setTitle(new TextTitle("�û���Ŀ/��������������ͼ", font));
		// ��������ͼ������
		LegendTitle legend = chart.getLegend();
		legend.setItemFont(font);
		// ����X�������ϵ����ֵ�����
		chart.getCategoryPlot().getDomainAxis().setTickLabelFont(font);
		// ����X��������������
		chart.getCategoryPlot().getDomainAxis().setLabelFont(font);
		// ����Y�������ϵ����ֵ�����
		chart.getCategoryPlot().getRangeAxis().setTickLabelFont(font);
		// ����Y��������������
		chart.getCategoryPlot().getRangeAxis().setLabelFont(font);

		return chart;
	}


	@Override
	public void windowClosing(WindowEvent event) {
		// TODO Auto-generated method stub
	}

}
