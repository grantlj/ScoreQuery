package util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
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
  
  public static void saveNewStudent(int i,String stuId,String name,String pwd,String scoreList,double gpa)
  {
	  Student newStu=new Student();
	 // newStu.setId(Integer.valueOf(stuId));
	  newStu.setId(i);
	  newStu.setStuId(stuId);
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
	 newClassInfo.setId(1);
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

public static ClassInfo getClassInfo() {
   
	Session sess=MySessionFactory.getSessionFactory().openSession();
	ClassInfo tmp=(ClassInfo) sess.get(ClassInfo.class, 1);
    sess.close();
	return tmp;
}

public static Subject getSubject(int i) {
	// TODO Auto-generated method stub
	Session sess=MySessionFactory.getSessionFactory().openSession();
	Subject tmp=(Subject) sess.get(Subject.class, i);
    sess.close();
	return tmp;
}

public static Student getStudent(int i) throws SQLException {
	// TODO Auto-generated method stub
	Session sess=MySessionFactory.getSessionFactory().openSession();
	/*
	Statement state=sess.connection().createStatement();
	ResultSet rs=state.executeQuery("select id from student limit 1");
	rs.next();
	i=i+rs.getInt(1)-1;
	//System.out.println("The id is::::"+i);
	Student tmp;
	do
	{
	tmp=(Student) sess.get(Student.class, i);
     
      i++;
	}
    while (tmp==null);
    */
    Student tmp=(Student) sess.get(Student.class, i);
	 sess.close();
	return tmp;
}

public static void dropAllTable() {
	// TODO Auto-generated method stub
	Session sess=MySessionFactory.getSessionFactory().openSession();
	try {
		Statement state=sess.connection().createStatement();
		String sql;
		sql="TRUNCATE TABLE classinfo";
		state.execute(sql);
		sql="TRUNCATE TABLE student";
		state.execute(sql);
		sql="TRUNCATE TABLE subject";
		state.execute(sql);
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally 
	{
		sess.close();
	}
	
}

public static Student getStudentbyUP(String u, String p) {
	// TODO Auto-generated method stub
	Session sess=MySessionFactory.getSessionFactory().openSession();
	
	/*Student tmp;
	int stuCount=getClassInfo().getStudentCount();
	for (int i=1;i<=stuCount;i++)
	{
	  tmp=(Student) sess.get(Student.class, i);
	  if (tmp.getStuId().equals(u) && tmp.getPwd().equals(p))
	  {
		  sess.close();
		  return tmp;
	  }
	}
	sess.close();
	return null;
	*/
	
	Student tmp;
	tmp=(Student) sess.createQuery("from Student where stuID="+u+" and pwd="+p).uniqueResult();
    sess.close();
    return tmp;
	
}

}
