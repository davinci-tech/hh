package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.listeners.LinkedAdListener;
import com.huawei.openalliance.ad.ok;
import java.util.List;

/* loaded from: classes5.dex */
public class LinkedSplashAd extends e implements ILinkedSplashAd {
    private ContentRecord f;
    private transient LinkedAdListener g;
    private transient IAdEvent h;
    private int i;
    private String j;
    private String k;
    private String l;
    private int m;
    private int n;
    private String o;
    private boolean p = false;

    @Override // com.huawei.openalliance.ad.inter.data.e, com.huawei.openalliance.ad.inter.data.INativeAd
    public List<FeedbackInfo> getFeedbackInfoList() {
        return null;
    }

    public void y(String str) {
        this.l = str;
    }

    public void x(String str) {
        this.k = str;
    }

    @Override // com.huawei.openalliance.ad.inter.data.e
    public void w(String str) {
        this.o = str;
    }

    public void v(String str) {
        this.j = str;
    }

    @Override // com.huawei.openalliance.ad.inter.data.ILinkedSplashAd
    public void setListener(LinkedAdListener linkedAdListener) {
        this.g = linkedAdListener;
    }

    @Override // com.huawei.openalliance.ad.inter.data.ILinkedSplashAd
    public boolean isFromExsplash() {
        return this.p;
    }

    public void i(int i) {
        this.n = i;
    }

    public void h(int i) {
        this.m = i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.ILinkedSplashAd
    public String getSoundSwitch() {
        return getVideoInfo() != null ? getVideoInfo().getSoundSwitch() : "n";
    }

    @Override // com.huawei.openalliance.ad.inter.data.ILinkedSplashAd
    public LinkedAdListener getListener() {
        return this.g;
    }

    public void g(int i) {
        this.i = i;
    }

    public void e(boolean z) {
        this.p = z;
    }

    @Override // com.huawei.openalliance.ad.inter.data.e
    public IAdEvent b(Context context) {
        if (this.h == null) {
            if (context != null) {
                this.h = new ok(context.getApplicationContext(), this);
            } else {
                ho.b("LinkedSplashAd", " context is null, ");
            }
        }
        return this.h;
    }

    public void a(ContentRecord contentRecord) {
        this.f = contentRecord;
    }

    public List<AdvertiserInfo> J() {
        ContentRecord contentRecord = this.f;
        if (contentRecord == null) {
            return null;
        }
        return contentRecord.aS();
    }

    public ContentRecord I() {
        return this.f;
    }

    public int H() {
        return this.n;
    }

    public int G() {
        return this.m;
    }

    public String F() {
        return this.l;
    }

    public String E() {
        return this.k;
    }

    @Override // com.huawei.openalliance.ad.inter.data.e
    public String D() {
        return this.o;
    }

    public String C() {
        return this.j;
    }

    public int B() {
        return this.i;
    }
}
