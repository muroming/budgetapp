package layout;


import Utils.Adapters.IconAdapter;
import Utils.DataStructures.Icon;
import Utils.DataStructures.IconDB;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.user.budgetapp.CreateIconActivity;
import com.example.user.budgetapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class IconsFragment extends Fragment {

    public static final int CREATE_ICON = 1;

    RecyclerView recyclerView;
    IconAdapter adapter;

    public IconsFragment() {
        // Required empty public constructor
    }

    public View.OnClickListener getListener(Icon.STATE state){
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
                    Toast.makeText(getContext(), "Clicked!", Toast.LENGTH_SHORT).show();
                }
            };
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CREATE_ICON && resultCode == 0) {
            adapter.setData(IconDB.getIcons());
            adapter.notifyDataSetChanged();
        }
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

    private void init_adapter() {
        adapter = new IconAdapter(this);
        SharedPreferences preferences = getContext().getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("icons", "");
        if(json.equals("")){
            ArrayList<Icon> data = new ArrayList<>();
            data.add(new Icon("","", Icon.STATE.ADD));
            IconDB.setIcons(data);
        }else{
            ArrayList<Icon> data = gson.fromJson(json, new TypeToken<ArrayList<Icon>>(){}.getType());
            IconDB.setIcons(data);
        }
        adapter.setData(IconDB.getIcons());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences preferences = getContext().getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(IconDB.getIcons());
        editor.putString("icons", json);
        editor.apply();
    }
}
