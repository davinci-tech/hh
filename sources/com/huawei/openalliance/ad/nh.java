package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.views.interfaces.n;
import com.huawei.phoneservice.feedbackcommon.utils.AsCache;

/* loaded from: classes9.dex */
public abstract class nh<V extends com.huawei.openalliance.ad.views.interfaces.n> extends jj<V> implements ob<V> {
    protected Context d;
    private boolean e = false;
    private boolean f = false;
    private CountDownTimer g;

    protected abstract void b(String str);

    @Override // com.huawei.openalliance.ad.ob
    public boolean h() {
        return this.f;
    }

    @Override // com.huawei.openalliance.ad.ob
    public void c() {
        jb adMediator = ((com.huawei.openalliance.ad.views.interfaces.n) d()).getAdMediator();
        if (adMediator != null) {
            adMediator.v();
        }
    }

    @Override // com.huawei.openalliance.ad.ob
    public void b(Long l) {
        a(11, AsCache.FEED_BACK_CACHE_FILE_NAME, l);
    }

    public void b(ContentRecord contentRecord) {
        jb adMediator = ((com.huawei.openalliance.ad.views.interfaces.n) d()).getAdMediator();
        if (adMediator != null) {
            adMediator.e(contentRecord);
        }
    }

    @Override // com.huawei.openalliance.ad.ob
    public void b() {
        ho.b("PPSBaseViewPresenter", "onDisplayTimeUp hasShowFinish: %s", Boolean.valueOf(this.e));
        if (this.e) {
            return;
        }
        this.e = true;
        a(true);
        jb adMediator = ((com.huawei.openalliance.ad.views.interfaces.n) d()).getAdMediator();
        if (adMediator != null) {
            adMediator.u();
        }
    }

    @Override // com.huawei.openalliance.ad.ob
    public void a(Long l) {
        a(10, "onWhyThisAd", l);
    }

    @Override // com.huawei.openalliance.ad.ob
    public void a(ContentRecord contentRecord, long j, int i) {
        jb adMediator = ((com.huawei.openalliance.ad.views.interfaces.n) d()).getAdMediator();
        if (adMediator != null) {
            adMediator.a(contentRecord, j, i);
        }
    }

    @Override // com.huawei.openalliance.ad.ob
    public void a(ContentRecord contentRecord) {
        this.f7126a = contentRecord;
        if (contentRecord == null) {
            ho.c("PPSBaseViewPresenter", "loadAdMaterial contentRecord is null");
            ((com.huawei.openalliance.ad.views.interfaces.n) d()).a(-7);
            return;
        }
        ho.b("PPSBaseViewPresenter", "loadAdMaterial");
        String z = contentRecord.z();
        if (TextUtils.isEmpty(z)) {
            z = contentRecord.y();
        }
        this.b.a(contentRecord);
        b(z);
    }

    @Override // com.huawei.openalliance.ad.ob
    public void a(int i, int i2, Long l) {
        ho.b("PPSBaseViewPresenter", "skip ad - hasShowFinish: %s", Boolean.valueOf(this.e));
        if (this.e) {
            return;
        }
        a(false);
        this.e = true;
        a();
    }

    @Override // com.huawei.openalliance.ad.ob
    public void a(int i, int i2, ContentRecord contentRecord, Long l, MaterialClickInfo materialClickInfo, int i3) {
        ho.b("PPSBaseViewPresenter", "onTouch");
        jb adMediator = ((com.huawei.openalliance.ad.views.interfaces.n) d()).getAdMediator();
        if (adMediator == null || !adMediator.a(i, i2, contentRecord, l, materialClickInfo, i3)) {
            return;
        }
        if (this.e) {
            ho.b("PPSBaseViewPresenter", "onDoActionSucc hasShowFinish");
            return;
        }
        this.e = true;
        a(false);
        a();
    }

    @Override // com.huawei.openalliance.ad.ob
    public void a(int i) {
        ho.b("PPSBaseViewPresenter", "startDisplayDurationCountTask duration: %d", Integer.valueOf(i));
        CountDownTimer countDownTimer = this.g;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(i, 500L) { // from class: com.huawei.openalliance.ad.nh.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                int max = Math.max(1, (int) Math.ceil((j * 1.0f) / 1000.0f));
                ho.a("PPSBaseViewPresenter", "count down time: %d seconds: %d", Long.valueOf(j), Integer.valueOf(max));
                ((com.huawei.openalliance.ad.views.interfaces.n) nh.this.d()).c(max);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ((com.huawei.openalliance.ad.views.interfaces.n) nh.this.d()).c(1);
                nh.this.b();
            }
        };
        this.g = countDownTimer2;
        countDownTimer2.start();
    }

    public void a() {
        ho.b("PPSBaseViewPresenter", "cancelDisplayDurationCountTask");
        CountDownTimer countDownTimer = this.g;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.g = null;
        }
    }

    private void a(boolean z) {
        ho.b("PPSBaseViewPresenter", "setNormalEnd, isNormalEnd= %s", Boolean.valueOf(z));
        this.f = z;
    }

    private void a(int i, String str, Long l) {
        if (this.e) {
            ho.b("PPSBaseViewPresenter", "%s hasShowFinish", str);
            return;
        }
        this.e = true;
        c();
        a();
    }

    public nh(Context context, V v, int i) {
        this.d = context.getApplicationContext();
        this.b = new ou(this.d, new si(this.d, i));
        a((nh<V>) v);
    }
}
