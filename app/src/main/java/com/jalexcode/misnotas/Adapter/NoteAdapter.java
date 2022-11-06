package com.jalexcode.misnotas.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.jalexcode.misnotas.Model.Note;
import com.jalexcode.misnotas.R;
import com.jalexcode.misnotas.Util.Util;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import co.dift.ui.SwipeToAction;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private Context context;
    private List<Note> noteList = new ArrayList<>();

    public NoteAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false));
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        // titulo
        String title = note.getTitle();
        if (!title.isEmpty()) {
            holder.noteTitle.setText(title);
        }
        // fecha de creacion
//        long timestamp = note.getCreationDate();
//        GregorianCalendar date =  Util.long2Date(timestamp);
//        holder.creationDate.setText(Util.date2String(date));
//        // texto
        String text = note.getText();
        if (!text.isEmpty()) {
            holder.noteText.setText(text);
        }else {
            holder.noteText.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNoteList(List<Note> list){
        noteList = list;
        notifyDataSetChanged();
    }

    class NoteViewHolder extends SwipeToAction.ViewHolder<Note> {
        private TextView noteTitle;
        private TextView noteText;
//        private TextView creationDate;

        public NoteViewHolder(View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.note_title);
            noteText = itemView.findViewById(R.id.note_text);
//            creationDate = itemView.findViewById(R.id.creation_date);
        }

        public int getItemPosition(){
            return getAdapterPosition();
        }
    }
}
