package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.StorageParams;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jgq {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Integer> f13827a = new HashMap(16);
    private static volatile jgq e;

    private jgq() {
        a();
    }

    public static jgq d() {
        if (e == null) {
            synchronized (jgq.class) {
                if (e == null) {
                    e = new jgq();
                }
            }
        }
        return e;
    }

    public void a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WearPlaceInfo", "configWearPlaceCode id is empty");
        } else {
            f13827a.put(str, Integer.valueOf(i));
            a(f13827a);
        }
    }

    public int d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WearPlaceInfo", "fetchWearPlaceCode id is empty");
            return -1;
        }
        Integer num = f13827a.get(str);
        if (num == null) {
            LogUtil.h("WearPlaceInfo", "wearPlaceCode is null");
            return -1;
        }
        return num.intValue();
    }

    public String d(int i) {
        BaseApplication.getContext().getResources().getString(R$string.IDS_bolt_wear_unrecognized);
        if (i == 8) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_bolt_wear_waist);
        }
        if (i == 13) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_bolt_wear_foot);
        }
        if (i == 53) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_bolt_wear_bike_spoke);
        }
        if (i == 0) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_bolt_wear_not_installed);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_bolt_wear_unrecognized);
    }

    private static void a() {
        f13827a = c();
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.Map<java.lang.String, java.lang.Integer> c() {
        /*
            java.lang.String r0 = "WearPlaceInfo"
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            health.compact.a.KeyValDbManager r1 = health.compact.a.KeyValDbManager.b(r1)
            health.compact.a.StorageParams r2 = new health.compact.a.StorageParams
            r3 = 1
            r2.<init>(r3)
            java.lang.String r3 = "cached_wear_place_info"
            java.lang.String r1 = r1.d(r3, r2)
            com.google.gson.Gson r2 = new com.google.gson.Gson     // Catch: java.lang.NumberFormatException -> L2b com.google.gson.JsonSyntaxException -> L36
            r2.<init>()     // Catch: java.lang.NumberFormatException -> L2b com.google.gson.JsonSyntaxException -> L36
            jgq$5 r3 = new jgq$5     // Catch: java.lang.NumberFormatException -> L2b com.google.gson.JsonSyntaxException -> L36
            r3.<init>()     // Catch: java.lang.NumberFormatException -> L2b com.google.gson.JsonSyntaxException -> L36
            java.lang.reflect.Type r3 = r3.getType()     // Catch: java.lang.NumberFormatException -> L2b com.google.gson.JsonSyntaxException -> L36
            java.lang.Object r1 = r2.fromJson(r1, r3)     // Catch: java.lang.NumberFormatException -> L2b com.google.gson.JsonSyntaxException -> L36
            java.util.Map r1 = (java.util.Map) r1     // Catch: java.lang.NumberFormatException -> L2b com.google.gson.JsonSyntaxException -> L36
            goto L41
        L2b:
            java.lang.String r1 = "savedMessage NumberFormatException"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
            goto L40
        L36:
            java.lang.String r1 = "savedMessage jsonSyntaxException "
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
        L40:
            r1 = 0
        L41:
            if (r1 != 0) goto L4a
            java.util.HashMap r1 = new java.util.HashMap
            r2 = 16
            r1.<init>(r2)
        L4a:
            int r2 = r1.size()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r3 = "getWearPlaceInfoFromDb: cachedMap.size = "
            java.lang.Object[] r2 = new java.lang.Object[]{r3, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jgq.c():java.util.Map");
    }

    private void a(Map<String, Integer> map) {
        LogUtil.a("WearPlaceInfo", "setWearPlaceToDb: wearPlaceMap.size = ", Integer.valueOf(map.size()));
        KeyValDbManager.b(BaseApplication.getContext()).a("cached_wear_place_info", new Gson().toJson(map), new StorageParams(1));
    }
}
