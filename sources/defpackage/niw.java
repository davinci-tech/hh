package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class niw {
    private static final int[] d = {DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value()};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f15314a = {"calorieUserValue", "calorieGoalValue"};
    private static final int[] e = {40003};
    private static final String[] c = {"sport_calorie_sum"};

    public static void b(final List<String> list, final int i, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("SCUI_RecommendGoalUtil", "keys is ", list, " recommendType is", Integer.valueOf(i));
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "callback is null in getRecommendGoal");
            return;
        }
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: niz
                @Override // java.lang.Runnable
                public final void run() {
                    niw.b((List<String>) list, i, iBaseResponseCallback);
                }
            });
            return;
        }
        if (koq.b(list)) {
            ReleaseLogUtil.a("SCUI_RecommendGoalUtil", "key is empty.");
            iBaseResponseCallback.d(-1, null);
        } else if (i == 0) {
            d(list, iBaseResponseCallback);
        } else if (i == 1) {
            b(list, iBaseResponseCallback);
        } else {
            ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "recommendType is invalid in getRecommendGoal");
        }
    }

    private static void d(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        njj.d("9002", list, new a(iBaseResponseCallback));
    }

    private static int c() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        float f = 59.0f;
        int i = 160;
        if (userInfo == null) {
            ReleaseLogUtil.a("SCUI_RecommendGoalUtil", "userInfomation is null in getCaloriesDefaultGoal");
            return a(e(1, 160, 59.0f, 35), RoundingMode.DOWN);
        }
        int gender = userInfo.getGender();
        int height = userInfo.getHeight();
        float weight = userInfo.getWeight();
        int age = userInfo.getAge();
        LogUtil.a("SCUI_RecommendGoalUtil", " g is ", Integer.valueOf(gender), " h is ", Integer.valueOf(height), " w is ", Float.valueOf(weight), " a is ", Integer.valueOf(age));
        int i2 = (gender == 0 || gender == 1) ? gender : 1;
        if (height > 0) {
            i = height;
        } else if (i2 == 0) {
            i = 170;
        }
        if (weight > 0.0f) {
            f = weight;
        } else if (i2 == 0) {
            f = 69.6f;
        }
        return a(e(i2, i, f, age > 0 ? age : 35), RoundingMode.DOWN);
    }

    private static int a(double d2, RoundingMode roundingMode) {
        if (d2 < 100000.0d) {
            return 100000;
        }
        if (d2 > 1500000.0d) {
            return 1500000;
        }
        return BigDecimal.valueOf(d2).setScale(-4, roundingMode).intValue();
    }

    private static double e(int i, int i2, float f, int i3) {
        double d2;
        double a2 = arw.a(i2, f);
        if (a2 < 28.0d) {
            double d3 = f;
            d2 = i == 0 ? (((d3 * 2.72d) + (i2 * 0.98d)) - (i3 * 1.37d)) + 18.6d : (((d3 * 1.83d) + (i2 * 0.39d)) - (i3 * 0.9d)) + 129.11d;
        } else {
            double d4 = (a2 / 28.0d) - 1.0d;
            d2 = i == 0 ? ((((2.72d - (d4 * 1.5d)) * f) + (i2 * 0.98d)) - (i3 * 1.37d)) + 18.6d : ((((1.83d - d4) * f) + (i2 * 0.39d)) - (i3 * 0.9d)) + 129.11d;
        }
        LogUtil.a("SCUI_RecommendGoalUtil", "caloriesBmiGoal is ", Double.valueOf(d2));
        return d2 * 1000.0d;
    }

    private static void b(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        HashMap hashMap = new HashMap(list.size());
        for (String str : list) {
            if ("900200007".equals(str)) {
                hashMap.put(str, a());
            } else {
                hashMap.put(str, e(str, -1));
            }
        }
        iBaseResponseCallback.d(d((HashMap<String, nir>) hashMap), hashMap);
    }

    private static nir a() {
        nir e2 = e("900200007", -1);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        HashMap hashMap3 = new HashMap(1);
        a(countDownLatch, hashMap2, d, f15314a);
        a(countDownLatch, hashMap3, e, c);
        b(countDownLatch, hashMap);
        try {
            countDownLatch.await(10L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "interrupted while waiting for requestData");
        }
        List list = (List) d((Map) hashMap2);
        List list2 = (List) d((Map) hashMap3);
        njc njcVar = (njc) d((Map) hashMap);
        if (koq.b(list) || koq.b(list2) || njcVar == null) {
            ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "QueryData is error with hiHealthData or statDataList");
            return e2;
        }
        c(d(list, list2, njcVar), njcVar.e(), e2);
        return e2;
    }

    private static void b(final CountDownLatch countDownLatch, final Map<Integer, njc> map) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900200007");
        njj.d("9002", arrayList, new HiDataReadResultListener() { // from class: niw.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("SCUI_RecommendGoalUtil", "errorCode is ", Integer.valueOf(i), "data is ", obj);
                if (!koq.e(obj, HiSampleConfig.class)) {
                    ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "setThreeCircleGoal objData is not instanceof HashMap");
                    countDownLatch.countDown();
                    return;
                }
                List list = (List) obj;
                if (koq.b(list)) {
                    ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "HiSampleConfig list is empty");
                    countDownLatch.countDown();
                    return;
                }
                HiSampleConfig hiSampleConfig = (HiSampleConfig) list.get(0);
                if (hiSampleConfig == null || TextUtils.isEmpty(hiSampleConfig.getConfigData())) {
                    ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "hiSampleConfig is null or configData is empty.");
                    countDownLatch.countDown();
                } else {
                    map.put(0, (njc) HiJsonUtil.e(hiSampleConfig.getConfigData(), njc.class));
                    countDownLatch.countDown();
                }
            }
        });
    }

    private static void a(final CountDownLatch countDownLatch, final Map<Integer, List<HiHealthData>> map, int[] iArr, String[] strArr) {
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(e(iArr, strArr), new HiAggregateListener() { // from class: niw.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.a("SCUI_RecommendGoalUtil", "errorCode is ", Integer.valueOf(i), "datas is ", list);
                map.put(0, list);
                countDownLatch.countDown();
            }
        });
    }

    private static <T> T d(Map<Integer, T> map) {
        if (map == null || !map.containsKey(0)) {
            return null;
        }
        return map.get(0);
    }

    private static HiAggregateOption e(int[] iArr, String[] strArr) {
        long currentTimeMillis = System.currentTimeMillis();
        long c2 = jdl.c(currentTimeMillis, 2, -1);
        long b = jdl.b(currentTimeMillis, 2, -1);
        LogUtil.a("SCUI_RecommendGoalUtil", " lastWeekStartTime ", Long.valueOf(c2), "lastWeekEndTime", Long.valueOf(b));
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(c2);
        hiAggregateOption.setEndTime(b);
        hiAggregateOption.setType(iArr);
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }

    private static int d(List<HiHealthData> list, List<HiHealthData> list2, njc njcVar) {
        int i;
        if (jdl.d(System.currentTimeMillis(), -6) < njcVar.d() || koq.b(list) || koq.b(list2)) {
            LogUtil.a("SCUI_RecommendGoalUtil", "change goal in last 7 days, or lastWeekList is empty");
            return 0;
        }
        HashMap hashMap = new HashMap();
        for (HiHealthData hiHealthData : list2) {
            if (hiHealthData != null) {
                hashMap.put(Long.valueOf(hiHealthData.getStartTime()), hiHealthData);
            }
        }
        int d2 = d(list2);
        int e2 = njcVar.e();
        int i2 = 0;
        for (HiHealthData hiHealthData2 : list) {
            if (hiHealthData2 != null) {
                String[] strArr = f15314a;
                int i3 = hiHealthData2.getInt(strArr[1]);
                if (hashMap.containsKey(Long.valueOf(hiHealthData2.getStartTime()))) {
                    i = ((HiHealthData) hashMap.get(Long.valueOf(hiHealthData2.getStartTime()))).getInt("sport_calorie_sum");
                } else {
                    i = hiHealthData2.getInt(strArr[0]);
                }
                if (i >= i3) {
                    i2++;
                }
            }
        }
        LogUtil.a("SCUI_RecommendGoalUtil", "caloriesStatAvg is ", Integer.valueOf(d2), " achievedDays is", Integer.valueOf(i2), "currentGoal is ", Integer.valueOf(e2));
        return a(i2, d2, e2);
    }

    private static int a(int i, int i2, int i3) {
        if (i < 5 || i2 < b(i3, 1.1d) || i3 >= 1500000) {
            return (i > 2 || i2 >= b(i3, 0.9d) || i2 <= 0 || i3 <= 100000) ? 0 : -1;
        }
        return 1;
    }

    private static int d(List<HiHealthData> list) {
        int i;
        int i2 = 0;
        int i3 = 0;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null && (i = hiHealthData.getInt("sport_calorie_sum")) > 0) {
                i3 += i;
                i2++;
            }
        }
        if (i2 != 0) {
            return i3 / i2;
        }
        return 0;
    }

    private static nir e(String str, int i) {
        nir nirVar = new nir();
        nirVar.e(str);
        nirVar.a(i);
        nirVar.c(i);
        nirVar.d(0);
        return nirVar;
    }

    private static void c(int i, int i2, nir nirVar) {
        nirVar.d(i);
        nirVar.c(i2);
        if (i != 0) {
            if (i == 1) {
                i2 = a(b(i2, 1.1d), RoundingMode.UP);
            } else if (i == -1) {
                i2 = a(b(i2, 0.9d), RoundingMode.DOWN);
            } else {
                LogUtil.b("SCUI_RecommendGoalUtil", "state is invalid");
                i2 = -1;
            }
        }
        LogUtil.a("SCUI_RecommendGoalUtil", "updateCaloriesRecommend changeGoal is ", Integer.valueOf(i2));
        nirVar.a(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static nir b(njc njcVar, String str, int i) {
        nir nirVar = new nir();
        nirVar.a(i);
        nirVar.c(njcVar.e());
        nirVar.d(0);
        nirVar.e(str);
        return nirVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(HashMap<String, nir> hashMap) {
        hashMap.put("900200007", e("900200007", c()));
        hashMap.put("900200008", e("900200008", 25));
        hashMap.put("900200009", e("900200009", 12));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(String str, njc njcVar) {
        if ("900200007".equals(str) && (njcVar.e() == 270000 || njcVar.e() == 230000)) {
            return true;
        }
        if ("900200008".equals(str) && (njcVar.e() == 30 || njcVar.e() == 20)) {
            return true;
        }
        return "900200009".equals(str) && njcVar.e() == 12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int d(HashMap<String, nir> hashMap) {
        for (Map.Entry<String, nir> entry : hashMap.entrySet()) {
            if (entry.getValue() == null || entry.getValue().e() == -1) {
                return -1;
            }
        }
        return 0;
    }

    private static int b(int i, double d2) {
        return BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(d2)).intValue();
    }

    static class a implements HiDataReadResultListener {
        WeakReference<IBaseResponseCallback> b;

        private a(IBaseResponseCallback iBaseResponseCallback) {
            this.b = new WeakReference<>(iBaseResponseCallback);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            IBaseResponseCallback iBaseResponseCallback = this.b.get();
            if (iBaseResponseCallback == null) {
                ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "callback is null!");
                return;
            }
            HashMap hashMap = new HashMap(3);
            niw.a(hashMap);
            LogUtil.a("SCUI_RecommendGoalUtil", "errorCode is ", Integer.valueOf(i), "data is ", obj);
            if (!koq.e(obj, HiSampleConfig.class)) {
                ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "setThreeCircleGoal objData is not instanceof HashMap");
                iBaseResponseCallback.d(niw.d((HashMap<String, nir>) hashMap), hashMap);
                return;
            }
            for (HiSampleConfig hiSampleConfig : (List) obj) {
                if (hiSampleConfig == null) {
                    ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "hiSampleConfig is null.");
                } else {
                    String configData = hiSampleConfig.getConfigData();
                    String configId = hiSampleConfig.getConfigId();
                    if (TextUtils.isEmpty(configData) || TextUtils.isEmpty(configId)) {
                        ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "configData or configId is empty.");
                    } else {
                        njc njcVar = (njc) HiJsonUtil.e(configData, njc.class);
                        if (njcVar != null && njcVar.e() > 0) {
                            if (!niw.d(configId, njcVar)) {
                                hashMap.put(configId, niw.b(njcVar, configData, njcVar.e()));
                            }
                        } else {
                            ReleaseLogUtil.c("SCUI_RecommendGoalUtil", "goalBean is null or value is valid.");
                        }
                    }
                }
            }
            iBaseResponseCallback.d(niw.d((HashMap<String, nir>) hashMap), hashMap);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("SCUI_RecommendGoalUtil", "errorCode is ", Integer.valueOf(i2), "data is ", obj, "intentType is ", Integer.valueOf(i));
        }
    }
}
