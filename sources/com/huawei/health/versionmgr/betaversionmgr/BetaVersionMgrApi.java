package com.huawei.health.versionmgr.betaversionmgr;

import android.content.Context;

/* loaded from: classes4.dex */
public interface BetaVersionMgrApi {
    void checkBetaAppVersion(CheckBetaUpdateCallBack checkBetaUpdateCallBack);

    void showNewBetaVersionDialog(Context context, boolean z);
}
