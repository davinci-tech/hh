package com.huawei.hwversionmgr.utils.callback;

import com.huawei.hwversionmgr.info.PsiUploadResponseInfo;

/* loaded from: classes5.dex */
public interface PsiUploadResultCallback {
    void onFailure(Throwable th);

    void onSuccess(PsiUploadResponseInfo psiUploadResponseInfo);
}
