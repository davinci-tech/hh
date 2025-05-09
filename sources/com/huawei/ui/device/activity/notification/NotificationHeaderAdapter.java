package com.huawei.ui.device.activity.notification;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes6.dex */
public class NotificationHeaderAdapter extends RecyclerView.Adapter<HeaderViewHolder> {
    private View b;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(HeaderViewHolder headerViewHolder, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 1;
    }

    public NotificationHeaderAdapter(View view) {
        this.b = view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cRj_, reason: merged with bridge method [inline-methods] */
    public HeaderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HeaderViewHolder(this.b);
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }
}
