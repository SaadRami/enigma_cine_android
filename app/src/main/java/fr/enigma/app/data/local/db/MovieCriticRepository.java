package fr.enigma.app.data.local.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.enigma.app.data.local.db.dao.MovieCriticDao;
import fr.enigma.app.data.model.db.MovieCritic;
import fr.enigma.app.util.AppExecutors;

public class MovieCriticRepository {
    private MovieCriticDao movieCriticDao;
    private LiveData<List<MovieCritic>> movieCritics;

    public MovieCriticRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        movieCriticDao = appDatabase.getMovieCriticDao();
    }

    public LiveData<List<MovieCritic>> getMovieCritics() {
        movieCritics = movieCriticDao.getAllMovieCritics();
        return movieCritics;
    }


    public void insertMovieCritic(final MovieCritic movieCritic) {
        AppExecutors.getInstance().diskIO().execute(() -> movieCriticDao.insertMovieCritic(movieCritic));
    }


    public void updateMovieCritic(final MovieCritic movieCritic) {
        AppExecutors.getInstance().diskIO().execute(() -> movieCriticDao.updateMovieCritic(movieCritic));
    }


}
