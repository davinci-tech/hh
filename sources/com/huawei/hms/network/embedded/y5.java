package com.huawei.hms.network.embedded;

import com.huawei.health.R;
import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.network.embedded.b6;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes9.dex */
public class y5 extends v5 {
    public static final String g = "PingDetectQuery";
    public static final int h = 6000;
    public int e = 3;
    public int f = 30;

    @Override // com.huawei.hms.network.embedded.v5
    public w5 b(i5 i5Var) {
        a6 a6Var;
        int i;
        i5Var.b(0);
        if (NetworkUtil.getCurrentNetworkType() != 1) {
            return this.f5539a;
        }
        String wifiGatewayIp = NetworkUtil.getWifiGatewayIp(ContextHolder.getResourceContext());
        if (!CheckParamUtils.isIpV4(wifiGatewayIp) && !CheckParamUtils.isIpV6(wifiGatewayIp)) {
            i = b6.d.d;
        } else {
            if (!a(wifiGatewayIp)) {
                Future submit = this.b.submit(new a(wifiGatewayIp));
                try {
                    try {
                        a6Var = (a6) submit.get(6000L, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException | ExecutionException | TimeoutException unused) {
                        i5Var.a(b6.d.f5175a);
                        a6Var = null;
                    }
                    if (a6Var == null || !a6Var.h()) {
                        Logger.v(g, "the ping is failed,and exit detect this time");
                        i5Var.a(b6.d.f5175a);
                    } else {
                        i5Var.a(204);
                        if (a6Var.e() != null) {
                            if (b(a6Var.e()) > this.f) {
                                i5Var.a(b6.d.f);
                            } else {
                                this.f5539a.b(true);
                            }
                        }
                    }
                    return this.f5539a;
                } finally {
                    submit.cancel(true);
                }
            }
            i = b6.d.e;
        }
        i5Var.a(i);
        return this.f5539a;
    }

    private long b(String str) {
        return (long) (Float.parseFloat(str.replace("ms", "").trim()) + 0.5f);
    }

    public class a implements Callable<a6> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5581a;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public a6 call() {
            return new z5().a(this.f5581a, y5.this.e, y5.this.f, new StringBuffer());
        }

        public a(String str) {
            this.f5581a = str;
        }
    }

    private boolean a(String str) {
        return str.equals(ContextHolder.getResourceContext().getString(R.string._2130851508_res_0x7f0236b4)) || str.equals(ContextHolder.getResourceContext().getString(R.string._2130851509_res_0x7f0236b5)) || str.equals(ContextHolder.getResourceContext().getString(R.string._2130851510_res_0x7f0236b6));
    }

    public y5(ExecutorService executorService) {
        this.b = executorService;
    }
}
