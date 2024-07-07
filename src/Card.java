import java.util.regex.Pattern;

public class Card {
    private String cardNumber;
    private String pin;

    public Card(String cardNumber, String pin) {
        if (!isValidCardNumber(cardNumber) || !isValidPin(pin)) {
            throw new IllegalArgumentException("Invalid card number or PIN format.");
        }
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    private boolean isValidCardNumber(String cardNumber) {
        String regex = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";
        return Pattern.matches(regex, cardNumber);
    }

    private boolean isValidPin(String pin) {
        return pin.length() == 4 && pin.chars().allMatch(Character::isDigit);
    }
}