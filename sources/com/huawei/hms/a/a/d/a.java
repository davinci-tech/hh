package com.huawei.hms.a.a.d;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.common.utils.PickerHiAnalyticsUtil;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.common.PickerCommonNaming;
import com.huawei.hms.support.api.hwid.RevokeAccessPickerResult;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.support.picker.common.AccountPickerUtil;
import defpackage.ksy;

/* loaded from: classes9.dex */
public class a extends TaskApiCall<com.huawei.hms.a.a.b.a, Void> {
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        return 9;
    }

    public a(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void doExecute(com.huawei.hms.a.a.b.a aVar, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<Void> taskCompletionSource) {
        a(responseErrorCode, str);
        ksy.b("[ACCOUNTSDK]AccountPickerAuthorizationTaskApiCall", "ResponseErrorCode:" + responseErrorCode.getErrorCode(), true);
        AccountPickerUtil.clearSignInAccountCache();
        if (!TextUtils.isEmpty(str)) {
            if ("{}".equals(str)) {
                ksy.b("[ACCOUNTSDK]AccountPickerAuthorizationTaskApiCall", "CancelAuthorization complete, body is null", true);
                taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
                return;
            }
            ksy.b("[ACCOUNTSDK]AccountPickerAuthorizationTaskApiCall", "cancel authorization body is not null.", true);
            RevokeAccessPickerResult beanFromJsonStr = new RevokeAccessPickerResult().getBeanFromJsonStr(str);
            if (beanFromJsonStr.getErrorCode() != null) {
                int intValue = beanFromJsonStr.getErrorCode().intValue();
                taskCompletionSource.setException(new ApiException(new Status(intValue, beanFromJsonStr.getErrorMsg())));
                a(aVar, intValue);
                return;
            } else {
                taskCompletionSource.setResult(null);
                a(aVar, 0);
                return;
            }
        }
        ksy.b("[ACCOUNTSDK]AccountPickerAuthorizationTaskApiCall", "cancel authorization body is null.", true);
        int errorCode = responseErrorCode.getErrorCode();
        taskCompletionSource.setException(new ApiException(new Status(errorCode, responseErrorCode.getErrorReason())));
        a(aVar, errorCode);
    }

    private void a(ResponseErrorCode responseErrorCode, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("headerErrorCode:" + responseErrorCode.getErrorCode());
        ksy.b("[ACCOUNTSDK]AccountPickerAuthorizationTaskApiCall", sb.toString(), true);
    }

    private void a(com.huawei.hms.a.a.b.a aVar, int i) {
        if (aVar != null) {
            HiAnalyticsClient.reportExit(aVar.getContext(), PickerCommonNaming.AccountPickerRevokeAccess, getTransactionId(), PickerHiAnalyticsUtil.getHiAnalyticsStatus(i), i);
        }
    }
}
