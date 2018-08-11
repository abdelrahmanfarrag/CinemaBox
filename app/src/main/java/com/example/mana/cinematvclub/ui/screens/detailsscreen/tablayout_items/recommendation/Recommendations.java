package com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.recommendation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.bus.IdBus;
import com.example.mana.cinematvclub.model.models.RecommendMovies;
import com.example.mana.cinematvclub.ui.base.BaseFragment;
import com.example.mana.cinematvclub.ui.screens.detailsscreen.MovieDetailsScreen;
import com.example.mana.cinematvclub.ui.screens.maincontentactivity.adapter.OnSingleMovieClick;
import com.example.mana.cinematvclub.utility.ActivityUtil;
import com.squareup.picasso.Picasso;

import static com.example.mana.cinematvclub.utility.Constants.BACKDROP_PATH;
import static com.example.mana.cinematvclub.utility.Constants.BASE_IMG_URL;
import static com.example.mana.cinematvclub.utility.Constants.MOVIE_ID;

public class Recommendations extends BaseFragment implements RecommendSetup, OnSingleMovieClick {
  @BindView(R.id.recommend_container) LinearLayout container;
  @BindView(R.id.recommend_next) ImageView next;
  @BindView(R.id.recommend_prev) ImageView prev;
  @BindView(R.id.recommend_txt) TextView counters;
  @BindView(R.id.recommend_back) ImageView background;
  @BindView(R.id.recommend_list) RecyclerView list;

  RecommendationPresenter presenter;

  @Override public int fragmentLayout() {
    return R.layout.recommend;
  }

  @Override public void init() {
    int id = IdBus.getId();
    String img = IdBus.getPath();
    setupRecycler(list);
    Picasso.with(getContext()).load(BASE_IMG_URL + img).into(background);
    presenter = new RecommendationPresenter(getContext(), disposables, id, this);
  }

  private void setupRecycler(RecyclerView view) {
    view.setLayoutManager(new GridLayoutManager(getContext(), 2));
    view.setHasFixedSize(true);
  }

  @SuppressLint("SetTextI18n") @Override public void setRecommendedMoviesData(RecommendMovies movies) {
    if (movies != null) {
      int current = movies.getCurrentPage();
      int total = movies.getTotalPages();
      counters.setText(current + " |" + total);
      presenter.setSimilarData(container, next, prev, current, total);
      list.setAdapter(new RecommendAdapter(movies.getRecommendedMovies(), this));
    }
  }

  @Override public void getMovieId(int id, String path) {
    Intent in = new Intent(getContext(), MovieDetailsScreen.class);
    in.putExtra(MOVIE_ID, id);
    in.putExtra(BACKDROP_PATH, path);
    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    assert getContext() != null;
    ActivityUtil.intentWithData(getContext(), in);
  }
}
