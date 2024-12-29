package ticketreservation.model;
import lombok.*;

/*@Data
@NoArgsConstructor
@AllArgsConstructor*/
public class BookingModel 
{
	
	private int bkid;
	private int uid;
	private String buname;
	private int eid;
	private String bename;
	private int numtickets;
	private String bkdate;
	private String status;
	private float finalprice;
	
	public BookingModel(int bkid, int uid, String buname, int eid, String bename, int numtickets, String bkdate,
			String status, float finalprice) 
	{
		
		this.bkid = bkid;
		this.uid = uid;
		this.buname = buname;
		this.eid = eid;
		this.bename = bename;
		this.numtickets = numtickets;
		this.bkdate = bkdate;
		this.status = status;
		this.finalprice = finalprice;
	}
   public BookingModel() {
		
	}


	public int getBkid() {
		return bkid;
	}

	public void setBkid(int bkid) {
		this.bkid = bkid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getBuname() {
		return buname;
	}

	public void setBuname(String buname) {
		this.buname = buname;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getBename() {
		return bename;
	}

	public void setBename(String bename) {
		this.bename = bename;
	}

	public int getNumtickets() {
		return numtickets;
	}

	public void setNumtickets(int numtickets) {
		this.numtickets = numtickets;
	}

	public String getBkdate() {
		return bkdate;
	}

	public void setBkdate(String bkdate) {
		this.bkdate = bkdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getFinalprice() {
		return finalprice;
	}

	public void setFinalprice(float finalprice) {
		this.finalprice = finalprice;
	}

	

}
