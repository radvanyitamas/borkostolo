package com.example.olahgabormihaly.borkostolo_hits.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.olahgabormihaly.borkostolo_hits.R;
import com.example.olahgabormihaly.borkostolo_hits.database.model.Borbiralat;

import java.util.List;

public class BorBiralatAdapter extends RecyclerView.Adapter<BorBiralatAdapter.MyViewHolder> {

    Context context;
    private List<Borbiralat> borBiralatList;

    public BorBiralatAdapter(Context context, List<Borbiralat> borbiralatList) {
        this.context = context;
        this.borBiralatList = borbiralatList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvBiraloID;
        public TextView tvBiraltBorID;
        public TextView tvMegjelenesTisztasaga;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvBiraloID = itemView.findViewById(R.id.BorBiralatBiraloID);
            tvBiraltBorID = itemView.findViewById(R.id.BorBiralatBiraloID);
            tvMegjelenesTisztasaga = itemView.findViewById(R.id.megjelenesTisztasaga);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.judgement_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Borbiralat borbiralat = borBiralatList.get(position);

        holder.tvBiraloID.setText(String.valueOf(borbiralat.getBiraloSzemelyID()));
        holder.tvBiraltBorID.setText(String.valueOf(borbiralat.getBiraltBorID()));
        holder.tvMegjelenesTisztasaga.setText(String.valueOf(borbiralat.getMegjelenesTisztasag()));

    }

    @Override
    public int getItemCount() {
        return borBiralatList.size();
    }


}
