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


    public static final String Icon = "icon";
    public static final String Type = "type";
    public static final String Title = "title";
    public static final String Cost = "cost";
    public static final String Date = "date";
    public static final String CardTitle = "cardTitle";
    public static final String CardIcon = "cardIcon";

    public IconInfo(){}

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.icon_info_layout, null);

        ImageView icon = v.findViewById(R.id.icon_info_pic), cardIcon = v.findViewById(R.id.icon_info_card_pic);
        TextView type = v.findViewById(R.id.icon_info_type), title = v.findViewById(R.id.icon_info_title),
                cost = v.findViewById(R.id.icon_info_cost), date = v.findViewById(R.id.icon_info_date),
                cardTitle = v.findViewById(R.id.icon_info_card_title);

        final Bundle args = getArguments();
        icon.setImageResource(args.getInt(Icon));
        type.setText(args.getString(Type));
        title.setText(args.getString(Title));
        cost.setText(args.getString(Cost));
        date.setText("Created: " + args.getString(Date));
        cardIcon.setImageResource(args.getInt(CardIcon));
        cardTitle.setText(args.getString(CardTitle));

        return new AlertDialog.Builder(getContext())
                .setView(v)
                .setPositiveButton("Close", null)
                .create();
    }
}
