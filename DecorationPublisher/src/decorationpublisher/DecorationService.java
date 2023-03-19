package decorationpublisher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import decorationpublisher.*;
import eventdatabase.EventDb;
import eventdatabase.IEventDb;

public class DecorationService implements DecorationInfoService{
	
	private Connection connection = null;
	private Statement statement = null;
	private IEventDb eventDatabase;
	private ResultSet resultSet;
	
	Scanner scan=new Scanner(System.in);

	public DecorationService() {
		super();
		eventDatabase = (IEventDb) new EventDb();
		connection = eventDatabase.connection();
	}

	@Override
	public String addDecorationPackage() {
		// TODO Auto-generated method stub
		DecorationModel decorationModel = new DecorationModel();
		
		System.out.println("Enter the Decoration Package Name : ");
		decorationModel.setDpackageName(scan.nextLine().trim());
		
		System.out.println("Enter Event time (Night or Day) : ");
		decorationModel.setEventTime(scan.nextLine().trim());
		
		System.out.println("Enter Event type (Ex:Birthday party,Wedding) : ");
		decorationModel.setEventType(scan.nextLine().trim());
		
		System.out.println("Enter Event Location area in square feet : ");
		decorationModel.setAreaSize(scan.nextInt());
		
		System.out.println("Enter Package Price Rs: ");
		decorationModel.setPrice(scan.nextInt());
		
		
		String insertCustomer = "INSERT INTO decorationpackageslist(packageName,eventTime,eventType,areaSize,price) " 
		+ "VALUES('"+ decorationModel.getDpackageName() +"', '"+ decorationModel.getEventTime() +"','"+ decorationModel.getEventType() +"','"+ decorationModel.getAreaSize() +"','"+ decorationModel.getPrice() +"')";
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertCustomer);
			System.out.println("Package Inserted");
		}catch (SQLException exc) {
			System.out.println("Error with Interted package");
			System.out.println(exc.getMessage());
			
		}
		return insertCustomer;
		
	}

	@Override
	public void getAllDecorationPackages() {
		// TODO Auto-generated method stub
		 String query = "SELECT * FROM decorationpackageslist";
		    try {
		        statement = connection.createStatement();
		        ResultSet rs = statement.executeQuery(query);
		        while (rs.next()) {
		            System.out.println("Package ID   : " + rs.getInt("id"));
		            System.out.println("Package Name : " + rs.getString("packageName"));
		            System.out.println("Event Time   : " + rs.getString("eventTime"));
		            System.out.println("Event Type   : " + rs.getString("eventType"));
		            System.out.println("Area Size    : " + rs.getInt("areaSize") + " square feet");
		            System.out.println("Package Price: " + rs.getInt("price"));
		            System.out.println("------------------------------");
		        }
		    } catch (SQLException e) {
		        System.out.println("Error retrieving decoration packages: " + e.getMessage());
		    }
		
	}

	@Override
	public void getDecorationPackageById() {
		// TODO Auto-generated method stub
		 System.out.println("Enter package ID: ");
		    int packageId = scan.nextInt();
		    String query = "SELECT * FROM decorationpackageslist WHERE id = " + packageId;
		    try {
		        statement = connection.createStatement();
		        ResultSet rs = statement.executeQuery(query);
		        if (rs.next()) {
		            System.out.println("Package ID: " + rs.getInt("id"));
		            System.out.println("Package Name: " + rs.getString("packageName"));
		            System.out.println("Event Time: " + rs.getString("eventTime"));
		            System.out.println("Event Type: " + rs.getString("eventType"));
		            System.out.println("Area Size: " + rs.getInt("areaSize") + " square feet");
		            System.out.println("Package Price: " + rs.getInt("price") );
		        } else {
		            System.out.println("No package found with ID " + packageId);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error retrieving decoration package: " + e.getMessage());
		    }
	}
	
	public int getDecorationPriceById(int id) {
		// TODO Auto-generated method stub
		
		int price;
		
		    String query = "SELECT price FROM decorationpackageslist WHERE id = " + id;
		    try {
		        statement = connection.createStatement();
		        ResultSet rs = statement.executeQuery(query);
		        if (rs.next()) {
		            price=rs.getInt("price");
		            return price;
		        } else {
		            System.out.println("No package found with ID " + id);
		            return -1;
		        }
		    } catch (SQLException e) {
		        System.out.println("Error retrieving decoration package: " + e.getMessage());
		        return -1;
		    }
	}

	@Override
	public void updateDecorationPackage() {
		// TODO Auto-generated method stub
		 System.out.println("Enter package ID to update: ");
		    int packageId = scan.nextInt();
		    scan.nextLine();
		    System.out.println("Enter new package name                            : ");
		    String packageName = scan.nextLine().trim();
		    System.out.println("Enter new event time (Night or Day)               : ");
		    String eventTime = scan.nextLine().trim();
		    System.out.println("Enter new event type (Ex: Birthday party, Wedding): ");
		    String eventType = scan.nextLine().trim();
		    System.out.println("Enter new area size in square feet                : ");
		    int areaSize = scan.nextInt();
		    System.out.println("Enter Updated Price ");
		    int price = scan.nextInt();
		    String query = "UPDATE decorationpackageslist SET packageName = ?, eventTime = ?, eventType = ?, areaSize = ?, price = ? WHERE id = ?";
		    try {
		        PreparedStatement statement = connection.prepareStatement(query);
		        statement.setString(1, packageName);
		        statement.setString(2, eventTime);
		        statement.setString(3, eventType);
		        statement.setInt(4, areaSize);
		        statement.setInt(5, price);
		        statement.setInt(6, packageId);
		        int rowsAffected = statement.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("Decoration package updated successfully");
		        } else {
		            System.out.println("No decoration package found with ID " + packageId);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error updating decoration package: " + e.getMessage());
		    }
		
	}

	@Override
	public void deleteDecorationPackage() {
		// TODO Auto-generated method stub
		System.out.println("Enter package ID: ");
	    int packageId = scan.nextInt();
	    String deleteQuery = "DELETE FROM decorationpackageslist WHERE id = " + packageId;
	    try {
	        statement = connection.createStatement();
	        int rowsAffected = statement.executeUpdate(deleteQuery);
	        if (rowsAffected == 0) {
	            System.out.println("No package found with ID " + packageId);
	        } else {
	            System.out.println("Package with ID " + packageId + " has been deleted.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error deleting decoration package: " + e.getMessage());
	    }
		
	}

	@Override
	public void calculateDecorationBill() {
		
		 double basePrice = 0.0;
		 double flowerPrice = 0.0;
		 String light ;
		 double lightPrice=0.0;
		 String ans;
		 String id;
		 int id2;



		 
	    Scanner scan = new Scanner(System.in);

	    System.out.println("\n\n\n");
	    System.out.println("************* Calculate Decoration Bill *************\n");
	    
	    System.out.println("Do You Want To See The Decoration Packages? (y/n)");
	    ans = scan.nextLine().trim();
	    
	    if(ans.equalsIgnoreCase("y")) {
	    	
	    	this.getAllDecorationPackages();
	    	
	    }
	    
	    System.out.print("Enter the Package ID : ");
	    id = scan.nextLine().trim();
	    id2=Integer.parseInt(id);
	    
	    basePrice=this.getDecorationPriceById(id2);
	    
	    
	    
	    
	    System.out.print("Enter the type of flowers (real or plastic): ");
	    String flowerType = scan.nextLine().trim();

	    if (flowerType.equalsIgnoreCase("real")) {
	        flowerPrice = basePrice*0.5;
	    } else if (flowerType.equalsIgnoreCase("plastic")) {
	        flowerPrice = basePrice*0.2;
	    } else {
	        System.out.println("Invalid flower type. Assuming no flowers selected.");
	    }
	    
	    System.out.print("Do you want Lights? (y or n): ");
	    light = scan.nextLine().trim();

	  
	    if (light.equalsIgnoreCase("y")) {
	        lightPrice = basePrice*0.1;
	    } 

	    double totalPrice = basePrice + flowerPrice+lightPrice;
	    double taxAmount = totalPrice * 0.15;
	    double finalPrice = totalPrice + taxAmount;

	    System.out.println("\n++--++--++--++--++--++--++-++--");
	    
	    System.out.println("Base price      : " + basePrice);
	    System.out.println("Flower type     : " + flowerType);
	    System.out.println("Flower price    : " + flowerPrice);
	    System.out.println("Light price     : " + lightPrice);
	    System.out.println("Total price     : " + totalPrice);
	    System.out.println("Tax amount (15%): " + taxAmount);
	    System.out.println("Final price     : " + finalPrice);
	    
	    System.out.println("\n++--++--++--++--++--++--++-++--");
	}


	
}
