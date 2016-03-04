package main.java.jp.kot.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.jp.kot.common.WorkingTimeTotal;
import resources.DBManager;

/**
 *労働時間関連DAO
 * @author ueno
 **/
public class WorkingTimeDao {

	private static String tableName = "working_all";

	/*総労働時間取得*/
	public static WorkingTimeTotal workingTimeTotal(Integer employeeId){
		String sql = "SELECT working_time_all,overtime_all FROM " + tableName + " WHERE" + employeeId;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){

			//総労働時間格納用
			WorkingTimeTotal workingTimeTotal = new WorkingTimeTotal();

			try(ResultSet rs = pstmt.executeQuery()){

				//労働時間格納用リスト
				List<Integer> workingTimeHourList = new ArrayList<>();
				List<Integer> workingTimeMinuteList =new ArrayList<>();

				//残業時間格納用リスト
				List<Integer> overWorkingTimeHourList = new ArrayList<>();
				List<Integer> overWorkingTimeMinuteList =new ArrayList<>();

				while(rs.next()){
					String[] strWorkTimeList = rs.getString("working_time_all").split(":");
					workingTimeHourList.add(Integer.parseInt(strWorkTimeList[0]));
					workingTimeMinuteList.add(Integer.parseInt(strWorkTimeList[1]));

					String[] strOverWorkTime = rs.getString("overtime_all").split(":");
					overWorkingTimeHourList.add(Integer.parseInt(strOverWorkTime[0]));
					overWorkingTimeMinuteList.add(Integer.parseInt(strOverWorkTime[1]));
				}


				//総労働時間算出
				int workingTimeHourTotal = 0;
				for(int i = 0; i < workingTimeHourList.size(); i++){
					workingTimeHourTotal += workingTimeHourList.get(i);
				}

				int workingTimeMinuteTotal = 0;
				for (int i = 0; i < workingTimeMinuteList.size(); i++) {
					workingTimeMinuteTotal += workingTimeMinuteList.get(i);
				}
				workingTimeHourTotal += workingTimeMinuteTotal / 60;
				workingTimeMinuteTotal = workingTimeMinuteTotal % 60;


				//String de tunagu
				//String tempWorkingTimeTotal

				//workingTimeTotal.setWorkingTimeTotal();


				return workingTimeTotal;
			}

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}

	}
}