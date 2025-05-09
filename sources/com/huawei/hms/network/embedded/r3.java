package com.huawei.hms.network.embedded;

import android.os.SystemClock;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.conf.api.ConfigAPI;
import com.huawei.hms.network.inner.api.InterceptorNetworkService;
import com.huawei.hms.network.inner.api.NetDiagnosisNetworkService;
import com.huawei.hms.network.inner.api.NetworkKitInnerImpl;
import com.huawei.hms.network.inner.api.NetworkService;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes9.dex */
public class r3 {
    public static final String A = "isSuccess";
    public static final String B = "isActive";
    public static final String C = "continuePing";
    public static final int COUNTRECEIVE_TYPE = 2;
    public static final int COUNTSEND_TYPE = 1;
    public static final String D = "delayPing";
    public static final String E = "delayTimes";
    public static final String F = "ai_ping_enable";
    public static final String G = "ai_ping_minthreshold";
    public static final String H = "ai_ping_nat";
    public static final String I = "enable_dynamic_ping";
    public static final String J = "pingInterval";
    public static final int PING_TYPE_HIGH_EFFIC = 1;
    public static final int PING_TYPE_LOWER_CONSUMPTION = 2;
    public static final int PING_TYPE_LOWER_CONSUMPTION_TIME = 120000;
    public static final String TAG = "WebSocketResetPingIntervalManager";
    public static final String o = "mnc";
    public static final String p = "domain";
    public static final String q = "businessPing";
    public static final String r = "ping";
    public static final String s = "pingStatus";
    public static final String t = "wifi_signal_strength";
    public static final String u = "mobile_signal_strength";
    public static final String v = "networkChange";
    public static final String w = "network_changed";
    public static final String x = "pingIntervalList";
    public static final String y = "networkType";
    public static final String z = "firstNetworkType";

    /* renamed from: a, reason: collision with root package name */
    public int f5451a;
    public int b;
    public long c;
    public int d;
    public int e;
    public int f;
    public NetDiagnosisNetworkService g;
    public l3 h;
    public long i;
    public Map<String, String> j;
    public NetworkService k;
    public boolean m;
    public int l = -2;
    public boolean n = false;

    public void setRequestFinishedInfo(l3 l3Var) {
        this.h = l3Var;
    }

    public int setPingResult(boolean z2, long j, LinkedList<Long> linkedList) {
        int i;
        if (getNetworkDate(z2, j, linkedList)) {
            Map<String, String> pingResult = pingResult(this.f, this.j);
            if (pingResult != null) {
                this.j.clear();
                this.j.putAll(pingResult);
                String str = this.j.get(C);
                String str2 = this.j.get(D);
                String str3 = this.j.get(r);
                if (str != null) {
                    i = StringUtils.stringToInteger(str, this.e);
                } else {
                    i = this.e;
                    if (str2 != null) {
                        i = StringUtils.stringToInteger(str2, i);
                    } else if (str3 != null) {
                        i = StringUtils.stringToInteger(str3, i);
                    }
                }
                this.d = i;
                return i;
            }
            Logger.w(TAG, "PingResult no data map, ping " + this.e);
        }
        return this.e;
    }

    public void setOnOpenTime(long j) {
        this.i = j;
    }

    public boolean setActionType(int i) {
        this.f = i;
        boolean z2 = false;
        if (i != 1 && i != 2) {
            Logger.i(TAG, "canot predictor model ,type is:" + i);
            return false;
        }
        NetworkService networkService = this.k;
        if (networkService != null) {
            z2 = networkService.initWebSocketPingModelPredictor();
            if (z2) {
                this.n = true;
            }
        } else {
            Logger.v(TAG, "not found AIPolicyService ai_ping_enable:" + this.m);
        }
        return z2;
    }

    public boolean predictorModel() {
        NetworkService networkService = this.k;
        if (networkService != null) {
            return networkService.initWebSocketPingModelPredictor();
        }
        Logger.v(TAG, "predictorModel not found AIPolicyService ai_ping_enable:" + this.m);
        return false;
    }

    public Map<String, String> pingResult(int i, Map<String, String> map) {
        if (this.k == null) {
            Logger.w(TAG, "pingResult not find AiService");
            return a();
        }
        if (this.n) {
            this.n = false;
            this.j.putAll(a());
        }
        return this.k.pingResult(i, map);
    }

    public boolean isWebSocketActive() {
        return System.currentTimeMillis() - this.c <= ((long) this.d);
    }

    public LinkedHashMap<String, String> getReporterData() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        int stringToInteger = StringUtils.stringToInteger(String.valueOf(ConfigAPI.getValue("ai_ping_minthreshold")), 0);
        int stringToInteger2 = StringUtils.stringToInteger(String.valueOf(ConfigAPI.getValue("ai_ping_nat")), 0);
        linkedHashMap.put("ai_ping_enable", this.m + "");
        linkedHashMap.put("ai_ping_minthreshold", stringToInteger + "");
        linkedHashMap.put("ai_ping_nat", stringToInteger2 + "");
        linkedHashMap.put(I, this.f + "");
        linkedHashMap.put(J, this.d + "");
        Map<String, String> map = this.j;
        if (map != null) {
            if (map.containsKey(s)) {
                linkedHashMap.put(s, this.j.get(s));
            } else {
                linkedHashMap.put(s, "");
            }
        }
        return linkedHashMap;
    }

    public boolean getNetworkDate(boolean z2, long j, LinkedList<Long> linkedList) {
        NetDiagnosisNetworkService netDiagnosisNetworkService = this.g;
        if (netDiagnosisNetworkService == null) {
            Logger.w(TAG, "getNetworkDate not find NetDiagnosisNetworkService");
            return false;
        }
        Map<String, String> websocketNetworkData = netDiagnosisNetworkService.getWebsocketNetworkData(this.i, SystemClock.elapsedRealtime());
        this.i = SystemClock.elapsedRealtime();
        this.j.put(q, j + "");
        this.j.put(x, linkedList.toString());
        this.j.put(o, NetworkUtil.getMNC(ContextHolder.getAppContext()));
        int currentNetworkType = NetworkUtil.getCurrentNetworkType();
        this.j.put(y, currentNetworkType + "");
        if (this.l == -2) {
            this.l = currentNetworkType;
        }
        this.j.put(z, this.l + "");
        this.j.put(B, isWebSocketActive() + "");
        this.j.put(A, z2 + "");
        this.j.put("domain", this.h.getHost());
        this.j.put("wifi_signal_strength", websocketNetworkData.get("wifi_signal_strength"));
        this.j.put("mobile_signal_strength", websocketNetworkData.get("mobile_signal_strength"));
        this.j.put(v, websocketNetworkData.get("network_changed"));
        Logger.d(TAG, "NetworkDate " + websocketNetworkData.get("wifi_signal_strength") + " " + websocketNetworkData.get("mobile_signal_strength") + " " + websocketNetworkData.get("network_changed"));
        return true;
    }

    public int getCount() {
        return this.f5451a + this.b;
    }

    public void counting(int i) {
        if (i == 1) {
            this.f5451a++;
        } else {
            this.b++;
        }
        this.c = System.currentTimeMillis();
    }

    public boolean aiPingEnable() {
        return this.m;
    }

    private Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put(r, this.e + "");
        hashMap.put(s, "0");
        if (this.f == 2) {
            hashMap.put(E, "1");
            hashMap.put(D, "120000");
        }
        return hashMap;
    }

    public r3(int i, int i2, String str) {
        this.f = i;
        this.e = i2;
        this.d = i2;
        NetworkService service = NetworkKitInnerImpl.getInstance().getService("ai");
        if (service != null) {
            this.k = service;
            this.m = StringUtils.stringToBoolean(String.valueOf(ConfigAPI.getValue("ai_ping_enable")), false);
        }
        InterceptorNetworkService interceptorNetworkService = NetworkKitInnerImpl.getInstance().getInterceptorNetworkService("netdiag");
        if (interceptorNetworkService != null) {
            NetDiagnosisNetworkService netDiagnosisNetworkService = (NetDiagnosisNetworkService) interceptorNetworkService;
            this.g = netDiagnosisNetworkService;
            netDiagnosisNetworkService.requestThirdMetrics(str);
        }
        this.j = a();
        Logger.w(TAG, "actionType：" + i);
    }
}
