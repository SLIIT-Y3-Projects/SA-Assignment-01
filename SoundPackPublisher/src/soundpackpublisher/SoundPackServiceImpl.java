package soundpackpublisher;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
//import java.util.ArrayList;
//import java.util.Scanner;

import eventdatabase.EventDb;
import eventdatabase.IEventDb;


public class SoundPackServiceImpl implements SoundPackService {
	
	private Connection connection = null;
	private Statement statement = null;
	private IEventDb eventDatabase;
	private ResultSet resultSet;
	
Scanner scan = new Scanner(System.in);
	
	public SoundPackServiceImpl() {
		super();
		eventDatabase = (IEventDb) new EventDb();
		connection = eventDatabase.connection();
	}

	@Override
	public void addPackage() {
		SoundPackModel soundPackModel = new SoundPackModel();
		
	
		
		System.out.println("Enter Sound Package Name : ");
		soundPackModel.setPackName(scan.nextLine().trim());
		

		System.out.println("Enter Sound Package Type (outdoor/indoor) : ");
		soundPackModel.setPackType(scan.nextLine().trim());
		
		
		System.out.println("Enter Sound Pack Price : ");
		soundPackModel.setPrice(scan.nextFloat());
		
		String insertSoundPack = "INSERT INTO soundpackdetails(packName,packType,price) " 
		+ "VALUES('"+ soundPackModel.getPackName() +"', '"+ soundPackModel.getPackType() +"','"+ soundPackModel.getPrice() +"')";
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertSoundPack);
			System.out.println("Sound Pack Inserted");
		}catch (SQLException exc) {
			System.out.println("Error with Interting Sound Pack");
			System.out.println(exc.getMessage());
			
		}		
	}

	@Override
	public void getAllPackages() {

		ArrayList<SoundPackModel>soundPackList=new ArrayList<SoundPackModel>();
		try {
			statement=connection.createStatement();
			String SelectAllPacks = "SELECT * FROM soundpackdetails";
			resultSet = statement.executeQuery(SelectAllPacks);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		
		try {
	      while (resultSet.next()) {
	    	  SoundPackModel soundPack = new SoundPackModel();
	    	  
	    	  	soundPack.setId(resultSet.getInt("id"));
	    	  	soundPack.setPackName(resultSet.getString("packName"));
	    	  	soundPack.setPackType(resultSet.getString("packType"));
	    	  	soundPack.setPrice(resultSet.getFloat("price"));
		    	soundPackList.add(soundPack);
		    	
			} }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
		 for(SoundPackModel soundPackModel: soundPackList){
	    	  System.out.printf("%10d %20s %20s %20f\n",soundPackModel.getId(),soundPackModel.getPackName(),soundPackModel.getPackType(),soundPackModel.getPrice());
	      } 
		
	}

	@Override
	public void getPackageByID() {
		
		int packId;
		
		System.out.print("Enter Sound Pack ID : ");
		packId = Integer.parseInt(scan.nextLine().trim());
		
		String getById = "SELECT * FROM soundpackdetails WHERE id = '"+ packId +"'";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getById);
		      while (resultSet.next()) { 
		    	
		    	  System.out.printf("\n%20d %20s %20s %20f \n",resultSet.getInt("id"),resultSet.getString("packName"),resultSet.getString("packType"),resultSet.getFloat("price"));		    	
		      }
		} catch (SQLException exc) {
			System.out.println("Error in get Sound Package by Id");
			System.out.println(exc.getMessage());
		}
	}
	

	public float getPrice(int packId) {
		// TODO Auto-generated method stub
		 String getPackagePrice = "SELECT price FROM soundpackdetails WHERE id = '"+ packId +"'";
			
			try {
				
				float price=0;
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery("SELECT * FROM soundpackdetails WHERE id = '"+ packId +"'");
			   
			    while (resultSet.next()) { 
			    	 price=resultSet.getInt("price");
					 
			      }
			    return price;
			    
			} catch (SQLException exc) {
				System.out.println("Error in get Sound Package by Id");
				System.out.println(exc.getMessage());
				return -1;
			}
		
	}
	

	@Override
	public void deletePackage() {
		
		int packId;
		
		System.out.print("Enter Sound Pack ID To Delete : ");
		packId = Integer.parseInt(scan.nextLine().trim());
		String deletePackage ="\ndelete from soundpackdetails where id='"+ packId +"' ";
				
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(deletePackage);
			System.out.println("Sound Pack Successfully deleted\n");

		} catch (SQLException exc) {
			System.out.println("Error with delete Sound Pack");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public void calTotalSoundsFee() {
		// TODO Auto-generated method stub
		String subOption;
		int packId;
		int hrs;
		int extraHrs=0;
		float price=0;
		double extraHrsCharge=0;
		double GovtTaxCharge=0;
		double TotalFee=0;
		
		
		System.out.println("Do you want to see the Package Rates? (y/n)");
		subOption = scan.nextLine().trim();
		System.out.println("\n");
		System.out.println("----------------------------------------------------------------------------------------------");
		
		if(subOption.equals("y")||subOption.equals("Y")) {
			
			getAllPackages();
			
			System.out.println("\n");
			System.out.println("\n");
			System.out.println("\n");
			System.out.println("All the prices given above is for 8 Hrs of time period.\n");
			System.out.println("Additional 10% from the package price will be added for each hour after 8 Hrs.\n");
			System.out.println("Government Tax of 5% will be add for Total Bill.\n\n");
			System.out.println("__________________________________________________________________________________________");

		}
		
		
		System.out.println("Enter the Package ID :");
		packId = Integer.parseInt(scan.nextLine().trim());

		System.out.println("Enter the time period(in Hrs) :");
		hrs = Integer.parseInt(scan.nextLine().trim());
		
        price=getPrice(packId);
		
		if(hrs>8){
			extraHrs=(hrs-8);
		}
		
		extraHrsCharge=(price*0.1)*extraHrs;
		GovtTaxCharge=(price+extraHrsCharge)*0.05;
		TotalFee=price+extraHrsCharge+GovtTaxCharge;
		
		
		System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
		
		System.out.println("Package ID                 :" +packId);
		System.out.println("Hrs Needed                 :" +hrs);
		System.out.println("Price for the Sound Package:" +price);
		System.out.println("No of Extra Hrs            :" +extraHrs);
		System.out.println("Charge for extra Hrs       :" + extraHrsCharge);
		System.out.println("Government Tax             :" + GovtTaxCharge);
		System.out.println("Total Fee                  :" + TotalFee);
		
		System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
		
		try {
			PrintWriter out = new PrintWriter("Quotation.txt");
			
			out.println("Quotation for the Sound Package");
			out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
			
			out.println("Package ID                 :" +packId);
			out.println("Hrs Needed                 :" +hrs);
			out.println("Price for the Sound Package:" +price);
			out.println("No of Extra Hrs            :" +extraHrs);
			out.println("Charge for extra Hrs       :" + extraHrsCharge);
			out.println("Government Tax             :" + GovtTaxCharge);
			out.println("Total Fee                  :" + TotalFee);
			
			out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
			
			out.close(); 
			
			System.out.println("Quotation is been saved to a file...");
			
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
