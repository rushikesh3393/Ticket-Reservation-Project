package ticketreservation.repository;

import ticketreservation.model.UserModel;
import java.sql.*;
import java.util.*;
import org.apache.log4j.*;



import java.util.*;

public class UserRepositoryImpl extends DBUSER implements UserRepository
{
	Logger logger=Logger.getLogger(UserRepositoryImpl.class);
	List<UserModel> custList;

	public boolean isAddNewUser(UserModel model) 
	{
		try 
		{
			
			stmt=conn.prepareStatement("insert into usermaster (uid,uname,uemail,upassword) values('0',?,?,?)");
			stmt.setString(1,model.getUname());
			stmt.setString(2,model.getUemail());
			stmt.setString(3,model.getUpassword());
		
			
			int value=stmt.executeUpdate();
			return value>0?true:false;
				
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex.getMessage());
			logger.fatal("Error is in ::UserRepositoryImpl->isAddNewUser "+ex.getMessage());
			return false;
	
		}
		
	}

	public boolean isExistingUser(String euname,String eupassword) {
		try {
			
			stmt=conn.prepareStatement("select *from usermaster where uname=? and upassword=? and role='customer'");
			stmt.setString(1, euname);
			stmt.setString(2, eupassword);
			rs = stmt.executeQuery();
			int value=0;
			if (rs.next()) 
			{
				value=1;
			}
		
			return value > 0 ? true : false;
			
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			logger.fatal("Error is in ::UserRepositoryImpl->isExistingUser "+ex.getMessage());
			return false;
			
		}
		
	}


	public boolean isExistingAdmin(String euname, String eupassword) {
		
         try {
			
			stmt=conn.prepareStatement("select *from usermaster where uname=? and upassword=? and role='admin'");
			stmt.setString(1, euname);
			stmt.setString(2, eupassword);
			rs = stmt.executeQuery();
			int value=0;
			if (rs.next()) 
			{
				value=1;
			}
		
			return value > 0 ? true : false;
			
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			logger.fatal("Error is in ::UserRepositoryImpl->isExistingAdmin "+ex.getMessage());
			return false;
		}
	}

	public boolean isAddNewAdmin(UserModel model) {
		try 
		{
			stmt=conn.prepareStatement("insert into usermaster (uid,uname,uemail,upassword,role) values('0',?,?,?,?)");
			stmt.setString(1,model.getUname());
			stmt.setString(2,model.getUemail());
			stmt.setString(3,model.getUpassword());
			stmt.setString(4,"Admin");
			
			int value=stmt.executeUpdate();
			return value>0?true:false;
				
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			logger.fatal("Error is in ::UserRepositoryImpl->isAddNewAdmin "+ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean isExistingKey(String Akey) {
        try {
			
			stmt=conn.prepareStatement("select *from keymaster where Akey=?");
			stmt.setString(1, Akey);
			
			rs = stmt.executeQuery();
			int value=0;
			if (rs.next()) 
			{
				value=1;
			}
		
			return value > 0 ? true : false;
			
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			logger.fatal("Error is in ::UserRepositoryImpl->isExistingKey"+ex.getMessage());
			return false;
		}
	}

	
	public List<UserModel> getAllCustomer() 
	{
	
		custList = new ArrayList<UserModel>();
		try 
		{
			stmt = conn.prepareStatement("select *from usermaster where role='customer'");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				custList.add(new UserModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			
		    return custList.size()>0?custList:null;
		}
		catch (Exception ex) 
		{
			System.out.println("Error is:"+ex);
			logger.fatal("Error is in ::UserRepositoryImpl->getAllCustomer"+ex.getMessage());
			return null;
		}

	}


	public boolean isDeleteCustomer(int uid) 
	{
		try
		{
			stmt=conn.prepareStatement("delete from usermaster where uid=?");
			stmt.setInt(1,uid);
			
			int value=stmt.executeUpdate();
			return value > 0 ? true : false;
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			logger.fatal("Error is in ::UserRepositoryImpl->isDeleteCustomer"+ex.getMessage());
			return false;
		}
	}

	public int getUidByUserName(String uname) 
	{
		try {
			stmt=conn.prepareStatement("select uid from usermaster where uname=? ");
			stmt.setString(1, uname);
			
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
			}
			else
			{
				return -1;
			}
			
		}
		catch(Exception ex)
		{
			logger.fatal("Error is in ::UserRepositoryImpl->getUidByUserName"+ex.getMessage());

			return -1;
		}
		
	}



}
