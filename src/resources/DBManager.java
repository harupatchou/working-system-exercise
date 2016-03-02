package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB接続＆切断用共通クラス
 * @author ueno
 */
public class DBManager {
	private final static String dbName = "attendance";
	private final static String url = "jdbc:postgresql://172.16.0.17:5432/" + dbName;
	private final static String	userName = "postgres";
	private final static String password = "postgres";

	/**
	 * DBに接続し、接続情報を呼び出し元に返す
	 * @return Connectionオブジェクト
	 */
	public static Connection createConnection(){
		try{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url, userName, password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB接続に失敗しました", e);
		}
	}

	/**
	 * 渡されたConnectionオブジェクトの接続を切断します
	 * @param Connectionオブジェクト
	 */
	public static void closeConnection(Connection con){
		try{
			con.close();
		}catch(SQLException e){
		}
	}
}
