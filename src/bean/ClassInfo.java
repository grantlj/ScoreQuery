package bean;

public class ClassInfo {
  private String chartTitle;
  private int studentCount;
  private int subjectCount;
  private double avgGpa;
  private Integer id;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public double getAvgGpa() {
	return avgGpa;
}
public void setAvgGpa(double avgGpa) {
	this.avgGpa = avgGpa;
}
public String getChartTitle() {
	return chartTitle;
}
public void setChartTitle(String chartTitle) {
	this.chartTitle = chartTitle;
}
public int getStudentCount() {
	return studentCount;
}
public void setStudentCount(int studentCount) {
	this.studentCount = studentCount;
}
public int getSubjectCount() {
	return subjectCount;
}
public void setSubjectCount(int subjectCount) {
	this.subjectCount = subjectCount;
}
  
}
