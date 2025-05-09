package com.huawei.hms.iapfull;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.iapfull.network.model.PaySignRequest;
import com.huawei.hms.iapfull.network.model.ReportPayResultParams;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.tencent.open.utils.HttpUtils;

/* loaded from: classes4.dex */
public class t0 {
    public void a(Context context, ReportPayResultParams reportPayResultParams, s0 s0Var) {
        if (s0Var == null) {
            return;
        }
        if (TextUtils.isEmpty(g0.a().f4711a)) {
            y0.a("PayRepository", "startReportPayResult: Grs is fail");
            return;
        }
        if (context == null) {
            s0Var.a(ResultCode.CODE_NO_NEW_DATA, "param error");
        } else if (!a1.b(context)) {
            s0Var.a("-1", HttpUtils.NetworkUnavailableException.ERROR_INFO);
        } else {
            com.huawei.hms.iapfull.network.b.a(context.getApplicationContext(), g0.a().f4711a).reportPayResult(reportPayResultParams).enqueue(new com.huawei.hms.iapfull.network.a(new l(s0Var)));
        }
    }

    public void a(Context context, PaySignRequest paySignRequest, r0 r0Var) {
        if (TextUtils.isEmpty(g0.a().f4711a)) {
            y0.a("PayRepository", "startReportPayResult: Grs is fail");
            return;
        }
        if (context == null) {
            r0Var.a(ResultCode.CODE_NO_NEW_DATA, "param error");
        } else if (!a1.b(context)) {
            r0Var.a("-1", HttpUtils.NetworkUnavailableException.ERROR_INFO);
        } else {
            com.huawei.hms.iapfull.network.b.a(context.getApplicationContext(), g0.a().f4711a).paySign(paySignRequest).enqueue(new com.huawei.hms.iapfull.network.a(new f(r0Var)));
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final t0 f4767a = new t0();
    }

    public static t0 a() {
        return b.f4767a;
    }

    private t0() {
    }
}
