package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.util.EventBus;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cjn {
    private static final HealthDevice.HealthDeviceKind b = HealthDevice.HealthDeviceKind.HDK_WEIGHT;
    private static cjn d;

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<Context> f748a;
    private IHealthDeviceCallback c;
    private String g;
    private boolean i;
    private HealthDevice j;
    private boolean l = false;
    private boolean n = true;
    private boolean k = false;
    private EventBus.ICallback e = new EventBus.ICallback() { // from class: cjn.5
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            if (bVar != null) {
                if (cjn.this.l && "weight_measure_choose_user".equals(bVar.e()) && cjn.this.n && !cjn.this.i && cjn.this.k) {
                    LogUtil.a("AutoMearsureController", "go to WeightAutoMeasureFragment");
                    cjn.this.FR_(bVar.Kl_());
                    return;
                }
                return;
            }
            LogUtil.h("AutoMearsureController", "onEvent event is null");
        }
    };
    private Handler h = new Handler(Looper.getMainLooper()) { // from class: cjn.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LogUtil.a("AutoMearsureController", "message what is ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1002) {
                cjn.this.j();
            } else if (i == 1003) {
                cjn.this.g();
            } else {
                LogUtil.h("AutoMearsureController", "message what is error");
            }
        }
    };
    private IDeviceEventHandler o = new IDeviceEventHandler() { // from class: cjn.4
        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onScanFailed(int i) {
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onStateChanged(int i) {
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onDeviceFound(HealthDevice healthDevice) {
            LogUtil.a("AutoMearsureController", "Callback, onDeviceFound deviceName: ", healthDevice != null ? healthDevice.getDeviceName() : "");
            cjn.this.h.sendEmptyMessage(1002);
        }
    };
    private cjr m = new cjr(this.o);
    private String f = h();

    private cjn(Context context) {
        this.f748a = new WeakReference<>(context);
    }

    public void d(IHealthDeviceCallback iHealthDeviceCallback) {
        this.c = (IHealthDeviceCallback) cpt.d(iHealthDeviceCallback);
    }

    public void e() {
        this.c = null;
    }

    public static cjn e(Context context) {
        if (d == null) {
            d = new cjn(context);
        }
        return d;
    }

    public void b(boolean z) {
        if (Utils.o()) {
            LogUtil.a("AutoMearsureController", "setUserVisibleHint isOversea");
            return;
        }
        LogUtil.a("AutoMearsureController", "setUserVisibleHint: ", Boolean.valueOf(z));
        this.n = z;
        if (z || !this.l) {
            return;
        }
        this.l = false;
        cjx.e().d(h(), this.g);
        this.h.sendEmptyMessage(1003);
    }

    public void c() {
        if (Utils.o()) {
            LogUtil.a("AutoMearsureController", "onCreate isOversea");
            return;
        }
        LogUtil.a("AutoMearsureController", "onCreate");
        this.m.e();
        EventBus.d(this.e, 2, "weight_measure_choose_user");
        LogUtil.a("AutoMearsureController", "onCreate End");
    }

    private String h() {
        String str = this.f;
        if (str != null) {
            LogUtil.a("AutoMearsureController", "getHonorWeight mHonorWeightId is not null, mHonorWeightId: ", str);
            if (this.j == null) {
                LogUtil.h("AutoMearsureController", "getHonorWeight device is null");
                this.j = cjx.e().a(this.f);
            }
            ArrayList<ContentValues> a2 = ceo.d().a(this.f);
            if (!koq.b(a2)) {
                this.g = a2.get(0).getAsString("uniqueId");
            }
            return this.f;
        }
        Iterator<String> it = cjx.e().a(b).iterator();
        while (it.hasNext()) {
            String next = it.next();
            if ("005A".equals(dij.e(next))) {
                LogUtil.a("AutoMearsureController", "getHonorWeight productId: ", next);
                this.j = cjx.e().a(next);
                this.f = next;
                ArrayList<ContentValues> a3 = ceo.d().a(next);
                if (!koq.b(a3)) {
                    this.g = a3.get(0).getAsString("uniqueId");
                }
                return next;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void FR_(Bundle bundle) {
        if (this.f748a != null) {
            this.i = true;
            Intent intent = new Intent();
            intent.setPackage(BaseApplication.getAppPackage());
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.device.ui.DeviceMainActivity");
            intent.putExtra("kind", "HDK_WEIGHT");
            intent.putExtra("view", "AutoMeasureDevice");
            intent.putExtra("productId", h());
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", this.g);
            contentValues.put("productId", h());
            intent.putExtra("commonDeviceInfo", contentValues);
            if (bundle != null) {
                try {
                    intent.putExtra("HealthData", bundle.getSerializable("weight_data"));
                } catch (Exception unused) {
                    LogUtil.b("AutoMearsureController", " startActivity Exception");
                }
            }
            Context context = this.f748a.get();
            if (context != null) {
                try {
                    context.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException unused2) {
                    LogUtil.b("AutoMearsureController", "startActivity ActivityNotFoundException occur.");
                    return;
                }
            }
            return;
        }
        i();
        LogUtil.h("AutoMearsureController", "context is null");
    }

    public void b() {
        if (Utils.o()) {
            LogUtil.a("AutoMearsureController", "onResume isOversea");
            return;
        }
        LogUtil.a("AutoMearsureController", "onResume");
        this.k = true;
        if (this.i && this.l) {
            cjx.e().d(h(), this.g);
        }
        this.h.sendEmptyMessage(1003);
        this.i = false;
        this.l = false;
    }

    public void a() {
        if (Utils.o()) {
            LogUtil.a("AutoMearsureController", "onPause isOversea");
            return;
        }
        LogUtil.a("AutoMearsureController", "onPause mIsGoToMeasureView: ", Boolean.valueOf(this.i));
        this.k = false;
        f();
        this.l = false;
        if (this.i) {
            return;
        }
        cjx.e().d(h(), this.g);
    }

    public void d() {
        if (Utils.o()) {
            LogUtil.a("AutoMearsureController", "onDestroy isOversea");
            return;
        }
        LogUtil.a("AutoMearsureController", "onDestroy");
        f();
        this.m.d();
        this.m = null;
        EventBus.a(this.e, "weight_measure_choose_user");
        this.h.removeCallbacksAndMessages(null);
        i();
    }

    private static void i() {
        d = null;
    }

    private void f() {
        cjr cjrVar = this.m;
        if (cjrVar != null) {
            cjrVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.l || !this.k) {
            LogUtil.a("AutoMearsureController", "measureWeight mIsOnResume: ", Boolean.valueOf(this.k));
            return;
        }
        this.l = true;
        if (h() == null) {
            LogUtil.a("AutoMearsureController", "measureWeight no bonded honor device");
            this.l = false;
            f();
            return;
        }
        boolean z = this.n;
        if (!z) {
            LogUtil.a("AutoMearsureController", "isVisibleToUser: ", Boolean.valueOf(z));
            this.l = false;
            this.h.sendEmptyMessage(1003);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("isAutoMeasure", true);
        boolean Gr_ = cjx.e().Gr_(h(), this.g, new IHealthDeviceCallback() { // from class: cjn.3
            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
                cjn.this.d(healthDevice, healthData);
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
                cjn.this.a(healthDevice, list);
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
                cjn.this.b(healthDevice, healthData);
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onStatusChanged(HealthDevice healthDevice, int i) {
                cjn.this.b(healthDevice, i);
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onFailed(HealthDevice healthDevice, int i) {
                cjn.this.a(healthDevice, i);
            }
        }, bundle);
        LogUtil.a("AutoMearsureController", "startMeasure isConnect: ", Boolean.valueOf(Gr_));
        if (Gr_) {
            return;
        }
        this.l = false;
        this.h.sendEmptyMessage(1003);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HealthDevice healthDevice, HealthData healthData) {
        LogUtil.a("AutoMearsureController", "startMeasure onDataChanged");
        IHealthDeviceCallback iHealthDeviceCallback = this.c;
        if (iHealthDeviceCallback != null) {
            iHealthDeviceCallback.onDataChanged(healthDevice, healthData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HealthDevice healthDevice, List<HealthData> list) {
        LogUtil.a("AutoMearsureController", "startMeasure callbackOnDataChanged");
        IHealthDeviceCallback iHealthDeviceCallback = this.c;
        if (iHealthDeviceCallback != null) {
            iHealthDeviceCallback.onDataChanged(healthDevice, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HealthDevice healthDevice, HealthData healthData) {
        LogUtil.a("AutoMearsureController", "startMeasure onProgressChanged");
        IHealthDeviceCallback iHealthDeviceCallback = this.c;
        if (iHealthDeviceCallback != null) {
            iHealthDeviceCallback.onProgressChanged(healthDevice, healthData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HealthDevice healthDevice, final int i) {
        LogUtil.a("AutoMearsureController", "startMeasure onStatusChanged status: ", Integer.valueOf(i));
        IHealthDeviceCallback iHealthDeviceCallback = this.c;
        if (iHealthDeviceCallback != null) {
            iHealthDeviceCallback.onStatusChanged(healthDevice, i);
        }
        this.h.post(new Runnable() { // from class: cjn.1
            @Override // java.lang.Runnable
            public void run() {
                cjn.this.e(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HealthDevice healthDevice, int i) {
        LogUtil.a("AutoMearsureController", "startMeasure onFailed deviceName: ", healthDevice != null ? healthDevice.getDeviceName() : "", " code: ", Integer.valueOf(i));
        IHealthDeviceCallback iHealthDeviceCallback = this.c;
        if (iHealthDeviceCallback != null) {
            iHealthDeviceCallback.onFailed(healthDevice, i);
        }
    }

    protected void e(int i) {
        LogUtil.a("AutoMearsureController", "handleStatusChanged status: ", Integer.valueOf(i));
        if (i == 2) {
            LogUtil.a("AutoMearsureController", "STATUS_CONNECTED");
        } else if (i == 3 || i == 5) {
            this.l = false;
            this.h.sendEmptyMessage(1003);
        } else {
            LogUtil.h("AutoMearsureController", "handleStatusChanged status is error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.m != null && this.k) {
            LogUtil.a("AutoMearsureController", "startScanner");
            this.m.e(h(), this.j);
        } else {
            LogUtil.h("AutoMearsureController", "mScanUtil is null");
        }
    }
}
