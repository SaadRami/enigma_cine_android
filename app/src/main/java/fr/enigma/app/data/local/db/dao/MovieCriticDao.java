package fr.enigma.app.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.enigma.app.data.model.db.MovieCritic;

@Dao
public interface MovieCriticDao {

    @Insert
    void insertMovieCritic(MovieCritic movieCritic);

    @Update
    void updateMovieCritic(MovieCritic movieCritic);


    @Query("SELECT * FROM movie_critics_table")
    LiveData<List<MovieCritic>> getAllMovieCritics();

}
