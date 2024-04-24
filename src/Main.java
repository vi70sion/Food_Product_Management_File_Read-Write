import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

//        Sukurkite programą, "Maisto prekių valdymas parduotuvėje". Programa turės gebėti skaityti, pildyti ir atnaujinti
//        duomenis apie prekes iš CSV failo.
//        Reikalavimai
//        Klasės struktūra:
//        Sukurkite bazinę klasę Produktas, kuri turėtų šias savybes:
//        kodas (int)
//        pavadinimas (String)
//        kaina (double)
//        galiojimoData (LocalDate)
//        Metodai: konstruktorius, getteriai/setteriai, toString().
//        Sukurkite paveldinčiasias klases Vaisius ir Mėsa. Kiekviena iš šių klasių turėtų turėti papildomų specifinių savybių:
//        Vaisius: arEkologiskas (boolean)
//        Mėsa: rusis (String)
//        Abu turėtų turėti atitinkamus konstruktorius, getterius/setterius ir perrašytą toString() metodą.
//        Implementuokite sąsają CSVImportExport, kuri turėtų metodus importuotiIsCSV(String path) ir
//        eksportuotiICSV(List<Produktas> produktai, String path), pridetiProduktaICSV(Produktas produktas),
//        pašalintiProduktaIsCSV(Produktas produktas) arba pašalintiProduktaIsCSV(int produktoKodas) (pasirinktinai).
//        Sukurkite krepšelį, jog produktus būtų galima pridėti pagal jų ID (kodą) į krepšelį,
//        kad būtų galima pamatyti prekių krepšelyje kainą.
//        Sukurkite prekių sąrašo pildymą iš konsolinės įvesties, bei ištrynimą, konsolinės įvesties pagalba

        Scanner scanner = new Scanner(System.in);
        String filePath = "C:\\JavaTest\\foods.csv";
        String filePathWrite = "C:\\JavaTest\\foodslist.csv";
        IOManager manager = new IOManager();
        List<Product> productList = new ArrayList<Product>();

        String choice = "";
        do{
            System.out.println("(1)- importuoti prekes iš failo, (2)- eksportuoti prekes į failą, (3)- pridėti prekę į failą,");
            System.out.println("(4)- pašalinti prekę iš failo, (5)- pašalinti prekę iš failo pagal ID, (6)- dėti prekes į krepšelį, (0)- pabaiga");
            choice = scanner.nextLine();
            switch (choice){
                case "1":
                    productList.clear();
                    manager.importFromCSV(filePath, productList);
                    for(Product item : productList) System.out.println(item.printProductInfo()); //console
                    break;
                case "2":
                    manager.exportToCSV(filePathWrite, productList);
                    break;
                case "3":
                    manager.addProductToCSV(filePathWrite, manager.describeProduct());
                    break;
                case "4":
                    productList.clear();
                    manager.importFromCSV(filePathWrite, productList);
                    for(Product item : productList) System.out.println(item.printProductInfo()); //console
                    System.out.println("Įveskite norimą pašalinti produktą viena eilute:");
                    Product product = manager.describeProduct();
                    manager.removeProductFromCSV(filePathWrite, product);
                    break;
                case "5":
                    productList.clear();
                    manager.importFromCSV(filePathWrite, productList);
                    for(Product item : productList) System.out.println(item.printProductInfo()); //console
                    System.out.println("Įveskite norimo pašalinti produkto kodą:");
                    int code = Integer.parseInt(scanner.nextLine());
                    manager.removeProductFromCSV(filePathWrite, code);
                case "6":
                    productList.clear();
                    List<Product> shoppingCart = new ArrayList<>();
                    manager.importFromCSV(filePathWrite, productList);
                    for(Product item : productList) System.out.println(item.printProductInfo()); //console
                    String input;
                    do{
                        System.out.println("Pridėkite prekę į krepšelį, įveskite (kodas) arba (0- pabaiga): ");
                        input = scanner.nextLine();
                        switch (input){
                            case "0":
                                break;
                            default:
                                for(Product item : productList){
                                    if(item.getCode() == Integer.parseInt(input)){
                                        System.out.println("Radau!");
                                        shoppingCart.add(item);
                                        break;
                                    }
                                }
                                break;
                        }
                    } while (!input.equals("0"));
                    double shoppingCartTotal = 0;
                    for(Product item : shoppingCart){
                        item.printProductInfoLine();
                        shoppingCartTotal += item.getPrice();
                    }
                    System.out.println("Krepšelio suma: " + shoppingCartTotal);
                    break;
                case "0":
                    break;
            }
        } while(!choice.equals("0"));

    }

}