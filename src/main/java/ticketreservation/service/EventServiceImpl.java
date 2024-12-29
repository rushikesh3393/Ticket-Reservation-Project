package ticketreservation.service;
import java.util.*;
import ticketreservation.model.*;
import ticketreservation.repository.*;

public class EventServiceImpl implements EventService
{
	EventRepository erepo=new EventRepositoryImpl();

	public boolean isAddNewEvent(EventModel emodel) 
	{
		return erepo.isAddNewEvent(emodel);
	}

	public List<EventModel> getAllEvent(EventModel emodel) {
		
		return erepo.getAllEvent(emodel);
	}

	public boolean isDeleteEvent(int eid) {
		
		return erepo.isDeleteEvent(eid);
	}

	public boolean updateDateById(int eid, String date) {
	
		return erepo.updateDateById(eid, date);
	}

	public int getEventIdByEventName(String ename) 
	{
		
		return erepo.getEventIdByEventName(ename);
	}

	public boolean isAsscociatedCatEvent(int cid, int eid) {
	
		return erepo.isAsscociatedCatEvent(cid, eid);
	}

	public LinkedHashMap<String, ArrayList<EventModel>> getAllEventByCategory() {
		
		return erepo.getAllEventByCategory();
	}

	public int getIdByEventName(String ename) {
		
		return erepo.getIdByEventName(ename);
	}

	public int getPriceByEventId(int eid) {
		
		return erepo.getPriceByEventId(eid);
	}

	public int getAvailableSeats(int eid) {
		
		return erepo.getAvailableSeats(eid);
	}

	public boolean updateAvailableSeats(int eid,int numtickets) {
	
		return erepo.updateAvailableSeats(eid,numtickets);
	}

	
	public boolean isAddAvailableSeats(int eid, int numtickets) 
	{
		return erepo.isAddAvailableSeats(eid, numtickets);
		
	}


	

	

}
