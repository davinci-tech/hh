package com.huawei.ui.commonui.adapter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrf;

/* loaded from: classes6.dex */
public class RecyclerHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private final SparseArray<View> f8761a;

    public RecyclerHolder(View view) {
        super(view);
        this.f8761a = new SparseArray<>(10);
    }

    public <V extends View> V cwK_(int i) {
        V v = (V) this.f8761a.get(i);
        if (v != null) {
            return v;
        }
        V v2 = (V) this.itemView.findViewById(i);
        this.f8761a.put(i, v2);
        return v2;
    }

    public RecyclerHolder c(int i, CharSequence charSequence) {
        HealthTextView healthTextView = (HealthTextView) cwK_(i);
        if (healthTextView != null) {
            healthTextView.setText(charSequence);
        }
        return this;
    }

    public RecyclerHolder c(int i, int i2) {
        HealthTextView healthTextView = (HealthTextView) cwK_(i);
        if (healthTextView != null) {
            healthTextView.setTextColor(i2);
        }
        return this;
    }

    public RecyclerHolder b(int i, String str) {
        HealthTextView healthTextView = (HealthTextView) cwK_(i);
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
        return this;
    }

    public RecyclerHolder b(int i, int i2) {
        HealthTextView healthTextView = (HealthTextView) cwK_(i);
        if (healthTextView != null) {
            try {
                healthTextView.setText(healthTextView.getContext().getResources().getString(i2));
            } catch (Resources.NotFoundException unused) {
                healthTextView.setText("");
            }
        }
        return this;
    }

    public RecyclerHolder d(int i, int i2) {
        ImageView imageView = (ImageView) cwK_(i);
        if (imageView != null) {
            imageView.setImageResource(i2);
        }
        return this;
    }

    public RecyclerHolder cwN_(int i, Drawable drawable) {
        ImageView imageView;
        if (drawable != null && (imageView = (ImageView) cwK_(i)) != null) {
            imageView.setImageDrawable(drawable);
        }
        return this;
    }

    public RecyclerHolder cwM_(int i, Bitmap bitmap) {
        ImageView imageView;
        if (bitmap != null && (imageView = (ImageView) cwK_(i)) != null) {
            imageView.setImageBitmap(bitmap);
        }
        return this;
    }

    public RecyclerHolder b(int i, String str, int i2) {
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith("http")) {
                nrf.cHI_(str, (ImageView) cwK_(i), i2);
            } else {
                a(i, str);
            }
        }
        return this;
    }

    private RecyclerHolder a(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            nrf.cJy_(str, (ImageView) cwK_(i));
        }
        return this;
    }

    public RecyclerHolder cwP_(int i, View.OnClickListener onClickListener) {
        View cwK_ = cwK_(i);
        if (cwK_ != null && onClickListener != null) {
            cwK_.setOnClickListener(onClickListener);
        }
        return this;
    }

    public RecyclerHolder cwQ_(int i, View.OnLongClickListener onLongClickListener) {
        View cwK_ = cwK_(i);
        if (cwK_ != null && onLongClickListener != null) {
            cwK_.setOnLongClickListener(onLongClickListener);
        }
        return this;
    }

    public RecyclerHolder cwO_(int i, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        CompoundButton compoundButton = (CompoundButton) cwK_(i);
        if (compoundButton != null) {
            compoundButton.setOnCheckedChangeListener(onCheckedChangeListener);
        }
        return this;
    }

    public RecyclerHolder a(int i, int i2) {
        if (cwK_(i) != null) {
            cwK_(i).setVisibility(i2);
        }
        return this;
    }

    public RecyclerHolder a(int i, boolean z) {
        CompoundButton compoundButton = (CompoundButton) cwK_(i);
        if (compoundButton != null) {
            compoundButton.setChecked(z);
        }
        return this;
    }

    public RecyclerHolder cwL_(int i, Drawable drawable) {
        View cwK_ = cwK_(i);
        if (cwK_ != null) {
            cwK_.setBackground(drawable);
        }
        return this;
    }
}
