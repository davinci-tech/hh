package defpackage;

import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.callback.DeviceStatusCallback;
import com.huawei.datatype.DeviceStatusParam;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.core.device.PowerModeChangeManager;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorMessage;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class tpb {

    /* renamed from: a, reason: collision with root package name */
    private HandlerThread f17295a;
    private Handler b;
    private Map<String, Integer> d;

    private tpb() {
        this.d = new ConcurrentHashMap();
        LogUtil.a("WearEngine_ConnectionStatusHandleManager", "enter ConnectionStatusHandleManager");
        d();
    }

    static class c {
        private static final tpb b = new tpb();
    }

    public static tpb a() {
        return c.b;
    }

    private void d() {
        HandlerThread handlerThread = new HandlerThread("SaveDeviceCapabilityThread");
        this.f17295a = handlerThread;
        handlerThread.start();
        if (this.f17295a.getLooper() == null) {
            LogUtil.h("WearEngine_ConnectionStatusHandleManager", "initHandler mSaveCapabilityThread getLooper is null!");
        } else {
            this.b = new Handler(this.f17295a.getLooper()) { // from class: tpb.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    tpb.this.fdn_(message);
                }
            };
        }
    }

    private void b(DeviceInfo deviceInfo) {
        LogUtil.a("WearEngine_ConnectionStatusHandleManager", "onReceive connectState device: ", CommonUtil.l(deviceInfo.getDeviceIdentify()), ", connectState: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
        Message obtain = Message.obtain();
        obtain.what = 1000;
        obtain.obj = deviceInfo;
        this.b.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fdn_(Message message) {
        if (message.what == 1000) {
            DeviceInfo deviceInfo = message.obj instanceof DeviceInfo ? (DeviceInfo) message.obj : null;
            if (deviceInfo == null) {
                LogUtil.b("WearEngine_ConnectionStatusHandleManager", "handleDeviceStateChangeMessage deviceInfo is null");
                return;
            }
            if (deviceInfo.getDeviceConnectState() == 2) {
                tog.b();
                tqy.l(deviceInfo);
            }
            boolean i = tqy.i(deviceInfo);
            if (i && deviceInfo.getDeviceConnectState() == 3) {
                tog.e(deviceInfo);
                tqy.a(deviceInfo);
            }
            toc.e().a(deviceInfo);
            d(deviceInfo);
            e(deviceInfo, i);
            d(deviceInfo, i);
        }
    }

    private void e(DeviceInfo deviceInfo, boolean z) {
        if (deviceInfo.getDeviceConnectState() == 2 || deviceInfo.getDeviceConnectState() == 3) {
            Device j = tqy.j(deviceInfo);
            if (!z && j == null) {
                LogUtil.b("WearEngine_ConnectionStatusHandleManager", "handleDeviceStateChangeMessage hiWearDevice is null");
                return;
            }
            int deviceConnectState = z ? 5 : deviceInfo.getDeviceConnectState();
            String uuid = TextUtils.isEmpty(deviceInfo.getDeviceUdid()) ? deviceInfo.getUuid() : deviceInfo.getDeviceUdid();
            int productType = j != null ? j.getProductType() : -1;
            LogUtil.c("WearEngine_ConnectionStatusHandleManager", "monitorReceiverOnChanged reportDeviceId: ", uuid, ", Identify: ", deviceInfo.getDeviceIdentify());
            if (deviceInfo.getDeviceConnectState() == 2) {
                c(deviceInfo, uuid, productType);
            }
            a(deviceConnectState, uuid, productType);
        }
    }

    private boolean c(DeviceInfo deviceInfo, String str, int i) {
        if (!a(deviceInfo)) {
            return false;
        }
        if (tqy.f(deviceInfo)) {
            LogUtil.a("WearEngine_ConnectionStatusHandleManager", "handleDevicePowerModeChanged GalileoDevice cant report EVENT_POWER_MODE");
            return false;
        }
        PowerModeChangeManager.a().d();
        if (tns.b().e() == null) {
            LogUtil.h("WearEngine_ConnectionStatusHandleManager", "handleDevicePowerModeChanged monitorCallback is null");
            return true;
        }
        try {
            tns.b().e().onChanged(0, new MonitorMessage().setMonitorItemType("powerMode").setDeviceId(str).setProductType(i).setIntData(tqy.b(deviceInfo)));
            LogUtil.a("WearEngine_ConnectionStatusHandleManager", "handleDevicePowerModeChanged notify ok.");
        } catch (RemoteException unused) {
            LogUtil.b("WearEngine_ConnectionStatusHandleManager", "handleDevicePowerModeChanged RemoteException.");
        } catch (Exception unused2) {
            LogUtil.b("WearEngine_ConnectionStatusHandleManager", "handleDevicePowerModeChanged send EventMessage failed.");
        }
        return true;
    }

    private boolean a(DeviceInfo deviceInfo) {
        Integer num;
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        boolean z = false;
        if (deviceIdentify == null) {
            LogUtil.h("WearEngine_ConnectionStatusHandleManager", "isPowerModeChanged deviceIdentify is null");
            return false;
        }
        if (this.d.containsKey(deviceIdentify) && (num = this.d.get(deviceIdentify)) != null && num.intValue() != deviceInfo.getPowerSaveModel()) {
            z = true;
        }
        if (!this.d.containsKey(deviceIdentify) || z) {
            this.d.put(deviceIdentify, Integer.valueOf(deviceInfo.getPowerSaveModel()));
        }
        LogUtil.a("WearEngine_ConnectionStatusHandleManager", "isPowerModeChanged result ", Boolean.valueOf(z));
        return z;
    }

    private void a(int i, String str, int i2) {
        try {
            if (tns.b().e() == null) {
                LogUtil.h("WearEngine_ConnectionStatusHandleManager", "handleConnectionStatusChanged monitorCallback is null");
            } else {
                tns.b().e().onChanged(0, new MonitorMessage().setMonitorItemType("connectionStatus").setDeviceId(str).setProductType(i2).setIntData(i));
                LogUtil.a("WearEngine_ConnectionStatusHandleManager", "handleConnectionStatusChanged notify ok.");
            }
        } catch (RemoteException unused) {
            LogUtil.b("WearEngine_ConnectionStatusHandleManager", "handleConnectionStatusChanged RemoteException.");
        } catch (Exception unused2) {
            LogUtil.b("WearEngine_ConnectionStatusHandleManager", "handleConnectionStatusChanged send EventMessage failed.");
        }
    }

    private void d(DeviceInfo deviceInfo, boolean z) {
        String str;
        if (!tqy.c("com.huawei.action.NOTIFICATION_DECISION_CENTER")) {
            LogUtil.a("WearEngine_ConnectionStatusHandleManager", "handleNotificationCoordinationMsg provider not support");
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 2 || deviceInfo.getDeviceConnectState() == 3) {
            boolean c2 = cwi.c(deviceInfo, 119);
            int deviceConnectState = z ? 5 : deviceInfo.getDeviceConnectState();
            LogUtil.a("WearEngine_ConnectionStatusHandleManager", "handleNotificationCoordinationMsg connectState: " + deviceConnectState);
            if (deviceConnectState != 5 && !c2) {
                LogUtil.a("WearEngine_ConnectionStatusHandleManager", "handleNotificationCoordinationMsg device not support");
                return;
            }
            if (deviceConnectState == 2) {
                str = "com.huawei.wearengine.NotificationCoordination.add";
            } else if (deviceConnectState == 3) {
                str = "com.huawei.wearengine.NotificationCoordination.update";
            } else if (deviceConnectState != 5) {
                return;
            } else {
                str = "com.huawei.wearengine.NotificationCoordination.delete";
            }
            tpn d = tpn.d();
            if (d == null) {
                LogUtil.h("WearEngine_ConnectionStatusHandleManager", "handleNotificationCoordinationMsg instance is null");
            } else {
                d.d(str, deviceInfo);
            }
        }
    }

    private void d(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceConnectState() == 2 || deviceInfo.getDeviceConnectState() == 3) {
            boolean[] zArr = {false, false, false, false};
            for (DeviceInfo deviceInfo2 : cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearEngine_ConnectionStatusHandleManager")) {
                if (deviceInfo2 != null) {
                    a(zArr, 0, deviceInfo2);
                    if (tqy.g(deviceInfo2)) {
                        if (!zArr[1]) {
                            zArr[1] = true;
                        }
                        a(zArr, 2, deviceInfo2);
                        d(zArr, deviceInfo2);
                    }
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("isHasConnectedWearDevice", zArr[0]);
                jSONObject.put("isHasSupportHiWearDevice", zArr[1]);
                jSONObject.put("isHasAvailableDevice", zArr[2]);
                jSONObject.put("isHasSupportCommonDevice", zArr[3]);
                SharedPreferenceManager.c(String.valueOf(53), "key_wearEngine_device", jSONObject.toString());
                LogUtil.a("WearEngine_ConnectionStatusHandleManager", "updateContentProviderData setWearEngineDevice done");
            } catch (JSONException unused) {
                LogUtil.b("WearEngine_ConnectionStatusHandleManager", "updateContentProviderData setWearEngineDevice has an JSONException");
            }
            try {
                BaseApplication.getContext().getContentResolver().notifyChange(Uri.parse("content://com.huawei.healthcloud.health.provider/wear_device_state"), null);
            } catch (Exception unused2) {
                LogUtil.b("WearEngine_ConnectionStatusHandleManager", "notifyChange has an Exception");
            }
        }
    }

    private void a(boolean[] zArr, int i, DeviceInfo deviceInfo) {
        if (zArr[i] || deviceInfo.getDeviceConnectState() != 2) {
            return;
        }
        zArr[i] = true;
    }

    private void d(boolean[] zArr, DeviceInfo deviceInfo) {
        if (zArr[3] || !tqy.d(deviceInfo, true)) {
            return;
        }
        zArr[3] = true;
    }

    public void e() {
        jsg.d("WearEngine", new DeviceStatusCallback() { // from class: tpi
            @Override // com.huawei.callback.DeviceStatusCallback
            public final void onDeviceConnectedChanged(DeviceInfo deviceInfo, int i, DeviceStatusParam deviceStatusParam) {
                tpb.this.e(deviceInfo, i, deviceStatusParam);
            }
        });
    }

    /* synthetic */ void e(DeviceInfo deviceInfo, int i, DeviceStatusParam deviceStatusParam) {
        LogUtil.a("WearEngine_ConnectionStatusHandleManager", "enter ConnectionChangeListener");
        if (deviceInfo == null) {
            LogUtil.b("WearEngine_ConnectionStatusHandleManager", "onConnectionChange deviceInfo is null");
        } else {
            b(deviceInfo);
        }
    }
}
