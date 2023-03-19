package decorationpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

//import decorationpublisher.DecorationInfoService;
//import decorationpublisher.DecorationService;

public class Activator implements BundleActivator {

private ServiceRegistration DecorationServiceAdd;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("DecorationInfo Publisher service started");
		DecorationInfoService decoration = new DecorationService();
		DecorationServiceAdd = context.registerService(DecorationInfoService.class.getName(), decoration, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("DecorationInfo Publisher service stopped");
		DecorationServiceAdd.unregister();
	}

}
