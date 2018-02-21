package layout;


import Utils.Adapters.IconAdapter;
import Utils.DataStructures.Icon;
import Utils.DataStructures.IconDB;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.budgetapp.CreateIconActivity;
import com.example.user.budgetapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IconsFragment extends Fragment {

    public static final int CREATE_ICON = 1;
    public static final String SHOW_INFO = "InfoDialog";

    private RecyclerView recyclerView;
    private IconAdapter adapter;

    public IconsFragment() {
        // Required empty public constructor
    }

    public View.OnClickListener getListener(Icon.STATE state, final Bundle bundle){
        if(state == Icon.STATE.ADD){
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), CreateIconActivity.class);
                    startActivityForResult(intent, CREATE_ICON);
                }
            };
        }else{
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IconInfo info = new IconInfo();
                    info.setArguments(bundle);
                    info.show(getFragmentManager(), SHOW_INFO);
                }
            };
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == CREATE_ICON) && resultCode == 0) {
            updateUI();
        }
    }

    void updateUI(){
        adapter.setData(IconDB.getDB().getIcons());
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_icons, container, false);
        recyclerView = v.findViewById(R.id.icons_recyclt_view);
        init_adapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setAdapter(adapter);
        /*SharedPreferences preferences = getContext().getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        preferences.edit().clear().commit();*/
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void init_adapter() {
        adapter = new IconAdapter(this);
        IconDB.getDB().setContext(getContext());
        IconDB.getDB().loadIcons();
        adapter.setData(IconDB.getDB().getIcons());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        IconDB.getDB().saveIcons();
    }
}
