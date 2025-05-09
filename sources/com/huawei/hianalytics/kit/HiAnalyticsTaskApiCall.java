package com.huawei.hianalytics.kit;

import com.huawei.hianalytics.t0;
import com.huawei.hianalytics.w0;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;

/* loaded from: classes4.dex */
public class HiAnalyticsTaskApiCall extends TaskApiCall<t0, w0> {
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public void doExecute(t0 t0Var, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<w0> taskCompletionSource) {
        if (responseErrorCode.getErrorCode() == 0) {
            taskCompletionSource.setResult(new w0(str));
        } else {
            taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
        }
    }

    public HiAnalyticsTaskApiCall(String str, String str2) {
        super(str, str2);
    }
}
