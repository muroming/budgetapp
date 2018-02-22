package Utils.Adapters;

import Utils.DataStructures.CreditCard;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.user.budgetapp.R;

import java.util.ArrayList;
import java.util.List;

public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.ViewHolder>{

    List<CreditCard> cards;
    Context context;
    LayoutInflater inflater;

    public CreditCardAdapter(Context context) {
        this.context = context;
        cards = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CreditCard data = cards.get(position);
        holder.logo.setImageResource(data.getPicId());
        holder.title.setText(data.getTitle());
        holder.money.setText(data.getMoneyString());
        holder.addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
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

        public ViewHolder(View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.card_pic);
            title = itemView.findViewById(R.id.card_title);
            money = itemView.findViewById(R.id.card_money);
            addMoney = itemView.findViewById(R.id.card_add_money);
        }
    }
}
