import java.util.List;

public interface CSVImportExport {
    public void importFromCSV(String path,List<Product> list);
    public void exportToCSV(List<Product> products, String path);
    public void addProductToCSV(Product product);
    public void removeProductFromCSV(Product product);
    public void removeProductFromCSV(int code);

}
