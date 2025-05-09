package com.huawei.hms.network.netdiag;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.hianalytics.LinkedHashMapPack;
import com.huawei.hms.network.embedded.o4;
import com.huawei.hms.network.embedded.p4;
import com.huawei.hms.network.embedded.t5;
import com.huawei.hms.network.embedded.w4;
import com.huawei.hms.network.embedded.x5;
import com.huawei.hms.network.embedded.y4;
import com.huawei.hms.network.embedded.z4;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.inner.api.NetDiagnosisNetworkService;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import com.huawei.hms.network.netdiag.qoe.QoeMetrics;
import java.util.Map;

/* loaded from: classes9.dex */
public class NetDiagnosisService extends NetDiagnosisNetworkService {
    public static final String TAG = "NetDiagnosisService";

    @Override // com.huawei.hms.network.inner.api.InterceptorNetworkService
    public boolean isNetworkInterceptor() {
        return true;
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void onDestroy(Context context) {
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void serviceOptions(Map<String, String> map) {
        String str = map.get(PolicyNetworkService.GlobalConstants.ENABLE_DETECT_WITH_HTTP);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        p4.f().b(Boolean.parseBoolean(str));
    }

    @Override // com.huawei.hms.network.inner.api.NetDiagnosisNetworkService
    public void requestThirdMetrics(String str) {
        p4.f().a(str);
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void onCreate(Context context, Bundle bundle) {
        p4.f().a(context);
    }

    @Override // com.huawei.hms.network.inner.api.NetDiagnosisNetworkService
    public boolean networkUnavailable(long j, long j2) {
        return p4.f().b(j, j2);
    }

    @Override // com.huawei.hms.network.inner.api.NetDiagnosisNetworkService
    public Map<String, String> getWebsocketNetworkData(long j, long j2) {
        z4 a2 = p4.f().a(j, j2);
        LinkedHashMapPack linkedHashMapPack = new LinkedHashMapPack();
        if (a2 == null) {
            return linkedHashMapPack.getAll();
        }
        linkedHashMapPack.put("network_changed", a2.h()).put("mobile_signal_strength", a2.c().getMobileSignalStrength()).put("wifi_signal_strength", a2.c().getWifiSignalStrength());
        return linkedHashMapPack.getAll();
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getVersion() {
        return "8.0.1.307";
    }

    @Override // com.huawei.hms.network.inner.api.NetDiagnosisNetworkService
    public Map<String, String> getSyncNetDiagnosisInfo(long j, long j2, boolean z, boolean z2) {
        z4 a2 = p4.f().a(j, j2);
        LinkedHashMapPack linkedHashMapPack = new LinkedHashMapPack();
        if (a2 == null) {
            return linkedHashMapPack.getAll();
        }
        linkedHashMapPack.putIfNotDefault("network_changed", a2.h(), 0L).putIfNotDefault(x5.e, a2.a(), 0L).put("mobile_signal_strength", a2.c().getMobileSignalStrength()).put("wifi_signal_strength", a2.c().getWifiSignalStrength()).put(x5.q, a2.c().getLteCqi()).put(x5.n, a2.c().getLteRsrp()).put(x5.m, a2.c().getLteRsrq()).put(x5.p, a2.c().getLteRssi()).put(x5.o, a2.c().getLteRssnr());
        if (z) {
            linkedHashMapPack.put(x5.b, a2.g().c()).put(x5.c, a2.g().b());
            w4 b = a2.b();
            y4 b2 = b.b(0);
            y4 b3 = b.b(1);
            if (b2 != null && b2.c() != 0) {
                linkedHashMapPack.put(x5.f, b2.b());
                linkedHashMapPack.put(x5.g, b2.c());
                linkedHashMapPack.put(x5.h, b2.e());
            }
            if (b3 != null && b3.c() != 0) {
                linkedHashMapPack.put(x5.i, b3.b());
                linkedHashMapPack.put(x5.j, b3.c());
                linkedHashMapPack.put(x5.k, b3.e());
            }
        }
        if (z2) {
            Logger.d(TAG, "enable report qoe");
            t5 a3 = p4.f().a(true);
            if (a3 == null || !a3.isSuccess()) {
                return linkedHashMapPack.getAll();
            }
            linkedHashMapPack.put(x5.z, a3.getChannelNum()).put(x5.y, a3.getChannelIndex()).put(x5.x, a3.getDlRate()).put(x5.w, a3.getUlRate()).put(x5.v, a3.getDlBandwidth()).put(x5.u, a3.getUlBandwidth()).put(x5.t, a3.getDlRtt()).put(x5.s, a3.getUlRtt()).put(x5.r, a3.getUlPkgLossRate());
        }
        return linkedHashMapPack.getAll();
    }

    @Override // com.huawei.hms.network.inner.api.NetDiagnosisNetworkService
    public Map<String, Integer> getSignalMetrics() {
        return p4.f().c();
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getServiceType() {
        return NetDiagnosisService.class.getName();
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getServiceName() {
        return "netdiag";
    }

    @Override // com.huawei.hms.network.inner.api.NetDiagnosisNetworkService
    public QoeMetrics getQoeMetrics(boolean z) {
        return p4.f().a(z);
    }

    @Override // com.huawei.hms.network.inner.api.NetDiagnosisNetworkService
    public String getNetworkMetrics() {
        return p4.f().b();
    }

    @Override // com.huawei.hms.network.inner.api.InterceptorNetworkService
    public Interceptor getInterceptor() {
        return new o4();
    }

    @Override // com.huawei.hms.network.inner.api.NetDiagnosisNetworkService
    public boolean checkConnectivity() {
        return p4.f().a();
    }
}
