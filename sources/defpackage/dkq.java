package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.HEXUtils;
import health.compact.a.KeyValDbManager;

/* loaded from: classes3.dex */
public class dkq {

    /* renamed from: a, reason: collision with root package name */
    private static volatile dkq f11699a;
    private static final byte[] b = new byte[1];
    private String d;

    public static dkq c() {
        if (f11699a == null) {
            synchronized (b) {
                if (f11699a == null) {
                    f11699a = new dkq();
                }
            }
        }
        return f11699a;
    }

    public void b(String str) {
        this.d = str;
    }

    public void e(String str, String str2, String str3) {
        int i;
        LogUtil.a("EcologyDeviceInfoUtils", "enter setDateTimeByUds");
        if (cez.s.toString().equalsIgnoreCase(str2)) {
            i = 7;
        } else if (cez.p.toString().equalsIgnoreCase(str2)) {
            i = 10;
        } else {
            LogUtil.h("EcologyDeviceInfoUtils", "byteSize is 0");
            i = 0;
        }
        byte[] c = dks.c(i);
        biu biuVar = new biu();
        biuVar.a(str2);
        biuVar.d(c);
        d(biuVar, str, str2, str3, CharacterOperationType.WRITE);
    }

    public void d(biu biuVar, String str, String str2, String str3, CharacterOperationType characterOperationType) {
        if (biuVar == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.h("EcologyDeviceInfoUtils", "data is null || serviceUuid == null || characterUuid == null || mac == null");
            return;
        }
        synchronized (b) {
            UniteDevice d = d(str3, 2);
            if (d != null) {
                ddw.c().e(d, ddw.c().c(biuVar, str, str2, characterOperationType));
                LogUtil.a("EcologyDeviceInfoUtils", "setDateTime : data frames:", HEXUtils.a(biuVar.a()));
            } else {
                LogUtil.h("EcologyDeviceInfoUtils", "uniteDevice is null");
            }
        }
    }

    public UniteDevice d(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        UniteDevice uniteDevice = new UniteDevice();
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceMac(str);
        deviceInfo.setDeviceBtType(i);
        return uniteDevice.build(str, deviceInfo, new ExternalDeviceCapability());
    }

    public void a(biu biuVar, String str, String str2, String str3) {
        d(biuVar, str, str2, str3, CharacterOperationType.READ);
    }

    public boolean d() {
        LogUtil.a("EcologyDeviceInfoUtils", "enter isSupportUds mHealthDeviceKind=", this.d);
        boolean parseBoolean = Boolean.parseBoolean((HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE.name().equals(this.d) || HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR.name().equals(this.d) || HealthDevice.HealthDeviceKind.HDK_HEART_RATE.name().equals(this.d)) ? KeyValDbManager.b(BaseApplication.getContext()).e("use_unite_device_service_key") : "");
        Object[] objArr = new Object[1];
        objArr[0] = parseBoolean ? "isSupportUds() now is new mode" : "isSupportUds() now is old mode";
        LogUtil.a("EcologyDeviceInfoUtils", objArr);
        return parseBoolean;
    }

    public void a(byte[] bArr, String str) {
        biu biuVar = new biu();
        biuVar.a(cez.an.toString());
        biuVar.d(bArr);
        d(biuVar, cez.aa.toString(), cez.an.toString(), str, CharacterOperationType.WRITE);
    }
}
