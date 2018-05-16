package com.example.olahgabormihaly.borkostolo_hits.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olahgabormihaly.borkostolo_hits.R;
import com.example.olahgabormihaly.borkostolo_hits.adapter.BorokAdapter;
import com.example.olahgabormihaly.borkostolo_hits.database.DatabaseHelper;
import com.example.olahgabormihaly.borkostolo_hits.database.model.Bor;
import com.example.olahgabormihaly.borkostolo_hits.utils.RecyclerTouchListener;

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
                showAddBorokDialog(false, -1);
            }
        });

        borokAdapter = new BorokAdapter(getContext(),borList);
        recyclerViewBorok.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewBorok.setAdapter(borokAdapter);
        recyclerViewBorok.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                recyclerViewBorok, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));
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

    private void showAddBorokDialog(final boolean shouldUpdate, final int position) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_bor, null);
        final EditText inputBorGyarto = view.findViewById(R.id.dialog_etBorGyarto);
        final EditText inputBorNeve = view.findViewById(R.id.dialog_etBorNev);
        final EditText inputBorFajta = view.findViewById(R.id.dialog_etBorFajta);
        final EditText inputBorSzin = view.findViewById(R.id.dialog_etBorSzin);
        final EditText inputBorEvjarat = view.findViewById(R.id.dialog_etBorEv);
        final EditText inputBorAlkhololTartam = view.findViewById(R.id.dialog_etBorAlkoholTartam);
        final EditText inputBorFogyasztasiHomerseklet = view.findViewById(R.id.dialog_etBorFogyasztasiHomerseklet);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("Borok hozzáadása")
                .setView(view)
                .setPositiveButton("Hozzáadás", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(inputBorGyarto.getText().toString())) {
                            Toast.makeText(getContext(), "Enter Producer of the wine!", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(inputBorNeve.getText().toString())) {
                            Toast.makeText(getContext(), "Enter name of the wine!", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(inputBorFajta.getText().toString())) {
                            Toast.makeText(getContext(), "Enter type of the wine!", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(inputBorSzin.getText().toString())) {
                            Toast.makeText(getContext(), "Enter color of the wine!", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(inputBorEvjarat.getText().toString())) {
                            Toast.makeText(getContext(), "Enter vintage of the wine!", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(inputBorAlkhololTartam.getText().toString())) {
                            Toast.makeText(getContext(), "Enter the alcohol content of the wine!", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(inputBorFogyasztasiHomerseklet.getText().toString())) {
                            Toast.makeText(getContext(), "Enter the consumption temperature of the wine!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            dialog.dismiss();
                        }

                        // check if user updating borkostoloSzemely
                        if (shouldUpdate && borList.get(position) != null) {
                            // update borkostoloSzemely by it's id
                            updateBor(inputBorGyarto.getText().toString(), inputBorNeve.getText().toString(), inputBorFajta.getText().toString(), inputBorSzin.getText().toString(), Integer.parseInt(inputBorEvjarat.getText().toString()), Float.parseFloat(inputBorAlkhololTartam.getText().toString()), Integer.parseInt(inputBorFogyasztasiHomerseklet.getText().toString()), position);
                        } else {
                            // create new borkostoloSzemely
                            createBor(inputBorGyarto.getText().toString(), inputBorNeve.getText().toString(), inputBorFajta.getText().toString(), inputBorSzin.getText().toString(), Integer.parseInt(inputBorEvjarat.getText().toString()), Float.parseFloat(inputBorAlkhololTartam.getText().toString()), Integer.parseInt(inputBorFogyasztasiHomerseklet.getText().toString()));
                        }
                        refreshList();
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    /**
     * Opens dialog with Edit - Delete options
     * Edit - 0
     * Delete - 0
     */
    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showAddBorokDialog(true, position);
                } else {
                    deleteBor(position);
                }
            }
        });
        builder.show();
    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */

    private void createBor(String borGyarto, String borNeve, String borFajta, String borSzin, int borEvjarat, float borAlkholotartam, int borFogyasztasiHom) {
        // inserting note in db and getting
        // newly inserted note id
        databaseHelper.insertBor(borGyarto, borNeve, borFajta, borSzin, borEvjarat, borAlkholotartam, borFogyasztasiHom);
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateBor(String borGyarto, String borNeve, String borFajta, String borSzin, int borEvjarat, float borAlkholotartam, int borFogyasztasiHom, int position) {
        Bor m = borList.get(position);
        // updating note text
        m.setBorGyarto(borGyarto);
        m.setBorNeve(borNeve);
        m.setBorFajta(borFajta);
        m.setBorSzin(borSzin);
        m.setBorEvjarat(borEvjarat);
        m.setBorAlkohol_tartam(borAlkholotartam);
        m.setBorFogyasztasiHomerseklet(borFogyasztasiHom);

        // updating note in db
        databaseHelper.updateBor(m);
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteBor(int position) {
        // deleting the note from db
        databaseHelper.deleteBor(borList.get(position));

        refreshList();
    }
}
