import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;

public class IOManager implements CSVImportExport{
    @Override
    public void importFromCSV(String filePath, List<Product> list) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            boolean booleanValue = false;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                String[] lineValues = line.split(",");
                try {
                    booleanValue = parseBool(lineValues[4]); //if no error - fruit, else - meat
                    list.add(new Fruit(Integer.parseInt(lineValues[0]),
                                       lineValues[1],
                                       Double.parseDouble(lineValues[2]),
                                       LocalDate.parse(lineValues[3]),
                                       parseBoolean(lineValues[4])));
                } catch (IllegalArgumentException  e) {
                    list.add(new Meat(Integer.parseInt(lineValues[0]),
                                       lineValues[1],
                                       Double.parseDouble(lineValues[2]),
                                       LocalDate.parse(lineValues[3]),
                                       lineValues[4]));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Nepavyko skaityti failo: " + e.getMessage());
        }
    }

    @Override
    public void exportToCSV(String filePath, List<Product> list) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Product item : list){
                bufferedWriter.write(item.printProductInfoLine()); //csv format
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Nepavyko įrašyti failo: " + e.getMessage());
        }
    }

    @Override
    public void addProductToCSV(String filePath, Product product) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(product.printProductInfoLine());
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Nepavyko rasyti failo: " + e.getMessage());
        }

    }

    @Override
    public void removeProductFromCSV(String filePath, Product product) {
        List<Product> productList = new ArrayList<Product>();
        IOManager manager = new IOManager();
        manager.importFromCSV(filePath, productList);
        for(Product item : productList){
            if(product.compareProducts(product, item)){
                System.out.println("Radau!");
                productList.remove(item);
                break;
            }
        }
        manager.exportToCSV(filePath, productList);
    }

    @Override
    public void removeProductFromCSV(String filePath, int code) {
        List<Product> productList = new ArrayList<Product>();
        IOManager manager = new IOManager();
        manager.importFromCSV(filePath, productList);
        for(Product item : productList){
            if(item.getCode() == code){
                System.out.println("Radau!");
                productList.remove(item);
                break;
            }
        }
        manager.exportToCSV(filePath, productList);
    }

    public Product describeProduct() {
        Scanner scanner = new Scanner(System.in);
        int code; String name; double price; LocalDate date; boolean ecoFriendly; String typeOfMeat;
        Product product = null;
        String line; String[] lineValues;
        System.out.println("Įveskite produktą, (1)- vaisius, (2)- mėsa:");
        String prodChoice = scanner.nextLine();
        switch (prodChoice){
            case "1":
                System.out.println("Įveskite vaisių (viena eilute, atskiriant kableliais). ");
                System.out.println("(ID kodas), (pavadinimas), (kaina), (galiojimo data YYYY-MM-DD), (ekologiškas- true, ne- false): ");
                line = scanner.nextLine();
                lineValues = line.split(",");
                product = new Fruit(Integer.parseInt(lineValues[0]), lineValues[1], Double.parseDouble(lineValues[2]),
                                    LocalDate.parse(lineValues[3]), Boolean.parseBoolean(lineValues[4]));
                break;
            case "2":
                System.out.println("Įveskite mėsą (viena eilute, atskiriant kableliais). ");
                System.out.println("(ID kodas), (pavadinimas), (kaina), (galiojimo data YYYY-MM-DD), (rūšis): ");
                line = scanner.nextLine();
                lineValues = line.split(",");
                product = new Meat(Integer.parseInt(lineValues[0]), lineValues[1], Double.parseDouble(lineValues[2]),
                                    LocalDate.parse(lineValues[3]), lineValues[4]);
                break;
            default:
                System.out.println("Neteisingas įvedimas.");
        }
        return product;
    }

    public static boolean parseBool(String value) {
        if (value.equalsIgnoreCase("true")) {
            return true;
        } else if (value.equalsIgnoreCase("false")) {
            return false;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
