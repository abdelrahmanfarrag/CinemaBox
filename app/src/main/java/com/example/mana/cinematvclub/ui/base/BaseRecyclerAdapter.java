package com.example.mana.cinematvclub.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import java.util.List;

public abstract class BaseRecyclerAdapter<T, H extends BaseViewHolder>
    extends RecyclerView.Adapter<H> {
  private List<T> recyclerItems;

  public BaseRecyclerAdapter(List<T> recyclerItems) {
    this.recyclerItems = recyclerItems;
  }

  protected LayoutInflater getLayoutInflater(Context context) {
    return LayoutInflater.from(context);
  }

  protected T getItematPosition(int position) {
    return recyclerItems.get(position);
  }

  @Override public int getItemCount() {
    return recyclerItems.size();
  }
}
