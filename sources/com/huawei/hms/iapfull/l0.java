package com.huawei.hms.iapfull;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hms.iapfull.network.model.QueryWithholdResultResponse;

/* loaded from: classes4.dex */
public class l0 extends i0 {
    @Override // com.huawei.hms.iapfull.i0
    protected boolean a(String str) {
        try {
            QueryWithholdResultResponse queryWithholdResultResponse = (QueryWithholdResultResponse) new Gson().fromJson(str, QueryWithholdResultResponse.class);
            y0.b("WithholdRetryInterceptor", "NewQueryOrderResultCallback isNeedQueryAgain time " + this.b + "returnCode::" + queryWithholdResultResponse.getReturnCode());
            if ("0".equals(queryWithholdResultResponse.getReturnCode())) {
                if (queryWithholdResultResponse.getData() != null && !queryWithholdResultResponse.getData().isEmpty()) {
                    y0.b("WithholdRetryInterceptor", "isWithholdSuccess");
                    return false;
                }
                y0.b("WithholdRetryInterceptor", "isWithholdSuccess fail response.getData() is null");
            }
            return true;
        } catch (JsonSyntaxException unused) {
            y0.a("WithholdRetryInterceptor", "needRetryPay JsonSyntaxException");
            return true;
        }
    }

    public l0() {
        this.f4718a = 3;
    }
}
