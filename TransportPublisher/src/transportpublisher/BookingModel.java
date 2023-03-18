
package transportpublisher;

public class BookingModel {

	private int id;
	private String customerName;
	private String customerNIC;
	private int packageId;
	private int numDays;
	private double totalCost;

	public BookingModel() {
	};

	/**
	 * @param id
	 * @param customerName
	 * @param customerNIC
	 * @param packageId
	 * @param numDays
	 * @param totalCost
	 */
	public BookingModel(String customerName, String customerNIC, int packageId, int numDays, double totalCost) {
		super();
		this.customerName = customerName;
		this.customerNIC = customerNIC;
		this.packageId = packageId;
		this.numDays = numDays;
		this.totalCost = totalCost;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerNIC
	 */
	public String getCustomerNIC() {
		return customerNIC;
	}

	/**
	 * @param customerNIC the customerNIC to set
	 */
	public void setCustomerNIC(String customerNIC) {
		this.customerNIC = customerNIC;
	}

	/**
	 * @return the packageId
	 */
	public int getPackageId() {
		return packageId;
	}

	/**
	 * @param packageId the packageId to set
	 */
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	/**
	 * @return the numDays
	 */
	public int getNumDays() {
		return numDays;
	}

	/**
	 * @param numDays the numDays to set
	 */
	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}

	/**
	 * @return the totalCost
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

}
