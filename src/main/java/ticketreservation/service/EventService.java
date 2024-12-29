package ticketreservation.service;
import java.util.*;
import ticketreservation.model.*;

public interface EventService 
{
	public boolean isAddNewEvent(EventModel emodel);
	List<EventModel> getAllEvent(EventModel emodel);
	public boolean isDeleteEvent(int eid);
	public boolean updateDateById(int eid,String date);
	public int getEventIdByEventName(String ename);
	public boolean isAsscociatedCatEvent(int cid,int eid);
	public LinkedHashMap<String,ArrayList<EventModel>> getAllEventByCategory();
	public int getIdByEventName(String ename);
	public int getPriceByEventId(int eid);
	public int getAvailableSeats(int eid);
	public boolean updateAvailableSeats(int eid,int numtickets);
	public boolean isAddAvailableSeats(int eid,int numtickets);
	

}
