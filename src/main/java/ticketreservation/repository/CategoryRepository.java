package ticketreservation.repository;

import ticketreservation.model.CategoryModel;
import java.util.*;

public interface CategoryRepository 
{
	public boolean isAddNewCategory(CategoryModel cmodel);
	public List<CategoryModel> getAllCategoriesList();
	public boolean isDeleteCategory(int cid);
	public int getCategoryIdByName(String name);
	

}
