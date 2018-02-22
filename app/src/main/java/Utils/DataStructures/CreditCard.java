package Utils.DataStructures;

import com.example.user.budgetapp.R;

import java.util.UUID;

public class CreditCard {
    public enum Type {
        AmericanExpress, Visa, Mir, Mastercard
    }

    private Type cardType;
    private String title;
    private int money;
    private UUID id;

    public CreditCard(Type cardType, String title, int money) {
        this.cardType = cardType;
        this.title = title;
        this.money = money;
        id = UUID.randomUUID();
    }

    public CreditCard() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public Type getCardType() {
        return cardType;
    }

    public int getPicId(){
        switch (cardType){
            case Mastercard:{
                return R.drawable.mastercard;
            }
            case Mir:{
                return R.drawable.mir;
            }
            case Visa:{
                return R.drawable.visa;
            }
            case AmericanExpress:{
                return R.drawable.american_express;
            }
            default:{
                return 0;
            }
        }
    }

    public void setCardType(Type cardType) {
        this.cardType = cardType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMoney() {
        return money;
    }

    public String getMoneyString() {
        if (money >= 100000) {
            return money / 1000 + "k";
        }
        if(money >= 1000000){
            return money / 1000000 + "m";
        }
        return String.valueOf(money);
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
