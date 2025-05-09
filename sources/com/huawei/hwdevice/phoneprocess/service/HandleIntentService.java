package com.huawei.hwdevice.phoneprocess.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bhh;
import defpackage.blt;
import defpackage.cun;
import defpackage.jst;
import defpackage.jtc;
import defpackage.kkp;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class HandleIntentService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (intent != null) {
            try {
                int intExtra = intent.getIntExtra("type_time_change", -1);
                ReleaseLogUtil.e("HandleIntentService", "type_time_change: ", Integer.valueOf(intExtra));
                if (intExtra == 1000) {
                    kkp.c().d();
                    a();
                } else {
                    BroadcastManagerUtil.bFI_(this, intent);
                }
            } catch (NullPointerException e) {
                LogUtil.b("HandleIntentService", "onStartCommand Exception error,", ExceptionUtils.d(e));
            }
        }
        return 2;
    }

    private void a() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HandleIntentService");
        LogUtil.a("HandleIntentService", "deviceList size(): ", Integer.valueOf(deviceList.size()));
        for (DeviceInfo deviceInfo : deviceList) {
            LogUtil.a("HandleIntentService", "deviceInfo address: ", blt.b(deviceInfo.getDeviceIdentify()));
            DeviceCapability d = jst.d(deviceInfo.getDeviceIdentify());
            if (d != null && d.isSupportTimeSetting()) {
                LogUtil.a("HandleIntentService", "updateTimeService support time set.");
                byte[] d2 = bhh.d();
                DeviceCommand deviceCommand = new DeviceCommand();
                deviceCommand.setServiceID(1);
                deviceCommand.setCommandID(5);
                deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
                deviceCommand.setDataContent(d2);
                deviceCommand.setDataLen(d2.length);
                LogUtil.a("HandleIntentService", "updateTimeService, deviceCommand: ", deviceCommand.toString());
                jtc.c().sendDeviceData(deviceCommand);
            }
        }
    }
}
