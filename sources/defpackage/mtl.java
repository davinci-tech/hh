package defpackage;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes6.dex */
public class mtl {

    /* renamed from: a, reason: collision with root package name */
    private static final String f15169a = String.valueOf(20006);

    private static void b(String str, String str2) {
        LogUtil.c("OperationCacheUtil", "setStringForSharedPreferences key ", str, " value ", str2);
        jfa.f(f15169a, str, str2);
    }

    private static String c(String str) {
        String d = jfa.d(f15169a, str, "");
        LogUtil.c("OperationCacheUtil", "getStringForSharedPreferences key ", str, " json ", d);
        return d;
    }

    public static boolean a() {
        return jfa.e(f15169a, "FUNCTION_ENTRANCE_INFO_LIST");
    }

    public static void b(List<mte> list) {
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.a("R_OperationCacheUtil", "setFunctionEntranceInfoList list ", list);
        } else {
            b("FUNCTION_ENTRANCE_INFO_LIST", HiJsonUtil.e(list));
        }
    }

    public static String e() {
        return c("FUNCTION_ENTRANCE_INFO_LIST");
    }

    public static List<mte> c() {
        String c = c("FUNCTION_ENTRANCE_INFO_LIST");
        if (TextUtils.isEmpty(c)) {
            LogUtil.a("OperationCacheUtil", "getFunctionEntranceInfoList json ", c);
            return Collections.emptyList();
        }
        return (List) HiJsonUtil.b(c, new TypeToken<List<mte>>() { // from class: mtl.1
        }.getType());
    }

    private static mte a(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("R_OperationCacheUtil", "getFunctionEntranceInfo functionId ", str);
            return null;
        }
        return a(c(), str);
    }

    private static mte a(List<mte> list, String str) {
        if (TextUtils.isEmpty(str) || CollectionUtils.d(list)) {
            LogUtil.a("OperationCacheUtil", "getFunctionEntranceInfo functionId ", str, " list ", list);
            return null;
        }
        for (mte mteVar : list) {
            if (mteVar != null && Objects.equals(str, mteVar.b())) {
                return mteVar;
            }
        }
        return null;
    }

    private static boolean d(mte mteVar) {
        if (mteVar != null) {
            return mteVar.c() == 1;
        }
        ReleaseLogUtil.a("R_OperationCacheUtil", "getFunctionEntranceInfoEnable functionEntranceInfo is null");
        return false;
    }

    public static boolean b(String str) {
        return d(a(str));
    }

    public static boolean e(List<mte> list, String str) {
        return d(a(list, str));
    }
}
