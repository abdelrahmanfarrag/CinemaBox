package com.example.mana.cinematvclub.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import butterknife.BindColor;
import com.example.mana.cinematvclub.R;

public class ThinTextView extends AppCompatTextView {


  public ThinTextView(Context context) {
    super(context);
    if (this.getTypeface() == null) {
      initThinTv(context);
    }
    this.setTextSize(15);
  }

  public ThinTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ThinTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private void initThinTv(Context ctx) {
    Typeface face = Typeface.createFromAsset(ctx.getAssets(), "benchnine_light.xml");
    this.setTypeface(face);
  }
}
