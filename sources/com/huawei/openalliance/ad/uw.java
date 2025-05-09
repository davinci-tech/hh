package com.huawei.openalliance.ad;

import android.graphics.drawable.Drawable;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes5.dex */
public class uw implements az.a {

    /* renamed from: a, reason: collision with root package name */
    private final PPSRewardView f7757a;

    @Override // com.huawei.openalliance.ad.utils.az.a
    public void a(final Drawable drawable) {
        if (drawable != null) {
            final Drawable a2 = com.huawei.openalliance.ad.utils.ax.a(this.f7757a.getContext(), drawable, 5.0f, 8.0f);
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.uw.1
                @Override // java.lang.Runnable
                public void run() {
                    uw.this.f7757a.a(drawable, a2);
                }
            });
        }
    }

    @Override // com.huawei.openalliance.ad.utils.az.a
    public void a() {
        ho.b("RewardViewOIDL", "get icon failed");
    }

    public uw(PPSRewardView pPSRewardView) {
        this.f7757a = pPSRewardView;
    }
}
