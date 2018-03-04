package layout;


import Utils.Adapters.IconAdapter;
import Utils.DataStructures.CreditCard;
import Utils.DataStructures.CreditCardDB;
import Utils.DataStructures.Icon;
import Utils.DataStructures.IconDB;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.user.budgetapp.R;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class IconsFragment extends Fragment {

    public static final String SHOW_INFO = "InfoDialog";

    private IconAdapter adapter;
    private TextView moneyOnCard, moneySpend;

    public IconsFragment() {
        // Required empty public constructor
    }

    public View.OnClickListener getListener(final Bundle bundle){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IconInfo info = new IconInfo();
                info.setArguments(bundle);
                info.show(getFragmentManager(), SHOW_INFO);
            }
        };
    }


    public View.OnLongClickListener getListener(final UUID id, final int cost, final RecyclerView.Adapter adapter){
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new ConfirmDialog(id, v, cost, adapter).getDialog(getContext()).show();
                return true;
            }
        };
    }

    void updateUI(){
        //Load icons
        adapter.setData(IconDB.getDB().getIcons());
        adapter.notifyDataSetChanged();

        //Update stats
        moneySpend.setText(String.valueOf(CreditCardDB.getDatabase().getMoneySpend()));
        CreditCard card = CreditCardDB.getDatabase().getCurrentCard();
        if (card != null) {
            moneyOnCard.setText(card.getMoneyString());
        } else {
            moneyOnCard.setText(getString(R.string.card_selection_warning));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_icons, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.icons_recyclt_view);
        init_adapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setAdapter(adapter);
        moneyOnCard = v.findViewById(R.id.total_info_current_money);
        moneySpend = v.findViewById(R.id.total_info_money_spend);
        updateUI();
        /*SharedPreferences preferences = getContext().getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        preferences.edit().clear().commit();*/
        return v;
    }

    private void init_adapter() {
        adapter = new IconAdapter(this);
        IconDB.getDB().setContext(getContext());
        IconDB.getDB().loadIcons();
        adapter.setData(IconDB.getDB().getIcons());
        adapter.notifyDataSetChanged();
    }

    class ConfirmDialog{
        UUID id;
        View v;
        RecyclerView.Adapter adapter;

        void deleteIcon(final boolean deleteMoney){
            if(deleteMoney){
                Icon icon = IconDB.getDB().getIconById(id);
                CreditCardDB.getDatabase().deleteTransaction(icon);
                updateUI();
            }
            IconDB.getDB().deleteIconById(id);
            adapter.notifyDataSetChanged();
            Snackbar bar = Snackbar.make(v, "Deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            IconDB.getDB().restoreLastDeleted();
                            if(deleteMoney){
                                Icon icon = IconDB.getDB().getIconById(id);
                                CreditCardDB.getDatabase().spendMoney(icon);
                                updateUI();
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });
            bar.show();
        }

        AlertDialog getDialog(Context context){
            return new AlertDialog.Builder(context)
                    .setTitle("Delete icon or transaction?")
                    .setMessage(context.getString(R.string.icon_delete_text))
                    .setPositiveButton("Transaction", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteIcon(true);
                        }
                    })
                    .setNegativeButton("Icon", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteIcon(false);
                        }
                    }).create();
        }

        ConfirmDialog(UUID id, View v, int money, RecyclerView.Adapter adapter) {
            this.id = id;
            this.v = v;
            this.adapter = adapter;
        }
    }
}
