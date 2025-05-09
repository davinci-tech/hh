package com.huawei.hms.hwid;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.ResolvableApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.api.hwid.SignOutResult;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class n extends TaskApiCall<c, Void> {
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        return 1;
    }

    public n(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void doExecute(c cVar, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<Void> taskCompletionSource) {
        if (responseErrorCode == null) {
            as.b("[AccountSDK]AccountSignOutTaskApiCall", "response is null.", true);
            taskCompletionSource.setException(new ApiException(new Status(2003, "response is null.")));
            return;
        }
        a(responseErrorCode, str);
        int errorCode = responseErrorCode.getErrorCode();
        if (errorCode != 0 && CommonCode.Resolution.HAS_RESOLUTION.equals(responseErrorCode.getResolution())) {
            as.b("[AccountSDK]AccountSignOutTaskApiCall", "apk version is low or is not exist.", true);
            Status status = new Status(errorCode, responseErrorCode.getErrorReason());
            ao.a(responseErrorCode, status);
            taskCompletionSource.setException(new ResolvableApiException(status));
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            if ("{}".equals(str)) {
                as.b("[AccountSDK]AccountSignOutTaskApiCall", "SignOut complete, body is null", true);
                taskCompletionSource.setException(new ApiException(new Status(errorCode, responseErrorCode.getErrorReason())));
                return;
            }
            try {
                SignOutResult fromJson = new SignOutResult().fromJson(str);
                if (fromJson.isSuccess()) {
                    taskCompletionSource.setResult(null);
                } else {
                    taskCompletionSource.setException(new ApiException(fromJson.getStatus()));
                }
                return;
            } catch (JSONException e) {
                as.b("[AccountSDK]AccountSignOutTaskApiCall", "JSONException:" + e.getClass().getSimpleName(), true);
                taskCompletionSource.setException(new ApiException(new Status(errorCode, responseErrorCode.getErrorReason())));
                return;
            }
        }
        taskCompletionSource.setException(new ApiException(new Status(errorCode, responseErrorCode.getErrorReason())));
    }

    private void a(ResponseErrorCode responseErrorCode, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("headerErrorCode:" + responseErrorCode.getErrorCode());
        as.b("[AccountSDK]AccountSignOutTaskApiCall", sb.toString(), true);
    }
}
