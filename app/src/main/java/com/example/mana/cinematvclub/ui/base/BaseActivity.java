package com.example.mana.cinematvclub.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity extends AppCompatActivity {

  private Activity context;
  protected CompositeDisposable disposables = new CompositeDisposable();
  public Activity getContext() {
    return context;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(setLayout());
    context = this;
    ButterKnife.bind(this);

  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    init(savedInstanceState);
    setupToolbar();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (disposables.isDisposed()) {
      disposables.clear();
    }
  }

  public abstract @LayoutRes int setLayout();

  public abstract void init(Bundle bundle);

  public abstract void setupToolbar();
}
