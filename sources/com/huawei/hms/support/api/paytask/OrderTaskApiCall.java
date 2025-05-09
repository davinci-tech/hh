package com.huawei.hms.support.api.paytask;

import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.pay.OrderRequest;
import com.huawei.hms.support.api.entity.pay.OrderResp;
import com.huawei.hms.support.api.pay.OrderResult;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes9.dex */
public class OrderTaskApiCall extends BasePayServiceTaskApiCall<PayHmsClient, OrderResult> {

    /* renamed from: a, reason: collision with root package name */
    private OrderRequest f5967a;

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        if (a()) {
            return 60000300;
        }
        return super.getMinApkVersion();
    }

    @Override // com.huawei.hms.support.api.paytask.BasePayServiceTaskApiCall
    protected void dealSuccess(TaskCompletionSource<OrderResult> taskCompletionSource, ResponseErrorCode responseErrorCode, String str) {
        HMSLog.i("OrderTaskApiCall", "dealSuccess");
        OrderResp orderResp = new OrderResp();
        if (!TextUtils.isEmpty(str)) {
            JsonUtil.jsonToEntity(str, orderResp);
        }
        OrderResult orderResult = new OrderResult(orderResp);
        orderResult.setStatus(new Status(orderResp.getReturnCode(), orderResp.getReturnDesc()));
        taskCompletionSource.setResult(orderResult);
    }

    private boolean a() {
        OrderRequest orderRequest = this.f5967a;
        if (orderRequest == null || TextUtils.isEmpty(orderRequest.signatureAlgorithm)) {
            return false;
        }
        HMSLog.i("OrderTaskApiCall", "Use the SHA256WithRSA/PSS algorithm.");
        return true;
    }

    public OrderTaskApiCall(String str, OrderRequest orderRequest, String str2) {
        super(str, JsonUtil.createJsonString(orderRequest), str2);
        this.f5967a = orderRequest;
        if (a()) {
            setApiLevel(4);
        }
    }
}
