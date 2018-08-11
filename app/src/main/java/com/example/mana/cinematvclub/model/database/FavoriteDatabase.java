package com.example.mana.cinematvclub.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Favorite.class, version = 1, exportSchema = false)

public abstract class FavoriteDatabase extends RoomDatabase {
  private static FavoriteDatabase favoriteInstance;

  public static FavoriteDatabase getFavoriteInstance(Context context) {
    if (favoriteInstance == null) {
      favoriteInstance =
          Room.databaseBuilder(context, FavoriteDatabase.class, "favorite.db").build();
    }
    return favoriteInstance;
  }
  public abstract FavoriteDao getAccessToDatabase();
}
