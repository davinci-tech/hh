package com.huawei.hms.framework.network.restclient.dnkeeper;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.hianalytics.HianalyticsBaseData;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.network.NetworkKit;

/* loaded from: classes4.dex */
public class e extends HianalyticsBaseData {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4569a = "DNKeeperHianalyticsData";
    private static volatile boolean b = false;
    public static final String c = "sdk_version";
    public static final String d = "dns_request";
    public static final String e = "dns_subtype";
    public static final String f = "dnkeeper";
    public static final String g = "request_domain";
    public static final String h = "dnkeeper_time";
    public static final String i = "dns_server_ips";
    public static final String j = "trace_id";
    public static final String k = "trigger_type";
    public static final String l = "dns_sync_query";
    public static final String m = "dns_init";
    public static final String n = "dns_lazy_update";
    public static final String o = "error_code";
    public static final int p = 10020000;
    public static final int q = 10020001;
    public static final String r = "protocol_impl";

    public static void a() {
        synchronized (e.class) {
            if (!b) {
                try {
                    String option = NetworkKit.getInstance().getOption("core_ha_tag");
                    if (!option.isEmpty()) {
                        HianalyticsHelper.getInstance().setHaTag(option);
                    }
                } catch (NoSuchMethodError unused) {
                    Logger.w(f4569a, "sdk version is too low,ha tag cannot be found");
                }
                b = true;
            }
        }
    }

    public e() {
        put("sdk_version", "8.0.1.307");
        put("dns_subtype", f);
    }
}
