package com.huawei.hms.hwid;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes9.dex */
public class aa extends TaskApiCall<u, Void> {
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        return 1;
    }

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return Constants.HMS_VERSION_CODE_40000300;
    }

    public aa(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void doExecute(u uVar, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<Void> taskCompletionSource) {
        if (responseErrorCode == null) {
            as.b("[HUAWEIIDSDK]HuaweiIdDeleteAuthInfoTaskApiCall", "response is null.", true);
            taskCompletionSource.setException(new ApiException(new Status(2003, "response is null.")));
            return;
        }
        a(responseErrorCode, str);
        if (!TextUtils.isEmpty(str)) {
            if (responseErrorCode.getErrorCode() == 0) {
                taskCompletionSource.setResult(null);
                return;
            } else {
                taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode())));
                return;
            }
        }
        taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
    }

    private void a(ResponseErrorCode responseErrorCode, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("headerErrorCode:" + responseErrorCode.getErrorCode());
        as.b("[HUAWEIIDSDK]HuaweiIdDeleteAuthInfoTaskApiCall", sb.toString(), true);
    }
}
