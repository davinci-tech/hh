package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoRsp;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class cuf {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11478a = new Object();
    private static volatile cuf b;
    private BaseCallbackInterface c;
    private ctk g;
    private Context h;
    private long e = 180000;
    private long j = 180000;
    private Timer d = null;
    private String i = "1";
    private e f = new e(this);

    private cuf(Context context) {
        this.h = context;
    }

    public static cuf e(Context context) {
        if (b == null) {
            synchronized (f11478a) {
                if (b == null) {
                    b = new cuf(context);
                }
            }
        }
        return b;
    }

    static class e extends StaticHandler<cuf> {
        e(cuf cufVar) {
            super(cufVar);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: MN_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(cuf cufVar, Message message) {
            if (cufVar == null || message == null) {
                cpw.e(false, "OtaStatusManager", "handleMessage object or msg is null");
                return;
            }
            cpw.a(false, "OtaStatusManager", "handleMessage", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 12) {
                if ("2".equals(cufVar.i)) {
                    return;
                }
                cpw.d(false, "OtaStatusManager", "handleMessage upgrade fail, No upgrade detected");
                cufVar.f.sendEmptyMessage(17);
                return;
            }
            if (i == 17) {
                cpw.a(false, "OtaStatusManager", "handleMessage Upgrade timeout");
                cufVar.b();
                if (cufVar.g != null) {
                    Map<String, String> e = csc.d().e(cufVar.g.d());
                    e.put("status", "1");
                    csc.d().d(cufVar.g.d(), e);
                } else {
                    cpw.d(false, "OtaStatusManager", "handleMessage device is null");
                }
                cufVar.c.onFailure(-2);
                return;
            }
            cpw.a(false, "OtaStatusManager", "handleMessage unKnow msg");
        }
    }

    private void f() {
        synchronized (this) {
            if (this.d == null) {
                this.d = new Timer("OtaStatusManager");
                cpw.a(false, "OtaStatusManager", "Start the timer connected devices");
                this.d.schedule(new b(this), 3000L, 3000L);
                this.f.sendEmptyMessageDelayed(12, 16000L);
            }
        }
    }

    public void e() {
        synchronized (this) {
            Timer timer = this.d;
            if (timer != null) {
                timer.cancel();
                this.d.purge();
                this.d = null;
                cpw.a(false, "OtaStatusManager", "Cancel the timer connected devices");
            } else {
                cpw.a(false, "OtaStatusManager", "Connection timer has been canceled");
            }
        }
    }

    private boolean d() {
        boolean z;
        synchronized (this) {
            z = this.d != null;
        }
        return z;
    }

    public void b() {
        e eVar = this.f;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
        this.i = "1";
        e();
    }

    static class b extends TimerTask {
        private WeakReference<cuf> e;

        b(cuf cufVar) {
            this.e = new WeakReference<>(cufVar);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            WeakReference<cuf> weakReference = this.e;
            if (weakReference == null) {
                cpw.a(false, "OtaStatusManager", "MyTimerTask run mWeakRef is null");
                return;
            }
            cuf cufVar = weakReference.get();
            if (cufVar == null) {
                cpw.a(false, "OtaStatusManager", "MyTimerTask run manager is null");
            } else {
                cpw.a(false, "OtaStatusManager", "check ota update result...");
                cufVar.c();
            }
        }
    }

    public void c(String str, BaseCallbackInterface baseCallbackInterface) {
        if ("1".equals(this.i) || !d()) {
            cpw.a(false, "OtaStatusManager", "setStatusCallback Restart detection");
            this.c = baseCallbackInterface;
            a(str);
            ctk ctkVar = this.g;
            if (ctkVar != null) {
                long c = csc.c(ctkVar.d());
                this.j = c;
                if (c > 0) {
                    long j = this.e;
                    if (c <= j) {
                        this.j = j - c;
                        cpw.a(false, "OtaStatusManager", "setStatusCallback timeOut:", Long.valueOf(this.j));
                        a();
                        return;
                    }
                }
                this.j = this.e;
                cpw.a(false, "OtaStatusManager", "setStatusCallback timeOut:", Long.valueOf(this.j));
                a();
                return;
            }
            cpw.d(false, "OtaStatusManager", "setStatusCallback mWiFiDevice is null");
            this.f.obtainMessage(17).sendToTarget();
            return;
        }
        this.c = baseCallbackInterface;
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            MeasurableDevice d = ceo.d().d(str, false);
            if (!(d instanceof ctk)) {
                LogUtil.h("OtaStatusManager", "device is not instanceof WiFiDevice");
                return;
            }
            ctk ctkVar = (ctk) d;
            this.g = ctkVar;
            if (csf.g(ctkVar.d())) {
                this.e = 480000L;
                return;
            }
            return;
        }
        cpw.d(false, "OtaStatusManager", "productId is empty, can't get WiFiDevice");
    }

    private void a() {
        b();
        this.f.sendEmptyMessageDelayed(17, this.j);
        this.i = "1";
        f();
    }

    public void e(String str, BaseCallbackInterface baseCallbackInterface) {
        cpw.a(false, "OtaStatusManager", "startCheckStatus in");
        this.c = baseCallbackInterface;
        a(str);
        if (this.g != null) {
            this.j = this.e;
            a();
            csc.b(this.g.d(), System.currentTimeMillis());
        } else {
            cpw.d(false, "OtaStatusManager", "startCheckStatus mWiFiDevice is null");
            this.f.obtainMessage(17).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq = new WifiDeviceServiceInfoReq();
        ctk ctkVar = this.g;
        if (ctkVar != null && ctkVar.b() != null) {
            if (!TextUtils.isEmpty(this.g.b().a())) {
                wifiDeviceServiceInfoReq.setDevId(this.g.b().a());
                wifiDeviceServiceInfoReq.setSid("devOtaInfo");
                jbs.a(this.h).b(wifiDeviceServiceInfoReq, new ICloudOperationResult<WifiDeviceServiceInfoRsp>() { // from class: cuf.4
                    @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                    /* renamed from: e, reason: merged with bridge method [inline-methods] */
                    public void operationResult(WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, String str, boolean z) {
                        if (!z) {
                            cpw.d(false, "OtaStatusManager", "checkOtaStatus get device ota status fail. text:", str, "|res:", wifiDeviceServiceInfoRsp);
                            return;
                        }
                        if (wifiDeviceServiceInfoRsp == null) {
                            ReleaseLogUtil.e("OtaStatusManager", "res is null");
                            return;
                        }
                        if (wifiDeviceServiceInfoRsp.getDeviceServiceInfo() != null && !wifiDeviceServiceInfoRsp.getDeviceServiceInfo().isEmpty()) {
                            for (DeviceServiceInfo deviceServiceInfo : wifiDeviceServiceInfoRsp.getDeviceServiceInfo()) {
                                cpw.c(false, "OtaStatusManager", "device service info size: ", Integer.valueOf(wifiDeviceServiceInfoRsp.getDeviceServiceInfo().size()));
                                if (cuf.this.a(deviceServiceInfo.getData(), deviceServiceInfo)) {
                                    return;
                                }
                            }
                        } else {
                            cpw.d(false, "OtaStatusManager", "checkOtaStatus device service is empty.");
                        }
                        cpw.a(false, "OtaStatusManager", "checkOtaStatus res:", wifiDeviceServiceInfoRsp, "|text:", str);
                    }
                });
                return;
            }
            cpw.a(false, "OtaStatusManager", "checkOtaStatus dev id is null");
            return;
        }
        cpw.d(false, "OtaStatusManager", "checkOtaStatus device id is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Map<String, String> map, DeviceServiceInfo deviceServiceInfo) {
        if (map == null) {
            LogUtil.h("OtaStatusManager", "getOtaStatus map is null");
            return false;
        }
        String str = map.get("status");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("OtaStatusManager", "getOtaStatus status is empty");
            return false;
        }
        String str2 = map.get("fwNewVer");
        String str3 = map.get("fwCurVer");
        cpw.a(false, "OtaStatusManager", "sid: ", deviceServiceInfo.getSid(), ", note: ", map.get("releaseNote"));
        ReleaseLogUtil.e("R_Weight_OtaStatusManager", "newVer: ", str2, ", curVer: ", str3, ", status: ", str);
        if (this.g != null) {
            Map<String, String> e2 = csc.d().e(this.g.d());
            if (!"3".equals(str) && e2 != null) {
                e2.put("status", str);
                csc.d().d(this.g.d(), e2);
            } else {
                LogUtil.a("OtaStatusManager", "getOtaStatus update new map");
                csc.d().d(this.g.d(), map);
            }
        } else {
            LogUtil.h("OtaStatusManager", "getOtaStatus mWiFiDevice is null");
        }
        if ("3".equals(str)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("fwCurVer", str3);
            contentValues.put("status", str);
            this.c.onSuccess(contentValues);
            csc.d().a();
            e();
            b();
            this.i = str;
            return true;
        }
        if (c(str)) {
            this.c.onFailure(-3);
            e();
            b();
            cpw.d(false, "OtaStatusManager", "getOtaStatus Upgrade fail status:", str, ", mUpgradeStatus:", this.i);
            this.i = str;
            return true;
        }
        this.i = str;
        return false;
    }

    private boolean c(String str) {
        return "1".equals(str) && "2".equals(this.i);
    }
}
