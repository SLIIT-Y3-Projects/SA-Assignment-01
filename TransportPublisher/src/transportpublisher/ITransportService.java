package transportpublisher;

public interface ITransportService {

	void getAllTransport();

	void getTransportByID();

	void deleteTransport();

	void addTransportPackage();
	
	void generateReport();
	
	void bookTransport();
	
	void getMyBookings();
}
