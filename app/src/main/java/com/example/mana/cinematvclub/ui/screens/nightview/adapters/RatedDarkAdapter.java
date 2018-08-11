package com.example.mana.cinematvclub.ui.screens.nightview.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.models.RatedMovies;
import com.example.mana.cinematvclub.ui.screens.detailsscreen.MovieDetailsScreen;
import com.example.mana.cinematvclub.utility.ActivityUtil;
import com.squareup.picasso.Picasso;
import java.util.List;

import static com.example.mana.cinematvclub.utility.Constants.BACKDROP_PATH;
import static com.example.mana.cinematvclub.utility.Constants.BASE_IMG_URL;
import static com.example.mana.cinematvclub.utility.Constants.MOVIE_ID;

public class RatedDarkAdapter extends PagerAdapter {
  private Context context;
  private List<RatedMovies.Results> results;

  public RatedDarkAdapter(Context context, List<RatedMovies.Results> results) {
    this.context = context;
    this.results = results;
  }

  @Override public int getCount() {
    return results.size();
  }

  @Override public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == object;
  }

  @SuppressLint("SetTextI18n") @SuppressWarnings("ConstantConditions") @NonNull @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View v = inflater.inflate(R.layout.single_preview_movie, container, false);
    ImageView backDropImage = v.findViewById(R.id.item_backdrop);
    ImageView posterImage = v.findViewById(R.id.item_poster);
    TextView movieTitle = v.findViewById(R.id.item_title);
    TextView movieOverview = v.findViewById(R.id.item_overview);
    TextView movieRating = v.findViewById(R.id.item_rating);

    Picasso.with(context)
        .load(BASE_IMG_URL + results.get(position).getPosterPath())
        .into(posterImage);
    Picasso.with(context)
        .load(BASE_IMG_URL + results.get(position).getBackDropImage())
        .into(backDropImage);
    movieTitle.setText(results.get(position).getTitle());
    movieOverview.setText(results.get(position).getOverView());
    movieRating.setText("  " + results.get(position).getVoteAverage() + "/10");
    v.setOnClickListener(v1 -> {
      Intent in = new Intent(context, MovieDetailsScreen.class);
      in.putExtra(MOVIE_ID, results.get(position).getId());
      in.putExtra(BACKDROP_PATH, results.get(position).getBackDropImage());
      ActivityUtil.intentWithData(context, in);
    });
    container.addView(v);
    return v;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView(container);
  }
}
