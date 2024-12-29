package ticketreservation.repository;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ticketreservation.clientapp.TicketReservationApp;
import ticketreservation.model.*;

public class BookingRepositoryImpl extends DBUSER implements BookingRepository
{
	    List<BookingModel> bklist;
	    List<BookingModel> ubklist;
	    List<BookingModel> receiptlist;
		static Logger logger = Logger.getLogger(BookingRepositoryImpl.class);
		public boolean isAddBookingDeatails(BookingModel bmodel) {
			try {
				stmt = conn.prepareStatement("insert into bookingmasterr(uid,buname,eid,bename,numtickets,status,finalprice)values(?,?,?,?,?,?,?)");
				stmt.setInt(1, bmodel.getUid());
				stmt.setString(2,bmodel.getBuname());
				stmt.setInt(3, bmodel.getEid());
				stmt.setString(4, bmodel.getBename());
				stmt.setInt(5, bmodel.getNumtickets());
				stmt.setString(6, bmodel.getStatus());
				stmt.setFloat(7, bmodel.getFinalprice());
				
				int val = stmt.executeUpdate();
				return val>0?true:false;
				
			} catch (Exception ex) {
				System.out.println("Error is:"+ex);
				logger.fatal(" BookingRepositoryImpl::isAddBookingDeatails Error is in Adding Booking Details "+ex.getMessage());
				return false;
			}
		}

		public List<BookingModel> getAllBookings() 
		{
			bklist=new ArrayList<BookingModel> ();
			try 
			{
				stmt=conn.prepareStatement("select *from bookingmasterr");
				rs=stmt.executeQuery();
				
				while(rs.next())
				{
				
					bklist.add(new BookingModel(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getFloat(9)));
					
				}
				return bklist.size() > 0 ? bklist :null;
				
			}
			catch(Exception ex)
			{
				System.out.println("Error is:"+ex);
				logger.fatal(" BookingRepositoryImpl::getAllBookings Error is in Getting Booking Details "+ex.getMessage());
				return null;
			}
			
		}


	
		public List<BookingModel> getBookingByUserName(String buname) {
			ubklist=new ArrayList<BookingModel> ();
			try 
			{
				stmt=conn.prepareStatement("select *from bookingmasterr where buname=? && status='Confirm'");
				stmt.setString(1, buname);
				rs=stmt.executeQuery();
				
				while(rs.next())
				{
				
					ubklist.add(new BookingModel(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getFloat(9)));
					
				}
				return ubklist.size() > 0 ? ubklist :null;
				
			}
			catch(Exception ex)
			{
				System.out.println("Error is:"+ex);
				logger.fatal(" BookingRepositoryImpl::getBookingByUserName Error is in Getting Booking Details by UserName "+ex.getMessage());
				return null;
			}
			
		}


		public boolean isCancelBooking(int bkid)
		{
			try 
			{
				stmt=conn.prepareStatement("delete from bookingmasterr where bkid=?");
				stmt.setInt(1, bkid);
				int val=stmt.executeUpdate();
				if(val>0)
				{
					return true;
				}
				else
				{
					return false;
				}
				
				
			}
			catch(Exception ex)
			{
				System.out.println("Error is:"+ex);
				logger.fatal(" BookingRepositoryImpl::isCancelBooking  Error is in cancel the Booking  "+ex.getMessage());
				return false;
				
			}
		}

		public int getEidByBookingId(int bkid) 
		{
			try {
				
				stmt=conn.prepareStatement("select eid from bookingmasterr where bkid =?");
				stmt.setInt(1, bkid);
				
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
				logger.fatal("Error is in BookingRepositoryImpl::getEidByBookingId "+ex.getMessage());
				return -1;
			}
			
			
		}

	
		public int getNumTicketsByBookingId(int bkid)
		{
		try {
			
			stmt=conn.prepareStatement("select numtickets from bookingmasterr where bkid =?");
			stmt.setInt(1, bkid);
			
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
			logger.fatal("Error is in BookingRepositoryImpl::getNumTicketsByBookingId "+ex.getMessage());
			return -1;
		}
		
		}

	
		public List<BookingModel> getBookingById(int bkid) {
		receiptlist=new ArrayList<BookingModel> ();
		try 
		{
			stmt=conn.prepareStatement("select *from bookingmasterr where bkid=?");
			stmt.setInt(1, bkid);
			rs=stmt.executeQuery();
			
			while(rs.next())
			{
			
				receiptlist.add(new BookingModel(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getFloat(9)));
				
			}
			return receiptlist.size() > 0 ? receiptlist :null;
			
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			logger.fatal("Error is in BookingRepositoryImpl::getBookingById "+ex.getMessage());
			return null;
		}
		}

		
	}


