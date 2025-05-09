package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class khk {
    private static final Object e = new Object();
    private static final List<Integer> b = Collections.unmodifiableList(Arrays.asList(1, 2, 3));

    /* renamed from: a, reason: collision with root package name */
    private static khk f14372a = null;

    public static khk c() {
        khk khkVar;
        synchronized (e) {
            if (f14372a == null) {
                f14372a = new khk();
            }
            khkVar = f14372a;
        }
        return khkVar;
    }

    private khk() {
    }

    public void c(int i) {
        ReleaseLogUtil.d("DEVMGR_HwNotificationSubSwitchPushManager", "sendAllSubSwitchStatusToDevice enter type: ", Integer.valueOf(i));
        if (!b.contains(Integer.valueOf(i))) {
            ReleaseLogUtil.d("DEVMGR_HwNotificationSubSwitchPushManager", "sendAllSubSwitchStatusToDevice other conditions is not send");
            return;
        }
        if (!e()) {
            ReleaseLogUtil.d("DEVMGR_HwNotificationSubSwitchPushManager", "sendAllSubSwitchStatusToDevice device is not support switch send deal");
            return;
        }
        if (!b()) {
            ReleaseLogUtil.d("DEVMGR_HwNotificationSubSwitchPushManager", "sendAllSubSwitchStatusToDevice notification main switch is closed.");
            return;
        }
        List<String> i2 = NotificationContentProviderUtil.i();
        ArrayList<String> arrayList = new ArrayList(bfg.d);
        arrayList.add(bfg.e);
        StringBuilder sb = new StringBuilder(16);
        for (String str : arrayList) {
            if (i2 != null && i2.contains(str)) {
                d(sb, str, 0);
            } else {
                d(sb, str, 1);
            }
        }
        DeviceCommand d = d(sb);
        LogUtil.a("HwNotificationSubSwitchPushManager", "sendAllSubSwitchStatusToDevice switch send deviceCommand: ", d);
        jtc.c().sendDeviceData(d);
    }

    private DeviceCommand d(StringBuilder sb) {
        String str = (cvx.e(129) + khs.b(sb.length() / 2)) + ((Object) sb);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(11);
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setDataContent(cvx.a(str));
        return deviceCommand;
    }

    private void d(StringBuilder sb, String str, int i) {
        String c = cvx.c(str);
        b(sb, cvx.e(3) + cvx.e(c.length() / 2) + c, cvx.e(4) + cvx.e(1) + cvx.e(i));
    }

    private boolean e() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwNotificationSubSwitchPushManager");
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            LogUtil.h("HwNotificationSubSwitchPushManager", "isSupportSubSwitchSendDeal deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, MachineControlPointResponse.OP_CODE_EXTENSION_SET_TOTAL_ENERGY);
    }

    private void b(StringBuilder sb, String str, String str2) {
        sb.append(cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS) + khs.b((str + str2).length() / 2));
        sb.append(str);
        sb.append(str2);
    }

    private boolean b() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10035), "notificationStatus");
        if (!TextUtils.isEmpty(b2)) {
            LogUtil.a("HwNotificationSubSwitchPushManager", "isAuthorizeEnabled value is: ", b2);
            try {
                return Integer.parseInt(b2) == 1;
            } catch (NumberFormatException unused) {
                LogUtil.b("HwNotificationSubSwitchPushManager", "isAuthorizeEnabled NumberFormatException");
                return false;
            }
        }
        boolean b3 = jrg.b();
        LogUtil.a("HwNotificationSubSwitchPushManager", "isAuthorizeEnabled value is null, isEnabled:", Boolean.valueOf(b3));
        return b3;
    }
}
