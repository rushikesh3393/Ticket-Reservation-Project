package ticketreservation.service;

import java.util.List;

import ticketreservation.model.UserModel;
import ticketreservation.repository.UserRepository;
import ticketreservation.repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService
{
	UserRepository usrepo=new UserRepositoryImpl();

	public boolean isAddNewUser(UserModel model) 
	{
		return usrepo.isAddNewUser(model);
	}

	public boolean isExistngUser(String euname, String eupassword) 
	{
		
		return usrepo.isExistingUser(euname,eupassword);
	}

	public boolean isExistingAdmin(String euname, String eupassword) {
		
		return usrepo.isExistingAdmin(euname,eupassword);
	}

	public boolean isAddNewAdmin(UserModel model) {
		
		return usrepo.isAddNewAdmin(model);
	}

	
	public boolean isExistingKey(String Akey) {
		
		return usrepo.isExistingKey(Akey);
	}

	
	public List<UserModel> getAllCustomer() 
	{
		
		return usrepo.getAllCustomer();
	}

	
	public boolean isDeleteCustomer(int uid) {
	
		return usrepo.isDeleteCustomer(uid);
	}

	public int getUidByUserName(String uname) {
	
		return usrepo.getUidByUserName(uname);
	}
	

}
