package bean;

import java.io.Serializable;

public class ConfigFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chartTitle;
	private int stuCount;
	private int subjectCount;
	private double avgGpa;
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getChartTitle() {
		return chartTitle;
	}
	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}
	public int getStuCount() {
		return stuCount;
	}
	public void setStuCount(int stuCount) {
		this.stuCount = stuCount;
	}
	public int getSubjectCount() {
		return subjectCount;
	}
	public void setSubjectCount(int subjectCount) {
		this.subjectCount = subjectCount;
	}
	public double getAvgGpa() {
		return avgGpa;
	}
	public void setAvgGpa(double avgGpa) {
		this.avgGpa = avgGpa;
	}
	
}
