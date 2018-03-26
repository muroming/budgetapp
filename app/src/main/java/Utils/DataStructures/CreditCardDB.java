package Utils.DataStructures;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.user.budgetapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class CreditCardDB {
    private List<CreditCard> cards;
    private CreditCard currentCard;
    private Context context;
    private int moneySpend;

    private static CreditCardDB database;

    private CreditCardDB() {
        cards = new ArrayList<>();
        currentCard = null;
        moneySpend = 0;
    }

    public static CreditCardDB getDatabase() {
        if (database == null) {
            database = new CreditCardDB();
        }
        return database;
    }

    public int getMoneySpend() {
        return moneySpend;
    }

    public void setMoneySpend(int moneySpend) {
        this.moneySpend = moneySpend;
    }

    public void spendMoney(int amount) {
        currentCard.setMoney(currentCard.getMoney() - amount);
        moneySpend += amount;
        saveCards();
    }

    public void spendMoney(Icon icon) {
        icon.getCard().setMoney(icon.getCard().getMoney() - icon.getCostInt());
        moneySpend += icon.getCostInt();
        saveCards();
    }

    public void deleteTransaction(Icon icon) {
        icon.getCard().setMoney(icon.getCard().getMoney() + icon.getCostInt());
        moneySpend -= icon.getCostInt();
        saveCards();
    }

    public void addMoney(int amount) {
        currentCard.setMoney(currentCard.getMoney() + amount);
        saveCards();
    }

    public CreditCardDB setContext(Context context) {
        this.context = context;
        return database;
    }

    public List<CreditCard> getCards() {
        return cards;
    }

    public void setCards(List<CreditCard> cards) {
        this.cards = cards;
    }

    public CreditCard getCurrentCard() {
        return currentCard;
    }

    public void addCard(CreditCard card) {
        cards.add(card);
        saveCards();
    }

    public void setCurrentCard(CreditCard currentCard) {
        this.currentCard = currentCard;
    }

    public void loadCards() {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json_cards = preferences.getString("cards", "");
        String json_current = preferences.getString("current", "");
        if (json_cards.equals("")) {
            ArrayList<CreditCard> data = new ArrayList<>();
            setCards(data);
        } else {
            ArrayList<CreditCard> data = gson.fromJson(json_cards, new TypeToken<ArrayList<CreditCard>>() {
            }.getType());
            setCards(data);
        }
        if (json_current.equals("")) {
            currentCard = null;
        } else {
            currentCard = gson.fromJson(json_current, new TypeToken<CreditCard>() {
            }.getType());
        }
    }

    private void saveCards() {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json_cards = gson.toJson(cards);
        String json_currnet = gson.toJson(currentCard);
        editor.putString("cards", json_cards);
        editor.putString("current", json_currnet);
        editor.apply();
    }

    public CreditCard getCardByTitle(String title) {
        for (CreditCard card : cards) {
            if (card.getTitle().equals(title)) {
                return card;
            }
        }
        return null;
    }
}
