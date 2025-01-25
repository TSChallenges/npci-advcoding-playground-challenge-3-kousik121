import java.io.*;
import java.util.*;

public class InventoryManager {

    public static void main(String[] args) {
        // Entry point for the program
        int itemCount;
        String itemName;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the operation to be performed: \n 1. Display the inventory \n 2. Add an item to the inventory \n 3. Update an item in the inventory");
        int input = scan.nextInt();
        switch (input) {
            case 1:
                readInventory("inventory.txt");
                break;
            case 2:
                System.out.println("Please enter the item name: ");
                itemName = scan.next();
                System.out.println("Please enter the item count: ");
                itemCount = scan.nextInt();
                addItem("inventory.txt", itemName, itemCount);
                break;
            case 3:
                System.out.println("Please enter the item name: ");
                itemName = scan.next();
                System.out.println("Please enter the item count: ");
                itemCount = scan.nextInt();
                updateItem("inventory.txt", itemName, itemCount);
                break;
            default:
                System.out.println("Please enter a valid input: ");
        }
        scan.close();
    }

    public static void readInventory(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/" + fileName));
            String line;
            while ((line = reader.readLine()) != null) {
              System.out.println(line);
            }
            reader.close();
            } catch (Exception e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
    }

    public static void addItem(String fileName, String itemName, int itemCount) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/" + fileName));
            String line;
            List<String> lines = new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            List<String> items = new ArrayList<String>();
            for(String entry: lines) {
                items.add(entry.split("[,]")[0]);
            }
            if (items.contains(itemName.toLowerCase())) {
                System.out.println("The given item is already present in the inventory. Please try updating it instead of adding.");
            }
            else {
                FileWriter writer = new FileWriter("./src/" + fileName, true);
                writer.write("\n" + itemName.toLowerCase() + "," + String.valueOf(itemCount));
                writer.close();
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateItem(String fileName, String itemName, int itemCount) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/" + fileName));
            String line, item;
            int lineNum = -1;
            List<String> lines = new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            for(int i = 0; i < lines.size(); i++) {
                item = (lines.get(i)).split("[,]")[0];
                if (item.equals(itemName)) {
                    lineNum = i;
                    break;
                }
            }
            if (lineNum != -1) {
                lines.set(lineNum, itemName + "," + String.valueOf(itemCount));
                System.out.println("Update done successfully");
            }
            String content = String.join("\n", lines);
            FileWriter writer = new FileWriter("./src/" + fileName);
            writer.write(content);
            writer.close();
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
