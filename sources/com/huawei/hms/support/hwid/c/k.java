package com.huawei.hms.support.hwid.c;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.api.UserRecoverableException;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.hwid.result.SignInByQrCodeResult;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class k extends b<Void> {
    @Override // com.huawei.hms.support.hwid.c.b
    protected boolean a() {
        return false;
    }

    @Override // com.huawei.hms.support.hwid.c.b, com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        return 5;
    }

    @Override // com.huawei.hms.support.hwid.c.b, com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return 50000300;
    }

    public k(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    @Override // com.huawei.hms.support.hwid.c.b
    protected void a(ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<Void> taskCompletionSource) {
        if (responseErrorCode.getParcelable() != null && (responseErrorCode.getParcelable() instanceof Intent)) {
            com.huawei.hms.support.hwid.common.e.g.a(this.f5995a, " need second verify.");
            taskCompletionSource.setException(new UserRecoverableException("User login need second verify", (Intent) responseErrorCode.getParcelable()));
            return;
        }
        if (TextUtils.isEmpty(str)) {
            com.huawei.hms.support.hwid.common.e.g.b(this.f5995a, "body is empty.");
            taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
            return;
        }
        try {
            SignInByQrCodeResult fromJson = new SignInByQrCodeResult().fromJson(str);
            if (fromJson.isSuccess()) {
                taskCompletionSource.setResult(null);
            } else {
                com.huawei.hms.support.hwid.common.e.g.a(this.f5995a, "request is error.");
                taskCompletionSource.setException(new ApiException(fromJson.getStatus()));
            }
        } catch (JSONException e) {
            com.huawei.hms.support.hwid.common.e.g.b(this.f5995a, "JSONException：" + e.getClass().getSimpleName());
            taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
        }
    }
}
