package foodandbeverageconsumer;

import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import foodandbeveragepublisher.IFoodandBeverageService;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("---Start Food and Beverage Subscriber Service---");
		//Register Consumer Service
		serviceReference = context.getServiceReference(IFoodandBeverageService.class.getName());
		IFoodandBeverageService foodandbeverage = (IFoodandBeverageService)context.getService(serviceReference);
		
		displayPackages(foodandbeverage);
	}

	private void displayPackages(IFoodandBeverageService foodandbeverage) {
		
		int option;
		String subOption = "y";
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n");
		System.out.println("---------- 🍔 Food and Beverage Management Section 🍔 ----------\n");
		System.out.println("[1]  - Add Food and Beverage Package to the Database");
		System.out.println("[2]  - Get all Food and Beverage Packages in the Database");
		System.out.println("[3]  - Get Food and Beverage Package By the Id ");
		System.out.println("[4]  - Delete Food and Beverage Package by the Id");
		System.out.println("[5]  - Get a custom Food and Beverage Quoation :");
		System.out.println("\n--------------------------------------------------------------");
		
		System.out.print("\n\nChoose an option :");
	
		
		option = Integer.parseInt(scan.nextLine().trim());
		
		switch(option) {
		case 1:
			foodandbeverage.addPackage();
			
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to Add Another Package ? (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					foodandbeverage.addPackage();
				}
			}
			displayPackages(foodandbeverage);
			break;
		case 2:
			foodandbeverage.getAllPackages();
			displayPackages(foodandbeverage);
			break;
		case 3:
			foodandbeverage.getById();
			displayPackages(foodandbeverage);
			break;
		case 4:
			foodandbeverage.deletePackage();
			displayPackages(foodandbeverage);
			break;
		case 5:
			foodandbeverage.calculateBill();
			displayPackages(foodandbeverage);
		
		default:
			System.out.println("Incorrect Input. Try Again...");
			displayPackages(foodandbeverage);
	}
		
		
	}
		
	public void stop(BundleContext context) throws Exception {
		System.out.println("---Stop Food and Beverage Subscriber Service!---");
		context.ungetService(serviceReference);
	}

}
