package Utils.DataStructures;

import com.example.user.budgetapp.R;

import java.util.UUID;

public class Icon {
    public enum STATE {
        FOOD, CLOTHES, TRANSPORT, OTHER, ADD
    }

    private String title, cost;
    private STATE picId;

    private UUID id;

    Icon(String title, String cost, STATE picId) {
        this.title = title;
        this.cost = cost;
        this.picId = picId;
        id = UUID.randomUUID();
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

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getPicId() {
        int id = 0;
        switch (picId) {
            case ADD: {
                id = R.drawable.plus;
                break;
            }
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
