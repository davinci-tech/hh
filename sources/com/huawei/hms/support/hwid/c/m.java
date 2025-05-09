package com.huawei.hms.support.hwid.c;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.hwid.result.SignInQrInfo;
import com.huawei.hms.support.hwid.result.StartQrLoginResult;
import org.json.JSONException;

/* loaded from: classes9.dex */
public class m extends b<SignInQrInfo> {
    private boolean b;

    @Override // com.huawei.hms.support.hwid.c.b
    protected boolean a() {
        return false;
    }

    public m(String str, String str2, String str3) {
        super(str, str2, str3);
        this.b = false;
    }

    @Override // com.huawei.hms.support.hwid.c.b, com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return b() ? 60600300 : 60100300;
    }

    @Override // com.huawei.hms.support.hwid.c.b, com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        return b() ? 17 : 16;
    }

    @Override // com.huawei.hms.support.hwid.c.b
    protected void a(ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<SignInQrInfo> taskCompletionSource) {
        if (TextUtils.isEmpty(str)) {
            com.huawei.hms.support.hwid.common.e.g.b(this.f5995a, "body is empty.");
            taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
            return;
        }
        try {
            StartQrLoginResult fromJson = new StartQrLoginResult().fromJson(str);
            if (fromJson.isSuccess()) {
                taskCompletionSource.setResult(fromJson.getSignInQrInfo());
            } else {
                com.huawei.hms.support.hwid.common.e.g.a(this.f5995a, "request is error.");
                taskCompletionSource.setException(new ApiException(fromJson.getStatus()));
            }
        } catch (JSONException e) {
            com.huawei.hms.support.hwid.common.e.g.a(this.f5995a, "JSONException：" + e.getClass().getSimpleName());
            taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
        }
    }

    public boolean b() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }
}
