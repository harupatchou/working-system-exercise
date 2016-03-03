package main.java.jp.kot.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.jp.kot.common.WorkingTime;
import main.java.jp.kot.common.WorkingTimeAll;
import resources.DBManager;

/**
 *労働時間関連DAO
 * @author ueno
 **/
public class WorkingTimeDao {

	private static String tableName = "working_all";

	/*総労働時間取得*/
	public static WorkingTimeAll WorkingTimeAll(String employeeName){
		String sql = "SELECT working_time_all FROM " + tableName;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){

			//総労働時間格納用
			WorkingTimeAll workingTimeAll = new WorkingTimeAll();
			//workingTimeAll.setWorkingTimeAll(10);
			//return workingTimeAll;

			try(ResultSet rs = pstmt.executeQuery()){

				//労働時間格納用リスト
				List<Integer> workingTimeList = new ArrayList<>();
				while(rs.next()){
					WorkingTime workingTime = new WorkingTime();
					workingTime.setWorkTime(rs.getInt("working_time_all"));

					//変数が増えることを見越してあえてこの書き方
					workingTimeList.add(workingTime.getWorkTime());
				}

				//総労働時間算出
				int total = 0;
				for(int i = 0; i < workingTimeList.size(); i++){
					total += workingTimeList.get(i);
				}
				workingTimeAll.setWorkingTimeAll(total);

				//暫定的に残業時間をnull
				workingTimeAll.setOverWorkingTimeAll(null);

				return workingTimeAll;
			}

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}

	}
}