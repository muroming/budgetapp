package layout;


import Utils.Adapters.CreditCardAdapter;
import Utils.DataStructures.CreditCard;
import Utils.DataStructures.CreditCardDB;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import com.example.user.budgetapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreditCardFragment extends Fragment {
    RecyclerView recyclerView;
    CreditCardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_credit_card, container, false);
        recyclerView = v.findViewById(R.id.credit_card_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        init_adapter();
        recyclerView.setAdapter(adapter);
        return v;
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

    public View.OnClickListener getListener(final CreditCard card){
        if(card != null){//select card as current
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreditCardDB.getDatabase().setCurrentCard(card);
                    Toast.makeText(getContext(), "Selected", Toast.LENGTH_SHORT).show();
                }
            };
        }else{//add money to the card
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                }
            };
        }
    }
}
