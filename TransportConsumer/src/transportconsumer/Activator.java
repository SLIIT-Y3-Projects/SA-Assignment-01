package transportconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import transportpublisher.ITransportService;

public class Activator implements BundleActivator {

	ServiceReference TransportServiceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		// Register Consumer Service
		TransportServiceReference = context.getServiceReference(ITransportService.class.getName());
		@SuppressWarnings("unchecked")
		ITransportService transport = (ITransportService) context.getService(TransportServiceReference);

		displayTransport(transport);
	}

	private void displayTransport(ITransportService transport) {

		int option;
		String subOption = "y";

		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n\n");
		System.out.println("-------- 🚗 Transport Management Section ---------\n");
		System.out.println("\t[1] - Add Transport Package to the Database");
		System.out.println("\t[2] - Get all Transport Packages in the Database");
		System.out.println("\t[3] - Get Transport Package By the Id ");
		System.out.println("\t[4] - Delete Transport Package by the Id");
		System.out.println("\t[5] - Generate Report");
		System.out.println("\t[6] - Exit");
		System.out.println("\n--------------------------------------------------");
		System.out.print("\nChoose an option : ");
		option = Integer.parseInt(scan.nextLine().trim());

		switch (option) {
		case 1:
			transport.addTransportPackage();

			while (subOption.equals("y")) {
				System.out.println("\n\nDo you want to Add Another Package (y/n)");
				subOption = scan.nextLine().trim();

				if (subOption.equals("y") || subOption.equals("Y")) {
					transport.addTransportPackage();
				}
			}
			displayTransport(transport);
			break;
		case 2:
			transport.getAllTransport();
			displayTransport(transport);
			break;
		case 3:
			transport.getTransportByID();
			displayTransport(transport);
			break;
		case 4:
			transport.deleteTransport();
			displayTransport(transport);
			break;
		case 5:
			transport.generateReport();
			displayTransport(transport);
			break;
		case 6:
			break;

		default:
			System.out.println("\n--------------------------------------------------");
			System.out.println("Incorrect Input. Try Again...");
			System.out.println("--------------------------------------------------");
			displayTransport(transport);
		}

	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(TransportServiceReference);
	}
}
