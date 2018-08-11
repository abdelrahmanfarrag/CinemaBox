package com.example.mana.cinematvclub.ui.screens.detailsscreen;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;
import butterknife.BindView;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.model.bus.IdBus;
import com.example.mana.cinematvclub.model.bus.PlayingBus;
import com.example.mana.cinematvclub.model.bus.message;
import com.example.mana.cinematvclub.model.models.PlayingMovies;
import com.example.mana.cinematvclub.ui.base.BaseActivity;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

import static com.example.mana.cinematvclub.utility.Constants.BACKDROP_PATH;
import static com.example.mana.cinematvclub.utility.Constants.MOVIE_ID;

public class MovieDetailsScreen extends BaseActivity {
  @BindView(R.id.tabs_pager) ViewPager pager;
  @BindView(R.id.tab_layout) TabLayout tabLayout;

  @Override public int setLayout() {
    return R.layout.movie_detail_screen;
  }

  @Override public void init(Bundle savedState) {
    managerIncomingData();
    tabSetup();
  }

  @Override public void setupToolbar() {

  }

  private void managerIncomingData() {
    int id = getIntent().getExtras().getInt(MOVIE_ID);
    String path = getIntent().getExtras().getString(BACKDROP_PATH);
    IdBus.setId(id);
    IdBus.setPath(path);
  }

  private void tabSetup() {
    TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
    pager.setAdapter(adapter);
    tabLayout.setupWithViewPager(pager);
  }
}
