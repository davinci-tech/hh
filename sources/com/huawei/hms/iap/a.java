package com.huawei.hms.iap;

import android.app.PendingIntent;
import android.content.Intent;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ResolvableApiException;
import com.huawei.hms.common.internal.HmsClient;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes4.dex */
public abstract class a<U extends HmsClient, V> extends TaskApiCall<U, V> {
    protected int a() {
        return 0;
    }

    protected abstract void a(TaskCompletionSource<V> taskCompletionSource, ResponseErrorCode responseErrorCode, String str);

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return Math.max(c(), a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void doExecute(U u, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<V> taskCompletionSource) {
        if (responseErrorCode == null) {
            HMSLog.e("BaseIapServiceTaskApiCall", "header is null");
            taskCompletionSource.setException(new IapApiException(new Status(1)));
            return;
        }
        HiAnalyticsClient.reportExit(u.getContext(), getUri(), getTransactionId(), responseErrorCode.getStatusCode(), responseErrorCode.getErrorCode(), 61606300);
        if (taskCompletionSource == null) {
            HMSLog.e("BaseIapServiceTaskApiCall", "taskCompletionSource is null");
        } else if (responseErrorCode.getErrorCode() == 0) {
            a(taskCompletionSource, responseErrorCode, str);
        } else {
            a(taskCompletionSource, responseErrorCode);
        }
    }

    protected void a(TaskCompletionSource taskCompletionSource, ResponseErrorCode responseErrorCode) {
        HMSLog.e("BaseIapServiceTaskApiCall", ("call " + getUri() + ", ") + "dealException, returnCode: " + responseErrorCode.getErrorCode());
        taskCompletionSource.setException((responseErrorCode.getErrorCode() == 1212 || responseErrorCode.getErrorCode() == 907135003) ? new ResolvableApiException(responseErrorCode) : new IapApiException(a(responseErrorCode)));
    }

    public void a(int i) {
        setApiLevel(Math.max(i, getApiLevel()));
    }

    protected Status a(ResponseErrorCode responseErrorCode) {
        String str = "call " + getUri() + ", ";
        if (responseErrorCode.getParcelable() instanceof Intent) {
            HMSLog.i("BaseIapServiceTaskApiCall", str + "getStatus, getParcelable is instanceof Intent");
            return new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason(), (Intent) responseErrorCode.getParcelable());
        }
        if (!(responseErrorCode.getParcelable() instanceof PendingIntent)) {
            HMSLog.i("BaseIapServiceTaskApiCall", str + "getStatus, no parcelable");
            return new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason());
        }
        HMSLog.i("BaseIapServiceTaskApiCall", str + "getStatus, getParcelable is instanceof PendingIntent");
        return new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason(), (PendingIntent) responseErrorCode.getParcelable());
    }

    private boolean d() {
        return com.huawei.hms.iap.util.b.b(getRequestJson(), "isConsignment");
    }

    private int c() {
        if (d()) {
            return 60900000;
        }
        return super.getMinApkVersion();
    }

    private int b() {
        if (d()) {
            return 10;
        }
        return super.getApiLevel();
    }

    protected a(String str, String str2, String str3, String str4) {
        super(str, com.huawei.hms.iap.util.b.a(str2, str3), str4);
        setApiLevel(b());
    }

    protected a(String str, String str2, String str3) {
        this(str, str2, null, str3);
    }
}
