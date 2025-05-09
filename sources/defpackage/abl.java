package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.HandlerThread;
import com.huawei.android.hicloud.sync.logic.SyncProcessInterface;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class abl {

    /* renamed from: a, reason: collision with root package name */
    private static final ArrayList<Integer> f164a;

    static {
        ArrayList<Integer> arrayList = new ArrayList<>();
        f164a = arrayList;
        arrayList.add(2100);
        arrayList.add(2101);
        arrayList.add(2102);
        arrayList.add(2103);
        arrayList.add(2104);
        arrayList.add(2105);
        arrayList.add(2106);
        arrayList.add(500);
        arrayList.add(406);
        arrayList.add(404);
        arrayList.add(1002);
        arrayList.add(Integer.valueOf(ExceptionCode.CHECK_FILE_HASH_FAILED));
        arrayList.add(Integer.valueOf(ExceptionCode.CHECK_FILE_SIZE_FAILED));
        arrayList.add(1106);
        arrayList.add(3203);
        arrayList.add(3204);
        arrayList.add(3205);
        arrayList.add(3206);
        arrayList.add(3208);
        arrayList.add(3303);
        arrayList.add(3304);
        arrayList.add(3305);
        arrayList.add(3306);
        arrayList.add(3308);
    }

    public static int a(int i) {
        if (i > 100 || i <= 0) {
            return 100;
        }
        return i;
    }

    private static boolean b(int i) {
        return i == 50001;
    }

    public static JSONArray c(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.has(str)) {
            return jSONObject.getJSONArray(str);
        }
        return null;
    }

    public static boolean c(int i) {
        return i != 0;
    }

    public static boolean c(List<String> list) {
        return list == null || list.size() == 0;
    }

    public static int d(int i) {
        abd.c("SyncUtil", "parseException : " + i);
        if (e(i)) {
            return 100;
        }
        if (f164a.contains(Integer.valueOf(i))) {
            return 120;
        }
        switch (i) {
            case -10:
                return 129;
            case 5:
                return 127;
            case 122:
                return 122;
            case 401:
            case 1102:
            case 2001:
                return 121;
            case 409:
                return 125;
            case 411:
            case 2010:
            case 2012:
                return 126;
            case 1201:
            case 1202:
            case 2015:
                return 123;
            case 3001:
                return 3001;
            default:
                return i;
        }
    }

    public static long e(boolean z) {
        return z ? 100L : 200L;
    }

    public static boolean e(int i) {
        abd.c("SyncUtil", "isExceedLimit errorCode = " + i);
        return g(i) || h(i) || i(i) || f(i) || b(i) || j(i) || n(i);
    }

    private static boolean f(int i) {
        return i < 40100 && i >= 40001;
    }

    private static boolean g(int i) {
        return i < 10100 && i >= 10001;
    }

    private static boolean h(int i) {
        return i < 20100 && i >= 20001;
    }

    private static boolean i(int i) {
        return i < 30100 && i >= 30001;
    }

    private static boolean j(int i) {
        return i == 70001;
    }

    private static boolean n(int i) {
        return i == 90001;
    }

    public static int e(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.has(str)) {
            return jSONObject.getInt(str);
        }
        return 0;
    }

    public static boolean b(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            abd.c("SyncUtil", "isNetWorkConnected context is null");
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && (activeNetworkInfo.getType() == 1 || activeNetworkInfo.getType() == 0 || activeNetworkInfo.getType() == 7)) {
                abd.a("SyncUtil", "isNetWorkConnected ,status = " + activeNetworkInfo.isConnected());
                return activeNetworkInfo.isConnected();
            }
        } catch (Exception e) {
            abd.b("SyncUtil", "isNetWorkConnected error." + e.toString());
        }
        return false;
    }

    public static boolean c(JSONObject jSONObject, String str, boolean z) throws JSONException {
        return jSONObject.has(str) ? jSONObject.getBoolean(str) : z;
    }

    public static boolean d(Context context, String str) {
        abd.c("SyncUtil", "hasPermission: packageName = " + str);
        if (context != null && str != null) {
            if (!abg.d().equals(str)) {
                abd.b("SyncUtil", "hasPermission: PackageName does not meet");
                return false;
            }
            String b = abg.b(context, str);
            if (b.equals(abg.a(context)) || b.equals(abg.e(context))) {
                return true;
            }
            abd.b("SyncUtil", "hasPermission: isHiCloudSign does not meet");
            return false;
        }
        abd.b("SyncUtil", "hasPermission: context or packageName is null");
        return false;
    }

    public static ArrayList<String> d(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (str != null && str.length() > 1) {
            String substring = str.substring(1, str.length() - 1);
            if (substring.length() == 0) {
                return arrayList;
            }
            for (String str2 : substring.split(",")) {
                arrayList.add(str2.trim());
            }
        }
        return arrayList;
    }

    public static String d(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.has(str)) {
            return jSONObject.getString(str);
        }
        return null;
    }

    public static String c() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static ArrayList<String> fu_(Bundle bundle, String str) {
        ArrayList<String> stringArrayList;
        return (bundle == null || (stringArrayList = bundle.getStringArrayList(str)) == null) ? new ArrayList<>(10) : stringArrayList;
    }

    public static void e(Map<String, String> map, List<String> list, List<String> list2) {
        if (map == null || list == null || list2 == null || list.size() <= 0 || list.size() != list2.size()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), list2.get(i));
        }
    }

    public static boolean c(Context context) {
        abd.c("SyncUtil", "checkHiCloudValidate");
        return d(context, abg.d());
    }

    public static String a() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }

    public static void b(Context context, String str, Map<String, Long> map) {
        abe e = abe.e(context);
        if (map == null || map.size() <= 0) {
            return;
        }
        abd.c("SyncUtil", "saveKindsUpperLimits to sp");
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            String key = entry.getKey();
            long longValue = entry.getValue().longValue();
            if (-1 != longValue) {
                e.e(str, key, longValue);
                e.a(str, key, System.currentTimeMillis());
            }
        }
    }

    public static boolean d(Context context) {
        boolean h = aaw.h(context);
        boolean z = aal.a() >= 107;
        boolean z2 = aal.b() >= 1;
        abd.c("SyncUtil", "isSupportRequireSyncModel: appFeatureState = " + h + ", sdkVersionCode = " + z + ", dataTypeSyncModel = " + z2);
        return h && z && z2;
    }

    public static boolean d(Context context, String str, List<String> list, Map<String, Long> map) {
        boolean z = true;
        for (String str2 : list) {
            boolean z2 = System.currentTimeMillis() - abe.e(context).e(str, str2) < 86400000;
            boolean z3 = map != null && map.containsKey(str2);
            if (!z2 || !z3) {
                abd.d("SyncUtil", "getNewExceedLimitNum: need server, dataType = " + str2 + ", " + z2 + "|" + z3);
                z = false;
            }
        }
        return z;
    }

    public static void fv_(HandlerThread handlerThread) {
        if (handlerThread != null) {
            handlerThread.quitSafely();
            abd.c("SyncUtil", "quit thread");
        }
    }

    public static void c(SyncProcessInterface syncProcessInterface) {
        if (syncProcessInterface != null) {
            syncProcessInterface.onSyncEnd();
        } else {
            abd.b("SyncUtil", "processEndSync error: callback is null");
        }
    }
}
