package Utils.Adapters;

import Utils.DataStructures.Icon;
import Utils.DataStructures.IconDB;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
    private List<Icon> data;
    private Context context;
    private LayoutInflater inflater;
    private IconsFragment fragment;

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
        final Icon data = this.data.get(position);
        holder.title.setText(data.getTitle());
        holder.cost.setText(data.getCost());
        holder.icon.setImageResource(data.getPicId());

        Bundle b = new Bundle();
        b.putInt("icon", data.getPicId());
        b.putString("type", data.getState().toString());
        b.putString("title", data.getTitle());
        b.putString("cost", data.getCost());

        holder.view.setOnClickListener(fragment.getListener(data.getState(), b));
        if(data.getState() != Icon.STATE.ADD){
            holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    IconDB.getDB().deleteIconById(data.getId());
                    getAdapter().notifyDataSetChanged();
                    Snackbar bar = Snackbar.make(v, "Deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    IconDB.getDB().restoreLastDeleted();
                                    getAdapter().notifyDataSetChanged();
                                }
                            });
                    bar.show();
                    return true;
                }
            });
        }
    }

    private IconAdapter getAdapter(){
        return this;
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
