package foodandbeveragepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Food and Beverage service started");
		IFoodandBeverageService foodandbeverage = new FoodandBeverageService();
		serviceRegistration = context.registerService(IFoodandBeverageService.class.getName(), foodandbeverage, null);
		
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Food and Beverage Publisher service stopped");
		serviceRegistration.unregister();
	}

}
