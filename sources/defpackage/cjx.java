package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.health.device.callback.HeartRateDeviceSelectedCallback;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.MeasureResult;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.wifi.entity.utils.JsonParser;
import com.huawei.health.devicemgr.api.constant.ExecuteMode;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.device.open.IDeviceEventHandler;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class cjx {
    private static cjx c;

    /* renamed from: a, reason: collision with root package name */
    private ceo f755a;
    private cjy b;
    private MeasurableDevice d;

    private cjx() {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter constructor");
        this.b = new cjy();
        this.f755a = ceo.d();
    }

    public static cjx e() {
        if (c == null) {
            c = new cjx();
        }
        return c;
    }

    public boolean j(String str) {
        return !koq.b(a(dks.c(str)));
    }

    public MeasureKit g(String str) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter getMeasureKit");
        return cfl.a().e(str);
    }

    public HealthDevice e(String str) {
        return ctq.c(str);
    }

    public ArrayList<String> c() {
        return this.f755a.i();
    }

    public ArrayList<ctk> a() {
        return this.f755a.a();
    }

    public HealthDevice a(String str) {
        return this.f755a.c(str);
    }

    public ArrayList<ContentValues> b(String str) {
        return this.f755a.a(str);
    }

    public HealthDevice c(String str) {
        return this.f755a.d(str, false);
    }

    public com.huawei.hihealth.device.open.HealthDevice d(String str) {
        return this.f755a.d(str);
    }

    public com.huawei.hihealth.device.open.HealthDevice c(String str, String str2) {
        return this.f755a.b(str, str2);
    }

    public ArrayList<String> a(HealthDevice.HealthDeviceKind healthDeviceKind) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter getBondedProducts");
        return this.f755a.c(healthDeviceKind);
    }

    public ArrayList<ContentValues> d(HealthDevice.HealthDeviceKind healthDeviceKind) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter getBondedProducts");
        return this.f755a.d(healthDeviceKind);
    }

    public ArrayList<String> b(HealthDevice.HealthDeviceKind healthDeviceKind) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter getBondedProductsForDeviceOnly");
        return this.f755a.a(healthDeviceKind);
    }

    public void b(ScanMode scanMode, List<bjf> list, DeviceScanCallback deviceScanCallback) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter scanDevice with UDS");
        this.f755a.a(scanMode, list, deviceScanCallback);
    }

    public boolean e(String str, IDeviceEventHandler iDeviceEventHandler) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter scanDeviceUniversal");
        if (iDeviceEventHandler == null) {
            return false;
        }
        return this.f755a.d(str, iDeviceEventHandler);
    }

    public boolean h(String str) {
        return this.f755a.j(str);
    }

    public void f() {
        this.f755a.h();
    }

    public boolean a(String str, HealthDevice healthDevice, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler) {
        dcz d = ResourceManager.e().d(str);
        if (d == null) {
            return false;
        }
        return b(str, d.s(), healthDevice, iDeviceEventHandler);
    }

    public boolean b(final String str, String str2, final HealthDevice healthDevice, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler) {
        boolean z;
        ReleaseLogUtil.e("R_Weight_Plugin_HealthDeviceEntry", "Enter bindDevice");
        if (healthDevice instanceof cfb) {
            z = this.f755a.e(str, healthDevice);
        } else if (healthDevice instanceof ctk) {
            z = this.f755a.a(str, str2, (ctk) healthDevice, iDeviceEventHandler);
        } else if (healthDevice instanceof MeasurableDevice) {
            MeasurableDevice measurableDevice = (MeasurableDevice) healthDevice;
            this.d = measurableDevice;
            z = this.f755a.e(str, str2, measurableDevice, iDeviceEventHandler);
        } else {
            LogUtil.a("Plugin_HealthDeviceEntry", "other device type");
            z = false;
        }
        jdx.b(new Runnable() { // from class: cjx.3
            @Override // java.lang.Runnable
            public void run() {
                cjx.this.d(str, healthDevice);
            }
        });
        return z;
    }

    public boolean c(String str, final String str2, String str3, final MeasurableDevice measurableDevice, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler) {
        String str4;
        String str5;
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter bindDevice");
        if (measurableDevice == null || TextUtils.isEmpty(measurableDevice.getUniqueId())) {
            LogUtil.h("Plugin_HealthDeviceEntry", "bindDevice device is null or device uniqueId is empty");
            iDeviceEventHandler.onStateChanged(-1);
            return false;
        }
        MeasurableDevice d = this.f755a.d(str, false);
        if (d != null) {
            str5 = d.getKind().name();
            str4 = d.getProductId();
        } else {
            str4 = "";
            str5 = str4;
        }
        if (TextUtils.isEmpty(str5) || TextUtils.isEmpty(str4)) {
            LogUtil.h("Plugin_HealthDeviceEntry", "attachKind or attachUuid is empty");
            iDeviceEventHandler.onStateChanged(-1);
            return false;
        }
        ContentValues Ec_ = this.f755a.Ec_(str2, str3, measurableDevice, "");
        Ec_.put("kind", HealthDevice.HealthDeviceKind.HDK_FITTINGS.name());
        Ec_.put("showInList", (Integer) 0);
        Ec_.put(KnitFragment.KEY_EXTRA, this.f755a.a(str5, str4, str, ""));
        boolean Ee_ = this.f755a.Ee_(str2, measurableDevice, iDeviceEventHandler, Ec_, "");
        if (Ee_) {
            ceo ceoVar = this.f755a;
            ceoVar.j(str, ceoVar.a("", str2, measurableDevice.getUniqueId(), str));
        }
        jdx.b(new Runnable() { // from class: cjx.4
            @Override // java.lang.Runnable
            public void run() {
                cjx.this.d(str2, measurableDevice);
            }
        });
        return Ee_;
    }

    public void a(String str, String str2, String str3, MeasurableDevice measurableDevice, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter bind fittings device");
        if (TextUtils.isEmpty(str) || measurableDevice == null || TextUtils.isEmpty(measurableDevice.getUniqueId())) {
            LogUtil.h("Plugin_HealthDeviceEntry", "bindDevice device is null or device uniqueId or attachUniqueId is empty");
            iDeviceEventHandler.onStateChanged(-1);
            return;
        }
        if (this.f755a.d(measurableDevice.getUniqueId(), false) == null) {
            LogUtil.a("Plugin_HealthDeviceEntry", "fittingDevice is null");
            c(str, str2, str3, measurableDevice, iDeviceEventHandler);
            return;
        }
        LogUtil.a("Plugin_HealthDeviceEntry", "device is not null");
        MeasurableDevice d = this.f755a.d(str, false);
        if (d == null) {
            LogUtil.a("Plugin_HealthDeviceEntry", "attachDevice is null");
            iDeviceEventHandler.onStateChanged(-1);
        } else {
            ceo ceoVar = this.f755a;
            ceoVar.j(str, ceoVar.a("", str2, measurableDevice.getUniqueId(), str));
            this.f755a.j(measurableDevice.getUniqueId(), this.f755a.a(d.getKind().name(), d.getProductId(), d.getUniqueId(), measurableDevice.getUniqueId()));
            measurableDevice.doCreateBond(iDeviceEventHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, HealthDevice healthDevice) {
        String uniqueId;
        if (str == null || healthDevice == null || (uniqueId = healthDevice.getUniqueId()) == null) {
            return;
        }
        dcz d = ResourceManager.e().d(str);
        MeasurableDevice d2 = ceo.d().d(uniqueId, false);
        if (d == null || d2 == null) {
            return;
        }
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("privacy_data_source_device_name_4F32F873AE094ACAA75E35D53690B72C");
        String str2 = dcx.d(str, d.n().b()) + Constants.LINK + cpa.f(uniqueId);
        String a2 = a(uniqueId, d2);
        String serialNumber = d2.getSerialNumber();
        if (userPreference != null) {
            Map<String, Object> c2 = JsonParser.c(userPreference.getValue());
            c2.put(a2, str2);
            if (!TextUtils.isEmpty(serialNumber)) {
                c2.put(uniqueId, str2);
            }
            String jSONObject = JsonParser.b(c2).toString();
            userPreference.setKey("privacy_data_source_device_name_4F32F873AE094ACAA75E35D53690B72C");
            userPreference.setValue(jSONObject);
            userPreference.setSyncStatus(0);
            LogUtil.a("Plugin_HealthDeviceEntry", "privacy hiUserPreference set ", Boolean.valueOf(HiHealthManager.d(BaseApplication.getContext()).setUserPreference(userPreference)));
            return;
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        HashMap hashMap = new HashMap();
        hashMap.put(a2, str2);
        if (!TextUtils.isEmpty(serialNumber)) {
            hashMap.put(uniqueId, str2);
        }
        String jSONObject2 = JsonParser.b(hashMap).toString();
        hiUserPreference.setKey("privacy_data_source_device_name_4F32F873AE094ACAA75E35D53690B72C");
        hiUserPreference.setValue(jSONObject2);
        LogUtil.a("Plugin_HealthDeviceEntry", "privacy hiUserPreference set ", Boolean.valueOf(HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference)));
    }

    private String a(String str, MeasurableDevice measurableDevice) {
        String serialNumber = measurableDevice.getSerialNumber();
        return !TextUtils.isEmpty(serialNumber) ? serialNumber : str;
    }

    public boolean a(String str, String str2, HealthDevice healthDevice, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter bindWiFiUpdateDevice");
        if (!(healthDevice instanceof ctk)) {
            return false;
        }
        return this.f755a.b(str, str2, (ctk) healthDevice, iDeviceEventHandler);
    }

    public boolean e(String str, String str2, com.huawei.hihealth.device.open.HealthDevice healthDevice, IDeviceEventHandler iDeviceEventHandler) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter bindDeviceUniversal");
        boolean a2 = this.f755a.a(str, str2, healthDevice, iDeviceEventHandler);
        cun.c().executeDeviceRelatedLogic(ExecuteMode.CLEAR_AM_16_CACHE, null, "Plugin_HealthDeviceEntry");
        return a2;
    }

    public boolean m(String str) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter unbindDevice");
        return this.f755a.g(str);
    }

    public boolean k(String str) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter unbindDevice");
        return this.f755a.f(str);
    }

    public boolean o(String str) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter unbindDevice");
        return this.f755a.n(str);
    }

    public boolean f(String str) {
        return this.f755a.k(str);
    }

    public boolean h(String str, String str2) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter unbindDeviceUniversal");
        boolean f = this.f755a.f(str, str2);
        cun.c().executeDeviceRelatedLogic(ExecuteMode.CLEAR_AM_16_CACHE, null, "Plugin_HealthDeviceEntry");
        return f;
    }

    public int d() {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter deleteWearDevice");
        return this.f755a.e();
    }

    public int h() {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter getWearDeviceSize");
        return this.f755a.j();
    }

    public boolean b() {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter cancelBinding");
        return this.f755a.b(this.d);
    }

    public boolean Gs_(String str, String str2, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle, MeasurableDevice measurableDevice) {
        ReleaseLogUtil.e("R_Weight_Plugin_HealthDeviceEntry", "Enter startMeasureDevice");
        return this.b.Gg_(str, str2, iHealthDeviceCallback, bundle, measurableDevice);
    }

    public boolean e(String str, String str2, IHealthDeviceCallback iHealthDeviceCallback) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter startBackgroundMeasure");
        Bundle bundle = new Bundle();
        bundle.putBoolean("isBackgroundMeasure", true);
        return this.b.Gf_(str, str2, iHealthDeviceCallback, bundle);
    }

    public boolean Gr_(String str, String str2, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter startMeasure");
        return this.b.Gf_(str, str2, iHealthDeviceCallback, bundle);
    }

    public boolean i(String str) {
        return this.b.a(str);
    }

    public boolean Gt_(String str, String str2, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle, boolean z) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter startMeasureReconnect");
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("Plugin_HealthDeviceEntry", "startMeasureReconnect uniqueId is empty");
            return this.b.Gh_(str, iHealthDeviceCallback, bundle, z);
        }
        return this.b.Gi_(str, str2, iHealthDeviceCallback, bundle, z);
    }

    public boolean Gu_(String str, String str2, com.huawei.hihealth.device.open.IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter startMeasureUniversal");
        return this.b.Gj_(str, str2, iHealthDeviceCallback, bundle);
    }

    public String b(HealthDevice.HealthDeviceKind healthDeviceKind, IHealthDeviceCallback iHealthDeviceCallback, HeartRateDeviceSelectedCallback heartRateDeviceSelectedCallback, com.huawei.hihealth.device.open.IHealthDeviceCallback iHealthDeviceCallback2, MeasureResult.MeasureResultListener measureResultListener) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter startMeasureHeartRate");
        return this.b.b(healthDeviceKind, iHealthDeviceCallback, heartRateDeviceSelectedCallback, iHealthDeviceCallback2, measureResultListener);
    }

    public void d(String str, String str2) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter stopMeasure");
        this.b.e(str, str2);
    }

    public void e(String str, String str2, int i) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter stopMeasureBleScale");
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("Plugin_HealthDeviceEntry", "stopMeasureBleScale unique is empty");
            this.b.d(str, i);
        } else {
            this.b.c(str, str2, i);
        }
    }

    public void a(String str, int i) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter stopMeasureBleScale");
        this.b.d(str, i);
    }

    public void e(String str, String str2) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter stopMeasureUniversal");
        this.b.b(str, str2);
    }

    public void a(String str, String str2) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter sendLocalBroadcast");
        this.f755a.c(str, str2);
    }

    public void a(String str, String str2, String str3) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter sendLocalBroadcast");
        this.f755a.c(str, str2, str3);
    }

    public void c(String str, String str2, String str3) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter sendLocalBroadcastUniversal");
        this.f755a.b(str, str2, str3);
    }

    public void b(String str, String str2) {
        LogUtil.a("Plugin_HealthDeviceEntry", "Enter cleanupMeasureUniversal");
        this.b.a(str, str2);
    }

    public boolean Gv_(Context context, ContentValues contentValues, String str) {
        return this.f755a.Ef_(context, contentValues, str);
    }

    public void g() {
        ContentValues Gq_ = Gq_();
        if (Gq_ == null) {
            LogUtil.h("Plugin_HealthDeviceEntry", "scale info is null");
            return;
        }
        String asString = Gq_.getAsString("productId");
        String asString2 = Gq_.getAsString("uniqueId");
        if (!cpa.am(asString) || TextUtils.isEmpty(asString2)) {
            LogUtil.h("Plugin_HealthDeviceEntry", "latest scale is not support, ", cpw.d(asString2));
            return;
        }
        dfg.d().c(asString);
        cld.c(asString, asString2);
        ReleaseLogUtil.e("R_Weight_Plugin_HealthDeviceEntry", "start connect latest scale, mac address:", cpw.d(asString2));
        MeasurableDevice d = ceo.d().d(asString2, true);
        if ((d instanceof cxh) && ((cxh) d).e()) {
            ReleaseLogUtil.e("R_Weight_Plugin_HealthDeviceEntry", "device has connected");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("type", -1);
        bundle.putString("productId", asString);
        bundle.putBoolean("isConnectAction", true);
        Gs_(asString, asString2, new IHealthDeviceCallback() { // from class: cjx.1
            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onFailed(HealthDevice healthDevice, int i) {
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onStatusChanged(HealthDevice healthDevice, int i) {
                LogUtil.a("Plugin_HealthDeviceEntry", "connect latest scale, onStatusChanged, device:", Integer.valueOf(System.identityHashCode(healthDevice)), ", status: ", Integer.valueOf(i));
            }
        }, bundle, d);
    }

    public ContentValues Gq_() {
        ArrayList<ContentValues> d = ceo.d().d(HealthDevice.HealthDeviceKind.HDK_WEIGHT);
        ContentValues contentValues = null;
        if (d == null || d.isEmpty()) {
            LogUtil.h("Plugin_HealthDeviceEntry", "contentValues is empty");
            return null;
        }
        cke ckeVar = new cke("deviceUsedTime");
        Iterator<ContentValues> it = d.iterator();
        long j = 0;
        while (it.hasNext()) {
            ContentValues next = it.next();
            String asString = next.getAsString("productId");
            String asString2 = next.getAsString("uniqueId");
            if (TextUtils.isEmpty(asString) || TextUtils.isEmpty(asString2)) {
                LogUtil.h("Plugin_HealthDeviceEntry", "get latest scale : productId or deviceIdentify is empty");
            } else {
                long b = ckeVar.b(cpp.a(), asString2);
                if (contentValues == null || j < b) {
                    contentValues = next;
                    j = b;
                }
            }
        }
        return contentValues;
    }
}
