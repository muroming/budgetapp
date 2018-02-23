package layout;


import Utils.DataStructures.CreditCard;
import Utils.DataStructures.CreditCardDB;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.user.budgetapp.CreditCardActivity;
import com.example.user.budgetapp.R;

public class TotalInfoFragment extends Fragment {

    TextView moneyOnCard, moneySpend;

    public TotalInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_total_info, container, false);
        moneyOnCard = v.findViewById(R.id.total_info_current_money);
        moneySpend = v.findViewById(R.id.total_info_money_spend);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreditCardActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        moneySpend.setText(String.valueOf(CreditCardDB.getDatabase().getMoneySpend()));
        CreditCard card = CreditCardDB.getDatabase().getCurrentCard();
        if (card != null) {
            moneyOnCard.setText(card.getMoneyString());
        } else {
            moneyOnCard.setText(getString(R.string.card_selection_warning));
        }
    }
}
