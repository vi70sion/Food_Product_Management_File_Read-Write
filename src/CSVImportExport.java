import java.util.List;

public interface CSVImportExport {
    public void importFromCSV(String path,List<Product> list);
    public void exportToCSV(String path, List<Product> products);
    public void addProductToCSV(String filePath, Product product);
    public void removeProductFromCSV(String filePath, Product product);
    public void removeProductFromCSV(String filePath, int code);
    public Product describeProduct();


}
