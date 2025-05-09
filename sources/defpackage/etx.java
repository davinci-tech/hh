package defpackage;

import android.content.SharedPreferences;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class etx {

    /* renamed from: a, reason: collision with root package name */
    private static volatile etx f12303a;
    private String[] e = {h()};
    private String d = h(f());
    private String b = h(j());

    private etx() {
    }

    public static etx b() {
        if (f12303a == null) {
            synchronized (etr.class) {
                if (f12303a == null) {
                    f12303a = new etx();
                }
            }
        }
        return f12303a;
    }

    private long a(String[] strArr) {
        long j = 0;
        for (String str : strArr) {
            j += gic.c((Object) h(str)).longValue();
        }
        return j;
    }

    private String e(String str, String[] strArr) {
        return new StringBuffer().append(str).append("_").append(String.valueOf(a(strArr))).toString();
    }

    private void l(String str) {
        a(str, this.e);
    }

    private void a(String str, String[] strArr) {
        d(str, e(String.valueOf(gib.h(System.currentTimeMillis())), strArr));
    }

    private boolean o(String str) {
        return d(str, this.e);
    }

    private boolean d(String str, String[] strArr) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        String str2 = this.b;
        if ((str2 != null && !str2.equals(accountInfo)) || (this.b == null && accountInfo != null)) {
            this.b = accountInfo;
            d(j(), this.b);
            a();
        }
        String a2 = arx.a();
        String str3 = this.d;
        if ((str3 != null && !str3.equals(a2)) || (this.d == null && a2 != null)) {
            this.d = a2;
            d(f(), this.d);
            a();
        }
        String h = h(str);
        String[] split = h != null ? h.split("_") : null;
        if (split == null || split.length != 2) {
            return true;
        }
        boolean c = c(gic.c((Object) split[0]).longValue(), gic.c((Object) split[1]).longValue(), a(strArr));
        LogUtil.c("Suggestion_DataUpdateHelper", str, " isNeedUpdate ", Boolean.valueOf(c));
        return c;
    }

    private boolean c(long j, long j2, long j3) {
        LogUtil.c("Suggestion_DataUpdateHelper", "lastUpdateTime:", Long.valueOf(j), ",lastVersion:", Long.valueOf(j2), ",limitVersion:", Long.valueOf(j3));
        long h = gib.h(System.currentTimeMillis()) - j;
        return h > 3600 || h < 0 || j2 != j3;
    }

    private void m(String str) {
        d(str, String.valueOf(0));
    }

    private void n(String str) {
        d(str, String.valueOf(gic.c((Object) h(str)).longValue() + 1));
    }

    public void a() {
        n(h());
    }

    public void e(String str) {
        l(f(str));
    }

    private String f(String str) {
        return "getWorkoutFilters_" + str;
    }

    public boolean d(String str, String str2, String str3) {
        return o(a(str, str2, str3));
    }

    public boolean d(String str, String str2, String str3, String str4) {
        return o(b(str, str2, str3, str4));
    }

    public void e(String str, String str2, String str3) {
        l(a(str, str2, str3));
    }

    public void a(String str, String str2, String str3, String str4) {
        l(b(str, str2, str3, str4));
    }

    private String a(String str, String str2, String str3) {
        String obj;
        synchronized (this) {
            HashMap hashMap = new HashMap(4);
            hashMap.put("action", "getWorkoutInfo");
            hashMap.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, str);
            hashMap.put("sex", str2);
            hashMap.put("version", str3);
            obj = hashMap.toString();
        }
        return obj;
    }

    private String b(String str, String str2, String str3, String str4) {
        String obj;
        synchronized (this) {
            HashMap hashMap = new HashMap(5);
            hashMap.put("action", "getWorkoutInfo");
            hashMap.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, str);
            hashMap.put("sex", str2);
            hashMap.put("version", str3);
            hashMap.put("language", str4);
            obj = hashMap.toString();
        }
        return obj;
    }

    public boolean a(String str) {
        return o(i(str));
    }

    public void d(String str) {
        l(i(str));
    }

    public void c(String str) {
        j(i(str));
    }

    private String i(String str) {
        String obj;
        synchronized (this) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("action", "getGetPlanProgress");
            hashMap.put("planId", str);
            obj = hashMap.toString();
        }
        return obj;
    }

    public boolean c(int i, int i2) {
        return o(d(i, i2));
    }

    public void a(int i, int i2) {
        l(d(i, i2));
    }

    private String d(int i, int i2) {
        String obj;
        synchronized (this) {
            HashMap hashMap = new HashMap(3);
            hashMap.put("action", "getFinishedPlans");
            hashMap.put("pageStart", Integer.valueOf(i));
            hashMap.put(IAchieveDBMgr.PARAM_PAGE_SIZE, Integer.valueOf(i2));
            obj = hashMap.toString();
        }
        return obj;
    }

    public boolean d() {
        return o(g());
    }

    public void c() {
        l(g());
    }

    public void e() {
        m(g());
    }

    public boolean b(String str) {
        return o(g(str));
    }

    private String g(String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("action", "getQueryTrainCountByWorkoutId");
        hashMap.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, str);
        return hashMap.toString();
    }

    private void d(String str, String str2) {
        if (arZ_().edit().putString(str, str2).commit()) {
            return;
        }
        LogUtil.b("Suggestion_DataUpdateHelper", "key=", str, "value=", str2);
    }

    private String h(String str) {
        return arZ_().getString(str, "");
    }

    private SharedPreferences arZ_() {
        return BaseApplication.getContext().getApplicationContext().getSharedPreferences("fit_DataUpdateHelper", 0);
    }

    private void j(String str) {
        SharedPreferences.Editor edit = arZ_().edit();
        edit.remove(str);
        if (edit.commit()) {
            return;
        }
        LogUtil.b("Suggestion_DataUpdateHelper", "deleteSharedPreference error key=", str);
    }

    private String h() {
        return "getLimitAllKey";
    }

    private String f() {
        return "getLanguage";
    }

    private String j() {
        return "getHuid";
    }

    private String g() {
        return "getCurrentPlan";
    }
}
