package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jvf {

    /* renamed from: a, reason: collision with root package name */
    private static jvf f14117a;
    private static Map<String, jwb> c = new HashMap(16);
    private static final Object d = new Object();
    private BroadcastReceiver b = new BroadcastReceiver() { // from class: jvf.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null || !"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                return;
            }
            DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
            if (deviceInfo == null) {
                LogUtil.h("HwFileServicesManager", "onReceive deviceInfo is null");
                return;
            }
            LogUtil.a("HwFileServicesManager", "mConnectStateChangedReceiver() status = ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            if (deviceConnectState != 2) {
                if (deviceConnectState == 3 || deviceConnectState == 4) {
                    jvu.b(deviceInfo);
                } else {
                    LogUtil.c("HwFileServicesManager", "mConnectStateChangedReceiver() default branch.");
                }
            }
        }
    };

    private jvf() {
        jvv.a();
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.b, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    public static jvf e() {
        jvf jvfVar;
        synchronized (d) {
            if (f14117a == null) {
                f14117a = new jvf();
            }
            jvfVar = f14117a;
        }
        return jvfVar;
    }

    public static Map<String, jwb> c() {
        return c;
    }

    public void a() {
        BaseApplication.getContext().unregisterReceiver(this.b);
    }

    public void e(byte[] bArr, DeviceInfo deviceInfo) {
        blt.d("HwFileServicesManager", bArr, "getResult()  message: ");
        jvs.e(bArr, deviceInfo);
    }

    public void d(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(new byte[]{Byte.MAX_VALUE, 4, 0, 1, -122, -91});
        deviceCommand.setDataLen(6);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }
}
