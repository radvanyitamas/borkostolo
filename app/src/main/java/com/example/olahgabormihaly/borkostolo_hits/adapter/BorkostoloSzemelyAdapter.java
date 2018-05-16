package com.example.olahgabormihaly.borkostolo_hits.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.olahgabormihaly.borkostolo_hits.R;
import com.example.olahgabormihaly.borkostolo_hits.database.model.BorkostoloSzemely;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BorkostoloSzemelyAdapter extends RecyclerView.Adapter<BorkostoloSzemelyAdapter.MyViewHolder> {

    private Context context;
    private List<BorkostoloSzemely> szemelyekList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView dot;
        public TextView szemIgSzam;
        public TextView vNev;
        public TextView kNev;
        public TextView szulDatum;
        public TextView szakmaisagErteke;
        public TextView timestamp;

        public MyViewHolder(View view) {
            super(view);
            dot = view.findViewById(R.id.dot);
            szemIgSzam = view.findViewById(R.id.szemIgSzam);
            vNev = view.findViewById(R.id.VNev);
            kNev = view.findViewById(R.id.KNev);
            szulDatum = view.findViewById(R.id.szulDatum);
            szakmaisagErteke = view.findViewById(R.id.szakmaisagErteke);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }

    public BorkostoloSzemelyAdapter(Context context, List<BorkostoloSzemely> szemelyekList) {
        this.context = context;
        this.szemelyekList = szemelyekList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BorkostoloSzemelyAdapter.MyViewHolder holder, int position) {
        BorkostoloSzemely borkostoloSzemely = szemelyekList.get(position);


        holder.szemIgSzam.setText(borkostoloSzemely.getSzemIgSzam());
        holder.vNev.setText(borkostoloSzemely.getVezetekNev());
        holder.kNev.setText(borkostoloSzemely.getKeresztNev());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        holder.szulDatum.setText(borkostoloSzemely.getSzuletesiDatum());
        holder.szakmaisagErteke.setText(new DecimalFormat("#.####").format(borkostoloSzemely.getSzakmaisagiErtek()));

        // Formatting and displaying szulDatum
        holder.timestamp.setText(formatDate(borkostoloSzemely.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return szemelyekList.size();
    }


    private String formatDate(long timestamp) {
        SimpleDateFormat fmt = new SimpleDateFormat("MM-d HH:mm");
        return fmt.format(new Date(timestamp));
    }
}
