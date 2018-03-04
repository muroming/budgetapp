package layout;


import Utils.DataStructures.CreditCard;
import Utils.DataStructures.CreditCardDB;
import Utils.DataStructures.Icon;
import Utils.DataStructures.IconDB;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.user.budgetapp.MainActivity;
import com.example.user.budgetapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateIconFragment extends Fragment {

    private EditText cost;
    private Spinner spinner;
    private List<ImageView> icons;
    private ArrayAdapter<String> spinnerAdapter;
    private boolean stateSelected = false;

    public CreateIconFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Icon icon = new Icon();
        View v = inflater.inflate(R.layout.fragment_create_icon, container, false);
        EditText title = v.findViewById(R.id.create_icon_title);
        cost = v.findViewById(R.id.create_icon_cost);
        Button addButton = v.findViewById(R.id.create_icon_button);
        spinner = v.findViewById(R.id.create_icon_spinner);

        icons = new ArrayList<>();
        icons.add((ImageView) v.findViewById(R.id.create_icon_food));
        icons.add((ImageView) v.findViewById(R.id.create_icon_transport));
        icons.add((ImageView) v.findViewById(R.id.create_icon_clothes));
        icons.add((ImageView) v.findViewById(R.id.create_icon_other));

        spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayList<String> cardsTitle = new ArrayList<>();
        cardsTitle.add("Current card");
        for (CreditCard card : CreditCardDB.getDatabase().getCards()) {
            cardsTitle.add(card.getTitle());
        }
        spinnerAdapter.addAll(cardsTitle);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String title = cardsTitle.get(position);
                if (title.equals("Current card")) {
                    icon.setCard(CreditCardDB.getDatabase().getCurrentCard());
                } else {
                    icon.setCard(CreditCardDB.getDatabase().getCardByTitle(title));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Setting Listeners
        for (final ImageView view : icons) {//Icon clicks
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (icons.indexOf(view)) {
                        case 0: {
                            updateIcons("Food");
                            icon.setPicId(Icon.STATE.FOOD);
                            break;
                        }
                        case 1: {
                            updateIcons("Transport");
                            icon.setPicId(Icon.STATE.TRANSPORT);
                            break;
                        }
                        case 2: {
                            updateIcons("Clothes");
                            icon.setPicId(Icon.STATE.CLOTHES);
                            break;
                        }
                        case 3: {
                            updateIcons("Other");
                            icon.setPicId(Icon.STATE.OTHER);
                            break;
                        }
                    }
                }
            });
        }
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                icon.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });//Title change
        cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                icon.setCost(s.toString() + "$");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });//Cost change

        addButton.setOnClickListener(new View.OnClickListener() {//Applying icon
            @Override
            public void onClick(View v) {
                boolean isGood = true;
                if (cost.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "No cost", Toast.LENGTH_SHORT).show();
                    isGood = false;
                }
                if (!stateSelected) {
                    Toast.makeText(getContext(), "No purpose selected", Toast.LENGTH_SHORT).show();
                    isGood = false;
                }
                if (isGood) {//Add icon and spent money
                    if (icon.getCard() != null) {
                        icon.setDate(new Date());
                        IconDB.getDB().insertIcon(icon);
                        CreditCardDB.getDatabase().spendMoney(icon);
                        ((MainActivity) getActivity()).returnToMainPage();
                    } else {
                        Toast.makeText(getContext(), "Wrong card selected", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;
    }


    private void updateIcons(String selected) {
        stateSelected = true;
        if (selected.equals("Food")) {
            icons.get(0).setImageResource(R.drawable.food_selected);
        } else {
            icons.get(0).setImageResource(R.drawable.food);
        }
        if(selected.equals("Transport")) {
            icons.get(1).setImageResource(R.drawable.car_selected);
        } else {
            icons.get(1).setImageResource(R.drawable.car);
        }
        if(selected.equals("Clothes")) {
            icons.get(2).setImageResource(R.drawable.clothes_selected);
        } else {
            icons.get(2).setImageResource(R.drawable.clothes);
        }
        if(selected.equals("Other")) {
            icons.get(3).setImageResource(R.drawable.money_selected);
        } else {
            icons.get(3).setImageResource(R.drawable.money);
        }
    }
}
