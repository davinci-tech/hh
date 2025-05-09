package com.huawei.hms.iap;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.iap.entity.IsSandboxActivatedResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes9.dex */
public class k extends a<g, IsSandboxActivatedResult> {
    @Override // com.huawei.hms.iap.a
    protected int a() {
        return 40002000;
    }

    @Override // com.huawei.hms.iap.a
    protected void a(TaskCompletionSource<IsSandboxActivatedResult> taskCompletionSource, ResponseErrorCode responseErrorCode, String str) {
        HMSLog.i("IsSandboxActivatedTaskApiCall", "dealSuccess");
        com.huawei.hms.iap.entity.c cVar = new com.huawei.hms.iap.entity.c();
        if (!TextUtils.isEmpty(str)) {
            JsonUtil.jsonToEntity(str, cVar);
        }
        IsSandboxActivatedResult isSandboxActivatedResult = new IsSandboxActivatedResult();
        isSandboxActivatedResult.setReturnCode(cVar.getReturnCode());
        isSandboxActivatedResult.setErrMsg(cVar.getErrMsg());
        isSandboxActivatedResult.setIsSandboxUser(cVar.getIsSandboxUser());
        isSandboxActivatedResult.setIsSandboxApk(cVar.getIsSandboxApk());
        isSandboxActivatedResult.setVersionInApk(cVar.getVersionInApk());
        isSandboxActivatedResult.setVersionFrMarket(cVar.getVersionFrMarket());
        isSandboxActivatedResult.setStatus(new Status(responseErrorCode.getStatusCode(), responseErrorCode.getErrorReason()));
        taskCompletionSource.setResult(isSandboxActivatedResult);
    }

    public k(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }
}
