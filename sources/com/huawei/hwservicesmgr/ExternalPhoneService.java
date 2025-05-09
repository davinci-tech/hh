package com.huawei.hwservicesmgr;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;
import defpackage.cvt;
import defpackage.cvz;
import defpackage.cwi;
import defpackage.jsz;
import defpackage.knl;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.MagicBuild;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ExternalPhoneService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        char c;
        super.onStartCommand(intent, i, i2);
        if (intent == null) {
            LogUtil.h("ExternalPhoneService", "onStartCommand intent is null");
            e(i2);
            return 2;
        }
        LogUtil.a("ExternalPhoneService", "ExternalPhoneService onStartCommand:", intent);
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            LogUtil.h("ExternalPhoneService", "error, action is null");
            e(i2);
            return 2;
        }
        action.hashCode();
        switch (action.hashCode()) {
            case -1826016838:
                if (action.equals("com.huawei.synergy.AUTH_STATE_REQ")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 48876334:
                if (action.equals("alarm_clock_msg_from_device")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 343047954:
                if (action.equals("com.hihonor.synergy.AUTH_STATE_REQ")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 702803404:
                if (action.equals("com.huawei.synergy.NOTIFY_MSG")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c == 1) {
                bRr_(intent);
            } else if (c != 2) {
                if (c == 3) {
                    bRt_(intent);
                } else {
                    LogUtil.h("ExternalPhoneService", "onStartCommand case default");
                }
            }
            e(i2);
            return 2;
        }
        bRs_(intent);
        e(i2);
        return 2;
    }

    private void e(int i) {
        if (CommonUtil.as()) {
            LogUtil.a("ExternalPhoneService", "stopExternalPhoneService startId:", Integer.valueOf(i));
            stopSelf(i);
        }
    }

    private void bRr_(Intent intent) {
        int intExtra = intent.getIntExtra("notification_id", -1);
        int intExtra2 = intent.getIntExtra("action", -1);
        LogUtil.a("ExternalPhoneService", "processAlarmClockMsgToSynergy notificationId = " + intExtra, "action = " + intExtra2);
        Intent intent2 = new Intent("com.huawei.synergy.CTROL_ALARM_MSG");
        intent2.setComponent(new ComponentName("com.huawei.synergy", "com.huawei.synergy.server.QueryService"));
        intent2.putExtra("notification_id", intExtra);
        intent2.putExtra("action", intExtra2);
        try {
            startService(intent2);
            LogUtil.a("ExternalPhoneService", "processAlarmClockMsgToSynergy startService");
        } catch (IllegalStateException unused) {
            LogUtil.b("ExternalPhoneService", "processAlarmClockMsgToSynergy IllegalStateException");
        } catch (SecurityException unused2) {
            LogUtil.b("ExternalPhoneService", "processAlarmClockMsgToSynergy SecurityException");
        }
    }

    private void bRt_(Intent intent) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "ExternalPhoneService");
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            ReleaseLogUtil.d("R_ExternalPhoneService", "processSynergyNotifyMsgToDevice deviceInfo is null");
            return;
        }
        if (!cwi.c(deviceInfo, 200)) {
            ReleaseLogUtil.d("R_ExternalPhoneService", "processSynergyNotifyMsgToDevice device is not support alarm clock process");
            return;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        LogUtil.a("ExternalPhoneService", "processSynergyNotifyMsgToDevice connectedMac = ", CommonUtil.l(deviceIdentify));
        if (cvz.a(knl.d(deviceIdentify)) != 1) {
            ReleaseLogUtil.d("R_ExternalPhoneService", "processSynergyNotifyMsgToDevice alarm switch is closed");
        } else {
            bRu_(intent, deviceIdentify);
        }
    }

    private void bRu_(Intent intent, String str) {
        boolean z;
        String stringExtra = intent.getStringExtra("macList");
        if (!TextUtils.isEmpty(stringExtra)) {
            z = false;
            for (String str2 : stringExtra.split(";")) {
                if (TextUtils.equals(str2, str)) {
                    break;
                }
            }
        }
        z = true;
        ReleaseLogUtil.e("R_ExternalPhoneService", "sendAlarmToDevice isSendClockToDevice = ", Boolean.valueOf(z));
        if (!z) {
            ReleaseLogUtil.e("R_ExternalPhoneService", "sendAlarmToDevice is not need send to device");
            return;
        }
        Bundle bundleExtra = intent.getBundleExtra("attribute");
        StringBuffer stringBuffer = new StringBuffer();
        DeviceCommand deviceCommand = new DeviceCommand();
        int i = bundleExtra.getInt("type", -1);
        LogUtil.a("ExternalPhoneService", "sendAlarmToDevice type = ", Integer.valueOf(i));
        if (i == 0) {
            bRp_(bundleExtra, stringBuffer, deviceCommand);
        } else if (i == 1 || i == 2) {
            bRq_(bundleExtra, stringBuffer, deviceCommand);
        } else {
            LogUtil.h("ExternalPhoneService", "sendAlarmToDevice unKnow type");
            return;
        }
        byte[] c = HEXUtils.c(stringBuffer.toString());
        deviceCommand.setDataContent(c);
        deviceCommand.setDataLen(c.length);
        LogUtil.a("ExternalPhoneService", "sendAlarmToDevice command = ", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void bRq_(Bundle bundle, StringBuffer stringBuffer, DeviceCommand deviceCommand) {
        String str;
        long j = bundle.getInt("notification_id", -1);
        LogUtil.a("ExternalPhoneService", "formatCommandByStopAndPause alarmId = ", Long.valueOf(j));
        String str2 = "";
        if (j != -1) {
            String e = HEXUtils.e(j);
            str = HEXUtils.e(1) + HEXUtils.e(e.length() / 2) + e;
        } else {
            str = "";
        }
        int i = bundle.getInt("type", -1);
        LogUtil.a("ExternalPhoneService", "formatCommandByStopAndPause alarmAction = ", Integer.valueOf(i));
        if (i != -1) {
            String e2 = HEXUtils.e(i);
            str2 = HEXUtils.e(2) + HEXUtils.e(e2.length() / 2) + e2;
        }
        stringBuffer.append(str).append(str2);
        deviceCommand.setServiceID(8);
        deviceCommand.setCommandID(10);
    }

    private void bRp_(Bundle bundle, StringBuffer stringBuffer, DeviceCommand deviceCommand) {
        String str;
        String str2;
        String str3;
        String str4;
        LogUtil.a("ExternalPhoneService", "formatCommandByOpen 5.8.9 start");
        int i = bundle.getInt("notification_id", -1);
        LogUtil.a("ExternalPhoneService", "formatCommandByOpen notificationId = ", Integer.valueOf(i));
        String str5 = "";
        if (i != -1) {
            String e = HEXUtils.e(i);
            str = HEXUtils.e(1) + HEXUtils.e(e.length() / 2) + e;
        } else {
            str = "";
        }
        String string = bundle.getString("title");
        if (!TextUtils.isEmpty(string)) {
            String b = HEXUtils.b(string);
            str2 = HEXUtils.e(2) + HEXUtils.e(b.length() / 2) + b;
        } else {
            LogUtil.a("ExternalPhoneService", "formatCommandByOpen alarm title is empty.");
            str2 = "";
        }
        long j = bundle.getLong("time", -1L);
        LogUtil.a("ExternalPhoneService", "formatCommandByOpen alarmTime = " + j);
        if (j != -1) {
            String c = HEXUtils.c(j);
            str3 = HEXUtils.e(3) + HEXUtils.e(c.length() / 2) + c;
        } else {
            str3 = "";
        }
        int i2 = bundle.getInt("snooze_delay", -1);
        LogUtil.a("ExternalPhoneService", "formatCommandByOpen snoozeDelay = ", Integer.valueOf(i2));
        if (i2 != -1) {
            String e2 = HEXUtils.e(i2);
            str4 = HEXUtils.e(4) + HEXUtils.e(e2.length() / 2) + e2;
        } else {
            str4 = "";
        }
        int i3 = bundle.getInt("snooze_count", -1);
        LogUtil.a("ExternalPhoneService", "formatCommandByOpen snoozeCount = ", Integer.valueOf(i3));
        if (i3 != -1) {
            String e3 = HEXUtils.e(i3);
            str5 = HEXUtils.e(5) + HEXUtils.e(e3.length() / 2) + e3;
        }
        stringBuffer.append(str).append(str2).append(str3).append(str4).append(str5);
        deviceCommand.setServiceID(8);
        deviceCommand.setCommandID(9);
    }

    private void bRs_(Intent intent) {
        int i;
        Intent intent2;
        LogUtil.a("ExternalPhoneService", "processSynergyAuth Enter");
        String stringExtra = intent.getStringExtra("DEVICE_ADDRESS");
        Iterator<DeviceInfo> it = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "ExternalPhoneService").iterator();
        while (true) {
            if (!it.hasNext()) {
                i = 0;
                break;
            }
            DeviceInfo next = it.next();
            if (!cvt.c(next.getProductType()) && next.getDeviceIdentify().equalsIgnoreCase(stringExtra)) {
                i = 1;
                break;
            }
        }
        LogUtil.a("ExternalPhoneService", "processSynergyAuth authInfo:", Integer.valueOf(i), ",address:", CommonUtil.l(stringExtra));
        if (MagicBuild.f13130a) {
            LogUtil.a("ExternalPhoneService", "processSynergyAuth honor");
            intent2 = new Intent("com.huawei.health.AUTH_STATE_RESP");
            intent2.setComponent(new ComponentName("com.hihonor.synergy", "com.hihonor.synergy.server.QueryService"));
            intent2.putExtra("DEVICE_ADDRESS", stringExtra);
            intent2.putExtra("AUTH_NAME", i);
        } else {
            intent2 = new Intent("com.huawei.health.AUTH_STATE_RESP");
            intent2.setComponent(new ComponentName("com.huawei.synergy", "com.huawei.synergy.server.QueryService"));
            intent2.putExtra("DEVICE_ADDRESS", stringExtra);
            intent2.putExtra("AUTH_NAME", i);
        }
        try {
            startService(intent2);
            LogUtil.a("ExternalPhoneService", "processSynergyAuth startService");
        } catch (IllegalStateException unused) {
            LogUtil.b("ExternalPhoneService", "processSynergyAuth IllegalStateException");
        } catch (SecurityException unused2) {
            LogUtil.b("ExternalPhoneService", "processSynergyAuth SecurityException");
        }
    }
}
