package foodandbeveragepublisher;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

import eventdatabase.EventDb;
import eventdatabase.IEventDb;

import java.io.*;


import java.util.ArrayList;

public class FoodandBeverageService implements IFoodandBeverageService {

	private Connection connection = null;
	private Statement statement = null;
	private IEventDb eventDatabase;
	private ResultSet resultSet;
	
	Scanner scan=new Scanner(System.in);
	
	public FoodandBeverageService() {
		super();
		eventDatabase = (IEventDb) new EventDb();
		connection = eventDatabase.connection();
	}
	
	@Override
	public void addPackage() {
		
		FoodandBeverageModel foodandbeveragemodel = new FoodandBeverageModel();
			
		System.out.println("Enter Package ID : ");
		foodandbeveragemodel.setPackageId(scan.nextLine().trim());
		
		System.out.println("Enter Package Name : ");
		foodandbeveragemodel.setPackageName(scan.nextLine().trim());
				
		System.out.println("Enter Package Price : ");
		foodandbeveragemodel.setPrice(scan.nextInt());
		
		
		
		String insertPackage = "INSERT INTO packagedetails(packageId,packageName, price)"
						+ "VALUES('"+ foodandbeveragemodel.getPackageId()+"', '"+ foodandbeveragemodel.getPackageName()+"', '"+ foodandbeveragemodel.getPrice()+"')";
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertPackage);
			System.out.println("---🍔 Food and Beverage Package successfully inserted 🍔 ---");
			
		}catch (SQLException exc) {
			System.out.println("Error with Insert Food and Beverage Package");
			System.out.println(exc.getMessage());
		}
		
	}

	@Override
	public void getAllPackages() {
		
		
		ArrayList<FoodandBeverageModel>packagesList = new ArrayList<FoodandBeverageModel>();
		
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM packagedetails";
			resultSet = statement.executeQuery(SelectAll);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			 while (resultSet.next()) {
				 FoodandBeverageModel packages = new FoodandBeverageModel();
		    	  
				 packages.setId(resultSet.getInt("id"));	
				 packages.setPackageId(resultSet.getString("packageId"));
			     packages.setPackageName(resultSet.getString("packageName"));
			     packages.setPrice(resultSet.getInt("price"));
			    
			     packagesList.add(packages);
			    	
				} }catch (SQLException e) {
					
					e.printStackTrace();
				}
		
		for(FoodandBeverageModel foodandbeveragemodel: packagesList){
	    	     	  
			  System.out.println("\n-----------------------------------------");
	    	  System.out.printf("%20s: %-25s\n", "ID", foodandbeveragemodel.getId());
	    	  System.out.printf("%20s: %-25s\n", "Package ID", foodandbeveragemodel.getPackageId());
	    	  System.out.printf("%20s: %-25s\n", "Package Name", foodandbeveragemodel.getPackageName());
	    	  System.out.printf("%20s: %-25s\n", "Per Person Price", foodandbeveragemodel.getPrice());
	    	  System.out.println("\n-----------------------------------------");
	    	  
	      } 
			
		}
		
	

	@Override
	public void getById() {
		int packageId;
		
		System.out.print("Enter Food and Beverage Package ID : ");
		packageId = Integer.parseInt(scan.nextLine().trim());
		
		String getById = "SELECT * FROM packagedetails WHERE id = '"+ packageId +"'";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getById);
		      while (resultSet.next()) { 
		    			    	  
		    	  System.out.println("\n-----------------------------------------");
		    	  System.out.printf("%20s: %-25s\n", "ID", resultSet.getInt("id"));
		    	  System.out.printf("%20s: %-25s\n", "Package ID", resultSet.getString("packageId"));
		    	  System.out.printf("%20s: %-25s\n", "Package Name", resultSet.getString("packageName"));
		    	  System.out.printf("%20s: %-25s\n", "Per Person Price", resultSet.getInt("price"));
		    	  System.out.println("\n-----------------------------------------");
		      
		      }
		} catch (SQLException exc) {
			System.out.println("Error in get Package by Id");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public void deletePackage() {
		int packageId;
		
		System.out.print("Enter Food and Beverage Package ID To Delete : ");
		packageId = Integer.parseInt(scan.nextLine().trim());
		String deletePackage ="\ndelete from packagedetails where id='"+packageId+"' ";
		
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(deletePackage);
			System.out.println("Food and Beverage Package Successfully deleted\n");

		} catch (SQLException exc) {
			System.out.println("Error with delete Food and Beverage Package");
			System.out.println(exc.getMessage());
		}
		
	}

	@Override
	public void calculateBill() {
		
		System.out.print("Enter Package Name : ");
		String packageName = scan.nextLine();
		
		System.out.print("Enter Per Person Price :");
		int price = scan.nextInt();
		
		System.out.print("Enter Number of Person :");
		int numOfPerson = scan.nextInt();
		
		System.out.print("Enter Service Charge :");
		int serviceCharge = scan.nextInt();
		
		int cal = price * numOfPerson ;
		int service = cal * serviceCharge/100;
		int totalCal = cal + service;
		
		System.out.println("\n-----------------------------------------");
		System.out.printf("------Food and Beverage Quoation---------");
		System.out.println("\n-----------------------------------------");
		System.out.printf("%20s: %-25s\n", "Package Name", packageName);
		System.out.printf("%20s: %-25s\n", "Per Person Price", price);
		System.out.printf("%20s: %-25s\n", "Number of Person", numOfPerson);
		System.out.printf("%20s: %-25s\n", "Service Charge(%)", serviceCharge);
		System.out.print("\n\n");
		System.out.print("\t\t -----------------");
		System.out.print("\n");
		System.out.printf("%20s: %-25s\n", "Total Price", +totalCal);
		System.out.print("\t\t -----------------");
		System.out.println("\n\n-----------------------------------------");
		System.out.printf("--------- Thank You ---------");
		System.out.println("\n-----------------------------------------");
		
		try {
			
			PrintWriter out =new PrintWriter("FoodQuotation.txt");
			
			out.println("\n-----------------------------------------");
			out.printf("------Food and Beverage Quoation---------");
			out.println("\n-----------------------------------------");
			out.printf("%20s: %-25s\n", "Package Name", packageName);
			out.printf("%20s: %-25s\n", "Per Person Price", price);
			out.printf("%20s: %-25s\n", "Number of Person", numOfPerson);
			out.printf("%20s: %-25s\n", "Service Charge(%)", serviceCharge);
			out.print("\n\n");
			out.print("\t\t -----------------");
			out.print("\n");
			out.printf("%20s: %-25s\n", "Total Price", +totalCal);
			out.print("\t\t -----------------");
			out.println("\n\n-----------------------------------------");
			out.printf("--------- Thank You ---------");
			out.println("\n-----------------------------------------");
			
			out.close();
			System.out.println("Food Quotation file downloaded...");
			
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	
		
		
	}
	
	

}
