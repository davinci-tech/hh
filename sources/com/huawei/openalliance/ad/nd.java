package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.views.PPSInterstitialVideoView;

/* loaded from: classes5.dex */
public class nd extends jj<PPSInterstitialVideoView> {
    private PPSInterstitialVideoView d;
    private Context e;
    private cs f;

    public void c() {
        this.b.f();
    }

    public void b(long j, long j2, long j3, long j4) {
        this.b.b(j, j2, (int) j3, (int) j4);
    }

    public void b() {
        this.b.c();
    }

    @Override // com.huawei.openalliance.ad.jj
    public final void a(PPSInterstitialVideoView pPSInterstitialVideoView) {
        this.d = pPSInterstitialVideoView;
    }

    public void a(ContentRecord contentRecord) {
        this.f7126a = contentRecord;
        Context context = this.e;
        this.b = new ou(context, new sd(context), this.f7126a);
    }

    public void a(long j, long j2, long j3, long j4) {
        this.b.c(j, j2, (int) j3, (int) j4);
    }

    public void a(final long j, final long j2, final long j3) {
        if (j == 0 || j >= j3 || this.f == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.nd.1
            @Override // java.lang.Runnable
            public void run() {
                long j4 = j3;
                long j5 = j;
                long j6 = j2;
                long j7 = 0;
                if (j6 != 0 && j6 < j4) {
                    j7 = j4 - j6;
                }
                nd.this.f.a(nd.this.f7126a, j4 - j5, j7);
            }
        });
    }

    @Override // com.huawei.openalliance.ad.jj
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public PPSInterstitialVideoView d() {
        return this.d;
    }

    public nd(Context context, PPSInterstitialVideoView pPSInterstitialVideoView) {
        a(pPSInterstitialVideoView);
        this.e = context;
        this.b = new ou(context, new sd(context));
        this.f = new com.huawei.openalliance.ad.analysis.c(this.e);
    }
}
