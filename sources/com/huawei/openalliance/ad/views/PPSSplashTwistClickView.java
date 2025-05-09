package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes9.dex */
public class PPSSplashTwistClickView extends l {
    private LinearLayout g;

    @Override // com.huawei.openalliance.ad.views.l
    protected String getViewTag() {
        return "PPSSplashTwistClickView";
    }

    public LinearLayout getClickAreaView() {
        return this.g;
    }

    private void a(Context context) {
        String str;
        ho.b("PPSSplashTwistClickView", "init");
        try {
            this.f8109a = inflate(context, R.layout.hiad_layout_splash_twist_click, this);
            this.f = (ImageView) this.f8109a.findViewById(R.id.hiad_click_phone_jpg);
            this.g = (LinearLayout) this.f8109a.findViewById(R.id.twist_click_area);
            this.b = (TextView) this.f8109a.findViewById(R.id.hiad_click_twist_string);
            this.c = (TextView) this.f8109a.findViewById(R.id.hiad_click_twist_desc);
        } catch (RuntimeException unused) {
            str = "init RuntimeException";
            ho.c("PPSSplashTwistClickView", str);
        } catch (Exception unused2) {
            str = "init error";
            ho.c("PPSSplashTwistClickView", str);
        }
    }

    public PPSSplashTwistClickView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public PPSSplashTwistClickView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PPSSplashTwistClickView(Context context) {
        super(context);
        a(context);
    }
}
