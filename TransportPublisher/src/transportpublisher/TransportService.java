package transportpublisher;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.ArrayList;
//import java.util.Scanner;

import eventdatabase.IEventDb;
import eventdatabase.EventDb;

public class TransportService implements ITransportService {

	private Connection connection = null;
	private Statement statement = null;
	private IEventDb vehicleRentalDatabase;
	private ResultSet resultSet;

	Scanner scan = new Scanner(System.in);

	public TransportService() {
		super();
		vehicleRentalDatabase = (IEventDb) new EventDb();
		connection = vehicleRentalDatabase.connection();
	}

	@Override
	public void addTransportPackage() {
		System.out.println("");
		System.out.println("╔══════════════════════════════════════════════════╗");
		System.out.println("║            Add New Transport Package             ║");
		System.out.println("╚══════════════════════════════════════════════════╝");
		System.out.println("");

		TransportModel transportModel = new TransportModel();

//		----------------------------------------------------------------------
		System.out.println("Enter Package Name : ");
		transportModel.setPackageName(scan.nextLine().trim());
//		----------------------------------------------------------------------
		System.out.println("Enter Vehicle Registration Number : ");
		transportModel.setVehicleRegNo(scan.nextLine().trim());
//		----------------------------------------------------------------------
		String[] vehicleTypes = { "Sedan", "Limousine", "Party Bus", "Van", "Luxury SUV" };

		System.out.println("\nSelect Vehicle Type:");

		for (int i = 0; i < vehicleTypes.length; i++) {
			System.out.println("\t" + (i + 1) + ". " + vehicleTypes[i]);
		}

		int vehicleTypeChoice = Integer.parseInt(scan.nextLine());

		if (vehicleTypeChoice > 0 && vehicleTypeChoice <= vehicleTypes.length) {
			transportModel.setVehicleType(vehicleTypes[vehicleTypeChoice - 1]);
		} else {
			System.out.println("Invalid choice");
		}
//		----------------------------------------------------------------------
		System.out.println("Enter Capacity : ");
		transportModel.setCapacity(Integer.parseInt(scan.nextLine()));
//		----------------------------------------------------------------------		
		String[] amenities;
		String[] allAmenities = { "Air conditioning", "GPS", "Heated Seats", "Sunroof", "Wi-Fi" };

		System.out.println("\nSelect Amenities:");

		for (int i = 0; i < allAmenities.length; i++) {
			System.out.println("\t" + (i + 1) + ". " + allAmenities[i]);
		}

		String[] selectedAmenities = new String[allAmenities.length];
		int numSelectedAmenities = 0;

		while (true) {
			System.out.println("\nSelect amenities (type 'done' to finish): ");
			String input = scan.nextLine().trim();

			if (input.equalsIgnoreCase("done")) {
				break;
			}

			int amenityChoice = Integer.parseInt(input);

			if (amenityChoice > 0 && amenityChoice <= allAmenities.length) {
				String amenity = allAmenities[amenityChoice - 1];
				boolean alreadySelected = false;

				for (int i = 0; i < numSelectedAmenities; i++) {
					if (selectedAmenities[i].equals(amenity)) {
						alreadySelected = true;
						break;
					}
				}

				if (!alreadySelected) {
					selectedAmenities[numSelectedAmenities] = amenity;
					numSelectedAmenities++;
				} else {
					System.out.println("Amenity already selected");
				}
			} else {
				System.out.println("Invalid choice");
			}
		}

		amenities = Arrays.copyOf(selectedAmenities, numSelectedAmenities);

		transportModel.setAmenities(amenities);
//		----------------------------------------------------------------------
		System.out.println("Enter Cost (per day): ");
		transportModel.setCost(Double.parseDouble(scan.nextLine()));
//		----------------------------------------------------------------------
		String[] safetySecurity;
		String[] safetySecurityFeatures = { "Airbags", "Anti-lock brakes (ABS)", "Driver identification",
				"Alarm system", "GPS tracking" };

		System.out.println("\nSelect safety and security features:");

		for (int i = 0; i < safetySecurityFeatures.length; i++) {
			System.out.println("\t" + (i + 1) + ". " + safetySecurityFeatures[i]);
		}

		String[] selectedFeatures = new String[safetySecurityFeatures.length];
		int numSelectedFeatures = 0;

		while (true) {
			System.out.println("\nSelect features (type 'done' to finish): ");
			String input = scan.nextLine().trim();

			if (input.equalsIgnoreCase("done")) {
				break;
			}

			int choice = Integer.parseInt(input);

			if (choice > 0 && choice <= safetySecurityFeatures.length) {
				String feature = safetySecurityFeatures[choice - 1];
				boolean alreadySelected = false;

				for (int i = 0; i < numSelectedFeatures; i++) {
					if (selectedFeatures[i].equals(feature)) {
						alreadySelected = true;
						break;
					}
				}

				if (!alreadySelected) {
					selectedFeatures[numSelectedFeatures] = feature;
					numSelectedFeatures++;
				} else {
					System.out.println("Feature already selected");
				}
			} else {
				System.out.println("Invalid choice");
			}
		}

		safetySecurity = Arrays.copyOf(selectedFeatures, numSelectedFeatures);

		transportModel.setSafetySecurity(safetySecurity);
//		----------------------------------------------------------------------

		// SQL Query
		String insertTransport = "INSERT INTO transportdetails(packageName,vehicleRegNo,vehicleType, capacity, amenities, cost, safetySecurity) "
				+ "VALUES('" + transportModel.getPackageName() + "', '" + transportModel.getVehicleRegNo() + "','"
				+ transportModel.getVehicleType() + "','" + transportModel.getCapacity() + "','"
				+ Arrays.toString(transportModel.getAmenities()) + "','" + transportModel.getCost() + "','"
				+ Arrays.toString(transportModel.getSafetySecurity()) + "')";

		// Insert Data into Table
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertTransport);
			System.out.println("\n--------------------------------------------------");
			System.out.println("Transport Package Inserted");
			System.out.println("--------------------------------------------------");
		} catch (SQLException exc) {
			System.out.println("Error with Interted Transport Package");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public void getAllTransport() {
		ArrayList<TransportModel> transportList = new ArrayList<TransportModel>();
		try {
			statement = connection.createStatement();
			String SelectAll = "SELECT * FROM transportdetails";
			resultSet = statement.executeQuery(SelectAll);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			while (resultSet.next()) {
				transportList.add(createObject(resultSet));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("");
		System.out.println("╔══════════════════════════════════════════════════╗");
		System.out.println("║              All Transport Packages              ║");
		System.out.println("╚══════════════════════════════════════════════════╝");
		for (TransportModel transport : transportList) {
			printTransportPackage(transport);
		}
		System.out.println("\n--------------------------------------------------");
	}

	@Override
	public void getTransportByID() {
		int transportId;

		System.out.print("Enter Package ID : ");
		transportId = Integer.parseInt(scan.nextLine().trim());

		String getById = "SELECT * FROM transportdetails WHERE id = '" + transportId + "'";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getById);

			System.out.println("");
			System.out.println("╔══════════════════════════════════════════════════╗");
			System.out.printf("║        Details for Transport Package : %d         ║\n", transportId);
			System.out.println("╚══════════════════════════════════════════════════╝");
			while (resultSet.next()) {
				printTransportPackage(createObject(resultSet));
				System.out.println("\n--------------------------------------------------");
			}
		} catch (SQLException exc) {
			System.out.println("Error in get Transport by Id");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public void deleteTransport() {
		int transportId;

		System.out.print("Enter Package ID To Delete : ");
		transportId = Integer.parseInt(scan.nextLine().trim());
		String deleteTransport = "\ndelete from transportdetails where id='" + transportId + "' ";

		try {

			statement = connection.createStatement();
			statement.executeUpdate(deleteTransport);
			System.out.println("Transport Successfully deleted\n");

		} catch (SQLException exc) {
			System.out.println("Error with delete Transport");
			System.out.println(exc.getMessage());
		}

	}

	private void printTransportPackage(TransportModel transport) {
		System.out.println("\n--------------------------------------------------");
		System.out.printf("%20s: %-25s\n", "ID", transport.getId());
		System.out.printf("%20s: %-25s\n", "Package Name", transport.getPackageName());
		System.out.printf("%20s: %-25s\n", "Vehicle Reg. No.", transport.getVehicleRegNo());
		System.out.printf("%20s: %-25s\n", "Vehicle Type", transport.getVehicleType());
		System.out.printf("%20s: %-25s\n", "Capacity", transport.getCapacity());

		String[] amenities = transport.getAmenities();
		System.out.printf("%20s: ", "Amenities");
		for (String amenity : amenities) {
			System.out.printf("• %s\n", amenity);
			System.out.print("" + String.format("%-20s", "") + "  ");
		}

		String[] safetySecurity = transport.getSafetySecurity();
		System.out.printf("\n%20s: ", "Safety & Security");
		for (String feature : safetySecurity) {
			System.out.printf("• %s\n", feature);
			System.out.print("" + String.format("%-20s", "") + "  ");
		}
	}

	private TransportModel createObject(ResultSet resultSet) {
		TransportModel transport = new TransportModel();

		try {
			transport.setId(resultSet.getInt("id"));
			transport.setPackageName(resultSet.getString("packageName"));
			transport.setVehicleRegNo(resultSet.getString("vehicleRegNo"));
			transport.setVehicleType(resultSet.getString("vehicleType"));
			transport.setCapacity(resultSet.getInt("capacity"));

			String amenitiesString = resultSet.getString("amenities");
			String[] amenitiesArray = amenitiesString.substring(1, amenitiesString.length() - 1).split(", ");
			transport.setAmenities(amenitiesArray);

			transport.setCost(resultSet.getDouble("cost"));

			String safetySecurityString = resultSet.getString("safetySecurity");
			String[] safetySecurityArray = safetySecurityString.substring(1, safetySecurityString.length() - 1)
					.split(", ");
			transport.setSafetySecurity(safetySecurityArray);
		} catch (SQLException exc) {
			System.out.println("Error in get Transport by Id");
			System.out.println(exc.getMessage());
		}

		return transport;
	}

	public void generateReport() {
		ArrayList<TransportModel> transportList = new ArrayList<TransportModel>();
		try {
			statement = connection.createStatement();
			String SelectAll = "SELECT * FROM transportdetails";
			resultSet = statement.executeQuery(SelectAll);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			while (resultSet.next()) {
				transportList.add(createObject(resultSet));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String username = System.getProperty("user.name");
			FileWriter fileWriter = new FileWriter("C:\\Users\\" + username + "\\Downloads\\transport_report.txt",
					true);
			PrintWriter printWriter = new PrintWriter(fileWriter);

			printWriter.println("╔══════════════════════════════════════════════════╗");
			printWriter.println("║            Transport Packages Report             ║");
			printWriter.println("╚══════════════════════════════════════════════════╝");

			for (TransportModel transport : transportList) {

				printWriter.println("\n--------------------------------------------------");
				printWriter.printf("%20s: %-25s\n", "ID", transport.getId());
				printWriter.printf("%20s: %-25s\n", "Package Name", transport.getPackageName());
				printWriter.printf("%20s: %-25s\n", "Vehicle Reg. No.", transport.getVehicleRegNo());
				printWriter.printf("%20s: %-25s\n", "Vehicle Type", transport.getVehicleType());
				printWriter.printf("%20s: %-25s\n", "Capacity", transport.getCapacity());

				String[] amenities = transport.getAmenities();
				printWriter.printf("%20s: ", "Amenities");
				for (String amenity : amenities) {
					printWriter.printf("• %s\n", amenity);
					printWriter.print("" + String.format("%-20s", "") + "  ");
				}

				String[] safetySecurity = transport.getSafetySecurity();
				printWriter.printf("\n%20s: ", "Safety & Security");
				for (String feature : safetySecurity) {
					printWriter.printf("• %s\n", feature);
					printWriter.print("" + String.format("%-20s", "") + "  ");
				}

			}
			printWriter.close();
		} catch (IOException e) {
			System.err.println("Error exporting data: " + e.getMessage());
		}

		System.out.println("\n--------------------------------------------------");
		System.out.println("Data exported to :");
		System.out.println("\tC:\\Users\\" + System.getProperty("user.name") + "\\Downloads\\transport_report.txt");
		System.out.println("--------------------------------------------------");
	}

	@Override
	public void bookTransport() {
		ArrayList<TransportModel> transportList = new ArrayList<TransportModel>();
		BookingModel booking = new BookingModel();

		try {
			statement = connection.createStatement();
			String SelectAll = "SELECT * FROM transportdetails";
			resultSet = statement.executeQuery(SelectAll);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			while (resultSet.next()) {
				transportList.add(createObject(resultSet));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Display the list of available transport packages to the customer
		System.out.println("\nAvailable transport packages:\n");
		for (int i = 0; i < transportList.size(); i++) {
			TransportModel transport = transportList.get(i);
			System.out.printf("\t%d. %s - Rs.%.2f per day\n", i + 1, transport.getPackageName(), transport.getCost());
		}

		// Ask the customer to select a transport package
		System.out.print("\nEnter the number of the transport package you want to book: ");
		int packageNumber = Integer.parseInt(scan.nextLine());

		// Get the selected transport package
		TransportModel selectedTransport = transportList.get(packageNumber - 1);

		// Ask the customer to enter the number of days they want to book the package
		// for
		System.out.printf("Enter the number of days you want to book the %s package for: ",
				selectedTransport.getPackageName());
		int numDays = Integer.parseInt(scan.nextLine());

		// Calculate the total cost of the booking
		double totalCost = selectedTransport.getCost() * numDays;

		// Display the booking details to the customer
		System.out.println("\nBooking details:\n");
		System.out.printf("\tTransport package: %s\n", selectedTransport.getPackageName());
		System.out.printf("\tCost per day: $%.2f\n", selectedTransport.getCost());
		System.out.printf("\tNumber of days: %d\n", numDays);
		System.out.printf("\tTotal cost: $%.2f\n", totalCost);

		// Ask if customer is sure they want to add the booking
		System.out.print("\nAre you sure you want to add this booking? (Y/N): ");
		String answer = scan.nextLine();
		if (answer.equalsIgnoreCase("Y")) {
			System.out.print("\nEnter your name: ");
			String customerName = scan.nextLine();

			System.out.print("Enter your NIC: ");
			String customerNIC = scan.nextLine();

			// Booking object
			booking.setCustomerName(customerName);
			booking.setCustomerNIC(customerNIC);
			booking.setPackageId(selectedTransport.getId());
			booking.setNumDays(numDays);
			booking.setTotalCost(totalCost);

			// Save booking to database
			saveBooking(booking);
			System.out.println("\nBooking added successfully.");
		} else {
			System.out.println("\nBooking not added.");
		}
	}

	public void saveBooking(BookingModel booking) {
		// SQL Query
		String insertTransport = "INSERT INTO transportbookings(customerName,customerNIC,packageId, numDays, totalCost) "
				+ "VALUES('" + booking.getCustomerName() + "', '" + booking.getCustomerNIC() + "','"
				+ booking.getPackageId() + "','" + booking.getNumDays() + "','" + booking.getTotalCost() + "')";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertTransport);

		} catch (SQLException exc) {
			System.err.println("Error saving booking to database");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public void getMyBookings() {
		System.out.print("Enter your NIC: ");
		String nic = scan.nextLine();

		String query = "SELECT tb.id, tb.customerName, tb.customerNIC, tb.packageId, tb.numDays, tb.totalCost, td.vehicleType "
				+ "FROM transportbookings tb " + "JOIN transportdetails td ON tb.packageId = td.id "
				+ "WHERE tb.customerNIC = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, nic);
			ResultSet rs = stmt.executeQuery();

			System.out.println("");
			System.out.println("╔══════════════════════════════════════════════════╗");
			System.out.println("║                   My Bookings                    ║");
			System.out.println("╚══════════════════════════════════════════════════╝");
			System.out.println("\n--------------------------------------------------");

			while (rs.next()) {
				int bookingId = rs.getInt("id");
				String customerName = rs.getString("customerName");
				String customerNIC = rs.getString("customerNIC");
				int packageId = rs.getInt("packageId");
				String vehicleType = rs.getString("vehicleType");
				int numDays = rs.getInt("numDays");
				double totalCost = rs.getInt("totalCost");

				System.out.println("\tBooking ID: " + bookingId);
				System.out.println("\tCustomer Name: " + customerName);
				System.out.println("\tCustomer NIC: " + customerNIC);
				System.out.println("\tPackage ID: " + packageId);
				System.out.println("\tVehicle Type: " + vehicleType);
				System.out.println("\tNumber of Days: " + numDays);
				System.out.println("\tTotal Cost: " + totalCost);
				System.out.println("\n--------------------------------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
