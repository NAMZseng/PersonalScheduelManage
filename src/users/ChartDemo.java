
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
	// 字体设置
	private static final Font font = new Font("simsun", Font.ITALIC, 22);

	public ChartDemo(int[] array) {
		super("用户项目/任务完成情况比例图");
		super.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\ATech\\Codes\\WorkPlace\\PersonalScheduelManage\\images\\Logo_2.png"));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(new ChartPanel(getChart(array)));
	}

	/*
	 * 封装柱状图所需的数据集对象DefaultCategoryDataset
	 */
	private static DefaultCategoryDataset getDataset(int[] array) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(array[0], "已完成", "已完成");
		dataset.setValue(array[1], "推迟", "推迟");
		dataset.setValue(array[2], "搁置", "搁置");
		dataset.setValue(array[3], "进行中", "进行中");
		return dataset;
	}
	public JFreeChart getChart(int[] array) {
		final DefaultCategoryDataset dataset = getDataset(array);
	
		JFreeChart chart = ChartFactory.createBarChart3D("用户项目/任务完成情况比例图", "完成状态", "个数", dataset, PlotOrientation.VERTICAL,
				true, false, false);

		/*
		 * 返回JFreeChart对象 JFreeChart对中文支持不好， 所有涉及汉字的部分，必须重新设置字体，否则显示乱码
		 */
		chart.setTitle(new TextTitle("用户项目/任务完成情况比例图", font));
		// 重新设置图例字体
		LegendTitle legend = chart.getLegend();
		legend.setItemFont(font);
		// 设置X轴坐标上的文字的字体
		chart.getCategoryPlot().getDomainAxis().setTickLabelFont(font);
		// 设置X轴坐标标题的字体
		chart.getCategoryPlot().getDomainAxis().setLabelFont(font);
		// 设置Y轴坐标上的文字的字体
		chart.getCategoryPlot().getRangeAxis().setTickLabelFont(font);
		// 设置Y轴坐标标题的字体
		chart.getCategoryPlot().getRangeAxis().setLabelFont(font);

		return chart;
	}


	@Override
	public void windowClosing(WindowEvent event) {
		// TODO Auto-generated method stub
	}

}
