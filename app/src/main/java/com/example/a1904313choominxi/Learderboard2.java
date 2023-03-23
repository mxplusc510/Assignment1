package com.example.a1904313choominxi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;


public class Learderboard2 extends ArrayAdapter<Player> {
    private final LayoutInflater inflater;
    public Learderboard2(Context context, List<Player> highScores) {
        super(context, R.layout.score2, highScores);
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            View view = inflater.inflate(R.layout.score2, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
            convertView = view;
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Player highScore = getItem(position);
        holder.bind(highScore);
        return convertView;
    }
    static class ViewHolder {
        private final TextView nameTextView;
        private final TextView scoreTextView;
        ViewHolder(@NonNull View itemView) {
            nameTextView = itemView.findViewById(R.id.names);
            scoreTextView = itemView.findViewById(R.id.scores);
        }
        void bind(Player highScore) {
            nameTextView.setText(highScore.getName());
            scoreTextView.setText(String.valueOf(highScore.getScore()));
        }
    }

}

