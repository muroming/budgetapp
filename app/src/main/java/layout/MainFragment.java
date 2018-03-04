package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.budgetapp.R;

public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    private Fragment iconsFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        iconsFragment = fm.findFragmentById(R.id.icon_container);
        if (iconsFragment == null) {
            iconsFragment = new IconsFragment();
            fm.beginTransaction()
                    .add(R.id.icon_container, iconsFragment)
                    .commit();
        }
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && iconsFragment != null){
            ((IconsFragment) iconsFragment).updateUI();
        }
    }
}
