package com.huawei.health.h5pro.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import java.util.List;

/* loaded from: classes8.dex */
public class FloatingBarAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    public List<FloatingBarItem> d;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.share_dist_entry, viewGroup, false));
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView d;
        public TextView e;

        public ItemViewHolder(View view) {
            super(view);
            this.e = (TextView) view.findViewById(R.id.share_tv);
            this.d = (ImageView) view.findViewById(R.id.share_img);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        FloatingBarItem floatingBarItem = this.d.get(i);
        itemViewHolder.e.setText(floatingBarItem.getDistName());
        itemViewHolder.d.setImageDrawable(floatingBarItem.getDistIcon());
        itemViewHolder.d.setOnClickListener(floatingBarItem.getClickCallback());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    public FloatingBarAdapter(List<FloatingBarItem> list) {
        this.d = list;
    }
}
