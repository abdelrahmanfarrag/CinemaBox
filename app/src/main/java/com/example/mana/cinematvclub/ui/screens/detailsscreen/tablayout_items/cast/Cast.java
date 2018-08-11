package com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.cast;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import butterknife.BindView;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.bus.IdBus;
import com.example.mana.cinematvclub.model.models.MovieCast;
import com.example.mana.cinematvclub.ui.base.BaseFragment;
import com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.cast.adapter.CastAdapter;
import com.example.mana.cinematvclub.ui.screens.maincontentactivity.adapter.OnSingleMovieClick;
import com.squareup.picasso.Picasso;

import static com.example.mana.cinematvclub.utility.Constants.BASE_IMG_URL;

public class Cast extends BaseFragment implements CastSetup, OnSingleMovieClick {
  @BindView(R.id.cast_list) RecyclerView castList;
  @BindView(R.id.cast_container) ImageView layout;

  String backdropPath;
  CastPresenter presenter;

  public static Cast newInstance() {
    return new Cast();
  }

  @Override public int fragmentLayout() {
    return R.layout.cast;
  }

  @Override public void init() {
    recyclerConfigs(castList);
    backdropPath = IdBus.getPath();
    Picasso.with(getContext()).load(BASE_IMG_URL + backdropPath).into(layout);
    presenter = new CastPresenter(getContext(), disposables, this, IdBus.getId());
  }

  private void recyclerConfigs(RecyclerView view) {
    view.setLayoutManager(new GridLayoutManager(getContext(), 2));
    view.setHasFixedSize(true);
  }

  @Override public void getCastResult(MovieCast cast) {
    if (cast!=null) {
      castList.setAdapter(new CastAdapter(cast.getCast(), this));
    }
  }

  @Override public void getMovieId(int id, String path) {
    presenter.loadCastMovies(id);
  }
}
