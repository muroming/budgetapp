package layout;


import Utils.Adapters.CreditCardAdapter;
import Utils.DataStructures.CreditCard;
import Utils.DataStructures.CreditCardDB;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.example.user.budgetapp.CreateCardActivity;
import com.example.user.budgetapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreditCardFragment extends Fragment {
    private CreditCardAdapter adapter;
    private View currentCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_credit_card, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.credit_card_recycler);
        Button addCard = v.findViewById(R.id.card_add_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        init_adapter();
        recyclerView.setAdapter(adapter);

        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateCardActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void init_adapter() {
        adapter = new CreditCardAdapter(this);
        CreditCardDB.getDatabase().setContext(getContext());
        ArrayList<CreditCard> cards = new ArrayList<>();
        cards.add(new CreditCard(CreditCard.Type.Visa, "VisaCard", 123));
        cards.add(new CreditCard(CreditCard.Type.AmericanExpress, "AmericanCard", 123456));
        adapter.setCards(cards);
        adapter.notifyDataSetChanged();
    }

    public View.OnClickListener getListener(final CreditCard card, final View newCard) {
        if (card != null) {//select card as current
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentCard != null) {
                        currentCard.setBackgroundResource(0);
                    }
                    currentCard = newCard;
                    currentCard.setBackgroundResource(R.drawable.card_selected);
                    CreditCardDB.getDatabase().setCurrentCard(card);
                    Toast.makeText(getContext(), "Selected", Toast.LENGTH_SHORT).show();
                }
            };
        } else {//add money to the card
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                }
            };
        }
    }
}
