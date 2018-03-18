package Utils.DataStructures;

import com.example.user.budgetapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Icon {
    public enum STATE {
        FOOD, CLOTHES, TRANSPORT, OTHER
    }

    private String title, cost;
    private STATE picId;
    private Date date;
    private CreditCard card;

    private UUID id;

    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM 'at' HH:mm");
        return format.format(date);
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Icon() {
        id = UUID.randomUUID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public int getCostInt(){
        return Integer.parseInt(cost.substring(0, cost.length() - 1));
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getPicId() {
        int id = 0;
        switch (picId) {
            case FOOD: {
                id = R.drawable.food;
                break;
            }
            case CLOTHES: {
                id = R.drawable.clothes;
                break;
            }
            case OTHER: {
                id = R.drawable.money;
                break;
            }
            case TRANSPORT: {
                id = R.drawable.car;
            }
        }
        return id;
    }

    public STATE getState() {
        return picId;
    }

    public void setPicId(STATE picId) {
        this.picId = picId;
    }

}
