package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.object.PanelHeader;

public class PanelHeaderModel {

	public ArrayList<PanelHeader> getAllPanels() {
		Connect db = Connect.getInstance();
		ArrayList<PanelHeader> PanelList = new ArrayList<>();

		String query = String.format("SELECT * FROM panelHeaders");
		ResultSet rs = db.selectData(query);

		try {
			while (rs.next()) {
				Integer panelId = rs.getInt("panelId");
				Integer userId = rs.getInt("userId");
				String panelTitle = rs.getString("panelTitle");
				String panelDescription = rs.getString("panelDescription");
				String location = rs.getString("location");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				Boolean isFinished = rs.getBoolean("isFinished");

				PanelList.add(new PanelHeader(panelId, userId, panelTitle, panelDescription, location, startTime,
						endTime, isFinished));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return PanelList;

	}

	public PanelHeader getPanelById(Integer panelId) {
		Connect db = Connect.getInstance();

		String query = String.format("SELECT * FROM panelHeaders WHERE panelId = %d", panelId);
		ResultSet rs = db.selectData(query);

		try {
			while (rs.next()) {
				Integer panelIds = rs.getInt("panelId");
				Integer userId = rs.getInt("userId");
				String panelTitle = rs.getString("panelTitle");
				String panelDescription = rs.getString("panelDescription");
				String location = rs.getString("location");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				Boolean isFinished = rs.getBoolean("isFinished");

				return new PanelHeader(panelIds, userId, panelTitle, panelDescription, location, startTime, endTime,
						isFinished);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public void addPanel(Integer userId, String panelTitle, String panelDescription, String location, String startTime,
			String endTime) {

		Connect db = Connect.getInstance();

		String query = String.format(
				"INSERT INTO `panelheaders` (userId, panelTitle, panelDescription, location, startTime, endTime, isFinished) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', 0)",
				userId, panelTitle, panelDescription, location, startTime, endTime);

		db.execute(query);

	}

	public void finishPanel(Integer panelId) {
		Connect db = Connect.getInstance();

		String query = String.format("UPDATE panelHeaders SET isFinished = 1 WHERE panelId = %d", panelId);

		db.execute(query);
	}

	public ArrayList<PanelHeader> getUnfinishedPanels() {
		Connect db = Connect.getInstance();
		ArrayList<PanelHeader> PanelList = new ArrayList<>();

		String query = String.format("SELECT * FROM panelHeaders WHERE isFinished = 0");
		ResultSet rs = db.selectData(query);

		try {
			while (rs.next()) {
				Integer panelId = rs.getInt("panelId");
				Integer userId = rs.getInt("userId");
				String panelTitle = rs.getString("panelTitle");
				String panelDescription = rs.getString("panelDescription");
				String location = rs.getString("location");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				Boolean isFinished = rs.getBoolean("isFinished");

				PanelList.add(new PanelHeader(panelId, userId, panelTitle, panelDescription, location, startTime,
						endTime, isFinished));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return PanelList;
	}

	public ArrayList<PanelHeader> getFinishedPanels() {
		Connect db = Connect.getInstance();
		ArrayList<PanelHeader> PanelList = new ArrayList<>();

		String query = String.format("SELECT * FROM panelHeaders WHERE isFinished = 1");
		ResultSet rs = db.selectData(query);

		try {
			while (rs.next()) {
				Integer panelId = rs.getInt("panelId");
				Integer userId = rs.getInt("userId");
				String panelTitle = rs.getString("panelTitle");
				String panelDescription = rs.getString("panelDescription");
				String location = rs.getString("location");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				Boolean isFinished = rs.getBoolean("isFinished");

				PanelList.add(new PanelHeader(panelId, userId, panelTitle, panelDescription, location, startTime,
						endTime, isFinished));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return PanelList;
	}

	public ArrayList<PanelHeader> getAllPanelByInfluencer(Integer userId) {
		Connect db = Connect.getInstance();
		ArrayList<PanelHeader> PanelList = new ArrayList<>();

		String query = String.format("SELECT * FROM panelHeaders WHERE userId = %d", userId);
		ResultSet rs = db.selectData(query);

		try {
			while (rs.next()) {
				Integer panelId = rs.getInt("panelId");
				Integer userIds = rs.getInt("userId");
				String panelTitle = rs.getString("panelTitle");
				String panelDescription = rs.getString("panelDescription");
				String location = rs.getString("location");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				Boolean isFinished = rs.getBoolean("isFinished");

				PanelList.add(new PanelHeader(panelId, userIds, panelTitle, panelDescription, location, startTime,
						endTime, isFinished));
			}

			return PanelList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
