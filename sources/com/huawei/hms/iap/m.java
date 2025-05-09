package com.huawei.hms.iap;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.iap.entity.ProductInfoResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes4.dex */
public class m extends a<g, ProductInfoResult> {
    @Override // com.huawei.hms.iap.a
    protected void a(TaskCompletionSource<ProductInfoResult> taskCompletionSource, ResponseErrorCode responseErrorCode, String str) {
        HMSLog.i("ProductInfoTaskApiCall", "dealSuccess");
        com.huawei.hms.iap.entity.f fVar = new com.huawei.hms.iap.entity.f();
        if (!TextUtils.isEmpty(str)) {
            JsonUtil.jsonToEntity(str, fVar);
        }
        ProductInfoResult productInfoResult = new ProductInfoResult();
        productInfoResult.setReturnCode(fVar.getReturnCode());
        productInfoResult.setErrMsg(fVar.getErrMsg());
        productInfoResult.setProductInfoList(fVar.getProductInfoList());
        productInfoResult.setStatus(new Status(responseErrorCode.getStatusCode(), responseErrorCode.getErrorReason()));
        taskCompletionSource.setResult(productInfoResult);
    }

    public m(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }
}
