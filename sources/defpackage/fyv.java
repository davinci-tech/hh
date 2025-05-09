package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.weight.WeightApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginoperation.util.DietKakaUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class fyv {
    public static boolean h(int i) {
        return i == 40 || i == 50 || i == 60;
    }

    public static void e(Context context, fiu fiuVar, boolean z, String str, IntPlan intPlan) {
        if (fiuVar == null || intPlan == null || ((OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class)) == null) {
            return;
        }
        e(context, new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeArg("foodId", fiuVar.a()).addCustomizeArg("mealType", String.valueOf(fiuVar.h())).addCustomizeArg("isRecorded", String.valueOf(z)).addCustomizeArg("count", String.valueOf(fiuVar.o())).addCustomizeArg("unit", fiuVar.l()).addCustomizeArg("time", str).addCustomizeArg("planId", intPlan.getPlanId()).addGlobalBiParam(WebViewHelp.BI_KEY_PULL_FROM, "weightControlPlan").setImmerse().showStatusBar().addPath("#/food_detail").setStatusBarTextBlack(true).setForceDarkMode(1));
    }

    public static void e(Context context, fiu fiuVar, String str, IntPlan intPlan, String str2) {
        if (fiuVar == null || intPlan == null) {
            return;
        }
        LogUtil.c("Util", "nmsDebug replaceFood", fiuVar.a());
        if (((OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class)) != null) {
            e(context, new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeArg("foodId", fiuVar.a()).addCustomizeArg("mealType", String.valueOf(fiuVar.h())).addCustomizeArg("count", String.valueOf(fiuVar.o())).addCustomizeArg("unit", fiuVar.l()).addCustomizeArg("time", str).addCustomizeArg("heat", String.valueOf(fiuVar.g())).addCustomizeArg("planId", intPlan.getPlanId()).addCustomizeArg("energy", str2).setImmerse().showStatusBar().addPath("#/substitute_food").setStatusBarTextBlack(true).setForceDarkMode(1));
        }
    }

    public static void e(Context context, H5ProLaunchOption.Builder builder) {
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi != null) {
            H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
        } else {
            LogUtil.h("FoodUtil", "weightApi is null.");
        }
        builder.addCustomizeJsModule("DietKakaUtil", DietKakaUtil.class);
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.ai-weight", builder);
    }

    public static List<quk> b(List<fiu> list) {
        LogUtil.a("FoodUtil", "food2FoodDescriptor foods is " + list.toString());
        ArrayList arrayList = new ArrayList();
        String q = jdl.q(System.currentTimeMillis());
        for (fiu fiuVar : list) {
            quk qukVar = new quk(fiuVar.a(), new Date().getTime());
            qukVar.e(fiuVar.l());
            qukVar.a(fiuVar.b());
            qukVar.c(fiuVar.c());
            qukVar.e(fiuVar.e());
            qukVar.d(fiuVar.o());
            qukVar.a(fiuVar.f());
            qukVar.h(fiuVar.j());
            qukVar.b(fiuVar.g());
            qukVar.b(q);
            arrayList.add(qukVar);
        }
        d(arrayList);
        LogUtil.a("FoodUtil", "food2FoodDescriptor fds is " + arrayList.toString());
        return arrayList;
    }

    private static void d(final List<quk> list) {
        HashSet hashSet = new HashSet();
        final HashMap hashMap = new HashMap();
        for (quk qukVar : list) {
            hashSet.add(qukVar.a());
            hashMap.put(qukVar.a(), qukVar);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        fiw.d().batchGetFood(hashSet, new DataCallback() { // from class: fyv.3
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                LogUtil.b("FoodUtil", "replenishSodium batch get food error " + str + " " + i);
                countDownLatch.countDown();
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                JSONArray jSONArray;
                String str;
                int i;
                int i2;
                String str2 = "unit";
                LogUtil.a("FoodUtil", "replenishSodium batch get food success " + jSONObject);
                if (jSONObject != null && jSONObject.has("foodList")) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("foodList");
                    int i3 = 0;
                    while (i3 < optJSONArray.length()) {
                        try {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i3);
                            String string = jSONObject2.getString("foodId");
                            String string2 = jSONObject2.getString(str2);
                            double d = jSONObject2.getDouble("count");
                            double d2 = jSONObject2.getDouble("sodium");
                            JSONArray jSONArray2 = jSONObject2.getJSONArray("extensionUnits");
                            HashMap hashMap2 = new HashMap();
                            int i4 = 0;
                            while (i4 < jSONArray2.length()) {
                                try {
                                    jSONArray = optJSONArray;
                                    try {
                                        str = str2;
                                    } catch (JSONException unused) {
                                        str = str2;
                                        i = i3;
                                        i2 = 1;
                                        Object[] objArr = new Object[i2];
                                        objArr[0] = "replenishSodium parse food json error";
                                        LogUtil.b("FoodUtil", objArr);
                                        i3 = i + 1;
                                        optJSONArray = jSONArray;
                                        str2 = str;
                                    }
                                } catch (JSONException unused2) {
                                    jSONArray = optJSONArray;
                                }
                                try {
                                    hashMap2.put(jSONArray2.getJSONObject(i4).getString(str2), Integer.valueOf(jSONArray2.getJSONObject(i4).getInt("unitValue")));
                                    i4++;
                                    optJSONArray = jSONArray;
                                    str2 = str;
                                } catch (JSONException unused3) {
                                    i = i3;
                                    i2 = 1;
                                    Object[] objArr2 = new Object[i2];
                                    objArr2[0] = "replenishSodium parse food json error";
                                    LogUtil.b("FoodUtil", objArr2);
                                    i3 = i + 1;
                                    optJSONArray = jSONArray;
                                    str2 = str;
                                }
                            }
                            jSONArray = optJSONArray;
                            str = str2;
                            hashMap2.put(BaseApplication.getContext().getString(R.string._2130849762_res_0x7f022fe2), 1);
                            int intValue = hashMap2.containsKey(string2) ? ((Integer) hashMap2.get(string2)).intValue() : 0;
                            if (intValue == 0) {
                                try {
                                    Object[] objArr3 = new Object[1];
                                    objArr3[0] = "replenishSodium food's unit not in unit2Value, unitValue=" + hashMap2 + ", food is " + jSONObject2;
                                    LogUtil.b("FoodUtil", objArr3);
                                    i = i3;
                                } catch (JSONException unused4) {
                                    i2 = 1;
                                    i = i3;
                                    Object[] objArr22 = new Object[i2];
                                    objArr22[0] = "replenishSodium parse food json error";
                                    LogUtil.b("FoodUtil", objArr22);
                                    i3 = i + 1;
                                    optJSONArray = jSONArray;
                                    str2 = str;
                                }
                            } else {
                                i = i3;
                                double d3 = d2 / (d * intValue);
                                try {
                                    quk qukVar2 = (quk) hashMap.get(string);
                                    if (qukVar2 == null) {
                                        try {
                                            Object[] objArr4 = new Object[1];
                                            objArr4[0] = "replenishSodium fd is not exist, food is " + jSONObject2;
                                            LogUtil.b("FoodUtil", objArr4);
                                        } catch (JSONException unused5) {
                                            i2 = 1;
                                            Object[] objArr222 = new Object[i2];
                                            objArr222[0] = "replenishSodium parse food json error";
                                            LogUtil.b("FoodUtil", objArr222);
                                            i3 = i + 1;
                                            optJSONArray = jSONArray;
                                            str2 = str;
                                        }
                                    } else {
                                        String n = qukVar2.n();
                                        if ((hashMap2.containsKey(n) ? ((Integer) hashMap2.get(n)).intValue() : 0) == 0) {
                                            try {
                                                Object[] objArr5 = new Object[1];
                                                objArr5[0] = "replenishSodium fd's unit not in unit2Value, unitValue is " + hashMap2 + ", food is " + jSONObject2 + ", fd is " + qukVar2;
                                                LogUtil.b("FoodUtil", objArr5);
                                            } catch (JSONException unused6) {
                                                i2 = 1;
                                                Object[] objArr2222 = new Object[i2];
                                                objArr2222[0] = "replenishSodium parse food json error";
                                                LogUtil.b("FoodUtil", objArr2222);
                                                i3 = i + 1;
                                                optJSONArray = jSONArray;
                                                str2 = str;
                                            }
                                        } else {
                                            qukVar2.f((float) (r5 * qukVar2.b() * d3));
                                        }
                                    }
                                } catch (JSONException unused7) {
                                    i2 = 1;
                                    Object[] objArr22222 = new Object[i2];
                                    objArr22222[0] = "replenishSodium parse food json error";
                                    LogUtil.b("FoodUtil", objArr22222);
                                    i3 = i + 1;
                                    optJSONArray = jSONArray;
                                    str2 = str;
                                }
                            }
                        } catch (JSONException unused8) {
                            jSONArray = optJSONArray;
                            str = str2;
                        }
                        i3 = i + 1;
                        optJSONArray = jSONArray;
                        str2 = str;
                    }
                }
                LogUtil.a("FoodUtil", "replenishSodium fds is " + list);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("FoodUtil", "thread interrupted");
        }
    }

    public static boolean e(quh quhVar, long j) {
        if (quhVar != null) {
            return quhVar.c() == DateFormatUtil.b(j);
        }
        LogUtil.h("FoodUtil", "isSameDay record is null");
        return false;
    }

    public static int d(int i) {
        if (i == 0) {
            return 10;
        }
        if (i == 1) {
            return 20;
        }
        if (i == 2) {
            return 30;
        }
        if (i == 3) {
            return 40;
        }
        if (i == 4) {
            return 50;
        }
        if (i == 5) {
            return 60;
        }
        LogUtil.h("FoodUtil", "getMealType position ", Integer.valueOf(i));
        return 0;
    }

    public static int a(int i) {
        if (i == 10 || i == 20 || i == 30) {
            return i;
        }
        if (i == 40) {
            return 10;
        }
        if (i == 50) {
            return 20;
        }
        if (i == 60) {
            return 30;
        }
        LogUtil.h("FoodUtil", "getRealMealType mealType ", Integer.valueOf(i));
        return 0;
    }

    public static String e(int i) {
        if (i == 10) {
            return " 08:00:00";
        }
        if (i == 20) {
            return " 12:00:00";
        }
        if (i == 30) {
            return " 18:00:00";
        }
        if (i == 40) {
            return " 08:00:00";
        }
        if (i == 50) {
            return " 12:00:00";
        }
        if (i == 60) {
            return " 18:00:00";
        }
        LogUtil.h("FoodUtil", "getMealTime mealType ", Integer.valueOf(i));
        return "";
    }

    public static int c(int i) {
        if (i == 10) {
            return 1;
        }
        if (i == 20) {
            return 2;
        }
        if (i == 30) {
            return 3;
        }
        if (i == 40) {
            return 1;
        }
        if (i == 50) {
            return 2;
        }
        if (i == 60) {
            return 3;
        }
        LogUtil.h("FoodUtil", "getIndex mealType ", Integer.valueOf(i));
        return 0;
    }

    public static int b(int i) {
        if (i != 10) {
            if (i == 20) {
                return 1;
            }
            if (i == 30) {
                return 2;
            }
            if (i != 40) {
                if (i == 50) {
                    return 1;
                }
                if (i == 60) {
                    return 2;
                }
                LogUtil.h("FoodUtil", "getIndex mealType ", Integer.valueOf(i));
                return 0;
            }
        }
        return 0;
    }

    public static List<String> e(quh quhVar, int i) {
        if (quhVar == null) {
            LogUtil.h("FoodUtil", "getFoodIdList record is null");
            return Collections.emptyList();
        }
        List<qul> a2 = quhVar.a();
        if (koq.b(a2)) {
            LogUtil.h("FoodUtil", "getFoodIdList mealList ", a2);
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (qul qulVar : a2) {
            if (qulVar != null && qulVar.h() == i) {
                List<quk> c = qulVar.c();
                if (!koq.b(c)) {
                    for (quk qukVar : c) {
                        if (qukVar != null) {
                            String a3 = qukVar.a();
                            if (!TextUtils.isEmpty(a3)) {
                                arrayList.add(a3);
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static boolean c(quh quhVar, long j) {
        if (quhVar == null || !e(quhVar, j)) {
            LogUtil.h("FoodUtil", "isSupportDietSetting record ", quhVar);
            return false;
        }
        List<qul> a2 = quhVar.a();
        if (koq.b(a2)) {
            LogUtil.h("FoodUtil", "isSupportDietSetting list ", a2);
            return false;
        }
        float f = 0.0f;
        for (qul qulVar : a2) {
            if (qulVar != null) {
                f += qulVar.b();
            }
        }
        return Float.compare(f, 0.0f) == 1;
    }

    public static boolean b(List<List<fiu>> list, quh quhVar, long j) {
        if (!jdl.ac(j)) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > jdl.e(currentTimeMillis, 21, 0)) {
            return true;
        }
        if (koq.b(list, 2) || quhVar == null || !e(quhVar, j)) {
            LogUtil.h("FoodUtil", "isShowTomorrowRecommendFood list ", list, " record ", quhVar, " timeMillis ", Long.valueOf(j));
            return false;
        }
        if (b(list.get(0), e(quhVar, 10)) && b(list.get(1), e(quhVar, 20))) {
            return b(list.get(2), e(quhVar, 30));
        }
        return false;
    }

    private static boolean b(List<fiu> list, List<String> list2) {
        for (fiu fiuVar : list) {
            if (fiuVar != null && !list2.contains(fiuVar.a())) {
                return false;
            }
        }
        return true;
    }

    public static int c(List<List<fiu>> list, quh quhVar, long j) {
        if (koq.b(list, 2) || quhVar == null || !e(quhVar, j)) {
            LogUtil.h("FoodUtil", "getDefaultPositionOffset list ", list, " record ", quhVar, " timeMillis ", Long.valueOf(j));
            return 0;
        }
        if (!jdl.ac(j)) {
            return 0;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long e = jdl.e(currentTimeMillis, 10, 0);
        boolean b = b(list.get(0), e(quhVar, 10));
        if (currentTimeMillis <= e && !b) {
            return 0;
        }
        long e2 = jdl.e(currentTimeMillis, 15, 0);
        boolean b2 = b(list.get(1), e(quhVar, 20));
        if (currentTimeMillis <= e2 && !b2) {
            return 1;
        }
        long e3 = jdl.e(currentTimeMillis, 21, 0);
        boolean b3 = b(list.get(2), e(quhVar, 30));
        if (currentTimeMillis <= e3 && !b3) {
            return 2;
        }
        if (b && b2 && b3) {
            return 3;
        }
        if (b) {
            return !b2 ? 1 : 2;
        }
        return 0;
    }

    public static fjb c(fiu fiuVar) {
        if (fiuVar == null) {
            return null;
        }
        List<fjb> d = fiuVar.d();
        if (koq.b(d)) {
            return null;
        }
        return d.get(0);
    }

    public static double d(fjb fjbVar, int i) {
        if (i <= 0) {
            return 0.0d;
        }
        if (fjbVar == null) {
            ReleaseLogUtil.a("R_FoodUtil", "convertFoodValue foodExtensionUnits is null value ", Integer.valueOf(i));
            return 0.0d;
        }
        int a2 = fjbVar.a();
        if (a2 <= 0) {
            ReleaseLogUtil.a("R_FoodUtil", "convertFoodValue unitValue ", Integer.valueOf(a2), " value ", Integer.valueOf(i), " foodExtensionUnits ", fjbVar);
            return 0.0d;
        }
        int i2 = i / a2;
        double a3 = UnitUtil.a(i / a2, 2);
        if (a3 > 0.0d && a3 <= 0.5d) {
            return 0.5d;
        }
        if (a3 > 0.5d && a3 <= 1.0d) {
            return 1.0d;
        }
        double d = i2;
        return (a3 < d || a3 > d + 0.25d) ? (a3 <= 0.25d + d || a3 > 0.75d + d) ? i2 + 1 : d + 0.5d : d;
    }

    public static void a(final List<quh> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_FoodUtil", "setDietRecordWeekListCache dietRecordList ", list);
            return;
        }
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: fyt
                @Override // java.lang.Runnable
                public final void run() {
                    fyv.a((List<quh>) list);
                }
            });
            return;
        }
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        if (list.size() > 1) {
            for (quh quhVar : list) {
                if (quhVar != null && jdl.f(DateFormatUtil.c(quhVar.c()), currentTimeMillis)) {
                    arrayList.add(quhVar);
                }
            }
            gsd.e(arrayList);
            return;
        }
        quh quhVar2 = list.get(0);
        if (quhVar2 == null) {
            ReleaseLogUtil.a("R_FoodUtil", "setDietRecordWeekListCache dayDietRecord is null");
            return;
        }
        long c = DateFormatUtil.c(quhVar2.c());
        if (jdl.f(c, currentTimeMillis)) {
            arrayList.add(quhVar2);
        }
        List<quh> d = gsd.d();
        if (koq.b(d)) {
            gsd.e(arrayList);
            return;
        }
        for (quh quhVar3 : d) {
            if (quhVar3 != null) {
                long c2 = DateFormatUtil.c(quhVar3.c());
                if (!jdl.d(c2, c) && jdl.f(c2, currentTimeMillis)) {
                    arrayList.add(quhVar3);
                }
            }
        }
        gsd.e(arrayList);
    }
}
