package com.huawei.openalliance.ad.views.dsa;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.huawei.openalliance.ad.utils.ao;

/* loaded from: classes9.dex */
public class DomesticDsaSplashView extends DomesticDsaView {
    @Override // com.huawei.openalliance.ad.views.dsa.DomesticDsaView, com.huawei.openalliance.ad.views.h
    public void a(Context context) {
        super.a(context);
        if (this.c != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.c.getLayoutParams();
            int a2 = ao.a(getContext(), 12.0f);
            layoutParams.setMargins(a2, a2, a2, a2);
            this.c.setLayoutParams(layoutParams);
            this.c.setElevation(ao.a(getContext(), 5.0f));
        }
    }

    public DomesticDsaSplashView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public DomesticDsaSplashView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DomesticDsaSplashView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DomesticDsaSplashView(Context context) {
        super(context);
    }
}
