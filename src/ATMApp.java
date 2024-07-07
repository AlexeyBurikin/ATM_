import java.io.*;
import java.util.Scanner;

public class ATMApp {
    private static final String DATA_FILE = "atm_data.txt";

    public static void main(String[] args) {
        ATM atm = new ATM(10000.0);  // Начальный баланс банкомата

        // Загрузка данных из файла
        loadATMData(atm);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.println("Enter your card number (XXXX-XXXX-XXXX-XXXX): ");
            String cardNumber = scanner.nextLine();

            System.out.println("Enter your PIN: ");
            String pin = scanner.nextLine();

            if (atm.authenticate(cardNumber, pin)) {
                while (true) {
                    System.out.println("1. Check balance");
                    System.out.println("2. Deposit funds");
                    System.out.println("3. Withdraw funds");
                    System.out.println("4. Exit");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();

                    try {
                        switch (choice) {
                            case 1:
                                System.out.println("Your balance: " + atm.checkBalance(cardNumber));
                                break;
                            case 2:
                                System.out.print("Enter amount to deposit: ");
                                double depositAmount = scanner.nextDouble();
                                atm.deposit(cardNumber, depositAmount);
                                System.out.println("Deposit successful.");
                                break;
                            case 3:
                                System.out.print("Enter amount to withdraw: ");
                                double withdrawAmount = scanner.nextDouble();
                                atm.withdraw(cardNumber, withdrawAmount);
                                System.out.println("Withdrawal successful.");
                                break;
                            case 4:
                                saveATMData(atm);
                                System.out.println("Thank you for using the ATM. Goodbye!");
                                return;
                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            } else {
                System.out.println("Invalid card number or PIN. Please try again.");
            }
        }
    }

    private static void loadATMData(ATM atm) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String cardNumber = data[0];
                double balance = Double.parseDouble(data[1]);
                atm.addAccount(new Account(cardNumber, balance));
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private static void saveATMData(ATM atm) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Account account : atm.accounts.values()) {
                writer.println(account.getCardNumber() + " " + account.getBalance());
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
