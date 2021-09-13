import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Product {

	// TO DO:
	// 1 - Manuel Product Insert
	// 2 

	// CONSTANTS
	public static int BARCODE_LENGTH = 30;
	public static int BARCODE_NUM_LENGTH = 10;
	public static Scanner scanStr = new Scanner(System.in);
	public static Scanner scanInt = new Scanner(System.in);
	public static Random random = new Random();
	public static HashMap<String,Product> barcode2product;
	public static HashMap<String,Product> barcodeNum2product;

	// TEST
	public static void main(String[] args) {

		ArrayList products = Product.read_products_csv("AppleProducts.csv");

		System.out.println(read_barcode("||  | |     | | |  || | ||||  "));

	}

	public static void initialize_product(){

		Product p1 = new Product(245135, "Macbook Pro");
		Product p2 = new Product(245256, "Ipad Pro", 13000, 4100);
		Product p3 = new Product(224522, "Airpad", 8024, 1542);
		Product p4 = new Product(457334, "I phone X");
		Product p5 = new Product(235644, "Android");
		Product p6 = new Product(109942, "Monster", 18034, 8425);

		
		
		ArrayList<Product> pList = new ArrayList<Product>();
		pList.add(p1);
		pList.add(p2);
		pList.add(p3);
		pList.add(p4);
		pList.add(p5);
		pList.add(p6);

		Product.get_information_arrList(pList);
		Product.save_products_csv(pList, "AppleProducts");

		ArrayList<Product> newShipment = Product.read_products_csv("AndroidProducts.csv");
		Product.get_information_arrList(newShipment);

		Product.save_products_csv(newShipment, "storedProducts");

	}

	public static Product read_barcode(String barcode){

		try{
			return barcode2product.get(barcode);
		}catch (Exception e){
			System.out.println("This barcode doesn't exist!!");
			return null;
		}

	}

	public static Product insert_product_by_user(){

		System.out.println(" -- Product Insert Menu -- ");
		System.out.println(" SAP Code (INTEGER): " ); int sap_code = Product.scanInt.nextInt();
		System.out.println(" Label (STRING): " ); String label = Product.scanStr.nextLine();

		return new Product(sap_code, label);

	}
	
	public void generate_new_barcode_num(){

		String barcodeNumber = "";

		for(int i = 0 ; i < this.BARCODE_NUM_LENGTH ; i++){
			barcodeNumber += this.random.nextInt(10);
		}

		// System.out.println("Barcode Number:" + barcodeNumber);
		this.barcodeNumber = barcodeNumber;
	    

	}

	// Static Methods
	public static void get_information_arrList(ArrayList<Product> pList){
		
		System.out.println("Get information is started.");

		double maxPrice = 0,minPrice = 999999,totalWeight = 0;

		for(Product p: pList){

			if(p.price > maxPrice){
				maxPrice = p.price;
			}
			if(p.price < minPrice && p.price >= 0){
				minPrice = p.price;
			}

			if(p.weight > 0 ){
				totalWeight += p.weight;
			}
			
		}

		System.out.printf("There are %d products.\n",pList.size());
		System.out.printf("The max price is %.2f.\n",maxPrice);
		System.out.printf("The min price is %.2f.\n",minPrice);
		System.out.printf("Total Weight is %.2f.\n",totalWeight);

	}	

	public static void save_products_csv(ArrayList<Product> products, String fileName) {

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName + ".csv")));

			bw.write("SAP Code,Label,Price,Cost,Quantitiy,Weight,Barcode,Barcode Number");
			bw.newLine();

			for (Product p : products) {
				
				bw.write(p.csv_string());
				
				bw.newLine();

			}

			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static ArrayList<Product> read_products_csv(String fileName) {
		ArrayList<Product> newProducts = new ArrayList<Product>();
		barcode2product = new HashMap<String,Product>();
		barcodeNum2product = new HashMap<String,Product>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			String line = "";
			while( (line = br.readLine()) != null ) {
				System.out.println(line);
				String[] pInfo = line.split(",");

				try {
					Product tempProduct = new Product( Integer.parseInt(pInfo[0]), pInfo[1], Double.parseDouble(pInfo[2]),Double.parseDouble(pInfo[3]),Integer.parseInt(pInfo[4]),Double.parseDouble(pInfo[5]),pInfo[6],pInfo[7]);					
					// System.out.println(tempProduct);
					newProducts.add(tempProduct);
					barcode2product.put(pInfo[6], tempProduct);
					barcode2product.put(pInfo[7], tempProduct);
				} catch (Exception e) {
					System.out.println("Line: " + line + " cannot be converted !!" );
				}
				
				System.out.println("--------------------------------------------------");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(newProducts.size() > 0 ){
			return newProducts;
		}else{
			return null;
		}
		
	}

	// Attribute
	int SAP_Code, quantity;
	double price, cost;
	String label;
	double weight;
	String barcode;
	String barcodeNumber;

	// Constructor
	public Product(int SAP_Code, String label) {

		this.SAP_Code = SAP_Code;
		this.label = label;
		this.price = -1;
		this.cost = -1;
		this.weight = Math.random() * 5 + 5;
		generate_new_barcode();
		generate_new_barcode_num();


	}

	public Product(int SAP_Code, String label, double price, double cost) {
		this(SAP_Code,label);
		this.price = price;
		this.cost = cost;
		this.weight = Math.random() * 5 + 5;

	}

	public Product(int SAP_Code, String label, double price, double cost, int quantity, double weight) {
		this(SAP_Code,label,price,cost);
		this.quantity = quantity;
		this.weight = weight;

	}

	public Product(int SAP_Code, String label, double price, double cost, int quantity, double weight,String barcode,String barcodeNumber) {
		this(SAP_Code,label,price,cost,quantity,weight);
		this.barcode = barcode;
		this.barcodeNumber = barcodeNumber;

	}

	// Behavior

	public void generate_new_barcode(){


		char tempChar = ' ';
		String barcode = "";
		for(int i = 0 ; i < Product.BARCODE_LENGTH ; i++){
			
			if(Math.random() > 0.5){
				tempChar = '|';
			}else{
				tempChar = ' ';
			}

			barcode+= tempChar;

		}

		// System.out.println("BARCODE: " + barcode);
		this.barcode = barcode;

	}

	public void save_as_txt() {

		try {

			BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File(this.SAP_Code + "_" + this.label.replace(" ", "_") + ".txt")));

			bw.write(this.toString());

			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void save_as_csv() {

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(this.SAP_Code + "_" + this.label.replace(" ", "_") + ".csv")));

			bw.write(csv_string());
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String csv_string(){

		// "SAP Code,Label,Price,Cost,Quantitiy,Weight,Barcode,Barcode Number"

		String content =  this.SAP_Code + ",";

		content +=  this.label + ",";

		if(this.price > 0){
			content +=  this.price + ",";
		}else{
			content += "0,";
		}

		if(this.cost > 0){
			content += this.cost + ",";
		}else{
			content += "0,";
		}

		if(this.quantity > 0){
			content +=  this.quantity+ ",";
		}else{
			content += "0,";
		}

		if(this.weight > 0){
			content +=  this.weight+ ",";
		}else{
			content += "0,";
		}
		
		// Barcode 
		if(this.barcode != null){
			content +=  this.barcode+ ",";
		}else{
			content += "-,";
		}
		if(this.barcodeNumber != null){
			content +=  this.barcodeNumber+ ",";
		}else{
			content += "-,";
		}

		return content;
	}

	public String toString() {

		String content = "----- Product " + this.label + "-----" + "\n" + "| SAP Code: " + this.SAP_Code + "\n";

		if (this.price > 0) {
			content += "| Price: " + this.price + "\n";
		}

		if (this.cost > 0) {
			content += "| Cost: " + this.cost + "\n";
		}


		// Barcode 
		if(this.barcode != null){
			content += "| Barcode: " + this.barcode+ "\n";
		}

		if(this.barcodeNumber != null){
			content += "| Barcode Number: " + this.barcodeNumber+ "\n";
		}

		return content;

	}

}
