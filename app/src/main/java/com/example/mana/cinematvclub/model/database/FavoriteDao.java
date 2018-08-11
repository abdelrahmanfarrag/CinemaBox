package com.example.mana.cinematvclub.model.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface FavoriteDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE) void insertMovieToFavorite(Favorite fav);

  @Query("SELECT * FROM Favorite") List<Favorite> getFavoriteMovies();

  @Query("DELETE FROM Favorite WHERE movie_id=:id") void deleteSelectedItem(int id);
}
