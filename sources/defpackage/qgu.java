package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class qgu {
    private qgu() {
    }

    public static void dDs_(String str, SparseArray<List<HiHealthData>> sparseArray) {
        LogUtil.a("BloodPressureDataCache", "setCacheData time: ", str);
        Context e = BaseApplication.e();
        b(e, sparseArray.get(10002), "CACHE_KEY_CORE");
        SharedPreferenceManager.e(e, "day_bloodpressure_cache", "CACHE_KEY_TIME", str, (StorageParams) null);
    }

    private static void b(Context context, List<HiHealthData> list, String str) {
        SharedPreferenceManager.e(context, "day_bloodpressure_cache", str, jdr.b(list, null), (StorageParams) null);
    }

    public static SparseArray<List<HiHealthData>> dDp_(String str) {
        LogUtil.a("BloodPressureDataCache", "getCacheData time: ", str);
        SparseArray<List<HiHealthData>> sparseArray = new SparseArray<>();
        Context e = BaseApplication.e();
        if (str.equals(SharedPreferenceManager.b(e, "day_bloodpressure_cache", "CACHE_KEY_TIME"))) {
            dDq_(e, "CACHE_KEY_CORE", 10002, sparseArray);
        }
        return sparseArray;
    }

    private static void dDq_(Context context, String str, int i, SparseArray<List<HiHealthData>> sparseArray) {
        String b = SharedPreferenceManager.b(context, "day_bloodpressure_cache", str);
        ArrayList arrayList = new ArrayList();
        jdr.d(b, qgu.class.getClassLoader(), null, arrayList);
        sparseArray.put(i, arrayList);
    }

    public static boolean dDr_(SparseArray<List<HiHealthData>> sparseArray, SparseArray<List<HiHealthData>> sparseArray2) {
        return sparseArray.size() == 0 || c(sparseArray.get(10002), sparseArray2.get(10002));
    }

    private static boolean c(List<HiHealthData> list, List<HiHealthData> list2) {
        return !jdr.b(list, null).equals(jdr.b(list2, null));
    }
}
