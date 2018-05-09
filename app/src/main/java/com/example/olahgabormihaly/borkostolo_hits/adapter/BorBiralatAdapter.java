package com.example.olahgabormihaly.borkostolo_hits.adapter;

import android.content.Context;
import android.content.res.Configuration;
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
    boolean orientation;

    public BorBiralatAdapter(Context context, List<Borbiralat> borbiralatList) {
        this.context = context;
        this.borBiralatList = borbiralatList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvBiraloID;
        public TextView tvBiraltBorID;

        public TextView tvAP5;
        public TextView tvAP4;
        public TextView tvAP3;
        public TextView tvAP2;
        public TextView tvAP1;

        public TextView tvAC10;
        public TextView tvAC8;
        public TextView tvAC6;
        public TextView tvAC4;
        public TextView tvAC2;

        public TextView tvAI8;
        public TextView tvAI7;
        public TextView tvAI6;
        public TextView tvAI4;
        public TextView tvAI2;

        public TextView tvACC6;
        public TextView tvACC5;
        public TextView tvACC4;
        public TextView tvACC3;
        public TextView tvACC2;


        public TextView tvAllInAll;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvBiraloID = itemView.findViewById(R.id.BorBiralatBiraloID);
            tvBiraltBorID = itemView.findViewById(R.id.BorBiraltBorID);
            // textView Appearance Purity
            tvAP5 = itemView.findViewById(R.id.AP5);
            tvAP4 = itemView.findViewById(R.id.AP4);
            tvAP3 = itemView.findViewById(R.id.AP3);
            tvAP2 = itemView.findViewById(R.id.AP2);
            tvAP1 = itemView.findViewById(R.id.AP1);
            // textView Appearance Color
            tvAC10 = itemView.findViewById(R.id.AC10);
            tvAC8 = itemView.findViewById(R.id.AC8);
            tvAC6 = itemView.findViewById(R.id.AC6);
            tvAC4 = itemView.findViewById(R.id.AC4);
            tvAC2 = itemView.findViewById(R.id.AC2);
            // textView Aroma intensity
            tvAI8 = itemView.findViewById(R.id.AI8);
            tvAI7 = itemView.findViewById(R.id.AI7);
            tvAI6 = itemView.findViewById(R.id.AI6);
            tvAI4 = itemView.findViewById(R.id.AI4);
            tvAI2 = itemView.findViewById(R.id.AI2);
            // textView Aroma character

            tvACC6 = itemView.findViewById(R.id.ACC6);
            tvACC5 = itemView.findViewById(R.id.ACC5);
            tvACC4 = itemView.findViewById(R.id.ACC4);
            tvACC3 = itemView.findViewById(R.id.ACC3);
            tvACC2 = itemView.findViewById(R.id.ACC2);

            tvAllInAll = itemView.findViewById(R.id.allInAll);
        }
    }

    protected boolean orieantationSet() {
        orientation = false;
        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            orientation = true;
        }

        return orientation;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        orieantationSet();
//        if( orientation == true) {
//            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.judgement_list_row, parent, false);
//            return new MyViewHolder(itemView);
//        } else {
//            Toast.makeText(context, "Please rotate landscape mode to phone!", Toast.LENGTH_LONG).show();
//        }

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.judgement_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Borbiralat borbiralat = borBiralatList.get(position);

        int AllInAll = borbiralat.getMegjelenesTisztasag() + borbiralat.getMegjelentesSzin() + borbiralat.getIllatIntenzitas();

        holder.tvAllInAll.setText(String.valueOf(AllInAll));

        holder.tvBiraloID.setText(String.valueOf(borbiralat.getBiraloSzemelyID()));
        holder.tvBiraltBorID.setText(String.valueOf(borbiralat.getBiraltBorID()));
            if (borbiralat.getMegjelenesTisztasag() == 5) {
                holder.tvAP5.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getMegjelenesTisztasag() == 4) {
                holder.tvAP4.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getMegjelenesTisztasag() == 3) {
                holder.tvAP3.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getMegjelenesTisztasag() == 2) {
                holder.tvAP2.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getMegjelenesTisztasag() == 1) {
                holder.tvAP1.setBackgroundResource(R.drawable.scribble_circle);
            }

            if (borbiralat.getMegjelentesSzin() == 10) {
                holder.tvAC10.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getMegjelentesSzin() == 8) {
                holder.tvAC8.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getMegjelentesSzin() == 6) {
                holder.tvAC6.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getMegjelentesSzin() == 4) {
                holder.tvAC4.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getMegjelentesSzin() == 2) {
                holder.tvAC2.setBackgroundResource(R.drawable.scribble_circle);
            }

            if (borbiralat.getIllatIntenzitas() == 8) {
                holder.tvAI8.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatIntenzitas() == 7) {
                holder.tvAI7.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatIntenzitas() == 6) {
                holder.tvAI6.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatIntenzitas() == 4) {
                holder.tvAI4.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatIntenzitas() == 2) {
                holder.tvAI2.setBackgroundResource(R.drawable.scribble_circle);
            }

            if (borbiralat.getIllatKarakter() == 6) {
                holder.tvACC6.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatKarakter() == 5) {
                holder.tvACC5.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatKarakter() == 4) {
                holder.tvACC4.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatKarakter() == 3) {
                holder.tvACC3.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatKarakter() == 2) {
                holder.tvACC2.setBackgroundResource(R.drawable.scribble_circle);
            }

    }

    @Override
    public int getItemCount() {
        return borBiralatList.size();
    }


}
