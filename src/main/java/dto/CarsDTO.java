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

    
    
}
