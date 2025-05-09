package defpackage;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.multisimservice.model.EUICCDeviceInfo;
import com.huawei.multisimservice.model.EUICCInfo;
import com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback;
import com.huawei.multisimservice.model.IOpenMultiSimCalbcak;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.multisimservice.model.SimInfo;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ktx extends HwBaseManager implements BluetoothDataReceiveCallback, DataReceiveCallback {
    private static ktx b;
    private List<IBaseResponseCallback> f;
    private BroadcastReceiver g;
    private Handler h;
    private String i;
    private IAttachedDeviceMultiSimCallback j;
    private IOpenMultiSimCalbcak k;
    private jfq l;
    private IBaseResponseCallback m;
    private boolean o;
    private static final Object d = new Object();
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static Map<Integer, List<IBaseResponseCallback>> f14592a = new HashMap(16);
    private static String c = "";

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.h.removeMessages(0);
        this.h.removeMessages(1);
        this.h.removeMessages(2);
        this.h.removeMessages(3);
        this.h.removeMessages(9);
        this.h.removeMessages(10);
        e(1, 100001, (Object) (-2));
        e(2, -1, (Object) (-2));
        e(3, 100001, (Object) (-2));
        e(4, -1, (Object) (-2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        LogUtil.a("HwMultiSimMgr", "startMultiSimAuthPage");
        try {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setAction("com.huawei.health.MULTI_SIM_AUTH");
            intent.setPackage(BaseApplication.getContext().getPackageName());
            BaseApplication.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("HwMultiSimMgr", "startMultiSimAuthPage ActivityNotFoundException");
        }
    }

    private ktx(Context context) {
        super(context);
        this.j = null;
        this.k = null;
        this.i = "";
        this.o = false;
        this.f = new ArrayList(16);
        this.g = new BroadcastReceiver() { // from class: ktx.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null) {
                    LogUtil.h("HwMultiSimMgr", "mConnectStatus intent is null");
                    sqo.o("mConnectStatus intent is null");
                    return;
                }
                String action = intent.getAction();
                LogUtil.a("HwMultiSimMgr", "onReceive deviceStatusReceiver:", action);
                if (action != null && action.equals("com.huawei.bone.action.CONNECTION_STATE_CHANGED")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                    if (parcelableExtra instanceof DeviceInfo) {
                        DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                        if (deviceInfo.getRelationship() != null && "followed_relationship".equals(deviceInfo.getRelationship())) {
                            LogUtil.a("HwMultiSimMgr", "This device does not have the correspond capability.");
                            sqo.o("This device does not have the correspond capability.");
                            return;
                        }
                        int deviceConnectState = deviceInfo.getDeviceConnectState();
                        ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "device Connect state ", Integer.valueOf(deviceConnectState));
                        if (deviceConnectState != 2) {
                            ktx.this.r();
                        } else if (ktx.this.h.hasMessages(4)) {
                            LogUtil.a("HwMultiSimMgr", "mHandler.hasMessages MULTI_SIM_WAIT_QUERY_CONN_TIMEOUT_MSG");
                            ktx.this.h.removeMessages(4);
                            ktx.this.q();
                        }
                        synchronized (ktx.d) {
                            Iterator it = ktx.this.f.iterator();
                            while (it.hasNext()) {
                                ((IBaseResponseCallback) it.next()).d(deviceConnectState, null);
                            }
                        }
                        return;
                    }
                    LogUtil.h("HwMultiSimMgr", "mConnectStatus() deviceInfo is null");
                    sqo.o("mConnectStatus() deviceInfo is null");
                    return;
                }
                LogUtil.h("HwMultiSimMgr", "action is null or unknown");
                sqo.o("action is null or unknown");
            }
        };
        this.h = new Handler(Looper.getMainLooper()) { // from class: ktx.5
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                LogUtil.c("HwMultiSimMgr", "message.what ", Integer.valueOf(message.what));
                switch (message.what) {
                    case 0:
                        ktx.this.e(1, 100001, (Object) null);
                        ktx.this.e(2, -1, (Object) null);
                        sqo.o("message.what = OEPN_SIM_TIMEOUT");
                        break;
                    case 1:
                        ktx.this.e(2, -1, (Object) null);
                        sqo.o("message.what = OEPN_SIM_AUTH_TIMEOUT");
                        break;
                    case 2:
                        ktx.this.e(3, 100001, (Object) null);
                        ktx.this.b(-1);
                        sqo.o("message.what = ESIM_SEND_CODE_TIMEOUT");
                        break;
                    case 3:
                        ktx.this.e(4, -1, (Object) null);
                        sqo.o("message.what = ESIM_SEND_CODE_AUTH_TIMEOUT");
                        break;
                    case 4:
                        ktx.this.q();
                        sqo.o("message.what = MULTI_SIM_WAIT_QUERY_CONN_TIMEOUT_MSG");
                        break;
                    case 5:
                        ktx.this.u();
                        sqo.o("message.what = SIM_INFO_QUERY_TIMEOUT_MSG");
                        break;
                    case 6:
                        ktx.this.e(6, -1, (Object) null);
                        sqo.o("message.what = DEVICE_INFO_QUERY_TIMEOUT_MSG");
                        break;
                    case 7:
                        ktx.this.e(10, 100009, (Object) null);
                        sqo.o("message.what = BATTERY_THRESHOLD_QUERY_TIMEOUT_MSG");
                        break;
                    case 8:
                        ktx.this.y();
                        break;
                    case 9:
                        ktx.this.e(5, -1, (Object) null);
                        sqo.o("message.what = ESIM_OPEN_RET_REPORT_TIMEOUT_MSG");
                        break;
                    case 10:
                        ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "enter delete esim profile time out");
                        ktx.this.e(8, 100009, (Object) null);
                        sqo.o("message.what = DELETE_ESIM_PROFILE_TIMEOUT_MSG");
                        break;
                    default:
                        LogUtil.h("HwMultiSimMgr", "message.what ", Integer.valueOf(message.what));
                        break;
                }
            }
        };
        jfq c2 = jfq.c();
        this.l = c2;
        if (c2 == null) {
            LogUtil.h("HwMultiSimMgr", "mHwDeviceConfigManager is null");
            return;
        }
        c2.e(29, this);
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.g, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
        x();
    }

    public static ktx e() {
        ktx ktxVar;
        synchronized (e) {
            if (b == null) {
                b = new ktx(BaseApplication.getContext());
            }
            ktxVar = b;
        }
        return ktxVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        LogUtil.a("HwMultiSimMgr", "onDestroy()");
        super.onDestroy();
        BaseApplication.getContext().unregisterReceiver(this.g);
        this.l.d(29);
        synchronized (t()) {
            f14592a.clear();
        }
        synchronized (d) {
            this.f.clear();
        }
        synchronized (e) {
            b = null;
        }
        LogUtil.a("HwMultiSimMgr", "onDestroy() complete");
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 29;
    }

    private void x() {
        LogUtil.a("HwMultiSimMgr", "initStorage");
        ktw ktwVar = new ktw();
        ktwVar.d(this);
        String s = s();
        ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "initStorage updateStatus ", s);
        if ("STATUSV1".equals(s)) {
            LogUtil.h("HwMultiSimMgr", "initStorage already update");
            return;
        }
        ktu ktuVar = new ktu();
        List<String> e2 = ktuVar.e(this, h());
        ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "initStorage appList", e2);
        if (e2 != null && e2.size() > 0) {
            ktwVar.d(this, e2, b(), d());
        }
        ktuVar.a(this);
        d("STATUSV1");
    }

    public static String b() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        c = accountInfo;
        if (accountInfo == null) {
            c = "";
        }
        return c;
    }

    private String s() {
        return getSharedPreference("MULTISIM_STORAGE_STATUS_KEY");
    }

    private void d(String str) {
        setSharedPreference("MULTISIM_STORAGE_STATUS_KEY", str, new StorageParams(2));
    }

    private static Map<Integer, List<IBaseResponseCallback>> t() {
        return f14592a;
    }

    private void c(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (t()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = f14592a.get(Integer.valueOf(i));
                if (list == null) {
                    list = new ArrayList<>(16);
                    f14592a.put(Integer.valueOf(i), list);
                }
                list.add(iBaseResponseCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2, Object obj) {
        LogUtil.a("HwMultiSimMgr", "processCallback callback");
        synchronized (t()) {
            List<IBaseResponseCallback> list = f14592a.get(Integer.valueOf(i));
            if (list != null) {
                int i3 = 0;
                while (true) {
                    if (list.size() <= 0) {
                        break;
                    }
                    IBaseResponseCallback iBaseResponseCallback = list.get(i3);
                    if (iBaseResponseCallback != null) {
                        iBaseResponseCallback.d(i2, obj);
                        list.remove(i3);
                        break;
                    } else {
                        list.remove(i3);
                        i3++;
                    }
                }
            }
        }
    }

    public void c(IAttachedDeviceMultiSimCallback iAttachedDeviceMultiSimCallback) {
        LogUtil.a("HwMultiSimMgr", "registerAttachedDeviceMultiSimCallback");
        if (iAttachedDeviceMultiSimCallback == null) {
            LogUtil.h("HwMultiSimMgr", "callback is null.");
            sqo.o("callback is null");
        } else {
            this.j = iAttachedDeviceMultiSimCallback;
        }
    }

    public void b(IAttachedDeviceMultiSimCallback iAttachedDeviceMultiSimCallback) {
        LogUtil.a("HwMultiSimMgr", "unregisterAttachedDeviceMultiSimCallback");
        if (iAttachedDeviceMultiSimCallback != this.j) {
            LogUtil.h("HwMultiSimMgr", "IAttachedDeviceMultiSimCallback not equal");
        }
        this.j = null;
    }

    public void a(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) {
        LogUtil.a("HwMultiSimMgr", "registerOpenMultiSimCalbcak");
        if (iOpenMultiSimCalbcak == null) {
            LogUtil.h("HwMultiSimMgr", "callback is null.");
            sqo.o("callback is null.");
        } else {
            this.k = iOpenMultiSimCalbcak;
        }
    }

    public void d(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) {
        LogUtil.a("HwMultiSimMgr", "unregisterOpenMultiSimCallback");
        if (iOpenMultiSimCalbcak != this.k) {
            LogUtil.h("HwMultiSimMgr", "mOpenMultiSimCallback not equal");
        }
        this.k = null;
    }

    private void b(byte[] bArr, int i) {
        LogUtil.a("HwMultiSimMgr", "processSetCmdResult Complete commandId", Integer.valueOf(i));
        int i2 = 201000;
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                i2 = kty.b(bArr);
                LogUtil.a("HwMultiSimMgr", "processSetCmdResult return errorCode:", Integer.valueOf(i2));
            }
        } catch (Exception e2) {
            LogUtil.b("HwMultiSimMgr", "processSetCmdResult Exception");
            sqo.o("processSetCmdResult Exception " + e2.getMessage());
        }
        LogUtil.a("HwMultiSimMgr", "processSetCmdResult return errorCode:", Integer.valueOf(i2));
    }

    private void c(byte[] bArr) {
        if (this.h.hasMessages(10)) {
            LogUtil.a("HwMultiSimMgr", "enter remove message");
            this.h.removeMessages(10);
        }
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                int b2 = kty.b(bArr);
                ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "processDeleteEimProfileCmdResult return errorCode:", Integer.valueOf(b2));
                e(8, b2, (Object) null);
            } else {
                e(8, 100001, (Object) null);
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("DEVMGR_HwMultiSimMgr", "processOpenEsim Exception");
            sqo.o("processOpenEsim Exception : " + e2.getMessage());
            e(8, 100001, (Object) null);
        }
    }

    private void i(byte[] bArr) {
        LogUtil.a("HwMultiSimMgr", "processSimInfoQuery");
        MultiSimDeviceInfo multiSimDeviceInfo = new MultiSimDeviceInfo();
        char c2 = 34470;
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                LogUtil.a("HwMultiSimMgr", "processSimInfoQuery return errorCode:", Integer.valueOf(kty.b(bArr)));
            } else {
                multiSimDeviceInfo = kty.j(bArr);
                LogUtil.a("HwMultiSimMgr", "processSimInfoQuery simInfo");
                c2 = 34464;
            }
        } catch (Exception e2) {
            LogUtil.b("HwMultiSimMgr", "processSimInfoQuery Exception");
            sqo.o("processSimInfoQuery Exception : " + e2.getMessage());
        }
        if (multiSimDeviceInfo == null) {
            LogUtil.h("HwMultiSimMgr", "processSimInfoQuery multiSimDeviceInfo is null");
            return;
        }
        if (c2 == 34464) {
            multiSimDeviceInfo.setResultCode(1);
        } else {
            multiSimDeviceInfo.setResultCode(0);
        }
        if (kty.a()) {
            e(6, 1, multiSimDeviceInfo);
            LogUtil.h("HwMultiSimMgr", "processSimInfoQuery isPowerDownStatus need retry");
        } else {
            e(6, 0, multiSimDeviceInfo);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void f(byte[] r7) {
        /*
            r6 = this;
            java.lang.String r0 = "procRemoveProfileRet errorCode:"
            java.lang.String r1 = "procRemoveProfileRet"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "HwMultiSimMgr"
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            r1 = 1
            r3 = 2
            r4 = r7[r3]     // Catch: java.lang.Exception -> L30
            r5 = 127(0x7f, float:1.78E-43)
            if (r4 != r5) goto L28
            int r7 = defpackage.kty.b(r7)     // Catch: java.lang.Exception -> L30
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L30
            r4 = 0
            r3[r4] = r0     // Catch: java.lang.Exception -> L30
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Exception -> L30
            r3[r1] = r7     // Catch: java.lang.Exception -> L30
            com.huawei.hwlogsmodel.LogUtil.a(r2, r3)     // Catch: java.lang.Exception -> L30
            goto L4f
        L28:
            int r1 = defpackage.kty.a(r7)     // Catch: java.lang.Exception -> L30
            r7 = 100000(0x186a0, float:1.4013E-40)
            goto L52
        L30:
            r7 = move-exception
            java.lang.String r3 = "processRemoveReportRet Exception"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "processRemoveReportRet Exception : "
            r3.<init>(r4)
            java.lang.String r7 = r7.getMessage()
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            defpackage.sqo.o(r7)
        L4f:
            r7 = 100006(0x186a6, float:1.40138E-40)
        L52:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            java.lang.String r4 = "defaultProfileResult:"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r3, r4, r5}
            java.lang.String r3 = "DEVMGR_HwMultiSimMgr"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r3, r0)
            r0 = 9
            defpackage.kua.b(r0, r7)
            com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback r7 = r6.j
            if (r7 == 0) goto L91
            r7.deleteESimProfileNotify(r1)     // Catch: android.os.RemoteException -> L72
            goto L91
        L72:
            r7 = move-exception
            java.lang.String r0 = "processRemoveReportRet exception"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "processRemoveReportRet exception : "
            r0.<init>(r1)
            java.lang.String r7 = r7.getMessage()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            defpackage.sqo.o(r7)
        L91:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ktx.f(byte[]):void");
    }

    private void b(byte[] bArr) {
        int i;
        LogUtil.a("HwMultiSimMgr", "processBatteryThresholdQuery");
        try {
            i = bArr[2] == Byte.MAX_VALUE ? kty.b(bArr) : 201000;
            LogUtil.a("HwMultiSimMgr", "processBatteryThresholdQuery return errorCode:", Integer.valueOf(i));
        } catch (Exception e2) {
            LogUtil.b("HwMultiSimMgr", "processBatteryThresholdQuery Exception");
            sqo.o("processBatteryThresholdQuery Exception : " + e2.getMessage());
            i = 100006;
        }
        if (i == 129001 || i == 0) {
            e(10, i, Integer.valueOf(kty.c(bArr)));
        } else {
            e(10, i, (Object) null);
        }
    }

    public void a(String str) {
        LogUtil.a("HwMultiSimMgr", "downloadEsimProfile save acCode: ", str);
        if (m() && !Utils.o()) {
            d(str, String.valueOf(System.currentTimeMillis()));
        }
        d(str, 1, new IBaseResponseCallback() { // from class: ktx.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "downloadEsimProfile first errorCode = ", Integer.valueOf(i), " objectData:", obj);
                if (ktx.this.j != null) {
                    try {
                        ktx.this.j.downloadESimProfile(i, null);
                    } catch (RemoteException e2) {
                        LogUtil.b("HwMultiSimMgr", "downloadEsimProfile exception");
                        sqo.o("downloadEsimProfile exception : " + e2.getMessage());
                    }
                }
            }
        }, new IBaseResponseCallback() { // from class: ktx.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "downloadEsimProfile second errorCode = ", Integer.valueOf(i));
                ktx.this.c(i);
                if (i == 0) {
                    ktx.this.a(true);
                    if (!ktx.this.m() || Utils.o()) {
                        return;
                    }
                    ktx.this.d("", "0");
                    return;
                }
                ktx.this.a(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        linkedHashMap.put("status", String.valueOf(i));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_ESIM_MANAGER_60010003.value(), linkedHashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(MultiSimDeviceInfo multiSimDeviceInfo) {
        if (multiSimDeviceInfo == null) {
            LogUtil.h("HwMultiSimMgr", "inpurt deviceInfo is null");
            return;
        }
        try {
            IAttachedDeviceMultiSimCallback iAttachedDeviceMultiSimCallback = this.j;
            if (iAttachedDeviceMultiSimCallback != null) {
                iAttachedDeviceMultiSimCallback.getAttachedDeviceMultiSimInfo(multiSimDeviceInfo);
            } else if (this.k != null) {
                if (!this.i.equals("com.sinovatech.unicom.ui") && !this.i.equals("com.cmic.heduohao")) {
                    this.k.getDeviceMultiSimInfo(multiSimDeviceInfo);
                }
                a(d(multiSimDeviceInfo), multiSimDeviceInfo);
            } else {
                LogUtil.h("HwMultiSimMgr", "callback is null");
            }
        } catch (RemoteException unused) {
            ReleaseLogUtil.c("DEVMGR_HwMultiSimMgr", "reportDeviceInfo exception");
        }
    }

    private void a(final EUICCDeviceInfo eUICCDeviceInfo, final MultiSimDeviceInfo multiSimDeviceInfo) {
        lpy.e(eUICCDeviceInfo, new ResultCallback<String>() { // from class: ktx.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                if (TextUtils.isEmpty(str)) {
                    LogUtil.a("HwMultiSimMgr", "resp is empty.");
                    sqo.o("resp is empty.");
                    ktx.this.d(eUICCDeviceInfo, multiSimDeviceInfo);
                    return;
                }
                LogUtil.a("HwMultiSimMgr", "resp = " + str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i = jSONObject.getInt("resultCode");
                    ReleaseLogUtil.c("DEVMGR_HwMultiSimMgr", "resultCode = ", Integer.valueOf(i));
                    if (i == 0) {
                        JSONObject jSONObject2 = new JSONObject(jSONObject.getString("encESimDeviceInfo"));
                        if (!ktx.this.i.equals("com.sinovatech.unicom.ui")) {
                            if (ktx.this.i.equals("com.cmic.heduohao")) {
                                multiSimDeviceInfo.setDeviceIMEI(jSONObject2.optString("deviceImei"));
                                multiSimDeviceInfo.setEID(jSONObject2.optString("eid"));
                                multiSimDeviceInfo.setApiVersion(jSONObject2.optString("apiVersion"));
                                multiSimDeviceInfo.setTechVersion(jSONObject2.optString("techVersion"));
                                multiSimDeviceInfo.setSign(jSONObject2.optString("ciphertextSign"));
                                multiSimDeviceInfo.setTime(jSONObject2.optLong("timestamp"));
                            }
                        } else {
                            String optString = jSONObject2.optString("ciphertext");
                            String optString2 = jSONObject2.optString("ciphertextSign");
                            eUICCDeviceInfo.setmCiphertext(optString);
                            eUICCDeviceInfo.setmCiphertextSign(optString2);
                        }
                    }
                    ktx.this.d(eUICCDeviceInfo, multiSimDeviceInfo);
                } catch (JSONException e2) {
                    ReleaseLogUtil.c("DEVMGR_HwMultiSimMgr", "getEsimDeviceInfoPost,JSONException", ExceptionUtils.d(e2));
                    ktx.this.d(eUICCDeviceInfo, multiSimDeviceInfo);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HwMultiSimMgr", " getESimDeviceInfoPost :", ExceptionUtils.d(th));
                sqo.o("getESimDeviceInfoPost fail:" + ExceptionUtils.d(th));
                ktx.this.d(eUICCDeviceInfo, multiSimDeviceInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(EUICCDeviceInfo eUICCDeviceInfo, MultiSimDeviceInfo multiSimDeviceInfo) {
        if (this.k == null) {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "mOpenMultiSimCallback is null.");
            sqo.o("mOpenMultiSimCallback is null.");
            return;
        }
        try {
            if (this.i.equals("com.sinovatech.unicom.ui")) {
                this.k.getDeviceEUICCInfo(eUICCDeviceInfo);
            } else if (this.i.equals("com.cmic.heduohao")) {
                this.k.getDeviceMultiSimInfo(multiSimDeviceInfo);
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("DEVMGR_HwMultiSimMgr", "getEsimDeviceInfoPost exception");
            sqo.o("getEsimDeviceInfoPost exception." + e2.getMessage());
        }
    }

    public void a(String str, long j, String str2) {
        lpx e2 = e(str, j, str2);
        LogUtil.a("HwMultiSimMgr", "euiccDownloadSimProfile = ", e2);
        lpy.e(e2, new ResultCallback<String>() { // from class: ktx.8
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str3) {
                if (TextUtils.isEmpty(str3)) {
                    LogUtil.a("HwMultiSimMgr", "onSuccess:", "respProfileInfo is empty.");
                    sqo.o("respProfileInfo is empty.");
                    return;
                }
                LogUtil.a("HwMultiSimMgr", "onSuccess:", "respProfileInfo = " + str3);
                try {
                    JSONObject jSONObject = new JSONObject(str3);
                    int i = jSONObject.getInt("resultCode");
                    ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "resultCode= ", Integer.valueOf(i));
                    if (i == 0) {
                        ktx.this.a(new JSONObject(jSONObject.getString("eSimProfileInfo")).getString("decActivationCode"));
                    }
                } catch (JSONException e3) {
                    LogUtil.b("HwMultiSimMgr", LogAnonymous.b((Throwable) e3));
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("HwMultiSimMgr", " getESimProfilePost :", ExceptionUtils.d(th));
                sqo.o("getESimProfilePost fail." + ExceptionUtils.d(th));
            }
        });
    }

    private lpx e(String str, long j, String str2) {
        lpx lpxVar = new lpx();
        if (this.i.equals("com.sinovatech.unicom.ui")) {
            lpxVar.a(2);
            lpxVar.a(System.currentTimeMillis());
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("mCiphertext");
                String optString2 = jSONObject.optString("mCiphertextSign");
                lpxVar.a(optString);
                lpxVar.e(optString2);
            } catch (JSONException e2) {
                ReleaseLogUtil.c("DEVMGR_HwMultiSimMgr", "getEncryptEsimProfileInfo exception:", LogAnonymous.b((Throwable) e2));
            }
        } else if (this.i.equals("com.cmic.heduohao")) {
            lpxVar.a(1);
            lpxVar.b(str);
            lpxVar.a(j);
            lpxVar.e(str2);
        }
        return lpxVar;
    }

    private EUICCDeviceInfo d(MultiSimDeviceInfo multiSimDeviceInfo) {
        EUICCDeviceInfo eUICCDeviceInfo = new EUICCDeviceInfo();
        if (this.i.equals("com.sinovatech.unicom.ui")) {
            eUICCDeviceInfo.setCarrierType(2);
            eUICCDeviceInfo.setmOsVersion(v());
            eUICCDeviceInfo.setmAidlVersion("1.0");
        } else if (this.i.equals("com.cmic.heduohao")) {
            eUICCDeviceInfo.setCarrierType(1);
            eUICCDeviceInfo.setmOsVersion("12");
            eUICCDeviceInfo.setmAidlVersion("107");
        }
        eUICCDeviceInfo.setResultCode(multiSimDeviceInfo.getResultCode());
        eUICCDeviceInfo.setDeviceType(multiSimDeviceInfo.getDeviceType());
        eUICCDeviceInfo.setDeviceIMEI(multiSimDeviceInfo.getDeviceIMEI());
        eUICCDeviceInfo.setDeviceSerialNumber(multiSimDeviceInfo.getDeviceSerialNumber());
        eUICCDeviceInfo.setProductName(multiSimDeviceInfo.getProductName());
        eUICCDeviceInfo.setEID(multiSimDeviceInfo.getEID());
        eUICCDeviceInfo.setTimeTemp(System.currentTimeMillis());
        List<EUICCInfo> e2 = e(multiSimDeviceInfo.getSimInfoList());
        if (!e2.isEmpty()) {
            eUICCDeviceInfo.setIMSI(e2.get(0).getIMSI());
            eUICCDeviceInfo.setICCID(e2.get(0).getICCID());
        }
        LogUtil.a("HwMultiSimMgr", "euiccDeviceInfo = ", eUICCDeviceInfo);
        return eUICCDeviceInfo;
    }

    private String v() {
        boolean c2 = cwi.c(jpt.a("HwMultiSimMgr"), a.M);
        ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "isSupportBindCard = ", Boolean.valueOf(c2));
        return c2 ? "10" : "0";
    }

    private List<EUICCInfo> e(List<SimInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null || list.isEmpty()) {
            LogUtil.a("HwMultiSimMgr", "simInfoList is empty");
            return arrayList;
        }
        for (SimInfo simInfo : list) {
            EUICCInfo eUICCInfo = new EUICCInfo();
            eUICCInfo.setIMSI(simInfo.getIMSI());
            eUICCInfo.setICCID(simInfo.getICCID());
            arrayList.add(eUICCInfo);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        MultiSimDeviceInfo multiSimDeviceInfo = new MultiSimDeviceInfo();
        multiSimDeviceInfo.setResultCode(i);
        c(multiSimDeviceInfo);
    }

    public void l() {
        LogUtil.a("HwMultiSimMgr", "simInfoQuery");
        if (!g()) {
            this.h.sendEmptyMessageDelayed(4, 3000L);
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "simInfoQuery device not connect wait");
            sqo.o("simInfoQuery device not connect wait.");
            return;
        }
        q();
    }

    public void q() {
        LogUtil.a("HwMultiSimMgr", "simInfoQueryFromDev");
        if (!g()) {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "simInfoQuery device not connect report not connect");
            a(-2);
            return;
        }
        if (!k()) {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "simInfoQuery device not support multisim");
            a(-3);
            return;
        }
        if (TextUtils.isEmpty(this.i)) {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "mCurCallingApp is empty");
            a(0);
        } else if (!this.i.equals("com.huawei.hwmultisim") && !c(this.i)) {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "simInfoQueryFromDev app not auth");
            this.h.sendEmptyMessage(8);
        } else {
            this.h.sendEmptyMessageDelayed(5, OpAnalyticsConstants.H5_LOADING_DELAY);
            j(new IBaseResponseCallback() { // from class: ktx.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (ktx.this.h.hasMessages(5)) {
                        ktx.this.h.removeMessages(5);
                    }
                    if (i != 0) {
                        ktx.this.a(0);
                    } else if (obj == null || !(obj instanceof MultiSimDeviceInfo)) {
                        ktx.this.a(0);
                    } else {
                        ktx.this.c((MultiSimDeviceInfo) obj);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (!g()) {
            ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "simInfoQueryTimeout device not connect report not connect");
            a(-2);
        } else {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "simInfoQueryTimeout device timeout unknown error");
            a(0);
        }
    }

    private void g(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMultiSimMgr", "getLocalDeviceInfo");
        j(new IBaseResponseCallback() { // from class: ktx.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "getLocalDeviceInfo onResponse errCode", Integer.valueOf(i));
                if (ktx.this.h.hasMessages(6)) {
                    ktx.this.h.removeMessages(6);
                }
                iBaseResponseCallback.d(i, obj);
            }
        });
    }

    private void i(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMultiSimMgr", "getLocalBatteryThreshold");
        f(new IBaseResponseCallback() { // from class: ktx.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "getLocalBatteryThreshold onResponse errCode", Integer.valueOf(i));
                iBaseResponseCallback.d(i, obj);
                if (ktx.this.h.hasMessages(7)) {
                    ktx.this.h.removeMessages(7);
                }
            }
        });
    }

    private void j(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HwMultiSimMgr", "sendSimInfoQueryCmd");
        this.h.sendEmptyMessageDelayed(6, OpAnalyticsConstants.H5_LOADING_DELAY);
        c(6, iBaseResponseCallback);
        kua.c();
    }

    private void f(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMultiSimMgr", "sendgetBatteryThresholdQueryCmd");
        this.h.sendEmptyMessageDelayed(7, OpAnalyticsConstants.H5_LOADING_DELAY);
        c(10, iBaseResponseCallback);
        kua.a();
    }

    public void b(List<SimInfo> list) {
        LogUtil.a("HwMultiSimMgr", "removeEsimProfile");
        kua.d(list);
    }

    public void a(List<SimInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMultiSimMgr", "removeEsimProfile");
        this.h.sendEmptyMessageDelayed(10, OpAnalyticsConstants.H5_LOADING_DELAY);
        c(8, iBaseResponseCallback);
        kua.d(list);
    }

    public void c(int i, String str) {
        LogUtil.a("HwMultiSimMgr", "simStatusNotify");
        kua.b(i, str);
    }

    public boolean c(String str) {
        LogUtil.a("HwMultiSimMgr", "getAppAuthStatus :", str);
        return new ktw().d(this, str, b(), d());
    }

    public void e(boolean z) {
        LogUtil.a("HwMultiSimMgr", "setAppAuthStatus");
        ktw ktwVar = new ktw();
        if (z) {
            ktwVar.a(this, this.i, b(), d());
            l();
        } else {
            a(-1);
        }
    }

    public void e(String str) {
        this.i = str;
    }

    public String i() {
        return this.i;
    }

    public boolean j() {
        return this.o;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public boolean g() {
        boolean z;
        DeviceInfo a2 = jpt.a("HwMultiSimMgr");
        LogUtil.c("HwMultiSimMgr", "isDeviceConnect");
        if (a2 == null || a2.getDeviceConnectState() != 2) {
            z = false;
        } else {
            LogUtil.a("HwMultiSimMgr", "getAttachedDeviceMultiSimInfo deviceInfo is connect");
            z = true;
        }
        ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "isDeviceConnect : ", Boolean.valueOf(z));
        return z;
    }

    public boolean o() {
        DeviceCapability d2 = cvs.d();
        if (d2 == null) {
            LogUtil.h("HwMultiSimMgr", "isSupportEsim deviceCapability is null");
            return false;
        }
        if (!d2.isSupportEsim()) {
            return false;
        }
        LogUtil.a("HwMultiSimMgr", "device support esim");
        return true;
    }

    public boolean m() {
        DeviceCapability d2 = cvs.d();
        if (d2 == null) {
            LogUtil.h("HwMultiSimMgr", "isSupportNewEsim deviceCapability is null");
            return false;
        }
        if (!d2.isSupportNewEsim()) {
            return false;
        }
        LogUtil.a("HwMultiSimMgr", "device support esim new ui");
        return true;
    }

    public boolean k() {
        DeviceCapability d2 = cvs.d();
        if (d2 == null) {
            LogUtil.h("HwMultiSimMgr", "isSupportMultiSim deviceCapability is null");
            return false;
        }
        if (!d2.isSupportMultiSim()) {
            return false;
        }
        LogUtil.a("HwMultiSimMgr", "device support multiSim");
        return true;
    }

    public String h() {
        DeviceInfo a2 = jpt.a("HwMultiSimMgr");
        return a2 != null ? a2.getSecurityDeviceId() : "";
    }

    public String d() {
        DeviceInfo a2 = jpt.a("HwMultiSimMgr");
        return a2 != null ? a2.getUuid() : "";
    }

    public void e(String str, IBaseResponseCallback iBaseResponseCallback, IBaseResponseCallback iBaseResponseCallback2) {
        LogUtil.c("HwMultiSimMgr", "Now it is openEsimWithoutConfirm Esim", str);
        c(1, iBaseResponseCallback);
        c(5, iBaseResponseCallback2);
        kua.e(str, 1);
        this.h.sendEmptyMessageDelayed(0, 45000L);
    }

    public void a(String str, IBaseResponseCallback iBaseResponseCallback, IBaseResponseCallback iBaseResponseCallback2) {
        LogUtil.c("HwMultiSimMgr", "Now it is openning Esim", str);
        c(1, iBaseResponseCallback);
        c(2, iBaseResponseCallback2);
        kua.e(str, 0);
        this.h.sendEmptyMessageDelayed(0, 45000L);
    }

    public void d(String str, int i, IBaseResponseCallback iBaseResponseCallback, IBaseResponseCallback iBaseResponseCallback2) {
        LogUtil.c("HwMultiSimMgr", "Now it is openning Esim", str);
        c(1, iBaseResponseCallback);
        if (m()) {
            c(5, iBaseResponseCallback2);
        } else {
            c(2, iBaseResponseCallback2);
        }
        kua.e(str, i);
        this.h.sendEmptyMessageDelayed(0, 45000L);
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (d) {
            if (iBaseResponseCallback != null) {
                this.f.add(iBaseResponseCallback);
            }
        }
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (t()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = f14592a.get(2);
                if (list != null) {
                    list.remove(iBaseResponseCallback);
                }
            }
        }
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (d) {
            if (iBaseResponseCallback != null) {
                this.f.remove(iBaseResponseCallback);
            }
        }
    }

    private void j(byte[] bArr) {
        LogUtil.a("HwMultiSimMgr", "processMateDataAuth");
        this.h.removeMessages(1);
        try {
            jdf d2 = kty.d(bArr);
            LogUtil.c("HwMultiSimMgr", "processMateDataAuth :", Integer.valueOf(d2.b()));
            if (d2.b() == 0) {
                e(2, 0, d2);
                kua.b(2, true);
            } else {
                e(2, d2.b(), (Object) null);
                if (d2.b() == -1) {
                    kua.b(2, false);
                } else {
                    kua.b(2, true);
                }
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("DEVMGR_HwMultiSimMgr", "processMateDataAuth Exception");
            sqo.o("processMateDataAuth Exception : " + e2.getMessage());
            e(2, -1, (Object) null);
            kua.b(2, false);
        }
    }

    private void h(byte[] bArr) {
        LogUtil.a("HwMultiSimMgr", "processOpenEsim");
        this.h.removeMessages(0);
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                int b2 = kty.b(bArr);
                ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "processOpenEsim return errorCode:", Integer.valueOf(b2));
                e(1, b2, (Object) null);
                if (b2 == 0) {
                    this.h.sendEmptyMessageDelayed(1, 150000L);
                } else if (b2 == 100005) {
                    e(2, 1000, (Object) null);
                } else {
                    e(2, -1, (Object) null);
                }
            } else {
                e(1, 100001, (Object) null);
                e(2, -1, (Object) null);
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("DEVMGR_HwMultiSimMgr", "processOpenEsim Exception");
            sqo.o("processOpenEsim Exception : " + e2.getMessage());
            e(1, 100001, (Object) null);
            e(2, -1, (Object) null);
        }
    }

    private void a(byte[] bArr) {
        LogUtil.a("HwMultiSimMgr", "processConformCode");
        this.h.removeMessages(2);
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                int b2 = kty.b(bArr);
                ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "procRemoveProfileRet return errorCode:", Integer.valueOf(b2));
                e(3, b2, (Object) null);
                if (b2 == 0) {
                    w();
                } else if (b2 == 100005) {
                    b(1000);
                } else {
                    b(-1);
                }
            } else {
                e(3, 100001, (Object) null);
                b(-1);
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("DEVMGR_HwMultiSimMgr", "processConformCode Exception");
            sqo.o("processConformCode Exception : " + e2.getMessage());
            e(3, 100001, (Object) null);
            b(-1);
        }
    }

    private void w() {
        if (m()) {
            this.h.sendEmptyMessageDelayed(9, 150000L);
        } else {
            this.h.sendEmptyMessageDelayed(3, 150000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (m()) {
            e(5, i, (Object) null);
        } else {
            e(4, i, (Object) null);
        }
    }

    private void d(byte[] bArr) {
        LogUtil.a("HwMultiSimMgr", "processConformCodeAuth");
        this.h.removeMessages(3);
        try {
            int e2 = kty.e(bArr);
            ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "procRemoveProfileRet return errorCode:", Integer.valueOf(e2));
            e(4, e2, (Object) null);
            if (e2 == -1) {
                kua.b(4, false);
            } else {
                kua.b(4, true);
            }
        } catch (Exception e3) {
            ReleaseLogUtil.c("DEVMGR_HwMultiSimMgr", "Exception");
            sqo.o("getConformCodeAuthRet Exception : " + e3.getMessage());
            e(4, -1, (Object) null);
            kua.b(4, false);
        }
    }

    private void e(byte[] bArr) {
        LogUtil.a("HwMultiSimMgr", "processEsimOpenRetReport");
        this.h.removeMessages(9);
        try {
            int e2 = kty.e(bArr);
            e(5, e2, (Object) null);
            ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "processEsimOpenRetReport return result:", Integer.valueOf(e2));
        } catch (Exception e3) {
            e(5, -1, (Object) null);
            ReleaseLogUtil.c("DEVMGR_HwMultiSimMgr", "Exception");
            sqo.o("processEsimOpenRetReport Exception : " + e3.getMessage());
        }
    }

    public int c() {
        DeviceInfo a2 = jpt.a("HwMultiSimMgr");
        if (a2 == null) {
            LogUtil.h("HwMultiSimMgr", "syncFitnessTodayData get device info error");
            return 3;
        }
        return a2.getDeviceConnectState();
    }

    public void e(String str, int i, IBaseResponseCallback iBaseResponseCallback, IBaseResponseCallback iBaseResponseCallback2) {
        c(3, iBaseResponseCallback);
        if (m()) {
            c(5, iBaseResponseCallback2);
        } else {
            c(4, iBaseResponseCallback2);
        }
        kua.c(str, i);
        this.h.sendEmptyMessageDelayed(2, 45000L);
    }

    public String f() {
        DeviceInfo a2 = jpt.a("HwMultiSimMgr");
        return (a2 == null || a2.getDeviceConnectState() != 2) ? "" : a2.getDeviceName();
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMultiSimMgr", "enter getMultiSimDevicInfo()");
        g(iBaseResponseCallback);
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwMultiSimMgr", "enter getMultiSimBatteryThreshold()");
        i(iBaseResponseCallback);
    }

    public void a(IBaseResponseCallback iBaseResponseCallback, IBaseResponseCallback iBaseResponseCallback2) {
        synchronized (t()) {
            List<IBaseResponseCallback> list = f14592a.get(3);
            if (list != null && list.contains(iBaseResponseCallback)) {
                list.remove(iBaseResponseCallback);
            }
            f14592a.put(3, list);
            List<IBaseResponseCallback> list2 = f14592a.get(4);
            if (list2 != null && list2.contains(iBaseResponseCallback2)) {
                list2.remove(iBaseResponseCallback2);
            }
            f14592a.put(4, list2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2) {
        SharedPreferenceManager.e(BaseApplication.getContext(), "share_preference_esim_accode", "KEY_ACCODE", str, (StorageParams) null);
        SharedPreferenceManager.e(BaseApplication.getContext(), "share_preference_esim_accode", "KEY_ACCODE_SAVE_TIME", str2, (StorageParams) null);
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (i != 0 || bArr == null) {
            return;
        }
        LogUtil.a("HwMultiSimMgr", "onResponse recv bt dataInfos");
        byte b2 = bArr[1];
        switch (b2) {
            case 1:
                h(bArr);
                break;
            case 2:
                j(bArr);
                break;
            case 3:
                a(bArr);
                break;
            case 4:
                d(bArr);
                break;
            case 5:
                e(bArr);
                break;
            case 6:
                i(bArr);
                break;
            case 7:
                b(bArr, 7);
                break;
            case 8:
                c(bArr);
                break;
            case 9:
                f(bArr);
                break;
            case 10:
                b(bArr);
                break;
            default:
                LogUtil.h("HwMultiSimMgr", "unknow command sUserId:", Byte.valueOf(b2));
                break;
        }
    }

    public void n() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.esimcardtype", this);
    }

    public void p() {
        cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.esimcardtype");
    }

    public void c(DeviceInfo deviceInfo, IBaseResponseCallback iBaseResponseCallback) {
        if (deviceInfo == null || iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "sendGetCardTypeCommand deviceInfo or callback is null.");
        } else {
            this.m = iBaseResponseCallback;
            cuk.b().sendSampleConfigCommand(deviceInfo, a(2, 0), "HwMultiSimMgr");
        }
    }

    public void a(DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null) {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "sendGetCardTypeCommand deviceInfo is null.");
        } else {
            cuk.b().sendSampleConfigCommand(deviceInfo, a(1, i), "HwMultiSimMgr");
        }
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "onDataReceived");
        if (deviceInfo == null || cvrVar == null) {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "onDataReceived deviceInfo or message is null.");
            return;
        }
        if (cvrVar instanceof cvq) {
            int a2 = a((cvq) cvrVar);
            ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "onDataReceived mResponseCallback = ", this.m);
            IBaseResponseCallback iBaseResponseCallback = this.m;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(i, Integer.valueOf(a2));
                return;
            }
            return;
        }
        ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "onDataReceived message is error.");
    }

    private int a(cvq cvqVar) {
        if (cvqVar == null) {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "getResultData sampleConfig is null.");
            return 0;
        }
        List<cvn> configInfoList = cvqVar.getConfigInfoList();
        if (koq.b(configInfoList)) {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "getResultData sampleConfigList is empty");
            return 0;
        }
        cvn cvnVar = configInfoList.get(0);
        String d2 = cvnVar != null ? cvx.d(cvnVar.b()) : "";
        ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "getResultData configData = ", d2);
        if (TextUtils.isEmpty(d2)) {
            ReleaseLogUtil.d("DEVMGR_HwMultiSimMgr", "getResultData configData is null.");
            return 0;
        }
        return CommonUtil.w(cvx.e(d2));
    }

    private cvq a(int i, int i2) {
        ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "constructSampleConfig configAction = ", Integer.valueOf(i));
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.unitedevice.esimcardtype");
        cvqVar.setWearPkgName("hw.watch.health.esimcardtype");
        cvqVar.setCommandId(1);
        ArrayList arrayList = new ArrayList(5);
        cvn cvnVar = new cvn();
        cvnVar.e(900100016L);
        cvnVar.d(i);
        if (i == 1) {
            String e2 = cvx.e(i2);
            ReleaseLogUtil.e("DEVMGR_HwMultiSimMgr", "constructSampleConfig convertedCardType = ", e2);
            cvnVar.c(cvx.a(e2));
        }
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }
}
