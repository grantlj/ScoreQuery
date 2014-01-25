

package test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import bean.Admin;



import util.*;

public class HibernateTest {
  public static void main(String[] args)
  {
	  SessionFactory sf=MySessionFactory.getSessionFactory();
	  Session session=sf.openSession();
	  Transaction ts=null;
	  try
		{
		  ts=session.beginTransaction();
		
		  Admin admin=new Admin();
		  admin.setUsername("grantlj");
		  admin.setUserpwd("liujiang940414");
		  session.save(admin);
		  ts.commit();
		
		}
		catch (Exception e)
		{
			if (ts!=null)
	          ts.rollback();
			e.printStackTrace();
		}
		
		finally
		{
		  session.close();
		} 
  }
}
