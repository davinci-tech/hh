package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.magazine.inter.MagLockAd;
import com.huawei.openalliance.ad.magazine.inter.MagLockAdInfo;
import java.util.List;

/* loaded from: classes9.dex */
public class hy implements MagLockAdInfo {

    /* renamed from: a, reason: collision with root package name */
    private int f6931a;
    private List<MagLockAd> b;

    public String toString() {
        return "MagLockAdInfoImpl [multiAds=" + this.b + ", code=" + this.f6931a + "]";
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdInfo
    public void setRetCode(int i) {
        this.f6931a = i;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdInfo
    public void setMultiAds(List<MagLockAd> list) {
        this.b = list;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdInfo
    public int getRetCode() {
        return this.f6931a;
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.MagLockAdInfo
    public List<MagLockAd> getMultiAds() {
        return this.b;
    }
}
