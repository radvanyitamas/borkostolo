package com.example.olahgabormihaly.borkostolo_hits.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.olahgabormihaly.borkostolo_hits.R;
import com.example.olahgabormihaly.borkostolo_hits.adapter.BorokAdapter;
import com.example.olahgabormihaly.borkostolo_hits.database.DatabaseHelper;
import com.example.olahgabormihaly.borkostolo_hits.database.model.Bor;

import java.util.ArrayList;
import java.util.List;

public class BorokFragment extends Fragment {

    private RecyclerView recyclerViewBorok;
    private FloatingActionButton floatingActionButton;
    private TextView tvNoBor;

    private BorokAdapter borokAdapter;
    private List<Bor> borList = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_borok, container, false);

        recyclerViewBorok = root.findViewById(R.id.recyclerViewBorok);
        floatingActionButton = root.findViewById(R.id.floatingActionButton);
        tvNoBor = root.findViewById(R.id.tvNoBor);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });

        borokAdapter = new BorokAdapter(getContext(),borList);
        recyclerViewBorok.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewBorok.setAdapter(borokAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        refreshList();
    }

    private void refreshList() {
        borList.clear();
        borList.addAll(databaseHelper.getAllBor());

        if(borList.size() == 0) {
            tvNoBor.setVisibility(View.VISIBLE);
        } else {
            tvNoBor.setVisibility(View.GONE);
        }

        borokAdapter.notifyDataSetChanged();
    }

    private void showAddDialog() {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_bor, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("Bor hozzáadása")
                .setView(view)
                .setPositiveButton("Hozzáadás", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etNev = view.findViewById(R.id.etNev);
                        databaseHelper.insertBor(etNev.getText().toString());
                        dialog.dismiss();
                        refreshList();
                    }
                });
        builder.create().show();
    }
}
