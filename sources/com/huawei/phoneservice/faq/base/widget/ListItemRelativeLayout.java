package com.huawei.phoneservice.faq.base.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.huawei.phoneservice.faq.base.util.o;
import com.huawei.uikit.hwresources.R$dimen;

/* loaded from: classes5.dex */
public class ListItemRelativeLayout extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f8245a;

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        o.cdm_(this, this.f8245a.getResources().getDimensionPixelSize(R$dimen.emui_dimens_max_start), this.f8245a.getResources().getDimensionPixelSize(R$dimen.emui_dimens_max_end));
        super.onConfigurationChanged(configuration);
    }

    private void d(Context context) {
        this.f8245a = context;
    }

    public ListItemRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d(context);
    }

    public ListItemRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ListItemRelativeLayout(Context context) {
        this(context, null);
    }
}
