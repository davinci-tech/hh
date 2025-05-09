package com.huawei.health.device.api;

import com.huawei.hwbasemgr.ResponseCallback;
import defpackage.cfi;

/* loaded from: classes3.dex */
public interface MultiUsersManagerApi {
    cfi getCurrentUser();

    void getCurrentUserForUserInfo(ResponseCallback<cfi> responseCallback);
}
