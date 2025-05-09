package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.datatype.EventAlarmInfo;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
class jgb {
    private Context e;

    jgb(Context context) {
        this.e = context;
    }

    public ByteBuffer e(List<EventAlarmInfo> list, boolean z) {
        ByteBuffer e;
        ArrayList arrayList = new ArrayList(0);
        int i = 0;
        for (int i2 = 0; i2 < jgh.d(BaseApplication.getContext()).i(); i2++) {
            if (i2 >= list.size()) {
                e = d(i2);
            } else {
                e = e(list, i2, z);
            }
            i += e.array().length;
            arrayList.add(e);
        }
        byte[] a2 = cvx.a(cvx.d(i));
        ByteBuffer allocate = ByteBuffer.allocate(i + a2.length + 1);
        allocate.put((byte) -127);
        allocate.put(a2);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            allocate.put(((ByteBuffer) it.next()).array());
        }
        return allocate;
    }

    private ByteBuffer d(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(5);
        allocate.put((byte) -126);
        allocate.put((byte) 3);
        allocate.put((byte) 3);
        allocate.put((byte) 1);
        allocate.put((byte) (i + 1));
        return allocate;
    }

    private ByteBuffer e(List<EventAlarmInfo> list, int i, boolean z) {
        DeviceInfo a2;
        String e = cvx.e(i + 1);
        String e2 = cvx.e(1);
        String e3 = cvx.e(3);
        String b = b(list, i, z);
        String a3 = cvx.a(cvx.j((list.get(i).getEventAlarmStartTimeHour() * 100) + list.get(i).getEventAlarmStartTimeMin()));
        String e4 = cvx.e(2);
        String e5 = cvx.e(5);
        String e6 = cvx.e(list.get(i).getEventAlarmRepeat());
        String e7 = cvx.e(1);
        String e8 = cvx.e(6);
        String c = cvx.c(list.get(i).getEventAlarmName());
        String d = cvx.d(c.length() / 2);
        String e9 = cvx.e(7);
        String e10 = cvx.e(list.get(i).getEventAlarmLegalHolidayEnable());
        String e11 = cvx.e(1);
        String e12 = cvx.e(8);
        String c2 = cvx.c(list.get(i).getCreateTimestamp());
        String d2 = cvx.d(c2.length() / 2);
        String e13 = cvx.e(9);
        String c3 = cvx.c(list.get(i).getModifiedTimestamp());
        String d3 = cvx.d(c3.length() / 2);
        String e14 = cvx.e(10);
        String str = e3 + e2 + e + b + e5 + e4 + a3 + e8 + e7 + e6 + e9 + d + c + e12 + e11 + e10;
        List<DeviceInfo> b2 = jpt.b("AlarmDeviceCommandCompose");
        if (b2 != null && b2.size() > 0) {
            a2 = b2.get(0);
        } else {
            a2 = jpt.a("AlarmDeviceCommandCompose");
        }
        if (a2 != null && a2.getDeviceName() != null && !a2.getDeviceName().toUpperCase(Locale.ROOT).contains("HUAWEI Band 3e".toUpperCase(Locale.ROOT)) && !a2.getDeviceName().toUpperCase(Locale.ROOT).contains("honor Band 4R".toUpperCase(Locale.ROOT))) {
            str = str + e13 + d2 + c2 + e14 + d3 + c3;
            LogUtil.a("AlarmDeviceCommandCompose", "not Aw70 device add alarm time value.");
        }
        String d4 = cvx.d(str.length() / 2);
        byte[] a4 = cvx.a(cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS) + d4 + str);
        ByteBuffer allocate = ByteBuffer.allocate(a4.length);
        allocate.put(a4);
        return allocate;
    }

    private String b(List<EventAlarmInfo> list, int i, boolean z) {
        EventAlarmInfo eventAlarmInfo = list.get(i);
        if (z && eventAlarmInfo.getEventAlarmRepeat() == 0 && eventAlarmInfo.getEventAlarmEnable() == 1) {
            int d = d(eventAlarmInfo);
            LogUtil.a("AlarmDeviceCommandCompose", "AlarmEnable enable = ", Integer.valueOf(d));
            if (d == 0) {
                list.get(i).setEventAlarmEnable(0);
            }
        }
        LogUtil.a("AlarmDeviceCommandCompose", "AlarmEnable enable = ", Integer.valueOf(list.get(i).getEventAlarmEnable()));
        String e = cvx.e(list.get(i).getEventAlarmEnable());
        String e2 = cvx.e(1);
        return cvx.e(4) + e2 + e;
    }

    private int d(EventAlarmInfo eventAlarmInfo) {
        int eventAlarmEnable = eventAlarmInfo.getEventAlarmEnable();
        String b = SharedPreferenceManager.b(this.e, String.valueOf(10022), "ONCE_EVENT_ALARM_INFO");
        LogUtil.a("AlarmDeviceCommandCompose", " onceEventAlarmIsOver json = " + b);
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("AlarmDeviceCommandCompose", "onceEventAlarmIsOver json is null");
            return eventAlarmEnable;
        }
        List list = (List) new Gson().fromJson(b, new TypeToken<List<EventAlarmInfo>>() { // from class: jgb.1
        }.getType());
        if (list != null && list.size() != 0) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EventAlarmInfo eventAlarmInfo2 = (EventAlarmInfo) it.next();
                if (eventAlarmInfo2.getEventAlarmIndex() == eventAlarmInfo.getEventAlarmIndex()) {
                    long currentTimeMillis = System.currentTimeMillis() / 1000;
                    LogUtil.a("AlarmDeviceCommandCompose", "onceEventAlarmIsOver CurTime = " + currentTimeMillis);
                    if (currentTimeMillis >= eventAlarmInfo2.getEventAlarmTime()) {
                        eventAlarmEnable = 0;
                    }
                }
            }
        }
        LogUtil.a("AlarmDeviceCommandCompose", "onceEventAlarmIsOver enableResult = " + eventAlarmEnable);
        return eventAlarmEnable;
    }
}
