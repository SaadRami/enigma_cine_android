package fr.enigma.app.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.enigma.app.R;
import fr.enigma.app.data.model.db.MovieCritic;

public class MainActivity extends AppCompatActivity implements MovieCriticsAdapter.ListItemClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    private ViewModel mViewModel;
    private List<MovieCritic> mMovieCritics;
    private RecyclerView mMovieCriticsList;
    private MovieCriticsAdapter mMovieCriticsAdapter;

    public static final int EDIT_MOVIE_CRITIC_REQUEST_CODE = 1;
    public static final String MOVIE_CRITIC = "movieCritic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setUpObservers();
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMovieCriticsList = findViewById(R.id.rv_movies);
        mMovieCriticsList.setLayoutManager(new LinearLayoutManager(this));
        mMovieCriticsList.setHasFixedSize(true);

    }

    private void setUpObservers() {
        mViewModel = new ViewModelProvider(this).get(ViewModel.class);

        mViewModel.getMovieCritics().observe(this, (Observer<List<MovieCritic>>) movieCritics -> {
            mMovieCritics = movieCritics;
            loadMovieCritics();
        });
    }


    private void loadMovieCritics() {
        mMovieCriticsAdapter = new MovieCriticsAdapter(mMovieCritics, this);
        mMovieCriticsList.setAdapter(mMovieCriticsAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItemId = item.getItemId();

        if (selectedItemId == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(MovieCritic movieCritic) {
        Intent intent = new Intent(MainActivity.this, EditMovieCriticActivity.class);
        intent.putExtra(MOVIE_CRITIC, movieCritic);
        startActivityForResult(intent, EDIT_MOVIE_CRITIC_REQUEST_CODE);
    }


    @Override
    public void onMailClick(MovieCritic movieCritic) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, movieCritic.toString());
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_MOVIE_CRITIC_REQUEST_CODE && resultCode == RESULT_OK) {
            MovieCritic movieCritic = (MovieCritic) data.getSerializableExtra(MOVIE_CRITIC);
            Log.d(TAG, "onActivityResult: " + movieCritic.getMovieTitle() + " " + movieCritic.getCriticDescription() + " "
                    + movieCritic.getProjectionTime() + " " + movieCritic.getId());
            mViewModel.updateMovieCritic(movieCritic);
            Toast.makeText(this, getString(R.string.movie_critic_successfull), Toast.LENGTH_SHORT).show();
        }
    }
}
