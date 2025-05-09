package com.huawei.operation.h5pro.jsmodules;

import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import defpackage.kud;
import defpackage.kvc;
import defpackage.kvf;
import defpackage.kvh;
import defpackage.kvi;
import health.compact.a.util.LogUtil;

/* loaded from: classes.dex */
public class ArkUIJsApi extends JsBaseModule {
    private static final String TAG = "ArkUIJsApi";
    private final kvi mReadDataProcess = new kvi();
    private final kvf mAggregateDataProcess = new kvf();
    private final kvh mReadDataSourceProcess = new kvh();
    private final kvc mDeleteDataProcess = new kvc();

    @JavascriptInterface
    public void readData(long j, String str) {
        LogUtil.d(TAG, "readData origin request = ", str);
        kud.d(str, this.mReadDataProcess, getCallback(j));
    }

    @JavascriptInterface
    public void aggregateData(long j, String str) {
        LogUtil.d(TAG, "aggregateData origin request = ", str);
        kud.d(str, this.mAggregateDataProcess, getCallback(j));
    }

    @JavascriptInterface
    public void readDataSource(long j, String str) {
        LogUtil.d(TAG, "readDataSource origin request = ", str);
        kud.d(str, this.mReadDataSourceProcess, getCallback(j));
    }

    @JavascriptInterface
    public void deleteData(long j, String str) {
        LogUtil.d(TAG, "deleteData origin request = ", str);
        kud.d(str, this.mDeleteDataProcess, getCallback(j));
    }

    private IBaseResponseCallback getCallback(final long j) {
        return new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.ArkUIJsApi$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ArkUIJsApi.this.m713x35b12925(j, i, obj);
            }
        };
    }

    /* renamed from: lambda$getCallback$0$com-huawei-operation-h5pro-jsmodules-ArkUIJsApi, reason: not valid java name */
    /* synthetic */ void m713x35b12925(long j, int i, Object obj) {
        if (i == 0) {
            onSuccessCallback(j, obj);
        } else {
            onFailureCallback(j, "");
        }
    }
}
