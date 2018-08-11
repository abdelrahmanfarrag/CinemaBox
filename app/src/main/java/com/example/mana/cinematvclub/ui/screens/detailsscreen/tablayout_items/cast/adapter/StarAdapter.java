package com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.cast.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.models.StarMovie;
import com.example.mana.cinematvclub.ui.base.BaseRecyclerAdapter;
import com.example.mana.cinematvclub.ui.base.BaseViewHolder;
import com.example.mana.cinematvclub.ui.screens.detailsscreen.MovieDetailsScreen;
import com.example.mana.cinematvclub.utility.ActivityUtil;
import com.example.mana.cinematvclub.utility.Other;
import com.squareup.picasso.Picasso;
import java.util.List;

import static com.example.mana.cinematvclub.utility.Constants.BACKDROP_PATH;
import static com.example.mana.cinematvclub.utility.Constants.BASE_IMG_URL;
import static com.example.mana.cinematvclub.utility.Constants.MOVIE_ID;

public class StarAdapter extends BaseRecyclerAdapter<StarMovie.Cast, BaseViewHolder> {
  public StarAdapter(
      List<StarMovie.Cast> recyclerItems) {
    super(recyclerItems);
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = getLayoutInflater(parent.getContext()).inflate(R.layout.single_details_movie, parent,
        false);
    return new StarHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    StarHolder hold = (StarHolder) holder;
    Picasso.with(hold.itemView.getContext())
        .load(BASE_IMG_URL + getItematPosition(position).getPosterPath())
        .into(hold.image);
    hold.name.setText(getItematPosition(position).getTitle());
    Other.adapterAnimation(hold.itemView.getContext(), hold.itemView, position, -1);
  }

  class StarHolder extends BaseViewHolder {
    @BindView(R.id.single_details_movie_image) ImageView image;
    @BindView(R.id.single_details_movie_name) TextView name;

    StarHolder(View itemView) {
      super(itemView);
    }

    @OnClick()
    public void onClick() {
      Intent in = new Intent(itemView.getContext(), MovieDetailsScreen.class);
      int id = getItematPosition(getLayoutPosition()).getId();
      String path = getItematPosition(getLayoutPosition()).getBackdropPath();
      in.putExtra(MOVIE_ID, id);
      in.putExtra(BACKDROP_PATH, path);
      in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
      ActivityUtil.intentWithData(itemView.getContext(), in);
    }
  }
}
