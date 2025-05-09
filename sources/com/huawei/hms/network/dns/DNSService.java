package com.huawei.hms.network.dns;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.network.embedded.t;
import com.huawei.hms.network.embedded.v;
import com.huawei.hms.network.embedded.w;
import com.huawei.hms.network.embedded.y;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.inner.api.DnsNetworkService;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import com.huawei.hms.network.inner.api.RequestContext;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes9.dex */
public class DNSService extends DnsNetworkService {
    public static final String TAG = "DNSService";
    public String falseStr = "false";

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getServiceType() {
        return null;
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void onDestroy(Context context) {
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void serviceOptions(Map<String, String> map) {
        t m;
        boolean z;
        t.m().a(Boolean.parseBoolean(map.get(PolicyNetworkService.GlobalConstants.ENABLE_HTTPDNS)));
        t.m().b(Integer.parseInt(map.get(PolicyNetworkService.GlobalConstants.DNS_RESULT_TTL)));
        if (TextUtils.equals(this.falseStr, map.get("core_enable_ipv6_preferred"))) {
            m = t.m();
            z = false;
        } else {
            m = t.m();
            z = true;
        }
        m.b(z);
        try {
            t.m().c(Boolean.parseBoolean(map.get("core_enable_site_detect")));
        } catch (NumberFormatException unused) {
            Logger.w(TAG, "Parse enable_site_detect error");
        }
        try {
            t.m().a(Long.parseLong(map.get(PolicyNetworkService.GlobalConstants.SITE_DETECT_THRESHOLD)));
        } catch (NumberFormatException unused2) {
            Logger.w(TAG, "Parse site_detect_threshold error");
        }
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void onCreate(Context context, Bundle bundle) {
        t.m().a(ContextHolder.getAppContext(), v.a(context));
    }

    @Override // com.huawei.hms.network.inner.api.DnsNetworkService
    public List<InetAddress> lookup(String str) throws UnknownHostException {
        List<InetAddress> list;
        try {
            list = t.m().c(str);
        } catch (UnknownHostException e) {
            throw e;
        } catch (Throwable th) {
            Logger.e(TAG, "may be error", th);
            list = null;
        }
        if (list == null || list.isEmpty()) {
            return y.b(str);
        }
        if (getDnsType().equals(w.k) && getDnsCache() != 1) {
            Logger.v(TAG, "localDns server use default ip list");
            return list;
        }
        if (list != null && !NetworkUtil.isSupportIpv6Net()) {
            Iterator<InetAddress> it = list.iterator();
            while (it.hasNext()) {
                InetAddress next = it.next();
                if (CheckParamUtils.isIpV6(next.getHostAddress())) {
                    Logger.v(TAG, "remove ipv6 address:" + next.getHostAddress());
                    it.remove();
                }
            }
        }
        return list;
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getVersion() {
        return "8.0.1.307";
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getServiceName() {
        return "dns";
    }

    @Override // com.huawei.hms.network.inner.api.DnsNetworkService
    public String getDnsType() {
        return t.m().a(t.m().i());
    }

    @Override // com.huawei.hms.network.inner.api.DnsNetworkService
    public long getDnsTtl() {
        return t.m().h();
    }

    @Override // com.huawei.hms.network.inner.api.DnsNetworkService
    public int getDnsStatus() {
        return t.m().g();
    }

    @Override // com.huawei.hms.network.inner.api.DnsNetworkService
    public int getDnsCache() {
        return t.m().f();
    }

    @Override // com.huawei.hms.network.inner.api.DnsNetworkService
    public void endEachRequest(RequestContext requestContext) {
        Response<ResponseBody> response = requestContext.response();
        Throwable throwable = requestContext.throwable();
        if (response != null) {
            t.m().a(NetworkUtil.getHost(requestContext.request().getUrl()), (String) Integer.valueOf(response.getCode()));
        } else {
            if (requestContext.isCancel()) {
                return;
            }
            t.m().a(NetworkUtil.getHost(requestContext.request().getUrl()), (String) throwable);
        }
    }

    @Override // com.huawei.hms.network.inner.api.DnsNetworkService
    public void dnsPrefetch(String str) {
        t.i(str);
    }

    public static final class b implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            t.m().a();
        }

        public b() {
        }
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void clear() {
        try {
            y.b().execute(new b());
        } catch (RejectedExecutionException e) {
            Logger.w(TAG, "Execute clear error", e);
        }
    }

    @Override // com.huawei.hms.network.inner.api.DnsNetworkService
    public void beginEachRequest(RequestContext requestContext) {
        t.m().g(NetworkUtil.getHost(requestContext.request().getUrl()));
    }

    private void printDnsIps(List<InetAddress> list) {
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<InetAddress> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getHostAddress());
            }
        } catch (NullPointerException e) {
            Logger.w(TAG, e);
        }
        Logger.v(TAG, "dns ips:" + Arrays.toString(arrayList.toArray()));
    }
}
