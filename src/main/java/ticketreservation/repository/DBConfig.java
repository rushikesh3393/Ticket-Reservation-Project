package ticketreservation.repository;


import java.sql.*;
import java.util.Properties;
import java.io.*;

public class DBConfig 
{
	protected static Connection conn;
	protected static PreparedStatement stmt;
	protected static ResultSet rs;
	protected static CallableStatement cstmt;
	private static DBConfig db=null;
	
	
	protected DBConfig()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			File f=new File("");
			String path=f.getAbsolutePath();
			
			FileInputStream inputStream=new FileInputStream(path+"\\src\\main\\resources\\dbconfig.properties");
			Properties p=new Properties();
			p.load(inputStream);
			String driverClassName=p.getProperty("driver");
			String username=p.getProperty("username");
			String password=p.getProperty("password");
			String url=p.getProperty("url");
			Class.forName(driverClassName);
             conn=DriverManager.getConnection(url, username, password);
             /*if(conn!=null)
             {
            	 System.out.println("DataBase Is Connected");
             }
             else
             {
            	 System.out.println("DataBase Connection Is Failed");
             }*/
		
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
		}
	}
	
	public static DBConfig getInstance()
	{
		if(db==null)
		{
			db=new DBConfig();
		}
		return db;
	}
	public static Connection getConn()
	{
		return conn;
	}
	public static PreparedStatement getStatement()
	{
		return stmt;
	}
	public static ResultSet getResult()
	{
		return rs;
	}
	public static CallableStatement getCallStatement()
	{
		return cstmt;
	}

}
