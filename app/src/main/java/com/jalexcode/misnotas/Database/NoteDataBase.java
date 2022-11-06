package com.jalexcode.misnotas.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jalexcode.misnotas.Model.Note;

import java.util.Calendar;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDataBase extends RoomDatabase {
    // singleton
    private static volatile NoteDataBase instance;
    //
    public abstract NoteDAO noteDAO();

    public static synchronized NoteDataBase getInstance(Context context) {
        if (instance == null){
            synchronized (NoteDataBase.class) {
                instance = Room.databaseBuilder(context,
                        NoteDataBase.class, "notas")
                        .addCallback(roomCallback)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

}
