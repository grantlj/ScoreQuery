package bean;

public class Student {
  private Integer id;
  private String stuId;
  public String getStuId() {
	return stuId;
}
public void setStuId(String stuId) {
	this.stuId = stuId;
}
private String name;
  private String pwd;
  private String scoreList;
  private double gpa;
  private int rank;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getScoreList() {
	return scoreList;
}
public void setScoreList(String scoreList) {
	this.scoreList = scoreList;
}
public double getGpa() {
	return gpa;
}
public void setGpa(double gpa) {
	this.gpa = gpa;
}
public int getRank() {
	return rank;
}
public void setRank(int rank) {
	this.rank = rank;
}
  
}
