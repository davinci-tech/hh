package com.huawei.hwversionmgr.utils.callback;

import com.huawei.hwversionmgr.info.EnterpriseResponseInfo;

/* loaded from: classes5.dex */
public interface EnterpriseResultCallback {
    void onFailure(Throwable th);

    void onSuccess(EnterpriseResponseInfo enterpriseResponseInfo);
}
