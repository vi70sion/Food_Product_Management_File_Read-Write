import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class IOManager implements CSVImportExport{
    @Override
    public void importFromCSV(String filePath, List<Product> list) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String[] lineValues = line.split(",");
                try {
                    boolean booleanValue = Boolean.parseBoolean(lineValues[4]); //if true - fruit
                    list.add(new Fruit(Integer.parseInt(lineValues[0]),
                                       lineValues[1],
                                       Double.parseDouble(lineValues[2]),
                                       LocalDate.parse(lineValues[3]),
                                       Boolean.parseBoolean(lineValues[4])));
                } catch (NumberFormatException e) {
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
    public void exportToCSV(List<Product> products, String path) {

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

}
