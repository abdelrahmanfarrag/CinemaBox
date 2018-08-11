package com.example.mana.cinematvclub.ui.screens.maincontentactivity.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.bus.IdBus;
import com.example.mana.cinematvclub.model.models.Hollywood;
import com.example.mana.cinematvclub.ui.base.BaseRecyclerAdapter;
import com.example.mana.cinematvclub.ui.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import java.util.List;

import static com.example.mana.cinematvclub.utility.Constants.BASE_IMG_URL;

public class HollywoodAdapter extends BaseRecyclerAdapter<Hollywood.Result, BaseViewHolder> {
  private OnStarClick click;

  public HollywoodAdapter(
      List<Hollywood.Result> recyclerItems, OnStarClick click) {
    super(recyclerItems);
    this.click = click;
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = getLayoutInflater(parent.getContext()).inflate(R.layout.single_movie, parent, false);
    return new HollywoodHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    HollywoodHolder hold = (HollywoodHolder) holder;
    hold.movieRating.setVisibility(View.GONE);
    hold.movieProduction.setVisibility(View.GONE);
    Picasso.with(hold.itemView.getContext())
        .load(BASE_IMG_URL + getItematPosition(position).getPath())
        .into(hold.starPoster);
    hold.starName.setText(getItematPosition(position).getName());
  }

  class HollywoodHolder extends BaseViewHolder {
    @BindView(R.id.single_movie_img) ImageView starPoster;
    @BindView(R.id.single_movie_name) TextView starName;
    @BindView(R.id.singe_movie_production_year) TextView movieProduction;
    @BindView(R.id.singe_movie_rating) TextView movieRating;

    HollywoodHolder(View itemView) {
      super(itemView);
    }

    @OnClick()
    public void onItemClick() {
      click.starId(getItematPosition(getLayoutPosition()).getId());
      IdBus.setName(getItematPosition(getLayoutPosition()).getName());
      IdBus.setPath(getItematPosition(getLayoutPosition()).getPath());
    }
  }
}
