package com.huawei.hms.network.embedded;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.common.ReflectionUtils;
import com.huawei.hms.framework.network.restclient.dnkeeper.DNKeeperManager;
import com.huawei.hms.framework.network.restclient.dnkeeper.RequestHost;
import com.huawei.hms.framework.network.restclient.hwhttp.dns.DnsResult;

/* loaded from: classes9.dex */
public class v implements l0 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5533a = "DefaultDNKeeper";
    public static volatile v b;

    @Override // com.huawei.hms.network.embedded.l0
    public boolean a(String str) {
        return DNKeeperManager.getInstance().removeCache(str);
    }

    @Override // com.huawei.hms.network.embedded.l0
    public void a(int i) {
        DNKeeperManager.getInstance().setRequestIntervalFailed(i);
    }

    @Override // com.huawei.hms.network.embedded.l0
    public String a() {
        return DNKeeperManager.getInstance().getDomainName();
    }

    @Override // com.huawei.hms.network.embedded.l0
    public m0 a(String str, String str2, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        RequestHost requestHost = new RequestHost(str);
        requestHost.setFailIP(str2);
        requestHost.setDnsFailType("" + i);
        requestHost.enableAccelerate(true);
        return a(DNKeeperManager.getInstance().queryIpsSync(requestHost));
    }

    public static v a(Context context) {
        if (!ReflectionUtils.checkCompatible("com.huawei.hms.framework.network.restclient.dnkeeper.DNKeeperManager")) {
            return null;
        }
        CheckParamUtils.checkNotNull(context, "context == null");
        if (b == null) {
            b = new v(context);
        }
        return b;
    }

    private m0 a(DnsResult dnsResult) {
        m0 m0Var = new m0();
        if (dnsResult != null && !dnsResult.isEmpty()) {
            m0Var.c(dnsResult.getType());
            m0Var.a(dnsResult.getCreateTime());
            m0Var.a(dnsResult.getCache());
            m0Var.b(dnsResult.getIpList());
        }
        return m0Var;
    }

    public v(Context context) {
        DNKeeperManager.getInstance().init(context.getApplicationContext());
    }
}
