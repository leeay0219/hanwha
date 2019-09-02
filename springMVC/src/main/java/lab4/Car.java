package lab4;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

//<bean id = "car" class = :lab4.car" ��¼��> �� comp[onent�� ����
@Component
public class Car {
	String model;
	int price;
	
	public Car() {}
	
	public Car(String model, int price) {
		super();
		this.model = model;
		this.price = price;
	}

	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "�ڵ��� ����: Car [model=" + model + ", price=" + price + "]";
	}


	
}
