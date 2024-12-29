package ticketreservation.repository;

import ticketreservation.model.CategoryModel;
import java.sql.*;
import java.util.*;
import org.apache.log4j.Logger;


public class CategoryRepositoryImpl extends DBUSER implements CategoryRepository 
{
	Logger logger=Logger.getLogger(CategoryRepositoryImpl.class);

	List<CategoryModel> catlist;
	public boolean isAddNewCategory(CategoryModel cmodel) 
	{
		try 
		{
			stmt = conn.prepareStatement("insert into category values('0',?)");
			stmt.setString(1,cmodel.getEcategory());
            int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		} 
		catch (Exception ex) 
		{
		    System.out.println("Error is:" + ex);
			logger.fatal("Error is in CategoryRepositoryImpl::isAddNewCategory"+ex.getMessage());
			return false;
			
		}

	}


	public List<CategoryModel> getAllCategoriesList() 
	{
		catlist=new ArrayList<CategoryModel> ();
		try
		{
			stmt=conn.prepareStatement("select *from category");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				
				catlist.add(new CategoryModel(rs.getInt(1),rs.getString(2)));
			}
			return catlist.size()>0?catlist:null;
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			logger.fatal("Error is in CategoryRepositoryImpl::getAllCategoriesList"+ex.getMessage());
			
			return null;
		}
		
	}


	public boolean isDeleteCategory(int cid) 
	{
		try {
			stmt=conn.prepareStatement("delete from category where categoryid=?");
			stmt.setInt(1, cid);
			
			int val=stmt.executeUpdate();
			return val > 0 ? true : false;
			
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			logger.fatal("Error is in CategoryRepositoryImpl::isDeleteCategory"+ex.getMessage());
			
			return false;
		}
		
		
	}


	public int getCategoryIdByName(String name) 
	{
	   try {
		   
		   stmt=conn.prepareStatement("select categoryid from category where ecategory=?");
		   stmt.setString(1, name);
		   
		   rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);

			} 
			else {
				return -1;
			}
		   
	   }
	   catch(Exception ex)
	   {
		   System.out.println("Error is:"+ex);
		   logger.fatal("Error is in CategoryRepositoryImpl::getCategoryIdByNamey"+ex.getMessage());
			
		   return -1;
	   }
	}

}
