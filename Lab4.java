import java.util.Scanner;

class Account{
    private static int nextAccountNumber = 1;
    private int accountNumber;
    private double accountBalance;
    public Account(){
        accountBalance = 0;
        accountNumber = 10001;
    }
    public Account(double accountBalance){
        this.accountBalance = accountBalance;
        this.accountNumber = nextAccountNumber++;

    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    public void withdrawal(double amount){
        accountBalance-=amount;
    }
    public void deposit(double amount){
        accountBalance+=amount;
    }
}

class Checking extends Account{
    public Checking(){
        super();
    }
    public Checking(double balance){
        setAccountBalance(balance);
    }
    @Override
    public void withdrawal(double amount) {
        double newBalance = getAccountBalance() - amount;
        if (getAccountBalance()<=0){
            System.out.println("Charging an overdraft fee of $20 because account is below $0.");
            setAccountBalance(newBalance-20);
        } else {
            setAccountBalance(newBalance);
        }
    }
}

class Saving extends Account{
    private int depositCount;

    public Saving(){
        super();

    }
    public Saving(double balance){
        setAccountBalance(balance);

    }

    @Override
    public void withdrawal(double amount) {
        double newBalance = getAccountBalance() - amount;
        if (getAccountBalance()<500){
            System.out.println("Charging a fee of $10 because you are below $500.");
            setAccountBalance(newBalance-10);
        } setAccountBalance(newBalance);
    }
    @Override
    public void deposit(double amount) {
        depositCount++;
        double newBalance = getAccountBalance() + amount;
        if (depositCount > 5){
            System.out.println("Charging a fee of $10.");
            setAccountBalance(newBalance - 10);
        } setAccountBalance(newBalance);
    }
    public void interest(){
        double newBalance = getAccountBalance() * 0.015;
        System.out.println("Customer earned "+ newBalance + " in interest.");
        setAccountBalance(getAccountBalance() + newBalance);
    }
}
public class Lab4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Checking check = new Checking();
        Saving saving = new Saving();

        boolean quit = false;

        do {
        System.out.println("1. Withdraw from Checking\n" +
                "2. Withdraw from Savings\n" +
                "3. Deposit to Checking\n" +
                "4. Deposit to Savings\n" +
                "5. Balance of Checking\n" +
                "6. Balance of Savings\n" +
                "7. Award Interest to Savings now\n" +
                "8. Quit");
        String choice = sc.next();

        switch (choice) {
            case "1":
                System.out.println("How much would you like to withdraw from Checking?");
                int caseOneChoice = Integer.parseInt(sc.next());
                check.withdrawal(caseOneChoice);
                break;
            case "2":
                System.out.println("How much would you like to withdraw from Saving?");
                int caseTwoChoice = Integer.parseInt(sc.next());
                saving.withdrawal(caseTwoChoice);
                break;
            case "3":
                System.out.println("How much would you like to deposit to Checking?");
                int caseThreeChoice = Integer.parseInt(sc.next());
                check.deposit(caseThreeChoice);
                break;
            case "4":
                System.out.println("How much would you like to deposit to Saving?");
                int caseFourChoice = Integer.parseInt(sc.next());
                saving.deposit(caseFourChoice);
                break;
            case "5":
                System.out.println("Your balance for checking " + check.getAccountNumber() + " is " + check.getAccountBalance());
                break;
            case "6":
                System.out.println("Your balance for saving " + saving.getAccountNumber() + " is " + saving.getAccountBalance());
                break;
            case "7":
                saving.interest();
                break;
            case "8":
                quit = true;
                break;
            default: System.out.println("Invalid option selected!");
        }
        }while (!quit);
    }
}

