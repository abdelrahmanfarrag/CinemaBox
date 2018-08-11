package com.example.mana.cinematvclub.ui.screens;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.cinematvclub.R;
import com.example.mana.cinematvclub.ui.base.BaseActivity;
import com.example.mana.cinematvclub.ui.screens.maincontentactivity.MainContentScreen;
import com.example.mana.cinematvclub.utility.ActivityUtil;

public class SplashScreen extends BaseActivity {

  @BindView(R.id.arabic_version) Button arabicButton;
  @BindView(R.id.english_version) Button englishButton;

  @BindString(R.string.app_name) String appName;
  @BindString(R.string.exit) String dialogTitle;
  @BindString(R.string.exit_msg) String msg;
  @BindString(R.string.yes) String yes;
  @BindString(R.string.no) String no;

  @Override public int setLayout() {
    return R.layout.activity_main;
  }

  @Override public void init(Bundle savedState) {

  }

  @OnClick({ R.id.arabic_version, R.id.english_version })
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.arabic_version:
        ActivityUtil.languageToLoad("ar", getContext(), MainContentScreen.class);
        break;
      case R.id.english_version:
        ActivityUtil.languageToLoad("eng", getContext(), MainContentScreen.class);
        break;
    }
  }

  @Override public void onBackPressed() {
    new AlertDialog.Builder(getContext()).setTitle(dialogTitle)
        .setMessage(msg + " " + appName)
        .setPositiveButton(yes,
            (dialog, which) -> finish())
        .setNegativeButton(no, (dialog, which) -> dialog.dismiss())
        .show();
  }

  @Override public void setupToolbar() {

  }
}