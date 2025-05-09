package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basichealth.bloodpressure.BloodPressureSyncManager;
import com.huawei.datatype.DataDeviceAvoidDisturbInfo;
import com.huawei.datatype.DataDeviceIntelligentInfo;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jog {
    private static jog d;
    private DataDeviceIntelligentInfo f;
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13982a = new Object();
    private String e = "";
    private IBaseResponseCallback c = new IBaseResponseCallback() { // from class: jog.5
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("DeviceSettingManager", "autoSendCommend response:", obj);
        }
    };
    private BroadcastReceiver j = new BroadcastReceiver() { // from class: jog.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                ReleaseLogUtil.d("DEVMGR_DeviceSettingManager", "mPeriodRriSyncReceiver intent is null");
            } else if ("com.huawei.health.action.PERIOD_RRI_SYNC_FINISH".equals(intent.getAction())) {
                ReleaseLogUtil.e("DEVMGR_DeviceSettingManager", "mPeriodRriSyncReceiver receive sync finish");
                jog.this.f();
                BroadcastManagerUtil.bFK_(BaseApplication.getContext(), jog.this.j);
            }
        }
    };

    private jog() {
    }

    public String e() {
        return this.e;
    }

    public static jog c() {
        jog jogVar;
        synchronized (b) {
            if (d == null) {
                d = new jog();
            }
            jogVar = d;
        }
        return jogVar;
    }

    public void d(int i, int i2, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        List<DeviceInfo> b2 = jpt.b("DeviceSettingManager");
        if (b2 == null || b2.isEmpty()) {
            LogUtil.h("DeviceSettingManager", "setDeviceDateDisplayFormat devices is null or empty");
            return;
        }
        for (DeviceInfo deviceInfo : b2) {
            if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
                DeviceCapability e2 = cvs.e(deviceInfo.getDeviceIdentify());
                boolean z2 = e2 != null && e2.isSupportTimeSetting();
                LogUtil.a("03", 1, "DeviceSettingManager", "setDeviceDateDisplayFormat isCapability:", Boolean.valueOf(z2));
                if (z2) {
                    DeviceCommand deviceCommand = new DeviceCommand();
                    deviceCommand.setServiceID(1);
                    deviceCommand.setCommandID(4);
                    deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
                    ByteBuffer allocate = ByteBuffer.allocate(8);
                    allocate.put((byte) -127);
                    allocate.put((byte) 6);
                    allocate.put((byte) 2);
                    allocate.put((byte) 1);
                    allocate.put(cvx.a(cvx.e(i)));
                    allocate.put((byte) 3);
                    allocate.put((byte) 1);
                    allocate.put(cvx.a(cvx.e(i2)));
                    a(deviceCommand, allocate, 4, iBaseResponseCallback, z);
                }
            }
        }
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        Object[] objArr = new Object[4];
        objArr[0] = "getIntelligentHomeLinkage() dataDeviceIntelligentInfo :";
        objArr[1] = this.f;
        objArr[2] = ", callback :";
        objArr[3] = iBaseResponseCallback == null ? Constants.NULL : "is no null";
        LogUtil.a("DeviceSettingManager", objArr);
        synchronized (f13982a) {
            DataDeviceIntelligentInfo dataDeviceIntelligentInfo = this.f;
            if (dataDeviceIntelligentInfo == null) {
                DeviceCommand deviceCommand = new DeviceCommand();
                deviceCommand.setServiceID(1);
                deviceCommand.setCommandID(34);
                deviceCommand.setPriority(2);
                ByteBuffer allocate = ByteBuffer.allocate(6);
                allocate.put((byte) 1);
                allocate.put((byte) 0);
                allocate.put((byte) 2);
                allocate.put((byte) 0);
                allocate.put((byte) 3);
                allocate.put((byte) 0);
                a(deviceCommand, allocate, 34, iBaseResponseCallback, true);
            } else if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, dataDeviceIntelligentInfo);
            }
        }
    }

    public void a(DataDeviceIntelligentInfo dataDeviceIntelligentInfo) {
        synchronized (f13982a) {
            this.f = dataDeviceIntelligentInfo;
        }
    }

    public void d() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "DeviceSettingManager");
        if (deviceList != null) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (it.hasNext()) {
                d(it.next().getDeviceIdentify());
            }
        }
    }

    public void d(String str) {
        ByteBuffer allocate;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(8);
        deviceCommand.setPriority(2);
        deviceCommand.setmIdentify(str);
        if (cwi.c(jpt.a("DeviceSettingManager"), 112)) {
            allocate = ByteBuffer.allocate(6);
            allocate.put((byte) 1);
            allocate.put((byte) 0);
            allocate.put((byte) 2);
            allocate.put((byte) 3);
            allocate.put((byte) 3);
            allocate.put((byte) 3);
        } else {
            allocate = ByteBuffer.allocate(2);
            allocate.put((byte) 1);
            allocate.put((byte) 0);
        }
        a(deviceCommand, allocate, 8, null, true);
    }

    private void d(List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) && deviceInfo.getDeviceConnectState() == 2) {
                DeviceCapability e2 = cvs.e(deviceInfo.getDeviceIdentify());
                if (e2 != null && e2.getIsSupportAutoUpdate()) {
                    d(deviceInfo.getDeviceIdentify(), deviceInfo.getDeviceUdid());
                } else {
                    LogUtil.h("DeviceSettingManager", "autoSendCommend() not Support AutoUpdateStatus");
                }
            }
        }
    }

    private void d(final String str, String str2) {
        jqi.a().getSwitchSetting("auto_update_status", str2, new IBaseResponseCallback() { // from class: jog.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("DeviceSettingManager", "sendAutoUpdateStatus errorCode :", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    jkv.b().d(str, TextUtils.equals("true", String.valueOf(obj)));
                }
            }
        });
    }

    private void c(List<DeviceInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceSettingManager", "setAutoLightScreen");
        for (DeviceInfo deviceInfo : list) {
            if (!jhb.d(deviceInfo.getDeviceIdentify())) {
                b(deviceInfo, iBaseResponseCallback);
            } else {
                String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10008), "auto_light_screen");
                b(deviceInfo, TextUtils.isEmpty(b2) ? true : Boolean.parseBoolean(b2), iBaseResponseCallback);
            }
        }
    }

    private void b(final DeviceInfo deviceInfo, final IBaseResponseCallback iBaseResponseCallback) {
        jqi.a().getSwitchSetting("auto_light_screen", new IBaseResponseCallback() { // from class: jog.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_DeviceSettingManager", "setAutoLightScreen errorCode :", Integer.valueOf(i), "; objectData :", obj);
                boolean z = true;
                if (i == 0 && (obj instanceof String)) {
                    z = true ^ "0".equals((String) obj);
                }
                jog.this.b(deviceInfo, z, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            return;
        }
        DeviceCapability e2 = cvs.e(deviceInfo.getDeviceIdentify());
        if (e2 != null && e2.isSupportAutoLightScreen()) {
            jqi.a().sendSetSwitchSettingCmd(z, deviceInfo.getDeviceIdentify(), 1, 9, iBaseResponseCallback);
        } else {
            LogUtil.h("DeviceSettingManager", "setAutoLightScreen not Support Auto_light_screen");
        }
    }

    private void e(final List<DeviceInfo> list) {
        if (CommonUtil.bh() && CommonUtil.ch()) {
            LogUtil.h("DeviceSettingManager", "setContactsDataSyncSwitch isUpEmui110");
        } else {
            jqi.a().getSwitchSetting("contacts_data_sync_switch", new IBaseResponseCallback() { // from class: jog.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    boolean z = (i == 0 && (obj instanceof String)) ? !"0".equals(obj) : true;
                    for (DeviceInfo deviceInfo : list) {
                        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
                            LogUtil.h("DeviceSettingManager", "setContactsDataSyncSwitch deviceInfo is null");
                        } else if (!cwi.c(deviceInfo, 23)) {
                            LogUtil.h("DeviceSettingManager", "setContactsDataSyncSwitch capability not Support");
                        } else {
                            jqi.a().sendSetSwitchSettingCmd(1, z ? "1" : "0", deviceInfo.getDeviceIdentify());
                        }
                    }
                }
            });
        }
    }

    public void a(boolean z, IBaseResponseCallback iBaseResponseCallback) {
        jou.c(2, iBaseResponseCallback);
        LogUtil.a("04", 1, "DeviceSettingManager", "setWearMessagePushSwitchStatus:", Boolean.valueOf(z));
        jqi.a().sendSetSwitchSettingCmd(!z, "", 2, 8, null);
        e(z);
    }

    private void e(boolean z) {
        synchronized (this) {
            LogUtil.a("DeviceSettingManager", "setWearMessagePushToSharedPreference() :", Boolean.valueOf(z));
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (TextUtils.isEmpty(accountInfo)) {
                accountInfo = "default_id";
            }
            jfq.c().setSharedPreference(accountInfo + "wear_push_message_key", String.valueOf(!z ? 1 : 0), null);
        }
    }

    private void d(final List<DeviceInfo> list, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceSettingManager", "setRotateSwitchScreen");
        jqi.a().getSwitchSetting("rotate_switch_screen", new IBaseResponseCallback() { // from class: jog.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                boolean z = (i == 0 && (obj instanceof String)) ? !"0".equals((String) obj) : false;
                for (DeviceInfo deviceInfo : list) {
                    if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
                        DeviceCapability e2 = cvs.e(deviceInfo.getDeviceIdentify());
                        if (e2 != null && e2.isSupportRotateSwitchScreen()) {
                            jqi.a().sendSetSwitchSettingCmd(z, deviceInfo.getDeviceIdentify(), 1, 27, iBaseResponseCallback);
                        } else {
                            LogUtil.b("DeviceSettingManager", "autoSendCommend() not Support Rotate_switch_screen");
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        jqi.a().getSwitchSetting("press_auto_monitor_switch_status", new IBaseResponseCallback() { // from class: jog.9
            /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void d(int r5, java.lang.Object r6) {
                /*
                    r4 = this;
                    r0 = 1
                    if (r5 != 0) goto L1a
                    boolean r1 = r6 instanceof java.lang.String
                    if (r1 == 0) goto L1a
                    java.lang.String r6 = (java.lang.String) r6
                    boolean r1 = android.text.TextUtils.isEmpty(r6)
                    if (r1 != 0) goto L1a
                    java.lang.String r1 = "true"
                    boolean r6 = r1.equals(r6)
                    if (r6 == 0) goto L1a
                    r6 = r0
                    goto L1b
                L1a:
                    r6 = 0
                L1b:
                    java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                    java.lang.String r1 = " isEnable: "
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r6)
                    java.lang.String r3 = "sendWearPressMonitorSwitchStatus errorCode: "
                    java.lang.Object[] r5 = new java.lang.Object[]{r3, r5, r1, r2}
                    java.lang.String r1 = "DEVMGR_DeviceSettingManager"
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r5)
                    if (r6 == 0) goto L34
                    goto L35
                L34:
                    r0 = 2
                L35:
                    jld r5 = defpackage.jld.b()
                    r5.a(r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.jog.AnonymousClass9.d(int, java.lang.Object):void");
            }
        });
    }

    public void d(final DeviceInfo deviceInfo) {
        jqi a2 = jqi.a();
        if (BloodPressureSyncManager.e()) {
            a2.getSwitchSetting("blood_pressure_remind", new IBaseResponseCallback() { // from class: jok
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    jog.b(i, obj);
                }
            });
        }
        if (cwi.c(deviceInfo, 72)) {
            a2.getSwitchSetting("highland.blood.oxygen.switch", new IBaseResponseCallback() { // from class: joo
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    jog.b(DeviceInfo.this, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void b(int i, Object obj) {
        LogUtil.a("DeviceSettingManager", "syncSwitchStatus bloodPressure errorCode: ", Integer.valueOf(i));
        if (i == 0 && (obj instanceof String)) {
            String str = (String) obj;
            LogUtil.a("DeviceSettingManager", "send BloodPressure switch status command: ", str);
            BloodPressureSyncManager.c().b(CommonUtil.h(str));
        }
    }

    static /* synthetic */ void b(DeviceInfo deviceInfo, int i, Object obj) {
        LogUtil.a("DeviceSettingManager", "syncSwitchStatus highLand errorCode: ", Integer.valueOf(i));
        if (i == 0 && (obj instanceof String)) {
            String str = (String) obj;
            ReleaseLogUtil.e("DEVMGR_DeviceSettingManager", "send highLand switch status command: ", str);
            jft.a().c(deviceInfo, CommonUtil.h(str));
        }
    }

    class e implements IBaseResponseCallback {
        private IBaseResponseCallback b;
        private List<DataDeviceAvoidDisturbInfo> c;
        private boolean d;
        private String e;

        e(String str, List<DataDeviceAvoidDisturbInfo> list, IBaseResponseCallback iBaseResponseCallback, boolean z) {
            this.e = str;
            this.c = list;
            this.b = iBaseResponseCallback;
            this.d = z;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (!(obj instanceof List)) {
                LogUtil.b("DeviceSettingManager", "AvoidDisturbCallback objectData is null");
                return;
            }
            List list = (List) obj;
            if (list.size() != 0) {
                ArrayList arrayList = new ArrayList(5);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(Integer.valueOf(((DataDeviceAvoidDisturbInfo) it.next()).getDeviceAvoidDisturbIndex()));
                }
                jog jogVar = jog.this;
                jogVar.b(this.e, arrayList, jogVar.c);
            }
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(1);
            deviceCommand.setCommandID(12);
            deviceCommand.setmIdentify(this.e);
            ByteBuffer allocate = ByteBuffer.allocate(22);
            DeviceCapability e = cvs.e(this.e);
            if (e != null && e.isSupportQueryAllowDisturbContent()) {
                allocate = ByteBuffer.allocate(26);
            }
            Iterator<DataDeviceAvoidDisturbInfo> it2 = this.c.iterator();
            while (it2.hasNext()) {
                jog.this.b(allocate, it2.next(), e);
                jog.this.a(deviceCommand, allocate, 12, this.b, this.d);
                LogUtil.c("DeviceSettingManager", "addDeviceAvoidDisturbingInfo mIsNeedRespond:", Boolean.valueOf(this.d), " size:", Integer.valueOf(list.size()));
                if (!this.d) {
                    if (list.size() == 0) {
                        jog.this.a(this.c, this.b);
                    } else {
                        jog.this.e(this.c, this.b);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ByteBuffer byteBuffer, DataDeviceAvoidDisturbInfo dataDeviceAvoidDisturbInfo, DeviceCapability deviceCapability) {
        LogUtil.a("03", 1, "DeviceSettingManager", "addDeviceAvoidDisturbingInfo:", dataDeviceAvoidDisturbInfo);
        byteBuffer.put((byte) -127);
        if (deviceCapability != null && deviceCapability.isSupportQueryAllowDisturbContent()) {
            byteBuffer.put((byte) 24);
        } else {
            byteBuffer.put((byte) 20);
        }
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 1);
        byteBuffer.put(cvx.a(cvx.e(dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbIndex())));
        byteBuffer.put((byte) 3);
        byteBuffer.put((byte) 1);
        if (dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbTimeSwitch() == 1 || dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbSwitch() == 1) {
            byteBuffer.put((byte) 1);
        } else {
            byteBuffer.put((byte) 0);
        }
        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 0);
        a(byteBuffer, dataDeviceAvoidDisturbInfo, deviceCapability);
    }

    private void a(ByteBuffer byteBuffer, DataDeviceAvoidDisturbInfo dataDeviceAvoidDisturbInfo, DeviceCapability deviceCapability) {
        byte[] a2;
        byte[] a3;
        byteBuffer.put((byte) 5);
        byteBuffer.put((byte) 2);
        if (dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbSwitch() == 1) {
            a2 = cvx.a(cvx.a(cvx.j(0)));
        } else {
            a2 = cvx.a(cvx.a(cvx.j(dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbStartTime())));
        }
        byteBuffer.put(a2);
        byteBuffer.put((byte) 6);
        byteBuffer.put((byte) 2);
        if (dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbSwitch() == 1) {
            a3 = cvx.a(cvx.a(cvx.j(2359)));
        } else {
            a3 = cvx.a(cvx.a(cvx.j(dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbEndTime())));
        }
        byteBuffer.put(a3);
        byteBuffer.put((byte) 7);
        byteBuffer.put((byte) 1);
        byteBuffer.put(cvx.a(cvx.e(dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbCycle())));
        if (deviceCapability == null || !deviceCapability.isSupportQueryAllowDisturbContent()) {
            return;
        }
        byteBuffer.put((byte) 8);
        byteBuffer.put((byte) 2);
        byteBuffer.put(cvx.a(cvx.a(dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbType())));
    }

    public void a(String str, List<DataDeviceAvoidDisturbInfo> list, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        LogUtil.a("DEVMGR_SETTING", "DeviceSettingManager", "addDeviceAvoidDisturbingInfo()");
        d(new e(str, list, iBaseResponseCallback, z));
    }

    public void d(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("DeviceSettingManager", "handleGetAvoidDisturbMessage callback is null");
        } else {
            jqi.a().getSwitchSetting("custom.avoid_disturb", new IBaseResponseCallback() { // from class: jog.6
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r4v15, types: [java.util.List] */
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    ArrayList arrayList = new ArrayList(16);
                    if (i == 0 && (obj instanceof String)) {
                        String str = (String) obj;
                        ReleaseLogUtil.e("DEVMGR_DeviceSettingManager", "handleGetAvoidDisturbMessage value :", str);
                        try {
                            arrayList = (List) new Gson().fromJson(str, new TypeToken<List<DataDeviceAvoidDisturbInfo>>() { // from class: jog.6.5
                            }.getType());
                        } catch (JsonSyntaxException unused) {
                            LogUtil.b("DeviceSettingManager", "handleGetAvoidDisturbMessage JsonSyntaxException");
                        } catch (NullPointerException unused2) {
                            LogUtil.b("DeviceSettingManager", "handleGetAvoidDisturbMessage NullPointerException");
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        iBaseResponseCallback.d(0, arrayList);
                    } else {
                        LogUtil.h("DeviceSettingManager", "handleGetAvoidDisturbMessage is null");
                        iBaseResponseCallback.d(100001, new ArrayList(10));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<DataDeviceAvoidDisturbInfo> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (list == null || iBaseResponseCallback == null) {
            LogUtil.h("DeviceSettingManager", "handleUpdateAvoidDisturbMessage List and responseCallback is null");
            return;
        }
        LogUtil.a("DeviceSettingManager", "handleUpdateAvoidDisturbMessage dataDeviceAvoidDisturbList: ", Boolean.valueOf(list.isEmpty()));
        if (list.isEmpty()) {
            return;
        }
        jqi.a().setSwitchSetting("custom.avoid_disturb", new Gson().toJson(list), new IBaseResponseCallback() { // from class: jog.13
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("DeviceSettingManager", "handleInsertAvoidDisturbMessage errorCode :", Integer.valueOf(i));
                if (i == -1) {
                    iBaseResponseCallback.d(-1, 101001);
                } else {
                    iBaseResponseCallback.d(0, 100000);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<DataDeviceAvoidDisturbInfo> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null || list == null) {
            LogUtil.h("DeviceSettingManager", "handleInsertAvoidDisturbMessage List and baseResponseCallback is null");
            return;
        }
        LogUtil.a("DeviceSettingManager", "handleInsertAvoidDisturbMessage mHwSwitchSettingManager: ", " dataDeviceAvoidDisturbList: ", Boolean.valueOf(list.isEmpty()));
        if (list.isEmpty()) {
            return;
        }
        jqi.a().setSwitchSetting("custom.avoid_disturb", new Gson().toJson(list), new IBaseResponseCallback() { // from class: jog.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("DeviceSettingManager", "handleInsertAvoidDisturbMessage errorCode :", Integer.valueOf(i));
                if (i == -1) {
                    iBaseResponseCallback.d(-1, 101001);
                } else {
                    iBaseResponseCallback.d(0, 100000);
                }
            }
        });
    }

    private void b(String str) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(29);
        deviceCommand.setmIdentify(str);
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        a(deviceCommand, allocate, 29, null, true);
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(35);
        ByteBuffer allocate = ByteBuffer.allocate(3);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        a(deviceCommand, allocate, 35, iBaseResponseCallback, true);
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(36);
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        a(deviceCommand, allocate, 36, iBaseResponseCallback, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, List<Integer> list, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("03", 1, "DeviceSettingManager", "deleteDeviceAvoidDisturbingInfo");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(11);
        deviceCommand.setmIdentify(str);
        ByteBuffer allocate = ByteBuffer.allocate(list.size() + 4);
        allocate.put((byte) -127);
        allocate.put((byte) (list.size() + 2));
        allocate.put((byte) 2);
        allocate.put((byte) list.size());
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            allocate.put(cvx.a(cvx.e(it.next().intValue())));
        }
        a(deviceCommand, allocate, 11, iBaseResponseCallback, true);
    }

    public void e(String str, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DEVMGR_SETTING", "DeviceSettingManager", " factoryReset status", Boolean.valueOf(z));
        this.e = str;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(13);
        deviceCommand.setmIdentify(str);
        ByteBuffer allocate = ByteBuffer.allocate(3);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        if (c(str) && z) {
            allocate.put((byte) 2);
            LogUtil.a("DeviceSettingManager", "Enter Command delivery 0x02");
        } else {
            allocate.put((byte) 1);
            LogUtil.a("DeviceSettingManager", "Enter Command delivery 0x01");
        }
        a(deviceCommand, allocate, 13, iBaseResponseCallback, true);
    }

    private boolean c(String str) {
        DeviceInfo e2;
        Map<String, DeviceCapability> a2;
        DeviceCapability e3 = cvs.e(str);
        if (e3 == null && (e2 = jpt.e(str, "DeviceSettingManager")) != null && e2.getDeviceConnectState() == 2 && (a2 = jfq.c().a(3, "", "DeviceSettingManager")) != null && a2.get(str) != null) {
            e3 = a2.get(str);
        }
        if (e3 == null || !e3.isSupportEsim()) {
            return false;
        }
        LogUtil.a("DeviceSettingManager", "device support esim");
        return true;
    }

    public void a(DeviceCommand deviceCommand, ByteBuffer byteBuffer, int i, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        if (iBaseResponseCallback != null && z) {
            jfh.b(i, iBaseResponseCallback);
        }
        deviceCommand.setDataLen(byteBuffer.array().length);
        deviceCommand.setDataContent(byteBuffer.array());
        jfq.c().b(deviceCommand);
    }

    public void b(DeviceInfo deviceInfo) {
        if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            DeviceCapability e2 = cvs.e(deviceInfo.getDeviceIdentify());
            if (e2 != null && e2.isSupportLeftRightHandWearMode()) {
                LogUtil.a("DeviceSettingManager", "autoSendCommend() isSupportLeftRightHandWearMode");
                i();
            } else {
                LogUtil.b("DeviceSettingManager", "autoSendCommend() not isSupportLeftRightHandWearMode");
            }
            if (e2 != null && e2.isSupportWearMessagePush() && e2.isAvoidDisturb()) {
                a(b(), (IBaseResponseCallback) null);
            }
            a(e2, deviceInfo);
            e(deviceInfo);
            a(deviceInfo);
        }
        d(jpv.d(), jpv.e(), this.c, true);
        a();
        if (deviceInfo != null) {
            d(deviceInfo.getDeviceIdentify());
        } else {
            d();
        }
    }

    private void e(DeviceInfo deviceInfo) {
        if (!jlj.a().e(deviceInfo)) {
            ReleaseLogUtil.d("DEVMGR_DeviceSettingManager", "setSleepBreatheSwitch deviceCapability isSupportSleepBreathe false");
        } else {
            jqi.a().getSwitchSetting("sleep_breathe_key", new IBaseResponseCallback() { // from class: jon
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    jog.d(i, obj);
                }
            });
        }
    }

    static /* synthetic */ void d(int i, Object obj) {
        LogUtil.a("DeviceSettingManager", "changeSleepBreatheSwitch errorCode: ", Integer.valueOf(i));
        if (i == 0 && (obj instanceof String)) {
            String str = (String) obj;
            if ("1".equals(obj)) {
                str = "true";
            }
            ReleaseLogUtil.e("DEVMGR_DeviceSettingManager", "setSleepBreatheSwitch sleepBreatheSwitch:", str);
            jlj.a().d(str);
            return;
        }
        ReleaseLogUtil.d("DEVMGR_DeviceSettingManager", "setSleepBreatheSwitch getSwitchSetting errorCode:", Integer.valueOf(i));
    }

    private void a(DeviceCapability deviceCapability, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("DEVMGR_DeviceSettingManager", "enter startSendPressSwitch");
        if (deviceCapability == null || deviceInfo == null) {
            ReleaseLogUtil.d("DEVMGR_DeviceSettingManager", "startSendPressSwitch deviceCapability is null or deviceInfo is null");
            return;
        }
        boolean c = cwi.c(deviceInfo, 206);
        boolean b2 = dkx.b();
        if (!deviceCapability.isSupportPressAutoMonitor() && !c) {
            ReleaseLogUtil.d("DEVMGR_DeviceSettingManager", "isSupportPressAutoMonitor and isSupportEmotionAutoMonitor is false");
            return;
        }
        if (c && !b2) {
            c(deviceCapability);
            return;
        }
        if (nhu.g().isPeriodRriSyncing()) {
            ReleaseLogUtil.d("DEVMGR_DeviceSettingManager", "SwitchTimerTask isPeriodRriSyncing.");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.health.action.PERIOD_RRI_SYNC_FINISH");
            BroadcastManagerUtil.bFE_(BaseApplication.getContext(), this.j, intentFilter);
            return;
        }
        f();
    }

    private void c(final DeviceCapability deviceCapability) {
        jps.e(new IBaseResponseCallback() { // from class: jog.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && (obj instanceof String)) {
                    String str = (String) obj;
                    ReleaseLogUtil.d("DEVMGR_DeviceSettingManager", "sendWearEmotionMonitorSwitchStatus switchStatus :", str);
                    jps.b(str);
                    if ("1".equals(str) && deviceCapability.isSupportPressAutoMonitor()) {
                        jog.this.f();
                    }
                }
            }
        });
    }

    public boolean b() {
        int j = j();
        LogUtil.a("DeviceSettingManager", "getWearMessagePushSwitchStatus() flag:", Integer.valueOf(j));
        return j == 0;
    }

    private int j() {
        synchronized (this) {
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (TextUtils.isEmpty(accountInfo)) {
                accountInfo = "default_id";
            }
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1), accountInfo + "wear_push_message_key");
            int i = 0;
            if (TextUtils.isEmpty(b2)) {
                return 0;
            }
            try {
                i = Integer.parseInt(b2);
            } catch (NumberFormatException unused) {
                LogUtil.b("DeviceSettingManager", "getWearMessagePushFromSharePreference NumberFormatException");
            }
            return i;
        }
    }

    private void i() {
        jqi.a().getSwitchSetting("left_or_right_hand_wear_status", new IBaseResponseCallback() { // from class: jop
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                jog.this.a(i, obj);
            }
        });
    }

    /* synthetic */ void a(int i, Object obj) {
        LogUtil.a("DeviceSettingManager", "sendLeftRightHandCmd errorCode = ", Integer.valueOf(i), " ; objectData = ", obj);
        if (i == 0 && (obj instanceof String)) {
            String str = (String) obj;
            jqi.a().sendSetSwitchSettingCmd("1".equals(str), "", 1, 26, this.c);
            if (jpt.a("DeviceSettingManager") != null) {
                jqi.a().setSwitchSetting("left_or_right_hand_wear_status", str, null);
            } else {
                LogUtil.h("DeviceSettingManager", "autoSendCommend() deviceInfo is null");
            }
        }
    }

    private void a() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "DeviceSettingManager");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.b("DeviceSettingManager", "autoSendCommandPartThree() deviceInfoList is null");
            return;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
                e(deviceInfo.getDeviceIdentify());
            }
        }
        d(deviceList, this.c);
        c(deviceList, this.c);
        b(deviceList);
        e(deviceList);
        d(deviceList);
    }

    private void e(String str) {
        LogUtil.a("DeviceSettingManager", "Enter requestDeviceAllowDisturbContent 5!");
        DeviceCapability e2 = cvs.e(str);
        if (e2 == null || !e2.isSupportQueryAllowDisturbContent()) {
            LogUtil.a("DeviceSettingManager", "Leave requestDeviceAllowDisturbContent no support return!");
        } else {
            b(str);
            LogUtil.a("DeviceSettingManager", "Leave requestDeviceAllowDisturbContent !");
        }
    }

    private void b(final List<DeviceInfo> list) {
        d(new IBaseResponseCallback() { // from class: jol
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                jog.this.b(list, i, obj);
            }
        });
    }

    /* synthetic */ void b(List list, int i, Object obj) {
        if (!(obj instanceof List)) {
            LogUtil.b("DeviceSettingManager", "sendAvoidDisturbCommand() objData is null");
            return;
        }
        List<DataDeviceAvoidDisturbInfo> list2 = (List) obj;
        if (list2.isEmpty()) {
            list2.add(new DataDeviceAvoidDisturbInfo());
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DeviceInfo deviceInfo = (DeviceInfo) it.next();
            if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
                LogUtil.b("DeviceSettingManager", "sendAvoidDisturbCommand() info is null");
            } else {
                DeviceCapability e2 = cvs.e(deviceInfo.getDeviceIdentify());
                if (e2 == null || !e2.isAvoidDisturb()) {
                    LogUtil.b("DeviceSettingManager", "sendAvoidDisturbCommand() not Support Avoid_disturb");
                } else {
                    a(deviceInfo.getDeviceIdentify(), list2, this.c, true);
                }
            }
        }
    }

    private void a(DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, 114);
        LogUtil.a("DeviceSettingManager", "setMenstrualPredictSwitch isSupportMenstrualPredict = ", Boolean.valueOf(c));
        if (c) {
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10100), "PHYSIOLOGICAL_SHOW_STATE_KEY");
            LogUtil.a("DeviceSettingManager", "setMenstrualPredictSwitch showState = ", b2);
            if ("false".equals(b2)) {
                return;
            }
            jqi.a().getSwitchSetting("menstrual_predict_switch", new IBaseResponseCallback() { // from class: jom
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    jog.e(i, obj);
                }
            });
        }
    }

    static /* synthetic */ void e(int i, Object obj) {
        LogUtil.a("DeviceSettingManager", "setMenstrualPredictSwitch errorCode = ", Integer.valueOf(i));
        String str = (i == 0 && (obj instanceof String)) ? (String) obj : "0";
        LogUtil.a("DeviceSettingManager", "setMenstrualPredictSwitch switchStatus = ", str);
        jqi.a().sendSettingSwitchCommand("hw.unitedevice.physiological", "hw.watch.health.physiological", 900300012L, str);
    }
}
