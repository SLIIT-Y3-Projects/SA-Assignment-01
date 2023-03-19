package soundpackconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import soundpackpublisher.SoundPackService;

public class Activator implements BundleActivator {
	
	ServiceReference SoundPackServiceReference;


	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Start Subscriber Service");
		//Register Consumer Service
		SoundPackServiceReference = bundleContext.getServiceReference(SoundPackService.class.getName());
		@SuppressWarnings("unchecked")
		SoundPackService soundPack = (SoundPackService)bundleContext.getService(SoundPackServiceReference);	
		
		displayApplication(soundPack);
		
	}
	
	
private void displayApplication(SoundPackService soundPack) {
		
		
		String option;
		int option2;
		String subOption = "y";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.println("***************Sound Pack Section***************\n");
		System.out.println("1  - Add New Sound Pack to the Database");
		System.out.println("2  - Get all Sound Packs in the Database");
		System.out.println("3  - Get Sound Pack By the Id ");
		System.out.println("4  - Delete Sound Pack by the Id");
		System.out.println("5  - Calculate Total fee For The SoundPackage.");
		System.out.println("6  - Exit From The Application.");
		
		System.out.println("\n**************************************************");
		System.out.println("\n--------------------------------------------------");
		
		System.out.print("\n\nChoose an option : \n\n");
		
		option=scan.nextLine().trim();		
		option2=Integer.parseInt(option);
		
		switch(option2) {
			case 1:
				soundPack.addPackage();
				
				while(subOption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Sound Pack (y/n)");
					subOption = scan.nextLine().trim();
		
					if(subOption.equals("y")||subOption.equals("Y")) {
						soundPack.addPackage();
					}else {
						displayApplication(soundPack);

					}
				}
				break;
			case 2:
				soundPack.getAllPackages();
				displayApplication(soundPack);
				break;
			case 3:
				soundPack.getPackageByID();
				displayApplication(soundPack);
				break;
			case 4:
				soundPack.deletePackage();
				displayApplication(soundPack);
				break;
			case 5:
				soundPack.calTotalSoundsFee();
				displayApplication(soundPack);
				break;
			case 6:
				break;
			default:
				System.out.println("Incorrect Input. Try Again...");
				displayApplication(soundPack);
		}
		
		
	
	}

	
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Sound Package Service Stopped..Bye!");
		bundleContext.ungetService(SoundPackServiceReference);
		
	}

}
