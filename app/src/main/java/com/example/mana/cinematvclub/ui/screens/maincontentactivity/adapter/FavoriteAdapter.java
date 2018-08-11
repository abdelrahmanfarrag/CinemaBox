package com.example.mana.cinematvclub.ui.screens.maincontentactivity.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.database.Favorite;
import com.example.mana.cinematvclub.ui.base.BaseRecyclerAdapter;
import com.example.mana.cinematvclub.ui.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import java.util.List;

public class FavoriteAdapter extends BaseRecyclerAdapter<Favorite, BaseViewHolder> {
  private FavoriteClick click;

  public FavoriteAdapter(
      List<Favorite> recyclerItems, FavoriteClick click) {
    super(recyclerItems);
    this.click = click;
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = getLayoutInflater(parent.getContext()).inflate(R.layout.single_movie, parent, false);
    return new FavoriteHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    FavoriteHolder hold = (FavoriteHolder) holder;
    Picasso.with(hold.itemView.getContext())
        .load(getItematPosition(position).getImage()).into(hold.img);
    hold.name.setText(getItematPosition(position).getName());
    hold.rating.setVisibility(View.INVISIBLE);
    hold.date.setVisibility(View.INVISIBLE);
  }

  class FavoriteHolder extends BaseViewHolder {
    @BindView(R.id.single_movie_img) ImageView img;
    @BindView(R.id.single_movie_name) TextView name;
    @BindView(R.id.singe_movie_rating) TextView rating;
    @BindView(R.id.singe_movie_production_year) TextView date;

    FavoriteHolder(View itemView) {
      super(itemView);
    }

    @OnLongClick
    public boolean onLongClick() {
      click.favoriteLongClick(getItematPosition(getLayoutPosition()).getMovieId(),
          getItematPosition(getLayoutPosition()).getName());
      return true;
    }

    @OnClick
    public void onClick() {
      click.favoriteNormalClcik(getItematPosition(getLayoutPosition()).getMovieId(),
          getItematPosition(getLayoutPosition()).getImage());
    }
  }
}
