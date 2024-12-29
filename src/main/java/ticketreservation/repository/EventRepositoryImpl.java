package ticketreservation.repository;

import ticketreservation.model.*;
import java.sql.*;
import java.util.*;
import org.apache.log4j.Logger;




	public class EventRepositoryImpl extends DBUSER implements EventRepository
	{
		static Logger logger=Logger.getLogger(EventRepositoryImpl.class);
    	public List<EventModel> elist;
	    public List eventCategoryList;
	    public LinkedHashMap<String,ArrayList<EventModel>> ecatlist;

		
		public boolean isAddNewEvent(EventModel model) {
			try {
				stmt = conn.prepareStatement("insert into eventmaster values('0',?,?,?,?,?,?,?)");
				stmt.setString(1, model.getEname());
				stmt.setString(2, model.getEdate());
				stmt.setInt(3,model.getTseat());
				stmt.setInt(4, model.getAseat());
				stmt.setInt(5, model.getPrice());
				stmt.setString(6, model.getTime());
				stmt.setString(7, model.getDesc());
				
				int val = stmt.executeUpdate();
				return val>0?true:false;
						
			} 
			catch (Exception ex) 
			{
				System.out.println("Error is:"+ex);
				logger.fatal(("Error is in ::EventRepositoryImpl->isAddNewEvent"+ex.getMessage()));
				return false;
			}
		}

		
		public List<EventModel> getAllEvent(EventModel model) {
			elist = new ArrayList<EventModel>();
			try {
				
				stmt=conn.prepareStatement("select *from eventmaster where edate > TIMESTAMP(now())");
				rs=stmt.executeQuery();
				while(rs.next()) 
				{
					elist.add(new EventModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8)));
				}
				return elist.size()>0?elist:null;
			} 
			catch (Exception ex) 
			{
				System.out.println("Error is"+ex);
				logger.fatal(("Error is in ::EventRepositoryImpl->getAllEvent"+ex.getMessage()));
				return null;
			}
		}

	
		public boolean isDeleteEvent(int eid) {
			try {
				stmt = conn.prepareStatement("delete from eventmaster where eid = ?");
				stmt.setInt(1, eid);
				int val = stmt.executeUpdate();
				return val>0?true:false;
			} 
			catch (Exception ex) 
			{
				System.out.println("Error is"+ex);
				logger.fatal(("Error is in ::EventRepositoryImpl->isDeleteEvent"+ex.getMessage()));
				return false;
			}
		}

		public boolean updateDateById(int eid,String date) 
		{
			try {
				stmt = conn.prepareStatement("update eventmaster set edate=? where eid=?");
				stmt.setString(1, date);
				stmt.setInt(2, eid);
				int val = stmt.executeUpdate();
				return val>0?true:false;
			} 
			catch (Exception ex) 
			{
				System.out.println("Error is"+ex);
				logger.fatal(("Error is in ::EventRepositoryImpl->updateDateById"+ex.getMessage()));
				return false;
			}
		}

		public int getEventIdByEventName(String ename) 
		{
			try 
			{
				stmt=conn.prepareStatement("select eid from eventmaster where ename=?");
				stmt.setString(1, ename);
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
				System.out.println("Error is:"+ex);
				logger.fatal(("Error is in ::EventRepositoryImpl->getEventIdByEventName"+ex.getMessage()));
				return -1;
			}
		
			
		}

		public boolean isAsscociatedCatEvent(int cid, int eid) 
		{
			try {
				cstmt =conn.prepareCall("{ call saveCatevent(?,?)}");
				cstmt.setInt(1,cid);
				cstmt.setInt(2,eid);
				
				boolean b=cstmt.execute();
				return !b;
				
			}
			catch(Exception ex)
			{
				System.out.println("Erroe is:"+ex);
				logger.fatal(("Error is in ::EventRepositoryImpl->isAsscociatedCatEvent"+ex.getMessage()));
				return false;
			}
		}


		public LinkedHashMap<String, ArrayList<EventModel>> getAllEventByCategory() 
		{
			
				ecatlist = new LinkedHashMap<String, ArrayList<EventModel>>();
				try {
					stmt=conn.prepareStatement("select c.ecategory,e.ename,e.edate,e.totalseats,e.availableseats,e.price,e.etime,e.description from category c inner join categoryeventjoin cej on c.categoryid = cej.categoryid inner join eventmaster e on e.eid=cej.eid having e.edate > TIMESTAMP(now())");
					rs=stmt.executeQuery();
					
					while(rs.next()) {
						String category = rs.getString(1);
   						EventModel event = new EventModel(
				                0, // Assuming ID is not needed here
				                rs.getString(2), // ename
				                rs.getString(3), // edate
				                rs.getInt(4), // totalseats
				                rs.getInt(5), // availabeseates
				                rs.getInt(6), // price
				                rs.getString(7), // etime
				                rs.getString(8)  // description
				            );
						
			            ArrayList<EventModel> eventList = ecatlist.getOrDefault(category, new ArrayList<>());
			            eventList.add(event); 
			            ecatlist.put(category, eventList); 

					}
					return ecatlist;
				} catch (Exception ex) {
					System.out.println("Error is:"+ex);
					logger.fatal(("Error is in ::EventRepositoryImpl->getAllEventByCategory"+ex.getMessage()));
					return null;
				}
			}

		public int getIdByEventName(String ename) 
		{
			try {
				stmt= conn.prepareStatement("select eid from eventmaster where ename =?");
				stmt.setString(1, ename);
				rs = stmt.executeQuery();
				if(rs.next()) 
				{
					return rs.getInt(1);
				}
				return -1;
			} 
			catch (Exception ex) 
			{
				System.out.println("Error is:"+ex);
				logger.fatal(("Error is in ::EventRepositoryImpl->getIdByEventName"+ex.getMessage()));
				return -1;
			}

		}

	
		public int getPriceByEventId(int eid) {
			try {
				stmt = conn.prepareStatement("select price from eventmaster where eid=?");
				stmt.setInt(1, eid);
				rs=stmt.executeQuery();
				if(rs.next()) {
					return rs.getInt(1);
				}
				else 
				{
					return -1;
				}
			} 
			catch (Exception ex) 
			{
			System.out.println("Error is:"+ex);
			logger.fatal(("Error is in ::EventRepositoryImpl->getPriceByEventId"+ex.getMessage()));
			 return -1;
			}

		}
		
		public int getAvailableSeats(int eid) 
		{
			try {
				stmt=conn.prepareStatement("select availableseats from eventmaster where eid=?");
				stmt.setInt(1, eid);
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
				System.out.println("Error is:"+ex);
				logger.fatal(("Error is in ::EventRepositoryImpl->getAvailableSeats"+ex.getMessage()));
				return -1;
			}
		}

		public boolean updateAvailableSeats(int eid, int numtickets) 
		{
		    try 
		    {
		        stmt=conn.prepareStatement("update eventmaster set availableseats=availableseats-? where eid=?");
		        stmt.setInt(1, numtickets);
		        stmt.setInt(2, eid);
		        
		        int value=stmt.executeUpdate();
		        if(value > 0)
		        {
		            System.out.println("Available Seats Updated Successfully...!");
		            return true;
		        }
		        else
		        {
		            System.out.println("Available Seats NOT Updated Successfully...!");
		            return false;
		        }
		        
		    }
		    catch(Exception ex)
		    {
		    	System.out.println("Error is:"+ex);
		     
		        logger.fatal(("Error is in ::EventRepositoryImpl->updateAvailableSeats"+ex.getMessage()));
		        return false;
		    }
		}

		public boolean isAddAvailableSeats(int eid, int numtickets) 
		{
			try 
		    {
		        stmt=conn.prepareStatement("update eventmaster set availableseats=availableseats+? where eid=?");
		        stmt.setInt(1, numtickets);
		        stmt.setInt(2, eid);
		        
		        int value=stmt.executeUpdate();
		        if(value > 0)
		        {
		            System.out.println("Available Seats Updated Successfully...!");
		            return true;
		        }
		        else
		        {
		            System.out.println("Available Seats NOT Updated Successfully...!");
		            return false;
		        }
		        
		    }
		    catch(Exception ex)
		    {
		    	System.out.println("Error is:"+ex);
		    	logger.fatal(("Error is in ::EventRepositoryImpl->isAddAvailableSeats"+ex.getMessage()));
		        return false;
		    }
			
		}


}

	


