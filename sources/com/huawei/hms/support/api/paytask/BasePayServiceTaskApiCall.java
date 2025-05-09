package com.huawei.hms.support.api.paytask;

import android.app.PendingIntent;
import android.content.Intent;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.ResolvableApiException;
import com.huawei.hms.common.internal.HmsClient;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes4.dex */
public abstract class BasePayServiceTaskApiCall<U extends HmsClient, V> extends TaskApiCall<U, V> {
    protected static final int MIN_APK_VERSION_FOR_SIGNALGORITHM = 60000300;
    protected static final int MIN_PAY_API_LEVEL_FOR_SIGNALGORITHM = 4;

    /* renamed from: a, reason: collision with root package name */
    private boolean f5966a;

    protected abstract void dealSuccess(TaskCompletionSource<V> taskCompletionSource, ResponseErrorCode responseErrorCode, String str);

    protected Status getStatus(ResponseErrorCode responseErrorCode) {
        String str = "call " + getUri() + ", ";
        if (responseErrorCode.getParcelable() instanceof Intent) {
            HMSLog.i("BasePayServiceTaskApiCall", str + "getStatus, getParcelable is instanceof Intent");
            return new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason(), (Intent) responseErrorCode.getParcelable());
        }
        if (!(responseErrorCode.getParcelable() instanceof PendingIntent)) {
            HMSLog.i("BasePayServiceTaskApiCall", str + "getStatus, no parcelable");
            return new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason());
        }
        HMSLog.i("BasePayServiceTaskApiCall", str + "getStatus, getParcelable is instanceof PendingIntent");
        return new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason(), (PendingIntent) responseErrorCode.getParcelable());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public void doExecute(U u, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<V> taskCompletionSource) {
        if (responseErrorCode == null) {
            HMSLog.e("BasePayServiceTaskApiCall", "header is null");
            taskCompletionSource.setException(new ApiException(new Status(1)));
            return;
        }
        if (this.f5966a) {
            HMSLog.i("BasePayServiceTaskApiCall", "reportExit");
            HiAnalyticsClient.reportExit(u.getContext(), getUri(), getTransactionId(), responseErrorCode.getStatusCode(), responseErrorCode.getErrorCode(), 61606300);
        }
        if (taskCompletionSource == null) {
            HMSLog.e("BasePayServiceTaskApiCall", "taskCompletionSource is null");
        } else if (responseErrorCode.getErrorCode() == 0) {
            dealSuccess(taskCompletionSource, responseErrorCode, str);
        } else {
            dealException(taskCompletionSource, responseErrorCode);
        }
    }

    protected void dealException(TaskCompletionSource taskCompletionSource, ResponseErrorCode responseErrorCode) {
        Exception resolvableApiException;
        String str = "call " + getUri() + ", ";
        HMSLog.e("BasePayServiceTaskApiCall", str + "dealException, returnCode: " + responseErrorCode.getErrorCode());
        if (responseErrorCode.getErrorCode() == 1212 || responseErrorCode.getErrorCode() == 907135003) {
            HMSLog.e("BasePayServiceTaskApiCall", str + "dealException, upgrade required");
            resolvableApiException = new ResolvableApiException(responseErrorCode);
        } else {
            resolvableApiException = new ApiException(getStatus(responseErrorCode));
        }
        taskCompletionSource.setException(resolvableApiException);
    }

    protected BasePayServiceTaskApiCall(String str, String str2, String str3) {
        super(str, str2, str3);
        this.f5966a = true;
    }

    protected BasePayServiceTaskApiCall(String str, String str2) {
        super(str, str2);
        this.f5966a = false;
    }
}
