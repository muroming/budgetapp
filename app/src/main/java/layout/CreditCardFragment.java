package layout;


import Utils.Adapters.CreditCardAdapter;
import Utils.DataStructures.CreditCard;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        adapter = new CreditCardAdapter(getContext());
        ArrayList<CreditCard> cards = new ArrayList<>();
        cards.add(new CreditCard(CreditCard.Type.Visa, "VisaCard", 123));
        cards.add(new CreditCard(CreditCard.Type.AmericanExpress, "AmericanCard", 123456));
        adapter.setCards(cards);
        adapter.notifyDataSetChanged();
    }
}
