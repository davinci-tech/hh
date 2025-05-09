package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class jya {

    /* renamed from: a, reason: collision with root package name */
    private static jya f14182a;
    private static final Object b = new Object();
    private Context e;
    private BroadcastReceiver d = new BroadcastReceiver() { // from class: jya.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HwCalendarSyncMgr", "mDeviceStatusReceiver onReceive intent is null.");
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                LogUtil.h("HwCalendarSyncMgr", "mDeviceStatusReceiver onReceive action is null");
                return;
            }
            LogUtil.a("HwCalendarSyncMgr", "mDeviceListChangedReceiver onReceive action :", action);
            if ("com.huawei.bone.action.DEVICE_LIST_CHANGED".equals(action)) {
                jya.this.a();
            }
        }
    };
    private BroadcastReceiver c = new BroadcastReceiver() { // from class: jya.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceInfo deviceInfo;
            if (context == null || intent == null || !"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || (deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo")) == null) {
                return;
            }
            if (deviceInfo.getRelationship() == null || !"followed_relationship".equals(deviceInfo.getRelationship())) {
                int deviceConnectState = deviceInfo.getDeviceConnectState();
                if (deviceConnectState == 2) {
                    jya.this.a(deviceInfo);
                } else {
                    if (deviceConnectState != 3) {
                        return;
                    }
                    jya.this.j(deviceInfo);
                }
            }
        }
    };
    private BroadcastReceiver i = new BroadcastReceiver() { // from class: jya.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null || intent.getAction() == null) {
                return;
            }
            if ("android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction()) || "android.intent.action.TIME_SET".equals(intent.getAction()) || "android.intent.action.DATE_CHANGED".equals(intent.getAction())) {
                LogUtil.a("HwCalendarSyncMgr", "ACTION: ", intent.getAction());
                jya.this.e(jyo.e());
            } else {
                LogUtil.a("HwCalendarSyncMgr", "mTimeChangedReceiver action default");
            }
        }
    };

    public static jya d() {
        jya jyaVar;
        synchronized (b) {
            if (f14182a == null) {
                f14182a = new jya();
            }
            jyaVar = f14182a;
        }
        return jyaVar;
    }

    private static void g() {
        synchronized (b) {
            f14182a = null;
        }
    }

    private jya() {
        LogUtil.a("HwCalendarSyncMgr", "constructor method");
        i();
    }

    private void i() {
        this.e = BaseApplication.getContext();
        h();
    }

    private void h() {
        BroadcastManagerUtil.bFC_(this.e, this.d, new IntentFilter("com.huawei.bone.action.DEVICE_LIST_CHANGED"), LocalBroadcast.c, null);
        BroadcastManagerUtil.bFC_(this.e, this.c, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.DATE_CHANGED");
        BroadcastManagerUtil.bFB_(this.e, this.i, intentFilter);
    }

    public void a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwCalendarSyncMgr", "onDeviceConnected: deviceInfo is null");
        } else {
            e(deviceInfo);
        }
    }

    public void j(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwCalendarSyncMgr", "onDeviceDisconnected: deviceInfo is null");
            return;
        }
        if (jyo.a() && CommonUtil.bh() && deviceInfo.getSingleFrameDevice() == 0) {
            jye.e(BaseApplication.getContext());
            LogUtil.h("HwCalendarSyncMgr", "onDeviceDisconnected: do not support calendar synchronization.");
        } else if (deviceInfo.getSingleFrameDevice() == 1 && CommonUtil.bv()) {
            LogUtil.a("HwCalendarSyncMgr", "The current device has a single framework and is release version.");
        } else {
            c(deviceInfo);
        }
    }

    public void e(final DeviceInfo deviceInfo) {
        LogUtil.a("HwCalendarSyncMgr", "handleDeviceConnected: ");
        if (deviceInfo != null) {
            LogUtil.a("HwCalendarSyncMgr", "handleDeviceConnected getSingleFrameDevice: ", Integer.valueOf(deviceInfo.getSingleFrameDevice()));
            if (jyo.a() && CommonUtil.bh() && deviceInfo.getSingleFrameDevice() == 0) {
                jye.e(BaseApplication.getContext());
                LogUtil.h("HwCalendarSyncMgr", "onDeviceDisconnected: do not support calendar synchronization.");
                return;
            } else if (deviceInfo.getSingleFrameDevice() == 1 && CommonUtil.bv()) {
                LogUtil.a("HwCalendarSyncMgr", "handleDeviceConnected The current device has a single framework and is release version.");
                return;
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: jyg
                    @Override // java.lang.Runnable
                    public final void run() {
                        jya.this.b(deviceInfo);
                    }
                });
                return;
            }
        }
        LogUtil.h("HwCalendarSyncMgr", "handleDeviceConnected: deviceInfo is null.");
    }

    /* synthetic */ void b(DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, 171);
        boolean c2 = cwi.c(deviceInfo, 184);
        if (!c && !c2) {
            LogUtil.h("HwCalendarSyncMgr", "not support CalendarSync");
        } else {
            jyc.b().a(a.t, 1);
            b();
        }
    }

    public void c(final DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwCalendarSyncMgr", "handleDeviceDisconnected: deviceInfo is null.");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jyb
                @Override // java.lang.Runnable
                public final void run() {
                    jya.d(DeviceInfo.this);
                }
            });
        }
    }

    static /* synthetic */ void d(DeviceInfo deviceInfo) {
        if (!jyo.d()) {
            LogUtil.h("HwCalendarSyncMgr", "handleDeviceState: have no contacts permissions");
            jyc.b().a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 6);
        } else if (!cwi.c(deviceInfo, 171) && !cwi.c(deviceInfo, 184)) {
            LogUtil.h("HwCalendarSyncMgr", "handleDeviceState: is not supported sync calendar capability.");
        } else if (deviceInfo.getDeviceConnectState() == 3) {
            jyk.b().c();
        }
    }

    private void b() {
        LogUtil.a("HwCalendarSyncMgr", "handleReconnectedSync: ");
        jyc.b().a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1);
    }

    public void a() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: jya.2
            @Override // java.lang.Runnable
            public void run() {
                jya.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Context context = BaseApplication.getContext();
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwCalendarSyncMgr");
        a(e(deviceList), jyj.e(context).d());
    }

    private List<String> e(List<DeviceInfo> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null) {
                String deviceIdentify = deviceInfo.getDeviceIdentify();
                if (!TextUtils.isEmpty(deviceIdentify)) {
                    arrayList.add(kak.b(deviceIdentify));
                }
            }
        }
        return arrayList;
    }

    private void a(List<String> list, List<String> list2) {
        boolean z = list == null || list.isEmpty();
        boolean z2 = list2 == null || list2.isEmpty();
        if (z && z2) {
            LogUtil.a("HwCalendarSyncMgr", "calculateChanged: SCENARIO 1.");
            return;
        }
        if (z) {
            LogUtil.a("HwCalendarSyncMgr", "calculateChanged: SCENARIO 2.");
            a(list2);
            return;
        }
        if (z2) {
            LogUtil.a("HwCalendarSyncMgr", "calculateChanged: SCENARIO 3.");
            return;
        }
        LogUtil.a("HwCalendarSyncMgr", "calculateChanged: SCENARIO 4.");
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList(list);
        List<String> arrayList3 = new ArrayList<>(list2);
        if (arrayList.retainAll(list2)) {
            LogUtil.a("HwCalendarSyncMgr", "calculateChanged: both of this list is same.");
            return;
        }
        if (arrayList2.removeAll(arrayList)) {
            LogUtil.a("HwCalendarSyncMgr", "removeAll ok");
        }
        if (arrayList3.removeAll(arrayList)) {
            a(arrayList3);
        }
    }

    private void a(List<String> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("HwCalendarSyncMgr", "onDeviceRemoved: removed device list is null or empty.");
        } else {
            LogUtil.a("HwCalendarSyncMgr", "onDeviceRemoved: removed device list's size:", Integer.valueOf(list.size()));
            jyi.d(list);
        }
    }

    public void c() {
        LogUtil.a("HwCalendarSyncMgr", "destroy");
        Context context = this.e;
        if (context != null) {
            context.unregisterReceiver(this.c);
            this.e.unregisterReceiver(this.i);
        }
        jyk.b().e(BaseApplication.getContext());
        jyk.b().a();
        g();
    }
}
