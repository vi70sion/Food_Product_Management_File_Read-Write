import java.time.LocalDate;

public class Meat extends Product{
    String typeOfMeat;
    public Meat(int code, String name, double price, LocalDate expiryDate, String typeOfMeat) {
        super(code, name, price, expiryDate);
        this.typeOfMeat = typeOfMeat;
    }

    public String getTypeOfMeat() { return typeOfMeat; }
    public void setTypeOfMeat(String typeOfMeat) { this.typeOfMeat = typeOfMeat; }

    @Override
    public String printProductInfo(){
        return super.printProductInfo() + ", rūšis: " + typeOfMeat;
    }
    @Override
    public String printProductInfoLine(){ return super.printProductInfoLine() + "," + typeOfMeat; }

}
