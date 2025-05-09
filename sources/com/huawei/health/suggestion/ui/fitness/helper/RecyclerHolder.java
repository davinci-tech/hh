package com.huawei.health.suggestion.ui.fitness.helper;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrf;

/* loaded from: classes4.dex */
public class RecyclerHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> c;

    public RecyclerHolder(View view) {
        super(view);
        this.c = new SparseArray<>(10);
    }

    public <V extends View> V aCA_(int i) {
        V v = (V) this.c.get(i);
        if (v != null) {
            return v;
        }
        V v2 = (V) this.itemView.findViewById(i);
        this.c.put(i, v2);
        return v2;
    }

    public RecyclerHolder e(int i, String str) {
        HealthTextView healthTextView = (HealthTextView) aCA_(i);
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
        return this;
    }

    public RecyclerHolder e(int i, int i2) {
        View aCA_ = aCA_(i);
        if (aCA_ != null) {
            aCA_.setVisibility(i2);
        }
        return this;
    }

    public RecyclerHolder e(int i, String str, int i2) {
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith("http")) {
                nrf.cHI_(str, (ImageView) aCA_(i), i2);
            } else {
                b(i, str);
            }
        }
        return this;
    }

    private RecyclerHolder b(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            nrf.cJy_(str, (ImageView) aCA_(i));
        }
        return this;
    }

    public RecyclerHolder aCB_(int i, View.OnClickListener onClickListener) {
        View aCA_ = aCA_(i);
        if (aCA_ != null) {
            aCA_.setOnClickListener(onClickListener);
        }
        return this;
    }

    public RecyclerHolder d(int i, int i2) {
        View aCA_ = aCA_(i);
        if (aCA_ != null) {
            aCA_.setVisibility(i2);
        }
        return this;
    }
}
