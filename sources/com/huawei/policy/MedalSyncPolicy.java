package com.huawei.policy;

import defpackage.lmy;
import defpackage.spn;

/* loaded from: classes6.dex */
public interface MedalSyncPolicy {
    void onLaunchPageStart(lmy lmyVar);

    void onLightMedalListStart(lmy lmyVar);

    void onLightMedalMessageListStart(lmy lmyVar);

    void onLightMedalStart(lmy lmyVar);

    void onSyncMedalsStart(lmy lmyVar);

    void receiveFromDevice(int i, spn spnVar);
}
