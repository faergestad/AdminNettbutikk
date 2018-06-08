package no.usn.a151710.hobbyhusetv2;


import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class VareAdapter extends RecyclerView.Adapter<VareAdapter.ViewHolder> {

    private Context context;
    private List<Vare> list;

    public VareAdapter(Context context, List<Vare> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_varer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Vare vare = list.get(position);

        holder.textDesc.setText(vare.getBetegnelse());
        holder.textLager.setText(String.valueOf(vare.getLagerAntall()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textDesc, textLager;
        LinearLayout parent_varer;

        public ViewHolder(View itemView) {
            super(itemView);

            textDesc = itemView.findViewById(R.id.vare_navn);
            textLager = itemView.findViewById(R.id.lager_antall);
            parent_varer = itemView.findViewById(R.id.vare_item_layout);

        }
    }

}