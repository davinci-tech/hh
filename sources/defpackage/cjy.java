package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.health.device.callback.HeartRateDeviceSelectedCallback;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.MeasureResult;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.ui.AutoTestHeartRateService;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes3.dex */
public class cjy {

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList<IHealthDeviceCallback> f758a = new ArrayList<>(16);
    private HashMap<String, b> d = new HashMap<>(16);

    protected cjy() {
    }

    public String b(HealthDevice.HealthDeviceKind healthDeviceKind, IHealthDeviceCallback iHealthDeviceCallback, HeartRateDeviceSelectedCallback heartRateDeviceSelectedCallback, com.huawei.hihealth.device.open.IHealthDeviceCallback iHealthDeviceCallback2, MeasureResult.MeasureResultListener measureResultListener) {
        LogUtil.a("PluginDevice_HealthDataImporter", "startMeasureWithDeviceType");
        ArrayList<ContentValues> d = ceo.d().d(healthDeviceKind);
        b bVar = new b();
        bVar.d(d);
        bVar.c(iHealthDeviceCallback);
        bVar.a(heartRateDeviceSelectedCallback);
        bVar.a(iHealthDeviceCallback2);
        bVar.d(measureResultListener);
        cpp.a(bVar);
        return null;
    }

    public boolean Gf_(String str, String str2, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        LogUtil.a("PluginDevice_HealthDataImporter", "startMeasure with productId two: ", str);
        return Gi_(str, str2, iHealthDeviceCallback, bundle, false);
    }

    public boolean Gg_(String str, String str2, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle, MeasurableDevice measurableDevice) {
        LogUtil.a("PluginDevice_HealthDataImporter", "startMeasure with productId four: ", str);
        if (measurableDevice != null) {
            try {
                if (!(measurableDevice instanceof ctk)) {
                    b bVar = new b();
                    measurableDevice.setProductId(str);
                    bVar.b(measurableDevice);
                    bVar.c(iHealthDeviceCallback);
                    bVar.Gp_(bundle);
                    bVar.a(str);
                    bVar.c(str2);
                    if (this.d.containsKey(str2)) {
                        this.d.remove(str2);
                    }
                    this.d.put(str2, bVar);
                    cpp.a(bVar);
                    return true;
                }
            } catch (RejectedExecutionException e) {
                LogUtil.b("PluginDevice_HealthDataImporter", "startMeasure e = ", e.getMessage());
                return false;
            }
        }
        LogUtil.h("PluginDevice_HealthDataImporter", "startMeasure: device is null or wifi device.");
        return false;
    }

    public boolean Gh_(String str, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle, boolean z) {
        LogUtil.a("PluginDevice_HealthDataImporter", "startMeasureReconnect with productId: ", str, " isReconnect is ", Boolean.valueOf(z));
        try {
            MeasurableDevice e = ceo.d().e(str, true);
            if (e != null && !(e instanceof ctk)) {
                String uniqueId = e.getUniqueId();
                b bVar = new b();
                bVar.b(e);
                bVar.c(iHealthDeviceCallback);
                bVar.Gp_(bundle);
                bVar.c(uniqueId);
                bVar.a(str);
                if (cpa.ab(str) && !z && bundle != null && bundle.getInt("type") == -1 && this.d.containsKey(uniqueId)) {
                    Gd_(str, e, iHealthDeviceCallback, bundle);
                    return true;
                }
                if (this.d.containsKey(uniqueId)) {
                    this.d.remove(uniqueId);
                }
                this.d.put(uniqueId, bVar);
                cpp.a(bVar);
                return true;
            }
            LogUtil.h("PluginDevice_HealthDataImporter", "startMeasureReconnect: device is null or wifi device.");
            return false;
        } catch (RejectedExecutionException e2) {
            LogUtil.b("PluginDevice_HealthDataImporter", "startMeasureReconnect e = ", e2.getMessage());
            return false;
        }
    }

    public boolean Gi_(String str, String str2, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle, boolean z) {
        LogUtil.a("PluginDevice_HealthDataImporter", "startMeasureReconnect with productId: ", str, " isReconnect:", Boolean.valueOf(z), " isContain:", Boolean.valueOf(this.d.containsKey(str2)));
        return Ge_(str, str2, z, iHealthDeviceCallback, bundle);
    }

    private boolean Ge_(String str, String str2, boolean z, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        try {
            MeasurableDevice d = ceo.d().d(str2, true);
            if (d != null && !(d instanceof ctk)) {
                b bVar = new b();
                bVar.b(d);
                bVar.c(iHealthDeviceCallback);
                bVar.Gp_(bundle);
                bVar.a(str);
                bVar.c(str2);
                if (cpa.ab(str) && !z && bundle != null && bundle.getInt("type") == -1 && this.d.containsKey(str2)) {
                    Gd_(str, d, iHealthDeviceCallback, bundle);
                    return true;
                }
                if (this.d.containsKey(str2)) {
                    this.d.remove(str2);
                }
                this.d.put(str2, bVar);
                cpp.a(bVar);
                return true;
            }
            LogUtil.h("PluginDevice_HealthDataImporter", "startMeasureReconnect: device is null or wifi device.");
            return false;
        } catch (RejectedExecutionException e) {
            LogUtil.b("PluginDevice_HealthDataImporter", "startMeasureReconnect e = ", e.getMessage());
            return false;
        }
    }

    private void Gd_(String str, MeasurableDevice measurableDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        dcz d = ResourceManager.e().d(str);
        if (d == null) {
            return;
        }
        MeasureKit g = cjx.e().g(d.s());
        MeasureController measureController = g != null ? g.getMeasureController() : null;
        if (measureController != null) {
            measureController.prepare(measurableDevice, iHealthDeviceCallback, bundle);
        }
    }

    public void e(String str, String str2) {
        c(str, str2, 0);
    }

    public void d(String str, int i) {
        c(str);
        if (i != -1) {
            MeasurableDevice c = ceo.d().c(str);
            if (c == null) {
                LogUtil.a("PluginDevice_HealthDataImporter", "stopMeasureBleScale device is null", str);
            } else {
                d(this.d.remove(c.getUniqueId()));
            }
        }
    }

    public void c(String str, String str2, int i) {
        c(str);
        if (i != -1) {
            d(this.d.remove(str2));
        }
    }

    private void c(String str) {
        dcz d;
        LogUtil.a("PluginDevice_HealthDataImporter", "stopMeasureBleScale:", str);
        if (!dfe.a().e() || TextUtils.isEmpty(str) || (d = ResourceManager.e().d(str)) == null || d.l() != HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
            return;
        }
        cpp.a().stopService(new Intent(cpp.a(), (Class<?>) AutoTestHeartRateService.class));
    }

    private void d(b bVar) {
        if (bVar != null) {
            LogUtil.a("PluginDevice_HealthDataImporter", "stopMeasureBleScale director is not null");
            if (bVar.b != null) {
                LogUtil.a("PluginDevice_HealthDataImporter", "stopMeasureBleScale, ending...");
                bVar.b.ending();
            }
            cpp.d(bVar);
        }
    }

    public boolean Gj_(String str, String str2, com.huawei.hihealth.device.open.IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        LogUtil.a("PluginDevice_HealthDataImporter", "startMeasureUniversal with productId: ", str);
        try {
            com.huawei.hihealth.device.open.HealthDevice b2 = ceo.d().b(str, str2);
            if (b2 == null) {
                LogUtil.h("PluginDevice_HealthDataImporter", "startMeasureUniversal deviceUniversal is null");
                return false;
            }
            b bVar = new b();
            bVar.Gp_(bundle);
            bVar.a(str);
            bVar.c(str2);
            bVar.a(b2);
            bVar.a(iHealthDeviceCallback);
            if (this.d.containsKey(str2)) {
                this.d.remove(str2);
            }
            this.d.put(str2, bVar);
            cpp.a(bVar);
            LogUtil.h("PluginDevice_HealthDataImporter", "startMeasureUniversal: execute");
            return true;
        } catch (RejectedExecutionException e) {
            LogUtil.b("PluginDevice_HealthDataImporter", "startMeasureUniversal e = ", e.getMessage());
            return false;
        }
    }

    public void b(String str, String str2) {
        LogUtil.a("PluginDevice_HealthDataImporter", "stopMeasureUniversal productId: ", str);
        if (cpa.z(str)) {
            b bVar = this.d.get(str2);
            if (bVar != null) {
                LogUtil.a("PluginDevice_HealthDataImporter", "stopMeasureUniversal two director is not null");
                if (bVar.d != null) {
                    LogUtil.a("PluginDevice_HealthDataImporter", "two stopMeasureUniversal, ending...");
                    bVar.d.ending();
                    return;
                }
                return;
            }
            return;
        }
        e(this.d.remove(str2));
    }

    private void e(b bVar) {
        if (bVar != null) {
            LogUtil.a("PluginDevice_HealthDataImporter", "stopMeasureUniversal three director is not null");
            if (bVar.d != null) {
                LogUtil.a("PluginDevice_HealthDataImporter", "three stopMeasureUniversal, ending...");
                bVar.d.ending();
                bVar.d.cleanup();
            }
            bVar.g = null;
        }
        cpp.d(bVar);
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && cpa.z(str)) {
            c(this.d.remove(str2));
        }
    }

    private void c(b bVar) {
        if (bVar != null) {
            LogUtil.a("PluginDevice_HealthDataImporter", "cleanupMeasureUniversal director is not null");
            if (bVar.d != null) {
                LogUtil.a("PluginDevice_HealthDataImporter", "stopMeasureUniversal, cleanup...");
                bVar.d.cleanup();
            }
            bVar.g = null;
        } else {
            com.huawei.hihealth.device.open.MeasureKit c = cfl.a().c("54C9739F-CA5C-4347-9F00-75B9DDF2C649");
            if (c == null) {
                return;
            }
            com.huawei.hihealth.device.open.MeasureController measureController = c.getMeasureController();
            if (measureController != null) {
                measureController.cleanup();
            }
        }
        cpp.d(bVar);
    }

    public boolean a(String str) {
        b bVar = this.d.get(str);
        return bVar != null && bVar.j;
    }

    class b implements Runnable {
        private MeasureController b;
        private MeasurableDevice c;
        private com.huawei.hihealth.device.open.MeasureController d;
        private Bundle e;
        private ArrayList<ContentValues> f;
        private com.huawei.hihealth.device.open.IHealthDeviceCallback g;
        private IHealthDeviceCallback h;
        private com.huawei.hihealth.device.open.HealthDevice i;
        private boolean j = true;
        private MeasureResult.MeasureResultListener k;
        private String l;
        private String m;
        private HeartRateDeviceSelectedCallback n;

        b() {
        }

        void c(IHealthDeviceCallback iHealthDeviceCallback) {
            this.h = iHealthDeviceCallback;
        }

        void a(com.huawei.hihealth.device.open.IHealthDeviceCallback iHealthDeviceCallback) {
            this.g = iHealthDeviceCallback;
        }

        void b(MeasurableDevice measurableDevice) {
            this.c = measurableDevice;
        }

        void a(com.huawei.hihealth.device.open.HealthDevice healthDevice) {
            this.i = healthDevice;
        }

        void Gp_(Bundle bundle) {
            this.e = bundle;
        }

        void a(String str) {
            this.l = str;
        }

        void d(ArrayList<ContentValues> arrayList) {
            this.f = arrayList;
        }

        void a(HeartRateDeviceSelectedCallback heartRateDeviceSelectedCallback) {
            this.n = heartRateDeviceSelectedCallback;
        }

        void d(MeasureResult.MeasureResultListener measureResultListener) {
            this.k = measureResultListener;
        }

        void c(String str) {
            this.m = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList<ContentValues> arrayList = this.f;
            if (arrayList == null) {
                Go_(30, this.e);
                this.j = false;
                return;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                this.l = this.f.get(size).getAsString("productId");
                this.m = this.f.get(size).getAsString("uniqueId");
                if (cjx.e().h(this.l)) {
                    this.i = ceo.d().b(this.l, this.m);
                    this.h = null;
                    this.e = cpa.JY_();
                } else {
                    MeasurableDevice d = ceo.d().d(this.m, true);
                    this.c = d;
                    if (d != null) {
                        LogUtil.a("PluginDevice_HealthDataImporter", "run current device is ", d.getDeviceName());
                    }
                }
                if (this.c == null && this.i == null) {
                    LogUtil.h("PluginDevice_HealthDataImporter", "run mDevice is null");
                    return;
                }
                LogUtil.a("PluginDevice_HealthDataImporter", "run current productId is ", this.l);
                if (!(this.c instanceof cfb) && Go_(30, this.e)) {
                    d();
                    return;
                }
            }
        }

        private void d() {
            if (cjy.this.d.containsKey(this.m)) {
                cjy.this.d.remove(this.m);
            }
            LogUtil.a("PluginDevice_HealthDataImporter", "doStartMeasureSelectDevice true");
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            if (cjx.e().h(this.l)) {
                com.huawei.hihealth.device.open.HealthDevice healthDevice = this.i;
                if (healthDevice != null) {
                    hashMap.put("macAddress", dis.b(healthDevice.getAddress()));
                }
            } else {
                MeasurableDevice measurableDevice = this.c;
                if (measurableDevice != null) {
                    hashMap.put("macAddress", dis.b(measurableDevice.getAddress()));
                }
            }
            dcz d = ResourceManager.e().d(this.l);
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.e(this.l, d.n().b()));
            hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, d.l().name());
            hashMap.put("prodId", dij.e(this.l));
            ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HEART_RATE_MEASURE_SUCCEED_2060016.value(), hashMap, 0);
            cjy.this.d.put(this.m, this);
            MeasureResult.MeasureResultListener measureResultListener = this.k;
            if (measureResultListener != null) {
                measureResultListener.onMeasureDevice(this.l, this.m, false);
            }
            LogUtil.a("PluginDevice_HealthDataImporter", "doStartMeasureSelectDevice target productId is ", this.l);
        }

        private boolean Go_(int i, Bundle bundle) {
            ReleaseLogUtil.e("R_Weight_PluginDevice_HealthDataImporter", "startMeasureSelectedDevice mHealthCallback:", this.h, ",mHealthCallbackUniversal:", this.g);
            if (this.h != null) {
                return Gm_(i, bundle);
            }
            if (this.g != null) {
                return Gn_(i, bundle);
            }
            return false;
        }

        private void e(MeasureController measureController) {
            if (measureController != null) {
                LogUtil.a("PluginDevice_HealthDataImporter", "showLogMessage current device use background measure");
            } else {
                LogUtil.a("PluginDevice_HealthDataImporter", "showLogMessage current device use direct measure");
            }
        }

        private void e(com.huawei.hihealth.device.open.MeasureController measureController) {
            if (measureController != null) {
                LogUtil.a("PluginDevice_HealthDataImporter", "showLogMessageUniversal current device use background measure");
            } else {
                LogUtil.a("PluginDevice_HealthDataImporter", "showLogMessageUniversal current device use direct measure");
            }
        }

        private boolean Gm_(int i, Bundle bundle) {
            ReleaseLogUtil.e("R_Weight_PluginDevice_HealthDataImporter", "enter prepareDevice");
            cfl a2 = cfl.a();
            LogUtil.a("PluginDevice_HealthDataImporter", "prepareDevice measure with ", this.c.getDeviceName(), " kitUuid = ", this.c.getMeasureKitUuid());
            MeasureKit e = a2.e(this.c.getMeasureKitUuid());
            dcz d = ResourceManager.e().d(this.l);
            int c = (d == null || d.x() == null) ? 1 : d.x().c();
            if (c == 2 || c == 1) {
                if (d != null && d.l() == HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
                    LogUtil.a("PluginDevice_HealthDataImporter", "prepareDevice current device is heart rate device");
                } else {
                    if (BluetoothAdapter.getDefaultAdapter().getState() == 10) {
                        LogUtil.h("PluginDevice_HealthDataImporter", "bluetooth state is off");
                        this.h.onFailed(this.c, 3);
                        return false;
                    }
                    LogUtil.a("PluginDevice_HealthDataImporter", "prepareDevice other device");
                }
            }
            if (e == null) {
                this.h.onFailed(this.c, 0);
                LogUtil.h("PluginDevice_HealthDataImporter", "prepareDevice no measure kit for ", this.c.getDeviceName());
                return false;
            }
            if (dfe.a().e()) {
                c();
            } else {
                LogUtil.a("PluginDevice_HealthDataImporter", "prepareDevice uuid:", e.getUuid(), " name:", e.getClass().getSimpleName());
                MeasureController measureController = e.getMeasureController();
                this.b = measureController;
                if (measureController != null) {
                    return Gk_(e, i, bundle);
                }
            }
            return false;
        }

        private boolean Gk_(MeasureKit measureKit, int i, Bundle bundle) {
            ReleaseLogUtil.e("R_Weight_PluginDevice_HealthDataImporter", "doMeasureController current controller is ", this.b.getClass().getSimpleName());
            this.b.prepare(this.c, this.h, this.e);
            ReleaseLogUtil.e("R_Weight_PluginDevice_HealthDataImporter", "doMeasureController current controller is 1");
            MeasureController backgroundController = measureKit.getBackgroundController();
            if (cpa.z(this.l)) {
                backgroundController = null;
            }
            boolean z = bundle != null ? bundle.getBoolean("isBackgroundMeasure", false) : false;
            ReleaseLogUtil.e("R_Weight_PluginDevice_HealthDataImporter", "doMeasureController current controller is flag = ", Boolean.valueOf(z));
            if (backgroundController != null && !z) {
                ReleaseLogUtil.e("R_Weight_PluginDevice_HealthDataImporter", "doMeasureController current device support background measure but use direct measure");
                cjx.e().a(this.l, this.m, "com.huawei.health.action.DEVICE_OCCUPIED");
                return c(i);
            }
            e(backgroundController);
            return c(i);
        }

        private void c() {
            dcz d = ResourceManager.e().d(this.l);
            if (d == null) {
                LogUtil.h("PluginDevice_HealthDataImporter", "doThreeDeviceVersion productInfo is null");
                return;
            }
            HealthDevice.HealthDeviceKind l = d.l();
            if (l == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE) {
                this.h.onDataChanged(dfe.a().b(this.l), dfe.a().e(this.l));
            } else if (l == HealthDevice.HealthDeviceKind.HDK_WEIGHT) {
                this.h.onDataChanged(dfe.a().b(this.l), dfe.a().c(this.l));
            } else {
                if (l == HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
                    Intent intent = new Intent(cpp.a(), (Class<?>) AutoTestHeartRateService.class);
                    intent.putExtra("productId", this.l);
                    cpp.a().startService(intent);
                    return;
                }
                LogUtil.a("PluginDevice_HealthDataImporter", "doThreeDeviceVersion other kind = ", l);
            }
        }

        private boolean Gn_(int i, Bundle bundle) {
            cfl a2 = cfl.a();
            dcz d = ResourceManager.e().d(this.l);
            if (d == null) {
                return false;
            }
            LogUtil.a("PluginDevice_HealthDataImporter", "prepareDeviceUniversal start to measure mDeviceUniversal with productInfo:", d.s(), ",kitManager:", a2);
            a2.e(d.s(), ResourceManager.e().c(this.l) + File.separator + d.k());
            com.huawei.hihealth.device.open.MeasureKit c = a2.c(d.s());
            if (c == null) {
                com.huawei.hihealth.device.open.IHealthDeviceCallback iHealthDeviceCallback = this.g;
                if (iHealthDeviceCallback != null) {
                    iHealthDeviceCallback.onFailed(this.i, 0);
                }
                com.huawei.hihealth.device.open.HealthDevice healthDevice = this.i;
                if (healthDevice != null) {
                    LogUtil.a("PluginDevice_HealthDataImporter", "prepareDeviceUniversal no measure kit for ", healthDevice.getDeviceName());
                } else {
                    LogUtil.h("PluginDevice_HealthDataImporter", "prepareDeviceUniversal no measure kit, mDeviceUniversal is null");
                }
                return false;
            }
            LogUtil.a("PluginDevice_HealthDataImporter", "prepareDeviceUniversal uuid:", c.getUuid(), " name:", c.getClass().getSimpleName());
            com.huawei.hihealth.device.open.MeasureController measureController = c.getMeasureController();
            this.d = measureController;
            if (measureController != null) {
                return Gl_(c, i, bundle);
            }
            return false;
        }

        private boolean Gl_(com.huawei.hihealth.device.open.MeasureKit measureKit, int i, Bundle bundle) {
            LogUtil.a("PluginDevice_HealthDataImporter", "doPrepareWithDeviceKit current controllerUniversal is ", this.d.getClass().getSimpleName());
            com.huawei.hihealth.device.open.IHealthDeviceCallback iHealthDeviceCallback = this.g;
            if (iHealthDeviceCallback != null) {
                this.d.prepare(this.i, iHealthDeviceCallback, this.e);
            }
            LogUtil.a("PluginDevice_HealthDataImporter", "doPrepareWithDeviceKit current controller is 1");
            com.huawei.hihealth.device.open.MeasureController backgroundController = measureKit.getBackgroundController();
            if (cpa.z(this.l)) {
                backgroundController = null;
            }
            boolean z = bundle != null ? bundle.getBoolean("isBackgroundMeasure", false) : false;
            LogUtil.a("PluginDevice_HealthDataImporter", "doPrepareWithDeviceKit current controller is flag = ", Boolean.valueOf(z));
            if (backgroundController != null && !z) {
                LogUtil.a("PluginDevice_HealthDataImporter", "doPrepareWithDeviceKit current device support background measure but use direct measure");
                cjx.e().c(this.l, this.m, "com.huawei.health.action.DEVICE_OCCUPIED");
                return c(i);
            }
            e(backgroundController);
            return c(i);
        }

        private boolean c(int i) {
            if (this.h != null) {
                ReleaseLogUtil.e("R_Weight_PluginDevice_HealthDataImporter", "connectTargetDevice Try to connect ", this.c.getDeviceName());
                cpw.a(true, "PluginDevice_HealthDataImporter", "connectTargetDevice device:", this.c.getAddress());
                this.h.onStatusChanged(this.c, 1);
                MeasureController measureController = this.b;
                if (!(measureController instanceof cgt) || ((cgt) measureController).k() != 2) {
                    this.b.updateState(1);
                }
                if (cpa.ar(this.l)) {
                    return a();
                }
                if (!b(i)) {
                    return false;
                }
                if (!cpa.ae(this.l)) {
                    this.h.onStatusChanged(this.c, 2);
                }
                this.b.start();
                return true;
            }
            if (this.g == null) {
                return false;
            }
            LogUtil.a("PluginDevice_HealthDataImporter", "connectTargetDevice Try to connect ", this.i.getDeviceName());
            LogUtil.c("PluginDevice_HealthDataImporter", "connectTargetDevice Try to connect address:", iyl.d().e(this.i.getAddress()));
            this.d.start();
            return true;
        }

        private boolean a() {
            if (TextUtils.isEmpty(this.m)) {
                LogUtil.h("PluginDevice_HealthDataImporter", "connectDeviceByUds : UniqueId is null");
                return false;
            }
            cjg.d().a(cpl.c().d(this.m), ConnectMode.SIMPLE);
            return true;
        }

        private boolean b(int i) {
            ReleaseLogUtil.e("R_Weight_PluginDevice_HealthDataImporter", "enter isConnectSyncSuccess");
            if (this.c.connectSync(i)) {
                return true;
            }
            this.h.onStatusChanged(this.c, 5);
            this.b.updateState(5);
            cjx.e().a(this.l, this.m, "com.huawei.health.action.DEVICE_AVAILABLE");
            LogUtil.h("PluginDevice_HealthDataImporter", "connectTargetDevice Fail to connect, ending, mDevice:", Integer.valueOf(System.identityHashCode(this.c)));
            this.b.ending();
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                LogUtil.b("PluginDevice_HealthDataImporter", "connectTargetDevice e = ", e.getMessage());
            }
            LogUtil.h("PluginDevice_HealthDataImporter", "connectTargetDevice Fail to connect, cleanup");
            this.b.cleanup();
            return false;
        }
    }
}
