package decorationpublisher;

public class DecorationModel {
	
	private int id;
	private String dpackageName;
	private String eventTime;
	private String eventType;
	private int areaSize;
	private int price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDpackageName() {
		return dpackageName;
	}
	public void setDpackageName(String dpackageName) {
		this.dpackageName = dpackageName;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public int getAreaSize() {
		return areaSize;
	}
	public void setAreaSize(int areaSize) {
		this.areaSize = areaSize;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
