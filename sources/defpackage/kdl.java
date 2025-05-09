package defpackage;

import android.provider.Settings;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kdl implements DataReceiveCallback {

    static class c {
        private static final kdl d = new kdl();
    }

    private kdl() {
    }

    public static kdl c() {
        return c.d;
    }

    public void b() {
        cuk.b().registerDeviceSampleListener("hw.app.settingsprovider", this);
        kdo.bNg_(BaseApplication.e().getContentResolver(), new kdm(null), "device_retail_warehouse");
        LogUtil.a("HwStorePutBackManager", "registerDeviceSampleListener success");
    }

    public void a() {
        cuk.b().unRegisterDeviceSampleListener("hw.app.settingsprovider");
        kdo.c(BaseApplication.e());
        LogUtil.a("HwStorePutBackManager", "unRegisterDeviceSampleListener success");
    }

    public void d() {
        if (Utils.m() && !Utils.o()) {
            cuk.b().sendSampleEventCommand(e(), f(), "HwStorePutBackManager");
        } else {
            LogUtil.a("HwStorePutBackManager", "sendStorePutBackEvent error");
        }
    }

    private cvp f() {
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.app.settingsprovider");
        cvpVar.setWearPkgName("hw.watch.retailmode");
        cvpVar.setCommandId(2);
        cvpVar.a(800100010L);
        cvpVar.b(0);
        cvpVar.e(cvx.a(i()));
        return cvpVar;
    }

    private String i() {
        int i;
        try {
            i = Settings.Secure.getInt(BaseApplication.e().getContentResolver(), "device_retail_warehouse");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            i = 0;
        }
        StringBuilder sb = new StringBuilder(16);
        String c2 = cvx.c("Settings.Secure");
        sb.append(cvx.e(1) + cvx.d(c2.length() / 2) + c2);
        String c3 = cvx.c("RETAILMODE_PUTBACK");
        sb.append(cvx.e(2) + cvx.d(c3.length() / 2) + c3);
        String c4 = cvx.c("int");
        sb.append(cvx.e(3) + cvx.d(c4.length() / 2) + c4);
        String e2 = cvx.e(i);
        sb.append(cvx.e(6) + cvx.d(e2.length() / 2) + e2);
        LogUtil.a("HwStorePutBackManager", "getEventData tlv:", sb.toString());
        return sb.toString();
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.a("HwStorePutBackManager", "errorCode is ", Integer.valueOf(i));
        if (deviceInfo == null || cvrVar == null) {
            LogUtil.b("HwStorePutBackManager", "onDataReceived, deviceInfo or message is null");
            return;
        }
        if (!(cvrVar instanceof cvp)) {
            LogUtil.b("HwStorePutBackManager", "onDataReceived, message is error");
            return;
        }
        cvp cvpVar = (cvp) cvrVar;
        if (cvrVar.getCommandId() != 2) {
            LogUtil.b("HwStorePutBackManager", "onDataReceived, message commandId: ", Integer.valueOf(cvrVar.getCommandId()));
            return;
        }
        if (cvpVar.e() != 800100010) {
            LogUtil.b("HwStorePutBackManager", "onDataReceived, sampleEvent EventId is error");
            return;
        }
        byte[] c2 = cvpVar.c();
        if (c2 == null) {
            LogUtil.b("HwStorePutBackManager", "onDataReceived, eventData is null");
        } else {
            LogUtil.a("HwStorePutBackManager", "onDataReceived data:", cvx.e(cvx.d(c2)));
        }
    }

    public static DeviceInfo e() {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwStorePutBackManager");
        if (deviceList != null && !deviceList.isEmpty()) {
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
        LogUtil.a("HwStorePutBackManager", "getConnectDeviceInfo() deviceInfo ", deviceInfo);
        return deviceInfo;
    }
}
