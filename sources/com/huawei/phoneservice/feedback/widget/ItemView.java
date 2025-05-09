package com.huawei.phoneservice.feedback.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.huawei.phoneservice.faq.base.util.o;
import com.huawei.uikit.hwresources.R$dimen;

/* loaded from: classes5.dex */
public class ItemView extends RelativeLayout {
    private Context b;

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        o.cdn_(this, this.b.getResources().getDimensionPixelOffset(R$dimen.emui_dimens_max_start));
    }

    public ItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
    }

    public ItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ItemView(Context context) {
        this(context, null);
    }
}
