package transportpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Transport Publisher service started ✅");
		ITransportService transport = new TransportService();
		serviceRegistration = context.registerService(ITransportService.class.getName(), transport, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Transport Publisher service stopped 🗑️");
		serviceRegistration.unregister();
	}

}
