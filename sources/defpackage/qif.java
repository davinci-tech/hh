package defpackage;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dqq;
import health.compact.a.LogAnonymous;
import health.compact.a.ProcessUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class qif {

    /* renamed from: a, reason: collision with root package name */
    private static final Pair<Integer, Integer> f16430a;
    private static final Pair<Integer, Integer> b;
    private static final Pair<Integer, Integer> c = new Pair<>(60, 230);
    private static final Pair<Integer, Integer> d;
    private static final Pair<Integer, Integer> e;

    private static float b(int i, int i2, int i3) {
        float f = (i - i2) / (i3 - i2);
        if (f < 0.0f) {
            return 0.0f;
        }
        return (f * 64.0f) + 45.0f;
    }

    static {
        Integer valueOf = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_TENNIS);
        b = new Pair<>(45, valueOf);
        d = new Pair<>(valueOf, 80);
        e = new Pair<>(135, 85);
        f16430a = new Pair<>(120, 70);
    }

    public static void a(List<cbk> list, ResponseCallback<Object> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("BloodPressureUtils", "saveBloodPressureMeasurePlan callback is null");
            return;
        }
        if (koq.b(list)) {
            LogUtil.h("BloodPressureUtils", "saveBloodPressureMeasurePlan bloodPressureAlarmInfoList ", list);
            responseCallback.onResponse(-1, list);
        } else {
            String e2 = HiJsonUtil.e(cbi.e(list));
            Objects.requireNonNull(responseCallback);
            qrp.a("900300001", e2, new bal(responseCallback));
        }
    }

    public static void d(final List<cbk> list) {
        a(list, (ResponseCallback<Object>) new ResponseCallback() { // from class: qid
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                dsl.j(list);
            }
        });
    }

    public static List<cbk> d() {
        final ArrayList arrayList = new ArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        c(new ResponseCallback() { // from class: qib
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                qif.c(arrayList, countDownLatch, i, (List) obj);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e2) {
            LogUtil.b("BloodPressureUtils", "getBloodPressureMeasurePlan interruptedException ", LogAnonymous.b((Throwable) e2));
        }
        return arrayList;
    }

    static /* synthetic */ void c(List list, CountDownLatch countDownLatch, int i, List list2) {
        list.addAll(list2);
        countDownLatch.countDown();
    }

    public static void c(final ResponseCallback<List<cbk>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("BloodPressureUtils", "getBloodPressureMeasurePlan callback is null");
        } else {
            qrp.b("900300001", new HiDataReadResultListener() { // from class: qif.1
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(final Object obj, final int i, final int i2) {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: qif.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            LogUtil.a("BloodPressureUtils", "getBloodPressureMeasurePlan onResult object ", obj, " errorCode ", Integer.valueOf(i), " anchor ", Integer.valueOf(i2));
                            if (!koq.e(obj, HiSampleConfig.class)) {
                                ResponseCallback.this.onResponse(i, qif.c());
                                return;
                            }
                            List list = (List) obj;
                            if (koq.b(list)) {
                                ResponseCallback.this.onResponse(i, qif.c());
                                return;
                            }
                            HiSampleConfig hiSampleConfig = (HiSampleConfig) list.get(0);
                            if (hiSampleConfig == null) {
                                ResponseCallback.this.onResponse(i, qif.c());
                                return;
                            }
                            String configData = hiSampleConfig.getConfigData();
                            if (TextUtils.isEmpty(configData)) {
                                ResponseCallback.this.onResponse(i, qif.c());
                                return;
                            }
                            List<cbk> a2 = cbi.a((cbj) HiJsonUtil.e(configData, cbj.class));
                            if (koq.b(a2)) {
                                ResponseCallback.this.onResponse(i, qif.c());
                            } else {
                                ResponseCallback.this.onResponse(i, a2);
                            }
                        }
                    });
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                    LogUtil.a("BloodPressureUtils", "getBloodPressureMeasurePlan intentType ", Integer.valueOf(i), " object ", obj, " errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<cbk> c() {
        if (Utils.l()) {
            LogUtil.h("BloodPressureUtils", "getOldBloodPressureMeasurePlan isOverseaNoCloudVersion ");
            return Collections.emptyList();
        }
        List<cbk> d2 = dsl.d();
        if (!koq.b(d2)) {
            return d2;
        }
        LogUtil.h("BloodPressureUtils", "getOldBloodPressureMeasurePlan alarmInfoList ", d2);
        return Collections.emptyList();
    }

    public static void e(boolean z) {
        if (z) {
            Calendar calendar = Calendar.getInstance();
            int i = 7;
            if (Utils.o()) {
                calendar.set(7, 7);
            } else {
                calendar.set(7, 1);
                i = 1;
            }
            calendar.set(11, 20);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            long timeInMillis = calendar.getTimeInMillis();
            long currentTimeMillis = System.currentTimeMillis();
            long d2 = d(i, 20, 21);
            if (currentTimeMillis > timeInMillis) {
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(d2);
                calendar2.add(4, 1);
                d2 = calendar2.getTimeInMillis();
            }
            LogUtil.a("BloodPressureUtils", "setting time ", DateFormatUtil.b(d2, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
            riy.d(1100, d2, 604800000L);
            return;
        }
        riy.e(1100);
    }

    public static int b(Context context) {
        SQLiteDatabase openOrCreateDatabase;
        int i = 0;
        try {
            openOrCreateDatabase = context.openOrCreateDatabase("device.db", 0, null);
        } catch (SQLException unused) {
            LogUtil.b("BloodPressureUtils", "getBondedProducts SQLException");
        }
        try {
            Cursor query = openOrCreateDatabase.query("device", new String[]{"productId"}, "kind = 'HDK_BLOOD_PRESSURE'", null, null, null, null);
            while (query.moveToNext()) {
                try {
                    i++;
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
            if (openOrCreateDatabase != null) {
                openOrCreateDatabase.close();
            }
            return i;
        } finally {
        }
    }

    public static long d(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(7, i);
        int nextInt = new SecureRandom().nextInt(((i3 - i2) * 60) + 1);
        calendar.set(11, i2 + (nextInt / 60));
        calendar.set(12, nextInt % 60);
        calendar.set(13, 0);
        calendar.set(14, 0);
        ReleaseLogUtil.e("R_BloodPressureUtils", "getRandomTime: ", Integer.valueOf(calendar.get(11)), ":", Integer.valueOf(calendar.get(12)), " ", Long.valueOf(calendar.getTimeInMillis()));
        return calendar.getTimeInMillis();
    }

    public static float c(int i, int i2) {
        Pair<Integer, Integer> dDO_ = dDO_("Twenty-four-hours");
        if (i >= ((Integer) dDO_.first).intValue() && i2 >= ((Integer) dDO_.second).intValue()) {
            return b(i, ((Integer) dDO_.first).intValue(), ((Integer) c.second).intValue());
        }
        if (i >= ((Integer) dDO_.first).intValue()) {
            return b(i, ((Integer) dDO_.first).intValue(), ((Integer) c.second).intValue());
        }
        if (i2 >= ((Integer) dDO_.second).intValue()) {
            return b(i2, ((Integer) dDO_.second).intValue(), ((Integer) b.second).intValue());
        }
        return c(i, i2, ((Integer) dDO_.first).intValue(), ((Integer) dDO_.second).intValue());
    }

    private static float c(int i, int i2, int i3, int i4) {
        int i5;
        int intValue = ((Integer) c.first).intValue();
        int intValue2 = ((Integer) b.first).intValue();
        int i6 = i3 - intValue;
        if (i6 == 0 || (i5 = i4 - intValue2) == 0) {
            LogUtil.h("BloodPressureUtils", "systolic is minSystolic or diastolic is minDiastolic");
            return 0.0f;
        }
        float f = (i - intValue) / i6;
        float f2 = (i2 - intValue2) / i5;
        if (f <= 0.0f) {
            f = 0.0f;
        }
        if (f2 <= 0.0f) {
            f2 = 0.0f;
        }
        int i7 = (f < 0.0f ? 0 : 1) + (f2 < 0.0f ? 0 : 1);
        if (i7 == 0) {
            return 0.0f;
        }
        return Math.max(Math.min((f + f2) / i7, 1.0f), 0.0f) * 45.0f;
    }

    public static boolean a(int i, int i2) {
        Pair<Integer, Integer> dDO_ = dDO_("Twenty-four-hours");
        return i >= ((Integer) dDO_.first).intValue() || i2 >= ((Integer) dDO_.second).intValue();
    }

    public static Pair<Integer, Integer> dDO_(String str) {
        ArrayList<dqq> d2 = dqu.b().d();
        if (koq.b(d2)) {
            LogUtil.h("BloodPressureUtils", "gradeList is empty!");
            if ("Twenty-four-hours".equals(str)) {
                return d;
            }
            if ("day".equals(str)) {
                return e;
            }
            if ("night".equals(str)) {
                return f16430a;
            }
            LogUtil.b("BloodPressureUtils", "bloodPressureType is error");
            return d;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < d2.size(); i3++) {
            dqq dqqVar = d2.get(i3);
            if (dqqVar == null) {
                LogUtil.b("BloodPressureUtils", "bloodPressureFeatureInfo is null");
            } else if (str.equals(dqqVar.d())) {
                List<dqq.a> a2 = dqqVar.a();
                if (koq.b(a2)) {
                    LogUtil.b("BloodPressureUtils", "thresholdList is empty");
                } else {
                    dqq.a aVar = a2.get(0);
                    if (aVar == null) {
                        LogUtil.b("BloodPressureUtils", "dynamicBloodPressureTypeCategorization is null");
                    } else {
                        List<Integer> b2 = aVar.b();
                        List<Integer> e2 = aVar.e();
                        if (koq.b(b2) || koq.b(e2)) {
                            LogUtil.b("BloodPressureUtils", "sysList or diaList is empty");
                        } else if (!koq.d(b2, 1) || !koq.d(e2, 1)) {
                            LogUtil.b("BloodPressureUtils", "sysList or diaList is out bounds");
                        } else {
                            i = b2.get(1).intValue();
                            i2 = e2.get(1).intValue();
                        }
                    }
                }
            }
        }
        return new Pair<>(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static boolean b() {
        if (!ProcessUtil.d()) {
            LogUtil.h("BloodPressureUtils", "isDeviceSupportDynamicBp not in main process");
            return false;
        }
        DeviceInfo d2 = jpt.d("BloodPressureUtils");
        if (d2 == null) {
            LogUtil.h("BloodPressureUtils", "isDeviceSupportDynamicBp deviceInfo is null");
            return false;
        }
        boolean c2 = cwi.c(d2, 205);
        LogUtil.a("BloodPressureUtils", "isDeviceSupportDynamicBp isSupport : ", Boolean.valueOf(c2));
        return c2;
    }
}
