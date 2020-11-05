package fr.enigma.app.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import fr.enigma.app.R;
import fr.enigma.app.data.model.db.MovieCritic;

public class EditMovieCriticActivity extends AppCompatActivity {

    private MovieCritic mSelectedMovieCritic;

    private EditText mMovieTitleEditText;
    private EditText mDateTimeEditText;
    private EditText mMovieMakingEditText;
    private EditText mMovieMusicEditText;
    private EditText mMovieCriticDescription;
    private EditText mMovieScenario;

    private ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie_critic);

        mViewModel = new ViewModelProvider(this).get(ViewModel.class);

        mViewModel.selectedMovieCritic.observe(this, movieCritic -> mSelectedMovieCritic = movieCritic);

        mMovieTitleEditText = findViewById(R.id.et_movie_title);
        mDateTimeEditText = findViewById(R.id.et_movie_date);
        mMovieMakingEditText = findViewById(R.id.et_movie_making);
        mMovieMusicEditText = findViewById(R.id.et_movie_music);
        mMovieCriticDescription = findViewById(R.id.et_movie_description);
        mMovieScenario = findViewById(R.id.et_movie_scenario);


        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.MOVIE_CRITIC)) {
            mSelectedMovieCritic = (MovieCritic) getIntent().getSerializableExtra(MainActivity.MOVIE_CRITIC);
            mMovieTitleEditText.setText(mSelectedMovieCritic.getMovieTitle());
            mDateTimeEditText.setText(mSelectedMovieCritic.getProjectionTime());
            mMovieMakingEditText.setText(String.valueOf(mSelectedMovieCritic.getMovieMakingGrade()));
            mMovieMusicEditText.setText(String.valueOf(mSelectedMovieCritic.getMusicGrade()));
            mMovieCriticDescription.setText(mSelectedMovieCritic.getCriticDescription());
            mMovieScenario.setText(String.valueOf(mSelectedMovieCritic.getScenarioGrade()));
        }
        setTitle(getString(R.string.make_critic));

    }

    private boolean editTextsAreEmpty() {
        return mMovieTitleEditText.getText().toString().isEmpty() || mDateTimeEditText.getText().toString().isEmpty()
                || mMovieMakingEditText.getText().toString().isEmpty() || mMovieMusicEditText.getText().toString().isEmpty()
                || mMovieCriticDescription.getText().toString().isEmpty();
    }

    public void onSendCriticClick(View view) {
        if (editTextsAreEmpty()) {
            Toast.makeText(EditMovieCriticActivity.this, getString(R.string.msg_remplir_champs), Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent();

            String title = mMovieTitleEditText.getText().toString();
            String date = mDateTimeEditText.getText().toString();
            String movieMaking = mMovieMakingEditText.getText().toString();
            String movieMusic = mMovieMusicEditText.getText().toString();
            String movieScenario = mMovieScenario.getText().toString();
            String movieCriticDescription = mMovieCriticDescription.getText().toString();

            mSelectedMovieCritic.setMovieTitle(title);
            mSelectedMovieCritic.setProjectionTime(date);
            mSelectedMovieCritic.setMovieMakingGrade(Integer.parseInt(movieMaking));
            mSelectedMovieCritic.setMusicGrade(Integer.parseInt(movieMusic));
            mSelectedMovieCritic.setCriticDescription(movieCriticDescription);
            mSelectedMovieCritic.setScenarioGrade(Integer.parseInt(movieScenario));

            intent.putExtra(MainActivity.MOVIE_CRITIC, mSelectedMovieCritic);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
