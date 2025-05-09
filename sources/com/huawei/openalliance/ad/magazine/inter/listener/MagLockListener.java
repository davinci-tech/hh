package com.huawei.openalliance.ad.magazine.inter.listener;

import com.huawei.openalliance.ad.magazine.inter.MagLockAdInfo;

/* loaded from: classes9.dex */
public interface MagLockListener {
    void onAdFailed(int i);

    void onAdSuccess(MagLockAdInfo magLockAdInfo);

    void onNoSupport();
}
