package com.huawei.hms.hwid;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.account.result.AssistTokenResult;
import com.huawei.hms.support.account.result.GetAssistTokenResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.account.AccountNaming;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import org.json.JSONException;

/* loaded from: classes9.dex */
public class p extends TaskApiCall<c, AssistTokenResult> {
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        return 14;
    }

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return 60000000;
    }

    public p(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void doExecute(c cVar, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<AssistTokenResult> taskCompletionSource) {
        if (responseErrorCode == null) {
            as.b("[AccountSDK]GetAssistTokenTaskApiCall", "response is null.", true);
            taskCompletionSource.setException(new ApiException(new Status(2003, "response is null.")));
            return;
        }
        int errorCode = responseErrorCode.getErrorCode();
        if (TextUtils.isEmpty(str) || "{}".equals(str)) {
            as.b("[AccountSDK]GetAssistTokenTaskApiCall", "getAssistToken complete, response or body is null, failed", true);
            a(cVar, errorCode);
            taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
            return;
        }
        try {
            AssistTokenResult assistTokenResult = new AssistTokenResult();
            GetAssistTokenResult fromJson = new GetAssistTokenResult().fromJson(str);
            if (fromJson.isSuccess()) {
                as.b("[AccountSDK]GetAssistTokenTaskApiCall", "getAssistToken success", true);
                a(cVar, errorCode);
                assistTokenResult.setAssistToken(fromJson.getAssistToken());
                taskCompletionSource.setResult(assistTokenResult);
            } else {
                as.b("[AccountSDK]GetAssistTokenTaskApiCall", "getAssistToken failed", true);
                a(cVar, errorCode);
                taskCompletionSource.setException(new ApiException(fromJson.getStatus()));
            }
        } catch (JSONException unused) {
            as.d("[AccountSDK]GetAssistTokenTaskApiCall", "getAssistToken complete, but parser json exception", true);
            a(cVar, errorCode);
            taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
        }
    }

    private void a(c cVar, int i) {
        if (cVar != null) {
            HiAnalyticsClient.reportExit(cVar.getContext(), AccountNaming.getAssistToken, getTransactionId(), ar.a(i), i);
        }
    }
}
