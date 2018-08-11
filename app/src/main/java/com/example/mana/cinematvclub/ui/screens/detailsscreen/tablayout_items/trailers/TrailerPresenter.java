package com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.trailers;

import android.content.Context;
import com.example.mana.cinematvclub.model.models.MovieTrailer;
import com.example.mana.cinematvclub.model.network.Services;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;

import static com.example.mana.cinematvclub.utility.Constants.API_KEY;
import static com.example.mana.cinematvclub.utility.Constants.API_KEY_KEY;

@SuppressWarnings("unchecked") public class TrailerPresenter implements Services.TransformResponse2Java {
  private TrailerSetup setup;

  TrailerPresenter(Context context, CompositeDisposable disposables, int id,
      TrailerSetup setup) {
    this.setup = setup;
    Services services = new Services(context, disposables, this);
    services.loadTrailers(id, getMap());
  }

  private Map getMap() {
    Map map = new HashMap();
    map.put(API_KEY_KEY, API_KEY);
    return map;
  }

  @Override public void transfrom(Object model) {
    MovieTrailer trailer = (MovieTrailer) model;
    setup.setTrailerData(trailer);
  }
}
