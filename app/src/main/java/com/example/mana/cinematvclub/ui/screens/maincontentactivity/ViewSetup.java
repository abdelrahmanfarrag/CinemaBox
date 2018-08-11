package com.example.mana.cinematvclub.ui.screens.maincontentactivity;

import com.example.mana.cinematvclub.model.database.Favorite;
import com.example.mana.cinematvclub.model.models.Hollywood;
import com.example.mana.cinematvclub.model.models.PlayingMovies;
import com.example.mana.cinematvclub.model.models.PouplarMovies;
import com.example.mana.cinematvclub.model.models.RatedMovies;
import com.example.mana.cinematvclub.model.models.UpComingMovies;
import java.util.List;

public interface ViewSetup {
  void upcomingMovies(UpComingMovies movies);

  void popularMovies(PouplarMovies movies);

  void playingMovies(PlayingMovies movies);

  void ratedMovies(RatedMovies movies);

  void hollywoodStars(Hollywood stars);

  void viewFavorites(List<Favorite> favorites);
}
