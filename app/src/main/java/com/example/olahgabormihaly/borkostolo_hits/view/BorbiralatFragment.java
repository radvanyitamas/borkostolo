package com.example.olahgabormihaly.borkostolo_hits.view;

import android.content.Context;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olahgabormihaly.borkostolo_hits.R;
import com.example.olahgabormihaly.borkostolo_hits.adapter.BorBiralatAdapter;
import com.example.olahgabormihaly.borkostolo_hits.database.DatabaseHelper;
import com.example.olahgabormihaly.borkostolo_hits.database.model.Borbiralat;
import com.example.olahgabormihaly.borkostolo_hits.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class BorbiralatFragment extends Fragment {

    private RecyclerView recyclerViewBorBiralat;
    private FloatingActionButton floatingActionButton;
    private TextView tvNoBiralat;

    private BorBiralatAdapter borBiralatAdapter;
    private List<Borbiralat> borBiralatList = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    public void refreshList() {
        borBiralatList.clear();
        borBiralatList.addAll(databaseHelper.getAllBorBiralat());

//        if(borBiralatList.isEmpty()) {
//            tvNoBiralat.setVisibility(View.VISIBLE);
//        } else {
//            tvNoBiralat.setVisibility(View.GONE);
//        }

        borBiralatAdapter.notifyDataSetChanged();
    }

    private void showAddBorBiralatDialog(final boolean shouldUpdate, final int position) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_biralat, null);
        final EditText inputBiraltBorID = view.findViewById(R.id.dialog_biraltBorID);
        final EditText inputBiraloID = view.findViewById(R.id.dialog_biraloID);
        final Spinner spMegjelenesTisztasag = view.findViewById(R.id.dialog_spMegjelenesTisztasag);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("Bírálat hozzáadása")
                .setView(view)
                .setPositiveButton("Hozzáadás", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(inputBiraltBorID.getText().toString())) {
                            Toast.makeText(getContext(), "Enter ID of the wine!", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(inputBiraloID.getText().toString())) {
                            Toast.makeText(getContext(), "Enter ID of the person!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            dialog.dismiss();
                        }

                        // check if user updating borkostoloSzemely
                        if (shouldUpdate && borBiralatList.get(position) != null) {
                            // update borkostoloSzemely by it's id
                            int borBiraloID = Integer.parseInt(String.valueOf(inputBiraloID.getText().toString()));
                            updateBorBiralat(Integer.parseInt(inputBiraltBorID.getText().toString()), borBiraloID, Integer.parseInt(String.valueOf(spMegjelenesTisztasag.getSelectedItem())), position);
                        } else {
                            // create new borkostoloSzemely
                            int borBiraloID = Integer.parseInt(String.valueOf(inputBiraloID.getText().toString()));
                            createBorBiralat(Integer.parseInt(inputBiraltBorID.getText().toString()), borBiraloID, Integer.parseInt(String.valueOf(spMegjelenesTisztasag.getSelectedItem())));
                            Toast.makeText(getContext(), String.valueOf(borBiraloID), Toast.LENGTH_SHORT).show();

                    }
                    refreshList();
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showAddBorBiralatDialog(true, position);
                } else {
                    deleteBorBiralat(position);
                }
            }
        });
        builder.show();
    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */

    private void createBorBiralat(int biraltBorId, int biraloSzemelyId, int megjelenesTisztasag) {
        // inserting note in db and getting
        // newly inserted note id
        String borBiraloID = String.valueOf(Integer.parseInt(String.valueOf(biraloSzemelyId)));
        Toast.makeText(getContext(), borBiraloID, Toast.LENGTH_SHORT).show();
        databaseHelper.insertBorBiralat(biraltBorId, biraloSzemelyId, megjelenesTisztasag);
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateBorBiralat(int biraltBorId, int biraloSzemelyId, int megjelenesTisztasaga, int position) {
        Borbiralat t = borBiralatList.get(position);
        // updating note text
        t.setBiraltBorID(biraltBorId);
        t.setBiraloSzemelyID(biraloSzemelyId);
        t.setMegjelenesTisztasag(megjelenesTisztasaga);
        // updating note in db
        databaseHelper.updateBorBiralat(t);
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteBorBiralat(int position) {
        // deleting the note from db
        databaseHelper.deleteBorBiralat(borBiralatList.get(position));

        refreshList();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_borok, container, false);

        recyclerViewBorBiralat = root.findViewById(R.id.recyclerViewBorok);
        floatingActionButton = root.findViewById(R.id.floatingActionButton);
        tvNoBiralat = root.findViewById(R.id.tvNoBiralat);

//        if(borBiralatList.size() == 0) {
//            tvNoBiralat.setVisibility(View.VISIBLE);
//        } else {
//            tvNoBiralat.setVisibility(View.GONE);
//        }

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddBorBiralatDialog(false, -1);
            }
        });

        borBiralatAdapter = new BorBiralatAdapter(getContext(),borBiralatList);
        recyclerViewBorBiralat.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewBorBiralat.setAdapter(borBiralatAdapter);
        recyclerViewBorBiralat.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                recyclerViewBorBiralat, new RecyclerTouchListener.ClickListener() {
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
}
