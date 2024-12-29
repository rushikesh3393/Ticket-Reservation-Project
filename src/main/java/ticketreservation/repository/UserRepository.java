package ticketreservation.repository;

import java.util.List;

import ticketreservation.model.UserModel;

public interface UserRepository 
{
	public boolean isAddNewUser(UserModel model);
	public boolean isAddNewAdmin(UserModel model);
	public boolean isExistingUser(String euname,String eupassword);
	public boolean isExistingAdmin(String euname,String eupassword);
	public boolean isExistingKey(String Akey);
	public List<UserModel> getAllCustomer();
	public boolean isDeleteCustomer(int uid);
	 public int getUidByUserName(String uname);

}
