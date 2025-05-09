package defpackage;

import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Pair;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.bubblelayout.HealthBubbleLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.ruler.circlescaleruler.HealthCircleScaleRuler;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class dpe {
    private static final Map<String, Pair<Float, Float>> d;

    private static int e(int i) {
        if (i == 11 || i == 21 || i == 31) {
            return 40;
        }
        return i;
    }

    static {
        HashMap hashMap = new HashMap();
        d = hashMap;
        Float valueOf = Float.valueOf(0.2f);
        Float valueOf2 = Float.valueOf(0.4f);
        hashMap.put("cn_0_0_10", Pair.create(valueOf, valueOf2));
        Float valueOf3 = Float.valueOf(0.3f);
        Float valueOf4 = Float.valueOf(0.5f);
        hashMap.put("cn_0_0_20", Pair.create(valueOf3, valueOf4));
        hashMap.put("cn_0_0_30", Pair.create(valueOf, valueOf2));
        Float valueOf5 = Float.valueOf(0.15f);
        Float valueOf6 = Float.valueOf(0.35f);
        hashMap.put("cn_0_1_10", Pair.create(valueOf5, valueOf6));
        Float valueOf7 = Float.valueOf(0.25f);
        Float valueOf8 = Float.valueOf(0.45f);
        hashMap.put("cn_0_1_20", Pair.create(valueOf7, valueOf8));
        hashMap.put("cn_0_1_30", Pair.create(valueOf5, valueOf6));
        Float valueOf9 = Float.valueOf(0.0f);
        Float valueOf10 = Float.valueOf(0.05f);
        hashMap.put("cn_0_1_11", Pair.create(valueOf9, valueOf10));
        hashMap.put("cn_0_1_21", Pair.create(valueOf9, valueOf10));
        hashMap.put("cn_0_1_31", Pair.create(valueOf9, valueOf10));
        hashMap.put("cn_1_0_10", Pair.create(valueOf7, valueOf8));
        hashMap.put("cn_1_0_20", Pair.create(valueOf3, valueOf4));
        hashMap.put("cn_1_0_30", Pair.create(valueOf5, valueOf6));
        hashMap.put("cn_1_1_10", Pair.create(valueOf, valueOf2));
        hashMap.put("cn_1_1_20", Pair.create(valueOf7, valueOf8));
        hashMap.put("cn_1_1_30", Pair.create(Float.valueOf(0.1f), valueOf3));
        hashMap.put("cn_1_1_11", Pair.create(valueOf9, valueOf10));
        hashMap.put("cn_1_1_21", Pair.create(valueOf9, valueOf10));
        hashMap.put("cn_1_1_31", Pair.create(valueOf9, valueOf10));
        hashMap.put("cn_2_0_10", Pair.create(valueOf, valueOf2));
        hashMap.put("cn_2_0_20", Pair.create(valueOf3, valueOf4));
        hashMap.put("cn_2_0_30", Pair.create(valueOf, valueOf2));
        hashMap.put("cn_2_1_10", Pair.create(valueOf5, valueOf6));
        hashMap.put("cn_2_1_20", Pair.create(valueOf7, valueOf8));
        hashMap.put("cn_2_1_30", Pair.create(valueOf5, valueOf6));
        hashMap.put("cn_2_1_11", Pair.create(valueOf9, valueOf10));
        hashMap.put("cn_2_1_21", Pair.create(valueOf9, valueOf10));
        hashMap.put("cn_2_1_31", Pair.create(valueOf9, valueOf10));
        hashMap.put("de_0_0_10", Pair.create(valueOf7, valueOf6));
        hashMap.put("de_0_0_20", Pair.create(valueOf3, valueOf8));
        hashMap.put("de_0_0_30", Pair.create(valueOf7, valueOf2));
        hashMap.put("de_0_1_10", Pair.create(valueOf, valueOf3));
        hashMap.put("de_0_1_20", Pair.create(valueOf7, valueOf2));
        hashMap.put("de_0_1_30", Pair.create(valueOf, valueOf6));
        hashMap.put("de_0_1_11", Pair.create(valueOf9, valueOf10));
        hashMap.put("de_0_1_21", Pair.create(valueOf9, valueOf10));
        hashMap.put("de_0_1_31", Pair.create(valueOf9, valueOf10));
        hashMap.put("de_1_0_10", Pair.create(valueOf7, valueOf6));
        hashMap.put("de_1_0_20", Pair.create(valueOf3, valueOf8));
        hashMap.put("de_1_0_30", Pair.create(valueOf7, valueOf2));
        hashMap.put("de_1_1_10", Pair.create(valueOf, valueOf3));
        hashMap.put("de_1_1_20", Pair.create(valueOf7, valueOf2));
        hashMap.put("de_1_1_30", Pair.create(valueOf, valueOf6));
        hashMap.put("de_1_1_11", Pair.create(valueOf9, valueOf10));
        hashMap.put("de_1_1_21", Pair.create(valueOf9, valueOf10));
        hashMap.put("de_1_1_31", Pair.create(valueOf9, valueOf10));
        hashMap.put("de_2_0_10", Pair.create(valueOf7, valueOf6));
        hashMap.put("de_2_0_20", Pair.create(valueOf3, valueOf8));
        hashMap.put("de_2_0_30", Pair.create(valueOf7, valueOf2));
        hashMap.put("de_2_1_10", Pair.create(valueOf, valueOf3));
        hashMap.put("de_2_1_20", Pair.create(valueOf7, valueOf2));
        hashMap.put("de_2_1_30", Pair.create(valueOf, valueOf6));
        hashMap.put("de_2_1_11", Pair.create(valueOf9, valueOf10));
        hashMap.put("de_2_1_21", Pair.create(valueOf9, valueOf10));
        hashMap.put("de_2_1_31", Pair.create(valueOf9, valueOf10));
        hashMap.put("it_0_0_10", Pair.create(valueOf, Float.valueOf(0.26f)));
        Float valueOf11 = Float.valueOf(0.47f);
        hashMap.put("it_0_0_20", Pair.create(valueOf2, valueOf11));
        hashMap.put("it_0_0_30", Pair.create(valueOf3, valueOf6));
        hashMap.put("it_0_1_10", Pair.create(valueOf5, Float.valueOf(0.21f)));
        Float valueOf12 = Float.valueOf(0.42f);
        hashMap.put("it_0_1_20", Pair.create(valueOf6, valueOf12));
        hashMap.put("it_0_1_30", Pair.create(valueOf7, valueOf3));
        hashMap.put("it_0_1_11", Pair.create(valueOf9, valueOf10));
        hashMap.put("it_0_1_21", Pair.create(valueOf9, valueOf10));
        hashMap.put("it_0_1_31", Pair.create(valueOf9, valueOf10));
        hashMap.put("it_1_0_10", Pair.create(valueOf7, Float.valueOf(0.32f)));
        hashMap.put("it_1_0_20", Pair.create(valueOf2, valueOf11));
        hashMap.put("it_1_0_30", Pair.create(valueOf7, valueOf3));
        hashMap.put("it_1_1_10", Pair.create(valueOf, Float.valueOf(0.27f)));
        hashMap.put("it_1_1_20", Pair.create(valueOf6, valueOf12));
        hashMap.put("it_1_1_30", Pair.create(valueOf, valueOf7));
        hashMap.put("it_1_1_11", Pair.create(valueOf9, valueOf10));
        hashMap.put("it_1_1_21", Pair.create(valueOf9, valueOf10));
        hashMap.put("it_1_1_31", Pair.create(valueOf9, valueOf10));
        hashMap.put("it_2_0_10", Pair.create(valueOf7, valueOf3));
        hashMap.put("it_2_0_20", Pair.create(valueOf2, valueOf4));
        hashMap.put("it_2_0_30", Pair.create(Float.valueOf(0.28f), valueOf6));
        hashMap.put("it_2_1_10", Pair.create(valueOf, valueOf7));
        hashMap.put("it_2_1_20", Pair.create(valueOf6, valueOf8));
        hashMap.put("it_2_1_30", Pair.create(Float.valueOf(0.23f), valueOf3));
        hashMap.put("it_2_1_11", Pair.create(valueOf9, valueOf10));
        hashMap.put("it_2_1_21", Pair.create(valueOf9, valueOf10));
        hashMap.put("it_2_1_31", Pair.create(valueOf9, valueOf10));
    }

    public static Pair<Float, Float> Yy_(String str, int i, boolean z, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_");
        sb.append(i);
        if (z) {
            sb.append("_1");
        } else {
            sb.append("_0");
        }
        sb.append("_");
        sb.append(i2);
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            ReleaseLogUtil.d("R_DietRecordHelper", "getRecommendCaloriesRange() mapKey is empty");
        }
        Pair.create(Float.valueOf(0.0f), Float.valueOf(0.0f));
        Pair<Float, Float> pair = d.get(sb2);
        ReleaseLogUtil.e("R_DietRecordHelper", "getRecommendCaloriesRange() mapKey: ", sb2, ", mapValue: ", pair);
        return pair;
    }

    private static void c(List<Integer> list, quh quhVar, qul qulVar, int i) {
        if (koq.b(qulVar.c())) {
            LogUtil.h("DietRecordHelper", "setDietSugRange food is null ");
            return;
        }
        int[] d2 = d(a(list, qulVar.h()), quhVar.d().c(), qulVar.h(), i);
        if (d2.length == 2) {
            qulVar.a(d2);
        }
    }

    public static int[] d(boolean z, float f, int i, int i2) {
        int i3;
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1010);
        LogUtil.a("DietRecordHelper", "setDietSugRange countryCode ", accountInfo, " weightManagerType ", Integer.valueOf(i2));
        String str = "it";
        if (!TextUtils.isEmpty(accountInfo) && ("de".equals(accountInfo.toLowerCase(Locale.ENGLISH)) || "it".equals(accountInfo.toLowerCase(Locale.ENGLISH)) || "cn".equals(accountInfo.toLowerCase(Locale.ENGLISH)))) {
            str = accountInfo.toLowerCase(Locale.ENGLISH);
        } else {
            ReleaseLogUtil.d("DietRecordHelper", "countryCode is error: ", accountInfo);
        }
        int i4 = 0;
        if (f <= 0.0f || TextUtils.isEmpty(str)) {
            return new int[0];
        }
        if (Yy_(str, i2, z, i) != null) {
            float floatValue = BigDecimal.valueOf(((Float) r5.first).floatValue() * f).setScale(2, 4).floatValue();
            float floatValue2 = BigDecimal.valueOf(f * ((Float) r5.second).floatValue()).setScale(2, 4).floatValue();
            i4 = (int) Math.ceil(floatValue);
            i3 = (int) Math.ceil(floatValue2);
        } else {
            i3 = 0;
        }
        ReleaseLogUtil.e("R_DietRecordHelper", "setDietSugRange left: ", Integer.valueOf(i4), " right: ", Integer.valueOf(i3));
        return new int[]{i4, i3};
    }

    public static gsh c(final int i) {
        final gsh gshVar = new gsh();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final int b = DateFormatUtil.b(System.currentTimeMillis());
        LogUtil.a("DietRecordHelper", "getOnePointFullValue getDietRecord dayFormat ", Integer.valueOf(b));
        ThreadPoolManager.d().execute(new Runnable() { // from class: dpu
            @Override // java.lang.Runnable
            public final void run() {
                grz.c(b, r0, 3000L, new ResponseCallback() { // from class: dpq
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i2, Object obj) {
                        dpe.a(r1, r2, r3, i2, (List) obj);
                    }
                });
            }
        });
        try {
            ReleaseLogUtil.e("DietRecordHelper", "getOnePointFullValue isCountDownZero = ", Boolean.valueOf(countDownLatch.await(3000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            ReleaseLogUtil.d("DietRecordHelper", "getOnePointFullValue exception");
        }
        return gshVar;
    }

    static /* synthetic */ void a(CountDownLatch countDownLatch, int i, gsh gshVar, int i2, List list) {
        if (koq.b(list)) {
            LogUtil.h("DietRecordHelper", "getDietRecord list is empty ");
            countDownLatch.countDown();
            return;
        }
        quh quhVar = (quh) list.get(0);
        if (quhVar == null || quhVar.d() == null) {
            LogUtil.h("DietRecordHelper", "getDietRecord Overview is null:");
            countDownLatch.countDown();
            return;
        }
        qts d2 = quhVar.d();
        LogUtil.a("DietRecordHelper", "getDietRecord overview is: ", d2);
        float c = d2.c();
        gshVar.d(a(c, 10, i));
        gshVar.e(a(c, 20, i));
        gshVar.a(a(c, 30, i));
        countDownLatch.countDown();
    }

    private static int a(float f, int i, int i2) {
        if (d(false, f, i, i2).length < 2) {
            LogUtil.h("DietRecordHelper", "getOnePointFullValue range is error");
            return 0;
        }
        return Math.round((r1[0] + r1[1]) / 14.0f);
    }

    public static boolean a(List<Integer> list, int i) {
        if (koq.b(list)) {
            ReleaseLogUtil.d("R_DietRecordHelper", "isContainAddMeal addMealTypeList ", list, " mealType ", Integer.valueOf(i));
            return false;
        }
        if (i == 10 || i == 11) {
            return list.contains(11);
        }
        if (i == 20 || i == 21) {
            return list.contains(21);
        }
        return list.contains(31);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final long j, final long j2, final DeviceInfo deviceInfo, final List<quh> list) {
        kot.a().b(new ResponseCallback() { // from class: dpm
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                ReleaseLogUtil.e("R_DietRecordHelper", "syncMealsToDevice isSuccess = ", Boolean.valueOf(dpe.a(j, j2, deviceInfo, (List<quh>) list, r14 != null ? ((gsi) obj).g() : 0)));
            }
        });
    }

    private static List<qul> d(quh quhVar) {
        ArrayList arrayList = new ArrayList();
        if (quhVar == null) {
            ReleaseLogUtil.d("R_DietRecordHelper", "getAddMealTypeList dietRecord is null");
            return arrayList;
        }
        List<qul> a2 = quhVar.a();
        if (koq.b(a2)) {
            ReleaseLogUtil.d("R_DietRecordHelper", "getAddMealTypeList mealList ", a2);
            return arrayList;
        }
        HashMap hashMap = new HashMap();
        for (qul qulVar : a2) {
            if (qulVar != null) {
                int h = qulVar.h();
                qul qulVar2 = (qul) hashMap.get(Integer.valueOf(h));
                if (qulVar2 == null) {
                    hashMap.put(Integer.valueOf(h), qulVar);
                } else {
                    qulVar2.c(qulVar2.b() + qulVar.b());
                    if (!koq.b(qulVar.c())) {
                        qulVar2.c().addAll(qulVar.c());
                    }
                }
            }
        }
        return new ArrayList(hashMap.values());
    }

    public static List<Integer> b(quh quhVar) {
        int h;
        if (quhVar == null) {
            ReleaseLogUtil.d("R_DietRecordHelper", "getAddMealTypeList dietRecord is null");
            return Collections.emptyList();
        }
        List<qul> a2 = quhVar.a();
        if (koq.b(a2)) {
            ReleaseLogUtil.d("R_DietRecordHelper", "getAddMealTypeList mealList ", a2);
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (qul qulVar : a2) {
            if (qulVar != null && !koq.b(qulVar.c()) && ((h = qulVar.h()) == 11 || h == 21 || h == 31)) {
                arrayList.add(Integer.valueOf(h));
            }
        }
        return arrayList;
    }

    public static void e(final long j, final long j2, final DeviceInfo deviceInfo) {
        if (gsd.a()) {
            b(j, j2, deviceInfo, 2);
            LogUtil.a("DietRecordHelper", "cache isSupportDiet syncMealsToDevice");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dpj
                @Override // java.lang.Runnable
                public final void run() {
                    grz.b(false, (ResponseCallback<Boolean>) new ResponseCallback() { // from class: dpi
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i, Object obj) {
                            dpe.c(r1, r3, r5, i, (Boolean) obj);
                        }
                    });
                }
            });
        }
    }

    static /* synthetic */ void c(long j, long j2, DeviceInfo deviceInfo, int i, Boolean bool) {
        if (bool != null && bool.booleanValue()) {
            b(j, j2, deviceInfo, 2);
            LogUtil.a("DietRecordHelper", "syncMealsToDevice isSupportDiet is true");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final long j, final long j2, DeviceInfo deviceInfo, final int i) {
        final DeviceInfo deviceInfo2 = (deviceInfo == null && EnvironmentUtils.c()) ? cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "DietRecordHelper") : deviceInfo;
        if (deviceInfo2 == null && EnvironmentUtils.c()) {
            ReleaseLogUtil.d("R_DietRecordHelper", "syncMealsToDevice deviceInfo is null retryTimes is : ", Integer.valueOf(i));
            if (i > 0) {
                HandlerCenter.d().e(new Runnable() { // from class: dpp
                    @Override // java.lang.Runnable
                    public final void run() {
                        dpe.b(j, j2, (DeviceInfo) null, i - 1);
                    }
                }, 2000L);
                return;
            }
            return;
        }
        ReleaseLogUtil.e("R_DietRecordHelper", "syncMealsToDevice startTime is ", Long.valueOf(j), ",endTime is ", Long.valueOf(j2));
        long t = jdl.t(j);
        long e = jdl.e(j2);
        long e2 = jdl.e(System.currentTimeMillis());
        if (j > j2 || j2 > e2 || String.valueOf(j2).length() != 13) {
            ReleaseLogUtil.d("R_DietRecordHelper", "syncMealsToDevice query time is illegal");
            t = jdl.d(e2, -6);
            e = e2;
        }
        if (jdl.e(t, e) > 7) {
            ReleaseLogUtil.d("R_DietRecordHelper", "syncMealsToDevice queryEndTime - queryStartTime > 7 ");
            t = jdl.d(e, -6);
        }
        int b = DateFormatUtil.b(t);
        int b2 = DateFormatUtil.b(e);
        ReleaseLogUtil.e("R_DietRecordHelper", "connectDeviceSyncMealsToDevice queryStartTime ", Long.valueOf(t), " queryEndTime ", Long.valueOf(e), " startDate ", Integer.valueOf(b), " endDate ", Integer.valueOf(b2));
        grz.b(b, b2, new ResponseCallback() { // from class: dps
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                dpe.d(j, j2, deviceInfo2, i2, (List) obj);
            }
        });
    }

    static /* synthetic */ void d(final long j, final long j2, final DeviceInfo deviceInfo, int i, List list) {
        LogUtil.a("DietRecordHelper", "connectDeviceSyncMealsToDevice errorCode ", Integer.valueOf(i), " list ", list);
        final List list2 = (List) HiJsonUtil.b(HiJsonUtil.e(list), new TypeToken<List<quh>>() { // from class: dpe.3
        }.getType());
        ThreadPoolManager.d().execute(new Runnable() { // from class: dpn
            @Override // java.lang.Runnable
            public final void run() {
                dpe.b(j, j2, deviceInfo, (List<quh>) list2);
            }
        });
    }

    public static boolean a(long j, long j2, DeviceInfo deviceInfo, List<quh> list, int i) {
        ReleaseLogUtil.e("R_DietRecordHelper", "sendDietSamplePoint");
        cvu cvuVar = new cvu();
        cvuVar.setSrcPkgName("hw.unitedevice.weightMgr");
        cvuVar.setWearPkgName("hw.sport.dietcal");
        cvuVar.setCommandId(3);
        cvuVar.c(DicDataTypeUtil.DataType.DIET_RECORD_SET.value());
        cvuVar.d(j);
        cvuVar.b(j2);
        ArrayList arrayList = new ArrayList();
        cvv cvvVar = new cvv();
        cvvVar.d(DicDataTypeUtil.DataType.DIET_RECORD_SET.value());
        cvvVar.b(cvx.a(cvx.c(HiJsonUtil.e(b(list, i, deviceInfo)))));
        arrayList.add(cvvVar);
        cvuVar.e(arrayList);
        cwj a2 = a(list);
        if (a2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("dietaryAnalysis", a2);
            String e = HiJsonUtil.e(hashMap);
            String c = cvx.c(e);
            cvuVar.d(cvx.a(c));
            LogUtil.a("DietRecordHelper", "sendDietSamplePoint json ", e, " hex ", c);
        }
        LogUtil.a("DietRecordHelper", "sendDietSamplePoint samplePoint: ", cvuVar.toString());
        return cuk.b().sendSamplePointCommand(deviceInfo, cvuVar, "DietRecordHelper");
    }

    private static cwj a(List<quh> list) {
        quh quhVar;
        if (koq.b(list)) {
            ReleaseLogUtil.d("R_DietRecordHelper", "getDeviceSyncDietAnalysis list ", list);
            return null;
        }
        int c = DateFormatUtil.c(System.currentTimeMillis(), TimeZone.getDefault());
        Iterator<quh> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                quhVar = null;
                break;
            }
            quhVar = it.next();
            if (quhVar != null && quhVar.c() == c) {
                break;
            }
        }
        if (quhVar == null) {
            ReleaseLogUtil.d("R_DietRecordHelper", "getDeviceSyncDietAnalysis todayDietRecord is null");
            return null;
        }
        LogUtil.a("DietRecordHelper", "getDeviceSyncDietAnalysis todayDietRecord ", quhVar);
        final cwj[] cwjVarArr = new cwj[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        grz.b(quhVar, (ResponseCallback<cwj>) new ResponseCallback() { // from class: dpo
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                dpe.a(cwjVarArr, countDownLatch, i, (cwj) obj);
            }
        });
        try {
            ReleaseLogUtil.e("R_DietRecordHelper", "getDeviceSyncDietAnalysis isAwait ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e) {
            ReleaseLogUtil.c("R_DietRecordHelper", "getDeviceSyncDietAnalysis exception ", ExceptionUtils.d(e));
        }
        return cwjVarArr[0];
    }

    static /* synthetic */ void a(cwj[] cwjVarArr, CountDownLatch countDownLatch, int i, cwj cwjVar) {
        LogUtil.a("DietRecordHelper", "getDeviceSyncDietAnalysis resultDeviceSyncDietAnalysis ", cwjVar);
        cwjVarArr[0] = cwjVar;
        countDownLatch.countDown();
    }

    private static qtp[] b(List<quh> list, int i, DeviceInfo deviceInfo) {
        int h;
        ReleaseLogUtil.e("R_DietRecordHelper", "getDeviceMealArray");
        ArrayList arrayList = new ArrayList();
        Iterator<quh> it = list.iterator();
        while (true) {
            int i2 = 0;
            if (!it.hasNext()) {
                LogUtil.a("DietRecordHelper", "getDeviceMealArray DeviceSyncMeal: ", HiJsonUtil.e(arrayList));
                return (qtp[]) arrayList.toArray(new qtp[0]);
            }
            quh next = it.next();
            if (next != null) {
                List<Integer> b = b(next);
                List<qul> d2 = d(next);
                boolean c = cwi.c(deviceInfo, a.N);
                LogUtil.a("DietRecordHelper", "isSupport check in Capability is : ", Boolean.valueOf(c));
                float f = 0.0f;
                qtp qtpVar = null;
                int i3 = 0;
                for (qul qulVar : d2) {
                    if (arrayList.size() > 42) {
                        ReleaseLogUtil.d("R_DietRecordHelper", "getDeviceMealArray over size: ", 42);
                        return (qtp[]) arrayList.toArray(new qtp[i2]);
                    }
                    if (String.valueOf(qulVar.d()).matches("\\d{13}")) {
                        LogUtil.h("DietRecordHelper", "getDeviceMealArray isTimeMillis true");
                        qulVar.l();
                    }
                    c(b, next, qulVar, i);
                    if (!c) {
                        h = e(qulVar.h());
                    } else {
                        h = qulVar.h();
                    }
                    if (h == 40 && !koq.b(qulVar.c())) {
                        f += qulVar.b();
                        i3 += qulVar.f()[1];
                        qtp qtpVar2 = new qtp(40, qulVar.d(), f, new int[]{0, i3});
                        qtpVar2.c(!koq.b(qulVar.c()));
                        qtpVar = qtpVar2;
                    } else {
                        qtp qtpVar3 = new qtp(h, qulVar.d(), qulVar.b(), qulVar.f());
                        qtpVar3.c(!koq.b(qulVar.c()));
                        arrayList.add(qtpVar3);
                    }
                    i2 = 0;
                }
                if (qtpVar != null) {
                    arrayList.add(qtpVar);
                }
            }
        }
    }

    public static void e(DeviceInfo deviceInfo, cvp cvpVar) {
        if (deviceInfo == null) {
            ReleaseLogUtil.d("R_DietRecordHelper", "syncVipTagToDevice deviceInfo is null");
            return;
        }
        byte[] c = cvpVar.c();
        if (c == null) {
            ReleaseLogUtil.d("R_DietRecordHelper", "syncVipTagToDevice eventData is null");
            return;
        }
        ReleaseLogUtil.e("R_DietRecordHelper", "syncVipTagToDevice data: ", cvx.d(c));
        if (CommonUtil.w(cvx.d(c)) != 1) {
            ReleaseLogUtil.d("R_DietRecordHelper", "syncVipTagToDevice req is not vip ");
        } else {
            b(deviceInfo);
        }
    }

    private static void b(final DeviceInfo deviceInfo) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: dpl
            @Override // java.lang.Runnable
            public final void run() {
                ((VipApi) Services.c("vip", VipApi.class)).getVipInfo(new VipCallback() { // from class: dpe.2
                    @Override // com.huawei.health.vip.api.VipCallback
                    public void onSuccess(Object obj) {
                        if (obj == null) {
                            ReleaseLogUtil.d("R_DietRecordHelper", "getVipTag onSuccess result is null");
                        } else if (obj instanceof UserMemberInfo) {
                            UserMemberInfo userMemberInfo = (UserMemberInfo) obj;
                            int i = (userMemberInfo.getMemberFlag() != 1 || gpn.d(userMemberInfo)) ? 0 : 1;
                            ReleaseLogUtil.e("R_DietRecordHelper", "getVipTag vipTag = ", Integer.valueOf(i));
                            ReleaseLogUtil.e("R_DietRecordHelper", "getVipTag onSuccess isSuccess = ", Boolean.valueOf(dpe.e(DeviceInfo.this, i)));
                        }
                    }

                    @Override // com.huawei.health.vip.api.VipCallback
                    public void onFailure(int i, String str) {
                        ReleaseLogUtil.d("R_DietRecordHelper", "getVipTag errorCode=", Integer.valueOf(i), " errMsg=", str);
                        ReleaseLogUtil.e("R_DietRecordHelper", "getVipTag onFailure isSuccess = ", Boolean.valueOf(dpe.e(DeviceInfo.this, 2)));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean e(DeviceInfo deviceInfo, int i) {
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.common");
        cvpVar.setWearPkgName("hw.sport.dietcal");
        cvpVar.setCommandId(2);
        cvpVar.a(80030004L);
        cvpVar.b(0);
        cvpVar.e(cvx.a(cvx.e(i)));
        ReleaseLogUtil.e("R_DietRecordHelper", "sendVipTagSampleEvent sampleEvent: ", cvpVar.toString());
        return cuk.b().sendSampleEventCommand(deviceInfo, cvpVar, "DietRecordHelper");
    }

    public static Pair<Float, Float> Yx_(qts qtsVar) {
        if (qtsVar == null || qtsVar.d() <= 0) {
            ReleaseLogUtil.d("DietRecordHelper", "getCaloricDeficitScopeAngle overview is null");
            return null;
        }
        if (qtsVar.j() <= 0.0d) {
            return null;
        }
        Pair<Integer, Integer> Yw_ = Yw_(qtsVar);
        float intValue = ((Integer) Yw_.second).intValue();
        if (intValue <= 0.0f) {
            LogUtil.h("DietRecordHelper", "getCaloricDeficitScopeAngle max ", Float.valueOf(intValue), " pair ", Yw_, " overview ", qtsVar);
            return null;
        }
        float e = e(qtsVar, intValue);
        if (e <= 0.0f) {
            LogUtil.h("DietRecordHelper", "getCaloricDeficitScopeAngle maxScaleValue ", Float.valueOf(e), " max ", Float.valueOf(intValue), " pair ", Yw_, " overview ", qtsVar);
            return null;
        }
        return new Pair<>(Float.valueOf(((((Integer) Yw_.first).intValue() / e) * 70.0f) + 270.0f), Float.valueOf(((intValue / e) * 70.0f) + 270.0f));
    }

    public static Pair<Integer, Integer> Yw_(qts qtsVar) {
        if (qtsVar == null) {
            return new Pair<>(0, 0);
        }
        int d2 = qtsVar.d();
        if (d2 <= 0) {
            ReleaseLogUtil.d("DietRecordHelper", "getCaloricDeficitScope caloricDeficitGoal ", Integer.valueOf(d2));
            int b = b(qtsVar);
            return new Pair<>(Integer.valueOf(b), Integer.valueOf(b));
        }
        return new Pair<>(Integer.valueOf(d2 - 100), Integer.valueOf(d2 + 100));
    }

    private static int e(qts qtsVar, float f) {
        if (qtsVar == null) {
            ReleaseLogUtil.d("DietRecordHelper", "getMaxScaleValue overview is null maxCaloricDeficitScope ", Float.valueOf(f));
            return 800;
        }
        double j = qtsVar.j();
        if (j == 0.0d) {
            return 800;
        }
        if (j < 0.0d) {
            return 1200;
        }
        if (f <= 0.0f) {
            ReleaseLogUtil.d("DietRecordHelper", "getMaxScaleValue maxCaloricDeficitScope ", Float.valueOf(f));
            return 2000;
        }
        if (f < 800.0f) {
            return 800;
        }
        if (f < 1200.0f) {
            return 1200;
        }
        if (f < 1600.0f) {
            return KakaConstants.TASK_MEASURE_TODAY_BLOOD_PRESSURE;
        }
        return 2000;
    }

    private static List<String> a(qts qtsVar, float f) {
        int e = e(qtsVar, f);
        int i = e / 4;
        ArrayList arrayList = new ArrayList();
        for (int i2 = -e; i2 <= e; i2 += i) {
            arrayList.add(UnitUtil.e(i2, 1, 0));
        }
        return arrayList;
    }

    public static List<Integer> d() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 35; i++) {
            arrayList.add(Integer.valueOf((i * 4) + 1));
        }
        return arrayList;
    }

    public static int e(qts qtsVar) {
        if (qtsVar == null) {
            ReleaseLogUtil.d("DietRecordHelper", "getConsumption overview is null");
            return 0;
        }
        return ((int) UnitUtil.a(qtsVar.e(), 0)) + qtsVar.h();
    }

    public static int b(qts qtsVar) {
        if (qtsVar == null) {
            ReleaseLogUtil.d("DietRecordHelper", "getCaloricDeficit overview is null");
            return 0;
        }
        int e = e(qtsVar);
        int a2 = (int) UnitUtil.a(qtsVar.f(), 0);
        return qtsVar.j() >= 0.0d ? e - a2 : a2 - e;
    }

    public static float a(qts qtsVar) {
        int b = b(qtsVar);
        if (b == 0) {
            return 0.0f;
        }
        float e = e(qtsVar, ((Integer) Yw_(qtsVar).second).intValue());
        if (e <= 0.0f) {
            LogUtil.h("DietRecordHelper", "getCaloricDeficitAngle maxScaleValue ", Float.valueOf(e), " caloricDeficit ", Integer.valueOf(b));
            return 0.0f;
        }
        float f = 70.0f / (e / b);
        if (f > 0.0f) {
            return Math.min(74.375f, f);
        }
        return Math.max(-74.375f, f);
    }

    public static void f(final qts qtsVar) {
        if (qtsVar == null) {
            ReleaseLogUtil.d("DietRecordHelper", "refreshWeightManager dietCalorieOverview is null");
            return;
        }
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dpk
                @Override // java.lang.Runnable
                public final void run() {
                    dpe.f(qts.this);
                }
            });
            return;
        }
        final int d2 = qtsVar.d();
        final int b = qtsVar.b();
        if (d2 <= 0 || b <= 0) {
            ReleaseLogUtil.d("DietRecordHelper", "refreshWeightManager caloricDeficitGoal ", Integer.valueOf(d2), " activityCalGoal ", Integer.valueOf(b));
        } else {
            grz.e((ResponseCallback<gse>) new ResponseCallback() { // from class: dpr
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    dpe.a(d2, b, i, (gse) obj);
                }
            });
        }
    }

    static /* synthetic */ void a(int i, int i2, int i3, final gse gseVar) {
        if (gseVar == null) {
            ReleaseLogUtil.d("DietRecordHelper", "refreshWeightManager onResponse goalDetail is null errorCode ", Integer.valueOf(i3));
            return;
        }
        gsg b = gseVar.b();
        if (b == null) {
            ReleaseLogUtil.d("DietRecordHelper", "refreshWeightManager onResponse simpleFatInfo is null goalDetail ", gseVar);
            return;
        }
        final int a2 = b.a();
        final int d2 = b.d();
        if (a2 == i && d2 == i2) {
            return;
        }
        LogUtil.a("DietRecordHelper", "refreshWeightManager onResponse cloudCaloriesGap ", Integer.valueOf(a2), " cloudActivityCalories ", Integer.valueOf(d2), " activityCalGoal ", Integer.valueOf(i2), " caloricDeficitGoal ", Integer.valueOf(i));
        sqp.c("900300023", new HiDataReadResultListener() { // from class: dpe.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i4, Object obj, int i5, int i6) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i4, int i5) {
                HiSampleConfig hiSampleConfig;
                gsi gsiVar;
                LogUtil.a("DietRecordHelper", "refreshWeightManager onResult errorCode ", Integer.valueOf(i4), " object ", obj);
                if (koq.e(obj, HiSampleConfig.class)) {
                    List list = (List) obj;
                    if (koq.b(list) || (hiSampleConfig = (HiSampleConfig) list.get(0)) == null) {
                        return;
                    }
                    long c = gse.this.c();
                    long modifiedTime = hiSampleConfig.getModifiedTime();
                    LogUtil.a("DietRecordHelper", "refreshWeightManager onResult updateTime ", Long.valueOf(c), " modifiedTime ", Long.valueOf(modifiedTime));
                    if (c >= modifiedTime && (gsiVar = (gsi) HiJsonUtil.e(hiSampleConfig.getConfigData(), gsi.class)) != null) {
                        if (a2 == gsiVar.c() && d2 == gsiVar.a()) {
                            return;
                        }
                        gsiVar.b(a2);
                        gsiVar.c(d2);
                        LogUtil.a("DietRecordHelper", "refreshWeightManager onResponse weightManager ", gsiVar);
                        kpu.c(gsiVar, c, false, (ResponseCallback<gsi>) null);
                    }
                }
            }
        });
    }

    public static void c(nqg nqgVar, qts qtsVar, boolean z) {
        if (nqgVar == null) {
            return;
        }
        if (z) {
            nqgVar.e(R.color._2131296613_res_0x7f090165, R.color._2131296609_res_0x7f090161, R.color._2131296611_res_0x7f090163);
        } else {
            nqgVar.e(R.color._2131296612_res_0x7f090164, R.color._2131296608_res_0x7f090160, R.color._2131296610_res_0x7f090162);
        }
        nqgVar.c(a(z ? null : qtsVar));
        nqgVar.e(a(qtsVar, ((Integer) Yw_(qtsVar).second).intValue()));
        Pair<Float, Float> Yx_ = Yx_(qtsVar);
        if (Yx_ == null) {
            nqgVar.b(0.0f, 0.0f);
            nqgVar.d("");
        } else {
            nqgVar.b(((Float) Yx_.first).floatValue(), ((Float) Yx_.second).floatValue());
            nqgVar.d(nsf.h(R$string.IDS_weight_heat_recording_target));
        }
    }

    public static void d(HealthCircleScaleRuler healthCircleScaleRuler, qts qtsVar, boolean z) {
        if (healthCircleScaleRuler == null) {
            ReleaseLogUtil.d("DietRecordHelper", "refreshHealthCircleScaleRuler scaleRuler is null overview ", qtsVar, " isFuture ", Boolean.valueOf(z));
            return;
        }
        healthCircleScaleRuler.setTitle(nsf.h(qtsVar.j() < 0.0d ? R$string.IDS_weight_heat_surpluses : R$string.IDS_weight_heat_gap));
        if (z) {
            qtsVar = null;
        }
        healthCircleScaleRuler.setValue(UnitUtil.e(b(qtsVar), 1, 0));
        if (LanguageUtil.b(BaseApplication.e())) {
            return;
        }
        healthCircleScaleRuler.setValueTextDirection(3);
    }

    public static void a(HealthTextView healthTextView, qts qtsVar, boolean z) {
        if (qtsVar == null) {
            ReleaseLogUtil.d("DietRecordHelper", "refreshConsumption overview is null isFuture ", Boolean.valueOf(z), " textView ", healthTextView);
            return;
        }
        int e = e(qtsVar);
        int i = 0;
        if (z) {
            e = 0;
        }
        double j = qtsVar.j();
        if (j >= 0.0d) {
            if (j == 0.0d) {
                i = (int) UnitUtil.a(qtsVar.c(), 0);
            } else {
                i = qtsVar.b() + ((int) UnitUtil.a(qtsVar.i(), 0));
            }
        }
        c(healthTextView, e, i);
    }

    public static void b(HealthTextView healthTextView, qts qtsVar, boolean z) {
        if (qtsVar == null) {
            ReleaseLogUtil.d("DietRecordHelper", "refreshIntake overview is null isFuture ", Boolean.valueOf(z), " textView ", healthTextView);
            return;
        }
        int a2 = (int) UnitUtil.a(qtsVar.f(), 0);
        if (z) {
            a2 = 0;
        }
        c(healthTextView, a2, qtsVar.j() >= 0.0d ? (int) UnitUtil.a(qtsVar.c(), 0) : 0);
    }

    private static void c(HealthTextView healthTextView, int i, int i2) {
        String a2;
        if (healthTextView == null) {
            ReleaseLogUtil.d("DietRecordHelper", "refreshTextView textView is null value ", Integer.valueOf(i), " goal ", Integer.valueOf(i2));
            return;
        }
        String e = UnitUtil.e(i, 1, 0);
        if (i2 > 0) {
            a2 = nsf.a(R.plurals._2130903354_res_0x7f03013a, i2, e, UnitUtil.e(i2, 1, 0));
        } else {
            a2 = nsf.a(R.plurals._2130903380_res_0x7f030154, i, e);
        }
        SpannableString spannableString = new SpannableString(a2);
        nsi.cKI_(spannableString, e, R.color._2131299236_res_0x7f090ba4);
        nsi.cKL_(spannableString, e, R$string.textFontFamilyMedium);
        nsi.cKJ_(spannableString, e, nsf.b(R.dimen._2131362937_res_0x7f0a0479));
        healthTextView.setText(spannableString);
    }

    public static void a(HealthBubbleLayout healthBubbleLayout, int i, HealthTextView healthTextView, HealthTextView healthTextView2, qts qtsVar) {
        if (qtsVar == null) {
            ReleaseLogUtil.d("R_DietRecordHelper", "consumptionOnClick overview is null");
            return;
        }
        if (healthBubbleLayout == null || healthTextView == null || healthTextView2 == null) {
            ReleaseLogUtil.d("R_DietRecordHelper", "consumptionOnClick bubbleLayout ", healthBubbleLayout, " sportText ", healthTextView, " restingText ", healthTextView2, " overview ", qtsVar);
            return;
        }
        if (healthBubbleLayout.getVisibility() == 0) {
            healthBubbleLayout.setVisibility(8);
            return;
        }
        healthBubbleLayout.setVisibility(0);
        healthBubbleLayout.setArrowPositionCenter(false);
        healthBubbleLayout.setArrowPosition(i - nsf.b(R.dimen._2131363094_res_0x7f0a0516));
        int a2 = (int) UnitUtil.a(qtsVar.e(), 0);
        int h = qtsVar.h();
        healthTextView.setText(nsf.b(R$string.IDS_h5_weight_sport_consumption_splicing, nsf.a(R.plurals._2130903380_res_0x7f030154, a2, UnitUtil.e(a2, 1, 0))));
        healthTextView2.setText(nsf.b(R$string.IDS_h5_weight_resting_consumption_splicing, nsf.a(R.plurals._2130903380_res_0x7f030154, h, UnitUtil.e(h, 1, 0))));
        ReleaseLogUtil.e("R_DietRecordHelper", "consumptionOnClick offset ", Integer.valueOf(i), " consumption ", Integer.valueOf(a2), " restingCaloriesBurned ", Integer.valueOf(h));
    }

    public static void c(nqg nqgVar, boolean z) {
        int i;
        if (nqgVar == null) {
            ReleaseLogUtil.d("DietRecordHelper", "initCircleScaleRulerData circleScaleRulerData is null");
            return;
        }
        boolean a2 = nrt.a(BaseApplication.e());
        ReleaseLogUtil.e("DietRecordHelper", "initCircleScaleRulerData isDarkMode ", Boolean.valueOf(a2), " isWeightCard ", Boolean.valueOf(z));
        int i2 = z ? R.drawable._2131430612_res_0x7f0b0cd4 : R.drawable._2131430610_res_0x7f0b0cd2;
        if (a2) {
            i2 = z ? R.drawable._2131430613_res_0x7f0b0cd5 : R.drawable._2131430611_res_0x7f0b0cd3;
            i = R.drawable.circle_scale_ruler_background_dark;
        } else {
            i = R.drawable.circle_scale_ruler_background;
        }
        nqgVar.cFA_(nsf.cKq_(i));
        nqgVar.cFB_(nsf.cKq_(i2));
        nqgVar.a(d());
    }
}
