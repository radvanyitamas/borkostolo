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
import com.example.olahgabormihaly.borkostolo_hits.adapter.BorkostoloSzemelyAdapter;
import com.example.olahgabormihaly.borkostolo_hits.algorithm.HITSAlgorithm;
import com.example.olahgabormihaly.borkostolo_hits.database.DatabaseHelper;
import com.example.olahgabormihaly.borkostolo_hits.database.model.Bor;
import com.example.olahgabormihaly.borkostolo_hits.database.model.Borbiralat;
import com.example.olahgabormihaly.borkostolo_hits.database.model.BorkostoloSzemely;
import com.example.olahgabormihaly.borkostolo_hits.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class KostolokFragment extends Fragment {

    private RecyclerView recyclerViewKostolok;
    private FloatingActionButton floatingActionButton;
    private TextView tvNoKostolok;

    private BorkostoloSzemelyAdapter borkostoloSzemelyAdapter;
    private List<BorkostoloSzemely> borkostoloSzemelyList = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kostolok, container, false);

        recyclerViewKostolok = root.findViewById(R.id.recyclerViewKostolok);
        floatingActionButton = root.findViewById(R.id.floatingActionButton);
        tvNoKostolok = root.findViewById(R.id.tvNoKostolo);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddKostoloDialog(false, -1);
            }
        });

        borkostoloSzemelyAdapter = new BorkostoloSzemelyAdapter(getContext(), borkostoloSzemelyList);
        recyclerViewKostolok.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewKostolok.setAdapter(borkostoloSzemelyAdapter);
        recyclerViewKostolok.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                recyclerViewKostolok, new RecyclerTouchListener.ClickListener() {
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
        borkostoloSzemelyList.clear();
        List<BorkostoloSzemely> allKostolo = databaseHelper.getAllKostolo();
        rendezesHitsSzerint(allKostolo);
        borkostoloSzemelyList.addAll(allKostolo);

        if (borkostoloSzemelyList.size() == 0) {
            tvNoKostolok.setVisibility(View.VISIBLE);
        } else {
            tvNoKostolok.setVisibility(View.GONE);
        }

        borkostoloSzemelyAdapter.notifyDataSetChanged();
    }

    private void rendezesHitsSzerint(List<BorkostoloSzemely> allKostolo) {
        List<Float> borAtlagPontszamok = new ArrayList<>();

        List<Bor> allBor = databaseHelper.getAllBor();
        Map<Integer, Float> borErtekek = new HashMap<>();

        List<Borbiralat> allBorBiralat = databaseHelper.getAllBorBiralat();

        for (Borbiralat borbiralat : allBorBiralat) {
            int borId = borbiralat.getBiraltBorID();
            if (borErtekek.containsKey(borId)) {
                float ertek = borErtekek.get(borId);
                ertek += borbiralat.getOsszesen();
                borErtekek.put(borId, ertek);
            } else {
                borErtekek.put(borId, (float) borbiralat.getOsszesen());
            }
        }

        Iterator<Integer> iterator = borErtekek.keySet().iterator();
        while (iterator.hasNext()) {
            Integer borId = iterator.next();
            Float ertek = borErtekek.get(borId);
            ertek /= allKostolo.size();
            borAtlagPontszamok.add(ertek);
        }

        HITSAlgorithm hitsAlgorithm = new HITSAlgorithm(getContext(), allKostolo.size(), borAtlagPontszamok);
        try {
            List<Float> rangsor = Executors.newSingleThreadExecutor().submit(hitsAlgorithm).get();
            for (int i = 0; i < rangsor.size(); i++) {
                allKostolo.get(i).setSzakmaisagiErtek(rangsor.get(i));
            }
            Collections.sort(allKostolo, new Comparator<BorkostoloSzemely>() {
                @Override
                public int compare(BorkostoloSzemely o1, BorkostoloSzemely o2) {
                    //10000-res szorzó: Lehet, hogy a pontszámok közt a különbség kisebb lesz, mint 1. Így ha int-té castoljuk, akkor elveszik a tizedes érték
                    return (int) ((o2.getSzakmaisagiErtek() - o1.getSzakmaisagiErtek()) * 10000);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void showAddKostoloDialog(final boolean shouldUpdate, final int position) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_kostolo, null);
        final EditText inputSzemIgSzam = view.findViewById(R.id.dialog_SzemIgSzam);
        final EditText inputVNev = view.findViewById(R.id.dialog_VNev);
        final EditText inputKNev = view.findViewById(R.id.dialog_KNev);
        final EditText inputSzulDatum = view.findViewById(R.id.dialog_SzulDatum);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("Kóstoló hozzáadása")
                .setView(view)
                .setPositiveButton("Hozzáadás", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(inputSzemIgSzam.getText().toString())) {
                            Toast.makeText(getContext(), "Enter ID Card number!", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(inputKNev.getText().toString())) {
                            Toast.makeText(getContext(), "Enter First Name!", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(inputVNev.getText().toString())) {
                            Toast.makeText(getContext(), "Enter Last Name!", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(inputSzulDatum.getText().toString())) {
                            Toast.makeText(getContext(), "Enter Birthday!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            dialog.dismiss();
                        }

                        // check if user updating borkostoloSzemely
                        if (shouldUpdate && borkostoloSzemelyList.get(position) != null) {
                            // update borkostoloSzemely by it's id
                            updateKostolo(inputSzemIgSzam.getText().toString(), inputVNev.getText().toString(), inputKNev.getText().toString(), inputSzulDatum.getText().toString(), position);
                        } else {
                            // create new borkostoloSzemely
                            createKostolo(inputSzemIgSzam.getText().toString(), inputVNev.getText().toString(), inputKNev.getText().toString(), inputSzulDatum.getText().toString(), System.currentTimeMillis());
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
                    showAddKostoloDialog(true, position);
                } else {
                    deleteNote(position);
                }
            }
        });
        builder.show();
    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createKostolo(String szemSzam, String vNev, String kNev, String szulDatum, long timeStamp) {
        // inserting note in db and getting
        // newly inserted note id
        databaseHelper.insertKostolo(szemSzam, vNev, kNev, szulDatum, timeStamp);
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateKostolo(String szemSzam, String vNev, String kNev, String szulDatum, int position) {
        BorkostoloSzemely n = borkostoloSzemelyList.get(position);
        // updating note text
        n.setSzemIgSzam(szemSzam);
        n.setVezetekNev(vNev);
        n.setKeresztNev(kNev);
        n.setSzuletesiDatum(szulDatum);

        // updating note in db
        databaseHelper.updateKostolo(n);
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteNote(int position) {
        // deleting the note from db
        databaseHelper.deleteKostolo(borkostoloSzemelyList.get(position));

        refreshList();
    }
}
