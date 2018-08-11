package com.example.mana.cinematvclub.ui.screens.nightview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.models.PlayingMovies;
import com.example.mana.cinematvclub.model.models.PouplarMovies;
import com.example.mana.cinematvclub.model.models.RatedMovies;
import com.example.mana.cinematvclub.model.models.UpComingMovies;
import com.example.mana.cinematvclub.ui.base.BaseActivity;
import com.example.mana.cinematvclub.ui.screens.nightview.adapters.UpcomingDarkAdapter;
import com.example.mana.cinematvclub.ui.screens.nightview.adapters.PlayingDarkAdapter;
import com.example.mana.cinematvclub.ui.screens.nightview.adapters.PopularDarkAdapter;
import com.example.mana.cinematvclub.ui.screens.nightview.adapters.RatedDarkAdapter;
import java.util.List;

import static com.example.mana.cinematvclub.utility.Constants.MOVIES_LIST;
import static com.example.mana.cinematvclub.utility.Constants.POPULAR_LIST;
import static com.example.mana.cinematvclub.utility.Constants.RATED_LIST;
import static com.example.mana.cinematvclub.utility.Constants.UPCOMING_LIST;

public class ShowlistInDarkMode extends BaseActivity {

  @BindView(R.id.pager_movies) ViewPager pagerData;
  @BindView(R.id.pause_mov) ImageView stopViewinDark;
  @BindView(R.id.mov_next) ImageView next;
  @BindView(R.id.prev_mov) ImageView prev;
  @BindView(R.id.pager_item_cont) TextView count;

  List<PlayingMovies.Results> movies;
  List<PouplarMovies.Results> popular;
  List<UpComingMovies.Results> upcoming;
  List<RatedMovies.Results> rated;

  @Override public int setLayout() {
    return R.layout.preview_movie_night;
  }

  @Override public void init(Bundle savedState) {
    movies = getIntent().getParcelableArrayListExtra(MOVIES_LIST);
    popular = getIntent().getParcelableArrayListExtra(POPULAR_LIST);
    upcoming=getIntent().getParcelableArrayListExtra(UPCOMING_LIST);
    rated=getIntent().getParcelableArrayListExtra(RATED_LIST);
    setupPager();
  }

  private void setupPager() {
    if (movies != null) {
      PlayingDarkAdapter adapter = new PlayingDarkAdapter(getContext(), movies);
      pagerData.setAdapter(adapter);
    } else if (popular != null) {
      PopularDarkAdapter adapter = new PopularDarkAdapter(getContext(), popular);
      pagerData.setAdapter(adapter);
    }else if (upcoming!=null){
      UpcomingDarkAdapter adapter = new UpcomingDarkAdapter(getContext(),upcoming);
      pagerData.setAdapter(adapter);
    }else if (rated!=null){
      RatedDarkAdapter adapter = new RatedDarkAdapter(getContext(),rated);
      pagerData.setAdapter(adapter);
    }
    pagerData.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @SuppressLint("SetTextI18n") @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 0) {
          prev.setVisibility(View.INVISIBLE);
        } else if (position == 19) {
          next.setVisibility(View.INVISIBLE);
        } else {
          prev.setVisibility(View.VISIBLE);
          next.setVisibility(View.VISIBLE);
        }
        if (movies != null) {
          count.setText(position + 1 + " |" + movies.size());
        } else if (popular != null) {
          count.setText(position + 1 + " |" + popular.size());
        }else if (upcoming!=null){
          count.setText(position + 1 + " |" + upcoming.size()); }
          else if (rated!=null){
          count.setText(position + 1 + " |" + rated.size());
        }
      }

      @Override public void onPageSelected(int position) {

      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });
  }

  @OnClick({ R.id.pause_mov, R.id.mov_next, R.id.prev_mov })
  public void Onclick(View v) {
    switch (v.getId()) {
      case R.id.pause_mov:
        onBackPressed();
        break;
      case R.id.mov_next:
        pagerData.setCurrentItem(getNextItem(1), true);
        break;
      case R.id.prev_mov:
        pagerData.setCurrentItem(getNextItem(-1), true);
    }
  }

  private int getNextItem(int next) {
    return pagerData.getCurrentItem() + next;
  }

  @Override public void setupToolbar() {

  }
}
