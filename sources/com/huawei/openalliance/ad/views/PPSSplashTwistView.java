package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes9.dex */
public class PPSSplashTwistView extends l {
    @Override // com.huawei.openalliance.ad.views.l
    protected String getViewTag() {
        return "PPSSplashTwistView";
    }

    private void a(Context context) {
        String str;
        ho.b("PPSSplashTwistView", "init");
        try {
            this.f8109a = inflate(context, R.layout.hiad_layout_splash_twist, this);
            this.f = (ImageView) this.f8109a.findViewById(R.id.hiad_phone_jpg);
            this.b = (TextView) this.f8109a.findViewById(R.id.hiad_twist_interact_string);
            this.c = (TextView) this.f8109a.findViewById(R.id.hiad_twist_desc);
        } catch (RuntimeException unused) {
            str = "init RuntimeException";
            ho.c("PPSSplashTwistView", str);
        } catch (Exception unused2) {
            str = "init error";
            ho.c("PPSSplashTwistView", str);
        }
    }

    public PPSSplashTwistView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public PPSSplashTwistView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PPSSplashTwistView(Context context) {
        super(context);
        a(context);
    }
}
