import java.util.InputMismatchException;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    public Account(int accountNumber, String accountHolderName, double initialDeposit, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Amount deposited successfully. Current Balance: " + balance);
        } else {
            System.out.println(" Deposit amount must be positive!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println(" Withdrawal successful. Current Balance: " + balance);
            } else {
                System.out.println(" Insufficient balance!");
            }
        } else {
            System.out.println(" Withdrawal amount must be positive!");
        }
    }

    public void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
    }

    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println(" Contact details updated successfully.");
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

public class BankingApplication1 {
    private Account[] accounts;
    private int accountCount;
    private Scanner sc;
    private static int nextAccountNumber = 1001; 
    public BankingApplication1(int size) {
        accounts = new Account[size];
        accountCount = 0;
        sc = new Scanner(System.in);
    }

    // Create Account
    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double deposit = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter email address: ");
        String email = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        int accountNumber = nextAccountNumber++;
        accounts[accountCount] = new Account(accountNumber, name, deposit, email, phone);
        accountCount++;
        System.out.println(" Account created successfully with Account Number: " + accountNumber);
    }

    private Account findAccount(int accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNo) {
                return accounts[i];
            }
        }
        return null;
    }

    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amt = sc.nextDouble();
        sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.deposit(amt);
        } else {
            System.out.println(" Account not found!");
        }
    }

    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amt = sc.nextDouble();
        sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.withdraw(amt);
        } else {
            System.out.println(" Account not found!");
        }
    }

    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println(" Account not found!");
        }
    }

    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter new email: ");
            String email = sc.nextLine();
            System.out.print("Enter new phone number: ");
            String phone = sc.nextLine();
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println(" Account not found!");
        }
    }

    public void mainMenu() {
        int choice = 0;
        do {
            try {
                System.out.println("\n===");
                System.out.println("   Banking Application Menu");
                System.out.println("====");
                System.out.println("1. Create a new account");
                System.out.println("2. Deposit money");
                System.out.println("3. Withdraw money");
                System.out.println("4. View account details");
                System.out.println("5. Update contact details");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: createAccount(); break;
                    case 2: performDeposit(); break;
                    case 3: performWithdrawal(); break;
                    case 4: showAccountDetails(); break;
                    case 5: updateContact(); break;
                    case 6: System.out.println(" Exiting... Thank you for using Banking Application!"); break;
                    default: System.out.println(" Invalid choice! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input! Please enter numbers only.");
                sc.nextLine(); 
            }
        } while (choice != 6);

        sc.close(); 
    }

    public static void main(String[] args) {
        BankingApplication1 app = new BankingApplication1(100);
        app.mainMenu();
    }
}
