package fr.enigma.app.ui.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.enigma.app.R;
import fr.enigma.app.data.model.db.MovieCritic;

public class MovieCriticsAdapter extends RecyclerView.Adapter<MovieCriticsAdapter.MovieCriticViewHolder> {
    private static final String TAG = MovieCriticsAdapter.class.getSimpleName();
    private final ListItemClickListener mOnClickListener;
    private List<MovieCritic> mMovieCritics;

    public MovieCriticsAdapter(List<MovieCritic> movieCritics, ListItemClickListener listItemClickListener) {
        mMovieCritics = movieCritics;
        mOnClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public MovieCriticViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_critic_list_item, viewGroup, false);
        return new MovieCriticViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MovieCriticViewHolder holder, int i) {
        if (mMovieCritics == null || mMovieCritics.isEmpty()) {
            Log.e(TAG, "onBindViewHolder: null or empty datasource");
            holder.itemView.setOnClickListener(null);
            return;
        }
        MovieCritic movieCritic = mMovieCritics.get(i);
        Log.d(TAG, "onBindViewHolder: " + movieCritic.getMovieTitle());

        holder.nameTextView.setText(movieCritic.getMovieTitle());
        holder.itemView.setOnClickListener(v -> mOnClickListener.onListItemClick(movieCritic));
        holder.sendMailBtn.setOnClickListener(v -> mOnClickListener.onMailClick(movieCritic));
    }

    @Override
    public int getItemCount() {
        return mMovieCritics.size();
    }


    public class MovieCriticViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageButton sendMailBtn;

        public MovieCriticViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_movie_title);
            sendMailBtn = itemView.findViewById(R.id.btn_send_mail);
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(MovieCritic movieCritic);

        void onMailClick(MovieCritic movieCritic);
    }

}
