package main.java.working.worktime;

import java.util.Date;

/*サンプル用*/
public class WorkTime {

	private Date workTime;


	//初期値を作るコンストラクタ
	public WorkTime(){
	workTime = new Date(); //"00:00"分をセット。日時はどうしようかな・・・
	}

/*	//任意の日時を設定するコンストラクタ
	public WorkTime(WorkTimeEntity entity){
	workTime = entity.getWorkDateTime;
	}*/


	//そのまま返す
	public Date getDateTime(){
		return workTime;
	}

	//文字列をDate型にコンバート : 時分
	public Date getTimeDate(){
		return null;
	}

	//workTimeを文字列にコンバート: 時分
	public String getTimeString(){
	return "12:34";
	}
	//workTimeを文字列にコンバート: 年月日
	public String getDateString(){
	return "2016/3/30";
	}
	//DBアクセス用。DAOでもいいかも
	public java.sql.Date getSqlDate(){
	return null;//コンバート後のDate
	}
	public void increase(String UNIT, int step){
	//任意の単位（UNIT）の値を増やしたり（マイナスのstepは不可）
	}
	public void decrease(String UNIT, int step){
	//任意の単位（UNIT）の値を減らす（マイナスのstepは不可）
	}

	//あとから日時を変更する
	/*public void setWorkTime(WorkTimeEntity entity){ }*/
	//あとから日時を変更する: Date型版もあるといいかな。
	public void setWorkTime(Date date){ }
}

