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

        private TextView tvBiraloID;
        private TextView tvBiraltBorID;

        private TextView tvAP5;
        private TextView tvAP4;
        private TextView tvAP3;
        private TextView tvAP2;
        private TextView tvAP1;

        private TextView tvAC10;
        private TextView tvAC8;
        private TextView tvAC6;
        private TextView tvAC4;
        private TextView tvAC2;

        private TextView tvAI8;
        private TextView tvAI7;
        private TextView tvAI6;
        private TextView tvAI4;
        private TextView tvAI2;

        private TextView tvACC6;
        private TextView tvACC5;
        private TextView tvACC4;
        private TextView tvACC3;
        private TextView tvACC2;

        private TextView tvAQ16;
        private TextView tvAQ14;
        private TextView tvAQ12;
        private TextView tvAQ10;
        private TextView tvAQ8;

        private TextView tvFI8;
        private TextView tvFI7;
        private TextView tvFI6;
        private TextView tvFI4;
        private TextView tvFI2;

        private TextView tvFC6;
        private TextView tvFC5;
        private TextView tvFC4;
        private TextView tvFC3;
        private TextView tvFC2;

        private TextView tvFQ22;
        private TextView tvFQ19;
        private TextView tvFQ16;
        private TextView tvFQ13;
        private TextView tvFQ10;

        private TextView tvFL8;
        private TextView tvFL7;
        private TextView tvFL6;
        private TextView tvFL5;
        private TextView tvFL4;

        private TextView tvOI11;
        private TextView tvOI10;
        private TextView tvOI9;
        private TextView tvOI8;
        private TextView tvOI7;


        private TextView tvAllInAll;

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

            tvAQ16 = itemView.findViewById(R.id.AQ16);
            tvAQ14 = itemView.findViewById(R.id.AQ14);
            tvAQ12 = itemView.findViewById(R.id.AQ12);
            tvAQ10 = itemView.findViewById(R.id.AQ10);
            tvAQ8 = itemView.findViewById(R.id.AQ8);

            tvFI8 = itemView.findViewById(R.id.FI8);
            tvFI7 = itemView.findViewById(R.id.FI7);
            tvFI6 = itemView.findViewById(R.id.FI6);
            tvFI4 = itemView.findViewById(R.id.FI4);
            tvFI2 = itemView.findViewById(R.id.FI2);

            tvFC6 = itemView.findViewById(R.id.FCC6);
            tvFC5 = itemView.findViewById(R.id.FCC5);
            tvFC4 = itemView.findViewById(R.id.FCC4);
            tvFC3 = itemView.findViewById(R.id.FCC3);
            tvFC2 = itemView.findViewById(R.id.FCC2);

            tvFQ22 = itemView.findViewById(R.id.FQ22);
            tvFQ19 = itemView.findViewById(R.id.FQ19);
            tvFQ16 = itemView.findViewById(R.id.FQ16);
            tvFQ13 = itemView.findViewById(R.id.FQ13);
            tvFQ10 = itemView.findViewById(R.id.FQ10);

            tvFL8 = itemView.findViewById(R.id.FL8);
            tvFL7 = itemView.findViewById(R.id.FL7);
            tvFL6 = itemView.findViewById(R.id.FL6);
            tvFL5 = itemView.findViewById(R.id.FL5);
            tvFL4 = itemView.findViewById(R.id.FL4);

            tvOI11 = itemView.findViewById(R.id.OI11);
            tvOI10 = itemView.findViewById(R.id.OI10);
            tvOI9 = itemView.findViewById(R.id.OI9);
            tvOI8 = itemView.findViewById(R.id.OI8);
            tvOI7 = itemView.findViewById(R.id.OI7);

            tvAllInAll = itemView.findViewById(R.id.allInAll);
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

        int AllInAll = borbiralat.getMegjelenesTisztasag() + borbiralat.getMegjelentesSzin() + borbiralat.getIllatIntenzitas() +
                borbiralat.getIllatKarakter() + borbiralat.getIllatMinoseg() + borbiralat.getZamatIntenzitas() + borbiralat.getZamatKarakter() +
                borbiralat.getZamatMinoseg() + borbiralat.getZamatHosszusag() + borbiralat.getOsszbenyomas();

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

            if (borbiralat.getIllatMinoseg() == 16) {
                holder.tvAQ16.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatMinoseg() == 14) {
                holder.tvAQ14.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatMinoseg() == 12) {
                holder.tvAQ12.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatMinoseg() == 10) {
                holder.tvAQ10.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getIllatMinoseg() == 8) {
                holder.tvAQ8.setBackgroundResource(R.drawable.scribble_circle);
            }

            if (borbiralat.getZamatIntenzitas() == 8) {
                holder.tvFI8.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatIntenzitas() == 7) {
                holder.tvFI7.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatIntenzitas() == 6) {
                holder.tvFI6.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatIntenzitas() == 4) {
                holder.tvFI4.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatIntenzitas() == 2) {
                holder.tvFI2.setBackgroundResource(R.drawable.scribble_circle);
            }

            if (borbiralat.getZamatKarakter() == 6) {
                holder.tvFC6.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatKarakter() == 5) {
                holder.tvFC5.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatKarakter() == 4) {
                holder.tvFC4.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatKarakter() == 3) {
                holder.tvFC3.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatKarakter() == 2) {
                holder.tvFC2.setBackgroundResource(R.drawable.scribble_circle);
            }

            if (borbiralat.getZamatMinoseg() == 22) {
                holder.tvFQ22.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatMinoseg() == 19) {
                holder.tvFQ19.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatMinoseg() == 16) {
                holder.tvFQ16.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatMinoseg() == 13) {
                holder.tvFQ13.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatMinoseg() == 10) {
                holder.tvFQ10.setBackgroundResource(R.drawable.scribble_circle);
            }

            if (borbiralat.getZamatHosszusag() == 8) {
                holder.tvFL8.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatHosszusag() == 7) {
                holder.tvFL7.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatHosszusag() == 6) {
                holder.tvFL6.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatHosszusag() == 5) {
                holder.tvFL5.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getZamatHosszusag() == 4) {
                holder.tvFL4.setBackgroundResource(R.drawable.scribble_circle);
            }

            if (borbiralat.getOsszbenyomas() == 11) {
                holder.tvOI11.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getOsszbenyomas() == 10) {
                holder.tvOI10.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getOsszbenyomas() == 9) {
                holder.tvOI9.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getOsszbenyomas() == 8) {
                holder.tvOI8.setBackgroundResource(R.drawable.scribble_circle);
            } else if (borbiralat.getOsszbenyomas() == 7) {
                holder.tvOI7.setBackgroundResource(R.drawable.scribble_circle);
            }

    }

    @Override
    public int getItemCount() {
        return borBiralatList.size();
    }


}
