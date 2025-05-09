package com.huawei.ui.main.stories.me.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;

/* loaded from: classes9.dex */
public abstract class AdapterWithBottomView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "Track_AdapterWithBottomView";
    public static final int TYPE_BOTTOM = 100005;
    private LinearLayout mBottomLinearLayout;

    public abstract int getContentItem();

    public abstract int getContentItemType();

    public abstract void onBindChildViewHolder(RecyclerView.ViewHolder viewHolder, int i);

    public abstract RecyclerView.ViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i);

    public AdapterWithBottomView(Context context) {
        initView(context);
    }

    private void initView(Context context) {
        this.mBottomLinearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.bottomMargin = context.getResources().getDimensionPixelOffset(R.dimen._2131362973_res_0x7f0a049d);
        this.mBottomLinearLayout.setLayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 100005) {
            return new BottomViewHolder(this.mBottomLinearLayout);
        }
        return onCreateChildViewHolder(viewGroup, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder == null || (viewHolder instanceof BottomViewHolder)) {
            return;
        }
        onBindChildViewHolder(viewHolder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getContentItem() + 1;
    }

    public void addBottomView(View view) {
        if (view != null) {
            this.mBottomLinearLayout.addView(view);
        }
    }

    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View view) {
            super(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i > getContentItem() + (-1) ? TYPE_BOTTOM : getContentItemType();
    }
}
