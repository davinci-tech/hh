package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.btproxy.callback.BtProxySleepCallback;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.util.LogUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class jue {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14090a = new Object();
    private static jue c;
    private Map<String, Integer> g = new HashMap(16);
    private BtProxySleepCallback b = null;
    private String d = null;
    private BroadcastReceiver e = new BroadcastReceiver() { // from class: jue.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            LogUtil.d("BtProxySleepStatusManager", "mDeviceStatusReceiver action: ", action);
            if (action == null) {
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                jue.this.bJy_(intent);
            } else {
                LogUtil.c("BtProxySleepStatusManager", "mDeviceStatusReceiver other action");
            }
        }
    };

    private jue() {
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.e, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    public void a(String str, BtProxySleepCallback btProxySleepCallback) {
        if (btProxySleepCallback == null) {
            LogUtil.c("BtProxySleepStatusManager", "registerSleepStatusListen btProxySleepCallback is null.");
            return;
        }
        LogUtil.d("BtProxySleepStatusManager", "registerSleepStatusListen deviceInfoIdentify: ", blt.b(str));
        this.d = str;
        this.b = btProxySleepCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bJy_(Intent intent) {
        DeviceInfo deviceInfo = intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo ? (DeviceInfo) intent.getParcelableExtra("deviceinfo") : null;
        if (deviceInfo == null) {
            LogUtil.c("BtProxySleepStatusManager", "deviceInfo is null");
        } else {
            LogUtil.d("BtProxySleepStatusManager", "dealConnectStatusChange deviceInfoIdentify: ", blt.b(deviceInfo.getDeviceIdentify()));
            e(deviceInfo.getDeviceIdentify(), 0);
        }
    }

    public static jue b() {
        jue jueVar;
        synchronized (f14090a) {
            if (c == null) {
                LogUtil.d("BtProxySleepStatusManager", "BtProxySleepStatusManager init.");
                c = new jue();
            }
            jueVar = c;
        }
        return jueVar;
    }

    public int c(String str) {
        LogUtil.d("BtProxySleepStatusManager", "getDeviceSleepStatus deviceIdentify is ", blt.b(str));
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("BtProxySleepStatusManager", "getDeviceSleepStatus deviceMac is null.");
            return 0;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        int i = calendar.get(11);
        LogUtil.d("BtProxySleepStatusManager", "current:", Long.valueOf(currentTimeMillis), " hour: ", Integer.valueOf(i), " minutes: ", Integer.valueOf(calendar.get(12)));
        if (CommonUtil.s(BaseApplication.getContext()) || i >= 9) {
            LogUtil.d("BtProxySleepStatusManager", "getDeviceSleepStatus operating hours.");
            return 0;
        }
        int a2 = a(str);
        LogUtil.d("BtProxySleepStatusManager", "getDeviceSleepStatus sleepStatus: ", Integer.valueOf(a2));
        return a2;
    }

    public void b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("BtProxySleepStatusManager", "dealDeviceSleepAction deviceMac is empty.");
            return;
        }
        LogUtil.d("BtProxySleepStatusManager", "dealDeviceSleepAction action: ", Integer.valueOf(i));
        if (i != 4) {
            if (i == 16) {
                e(str, 1);
                return;
            } else {
                LogUtil.c("BtProxySleepStatusManager", "dealDeviceSleepAction else action.");
                return;
            }
        }
        e(str, 0);
        if (this.b == null || !str.equals(this.d)) {
            return;
        }
        this.b.onSleepStatusCallback(0);
    }

    public boolean a(DeviceInfo deviceInfo) {
        return cwi.c(deviceInfo, 136);
    }

    private int a(String str) {
        if (this.g.containsKey(str)) {
            return this.g.get(str).intValue();
        }
        return 0;
    }

    private void e(String str, int i) {
        this.g.put(str, Integer.valueOf(i));
    }

    public void e() {
        BaseApplication.getContext().unregisterReceiver(this.e);
        this.g.clear();
        this.b = null;
        c();
    }

    private static void c() {
        synchronized (f14090a) {
            c = null;
        }
    }
}
