import java.time.LocalDate;

public class Product {
    int code;
    String name;
    double price;
    LocalDate expiryDate;
    public Product(int code, String name, double price, LocalDate expiryDate) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
    }
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }


    public String printProductInfo(){
        return "ID: " + code + ", pavadinimas: " + name +  ", price: " + price + ", galiojimo data: " + expiryDate;
    }

}
