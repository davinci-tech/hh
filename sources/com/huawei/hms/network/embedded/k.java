package com.huawei.hms.network.embedded;

import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import com.huawei.hms.network.inner.utils.CheckConfigUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5330a = "networkkit_";
    public static final String b = "core_configversion";
    public static final String c = "core_unitransenable";
    public static final String d = "dynamic_load";
    public static final String e = "core_switch_profile_v2";
    public static final String f = "1.0.0.100";
    public static final String g = "enable";
    public static final Map<String, Object> h = new HashMap();
    public static final k i = new k();

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public static final String f5331a = "bbrv1";
        public static final String b = "pcc";
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        public static final boolean f5332a = true;
        public static final boolean b = false;
        public static final int c = 600;
        public static final boolean d = true;
        public static final long e = 0;
        public static final boolean f = false;
        public static final boolean g = false;
        public static final int h = 8;
        public static final int i = 5;
        public static final int j = -1;
        public static final boolean k = false;
        public static final long l = 604800;
        public static final long m = 86400;
        public static final String n = "com.huawei.music,com.android.mediacenter";
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        public static final String f5333a = "huawei_module_quic_pro";
        public static final boolean b = false;
        public static final boolean c = false;
        public static final boolean d = false;
        public static final boolean e = false;
        public static final String f = "bbrv1";
        public static final String g = "none";
        public static final boolean h = false;
        public static final int i = 0;
        public static final int j = 1;
        public static final int k = -1;
        public static final int l = 0;
        public static final int m = 1296000;
        public static final int n = 10;
        public static final int o = 2;
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        public static final int f5334a = 10000;
        public static final int b = 10000;
        public static final int c = 1;
        public static final int d = 0;
        public static final int e = 0;
        public static final int f = 10000;
        public static final int g = 500;
        public static final boolean h = true;
        public static final boolean i = false;
        public static final boolean j = true;
        public static final boolean k = false;
        public static final boolean l = false;
        public static final int m = 0;
        public static final int n = 0;
        public static final int o = 0;
        public static final boolean p = false;
        public static final boolean q = true;
        public static final int r = 0;
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        public static final int f5335a = 2;
        public static final int b = 2097152;
        public static final boolean c = false;
        public static final int d = 0;
    }

    public static final class f {

        /* renamed from: a, reason: collision with root package name */
        public static final String f5336a = "serial";
        public static final String b = "none";
    }

    public static final class g {

        /* renamed from: a, reason: collision with root package name */
        public static final String f5337a = "abtest_dyfrag_groupid";
        public static final String b = "filemanager_slice_threshold";
        public static final String c = "filemanager_slice_num";
        public static final String d = "filemanager_auto_slice";
        public static final String e = "filemanager_executor_num";
        public static final String f = "filemanager_protocol_policy";
        public static final String g = "filemanager_pcc_switch";
    }

    public static final class h {

        /* renamed from: a, reason: collision with root package name */
        public static final long f5338a = 120000;
        public static final long b = 1000;
        public static final long c = 60000;
        public static final long d = 1000;
    }

    public static final class i {

        /* renamed from: a, reason: collision with root package name */
        public static final String f5339a = "netdiag_enable_report_qoe";
        public static final String b = "netdiag_qoe_report_suppress_time";
        public static final String c = "netdiag_qoe_call_suppress_time";
    }

    public Set<String> a() {
        return h.keySet();
    }

    public Object a(String str) {
        return h.get(str);
    }

    private void c() {
        Iterator<String> it = j.x.iterator();
        while (it.hasNext()) {
            h.put(it.next(), "");
        }
        Map<String, Object> map = h;
        map.put("core_switch_ai", true);
        map.put("core_switch_netdiag", true);
        map.put(NetworkService.Constants.REMOTE_SCENE_SWITCH, false);
        map.put(NetworkService.Constants.AI_IPSORT_SWITCH, false);
        map.put(NetworkService.Constants.AI_CONNECTTIMEOUT_SWITCH, false);
        map.put(NetworkService.Constants.AI_CONNECTTIMEOUT_THRESHOLD, "");
        map.put("ai_ping_enable", false);
        map.put("ai_ping_minthreshold", "");
        map.put("ai_ping_nat", "");
        map.put("core_switch_dns", true);
        map.put(e, false);
        map.put(c, false);
        map.put("core_configversion", f);
        map.put(d, g);
        map.put(PolicyNetworkService.GlobalConstants.ENABLE_DETECT_WITH_HTTP, true);
        map.put(PolicyNetworkService.GlobalConstants.ENABLE_HTTPDNS, false);
        map.put(PolicyNetworkService.GlobalConstants.DNS_RESULT_TTL, 600);
        map.put("core_enable_ipv6_preferred", true);
        map.put("core_enable_site_detect", false);
        map.put(PolicyNetworkService.GlobalConstants.SITE_DETECT_THRESHOLD, 0L);
        map.put("core_ha_tag", "");
        map.put(PolicyNetworkService.GlobalConstants.CONNECT_POOL_SIZE, 8);
        map.put(PolicyNetworkService.GlobalConstants.CONNECT_KEEP_ALIVE_DURATION, 5);
        map.put("core_enable_privacy_policy", false);
        map.put(PolicyNetworkService.GlobalConstants.CONFIG_EXPIRED_TIME, Long.valueOf(b.l));
        map.put(PolicyNetworkService.GlobalConstants.AB_EXPIRED_TIME, Long.valueOf(b.m));
        map.put(PolicyNetworkService.GlobalConstants.AB_ALLOWED_LIST, b.n);
        map.put(PolicyNetworkService.GlobalConstants.INDEPENDENT_CONFIGS, "");
        map.put(PolicyNetworkService.GlobalConstants.PROFILE_CONFIGS, "");
        map.put("core_disable_weaknetwork_retry", true);
        map.put("core_enable_plaintext_url_path", false);
        map.put(PolicyNetworkService.ClientConstants.FOLLOW_REDIRECTS, true);
        map.put(PolicyNetworkService.ClientConstants.FOLLOW_SSL_REDIRECTS, true);
        map.put(PolicyNetworkService.ClientConstants.SUPPORT_PROTOCOLS, Arrays.asList(CheckConfigUtils.Constants.HTTP_2, CheckConfigUtils.Constants.HTTP_1_1));
        map.put(PolicyNetworkService.ClientConstants.TRAFFIC_CLASS, 0);
        map.put(PolicyNetworkService.ClientConstants.QUIC_ENABLE_GSO, false);
        map.put(PolicyNetworkService.ClientConstants.QUIC_ENABLE_BANDWIDTH, true);
        map.put(PolicyNetworkService.ClientConstants.SMALLPKT_FEC, false);
        map.put(PolicyNetworkService.ClientConstants.SMALLPKT_FEC_INITIALLEVEL, 0);
        map.put(PolicyNetworkService.ClientConstants.ENABLE_MULTIPATH, false);
        map.put(PolicyNetworkService.ClientConstants.MULTIPATH_TYPE, 1);
        map.put(PolicyNetworkService.ClientConstants.TLS_ZERO_RTT, false);
        map.put(PolicyNetworkService.ClientConstants.REDUNDANT_ACK, false);
        map.put(PolicyNetworkService.ClientConstants.CONGESTION_CONTROL_TYPE, "bbrv1");
        map.put(PolicyNetworkService.ClientConstants.PRIORITY_CONTROL_TYPE, "none");
        map.put(PolicyNetworkService.ClientConstants.WAIT_FOR_NEW_NETWORK_TIME_MS, -1);
        map.put(PolicyNetworkService.ClientConstants.HWHTTP_ENABLE_CONNECTION_MIGRATION, false);
        map.put(PolicyNetworkService.GlobalConstants.REPORT_RATE, -1);
        map.put(PolicyNetworkService.GlobalConstants.QUIC_REPORT_RATE, -1);
        map.put(PolicyNetworkService.ClientConstants.SERVER_CONFIG_PERSIST_DELAY, 0);
        map.put(PolicyNetworkService.ClientConstants.COMMIT_FILE_DELAY, 10);
        map.put(PolicyNetworkService.ClientConstants.TLS13_SESSION_TIMEOUT, 1296000);
        map.put(PolicyNetworkService.ClientConstants.QUIC_BROKEN_MDOE, 2);
        map.put("core_call_timeout", 0);
        map.put("core_connect_timeout", 10000);
        map.put("core_write_timeout", 10000);
        map.put("core_read_timeout", 10000);
        map.put("core_retry_time", 1);
        map.put("core_concurrent_connect_delay", 500);
        map.put("core_ping_interval", 0);
        map.put("core_enable_concurrent_connect", true);
        map.put("core_connect_empty_body", false);
        map.put(PolicyNetworkService.RequestConstants.ENABLE_TRAFFIC_CONTROL_WITH_429, false);
        map.put(PolicyNetworkService.RequestConstants.MAX_REQUEST_DISCRETE_TIME, 0);
        map.put(PolicyNetworkService.RequestConstants.MAX_RETRY_WAITING_TIME, 0);
        map.put(g.f5337a, "");
        map.put(g.b, 2097152);
        map.put(g.c, 2);
        map.put(g.d, false);
        map.put(g.e, 0);
        map.put(g.f, "");
        map.put(g.g, "");
        map.put(i.f5339a, false);
        map.put(i.b, 120000L);
        map.put(i.c, 1000L);
        map.put(PolicyNetworkService.QuicConstants.QUICHINT, "");
        map.put(PolicyNetworkService.QuicConstants.MODULE_NAME, c.f5333a);
        map.put(PolicyNetworkService.ClientConstants.QUIC_CONNECTION_OPTIONS, "");
        map.put("core_enable_alllink_delay_analysis", false);
        map.put(PolicyNetworkService.RequestConstants.METRIC_POLICY, 0);
    }

    public static k b() {
        return i;
    }

    public k() {
        c();
    }
}
