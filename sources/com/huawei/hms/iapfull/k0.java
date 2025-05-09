package com.huawei.hms.iapfull;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hms.iapfull.network.model.QueryOrderResponse;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes4.dex */
public class k0 extends i0 {
    public static final Set<String> c;

    @Override // com.huawei.hms.iapfull.i0
    protected boolean a(String str) {
        try {
            QueryOrderResponse queryOrderResponse = (QueryOrderResponse) new Gson().fromJson(str, QueryOrderResponse.class);
            y0.b("PayRetryInterceptor", "NewQueryOrderResultCallback isNeedQueryAgain time " + this.b + "returnCode::" + queryOrderResponse.getReturnCode() + " stateCode :: " + queryOrderResponse.getStatus());
            if ("0".equals(queryOrderResponse.getReturnCode())) {
                if (1 == queryOrderResponse.getStatus()) {
                    return false;
                }
                if (queryOrderResponse.getStatus() == 0) {
                    if (!c.contains(queryOrderResponse.getErrCode())) {
                        return false;
                    }
                }
            }
            return true;
        } catch (JsonSyntaxException unused) {
            y0.a("PayRetryInterceptor", "needRetryPay JsonSyntaxException");
            return true;
        }
    }

    public k0() {
        this.f4718a = 2;
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("x400130");
        hashSet.add("x4001301");
        hashSet.add("80110");
        hashSet.add("80111");
        hashSet.add("80115");
        c = Collections.unmodifiableSet(hashSet);
    }
}
