package soundpackpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;



public class Activator implements BundleActivator {
	
	private ServiceRegistration serviceRegistration;


	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("SoundPack Publisher service started");
		SoundPackService soundPackService = new SoundPackServiceImpl();
		serviceRegistration = bundleContext.registerService(SoundPackService.class.getName(), soundPackService, null);
	
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Sound Pack Publisher service stopped");
		serviceRegistration.unregister();
	}

}
