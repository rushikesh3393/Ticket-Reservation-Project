package ticketreservation.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
	private int categoryid;
	private String ecategory;

	/*public CategoryModel() {

	}

	public CategoryModel(int categoryid, String ecategory) {
		this.categoryid = categoryid;
		this.ecategory = ecategory;

	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getEcategory() {
		return ecategory;
	}

	public void setEcategory(String ecategory) {
		this.ecategory = ecategory;
	}*/

}
