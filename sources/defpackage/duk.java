package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.hwhealthlinkage.linkageinterface.SportPostureLifecycle;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gsy;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class duk implements SportPostureLifecycle {

    /* renamed from: a, reason: collision with root package name */
    private dwj f11844a;
    private dwg b;
    private boolean c;
    private List<gsy.b> d;
    private Map<String, IBaseResponseCallback> e;
    private int f;
    private boolean g;
    private int h;
    private DeviceInfo i;
    private boolean j;
    private dwg m;

    private duk() {
        this.g = false;
        this.c = false;
        this.j = false;
        this.e = new HashMap(16);
        this.d = new ArrayList(10);
    }

    @Override // com.huawei.health.hwhealthlinkage.linkageinterface.SportPostureLifecycle
    public void openReport(int i, int i2) {
        this.f = i2;
        this.h = i;
        ReleaseLogUtil.e("HWhealthLinkage_Posture_SportPostureInteractor", "openReport sportType：", Integer.valueOf(i), ", sportStatus：", Integer.valueOf(i2), ", mIsWornFootBolt: ", Boolean.valueOf(this.g), ", mIsOnlyWornWaistBolt: ", Boolean.valueOf(this.c));
        if (i == 258 || i == 264) {
            i();
            e(i, i2);
            if (this.g || this.c) {
                return;
            } else {
                d(i, i2);
            }
        }
        if (i == 259) {
            e(i, i2);
            if (this.g) {
                return;
            }
            a(i, i2);
        }
    }

    @Override // com.huawei.health.hwhealthlinkage.linkageinterface.SportPostureLifecycle
    public void closeReport(int i, int i2) {
        this.f = i2;
        this.h = i;
        ReleaseLogUtil.e("HWhealthLinkage_Posture_SportPostureInteractor", "closeReport sportType：", Integer.valueOf(i), ", sportStatus：", Integer.valueOf(i2), ", mIsWornFootBolt: ", Boolean.valueOf(this.g), ", mIsOnlyWornWaistBolt: ", Boolean.valueOf(this.c));
        if (i == 258 || i == 264) {
            h(i, i2);
            h();
            if (this.g || this.c) {
                d(i2);
                return;
            }
            b(i, i2);
        }
        if (i == 259) {
            h(i, i2);
            if (this.g) {
                d(i2);
                return;
            }
            c(i, i2);
        }
        d(i2);
    }

    private void d(int i) {
        if (i == 4) {
            this.d = new ArrayList(10);
            this.j = false;
        }
        this.g = false;
        this.c = false;
    }

    @Override // com.huawei.health.hwhealthlinkage.linkageinterface.SportPostureLifecycle
    public void sendDataToDevice(Bundle bundle) {
        DeviceInfo deviceInfo;
        if (koq.c(this.d)) {
            this.b.d(5, this.h);
        }
        dwg dwgVar = this.m;
        if (dwgVar == null || (deviceInfo = this.i) == null) {
            return;
        }
        dwgVar.aan_(bundle, deviceInfo);
    }

    public List<gsy.b> d() {
        return this.d;
    }

    public int b() {
        return this.h;
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final duk f11846a = new duk();
    }

    public Map<String, IBaseResponseCallback> e() {
        return this.e;
    }

    public static duk a() {
        return b.f11846a;
    }

    public void d(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("Posture_SportPostureInteractor", "setBoltStatusCallback: invalid input");
        } else {
            this.e.put(str, iBaseResponseCallback);
        }
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Posture_SportPostureInteractor", "setBoltStatusCallback: invalid input");
        } else {
            this.e.remove(str);
        }
    }

    public List<DeviceInfo> c() {
        ArrayList arrayList = new ArrayList();
        if (koq.b(this.d)) {
            LogUtil.h("Posture_SportPostureInteractor", "getCurrentDevices failed, mBoltDeviceInfos is empty.");
            return arrayList;
        }
        Iterator<gsy.b> it = this.d.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().e());
        }
        return arrayList;
    }

    private void e(int i, int i2) {
        a(i);
        if (koq.c(this.d)) {
            this.b = new dwg();
            cuk.b().registerDeviceSampleListener("hw.sport.linkage", this.b);
            this.b.d(i2, i);
            j();
        }
    }

    private void d(int i, int i2) {
        List<DeviceCapability> c = dwo.d().c();
        if (koq.b(c)) {
            ReleaseLogUtil.e("HWhealthLinkage_Posture_SportPostureInteractor", "openPostureDataReport: healthDeviceList is empty.");
            return;
        }
        for (int i3 = 0; i3 < c.size(); i3++) {
            LogUtil.a("Posture_SportPostureInteractor", "openPostureDataReport: healthDevice: ", Integer.valueOf(i3), c.get(i3));
            boolean isSupportRunPosture = c.get(i3).isSupportRunPosture();
            this.j = isSupportRunPosture;
            if (isSupportRunPosture) {
                dwj dwjVar = new dwj();
                this.f11844a = dwjVar;
                dwjVar.e(i2, i);
                return;
            }
        }
    }

    private void a(int i, int i2) {
        dwj dwjVar = new dwj();
        this.f11844a = dwjVar;
        dwjVar.a(i2, i);
    }

    private void i() {
        if (dwi.a()) {
            this.i = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Posture_SportPostureInteractor");
            this.m = new dwg();
            cuk.b().registerDeviceSampleListener("hw.linkage.watch.posture.app", this.m);
        }
    }

    private void j() {
        gsy.b().c("Posture_SportPostureInteractor", new IBaseResponseCallback() { // from class: duk.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (duk.this.f == 1 || duk.this.f == 3) {
                    duk.this.b = new dwg();
                    cuk.b().registerDeviceSampleListener("hw.sport.linkage", duk.this.b);
                }
            }
        });
    }

    private void h(int i, int i2) {
        if (koq.c(this.d)) {
            this.b.d(i2, i);
            if (i2 == 2) {
                cuk.b().unRegisterDeviceSampleListener("hw.sport.linkage");
            } else {
                gsy.b().e("Posture_SportPostureInteractor");
            }
        }
    }

    private void b(int i, int i2) {
        if (this.j) {
            this.f11844a.c(i2, i);
        }
    }

    private void c(final int i, final int i2) {
        dwo.d().d(new IBaseResponseCallback() { // from class: duk.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                if (i3 == 100000 && "true".equals(obj)) {
                    LogUtil.a("Posture_SportPostureInteractor", "closeRidePosture support ride posture");
                    duk.this.f11844a.d(i2, i);
                }
            }
        });
    }

    private void h() {
        if (dwi.a()) {
            cuk.b().unRegisterDeviceSampleListener("hw.linkage.watch.posture.app");
        }
    }

    private void a(int i) {
        if (this.f == 1) {
            this.d = gsy.b().e(i);
        }
        if (koq.b(this.d)) {
            ReleaseLogUtil.d("HWhealthLinkage_Posture_SportPostureInteractor", "openPostureDataReport: boltDeviceInfos is empty.");
            return;
        }
        if (this.d.size() == 1 && this.d.get(0).b() == 1) {
            this.c = true;
            return;
        }
        Iterator<gsy.b> it = this.d.iterator();
        while (it.hasNext()) {
            if (it.next().b() == 0) {
                this.g = true;
                return;
            }
        }
    }
}
