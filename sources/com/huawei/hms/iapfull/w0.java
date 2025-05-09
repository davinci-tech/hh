package com.huawei.hms.iapfull;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.iapfull.network.model.QueryOrderRequest;
import com.huawei.hms.iapfull.network.model.QueryOrderResponse;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.tencent.open.utils.HttpUtils;

/* loaded from: classes4.dex */
public class w0 {
    public void a(Context context, int i, QueryOrderRequest queryOrderRequest, u0 u0Var) {
        Submit<QueryOrderResponse> queryOrder;
        com.huawei.hms.iapfull.network.a aVar;
        String str;
        String str2;
        if (context == null) {
            str = ResultCode.CODE_NO_NEW_DATA;
            str2 = "param error";
        } else {
            if (a1.b(context)) {
                h hVar = new h(u0Var);
                if (5 == i) {
                    y0.b("QueryRepository", "Query order poll start");
                    queryOrder = com.huawei.hms.iapfull.network.d.a(context.getApplicationContext(), g0.a().f4711a).pollQueryOrder(queryOrderRequest);
                    aVar = new com.huawei.hms.iapfull.network.a(hVar);
                } else {
                    queryOrder = com.huawei.hms.iapfull.network.d.c(context.getApplicationContext(), g0.a().f4711a).queryOrder(queryOrderRequest);
                    aVar = new com.huawei.hms.iapfull.network.a(hVar);
                }
                queryOrder.enqueue(aVar);
                return;
            }
            str = "-1";
            str2 = HttpUtils.NetworkUnavailableException.ERROR_INFO;
        }
        u0Var.a(str, str2);
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static w0 f4773a = new w0();
    }

    public static w0 a() {
        return b.f4773a;
    }

    private w0() {
    }
}
