package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jiu extends HwBaseManager implements BluetoothDataReceiveCallback {
    private static jiu d;
    private BroadcastReceiver c;
    private Context f;
    private IBaseResponseCallback i;
    private jfq j;
    private static final byte[] b = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private static List<IBaseResponseCallback> f13880a = new ArrayList(10);
    private static List<IBaseResponseCallback> e = new ArrayList(10);

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public boolean onDataMigrate() {
        return true;
    }

    private jiu(Context context) {
        super(context);
        this.i = new IBaseResponseCallback() { // from class: jiu.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HwLinkLossAlarmManager", "autoSendCommend response: ", obj);
            }
        };
        this.c = new BroadcastReceiver() { // from class: jiu.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Parcelable parcelable;
                if (intent == null) {
                    LogUtil.h("HwLinkLossAlarmManager", "connectStateChangedReceiver intent is null");
                    return;
                }
                LogUtil.a("HwLinkLossAlarmManager", "connectStateChangedReceiver action = ", intent.getAction());
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    try {
                        parcelable = intent.getParcelableExtra("deviceinfo");
                    } catch (BadParcelableException unused) {
                        LogUtil.b("HwLinkLossAlarmManager", "fuzzy test exception, no care this.");
                        parcelable = null;
                    }
                    if (!(parcelable instanceof DeviceInfo)) {
                        LogUtil.a("HwLinkLossAlarmManager", "device info is wrong, please check.");
                        return;
                    }
                    DeviceInfo deviceInfo = (DeviceInfo) parcelable;
                    if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2) {
                        jiu.this.a();
                    } else {
                        LogUtil.h("HwLinkLossAlarmManager", "connectStateChangedReceiver deviceInfo is null or connection status is broken");
                    }
                }
            }
        };
        this.f = context;
        jfq c = jfq.c();
        this.j = c;
        if (c != null) {
            BroadcastManagerUtil.bFC_(context, this.c, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
            this.j.e(11, this);
            a();
            return;
        }
        LogUtil.b("HwLinkLossAlarmManager", "HwLinkLossAlarmManager() hwDeviceConfigManager is null");
    }

    public static jiu c() {
        jiu jiuVar;
        synchronized (b) {
            if (d == null) {
                d = new jiu(BaseApplication.getContext());
            }
            jiuVar = d;
        }
        return jiuVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 11;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        try {
            e();
            this.f.unregisterReceiver(this.c);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HwLinkLossAlarmManager", "connectStateChangedReceiver is not registered");
        }
        LogUtil.a("HwLinkLossAlarmManager", "onDestroy()");
    }

    private static void e() {
        synchronized (b) {
            d = null;
        }
        synchronized (g()) {
            e.clear();
        }
        synchronized (b()) {
            f13880a.clear();
        }
    }

    public void d(String str, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwLinkLossAlarmManager", "setBTLostRemindEnable: ", Boolean.valueOf(z));
        c(str, z, iBaseResponseCallback);
        c(str, z);
    }

    public void c(String str, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwLinkLossAlarmManager", "sendBtLostRemindEnable: ", Boolean.valueOf(z));
        jqi.a().sendSetSwitchSettingCmd(z, str, 11, 3, null);
        synchronized (g()) {
            e.add(iBaseResponseCallback);
        }
    }

    private void c(String str, boolean z) {
        LogUtil.a("HwLinkLossAlarmManager", "saveBtLostRemindEnableToSharedPreference() : isChecked = ", Boolean.valueOf(z));
        DeviceInfo e2 = jpt.e(str, "HwLinkLossAlarmManager");
        if (e2 != null) {
            boolean b2 = b(e2, "bt_lost_remind", z);
            if (!b2) {
                jqi.a().setSwitchSetting("bt_lost_remind", z ? "1" : "0", null);
                return;
            } else {
                LogUtil.a("HwLinkLossAlarmManager", "saveBtLostRemindEnableToSharedPreference() : isFamilyMode = ", Boolean.valueOf(b2));
                return;
            }
        }
        LogUtil.b("HwLinkLossAlarmManager", "saveBtLostRemindEnableToSharedPreference() : deviceInfo is null");
    }

    private boolean b(DeviceInfo deviceInfo, String str, boolean z) {
        if (!jhb.d(deviceInfo.getDeviceIdentify())) {
            return false;
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), str, Boolean.toString(z), (StorageParams) null);
        return true;
    }

    private void b(byte[] bArr) {
        int i;
        LogUtil.a("HwLinkLossAlarmManager", "getResult(): ", cvx.d(bArr));
        if (bArr.length < 2) {
            LogUtil.b("HwLinkLossAlarmManager", "getResult(): data error");
            return;
        }
        byte b2 = bArr[1];
        if (b2 == 2) {
            int c = c(bArr);
            i = c == 100000 ? 0 : -1;
            synchronized (b()) {
                if (f13880a.size() != 0) {
                    f13880a.get(0).d(i, Integer.valueOf(c));
                    f13880a.remove(0);
                }
            }
            return;
        }
        if (b2 == 3) {
            int c2 = c(bArr);
            i = c2 == 100000 ? 0 : -1;
            synchronized (g()) {
                if (e.size() != 0) {
                    e.get(0).d(i, Integer.valueOf(c2));
                    e.remove(0);
                }
            }
            return;
        }
        LogUtil.h("HwLinkLossAlarmManager", "Command does not match ");
    }

    private int c(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2)) {
            LogUtil.h("HwLinkLossAlarmManager", "messageHex is empty.");
            return -1;
        }
        if (d2.length() <= 8) {
            return -1;
        }
        return CommonUtil.w(d2.substring(8, d2.length()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("HwLinkLossAlarmManager", "autoSendCommend() enter.");
        ThreadPoolManager.d().execute(new Runnable() { // from class: jit
            @Override // java.lang.Runnable
            public final void run() {
                jiu.this.d();
            }
        });
    }

    /* synthetic */ void d() {
        List<DeviceInfo> b2 = jpt.b("HwLinkLossAlarmManager");
        if (b2 == null) {
            LogUtil.h("HwLinkLossAlarmManager", "autoSendCommend devices is null");
            return;
        }
        for (final DeviceInfo deviceInfo : b2) {
            if (!c(deviceInfo, "bt_lost_remind")) {
                jqi.a().getSwitchSetting("bt_lost_remind", new IBaseResponseCallback() { // from class: jir
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        jiu.this.c(deviceInfo, i, obj);
                    }
                });
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002a, code lost:
    
        if (r4 == 1) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    /* synthetic */ void c(com.huawei.health.devicemgr.business.entity.DeviceInfo r3, int r4, java.lang.Object r5) {
        /*
            r2 = this;
            boolean r0 = defpackage.jpv.a()
            java.lang.String r1 = "HwLinkLossAlarmManager"
            if (r0 != 0) goto L12
            java.lang.String r3 = "no login return."
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r3)
            return
        L12:
            java.lang.String r0 = "login success."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            if (r4 != 0) goto L37
            boolean r4 = r5 instanceof java.lang.String
            if (r4 == 0) goto L2d
            java.lang.String r5 = (java.lang.String) r5
            android.content.Context r4 = r2.f
            int r4 = health.compact.a.CommonUtil.m(r4, r5)
            r5 = 1
            if (r4 != r5) goto L37
            goto L38
        L2d:
            java.lang.String r3 = "autoSendCommend info is null"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r3)
            return
        L37:
            r5 = 0
        L38:
            r2.a(r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jiu.c(com.huawei.health.devicemgr.business.entity.DeviceInfo, int, java.lang.Object):void");
    }

    private boolean c(DeviceInfo deviceInfo, String str) {
        if (!jhb.d(deviceInfo.getDeviceIdentify())) {
            return false;
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10008), str);
        a(deviceInfo, TextUtils.isEmpty(b2) ? false : Boolean.parseBoolean(b2));
        return true;
    }

    private void a(DeviceInfo deviceInfo, boolean z) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.h("HwLinkLossAlarmManager", "autoSendCommend DeviceInfo error");
            return;
        }
        DeviceCapability e2 = cvs.e(deviceInfo.getDeviceIdentify());
        if (e2 == null || !e2.isBluetoothOffAlert()) {
            return;
        }
        LogUtil.a("HwLinkLossAlarmManager", "autoSendCommend() flag = ", Boolean.valueOf(z));
        c(deviceInfo.getDeviceIdentify(), z, this.i);
    }

    private static Object b() {
        List<IBaseResponseCallback> list;
        synchronized (jiu.class) {
            list = f13880a;
        }
        return list;
    }

    private static Object g() {
        List<IBaseResponseCallback> list;
        synchronized (jiu.class) {
            list = e;
        }
        return list;
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr != null) {
            b(bArr);
        }
    }
}
