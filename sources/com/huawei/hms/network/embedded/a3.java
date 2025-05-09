package com.huawei.hms.network.embedded;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.framework.common.hianalytics.HianalyticsBaseData;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.framework.common.hianalytics.InitReport;
import com.huawei.hms.network.inner.api.NetworkReceiver;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.LinkedHashMap;

/* loaded from: classes9.dex */
public class a3 {
    public static final String APP_NAME = "app_name";
    public static final String EVENT_ID = "sysevent";
    public static final String EVENT_TYPE = "sysevent_type";
    public static final String EVENT_VALUE = "sysevent_value";
    public static final String NETSWITCH_EVENT = "netswitch";

    /* renamed from: a, reason: collision with root package name */
    public static final String f5152a = "SysEventData";

    public static void reportSysEvent(Long l, String str, String str2) {
        if (HianalyticsHelper.getInstance().isEnableReport(ContextHolder.getAppContext())) {
            InitReport.reportWhenInit(new a(l, str, str2));
        }
    }

    public static void reportNetSwitch(NetworkInfo networkInfo, NetworkInfo networkInfo2) {
        if (NetworkUtil.isConnectTypeChange(networkInfo, networkInfo2)) {
            reportSysEvent(Long.valueOf(System.currentTimeMillis()), NETSWITCH_EVENT, NetworkUtil.getPrimaryNetworkType(networkInfo) + Constants.LINK + NetworkUtil.getPrimaryNetworkType(networkInfo2));
        }
    }

    public static void init() {
        l4.b().a(new b());
    }

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Long f5153a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;

        @Override // java.lang.Runnable
        public void run() {
            a3.b(this.f5153a, this.b, this.c);
        }

        public a(Long l, String str, String str2) {
            this.f5153a = l;
            this.b = str;
            this.c = str2;
        }
    }

    public static class b implements NetworkReceiver {

        /* renamed from: a, reason: collision with root package name */
        public final NetworkInfo f5154a = NetworkUtil.getNetworkInfo(ContextHolder.getAppContext());

        @Override // com.huawei.hms.network.inner.api.NetworkReceiver
        public void onReceiveMsg(Context context, Intent intent) {
            NetworkInfo networkInfo = NetworkUtil.getNetworkInfo(context);
            if (networkInfo == null) {
                Logger.v(a3.f5152a, "Get NetworkInfo failed");
            } else {
                Logger.v(a3.f5152a, "networkInfo: %s", networkInfo);
                a3.reportNetSwitch(this.f5154a, networkInfo);
            }
        }
    }

    public static void b(Long l, String str, String str2) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("sdk_type", "UxPP");
        linkedHashMap.put(HianalyticsBaseData.SDK_NAME, "networkkit");
        linkedHashMap.put("sdk_version", "8.0.1.307");
        linkedHashMap.put("time", l + "");
        linkedHashMap.put(EVENT_TYPE, str);
        linkedHashMap.put(EVENT_VALUE, str2);
        linkedHashMap.put("app_name", ContextHolder.getAppContext() != null ? ContextHolder.getAppContext().getPackageName() : "null context");
        HianalyticsHelper.getInstance().onEvent(linkedHashMap, EVENT_ID);
        Logger.v(f5152a, "%s", linkedHashMap);
    }
}
