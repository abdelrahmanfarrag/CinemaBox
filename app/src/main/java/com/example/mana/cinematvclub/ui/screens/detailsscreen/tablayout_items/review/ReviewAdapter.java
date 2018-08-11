package com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.review;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.models.MovieReview;
import com.example.mana.cinematvclub.ui.base.BaseRecyclerAdapter;
import com.example.mana.cinematvclub.ui.base.BaseViewHolder;
import com.example.mana.cinematvclub.utility.Other;
import java.util.List;

public class ReviewAdapter extends BaseRecyclerAdapter<MovieReview.Reviews, BaseViewHolder> {
  ReviewAdapter(
      List<MovieReview.Reviews> recyclerItems) {
    super(recyclerItems);
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = getLayoutInflater(parent.getContext()).inflate(R.layout.single_review, parent, false);

    return new ReviewHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    ReviewHolder hold = (ReviewHolder) holder;
    hold.name.setText(getItematPosition(position).getAuthorName());
    hold.content.setText(getItematPosition(position).getContent());
    Other.fadeAdapterAnimation(hold.itemView.getContext(),hold.itemView,position,-1);
  }

  class ReviewHolder extends BaseViewHolder {
    @BindView(R.id.author_name) TextView name;
    @BindView(R.id.author_review) TextView content;

    ReviewHolder(View itemView) {
      super(itemView);
    }
  }
}
