package com.google.android.gms.common.internal;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.common.util.DeviceProperties;

/* loaded from: classes8.dex */
public final class SignInButtonImpl extends Button {
    public SignInButtonImpl(Context context) {
        this(context, null);
    }

    public SignInButtonImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyle);
    }

    public final void configure(Resources resources, SignInButtonConfig signInButtonConfig) {
        configure(resources, signInButtonConfig.getButtonSize(), signInButtonConfig.getColorScheme());
    }

    public final void configure(Resources resources, int i, int i2) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        int i3 = (int) ((resources.getDisplayMetrics().density * 48.0f) + 0.5f);
        setMinHeight(i3);
        setMinWidth(i3);
        int zaa = zaa(i2, com.huawei.health.R.drawable._2131427807_res_0x7f0b01df, com.huawei.health.R.drawable._2131427812_res_0x7f0b01e4, com.huawei.health.R.drawable._2131427812_res_0x7f0b01e4);
        int zaa2 = zaa(i2, com.huawei.health.R.drawable._2131427816_res_0x7f0b01e8, com.huawei.health.R.drawable._2131427821_res_0x7f0b01ed, com.huawei.health.R.drawable._2131427821_res_0x7f0b01ed);
        if (i == 0 || i == 1) {
            zaa = zaa2;
        } else if (i != 2) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Unknown button size: ");
            sb.append(i);
            throw new IllegalStateException(sb.toString());
        }
        Drawable wrap = DrawableCompat.wrap(resources.getDrawable(zaa));
        DrawableCompat.setTintList(wrap, resources.getColorStateList(com.huawei.health.R.color._2131296965_res_0x7f0902c5));
        DrawableCompat.setTintMode(wrap, PorterDuff.Mode.SRC_ATOP);
        setBackgroundDrawable(wrap);
        setTextColor((ColorStateList) Preconditions.checkNotNull(resources.getColorStateList(zaa(i2, com.huawei.health.R.color._2131296955_res_0x7f0902bb, com.huawei.health.R.color._2131296960_res_0x7f0902c0, com.huawei.health.R.color._2131296960_res_0x7f0902c0))));
        if (i == 0) {
            setText(resources.getString(com.huawei.health.R.string._2130850807_res_0x7f0233f7));
        } else if (i == 1) {
            setText(resources.getString(com.huawei.health.R.string._2130850808_res_0x7f0233f8));
        } else if (i == 2) {
            setText((CharSequence) null);
        } else {
            StringBuilder sb2 = new StringBuilder(32);
            sb2.append("Unknown button size: ");
            sb2.append(i);
            throw new IllegalStateException(sb2.toString());
        }
        setTransformationMethod(null);
        if (DeviceProperties.isWearable(getContext())) {
            setGravity(19);
        }
    }

    private static int zaa(int i, int i2, int i3, int i4) {
        if (i == 0) {
            return i2;
        }
        if (i == 1) {
            return i3;
        }
        if (i == 2) {
            return i4;
        }
        StringBuilder sb = new StringBuilder(33);
        sb.append("Unknown color scheme: ");
        sb.append(i);
        throw new IllegalStateException(sb.toString());
    }
}
