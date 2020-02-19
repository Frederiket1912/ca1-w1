package dto;
import entities.Cars;

public class CarsDTO {
    private String manufacturer;
    private int year;
    private String model;
    private int price;
    private int quantity;
    
    
    public CarsDTO(Cars cars) {
        this.manufacturer = cars.getManufacturer();
        this.year = cars.getYear();
        this.model = cars.getModel();
        this.price = cars.getPrice();
        this.quantity = cars.getQuantity();
    }

    @Override
    public String toString() {
        return "CarsDTO{" + "manufacturer=" + manufacturer + ", year=" + year + ", model=" + model + ", price=" + price + ", quantity=" + quantity + '}';
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
}
