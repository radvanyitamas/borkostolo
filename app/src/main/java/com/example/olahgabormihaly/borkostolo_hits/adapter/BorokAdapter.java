package com.example.olahgabormihaly.borkostolo_hits.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.olahgabormihaly.borkostolo_hits.R;
import com.example.olahgabormihaly.borkostolo_hits.database.model.Bor;

import java.util.List;

public class BorokAdapter extends RecyclerView.Adapter<BorokAdapter.MyViewHolder> {

    private Context context;
    private List<Bor> borList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvGyarto;
        public TextView tvNeve;
        public TextView tvFajta;
        public TextView tvSzin;
        public TextView tvEvjarat;
        public TextView tvAlkohol;
        public TextView tvFogyasztasiHomerseklet;
        public TextView tvAtlagPontszam;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvGyarto = itemView.findViewById(R.id.BorGyarto);
            tvNeve = itemView.findViewById(R.id.BorNeve);
            tvFajta = itemView.findViewById(R.id.BorFajta);
            tvSzin = itemView.findViewById(R.id.BorSzin);
            tvEvjarat = itemView.findViewById(R.id.BorEvjarat);
            tvAlkohol = itemView.findViewById(R.id.BorAlkoholTartam);
            tvFogyasztasiHomerseklet = itemView.findViewById(R.id.BorFogyasztasiHomerseklet);
            tvAtlagPontszam = itemView.findViewById(R.id.BorAtlagPontszam);
        }
    }

    public BorokAdapter(Context context, List<Bor> borList) {
        this.context = context;
        this.borList = borList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wine_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bor bor = borList.get(position);

        holder.tvGyarto.setText(bor.getBorGyarto());
        holder.tvNeve.setText(bor.getBorNeve());
        holder.tvFajta.setText(bor.getBorFajta());
        holder.tvSzin.setText(bor.getBorSzin());
        holder.tvEvjarat.setText(String.valueOf(bor.getBorEvjarat()));
        holder.tvAlkohol.setText("Alc. " +String.valueOf(bor.getBorAlkohol_tartam())  + "% Vol");
        holder.tvFogyasztasiHomerseklet.setText(String.valueOf(bor.getBorFogyasztasiHomerseklet()) + " C°");

    }

    @Override
    public int getItemCount() {
        return borList.size();
    }
}
