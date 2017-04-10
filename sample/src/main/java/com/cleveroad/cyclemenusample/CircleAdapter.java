package com.cleveroad.cyclemenusample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleveroad.cyclemenuwidget.OnMenuItemClickListener;
import com.cleveroad.cyclemenuwidget.RecyclerMenuAdapter;

public class CircleAdapter extends RecyclerMenuAdapter<String, CircleAdapter.ViewHolder> {

    private LayoutInflater inflater;

    public CircleAdapter(Context context) {
        super();
        inflater = LayoutInflater.from(context);

        for (int i = 0; i < 20; i++) {
            add("x");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fab_item, parent, false);
        return new ViewHolder(view, getOnMenuItemClickListener());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    static class ViewHolder extends RecyclerMenuAdapter.ItemHolder {

        ViewHolder(View itemView, OnMenuItemClickListener listener) {
            super(itemView, listener);
        }
    }
}
