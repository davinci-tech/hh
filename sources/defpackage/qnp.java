package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.network.embedded.k;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.SleepDailyProcessResultCallback;
import com.huawei.hwbasemgr.SleepMonthlyProcessResultCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginsleeplabel.SleepLabelApi;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBreatheBannerProvider;
import defpackage.qnp;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class qnp {
    private static final int[] f = {44101, 44102, 44103, 44104, 44105, 44106, 44201, 44205, 44202, 44207, 44204, 44203, 44208, 44107, 44108, 46020, 46018, 46016, 44306, 44001, 44002, 44003, 44005, 44004, 44109, 44216, 44110, 44218, 44217, 44219, 44220, 44221, 44227, 44228, 44229, 44223, 44224, 44225, 44231, 44232, 44233};
    private static final String[] d = {"core_sleep_dream_key", "core_sleep_deep_key", "core_sleep_shallow_key", "core_sleep_wake_key", "core_sleep_sum_key", "core_sleep_deep_count_key", "core_sleep_fall_time_key", "core_sleep_go_bed_time_key", "core_sleep_wake_up_time_key", "core_sleep_efficiency_key", "core_sleep_latency_key", "core_sleep_score_key", "core_sleep_snore_freq_key", "core_sleep_wake_count_key", "core_sleep_noon_sleep_count_key", "rest_heart_rate_old", "rest_heart_rate_new", "max_heart_rate", "stress_day_avg", "sleep_deep_key", "sleep_shallow_key", "sleep_wake_key", "sleep_wake_count_key", "common_sleep_duration_sleep_sum_key", "data_session_manual_sleep_bed_time_key", "core_sleep_off_bed_time_key", "sleep_device_category_key", "core_sleep_latency_new_key", "core_sleep_bed_duration_key", "avgHeartrate", "minHeartrateBaseline", "maxHeartrateBaseline", "avgBreathrate", "minBreathrateBaseline", "maxBreathrateBaseline", "avgOxygenSaturation", "minOxygenSaturationBaseline", "maxOxygenSaturationBaseline", "avgHrv", "minHrvBaseline", "maxHrvBaseline"};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f16497a = {"bedTime", "risingTime", "latency"};
    private static final int[] e = {DicDataTypeUtil.DataType.BED_TIME.value(), DicDataTypeUtil.DataType.RISING_TIME.value(), DicDataTypeUtil.DataType.DAILY_SLEEP_LATENCY.value()};
    private static final int[] b = {DicDataTypeUtil.DataType.RHYTHM_TYPE.value(), DicDataTypeUtil.DataType.MONTHLY_SLEEP_PROBLEM.value(), DicDataTypeUtil.DataType.MONTHLY_SLEEP_DEGREE.value(), DicDataTypeUtil.DataType.MONTHLY_SLEEP_INTERPRET.value(), DicDataTypeUtil.DataType.MONTHLY_SLEEP_TASKS.value()};
    private static final int[] c = {DicDataTypeUtil.DataType.DAILY_SLEEP_PROBLEM.value()};

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(int i2) {
        int i3 = 1;
        if (i2 == 1) {
            return 2;
        }
        if (i2 != 2) {
            if (i2 == 3) {
                return 3;
            }
            i3 = 4;
            if (i2 == 4) {
                return 3;
            }
            if (i2 != 5) {
                return 0;
            }
        }
        return i3;
    }

    private static int b(int i2, int i3, int i4) {
        if (i2 < i3) {
            return 3;
        }
        return i2 > i4 ? 1 : 2;
    }

    private static boolean c(int i2) {
        return i2 <= 0 || i2 > 255;
    }

    private static boolean d(int i2) {
        return i2 <= 0 || i2 > 200;
    }

    private static boolean e(int i2) {
        return i2 <= 0 || i2 > 80;
    }

    private static boolean f(int i2) {
        return i2 <= 0 || i2 > 100;
    }

    public static void b(long j2, SleepDailyProcessResultCallback<fda> sleepDailyProcessResultCallback) {
        c(j2, sleepDailyProcessResultCallback);
    }

    public static void a(final fcz fczVar, final fcw fcwVar, final long j2, final long j3, final SleepMonthlyProcessResultCallback<fdh> sleepMonthlyProcessResultCallback) {
        jdx.b(new Runnable() { // from class: qnp.2
            @Override // java.lang.Runnable
            public void run() {
                qnp.e(fcz.this, fcwVar, j2, j3, (SleepMonthlyProcessResultCallback<fdh>) sleepMonthlyProcessResultCallback);
            }
        });
    }

    private static fdd a(fcz fczVar, fcw fcwVar, long j2, long j3) {
        fdd fddVar;
        if (fcwVar != null) {
            fdg fdgVar = new fdg();
            fdgVar.e(fcwVar.d());
            fdgVar.d(fcwVar.b());
            fdgVar.b(fcwVar.c());
            fdgVar.c(fcwVar.a());
            LogUtil.d("SleepProcessResultUtil", "SleepMonthlyProcessBean: ", fdgVar.toString());
            fddVar = fdgVar;
        } else {
            fddVar = c(j2, j3);
        }
        if (fczVar != null) {
            fddVar.c(fczVar.c());
        }
        return fddVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(fcz fczVar, fcw fcwVar, long j2, long j3, SleepMonthlyProcessResultCallback<fdh> sleepMonthlyProcessResultCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.d("SleepProcessResultUtil", "start executeMonthlyProcess startTime is ", Long.valueOf(j2), " endtime is ", Long.valueOf(j3));
        fdd a2 = a(fczVar, fcwVar, j2, j3);
        Gson create = new GsonBuilder().create();
        String json = create.toJson(a2);
        LogUtil.d("SleepProcessResultUtil", "finish executeMonthlyProcess time is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        LogUtil.d("SleepProcessResultUtil", "monnth countdown ok, json is ", json);
        try {
            long currentTimeMillis2 = System.currentTimeMillis();
            String monthlySleepLabel = ((SleepLabelApi) Services.c("SleepLabelAlgorithm", SleepLabelApi.class)).getMonthlySleepLabel(json);
            LogUtil.d("SleepProcessResultUtil", "month result back execute time is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
            LogUtil.d("SleepProcessResultUtil", "month result back ", monthlySleepLabel);
            fdh fdhVar = (fdh) create.fromJson(monthlySleepLabel, fdh.class);
            if (fdhVar.l() == 0) {
                sleepMonthlyProcessResultCallback.onResponse(0, fdhVar);
            } else {
                sleepMonthlyProcessResultCallback.onResponse(0, null);
            }
        } catch (Exception e2) {
            LogUtil.e("SleepProcessResultUtil", "get result failed check in json, e: ", ExceptionUtils.d(e2));
            sleepMonthlyProcessResultCallback.onResponse(1, null);
        }
    }

    private static fdd c(long j2, long j3) {
        int i2 = (int) (((j3 + 1000) - j2) / 86400000);
        fdd fddVar = new fdd();
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(new fdb());
        }
        fdf fdfVar = new fdf();
        CountDownLatch countDownLatch = new CountDownLatch((i2 * 3) + 3);
        e(j2, j3, fdfVar, countDownLatch, false);
        c(j2, j3, countDownLatch, arrayList, i2);
        d(j2, j3, countDownLatch, arrayList);
        try {
            LogUtil.d("SleepProcessResultUtil", "getSleepMonthlyProcessBean await: ", Boolean.valueOf(countDownLatch.await(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e2) {
            LogUtil.e("SleepProcessResultUtil", "getSleepMonthlyProcessBean exception: ", ExceptionUtils.d(e2));
        }
        CountDownLatch countDownLatch2 = new CountDownLatch(3);
        a(j2, j3, countDownLatch2, arrayList, i2);
        e(j2, j3, countDownLatch2, arrayList, i2);
        if (arrayList.get(0).d() == 0) {
            countDownLatch2.countDown();
        } else {
            a(countDownLatch2, arrayList.get(0), arrayList.get(0).d());
        }
        try {
            countDownLatch2.await(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e3) {
            LogUtil.e("SleepProcessResultUtil", "getSleepMonthlyProcessBean exception: ", ExceptionUtils.d(e3));
        }
        LogUtil.d("SleepProcessResultUtil", "countdown ok");
        e(arrayList);
        fddVar.e(arrayList);
        fddVar.e(fdfVar);
        return fddVar;
    }

    private static void e(long j2, long j3, CountDownLatch countDownLatch, List<fdb> list, int i2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long j4 = j2 - 86400000;
        hiDataReadOption.setTimeInterval(j4, j3 + 999);
        hiDataReadOption.setType(new int[]{7});
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new d(j4, countDownLatch, list, i2));
    }

    private static void c(long j2, long j3, CountDownLatch countDownLatch, List<fdb> list, int i2) {
        kor.a().c((j2 / 1000) - 14400, 3, 1, i2 * ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, (IBaseResponseCallback) new j(j2, j3, countDownLatch, list, i2));
    }

    private static void c(long j2, SleepDailyProcessResultCallback<fda> sleepDailyProcessResultCallback) {
        LogUtil.d("SleepProcessResultUtil", "start getDailyProcessResult startTime is ", Long.valueOf(j2));
        long currentTimeMillis = System.currentTimeMillis();
        fdd fddVar = new fdd();
        fdb fdbVar = new fdb();
        fdf fdfVar = new fdf();
        CountDownLatch countDownLatch = new CountDownLatch(7);
        e(j2, System.currentTimeMillis(), fdfVar, countDownLatch, true);
        c(j2, countDownLatch, fdbVar);
        d(j2, countDownLatch, fdbVar);
        b(j2, countDownLatch, fdbVar);
        try {
            countDownLatch.await();
        } catch (InterruptedException e2) {
            LogUtil.e("SleepProcessResultUtil", "countDownLatch1 executeDailyProcess exception: ", ExceptionUtils.d(e2));
        }
        long d2 = fdbVar.d();
        if (d2 == 0) {
            LogUtil.e("SleepProcessResultUtil", "No awake Time return!");
            sleepDailyProcessResultCallback.onResponse(1, null);
            return;
        }
        LogUtil.d("SleepProcessResultUtil", "lastAwakeTime is ", Long.valueOf(d2));
        CountDownLatch countDownLatch2 = new CountDownLatch(3);
        c(countDownLatch2, fdbVar, d2);
        b(countDownLatch2, fdbVar, d2);
        a(countDownLatch2, fdbVar, d2);
        try {
            countDownLatch2.await();
        } catch (InterruptedException e3) {
            LogUtil.e("SleepProcessResultUtil", "countDownLatch2 executeDailyProcess exception: ", ExceptionUtils.d(e3));
        }
        fdbVar.b(System.currentTimeMillis() / 1000);
        LogUtil.d("SleepProcessResultUtil", "countdown ok, sleep info is ", fdbVar.toString());
        ArrayList arrayList = new ArrayList();
        arrayList.add(fdbVar);
        e(arrayList);
        fddVar.e(arrayList);
        fddVar.e(fdfVar);
        LogUtil.d("SleepProcessResultUtil", "execute daily ok cost time is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        Gson create = new GsonBuilder().create();
        String json = create.toJson(fddVar);
        LogUtil.d("SleepProcessResultUtil", "countdown ok, json is ", json);
        try {
            long currentTimeMillis2 = System.currentTimeMillis();
            String dailySleepLabel = ((SleepLabelApi) Services.c("SleepLabelAlgorithm", SleepLabelApi.class)).getDailySleepLabel(json);
            LogUtil.d("SleepProcessResultUtil", "result back execute time is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
            LogUtil.d("SleepProcessResultUtil", "result back ", dailySleepLabel);
            fda fdaVar = (fda) create.fromJson(dailySleepLabel, fda.class);
            if (fdaVar.k() == 0) {
                sleepDailyProcessResultCallback.onResponse(0, fdaVar);
            } else {
                sleepDailyProcessResultCallback.onResponse(0, null);
            }
        } catch (Exception e4) {
            LogUtil.e("SleepProcessResultUtil", "get result failed check in json, e: ", ExceptionUtils.d(e4));
            sleepDailyProcessResultCallback.onResponse(1, null);
        }
    }

    private static void e(List<fdb> list) {
        for (fdb fdbVar : list) {
            if (fdbVar.e() == 4 && fdbVar.c() != 0 && fdbVar.b() != 0) {
                int a2 = (int) ((pxb.a(fdbVar.i() / 60) * 0.5f) + (pxb.a(r1) * 0.3f) + (pxb.d(r2) * 0.2f));
                LogUtil.d("SleepProcessResultUtil", "manual sleep need recalculate score sleepEfficiency is ", Float.valueOf(fdbVar.i() / fdbVar.b()), " manualFallTime is ", Integer.valueOf((int) ((fdbVar.c() - fdbVar.a()) / 60)), " getTotalDuration is ", Integer.valueOf(fdbVar.i()), " manualscore is ", Integer.valueOf(a2));
                fdbVar.k(a2);
            }
            if (fdbVar.e() == 3) {
                fdbVar.x(0);
            }
        }
    }

    private static void a(CountDownLatch countDownLatch, fdb fdbVar, long j2) {
        kor.a().c((((j2 * 1000) - 86400000) / 1000) + 60, 3, 1, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, (IBaseResponseCallback) new b(countDownLatch, fdbVar, j2));
    }

    private static void e(long j2, long j3, fdf fdfVar, CountDownLatch countDownLatch, boolean z) {
        int i2;
        String gender = SharedPreferenceUtil.getInstance(com.huawei.haf.application.BaseApplication.e()).getGender();
        if ("1".equals(gender)) {
            i2 = 0;
        } else {
            i2 = "0".equals(gender) ? 1 : -1;
        }
        int b2 = b(SharedPreferenceUtil.getInstance(com.huawei.haf.application.BaseApplication.e()).getBirthDate());
        fdfVar.d(i2);
        fdfVar.c(b2);
        String q = jdl.q(System.currentTimeMillis());
        boolean startsWith = q.startsWith(Constants.LINK);
        int h2 = CommonUtil.h(q.substring(2, 3));
        if (startsWith) {
            fdfVar.b(-h2);
        } else {
            fdfVar.b(h2);
        }
        d(j2, j3, fdfVar, countDownLatch, z);
        d(j2, fdfVar, countDownLatch);
    }

    private static void d(long j2, final fdf fdfVar, final CountDownLatch countDownLatch) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(c);
        final long j3 = j2 - 604800000;
        hiDataReadOption.setStartTime(j3);
        hiDataReadOption.setEndTime(j2 - 1);
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: qnp.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                LogUtil.d("SleepProcessResultUtil", "getLatestProblems back", Integer.valueOf(i2));
                if (i2 != 0) {
                    LogUtil.d("SleepProcessResultUtil", "getLatestProblems errorCode: ", Integer.valueOf(i2));
                    countDownLatch.countDown();
                    return;
                }
                if (!(obj instanceof SparseArray)) {
                    LogUtil.d("SleepProcessResultUtil", "getLatestProblems no data");
                    countDownLatch.countDown();
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.c("SleepProcessResultUtil", "getLatestProblems map.size() <= 0");
                    countDownLatch.countDown();
                    return;
                }
                ArrayList arrayList = new ArrayList();
                long j4 = j3;
                List list = (List) sparseArray.get(DicDataTypeUtil.DataType.DAILY_SLEEP_PROBLEM.value());
                if (list.size() > 7) {
                    LogUtil.c("SleepProcessResultUtil", "getLatestProblems a day has multi problems");
                    countDownLatch.countDown();
                    return;
                }
                for (int size = list.size() - 1; size >= 0; size--) {
                    HiHealthData hiHealthData = (HiHealthData) list.get(size);
                    if (hiHealthData.getStartTime() >= j4 && hiHealthData.getStartTime() < j4 + 86400000) {
                        arrayList.add(Integer.valueOf(hiHealthData.getIntValue()));
                    } else {
                        arrayList.add(0);
                    }
                    j4 += 86400000;
                }
                fdfVar.b(arrayList);
                countDownLatch.countDown();
            }
        });
    }

    private static void d(final long j2, long j3, final fdf fdfVar, final CountDownLatch countDownLatch, final boolean z) {
        long s;
        long a2;
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(b);
        if (z) {
            s = jdl.u(j2);
            a2 = jdl.f(j2);
        } else {
            s = jdl.s(j2);
            a2 = jdl.a(j2);
        }
        hiDataReadOption.setStartTime(s);
        hiDataReadOption.setEndTime(a2);
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: qnp.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                try {
                    qnp.d(obj, i2, countDownLatch, fdfVar, z, j2);
                } catch (Exception e2) {
                    LogUtil.e("SleepProcessResultUtil", "getProblems back failed json wrong ", ExceptionUtils.d(e2));
                    countDownLatch.countDown();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Object obj, int i2, CountDownLatch countDownLatch, fdf fdfVar, boolean z, long j2) {
        LogUtil.d("SleepProcessResultUtil", "getProblems back", Integer.valueOf(i2));
        if (i2 != 0) {
            LogUtil.d("SleepProcessResultUtil", "getProblems errorCode is not zero ", Integer.valueOf(i2));
            countDownLatch.countDown();
            return;
        }
        if (!(obj instanceof SparseArray)) {
            LogUtil.d("SleepProcessResultUtil", "getProblems no data");
            countDownLatch.countDown();
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            LogUtil.d("SleepProcessResultUtil", "getProblems map.size() <= 0");
            countDownLatch.countDown();
            return;
        }
        fdfVar.a(pob.drh_(sparseArray, DicDataTypeUtil.DataType.RHYTHM_TYPE.value()));
        fdfVar.e(pob.drh_(sparseArray, DicDataTypeUtil.DataType.MONTHLY_SLEEP_PROBLEM.value()));
        if (!z) {
            countDownLatch.countDown();
            return;
        }
        Gson create = new GsonBuilder().create();
        String dri_ = pob.dri_(sparseArray, DicDataTypeUtil.DataType.MONTHLY_SLEEP_INTERPRET.value());
        String dri_2 = pob.dri_(sparseArray, DicDataTypeUtil.DataType.MONTHLY_SLEEP_TASKS.value());
        fcs fcsVar = (fcs) create.fromJson(dri_, fcs.class);
        fcs fcsVar2 = (fcs) create.fromJson(dri_2, fcs.class);
        List<fcp> d2 = fcsVar.d();
        List<List<fcx>> c2 = fcsVar2.c();
        Date date = new Date();
        date.setTime(j2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i3 = calendar.get(5) - 1;
        if (koq.d(d2, i3)) {
            ArrayList arrayList = new ArrayList();
            fcp fcpVar = d2.get(i3);
            fcf fcfVar = new fcf();
            fcfVar.e(fcpVar.a());
            fcfVar.d(fcpVar.d());
            fcfVar.d(fcpVar.b());
            arrayList.add(fcfVar);
            fdfVar.c(arrayList);
        }
        if (koq.d(c2, i3)) {
            ArrayList arrayList2 = new ArrayList();
            for (fcx fcxVar : c2.get(i3)) {
                fde fdeVar = new fde();
                fcr fcrVar = new fcr();
                fcq a2 = fcxVar.a();
                fcrVar.a(a2.b());
                fcrVar.b(a2.d());
                fdeVar.b(fcrVar);
                fdeVar.b(fcxVar.b());
                fdeVar.c(fcxVar.c());
                fdeVar.b(fcxVar.d());
                arrayList2.add(fdeVar);
            }
            fdfVar.a(arrayList2);
        }
        countDownLatch.countDown();
    }

    private static int b(String str) {
        try {
            LogUtil.d("SleepProcessResultUtil", "getAge birthdayString is ", str);
            Date parse = new SimpleDateFormat("YYYYMMDD").parse(str);
            Calendar calendar = Calendar.getInstance();
            if (calendar.before(parse)) {
                return 0;
            }
            int i2 = calendar.get(1);
            int i3 = calendar.get(2);
            int i4 = calendar.get(5);
            calendar.setTime(parse);
            int i5 = calendar.get(1);
            int i6 = calendar.get(2);
            int i7 = i2 - i5;
            return i3 <= i6 ? (i3 != i6 || i4 < calendar.get(5)) ? i7 - 1 : i7 : i7;
        } catch (Exception e2) {
            LogUtil.e("SleepProcessResultUtil", "getAge failed, e: ", ExceptionUtils.d(e2));
            return 0;
        }
    }

    private static void b(CountDownLatch countDownLatch, fdb fdbVar, long j2) {
        long j3 = j2 * 1000;
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j3 - 86399999, j3);
        hiDataReadOption.setType(new int[]{7});
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new e(countDownLatch, fdbVar));
    }

    private static void c(CountDownLatch countDownLatch, fdb fdbVar, long j2) {
        long j3 = j2 * 1000;
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j3 - 86399999, j3);
        hiDataReadOption.setType(new int[]{2002});
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new c(countDownLatch, fdbVar));
    }

    private static void a(long j2, long j3, CountDownLatch countDownLatch, List<fdb> list, int i2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long j4 = j2 - 86400000;
        hiDataReadOption.setTimeInterval(j4, j3 + 999);
        hiDataReadOption.setType(new int[]{2002});
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new h(j4, countDownLatch, list, i2));
    }

    private static void d(long j2, CountDownLatch countDownLatch, fdb fdbVar) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j2 - 86400000);
        hiAggregateOption.setEndTime(86399999 + j2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(d);
        hiAggregateOption.setType(f);
        hiAggregateOption.setReadType(0);
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new n(j2, countDownLatch, fdbVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final long j2, final CountDownLatch countDownLatch, final fdb fdbVar) {
        if (!drl.c("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.sleep-apnea")) {
            ReleaseLogUtil.e("R_Sleep_SleepProcessResultUtil", "is not Support Osa");
            countDownLatch.countDown();
        } else {
            if (HandlerExecutor.c()) {
                jdx.b(new Runnable() { // from class: qnn
                    @Override // java.lang.Runnable
                    public final void run() {
                        qnp.b(j2, countDownLatch, fdbVar);
                    }
                });
                return;
            }
            com.huawei.hwlogsmodel.LogUtil.a("SleepProcessResultUtil", "start OsaData");
            HiAggregateOption e2 = SleepBreatheBannerProvider.e();
            e2.setCount(5);
            e2.setTimeInterval(DateFormatUtil.a(jdl.e(jdl.b(j2, -1), 20, 0), DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), DateFormatUtil.a(jdl.e(j2, 20, 0) - 1, DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), HiDataReadOption.TimeFormatType.DATE_FORMAT_MILLISECONDS);
            HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).aggregateHiHealthData(e2, new g(j2, countDownLatch, fdbVar));
        }
    }

    private static void d(long j2, long j3, CountDownLatch countDownLatch, List<fdb> list) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j2 - 86400000);
        hiAggregateOption.setEndTime(999 + j3);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(d);
        hiAggregateOption.setType(f);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(0);
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new f(j2, j3, countDownLatch, list));
    }

    private static void c(long j2, CountDownLatch countDownLatch, fdb fdbVar) {
        kor.a().c((j2 / 1000) - 14400, 3, 1, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, (IBaseResponseCallback) new a(j2, countDownLatch, fdbVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(SleepTotalData sleepTotalData) {
        return sleepTotalData.getManualOnTime() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long e(List<SleepTotalData> list, long j2, long j3) {
        long j4;
        int i2 = 0;
        while (true) {
            if (i2 >= list.size()) {
                j4 = j3;
                break;
            }
            if (b(list.get(i2))) {
                j4 = b(i2, j2);
                break;
            }
            i2++;
        }
        return j4 > j3 ? j3 : j4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long d(List<SleepTotalData> list, long j2, long j3) {
        long j4;
        int size = list.size() - 1;
        while (true) {
            if (size < 0) {
                j4 = j3;
                break;
            }
            if (b(list.get(size))) {
                j4 = b(size, j2) + 60000;
                break;
            }
            size--;
        }
        return j4 < j3 ? j3 : j4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long b(int i2, long j2) {
        Date date = new Date((j2 - 14400000) + (i2 * 60 * 1000));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(SleepTotalData sleepTotalData, List<Long> list, List<Integer> list2, long j2) {
        List<Map<String, Long>> noonMapList = sleepTotalData.getNoonMapList();
        if (noonMapList == null || noonMapList.size() == 0) {
            LogUtil.d("SleepProcessResultUtil", "noonMapList is null");
            return;
        }
        for (Map<String, Long> map : noonMapList) {
            Long l = map.get("core_sleep_start_time_key");
            if (l == null) {
                LogUtil.d("SleepProcessResultUtil", "setNoonSleepData start is null");
            } else {
                Long l2 = map.get("core_sleep_end_time_key");
                if (l2 == null) {
                    LogUtil.d("SleepProcessResultUtil", "setNoonSleepData end is null");
                } else {
                    long longValue = l.longValue();
                    long longValue2 = l2.longValue();
                    if (longValue <= j2 && longValue2 >= j2 - 86400000) {
                        list.add(Long.valueOf(longValue / 1000));
                        list2.add(Integer.valueOf((int) ((longValue2 - longValue) / 1000)));
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean a(SleepTotalData sleepTotalData) {
        return sleepTotalData.getManualOnTime() == 1 || sleepTotalData.getDeepSleepTime() == 1 || sleepTotalData.getShallowSleepTime() == 1 || sleepTotalData.getSlumberSleepTime() == 1;
    }

    private static boolean b(SleepTotalData sleepTotalData) {
        return sleepTotalData.getManualBedTime() == 1;
    }

    public static void b(final long j2, final long j3, final IBaseResponseCallback iBaseResponseCallback) {
        jdx.b(new Runnable() { // from class: qnp.3
            @Override // java.lang.Runnable
            public void run() {
                qnp.c(j2, j3, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(long j2, long j3, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.d("SleepProcessResultUtil", "start executeQuestionnaireProcess startTime is ", Long.valueOf(j2), " endtime is ", Long.valueOf(j3));
        fdd c2 = c(j2, j3);
        Gson create = new GsonBuilder().create();
        String json = create.toJson(c2);
        LogUtil.d("SleepProcessResultUtil", "countdown ok, json is ", json);
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String questionnaireAnalysis = ((SleepLabelApi) Services.c("SleepLabelAlgorithm", SleepLabelApi.class)).getQuestionnaireAnalysis(json);
            LogUtil.d("SleepProcessResultUtil", "Questionnaire result back execute time is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            LogUtil.d("SleepProcessResultUtil", "Questionnaire result back ", questionnaireAnalysis);
            fdc fdcVar = (fdc) create.fromJson(questionnaireAnalysis, fdc.class);
            if (fdcVar.d() == 0) {
                iBaseResponseCallback.d(0, fdcVar);
            } else {
                iBaseResponseCallback.d(0, null);
            }
        } catch (Exception e2) {
            LogUtil.e("SleepProcessResultUtil", "get result failed check in json, e: ", ExceptionUtils.d(e2));
            iBaseResponseCallback.d(1, null);
        }
    }

    static class j implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private int f16506a;
        private CountDownLatch b;
        private long c;
        private List<fdb> d;
        private long e;
        private long f;
        private String j;

        j(long j, long j2, CountDownLatch countDownLatch, List<fdb> list, int i) {
            this.f = j;
            this.e = j2;
            this.b = countDownLatch;
            this.d = list;
            this.f16506a = i;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            int i2;
            ArrayList arrayList;
            String str;
            List list;
            long j;
            int i3;
            boolean z;
            String str2;
            String str3;
            long j2;
            long j3;
            int i4;
            long j4;
            long j5;
            fdb fdbVar;
            SleepTotalData sleepTotalData;
            int i5;
            long j6;
            int i6;
            Object obj2;
            final fdb fdbVar2;
            String str4;
            long j7;
            j jVar = this;
            String str5 = "SleepProcessResultUtil";
            LogUtil.d("SleepProcessResultUtil", "Month DetailSleepResponseCallback response!");
            int i7 = 3;
            int i8 = 0;
            if (i != 0 || !(obj instanceof List)) {
                for (int i9 = 0; i9 < jVar.f16506a * 3; i9++) {
                    jVar.b.countDown();
                }
                LogUtil.d("SleepProcessResultUtil", "get day detail error or null, error code is ", Integer.valueOf(i));
                return;
            }
            List list2 = (List) obj;
            com.huawei.hwlogsmodel.LogUtil.a("SleepProcessResultUtil", " handleDayData retListData.size = ", Integer.valueOf(list2.size()));
            if (list2.isEmpty()) {
                while (i8 < jVar.f16506a * 3) {
                    jVar.b.countDown();
                    i8++;
                }
                LogUtil.d("SleepProcessResultUtil", "get day detail retListData.isEmpty()");
                return;
            }
            SleepTotalData sleepTotalData2 = (SleepTotalData) list2.get(0);
            int i10 = 0;
            while (i10 < jVar.f16506a) {
                ArrayList arrayList2 = new ArrayList();
                int i11 = i10 * ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
                while (true) {
                    i2 = i10 + 1;
                    if (i11 >= i2 * ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL) {
                        break;
                    }
                    arrayList2.add((SleepTotalData) list2.get(i11));
                    i11++;
                }
                long j8 = (i10 * 86400000) + jVar.f;
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                int i12 = i8;
                while (true) {
                    if (i12 >= arrayList2.size()) {
                        arrayList = arrayList4;
                        str = "";
                        list = list2;
                        j = 0;
                        i3 = 0;
                        z = false;
                        break;
                    }
                    SleepTotalData sleepTotalData3 = (SleepTotalData) arrayList2.get(i12);
                    if (qnp.a(sleepTotalData3)) {
                        jVar.j = sleepTotalData3.getTimeZone();
                        jVar.c = j8;
                        z = qnp.c(sleepTotalData3);
                        long b = qnp.b(i12, j8);
                        String deviceId = sleepTotalData3.getDeviceType() == i7 ? sleepTotalData3.getDeviceId() : "";
                        arrayList = arrayList4;
                        if (sleepTotalData3.getNoonSleepTime() != 1) {
                            int b2 = qnp.b(sleepTotalData3.getDeviceType());
                            list = list2;
                            j = b;
                            str = deviceId;
                            i3 = b2;
                        } else {
                            str = deviceId;
                            list = list2;
                            j = b;
                            i3 = 0;
                        }
                    } else {
                        i12++;
                        i7 = 3;
                    }
                }
                int size = arrayList2.size() - 1;
                while (true) {
                    if (size < 0) {
                        str2 = str5;
                        str3 = str;
                        j2 = 0;
                        break;
                    } else {
                        if (qnp.a((SleepTotalData) arrayList2.get(size))) {
                            str2 = str5;
                            str3 = str;
                            j2 = qnp.b(size, j8) + 60000;
                            break;
                        }
                        size--;
                    }
                }
                fdb fdbVar3 = jVar.d.get(i10);
                if (z) {
                    j5 = qnp.e(arrayList2, j8, j);
                    long d = qnp.d(arrayList2, j8, j2);
                    j3 = j8;
                    i4 = i3;
                    fdbVar3.p(qnp.e(j, j5, jVar.j, jVar.c) * 60);
                    fdbVar3.e(j5 / 1000);
                    fdbVar3.c(d / 1000);
                    j4 = d;
                } else {
                    j3 = j8;
                    i4 = i3;
                    j4 = 0;
                    j5 = 0;
                }
                if (j2 != 0) {
                    SleepTotalData sleepTotalData4 = sleepTotalData2;
                    sleepTotalData = sleepTotalData2;
                    i6 = i4;
                    j6 = j3;
                    i5 = i2;
                    obj2 = "";
                    fdbVar = fdbVar3;
                    b(sleepTotalData4, j2 - 86400000, j2, arrayList3, arrayList);
                } else {
                    fdbVar = fdbVar3;
                    sleepTotalData = sleepTotalData2;
                    i5 = i2;
                    j6 = j3;
                    i6 = i4;
                    obj2 = "";
                }
                fdbVar.f(arrayList3);
                ArrayList arrayList5 = arrayList;
                fdbVar.c(arrayList5);
                fdbVar.a(j / 1000);
                fdbVar.d(j2 / 1000);
                fdbVar.a(i6);
                fdbVar.b(System.currentTimeMillis() / 1000);
                String str6 = str3;
                if (!str6.equals(obj2)) {
                    fdbVar2 = fdbVar;
                    str4 = str2;
                    jVar = this;
                    j7 = j6;
                    qnp.c(str6, fdbVar2, jVar.b, j7);
                    qnp.b(str6, fdbVar2, jVar.b, j7);
                } else {
                    str4 = str2;
                    LogUtil.d(str4, "startSleepTime is ", Long.valueOf(j), ",endSleepTime is ", Long.valueOf(j2), ",startBedTime is ", Long.valueOf(j5), ",endBedTime is ", Long.valueOf(j4));
                    LogUtil.d(str4, "noonstartList is ", arrayList3, ", noontimelist is ", arrayList5);
                    fdbVar2 = fdbVar;
                    jVar = this;
                    jVar.b.countDown();
                    jVar.b.countDown();
                    j7 = j6;
                }
                if (3 == i6) {
                    final long j9 = j7;
                    final long j10 = j;
                    ThreadPoolManager.d().execute(new Runnable() { // from class: qno
                        @Override // java.lang.Runnable
                        public final void run() {
                            qnp.j.this.a(j9, fdbVar2, j10);
                        }
                    });
                } else {
                    jVar.b.countDown();
                }
                sleepTotalData2 = sleepTotalData;
                i10 = i5;
                str5 = str4;
                list2 = list;
                i7 = 3;
                i8 = 0;
            }
        }

        /* synthetic */ void a(long j, fdb fdbVar, long j2) {
            qnp.c(j, this.b, fdbVar, j2);
        }

        private void b(SleepTotalData sleepTotalData, long j, long j2, List<Long> list, List<Integer> list2) {
            List<Map<String, Long>> noonMapList = sleepTotalData.getNoonMapList();
            if (noonMapList == null || noonMapList.size() == 0) {
                LogUtil.d("SleepProcessResultUtil", "noonMapList is null");
                return;
            }
            for (Map<String, Long> map : noonMapList) {
                Long l = map.get("core_sleep_start_time_key");
                if (l == null) {
                    LogUtil.d("SleepProcessResultUtil", "setNoonSleepData start is null");
                } else {
                    Long l2 = map.get("core_sleep_end_time_key");
                    if (l2 == null) {
                        LogUtil.d("SleepProcessResultUtil", "setNoonSleepData end is null");
                    } else {
                        long longValue = l.longValue();
                        long longValue2 = l2.longValue();
                        if (longValue >= j && longValue2 < j2) {
                            list.add(Long.valueOf(longValue / 1000));
                            list2.add(Integer.valueOf((int) ((longValue2 - longValue) / 1000)));
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(long j2, CountDownLatch countDownLatch, fdb fdbVar, long j3) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j2);
        hiAggregateOption.setEndTime(86399999 + j2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(f16497a);
        hiAggregateOption.setType(e);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HiDataAggregateProOption.Builder().c(hiAggregateOption).c((String) null).c());
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).aggregateHiHealthDataProEx(arrayList, new i(countDownLatch, fdbVar, j3, j2));
    }

    static class i implements HiAggregateListenerEx {
        private CountDownLatch b;
        private fdb c;
        private long d;
        private long e;

        i(CountDownLatch countDownLatch, fdb fdbVar, long j, long j2) {
            this.b = countDownLatch;
            this.c = fdbVar;
            this.d = j;
            this.e = j2;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
        public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
            LogUtil.d("SleepProcessResultUtil", "PhoneSleepResponseCallback dataList: ", sparseArray);
            if (sparseArray == null) {
                LogUtil.c("SleepProcessResultUtil", "PhoneSleepResponseCallback dataList is null");
                this.b.countDown();
                return;
            }
            List<HiHealthData> list = sparseArray.get(0);
            if (bkz.e(list)) {
                LogUtil.c("SleepProcessResultUtil", "PhoneSleepResponseCallback hiHealthData is null");
                this.b.countDown();
                return;
            }
            HiHealthData hiHealthData = list.get(0);
            long j = hiHealthData.getLong("bedTime");
            long j2 = hiHealthData.getLong("risingTime");
            LogUtil.d("SleepProcessResultUtil", "PhoneSleepResponseCallback bedTime: ", Long.valueOf(j), " risingTime: ", Long.valueOf(j2), " latency: ", Integer.valueOf(hiHealthData.getInt("latency")));
            this.c.e(j / 1000);
            this.c.c(j2 / 1000);
            this.c.p(qnp.e(this.d, j, hiHealthData.getTimeZone(), this.e) * 60);
            this.b.countDown();
        }
    }

    static class b implements IBaseResponseCallback {
        private fdb c;
        private long d;
        private CountDownLatch e;

        b(CountDownLatch countDownLatch, fdb fdbVar, long j) {
            this.d = j;
            this.e = countDownLatch;
            this.c = fdbVar;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.d("SleepProcessResultUtil", "DetailNoonSleepResponseCallback response!");
            if (i != 0 || !(obj instanceof List)) {
                LogUtil.d("SleepProcessResultUtil", "get day detail error or null, error code is ", Integer.valueOf(i));
                this.e.countDown();
                return;
            }
            List list = (List) obj;
            com.huawei.hwlogsmodel.LogUtil.a("SleepProcessResultUtil", " handleDayData retListData.size = ", Integer.valueOf(list.size()));
            if (list.isEmpty()) {
                LogUtil.d("SleepProcessResultUtil", "get day detail retListData.isEmpty()");
                this.e.countDown();
                return;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            qnp.d((SleepTotalData) list.get(0), arrayList, arrayList2, this.d * 1000);
            this.c.f(arrayList);
            this.c.c(arrayList2);
            LogUtil.d("SleepProcessResultUtil", "noonstartList is ", arrayList, ", noontimelist is ", arrayList2);
            this.e.countDown();
        }
    }

    static class a implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private CountDownLatch f16502a;
        private fdb b;
        private long c;
        private String d;
        private long e;

        a(long j, CountDownLatch countDownLatch, fdb fdbVar) {
            this.c = j;
            this.f16502a = countDownLatch;
            this.b = fdbVar;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            String str;
            boolean z;
            int i2;
            long j;
            long j2;
            long j3;
            long j4;
            long j5;
            LogUtil.d("SleepProcessResultUtil", "Day DetailSleepResponseCallback response!");
            if (i != 0 || !(obj instanceof List)) {
                LogUtil.d("SleepProcessResultUtil", "get day detail error or null, error code is ", Integer.valueOf(i));
                this.f16502a.countDown();
                this.f16502a.countDown();
                this.f16502a.countDown();
                return;
            }
            List list = (List) obj;
            com.huawei.hwlogsmodel.LogUtil.a("SleepProcessResultUtil", " handleDayData retListData.size = ", Integer.valueOf(list.size()));
            if (list.isEmpty()) {
                LogUtil.d("SleepProcessResultUtil", "get day detail retListData.isEmpty()");
                this.f16502a.countDown();
                this.f16502a.countDown();
                this.f16502a.countDown();
                return;
            }
            long j6 = this.c;
            int i3 = 0;
            while (true) {
                if (i3 >= list.size()) {
                    str = "";
                    z = false;
                    i2 = 0;
                    j = 0;
                    break;
                }
                SleepTotalData sleepTotalData = (SleepTotalData) list.get(i3);
                if (qnp.a(sleepTotalData)) {
                    boolean c = qnp.c(sleepTotalData);
                    long b = qnp.b(i3, j6);
                    this.d = sleepTotalData.getTimeZone();
                    this.e = j6;
                    String deviceId = sleepTotalData.getDeviceType() == 3 ? sleepTotalData.getDeviceId() : "";
                    int b2 = sleepTotalData.getNoonSleepTime() != 1 ? qnp.b(sleepTotalData.getDeviceType()) : 0;
                    str = deviceId;
                    i2 = b2;
                    z = c;
                    j = b;
                } else {
                    i3++;
                }
            }
            int size = list.size() - 1;
            while (true) {
                if (size < 0) {
                    j2 = 0;
                    break;
                } else {
                    if (qnp.a((SleepTotalData) list.get(size))) {
                        j2 = qnp.b(size, j6) + 60000;
                        break;
                    }
                    size--;
                }
            }
            if (z) {
                j5 = qnp.e((List<SleepTotalData>) list, j6, j);
                j4 = qnp.d((List<SleepTotalData>) list, j6, j2);
                j3 = j;
                int e = qnp.e(j, j5, this.d, this.e);
                this.b.e(j5 / 1000);
                this.b.c(j4 / 1000);
                this.b.p(e * 60);
            } else {
                j3 = j;
                j4 = 0;
                j5 = 0;
            }
            final long j7 = j3;
            this.b.a(j7 / 1000);
            this.b.d(j2 / 1000);
            this.b.a(i2);
            if (str.equals("")) {
                this.f16502a.countDown();
                this.f16502a.countDown();
                LogUtil.d("SleepProcessResultUtil", "startSleepTime is ", Long.valueOf(j7), ",endSleepTime is ", Long.valueOf(j2), ",startBedTime is ", Long.valueOf(j5), ",endBedTime is ", Long.valueOf(j4));
            } else {
                LogUtil.d("SleepProcessResultUtil", "get phone data phoneDeviceId is ", str);
                qnp.c(str, this.b, this.f16502a, this.c);
                qnp.b(str, this.b, this.f16502a, this.c);
            }
            if (3 == i2) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: qnm
                    @Override // java.lang.Runnable
                    public final void run() {
                        qnp.a.this.a(j7);
                    }
                });
            } else {
                this.f16502a.countDown();
            }
        }

        /* synthetic */ void a(long j) {
            qnp.c(this.c, this.f16502a, this.b, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int e(long j2, long j3, String str, long j4) {
        if (j2 <= 0 || j3 <= 0) {
            return -1;
        }
        return Math.max(0, d(j2, j4, str) - b(j3, j4, str));
    }

    private static int d(long j2, long j3, String str) {
        if (j2 <= 0 || j3 <= 0) {
            return -1;
        }
        return fcj.b(j2, fcj.e(j3, str, -1));
    }

    private static int b(long j2, long j3, String str) {
        if (j2 <= 0 || j3 <= 0) {
            return -1;
        }
        return fcj.b(j2, fcj.e(j3, str, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, final fdb fdbVar, final CountDownLatch countDownLatch, long j2) {
        LogUtil.d("SleepProcessResultUtil", "begin to request envnoise data!");
        long e2 = jec.e(new Date(j2)) * 1000;
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(e2 - 28800000);
        hiDataReadOption.setEndTime(e2 - (-86400000));
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.ENVIRONMENT_NOISE_TYPE.value()});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(5);
        HiHealthNativeApi.a(com.huawei.haf.application.BaseApplication.e()).readHiHealthDataPro(HiDataReadProOption.builder().e(hiDataReadOption).b(1).e(), new HiDataReadResultListener() { // from class: qnp.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                LogUtil.d("SleepProcessResultUtil", "getPhoneNoiseData back");
                if (!(obj instanceof SparseArray)) {
                    LogUtil.e("SleepProcessResultUtil", "getPhoneNoiseData no data");
                    countDownLatch.countDown();
                    return;
                }
                Object obj2 = ((SparseArray) obj).get(DicDataTypeUtil.DataType.ENVIRONMENT_NOISE_TYPE.value());
                List list = obj2 instanceof List ? (List) obj2 : null;
                if (bkz.e(list)) {
                    LogUtil.d("SleepProcessResultUtil", "no env noise data!");
                    countDownLatch.countDown();
                    return;
                }
                String metaData = ((HiHealthData) list.get(0)).getMetaData();
                if (TextUtils.isEmpty(metaData)) {
                    LogUtil.d("SleepProcessResultUtil", "no env noise meta data!");
                } else {
                    try {
                        fdbVar.h(new JSONObject(metaData).getInt("avgSnoreDb"));
                    } catch (JSONException unused) {
                        LogUtil.d("SleepProcessResultUtil", "sleep environmental noise MetaData get fail");
                    }
                }
                countDownLatch.countDown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, final fdb fdbVar, final CountDownLatch countDownLatch, final long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        calendar.add(5, 1);
        Date time = calendar.getTime();
        calendar.setTimeInMillis(j2);
        calendar.add(5, -1);
        long n2 = jec.n(calendar.getTime());
        long k = jec.k(time);
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        if (str != null && !str.equals("")) {
            hiDataReadOption.setDeviceUuid(str);
        }
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.SLEEP_SCORE.value(), DicDataTypeUtil.DataType.WAKE_COUNT.value()});
        hiDataReadOption.setSortOrder(0);
        hiDataReadOption.setTimeInterval(n2 * 1000, k * 1000);
        hiDataReadOption.setReadType(2);
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: qnp.10
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                LogUtil.d("SleepProcessResultUtil", "getExtraPhoneData back");
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray == null || sparseArray.size() <= 0) {
                    LogUtil.d("SleepProcessResultUtil", "map is null");
                    countDownLatch.countDown();
                    return;
                }
                long e2 = jec.e(new Date(j2)) * 1000;
                int dGn_ = qnl.dGn_(sparseArray, DicDataTypeUtil.DataType.SLEEP_SCORE.value(), e2, ((e2 / 1000) + k.b.m) * 1000);
                LogUtil.d("SleepProcessResultUtil", "extra coreScore is ", Integer.valueOf(dGn_), "wakeCount is ", 0);
                fdbVar.k(dGn_);
                fdbVar.x(0);
                countDownLatch.countDown();
            }
        });
    }

    static class g implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private long f16504a;
        private CountDownLatch b;
        private fdb e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        public g(long j, CountDownLatch countDownLatch, fdb fdbVar) {
            this.f16504a = j;
            this.b = countDownLatch;
            this.e = fdbVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            HiHealthData next;
            if (bkz.e(list)) {
                ReleaseLogUtil.d("R_Sleep_SleepProcessResultUtil", "datas is empty");
                this.b.countDown();
                return;
            }
            Iterator<HiHealthData> it = list.iterator();
            HiHealthData hiHealthData = null;
            while (it.hasNext() && (next = it.next()) != null) {
                if (hiHealthData == null || hiHealthData.getEndTime() < next.getEndTime()) {
                    hiHealthData = next;
                }
            }
            this.e.q(qnp.a(hiHealthData));
            this.b.countDown();
        }
    }

    static class n implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private fdb f16507a;
        private long b;
        private CountDownLatch e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        public n(long j, CountDownLatch countDownLatch, fdb fdbVar) {
            this.b = j;
            this.e = countDownLatch;
            this.f16507a = fdbVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            LogUtil.d("SleepProcessResultUtil", "SumSleepResponseCallback response!");
            if (bkz.e(list)) {
                LogUtil.d("SleepProcessResultUtil", "on success() sumList = null");
                this.e.countDown();
                return;
            }
            long j = this.b;
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData.getStartTime() == j) {
                    qnp.a(this.f16507a, hiHealthData);
                }
                if (hiHealthData.getStartTime() == j - 86400000) {
                    qnp.d(this.f16507a, hiHealthData);
                }
            }
            LogUtil.d("SleepProcessResultUtil", "now day sum sleep info ok ", this.f16507a.toString());
            this.e.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(fdb fdbVar, HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return;
        }
        int i2 = hiHealthData.getInt("rest_heart_rate_new");
        if (i2 == 0) {
            i2 = hiHealthData.getInt("rest_heart_rate_old");
        }
        int i3 = hiHealthData.getInt("max_heart_rate");
        int i4 = hiHealthData.getInt("stress_day_avg");
        fdbVar.o(i2);
        fdbVar.j(i3);
        fdbVar.d(i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(fdb fdbVar, HiHealthData hiHealthData) {
        int i2;
        if (hiHealthData == null) {
            return;
        }
        int i3 = hiHealthData.getInt("core_sleep_deep_key");
        int i4 = hiHealthData.getInt("core_sleep_shallow_key");
        int i5 = hiHealthData.getInt("core_sleep_wake_key");
        int i6 = hiHealthData.getInt("core_sleep_wake_count_key");
        int i7 = hiHealthData.getInt("core_sleep_dream_key");
        int i8 = hiHealthData.getInt("core_sleep_score_key");
        int i9 = hiHealthData.getInt("core_sleep_sum_key");
        int i10 = hiHealthData.getInt("data_session_manual_sleep_bed_time_key");
        int i11 = hiHealthData.getInt("core_sleep_snore_freq_key");
        int i12 = hiHealthData.getInt("core_sleep_deep_count_key");
        boolean z = i4 > 0 || i3 > 0 || i7 > 0;
        int i13 = hiHealthData.getInt("common_sleep_duration_sleep_sum_key");
        int i14 = hiHealthData.getInt("sleep_device_category_key");
        int d2 = d(hiHealthData);
        int e2 = e(hiHealthData);
        int c2 = c(hiHealthData);
        int j2 = j(hiHealthData);
        SleepViewConstants.SleepTypeEnum i15 = i(i14);
        LogUtil.d("SleepProcessResultUtil", "setDaySleepInfo sleepType ", i15, " deepsum ", Integer.valueOf(i3), " shallowsum ", Integer.valueOf(i4), " coreWakeSum ", Integer.valueOf(i5), " dreamSum ", Integer.valueOf(i7), " score ", Integer.valueOf(i8), " commonSleepTotalTime ", Integer.valueOf(i13), " deviceCategory ", Integer.valueOf(i14));
        if (i15 == SleepViewConstants.SleepTypeEnum.SCIENCE || i15 == SleepViewConstants.SleepTypeEnum.PHONE || (z && i13 <= 0 && i14 == 0)) {
            fdbVar.e(i3 * 60);
            fdbVar.i(i4 * 60);
            fdbVar.f(i7 * 60);
            fdbVar.r(i9 * 60);
            fdbVar.x(i6);
            fdbVar.v(i5 * 60);
            if (i15 == SleepViewConstants.SleepTypeEnum.SCIENCE || i8 > 0 || i7 > 0) {
                long j3 = hiHealthData.getLong("core_sleep_go_bed_time_key");
                long j4 = hiHealthData.getLong("core_sleep_off_bed_time_key");
                int i16 = hiHealthData.getInt("core_sleep_latency_new_key");
                int i17 = hiHealthData.getInt("core_sleep_bed_duration_key");
                fdbVar.e(j3 / 1000);
                fdbVar.c(j4 / 1000);
                fdbVar.p(i16 * 60);
                fdbVar.n(d2);
                fdbVar.m(e2);
                fdbVar.l(c2);
                fdbVar.s(j2);
                LogUtil.d("SleepProcessResultUtil", "setDaySleepInfo onBedTime: ", Long.valueOf(j3), " offBedTime; ", Long.valueOf(j4), " sleepLatency: ", Integer.valueOf(i16), "heartRateStatus", Integer.valueOf(d2), "breathRateStatus", Integer.valueOf(e2), "hrvStatus", Integer.valueOf(c2), "spO2status", Integer.valueOf(j2));
                i2 = i17;
            } else {
                i2 = i5 + i9;
            }
            LogUtil.d("SleepProcessResultUtil", "setDaySleepInfo bedDuration; ", Integer.valueOf(i2));
            if (i2 > 0) {
                int a2 = (int) (UnitUtil.a((i9 * 1.0f) / i2, 2) * 100.0d);
                fdbVar.b(i2 * 60);
                fdbVar.t(a2);
                LogUtil.d("SleepProcessResultUtil", "setDaySleepInfo sleepEfficiency: ", Integer.valueOf(a2));
            }
        } else if (!z && i13 > 0) {
            int i18 = hiHealthData.getInt("sleep_deep_key");
            int i19 = hiHealthData.getInt("sleep_shallow_key");
            int i20 = hiHealthData.getInt("sleep_wake_key");
            int i21 = hiHealthData.getInt("sleep_wake_count_key");
            fdbVar.e(i18);
            fdbVar.i(i19);
            fdbVar.r(i13);
            fdbVar.x(i21);
            fdbVar.v(i20);
        } else if (i10 > 0) {
            fdbVar.e(i3 * 60);
            fdbVar.i(i4 * 60);
            fdbVar.f(i7 * 60);
            fdbVar.r(i9 * 60);
            fdbVar.x(i6);
            fdbVar.v(i5 * 60);
            fdbVar.b(i10 * 60);
            fdbVar.t((int) (UnitUtil.a((i9 * 1.0f) / i10, 2) * 100.0d));
        } else {
            LogUtil.d("SleepProcessResultUtil", "setDaySleepInfo sleep type is wrong");
        }
        fdbVar.g(i11);
        fdbVar.c(i12);
        fdbVar.k(i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int a(HiHealthData hiHealthData) {
        int i2 = hiHealthData.getInt("osaLevel");
        if (i2 <= 0 || i2 > 4) {
            return 0;
        }
        return i2;
    }

    private static int d(HiHealthData hiHealthData) {
        int i2 = hiHealthData.getInt("avgHeartrate");
        int i3 = hiHealthData.getInt("minHeartrateBaseline");
        int i4 = hiHealthData.getInt("maxHeartrateBaseline");
        if (c(i3) || c(i4) || c(i2) || i4 < i3) {
            return 0;
        }
        return b(i2, i3, i4);
    }

    private static int e(HiHealthData hiHealthData) {
        int i2 = hiHealthData.getInt("avgBreathrate");
        int i3 = hiHealthData.getInt("minBreathrateBaseline");
        int i4 = hiHealthData.getInt("maxBreathrateBaseline");
        if (e(i2) || e(i3) || e(i4) || i4 < i3) {
            return 0;
        }
        return b(i2, i3, i4);
    }

    private static int c(HiHealthData hiHealthData) {
        int i2 = hiHealthData.getInt("avgHrv");
        int i3 = hiHealthData.getInt("minHrvBaseline");
        int i4 = hiHealthData.getInt("maxHrvBaseline");
        if (d(i3) || d(i4) || d(i2) || i4 < i3) {
            return 0;
        }
        return b(i2, i3, i4);
    }

    private static int j(HiHealthData hiHealthData) {
        int i2 = hiHealthData.getInt("avgOxygenSaturation");
        int i3 = hiHealthData.getInt("minOxygenSaturationBaseline");
        int i4 = hiHealthData.getInt("maxOxygenSaturationBaseline");
        if (f(i3) || f(i4) || f(i2) || i4 < i3) {
            return 0;
        }
        return b(i2, i3, i4);
    }

    private static SleepViewConstants.SleepTypeEnum i(int i2) {
        SleepViewConstants.SleepTypeEnum sleepTypeEnum = SleepViewConstants.SleepTypeEnum.COMMON;
        if (fcj.i(i2)) {
            sleepTypeEnum = SleepViewConstants.SleepTypeEnum.SCIENCE;
        } else if (fcj.f(i2)) {
            sleepTypeEnum = SleepViewConstants.SleepTypeEnum.PHONE;
        }
        LogUtil.d("SleepProcessResultUtil", "judgeScienceAndPhone:", sleepTypeEnum);
        return sleepTypeEnum;
    }

    static class f implements HiAggregateListener {
        private List<fdb> b;
        private long c;
        private long d;
        private CountDownLatch e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        public f(long j, long j2, CountDownLatch countDownLatch, List<fdb> list) {
            this.c = j;
            this.d = j2;
            this.e = countDownLatch;
            this.b = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            LogUtil.d("SleepProcessResultUtil", "SumSleepResponseCallback response!");
            if (list == null || list.isEmpty()) {
                LogUtil.d("SleepProcessResultUtil", "on success() sumList = null");
                this.e.countDown();
                return;
            }
            for (int i3 = 0; i3 < this.b.size(); i3++) {
                fdb fdbVar = this.b.get(i3);
                long j = this.c;
                long j2 = i3;
                long j3 = i3 - 1;
                HiHealthData hiHealthData = null;
                HiHealthData hiHealthData2 = null;
                for (HiHealthData hiHealthData3 : list) {
                    if (hiHealthData3.getStartTime() == (j2 * 86400000) + j) {
                        hiHealthData = hiHealthData3;
                    }
                    if (hiHealthData3.getStartTime() == j + (86400000 * j3)) {
                        hiHealthData2 = hiHealthData3;
                    }
                }
                qnp.a(fdbVar, hiHealthData);
                qnp.d(fdbVar, hiHealthData2);
                LogUtil.d("SleepProcessResultUtil", "now sleep info ok ", fdbVar.toString());
            }
            LogUtil.d("SleepProcessResultUtil", "sleep info list ok ");
            this.e.countDown();
        }
    }

    static class h implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private long f16505a;
        private List<fdb> c;
        private int d;
        private CountDownLatch e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public h(long j, CountDownLatch countDownLatch, List<fdb> list, int i) {
            this.f16505a = j;
            this.e = countDownLatch;
            this.c = list;
            this.d = i;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (i != 0 || !(obj instanceof SparseArray)) {
                LogUtil.d("SleepProcessResultUtil", "get heart rate day detail error or null, error code is ", Integer.valueOf(i));
                this.e.countDown();
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() <= 0) {
                LogUtil.d("SleepProcessResultUtil", "heartRateList is empty", Integer.valueOf(i));
                this.e.countDown();
                return;
            }
            List<HiHealthData> list = (List) sparseArray.get(2002);
            ArrayList arrayList = new ArrayList();
            long j = this.f16505a;
            for (int i3 = 0; i3 < this.d; i3++) {
                ArrayList arrayList2 = new ArrayList();
                long d = this.c.get(i3).d() * 1000;
                if (d == 0) {
                    arrayList.add(arrayList2);
                } else {
                    boolean z = false;
                    for (HiHealthData hiHealthData : list) {
                        if (hiHealthData.getIntValue() != 0 && hiHealthData.getStartTime() >= j && hiHealthData.getStartTime() < d) {
                            arrayList2.add(hiHealthData);
                            z = true;
                        }
                    }
                    arrayList.add(arrayList2);
                }
                j += 86400000;
            }
            LogUtil.d("SleepProcessResultUtil", "dayHeartRateList ok ");
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                for (HiHealthData hiHealthData2 : (List) arrayList.get(i4)) {
                    arrayList3.add(Long.valueOf(hiHealthData2.getStartTime() / 1000));
                    arrayList4.add(Integer.valueOf(hiHealthData2.getIntValue()));
                }
                this.c.get(i4).b(arrayList3);
                this.c.get(i4).a(arrayList4);
            }
            this.e.countDown();
        }
    }

    static class c implements HiDataReadResultListener {
        private CountDownLatch c;
        private fdb e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public c(CountDownLatch countDownLatch, fdb fdbVar) {
            this.c = countDownLatch;
            this.e = fdbVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.d("SleepProcessResultUtil", "DetailHeartRateResponseCallback back ", Integer.valueOf(i));
            if (i != 0 || !(obj instanceof SparseArray)) {
                LogUtil.d("SleepProcessResultUtil", "get heart rate day detail error or null, error code is ", Integer.valueOf(i));
                this.c.countDown();
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() > 0) {
                List<HiHealthData> list = (List) sparseArray.get(2002);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (HiHealthData hiHealthData : list) {
                    if (hiHealthData.getIntValue() != 0) {
                        arrayList.add(Long.valueOf(hiHealthData.getStartTime() / 1000));
                        arrayList2.add(Integer.valueOf(hiHealthData.getIntValue()));
                    }
                }
                this.e.b(arrayList);
                this.e.a(arrayList2);
                this.c.countDown();
                return;
            }
            LogUtil.d("SleepProcessResultUtil", "heartRateList is empty", Integer.valueOf(i));
            this.c.countDown();
        }
    }

    static class d implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private int f16503a;
        private List<fdb> b;
        private long c;
        private CountDownLatch e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public d(long j, CountDownLatch countDownLatch, List<fdb> list, int i) {
            this.c = j;
            this.e = countDownLatch;
            this.b = list;
            this.f16503a = i;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (i != 0 || !(obj instanceof SparseArray)) {
                LogUtil.d("SleepProcessResultUtil", "get exercise day detail error or null, error code is ", Integer.valueOf(i));
                this.e.countDown();
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() <= 0) {
                LogUtil.d("SleepProcessResultUtil", "heartRateList is empty", Integer.valueOf(i));
                this.e.countDown();
                return;
            }
            List<HiHealthData> list = (List) sparseArray.get(7);
            ArrayList arrayList = new ArrayList();
            long j = this.c;
            for (int i3 = 0; i3 < this.f16503a; i3++) {
                ArrayList arrayList2 = new ArrayList();
                long d = this.b.get(i3).d() * 1000;
                if (d == 0) {
                    arrayList.add(arrayList2);
                } else {
                    boolean z = false;
                    for (HiHealthData hiHealthData : list) {
                        if (hiHealthData.getIntValue() != 0 && hiHealthData.getStartTime() >= j && hiHealthData.getStartTime() < d) {
                            arrayList2.add(hiHealthData);
                            z = true;
                        }
                    }
                    arrayList.add(arrayList2);
                }
                j += 86400000;
            }
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                List list2 = (List) arrayList.get(i4);
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                int i5 = 60;
                long j2 = 0;
                for (int i6 = 0; i6 < list2.size(); i6++) {
                    HiHealthData hiHealthData2 = (HiHealthData) list2.get(i6);
                    if (hiHealthData2.getStartTime() - j2 > 60000) {
                        arrayList3.add(Long.valueOf(hiHealthData2.getStartTime() / 1000));
                        arrayList4.add(60);
                        i5 = 60;
                    } else {
                        i5 += 60;
                        arrayList4.set(arrayList4.size() - 1, Integer.valueOf(i5));
                    }
                    j2 = hiHealthData2.getStartTime();
                }
                this.b.get(i4).e(arrayList3);
                this.b.get(i4).d(arrayList4);
            }
            this.e.countDown();
        }
    }

    static class e implements HiDataReadResultListener {
        private CountDownLatch b;
        private fdb c;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public e(CountDownLatch countDownLatch, fdb fdbVar) {
            this.b = countDownLatch;
            this.c = fdbVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.d("SleepProcessResultUtil", "DetailExerciseIntensityResponseCallback back ", Integer.valueOf(i));
            if (i != 0 || !(obj instanceof SparseArray)) {
                LogUtil.d("SleepProcessResultUtil", "get exercise day detail error or null, error code is ", Integer.valueOf(i));
                this.b.countDown();
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() > 0) {
                List list = (List) sparseArray.get(7);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                long j = 0;
                int i3 = 60;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    HiHealthData hiHealthData = (HiHealthData) list.get(i4);
                    if (hiHealthData.getStartTime() - j > 60000) {
                        arrayList.add(Long.valueOf(hiHealthData.getStartTime() / 1000));
                        arrayList2.add(60);
                        i3 = 60;
                    } else {
                        i3 += 60;
                        arrayList2.set(arrayList2.size() - 1, Integer.valueOf(i3));
                    }
                    j = hiHealthData.getStartTime();
                }
                this.c.e(arrayList);
                this.c.d(arrayList2);
                this.b.countDown();
                return;
            }
            LogUtil.d("SleepProcessResultUtil", "exerciseList is empty", Integer.valueOf(i));
            this.b.countDown();
        }
    }
}
