package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

public class TableOperate {

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String sql = null;

	public void delUser(String email) {

		try {
			con = ConnectDB.getConnection();
			sql = "delete from users where email = ?";
			pst = con.prepareStatement(sql);
			pst.setObject(1, email);

			int num = pst.executeUpdate();
			if (num != 0) {
				JOptionPane.showMessageDialog(null, "删除成功！", "系统提示", JOptionPane.PLAIN_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

	}

	public void delList(String cTime, String userEmail) {
		try {
			con = ConnectDB.getConnection();
			sql = "delete from list where cTime = ? and userEmail = ?";
			pst = con.prepareStatement(sql);
			pst.setObject(1, cTime);
			pst.setObject(2, userEmail);

			int num = pst.executeUpdate();
			if (num != 0) {
				JOptionPane.showMessageDialog(null, "删除成功！", "系统提示", JOptionPane.PLAIN_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

	}

	public void delProj(int projId) {
		try {
			con = ConnectDB.getConnection();
			sql = "delete from project where projId = ?";
			pst = con.prepareStatement(sql);
			pst.setObject(1, projId);

			int num = pst.executeUpdate();
			if (num != 0) {
				JOptionPane.showMessageDialog(null, "删除成功！", "系统提示", JOptionPane.PLAIN_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

	}

	public void delIdeas(String cTime, String userEmail) {
		try {
			con = ConnectDB.getConnection();
			sql = "delete from ideas where cTime = ? and userEmail = ?";
			pst = con.prepareStatement(sql);
			pst.setObject(1, cTime);
			pst.setObject(2, userEmail);

			int num = pst.executeUpdate();
			if (num != 0) {
				JOptionPane.showMessageDialog(null, "删除成功！", "系统提示", JOptionPane.PLAIN_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

	}

	public void delTask(int taskId) {
		try {
			con = ConnectDB.getConnection();
			sql = "delete from task where taskId = ?";
			pst = con.prepareStatement(sql);
			pst.setObject(1, taskId);

			int num = pst.executeUpdate();
			if (num != 0) {
				JOptionPane.showMessageDialog(null, "删除成功！", "系统提示", JOptionPane.PLAIN_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

	}

	public Vector<String> getUserEmailList() {

		Vector<String> list = new Vector<String>();

		try {
			con = ConnectDB.getConnection();
			String sql = "select email from users";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				list.add(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

		return list;
	}

	public Vector<String> getProjName(String userEmail) {
		Vector<String> list = new Vector<String>();

		try {
			con = ConnectDB.getConnection();
			String sql = "select projname from project where userEmail = ?";
			pst = con.prepareStatement(sql);
			pst.setObject(1, userEmail);
			rs = pst.executeQuery();

			while (rs.next()) {
				list.add(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

		return list;
	}

	public ArrayList<Project> selectProj(String email) {

		ArrayList<Project> aList = new ArrayList<Project>();
		try {
			sql = "select projId, projname, projStatus, remark from project where userEmail = ?";
			con = ConnectDB.getConnection();
			pst = con.prepareStatement(sql);
			pst.setObject(1, email);
			rs = pst.executeQuery();

			while (rs.next()) {
				Project proj = new Project();
				proj.setProjId(rs.getInt("projId"));
				proj.setProjName(rs.getString("projname"));
				proj.setProjStatus(rs.getString("projStatus"));
				proj.setRemark(rs.getString("remark"));

				aList.add(proj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

		return aList;
	}

	public ArrayList<Task> selectTask(String projname) {

		ArrayList<Task> aList = new ArrayList<Task>();
		try {
			sql = "select taskId, taskname, taskStatus, task.remark from task,project "
					+ "where task.projId = project.projId and projname  = ?";
			con = ConnectDB.getConnection();
			pst = con.prepareStatement(sql);
			pst.setObject(1, projname);
			rs = pst.executeQuery();

			while (rs.next()) {
				Task task = new Task();
				task.setTaskId(rs.getInt("taskId"));
				task.setTaskName(rs.getString("taskname"));
				task.setTaskStatus(rs.getString("taskStatus"));
				task.setRemark(rs.getString("remark"));

				aList.add(task);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

		return aList;
	}

	public ArrayList<List> selectList(String email) {

		ArrayList<List> aList = new ArrayList<List>();
		try {
			sql = "select cTime, content from list where userEmail = ?";
			con = ConnectDB.getConnection();
			pst = con.prepareStatement(sql);
			pst.setObject(1, email);
			rs = pst.executeQuery();

			while (rs.next()) {
				List list = new List();
				list.setcTime(rs.getString("cTime"));
				list.setContent(rs.getString("content"));

				aList.add(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

		return aList;
	}

	public ArrayList<Ideas> selectIdeas(String email) {

		ArrayList<Ideas> aList = new ArrayList<Ideas>();

		try {
			sql = "select cTime, title, content from ideas where userEmail = ?";
			con = ConnectDB.getConnection();
			pst = con.prepareStatement(sql);
			pst.setObject(1, email);
			rs = pst.executeQuery();

			while (rs.next()) {
				Ideas ideas = new Ideas();
				ideas.setcTime(rs.getString("cTime"));
				ideas.setTitle(rs.getString("title"));
				ideas.setContent(rs.getString("content"));

				aList.add(ideas);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

		return aList;
	}

	public ArrayList<Ideas> selectIdeas() {

		ArrayList<Ideas> aList = new ArrayList<Ideas>();

		try {
			sql = "select userEmail,cTime, title, content from ideas";
			con = ConnectDB.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Ideas ideas = new Ideas();
				ideas.setEmail(rs.getString("userEmail"));
				ideas.setcTime(rs.getString("cTime"));
				ideas.setTitle(rs.getString("title"));
				ideas.setContent(rs.getString("content"));

				aList.add(ideas);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

		return aList;
	}

	public void insertList(String email, String content) {
		try {
			con = ConnectDB.getConnection();
			sql = "insert into list(userEmail,content) values(?,?)";
			pst = con.prepareStatement(sql);
			pst.setObject(1, email);
			pst.setObject(2, content);

			if (pst.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "保存成功", "处理结果", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "保存失败", "处理结果", JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}
	}

	public void insertIdeas(String email, String title, String content) {
		try {
			con = ConnectDB.getConnection();
			sql = "insert into ideas(userEmail,title,content) values(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setObject(1, email);
			pst.setObject(2, title);
			pst.setObject(3, content);

			if (pst.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "保存成功", "处理结果", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}
	}

	public void insertTask(int projId, String taskname, String remark) {
		try {
			con = ConnectDB.getConnection();
			sql = "insert into task(projId,taskname, remark) values(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setObject(1, projId);
			pst.setObject(2, taskname);
			pst.setObject(3, remark);

			if (pst.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "保存成功", "处理结果", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "保存失败", "处理结果", JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}
	}

	public void insertProject(String userEmail, String projname, String remark) {
		try {
			con = ConnectDB.getConnection();
			sql = "insert into project(userEmail,projname,remark) values(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setObject(1, userEmail);
			pst.setObject(2, projname);
			pst.setObject(3, remark);

			if (pst.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "保存成功", "处理结果", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "保存失败", "处理结果", JOptionPane.ERROR_MESSAGE);

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

	}

	public void updateIdeas(String title, String content, String email, String cTime) {

		try {
			con = ConnectDB.getConnection();

			if (!title.equals("") && !content.equals("")) {
				sql = "update ideas set title = ? , content = ? where userEmail = ? and cTime = ?";
				pst = con.prepareStatement(sql);
				pst.setObject(1, title);
				pst.setObject(2, content);
				pst.setObject(3, email);
				pst.setObject(4, cTime);

			} else if (title.equals("") && !content.equals("")) {
				sql = "update ideas set content = ? where userEmail = ? and cTime = ?";
				pst = con.prepareStatement(sql);
				pst.setObject(1, content);
				pst.setObject(2, email);
				pst.setObject(3, cTime);

			} else if (!title.equals("") && content.equals("")) {
				sql = "update ideas set title = ? where userEmail = ? and cTime = ?";
				pst = con.prepareStatement(sql);
				pst.setObject(1, title);
				pst.setObject(2, email);
				pst.setObject(3, cTime);
			} else {
				JOptionPane.showMessageDialog(null, "请填入要修改的数据", "系统提示", JOptionPane.INFORMATION_MESSAGE);
			}

			if (pst.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "修改成功", "处理结果", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}
	}

	public void updateList(String content, String email, String cTime) {

		try {
			con = ConnectDB.getConnection();

			if (!content.equals("")) {
				sql = "update list set content = ? where userEmail = ? and cTime = ?";
				pst = con.prepareStatement(sql);
				pst.setObject(1, content);
				pst.setObject(2, email);
				pst.setObject(3, cTime);

				if (pst.executeUpdate() != 0) {
					JOptionPane.showMessageDialog(null, "修改成功", "处理结果", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}
	}

	public void updateProj(String projStatus, int projId) {
		try {
			con = ConnectDB.getConnection();

			sql = "update project set projStatus = ? where projId = ?";
			pst = con.prepareStatement(sql);
			pst.setObject(1, projStatus);
			pst.setObject(2, projId);

			if (pst.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "修改成功", "处理结果", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (

		SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}
	}

	public void updateTask(int taskId, String taskStatus) {
		try {
			con = ConnectDB.getConnection();

			sql = "update task set taskStatus = ? where taskId = ?";
			pst = con.prepareStatement(sql);
			pst.setObject(1, taskStatus);
			pst.setObject(2, taskId);

			if (pst.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "修改成功", "处理结果", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (

		SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}
	}

	public void updateUser(String email, String paswd, String check_paswd) {
		try {
			con = ConnectDB.getConnection();

			if (paswd.equals(check_paswd)) {
				sql = "update users set paswd = ? where email = ?";
				pst = con.prepareStatement(sql);
				pst.setObject(1, check_paswd);
				pst.setObject(2, email);
				
				if (pst.executeUpdate() != 0) {
					JOptionPane.showMessageDialog(null, "修改成功", "处理结果", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "两次密码输入不匹配，请重新输入", "处理结果", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}
	}

	public int[] getArray(String userEmail) {
		int[] a = new int[4];
		String[] projStatus = { "已完成", "推迟", "搁置", "进行中" };

		try {
			con = ConnectDB.getConnection();
			for (int i = 0; i < 4; i++) {
				sql = "select COUNT(*) from project where projStatus = ? and userEmail = ?";
				pst = con.prepareStatement(sql);
				pst.setObject(1, projStatus[i]);
				pst.setObject(2, userEmail);
				rs = pst.executeQuery();

				if (rs.next()) {
					a[i] = rs.getInt(1);
				}
			}
		} catch (

		SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

		return a;
	}

	public int[] getArray_2(String projname) {
		int[] a = new int[4];
		String[] taskStatus = { "已完成", "推迟", "搁置", "进行中" };

		try {
			con = ConnectDB.getConnection();
			for (int i = 0; i < 4; i++) {
				sql = "select COUNT(*) from task,project where task.projId = project.projId "
						+ "and taskStatus = ? and projname = ?";
				pst = con.prepareStatement(sql);
				pst.setObject(1, taskStatus[i]);
				pst.setObject(2, projname);
				rs = pst.executeQuery();

				if (rs.next()) {
					a[i] = rs.getInt(1);
				}
			}
		} catch (

		SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			ConnectDB.closeAll(rs, pst, con);
		}

		return a;
	}
}