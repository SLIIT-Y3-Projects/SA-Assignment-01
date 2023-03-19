package foodandbeveragepublisher;

public class FoodandBeverageModel {
	
	private int id;
	private String packageId;
	private String packageName;
	private int price;

		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int string) {
		this.price = string;
	}
		

}