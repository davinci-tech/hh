package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class kiw implements ParserInterface {
    private static final Object c = new Object();
    private static kiw e;

    /* renamed from: a, reason: collision with root package name */
    private String f14406a;
    private String b;
    private String d;
    private String f;
    private HwDeviceMgrInterface h;
    private Map<String, String> j = new HashMap();
    private BroadcastReceiver g = new BroadcastReceiver() { // from class: kiw.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action;
            DeviceInfo deviceInfo;
            Object[] objArr = new Object[2];
            objArr[0] = "mConnectStateChangedReceiver action:";
            objArr[1] = intent == null ? Constants.NULL : intent.getAction();
            LogUtil.a("HwTempPhoneServiceManager", objArr);
            if (intent == null || (action = intent.getAction()) == null || !action.equals("com.huawei.bone.action.CONNECTION_STATE_CHANGED")) {
                return;
            }
            try {
                deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
            } catch (ClassCastException unused) {
                LogUtil.b("HwTempPhoneServiceManager", "ClassCastException");
                deviceInfo = null;
            }
            if (deviceInfo == null) {
                LogUtil.h("HwTempPhoneServiceManager", "mConnectStateChangedReceiver deviceInfo is null");
                return;
            }
            LogUtil.a("HwTempPhoneServiceManager", "mConnectStateChangedReceiver ConnectState is :", Integer.valueOf(deviceInfo.getDeviceConnectState()), " deviceName: ", deviceInfo.getDeviceName());
            if (deviceInfo.getDeviceConnectState() == 2) {
                if (!kiw.this.a(deviceInfo)) {
                    LogUtil.h("HwTempPhoneServiceManager", "mConnectStateChangedReceiver sendTmpCommandToDevice, isn't support temp.");
                } else if (kiw.this.e(deviceInfo) && !Utils.o()) {
                    kiw.this.i(deviceInfo);
                } else {
                    kiw.this.b(deviceInfo);
                    kiw.this.h(deviceInfo);
                }
            }
        }
    };

    public static kiw c() {
        kiw kiwVar;
        synchronized (c) {
            if (e == null) {
                e = new kiw();
            }
            kiwVar = e;
        }
        return kiwVar;
    }

    private kiw() {
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.g, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
        this.h = jsz.b(BaseApplication.getContext());
    }

    public void a(DeviceInfo deviceInfo, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerUnitHiSync value isEmpty");
            return;
        }
        UnitUtil.a(!"false".equals(str2));
        if (str2.equals(this.b)) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerUnitHiSync value equals last");
            return;
        }
        if (deviceInfo == null) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerUnitHiSync deviceInfo == null");
        } else if (!a(deviceInfo)) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerUnitHiSync, Device isn't support temp.");
        } else {
            b(deviceInfo);
        }
    }

    public void b(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(12);
        deviceCommand.setCommandID(5);
        boolean d = UnitUtil.d();
        int i = !d ? 1 : 0;
        LogUtil.a("HwTempPhoneServiceManager", "Need set temperature unit info:", Integer.valueOf(i));
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(1)).append(cvx.e(1)).append(cvx.e(i));
        deviceCommand.setDataLen(cvx.a(stringBuffer.toString()).length);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        this.h.sendDeviceData(deviceCommand);
        LogUtil.a("HwTempPhoneServiceManager", "sendTmpCommandToDevice Command:", deviceCommand.toString());
        this.b = String.valueOf(d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(DeviceInfo deviceInfo) {
        if (Utils.o()) {
            LogUtil.a("HwTempPhoneServiceManager", "sendTempDataToDevice, oversea setTemperatureSwitchToDevice");
            d(deviceInfo, jqy.c("continuous_temp_monitoring"));
        } else {
            LogUtil.a("HwTempPhoneServiceManager", "sendTempDataToDevice, inland setTempUpperAndLowerRemindToDevice");
            c(deviceInfo, jqy.c("temperature_upper_remind"));
            a(deviceInfo, jqy.c("temperature_lower_remind"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(DeviceInfo deviceInfo) {
        c(4, deviceInfo, 0, 0);
    }

    private void d(DeviceInfo deviceInfo, String str) {
        ReleaseLogUtil.e("R_HwTempPhoneServiceManager", "setTemperatureSwitchToDevice value:", str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c(1, deviceInfo, CommonUtil.m(BaseApplication.getContext(), str) == 1 ? 1 : 0, -1);
        this.d = str;
    }

    private void c(DeviceInfo deviceInfo, String str) {
        int i;
        ReleaseLogUtil.e("R_HwTempPhoneServiceManager", "setTempUpperRemindToDevice value:", str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int i2 = 0;
        try {
            float parseFloat = Float.parseFloat(str);
            if (parseFloat < 340.0f) {
                parseFloat *= 10.0f;
            }
            i = (int) parseFloat;
        } catch (NumberFormatException unused) {
            LogUtil.b("HwTempPhoneServiceManager", "setTemperatureDataToDevice NumberFormatException tempData : ", str);
            i = 0;
        }
        if (i != 0) {
            if (d(deviceInfo) && i != 372) {
                LogUtil.a("HwTempPhoneServiceManager", "setTemperatureDataToDevice setTempUpperRemindToCloud");
                str = d(372);
                i = 372;
            }
            i2 = 1;
        }
        c(2, deviceInfo, i2, i);
        this.f = str;
    }

    private void a(DeviceInfo deviceInfo, String str) {
        int i;
        ReleaseLogUtil.e("R_HwTempPhoneServiceManager", "setTempLowerRemindToDevice value:", str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            float parseFloat = Float.parseFloat(str);
            if (parseFloat < 340.0f) {
                parseFloat *= 10.0f;
            }
            i = (int) parseFloat;
        } catch (NumberFormatException unused) {
            LogUtil.b("HwTempPhoneServiceManager", "setTemperatureDataToDevice NumberFormatException tempData : ", str);
            i = 0;
        }
        c(3, deviceInfo, i != 0 ? 1 : 0, i);
        this.f14406a = str;
    }

    private void c(int i, DeviceInfo deviceInfo, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(1)).append(cvx.e(1)).append(cvx.e(i));
        stringBuffer.append(cvx.e(2)).append(cvx.e(1)).append(cvx.e(i2));
        String a2 = cvx.a(i3);
        if (i3 != 0) {
            stringBuffer.append(cvx.e(3)).append(cvx.e(a2.length() / 2)).append(a2);
        }
        byte[] a3 = cvx.a(stringBuffer.toString());
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(42);
        deviceCommand.setDataLen(a3.length);
        deviceCommand.setDataContent(a3);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        this.h.sendDeviceData(deviceCommand);
        ReleaseLogUtil.e("R_HwTempPhoneServiceManager", "setTemperatureDataToDevice switchType : ", Integer.valueOf(i), ", switchStatus : ", Integer.valueOf(i2), ", switchValue : ", Integer.valueOf(i3));
    }

    private String d(int i) {
        String f = Float.toString(i / 10.0f);
        jqy.a("temperature_upper_remind", f);
        return f;
    }

    public boolean a(DeviceInfo deviceInfo) {
        boolean c2 = cwi.c(deviceInfo, 29);
        LogUtil.a("HwTempPhoneServiceManager", "isDeviceSupportTemperature : ", Boolean.valueOf(c2));
        return c2;
    }

    public boolean d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return false;
        }
        if (TextUtils.isEmpty(deviceInfo.getExpandCapability())) {
            LogUtil.a("HwTempPhoneServiceManager", "isDeviceSportTempClassification mExpandCapability is empty");
            deviceInfo.setExpandCapability(e(deviceInfo.getDeviceIdentify()));
        }
        boolean c2 = cwi.c(deviceInfo, 186);
        LogUtil.a("HwTempPhoneServiceManager", "isSupportTempClassification : ", Boolean.valueOf(c2), " deviceName: ", deviceInfo.getDeviceName());
        return c2;
    }

    private String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "HwTempPhoneServiceManager");
        if (deviceList == null || deviceList.isEmpty() || deviceList.get(0) == null) {
            LogUtil.h("HwTempPhoneServiceManager", "getDefiniteDeviceInfo deviceInfoList is null");
            return null;
        }
        return deviceList.get(0).getExpandCapability();
    }

    public boolean e(DeviceInfo deviceInfo) {
        boolean c2 = cwi.c(deviceInfo, 67);
        LogUtil.a("HwTempPhoneServiceManager", "isDeviceSupportTemperatureStudy : ", Boolean.valueOf(c2));
        return c2;
    }

    private void a(byte[] bArr, DeviceInfo deviceInfo) {
        boolean z;
        if (Utils.o()) {
            LogUtil.a("HwTempPhoneServiceManager", "handleTempStudyData, oversea");
            return;
        }
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("HwTempPhoneServiceManager", "handleTempStudyData data is error");
            return;
        }
        try {
            String str = "";
            loop0: while (true) {
                z = false;
                for (cwd cwdVar : new cwl().a(d.substring(4, d.length())).e()) {
                    int w = CommonUtil.w(cwdVar.e());
                    if (w != 1) {
                        if (w == 2) {
                            str = cwdVar.c();
                        } else {
                            LogUtil.h("HwTempPhoneServiceManager", "handleTempStudyData default");
                        }
                    } else if (CommonUtil.w(cwdVar.c()) == 4) {
                        z = true;
                    }
                }
            }
            LogUtil.a("HwTempPhoneServiceManager", "handleTempStudyData isStudy: ", Boolean.valueOf(z), "  status: ", str);
            if (z) {
                if (CommonUtil.m(BaseApplication.getContext(), str) == 1) {
                    j(deviceInfo);
                }
                b(deviceInfo, str);
                Intent intent = new Intent();
                intent.setPackage(BaseApplication.getAppPackage());
                intent.setAction(d("tempStudyStatus"));
                BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
                return;
            }
            LogUtil.h("HwTempPhoneServiceManager", "handleTempStudyData isStudy : false");
        } catch (cwg unused) {
            LogUtil.b("HwTempPhoneServiceManager", "handleTempStudyData error");
        }
    }

    private void b(DeviceInfo deviceInfo, String str) {
        String c2 = c(deviceInfo);
        if (TextUtils.isEmpty(c2)) {
            return;
        }
        this.j.put(c2, str);
        jqi.a().setSwitchSettingToDb(c2, str);
    }

    private String c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return "";
        }
        return deviceInfo.getDeviceName() + "_" + deviceInfo.getSoftVersion() + "_dbTempStudyStatusKey";
    }

    private String d(String str) {
        return BaseApplication.getAppPackage() + "." + str;
    }

    private void j(DeviceInfo deviceInfo) {
        b(deviceInfo);
        c(deviceInfo, jqy.c("temperature_upper_remind"));
        a(deviceInfo, jqy.c("temperature_lower_remind"));
    }

    public void a() {
        BaseApplication.getContext().unregisterReceiver(this.g);
        d();
    }

    private static void d() {
        synchronized (c) {
            e = null;
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        a(bArr, deviceInfo);
    }

    public void e(DeviceInfo deviceInfo, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerTempMonitoring value isEmpty");
            return;
        }
        if (str.equals(this.d)) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerTempMonitoring value equals last");
            return;
        }
        if (deviceInfo == null) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerTempMonitoring deviceInfo == null");
            return;
        }
        if (!a(deviceInfo)) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerTempMonitoring Device isn't support temp.");
        } else if (!Utils.o()) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerTempMonitoring, is not Oversea");
        } else {
            d(deviceInfo, str);
        }
    }

    public void b(DeviceInfo deviceInfo, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerTempRemind value isEmpty");
            return;
        }
        if (str2.equals("temperature_upper_remind".equals(str) ? this.f : this.f14406a)) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerTempRemind value equals last");
            return;
        }
        if (deviceInfo == null) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerTempRemind deviceInfo == null");
            return;
        }
        if (!a(deviceInfo)) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerTempRemind Device isn't support temp.");
            return;
        }
        if (Utils.o()) {
            LogUtil.h("HwTempPhoneServiceManager", "handlerTempRemind, is Oversea");
            return;
        }
        if (e(deviceInfo)) {
            if (CommonUtil.m(BaseApplication.getContext(), this.j.get(c(deviceInfo))) != 1) {
                LogUtil.h("HwTempPhoneServiceManager", "handlerTempRemind Device isn't join Study.");
                return;
            }
        }
        if ("temperature_upper_remind".equals(str)) {
            c(deviceInfo, str2);
        } else {
            a(deviceInfo, str2);
        }
    }
}
