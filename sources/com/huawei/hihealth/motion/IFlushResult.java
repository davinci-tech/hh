package com.huawei.hihealth.motion;

import android.os.Bundle;

/* loaded from: classes.dex */
public interface IFlushResult {
    void onFailed(Bundle bundle);

    void onServiceException(Bundle bundle);

    void onSuccess(Bundle bundle);
}
