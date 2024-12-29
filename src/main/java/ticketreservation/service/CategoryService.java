package ticketreservation.service;
import java.util.List;

import ticketreservation.model.CategoryModel;

public interface CategoryService 
{
	public boolean isAddNewCategory(CategoryModel cmodel);
	public List<CategoryModel> getAllCategoriesList();
	public boolean isDeleteCategory(int cid);
	public int getCategoryIdByName(String name);

}
