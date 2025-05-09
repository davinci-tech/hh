package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender;
import com.huawei.hwlogsmodel.LogUtil;
import java.nio.ByteBuffer;
import java.util.Map;

/* loaded from: classes5.dex */
public class jzg {
    public void c(final DeviceInfo deviceInfo) {
        LogUtil.a("ConnectStateChangeHandler", "handleDeviceConnected: ");
        if (deviceInfo == null) {
            LogUtil.h("ConnectStateChangeHandler", "handleDeviceConnected: deviceInfo is null.");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jzk
                @Override // java.lang.Runnable
                public final void run() {
                    jzg.this.e(deviceInfo);
                }
            });
        }
    }

    /* synthetic */ void e(DeviceInfo deviceInfo) {
        a("contact_sync_" + kak.b(deviceInfo.getDeviceIdentify()), deviceInfo);
    }

    public void d(final DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("ConnectStateChangeHandler", "handleDeviceDisconnected: deviceInfo is null.");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jzf
                @Override // java.lang.Runnable
                public final void run() {
                    jzg.this.b(deviceInfo);
                }
            });
        }
    }

    /* synthetic */ void b(DeviceInfo deviceInfo) {
        if (!c()) {
            LogUtil.h("ConnectStateChangeHandler", "handleDeviceState: have no contacts permissions");
        } else if (!a(deviceInfo)) {
            LogUtil.h("ConnectStateChangeHandler", "handleDeviceState: is not supported sync contact capability.");
        } else if (deviceInfo.getDeviceConnectState() == 3) {
            a();
        }
    }

    private void a(String str, DeviceInfo deviceInfo) {
        LogUtil.a("ConnectStateChangeHandler", "handleReconnectedSync: ");
        jze a2 = jze.a();
        if (!kae.c()) {
            f(deviceInfo);
            a2.b(BaseApplication.getContext(), str);
            LogUtil.h("ConnectStateChangeHandler", "handleReconnectedSync: reconnect ok but cannot sync contacts with no contact permission.");
            return;
        }
        Boolean e = a2.e(str);
        LogUtil.a("ConnectStateChangeHandler", "isChangedContacts: ", e);
        if (e == null || !e.booleanValue()) {
            a2.e(deviceInfo);
            a2.b(str, 0);
        } else {
            LogUtil.a("ConnectStateChangeHandler", "The mobile phone contact does not change.");
        }
        a2.b(BaseApplication.getContext(), str);
    }

    private void f(DeviceInfo deviceInfo) {
        LogUtil.a("ConnectStateChangeHandler", "responseNoPermissions: report to device with no contacts permission");
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put(Byte.MAX_VALUE);
        allocate.put((byte) 4);
        allocate.putInt(100004);
        ContactsDataSender.e().e(4, allocate.array(), deviceInfo);
    }

    private void a() {
        LogUtil.a("ConnectStateChangeHandler", "onDisconnected: ");
        jze.a().b();
    }

    private boolean a(DeviceInfo deviceInfo) {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "ConnectStateChangeHandler");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            LogUtil.h("ConnectStateChangeHandler", "getDeviceSupportCapacity, deviceCapabilityHashMaps is empty");
            return false;
        }
        DeviceCapability deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability != null) {
            return deviceCapability.isSupportSyncContacts();
        }
        return false;
    }

    private boolean c() {
        return kae.c();
    }
}
