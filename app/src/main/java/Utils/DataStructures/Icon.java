package Utils.DataStructures;

import com.example.user.budgetapp.R;

public class Icon {
    public enum STATE {
        FOOD, CLOTHES, TRANSPORT, OTHER, ADD
    }

    String title, cost;
    STATE picId;

    public Icon(String title, String cost, STATE picId) {
        this.title = title;
        this.cost = cost;
        this.picId = picId;
    }

    public Icon() {
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