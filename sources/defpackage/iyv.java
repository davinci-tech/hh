package defpackage;

import com.google.protobuf.DescriptorProtos;
import health.compact.a.LogUtil;
import java.util.LinkedHashMap;

/* loaded from: classes5.dex */
public class iyv {
    public static void d() {
        b("85070001", DescriptorProtos.Edition.EDITION_99999_TEST_ONLY_VALUE);
    }

    public static void h() {
        b("85070006", 0);
    }

    public static void d(int i) {
        b("85070006", i);
    }

    public static void c(int i) {
        b("85070006", i);
    }

    public static void a(int i) {
        b("85070004", i);
    }

    public static void a() {
        b("85070004", 1002113);
    }

    public static void e() {
        b("85070004", 0);
    }

    public static void b(int i) {
        b("85070002", i);
    }

    public static void c() {
        b("85070002", 0);
    }

    public static void e(int i) {
        b("85070003", i);
    }

    public static void l() {
        b("85070003", 0);
    }

    public static void n() {
        LogUtil.c("DeviceOpAnalyticsUtil", "wifiSendFileToDevice enter.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put("sendType", "1");
        d("87010001", linkedHashMap);
    }

    public static void b() {
        LogUtil.c("DeviceOpAnalyticsUtil", "bluetoothSendFileToDevice enter.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put("sendType", "0");
        d("87010001", linkedHashMap);
    }

    public static void j() {
        LogUtil.c("DeviceOpAnalyticsUtil", "wifiP2pLinkSuccess enter.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put("wifiConnectStatus", "1");
        d("87010001", linkedHashMap);
    }

    public static void f() {
        LogUtil.c("DeviceOpAnalyticsUtil", "wifiP2pLinkFail enter.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put("wifiConnectStatus", "0");
        d("87010001", linkedHashMap);
    }

    public static void g() {
        LogUtil.c("DeviceOpAnalyticsUtil", "wifiP2pCloseStatistic enter.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put("wifiClose", "1");
        d("87010001", linkedHashMap);
    }

    public static void i() {
        LogUtil.c("DeviceOpAnalyticsUtil", "switchHeartRateDetection enter.");
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put("click", "1");
        d("80070006", linkedHashMap);
    }

    public static void d(String str, LinkedHashMap<String, String> linkedHashMap) {
        c(str, linkedHashMap, false);
    }

    public static void c(String str, String str2) {
        snu.e().wearLinkRiskEvent(str, str2);
    }

    private static void c(String str, LinkedHashMap<String, String> linkedHashMap, boolean z) {
        snu.e().opEvent2nd(str, linkedHashMap, z);
    }

    private static void b(String str, int i) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put("errorCode", Integer.toString(i));
        if (bky.e() && !snu.e().noCloudVersion()) {
            c(str, linkedHashMap, true);
        } else {
            d(str, linkedHashMap);
        }
    }
}
