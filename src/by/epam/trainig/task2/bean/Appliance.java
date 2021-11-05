package by.epam.trainig.task2.bean;


/** Класс бытовой техники (appliance).
 * @author Noname
 */

public class Appliance {
	
	/** Типом техники может являтся "утюг", "холодильник" и т.д.*/
	private String type;
	private String model;
	private String manufacturer;
	private double price;
	
	public String getType() {
		return type;
	}
	
	public void setType(String category) {
		this.type = category;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public String getFullInfo() {
		return String.format("Type: %s, Model: %s, Manufacturer: %s, Price: %.2f$", 
							 type, model, manufacturer, price);
	}
	
	public void reset(String type, String model, String manufacturer, double price) {
		this.type = type;
		this.model = model;
		this.manufacturer = manufacturer;
		this.price = price;
	}
	
	public Appliance(String type, String model, String manufacturer, double price) {
		reset(type, model, manufacturer, price);
	}
	
	public Appliance() {}
	
}