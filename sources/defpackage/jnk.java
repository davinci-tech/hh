package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.huawei.datatype.PayApduInfo;
import com.huawei.datatype.PayOpenChannelInfo;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwpaymgr.HwPayManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class jnk {
    private static jnk d;
    private String h;
    private String i;
    private static final Object e = new Object();
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13970a = new Object();
    private Map<String, String> o = new HashMap(16);
    private HashMap<jmt, String> g = new HashMap<>(16);
    private Map<Integer, Object> c = new HashMap(16);
    private int n = 2;
    private int m = 0;
    private IBaseResponseCallback j = new IBaseResponseCallback() { // from class: jnk.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("R_ApduServer", "send apdu onResponse, errorCode: ", Integer.valueOf(i));
            if (i != 0) {
                Iterator it = jnk.this.c.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    LogUtil.c("ApduServer", "fail entry.getKey:", entry.getKey(), "entry.getValue:", entry.getValue());
                    jnk.this.h = null;
                    it.remove();
                }
                jnk.this.h();
                LogUtil.h("ApduServer", "fail, unlockAndRemoveAll, map.size: ", Integer.valueOf(jnk.this.c.size()));
                return;
            }
            if (obj instanceof PayApduInfo) {
                PayApduInfo payApduInfo = (PayApduInfo) obj;
                jnk.this.h = payApduInfo.getApdu();
                jnk.this.h();
                jnk.this.c.remove(Integer.valueOf(payApduInfo.getReqid()));
                LogUtil.a("R_ApduServer", "success ,unlockAndRemove,reqId: ", Integer.valueOf(payApduInfo.getReqid()), " lockObject: ", jnk.this.c.get(Integer.valueOf(payApduInfo.getReqid())));
            }
        }
    };
    private BroadcastReceiver f = new BroadcastReceiver() { // from class: jnk.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceInfo deviceInfo;
            if (context == null) {
                LogUtil.h("ApduServer", "mConnectStateChangedReceiver, context is null");
                return;
            }
            if (intent == null) {
                LogUtil.h("ApduServer", "mConnectStateChangedReceiver, intent is null");
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                LogUtil.h("ApduServer", "mConnectStateChangedReceiver, intentAction is null.");
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                try {
                    deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                } catch (ClassCastException unused) {
                    LogUtil.b("ApduServer", "mConnectStateChangedReceiver, ClassCastException exception");
                    deviceInfo = null;
                }
                if (deviceInfo == null) {
                    LogUtil.h("ApduServer", "mConnectStateChangedReceiver, deviceInfo is null");
                    return;
                }
                if (deviceInfo.getRelationship() != null && "followed_relationship".equals(deviceInfo.getRelationship())) {
                    LogUtil.h("ApduServer", "This device does not have the correspond capability.");
                    return;
                }
                jnk.this.n = deviceInfo.getDeviceConnectState();
                LogUtil.a("ApduServer", "mConnectStateChangedReceiver, onConnectStateChange state:", Integer.valueOf(jnk.this.n));
                if (jnk.this.n != 2) {
                    jnk.this.h = null;
                } else {
                    LogUtil.a("ApduServer", "mConnectStateChangedReceiver, device is connect");
                }
            }
        }
    };

    private jnk() {
    }

    public static jnk a() {
        jnk jnkVar;
        synchronized (f13970a) {
            if (d == null) {
                d = new jnk();
            }
            jnkVar = d;
        }
        return jnkVar;
    }

    public void e() {
        this.n = jnu.b();
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.f, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    public void g() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.f);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("ApduServer", "unRegisterConnectStateChangeReceiver IllegalArgumentException");
        } catch (IllegalStateException unused2) {
            LogUtil.b("ApduServer", "unRegisterConnectStateChangeReceiver IllegalStateException");
        }
        this.h = null;
        this.i = null;
        this.m = 0;
        this.o.clear();
        this.g.clear();
        this.c.clear();
    }

    private String d(String str) {
        int w = (CommonUtil.w(str.length() >= 10 ? str.substring(8, 10) : "") * 2) + 10;
        return str.length() >= w ? str.substring(10, w) : "";
    }

    public jmr<jmt> c(List<jmn> list, jmt jmtVar) {
        jmr<jmt> jmrVar = new jmr<>();
        if (list == null || list.isEmpty()) {
            LogUtil.h("ApduServer", "apduList is isEmpty or is null");
            return d(jmrVar, jmtVar, 1004);
        }
        e(list);
        a(jmtVar.c(), 0);
        for (jmn jmnVar : list) {
            LogUtil.c("ApduServer", "ApduCommand command:", jmnVar);
            jmrVar.c(jmnVar);
            if (TextUtils.isEmpty(jmnVar.a())) {
                return d(jmrVar, jmtVar, 1001);
            }
            try {
                if (jmnVar.a().toUpperCase(Locale.getDefault()).startsWith("00A40400")) {
                    String d2 = d(jmnVar.a());
                    if (!TextUtils.isEmpty(this.g.get(jmtVar))) {
                        d();
                    }
                    jmtVar.e(d2);
                    Map a2 = a(d2, jmtVar.b());
                    if (a2 != null) {
                        d(jmtVar, (Map<String, String>) a2, jmnVar);
                    }
                }
                String b2 = b("00", jmnVar.a());
                if (TextUtils.isEmpty(b2) || b2.length() < 4) {
                    return d(jmrVar, jmtVar, 4001);
                }
                if (c(jmnVar.a(), b2)) {
                    LogUtil.a("ApduServer", "need init ese");
                    return d(jmrVar, jmtVar, WearableStatusCodes.DATA_ITEM_TOO_LARGE);
                }
                jmnVar.e(b2);
                String upperCase = jmnVar.d().toUpperCase(Locale.getDefault());
                if (jmnVar.b() != null && !upperCase.matches(jmnVar.b())) {
                    jmrVar.c(jmnVar);
                    LogUtil.c("ApduServer", "executeApduList fail sw:", jmnVar.d());
                    return d(jmrVar, jmtVar, 4002);
                }
            } catch (jms e2) {
                return d(jmrVar, jmtVar, e2.c());
            }
        }
        jmrVar.a(jmtVar);
        return jmrVar;
    }

    private void d(jmt jmtVar, Map<String, String> map, jmn jmnVar) {
        LogUtil.c("ApduServer", "ApduServer executeApduList selectResp:", map.get(PluginPayAdapter.KEY_OPEN_CHANNEL_ID));
        jmnVar.e(map.get(PluginPayAdapter.KEY_OPEN_CHANNEL_ID));
        this.g.put(jmtVar, map.get(PluginPayAdapter.KEY_OPEN_CHANNEL_APDU));
    }

    private boolean c(String str, String str2) {
        if (str.length() > 4) {
            int w = CommonUtil.w(str.substring(0, 2));
            String substring = str.substring(2, 4);
            if ((w & 128) == 128 && substring.equals("E6") && str.contains("D2760000850101") && str2.contains("6A88")) {
                return true;
            }
            if (substring.equals("E0") && str.contains("D2760000850101") && str2.contains("6E00")) {
                return true;
            }
            LogUtil.h("ApduServer", "isNeedInitEse else branch");
        }
        return false;
    }

    private jmr<jmt> d(jmr<jmt> jmrVar, jmt jmtVar, int i) {
        jmrVar.a(jmtVar);
        jmrVar.d(i);
        LogUtil.a("ApduServer", "ApduServer setResult resultCode:", Integer.valueOf(i));
        return jmrVar;
    }

    private void e(List<jmn> list) {
        for (jmn jmnVar : list) {
            jmnVar.a("");
            jmnVar.b("");
        }
    }

    public String b(String str, String str2) throws jms {
        LogUtil.a("ApduServer", "executeApdu channelId:", str, "responseApdu:", str2);
        this.h = null;
        if (str2 == null) {
            LogUtil.h("ApduServer", "executeApdu responseApdu is null");
            return this.h;
        }
        if (!c()) {
            LogUtil.h("ApduServer", "executeApdu disConnected");
            return this.h;
        }
        int i = i();
        this.c.put(Integer.valueOf(i), new Object());
        PayApduInfo payApduInfo = new PayApduInfo();
        payApduInfo.setApdu(str2);
        payApduInfo.setReqid(i);
        try {
            payApduInfo.setChannelId(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            LogUtil.b("ApduServer", "executeApdu channelId NumberFormatException");
        }
        LogUtil.a("ApduServer", "PayApduInfo:", payApduInfo.toString());
        HwPayManager.a().d(payApduInfo, this.j);
        j();
        LogUtil.a("ApduServer", "executeApdu mApduResponse:", this.h);
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        synchronized (this) {
            notifyAll();
        }
    }

    private void j() {
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException unused) {
                LogUtil.b("ApduServer", "lock InterruptedException");
            }
        }
    }

    private int i() {
        if (this.m == Integer.MAX_VALUE) {
            this.m = 1;
        }
        int i = this.m;
        this.m = i + 1;
        return i;
    }

    public boolean c() {
        return this.n == 2;
    }

    public Map a(String str, int i) {
        synchronized (e) {
            this.o = new HashMap(16);
            LogUtil.a("ApduServer", "openChannel channelType:", Integer.valueOf(i));
            if (!c()) {
                LogUtil.h("ApduServer", "is not connected");
                return this.o;
            }
            HwPayManager.a().b(str, i, new IBaseResponseCallback() { // from class: jnk.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("ApduServer", "openChannel response errorCode:", Integer.valueOf(i2));
                    if (i2 != 0 || !(obj instanceof PayOpenChannelInfo)) {
                        jnk.this.o.clear();
                    } else {
                        PayOpenChannelInfo payOpenChannelInfo = (PayOpenChannelInfo) obj;
                        jnk.this.o.put(PluginPayAdapter.KEY_OPEN_CHANNEL_APDU, payOpenChannelInfo.getApdu());
                        jnk.this.o.put(PluginPayAdapter.KEY_OPEN_CHANNEL_ID, payOpenChannelInfo.getChannelId() + "");
                    }
                    jnk.this.h();
                }
            });
            j();
            return this.o;
        }
    }

    public void d() {
        synchronized (b) {
            LogUtil.a("ApduServer", "closeChannel enter");
            if (!c()) {
                LogUtil.h("ApduServer", "is not connected");
            } else {
                HwPayManager.a().d(new IBaseResponseCallback() { // from class: jnk.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (i == 0) {
                            LogUtil.a("ApduServer", "closeChannel success");
                        } else {
                            LogUtil.h("ApduServer", "closeChannel fail");
                        }
                    }
                });
            }
        }
    }

    public String b() {
        LogUtil.a("ApduServer", "getCplc enter");
        if (!TextUtils.isEmpty(this.i)) {
            return this.i;
        }
        if (!c()) {
            LogUtil.h("ApduServer", "get mCplc is not connected");
            return this.i;
        }
        HwPayManager.a().e(new IBaseResponseCallback() { // from class: jnk.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("ApduServer", "PluginPayAdapterImpl getCplc onResponse errorCode :", Integer.valueOf(i));
                if (i != 0 || !(obj instanceof String)) {
                    jnk.this.i = null;
                } else {
                    jnk.this.i = (String) obj;
                }
                jnk.this.h();
            }
        });
        if (TextUtils.isEmpty(this.i)) {
            j();
        }
        return this.i;
    }
}
