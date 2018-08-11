package com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.recommendation;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.models.RecommendMovies;
import com.example.mana.cinematvclub.ui.base.BaseRecyclerAdapter;
import com.example.mana.cinematvclub.ui.base.BaseViewHolder;
import com.example.mana.cinematvclub.ui.screens.maincontentactivity.adapter.OnSingleMovieClick;
import com.example.mana.cinematvclub.utility.Other;
import com.squareup.picasso.Picasso;
import java.util.List;

import static com.example.mana.cinematvclub.utility.Constants.BASE_IMG_URL;

public class RecommendAdapter extends BaseRecyclerAdapter<RecommendMovies.Results, BaseViewHolder> {

  private OnSingleMovieClick click;

  RecommendAdapter(
      List<RecommendMovies.Results> recyclerItems, OnSingleMovieClick click) {
    super(recyclerItems);
    this.click = click;
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = getLayoutInflater(parent.getContext()).inflate(R.layout.single_details_movie, parent,
        false);
    return new RecommendHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    RecommendHolder hold = (RecommendHolder) holder;
    Picasso.with(hold.itemView.getContext())
        .load(BASE_IMG_URL + getItematPosition(position).getPath())
        .into(hold.image);
    hold.name.setText(getItematPosition(position).getTitle());
    Other.adapterAnimation(hold.itemView.getContext(),hold.itemView,position,-1);
  }

  class RecommendHolder extends BaseViewHolder {
    @BindView(R.id.single_details_movie_image) ImageView image;
    @BindView(R.id.single_details_movie_name) TextView name;

    RecommendHolder(View itemView) {
      super(itemView);
    }

    @OnClick()
    public void onClick() {
      click.getMovieId(getItematPosition(getLayoutPosition()).getId(),
          getItematPosition(getLayoutPosition()).getPath());
    }
  }
}
