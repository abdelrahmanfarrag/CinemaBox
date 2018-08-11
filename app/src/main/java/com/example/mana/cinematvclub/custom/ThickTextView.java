package com.example.mana.cinematvclub.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import butterknife.BindColor;
import com.example.mana.cinematvclub.R;

public class ThickTextView extends AppCompatTextView {

  @BindColor(R.color.orange_a) int orange;

  public ThickTextView(Context context) {
    super(context);
    if (this.getTypeface() == null) {
      initTypeFace(context);
    }
    this.setTextSize(15);
    this.setTextColor(orange);
  }

  public ThickTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    if (this.getTypeface() == null) {
      initTypeFace(context);
    }
  }

  public ThickTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    if (this.getTypeface() == null) {
      initTypeFace(context);
    }
  }

  private void initTypeFace(Context ctx) {
    Typeface face = Typeface.createFromAsset(ctx.getAssets(), "bowlby_one.xml");
    this.setTypeface(face);
  }
}
