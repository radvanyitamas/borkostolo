package com.example.olahgabormihaly.borkostolo_hits.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olahgabormihaly.borkostolo_hits.R;
import com.example.olahgabormihaly.borkostolo_hits.adapter.BorkostoloSzemelyAdapter;
import com.example.olahgabormihaly.borkostolo_hits.database.DatabaseHelper;
import com.example.olahgabormihaly.borkostolo_hits.database.model.BorkostoloSzemely;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BorkostoloSzemelyAdapter mAdapter;
    private List<BorkostoloSzemely> notesList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noNotesView;

    private DatabaseHelper db;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.borok) {
                    BorokFragment borokFragment = new BorokFragment();
                    fragmentManager.beginTransaction().replace(R.id.containerLayout, borokFragment).commit();
                } else if (item.getItemId() == R.id.kostolok) {
                    KostolokFragment kostolokFragment = new KostolokFragment();
                    fragmentManager.beginTransaction().replace(R.id.containerLayout, kostolokFragment).commit();
                } else if (item.getItemId() == R.id.biralat) {
                    BorbiralatFragment biraltFragment = new BorbiralatFragment();
                    fragmentManager.beginTransaction().replace(R.id.containerLayout, biraltFragment).commit();
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.borok);

        /*coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recycler_view);
        noNotesView = findViewById(R.id.empty_notes_view);

        db = new DatabaseHelper(this);

        notesList.addAll(db.getAllKostolo());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoteDialog(false, null, -1);
            }
        });

        mAdapter = new BorkostoloSzemelyAdapter(this, notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyNotes();

        *//**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * *//*
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));*/
    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createNote(String szemSzam, String vNev, String kNev, String szulDatum, long timeStamp) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertKostolo(szemSzam, vNev, kNev, szulDatum, timeStamp);

        // get the newly inserted note from db
        BorkostoloSzemely n = db.getKostolo(id);

        if (n != null) {
            // adding new note to array list at 0 position
            notesList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyNotes();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateNote(String szemSzam, String vNev, String kNev, String szulDatum, int position) {
        BorkostoloSzemely n = notesList.get(position);
        // updating note text
        n.setSzemIgSzam(szemSzam);
        n.setVezetekNev(vNev);
        n.setKeresztNev(kNev);
        n.setSzuletesiDatum(szulDatum);

        // updating note in db
        db.updateKostolo(n);

        // refreshing the list
        notesList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyNotes();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteNote(int position) {
        // deleting the note from db
        db.deleteKostolo(notesList.get(position));

        // removing the note from the list
        notesList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyNotes();
    }

    /**
     * Opens dialog with Edit - Delete options
     * Edit - 0
     * Delete - 0
     */
    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showNoteDialog(true, notesList.get(position), position);
                } else {
                    deleteNote(position);
                }
            }
        });
        builder.show();
    }

    /**
     * Shows alert dialog with EditText options to enter / edit
     * a borkostoloSzemely.
     * when shouldUpdate=true, it automatically displays old borkostoloSzemely and changes the
     * button text to UPDATE
     */
    private void showNoteDialog(final boolean shouldUpdate, final BorkostoloSzemely borkostoloSzemely, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.dialog_add_kostolo, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputSzemIgSzam = view.findViewById(R.id.dialog_SzemIgSzam);
        final EditText inputVNev = view.findViewById(R.id.dialog_VNev);
        final EditText inputKNev = view.findViewById(R.id.dialog_KNev);
        final EditText inputSzulDatum = view.findViewById(R.id.dialog_SzulDatum);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_note_title) : getString(R.string.lbl_edit_note_title));

        if (shouldUpdate && borkostoloSzemely != null) {
            inputSzemIgSzam.setText(borkostoloSzemely.getSzemIgSzam());
            inputVNev.setText(borkostoloSzemely.getVezetekNev());
            inputKNev.setText(borkostoloSzemely.getKeresztNev());
            inputSzulDatum.setText(borkostoloSzemely.getSzuletesiDatum());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(inputSzemIgSzam.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter ID Card number!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(inputKNev.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter First Name!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(inputVNev.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter Last Name!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(inputSzulDatum.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter Birthday!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                // check if user updating borkostoloSzemely
                if (shouldUpdate && borkostoloSzemely != null) {
                    // update borkostoloSzemely by it's id
                    updateNote(inputSzemIgSzam.getText().toString(), inputVNev.getText().toString(), inputKNev.getText().toString(), inputSzulDatum.getText().toString(), position);
                } else {
                    // create new borkostoloSzemely
                    createNote(inputSzemIgSzam.getText().toString(), inputVNev.getText().toString(), inputKNev.getText().toString(), inputSzulDatum.getText().toString(), System.currentTimeMillis());
                }
            }
        });
    }

    /**
     * Toggling list and empty notes view
     */
    private void toggleEmptyNotes() {
        // you can check notesList.size() > 0

        if (db.getKostoloCount() > 0) {
            noNotesView.setVisibility(View.GONE);
        } else {
            noNotesView.setVisibility(View.VISIBLE);
        }
    }
}
