package defpackage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.CalendarAllSyncInfo;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.CalendarDbBean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.DeviceCalendarSyncInfo;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SimpleCalendarBean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SmartSimpleCalendarBean;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes5.dex */
public class jyk {

    /* renamed from: a, reason: collision with root package name */
    private static final Lock f14200a;
    private static final Condition b;
    private static final Object c;
    private static final Object d;
    private static volatile jyk e;
    private ContentResolver f;
    private Handler g;
    private ConcurrentLinkedQueue<SimpleCalendarBean> i;
    private ContentObserver j;
    private ConcurrentLinkedQueue<List<SimpleCalendarBean>> k;
    private HandlerThread n;
    private ExtendHandler o;
    private a h = new a();
    private Map<String, Boolean> m = new HashMap(16);

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        f14200a = reentrantLock;
        d = new Object();
        c = new Object();
        b = reentrantLock.newCondition();
    }

    private jyk() {
        g();
    }

    public static jyk b() {
        jyk jykVar;
        synchronized (d) {
            if (e == null) {
                e = new jyk();
            }
            jykVar = e;
        }
        return jykVar;
    }

    private static void n() {
        synchronized (d) {
            e = null;
        }
    }

    private void g() {
        this.k = new ConcurrentLinkedQueue<>();
        this.i = new ConcurrentLinkedQueue<>();
        this.n = new HandlerThread("observe_calendar_change_thread");
        this.o = HandlerCenter.yt_(this.h, "CalendarSyncUtils");
        this.n.start();
        this.g = new Handler(this.n.getLooper());
        jyi.a();
    }

    public void c(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("CalendarSyncUtils", "storeLastUpdated: deviceId is null or empty.");
            return;
        }
        ReleaseLogUtil.e("R_CalendarSyncUtils", "storeLastUpdated.");
        List<CalendarDbBean> f = f();
        long size = f.size();
        ArrayList arrayList = new ArrayList();
        Iterator<CalendarDbBean> it = f.iterator();
        HashSet hashSet = new HashSet();
        while (it.hasNext()) {
            CalendarDbBean next = it.next();
            if (next.getOperation() == 2) {
                arrayList.add(next.getEventUuid());
                jyi.d(str2, next.getEventUuid());
                it.remove();
            } else {
                hashSet.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(hashSet);
        jyi.d(str, str2, arrayList2, size, z);
        j();
        ReleaseLogUtil.e("R_CalendarSyncUtils", "storeLastUpdated: SYNC SUCCESSFULLY. currentDeleted Size: ", Integer.valueOf(arrayList.size()), "updateContactBeans Size: ", Integer.valueOf(arrayList2.size()));
    }

    private List<CalendarDbBean> f() {
        if (this.i.isEmpty()) {
            LogUtil.a("CalendarSyncUtils", "getCurrentSynced: current update queue is empty.");
            return Collections.emptyList();
        }
        LogUtil.a("CalendarSyncUtils", "getCurrentSynced: start");
        ArrayList arrayList = new ArrayList(this.i.size());
        Iterator<SimpleCalendarBean> it = this.i.iterator();
        while (it.hasNext()) {
            SimpleCalendarBean next = it.next();
            if (next instanceof SmartSimpleCalendarBean) {
                LogUtil.a("CalendarSyncUtils", "build smartWatch CalendarVcard");
                SmartSimpleCalendarBean smartSimpleCalendarBean = (SmartSimpleCalendarBean) next;
                arrayList.add(new CalendarDbBean(smartSimpleCalendarBean.getEventId(), smartSimpleCalendarBean.getOperation(), smartSimpleCalendarBean.getEventId(), smartSimpleCalendarBean.getDtstart(), smartSimpleCalendarBean.getDtend(), smartSimpleCalendarBean.getAllDay(), jyh.d(smartSimpleCalendarBean)));
            } else {
                arrayList.add(new CalendarDbBean(next.getEventUuid(), next.getOperation(), next.getEventId(), next.getDtstart(), next.getDtend(), next.getAllDay(), jyh.a(next)));
            }
        }
        LogUtil.a("CalendarSyncUtils", "getCurrentSynced: currentUpdated list's size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public void c(String str, String str2, int i, boolean z) {
        if (!jyo.d()) {
            jyc.b().a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 6);
            LogUtil.h("CalendarSyncUtils", "syncCalendarData: no contact permission.");
            this.m.remove(str);
            return;
        }
        boolean e2 = jyi.e(str2, str);
        boolean b2 = jyo.b();
        ReleaseLogUtil.e("R_CalendarSyncUtils", "syncCalendarData： isSynchronizedOnce: ", Boolean.valueOf(e2), " ,isSyncFlag: ", Boolean.valueOf(b2));
        if (e2 && b2) {
            e(str, i, z);
        } else {
            b(str, i, str2, z);
        }
    }

    private void b(String str, int i, String str2, boolean z) {
        synchronized (c) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("CalendarSyncUtils", "syncAllContacts: deviceId is null or empty.");
                this.m.clear();
                return;
            }
            if (!jyo.d()) {
                jyc.b().a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 6);
                LogUtil.h("CalendarSyncUtils", "syncAllContacts: no contacts permissions");
                this.m.remove(str);
                return;
            }
            jyi.c(str);
            List<SimpleCalendarBean> d2 = d(str, i, z);
            ReleaseLogUtil.e("R_CalendarSyncUtils", "syncAllCalendar: ", Integer.valueOf(d2.size()));
            if (d2.isEmpty()) {
                b(str, str2, z);
                return;
            }
            if (!z && d2.size() > i) {
                d2 = d2.subList(0, i);
            }
            e(d2, str, z ? 1 : 0, 0);
        }
    }

    private void b(String str, String str2, boolean z) {
        if (!jyo.b() && TextUtils.isEmpty(str2)) {
            jyc.b().a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 4);
            this.m.remove(str);
            return;
        }
        String str3 = "calendar_data_" + System.currentTimeMillis() + ".json";
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Gson gson = new Gson();
        LogUtil.a("CalendarSyncUtils", "calendarMinor");
        CalendarAllSyncInfo calendarAllSyncInfo = new CalendarAllSyncInfo();
        calendarAllSyncInfo.setMajor(CommonUtil.a(BaseApplication.getContext(), false));
        calendarAllSyncInfo.setMinor(0L);
        calendarAllSyncInfo.setCalendarBeanList(arrayList2);
        arrayList.add(gson.toJson(calendarAllSyncInfo));
        if (!jyl.e(arrayList, jyn.e, str3, 0)) {
            LogUtil.h("CalendarSyncUtils", "clearCacheInitSync: vcard text write to file failed. ");
            this.m.remove(str);
            return;
        }
        jyc.b().d(jyn.e + str3, z);
    }

    private void e(String str, int i, boolean z) {
        synchronized (c) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("CalendarSyncUtils", "syncChangedContacts: failure, parameter is null");
                this.m.clear();
                return;
            }
            if (!jyo.d()) {
                jyc.b().a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 6);
                LogUtil.h("CalendarSyncUtils", "syncChangedContacts: have no contacts permissions");
                this.m.remove(str);
                return;
            }
            List<SimpleCalendarBean> d2 = d(str, i, z);
            if (!z && d2.size() > i) {
                d2 = d2.subList(0, i);
            }
            List<SimpleCalendarBean> d3 = jym.d(str, d2, z);
            if (d3.isEmpty()) {
                jyc.b().a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 4);
                this.m.remove(str);
            } else {
                e(d3, str, z ? 1 : 0, 1);
                ReleaseLogUtil.e("R_CalendarSyncUtils", " calendarCompareList size: ", Integer.valueOf(d3.size()));
            }
        }
    }

    private static List<SimpleCalendarBean> d(String str, int i, boolean z) {
        if (z) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            long timeInMillis = calendar.getTimeInMillis();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(5, 34);
            calendar2.set(11, 23);
            calendar2.set(12, 59);
            calendar2.set(13, 59);
            return jyh.d(BaseApplication.getContext(), str, timeInMillis, calendar2.getTimeInMillis());
        }
        return jyh.e(BaseApplication.getContext(), i);
    }

    public void b(Context context) {
        LogUtil.h("CalendarSyncUtils", "startListen enter.");
        if (context == null) {
            LogUtil.h("CalendarSyncUtils", "startListen: context or deviceId is invalid.");
        } else {
            e(context);
            jye.bKQ_(bLd_(context), bLc_(this.g));
        }
    }

    private ContentObserver bLc_(Handler handler) {
        if (this.j == null) {
            this.j = new jyd(handler) { // from class: jyk.4
            };
        }
        return this.j;
    }

    private ContentResolver bLd_(Context context) {
        if (this.f == null) {
            this.f = context.getContentResolver();
        }
        return this.f;
    }

    public void e(Context context) {
        jye.e(context);
    }

    private void e(List<SimpleCalendarBean> list, String str, int i, int i2) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("CalendarSyncUtils", "sendDeletedContactsToDevice: failure. deletedUidList is null or empty. ");
            this.m.remove(str);
        } else {
            this.k.offer(list);
            e(i2, i, str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x009b, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.h("CalendarSyncUtils", "sendZip: vcard text write to file failed. ");
        r11.m.remove(r14);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(int r12, int r13, java.lang.String r14) {
        /*
            r11 = this;
            java.util.concurrent.ConcurrentLinkedQueue<java.util.List<com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SimpleCalendarBean>> r0 = r11.k
            int r0 = r0.size()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = "sendZip: queue size: "
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            java.lang.String r1 = "R_CalendarSyncUtils"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r0)
            java.util.concurrent.locks.Lock r0 = defpackage.jyk.f14200a
            r0.lock()
            r11.e()     // Catch: java.lang.Throwable -> Ld2
        L1e:
            java.util.concurrent.ConcurrentLinkedQueue<java.util.List<com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SimpleCalendarBean>> r0 = r11.k     // Catch: java.lang.Throwable -> Ld2
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> Ld2
            if (r0 != 0) goto Lac
            java.util.concurrent.ConcurrentLinkedQueue<java.util.List<com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SimpleCalendarBean>> r0 = r11.k     // Catch: java.lang.Throwable -> Ld2
            java.lang.Object r0 = r0.poll()     // Catch: java.lang.Throwable -> Ld2
            java.util.List r0 = (java.util.List) r0     // Catch: java.lang.Throwable -> Ld2
            if (r0 == 0) goto L1e
            boolean r1 = r0.isEmpty()     // Catch: java.lang.Throwable -> Ld2
            if (r1 == 0) goto L37
            goto L1e
        L37:
            java.util.concurrent.ConcurrentLinkedQueue<com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SimpleCalendarBean> r1 = r11.i     // Catch: java.lang.Throwable -> Ld2
            r1.clear()     // Catch: java.lang.Throwable -> Ld2
            java.util.concurrent.ConcurrentLinkedQueue<com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SimpleCalendarBean> r1 = r11.i     // Catch: java.lang.Throwable -> Ld2
            r1.addAll(r0)     // Catch: java.lang.Throwable -> Ld2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld2
            r1.<init>()     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r2 = "calendar_data_"
            r1.append(r2)     // Catch: java.lang.Throwable -> Ld2
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Ld2
            r1.append(r2)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r2 = ".json"
            r1.append(r2)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Ld2
            r2 = 0
            r3 = 1
            if (r13 != r3) goto L8f
            com.google.gson.Gson r4 = new com.google.gson.Gson     // Catch: java.lang.Throwable -> Ld2
            r4.<init>()     // Catch: java.lang.Throwable -> Ld2
            long r5 = defpackage.jyi.c(r3)     // Catch: java.lang.Throwable -> Ld2
            int r7 = r0.size()     // Catch: java.lang.Throwable -> Ld2
            long r7 = (long) r7     // Catch: java.lang.Throwable -> Ld2
            com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.CalendarAllSyncInfo r9 = new com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.CalendarAllSyncInfo     // Catch: java.lang.Throwable -> Ld2
            r9.<init>()     // Catch: java.lang.Throwable -> Ld2
            android.content.Context r10 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r10 = health.compact.a.CommonUtil.a(r10, r2)     // Catch: java.lang.Throwable -> Ld2
            r9.setMajor(r10)     // Catch: java.lang.Throwable -> Ld2
            long r5 = r5 + r7
            r9.setMinor(r5)     // Catch: java.lang.Throwable -> Ld2
            r9.setCalendarBeanList(r0)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r0 = r4.toJson(r9)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r4 = defpackage.jyn.e     // Catch: java.lang.Throwable -> Ld2
            boolean r0 = defpackage.jyl.d(r0, r4, r1, r12)     // Catch: java.lang.Throwable -> Ld2
            goto L99
        L8f:
            java.util.List r0 = defpackage.jyh.c(r0, r2)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r4 = defpackage.jyn.e     // Catch: java.lang.Throwable -> Ld2
            boolean r0 = defpackage.jyl.c(r0, r4, r1, r12)     // Catch: java.lang.Throwable -> Ld2
        L99:
            if (r0 != 0) goto Lb2
            java.lang.Object[] r12 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r13 = "sendZip: vcard text write to file failed. "
            r12[r2] = r13     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r13 = "CalendarSyncUtils"
            com.huawei.hwlogsmodel.LogUtil.h(r13, r12)     // Catch: java.lang.Throwable -> Ld2
            java.util.Map<java.lang.String, java.lang.Boolean> r12 = r11.m     // Catch: java.lang.Throwable -> Ld2
            r12.remove(r14)     // Catch: java.lang.Throwable -> Ld2
        Lac:
            java.util.concurrent.locks.Lock r12 = defpackage.jyk.f14200a
            r12.unlock()
            return
        Lb2:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld2
            r0.<init>()     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r4 = defpackage.jyn.e     // Catch: java.lang.Throwable -> Ld2
            r0.append(r4)     // Catch: java.lang.Throwable -> Ld2
            r0.append(r1)     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Ld2
            jyc r1 = defpackage.jyc.b()     // Catch: java.lang.Throwable -> Ld2
            if (r13 != r3) goto Lca
            r2 = r3
        Lca:
            r1.d(r0, r2)     // Catch: java.lang.Throwable -> Ld2
            r11.k()     // Catch: java.lang.Throwable -> Ld2
            goto L1e
        Ld2:
            r12 = move-exception
            java.util.concurrent.locks.Lock r13 = defpackage.jyk.f14200a
            r13.unlock()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jyk.e(int, int, java.lang.String):void");
    }

    public void e() {
        LogUtil.a("CalendarSyncUtils", "deleteUpdatedCache.");
        kam.c(jyn.e);
    }

    public void j() {
        Lock lock = f14200a;
        lock.lock();
        try {
            b.signalAll();
            lock.unlock();
        } catch (Throwable th) {
            f14200a.unlock();
            throw th;
        }
    }

    private void k() {
        try {
            LogUtil.a("CalendarSyncUtils", "lock: is timeout: ", Boolean.valueOf(b.await(300000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e2) {
            LogUtil.b("CalendarSyncUtils", "lock: InterruptedException occurred on locking thread.", Thread.currentThread().getName(), e2.getMessage());
        }
    }

    private void h() {
        this.k.clear();
        this.i.clear();
    }

    private void i() {
        ExtendHandler extendHandler = this.o;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
        }
        this.g.removeCallbacksAndMessages(null);
    }

    public void c() {
        e();
        h();
        i();
    }

    public void a() {
        h();
        i();
        ExtendHandler extendHandler = this.o;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.o = null;
        }
        this.n.quit();
        n();
    }

    class a implements Handler.Callback {
        private a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 16) {
                return true;
            }
            LogUtil.h("CalendarSyncUtils", "handleMessage: fail in default case. what: ", Integer.valueOf(message.what));
            return false;
        }
    }

    public boolean c(DeviceInfo deviceInfo, DeviceCalendarSyncInfo deviceCalendarSyncInfo, Context context) {
        boolean z = false;
        if (deviceCalendarSyncInfo == null) {
            LogUtil.h("CalendarSyncUtils", "calendarInfo from device is empty");
            return false;
        }
        int count = deviceCalendarSyncInfo.getCount();
        List<SmartSimpleCalendarBean> scheduleList = deviceCalendarSyncInfo.getScheduleList();
        if (count == 0 || count != scheduleList.size()) {
            LogUtil.h("CalendarSyncUtils", "Event count from device is" + count + "actual scheduleList size is" + scheduleList.size());
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        ArrayList arrayList = new ArrayList(count);
        e(context);
        for (int i = 0; i < count; i++) {
            SmartSimpleCalendarBean smartSimpleCalendarBean = scheduleList.get(i);
            Object[] objArr = new Object[2];
            objArr[z ? 1 : 0] = "recieve event from smart device:";
            objArr[1] = smartSimpleCalendarBean.toString();
            LogUtil.a("CalendarSyncUtils", objArr);
            if (smartSimpleCalendarBean.getOperation() == 1) {
                if (smartSimpleCalendarBean.getDtstart() == 0 || kat.b(smartSimpleCalendarBean.getEventTimezone())) {
                    return z;
                }
                String eventUuid = smartSimpleCalendarBean.getEventUuid();
                ContentValues contentValues = new ContentValues();
                long d2 = jyh.d(context, smartSimpleCalendarBean);
                smartSimpleCalendarBean.setCalendar_id(String.valueOf(d2));
                contentValues.put("calendar_id", Long.valueOf(d2));
                contentValues.put("title", smartSimpleCalendarBean.getTitle());
                contentValues.put("allDay", Integer.valueOf(smartSimpleCalendarBean.getAllDay()));
                contentValues.put("dtstart", Long.valueOf(smartSimpleCalendarBean.getDtstart()));
                if (!kat.b(smartSimpleCalendarBean.getRule())) {
                    contentValues.put("rrule", smartSimpleCalendarBean.getRule());
                    if (!kat.b(smartSimpleCalendarBean.getDuration())) {
                        contentValues.put("duration", smartSimpleCalendarBean.getDuration());
                    }
                } else {
                    contentValues.put("dtend", Long.valueOf(smartSimpleCalendarBean.getDtend()));
                }
                contentValues.put("eventTimezone", smartSimpleCalendarBean.getEventTimezone());
                try {
                    String lastPathSegment = contentResolver.insert(CalendarContract.Events.CONTENT_URI, contentValues).getLastPathSegment();
                    String a2 = a(context, lastPathSegment);
                    smartSimpleCalendarBean.setCalendar_id(a2);
                    int i2 = (smartSimpleCalendarBean.getAllDay() == 1 && CommonUtil.bf()) ? HwExerciseConstants.NINE_MINUTES_PACE : z ? 1 : 0;
                    if (!kat.b(smartSimpleCalendarBean.getMinutes())) {
                        LogUtil.a("CalendarSyncUtils", "insert device event reminder result is " + jyh.b(context, smartSimpleCalendarBean.getMinutes() + i2, smartSimpleCalendarBean.getMethod(), lastPathSegment));
                    }
                    LogUtil.a("CalendarSyncUtils", "insert event title is ", smartSimpleCalendarBean.getTitle(), " result eventId is ", lastPathSegment, " event calendar_id: ", a2);
                    d(arrayList, smartSimpleCalendarBean, lastPathSegment);
                    String valueOf = String.valueOf(Long.parseLong(lastPathSegment));
                    jyc.b().c(valueOf, valueOf, eventUuid);
                } catch (Exception e2) {
                    LogUtil.b("CalendarSyncUtils", "insert event to phone occurs error:", e2);
                }
                z = false;
            }
        }
        String d3 = jyo.d(deviceInfo.getDeviceIdentify(), true);
        String a3 = CommonUtil.a(BaseApplication.getContext(), z);
        jyi.d(a3, d3, arrayList, count, true);
        c(d3, a3, z ? 1 : 0, true);
        b(context);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0049, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0046, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
    
        if (r7 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002a, code lost:
    
        if (r7 != null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(android.content.Context r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = "calendar_id"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            java.lang.String[] r5 = new java.lang.String[]{r10}
            java.lang.String r10 = "1"
            r7 = 0
            android.content.ContentResolver r1 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            android.net.Uri r2 = android.provider.CalendarContract.Events.CONTENT_URI     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            java.lang.String r4 = "_id = ?"
            r6 = 0
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            if (r7 == 0) goto L2a
            boolean r9 = r7.moveToFirst()     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            if (r9 == 0) goto L2a
            int r9 = r7.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            java.lang.String r10 = r7.getString(r9)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
        L2a:
            if (r7 == 0) goto L49
            goto L46
        L2d:
            r9 = move-exception
            goto L4a
        L2f:
            r9 = move-exception
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L2d
            java.lang.String r1 = "getSevenDayCalendarData: Exception."
            r2 = 0
            r0[r2] = r1     // Catch: java.lang.Throwable -> L2d
            java.lang.String r9 = com.huawei.haf.common.exception.ExceptionUtils.d(r9)     // Catch: java.lang.Throwable -> L2d
            r1 = 1
            r0[r1] = r9     // Catch: java.lang.Throwable -> L2d
            java.lang.String r9 = "CalendarSyncUtils"
            com.huawei.hwlogsmodel.LogUtil.a(r9, r0)     // Catch: java.lang.Throwable -> L2d
            if (r7 == 0) goto L49
        L46:
            r7.close()
        L49:
            return r10
        L4a:
            if (r7 == 0) goto L4f
            r7.close()
        L4f:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jyk.a(android.content.Context, java.lang.String):java.lang.String");
    }

    public Map<String, Boolean> d() {
        return this.m;
    }

    private static void d(List<CalendarDbBean> list, SmartSimpleCalendarBean smartSimpleCalendarBean, String str) {
        LogUtil.a("CalendarSyncUtils", "enter getSmartBeanFromDevice.");
        if (smartSimpleCalendarBean == null || kat.b(str)) {
            LogUtil.b("CalendarSyncUtils", "deviceCalendarBean is null or eventId is null");
            return;
        }
        smartSimpleCalendarBean.setEventId(str);
        smartSimpleCalendarBean.setEventUuid(str);
        list.add(new CalendarDbBean(smartSimpleCalendarBean.getEventId(), smartSimpleCalendarBean.getOperation(), smartSimpleCalendarBean.getEventId(), smartSimpleCalendarBean.getDtstart(), smartSimpleCalendarBean.getDtend(), smartSimpleCalendarBean.getAllDay(), jyh.d(smartSimpleCalendarBean)));
    }
}
