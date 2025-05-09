package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.bir;
import defpackage.bjf;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ddw {

    /* renamed from: a, reason: collision with root package name */
    private dgw f11617a;
    private int b;
    private UniteDevice c;
    private snq d;
    private Context e;

    private ddw() {
        this.b = 3;
        this.e = BaseApplication.getContext();
        snq c = snq.c();
        this.d = c;
        c.a(this.e);
    }

    public static ddw c() {
        return d.b;
    }

    public void a(String str, DeviceStatusChangeCallback deviceStatusChangeCallback) {
        this.d.registerDeviceStateListener(str, deviceStatusChangeCallback);
    }

    public void e(String str, MessageReceiveCallback messageReceiveCallback) {
        this.d.registerDeviceMessageListener(str, messageReceiveCallback);
    }

    public void d(String str) {
        this.d.unregisterDeviceStateListener(str);
    }

    public void a(String str) {
        this.d.unregisterDeviceMessageListener(str);
    }

    public void b(ScanMode scanMode, List<bjf> list, DeviceScanCallback deviceScanCallback) {
        this.d.scanDevice(scanMode, list, deviceScanCallback);
    }

    public List<bjf> c(String str) {
        bjf.a aVar = new bjf.a();
        aVar.e(2);
        dcz d2 = ResourceManager.e().d(str);
        aVar.a((d2 == null || d2.aa() == null || !koq.d(d2.aa(), 0) || d2.aa().get(0) == null) ? "" : d2.aa().get(0).d());
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(aVar.a());
        return arrayList;
    }

    public List<bjf> b() {
        ArrayList arrayList = new ArrayList(10);
        bjf.a aVar = new bjf.a();
        aVar.e(2);
        aVar.a("moredevice");
        arrayList.add(aVar.a());
        return arrayList;
    }

    public List<bjf> b(String str, String str2) {
        ArrayList arrayList = new ArrayList(10);
        bjf.a aVar = new bjf.a();
        if (!TextUtils.isEmpty(str)) {
            aVar.a(str);
            aVar.e(1);
        } else if (!TextUtils.isEmpty(str2)) {
            aVar.a(str2);
            aVar.e(5);
        } else {
            LogUtil.h("ThirdPartyDeviceManager", "getFiltersExact is empty");
            return arrayList;
        }
        arrayList.add(aVar.a());
        return arrayList;
    }

    public void c(dgw dgwVar, int i) {
        ArrayList<dhc> d2 = dgwVar.d();
        if (koq.d(d2, i)) {
            dhc dhcVar = d2.get(i);
            this.c = new UniteDevice().build(dhcVar.b(), dhcVar.a(), null);
        }
    }

    public void c(dhc dhcVar) {
        this.d.createSystemBond(new UniteDevice().build(dhcVar.b(), dhcVar.a(), null));
    }

    public void d(UniteDevice uniteDevice) {
        this.d.createSystemBond(uniteDevice);
    }

    public void e(UniteDevice uniteDevice, ConnectMode connectMode, int i, boolean z) {
        if (uniteDevice == null || uniteDevice.getDeviceInfo() == null) {
            LogUtil.h("ThirdPartyDeviceManager", "connectDevice : device == null || deviceInfo == null");
            return;
        }
        this.c = uniteDevice;
        uniteDevice.getDeviceInfo().setDeviceBtType(i);
        this.d.connectDevice(uniteDevice, z, connectMode);
    }

    public void e(UniteDevice uniteDevice, bir birVar) {
        this.d.sendCommand(uniteDevice, birVar);
    }

    public void b(UniteDevice uniteDevice, String str, String str2, boolean z) {
        this.d.setCharacteristicNotify(uniteDevice, str, str2, SendMode.PROTOCOL_TYPE_DIRECT, z);
    }

    public void a() {
        UniteDevice uniteDevice = this.c;
        if (uniteDevice != null) {
            b(uniteDevice);
        }
    }

    public void b(UniteDevice uniteDevice) {
        this.d.disconnect(uniteDevice);
    }

    public bir c(biu biuVar, String str, String str2, CharacterOperationType characterOperationType) {
        bir birVar = new bir();
        if (biuVar == null) {
            LogUtil.h("ThirdPartyDeviceManager", "getBloodPressureCommand : dataContents == null");
            return birVar;
        }
        bir birVar2 = new bir();
        birVar2.c(str);
        birVar2.b(str2);
        birVar2.e(biuVar.a());
        birVar2.b(SendMode.PROTOCOL_TYPE_DIRECT);
        bir.a aVar = new bir.a();
        aVar.d(birVar2.h());
        aVar.c(false);
        aVar.d(characterOperationType);
        return aVar.b(birVar2);
    }

    public void e(int i) {
        this.b = i;
    }

    public int e() {
        return this.b;
    }

    public void b(dgw dgwVar) {
        this.f11617a = dgwVar;
    }

    public dgw d() {
        return this.f11617a;
    }

    static class d {
        private static ddw b = new ddw();
    }
}
