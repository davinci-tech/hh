package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.huawei.openalliance.ad.inter.data.PlacementMediaFile;
import com.huawei.openalliance.ad.utils.az;

/* loaded from: classes5.dex */
public class nn extends jj<com.huawei.openalliance.ad.views.interfaces.o> implements oc<com.huawei.openalliance.ad.views.interfaces.o> {
    private Context d;

    @Override // com.huawei.openalliance.ad.oc
    public void a(final com.huawei.openalliance.ad.inter.data.g gVar) {
        if (gVar != null) {
            com.huawei.openalliance.ad.utils.m.b(new Runnable() { // from class: com.huawei.openalliance.ad.nn.2
                @Override // java.lang.Runnable
                public void run() {
                    PlacementMediaFile mediaFile = gVar.getMediaFile();
                    rt rtVar = new rt();
                    rtVar.c(mediaFile.getUrl());
                    rtVar.b(mediaFile.getSha256());
                    rtVar.a("placement");
                    rtVar.b(mediaFile.getCheckSha256() == 0);
                    rtVar.c(true);
                    rtVar.a(pi.a(gVar));
                    ru a2 = new rr(nn.this.d, rtVar).a();
                    if (a2 != null) {
                        String c = dh.a(nn.this.d, "normal").c(a2.a());
                        if (com.huawei.openalliance.ad.utils.ae.b(c)) {
                            mediaFile.b(c);
                            nn.this.a(mediaFile);
                            return;
                        }
                    }
                    nn.this.a((PlacementMediaFile) null);
                }
            });
        }
    }

    public void a(final PlacementMediaFile placementMediaFile) {
        if (placementMediaFile == null) {
            d().a(null, null);
        } else {
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.nn.1
                @Override // java.lang.Runnable
                public void run() {
                    if (placementMediaFile.a(nn.this.d)) {
                        nn.this.b(placementMediaFile);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final PlacementMediaFile placementMediaFile) {
        if (placementMediaFile == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.az.a(this.d, placementMediaFile.b(), new az.a() { // from class: com.huawei.openalliance.ad.nn.3
            @Override // com.huawei.openalliance.ad.utils.az.a
            public void a(final Drawable drawable) {
                com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.nn.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        nn.this.d().a(placementMediaFile, drawable);
                    }
                });
            }

            @Override // com.huawei.openalliance.ad.utils.az.a
            public void a() {
                ho.c("PlacementImageViewPresenter", "placement image load failed");
                com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.nn.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        nn.this.d().a(null, null);
                    }
                });
            }
        });
    }

    public nn(Context context, com.huawei.openalliance.ad.views.interfaces.o oVar) {
        a((nn) oVar);
        this.d = context;
    }
}
