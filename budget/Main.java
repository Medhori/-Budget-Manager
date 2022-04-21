package budget;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static LinkedHashMap<String, LinkedHashMap<String, Double>> purchasesList = new LinkedHashMap<>();
    static double balance = 0;
    static String filename = "purchases.txt";
    static SerializableUI serializableUI = new SerializableUI();


    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("Choose your action: ");
            System.out.println("1) Add income");
            System.out.println("2) Add purchase");
            System.out.println("3) Show list of purchases");
            System.out.println("4) Balance");
            System.out.println("5) Save");
            System.out.println("6) Load");
            System.out.println("7) Analyze (Sort)");
            System.out.println("0) Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println();
                    addIncome();
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    addPurchases();
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    showListOfPurchases();
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    balance();
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    save();
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    load();
                    System.out.println();
                    break;
                case 7:
                    System.out.println();
                    analyzeSort();
                    System.out.println();
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid operation Try again!!");
                    break;
            }
        }
    }

    private static void analyzeSort() {
        Analyze analyze = new Analyze(purchasesList);
        while (true) {
            System.out.println("How do you want to sort?");
            System.out.println("1) Sort all purchases");
            System.out.println("2) Sort by type");
            System.out.println("3) Sort certain type");
            System.out.println("4) Back");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println();
                    analyze.sortAllPurchases();
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    analyze.sortByType();
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    analyze.sortCertainType();
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    mainMenu();
            }
        }
    }

    private static void load() {
        var object = serializableUI.load();
        balance = (double) object.get(0);
        purchasesList = (LinkedHashMap<String, LinkedHashMap<String, Double>>) object.get(1);
    }

    private static void save() {
        serializableUI.save(filename, balance, purchasesList);
    }

    private static void showListOfPurchases() {
        if (purchasesList.isEmpty()) {
            System.out.println("Purchase list is empty!");
        } else {
            while (true) {
                double sum = 0;
                String type = ShowPurchasesMenu();
                System.out.println();
                System.out.println(type + ":");
                if ("All".equals(type)) {
                    for (String key : purchasesList.keySet()) {
                        for (Map.Entry<String, Double> purchase : purchasesList.get(key).entrySet()) {
                            System.out.printf("%s $%.2f\n", purchase.getKey(), purchase.getValue());
                            sum += purchase.getValue();
                        }
                    }
                } else {
                    if (!purchasesList.containsKey(type)) {
                        System.out.println("Purchase list is empty!");
                        return;
                    } else {
                        for (Map.Entry<String, Double> purchase : purchasesList.get(type).entrySet()) {
                            System.out.printf("%s $%.2f\n", purchase.getKey(), purchase.getValue());
                            sum += purchase.getValue();
                        }
                    }
                }
                System.out.printf("Total sum: $%.2f\n", sum);
                System.out.println();
            }
        }
    }

    private static void addPurchases() {
        while (true) {
            String type = addPurchasesMenu();
            System.out.println();
            System.out.println("Enter purchase name:");
            String name = scanner.nextLine();
            System.out.println("Enter its price:");
            double price = Double.parseDouble(scanner.nextLine());
            if (purchasesList.containsKey(type)) {
                purchasesList.get(type).put(name, price);
            } else {
                LinkedHashMap<String, Double> a = new LinkedHashMap<>();
                a.put(name, price);
                purchasesList.put(type, a);
            }
            balance -= price;
            System.out.println("Purchase was added!");
            System.out.println();
        }
    }

    private static void addIncome() {
        System.out.println("Enter income: ");
        double income = scanner.nextDouble();
        balance += income;
        System.out.println("Income was added!");
    }

    private static void balance() {
        System.out.printf("Balance: $%.2f%n", balance);
    }

    public static String ShowPurchasesMenu() {
        String category = null;
        System.out.println("Choose the type of purchase: ");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                category = "Food";
                break;
            case 2:
                category = "Clothes";
                break;
            case 3:
                category = "Entertainment";
                break;
            case 4:
                category = "Other";
                break;
            case 5:
                category = "All";
                break;
            case 6:
                System.out.println();
                mainMenu();
                break;
            default:
                System.out.println("Invalid operation Try again!!");
                break;
        }
        return category;
    }


    public static String addPurchasesMenu() {
        String category = null;
        System.out.println("Choose the type of purchase: ");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) Back");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                category = "Food";
                break;
            case 2:
                category = "Clothes";
                break;
            case 3:
                category = "Entertainment";
                break;
            case 4:
                category = "Other";
                break;
            case 5:
                System.out.println();
                mainMenu();
                break;
            default:
                System.out.println("Invalid operation Try again!!");
                break;
        }
        return category;
    }

}


