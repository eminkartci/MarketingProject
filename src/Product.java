


public class Product {
	
	
	 
	// TEST
	public static void main(String[] args) {

		Product p1 = new Product(0, "Macbook 2");
		Product p2 = new Product(0, "Macbook 2",13000,4100);
		
		System.out.println(p1);
		System.out.println(p2);
	}
	
	
	// Attribute
	int SAP_Code,quantity;
	double price,cost;
	String label;
	double weight;
	
	
	
	// Constructor
	public Product(int SAP_Code,String label) {
		
		this.SAP_Code 	= SAP_Code;
		this.label 		= label;
		
	}
	
	public Product(int SAP_Code,String label,double price,double cost) {
		
		this.SAP_Code 	= SAP_Code;
		this.label 		= label;
		this.price 		= price;
		this.cost 		= cost;
		
	}
	
	
	// Behavior

}
