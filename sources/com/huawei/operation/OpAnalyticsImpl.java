package com.huawei.operation;

import com.huawei.hianalytics.v2.HiAnalytics;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.iyj;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public class OpAnalyticsImpl {
    private static final int ERROR = -1;
    private static final int SUCCESS = 0;
    private static final String TAG = "OpAnalyticsImpl";

    private OpAnalyticsImpl() {
    }

    static void setEvent(final int i, final String str, final LinkedHashMap<String, String> linkedHashMap, final boolean z) {
        try {
            iyj.e(TAG, " type = ", Integer.valueOf(i), " eventId = ", str, " content = ", linkedHashMap.toString());
            if (!HiAnalytics.getInitFlag()) {
                OpAnalyticsUtil.getInstance().init(BaseApplication.getContext(), new IBaseResponseCallback() { // from class: com.huawei.operation.OpAnalyticsImpl$$ExternalSyntheticLambda0
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj) {
                        OpAnalyticsImpl.lambda$setEvent$0(i, str, linkedHashMap, z, i2, obj);
                    }
                });
            } else {
                eventAndReport(i, str, linkedHashMap, z);
            }
        } catch (Exception unused) {
            iyj.a(TAG, "setEvent Exception");
        }
    }

    static /* synthetic */ void lambda$setEvent$0(int i, String str, LinkedHashMap linkedHashMap, boolean z, int i2, Object obj) {
        if (i2 == -1) {
            return;
        }
        eventAndReport(i, str, linkedHashMap, z);
    }

    static void onReport() {
        HiAnalytics.onReport();
    }

    private static void eventAndReport(int i, String str, LinkedHashMap<String, String> linkedHashMap, boolean z) {
        HiAnalytics.onEvent(i, str, linkedHashMap);
        iyj.b(TAG, "the eventId: ", str, "the setEvent result: success");
        if (z) {
            onReport();
        }
    }
}
