package fr.enigma.app.data.local.db;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import fr.enigma.app.data.local.db.dao.MovieCriticDao;
import fr.enigma.app.data.model.db.MovieCritic;
import fr.enigma.app.util.AppExecutors;

@Database(entities = {MovieCritic.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = AppDatabase.class.getSimpleName();
    public static final String DATABASE_NAME = "MovieCriticsDatabse.db";
    private static volatile AppDatabase sInstance;

    public abstract MovieCriticDao getMovieCriticDao();

    public static AppDatabase getInstance(Application application) {
        Log.d(TAG, "getInstance: " + sInstance);
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                sInstance = Room.databaseBuilder(application,
                        AppDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .addCallback(getRoomDBCallBack())
                        .build();
            }
        }
        return sInstance;
    }


    private static RoomDatabase.Callback getRoomDBCallBack() {
        RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Log.i(TAG, "RDC onCreate: starts");
                AppExecutors.getInstance().diskIO().execute(
                        () -> {
                            initDatabase(sInstance);
                        });
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                Log.i(TAG, "RDC onOpen: starts");
            }
        };

        return rdc;
    }


    private static void initDatabase(AppDatabase appDatabase) {
        MovieCriticDao movieCriticDao = appDatabase.getMovieCriticDao();

        MovieCritic movieCritic = new MovieCritic("Batman");
        movieCriticDao.insertMovieCritic(movieCritic);

        movieCritic = new MovieCritic("SuperMan");
        movieCriticDao.insertMovieCritic(movieCritic);

        movieCritic = new MovieCritic("Le seigneur des anneaux 1");
        movieCriticDao.insertMovieCritic(movieCritic);

        movieCritic = new MovieCritic("Spiderman 2");
        movieCriticDao.insertMovieCritic(movieCritic);
    }

}
