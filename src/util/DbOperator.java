package util;


import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import bean.*;

public class DbOperator {
  private static boolean subjectTableClear=false;
  private static boolean studentTableClear=false;
  private static boolean classTableClear=false;
  

public static void saveNewSubject(int id,String arg1, String arg2)
  {
	  Subject newSub=new Subject();
	  newSub.setId(Integer.valueOf(id));
	  newSub.setSubjectName(arg1);
	  newSub.setSubjectPoint(Double.parseDouble(arg2));
	  
	  Session sess=null;
	  // System.out.println("haha"+newSub.getSubjectName()+" "+newSub.getSubjectPoint());
	  try
	  {
	 
	  SessionFactory sf=MySessionFactory.getSessionFactory();
	  sess=sf.openSession();
	  Transaction trans=sess.beginTransaction();
	  if (!subjectTableClear)
	  {
	    Statement state=sess.connection().createStatement();
	    String exp="TRUNCATE TABLE subject";
	    state.execute(exp);
	    subjectTableClear=true;
	  }
	  
	  sess.save(newSub);
	  trans.commit();
	  
	  
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
		 
	  }
	  finally
	  {
		  if (sess!=null)
			  sess.close();
	  }
  }
  
  public static void saveNewStudent(String stuId,String name,String pwd,String scoreList,double gpa)
  {
	  Student newStu=new Student();
	  newStu.setId(Integer.valueOf(stuId));
	  newStu.setName(name);
	  newStu.setPwd(pwd);
	  newStu.setScoreList(scoreList);
	  newStu.setGpa(gpa);
	  
	  Session sess=null;
	  
	  try
	  {
	 
	  SessionFactory sf=MySessionFactory.getSessionFactory();
	  sess=sf.openSession();
	  Transaction trans=sess.beginTransaction();
	  if (!studentTableClear)
	  {
	    Statement state=sess.connection().createStatement();
	    String exp="TRUNCATE TABLE student";
	    state.execute(exp);
	    studentTableClear=true;
	  }
	  
	  sess.save(newStu);
	  trans.commit();
	  
	  
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
		 
	  }
	  finally
	  {
		  if (sess!=null)
			  sess.close();
	  }
  }

  public static void saveNewClass(String chartTitle, int studentCount, int subjectCount, double avgGpa)
  {
	 ClassInfo newClassInfo=new ClassInfo();
	 newClassInfo.setChartTitle(chartTitle);
	 newClassInfo.setStudentCount(studentCount);
	 newClassInfo.setSubjectCount(subjectCount);
	 newClassInfo.setAvgGpa(avgGpa);
	 Session sess=null;
	  try
	  {
	 
	  SessionFactory sf=MySessionFactory.getSessionFactory();
	  sess=sf.openSession();
	  Transaction trans=sess.beginTransaction();
	  if (!classTableClear)
	  {
	    Statement state=sess.connection().createStatement();
	    String exp="TRUNCATE TABLE classinfo";
	    state.execute(exp);
	    classTableClear=true;
	  }
	  
	  sess.save(newClassInfo);
	  trans.commit();
	  
	  
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
		 
	  }
	  finally
	  {
		  if (sess!=null)
			  sess.close();
	  }
  }

public static boolean isStudentTableClear() {
	return studentTableClear;
}

public static void setStudentTableClear(boolean studentTableClear) {
	DbOperator.studentTableClear = studentTableClear;
}

public static boolean isSubjectTableClear() {
	return subjectTableClear;
}

public static void setSubjectTableClear(boolean subjectTableClear) {
	DbOperator.subjectTableClear = subjectTableClear;
}

public static boolean isClassTableClear() {
	return classTableClear;
}

public static void setClassTableClear(boolean classTableClear) {
	DbOperator.classTableClear = classTableClear;
}

}
