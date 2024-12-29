package ticketreservation.service;

import java.util.List;

import ticketreservation.model.BookingModel;
import ticketreservation.repository.*;

public class BookingServiceImpl implements BookingService
{
	BookingRepository brepo=new BookingRepositoryImpl();
	
	public boolean isAddBookingDeatails(BookingModel bmodel)
	{
		return brepo.isAddBookingDeatails(bmodel);
	}


	public float calBookingAmount(int price,int numtickets) 
	{
		float ticketprice=price*numtickets;
		System.out.println("There is 9% CGST And 9% SGST");
		
		ticketprice=(float) (ticketprice+ticketprice*0.18);

		return ticketprice;
	}


	public List<BookingModel> getAllBookings() 
	{
		return brepo.getAllBookings();
	}

	public List<BookingModel> getBookingByUserName(String buname) 
	{
		return brepo.getBookingByUserName(buname);
	}
	
	public boolean isCancelBooking(int bkid) 
	{
		return brepo.isCancelBooking(bkid);
	}

	public int getEidByBookingId(int bkid) {
		
		return brepo.getEidByBookingId( bkid);
	}

	public int getNumTicketsByBookingId(int bkid) {
	
		return brepo.getNumTicketsByBookingId( bkid);
	}


	public List<BookingModel> getBookingById(int bkid) {
		
		return brepo.getBookingById(bkid);
	}


	



	

}
