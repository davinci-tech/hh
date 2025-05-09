package com.huawei.hms.hwid;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.hwid.SignOutResult;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class ad extends TaskApiCall<u, Void> {
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        return 1;
    }

    public ad(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void doExecute(u uVar, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<Void> taskCompletionSource) {
        if (responseErrorCode == null) {
            as.b("[HUAWEIIDSDK]HuaweiIdSignOutTaskApiCall", "response is null.", true);
            taskCompletionSource.setException(new ApiException(new Status(2003, "response is null.")));
            return;
        }
        a(responseErrorCode, str);
        int errorCode = responseErrorCode.getErrorCode();
        if (!TextUtils.isEmpty(str)) {
            try {
                SignOutResult fromJson = new SignOutResult().fromJson(str);
                errorCode = fromJson.getStatus().getStatusCode();
                if (fromJson.isSuccess()) {
                    taskCompletionSource.setResult(null);
                } else {
                    taskCompletionSource.setException(new ApiException(fromJson.getStatus()));
                }
            } catch (JSONException unused) {
                taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
            }
        } else {
            taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
        }
        if (uVar != null) {
            HiAnalyticsClient.reportExit(uVar.getContext(), "hwid.signout", getTransactionId(), ar.a(errorCode), errorCode);
        }
    }

    private void a(ResponseErrorCode responseErrorCode, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("headerErrorCode:" + responseErrorCode.getErrorCode());
        as.b("[HUAWEIIDSDK]HuaweiIdSignOutTaskApiCall", sb.toString(), true);
    }
}
