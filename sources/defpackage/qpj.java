package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class qpj {
    public static SparseArray<Object> dGX_(int[] iArr, String str) {
        SparseArray<Object> sparseArray = new SparseArray<>();
        Context context = BaseApplication.getContext();
        String b = SharedPreferenceManager.b(context, "day_temperature_cache", "CACHE_KEY_TIME");
        LogUtil.a("TemperatureDataCache", "getCacheData timeStr: ", str, " cacheTime: ", b);
        if (TextUtils.equals(b, str)) {
            for (int i : iArr) {
                dGY_(context, i, sparseArray);
            }
        }
        return sparseArray;
    }

    private static void dGY_(Context context, int i, SparseArray<Object> sparseArray) {
        String b = SharedPreferenceManager.b(context, "day_temperature_cache", c(i));
        ArrayList arrayList = new ArrayList();
        jdr.d(b, qpj.class.getClassLoader(), null, arrayList);
        sparseArray.put(i, arrayList);
    }

    private static String c(int i) {
        return "CACHE_KEY_" + i;
    }

    public static void dHa_(int[] iArr, String str, SparseArray<Object> sparseArray) {
        LogUtil.a("TemperatureDataCache", "setCacheData, time: ", str);
        Context context = BaseApplication.getContext();
        for (int i : iArr) {
            Object obj = sparseArray.get(i);
            b(context, obj instanceof List ? (List) obj : new ArrayList(), i);
        }
        SharedPreferenceManager.e(context, "day_temperature_cache", "CACHE_KEY_TIME", str, (StorageParams) null);
    }

    private static void b(Context context, List<HiHealthData> list, int i) {
        SharedPreferenceManager.e(context, "day_temperature_cache", c(i), jdr.b(list, null), (StorageParams) null);
    }

    public static boolean dGZ_(int[] iArr, SparseArray<Object> sparseArray, SparseArray<Object> sparseArray2) {
        if (sparseArray.size() <= 0) {
            LogUtil.a("TemperatureDataCache", "cacheData size <= 0");
            return true;
        }
        for (int i : iArr) {
            if (e(sparseArray.get(i) instanceof List ? (List) sparseArray.get(i) : new ArrayList(), sparseArray2.get(i) instanceof List ? (List) sparseArray2.get(i) : new ArrayList())) {
                LogUtil.a("TemperatureDataCache", "list diff type: ", Integer.valueOf(i));
                return true;
            }
        }
        return false;
    }

    private static boolean e(List list, List list2) {
        return !TextUtils.equals(jdr.b(list, null), jdr.b(list2, null));
    }

    public static void e(int[] iArr) {
        LogUtil.a("TemperatureDataCache", "clearCache");
        Context context = BaseApplication.getContext();
        for (int i : iArr) {
            SharedPreferenceManager.e(context, "day_temperature_cache", c(i), (String) null, (StorageParams) null);
        }
        SharedPreferenceManager.e(context, "day_temperature_cache", "CACHE_KEY_TIME", (String) null, (StorageParams) null);
    }
}
