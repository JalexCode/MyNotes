package com.jalexcode.misnotas.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jalexcode.misnotas.Adapter.NoteAdapter;
import com.jalexcode.misnotas.Model.Note;
import com.jalexcode.misnotas.R;
import com.jalexcode.misnotas.ViewModel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import co.dift.ui.SwipeToAction;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private NoteAdapter noteAdapter;
    private TextView noNotes;

    private final int ADD_NOTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddNoteActivity.class);
                startActivityForResult(i, ADD_NOTE);
            }
        });
        // recycler view
        recyclerView = findViewById(R.id.notes_rv);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // adaptador
        noteAdapter = new NoteAdapter(getApplicationContext());
        // observer
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                setAdapter(notes);
            }
        });
        //
        noNotes = findViewById(R.id.no_notes);
    }

    private void setAdapter(List<Note> notes) {
        if (notes.isEmpty()){
            noNotes.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            noNotes.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        noteAdapter.setNoteList(notes);
        recyclerView.setAdapter(noteAdapter);
        SwipeToAction swipeToAction = new SwipeToAction(recyclerView, new SwipeToAction.SwipeListener<NoteAdapter>() {
            @Override
            public boolean swipeLeft(NoteAdapter itemData) {
                return false;
            }

            @Override
            public boolean swipeRight(NoteAdapter itemData) {
                SweetAlertDialog sd = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
                sd.setTitleText("Eliminar nota");
                sd.setContentText("¿Estás seguro?");
                sd.setCancelable(false);
                sd.setCanceledOnTouchOutside(false);
                sd.setConfirmText("Eliminar");
                sd.setCancelText("Cancelar");
                sd.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        //noteViewModel.delete(itemData.getItemPosition());
                        sweetAlertDialog.setTitleText("Nota eliminada")
                                .setContentText("La nota ha sido eliminada")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                });
                sd.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
                sd.show();
                return false;
            }

            @Override
            public void onClick(NoteAdapter itemData) {
//                Intent i = new Intent();
//                i.putExtra()
            }

            @Override
            public void onLongClick(NoteAdapter itemData) {

            }
        });
    }
        //

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE && resultCode == RESULT_OK){
            Toast.makeText(getApplicationContext(), "Trying", Toast.LENGTH_SHORT).show();
            String title = data.getStringExtra("TITLE");
            String text = data.getStringExtra("TEXT");
            //
            noteViewModel.insert(new Note(title, text));
            //
            Toast.makeText(getApplicationContext(), "Nota agregada", Toast.LENGTH_SHORT).show();
        }
    }

    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all:
                SweetAlertDialog sd = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
                sd.setTitleText("Eliminar todas las notas");
                sd.setContentText("¿Estás seguro?");
                sd.setCancelable(false);
                sd.setCanceledOnTouchOutside(false);
                sd.setConfirmText("Eliminar");
                sd.setCancelText("Cancelar");
                sd.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        noteViewModel.deleteAllNotes();
                        sweetAlertDialog.setTitleText("Todas las notas eliminadas")
                                .setContentText("La notas han sido eliminadas")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                });
                sd.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
                sd.show();
                return true;
            case R.id.about:
                Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}