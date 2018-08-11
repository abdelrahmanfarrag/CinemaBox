package com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.cast;

import android.app.Dialog;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindColor;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.bus.IdBus;
import com.example.mana.cinematvclub.model.models.MovieCast;
import com.example.mana.cinematvclub.model.models.StarMovie;
import com.example.mana.cinematvclub.model.network.Services;
import com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.cast.adapter.StarAdapter;
import com.example.mana.cinematvclub.utility.Other;
import com.squareup.picasso.Picasso;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.mana.cinematvclub.utility.Constants.API_KEY;
import static com.example.mana.cinematvclub.utility.Constants.API_KEY_KEY;
import static com.example.mana.cinematvclub.utility.Constants.BASE_IMG_URL;

@SuppressWarnings("unchecked") public class CastPresenter implements Services.TransformResponse2Java {
  private Context context;
  private Services services;
  private CastSetup setup;

  @BindColor(R.color.black) int blackColor;

  CastPresenter(Context context, CompositeDisposable disposables, CastSetup setup, int id) {
    this.context = context;
    this.setup = setup;
    services = new Services(context, disposables, this);
    services.loadMovieCast(id, map());
  }

  private Map map() {
    Map map = new HashMap();
    map.put(API_KEY_KEY, API_KEY);
    return map;
  }

  public void loadCastMovies(int id) {
    if (map() != null) {
      services.loadStarMovies(id, map());
    }
  }

  private void dialogSetup(StarMovie movie) {
    Dialog d = Other.transparentDialog(context, R.layout.similar, R.style.wide_dialog);
    Objects.requireNonNull(d.getWindow())
        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    LinearLayout container = d.findViewById(R.id.similar_container);
    ConstraintLayout constraintLayout = d.findViewById(R.id.similar_full_cont);
    constraintLayout.setBackgroundColor(blackColor);
    ImageView backdrop = d.findViewById(R.id.similar_backdrop);
    RecyclerView list = d.findViewById(R.id.similar_list);
    list.setLayoutManager(new GridLayoutManager(context, 2));
    list.setHasFixedSize(true);
    Picasso.with(context).load(BASE_IMG_URL + IdBus.getPath()).into(backdrop);
    container.setVisibility(View.GONE);
    list.setAdapter(new StarAdapter(movie.getStartMovie()));
    d.show();
  }

  @Override public void transfrom(Object model) {
    if (model instanceof MovieCast) {
      MovieCast cast = (MovieCast) model;
      setup.getCastResult(cast);
    } else if (model instanceof StarMovie) {
      StarMovie movie = (StarMovie) model;
      dialogSetup(movie);
    }
  }
}
