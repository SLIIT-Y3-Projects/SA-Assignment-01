package costomerconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import foodandbeveragepublisher.IFoodandBeverageService;
import transportpublisher.ITransportService;

public class Activator implements BundleActivator {

	// This is Transport Service
	ServiceReference TransportServiceReference;
	private ITransportService transport;
	
	// This is Food Service
	ServiceReference FoodServiceReference;
	private IFoodandBeverageService food;
	
	

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		// Register Consumer Service
		TransportServiceReference = context.getServiceReference(ITransportService.class.getName());
//		@SuppressWarnings("unchecked")
		this.transport = (ITransportService) context.getService(TransportServiceReference);
		
		FoodServiceReference = context.getServiceReference(IFoodandBeverageService.class.getName());
		this.food = (IFoodandBeverageService) context.getService(FoodServiceReference);

		displayServices();
	}

	private void displayServices() {
		int option;

		Scanner scan = new Scanner(System.in);

		System.out.println("\n\n");
		System.out.println("---- 🌐 Welcome to Event Management System! ------\n");
//		System.out.println("");
		System.out.println("Please select a service from the following list:");
		System.out.println("\t[1] - Transport");
		System.out.println("\t[2] - Food and Beverage");
		System.out.println("\t[3] - Sound");
		System.out.println("\t[4] - Decoration ");
		System.out.println("\t[5] - Exit");
		System.out.print("\nEnter the number of the service you want to select: ");

		option = Integer.parseInt(scan.nextLine().trim());

		switch (option) {
		case 1:
			transportService(transport);
			break;
		case 2:
			foodService(food);
			break;
		case 3:

			break;
		case 4:

			break;
		case 5:
			break;

		default:
			System.out.println("\n--------------------------------------------------");
			System.out.println("Incorrect Input. Try Again...");
			System.out.println("--------------------------------------------------");
			displayServices();
		}
	}

	// This is Transport Service
	public void transportService(ITransportService transport) {
		int option;
		String subOption = "y";

		Scanner scan = new Scanner(System.in);

		System.out.println("\n\n");
		System.out.println("--------- 🚌 Transport Service Section -----------\n");
		System.out.println("\t[1] - Get all Transport Packages in the Database");
		System.out.println("\t[2] - Get Transport Package By the Id ");
		System.out.println("\t[3] - Book Transport Package");
		System.out.println("\t[4] - Get my Bookings");
		System.out.println("\t[5] - Exit");
		System.out.println("\n--------------------------------------------------");
		System.out.print("\nChoose an option : ");
		option = Integer.parseInt(scan.nextLine().trim());

		switch (option) {
		case 1:
			transport.getAllTransport();
			transportService(transport);
			break;
		case 2:
			transport.getTransportByID();
			transportService(transport);
			break;
		case 3:
			transport.bookTransport();
			transportService(transport);
			break;
		case 4:
			transport.getMyBookings();
			transportService(transport);
			break;
		case 5:
			displayServices();
			break;

		default:
			System.out.println("\n--------------------------------------------------");
			System.out.println("Incorrect Input. Try Again...");
			System.out.println("--------------------------------------------------");
			transportService(transport);
		}

	}
	
	// This is Food Service
	public void foodService(IFoodandBeverageService food) {
		// TODO : Implement Food Section
		System.out.println("\n\n");
		System.out.println("--------- 🍔 Food Service Section -----------\n");
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(TransportServiceReference);
	}
}
