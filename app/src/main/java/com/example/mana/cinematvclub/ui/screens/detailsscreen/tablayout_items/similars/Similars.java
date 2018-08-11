package com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.similars;

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
import com.example.mana.cinematvclub.model.models.SimilarMovies;
import com.example.mana.cinematvclub.ui.base.BaseFragment;
import com.example.mana.cinematvclub.ui.screens.detailsscreen.MovieDetailsScreen;
import com.example.mana.cinematvclub.ui.screens.maincontentactivity.adapter.OnSingleMovieClick;
import com.example.mana.cinematvclub.utility.ActivityUtil;
import com.squareup.picasso.Picasso;

import static com.example.mana.cinematvclub.utility.Constants.BACKDROP_PATH;
import static com.example.mana.cinematvclub.utility.Constants.BASE_IMG_URL;
import static com.example.mana.cinematvclub.utility.Constants.MOVIE_ID;

public class Similars extends BaseFragment implements SimilarSetup, OnSingleMovieClick {

  @BindView(R.id.similar_backdrop) ImageView backdrop;
  @BindView(R.id.similar_list) RecyclerView list;
  @BindView(R.id.similar_txt) TextView numbers;
  @BindView(R.id.similar_next) ImageView next;
  @BindView(R.id.similar_prev) ImageView prev;
  @BindView(R.id.similar_container) LinearLayout container;

  SimilarPresenter presenter;

  @Override public int fragmentLayout() {
    return R.layout.similar;
  }

  @Override public void init() {
    int id = IdBus.getId();
    String path = IdBus.getPath();
    Picasso.with(getContext()).load(BASE_IMG_URL + path).into(backdrop);
    setupRecycler(list);
    presenter = new SimilarPresenter(getContext(), disposables, this, id);
  }

  private void setupRecycler(RecyclerView view) {
    view.setLayoutManager(new GridLayoutManager(getContext(), 2));
    view.setHasFixedSize(true);
  }

  @SuppressLint("SetTextI18n") @Override public void setSimilarData(SimilarMovies movies) {
    if (movies!=null) {
      int current = movies.getCurrentPage();
      int total = movies.getTotalPages();
      numbers.setText(current + " |" + total);
      presenter.setSimilarData(container, next, prev, current, total);
      list.setAdapter(new SimilarAdapter(movies.getResults(), this));
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
