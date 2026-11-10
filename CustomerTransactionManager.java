import java.util.Scanner;

public class CustomerTransactionManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Task 1: Dynamic Data Input via Scanner ---
        System.out.print("Enter number of customer transactions to process: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Clear input buffer

        // --- Task 2: Array Implementations ---
        String[] transactionIds = new String[count];
        double[] transactionAmounts = new double[count];
        double[][] itemDetails = new double[count][3]; // [Customer][Item Category 1-3]

        // Populate arrays interactively
        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Entering Data for Customer " + (i + 1) + " ---");
            System.out.print("Enter Transaction ID: ");
            transactionIds[i] = scanner.nextLine();

            System.out.print("Enter Total Purchase Amount ($): ");
            transactionAmounts[i] = scanner.nextDouble();

            System.out.println("Enter breakdown for 3 item categories:");
            for (int j = 0; j < 3; j++) {
                System.out.print("  Category " + (j + 1) + " Amount: $");
                itemDetails[i][j] = scanner.nextDouble();
            }
            
            scanner.nextLine(); // Clear input buffer
        }

        // Display Raw Data Summary Table using Nested Loops (Task 2c)
        displayTransactionTable(transactionIds, transactionAmounts, itemDetails, "RAW TRANSACTION DATA");

        // --- Task 3: Sorting Algorithm Implementation (Insertion Sort) ---
        System.out.print("\nWould you like to sort by amount in Ascending (ASC) or Descending (DESC)? ");
        String direction = scanner.next().trim().toUpperCase();

        insertionSort(transactionAmounts, transactionIds, itemDetails, direction);

        // Display Sorted Results
        displayTransactionTable(transactionIds, transactionAmounts, itemDetails, "SORTED TRANSACTION DATA (" + direction + ")");

        scanner.close();
    }

    // Helper method to display tables (Task 2c)
    public static void displayTransactionTable(String[] ids, double[] amounts, double[][] details, String title) {
        System.out.println("\n========================================================");
        System.out.println("            " + title);
        System.out.println("========================================================");
        System.out.printf("%-15s %-15s %-25s\n", "Txn ID", "Total ($)", "Item Breakdown [1, 2, 3]");
        System.out.println("--------------------------------------------------------");
        
        for (int i = 0; i < ids.length; i++) {
            System.out.printf("%-15s $%-14.2f [%.2f, %.2f, %.2f]\n", 
                ids[i], 
                amounts[i], 
                details[i][0], details[i][1], details[i][2]);
        }
        System.out.println("========================================================");
    }

    // --- Task 3: Insertion Sort Method with Parallel Array Tracking ---
    public static void insertionSort(double[] amounts, String[] ids, double[][] details, String order) {
        int n = amounts.length;

        for (int i = 1; i < n; i++) {
            double keyAmount = amounts[i];
            String keyId = ids[i];
            double[] keyRow = details[i];
            int j = i - 1;

            // Determine condition based on sorting direction
            boolean condition;
            do {
                if (order.equals("DESC")) {
                    condition = (j >= 0 && amounts[j] < keyAmount);
                } else {
                    condition = (j >= 0 && amounts[j] > keyAmount);
                }

                if (condition) {
                    amounts[j + 1] = amounts[j];
                    ids[j + 1] = ids[j];
                    details[j + 1] = details[j];
                    j--;
                }
            } while (condition);

            // Insert key into correct position
            amounts[j + 1] = keyAmount;
            ids[j + 1] = keyId;
            details[j + 1] = keyRow;
        }
    }
}


