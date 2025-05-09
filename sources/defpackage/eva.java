package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.suggestion.RunCourseRecommendCallback;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitWorkout;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes3.dex */
public class eva {
    private static HashMap<String, Integer> e = new HashMap<>();
    private static TreeMap<Integer, Integer[]> b = new TreeMap<>();

    /* renamed from: a, reason: collision with root package name */
    private static TreeMap<Integer, Integer[]> f12352a = new TreeMap<>();

    private static int d(double d) {
        if (d >= 573.0d) {
            return 4;
        }
        if (d >= 522.0d) {
            return 3;
        }
        return (d < 467.0d && d < 418.0d) ? 1 : 2;
    }

    private static int e(double d) {
        if (d >= 500.0d) {
            return 4;
        }
        if (d >= 450.0d) {
            return 3;
        }
        return (d < 396.0d && d < 354.0d) ? 1 : 2;
    }

    static {
        b.put(0, new Integer[]{26, 32, 38, 44, 51, 57, 63, 69, 0, 24});
        b.put(25, new Integer[]{26, 31, 36, 43, 49, 54, 60, 66, 25, 29});
        b.put(30, new Integer[]{23, 29, 35, 41, 46, 52, 57, 62, 30, 34});
        b.put(35, new Integer[]{23, 28, 33, 39, 44, 49, 55, 61, 35, 39});
        b.put(40, new Integer[]{20, 26, 32, 36, 42, 47, 52, 57, 40, 44});
        b.put(45, new Integer[]{20, 25, 30, 35, 40, 44, 49, 54, 45, 49});
        b.put(50, new Integer[]{20, 24, 28, 33, 37, 42, 47, 52, 50, 54});
        b.put(55, new Integer[]{17, 22, 27, 31, 35, 40, 44, 48, 55, 59});
        b.put(60, new Integer[]{17, 21, 25, 29, 33, 37, 41, 45, 60, 65});
        f12352a.put(0, new Integer[]{22, 27, 32, 37, 42, 47, 52, 57, 0, 24});
        f12352a.put(25, new Integer[]{21, 26, 31, 36, 41, 45, 50, 55, 25, 29});
        f12352a.put(30, new Integer[]{20, 25, 30, 34, 38, 43, 47, 51, 30, 34});
        f12352a.put(35, new Integer[]{20, 24, 28, 32, 36, 41, 45, 49, 35, 39});
        f12352a.put(40, new Integer[]{18, 22, 26, 30, 34, 38, 42, 46, 40, 44});
        f12352a.put(45, new Integer[]{18, 21, 24, 28, 32, 36, 39, 42, 45, 49});
        f12352a.put(50, new Integer[]{14, 19, 23, 26, 30, 33, 37, 41, 50, 54});
        f12352a.put(55, new Integer[]{15, 18, 21, 24, 28, 31, 34, 37, 55, 59});
        f12352a.put(60, new Integer[]{13, 16, 19, 22, 25, 28, 31, 34, 60, 65});
    }

    public static double b(fig figVar) {
        if (figVar == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "runCourseBean == null");
            return 0.0d;
        }
        int i = figVar.b().i();
        double e2 = figVar.e();
        int d = figVar.c().d();
        double e3 = e(figVar.d().e());
        return ((4.0d - Math.abs(i - e2)) * 10.0d) + ((4.0d - Math.abs(d - e3)) * 10.0d) + ((4.0d - Math.abs(figVar.c().c() - e3)) * 10.0d) + ((c((figVar.b().j() / 1000.0d) / 60.0d) - c(figVar.d().a() / 60.0d)) * 0.1d);
    }

    public static void a(int i) {
        b("userPurpose", Integer.toString(i));
    }

    public static void a(int i, int i2, double d) {
        b("userFitnessLevel", Integer.toString(i));
        b("userPurpose", Integer.toString(i2));
        b("userPurposeTime", Double.toString(d));
    }

    public static int b(fij fijVar) {
        if (fijVar == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "userFitnessInfoBean == null");
            return 1;
        }
        if (fijVar.g()) {
            return a(fijVar.f(), e(fijVar.c(), fijVar.a()));
        }
        LogUtil.a("Suggest_RunCourseRecommendUtil", "userFitnessInfoBean.getAverageDistance()= ", Integer.valueOf(fijVar.e()));
        if (fijVar.e() < 2000) {
            return 1;
        }
        if (fijVar.c() == 0) {
            return e(fijVar.b());
        }
        return d(fijVar.b());
    }

    private static double c(double d) {
        return new BigDecimal(d).setScale(1, 1).doubleValue();
    }

    private static int a(int i, Integer[] numArr) {
        if (numArr != null && numArr.length > 1 && numArr.length == 10) {
            if (i >= numArr[5].intValue()) {
                return 4;
            }
            if (i >= numArr[4].intValue()) {
                return 3;
            }
            if (i >= numArr[2].intValue()) {
                return 2;
            }
        }
        return 1;
    }

    public static Integer[] e(int i, int i2) {
        Map.Entry<Integer, Integer[]> floorEntry;
        if (i2 == 0) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "getOxRange maybe on wrong age 0");
        }
        if (i == 0) {
            floorEntry = b.floorEntry(Integer.valueOf(i2));
        } else {
            floorEntry = f12352a.floorEntry(Integer.valueOf(i2));
        }
        if (floorEntry != null) {
            return floorEntry.getValue();
        }
        return new Integer[]{0};
    }

    public static int[] e(fij fijVar, int i, fin finVar) {
        if (fijVar == null || finVar == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "userFitnessInfoBean == null || userPurposeBean == null");
            return new int[]{1, 1};
        }
        if (finVar.d() != 0) {
            return new int[]{finVar.d(), finVar.c()};
        }
        if (fijVar.d() == 0.0d) {
            return new int[]{1, 1};
        }
        if (24.0d < fijVar.d()) {
            return new int[]{2, 2};
        }
        if (fijVar.d() >= 24.0d || fijVar.d() <= 0.0d) {
            return new int[]{1, 1};
        }
        if (i == 1) {
            return new int[]{1, 1};
        }
        if (i == 2) {
            return new int[]{3, 1};
        }
        if (i != 3) {
            return i != 4 ? new int[]{1, 1} : new int[]{4, 3};
        }
        return new int[]{3, 4};
    }

    public static int e(String str) {
        e.put("R001", 1);
        e.put("R002", 1);
        e.put("R012R", 1);
        e.put("R014R", 1);
        e.put("R013R", 2);
        e.put("R016R", 2);
        e.put("R017R", 3);
        e.put("R018R", 3);
        e.put("R019R", 3);
        e.put("R020R", 3);
        e.put("R021R", 4);
        e.put("R022R", 4);
        e.put("R023R", 4);
        e.put("R024R", 4);
        e.put("R008R", 1);
        e.put("R009R", 1);
        e.put("R011R", 3);
        e.put("R010R", 3);
        e.put("R015R", 2);
        if (e.containsKey(str)) {
            return e.get(str).intValue();
        }
        return 0;
    }

    public static void e(String str, long j, FitWorkout fitWorkout) {
        if (str == null || fitWorkout == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "label == null|| fitWorkout == null");
            return;
        }
        String num = Integer.toString(e(fitWorkout.acquireId()));
        LogUtil.a("Suggest_RunCourseRecommendUtil", "courseIdStr= ", num);
        e(str, num, j, null);
        a(str, j, num);
    }

    public static void e(String str, String str2, long j, fin finVar) {
        if (str == null || str2 == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "label == null||courseIdStr==null");
            return;
        }
        fin finVar2 = new fin();
        if (finVar != null) {
            c(str, c(finVar));
            return;
        }
        String h = h(str);
        if (h == null) {
            finVar2.c(j);
            finVar2.e(j);
            finVar2.d(CommonUtil.h(str2));
            finVar2.b(CommonUtil.h(str2));
        } else {
            fin b2 = b(h);
            if (b2 != null && b2.d() != 0) {
                finVar2.c(j);
                finVar2.e(b2.a());
                finVar2.d(CommonUtil.h(str2));
                finVar2.b(b2.c());
            } else {
                finVar2.c(j);
                finVar2.e(j);
                finVar2.d(CommonUtil.h(str2));
                finVar2.b(CommonUtil.h(str2));
            }
        }
        LogUtil.a("Suggest_RunCourseRecommendUtil", "setLastUseOrWatchCourse buildRequestParam", c(finVar2));
        c(str, c(finVar2));
    }

    private static void a(final String str, final long j, final String str2) {
        LogUtil.a("Suggest_RunCourseRecommendUtil", "setLastUseCourseToDb");
        final fin finVar = new fin();
        b(str, new RunCourseRecommendCallback() { // from class: eva.4
            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onSuccess(List<String> list) {
            }

            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onSuccess(String str3) {
                fin b2 = eva.b(str3);
                if (b2 == null || b2.d() == 0) {
                    fin.this.c(j);
                    fin.this.e(j);
                    fin.this.d(CommonUtil.h(str2));
                    fin.this.b(CommonUtil.h(str2));
                } else {
                    fin.this.c(j);
                    fin.this.e(b2.a());
                    fin.this.d(CommonUtil.h(str2));
                    fin.this.b(b2.d());
                }
                eva.e(str, eva.c(fin.this));
            }

            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onFailure(int i, String str3) {
                fin.this.c(j);
                fin.this.e(j);
                fin.this.d(CommonUtil.h(str2));
                fin.this.b(CommonUtil.h(str2));
                eva.e(str, eva.c(fin.this));
            }
        });
    }

    public static fin d(String str) {
        if (str == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "getLastUseCourse label == null ");
            return null;
        }
        LogUtil.a("Suggest_RunCourseRecommendUtil", "getLastUseCourse");
        return b(h(str));
    }

    public static void e(List<String> list, long j) {
        fik fikVar = new fik();
        fikVar.c(list);
        fikVar.c(j);
        String c = c(fikVar);
        LogUtil.a("Suggest_RunCourseRecommendUtil", "setFourRecommendCourse buildRequestParam(tempBean)", c);
        c("save_run_course_recommend", c);
        j(c);
    }

    public static String a() {
        String h = h("save_run_course_recommend");
        LogUtil.a("Suggest_RunCourseRecommendUtil", "getFourRecommendCourse .get ", h);
        return h;
    }

    public static void i(String str) {
        if (str == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "setUserFitness info == null ");
        } else {
            c("run_course_recommend_config", str);
        }
    }

    public static String e() {
        return h("run_course_recommend_config");
    }

    private static void b(String str, String str2) {
        if (str == null || str2 == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "insertLabel, key == null || value == null");
        } else {
            ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).initLabel(str, str2, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        }
    }

    public static void g(final String str) {
        if (str == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "setUserBodyInfoToDb userInfo == null ");
        } else {
            jdx.b(new Runnable() { // from class: eva.1
                @Override // java.lang.Runnable
                public void run() {
                    HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference("run_course_recommend_config", str), true);
                }
            });
        }
    }

    public static void a(final RunCourseRecommendCallback runCourseRecommendCallback) {
        if (runCourseRecommendCallback == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "getUserBodyInfoFromDb callback == null ");
        } else {
            jdx.b(new Runnable() { // from class: eva.5
                @Override // java.lang.Runnable
                public void run() {
                    HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("run_course_recommend_config");
                    if (userPreference == null) {
                        RunCourseRecommendCallback.this.onFailure(0, "getUserBodyInfoFromDb userPreference is null");
                    } else if (TextUtils.isEmpty(userPreference.getValue())) {
                        RunCourseRecommendCallback.this.onFailure(0, "getUserBodyInfoFromDb userinfo is null");
                    } else {
                        RunCourseRecommendCallback.this.onSuccess(userPreference.getValue());
                        LogUtil.a("Suggest_RunCourseRecommendUtil", "getUserBodyInfoFromDb callback.onSuccess .");
                    }
                }
            });
        }
    }

    private static void j(final String str) {
        if (str == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "setCourseToDb userInfo == null ");
        } else {
            jdx.b(new Runnable() { // from class: eva.2
                @Override // java.lang.Runnable
                public void run() {
                    HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference("save_run_course_recommend", str), true);
                    LogUtil.a("Suggest_RunCourseRecommendUtil", "setCourseToDb finish.");
                }
            });
        }
    }

    public static void b(final RunCourseRecommendCallback runCourseRecommendCallback) {
        if (runCourseRecommendCallback == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "getCourseFromDb callback == null ");
        } else {
            jdx.b(new Runnable() { // from class: eva.3
                @Override // java.lang.Runnable
                public void run() {
                    HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("save_run_course_recommend");
                    if (userPreference == null) {
                        RunCourseRecommendCallback.this.onFailure(0, "getCourseFromDb userPreference is null");
                    } else if (TextUtils.isEmpty(userPreference.getValue())) {
                        RunCourseRecommendCallback.this.onFailure(0, "getCourseFromDb userinfo is null");
                    } else {
                        RunCourseRecommendCallback.this.onSuccess(userPreference.getValue());
                        LogUtil.a("Suggest_RunCourseRecommendUtil", "getCourseFromDb callback.onSuccess .");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(final String str, final String str2) {
        jdx.b(new Runnable() { // from class: eva.6
            @Override // java.lang.Runnable
            public void run() {
                HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference(str, str2), true);
                LogUtil.a("Suggest_RunCourseRecommendUtil", "setUseOrWatchCourseToDb finish.");
            }
        });
    }

    public static void b(final String str, final RunCourseRecommendCallback runCourseRecommendCallback) {
        if (str == null || runCourseRecommendCallback == null) {
            LogUtil.h("Suggest_RunCourseRecommendUtil", "getUseOrWatchCourseFromDb key == null || callback == null ");
        } else {
            jdx.b(new Runnable() { // from class: eva.8
                @Override // java.lang.Runnable
                public void run() {
                    HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
                    if (userPreference == null) {
                        runCourseRecommendCallback.onFailure(0, "getUseOrWatchCourseFromDb userPreference is null");
                    } else if (TextUtils.isEmpty(userPreference.getValue())) {
                        runCourseRecommendCallback.onFailure(0, "getUseOrWatchCourseFromDb userinfo is null");
                    } else {
                        runCourseRecommendCallback.onSuccess(userPreference.getValue());
                        LogUtil.a("Suggest_RunCourseRecommendUtil", "getUseOrWatchCourseFromDb callback.onSuccess");
                    }
                }
            });
        }
    }

    public static String c(fij fijVar) {
        if (fijVar == null) {
            return null;
        }
        LogUtil.a("Suggest_RunCourseRecommendUtil", "buildRequestParam UserFitnessInfoBean");
        return new Gson().toJson(fijVar);
    }

    private static String c(fik fikVar) {
        if (fikVar == null) {
            return null;
        }
        LogUtil.a("Suggest_RunCourseRecommendUtil", "buildRequestParam runCourseRecommendBean");
        return new Gson().toJson(fikVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(fin finVar) {
        if (finVar == null) {
            return null;
        }
        LogUtil.a("Suggest_RunCourseRecommendUtil", "buildRequestParam userPurposeBean");
        return new Gson().toJson(finVar);
    }

    public static fij c(String str) {
        if (str == null) {
            LogUtil.b("Suggest_RunCourseRecommendUtil", "UserFitnessInfo data == null");
            return null;
        }
        try {
            return (fij) new Gson().fromJson(str, fij.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("Suggest_RunCourseRecommendUtil", "UserFitnessInfo Json Exception");
            return null;
        }
    }

    public static fik a(String str) {
        if (str == null) {
            LogUtil.b("Suggest_RunCourseRecommendUtil", "Recommend data == null");
            return null;
        }
        try {
            return (fik) new Gson().fromJson(str, fik.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("Suggest_RunCourseRecommendUtil", "Recommend Json Exception");
            return null;
        }
    }

    public static fin b(String str) {
        if (str == null) {
            LogUtil.b("Suggest_RunCourseRecommendUtil", "UserPurpose data == null");
            return null;
        }
        try {
            return (fin) new Gson().fromJson(str, fin.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("Suggest_RunCourseRecommendUtil", "UserPurpose Json Exception");
            return null;
        }
    }

    public static int d(int i) {
        if (i == 0) {
            return 2;
        }
        if (i == 1) {
            return 3;
        }
        if (i == 2) {
            return 4;
        }
        if (i != 6) {
            LogUtil.a("Suggest_RunCourseRecommendUtil", "difficulty is null");
        }
        return 1;
    }

    private static void c(String str, String str2) {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), str, str2, (StorageParams) null);
    }

    private static String h(String str) {
        return SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), str);
    }
}
