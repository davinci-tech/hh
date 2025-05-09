package com.huawei.hms.network.inner.api;

import android.content.Context;
import android.os.Bundle;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public abstract class NetworkService {

    public static final class Constants {
        public static final String AI_CONNECTTIMEOUT_SWITCH = "ai_connecttimeout_switch";
        public static final String AI_CONNECTTIMEOUT_THRESHOLD = "ai_connecttimeout_threshold";
        public static final String AI_IPSORT_SWITCH = "ai_ipsort_switch";
        public static final String AI_PING_ENABLE = "ai_ping_enable";
        public static final String AI_PING_MINTHRESHOLD = "ai_ping_minthreshold";
        public static final String AI_PING_NAT = "ai_ping_nat";
        public static final String AI_SERVICE = "ai";
        public static final String CONFIG_SERVICE = "config";
        public static final String DNS_SERVICE = "dns";
        public static final String NETDIAG_SERVICE = "netdiag";
        public static final String REMOTE_CONFIG_SWITCH = "remote_config_switch";
        public static final String REMOTE_SCENE_SWITCH = "remote_scene_switch";
        public static final String GRS_SERVICE = "grs";
        public static final List<String> NETWORK_SERVICES = Collections.unmodifiableList(Arrays.asList("ai", "dns", GRS_SERVICE, "netdiag"));
    }

    public void clear() {
    }

    public int compareIp(InetAddress inetAddress, InetAddress inetAddress2) {
        return -1;
    }

    public int getAiConnectTimeout() {
        return -1;
    }

    public String getConfigValue(String str) {
        return null;
    }

    public abstract String getServiceName();

    public abstract String getServiceType();

    public abstract String getVersion();

    public boolean initWebSocketPingModelPredictor() {
        return false;
    }

    public List<List<InetAddress>> ipListsSort(List<List<InetAddress>> list) {
        return null;
    }

    public abstract void onCreate(Context context, Bundle bundle);

    public abstract void onDestroy(Context context);

    public Map<String, String> pingResult(int i, Map<String, String> map) {
        return null;
    }

    public void serviceOptions(Map<String, String> map) {
    }
}
