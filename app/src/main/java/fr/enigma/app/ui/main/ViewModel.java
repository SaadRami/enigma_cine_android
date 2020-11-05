package fr.enigma.app.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.enigma.app.data.local.db.MovieCriticRepository;
import fr.enigma.app.data.model.db.MovieCritic;

public class ViewModel extends AndroidViewModel {
    private MovieCriticRepository movieCriticRepository;
    private LiveData<List<MovieCritic>> movieCritics;
    MutableLiveData<MovieCritic> selectedMovieCritic = new MutableLiveData<>();


    public ViewModel(@NonNull Application application) {
        super(application);
        movieCriticRepository = new MovieCriticRepository(application);
    }

    public LiveData<List<MovieCritic>> getMovieCritics() {
        movieCritics = movieCriticRepository.getMovieCritics();
        return movieCritics;
    }


    public void updateMovieCritic(MovieCritic movieCritic) {
        movieCriticRepository.updateMovieCritic(movieCritic);
    }

}
