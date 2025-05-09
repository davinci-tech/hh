package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.os.Message;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.api.MeasureKitManagerApi;
import com.huawei.health.device.api.MultiUsersManagerApi;
import com.huawei.health.device.callback.HeartRateDeviceSelectedCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.MeasureResult;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.util.ISimpleHandler;
import com.huawei.hihealth.device.open.IHealthDeviceCallback;
import com.huawei.hihealth.device.open.MeasureController;
import com.huawei.hihealth.device.open.MeasureKit;
import com.huawei.hihealth.device.open.data.MeasureRecord;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class dfg implements ISimpleHandler {
    private static final Object b = new Object();
    private static dfg d;
    private HeartRateDeviceSelectedCallback e;
    private MeasureResult.MeasureResultListener f;
    private boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f11635a = false;
    private boolean h = false;
    private String g = null;
    private String l = null;
    private String i = null;
    private String m = null;
    private boolean j = false;

    private dfg() {
    }

    public static dfg d() {
        dfg dfgVar;
        synchronized (b) {
            if (d == null) {
                d = new dfg();
            }
            dfgVar = d;
        }
        return dfgVar;
    }

    public void c(HeartRateDeviceSelectedCallback heartRateDeviceSelectedCallback) {
        this.e = heartRateDeviceSelectedCallback;
    }

    public String c(HealthDevice.HealthDeviceKind healthDeviceKind, MeasureResult.MeasureResultListener measureResultListener) {
        if (healthDeviceKind != HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
            if (measureResultListener != null) {
                measureResultListener.onMeasureFailed(MeasureResult.MeasureErrorCode.NOT_SUPPORT);
            }
            LogUtil.b("PluginDevice_MeasureHelper", "startMeasureBackground failed, no suitable kit.");
            return "";
        }
        HealthDeviceEntryApi healthDeviceEntryApi = (HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class);
        ArrayList<ContentValues> bondedDeviceByDeviceKind = healthDeviceEntryApi.getBondedDeviceByDeviceKind(healthDeviceKind);
        if (bondedDeviceByDeviceKind == null || koq.b(bondedDeviceByDeviceKind)) {
            if (measureResultListener != null) {
                measureResultListener.onMeasureFailed(MeasureResult.MeasureErrorCode.NO_DEVICE);
            }
            LogUtil.b("PluginDevice_MeasureHelper", "Measure failed, no device bonded for " + healthDeviceKind.name());
            return "";
        }
        if (bondedDeviceByDeviceKind.size() == 1) {
            String asString = bondedDeviceByDeviceKind.get(0).getAsString("productId");
            String asString2 = bondedDeviceByDeviceKind.get(0).getAsString("uniqueId");
            if (measureResultListener != null) {
                measureResultListener.onMeasureDevice(asString, asString2, false);
            }
            LogUtil.a("PluginDevice_MeasureHelper", "Found 1 product, ID: " + asString);
            if (c(measureResultListener, healthDeviceEntryApi, asString, asString2)) {
                return "";
            }
            this.f = measureResultListener;
            return asString;
        }
        LogUtil.a("PluginDevice_MeasureHelper", "productsList.size() > 1");
        this.f = measureResultListener;
        cpp.d("PluginDevice_MeasureHelper", this);
        e("unknown_device");
        return "";
    }

    private boolean c(MeasureResult.MeasureResultListener measureResultListener, HealthDeviceEntryApi healthDeviceEntryApi, String str, String str2) {
        if (healthDeviceEntryApi.isDeviceKitUniversal(str)) {
            c(str, str2);
            return false;
        }
        if (healthDeviceEntryApi.getBondedDevice(str) == null) {
            if (measureResultListener != null) {
                measureResultListener.onMeasureFailed(MeasureResult.MeasureErrorCode.NO_DEVICE);
            }
            LogUtil.b("PluginDevice_MeasureHelper", "Measure failed, getBondedDevice returned null:" + str);
            return true;
        }
        LogUtil.a("PluginDevice_MeasureHelper", "startMeasureBackground with product: " + str);
        cpp.d("PluginDevice_MeasureHelper", this);
        this.i = str;
        this.m = str2;
        e(str);
        return false;
    }

    private void e(String str) {
        if (BluetoothAdapter.getDefaultAdapter().getState() == 10) {
            LogUtil.b("PluginDevice_MeasureHelper", "Measure failed,the bluetooth is off");
            return;
        }
        Message obtain = Message.obtain();
        obtain.obj = str;
        obtain.what = 1;
        LogUtil.a("PluginDevice_MeasureHelper", "MeasureHelper sendMessage " + obtain.obj);
        cpp.Kk_("PluginDevice_MeasureHelper", obtain);
    }

    public void e(String str, String str2) {
        HealthDeviceEntryApi healthDeviceEntryApi = (HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class);
        if (healthDeviceEntryApi.isDeviceKitUniversal(str)) {
            healthDeviceEntryApi.stopMeasureUniversal(str, str2);
            healthDeviceEntryApi.cleanupMeasureUniversal(str, str2);
        } else {
            healthDeviceEntryApi.stopMeasure(str, str2);
        }
    }

    @Override // com.huawei.health.device.util.ISimpleHandler
    public void handleMessage(Message message) {
        LogUtil.a("PluginDevice_MeasureHelper", "MeasureHelper handleMessage");
        if (message == null) {
            LogUtil.h("PluginDevice_MeasureHelper", "MeasureHelper message is null");
            return;
        }
        if (message.what == 1) {
            if (message.obj == null) {
                LogUtil.h("PluginDevice_MeasureHelper", "message.obj is null");
                return;
            }
            String str = message.obj instanceof String ? (String) message.obj : null;
            cpp.b("PluginDevice_MeasureHelper", this);
            if ("cancelEnableBluetooth".equals(str)) {
                LogUtil.a("PluginDevice_MeasureHelper", "MeasureHelper message is cancelEnableBluetooth");
                return;
            } else {
                f();
                return;
            }
        }
        LogUtil.h("PluginDevice_MeasureHelper", "handleMessage default");
    }

    private void f() {
        final dfd dfdVar = new dfd(50001);
        ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).startMeasureHeartRate(HealthDevice.HealthDeviceKind.HDK_HEART_RATE, new dfd(50001) { // from class: dfg.3
            @Override // defpackage.dfd, com.huawei.health.device.callback.IHealthDeviceCallback
            public void onStatusChanged(HealthDevice healthDevice, int i) {
                super.onStatusChanged(healthDevice, i);
                LogUtil.a("PluginDevice_MeasureHelper", "onStatusChanged:", Integer.valueOf(i));
                if (dfg.this.f == null || i != 2) {
                    return;
                }
                dfg.this.f.onMeasureDevice(dfg.this.i, dfg.this.m, true);
            }
        }, this.e, new IHealthDeviceCallback() { // from class: dfg.1
            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onFailed(com.huawei.hihealth.device.open.HealthDevice healthDevice, int i) {
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onProgressChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, MeasureRecord measureRecord) {
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onDataChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, com.huawei.hihealth.device.open.data.MeasureResult measureResult) {
                dfg.this.a(measureResult, healthDevice, dfdVar);
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onStatusChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, int i) {
                LogUtil.a("PluginDevice_MeasureHelper", "-----heartRateData_onStatusChanged---:", Integer.valueOf(i));
            }
        }, this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        MeasureController measureController;
        this.g = str;
        this.l = str2;
        if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHonourDevice(str)) {
            com.huawei.hihealth.device.open.HealthDevice bondedDeviceUniversal = cei.b().getBondedDeviceUniversal(str, str2);
            MeasureKit healthDeviceKitUniversal = ((MeasureKitManagerApi) Services.c("PluginDevice", MeasureKitManagerApi.class)).getHealthDeviceKitUniversal("54C9739F-CA5C-4347-9F00-75B9DDF2C649");
            if (healthDeviceKitUniversal == null || (measureController = healthDeviceKitUniversal.getMeasureController()) == null) {
                return;
            }
            measureController.prepare(bondedDeviceUniversal, new IHealthDeviceCallback() { // from class: dfg.2
                @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
                public void onDataChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, com.huawei.hihealth.device.open.data.MeasureResult measureResult) {
                }

                @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
                public void onFailed(com.huawei.hihealth.device.open.HealthDevice healthDevice, int i) {
                }

                @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
                public void onProgressChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, MeasureRecord measureRecord) {
                }

                @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
                public void onStatusChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, int i) {
                    LogUtil.a("PluginDevice_MeasureHelper", "-----prepare_heartRateData_onStatusChanged---:" + i);
                    if (BaseApplication.isRunningForeground() && i == 2) {
                        dfg.this.i();
                    }
                }
            }, ((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).getAm16Bundle());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        final dfd dfdVar = new dfd(50001);
        ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).startMeasureHeartRate(HealthDevice.HealthDeviceKind.HDK_HEART_RATE, new dfd(50001), this.e, new IHealthDeviceCallback() { // from class: dfg.5
            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onFailed(com.huawei.hihealth.device.open.HealthDevice healthDevice, int i) {
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onProgressChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, MeasureRecord measureRecord) {
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onDataChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, com.huawei.hihealth.device.open.data.MeasureResult measureResult) {
                dfg.this.a(measureResult, healthDevice, dfdVar);
            }

            @Override // com.huawei.hihealth.device.open.IHealthDeviceCallback
            public void onStatusChanged(com.huawei.hihealth.device.open.HealthDevice healthDevice, int i) {
                LogUtil.a("PluginDevice_MeasureHelper", "-----heartRateData_onStatusChanged---:" + i);
                if (i == 2) {
                    if (dfg.this.g == null || !dfg.this.j) {
                        return;
                    }
                    dfg dfgVar = dfg.this;
                    dfgVar.c(dfgVar.g, dfg.this.l);
                    return;
                }
                if (i == 3) {
                    dfg.this.j = true;
                    return;
                }
                if (i == 14) {
                    dfg.this.j = false;
                } else {
                    if (i == 17) {
                        dfg.this.j = false;
                        dfg.this.g = null;
                        dfg.this.l = null;
                        return;
                    }
                    LogUtil.a("PluginDevice_MeasureHelper", "onStatusChanged default");
                }
            }
        }, this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.huawei.hihealth.device.open.data.MeasureResult measureResult, final com.huawei.hihealth.device.open.HealthDevice healthDevice, dfd dfdVar) {
        HealthData d2 = dic.c().d(measureResult);
        der derVar = d2 instanceof der ? (der) d2 : null;
        if (derVar != null) {
            LogUtil.c("PluginDevice_MeasureHelper", "-----heartRateData---:" + derVar.getHeartRate());
            HealthDevice healthDevice2 = new HealthDevice() { // from class: dfg.4
                @Override // com.huawei.health.device.model.HealthDevice
                public String getDeviceName() {
                    return healthDevice.getDeviceName();
                }

                @Override // com.huawei.health.device.model.HealthDevice
                public String getAddress() {
                    return healthDevice.getAddress();
                }

                @Override // com.huawei.health.device.model.HealthDevice
                public String getUniqueId() {
                    return healthDevice.getUniqueId();
                }
            };
            if (derVar.getHeartRate() != -1) {
                dfdVar.onDataChanged(healthDevice2, derVar);
            }
        }
    }

    public boolean b() {
        return this.c;
    }

    public void c(String str) {
        String e = dij.e(str);
        LogUtil.a("PluginDevice_MeasureHelper", "setHonorWeightScale prodId is：", e);
        this.c = "005A".equals(e) || "0059".equals(e);
        this.f11635a = ((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHuaweiWspScaleProduct(str);
        this.h = "2A19".equals(e);
    }

    public boolean a() {
        return this.f11635a;
    }

    public boolean c() {
        return this.h;
    }

    public void g() {
        this.c = false;
    }

    public boolean d(double d2, double d3) {
        boolean z = false;
        if (d2 <= 0.0d) {
            return false;
        }
        float f = ((MultiUsersManagerApi) Services.c("PluginDevice", MultiUsersManagerApi.class)).getCurrentUser().f();
        if (f <= 0.0f) {
            LogUtil.a("PluginDevice_MeasureHelper", "judgeIsReachedGoal startWeight <= 0 ");
            return false;
        }
        boolean z2 = d2 - ((double) f) <= 0.0d;
        LogUtil.a("PluginDevice_MeasureHelper", "isLooseWeight: " + z2);
        if (d3 <= d2 && z2) {
            LogUtil.a("PluginDevice_MeasureHelper", "looseWeight user reach goal");
            z = true;
        }
        if (d3 < d2 || z2) {
            return z;
        }
        LogUtil.a("PluginDevice_MeasureHelper", "gainWeight user reach goal");
        return true;
    }

    public int e() {
        return BaseApplication.getContext().getSharedPreferences("sleep_shared_pref_smart_msg", 0).getInt("weight_goal_over", 0);
    }
}
