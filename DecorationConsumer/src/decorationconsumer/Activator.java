package decorationconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import decorationpublisher.DecorationInfoService;

public class Activator implements BundleActivator {

    private ServiceReference<DecorationInfoService> serviceReference;
    private DecorationInfoService decoration;

    public void start(BundleContext context) throws Exception {
        System.out.println("Start Subscriber Service");
        // Register Consumer Service
        serviceReference = context.getServiceReference(DecorationInfoService.class);
        decoration = context.getService(serviceReference);
        displayPackages();
    }

   
    private void displayPackages() {
        int option;
        String subOption = "y";

        Scanner scan = new Scanner(System.in);
        System.out.println("\n\n\n");
        System.out.println("************* Decoration Package Management *************\n");
        System.out.println("1  - Add Decoration Package.");
        System.out.println("2  - View all Decoration Packages.");
        System.out.println("3  - Select one Decoration Package By Id ");
        System.out.println("4  - Delete Decoration Package by Id");
        System.out.println("5  - Update Decoration Package by Id");
        System.out.println("6  - Calculate your selected package bill");
        System.out.println("7  - Press to stop the service");
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        

        System.out.print("\n\nChoose an option : \n\n");

        option = Integer.parseInt(scan.nextLine().trim());

        switch (option) {
            case 1:
                String result = decoration.addDecorationPackage();
                System.out.println(result);
                while (subOption.equalsIgnoreCase("y")) {
                    System.out.println("\n\nDo you want to Add Another Vehicle (y/n)");
                    subOption = scan.nextLine().trim();

                    if (subOption.equalsIgnoreCase("y")) {
                        result = decoration.addDecorationPackage();
                        System.out.println(result);
                    }
                }
                displayPackages();
                break;
            case 2:
                decoration.getAllDecorationPackages();
                displayPackages();
                break;
            case 3:
                decoration.getDecorationPackageById();
                displayPackages();
                break;
            case 4:
                decoration.deleteDecorationPackage();
                displayPackages();
                break;
            case 5:
                decoration.updateDecorationPackage();
                displayPackages();
                break;
            case 6:
                decoration.calculateDecorationBill();
                displayPackages();
                break;
            case 7:
            	
                break;

            default:
                System.out.println("Incorrect Input. Try Again...");
                displayPackages();
        }

        //scan.close();
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Have Nice Day!");
        context.ungetService(serviceReference);
    }
}
