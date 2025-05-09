package com.huawei.openalliance.ad.magazine.inter;

import com.huawei.openalliance.ad.hx;
import java.util.List;

/* loaded from: classes9.dex */
public interface MagLockAd {
    List<MagLockAdContent> getAdList();

    int getRetCode();

    String getSlotId();

    void setAdList(List<MagLockAdContent> list);

    void setRetCode(int i);

    void setSlotId(String str);

    public static final class Builder {
        public final MagLockAd build() {
            return new hx();
        }
    }
}
