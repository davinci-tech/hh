package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase;
import com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri.HwDevicePeriodRriFileManager;
import com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription;
import com.huawei.hwdevicemgr.framework.INoitifyPhoneServiceCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class jso {
    private INoitifyPhoneServiceCallback c;

    private jso() {
        this.c = null;
        LogUtil.a("PhoneService_ProcessInfoRequest", "create ProcessInfoRequest");
    }

    public static jso d() {
        return a.c;
    }

    public boolean b(String str, DeviceInfo deviceInfo, int i, String str2, Context context) {
        LogUtil.a("PhoneService_ProcessInfoRequest", "enter notifyPhoneService. module: ", str, ", deviceInfo: ", deviceInfo, ", message: ", str2);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PhoneService_ProcessInfoRequest", "deviceInfo or module is invalid.");
            return false;
        }
        if ("syncBasicData".equals(str)) {
            jwy.b().e(true, deviceInfo, (IBaseResponseCallback) null);
        }
        if ("syncCalendarSwitch".equals(str)) {
            jyc.b().a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, i);
        }
        if ("seizeDevice".equals(str)) {
            kdj.e().a(deviceInfo, str2);
            return true;
        }
        if ("deleteDevice".equals(str)) {
            kjo.e(context).a(str2, deviceInfo);
            return true;
        }
        if ("earphoneManager".equals(str)) {
            kcf.b().e(deviceInfo, str2);
        }
        if ("earphoneLogPre".equals(str)) {
            jvg.e().d(str2, deviceInfo);
            return true;
        }
        if ("privacySwitch".equals(str)) {
            kia.c().c(deviceInfo, str2);
            return true;
        }
        if ("featureManager".equals(str)) {
            jzd.b().c(str2);
            return true;
        }
        if ("stressRelaxManager".equals(str)) {
            kdq.c().a();
            return true;
        }
        if ("syncPeriodRri".equals(str)) {
            HwDevicePeriodRriFileManager.getInstance().getPeriodRriFileFromDevice();
            return true;
        }
        if ("oobeCreated".equals(str)) {
            return a(deviceInfo);
        }
        if ("syncSpo2AlarmData".equals(str)) {
            ReleaseLogUtil.e("BTSYNC_PhoneService_ProcessInfoRequest", "startSyncLowSpo2Alarm");
            kej.e().b(deviceInfo);
            return true;
        }
        if ("syncHeartRateAlarmData".equals(str)) {
            ReleaseLogUtil.e("BTSYNC_PhoneService_ProcessInfoRequest", "startSyncHeartRateAlarm");
            kee.c().a(deviceInfo);
            return true;
        }
        if ("reverseLinkage".equals(str)) {
            ReleaseLogUtil.e("BTSYNC_PhoneService_ProcessInfoRequest", "sendSampleEventToDevice");
            kgc.b().e(deviceInfo, i);
            return true;
        }
        if ("reverseLinkageReply".equals(str)) {
            ReleaseLogUtil.e("BTSYNC_PhoneService_ProcessInfoRequest", "replySampleEventToDevice");
            kgc.b().c(deviceInfo, i, str2);
            return true;
        }
        if ("startAllReconnect".equals(str)) {
            bjy.d().c(4);
            return true;
        }
        if ("notify_device_setting_changer".equals(str)) {
            DeviceSettingSubscription.b().g(deviceInfo, str2, jqy.c(str2));
            return true;
        }
        if (i == 230227) {
            try {
                Object invoke = HwDevicePeriodRriFileManager.getInstance().getClass().getDeclaredMethod(str, new Class[0]).invoke(HwDevicePeriodRriFileManager.getInstance(), new Object[0]);
                if (invoke instanceof Boolean) {
                    return ((Boolean) invoke).booleanValue();
                }
                return false;
            } catch (NoSuchMethodException e) {
                LogUtil.h("PhoneService_ProcessInfoRequest", "method exception = ", e.getMessage());
            } catch (Exception e2) {
                LogUtil.h("PhoneService_ProcessInfoRequest", "rri exception = ", e2.getMessage());
            }
        }
        return true;
    }

    private boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("PhoneService_ProcessInfoRequest", "notifyPhoneService deviceInfo is null");
            return false;
        }
        VariableHandshakeGeneralCommandBase a2 = jtn.b().a(deviceInfo.getDeviceIdentify());
        if (!(a2 instanceof jtl)) {
            LogUtil.h("PhoneService_ProcessInfoRequest", "Command is not instanceof StartupGuideUserSettingCommand");
            return false;
        }
        jtl jtlVar = (jtl) a2;
        jtlVar.a(true);
        jtlVar.c();
        return true;
    }

    public INoitifyPhoneServiceCallback b(String str) {
        LogUtil.h("PhoneService_ProcessInfoRequest", "getCallback put: ", str);
        return this.c;
    }

    public void d(String str, INoitifyPhoneServiceCallback iNoitifyPhoneServiceCallback) {
        LogUtil.h("PhoneService_ProcessInfoRequest", "registerCallback put: ", str);
        this.c = iNoitifyPhoneServiceCallback;
    }

    public void c(String str) {
        LogUtil.h("PhoneService_ProcessInfoRequest", "unregisterCallback remove: ", str);
        this.c = null;
    }

    static class a {
        private static jso c = new jso();
    }
}
