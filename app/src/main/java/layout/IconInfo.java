package layout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.user.budgetapp.R;

public class IconInfo extends DialogFragment {

    public IconInfo(){}

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.icon_info_layout, null);

        ImageView icon = v.findViewById(R.id.icon_info_pic);
        TextView type = v.findViewById(R.id.icon_info_type), title = v.findViewById(R.id.icon_info_title),
                cost = v.findViewById(R.id.icon_info_cost);

        final Bundle args = getArguments();
        icon.setImageResource(args.getInt("icon"));
        type.setText(args.getString("type"));
        title.setText(args.getString("title"));
        cost.setText(args.getString("cost"));

        return new AlertDialog.Builder(getContext())
                .setView(v)
                .setPositiveButton("Close", null)
                .create();
    }
}
