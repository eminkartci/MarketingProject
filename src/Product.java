import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Product {

	// TO DO:
	// 1 - Barcode |||| | |||| || |||
	// 2 - Read from csv file

	// TEST
	public static void main(String[] args) {

		Product p1 = new Product(0, "Macbook 2");
		Product p2 = new Product(1, "Ipad 7", 13000, 4100);

		System.out.println(p1);
		System.out.println(p2);

		p2.save_as_csv();
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

			bw.write("SAP CODE,"+this.SAP_Code + "\n");
			bw.write("Label,"+this.label + "\n");
			
			if (this.price > 0) {
				bw.write("Price,"+this.price + "\n");
			}
			
			if (this.cost > 0) {
				bw.write("Cost,"+this.cost + "\n");
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
