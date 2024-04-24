import java.time.LocalDate;
import java.util.Objects;

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
    public String printProductInfoLine(){
        return code + "," + name +  "," + price + "," + expiryDate;
    }

    public boolean compareProducts(Product obj1, Product obj2) {
        return (obj1.code == obj2.code && obj1.name.equals(obj2.name) && obj1.price == obj2.price && obj1.expiryDate.isEqual(obj2.expiryDate));
    }

}
