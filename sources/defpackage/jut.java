package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.datatype.DeviceCommand;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class jut {
    private static jut b;
    private static final Object e = new Object();
    private Map<String, Boolean> c;
    private String f;
    private List<String> h;
    private Map<String, d> g = new ConcurrentHashMap(16);
    private Map<String, d> i = new ConcurrentHashMap(16);
    private Map<String, Integer> j = new HashMap(16);

    /* renamed from: a, reason: collision with root package name */
    private boolean f14102a = false;
    private d d = new d(Looper.getMainLooper());

    class d extends Handler {
        d(Looper looper) {
            super(looper);
            LogUtil.c("DeviceBackupManage", "ProcessHandler start");
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                LogUtil.c("DeviceBackupManage", "ProcessHandler message is null");
                return;
            }
            int i = message.what;
            if (i == 100) {
                LogUtil.c("DeviceBackupManage", "ProcessHandler command time out");
                jut.this.a((String) message.obj);
            } else {
                if (i != 200) {
                    if (i != 300) {
                        return;
                    }
                    LogUtil.c("DeviceBackupManage", "sendOldCloneCommand start");
                    jut.this.a((DeviceInfo) message.obj);
                    return;
                }
                LogUtil.c("DeviceBackupManage", "ProcessHandler retry connect");
                jtd.b().a(jtd.b().a((String) message.obj), true, ConnectMode.GENERAL);
            }
        }
    }

    public static jut c() {
        jut jutVar;
        synchronized (e) {
            if (b == null) {
                b = new jut();
            }
            jutVar = b;
        }
        return jutVar;
    }

    private jut() {
        LogUtil.c("DeviceBackupManage", "DeviceBackupManage init success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        List<String> list = this.h;
        if (list == null || !list.contains(str)) {
            return;
        }
        LogUtil.c("DeviceBackupManage", "clearOldDeviceMap isSuccess: ", Boolean.valueOf(this.h.remove(str)));
        if (this.h.size() == 0) {
            this.h.clear();
            LogUtil.c("DeviceBackupManage", "clearOldDeviceMap set empty");
            this.h = null;
        }
    }

    public String d() {
        return this.f;
    }

    public void b(DeviceInfo deviceInfo) {
        LogUtil.c("DeviceBackupManage", "sendOldCloneCommandDelay enter");
        List<String> list = this.h;
        if (list == null || list.size() <= 0) {
            return;
        }
        LogUtil.c("DeviceBackupManage", "sendOldCloneCommandDelay size not zero");
        Message obtain = Message.obtain();
        obtain.what = 300;
        obtain.obj = deviceInfo;
        this.d.sendMessageDelayed(obtain, 5000L);
        LogUtil.c("DeviceBackupManage", "sendOldCloneCommandDelay end, threadName: ", Thread.currentThread().getName());
    }

    public void b(String str) {
        this.f = str;
    }

    public void e(Map<String, Boolean> map) {
        this.c = map;
    }

    public void d(List<String> list) {
        this.h = list;
    }

    public void a(DeviceInfo deviceInfo) {
        LogUtil.c("DeviceBackupManage", "sendOldPhoneCommand identify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
        if (!bmm.a(deviceInfo, 223)) {
            LogUtil.a("DeviceBackupManage", "sendOldPhoneCommand device is not support.");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(58);
        String c = cvx.c(d());
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(2));
        sb.append(cvx.e(c.length() / 2));
        sb.append(c);
        sb.append(cvx.e(4));
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        deviceCommand.setDataLen(sb.length());
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        LogUtil.c("DeviceBackupManage", "sendOldPhoneCommand: ", cvx.d(deviceCommand.getDataContent()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        i(deviceInfo);
    }

    public void d(DeviceInfo deviceInfo, biu biuVar) {
        LogUtil.c("DeviceBackupManage", "dealOldPhoneResult identify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
        List<String> list = this.h;
        if (list == null || list.size() == 0) {
            LogUtil.a("DeviceBackupManage", "dealOldPhoneResult return.");
            return;
        }
        if (!bmm.a(deviceInfo, 223)) {
            LogUtil.a("DeviceBackupManage", "dealOldPhoneResult device is not support.");
            return;
        }
        if (this.h.contains(deviceInfo.getDeviceIdentify())) {
            d dVar = this.g.get(deviceInfo.getDeviceIdentify());
            if (dVar != null) {
                dVar.removeMessages(100);
                this.g.remove(deviceInfo.getDeviceIdentify());
            }
            a(deviceInfo.getDeviceIdentify());
            bmj e2 = bhh.e(biuVar.a());
            if (e2 == null) {
                LogUtil.a("DeviceBackupManage", "dealOldPhoneResult tlvFather is null");
                return;
            }
            int i = 0;
            for (bmi bmiVar : e2.b()) {
                if (bli.e(bmiVar.e()) == 3) {
                    i = bli.e(bmiVar.c());
                }
            }
            LogUtil.c("DeviceBackupManage", "dealOldPhoneResult confirmResult: ", Integer.valueOf(i));
            if (i == 0) {
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(deviceInfo.getDeviceIdentify());
                jsz.b(BaseApplication.getContext()).unPair(arrayList, false);
                return;
            }
            LogUtil.a("DeviceBackupManage", "dealOldPhoneResult do not");
        }
    }

    public void d(DeviceInfo deviceInfo) {
        LogUtil.c("DeviceBackupManage", "dealOldPhoneConnectFail identify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
        Map<String, Boolean> map = this.c;
        if (map == null || map.size() == 0) {
            LogUtil.a("DeviceBackupManage", "dealOldPhoneConnectFail return.");
            return;
        }
        if (!bmm.a(deviceInfo, 223)) {
            LogUtil.a("DeviceBackupManage", "dealOldPhoneConnectFail device is not support.");
            return;
        }
        if (this.c.containsKey(deviceInfo.getDeviceIdentify())) {
            if (this.c.get(deviceInfo.getDeviceIdentify()).booleanValue()) {
                this.c.put(deviceInfo.getDeviceIdentify(), false);
                jtd.b().a(jtd.b().a(deviceInfo.getDeviceIdentify()), true, ConnectMode.GENERAL);
            } else {
                a(deviceInfo.getDeviceIdentify());
                this.c.remove(deviceInfo.getDeviceIdentify());
            }
        }
    }

    public void e(DeviceInfo deviceInfo) {
        if (deviceInfo == null || !a()) {
            LogUtil.a("DeviceBackupManage", "dealNewPhoneConnectFail is not clone.");
            return;
        }
        if (!bmm.a(deviceInfo, 223)) {
            LogUtil.a("DeviceBackupManage", "dealNewPhoneConnectFail device is not support.");
            return;
        }
        LogUtil.a("DeviceBackupManage", "dealNewPhoneConnectFail app package: ", BaseApplication.getAppPackage());
        if (CommonUtil.d(BaseApplication.getAppPackage(), BaseApplication.getContext())) {
            LogUtil.a("DeviceBackupManage", "dealNewPhoneConnectFail main process running, return.");
            a(false);
            return;
        }
        if (this.j.size() == 0) {
            LogUtil.a("DeviceBackupManage", "dealNewPhoneConnectFail mNewPhoneReconnectDeviceMap is zero.");
            a(false);
            return;
        }
        if (!this.j.containsKey(deviceInfo.getDeviceIdentify())) {
            LogUtil.a("DeviceBackupManage", "dealNewPhoneConnectFail is not contains.");
            return;
        }
        if (this.j.get(deviceInfo.getDeviceIdentify()) == null) {
            LogUtil.a("DeviceBackupManage", "dealNewPhoneConnectFail value is null.");
            return;
        }
        int intValue = this.j.get(deviceInfo.getDeviceIdentify()).intValue();
        if (intValue >= 2) {
            LogUtil.a("DeviceBackupManage", "dealNewPhoneConnectFail count is 2, return.");
            this.j.remove(deviceInfo.getDeviceIdentify());
            return;
        }
        this.j.put(deviceInfo.getDeviceIdentify(), Integer.valueOf(intValue));
        d dVar = this.i.get(deviceInfo.getDeviceIdentify());
        if (dVar != null) {
            Message obtainMessage = dVar.obtainMessage(200);
            obtainMessage.obj = deviceInfo.getDeviceIdentify();
            dVar.sendMessageDelayed(obtainMessage, 60000L);
        }
    }

    public void b() {
        if (!a()) {
            LogUtil.a("DeviceBackupManage", "initNewPhoneData is not clone.");
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "initNewPhoneData");
        if (deviceList == null || deviceList.size() == 0) {
            return;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (bmm.a(deviceInfo, 223) && deviceInfo.getDeviceConnectState() != 2) {
                this.j.put(deviceInfo.getDeviceIdentify(), 0);
                if (this.i.get(deviceInfo.getDeviceIdentify()) == null) {
                    this.i.put(deviceInfo.getDeviceIdentify(), new d(Looper.getMainLooper()));
                }
            }
        }
        e();
        LogUtil.c("DeviceBackupManage", "initNewPhoneData mNewPhoneReconnectDeviceMap size: ", Integer.valueOf(this.j.size()));
    }

    public void c(DeviceInfo deviceInfo) {
        LogUtil.c("DeviceBackupManage", "removeNewPhoneDeviceMapByMac deviceIdentify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
        if (deviceInfo == null || !a()) {
            LogUtil.a("DeviceBackupManage", "removeNewPhoneDeviceMapByMac is not clone.");
            return;
        }
        if (this.j.containsKey(deviceInfo.getDeviceIdentify())) {
            d dVar = this.i.get(deviceInfo.getDeviceIdentify());
            if (dVar != null) {
                dVar.removeMessages(200);
                this.i.remove(deviceInfo.getDeviceIdentify());
            }
            this.j.remove(deviceInfo.getDeviceIdentify());
            LogUtil.a("DeviceBackupManage", "cancelReconnectTimerTask deviceIdentify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
            e();
        }
        LogUtil.c("DeviceBackupManage", "removeNewPhoneDeviceMapByMac mNewPhoneReconnectDeviceMap size: ", Integer.valueOf(this.j.size()));
    }

    private void e() {
        if (this.j.size() == 0) {
            a(false);
        }
    }

    private void i(DeviceInfo deviceInfo) {
        d dVar = this.g.get(deviceInfo.getDeviceIdentify());
        if (dVar == null) {
            dVar = new d(Looper.getMainLooper());
            this.g.put(deviceInfo.getDeviceIdentify(), dVar);
            LogUtil.a("DeviceBackupManage", "create new timeOutHandler");
        }
        Message obtainMessage = dVar.obtainMessage(100);
        obtainMessage.obj = deviceInfo.getDeviceIdentify();
        dVar.sendMessageDelayed(obtainMessage, OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    public void a(boolean z) {
        this.f14102a = z;
    }

    public boolean a() {
        return this.f14102a;
    }
}
