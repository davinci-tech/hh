package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class PPSSplashSwipeView extends k {
    @Override // com.huawei.openalliance.ad.views.k
    protected String getViewTag() {
        return "PPSSplashSwipeView";
    }

    private void a(Context context) {
        String str;
        ho.b("PPSSplashSwipeView", "init");
        try {
            this.f8109a = inflate(context, R.layout.hiad_layout_splash_swipe, this);
            this.b = (TextView) this.f8109a.findViewById(R.id.hiad_swipe_interact_string);
            this.c = (TextView) this.f8109a.findViewById(R.id.hiad_swipe_desc);
            this.f = (ImageView) this.f8109a.findViewById(R.id.hiad_arrow);
            this.g = (ScanningView) this.f8109a.findViewById(R.id.scanning_view);
        } catch (RuntimeException unused) {
            str = "init RuntimeException";
            ho.c("PPSSplashSwipeView", str);
        } catch (Exception unused2) {
            str = "init error";
            ho.c("PPSSplashSwipeView", str);
        }
    }

    public PPSSplashSwipeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public PPSSplashSwipeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PPSSplashSwipeView(Context context) {
        super(context);
        a(context);
    }
}
