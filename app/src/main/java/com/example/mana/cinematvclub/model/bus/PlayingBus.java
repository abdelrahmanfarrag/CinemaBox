package com.example.mana.cinematvclub.model.bus;

import com.example.mana.cinematvclub.model.models.PlayingMovies;
import java.util.List;

public class PlayingBus {
  private List<PlayingMovies.Results> playingList;

  public PlayingBus(
      List<PlayingMovies.Results> playingList) {
    this.playingList = playingList;
  }

  public List<PlayingMovies.Results> getPlayingList() {
    return playingList;
  }

  public void setPlayingList(
      List<PlayingMovies.Results> playingList) {
    this.playingList = playingList;
  }
}
