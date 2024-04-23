import java.io.*;
import java.time.LocalDate;
import java.util.List;

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
    public void addProductToCSV(Product product) {

    }

    @Override
    public void removeProductFromCSV(Product product) {

    }

    @Override
    public void removeProductFromCSV(int code) {

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
