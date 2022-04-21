package budget;

import java.util.*;

public class Analyze {
    static Scanner scanner = new Scanner(System.in);
    LinkedHashMap<String, LinkedHashMap<String, Double>> purchasesList;


    public Analyze(LinkedHashMap<String, LinkedHashMap<String, Double>> purchasesList) {
        this.purchasesList = purchasesList;
    }

    public double sumType(String type) {
        double sum = 0;
        if (purchasesList.containsKey(type)) {
            for (Map.Entry<String, Double> purchase : purchasesList.get(type).entrySet()) {
                sum += purchase.getValue();
            }
        }
        return sum;
    }

    public void sortAllPurchases() {
        LinkedHashMap<String, Double> lMap = new LinkedHashMap<>();
        double sum = 0;
        if (purchasesList.isEmpty()) {
            System.out.println("Purchase list is empty!");
        } else {
            System.out.println("All:");
            for (String key : purchasesList.keySet()) {
                for (Map.Entry<String, Double> purchase : purchasesList.get(key).entrySet()) {
                    lMap.put(purchase.getKey(), purchase.getValue());
                    sum += purchase.getValue();
                }
            }

            sortList(lMap);
            System.out.printf("Total sum: $%.2f\n", sum);
            System.out.println();
        }
    }


    public void sortList(LinkedHashMap<String, Double> unSortedMap) {
        LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();
        unSortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        for (var entry : reverseSortedMap.entrySet()) {
            System.out.printf("%s $%.2f\n", entry.getKey(), entry.getValue());
        }
    }


    public void sortByType() {
        System.out.println("Types:");
        System.out.printf("Food - $%.2f\n", sumType("Food"));
        System.out.printf("Entertainment - $%.2f\n", sumType("Entertainment"));
        System.out.printf("Clothes - $%.2f\n", sumType("Clothes"));
        System.out.printf("Other - $%.2f\n", sumType("Other"));
        double total = sumType("Food") + sumType("Entertainment") + sumType("Clothes") + sumType("Other");
        System.out.println("Total sum: $" + total);
    }

    public void sortCertainType() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        var choice = Integer.parseInt(scanner.nextLine());
        System.out.println();
        switch (choice) {
            case 1:
                showCertainType("Food");
                break;
            case 2:
                showCertainType("Clothes");
                break;
            case 3:
                showCertainType("Entertainment");
                break;
            case 4:
                showCertainType("Other");
                break;
            default:
                System.out.println("Invalid operation!");
                break;
        }
    }

    public int menuCertainType() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("3) Other");
        return Integer.parseInt(scanner.nextLine());
    }


    public void showCertainType(String type) {
        LinkedHashMap<String, Double> lMap = new LinkedHashMap<>();
        double sum = 0;
        if (!purchasesList.containsKey(type)) {
            System.out.println("Purchase list is empty!");
        } else {
            System.out.println(type + ":");
            for (Map.Entry<String, Double> purchase : purchasesList.get(type).entrySet()) {
                lMap.put(purchase.getKey(), purchase.getValue());
                sum += purchase.getValue();
            }
            sortList(lMap);
            System.out.printf("Total sum: $%.2f\n", sum);
            System.out.println();
        }
    }
}
