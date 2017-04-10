package com.cleveroad.cyclemenuwidget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Inner adapter for menu mItems.
 */
public abstract class RecyclerMenuAdapter<T, A extends RecyclerMenuAdapter.ItemHolder> extends RecyclerView.Adapter<A> implements OnMenuItemClickListener {

    private List<T> mItems;
    private OnMenuItemClickListener mOnMenuItemClickListener;

    private CycleMenuWidget.SCROLL mScrollType = CycleMenuWidget.SCROLL.BASIC;

    public RecyclerMenuAdapter() {
        mItems = new ArrayList<>();
    }

    /**
     * Set scroll type for menu
     *
     * @param scrollType the scroll type BASIC, ENDLESS
     */
    public void setScrollType(CycleMenuWidget.SCROLL scrollType) {
        mScrollType = scrollType;
    }

    /**
     * Set items Collection for the adapter
     *
     * @param items collections to be set to adapter
     */
    public void addAll(Collection<T> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * Set menu item click listener
     *
     * @param onMenuItemClickListener listener
     */
    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public OnMenuItemClickListener getOnMenuItemClickListener() {
        return mOnMenuItemClickListener;
    }

    /**
     * Add item to the adapter
     *
     * @param item that need to add to the adapter
     */
    public void add(T item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        //if scrollType is ENDLESS then need to set infinite scrolling
        if (mScrollType == CycleMenuWidget.SCROLL.ENDLESS) {
            return Integer.MAX_VALUE;
        }
        return mItems.size();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public int getRealItemsCount() {
        return mItems.size();
    }

    /**
     * Return real position of item in adapter. Is used when scrollType = ENDLESS.
     *
     * @param position position form adapter.
     * @return int realPosition of the item in adapter
     */
    private int getRealPosition(int position) {
        return position % mItems.size();
    }

    @Override
    public void onMenuItemClick(View view, int itemPosition) {
        if (mOnMenuItemClickListener != null) {
            mOnMenuItemClickListener.onMenuItemClick(view, getRealPosition(itemPosition));
        }
    }

    @Override
    public void onMenuItemLongClick(View view, int itemPosition) {
        if (mOnMenuItemClickListener != null) {
            mOnMenuItemClickListener.onMenuItemLongClick(view, getRealPosition(itemPosition));
        }
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private OnMenuItemClickListener mOnMenuItemClickListener;

        public ItemHolder(View itemView, OnMenuItemClickListener listener) {
            super(itemView);
            mOnMenuItemClickListener = listener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Resend click to the outer menu item click listener with provided item position. if scrollType is ENDLESS need to getRealPosition from the position.
            if (mOnMenuItemClickListener != null) {
                mOnMenuItemClickListener.onMenuItemClick(view, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mOnMenuItemClickListener != null) {
                mOnMenuItemClickListener.onMenuItemLongClick(view, getAdapterPosition());
            }
            return true;
        }
    }


}
