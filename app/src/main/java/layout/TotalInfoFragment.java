package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.budgetapp.CreditCardActivity;
import com.example.user.budgetapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalInfoFragment extends Fragment {


    public TotalInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_total_info, container, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreditCardActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

}
