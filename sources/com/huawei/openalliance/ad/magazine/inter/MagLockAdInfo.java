package com.huawei.openalliance.ad.magazine.inter;

import com.huawei.openalliance.ad.hy;
import java.util.List;

/* loaded from: classes9.dex */
public interface MagLockAdInfo {
    List<MagLockAd> getMultiAds();

    int getRetCode();

    void setMultiAds(List<MagLockAd> list);

    void setRetCode(int i);

    public static final class Builder {
        public final MagLockAdInfo build() {
            return new hy();
        }
    }
}
