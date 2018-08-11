package com.example.mana.cinematvclub.ui.screens.maincontentactivity.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.models.PlayingMovies;
import com.example.mana.cinematvclub.ui.base.BaseRecyclerAdapter;
import com.example.mana.cinematvclub.ui.base.BaseViewHolder;
import com.example.mana.cinematvclub.utility.Other;
import com.squareup.picasso.Picasso;
import java.util.List;

import static com.example.mana.cinematvclub.utility.Constants.BASE_IMG_URL;

public class PlayingAdapter extends BaseRecyclerAdapter<PlayingMovies.Results, BaseViewHolder> {
private OnSingleMovieClick itemClick;
  public PlayingAdapter(
      List<PlayingMovies.Results> recyclerItems,OnSingleMovieClick itemClick) {
    super(recyclerItems);
    this.itemClick=itemClick;
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = getLayoutInflater(parent.getContext()).inflate(R.layout.single_movie, parent, false);
    return new PlayingHolder(v);
  }

  @SuppressLint("SetTextI18n") @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    PlayingHolder movieHolder = (PlayingHolder) holder;
    Picasso.with(movieHolder.itemView.getContext())
        .load(BASE_IMG_URL + getItematPosition(position).getPosterPath())
        .into(movieHolder.moviePoster);
    movieHolder.movieName.setText(getItematPosition(position).getTitle());
    movieHolder.movieProduction.setText("  " + getItematPosition(position).getDate());
    movieHolder.movieRating.setText("  " + getItematPosition(position).getVoteAverage() + "/10");
    Other.fadeAdapterAnimation(movieHolder.itemView.getContext(),movieHolder.itemView,position,-1);

  }

  class PlayingHolder extends BaseViewHolder {
    @BindView(R.id.single_movie_img) ImageView moviePoster;
    @BindView(R.id.single_movie_name) TextView movieName;
    @BindView(R.id.singe_movie_production_year) TextView movieProduction;
    @BindView(R.id.singe_movie_rating) TextView movieRating;

    PlayingHolder(View itemView) {
      super(itemView);
    }
    @OnClick()
    public void onClick(){
      itemClick.getMovieId(getItematPosition(getLayoutPosition()).getId(),getItematPosition(getLayoutPosition()).getBackDropImage());
    }
  }
}
