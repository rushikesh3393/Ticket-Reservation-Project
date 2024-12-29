package ticketreservation.service;

import java.util.*;

import ticketreservation.model.BookingModel;

public interface BookingService 
{
	public boolean isAddBookingDeatails(BookingModel bmodel);
	public float calBookingAmount(int price,int numtickets);
	public List<BookingModel> getAllBookings();
	public List<BookingModel> getBookingByUserName(String buname);
	public boolean isCancelBooking(int bkid);
	public int getEidByBookingId(int bkid);
	public int getNumTicketsByBookingId(int bkid);
	public List<BookingModel> getBookingById(int bkid);

}
