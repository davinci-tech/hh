package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.huawei.basichealth.bloodpressure.BloodPressureSyncManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.notification.HealthCommondReceiver;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity;
import health.compact.a.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* loaded from: classes6.dex */
public class qhc {
    public static void d(final List<cbk> list, final boolean z, final ResponseCallback<List<cbk>> responseCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: qhl
            @Override // java.lang.Runnable
            public final void run() {
                qhc.c(list, z, responseCallback);
            }
        });
    }

    static /* synthetic */ void c(List list, boolean z, ResponseCallback responseCallback) {
        boolean c = c();
        qif.d(list);
        if (z) {
            c = b(new HashSet(list));
        }
        if (c && BloodPressureSyncManager.e()) {
            BloodPressureSyncManager.c().b((List<cbk>) list);
        }
        if (responseCallback != null) {
            responseCallback.onResponse(c ? 0 : -1, list);
        }
    }

    private static boolean c() {
        LogUtil.a("MeasurePlanHelper", "enter cancelAllAlarms");
        for (int i = 0; i < 10; i++) {
            for (int i2 = 0; i2 < 7; i2++) {
                sqa.ekn_(i2, dDB_(i, i2, -1, -1), 201326592);
            }
        }
        return true;
    }

    private static Intent dDB_(int i, int i2, int i3, int i4) {
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) HealthCommondReceiver.class);
        intent.setAction("BLOOD_PRESSURE_MEASURE_PLAN_ACTION_" + i + Constants.LINK + i2);
        intent.putExtra("BLOOD_PRESSURE_MEASURE_PLAN_ID", i);
        intent.putExtra("BLOOD_PRESSURE_MEASURE_PLAN_HOUR_OF_DAY", i3);
        intent.putExtra("BLOOD_PRESSURE_MEASURE_PLAN_MINUTE", i4);
        return intent;
    }

    public static void b() {
        qif.c(new ResponseCallback() { // from class: qhf
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                qhc.b(i, (List) obj);
            }
        });
    }

    static /* synthetic */ void b(int i, List list) {
        c();
        b(new HashSet(list));
    }

    private static boolean b(Set<cbk> set) {
        LogUtil.a("MeasurePlanHelper", "enter registerAllAlarm");
        Map<Integer, List<Long>> d = d(set);
        for (cbk cbkVar : set) {
            List<Long> list = d.get(Integer.valueOf(cbkVar.f()));
            if (list != null) {
                Iterator<Long> it = list.iterator();
                int i = 0;
                while (it.hasNext()) {
                    long longValue = it.next().longValue();
                    LogUtil.a("MeasurePlanHelper", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(longValue)));
                    sqa.ekz_(i, dDB_(cbkVar.f(), i + 1, cbkVar.a(), cbkVar.e()), 201326592, 0, longValue);
                    i += 2;
                }
            }
        }
        return true;
    }

    private static Map<Integer, List<Long>> d(Set<cbk> set) {
        HashMap hashMap = new HashMap();
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        for (cbk cbkVar : set) {
            if (cbkVar.i()) {
                char[] charArray = Integer.toBinaryString(cbkVar.d()).toCharArray();
                ArrayList arrayList = new ArrayList(charArray.length);
                for (char c : charArray) {
                    arrayList.add(Character.valueOf(c));
                }
                for (int size = 7 - arrayList.size(); size > 0; size--) {
                    arrayList.add(0, '0');
                }
                Collections.reverse(arrayList);
                Collections.rotate(arrayList, 1);
                ArrayList arrayList2 = new ArrayList(7);
                hashMap.put(Integer.valueOf(cbkVar.f()), arrayList2);
                int i = 0;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    i++;
                    if (((Character) arrayList.get(i2)).charValue() == '1') {
                        calendar.setTimeInMillis(timeInMillis);
                        calendar.set(7, i);
                        calendar.set(11, cbkVar.a());
                        calendar.set(12, cbkVar.e());
                        calendar.set(13, 0);
                        calendar.set(14, 0);
                        long timeInMillis2 = calendar.getTimeInMillis();
                        if (timeInMillis2 < timeInMillis) {
                            calendar.setTimeInMillis(timeInMillis2 + 604800000);
                            timeInMillis2 = calendar.getTimeInMillis();
                        }
                        arrayList2.add(Long.valueOf(timeInMillis2));
                    }
                }
            }
        }
        return hashMap;
    }

    public static void dDE_(final Intent intent, final int i, final int i2, final int i3) {
        qif.c(new ResponseCallback() { // from class: qhg
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i4, Object obj) {
                qhc.dDC_(intent, i, i2, i3, i4, (List) obj);
            }
        });
    }

    static /* synthetic */ void dDC_(Intent intent, int i, int i2, int i3, int i4, List list) {
        HashSet hashSet = new HashSet(list);
        dDF_(intent, i, i2, i3, hashSet);
        c();
        b(hashSet);
    }

    private static void dDF_(final Intent intent, int i, int i2, int i3, final Set<cbk> set) {
        LogUtil.a("MeasurePlanHelper", "hourOfDay=", Integer.valueOf(i2), ", minute=", Integer.valueOf(i3));
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i2);
        calendar.set(12, i3);
        calendar.set(13, 0);
        calendar.set(14, 0);
        long timeInMillis = calendar.getTimeInMillis();
        LogUtil.a("MeasurePlanHelper", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(timeInMillis)));
        List<Long> list = d(set).get(Integer.valueOf(i));
        if (list == null) {
            LogUtil.h("MeasurePlanHelper", "do not notify1");
            return;
        }
        long j = 86400000 + timeInMillis;
        LogUtil.a("MeasurePlanHelper", Long.valueOf(j));
        LogUtil.a("MeasurePlanHelper", list.toString());
        Iterator<Long> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().longValue() == j) {
                kor.a().a(timeInMillis - 1800000, timeInMillis + 1800000, 1, new IBaseResponseCallback() { // from class: qhj
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i4, Object obj) {
                        qhc.dDD_(intent, set, i4, obj);
                    }
                });
                return;
            }
        }
        LogUtil.h("MeasurePlanHelper", "do not notify2");
    }

    static /* synthetic */ void dDD_(Intent intent, Set set, int i, Object obj) {
        boolean e = BloodPressureSyncManager.e();
        if (obj == null && !e) {
            boolean ekw_ = sqa.ekw_(intent);
            ReleaseLogUtil.b("MeasurePlanHelper", "tryNotify isDifferentTimeZone ", Boolean.valueOf(ekw_));
            if (ekw_) {
                c();
                b(set);
                return;
            } else {
                e();
                return;
            }
        }
        LogUtil.a("MeasurePlanHelper", "not sendNotify, isSupport=", Boolean.valueOf(e));
    }

    private static void e() {
        jdh.c().a(20200319);
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        Context e = BaseApplication.e();
        Intent intent = new Intent(e, (Class<?>) KnitBloodPressureActivity.class);
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.addFlags(536870912);
        String string = e.getString(R$string.IDS_blood_pressure_notify);
        xf_.setContentTitle(e.getString(R$string.IDS_blood_pressure_measure_plan_comp)).setContentText(string).setContentIntent(PendingIntent.getActivity(e, 0, intent, 201326592));
        xf_.setAutoCancel(true);
        glq.aNY_(20200319, xf_, string);
    }

    public static String a(cbk cbkVar) {
        if (cbkVar == null) {
            LogUtil.h("MeasurePlanHelper", "alarmInfo is null");
            return "";
        }
        if (cbkVar.f() == 0) {
            return BaseApplication.e().getString(R$string.IDS_blood_pressure_get_up);
        }
        if (cbkVar.f() == 9) {
            return BaseApplication.e().getString(R$string.IDS_blood_pressure_before_sleep);
        }
        return String.format(Locale.ROOT, BaseApplication.e().getString(R$string.IDS_blood_pressure_customize_title), Integer.valueOf(cbkVar.b()));
    }

    public static Comparator<cbk> a() {
        return new Comparator() { // from class: qhi
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return qhc.d((cbk) obj, (cbk) obj2);
            }
        };
    }

    static /* synthetic */ int d(cbk cbkVar, cbk cbkVar2) {
        if (cbkVar == null || cbkVar2 == null) {
            return 0;
        }
        int a2 = cbkVar.a() - cbkVar2.a();
        return a2 != 0 ? a2 : cbkVar.e() - cbkVar2.e();
    }

    public static long b(int i, int i2) {
        Calendar d = d();
        d.set(11, i);
        d.set(12, i2);
        return d.getTimeInMillis();
    }

    public static Calendar d() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar;
    }
}
