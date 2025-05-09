package com.google.android.material.tabs;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.TintTypedArray;

/* loaded from: classes2.dex */
public class TabItem extends View {
    public final int customLayout;
    public final Drawable icon;
    public final CharSequence text;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, new int[]{R.attr.icon, R.attr.layout, R.attr.text});
        this.text = obtainStyledAttributes.getText(2);
        this.icon = obtainStyledAttributes.getDrawable(0);
        this.customLayout = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
    }
}
