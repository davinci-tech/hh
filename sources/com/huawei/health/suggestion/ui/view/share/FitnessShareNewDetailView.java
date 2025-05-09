package com.huawei.health.suggestion.ui.view.share;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.nsn;

/* loaded from: classes4.dex */
public class FitnessShareNewDetailView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f3427a;
    private Context b;

    public FitnessShareNewDetailView(Context context) {
        super(context);
        this.b = context;
        b();
    }

    public FitnessShareNewDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        b();
    }

    private void b() {
        Object systemService = this.b.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        Point point = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getSize(point);
        }
        this.f3427a = point.x;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.f3427a, 1073741824), View.MeasureSpec.makeMeasureSpec(this.f3427a + nsn.c(this.b, 48.0f), 1073741824));
    }
}
