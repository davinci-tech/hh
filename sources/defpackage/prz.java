package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class prz {
    private prz() {
    }

    public static void dsE_(String str, SparseArray<List<HiHealthData>> sparseArray) {
        LogUtil.a("DayDataCache", "setCacheData time: ", str);
        Context e = BaseApplication.e();
        String b = b(e);
        e(e, sparseArray.get(2002), "CACHE_KEY_DYNAMIC" + b);
        e(e, sparseArray.get(2018), "CACHE_KEY_REST" + b);
        e(e, sparseArray.get(2105), "CACHE_KEY_NEW_REST" + b);
        e(e, sparseArray.get(2101), "CACHE_KEY_UP" + b);
        e(e, sparseArray.get(2102), "CACHE_KEY_LOW" + b);
        SharedPreferenceManager.e(e, "day_heart_rate_cache", "CACHE_KEY_TIME" + b, str, (StorageParams) null);
    }

    private static void e(Context context, List<HiHealthData> list, String str) {
        SharedPreferenceManager.e(context, "day_heart_rate_cache", str, jdr.b(list, null), (StorageParams) null);
    }

    public static SparseArray<List<HiHealthData>> dsB_(String str) {
        LogUtil.a("DayDataCache", "getCacheData time: ", str);
        SparseArray<List<HiHealthData>> sparseArray = new SparseArray<>();
        Context e = BaseApplication.e();
        String b = b(e);
        if (str.equals(SharedPreferenceManager.b(e, "day_heart_rate_cache", "CACHE_KEY_TIME" + b))) {
            dsC_(e, "CACHE_KEY_DYNAMIC" + b, 2002, sparseArray);
            dsC_(e, "CACHE_KEY_REST" + b, 2018, sparseArray);
            dsC_(e, "CACHE_KEY_NEW_REST" + b, 2105, sparseArray);
            dsC_(e, "CACHE_KEY_UP" + b, 2101, sparseArray);
            dsC_(e, "CACHE_KEY_LOW" + b, 2102, sparseArray);
        }
        return sparseArray;
    }

    private static void dsC_(Context context, String str, int i, SparseArray<List<HiHealthData>> sparseArray) {
        String b = SharedPreferenceManager.b(context, "day_heart_rate_cache", str);
        ArrayList arrayList = new ArrayList();
        jdr.d(b, prz.class.getClassLoader(), null, arrayList);
        sparseArray.put(i, arrayList);
    }

    public static boolean dsD_(SparseArray<List<HiHealthData>> sparseArray, SparseArray<List<HiHealthData>> sparseArray2) {
        return sparseArray.size() == 0 || b(sparseArray.get(2002), sparseArray2.get(2002)) || b(sparseArray.get(2018), sparseArray2.get(2018)) || b(sparseArray.get(2105), sparseArray2.get(2105)) || b(sparseArray.get(2101), sparseArray2.get(2101)) || b(sparseArray.get(2102), sparseArray2.get(2102));
    }

    private static boolean b(List<HiHealthData> list, List<HiHealthData> list2) {
        return !jdr.b(list, null).equals(jdr.b(list2, null));
    }

    private static String b(Context context) {
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        return !TextUtils.isEmpty(accountInfo) ? SecurityUtils.d(accountInfo) : "";
    }
}
