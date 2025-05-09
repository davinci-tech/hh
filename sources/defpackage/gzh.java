package defpackage;

import android.text.TextUtils;
import com.amap.api.services.district.DistrictSearchQuery;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.WebViewHelp;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class gzh {
    public static void d() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put(CommonUtil.PAGE_TYPE, 2);
        ixx.d().d(BaseApplication.e(), "1040081", hashMap, 0);
    }

    public static void c() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put(CommonUtil.PAGE_TYPE, 3);
        ixx.d().d(BaseApplication.e(), "1040081", hashMap, 0);
    }

    public static void h() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("event", 4);
        ixx.d().d(BaseApplication.e(), "1040083", hashMap, 0);
    }

    public static void f() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("event", 3);
        ixx.d().d(BaseApplication.e(), "1040083", hashMap, 0);
    }

    public static void b() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("event", 1);
        ixx.d().d(BaseApplication.e(), "1040083", hashMap, 0);
    }

    public static void a(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(BaseApplication.e(), "1040087", hashMap, 0);
    }

    public static void e() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("event", 2);
        ixx.d().d(BaseApplication.e(), "1040083", hashMap, 0);
    }

    public static void b(boolean z, int i) {
        HashMap hashMap = new HashMap(7);
        hashMap.put("click", 1);
        hashMap.put("searchResult", z + "");
        hashMap.put(DistrictSearchQuery.KEYWORDS_CITY, i());
        hashMap.put("routeStyle", Integer.valueOf(i));
        hashMap.put(BleConstants.SPORT_TYPE, 258);
        hashMap.put("orderType", null);
        hashMap.put(CommonUtil.PAGE_TYPE, 2);
        ixx.d().d(BaseApplication.e(), "1040082", hashMap, 0);
    }

    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_RouteBiUtils", "routeId is empty, no bi");
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("event", 1);
        hashMap.put("routeID", str);
        ixx.d().d(BaseApplication.e(), "1040090", hashMap, 0);
    }

    public static void e(int i, int i2, String str, int i3) {
        HashMap hashMap = new HashMap(6);
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i2));
        hashMap.put("sportPage", 258);
        hashMap.put("routeStyle", Integer.valueOf(i));
        hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, str);
        hashMap.put("status", Integer.valueOf(i3));
        ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), "1040079", hashMap, 0);
    }

    public static void a(long j) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("grades", Long.valueOf(j));
        ixx.d().d(BaseApplication.e(), "1040080", hashMap, 0);
    }

    public static void a() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put(CommonUtil.PAGE_TYPE, 1);
        ixx.d().d(BaseApplication.e(), "1040081", hashMap, 0);
    }

    public static void d(String str, String str2, String str3, boolean z) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("event", 1);
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, str);
        hashMap.put("resource_id", str2);
        hashMap.put("resource_name", str3);
        hashMap.put("resourceType", z ? "graffiti" : "routes");
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("pageId", "Sport_routes_0004");
        iyb iybVar = new iyb();
        iybVar.d(hashMap);
        iybVar.e(hashMap2);
        ixx.d().a(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), "1040090", iybVar, 0);
    }

    public static void d(int i, String str, String str2, boolean z) {
        HashMap hashMap = new HashMap(6);
        hashMap.put("event", 1);
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, "m4");
        hashMap.put("value", Integer.valueOf(i));
        hashMap.put("resource_id", str);
        hashMap.put("resource_name", str2);
        hashMap.put("resourceType", z ? "graffiti" : "routes");
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("pageId", "Sport_routes_0004");
        iyb iybVar = new iyb();
        iybVar.d(hashMap);
        iybVar.e(hashMap2);
        ixx.d().a(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), "1040090", iybVar, 0);
    }

    public static void c(int i, String str, String str2, String str3, boolean z) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, str);
        hashMap.put("resource_id", str2);
        hashMap.put("resource_name", str3);
        hashMap.put("resourceType", z ? "graffiti" : "routes");
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("pageId", "Sport_routes_0004");
        iyb iybVar = new iyb();
        iybVar.d(hashMap);
        iybVar.e(hashMap2);
        ixx.d().a(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), "1040079", iybVar, 0);
    }

    private static String i() {
        HotPathCityInfo hotPathCityInfo;
        if (gzi.d() != null) {
            return gzi.d().getCityName();
        }
        String b = ash.b("RUNNING_PATH_CITY_INFO");
        return (TextUtils.isEmpty(b) || (hotPathCityInfo = (HotPathCityInfo) nrv.b(b, HotPathCityInfo.class)) == null) ? "" : hotPathCityInfo.getCityName();
    }
}
