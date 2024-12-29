package ticketreservation.service;

import java.util.List;

import ticketreservation.model.CategoryModel;
import ticketreservation.repository.*;

public class CategoryServiceImpl implements CategoryService
{

	CategoryRepository ctrepo=new CategoryRepositoryImpl();
	
	public boolean isAddNewCategory(CategoryModel cmodel) 
	{
		return ctrepo.isAddNewCategory(cmodel);
	}

	
	public List<CategoryModel> getAllCategoriesList() 
	{
		
		return ctrepo.getAllCategoriesList();
	}


	public boolean isDeleteCategory(int cid) {
	
		return ctrepo.isDeleteCategory(cid);
	}


	public int getCategoryIdByName(String name) {
	
		return ctrepo.getCategoryIdByName(name);
	}

}
