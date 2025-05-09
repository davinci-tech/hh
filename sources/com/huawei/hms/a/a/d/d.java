package com.huawei.hms.a.a.d;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.common.utils.PickerHiAnalyticsUtil;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.common.PickerCommonNaming;
import com.huawei.hms.support.api.hwid.AccountPickerSignOutResult;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import defpackage.ksy;
import org.json.JSONException;

/* loaded from: classes9.dex */
public class d extends TaskApiCall<com.huawei.hms.a.a.b.a, Void> {
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        return 9;
    }

    public d(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void doExecute(com.huawei.hms.a.a.b.a aVar, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<Void> taskCompletionSource) {
        a(responseErrorCode, str);
        int errorCode = responseErrorCode.getErrorCode();
        ksy.b("[ACCOUNTSDK]AccountPickerSignOutTaskApiCall", "ResponseErrorCode:" + errorCode, true);
        if (!TextUtils.isEmpty(str)) {
            ksy.b("[ACCOUNTSDK]AccountPickerSignOutTaskApiCall", "signOut body is not null.", true);
            try {
                AccountPickerSignOutResult fromJson = new AccountPickerSignOutResult().fromJson(str);
                errorCode = fromJson.getStatus().getStatusCode();
                if (fromJson.isSuccess()) {
                    taskCompletionSource.setResult(null);
                    a(aVar, 0);
                } else {
                    taskCompletionSource.setException(new ApiException(new Status(errorCode, responseErrorCode.getErrorReason())));
                    a(aVar, errorCode);
                }
                return;
            } catch (JSONException e) {
                ksy.c("[ACCOUNTSDK]AccountPickerSignOutTaskApiCall", "JSONException：" + e.getClass().getSimpleName(), true);
                taskCompletionSource.setException(new ApiException(new Status(2015, "JSON parse exception.")));
                a(aVar, errorCode);
                return;
            }
        }
        ksy.b("[ACCOUNTSDK]AccountPickerSignOutTaskApiCall", "signOut body is null.", true);
        taskCompletionSource.setException(new ApiException(new Status(errorCode, responseErrorCode.getErrorReason())));
        a(aVar, errorCode);
    }

    private void a(ResponseErrorCode responseErrorCode, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("headerErrorCode:" + responseErrorCode.getErrorCode());
        ksy.b("[ACCOUNTSDK]AccountPickerSignOutTaskApiCall", sb.toString(), true);
    }

    private void a(com.huawei.hms.a.a.b.a aVar, int i) {
        if (aVar != null) {
            HiAnalyticsClient.reportExit(aVar.getContext(), PickerCommonNaming.AccountPickerSignout, getTransactionId(), PickerHiAnalyticsUtil.getHiAnalyticsStatus(i), i);
        }
    }
}
