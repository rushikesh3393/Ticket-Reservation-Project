package ticketreservation.repository;

import java.util.List;

import ticketreservation.model.BookingModel;

public interface BookingRepository 
{
	public boolean isAddBookingDeatails(BookingModel bmodel);
	public List<BookingModel> getAllBookings();
	public List<BookingModel> getBookingByUserName(String buname);
	public boolean isCancelBooking(int bkid);
	public int getEidByBookingId(int bkid);
	public int getNumTicketsByBookingId(int bkid);
	public List <BookingModel> getBookingById(int bkid);
	

}
