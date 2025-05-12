package api.payload;

import org.apache.juneau.annotation.Beanc;

import lombok.Data;

@Data
public class Product {
	
	private String name;
	private int price;
	private String color;
	//private String[] sellernames;
	
	@Beanc(properties = "name,price,color")
	public Product(String name, int price, String color) {
		super();
		this.name = name;
		this.price = price;
		this.color = color;
		//this.sellernames = sellernames;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

//	public String[] getSellersName() {
//		return sellernames;
//	}
//
//	public void setSellersName(String[] sellersName) {
//		this.sellernames = sellersName;
//	}
	
	
}
