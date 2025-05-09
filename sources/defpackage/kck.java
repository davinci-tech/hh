package defpackage;

import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kck implements DataReceiveCallback {

    static class e {
        private static final kck c = new kck();
    }

    private kck() {
    }

    public static kck c() {
        return e.c;
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        ReleaseLogUtil.e("DEVMGR_HealthAlarmManager", "errorCode is ", Integer.valueOf(i));
        if (deviceInfo == null || cvrVar == null) {
            ReleaseLogUtil.c("DEVMGR_HealthAlarmManager", "onDataReceived, deviceInfo or message is null");
            return;
        }
        if (!(cvrVar instanceof cvp)) {
            ReleaseLogUtil.c("DEVMGR_HealthAlarmManager", "onDataReceived, message is error");
            return;
        }
        cvp cvpVar = (cvp) cvrVar;
        if (cvrVar.getCommandId() != 2) {
            ReleaseLogUtil.c("DEVMGR_HealthAlarmManager", "onDataReceived, message commandId: ", Integer.valueOf(cvrVar.getCommandId()));
            return;
        }
        if (cvpVar.e() != 80030005) {
            ReleaseLogUtil.c("DEVMGR_HealthAlarmManager", "onDataReceived, sampleEvent EventId is error");
            return;
        }
        byte[] c = cvpVar.c();
        if (c == null) {
            ReleaseLogUtil.c("DEVMGR_HealthAlarmManager", "onDataReceived, eventData is null");
            return;
        }
        String e2 = cvx.e(cvx.d(c));
        LogUtil.a("HealthAlarmManager", "onDataReceived data:", e2);
        try {
            int i2 = new JSONObject(e2).getInt("alarmType");
            ReleaseLogUtil.e("DEVMGR_HealthAlarmManager", "alarmType:", Integer.valueOf(i2));
            if (i2 == 0) {
                ReleaseLogUtil.e("DEVMGR_HealthAlarmManager", "heart rate is too high.");
                nhu.d().startSyncHeartRateAlarm(deviceInfo);
            } else if (i2 == 1) {
                ReleaseLogUtil.e("DEVMGR_HealthAlarmManager", "heart rate is too low.");
                nhu.d().startSyncHeartRateAlarm(deviceInfo);
            } else if (i2 == 2) {
                ReleaseLogUtil.e("DEVMGR_HealthAlarmManager", "oxygen saturation is too low.");
                nhu.d().startSyncLowSpo2Alarm(deviceInfo);
            }
        } catch (JSONException unused) {
            ReleaseLogUtil.c("DEVMGR_HealthAlarmManager", "parse json exception");
        }
    }

    public void d() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.healthalarm", this);
        ReleaseLogUtil.e("DEVMGR_HealthAlarmManager", "register success");
    }

    public void a() {
        cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.healthalarm");
        ReleaseLogUtil.e("DEVMGR_HealthAlarmManager", "unregister success");
    }
}
