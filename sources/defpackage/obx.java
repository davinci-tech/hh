package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.datatype.EventAlarmInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes6.dex */
public class obx implements Serializable {
    private static String b = "";
    private int c = 0;
    private int d = 0;
    private int n = 0;
    private int h = 0;
    private String g = null;

    /* renamed from: a, reason: collision with root package name */
    private String f15607a = null;
    private String f = null;
    private int e = 0;
    private int m = 1;
    private int o = 0;
    private long i = 0;
    private long j = 0;

    public void b(long j) {
        this.i = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public void d(long j) {
        this.j = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int e() {
        return ((Integer) jdy.d(Integer.valueOf(this.c))).intValue();
    }

    public void b(int i) {
        this.c = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int d() {
        return ((Integer) jdy.d(Integer.valueOf(this.d))).intValue();
    }

    public void d(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String g() {
        return (String) jdy.d(this.g);
    }

    public void d(String str) {
        this.g = (String) jdy.d(str);
    }

    public String a() {
        return (String) jdy.d(this.f15607a);
    }

    public void e(String str) {
        this.f15607a = (String) jdy.d(str);
    }

    public String b() {
        return (String) jdy.d(this.f);
    }

    public void c(String str) {
        this.f = (String) jdy.d(str);
    }

    public int h() {
        return ((Integer) jdy.d(Integer.valueOf(this.n))).intValue();
    }

    public void c(int i) {
        this.n = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int j() {
        return ((Integer) jdy.d(Integer.valueOf(this.h))).intValue();
    }

    public void e(int i) {
        this.h = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int f() {
        return ((Integer) jdy.d(Integer.valueOf(this.m))).intValue();
    }

    public void g(int i) {
        this.m = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int c() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public String toString() {
        return "AlarmListItem{alarmEnable=" + this.c + ", alarmDbTime=" + this.d + ", repeat=" + this.n + ", eventIndex=" + this.h + ", alarmTime='" + this.g + "', alarmContent='" + this.f15607a + "', alarmRepeat='" + this.f + "', type=" + this.m + ",  alarmLegalHolidayEnable=" + this.e + ",needSendAlarm=" + this.o + ", createTimestamp=" + this.i + ", modifiedTimestamp=" + this.j + '}';
    }

    public obx a(obx obxVar, EventAlarmInfo eventAlarmInfo, oal oalVar, Context context) {
        if (eventAlarmInfo != null && oalVar != null && context != null) {
            String b2 = oal.b(context, (eventAlarmInfo.getEventAlarmStartTimeHour() * 100) + eventAlarmInfo.getEventAlarmStartTimeMin());
            LogUtil.a("AlarmListItem", "strTime ", b2);
            obxVar.b(eventAlarmInfo.getEventAlarmEnable());
            obxVar.d((eventAlarmInfo.getEventAlarmStartTimeHour() * 100) + eventAlarmInfo.getEventAlarmStartTimeMin());
            obxVar.d(b2);
            obxVar.e(eventAlarmInfo.getEventAlarmName());
            String b3 = oalVar.b(eventAlarmInfo.getEventAlarmRepeat());
            obxVar.c(b3);
            obxVar.e(eventAlarmInfo.getEventAlarmIndex());
            obxVar.g(1);
            LogUtil.a("AlarmListItem", "weekRepeat", b3);
            obxVar.c(eventAlarmInfo.getEventAlarmRepeat());
            obxVar.a(eventAlarmInfo.getEventAlarmLegalHolidayEnable());
            obxVar.b(eventAlarmInfo.getCreateTimestamp());
            obxVar.d(eventAlarmInfo.getModifiedTimestamp());
        }
        return obxVar;
    }

    public obx b(obx obxVar, EventAlarmInfo eventAlarmInfo, oal oalVar, List<EventAlarmInfo> list, int i) {
        if (eventAlarmInfo != null && oalVar != null && list != null) {
            LogUtil.a("AlarmListItem", "EventAlarmStartTime ", Integer.valueOf((eventAlarmInfo.getEventAlarmStartTimeHour() * 100) + eventAlarmInfo.getEventAlarmStartTimeMin()));
            LogUtil.a("AlarmListItem", "EventAlarmInfo ", eventAlarmInfo.toString());
            DeviceCapability e = cvs.e(b);
            boolean isSupportChangeAlarm = e != null ? e.isSupportChangeAlarm() : false;
            LogUtil.a("AlarmListItem", "bIsSupportChangeAlarm is ", Boolean.valueOf(isSupportChangeAlarm));
            if (isSupportChangeAlarm) {
                LogUtil.a("AlarmListItem", "device is support modify");
                obxVar.b(eventAlarmInfo.getEventAlarmEnable());
            } else if (eventAlarmInfo.getEventAlarmRepeat() == 0 && eventAlarmInfo.getEventAlarmEnable() == 1) {
                int e2 = e(eventAlarmInfo, BaseApplication.e());
                obxVar.b(e2);
                if (e2 == 0) {
                    this.o = 1;
                    a(list, i, e2);
                }
            } else {
                obxVar.b(eventAlarmInfo.getEventAlarmEnable());
            }
            String b2 = oal.b(BaseApplication.e(), (eventAlarmInfo.getEventAlarmStartTimeHour() * 100) + eventAlarmInfo.getEventAlarmStartTimeMin());
            obxVar.d((eventAlarmInfo.getEventAlarmStartTimeHour() * 100) + eventAlarmInfo.getEventAlarmStartTimeMin());
            obxVar.d(b2);
            obxVar.e(eventAlarmInfo.getEventAlarmName());
            obxVar.c(eventAlarmInfo.getEventAlarmRepeat());
            obxVar.c(oalVar.b(eventAlarmInfo.getEventAlarmRepeat()));
            obxVar.g(1);
            obxVar.e(eventAlarmInfo.getEventAlarmIndex());
            obxVar.a(eventAlarmInfo.getEventAlarmLegalHolidayEnable());
            obxVar.b(eventAlarmInfo.getCreateTimestamp());
            obxVar.d(eventAlarmInfo.getModifiedTimestamp());
            LogUtil.a("AlarmListItem", "alarm ", obxVar.toString());
        }
        return obxVar;
    }

    private void a(List<EventAlarmInfo> list, int i, int i2) {
        if (i < 0 || i >= list.size()) {
            return;
        }
        list.get(i).setEventAlarmEnable(i2);
    }

    public int i() {
        return this.o;
    }

    private int e(EventAlarmInfo eventAlarmInfo, Context context) {
        int eventAlarmEnable = eventAlarmInfo.getEventAlarmEnable();
        String b2 = SharedPreferenceManager.b(context, String.valueOf(10022), "ONCE_EVENT_ALARM_INFO");
        LogUtil.a("AlarmListItem", "once onceEventAlarmIsOver json ", b2);
        if (!TextUtils.isEmpty(b2)) {
            eventAlarmEnable = e(eventAlarmInfo, b2);
        } else {
            LogUtil.h("AlarmListItem", "once onceEventAlarmIsOver json is null");
        }
        LogUtil.a("AlarmListItem", "nce onceEventAlarmIsOver itemEventAlarmEnable ", Integer.valueOf(eventAlarmEnable));
        return eventAlarmEnable;
    }

    private int e(EventAlarmInfo eventAlarmInfo, String str) {
        int eventAlarmEnable = eventAlarmInfo.getEventAlarmEnable();
        List<EventAlarmInfo> list = (List) new Gson().fromJson(str, new TypeToken<List<EventAlarmInfo>>() { // from class: obx.1
        }.getType());
        if (list != null && !list.isEmpty()) {
            for (EventAlarmInfo eventAlarmInfo2 : list) {
                if (eventAlarmInfo2.getEventAlarmIndex() == eventAlarmInfo.getEventAlarmIndex()) {
                    long currentTimeMillis = System.currentTimeMillis() / 1000;
                    LogUtil.a("AlarmListItem", "once curTime ", Long.valueOf(currentTimeMillis));
                    if (currentTimeMillis >= eventAlarmInfo2.getEventAlarmTime()) {
                        eventAlarmEnable = 0;
                    }
                }
            }
        }
        return eventAlarmEnable;
    }

    public static void b(String str) {
        b = str;
    }
}
