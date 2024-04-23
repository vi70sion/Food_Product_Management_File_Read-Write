import java.time.LocalDate;

public class Fruit extends Product {
    boolean ecoFriendly;
    public Fruit(int code, String name, double price, LocalDate expiryDate, boolean ecoFriendly) {
        super(code, name, price, expiryDate);
        this.ecoFriendly = ecoFriendly;
    }
    public boolean isEcoFriendly() { return ecoFriendly; }
    public void setEcoFriendly(boolean ecoFriendly) { this.ecoFriendly = ecoFriendly; }

    @Override
    public String printProductInfo(){
        return super.printProductInfo() + ", ekologiškas: " + ecoFriendly;
    }
    @Override
    public String printProductInfoLine(){ return super.printProductInfoLine() + "," + ecoFriendly; }
}
