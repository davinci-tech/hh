package defpackage;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.CalendarDbBean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.CalendarSyncStateBean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SecurityCalendarDbHelper;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jyi {
    public static void a(String str, int i, long j) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CacheDatabaseUtils", "setSyncAllContactsFlag: deviceId is null or empty.");
            return;
        }
        int i2 = i == 0 ? 0 : 16;
        jyj e = jyj.e(BaseApplication.getContext());
        List<CalendarSyncStateBean> d = e.d(kao.b(str));
        LogUtil.a("CacheDatabaseUtils", "setSyncAllContactsFlag: start, queried size: ", Integer.valueOf(d.size()));
        if (d.isEmpty()) {
            e.e(kao.b(new CalendarSyncStateBean(str, str, i2, j)));
            return;
        }
        CalendarSyncStateBean calendarSyncStateBean = d.get(0);
        if (calendarSyncStateBean == null) {
            LogUtil.h("CacheDatabaseUtils", "setSyncAllContactsFlag: deviceSyncStateBean is null.");
            return;
        }
        long minor = calendarSyncStateBean.getMinor();
        calendarSyncStateBean.setSyncAllFlag(i);
        calendarSyncStateBean.setMinor(minor + j);
        e.c(kao.b(calendarSyncStateBean));
    }

    public static long c(boolean z) {
        List<CalendarSyncStateBean> d = jyj.e(BaseApplication.getContext()).d(kao.b(e(z)));
        LogUtil.a("CacheDatabaseUtils", "syncedMinor: start, queried size: ", Integer.valueOf(d.size()));
        if (d.isEmpty()) {
            return 0L;
        }
        CalendarSyncStateBean calendarSyncStateBean = d.get(0);
        if (calendarSyncStateBean == null) {
            LogUtil.h("CacheDatabaseUtils", "setSyncAllContactsFlag: deviceSyncStateBean is null.");
            return 0L;
        }
        return calendarSyncStateBean.getMinor();
    }

    public static boolean e(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("CacheDatabaseUtils", "isSynchronizedOnce: deviceId is null or empty.");
            return false;
        }
        LogUtil.a("CacheDatabaseUtils", "isSynchronizedOnce: enter. received major is ", str);
        return CommonUtil.a(BaseApplication.getContext(), false).equalsIgnoreCase(str);
    }

    public static void d(String str, String str2, List<CalendarDbBean> list, long j, boolean z) {
        if (TextUtils.isEmpty(str2) || list == null || list.isEmpty()) {
            Log.w("CacheDatabaseUtils", "saveSyncedContacts: input parameters are invalid.");
            return;
        }
        jyf d = jyf.d(BaseApplication.getContext());
        if (e(str, str2)) {
            d.e(list, str2);
        } else {
            d.d(list, str2);
        }
        if (z) {
            a(str2, 16, j);
        }
    }

    public static void d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.w("CacheDatabaseUtils", "saveDeletedContacts: input parameters are invalid.");
        } else {
            jyf.d(BaseApplication.getContext()).c(str2, str);
        }
    }

    public static void c(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.w("CacheDatabaseUtils", "createSyncDataTable: deviceId is null or empty.");
        } else {
            jyf.d(BaseApplication.getContext()).a(str);
        }
    }

    public static List<CalendarDbBean> d(String str) {
        return jyf.d(BaseApplication.getContext()).c(str);
    }

    public static void d(List<String> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("CacheDatabaseUtils", "clearDeviceFromDb: deviceIdList is null or empty.");
            return;
        }
        LogUtil.a("CacheDatabaseUtils", "clearDeviceFromDb: list size: ", Integer.valueOf(list.size()));
        jyj.e(BaseApplication.getContext()).a(list);
        SecurityCalendarDbHelper d = SecurityCalendarDbHelper.d(BaseApplication.getContext());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            d.d(it.next());
        }
    }

    public static String e(boolean z) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "CacheDatabaseUtils");
        if (deviceList.size() == 0) {
            LogUtil.h("CacheDatabaseUtils", "getContactedDeviceMac: the object of HwDeviceMgr is null. ");
            return "00:00:00:00:00:00";
        }
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    deviceInfo = next;
                    break;
                }
            }
        }
        if (deviceInfo == null) {
            LogUtil.h("CacheDatabaseUtils", "getContactedDeviceMac: the object of DeviceInfo is null. ");
            return "00:00:00:00:00:00";
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        return jyo.d(TextUtils.isEmpty(deviceIdentify) ? "00:00:00:00:00:00" : deviceIdentify, z);
    }

    public static void a() {
        jyf.d(BaseApplication.getContext());
        jyj.e(BaseApplication.getContext());
    }
}
