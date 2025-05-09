package com.huawei.hms.hwid;

import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes9.dex */
public class al extends TaskApiCall<ai, Void> {
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return Constants.TV_OAID_AVAILABLE_HMS_VERSION;
    }

    public al(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        as.b("StartConsentTaskApiCall", "startConsent getApiLevel8", true);
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void doExecute(ai aiVar, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<Void> taskCompletionSource) {
        ag b = ag.b(str);
        Integer a2 = b.a();
        String b2 = b.b();
        if (a2 == null) {
            a2 = Integer.valueOf(responseErrorCode.getErrorCode());
            b2 = responseErrorCode.getErrorReason();
        }
        if (a(responseErrorCode, b.a())) {
            taskCompletionSource.setResult(null);
        } else {
            taskCompletionSource.setException(new ApiException(new Status(a2.intValue(), b2)));
        }
        if (aiVar != null) {
            HiAnalyticsClient.reportExit(aiVar.getContext(), getUri(), getTransactionId(), ar.a(a2.intValue()), a2.intValue());
        }
    }

    private boolean a(ResponseErrorCode responseErrorCode, Integer num) {
        return num == null ? responseErrorCode.getStatusCode() == 0 : num.intValue() == 0;
    }
}
