package Utils.DataStructures;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.user.budgetapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IconDB {
    private static IconDB iconDB;

    private IconDB() {
    }

    public static IconDB getDB() {
        if (iconDB == null) {
            iconDB = new IconDB();
        }
        return iconDB;
    }

    private List<Icon> icons;
    private Context context;

    private Icon lastDeleted;
    private int indLastDeleted;

    public List<Icon> getIcons() {
        return icons;
    }

    public void setContext(Context c) {
        context = c;
    }

    private void setIcons(List<Icon> icons) {
        iconDB.icons = icons;
    }

    public void insertIcon(Icon icon) {
        icons.add(icons.size(), icon);
        saveIcons();
    }

    public void deleteIconById(UUID id) {
        for (Icon icon : icons) {
            if (icon.getId().equals(id)) {
                lastDeleted = icon;
                indLastDeleted = icons.indexOf(icon);
                icons.remove(icon);
                break;
            }
        }
        saveIcons();
    }

    public Icon getIconById(UUID id){
        for (Icon icon : icons) {
            if (icon.getId().equals(id)) {
                return icon;
            }
        }
        return null;
    }

    public void loadIcons() {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("icons", "");
        if (json.equals("")) {
            ArrayList<Icon> data = new ArrayList<>();
            setIcons(data);
        } else {
            ArrayList<Icon> data = gson.fromJson(json, new TypeToken<ArrayList<Icon>>() {
            }.getType());
            setIcons(data);
        }
    }

    public void saveIcons() {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(getIcons());
        editor.putString("icons", json);
        editor.apply();
    }

    public void restoreLastDeleted() {
        icons.add(indLastDeleted, lastDeleted);
        saveIcons();
    }
}
