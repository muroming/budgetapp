package Utils.Adapters;

import Utils.DataStructures.CreditCard;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.user.budgetapp.R;
import layout.CreditCardFragment;

import java.util.ArrayList;
import java.util.List;

public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.ViewHolder>{

    private List<CreditCard> cards;
    private Context context;
    private LayoutInflater inflater;
    private CreditCardFragment creditCardFragment;

    public CreditCardAdapter(CreditCardFragment fragment) {
        context = fragment.getContext();
        cards = new ArrayList<>();
        inflater = LayoutInflater.from(context);
        creditCardFragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@Nullable ViewHolder holder, int position) {
        CreditCard data = cards.get(position);
        holder.logo.setImageResource(data.getPicId());
        holder.title.setText(data.getTitle());
        holder.money.setText(data.getMoneyString());
        holder.addMoney.setOnClickListener(creditCardFragment.getListener(null, null));//Listener for plus
        holder.view.setOnClickListener(creditCardFragment.getListener(data, holder.view));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void setCards(List<CreditCard> cards) {
        this.cards = cards;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView logo, addMoney;
        TextView title, money;
        View view;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            logo = itemView.findViewById(R.id.card_pic);
            title = itemView.findViewById(R.id.card_title);
            money = itemView.findViewById(R.id.card_money);
            addMoney = itemView.findViewById(R.id.card_add_money);
        }
    }
}
