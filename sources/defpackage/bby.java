package defpackage;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.StaleDataException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.util.Pair;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.basichealthmodel.R$string;
import com.huawei.basichealthmodel.receiver.ReminderReceiver;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.ayj;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class bby {
    private static final Map<Integer, Integer> c;
    private static List<Integer> d;
    private static final HashMap<Integer, int[]> e;
    private static final Map<Integer, String> b = Collections.synchronizedMap(new HashMap());

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, String> f314a = Collections.synchronizedMap(new HashMap());

    public static int a(int i) {
        return i * 10000;
    }

    public static int c(int i) {
        return (i * 10000) + 9000;
    }

    private static boolean c(int i, int i2) {
        return i < 12 || i > 14 || (i == 14 && i2 > 0);
    }

    private static int f(int i) {
        if (i == 3006) {
            return 22;
        }
        if (i == 3011) {
            return 12;
        }
        return i == 3013 ? 3 : 1;
    }

    private static boolean i(int i) {
        switch (i) {
            case 10:
            case 11:
            case 12:
                return false;
            default:
                return true;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        c = hashMap;
        HashMap<Integer, int[]> hashMap2 = new HashMap<>();
        e = hashMap2;
        d = Collections.synchronizedList(new ArrayList());
        hashMap.put(1, 3000);
        Integer valueOf = Integer.valueOf(IEventListener.EVENT_ID_DEVICE_RTSP_CONN);
        hashMap.put(2, valueOf);
        Integer valueOf2 = Integer.valueOf(IEventListener.EVENT_ID_DEVICE_UPDATE);
        hashMap.put(3, valueOf2);
        hashMap.put(6, 3002);
        hashMap.put(7, 3001);
        Integer valueOf3 = Integer.valueOf(IEventListener.EVENT_ID_DEVICE_CONN_FAIL);
        hashMap.put(8, valueOf3);
        hashMap.put(5, 3003);
        Integer valueOf4 = Integer.valueOf(IEventListener.EVENT_ID_DEVICE_DISCONN_SUCC);
        hashMap.put(9, valueOf4);
        Integer valueOf5 = Integer.valueOf(IEventListener.EVENT_ID_DEVICE_SCAN_FINISH);
        hashMap.put(10, valueOf5);
        Integer valueOf6 = Integer.valueOf(IEventListener.EVENT_ID_DEVICE_DLNA_CONN_SUCC);
        hashMap.put(11, valueOf6);
        Integer valueOf7 = Integer.valueOf(IEventListener.EVENT_ID_DEVICE_REQUEST_PLAY);
        hashMap.put(13, valueOf7);
        Integer valueOf8 = Integer.valueOf(IEventListener.EVENT_ID_GRAB_STATE_CHANGED);
        hashMap.put(14, valueOf8);
        hashMap2.put(3001, new int[]{7, 202004201});
        hashMap2.put(3002, new int[]{6, 202004202});
        hashMap2.put(3003, new int[]{5, 202004203});
        hashMap2.put(valueOf3, new int[]{8, 202004204});
        hashMap2.put(valueOf4, new int[]{9, 202004206});
        hashMap2.put(valueOf5, new int[]{10, 202004207});
        hashMap2.put(valueOf, new int[]{2, 202004208});
        hashMap2.put(valueOf2, new int[]{3, 202004209});
        hashMap2.put(valueOf6, new int[]{11, 202004211});
        hashMap2.put(valueOf7, new int[]{13, 202004213});
        hashMap2.put(valueOf8, new int[]{14, 202004214});
        hashMap2.put(3000, new int[]{1, 202004200});
        hashMap2.put(4000, new int[]{202004205, 202004205});
        hashMap2.put(4001, new int[]{202004205, 202004205});
    }

    public static List<Integer> c() {
        if (d.size() == 0) {
            d = dsl.d(R.array._2130968656_res_0x7f040050);
        }
        return d;
    }

    private static void mY_(long j, int i, Intent intent, boolean z) {
        long j2 = (j / 60000) * 60000;
        ReleaseLogUtil.b("HealthLife_ReminderUtils", "registerReminder isSingle ", Boolean.valueOf(z), " requestCode ", Integer.valueOf(i), " time ", Long.valueOf(j), " timeMillis ", Long.valueOf(j2));
        if (intent == null) {
            LogUtil.h("HealthLife_ReminderUtils", "registerReminder intent is null!");
        } else if (z) {
            sqa.ekx_(i, intent, 201326592, 0, j2);
        } else {
            sqa.ekC_(i, intent, 201326592, 0, j2, 86400000L);
        }
    }

    public static void c(final boolean z, final int i, final String str) {
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelRemind_" + i, new Runnable() { // from class: bcb
                @Override // java.lang.Runnable
                public final void run() {
                    bby.c(z, i, str);
                }
            });
            return;
        }
        String str2 = (z ? f() : j()).get(Integer.valueOf(i));
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey(str2);
        hiUserPreference.setValue(str);
        if (HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference)) {
            bao.e(str2, str);
            LogUtil.a("HealthLife_ReminderUtils", "setRemindUserPreference key ", str2, " value ", str);
        }
    }

    private static void mT_(Context context, int i, Intent intent, List<String> list) {
        if (context == null || intent == null) {
            LogUtil.h("HealthLife_ReminderUtils", "context or intent is null!");
            return;
        }
        intent.setAction("HEALTHLIFE_REMINDER_" + i);
        list.add(intent.getAction());
        sqa.ekn_(i, intent, 201326592);
    }

    private static boolean c(long j) {
        return jdl.ac(j) && System.currentTimeMillis() - j > 900000;
    }

    public static void b(int i, long j, int i2, int i3) {
        if (c(j)) {
            LogUtil.h("HealthLife_ReminderUtils", "registerRepeatingReminder time ", Long.valueOf(j), " reminderId ", Integer.valueOf(i), " reminderTotalNumber ", Integer.valueOf(i2), " reminderCurrentNumber ", Integer.valueOf(i3));
            return;
        }
        int c2 = (c(i) + i3) - 1;
        mY_(j, c2, mV_(i, i2, i3), false);
        LogUtil.a("HealthLife_ReminderUtils", "registerRepeatingReminder requestCode ", Integer.valueOf(c2), " time ", Long.valueOf(j), " reminderId ", Integer.valueOf(i), " reminderTotalNumber ", Integer.valueOf(i2), " reminderCurrentNumber ", Integer.valueOf(i3));
    }

    public static void e(int i, long j, int i2, int i3) {
        d(i, j, i2, i3, new ArrayList());
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void d(int r16, long r17, int r19, int r20, java.util.List<java.lang.String> r21) {
        /*
            r0 = r16
            r1 = r20
            r11 = r21
            boolean r2 = c(r17)
            java.lang.String r12 = "HealthLife_ReminderUtils"
            if (r2 == 0) goto L3b
            java.lang.String r2 = "registerSingleReminder time "
            java.lang.Long r3 = java.lang.Long.valueOf(r17)
            java.lang.String r4 = " reminderId "
            java.lang.Integer r5 = java.lang.Integer.valueOf(r16)
            java.lang.String r6 = " reminderTotalNumber "
            java.lang.Integer r7 = java.lang.Integer.valueOf(r19)
            java.lang.String r8 = " reminderCurrentNumber "
            java.lang.Integer r9 = java.lang.Integer.valueOf(r20)
            java.lang.String r10 = " actions "
            r0 = r2
            r1 = r3
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r8
            r7 = r9
            r8 = r10
            r9 = r21
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9}
            com.huawei.hwlogsmodel.LogUtil.h(r12, r0)
            return
        L3b:
            r2 = 3000(0xbb8, float:4.204E-42)
            r3 = 1
            if (r0 != r2) goto L45
            int r2 = j(r16)
            goto L54
        L45:
            r2 = 4000(0xfa0, float:5.605E-42)
            if (r0 == r2) goto L57
            r2 = 4001(0xfa1, float:5.607E-42)
            if (r0 != r2) goto L4e
            goto L57
        L4e:
            int r2 = a(r16)
            int r2 = r2 + r1
            int r2 = r2 - r3
        L54:
            r4 = r19
            goto L5a
        L57:
            r4 = r19
            r2 = r0
        L5a:
            android.content.Intent r5 = mV_(r0, r4, r1)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "HEALTHLIFE_REMINDER_"
            r6.<init>(r7)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            r5.setAction(r6)
            if (r11 == 0) goto L78
            java.lang.String r6 = r5.getAction()
            r11.add(r6)
        L78:
            r6 = r17
            mY_(r6, r2, r5, r3)
            java.lang.String r3 = "registerSingleReminder requestCode "
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r5 = " time "
            java.lang.Long r6 = java.lang.Long.valueOf(r17)
            java.lang.String r7 = " reminderId "
            java.lang.Integer r8 = java.lang.Integer.valueOf(r16)
            java.lang.String r9 = " reminderTotalNumber "
            java.lang.Integer r10 = java.lang.Integer.valueOf(r19)
            java.lang.String r13 = " reminderCurrentNumber "
            java.lang.Integer r14 = java.lang.Integer.valueOf(r20)
            java.lang.String r15 = " actions "
            r0 = r3
            r1 = r2
            r2 = r5
            r3 = r6
            r4 = r7
            r5 = r8
            r6 = r9
            r7 = r10
            r8 = r13
            r9 = r14
            r10 = r15
            r11 = r21
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11}
            com.huawei.hwlogsmodel.LogUtil.a(r12, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bby.d(int, long, int, int, java.util.List):void");
    }

    private static Intent mV_(int i, int i2, int i3) {
        Context e2 = BaseApplication.e();
        Intent intent = new Intent(e2, (Class<?>) ReminderReceiver.class);
        String accountInfo = LoginInit.getInstance(e2).getAccountInfo(1011);
        intent.putExtra("reminderId", i);
        intent.putExtra("reminderHuid", accountInfo);
        intent.putExtra("reminderTotalNumber", i2);
        intent.putExtra("reminderCurrentNumber", i3);
        return intent;
    }

    public static void e(int i) {
        ArrayList arrayList = new ArrayList();
        Context e2 = BaseApplication.e();
        Intent intent = new Intent(e2, (Class<?>) ReminderReceiver.class);
        if (i == 3000) {
            mT_(e2, j(0), intent, arrayList);
            mT_(e2, j(1), intent, arrayList);
            return;
        }
        mT_(e2, i, intent, arrayList);
        if (i != 4000 && i != 4001) {
            mU_(e2, i, intent, arrayList);
        }
        i(arrayList);
    }

    private static void i(List<String> list) {
        if (EnvironmentInfo.r() && koq.c(list)) {
            ReleaseLogUtil.b("HealthLife_ReminderUtils", "unapplyForAlarmExemption actions ", list);
            PowerKitManager.e().a(list);
        }
    }

    private static void mU_(Context context, int i, Intent intent, List<String> list) {
        int f = f(i);
        for (int i2 = 0; i2 < f; i2++) {
            int a2 = a(i);
            int c2 = c(i);
            mT_(context, a2 + i2, intent, list);
            mT_(context, c2 + i2, intent, list);
        }
    }

    private static void c(int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        Context e2 = BaseApplication.e();
        Intent intent = new Intent(e2, (Class<?>) ReminderReceiver.class);
        mT_(e2, i, intent, arrayList);
        int f = f(i);
        int a2 = z ? a(i) : c(i);
        LogUtil.a("HealthLife_ReminderUtils", "cancelTaskReminder isSingle=", Boolean.valueOf(z), ",reminderId=", Integer.valueOf(i), ",reminderCode=", Integer.valueOf(a2), ",maxReminderSize=", Integer.valueOf(f));
        for (int i2 = 0; i2 < f; i2++) {
            mT_(e2, a2 + i2, intent, arrayList);
        }
        if (z) {
            i(arrayList);
        }
    }

    public static void d(final HealthLifeBean healthLifeBean, final boolean z, final int i, final List<Calendar> list, final Map<Integer, ayh> map) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_ReminderUtils", "setHealthLifeReminder bean is null");
            return;
        }
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelRemind_" + healthLifeBean.getId() + i, new Runnable() { // from class: bcd
                @Override // java.lang.Runnable
                public final void run() {
                    bby.d(HealthLifeBean.this, z, i, (List<Calendar>) list, (Map<Integer, ayh>) map);
                }
            });
            return;
        }
        int id = healthLifeBean.getId();
        int g = g(id);
        ayh c2 = c(map, id);
        if (!z) {
            c2.b(false);
            map.put(Integer.valueOf(id), c2);
            d(map);
            c(g, i == 1);
            return;
        }
        if (healthLifeBean.getAddStatus() != 1) {
            LogUtil.a("HealthLife_ReminderUtils", "setHealthLifeReminder bean is not added");
            if (i == 1) {
                c2.b(false);
                map.put(Integer.valueOf(id), c2);
                d(map);
            }
            c(g, i == 1);
            return;
        }
        if (koq.b(list)) {
            list = e(c2.c());
            c2.d(c(list));
        }
        c2.b(true);
        c2.d(c(list));
        map.put(Integer.valueOf(id), c2);
        d(map);
        e(g, list, i);
    }

    public static void c(final List<HealthLifeBean> list, final boolean z, final Map<Integer, ayh> map) {
        int g;
        if (list == null || map == null) {
            LogUtil.h("HealthLife_ReminderUtils", "setHealthLifeReminder list is null");
            return;
        }
        LogUtil.a("HealthLife_ReminderUtils", "setHealthLifeReminder isSingle ", Boolean.valueOf(z), " map ", map, " list ", list);
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelRemind_" + z, new Runnable() { // from class: bbz
                @Override // java.lang.Runnable
                public final void run() {
                    bby.c((List<HealthLifeBean>) list, z, (Map<Integer, ayh>) map);
                }
            });
            return;
        }
        List<Integer> c2 = c();
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean == null) {
                LogUtil.h("HealthLife_ReminderUtils", "setHealthLifeReminder bean is null");
            } else {
                int id = healthLifeBean.getId();
                if (c2.contains(Integer.valueOf(id)) && (g = g(id)) != -1) {
                    if (id != 1) {
                        ayh c3 = c(map, id);
                        if (healthLifeBean.getAddStatus() == 0) {
                            if (z) {
                                c3.b(false);
                                map.put(Integer.valueOf(id), c3);
                                d(map);
                            }
                            c(g, z);
                        } else {
                            List<Calendar> a2 = a(id, healthLifeBean, list, c3);
                            c3.d(c(a2));
                            map.put(Integer.valueOf(id), c3);
                            if (!c3.d()) {
                                c(g, z);
                            } else {
                                e(g, a2, z ? 1 : 3);
                            }
                        }
                    }
                }
            }
        }
        d(map);
    }

    private static List<Calendar> a(int i, HealthLifeBean healthLifeBean, List<HealthLifeBean> list, ayh ayhVar) {
        if (i == 6 || i == 7) {
            return bcm.c(i, healthLifeBean.getTarget());
        }
        if (i == 9) {
            return a(list, ayhVar);
        }
        return e(ayhVar.c());
    }

    private static List<Calendar> a(List<HealthLifeBean> list, ayh ayhVar) {
        String str = "";
        List<Calendar> e2 = bcm.e(9, "", ayhVar);
        if (!koq.b(e2)) {
            return e2;
        }
        HealthLifeBean healthLifeBean = bax.mv_(list).get(7);
        if (healthLifeBean != null) {
            str = healthLifeBean.getTarget();
        }
        if (!TextUtils.isEmpty(str)) {
            Calendar f = azi.f(str);
            f.add(12, -60);
            return Collections.singletonList(f);
        }
        return bcm.a(9);
    }

    public static void e(int i, List<Calendar> list, int i2) {
        LogUtil.a("HealthLife_ReminderUtils", "setReminder type ", Integer.valueOf(i2), "setReminder reminderId ", Integer.valueOf(i));
        Map<Integer, Calendar> e2 = e(i, list);
        if (e2.isEmpty()) {
            LogUtil.h("HealthLife_ReminderUtils", "setReminder calendarMap is empty");
            c(i, i2 == 1);
            return;
        }
        int intValue = ((Integer) Collections.max(e2.keySet())).intValue();
        c(i, i2 == 1);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Integer, Calendar> entry : e2.entrySet()) {
            Calendar value = entry.getValue();
            value.set(13, 0);
            value.set(14, 0);
            int intValue2 = entry.getKey().intValue();
            if (i2 == 1) {
                d(i, value.getTimeInMillis(), intValue, intValue2, arrayList);
            } else {
                if (i2 == 3) {
                    value.add(5, 1);
                }
                b(i, value.getTimeInMillis(), intValue, intValue2);
            }
        }
        d(arrayList);
    }

    private static void d(List<String> list) {
        if (EnvironmentInfo.r() && koq.c(list)) {
            ReleaseLogUtil.b("HealthLife_ReminderUtils", "applyForAlarmExemption actions ", list);
            PowerKitManager.e().e(list);
        }
    }

    public static Map<Integer, String> f() {
        if (b.size() == 0) {
            for (Integer num : c()) {
                b.put(num, "health_model_task_reminder_" + num);
            }
            Map<Integer, String> map = b;
            map.put(5, "health_model_breath_reminder");
            map.put(7, "health_model_sleep_reminder");
            map.put(6, "health_model_wake_up_reminder");
            map.put(1, "health_model_daily_summary_reminder");
            map.put(8, "health_model_smile_reminder");
        }
        return b;
    }

    public static Map<Integer, String> j() {
        if (f314a.size() <= 0) {
            for (Integer num : c()) {
                f314a.put(num, "health_model_task_reminder_" + g(num.intValue()));
            }
        }
        return f314a;
    }

    public static int g(int i) {
        Map<Integer, Integer> map = c;
        if (map.containsKey(Integer.valueOf(i))) {
            return map.get(Integer.valueOf(i)).intValue();
        }
        return -1;
    }

    public static HashMap<Integer, int[]> a() {
        return e;
    }

    private static int j(int i) {
        Calendar calendar = Calendar.getInstance();
        if (i == 1) {
            calendar.add(5, 1);
        }
        try {
            return Integer.parseInt("3000" + new SimpleDateFormat("MMdd").format(calendar.getTime()));
        } catch (NumberFormatException unused) {
            LogUtil.b("HealthLife_ReminderUtils", "generateSummaryRequestCode numberFormatException");
            return 3000;
        }
    }

    public static void d() {
        Iterator<Integer> it = e.keySet().iterator();
        while (it.hasNext()) {
            e(it.next().intValue());
        }
    }

    public static void mZ_(final List<HealthLifeBean> list, final SparseArray<HealthLifeBean> sparseArray, final Map<Integer, ayh> map) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_ReminderUtils", "setReminderForManagement beansTomorrow is empty");
            return;
        }
        LogUtil.a("HealthLife_ReminderUtils", "setReminderForManagement beansTomorrow ", list.toString());
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelRemind_" + list, new Runnable() { // from class: bcf
                @Override // java.lang.Runnable
                public final void run() {
                    bby.mZ_(list, sparseArray, map);
                }
            });
            return;
        }
        List<Integer> c2 = c();
        LogUtil.a("HealthLife_ReminderUtils", "setReminderForManagement reminderIds ", c2.toString());
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null) {
                int id = healthLifeBean.getId();
                if (c2.contains(Integer.valueOf(id)) && id != 1) {
                    int g = g(id);
                    if (!c(map, id).d()) {
                        LogUtil.h("HealthLife_ReminderUtils", "setReminderForManagement isOpenReminder is false");
                        e(g);
                    } else {
                        mX_(id, g, sparseArray, map);
                        b(healthLifeBean, g, map);
                    }
                }
            }
        }
    }

    private static void b(HealthLifeBean healthLifeBean, int i, Map<Integer, ayh> map) {
        if (healthLifeBean.getAddStatus() != 1) {
            c(i, false);
            return;
        }
        int id = healthLifeBean.getId();
        List<Calendar> e2 = bcm.e(id, healthLifeBean.getTarget(), map.get(Integer.valueOf(id)));
        if (!koq.b(e2)) {
            ayh c2 = c(map, id);
            c2.d(c(e2));
            c2.b(true);
            map.put(Integer.valueOf(id), c2);
            d(map);
        }
        e(i, e2, 3);
    }

    private static void mX_(int i, int i2, SparseArray<HealthLifeBean> sparseArray, Map<Integer, ayh> map) {
        if (sparseArray == null || sparseArray.get(i) == null || sparseArray.get(i).getAddStatus() != 1) {
            c(i2, true);
            ayh c2 = c(map, i);
            c2.b(false);
            map.put(Integer.valueOf(i), c2);
            d(map);
            return;
        }
        List<Calendar> e2 = bcm.e(i, sparseArray.get(i).getTarget(), map.get(Integer.valueOf(i)));
        if (!koq.b(e2)) {
            ayh c3 = c(map, i);
            c3.d(c(e2));
            c3.b(true);
            map.put(Integer.valueOf(i), c3);
            d(map);
        }
        e(i2, e2, 1);
    }

    public static void g() {
        bao.c("reminder_1", "");
        bao.c("reminder_7", "");
        bao.c("reminder_6", "");
        bao.c("reminder_5", "");
        bao.c("reminder_8", "");
    }

    public static Map<Integer, Calendar> e(int i, List<Calendar> list) {
        if (koq.b(list)) {
            return new HashMap(2);
        }
        Calendar calendar = list.get(0);
        if (calendar == null || !(calendar.clone() instanceof Calendar)) {
            LogUtil.h("HealthLife_ReminderUtils", "getReminderCalendarList startCalendar is null");
            return new HashMap(2);
        }
        HashMap hashMap = new HashMap();
        if (i == 3006 && koq.d(list, 1)) {
            Calendar calendar2 = (Calendar) calendar.clone();
            Calendar calendar3 = list.get(1);
            if (calendar3 == null) {
                calendar3 = (Calendar) calendar.clone();
            }
            LogUtil.a("HealthLife_ReminderUtils", "getReminderCalendarList endCalendar ", Long.valueOf(calendar3.getTimeInMillis()), "startCalendar ", Long.valueOf(calendar2.getTimeInMillis()));
            int i2 = 1;
            while (!calendar2.after(calendar3)) {
                if (c(calendar2.get(11), calendar2.get(12))) {
                    Calendar calendar4 = Calendar.getInstance();
                    calendar4.setTimeInMillis(calendar2.getTimeInMillis());
                    hashMap.put(Integer.valueOf(i2), calendar4);
                }
                calendar2.add(11, 1);
                i2++;
            }
            return hashMap;
        }
        for (int i3 = 1; i3 <= list.size(); i3++) {
            hashMap.put(Integer.valueOf(i3), list.get(i3 - 1));
        }
        return hashMap;
    }

    public static void i() {
        if (jfa.a()) {
            if (HandlerExecutor.c()) {
                azi.b(ThreadPoolManager.d(), "HealthModelRemind_", new Runnable() { // from class: bce
                    @Override // java.lang.Runnable
                    public final void run() {
                        bby.i();
                    }
                });
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add("health_model_reminder");
            List<HiUserPreference> userPreferenceList = HiHealthManager.d(BaseApplication.e()).getUserPreferenceList(arrayList);
            if (!koq.b(userPreferenceList) && userPreferenceList.get(0) != null && !TextUtils.isEmpty(userPreferenceList.get(0).getValue())) {
                LogUtil.a("HealthLife_ReminderUtils", "syncSaveAllReminder hiUserPreference ", userPreferenceList);
                try {
                    HashMap hashMap = (HashMap) HiJsonUtil.b(userPreferenceList.get(0).getValue(), new TypeToken<HashMap<Integer, ayj>>() { // from class: bby.1
                    }.getType());
                    HashMap hashMap2 = new HashMap();
                    e(hashMap2, hashMap);
                    bao.e("health_model_reminder", HiJsonUtil.e(hashMap2));
                    return;
                } catch (JsonParseException | IllegalStateException e2) {
                    LogUtil.b("HealthLife_ReminderUtils", "syncSaveAllReminder exception ", LogAnonymous.b(e2));
                }
            }
            HashMap hashMap3 = new HashMap();
            c(n(), hashMap3);
            String d2 = HiJsonUtil.d(hashMap3, new TypeToken<HashMap<Integer, ayj>>() { // from class: bby.5
            }.getType());
            if (TextUtils.isEmpty(d2)) {
                LogUtil.h("HealthLife_ReminderUtils", "syncSaveAllReminder value ", d2);
                return;
            }
            HiUserPreference hiUserPreference = new HiUserPreference();
            hiUserPreference.setKey("health_model_reminder");
            hiUserPreference.setValue(d2);
            if (HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference)) {
                HashMap hashMap4 = new HashMap();
                e(hashMap4, hashMap3);
                bao.e("health_model_reminder", HiJsonUtil.e(hashMap4));
                azi.an();
            }
            LogUtil.a("HealthLife_ReminderUtils", "syncSaveAllReminder key ", "health_model_reminder", " value ", d2);
        }
    }

    public static void h() {
        Map map;
        if (jfa.a()) {
            if (HandlerExecutor.c()) {
                azi.b(ThreadPoolManager.d(), "HealthModelRemind_", new Runnable() { // from class: bbw
                    @Override // java.lang.Runnable
                    public final void run() {
                        bby.h();
                    }
                });
                return;
            }
            String e2 = bao.e("health_model_reminder");
            if (TextUtils.isEmpty(e2)) {
                LogUtil.h("HealthLife_ReminderUtils", "syncHiUserPreference value is empty");
                return;
            }
            try {
                map = (Map) HiJsonUtil.b(e2, new TypeToken<HashMap<Integer, ayh>>() { // from class: bby.2
                }.getType());
            } catch (JsonParseException | IllegalStateException e3) {
                LogUtil.b("HealthLife_ReminderUtils", "syncHiUserPreference exception ", LogAnonymous.b(e3));
                map = null;
            }
            if (map == null || koq.b(map.entrySet())) {
                LogUtil.h("HealthLife_ReminderUtils", "syncHiUserPreference map is empty");
                return;
            }
            HashMap hashMap = new HashMap();
            c((Map<Integer, ayh>) map, hashMap);
            if (koq.b(hashMap.entrySet())) {
                LogUtil.h("HealthLife_ReminderUtils", "syncHiUserPreference beanMap is empty");
                return;
            }
            String e4 = HiJsonUtil.e(hashMap);
            HiUserPreference hiUserPreference = new HiUserPreference();
            hiUserPreference.setKey("health_model_reminder");
            hiUserPreference.setValue(e4);
            boolean userPreference = HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
            if (userPreference) {
                azi.an();
            }
            LogUtil.a("HealthLife_ReminderUtils", "syncHiUserPreference isSuccess ", Boolean.valueOf(userPreference), " key ", "health_model_reminder", " value ", e4);
        }
    }

    private static Map<Integer, ayh> n() {
        HashMap hashMap = new HashMap();
        List<Integer> c2 = c();
        if (koq.b(c2)) {
            LogUtil.a("HealthLife_ReminderUtils", "getReminder idList  ", c2);
            return hashMap;
        }
        Iterator<Integer> it = c2.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue == 1) {
                LogUtil.a("HealthLife_ReminderUtils", "convertCloudList task is Summary ");
            } else {
                hashMap.put(Integer.valueOf(intValue), new ayh(d(intValue), c(a(intValue, true))));
            }
        }
        return hashMap;
    }

    public static List<Calendar> b(String str) {
        String[] split;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_ReminderUtils", "convertTimestampInterval timestampInterval is empty");
            return new ArrayList(2);
        }
        ArrayList arrayList = new ArrayList(2);
        if (str.contains(Constants.LINK)) {
            split = str.split(Constants.LINK);
        } else {
            split = str.split(",");
        }
        for (String str2 : split) {
            long h = nsn.h(str2);
            if (h == 0) {
                LogUtil.h("HealthLife_ReminderUtils", "convertTimestampInterval timestamp is invalid");
                return new ArrayList(2);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(h);
            arrayList.add(azi.a(calendar));
        }
        return arrayList;
    }

    public static List<Calendar> a(int i, boolean z) {
        final String str = j().get(Integer.valueOf(i));
        List<Calendar> b2 = b(bao.e(str));
        if (koq.b(b2)) {
            azi.b(ThreadPoolManager.d(), "HealthModelRemind_" + i, new Runnable() { // from class: bca
                @Override // java.lang.Runnable
                public final void run() {
                    bby.a(str);
                }
            });
            LogUtil.h("HealthLife_ReminderUtils", "getAlarmTimeById calendarList is empty id ", Integer.valueOf(i));
            if (z) {
                return bcm.a(i);
            }
        }
        return b2;
    }

    static /* synthetic */ void a(String str) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference(str);
        if (userPreference == null || TextUtils.isEmpty(userPreference.getValue())) {
            return;
        }
        bao.e(str, userPreference.getValue());
    }

    public static boolean d(int i) {
        String str = f().get(Integer.valueOf(i));
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference(str);
        LogUtil.a("HealthLife_ReminderUtils", "getAlarmSwitchStatus hiUserPreference ", userPreference, " key ", str, " value ", userPreference != null ? userPreference.getValue() : "");
        return !TextUtils.isEmpty(r6);
    }

    public static Map<Integer, ayh> e() {
        if (!jfa.a()) {
            return new HashMap();
        }
        Map<Integer, ayh> b2 = b();
        if (b2 != null && !koq.b(b2.entrySet())) {
            return b2;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("health_model_reminder");
        List<HiUserPreference> userPreferenceList = HiHealthManager.d(BaseApplication.e()).getUserPreferenceList(arrayList);
        if (koq.b(userPreferenceList) || userPreferenceList.get(0) == null || TextUtils.isEmpty(userPreferenceList.get(0).getValue())) {
            LogUtil.h("HealthLife_ReminderUtils", "getAllReminder hiUserPreference ", userPreferenceList);
            return n();
        }
        LogUtil.a("HealthLife_ReminderUtils", "getAllReminder map value ", userPreferenceList.get(0).getValue());
        try {
            Map map = (Map) HiJsonUtil.b(userPreferenceList.get(0).getValue(), new TypeToken<HashMap<Integer, ayj>>() { // from class: bby.4
            }.getType());
            if (map != null && !koq.b(map.entrySet())) {
                if (b2 == null) {
                    b2 = new HashMap<>();
                }
                e(b2, (Map<Integer, ayj>) map);
                return b2;
            }
            LogUtil.h("HealthLife_ReminderUtils", "getAllReminder cloudBeanMap ", map);
            return n();
        } catch (JsonParseException | IllegalStateException | NullPointerException e2) {
            LogUtil.b("HealthLife_ReminderUtils", "getAllReminder exception ", LogAnonymous.b(e2));
            return n();
        }
    }

    public static Map<Integer, ayh> b() {
        if (!jfa.a()) {
            return new HashMap();
        }
        String e2 = bao.e("health_model_reminder");
        if (TextUtils.isEmpty(e2)) {
            LogUtil.h("HealthLife_ReminderUtils", "getSpAllReminder value ", e2);
            return new HashMap();
        }
        try {
            Map<Integer, ayh> map = (Map) HiJsonUtil.b(e2, new TypeToken<HashMap<Integer, ayh>>() { // from class: bby.3
            }.getType());
            List<cbk> b2 = azi.b(bao.e("bloodPressureMeasurePlan"));
            if (koq.b(b2)) {
                LogUtil.h("HealthLife_ReminderUtils", "getSpAllReminder bloodPressureAlarmInfoList ", b2);
                return map;
            }
            ArrayList arrayList = new ArrayList();
            for (cbk cbkVar : b2) {
                if (cbkVar != null) {
                    wq wqVar = new wq();
                    wqVar.a(cbkVar.a());
                    wqVar.c(cbkVar.e());
                    wqVar.b(cbkVar.i());
                    wqVar.b(cbkVar.d());
                    arrayList.add(wqVar);
                }
            }
            if (koq.b(arrayList)) {
                LogUtil.h("HealthLife_ReminderUtils", "getSpAllReminder list ", arrayList);
                return map;
            }
            map.put(12, new ayh(((wq) arrayList.get(0)).i(), arrayList));
            return map;
        } catch (JsonParseException | IllegalStateException | NullPointerException e3) {
            LogUtil.b("HealthLife_ReminderUtils", "getSpAllReminder exception ", ExceptionUtils.d(e3));
            return new HashMap();
        }
    }

    private static Map<Integer, List<Long>> m() {
        ayh value;
        Map<Integer, ayh> b2 = b();
        if (b2 == null) {
            return new HashMap();
        }
        long currentTimeMillis = System.currentTimeMillis();
        HashMap hashMap = new HashMap();
        for (Map.Entry<Integer, ayh> entry : b2.entrySet()) {
            if (entry != null && (value = entry.getValue()) != null) {
                List<wq> c2 = value.c();
                if (!koq.b(c2)) {
                    int intValue = entry.getKey().intValue();
                    if (intValue == 10) {
                        List<Long> b3 = b(c2, currentTimeMillis);
                        if (!koq.b(b3)) {
                            hashMap.put(Integer.valueOf(intValue), b3);
                        }
                    } else {
                        ArrayList arrayList = new ArrayList();
                        for (wq wqVar : c2) {
                            if (wqVar != null) {
                                arrayList.add(Long.valueOf(jdl.e(currentTimeMillis, wqVar.a(), wqVar.e())));
                            }
                        }
                        if (!koq.b(arrayList)) {
                            hashMap.put(Integer.valueOf(intValue), arrayList);
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    private static List<Long> b(List<wq> list, long j) {
        if (koq.b(list, 1)) {
            return Collections.emptyList();
        }
        wq wqVar = list.get(0);
        wq wqVar2 = list.get(1);
        if (wqVar == null || wqVar2 == null) {
            return Collections.emptyList();
        }
        long e2 = jdl.e(j, wqVar.a(), wqVar.e());
        long e3 = jdl.e(j, wqVar2.a(), wqVar2.e());
        if (Long.compare(e3, e2) != 1) {
            e3 = e2;
            e2 = e3;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(e2);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(e3);
        ArrayList arrayList = new ArrayList();
        while (!calendar.after(calendar2)) {
            if (c(calendar.get(11), calendar.get(12))) {
                arrayList.add(Long.valueOf(calendar.getTimeInMillis()));
            }
            calendar.add(11, 1);
        }
        return arrayList;
    }

    public static List<wq> c(List<Calendar> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        for (Calendar calendar : list) {
            if (calendar != null) {
                wq wqVar = new wq();
                wqVar.a(calendar.get(11));
                wqVar.c(calendar.get(12));
                wqVar.b(127);
                arrayList.add(wqVar);
            }
        }
        return arrayList;
    }

    public static void d(Map<Integer, ayh> map) {
        if (jfa.a()) {
            String d2 = HiJsonUtil.d(map, new TypeToken<HashMap<Object, ayh>>() { // from class: bby.7
            }.getType());
            if (TextUtils.isEmpty(d2)) {
                LogUtil.h("HealthLife_ReminderUtils", "updateSingeTaskReminder value ", d2);
            } else {
                bao.e("health_model_reminder", d2);
                LogUtil.a("HealthLife_ReminderUtils", "updateSingeTaskReminder value ", d2);
            }
        }
    }

    public static List<Calendar> e(List<wq> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        for (wq wqVar : list) {
            if (wqVar != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(11, wqVar.a());
                calendar.set(12, wqVar.e());
                arrayList.add(calendar);
            }
        }
        return arrayList;
    }

    public static ayh c(Map<Integer, ayh> map, int i) {
        if (map == null) {
            ReleaseLogUtil.a("R_HealthLife_ReminderUtils", "getHealthModeModelReminderBean map is null");
            return new ayh(false, new ArrayList());
        }
        ayh ayhVar = map.get(Integer.valueOf(i));
        if (ayhVar == null) {
            ReleaseLogUtil.a("R_HealthLife_ReminderUtils", "getHealthModeModelReminderBean bean is null taskId ", Integer.valueOf(i));
            return new ayh(false, new ArrayList());
        }
        return new ayh(ayhVar.d(), ayhVar.c());
    }

    private static void c(Map<Integer, ayh> map, Map<Integer, ayj> map2) {
        LogUtil.a("HealthLife_ReminderUtils", "convertCloudList enter");
        if (map == null || koq.b(map.entrySet())) {
            LogUtil.h("HealthLife_ReminderUtils", "convertCloudList map is fail");
            return;
        }
        for (Map.Entry<Integer, ayh> entry : map.entrySet()) {
            ayh value = entry.getValue();
            if (value == null || entry.getKey().intValue() == 1) {
                LogUtil.a("HealthLife_ReminderUtils", "convertCloudList modeReminderBean is null or task is Summary ");
            } else {
                ArrayList arrayList = new ArrayList();
                for (wq wqVar : value.c()) {
                    if (wqVar != null) {
                        arrayList.add(new ayj.d(wqVar.a(), wqVar.e()));
                    }
                }
                map2.put(entry.getKey(), new ayj(value.d(), arrayList));
            }
        }
        LogUtil.a("HealthLife_ReminderUtils", "convertCloudList cloudBeanMap is ", map2.toString());
    }

    private static void e(Map<Integer, ayh> map, Map<Integer, ayj> map2) {
        if (map2 == null || koq.b(map2.entrySet())) {
            LogUtil.h("HealthLife_ReminderUtils", "convertAlarmInfoList cloudBeanMap is fail");
            return;
        }
        for (Map.Entry<Integer, ayj> entry : map2.entrySet()) {
            ayj value = entry.getValue();
            if (value != null) {
                ArrayList arrayList = new ArrayList();
                for (ayj.d dVar : value.b()) {
                    if (dVar != null) {
                        wq wqVar = new wq();
                        wqVar.c(dVar.c());
                        wqVar.a(dVar.a());
                        wqVar.b(127);
                        arrayList.add(wqVar);
                    }
                }
                map.put(entry.getKey(), new ayh(value.c(), arrayList));
            }
        }
        LogUtil.a("HealthLife_ReminderUtils", "convertAlarmInfoList map is ", map.toString());
    }

    public static boolean c(String str) {
        Cursor query;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_ReminderUtils", "isAppPushEnable package is empty");
            return false;
        }
        try {
            query = BaseApplication.e().getContentResolver().query(Uri.parse(jdz.j), null, null, null, null);
            try {
                LogUtil.a("HealthLife_ReminderUtils", jdz.a("NotificationList", null, null, null, null));
            } finally {
            }
        } catch (SQLException | StaleDataException | IllegalArgumentException e2) {
            LogUtil.a("HealthLife_ReminderUtils", "isAppPushEnable Exception: ", LogAnonymous.b(e2));
        }
        if (query != null && query.getCount() != 0) {
            while (query.moveToNext()) {
                Integer bGi_ = jdz.bGi_("name", query, "HealthLife_ReminderUtils");
                if (bGi_ == null) {
                    LogUtil.h("HealthLife_ReminderUtils", "isAppPushEnable columnIndex is null");
                    if (query != null) {
                        query.close();
                    }
                    return false;
                }
                if (str.equals(query.getString(bGi_.intValue()))) {
                    if (query != null) {
                        query.close();
                    }
                    return true;
                }
            }
            if (query != null) {
                query.close();
            }
            return false;
        }
        LogUtil.h("HealthLife_ReminderUtils", "isAppPushEnable cursor is null");
        if (query != null) {
            query.close();
        }
        return false;
    }

    public static String b(int i) {
        int i2;
        switch (i) {
            case 2:
                i2 = R$string.IDS_reminder_20;
                break;
            case 3:
                i2 = R$string.IDS_reminder_33;
                break;
            case 4:
            case 11:
            case 12:
            default:
                i2 = 0;
                break;
            case 5:
                i2 = R$string.IDS_reminder_54;
                break;
            case 6:
                i2 = R$string.IDS_reminder_66;
                break;
            case 7:
                i2 = R$string.IDS_reminder_71;
                break;
            case 8:
                i2 = R$string.IDS_reminder_82;
                break;
            case 9:
                i2 = R$string.IDS_reminder_92;
                break;
            case 10:
                i2 = R$string.IDS_reminder_100;
                break;
            case 13:
                i2 = R$string.IDS_reminder_130;
                break;
            case 14:
                i2 = R$string.IDS_reminder_140;
                break;
        }
        return nsf.h(i2);
    }

    private static void n(int i) {
        Map<Integer, List<Long>> k = k();
        if (k == null || k.size() <= 0) {
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            arrayList.add(Long.valueOf(System.currentTimeMillis()));
            hashMap.put(Integer.valueOf(i), arrayList);
            bao.e("health_life_colver_reminder_time", HiJsonUtil.e(hashMap));
            return;
        }
        HashMap hashMap2 = new HashMap();
        long currentTimeMillis = System.currentTimeMillis();
        int b2 = DateFormatUtil.b(currentTimeMillis);
        for (Map.Entry<Integer, List<Long>> entry : k.entrySet()) {
            if (entry != null) {
                int intValue = entry.getKey().intValue();
                List<Long> value = entry.getValue();
                ArrayList arrayList2 = new ArrayList();
                if (koq.c(value)) {
                    for (Long l : value) {
                        if (DateFormatUtil.b(l.longValue()) == b2) {
                            arrayList2.add(l);
                        }
                    }
                }
                if (intValue == i) {
                    arrayList2.add(Long.valueOf(currentTimeMillis));
                }
                if (!koq.b(arrayList2)) {
                    hashMap2.put(Integer.valueOf(intValue), arrayList2);
                }
            }
        }
        if (!hashMap2.containsKey(Integer.valueOf(i))) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(Long.valueOf(currentTimeMillis));
            hashMap2.put(Integer.valueOf(i), arrayList3);
        }
        bao.e("health_life_colver_reminder_time", HiJsonUtil.e(hashMap2));
    }

    private static Map<Integer, List<Long>> k() {
        String e2 = bao.e("health_life_colver_reminder_time");
        if (TextUtils.isEmpty(e2)) {
            return new HashMap();
        }
        try {
            return (Map) HiJsonUtil.b(e2, new TypeToken<Map<Integer, List<Long>>>() { // from class: bby.9
            }.getType());
        } catch (JsonParseException | IllegalStateException e3) {
            LogUtil.b("HealthLife_ReminderUtils", "getReminderTimeList exception ", ExceptionUtils.d(e3));
            return new HashMap();
        }
    }

    private static List<Integer> b(List<HealthLifeBean> list) {
        if (koq.b(list)) {
            return Collections.emptyList();
        }
        ArrayList<Integer> arrayList = new ArrayList();
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null && healthLifeBean.getComplete() <= 0) {
                arrayList.add(Integer.valueOf(healthLifeBean.getId()));
            }
        }
        Map<Integer, List<Long>> k = k();
        if (k == null) {
            return arrayList;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int b2 = DateFormatUtil.b(currentTimeMillis);
        ArrayList arrayList2 = new ArrayList();
        for (Integer num : arrayList) {
            List<Long> list2 = k.get(num);
            if (koq.b(list2)) {
                arrayList2.add(num);
            } else {
                boolean i = i(num.intValue());
                Iterator<Long> it = list2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Long next = it.next();
                        if (DateFormatUtil.b(next.longValue()) != b2 || (!i && Math.abs(currentTimeMillis - next.longValue()) >= 3600000)) {
                        }
                    } else {
                        arrayList2.add(num);
                        break;
                    }
                }
            }
        }
        return arrayList2;
    }

    private static Map<Integer, Long> f(List<HealthLifeBean> list) {
        List<Integer> b2 = b(list);
        if (koq.b(b2)) {
            return new HashMap();
        }
        Map<Integer, List<Long>> m = m();
        HashMap hashMap = new HashMap();
        long currentTimeMillis = System.currentTimeMillis();
        for (Map.Entry<Integer, List<Long>> entry : m.entrySet()) {
            if (entry != null) {
                int intValue = entry.getKey().intValue();
                if (b2.contains(Integer.valueOf(intValue))) {
                    List<Long> value = entry.getValue();
                    if (!koq.b(value)) {
                        if (intValue == 13) {
                            long longValue = value.get(value.size() - 1).longValue();
                            long abs = Math.abs(currentTimeMillis - longValue);
                            if (!hashMap.containsKey(Integer.valueOf(intValue)) && abs < 3600000) {
                                hashMap.put(Integer.valueOf(intValue), Long.valueOf(longValue));
                            }
                        } else {
                            boolean i = i(intValue);
                            Iterator<Long> it = value.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    Long next = it.next();
                                    long abs2 = Math.abs(currentTimeMillis - next.longValue());
                                    if (i) {
                                        if (!hashMap.containsKey(Integer.valueOf(intValue)) && abs2 < 3600000) {
                                            hashMap.put(Integer.valueOf(intValue), next);
                                            break;
                                        }
                                    } else if (!hashMap.containsKey(Integer.valueOf(intValue)) && abs2 < 1800000) {
                                        hashMap.put(Integer.valueOf(intValue), next);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    private static Pair<Integer, Long> h(List<HealthLifeBean> list) {
        List<Map.Entry<Integer, Long>> c2 = c(f(list));
        if (koq.b(c2)) {
            return o();
        }
        long currentTimeMillis = System.currentTimeMillis();
        HashMap hashMap = new HashMap();
        long j = currentTimeMillis;
        for (Map.Entry<Integer, Long> entry : c2) {
            if (entry != null) {
                long longValue = entry.getValue().longValue();
                long abs = Math.abs(currentTimeMillis - longValue);
                if (abs <= j) {
                    hashMap.put(entry.getKey(), Long.valueOf(longValue));
                    j = abs;
                }
            }
        }
        List<Map.Entry<Integer, Long>> c3 = c(hashMap);
        if (koq.b(c3)) {
            return o();
        }
        Map.Entry<Integer, Long> entry2 = c3.get(nsg.b(c3.size()));
        if (entry2 == null) {
            LogUtil.h("HealthLife_ReminderUtils", "getRemindTimeMap entry is null list ", c3);
            return o();
        }
        return new Pair<>(entry2.getKey(), entry2.getValue());
    }

    private static Pair<Integer, Long> o() {
        List<Integer> b2 = b(bda.d());
        if (koq.b(b2)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Integer num : b2) {
            if (num.intValue() != 2 && num.intValue() != 3) {
                arrayList.add(num);
            }
        }
        if (koq.b(arrayList)) {
            return null;
        }
        Map<Integer, List<Long>> m = m();
        HashMap hashMap = new HashMap();
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<Map.Entry<Integer, List<Long>>> it = m.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<Integer, List<Long>> next = it.next();
            if (next != null) {
                int intValue = next.getKey().intValue();
                if (arrayList.contains(Integer.valueOf(intValue))) {
                    List<Long> value = next.getValue();
                    if (!koq.b(value)) {
                        long longValue = value.get(value.size() - 1).longValue();
                        boolean z = intValue == 13;
                        long j = currentTimeMillis;
                        long j2 = 0;
                        for (Long l : value) {
                            long longValue2 = l.longValue();
                            if (z) {
                                longValue2 = longValue;
                            }
                            if (longValue2 <= currentTimeMillis) {
                                long j3 = currentTimeMillis - longValue2;
                                if (j3 <= j) {
                                    j2 = l.longValue();
                                    j = j3;
                                }
                            }
                        }
                        if (j2 > 0) {
                            hashMap.put(Integer.valueOf(intValue), Long.valueOf(j2));
                        }
                    }
                }
            }
        }
        List<Map.Entry<Integer, Long>> c2 = c(hashMap);
        if (koq.b(c2)) {
            return null;
        }
        Map.Entry<Integer, Long> entry = c2.get(c2.size() - 1);
        if (entry == null) {
            LogUtil.h("HealthLife_ReminderUtils", "getDefaultReminderIdMap entry is null list ", c2);
            return null;
        }
        return new Pair<>(entry.getKey(), entry.getValue());
    }

    private static List<Map.Entry<Integer, Long>> c(Map<Integer, Long> map) {
        if (map == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<Integer, Long>>() { // from class: bby.6
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<Integer, Long> entry, Map.Entry<Integer, Long> entry2) {
                return Long.compare(entry != null ? entry.getValue().longValue() : 0L, entry2 != null ? entry2.getValue().longValue() : 0L);
            }
        });
        return arrayList;
    }

    private static String h(int i) {
        ArrayList arrayList = new ArrayList();
        switch (i) {
            case 2:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_2_1));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_2_2));
                break;
            case 3:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_3_1));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_3_2));
                break;
            case 4:
            default:
                LogUtil.h("HealthLife_ReminderUtils", "getRemindTip id ", Integer.valueOf(i));
                break;
            case 5:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_5_1));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_5_2));
                break;
            case 6:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_6_1));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_6_2));
                break;
            case 7:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_7_1));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_7_2));
                break;
            case 8:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_8_1));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_8_2));
                break;
            case 9:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_9_1));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_9_2));
                break;
            case 10:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_10_1));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_10_2));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_10_3));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_10_4));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_10_5));
                break;
            case 11:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_11_1));
                break;
            case 12:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_12_1));
                break;
            case 13:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_13_1));
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_13_2));
                break;
            case 14:
                arrayList.add(Integer.valueOf(R$string.IDS_life_task_reminder_14_1));
                break;
        }
        if (koq.b(arrayList)) {
            LogUtil.h("HealthLife_ReminderUtils", "getRemindTip list ", arrayList, " id ", Integer.valueOf(i));
            return "";
        }
        return nsf.h(((Integer) arrayList.get(nsg.b(arrayList.size()))).intValue());
    }

    public static Pair<Integer, String> a(List<HealthLifeBean> list) {
        Pair<Integer, Long> h = h(list);
        if (h == null) {
            return new Pair<>(-1, "");
        }
        Integer num = h.first;
        int intValue = num != null ? num.intValue() : -1;
        Long l = h.second;
        LogUtil.a("HealthLife_ReminderUtils", "getRemindTipMap id ", Integer.valueOf(intValue), " timeMillis ", Long.valueOf(l != null ? l.longValue() : -1L));
        if (intValue <= -1) {
            return new Pair<>(-1, "");
        }
        n(intValue);
        return new Pair<>(Integer.valueOf(intValue), h(intValue));
    }
}
