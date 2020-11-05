package fr.enigma.app.data.model.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "movie_critics_table")
public class MovieCritic implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "movie_title")
    private String movieTitle;

    @ColumnInfo(name = "projection_time")
    private String projectionTime;

    @ColumnInfo(name = "scenario_grade")
    private int scenarioGrade;

    @ColumnInfo(name = "movie_making_grade")
    private int movieMakingGrade;

    @ColumnInfo(name = "music_grade")
    private int musicGrade;

    @ColumnInfo(name = "critic")
    private String criticDescription;

    @Ignore
    public MovieCritic(String name) {
        this(0, name, null, 0, 0, 0, null);
    }

    public MovieCritic(int id, String movieTitle, String projectionTime, int scenarioGrade,
                       int movieMakingGrade, int musicGrade, String criticDescription) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.projectionTime = projectionTime;
        this.scenarioGrade = scenarioGrade;
        this.movieMakingGrade = movieMakingGrade;
        this.musicGrade = musicGrade;
        this.criticDescription = criticDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getProjectionTime() {
        return projectionTime;
    }

    public void setProjectionTime(String projectionTime) {
        this.projectionTime = projectionTime;
    }

    public int getScenarioGrade() {
        return scenarioGrade;
    }

    public void setScenarioGrade(int scenarioGrade) {
        this.scenarioGrade = scenarioGrade;
    }

    public int getMovieMakingGrade() {
        return movieMakingGrade;
    }

    public void setMovieMakingGrade(int movieMakingGrade) {
        this.movieMakingGrade = movieMakingGrade;
    }

    public int getMusicGrade() {
        return musicGrade;
    }

    public void setMusicGrade(int musicGrade) {
        this.musicGrade = musicGrade;
    }

    public String getCriticDescription() {
        return criticDescription;
    }

    public void setCriticDescription(String criticDescription) {
        this.criticDescription = criticDescription;
    }

    @NonNull
    @Override
    public String toString() {
        String infoResult = "Title : %s\n" +
                "Date de Projection : %s\n" +
                "Scenario : %s/10\n" +
                "Réalisation : %s/10\n" +
                "Musique : %s/10\n" +
                "Description : %s";

        return String.format(infoResult, movieTitle,
                projectionTime, scenarioGrade
                , movieMakingGrade, musicGrade,
                criticDescription);
    }
}