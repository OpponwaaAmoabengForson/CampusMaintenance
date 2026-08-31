import java.util.Scanner;

public class CampusMaintenance {

    // Global flag to track array sorting state for binary search validation
    private static boolean isSortedByID = false;

    public static void main(String[] args) {
        // Dataset initialization using parallel arrays
        String[] ticketID = {"M006", "M002", "M009", "M001", "M004", "M008", "M003", "M007", "M005"};
        String[] location = {"JQB-19", "CS-Lab", "Balme-Library", "CS-Office", "Sarbah-Hall", "Legon-Hall", "UG-Main-Gate", "N-Block", "Night-Market"};
        int[] priority = {2, 1, 3, 2, 1, 4, 3, 2, 5};
        int[] minutes = {35, 20, 45, 25, 30, 60, 50, 40, 70};

        int n = ticketID.length;
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Campus Maintenance Request Menu ---");
            System.out.println("1. Show original maintenance requests");
            System.out.println("2. Linear search for a ticket ID");
            System.out.println("3. Sort by priority using selection sort");
            System.out.println("4. Sort by ticket ID in ascending order");
            System.out.println("5. Binary search for a ticket ID after ID sorting");
            System.out.println("6. Exhaustive search for two jobs whose minutes sum to a target");
            System.out.println("7. Exit");
            System.out.print("Enter choice (1-7): ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayRequests(ticketID, location, priority, minutes, n);
                    break;
                case 2:
                    System.out.print("Enter Ticket ID to search (e.g., M007): ");
                    String targetLinear = scanner.nextLine().trim();
                    linearSearch(ticketID, location, priority, minutes, n, targetLinear);
                    break;
                case 3:
                    selectionSortByPriority(ticketID, location, priority, minutes, n);
                    System.out.println("Requests successfully sorted by Priority then Ticket ID.");
                    displayRequests(ticketID, location, priority, minutes, n);
                    isSortedByID = false; // Reset binary search precondition state
                    break;
                case 4:
                    selectionSortByID(ticketID, location, priority, minutes, n);
                    System.out.println("Requests successfully sorted by Ticket ID (Ascending).");
                    displayRequests(ticketID, location, priority, minutes, n);
                    isSortedByID = true; // Precondition for binary search satisfied
                    break;
                case 5:
                    if (!isSortedByID) {
                        System.out.println("Error: Binary search is invalid because Ticket IDs are not sorted in ascending order.");
                        System.out.println("Please execute Option 4 first.");
                    } else {
                        System.out.print("Enter Ticket ID for binary search: ");
                        String targetBinary = scanner.nextLine().trim();
                        int foundIndex = binarySearch(ticketID, n, targetBinary);
                        if (foundIndex != -1) {
                            System.out.println("Found: " + ticketID[foundIndex] + " | Location: " + location[foundIndex] 
                                               + " | Priority: " + priority[foundIndex] + " | Minutes: " + minutes[foundIndex]);
                        } else {
                            System.out.println("Ticket ID " + targetBinary + " not found.");
                        }
                    }
                    break;
                case 6:
                    System.out.print("Enter target minutes sum (e.g., 75): ");
                    int targetSum = scanner.nextInt();
                    exhaustivePairSearch(ticketID, minutes, n, targetSum);
                    break;case 7:
                    System.out.println("Exiting Application.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }

    // Helper method to display array contents
    public static void displayRequests(String[] id, String[] loc, int[] prio, int[] min, int n) {
        System.out.printf("\n%-6s | %-8s | %-15s | %-8s | %-7s\n", "Index", "Ticket ID", "Location", "Priority", "Minutes");
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-6d | %-8s | %-15s | %-8d | %-7d\n", i, id[i], loc[i], prio[i], min[i]);
        }
    }

    // Swapping parallel array entries
    private static void swap(String[] id, String[] loc, int[] prio, int[] min, int i, int j) {
        String tempID = id[i]; id[i] = id[j]; id[j] = tempID;
        String tempLoc = loc[i]; loc[i] = loc[j]; loc[j] = tempLoc;
        int tempPrio = prio[i]; prio[i] = prio[j]; prio[j] = tempPrio;
        int tempMin = min[i]; min[i] = min[j]; min[j] = tempMin;
    }

    // Algorithm 5.1: Selection Sort by Priority and Ticket ID
    public static void selectionSortByPriority(String[] id, String[] loc, int[] prio, int[] min, int n) {
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (prio[j] < prio[minIndex]) {
                    minIndex = j;
                } else if (prio[j] == prio[minIndex] && id[j].compareTo(id[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(id, loc, prio, min, i, minIndex);
        }
    }

    // Selection Sort strictly by Ticket ID (Ascending)
    public static void selectionSortByID(String[] id, String[] loc, int[] prio, int[] min, int n) {
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (id[j].compareTo(id[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(id, loc, prio, min, i, minIndex);
        }
    }

    // Linear Search Implementation
    public static void linearSearch(String[] id, String[] loc, int[] prio, int[] min, int n, String target) {
        int comparisons = 0;
        boolean found = false;
        for (int i = 0; i < n; i++) {
            comparisons++;
            if (id[i].equalsIgnoreCase(target)) {
                System.out.println("Found " + loc[i] + " request at index " + i + "; expected comparisons = " + comparisons + ".");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Ticket ID " + target + " not found after " + comparisons + " comparisons.");
        }
    }

    // Algorithm 5.3: Binary Search for Ticket ID
    public static int binarySearch(String[] id, int n, String target) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = id[mid].compareTo(target);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Algorithm 5.2: Exhaustive Pair Search for Workload Matching
    public static void exhaustivePairSearch(String[] id, int[] min, int n, int target) {
        System.out.println("Matching pairs for target duration of " + target + " minutes:");
        boolean pairFound = false;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (min[i] + min[j] == target) {
                    System.out.println("Pair: " + id[i] + " (" + min[i] + " mins) + " + id[j] + " (" + min[j] + " mins)");
                    pairFound = true;
                }
            }}
        if (!pairFound) {
            System.out.println("No matching pairs found for target duration " + target + " minutes.");
        }
    }
}