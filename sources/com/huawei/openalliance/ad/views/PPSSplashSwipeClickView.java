package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class PPSSplashSwipeClickView extends k {
    private LinearLayout h;

    @Override // com.huawei.openalliance.ad.views.k
    protected String getViewTag() {
        return "PPSSplashSwipeClickView";
    }

    public LinearLayout getClickAreaView() {
        return this.h;
    }

    private void a(Context context) {
        String str;
        ho.b("PPSSplashSwipeClickView", "init");
        try {
            this.f8109a = inflate(context, R.layout.hiad_layout_splash_swipe_click, this);
            this.h = (LinearLayout) this.f8109a.findViewById(R.id.swipe_click_area);
            this.f = (ImageView) this.f8109a.findViewById(R.id.hiad_click_arrow);
            this.b = (TextView) this.f8109a.findViewById(R.id.hiad_click_swipe_string);
            this.c = (TextView) this.f8109a.findViewById(R.id.hiad_click_swipe_desc);
            this.g = (ScanningView) this.f8109a.findViewById(R.id.hiad_scanning_view);
        } catch (RuntimeException unused) {
            str = "init RuntimeException";
            ho.c("PPSSplashSwipeClickView", str);
        } catch (Exception unused2) {
            str = "init error";
            ho.c("PPSSplashSwipeClickView", str);
        }
    }

    public PPSSplashSwipeClickView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public PPSSplashSwipeClickView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PPSSplashSwipeClickView(Context context) {
        super(context);
        a(context);
    }
}
