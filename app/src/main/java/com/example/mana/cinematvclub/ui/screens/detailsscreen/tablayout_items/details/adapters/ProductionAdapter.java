package com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.details.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.models.Details;
import com.example.mana.cinematvclub.ui.base.BaseRecyclerAdapter;
import com.example.mana.cinematvclub.ui.base.BaseViewHolder;
import com.example.mana.cinematvclub.utility.Other;
import com.squareup.picasso.Picasso;
import java.util.List;

import static com.example.mana.cinematvclub.utility.Constants.BASE_IMG_URL;

public class ProductionAdapter
    extends BaseRecyclerAdapter<Details.ProductionCompanies, BaseViewHolder> {

  public ProductionAdapter(
      List<Details.ProductionCompanies> recyclerItems) {
    super(recyclerItems);
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = getLayoutInflater(parent.getContext()).inflate(R.layout.single_production_item, parent,
        false);
    return new CollectionHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    CollectionHolder hold = (CollectionHolder) holder;
    Picasso.with(hold.itemView.getContext())
        .load(BASE_IMG_URL + getItematPosition(position).getLogo())
        .into(hold.image);
    hold.name.setText(getItematPosition(position).getName());

    Other.fadeAdapterAnimation(hold.itemView.getContext(),hold.itemView,position,-1);
  }

  class CollectionHolder extends BaseViewHolder {
    @BindView(R.id.collection_image) ImageView image;
    @BindView(R.id.movie_collection_name) TextView name;

    CollectionHolder(View itemView) {
      super(itemView);
    }
  }
}
