package ticketreservation.model;
import lombok.*;

/*@Data
@NoArgsConstructor
@AllArgsConstructor*/
public class UserModel {
	private int uid;
	private String uname;
	private String uemail;
	private String upassword;
	private String role;
	private String key;

	
	  public UserModel(int uid, String uname, String uemail, String upassword, String role) {
		
		this.uid = uid;
		this.uname = uname;
		this.uemail = uemail;
		this.upassword = upassword;
		this.role = role;
	}

	public UserModel() {
		
	}
	
    public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
