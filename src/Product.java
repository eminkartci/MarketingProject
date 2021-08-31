import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Product {

	// TO DO:
	// 1 - Barcode |||| | |||| || |||
	// 2 - Read from csv file

	// TEST
	public static void main(String[] args) {

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

		// Product.read_products_csv("AndroidProducts.csv");
	}

	// Static Methods
	public static void get_information_arrList(ArrayList<Product> pList){
		
		System.out.println("Get information is started.");

		double maxPrice = 0,minPrice = 0,totalWeight = 0;

		for(Product p: pList){

			if(p.price > maxPrice){
				maxPrice = p.price;
			}
			if(p.price < minPrice){
				minPrice = p.price;
			}

			totalWeight += p.weight;
			
		}

		System.out.printf("There are %d products.\n",pList.size());
		System.out.printf("The max price is %f.\n",maxPrice);
		System.out.printf("The min price is %f.\n",minPrice);
		System.out.printf("Total Weight is %f.\n",totalWeight);

	}	

	public static void save_products_csv(ArrayList<Product> products, String fileName) {

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName + ".csv")));

			bw.write("SAP Code,Label,Price,Cost,Quantitiy,Weight");
			bw.newLine();

			for (Product p : products) {

				bw.write(p.SAP_Code + ",");
				bw.write(p.label + ",");

				if (p.price > 0) {
					bw.write(p.price + ",");
				}else {
					bw.write("0,");
				}

				if (p.cost > 0) {
					bw.write(p.cost + ",");
				}else {
					bw.write("0,");
				}
				
				bw.newLine();

			}

			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void read_products_csv(String fileName) {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			String line = "";
			while( (line = br.readLine()) != null ) {
				System.out.println(line.replace(",", "\t| "));
				System.out.println("--------------------------------------------------");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	// Attribute
	int SAP_Code, quantity;
	double price, cost;
	String label;
	double weight;

	// Constructor
	public Product(int SAP_Code, String label) {

		this.SAP_Code = SAP_Code;
		this.label = label;
		this.price = -1;
		this.cost = -1;
	}

	public Product(int SAP_Code, String label, double price, double cost) {

		this.SAP_Code = SAP_Code;
		this.label = label;
		this.price = price;
		this.cost = cost;

	}

	// Behavior

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

			BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File(this.SAP_Code + "_" + this.label.replace(" ", "_") + ".csv")));

			bw.write("SAP CODE," + this.SAP_Code + "\n");
			bw.write("Label," + this.label + "\n");

			if (this.price > 0) {
				bw.write("Price," + this.price + "\n");
			}

			if (this.cost > 0) {
				bw.write("Cost," + this.cost + "\n");
			}

			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String toString() {

		String content = "----- Product " + this.label + "-----" + "\n" + "| SAP Code: " + this.SAP_Code + "\n";

		if (this.price > 0) {
			content += "| Price: " + this.price + "\n";
		}

		if (this.cost > 0) {
			content += "| Cost: " + this.cost + "\n";
		}

		return content;

	}

}
