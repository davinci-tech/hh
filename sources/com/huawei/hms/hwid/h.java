package com.huawei.hms.hwid;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.ResolvableApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.api.hwid.RevokeAccessResult;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import org.json.JSONException;

/* loaded from: classes9.dex */
public class h extends TaskApiCall<c, Void> {
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        return 1;
    }

    public h(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void doExecute(c cVar, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<Void> taskCompletionSource) {
        as.b("AccountCancelAuthorizationTaskApiCall", "AccountCancelAuthorizationTaskApiCall doExecute", true);
        f.a();
        if (responseErrorCode == null) {
            as.b("AccountCancelAuthorizationTaskApiCall", "response is null.", true);
            taskCompletionSource.setException(new ApiException(new Status(2003, "response is null.")));
            return;
        }
        int errorCode = responseErrorCode.getErrorCode();
        if (errorCode != 0 && CommonCode.Resolution.HAS_RESOLUTION.equals(responseErrorCode.getResolution())) {
            as.b("AccountCancelAuthorizationTaskApiCall", "apk version is low or is not exist.", true);
            Status status = new Status(errorCode, responseErrorCode.getErrorReason());
            ao.a(responseErrorCode, status);
            taskCompletionSource.setException(new ResolvableApiException(status));
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            if ("{}".equals(str)) {
                as.b("AccountCancelAuthorizationTaskApiCall", "CancelAuthorization complete, body is null", true);
                taskCompletionSource.setException(new ApiException(new Status(errorCode, responseErrorCode.getErrorReason())));
                return;
            }
            try {
                RevokeAccessResult fromJson = new RevokeAccessResult().fromJson(str);
                if (!fromJson.isSuccess()) {
                    Status status2 = fromJson.getStatus();
                    taskCompletionSource.setException(new ApiException(new Status(status2.getStatusCode(), status2.getStatusMessage())));
                } else {
                    taskCompletionSource.setResult(null);
                }
            } catch (JSONException e) {
                as.b("AccountCancelAuthorizationTaskApiCall", "JSONException:" + e.getClass().getSimpleName(), true);
                Status status3 = new Status(errorCode, responseErrorCode.getErrorReason());
                ao.a(responseErrorCode, status3);
                taskCompletionSource.setException(new ApiException(status3));
            }
        } else {
            taskCompletionSource.setException(new ApiException(new Status(errorCode, responseErrorCode.getErrorReason())));
        }
        if (cVar != null) {
            HiAnalyticsClient.reportExit(cVar.getContext(), getUri(), getTransactionId(), ar.a(errorCode), errorCode);
        }
    }
}
