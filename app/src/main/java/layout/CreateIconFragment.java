package layout;


import Utils.DataStructures.Icon;
import Utils.DataStructures.IconDB;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.user.budgetapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateIconFragment extends Fragment {

    EditText title, cost;
    TextView selected;
    Button addButton;
    List<ImageView> icons;

    public CreateIconFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Icon icon = new Icon();
        View v = inflater.inflate(R.layout.fragment_create_icon, container, false);
        title = v.findViewById(R.id.create_icon_title);
        cost = v.findViewById(R.id.create_icon_cost);
        addButton = v.findViewById(R.id.create_icon_button);
        icons = new ArrayList<>();
        icons.add((ImageView) v.findViewById(R.id.create_icon_food));
        icons.add((ImageView) v.findViewById(R.id.create_icon_transport));
        icons.add((ImageView) v.findViewById(R.id.create_icon_clothes));
        icons.add((ImageView) v.findViewById(R.id.create_icon_other));
        selected = v.findViewById(R.id.create_icon_selected);
        //Setting Listeners
        for (final ImageView view : icons) {//Icon clicks
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (icons.indexOf(view)) {
                        case 0: {
                            selected.setText("Food");
                            break;
                        }
                        case 1: {
                            selected.setText("Transport");
                            break;
                        }
                        case 2: {
                            selected.setText("Clothes");
                            break;
                        }
                        case 3: {
                            selected.setText("Other");
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
                switch (selected.getText().toString()) {
                    case "Food": {
                        icon.setPicId(Icon.STATE.FOOD);
                        break;
                    }
                    case "Transport": {
                        icon.setPicId(Icon.STATE.TRANSPORT);
                        break;
                    }
                    case "Clothes": {
                        icon.setPicId(Icon.STATE.CLOTHES);
                        break;
                    }
                    case "Other": {
                        icon.setPicId(Icon.STATE.OTHER);
                        break;
                    }
                }
                IconDB.insertIcon(icon);
                getActivity().setResult(0);
                getActivity().finish();
            }
        });

        return v;
    }

}
