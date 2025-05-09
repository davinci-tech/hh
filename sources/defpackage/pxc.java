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
public class pxc {
    private pxc() {
    }

    public static void duL_(String str, SparseArray<List<HiHealthData>> sparseArray) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SCUI_StepDayDataCache", "setCacheData time is null or empty");
            return;
        }
        if (sparseArray == null || sparseArray.size() < 1) {
            LogUtil.h("SCUI_StepDayDataCache", "setCacheData dataArray is null or size less than two");
            return;
        }
        LogUtil.a("SCUI_StepDayDataCache", "setCacheData time: ", str);
        Context e = BaseApplication.e();
        String e2 = e(e);
        List<HiHealthData> list = sparseArray.get(0);
        List<HiHealthData> list2 = sparseArray.get(1);
        c(e, list, "cache_key_data" + e2);
        c(e, list2, "cache_key_sum" + e2);
        SharedPreferenceManager.e(e, "step_day_data_cache", "cache_key_time" + e2, str, (StorageParams) null);
    }

    public static SparseArray<List<HiHealthData>> duI_(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SCUI_StepDayDataCache", "getCacheData time is null or empty");
            return null;
        }
        LogUtil.a("SCUI_StepDayDataCache", "getCacheData time: ", str);
        SparseArray<List<HiHealthData>> sparseArray = new SparseArray<>();
        Context e = BaseApplication.e();
        String e2 = e(e);
        if (str.equals(SharedPreferenceManager.b(e, "step_day_data_cache", "cache_key_time" + e2))) {
            duJ_(e, "cache_key_data" + e2, 0, sparseArray);
            duJ_(e, "cache_key_sum" + e2, 1, sparseArray);
        }
        return sparseArray;
    }

    public static boolean duK_(SparseArray<List<HiHealthData>> sparseArray, SparseArray<List<HiHealthData>> sparseArray2) {
        if (sparseArray == null || sparseArray.size() < 1) {
            LogUtil.h("SCUI_StepDayDataCache", "isSame cacheData is null or size less than two");
            return false;
        }
        if (sparseArray2 != null && sparseArray2.size() >= 1) {
            return d(sparseArray.get(0), sparseArray2.get(0)) && d(sparseArray.get(1), sparseArray2.get(1));
        }
        LogUtil.h("SCUI_StepDayDataCache", "isSame queryData is null or size less than two");
        return false;
    }

    private static void c(Context context, List<HiHealthData> list, String str) {
        SharedPreferenceManager.e(context, "step_day_data_cache", str, jdr.b(list, null), (StorageParams) null);
    }

    private static void duJ_(Context context, String str, int i, SparseArray<List<HiHealthData>> sparseArray) {
        String b = SharedPreferenceManager.b(context, "step_day_data_cache", str);
        ArrayList arrayList = new ArrayList();
        jdr.d(b, pxc.class.getClassLoader(), null, arrayList);
        sparseArray.put(i, arrayList);
    }

    private static boolean d(List<HiHealthData> list, List<HiHealthData> list2) {
        return jdr.b(list, null).equals(jdr.b(list2, null));
    }

    private static String e(Context context) {
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        return !TextUtils.isEmpty(accountInfo) ? SecurityUtils.d(accountInfo) : "";
    }
}
