package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.huawei.openalliance.ad.utils.az;

/* loaded from: classes9.dex */
public class ni extends nh<com.huawei.openalliance.ad.views.interfaces.i> implements ny {
    @Override // com.huawei.openalliance.ad.nh
    protected void b(String str) {
        ((com.huawei.openalliance.ad.views.interfaces.i) d()).d();
        ho.b("PPSImageViewPresenter", "onMaterialLoaded - begin to load image");
        com.huawei.openalliance.ad.utils.az.a(this.d, str, new az.a() { // from class: com.huawei.openalliance.ad.ni.1
            @Override // com.huawei.openalliance.ad.utils.az.a
            public void a(final Drawable drawable) {
                ho.b("PPSImageViewPresenter", "onMaterialLoaded - image load success");
                com.huawei.openalliance.ad.utils.cv.a(ni.this.d.getApplicationContext(), new Runnable() { // from class: com.huawei.openalliance.ad.ni.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ((com.huawei.openalliance.ad.views.interfaces.i) ni.this.d()).a(drawable);
                        ((com.huawei.openalliance.ad.views.interfaces.i) ni.this.d()).c();
                    }
                });
            }

            @Override // com.huawei.openalliance.ad.utils.az.a
            public void a() {
                ho.b("PPSImageViewPresenter", "onMaterialLoaded - image load failed");
                com.huawei.openalliance.ad.utils.cv.a(ni.this.d.getApplicationContext(), new Runnable() { // from class: com.huawei.openalliance.ad.ni.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ((com.huawei.openalliance.ad.views.interfaces.i) ni.this.d()).a(-9);
                    }
                });
                ni niVar = ni.this;
                niVar.b(niVar.f7126a);
            }
        }, this.f7126a);
    }

    public ni(Context context, com.huawei.openalliance.ad.views.interfaces.i iVar, int i) {
        super(context, iVar, i);
    }
}
