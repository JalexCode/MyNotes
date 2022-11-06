package com.jalexcode.misnotas.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jalexcode.misnotas.Model.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository notesRepository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NoteRepository(application);
    }

    public void insert(Note note){
        notesRepository.insert(note);
    }

    public void update(Note note){
        notesRepository.insert(note);
    }

    public void delete(Note note){
        notesRepository.insert(note);
    }

    public void deleteAllNotes(){
        notesRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return notesRepository.getAllNotes();
    }
}
