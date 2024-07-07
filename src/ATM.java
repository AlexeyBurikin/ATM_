import java.util.HashMap;
import java.util.Map;

public class ATM {
    public Map<String, Account> accounts;
    private double atmBalance;

    public ATM(double initialAtmBalance) {
        this.accounts = new HashMap<>();
        this.atmBalance = initialAtmBalance;
    }

    public void addAccount(Account account) {
        accounts.put(account.getCardNumber(), account);
    }

    public boolean authenticate(String cardNumber, String pin) {
        Account account = accounts.get(cardNumber);
        if (account != null && cardNumber.equals(account.getCardNumber())) {
            return true;
        }
        return false;
    }

    public double checkBalance(String cardNumber) {
        Account account = accounts.get(cardNumber);
        if (account != null) {
            return account.getBalance();
        }
        throw new IllegalArgumentException("Account not found.");
    }

    public void deposit(String cardNumber, double amount) {
        Account account = accounts.get(cardNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            throw new IllegalArgumentException("Account not found.");
        }
    }

    public void withdraw(String cardNumber, double amount) {
        Account account = accounts.get(cardNumber);
        if (account != null) {
            if (amount > atmBalance) {
                throw new IllegalArgumentException("ATM has insufficient funds.");
            }
            account.withdraw(amount);
            atmBalance -= amount;
        } else {
            throw new IllegalArgumentException("Account not found.");
        }
    }
}