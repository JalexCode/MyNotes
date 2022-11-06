package com.jalexcode.misnotas.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.jalexcode.misnotas.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class AddNoteActivity extends AppCompatActivity {
    private MaterialEditText title;
    private MaterialEditText text;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        setTitle("Editar nota");
        //
        title = findViewById(R.id.note_title);
        text = findViewById(R.id.note_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.edit_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_note:
                saveNote();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote(){
        String titleStr = title.getText().toString();
        String textStr = text.getText().toString();
        if (validate(titleStr, textStr)) {
            //
            Intent addNote = new Intent();
            addNote.putExtra("TITLE", titleStr);
            addNote.putExtra("TEXT", textStr);
            setResult(RESULT_OK, addNote);
            finish();
        }
    }

    private boolean validate(String titleStr, String textStr){
        if (titleStr.isEmpty()){
            Toast.makeText(AddNoteActivity.this, "El título es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (titleStr.length() > 20){
            Toast.makeText(AddNoteActivity.this, "El título solo puede tener 20 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (textStr.length() > 500){
            Toast.makeText(AddNoteActivity.this, "La descripción solo puede tener 500 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}