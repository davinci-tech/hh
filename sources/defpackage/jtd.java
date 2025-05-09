package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.devicesdk.strategy.CommandTimerTask;
import com.huawei.haf.common.dfx.memory.MemoryMonitor;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hms.network.embedded.k;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.DeviceStateCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.bir;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class jtd {
    private DeviceStateCallback h;
    private static final Object c = new Object();
    private static final Map<Integer, String> b = new HashMap<Integer, String>(2) { // from class: jtd.1
        private static final long serialVersionUID = 6412797464020131549L;

        {
            put(9, "true");
            put(10, "true");
        }
    };
    private static jtd d = null;

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14065a = new Object();
    private snq f = null;
    private CopyOnWriteArrayList<CommandTimerTask> e = new CopyOnWriteArrayList<>();
    private Context g = BaseApplication.getContext();
    private Timer j = new Timer("HwHandleUniteDeviceMgr");

    private jtd() {
    }

    public static jtd b() {
        jtd jtdVar;
        synchronized (c) {
            if (d == null) {
                d = new jtd();
            }
            jtdVar = d;
        }
        return jtdVar;
    }

    public void d(snq snqVar) {
        this.f = snqVar;
        if (snqVar != null) {
            snqVar.a(this.g);
        }
    }

    public void c() {
        i("");
    }

    private void i(String str) {
        if (str == null) {
            return;
        }
        if (str.isEmpty()) {
            LogUtil.a("HwHandleUniteDeviceMgr", "saveAutoRemoveDevice clear auto remove device info.");
        }
        LogUtil.a("HwHandleUniteDeviceMgr", "saveAutoRemoveDevice identify", iyl.d().e(str));
        SharedPreferenceManager.e(this.g, String.valueOf(1018), "deviceAutoRemoveIdentify", str, new StorageParams(1));
    }

    public List<Integer> b(List<DeviceInfo> list, String str) {
        LogUtil.a("HwHandleUniteDeviceMgr", "findWantedSwitchDevice enter");
        ArrayList arrayList = new ArrayList(24);
        if (list == null) {
            return arrayList;
        }
        for (int i = 0; i < list.size(); i++) {
            DeviceInfo deviceInfo = list.get(i);
            if (deviceInfo != null && deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getDeviceConnectState() != 2) {
                if (deviceInfo.getDeviceIdentify().equals(str)) {
                    LogUtil.a("HwHandleUniteDeviceMgr", "findWantedSwitchDevice find ", deviceInfo.getDeviceName());
                    arrayList.add(Integer.valueOf(i));
                } else {
                    LogUtil.h("HwHandleUniteDeviceMgr", "manual reconnect:", iyl.d().e(str), ",traversal:", iyl.d().e(deviceInfo.getDeviceIdentify()));
                }
            }
        }
        return arrayList;
    }

    public boolean c(DeviceInfo deviceInfo) {
        boolean isLogined;
        if (deviceInfo == null) {
            LogUtil.h("HwHandleUniteDeviceMgr", "reconnectDeviceInfo is null");
            return false;
        }
        boolean z = deviceInfo.getSupportAccountSwitch() == 1;
        LogUtil.a("HwHandleUniteDeviceMgr", "reconnectDeviceInfo is ", deviceInfo.getDeviceName(), "isSupportAccountSwitch ", Boolean.valueOf(z));
        if (!z) {
            return true;
        }
        boolean i = Utils.i();
        LogUtil.a("HwHandleUniteDeviceMgr", "isReconnectionDevice ifAllowLogin ", Boolean.valueOf(i));
        if (!i) {
            return true;
        }
        if (CommonUtil.z(this.g)) {
            String logoutFlag = ThirdLoginDataStorageUtil.getLogoutFlag();
            LogUtil.a("HwHandleUniteDeviceMgr", "isReconnectionDevice logoutFlag ", logoutFlag);
            isLogined = "false".equals(logoutFlag);
        } else {
            isLogined = LoginInit.getInstance(this.g).getIsLogined();
        }
        ReleaseLogUtil.b("R_HwHandleUniteDeviceMgr", "isReconnectionDevice isLogin ", Boolean.valueOf(isLogined));
        return isLogined;
    }

    public List<UniteDevice> h() {
        ArrayList arrayList = new ArrayList(24);
        Map<String, UniteDevice> e = e();
        if (!e.isEmpty()) {
            arrayList.addAll(e.values());
        }
        LogUtil.a("HwHandleUniteDeviceMgr", "return getUniteDeviceInfolist() size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public List<DeviceInfo> d() {
        ArrayList arrayList = new ArrayList(24);
        List<UniteDevice> h = h();
        if (h.size() != 0) {
            Iterator<UniteDevice> it = h.iterator();
            while (it.hasNext()) {
                arrayList.add(blc.c(it.next()));
            }
        }
        List<DeviceInfo> d2 = jta.d().d(arrayList);
        if (d2.size() == 0) {
            LogUtil.h("HwHandleUniteDeviceMgr", "getDeviceInfolist deviceRelationList size is zero.");
        }
        return d2;
    }

    public UniteDevice a(String str) {
        if (str == null) {
            LogUtil.h("HwHandleUniteDeviceMgr", "getUniteDeviceInfo mac is null.");
            return null;
        }
        Map<String, UniteDevice> e = e();
        if (e.size() != 0) {
            return e.get(str);
        }
        LogUtil.a("HwHandleUniteDeviceMgr", "return getUniteDeviceInfo is null.");
        return null;
    }

    public DeviceInfo e(com.huawei.devicesdk.entity.DeviceInfo deviceInfo, List<DeviceInfo> list) {
        DeviceInfo deviceInfo2 = new DeviceInfo();
        if (deviceInfo == null) {
            LogUtil.a("HwHandleUniteDeviceMgr", "convertTargetDeviceInfo deviceInfo is null.");
            return deviceInfo2;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("HwHandleUniteDeviceMgr", "convertTargetDeviceInfo deviceInfo deviceIdentify is null.");
            return deviceInfo2;
        }
        if (bkz.e(list)) {
            deviceInfo2.setDeviceBluetoothType(deviceInfo.getDeviceBtType());
            deviceInfo2.setDeviceIdentify(deviceInfo.getDeviceMac());
            deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
            deviceInfo2.setProductType(deviceInfo.getDeviceType());
            return deviceInfo2;
        }
        Iterator<DeviceInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (deviceInfo.getDeviceMac().equalsIgnoreCase(next.getDeviceIdentify())) {
                deviceInfo2 = next;
                break;
            }
        }
        if (TextUtils.isEmpty(deviceInfo2.getDeviceIdentify())) {
            deviceInfo2.setDeviceIdentify(deviceInfo.getDeviceMac());
        }
        if (TextUtils.isEmpty(deviceInfo2.getDeviceName())) {
            deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
        }
        if (deviceInfo2.getProductType() == -1) {
            deviceInfo2.setProductType(deviceInfo.getDeviceType());
        }
        if (deviceInfo2.getDeviceBluetoothType() == -1) {
            deviceInfo2.setDeviceBluetoothType(deviceInfo.getDeviceBtType());
        }
        return deviceInfo2;
    }

    public DeviceCapability e(DeviceInfo deviceInfo, Map<String, UniteDevice> map) {
        UniteDevice uniteDevice;
        if (deviceInfo == null || map == null || deviceInfo.getDeviceIdentify() == null) {
            LogUtil.h("HwHandleUniteDeviceMgr", "getDeviceInfoCapability input parameter is null.");
            return null;
        }
        if (map.containsKey(deviceInfo.getDeviceIdentify())) {
            LogUtil.a("HwHandleUniteDeviceMgr", "getDeviceInfoCapability id:", CommonUtil.l(deviceInfo.getDeviceIdentify()));
            uniteDevice = map.get(deviceInfo.getDeviceIdentify());
        } else {
            uniteDevice = null;
        }
        if (uniteDevice != null) {
            return blc.a(uniteDevice);
        }
        LogUtil.a("HwHandleUniteDeviceMgr", "return getDeviceInfoCapability is null.");
        return null;
    }

    public void d(DeviceStateCallback deviceStateCallback) {
        LogUtil.a("HwHandleUniteDeviceMgr", "Enter registerDeviceStateCallback().");
        this.h = deviceStateCallback;
    }

    public void i() {
        LogUtil.a("HwHandleUniteDeviceMgr", "enter unRegisterDeviceStateCallback().");
        this.h = null;
    }

    public boolean e(String str) {
        boolean z = a(str) != null;
        LogUtil.a("HwHandleUniteDeviceMgr", "isNewDeviceList: ", Boolean.valueOf(z));
        return z;
    }

    public void a(UniteDevice uniteDevice) {
        String str;
        DeviceInfo deviceInfo;
        if (uniteDevice != null) {
            str = uniteDevice.getIdentify();
            deviceInfo = blc.c(uniteDevice);
        } else {
            str = null;
            deviceInfo = null;
        }
        LogUtil.a("HwHandleUniteDeviceMgr", "disconnectBluetooth enter, goingDisconnectDevice", CommonUtil.l(str));
        if (TextUtils.isEmpty(str) || deviceInfo == null) {
            return;
        }
        LogUtil.a("HwHandleUniteDeviceMgr", "addDevice goingDisconnectDevice is not null.");
        this.f.disconnect(uniteDevice);
        if (deviceInfo.getDeviceActiveState() == 1) {
            LogUtil.a("HwHandleUniteDeviceMgr", "Start to send disconnect broadcast to EMUI.");
            b(deviceInfo);
        }
    }

    public void b(DeviceInfo deviceInfo) {
        LogUtil.a("HwHandleUniteDeviceMgr", "EMUI sendDisconnectBroadcastToEmui.");
        if (deviceInfo == null) {
            LogUtil.h("HwHandleUniteDeviceMgr", "deviceInfo is null.");
            return;
        }
        if (!deviceInfo.getDeviceIdentify().equalsIgnoreCase("AndroidWear")) {
            if (TextUtils.isEmpty(deviceInfo.getNodeId())) {
                try {
                    Intent intent = new Intent("com.huawei.iconnect.action.DEVICE_BOND_STATE_CHANGED");
                    intent.setComponent(new ComponentName("com.huawei.iconnect", "com.huawei.iconnect.MessageIntentService"));
                    if (jss.b()) {
                        intent.putExtra("DEVICE_ID", jss.e(deviceInfo.getDeviceIdentify()));
                    } else {
                        intent.putExtra("com.huawei.iconnect.extra.DEVICE_MAC_ADDRESS", deviceInfo.getDeviceIdentify());
                    }
                    intent.putExtra("android.bluetooth.device.extra.BOND_STATE", 10);
                    intent.putExtra("com.huawei.iconnect.extra.PACKAGE_NAME", "com.huawei.health");
                    this.g.startService(intent);
                } catch (SecurityException unused) {
                    LogUtil.b("HwHandleUniteDeviceMgr", "0xA0200008", ",securityException");
                }
                LogUtil.c("HwHandleUniteDeviceMgr", "EMUI SendDisconnectBroadcastToEmui, send BOND_NONE broadcast, DeviceIdentify", iyl.d().e(deviceInfo.getDeviceIdentify()));
                return;
            }
            return;
        }
        LogUtil.c("HwHandleUniteDeviceMgr", "EMUI SendDisconnectBroadcastToEmui, don't need send broadcast for AndroidWear");
    }

    public boolean e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwHandleUniteDeviceMgr", "isSupportBrConnect deviceInfo is null.");
            return false;
        }
        if (deviceInfo.getDeviceBluetoothType() == 1 && deviceInfo.getDeviceConnectState() != 2 && deviceInfo.getDeviceConnectState() != 1 && deviceInfo.getDeviceActiveState() == 1) {
            if (bkt.e().a().contains(deviceInfo.getDeviceIdentify())) {
                LogUtil.c("HwHandleUniteDeviceMgr", "HFP or HID is Connected");
                return true;
            }
            LogUtil.h("HwHandleUniteDeviceMgr", "isSupportBrConnect is false.");
        }
        return false;
    }

    public void a() {
        synchronized (c) {
            d((jtd) null);
        }
    }

    private static void d(jtd jtdVar) {
        d = jtdVar;
    }

    public void c(DeviceCommand deviceCommand) {
        if (deviceCommand == null) {
            return;
        }
        jte.e(deviceCommand);
        com.huawei.devicesdk.entity.DeviceInfo deviceInfo = new com.huawei.devicesdk.entity.DeviceInfo();
        deviceInfo.setDeviceMac(deviceCommand.getmIdentify());
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setIdentify(deviceCommand.getmIdentify());
        uniteDevice.setDeviceInfo(deviceInfo);
        a(uniteDevice, deviceCommand);
        e(uniteDevice, deviceCommand);
    }

    public void d(izf izfVar) {
        if (izfVar == null) {
            return;
        }
        c(jte.b(izfVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(UniteDevice uniteDevice, DeviceCommand deviceCommand) {
        byte[] dataContent = deviceCommand.getDataContent();
        ByteBuffer allocate = ByteBuffer.allocate(dataContent.length + 2);
        allocate.put((byte) deviceCommand.getServiceID());
        allocate.put((byte) deviceCommand.getCommandID());
        allocate.put(dataContent);
        bir birVar = new bir();
        birVar.e(allocate.array());
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        LogUtil.a("HwHandleUniteDeviceMgr", "sendTransparentCommand getServiceID: ", Integer.valueOf(deviceCommand.getServiceID()));
        bir.a aVar = new bir.a();
        aVar.c(b(deviceCommand.getServiceID(), deviceCommand.getNeedEncrypt()));
        aVar.d(deviceCommand.getPriority());
        this.f.sendCommand(uniteDevice, aVar.b(birVar));
    }

    private boolean b(int i, boolean z) {
        if (!z) {
            return false;
        }
        if ("true".equals(b.get(Integer.valueOf(i)))) {
            z = false;
        }
        LogUtil.a("HwHandleUniteDeviceMgr", "isNeedEncrypt is ", Boolean.valueOf(z));
        return z;
    }

    private void a(final UniteDevice uniteDevice, final DeviceCommand deviceCommand) {
        if (deviceCommand.getNeedAck()) {
            final String str = cvx.e(deviceCommand.getServiceId()) + cvx.e(deviceCommand.getCommandId());
            CommandTimerTask commandTimerTask = new CommandTimerTask(str, 2) { // from class: jtd.5
                @Override // com.huawei.devicesdk.strategy.CommandTimerTask
                public void doTaskAction() {
                    LogUtil.a("HwHandleUniteDeviceMgr", "checkAckCommand ServiceID: ", Integer.valueOf(deviceCommand.getServiceID()), ", CommandId:", Integer.valueOf(deviceCommand.getCommandId()));
                    if (!jtd.this.d(uniteDevice)) {
                        jtd.this.e(uniteDevice, deviceCommand);
                    } else {
                        LogUtil.h("HwHandleUniteDeviceMgr", "isCancelSendData");
                    }
                }

                @Override // com.huawei.devicesdk.strategy.CommandTimerTask
                public void doTimeoutAction() {
                    if (jtd.this.h != null) {
                        byte[] a2 = cvx.a(str + (cvx.e(127) + cvx.e(4) + cvx.b(100009L)));
                        DeviceInfo deviceInfo = new DeviceInfo();
                        deviceInfo.setDeviceIdentify(uniteDevice.getIdentify());
                        jtd.this.h.onDataReceived(deviceInfo, a2.length, a2);
                    }
                }
            };
            this.j.schedule(commandTimerTask, PreConnectManager.CONNECT_INTERNAL, PreConnectManager.CONNECT_INTERNAL);
            this.e.add(commandTimerTask);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(UniteDevice uniteDevice) {
        List<DeviceInfo> d2 = b().d();
        if (d2.size() == 0) {
            return true;
        }
        for (DeviceInfo deviceInfo : d2) {
            if (deviceInfo.getDeviceIdentify().equals(uniteDevice.getIdentify()) && deviceInfo.getDeviceConnectState() == 3) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002e, code lost:
    
        if (r2 == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
    
        r2.cancel();
        r4.e.remove(r2);
        com.huawei.hwlogsmodel.LogUtil.h("HwHandleUniteDeviceMgr", "remove task ", r2.getActionName());
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0048, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(java.lang.String r5) {
        /*
            r4 = this;
            java.util.concurrent.CopyOnWriteArrayList<com.huawei.devicesdk.strategy.CommandTimerTask> r0 = r4.e
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = defpackage.jtd.f14065a
            monitor-enter(r1)
        L9:
            boolean r2 = r0.hasNext()     // Catch: java.lang.Throwable -> L49
            if (r2 == 0) goto L2c
            java.lang.Object r2 = r0.next()     // Catch: java.lang.Throwable -> L49
            boolean r3 = r2 instanceof com.huawei.devicesdk.strategy.CommandTimerTask     // Catch: java.lang.Throwable -> L49
            if (r3 != 0) goto L19
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L49
            return
        L19:
            com.huawei.devicesdk.strategy.CommandTimerTask r2 = (com.huawei.devicesdk.strategy.CommandTimerTask) r2     // Catch: java.lang.Throwable -> L49
            java.lang.String r3 = r2.getActionName()     // Catch: java.lang.Throwable -> L49
            if (r3 == 0) goto L9
            java.lang.String r3 = r2.getActionName()     // Catch: java.lang.Throwable -> L49
            boolean r3 = r3.equals(r5)     // Catch: java.lang.Throwable -> L49
            if (r3 == 0) goto L9
            goto L2d
        L2c:
            r2 = 0
        L2d:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L49
            if (r2 == 0) goto L48
            r2.cancel()
            java.util.concurrent.CopyOnWriteArrayList<com.huawei.devicesdk.strategy.CommandTimerTask> r5 = r4.e
            r5.remove(r2)
            java.lang.String r5 = "remove task "
            java.lang.String r0 = r2.getActionName()
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r0}
            java.lang.String r0 = "HwHandleUniteDeviceMgr"
            com.huawei.hwlogsmodel.LogUtil.h(r0, r5)
        L48:
            return
        L49:
            r5 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L49
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jtd.d(java.lang.String):void");
    }

    public void c(String str, boolean z) {
        LogUtil.a("HwHandleUniteDeviceMgr", "removeCurrentInstance isUnPairDevice: ", Boolean.valueOf(z));
        if (str == null) {
            LogUtil.h("HwHandleUniteDeviceMgr", "removeCurrentInstance mac is null ");
            return;
        }
        UniteDevice a2 = a(str);
        if (a2 == null) {
            a2 = new UniteDevice();
            a2.setIdentify(str);
            com.huawei.devicesdk.entity.DeviceInfo deviceInfo = new com.huawei.devicesdk.entity.DeviceInfo();
            deviceInfo.setDeviceMac(str);
            a2.setDeviceInfo(deviceInfo);
        }
        if (z) {
            this.f.unPairDevice(a2);
        } else {
            this.f.disconnect(a2);
        }
    }

    public void d(List<DeviceInfo> list) {
        boolean z = (!CommonUtil.bh() || a(list) || ((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isNoneHonourAm16BondedDevice()) ? false : true;
        LogUtil.a("HwHandleUniteDeviceMgr", "processMemoryCheck isAllowSelfKill :", Boolean.valueOf(z));
        MemoryMonitor.c(z);
    }

    private boolean a(List<DeviceInfo> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo.getDeviceConnectState() == 1 || deviceInfo.getDeviceConnectState() == 2) {
                return true;
            }
        }
        return false;
    }

    public void bJn_(Intent intent, boolean z) {
        if (intent == null) {
            LogUtil.h("HwHandleUniteDeviceMgr", "System Time intent is null");
            return;
        }
        if ("android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction()) || "android.intent.action.TIME_SET".equals(intent.getAction()) || "android.intent.action.DATE_CHANGED".equals(intent.getAction()) || z) {
            ReleaseLogUtil.b("DEVMGR_HwUniteDeviceMgr", "System Time changed with type:", intent.getAction());
            if (EnvironmentInfo.r()) {
                ReleaseLogUtil.b("DEVMGR_HwUniteDeviceMgr", "sendTimeSync harmony3.0 free start.");
                long c2 = kkn.c();
                if (c2 > 0 && c2 <= k.b.m) {
                    kkn.b(c2);
                }
            }
            kkp.c().d();
            f();
        }
    }

    public void f() {
        izf e;
        LogUtil.a("HwHandleUniteDeviceMgr", "sendTimeSync previous logic start.");
        List<DeviceInfo> c2 = c(d());
        if (c2.isEmpty()) {
            return;
        }
        Iterator<DeviceInfo> it = c2.iterator();
        DeviceCapability deviceCapability = null;
        while (it.hasNext()) {
            String deviceIdentify = it.next().getDeviceIdentify();
            UniteDevice a2 = a(deviceIdentify);
            if (a2 != null) {
                deviceCapability = blc.a(a2);
            }
            if (deviceCapability != null && deviceCapability.isSupportTimeSetting()) {
                DeviceCommand deviceCommand = new DeviceCommand();
                deviceCommand.setServiceID(1);
                deviceCommand.setCommandID(4);
                deviceCommand.setmIdentify(deviceIdentify);
                bms bmsVar = new bms();
                bmsVar.c(1);
                byte[] d2 = bmsVar.b(bmsVar.d(2, jpv.d()).d(3, jpv.e()).c()).d();
                deviceCommand.setDataLen(d2.length);
                deviceCommand.setDataContent(d2);
                ReleaseLogUtil.b("DEVMGR_HwUniteDeviceMgr", "Support Time Setting content:", deviceCommand.toString());
                c(deviceCommand);
            }
            boolean z = deviceCapability != null && deviceCapability.isSupportZoneId();
            ReleaseLogUtil.b("DEVMGR_HwUniteDeviceMgr", "isSupportZoneId:", Boolean.valueOf(z));
            if (z) {
                e = iyo.o();
            } else {
                e = iyo.e(false);
            }
            if (deviceIdentify.length() == 0) {
                return;
            }
            e.e(deviceIdentify);
            e.a(b(e.g(), e.i()));
            d(e);
            ReleaseLogUtil.b("DEVMGR_HwUniteDeviceMgr", "Support Zone content:", e.toString());
        }
    }

    public List<DeviceInfo> c(List<DeviceInfo> list) {
        ArrayList arrayList = new ArrayList(24);
        if (list != null && !list.isEmpty()) {
            for (DeviceInfo deviceInfo : list) {
                if (deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getDeviceConnectState() == 2) {
                    LogUtil.a("HwHandleUniteDeviceMgr", "connected device name :", deviceInfo.getDeviceName());
                    arrayList.add(deviceInfo);
                }
            }
        }
        return arrayList;
    }

    public boolean b(String str) {
        Date c2;
        LogUtil.a("HwHandleUniteDeviceMgr", "isAlreadyUpdated: lastTime", str);
        if (TextUtils.isEmpty(str) || (c2 = jte.c(str)) == null) {
            return false;
        }
        return Math.abs(System.currentTimeMillis() - c2.getTime()) <= 259200000;
    }

    public void b(List<DeviceInfo> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("HwHandleUniteDeviceMgr", "unPairBtDevices input parameter is null.");
            return;
        }
        LogUtil.a("HwHandleUniteDeviceMgr", "unPairBtDevices :", Integer.valueOf(list.size()));
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null && deviceInfo.getDeviceBluetoothType() == 2 && deviceInfo.getDeviceActiveState() == 0 && !bkw.d().sK_(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceInfo.getDeviceIdentify()))) {
                LogUtil.h("HwHandleUniteDeviceMgr", "removeBTDeviceInstance Remove bond device fail.");
            }
        }
    }

    public Map<String, UniteDevice> e(Set set) {
        HashMap hashMap = new HashMap(24);
        if (set == null) {
            LogUtil.h("HwHandleUniteDeviceMgr", "getConnectDeviceList allDeviceList is null.");
            return hashMap;
        }
        for (UniteDevice uniteDevice : e().values()) {
            com.huawei.devicesdk.entity.DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
            if (deviceInfo.isUsing() && deviceInfo.getDeviceConnectState() == 2) {
                set.add(uniteDevice.getIdentify());
                if ("main_relationship".equals(jta.d().e(deviceInfo.getDeviceMac())) || cvt.c(deviceInfo.getDeviceType())) {
                    hashMap.put(uniteDevice.getIdentify(), uniteDevice);
                }
            }
        }
        LogUtil.a("HwHandleUniteDeviceMgr", "getDeviceList deviceMap size is :", Integer.valueOf(hashMap.size()));
        return hashMap;
    }

    public Map<String, UniteDevice> e() {
        HashMap hashMap = new HashMap(24);
        if (this.f == null) {
            snq c2 = snq.c();
            this.f = c2;
            if (c2 == null) {
                LogUtil.a("HwHandleUniteDeviceMgr", "getUdsDeviceList mUniteDeviceService is null.");
                return hashMap;
            }
        }
        for (UniteDevice uniteDevice : this.f.getDeviceList().values()) {
            com.huawei.devicesdk.entity.DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
            if (deviceInfo != null && deviceInfo.getDeviceType() > 0) {
                hashMap.put(uniteDevice.getIdentify(), uniteDevice);
            }
        }
        if (hashMap.size() == 0) {
            LogUtil.h("HwHandleUniteDeviceMgr", "getUdsDeviceList deviceMap size is zero.");
        }
        return hashMap;
    }

    public void c(String str) {
        LogUtil.a("HwHandleUniteDeviceMgr", "setDisconnectState enter.");
        c();
        c(str, false);
        b(blc.c(a(str)));
    }

    public void c(DeviceParameter deviceParameter) {
        LogUtil.a("HwHandleUniteDeviceMgr", "directConnectDevice enter");
        if (deviceParameter == null) {
            LogUtil.h("HwHandleUniteDeviceMgr", "directConnectDevice deviceParameter is null");
            return;
        }
        String mac = deviceParameter.getMac();
        if (mac != null && mac.endsWith("smart_watch")) {
            mac = "AndroidWear";
        }
        com.huawei.devicesdk.entity.DeviceInfo deviceInfo = new com.huawei.devicesdk.entity.DeviceInfo();
        deviceInfo.setDeviceMac(mac);
        deviceInfo.setDeviceName(deviceParameter.getDeviceNameInfo());
        deviceInfo.setDeviceBtType(deviceParameter.getBluetoothType());
        deviceInfo.setDeviceType(deviceParameter.getProductType());
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setIdentify(mac);
        uniteDevice.setDeviceInfo(deviceInfo);
        a(uniteDevice, false, ConnectMode.GENERAL);
    }

    public void a(UniteDevice uniteDevice, boolean z, ConnectMode connectMode) {
        if (uniteDevice == null || uniteDevice.getDeviceInfo() == null) {
            LogUtil.h("HwHandleUniteDeviceMgr", "deviceInfo is null");
            return;
        }
        if (TextUtils.isEmpty(uniteDevice.getDeviceInfo().getDeviceMac())) {
            LogUtil.h("HwHandleUniteDeviceMgr", "deviceMac is empty");
            return;
        }
        List<DeviceInfo> d2 = d();
        String identify = uniteDevice.getIdentify();
        for (DeviceInfo deviceInfo : d2) {
            if (deviceInfo != null && identify.equals(deviceInfo.getDeviceIdentify()) && deviceInfo.getDeviceConnectState() == 2) {
                LogUtil.a("HwHandleUniteDeviceMgr", "connectWearDevice simulatNotifyConnectionStateChanged isReconnect ", Boolean.valueOf(z));
                blz.a("device_is_reconnect", String.valueOf(z));
                jtc.c().b(deviceInfo, 2);
                return;
            }
        }
        if (this.f != null) {
            if (z) {
                LogUtil.a("HwHandleUniteDeviceMgr", "deviceInfo setTriggerType");
                uniteDevice.getDeviceInfo().setTriggerType((byte) 1);
            }
            this.f.connectDevice(uniteDevice, z, connectMode);
            return;
        }
        LogUtil.a("HwHandleUniteDeviceMgr", "connectWearDevice mUniteDeviceService is null");
    }
}
