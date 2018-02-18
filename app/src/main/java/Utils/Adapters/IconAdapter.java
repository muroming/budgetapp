package Utils.Adapters;

import Utils.DataStructures.Icon;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.user.budgetapp.R;
import layout.IconsFragment;

import java.util.List;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {
    List<Icon> data;
    Context context;
    LayoutInflater inflater;
    IconsFragment fragment;

    public IconAdapter(IconsFragment fragment) {
        context = fragment.getContext();
        this.fragment = fragment;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public IconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.icon_layout, parent, false);
        return new IconAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Icon data = this.data.get(position);
        holder.title.setText(data.getTitle());
        holder.cost.setText(data.getCost());
        holder.icon.setImageResource(data.getPicId());
        holder.view.setOnClickListener(fragment.getListener(data.getState()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Icon> data) {
        this.data = data;
    }

    public List<Icon> getData() {
        return data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title, cost;
        View view;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            icon = itemView.findViewById(R.id.icon_pic);
            title = itemView.findViewById(R.id.icon_title);
            cost = itemView.findViewById(R.id.icon_cost);
        }
    }
}
