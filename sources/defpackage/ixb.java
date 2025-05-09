package defpackage;

import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.datatype.PayApduInfo;
import com.huawei.datatype.PayOpenChannelInfo;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwpaymgr.HwPayManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.nfc.WalletResponseListener;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
public class ixb implements PluginPayAdapter {
    private static int ad;
    private static HwPayManager u;
    private static jfo v;
    private static ixb x;
    private static jfq y;
    private String aa;
    private int ab;
    private String af;
    private String ag;
    private String ai;
    private List<String> ak;
    private String am;
    private boolean an;
    private boolean ao;
    private boolean aq;
    private boolean as;
    private boolean at;
    private boolean au;
    private boolean av;
    private String ay;
    private String az;
    private String bb;
    private String z;
    private static final Object b = new Object();
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13641a = new Object();
    private static final Object n = new Object();
    private static final Object d = new Object();
    private static final Object w = new Object();
    private static final Object l = new Object();
    private static final Object s = new Object();
    private static final Object i = new Object();
    private static final Object c = new Object();
    private static final Object h = new Object();
    private static final Object o = new Object();
    private static final Object g = new Object();
    private static final Object m = new Object();
    private static final Object f = new Object();
    private static final Object p = new Object();
    private static final Object q = new Object();
    private static final Object j = new Object();
    private static final Object t = new Object();
    private static final Object k = new Object();
    private static final byte[] r = new byte[0];
    private Map<Integer, Object> ac = new HashMap(16);
    private int bc = -1;
    private boolean ap = false;
    private long aw = 0;
    private int ax = -1;
    private Map<String, String> ba = new HashMap(16);
    private Handler al = HandlerExecutor.yF_();
    private boolean ar = false;
    private Map<String, ArrayList<String>> aj = new HashMap(16);
    private Runnable ae = new Runnable() { // from class: ixb.5
        @Override // java.lang.Runnable
        public void run() {
            LogUtil.a("PluginPayAdapterImpl", "Enter mBatteryRunnable");
            jog.c().d();
        }
    };
    private IBaseResponseCallback ah = new IBaseResponseCallback() { // from class: ixb.13
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i2, Object obj) {
            ReleaseLogUtil.e("R_PluginPayAdapterImpl", "send apdu onResponse, errorCode: ", Integer.valueOf(i2), " mApduMap.size: ", Integer.valueOf(ixb.this.ac.size()));
            ixb.this.al.removeCallbacks(ixb.this.ae);
            if (i2 != 0 || !(obj instanceof PayApduInfo)) {
                Iterator it = ixb.this.ac.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    LogUtil.c("PluginPayAdapterImpl", "fail entry.getKey(): ", entry.getKey(), " entry.getValue(): ", entry.getValue());
                    ixb.this.aa = null;
                    ixb.this.d("onResponse", entry.getValue());
                    it.remove();
                }
                LogUtil.c("PluginPayAdapterImpl", "fail, unlockAndRemoveAll, map.size: ", Integer.valueOf(ixb.this.ac.size()));
                return;
            }
            PayApduInfo payApduInfo = (PayApduInfo) obj;
            ixb.this.aa = payApduInfo.getApdu();
            ixb ixbVar = ixb.this;
            ixbVar.d("onResponse", ixbVar.ac.get(Integer.valueOf(payApduInfo.getReqid())));
            ixb.this.ac.remove(Integer.valueOf(payApduInfo.getReqid()));
            LogUtil.c("PluginPayAdapterImpl", "success, unlockAndRemove, requestId: ", Integer.valueOf(payApduInfo.getReqid()), " lockObject: ", ixb.this.ac.get(Integer.valueOf(payApduInfo.getReqid())), " map.size: ", Integer.valueOf(ixb.this.ac.size()));
        }
    };

    class a implements HwPayManager.ConnectStateChangeCallback {
        private a() {
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwpaymgr.HwPayManager.ConnectStateChangeCallback
        public void onConnectStateChange(int i) {
            LogUtil.a("PluginPayAdapterImpl", "onConnectStateChange");
            if (i != 2) {
                ixb.this.am = null;
                ixb.this.af = null;
                ixb.this.aa = null;
                ixb.this.bb = null;
                return;
            }
            LogUtil.c("PluginPayAdapterImpl", "DEVICE_CONNECTED");
        }
    }

    private ixb() {
        LogUtil.a("PluginPayAdapterImpl", "PluginPayAdapterImpl init");
        HwPayManager a2 = HwPayManager.a();
        u = a2;
        a2.b(new a());
        y = jfq.c();
        v = jfo.e();
    }

    public static ixb p() {
        ixb ixbVar;
        synchronized (r) {
            if (x == null) {
                x = new ixb();
            }
            LogUtil.a("PluginPayAdapterImpl", "PluginPayAdapterImpl getInstance: ", x);
            ixbVar = x;
        }
        return ixbVar;
    }

    private void c(boolean z) {
        LogUtil.a("PluginPayAdapterImpl", "setmIsFmRecharge isFmRecharge: ", Boolean.valueOf(z));
        this.ap = z;
        if (z) {
            this.aw = System.currentTimeMillis();
        } else {
            this.aw = 0L;
        }
    }

    private int t() {
        if (ad == Integer.MAX_VALUE) {
            ad = 1;
        }
        int i2 = ad;
        ad = i2 + 1;
        return i2;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public byte[] openChannelSnb(byte[] bArr, int i2) {
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "openChannelSNB, aids: ", cvx.d(bArr), " channelType: ", Integer.valueOf(i2));
        Map<String, String> openChannel = openChannel(cvx.d(bArr), i2);
        if (openChannel == null) {
            return null;
        }
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "openChannelSNB, apdu: ", openChannel.get(PluginPayAdapter.KEY_OPEN_CHANNEL_APDU));
        c(true);
        return cvx.a(openChannel.get(PluginPayAdapter.KEY_OPEN_CHANNEL_APDU));
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public byte[] transmitSnb(byte[] bArr) {
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "transmitSNB, apduBytes: ", cvx.d(bArr));
        return cvx.a(d("00", cvx.d(bArr)));
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public void closeChannelSnb() {
        c(false);
        closeChannel();
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public String getCplc() {
        LogUtil.a("PluginPayAdapterImpl", "PluginPayAdapterImpl getCplc enter");
        if (q()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "getCplc fm operation:", this.am);
            return this.am;
        }
        if (!TextUtils.isEmpty(this.am)) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "cplc is not null, return cplc: ", this.am);
            return this.am;
        }
        if (u == null || !x()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "PluginPayAdapterImpl getCplc sPayManager is null");
            return this.am;
        }
        u.e(new IBaseResponseCallback() { // from class: ixb.19
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "PluginPayAdapterImpl getCplc.onResponse.errorCode: ", Integer.valueOf(i2));
                if (i2 != 0 || !(obj instanceof String)) {
                    ixb.this.am = null;
                } else {
                    ixb.this.am = (String) obj;
                    LogUtil.c("PluginPayAdapterImpl", "get cplc from watch: ", ixb.this.am);
                }
                ixb.this.d("getCplc", ixb.f);
            }
        });
        if (TextUtils.isEmpty(this.am)) {
            b("getCplc", f);
        }
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "PluginPayAdapterImpl getCplc return, cplc: ", this.am);
        return this.am;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public Map<String, String> getDeviceInfo() {
        String uuid;
        HashMap hashMap = new HashMap();
        if (y == null || !x()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "PluginPayAdapterImpl getDeviceInfo sDeviceManager is null");
            return hashMap;
        }
        DeviceInfo a2 = jpt.a("PluginPayAdapterImpl");
        if (a2 != null) {
            if (a2.getDeviceConnectState() == 2) {
                if (TextUtils.isEmpty(a2.getUuid())) {
                    uuid = a2.getDeviceIdentify();
                } else {
                    uuid = a2.getUuid();
                }
                if (TextUtils.isEmpty(uuid)) {
                    sqo.ae("getDeviceInfo deviceSn is Empty");
                }
                a(a2);
                hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_SN, cvx.e(uuid));
                hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, a2.getDeviceModel());
                hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_SOFT_VERSION, a2.getSoftVersion());
                hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_BT_VERSION, a2.getBluetoothVersion());
                hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_CERT_MODEL, a2.getCertModel());
                hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_UDID, a2.getDeviceUdid());
                hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, a2.getDeviceName());
                hashMap.put(PluginPayAdapter.DEVICE_CATEGORY, String.valueOf(jfu.h(a2.getProductType()) ? 1 : 2));
                hashMap.put(PluginPayAdapter.DEVICE_BT_TYPE, String.valueOf(a2.getDeviceBluetoothType()));
            }
        }
        return hashMap;
    }

    private void a(DeviceInfo deviceInfo) {
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            return;
        }
        String deviceUdid = deviceInfo.getDeviceUdid();
        String valueOf = String.valueOf(deviceInfo.getProductType());
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        ArrayList<String> arrayList = this.aj.get(deviceIdentify);
        if (arrayList == null || arrayList.size() != 3) {
            ArrayList<String> arrayList2 = new ArrayList<>(3);
            arrayList2.add(deviceUdid);
            arrayList2.add(valueOf);
            arrayList2.add(securityDeviceId);
            this.aj.put(deviceIdentify, arrayList2);
            return;
        }
        if (Objects.equals(arrayList.get(0), deviceUdid) && Objects.equals(arrayList.get(1), valueOf) && Objects.equals(arrayList.get(2), securityDeviceId)) {
            return;
        }
        String join = String.join(" ", "checkRiskDeviceUdid deviceName:", deviceInfo.getDeviceName(), "deviceIdentify:", CommonUtil.l(deviceIdentify), "oldDeviceUdid:", arrayList.set(0, deviceUdid), "deviceUdid:", deviceUdid, "oldProductType:", arrayList.set(1, valueOf), "productType:", valueOf, "oldSecurityDeviceId:", CommonUtil.l(arrayList.set(2, securityDeviceId)), "securityDeviceId:", CommonUtil.l(securityDeviceId));
        ReleaseLogUtil.d("R_PluginPayAdapterImpl", join);
        sqo.ae(join);
    }

    private String d(String str, String str2) {
        LogUtil.a("PluginPayAdapterImpl", "enter sendApduForFm channelId: ", str, " requestApdu: ", str2);
        this.aa = null;
        if (str2 == null) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "sendApduForFm requestApdu is null");
            return this.aa;
        }
        if (u == null || !x()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "sendApduForFm sPayManager is null, return null, isConnected is ", Boolean.valueOf(x()));
            return this.aa;
        }
        Integer valueOf = Integer.valueOf(t());
        this.ac.put(valueOf, new Object());
        PayApduInfo payApduInfo = new PayApduInfo();
        payApduInfo.setApdu(str2);
        payApduInfo.setReqid(valueOf.intValue());
        try {
            payApduInfo.setChannelId(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("R_PluginPayAdapterImpl", "sendApduForFm NumberFormatException");
        }
        u.d(payApduInfo, this.ah);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "lock, requestId: ", valueOf, " mApduMap: ", this.ac.get(valueOf), " size: ", Integer.valueOf(this.ac.size()));
        b("sendApdu", this.ac.get(valueOf));
        return this.aa;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public String sendApdu(String str, String str2) {
        LogUtil.c("PluginPayAdapterImpl", "enter sendApdu channelId: ", str, " requestApdu: ", str2);
        this.aa = null;
        if (q()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", " sendApdu fm operation");
            return this.aa;
        }
        if (str2 == null) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", " sendApdu requestApdu is null");
            return this.aa;
        }
        if (u == null || !x()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "PluginPayAdapterImpl sendApdu sPayManager is null, return null, isConnected is ", Boolean.valueOf(x()));
            return this.aa;
        }
        Integer valueOf = Integer.valueOf(t());
        this.ac.put(valueOf, new Object());
        PayApduInfo payApduInfo = new PayApduInfo();
        payApduInfo.setApdu(str2);
        payApduInfo.setReqid(valueOf.intValue());
        try {
            payApduInfo.setChannelId(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("R_PluginPayAdapterImpl", "sendApdu NumberFormatException");
        }
        u.d(payApduInfo, this.ah);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "lock, requestId: ", valueOf, " lockObject: ", this.ac.get(valueOf), " map.size: ", Integer.valueOf(this.ac.size()));
        if (this.ar) {
            this.al.removeCallbacks(this.ae);
            this.al.postDelayed(this.ae, 15000L);
        }
        b("sendApdu", this.ac.get(valueOf));
        return this.aa;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public Map<String, String> openChannel(String str, int i2) {
        synchronized (q) {
            this.ba = new HashMap(16);
            if (q()) {
                ReleaseLogUtil.d("R_PluginPayAdapterImpl", " openChannel fm operation");
                return null;
            }
            if (u != null && x()) {
                u.b(str, i2, new IBaseResponseCallback() { // from class: ixb.24
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i3, Object obj) {
                        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "openChannel onResponse errorCode:", Integer.valueOf(i3));
                        if (i3 != 0 || !(obj instanceof PayOpenChannelInfo)) {
                            ixb.this.ba.clear();
                        } else {
                            PayOpenChannelInfo payOpenChannelInfo = (PayOpenChannelInfo) obj;
                            ixb.this.ba.put(PluginPayAdapter.KEY_OPEN_CHANNEL_APDU, payOpenChannelInfo.getApdu());
                            ixb.this.ba.put(PluginPayAdapter.KEY_OPEN_CHANNEL_ID, payOpenChannelInfo.getChannelId() + "");
                        }
                        ixb.this.d("openChannel", ixb.s);
                    }
                });
                b("openChannel", s);
                return this.ba;
            }
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "PluginPayAdapterImpl openChannel sPayManager is null");
            return this.ba;
        }
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public void closeChannel() {
        synchronized (j) {
            LogUtil.c("PluginPayAdapterImpl", "closeChannel enter");
            if (q()) {
                ReleaseLogUtil.d("R_PluginPayAdapterImpl", "closeChannel fm operation");
                return;
            }
            if (u != null && x()) {
                u.d(new IBaseResponseCallback() { // from class: ixb.23
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "closeChannel onResponse errorCode:", Integer.valueOf(i2));
                        if (i2 == 0) {
                            LogUtil.c("PluginPayAdapterImpl", "closeChannel SUCCESS");
                        } else {
                            LogUtil.c("PluginPayAdapterImpl", "closeChannel FAIL");
                        }
                        ixb.this.d("closeChannel", ixb.i);
                    }
                });
                b("closeChannel", i);
                return;
            }
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "PluginPayAdapterImpl closeChannel sPayManager is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, Object obj) {
        if (obj == null) {
            return;
        }
        synchronized (obj) {
            obj.notifyAll();
            LogUtil.c("PluginPayAdapterImpl", "method: ", str, " unlock: ", obj);
        }
    }

    private void b(String str, Object obj) {
        if (obj != null) {
            a(str, obj, 80000L);
        } else {
            LogUtil.h("PluginPayAdapterImpl", "objectLock is null");
        }
    }

    private void a(String str, Object obj, long j2) {
        synchronized (obj) {
            try {
                LogUtil.c("PluginPayAdapterImpl", "method: ", str, " lock: ", obj);
                obj.wait(j2);
            } catch (InterruptedException unused) {
                LogUtil.b("PluginPayAdapterImpl", "lock InterruptedException Exception");
            }
        }
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public String getBtcInfoResponse() {
        this.af = "";
        if (q()) {
            LogUtil.a("PluginPayAdapterImpl", " getBTCInfoResponse fm operation");
            return this.af;
        }
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl getBTCInfoResponse sPayManager is null");
            return this.af;
        }
        u.b(new IBaseResponseCallback() { // from class: ixb.25
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 != 0 || !(obj instanceof String)) {
                    ixb.this.af = null;
                } else {
                    ixb.this.af = (String) obj;
                    LogUtil.a("PluginPayAdapterImpl", "mBtcInfoResponse: ", ixb.this.af);
                }
                ixb.this.d("getBTCInfoResponse", ixb.c);
            }
        });
        if ("".equals(this.af)) {
            b("getBTCInfoResponse", c);
        }
        LogUtil.a("PluginPayAdapterImpl", "getBTCInfoResponse, btcInfo: ", this.af);
        return this.af;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public int getDeviceConnectState() {
        int i2;
        DeviceInfo a2 = jpt.a("PluginPayAdapterImpl");
        if (a2 != null) {
            i2 = a2.getDeviceConnectState();
        } else {
            LogUtil.h("PluginPayAdapterImpl", "getDeviceConnectState error,deviceInfo is null!");
            i2 = 0;
        }
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getDeviceConnectState, state: ", Integer.valueOf(i2));
        return i2;
    }

    private boolean x() {
        return getDeviceConnectState() == 2;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public boolean addBusCard(String str, String str2, String str3) {
        LogUtil.a("PluginPayAdapterImpl", "addBusCard enter");
        if (q()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "addBusCard fm operation");
            return false;
        }
        if (u == null || !x()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "PluginPayAdapterImpl addBusCard sPayManager is null");
            return false;
        }
        u.c(str, str2, str3, new IBaseResponseCallback() { // from class: ixb.22
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.d("R_PluginPayAdapterImpl", "addBusCard onResponse errorCode:", Integer.valueOf(i2));
                if (i2 == 0) {
                    ixb.this.an = true;
                } else {
                    ixb.this.an = false;
                }
                ixb.this.d("addBusCard", ixb.f13641a);
            }
        });
        b("addBusCard", f13641a);
        return this.an;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public String getUserId() {
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        return loginInit == null ? "" : loginInit.getAccountInfo(1011);
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public boolean notifyAfterTransferFile(List<Map<String, Object>> list) {
        LogUtil.a("PluginPayAdapterImpl", "notifyAfterTransferFile enter");
        if (!q()) {
            LogUtil.c("PluginPayAdapterImpl", "PluginPayAdapterImpl notifyAfterTransferFile sInstance: ", x);
            if (u == null || !x()) {
                ReleaseLogUtil.d("R_PluginPayAdapterImpl", "PluginPayAdapterImpl notifyAfterTransferFile sPayManager is null");
                return false;
            }
            ArrayList arrayList = new ArrayList(16);
            for (int i2 = 0; i2 < list.size(); i2++) {
                Object obj = list.get(i2).get("FILE_NAME");
                Object obj2 = list.get(i2).get("FILE_TYPE");
                if ((obj instanceof String) && (obj2 instanceof Integer)) {
                    jlb jlbVar = new jlb();
                    String str = (String) obj;
                    int intValue = ((Integer) obj2).intValue();
                    if (TextUtils.isEmpty(str)) {
                        return false;
                    }
                    jlbVar.c(str);
                    jlbVar.a(intValue);
                    arrayList.add(jlbVar);
                }
            }
            u.e(arrayList, new IBaseResponseCallback() { // from class: ixb.21
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i3, Object obj3) {
                    ReleaseLogUtil.e("R_PluginPayAdapterImpl", "notifyAfterTransferFile transmitFile errorCode:", Integer.valueOf(i3));
                    if (i3 == 0) {
                        ixb.this.aq = true;
                    } else {
                        ixb.this.aq = false;
                    }
                    ixb.this.d("notifyAfterTransferFile", ixb.n);
                }
            });
            b("notifyAfterTransferFile", n);
            Object[] objArr = new Object[2];
            objArr[0] = "notifyAfterTransferFile end, result is ";
            objArr[1] = this.aq ? "success" : "false";
            ReleaseLogUtil.e("R_PluginPayAdapterImpl", objArr);
            return this.aq;
        }
        ReleaseLogUtil.d("R_PluginPayAdapterImpl", " notifyAfterTransferFile fm operation");
        return false;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public boolean addCardToWatch(String str) {
        LogUtil.a("PluginPayAdapterImpl", "addCardToWatch enter");
        if (q()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", " addCardToWatch fm operation");
            return false;
        }
        if (u == null || !x()) {
            ReleaseLogUtil.e("R_PluginPayAdapterImpl", "PluginPayAdapterImpl addCardToWatch sPayManager is null");
            return false;
        }
        u.c(str, new IBaseResponseCallback() { // from class: ixb.28
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "addCardToWatch onResponse errorCode:", Integer.valueOf(i2));
                if (i2 == 0) {
                    ixb.this.ao = true;
                } else {
                    ixb.this.ao = false;
                }
                ixb.this.d("addCardToWatch", ixb.d);
            }
        });
        b("addCardToWatch", d);
        Object[] objArr = new Object[2];
        objArr[0] = "addCardToWatch end, result is ";
        objArr[1] = this.ao ? "success" : "false";
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", objArr);
        return this.ao;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public boolean updateCardInfo(String str) {
        LogUtil.a("PluginPayAdapterImpl", "updateCardInfo enter");
        if (q()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "updateCardInfo fm operation");
            return false;
        }
        this.av = false;
        if (u == null || !x()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "PluginPayAdapterImpl updateCardInfo sPayManager is null");
            return false;
        }
        u.a(str, new IBaseResponseCallback() { // from class: ixb.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "updateCardInfo onResponse errorCode:", Integer.valueOf(i2));
                if (i2 == 0) {
                    ixb.this.av = true;
                } else {
                    ixb.this.av = false;
                }
                ixb.this.d("updateCardInfo", ixb.w);
            }
        });
        Object obj = w;
        if (obj != null) {
            a("updateCardInfo", obj, 6000L);
        } else {
            LogUtil.h("PluginPayAdapterImpl", "UPDATE_CARD_INFO_LOCK is null");
        }
        Object[] objArr = new Object[2];
        objArr[0] = "updateCardInfo end, result is ";
        objArr[1] = this.av ? "success" : "false";
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", objArr);
        return this.av;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public List<String> obtainCardList() {
        LogUtil.a("PluginPayAdapterImpl", "obtainCardList enter");
        if (q()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "obtainCardList fm operation");
            return null;
        }
        if (u == null || !x()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "PluginPayAdapterImpl obtainCardList sPayManager is null");
            return null;
        }
        u.c(new IBaseResponseCallback() { // from class: ixb.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "obtainCardList onResponse errorCode:", Integer.valueOf(i2));
                if (i2 != 0) {
                    ixb.this.ak = null;
                } else if (obj instanceof List) {
                    ixb.this.ak = (List) obj;
                }
                ixb.this.d("obtainCardList", ixb.l);
            }
        });
        b("obtainCardList", l);
        List<String> list = this.ak;
        if (list != null) {
            ReleaseLogUtil.e("R_PluginPayAdapterImpl", "obtainCardList end, result cardList size is ", Integer.valueOf(list.size()));
        }
        return this.ak;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public boolean deleteCard(String str) {
        LogUtil.a("PluginPayAdapterImpl", "deleteCard enter");
        if (q()) {
            LogUtil.h("PluginPayAdapterImpl", " deleteCard fm operation");
            return false;
        }
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl deleteCard sPayManager is null");
            return false;
        }
        u.d(str, new IBaseResponseCallback() { // from class: ixb.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "deleteCard onResponse errorCode:", Integer.valueOf(i2));
                if (i2 == 0) {
                    ixb.this.as = true;
                } else {
                    ixb.this.as = false;
                }
                ixb.this.d("deleteCard", ixb.h);
            }
        });
        b("deleteCard", h);
        Object[] objArr = new Object[2];
        objArr[0] = "deleteCard end, result is ";
        objArr[1] = this.as ? "success" : "false";
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", objArr);
        return this.as;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public String getWalletAbility() {
        LogUtil.a("PluginPayAdapterImpl", "getWalletAbility enter");
        if (q()) {
            LogUtil.a("R_PluginPayAdapterImpl", "getWalletAbility fm operation");
            return "";
        }
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl getWalletAbility sPayManager is null");
            return "";
        }
        if (!TextUtils.isEmpty(this.bb)) {
            LogUtil.c("PluginPayAdapterImpl", "mWalletSupportCapacity is not null, return mWalletSupportCapacity: ", this.bb);
            return this.bb;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        u.g(new IBaseResponseCallback() { // from class: ixb.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getWalletAbility onResponse errorCode:", Integer.valueOf(i2));
                if (i2 != 0 || !(obj instanceof String)) {
                    ixb.this.bb = null;
                } else {
                    ixb.this.bb = (String) obj;
                }
                ixb.this.d("getWalletAbility", ixb.o);
            }
        });
        b("getWalletAbility", o);
        long uptimeMillis2 = SystemClock.uptimeMillis() - uptimeMillis;
        if (uptimeMillis2 > 5000) {
            sqo.ae("getWalletAbility waitTime:" + uptimeMillis2);
        }
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getWalletAbility end, result is ", this.bb);
        return this.bb;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public int getLockscreenStatus() {
        LogUtil.a("PluginPayAdapterImpl", "getLockscreenStatus enter");
        if (q()) {
            LogUtil.a("PluginPayAdapterImpl", " getLockscreenStatus fm operation");
            return this.ax;
        }
        if (y == null) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl getLockscreenStatus sDeviceManager is null");
            return this.ax;
        }
        joh.a().a(new IBaseResponseCallback() { // from class: ixb.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getLockscreenStatus onResponse errorCode:", Integer.valueOf(i2));
                if (i2 != 0 || !(obj instanceof Integer)) {
                    ixb.this.ax = -1;
                } else {
                    ixb.this.ax = ((Integer) obj).intValue();
                }
                ixb.this.d("getLockscreenStatus", ixb.g);
            }
        });
        b("getLockscreenStatus", g);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getLockscreenStatus end, result is ", Integer.valueOf(this.ax));
        return this.ax;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public boolean notificationOpenPageOfBand(String str) {
        LogUtil.a("PluginPayAdapterImpl", "notificationOpenPageOfBand enter");
        if (y == null) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl notificationOpenPageOfBand sDeviceManager is null");
            return this.au;
        }
        if (q()) {
            LogUtil.a("PluginPayAdapterImpl", " notificationOpenPageOfBand fm operation");
            return this.au;
        }
        joh.a().e(str, new IBaseResponseCallback() { // from class: ixb.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "notificationOpenPageOfBand onResponse errorCode:", Integer.valueOf(i2));
                if (i2 == 0) {
                    ixb.this.au = true;
                    LogUtil.a("PluginPayAdapterImpl", "mIsOpenPageResponse: ", Boolean.valueOf(ixb.this.au));
                } else {
                    ixb.this.au = false;
                }
                ixb.this.d("notificationOpenPageOfBand", ixb.m);
            }
        });
        b("notificationOpenPageOfBand", m);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "notificationOpenPageOfBand end,result is ", Boolean.valueOf(this.au));
        return this.au;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public int sendAccount(String str) {
        LogUtil.a("PluginPayAdapterImpl", "sendAccount enter");
        DeviceCapability d2 = cvs.d();
        if (d2 == null) {
            LogUtil.h("PluginPayAdapterImpl", "ability is null, Do not sendAccount");
            return -2;
        }
        LogUtil.a("PluginPayAdapterImpl", "sendAccount ability: ", d2);
        if (!d2.isSupportAccount()) {
            LogUtil.h("PluginPayAdapterImpl", "bot SupportAccount, Do not sendAccount");
            return -2;
        }
        jfo jfoVar = v;
        if (jfoVar == null) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl sendAccount sHwWearableManager is null");
            return this.bc;
        }
        jfoVar.a(str, new IBaseResponseCallback() { // from class: ixb.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "sendAccount onResponse errorCode:", Integer.valueOf(i2));
                if (obj instanceof Integer) {
                    ixb.this.bc = ((Integer) obj).intValue();
                }
                ixb.this.d("sendAccount", ixb.p);
            }
        });
        b("sendAccount", p);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "sendAccount end,result is ", Integer.valueOf(this.bc));
        return this.bc;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public void cardEvent(String str, int i2) {
        LogUtil.a("PluginPayAdapterImpl", "cardEvent enter");
        if (q()) {
            LogUtil.a("PluginPayAdapterImpl", "cardEvent fm operation");
        } else if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl cardEvent sPayManager is null");
        } else {
            u.d(str, i2, new IBaseResponseCallback() { // from class: ixb.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i3, Object obj) {
                    ReleaseLogUtil.e("R_PluginPayAdapterImpl", "cardEvent onResponse errorCode:", Integer.valueOf(i3));
                    if (i3 == 0) {
                        LogUtil.c("PluginPayAdapterImpl", "closeChannel SUCCESS");
                    } else {
                        LogUtil.c("PluginPayAdapterImpl", "closeChannel FAIL");
                    }
                    ixb.this.d("cardEvent", ixb.e);
                }
            });
            b("cardEvent", e);
        }
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public int getDeviceProtocol() {
        int i2;
        DeviceInfo a2 = jpt.a("PluginPayAdapterImpl");
        if (a2 != null) {
            i2 = a2.getProductType();
        } else {
            LogUtil.h("PluginPayAdapterImpl", "getDeviceProtocol error, deviceInfo is null!");
            i2 = -1;
        }
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getDeviceProtocol, deviceType: ", Integer.valueOf(i2));
        return i2;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public int getDeviceBluetoothType() {
        int i2;
        DeviceInfo a2 = jpt.a("PluginPayAdapterImpl");
        if (a2 != null) {
            i2 = a2.getDeviceBluetoothType();
        } else {
            LogUtil.h("PluginPayAdapterImpl", "getDeviceBTType error,deviceInfo is null!");
            i2 = -1;
        }
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getDeviceBTType, state: ", Integer.valueOf(i2));
        return i2;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public boolean isDeviceBand(int i2) {
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "isDeviceBand deviceType: ", Integer.valueOf(i2));
        return jfu.h(i2);
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public String getServiceCountryCode() {
        String accountInfo;
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit == null || (accountInfo = loginInit.getAccountInfo(1010)) == null || "".equals(accountInfo)) {
            return "CN";
        }
        LogUtil.a("PluginPayAdapterImpl", "isDeviceBand countryCode: ", accountInfo);
        return accountInfo;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public boolean isSendCardHciAndAmountRuler(String str, Map<String, String> map, List<String> list) {
        LogUtil.a("PluginPayAdapterImpl", "sendCardHciAndAmountRuler enter");
        if (q()) {
            LogUtil.a("PluginPayAdapterImpl", "sendCardHciAndAmountRuler fm operation");
            return false;
        }
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl sendCardHciAndAmountRuler sPayManager is null");
            return false;
        }
        u.a(str, map, list, new IBaseResponseCallback() { // from class: ixb.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "isSendCardHciAndAmountRuler onResponse errorCode:", Integer.valueOf(i2));
                if (i2 == 0) {
                    ixb.this.at = true;
                } else {
                    ixb.this.at = false;
                }
                ixb.this.d("sendCardHciAndAmountRuler", ixb.t);
            }
        });
        b("sendCardHciAndAmountRuler", t);
        Object[] objArr = new Object[2];
        objArr[0] = "sendCardHciAndAmountRuler end, result is ";
        objArr[1] = this.at ? "success" : "false";
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", objArr);
        return this.at;
    }

    private boolean q() {
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "checkIsFmRecharge mIsFmRecharge:", Boolean.valueOf(this.ap));
        if (!this.ap) {
            return false;
        }
        if (Math.abs(System.currentTimeMillis() - this.aw) <= 180000) {
            return true;
        }
        this.ap = false;
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "checkIsFmRecharge mIsFmRecharge is false");
        return false;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public String getNfcTagInfo() {
        LogUtil.a("PluginPayAdapterImpl", "getNfcTagInfo enter");
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl getNfcTagInfo sPayManager is null");
            return "";
        }
        u.j(new IBaseResponseCallback() { // from class: ixb.11
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getNfcTagInfo onResponse errorCode:", Integer.valueOf(i2));
                if (obj instanceof String) {
                    ixb.this.ay = (String) obj;
                }
                LogUtil.c("PluginPayAdapterImpl", "unlock getNfcTagInfo");
                ixb.this.d("getNfcTagInfo", ixb.b);
            }
        });
        b("getNfcTagInfo", b);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "lock getNfcTagInfo, result is ", this.ay);
        return this.ay;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public String getNfcCommandResponse(int i2, String str) {
        LogUtil.a("PluginPayAdapterImpl", "getNfcCommandResponse enter");
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl getNfcCommandResponse sPayManager is null");
            return "";
        }
        u.d(i2, str, new IBaseResponseCallback() { // from class: ixb.12
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getNfcCommandResponse onResponse errorCode:", Integer.valueOf(i3));
                if (obj instanceof String) {
                    ixb.this.az = (String) obj;
                }
                LogUtil.c("PluginPayAdapterImpl", "unlock getNfcCommandResponse");
                ixb.this.d("getNfcCommandResponse", ixb.b);
            }
        });
        b("getNfcCommandResponse", b);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "lock getNfcCommandResponse, result is ", this.az);
        return this.az;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public int autheticateMifareSector(byte[] bArr, int i2, int i3) {
        LogUtil.a("PluginPayAdapterImpl", "autheticateMifareSector enter");
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl autheticateMifareSector sPayManager is null");
            return -2;
        }
        this.ab = -1;
        u.b(bArr, i2, i3, new IBaseResponseCallback() { // from class: ixb.14
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i4, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "autheticateMifareSector onResponse errorCode:", Integer.valueOf(i4));
                if (i4 == 100000) {
                    ixb.this.ab = 0;
                } else {
                    ixb.this.ab = i4;
                }
                LogUtil.c("PluginPayAdapterImpl", "unlock autheticateMifareSector");
                ixb.this.d("autheticateMifareSector", ixb.b);
            }
        });
        b("autheticateMifareSector", b);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "lock autheticateMifareSector, result is ", Integer.valueOf(this.ab));
        return this.ab;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public String getBlockData(byte[] bArr, int i2, int i3) {
        LogUtil.a("PluginPayAdapterImpl", "getBlockData enter");
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl getBlockData sPayManager is null");
            return "";
        }
        u.d(bArr, i2, i3, new IBaseResponseCallback() { // from class: ixb.15
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i4, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getBlockData onResponse errorCode:", Integer.valueOf(i4));
                if (obj instanceof String) {
                    ixb.this.ag = (String) obj;
                }
                LogUtil.c("PluginPayAdapterImpl", "unlock getBlockData");
                ixb.this.d("getBlockData", ixb.b);
            }
        });
        b("getBlockData", b);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "lock getBlockData, result is ", this.ag);
        return this.ag;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public int sendAccessCardRfInfo(String str, String str2) {
        LogUtil.a("PluginPayAdapterImpl", "sendAccessCardRfInfo enter");
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl sendAccessCardRfInfo sPayManager is null");
            return -2;
        }
        u.b(str, str2, new IBaseResponseCallback() { // from class: ixb.17
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "sendAccessCardRfInfo onResponse errorCode:", Integer.valueOf(i2));
                ixb.this.ab = i2;
                LogUtil.c("PluginPayAdapterImpl", "unlock sendAccessCardRfInfo");
                ixb.this.d("sendAccessCardRfInfo", ixb.b);
            }
        });
        b("sendAccessCardRfInfo", b);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "lock sendAccessCardRfInfo, result is ", Integer.valueOf(this.ab));
        return this.ab;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public int sendAccessCardReadEvent(int i2) {
        LogUtil.a("PluginPayAdapterImpl", "sendAccessCardReadEvent enter");
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl sendAccessCardReadEvent sPayManager is null");
            return -2;
        }
        u.e(i2, new IBaseResponseCallback() { // from class: ixb.16
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "sendAccessCardReadEvent onResponse errorCode:", Integer.valueOf(i3));
                ixb.this.ab = i3;
                LogUtil.c("PluginPayAdapterImpl", "unlock sendAccessCardReadEvent");
                ixb.this.d("sendAccessCardReadEvent", ixb.b);
            }
        });
        b("sendAccessCardReadEvent", b);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "lock sendAccessCardReadEvent, result is ", Integer.valueOf(this.ab));
        return this.ab;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public String getCardInfo(int i2, int i3) {
        LogUtil.a("PluginPayAdapterImpl", "getCardInfo enter");
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl getCardInfo sPayManager is null");
            return null;
        }
        HwPayManager.a(i2, i3, new IBaseResponseCallback() { // from class: ixb.20
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i4, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getCardInfo onResponse errorCode:", Integer.valueOf(i4));
                if (obj instanceof String) {
                    ixb.this.ai = (String) obj;
                } else {
                    ixb.this.ai = null;
                }
                ixb.this.d("getCardInfo", ixb.l);
            }
        });
        b("getCardInfo", l);
        String str = this.ai;
        if (str != null) {
            ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getCardInfo end, cardInfo: ", str);
        }
        return this.ai;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public String getAccessConfigInfo() {
        LogUtil.a("PluginPayAdapterImpl", "getAccessConfigInfo enter");
        if (u == null || !x()) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl getLowPowerNumber sPayManager is null");
            return this.z;
        }
        u.a(new IBaseResponseCallback() { // from class: ixb.18
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getAccessConfigInfo onResponse errorCode:", Integer.valueOf(i2));
                if (obj instanceof String) {
                    ixb.this.z = (String) obj;
                } else {
                    ixb.this.z = null;
                }
                ixb.this.d("getAccessConfigInfo", ixb.b);
            }
        });
        b("getAccessConfigInfo", b);
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "getAccessConfigInfo: ", this.z);
        return this.z;
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public void registerHealthResponseListener(WalletResponseListener walletResponseListener) {
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "registerHealthResponseListener");
        HwPayManager hwPayManager = u;
        if (hwPayManager == null) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "PluginPayAdapterImpl registerHealthResponseListener sPayManager is null");
        } else {
            hwPayManager.e(walletResponseListener);
        }
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public void unRegisterHealthResponseListener() {
        LogUtil.a("PluginPayAdapterImpl", "unRegisterHealthResponseListener");
        HwPayManager hwPayManager = u;
        if (hwPayManager == null) {
            LogUtil.h("PluginPayAdapterImpl", "PluginPayAdapterImpl unRegisterHealthResponseListener sPayManager is null");
        } else {
            hwPayManager.c();
        }
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public void sendBluetoothCommand(int i2, int i3, ByteBuffer byteBuffer) {
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "sendWalletTlv");
        if (u == null || !x()) {
            ReleaseLogUtil.d("R_PluginPayAdapterImpl", "PluginPayAdapterImpl notifyAfterTransferFile sPayManager is null");
        } else {
            u.e(i2, i3, byteBuffer);
        }
    }

    @Override // com.huawei.nfc.PluginPayAdapter
    public boolean isWalletOpenCard() {
        ReleaseLogUtil.e("R_PluginPayAdapterImpl", "isOpenCardNewWay");
        return jqx.a();
    }
}
