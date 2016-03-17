package main.java.kot.common;

/**
 * 残業可能時間関連
 * @author ueno
 **/
public class LimitWorkingTime {

	/*月の残業上限時間*/
	private String upperLimitTime;
	/*月の法定労働時間*/
	private String monthlyLegalWorkingTime;
	/*可能残業時間メッセージ*/
	private String overtimeMessage;
	/*法定労働時間メッセージ*/
	private String monthlyLegalMessage;

	public LimitWorkingTime() {
	}

	public LimitWorkingTime(String upperLimitTime,
			String monthlyLegalWorkingTime, String overtimeMessage,
			String monthlyLegalMessage) {
		this.upperLimitTime = upperLimitTime;
		this.monthlyLegalWorkingTime = monthlyLegalWorkingTime;
		this.overtimeMessage = overtimeMessage;
		this.monthlyLegalMessage = monthlyLegalMessage;
	}

	public String getUpperLimitTime() {
		return upperLimitTime;
	}
	public void setUpperLimitTime(String upperLimitTime) {
		this.upperLimitTime = upperLimitTime;
	}
	public String getMonthlyLegalWorkingTime() {
		return monthlyLegalWorkingTime;
	}
	public void setMonthlyLegalWorkingTime(String monthlyLegalWorkingTime) {
		this.monthlyLegalWorkingTime = monthlyLegalWorkingTime;
	}
	public String getOvertimeMessage() {
		return overtimeMessage;
	}
	public void setOvertimeMessage(String overtimeMessage) {
		this.overtimeMessage = overtimeMessage;
	}
	public String getMonthlyLegalMessage() {
		return monthlyLegalMessage;
	}
	public void setMonthlyLegalMessage(String monthlyLegalMessage) {
		this.monthlyLegalMessage = monthlyLegalMessage;
	}

}
