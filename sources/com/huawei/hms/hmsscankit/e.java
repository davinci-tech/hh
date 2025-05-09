package com.huawei.hms.hmsscankit;

import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.hmsscankit.api.IOnResultCallback;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.scankit.p.o4;

/* loaded from: classes4.dex */
class e extends IOnResultCallback.Stub {

    /* renamed from: a, reason: collision with root package name */
    private final OnResultCallback f4636a;
    private String b;
    private boolean c;

    e(OnResultCallback onResultCallback, boolean z) {
        this.f4636a = onResultCallback;
        this.c = z;
    }

    @Override // com.huawei.hms.hmsscankit.api.IOnResultCallback
    public void onResult(HmsScan[] hmsScanArr) throws RemoteException {
        HmsScan hmsScan;
        o4.d("OnResultCallbackDelegat", "result callback sdk continueScan" + this.c);
        if (this.c) {
            this.f4636a.onResult(hmsScanArr);
            return;
        }
        if (hmsScanArr == null || hmsScanArr.length <= 0 || (hmsScan = hmsScanArr[0]) == null || TextUtils.equals(this.b, hmsScan.getOriginalValue())) {
            return;
        }
        this.b = hmsScanArr[0].getOriginalValue();
        o4.d("OnResultCallbackDelegat", "result callback sdk continueScan" + this.c);
        this.f4636a.onResult(hmsScanArr);
    }
}
