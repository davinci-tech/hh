package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.ecgservice.EcgIvActivationRequest;
import com.huawei.hwcloudmodel.model.ecgservice.EcgServiceActivationData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jgc {

    /* renamed from: a, reason: collision with root package name */
    private static jgc f13804a;
    private static final Object c = new Object();
    private static final Object d = new Object();
    private String f;
    private DeviceInfo g;
    private String j;
    private boolean i = false;
    private List<IBaseResponseCallback> e = new ArrayList(16);
    private final BroadcastReceiver h = new BroadcastReceiver() { // from class: jgc.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("EcgServiceIVUtil", "mGetIvReceiver(): intent is ", intent.getAction());
            if (context == null) {
                LogUtil.h("EcgServiceIVUtil", "mGetIvReceiver context is null.");
                return;
            }
            if ("com.huawei.health.action.ecgiv.received".equals(intent.getAction())) {
                LogUtil.a("EcgServiceIVUtil", "handle GetIv BroadcastReceiver.");
                String stringExtra = intent.getStringExtra("dataIv");
                if (!TextUtils.isEmpty(stringExtra)) {
                    try {
                        JSONObject jSONObject = new JSONObject(stringExtra);
                        jgc.this.j = jSONObject.optString("ecgServiceIv");
                        jgc.this.f = jSONObject.optString("ecgServiceIvPsd");
                        LogUtil.a("EcgServiceIVUtil", "getEcgServiceIv receive ok.");
                    } catch (JSONException unused) {
                        LogUtil.b("EcgServiceIVUtil", "setCommandIdEcgServiceIv has JSONException");
                    }
                }
                LogUtil.c("EcgServiceIVUtil", "getEcgServiceIv end, mEcgIv:", jgc.this.j, ", mEcgGenerate:", jgc.this.f, ", mDeviceInfoSn:", jgc.this.g.getSecurityDeviceId());
                synchronized (jgc.d) {
                    if (!jgc.this.e.isEmpty()) {
                        jgc.this.c();
                    }
                }
            }
            jgc.this.h();
        }
    };
    private Context b = BaseApplication.getContext();

    private jgc() {
    }

    public static jgc a() {
        jgc jgcVar;
        synchronized (c) {
            if (f13804a == null) {
                f13804a = new jgc();
            }
            jgcVar = f13804a;
        }
        return jgcVar;
    }

    public void c(DeviceInfo deviceInfo) {
        LogUtil.a("EcgServiceIVUtil", "start justGetEcgIv");
        if (e(deviceInfo)) {
            a(deviceInfo);
        }
    }

    public void e(IBaseResponseCallback iBaseResponseCallback, DeviceInfo deviceInfo) {
        LogUtil.a("EcgServiceIVUtil", "start getEcgIv");
        if (deviceInfo == null) {
            iBaseResponseCallback.d(-1, "deviceInfo is null");
            LogUtil.h("EcgServiceIVUtil", "getEcgIv deviceInfo is null");
            return;
        }
        synchronized (d) {
            this.e.add(iBaseResponseCallback);
        }
        if (e(deviceInfo)) {
            a(deviceInfo);
        } else if (this.i) {
            LogUtil.a("EcgServiceIVUtil", "getEcgIv is querying");
        } else {
            c();
        }
    }

    private boolean e(DeviceInfo deviceInfo) {
        if (TextUtils.isEmpty(this.j) || TextUtils.isEmpty(this.f) || this.g == null) {
            return true;
        }
        return !deviceInfo.getDeviceIdentify().equalsIgnoreCase(this.g.getDeviceIdentify());
    }

    private void a(DeviceInfo deviceInfo) {
        this.g = deviceInfo;
        d();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(35);
        deviceCommand.setCommandID(17);
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(0));
        sb.append(cvx.e(2));
        sb.append(cvx.e(0));
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        LogUtil.a("EcgServiceIVUtil", "5.35.17 command : ", sb.toString());
        jfq.c().b(deviceCommand);
    }

    private void a(int i, Object obj) {
        LogUtil.a("EcgServiceIVUtil", "enter handleResult");
        this.i = false;
        synchronized (d) {
            if (this.e.isEmpty()) {
                return;
            }
            LogUtil.a("EcgServiceIVUtil", "start handleResult callback");
            for (int size = this.e.size() - 1; size >= 0; size--) {
                IBaseResponseCallback iBaseResponseCallback = this.e.get(size);
                if (iBaseResponseCallback != null) {
                    LogUtil.a("EcgServiceIVUtil", "callback onResponse index:", Integer.valueOf(size));
                    iBaseResponseCallback.d(i, obj);
                }
                this.e.remove(size);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (TextUtils.isEmpty(this.j) || TextUtils.isEmpty(this.f)) {
            a(-1, "receive ecgIv error.");
            LogUtil.h("EcgServiceIVUtil", "receive ecgIv error.");
        } else {
            this.i = true;
            ThreadPoolManager.d().execute(new Runnable() { // from class: jga
                @Override // java.lang.Runnable
                public final void run() {
                    jgc.this.e();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public void e() {
        LogUtil.a("EcgServiceIVUtil", "enter startQuery");
        EcgIvActivationRequest ecgIvActivationRequest = new EcgIvActivationRequest();
        ecgIvActivationRequest.setDeviceIv(this.j);
        ecgIvActivationRequest.setDeviceCardPass(this.f);
        ecgIvActivationRequest.setDeviceSn(this.g.getSecurityDeviceId());
        a(ecgIvActivationRequest);
    }

    private void a(EcgIvActivationRequest ecgIvActivationRequest) {
        EcgServiceActivationData d2 = jbs.a(BaseApplication.getContext()).d(ecgIvActivationRequest);
        if (d2 == null) {
            a(-1, "receive device error.");
            LogUtil.a("EcgServiceIVUtil", "getEcgIvActivationStatus has error");
            return;
        }
        LogUtil.a("EcgServiceIVUtil", "receive data:", d2.toString());
        if (d2.getResultCode().intValue() != 0) {
            a(-1, "receive data error.");
        } else {
            a(0, d2);
        }
    }

    private void d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.ecgiv.received");
        BroadcastManagerUtil.bFC_(this.b, this.h, intentFilter, LocalBroadcast.c, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        try {
            this.b.unregisterReceiver(this.h);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("EcgServiceIVUtil", "unregister NotifyRestart Broadcast exception");
        }
    }
}
