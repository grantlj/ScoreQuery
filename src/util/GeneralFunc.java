package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class GeneralFunc {
  // private static final String configFilePath="d://WebProjects//ScoreQuery//test//config.dat";
   private static final String defaultDir="d://WebProjects//ScoreQuery//test//";
   private static final String configFilePath=defaultDir+"config.dat";
   private static final String defaultxlsPath=defaultDir+"cj.xls";
   public static String getDefaultdir() {
	return defaultDir;
}

public static boolean validAdmin(String u,String p) throws HibernateException, SQLException
   {
	 Session sess=null;
	   try
	  {
	 
	  SessionFactory sf=MySessionFactory.getSessionFactory();
	  sess=sf.openSession();
	  sess.beginTransaction();
	  Statement state=sess.connection().createStatement();
	  
	  String exp="select userpwd from admin where username="+"'"+u+"'";
	   
	   ResultSet rs=state.executeQuery(exp);
	   
	   rs.next();
	   
	
	   if (rs.getString(1).equals(p))
	     return true;
	   else
		 return false;
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
		  return false;
	  }
	  finally
	  {
		  if (sess!=null)
			  sess.close();
	  }
   }

public static boolean checkScoreExists() {
	File file=new File(configFilePath);
	if (!file.exists())
	  return false;
	else
	  return true;
}

public static String getConfigfilepath() {
	return configFilePath;
}

public static void initialScoreFile(String src) throws FileNotFoundException, IOException {
	// TODO Auto-generated method stub
	if (new File(defaultxlsPath).exists()) new File(defaultxlsPath).delete();
	File f=new File(defaultDir+src);
	f.renameTo(new File(defaultxlsPath));
	
	 HSSFSheet sheet=new HSSFWorkbook(new FileInputStream(defaultxlsPath)).getSheetAt(0);
		
	  int subjectCount=sheet.getRow(4).getLastCellNum()-1;
	  int stuCount=((sheet.getLastRowNum()-2)-6)/2+1;
	  double avgGpa=0;
	  String chartTitle=sheet.getRow(2).getCell(0).getStringCellValue();
	  
	  HSSFRow subRow=sheet.getRow(3);
	  HSSFRow ptRow=sheet.getRow(4);
	  
	  DbOperator.setSubjectTableClear(false);
	  
	  double[] point=new double[subjectCount];
	  
	  for (int i=0;i<subjectCount;i++)
	  {
		  String str1,str2;
		  str1=subRow.getCell(i+1).getStringCellValue();
		  str2=ptRow.getCell(i+1).getStringCellValue();
		  str2=str2.substring(0, str2.length()-1);
		  
		  point[i]=Double.parseDouble(str2);
		  
		  DbOperator.saveNewSubject(i+1,str1, str2);
		  
		  //System.out.println(str1);
		  //System.out.println(str2);
	  }
	  DbOperator.setSubjectTableClear(true);
	  
	  
	  DbOperator.setStudentTableClear(false);
	  int stuP=5;
	  for (int i=0;i<stuCount;i++)
	  {
		  HSSFRow stuIdRow=sheet.getRow(stuP);
		  HSSFRow stuRow=sheet.getRow(stuP+1);
		  String stuName=stuRow.getCell(0).getStringCellValue();
		  String stuId=stuIdRow.getCell(0).getStringCellValue();
		  stuId=stuId.substring(1,stuId.length()-1);
		  stuName=stuName.substring(1,stuName.length()-1);
		  
		  double gpa=0;String scoreList="";
		  double pointCount=0;
		  
		  // System.out.println(stuName+" "+stuId);
		  
		  for (int j=0;j<subjectCount;j++)
		  {
			 String pointj=stuIdRow.getCell(j+1).getStringCellValue();
			 try
			 {
			   if (pointj!="")
				 pointj=pointj.substring(2,pointj.lastIndexOf('/'));
			   else 
				 pointj="32767";
			 }
			 catch (Exception e)
			 {
				 pointj="-32767";
			 }
			 
			 scoreList=scoreList+pointj+",";
			 
			 if (!(pointj.equals("32767") || pointj.equals("-32767")))
			 {
				 //is valid point. calc the gpa.
				 gpa+=Double.parseDouble(pointj)*point[j];
				 pointCount+=point[j];
				 
			 }
			// System.out.print(point+"_");
		  }
		  scoreList=scoreList.substring(0,scoreList.length()-1);
		  gpa/=pointCount;
		  avgGpa+=gpa;
		  DbOperator.saveNewStudent(stuId, stuName, stuId, scoreList, gpa);
		  //System.out.println();
		  stuP+=2;
		
	  }
	  
	  DbOperator.setStudentTableClear(true);
	  
	  avgGpa/=stuCount;
	  DbOperator.setClassTableClear(false);
	  DbOperator.saveNewClass(chartTitle, stuCount, subjectCount, avgGpa);
	  DbOperator.setClassTableClear(true);
	  
}
}
