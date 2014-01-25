package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.*;
import bean.Subject;

public class POITest {
  public static void main(String[] args) throws FileNotFoundException, IOException
  {
	  HSSFSheet sheet=new HSSFWorkbook(new FileInputStream("d:\\test2.xls")).getSheetAt(0);
	
	  int subjectCount=sheet.getRow(4).getLastCellNum()-1;
	  int stuCount=((sheet.getLastRowNum()-2)-6)/2+1;
	  
	  HSSFRow subRow=sheet.getRow(3);
	  HSSFRow ptRow=sheet.getRow(4);
	  
	  for (int i=0;i<subjectCount;i++)
	  {
		  String str1,str2;
		  str1=subRow.getCell(i+1).getStringCellValue();
		  str2=ptRow.getCell(i+1).getStringCellValue();
		  
		  //System.out.println(str1);
		  //System.out.println(str2);
	  }
	  
	
	  int stuP=5;
	  for (int i=0;i<stuCount;i++)
	  {
		  HSSFRow stuIdRow=sheet.getRow(stuP);
		  HSSFRow stuRow=sheet.getRow(stuP+1);
		  String stuName=stuRow.getCell(0).getStringCellValue();
		  String stuId=stuIdRow.getCell(0).getStringCellValue();
		  stuId=stuId.substring(1,stuId.length()-1);
		  stuName=stuName.substring(1,stuName.length()-1);
		  System.out.println(stuName+" "+stuId);
		  
		  for (int j=0;j<subjectCount;j++)
		  {
			 String point=stuIdRow.getCell(j+1).getStringCellValue();
			 try
			 {
			   if (point!="")
				 point=point.substring(2,point.lastIndexOf('/'));
			   else 
				 point="32767";
			 }
			 catch (Exception e)
			 {
				 point="-32767";
			 }
			// System.out.print(point+"_");
		  }
		  
		  System.out.println();
		  stuP+=2;
	  }
	  
  }
}
