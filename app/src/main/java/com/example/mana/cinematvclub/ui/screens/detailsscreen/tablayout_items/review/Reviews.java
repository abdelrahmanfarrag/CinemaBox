package com.example.mana.cinematvclub.ui.screens.detailsscreen.tablayout_items.review;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.bus.IdBus;
import com.example.mana.cinematvclub.model.models.MovieReview;
import com.example.mana.cinematvclub.ui.base.BaseFragment;
import com.squareup.picasso.Picasso;

import static com.example.mana.cinematvclub.utility.Constants.BASE_IMG_URL;

public class Reviews extends BaseFragment implements ReviewSetup {

  @BindView(R.id.reviews_container) ImageView reviewContainer;
  @BindView(R.id.review_count) TextView count;
  @BindView(R.id.review_next) ImageView next;
  @BindView(R.id.review_prev) ImageView prev;
  @BindView(R.id.review_current_total_preview) TextView numbers;
  @BindView(R.id.review_lists) RecyclerView reviews;
  @BindView(R.id.review_warning) TextView warning;

  ReviewPresenter presenter;

  public static Reviews newInstance() {
    return new Reviews();
  }

  @Override public int fragmentLayout() {
    return R.layout.reviews;
  }

  @Override public void init() {
    Picasso.with(getContext()).load(BASE_IMG_URL + IdBus.getPath())
        .into(reviewContainer);
    setupRecycler(reviews);
    presenter = new ReviewPresenter(getContext(), disposables, this, IdBus.getId());
  }

  private void setupRecycler(RecyclerView view) {
    view.setLayoutManager(new LinearLayoutManager(getContext()));
    view.setHasFixedSize(true);
  }

  @SuppressLint("SetTextI18n") @Override public void setReviewData(MovieReview model) {
    if (model!=null) {
      int totalReviews = model.getTotalReviews();
      int currentPage = model.getCurrentPage();
      int totalPage = model.getTotalPages();
      count.setText("There are " + totalReviews + " reviews");
      numbers.setText(currentPage + " |" + totalPage);
      presenter.checkCurrentPageVsPreviousPage(currentPage, totalPage, totalReviews, next, prev,
          numbers, count, reviews, warning);
      reviews.setAdapter(new ReviewAdapter(model.getReviews()));
    }
  }
}
