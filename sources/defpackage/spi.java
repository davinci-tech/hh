package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class spi {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f17199a = new Object();
    private String c;
    private int d;

    private spi() {
        this.c = "hw.unitedevice.";
        this.d = 1;
        LogUtil.c("HwP2pKitManager", "enter HwHiWearKitManager");
    }

    public int c() {
        int i;
        synchronized (f17199a) {
            i = (this.d + 1) % 32766;
            this.d = i;
        }
        return i;
    }

    public boolean c(byte[] bArr, spp sppVar, Map<Integer, PingCallback> map) {
        if (bArr != null && bArr[0] == 57 && bArr[1] == 1) {
            LogUtil.a("HwP2pKitManager", "isHealthResult sport bt-proxy");
            return true;
        }
        if (sppVar == null || map == null) {
            LogUtil.a("HwP2pKitManager", "isHealthResult input param is invalid.");
            return false;
        }
        String d2 = sppVar.d();
        if (d2 == null) {
            LogUtil.a("HwP2pKitManager", "isHealthResult destPkgName is invalid.");
            return false;
        }
        LogUtil.c("HwP2pKitManager", "isHealthResult destPkgName: ", d2);
        if (d2.startsWith(this.c)) {
            return true;
        }
        return d2.equals("com.huawei.health") && sppVar.i() == 3 && map.containsKey(Integer.valueOf(sppVar.e()));
    }

    public boolean c(DeviceInfo deviceInfo, byte[] bArr) {
        if (deviceInfo == null || bArr == null || bArr.length < 2) {
            LogUtil.a("HwP2pKitManager", "isP2pResult input param is invalid.");
            return false;
        }
        blt.c("HwP2pKitManager", bArr, "fuzzyDevice:", blt.a(deviceInfo), "isP2pResult. content:");
        byte b = bArr[0];
        if (b == 52 && bArr[1] == 1) {
            LogUtil.c("HwP2pKitManager", "isP2pResult p2p data received");
            return true;
        }
        if (b != 57 || bArr[1] != 1) {
            return false;
        }
        LogUtil.c("HwP2pKitManager", "isP2pResult p2p data received sport watch bt-proxy");
        return true;
    }

    public spp c(byte[] bArr) {
        List<bmi> d2 = sph.d(bArr);
        if (d2 == null || d2.isEmpty()) {
            LogUtil.a("HwP2pKitManager", "parseP2pResponse tlv format is invalid");
            return null;
        }
        spp sppVar = new spp();
        for (bmi bmiVar : d2) {
            switch (bli.e(bmiVar.e())) {
                case 1:
                    sppVar.a(sph.b(bmiVar));
                    break;
                case 2:
                    sppVar.e(sph.b(bmiVar));
                    break;
                case 3:
                    sppVar.e(blq.d(bmiVar.c()));
                    break;
                case 4:
                    sppVar.c(blq.d(bmiVar.c()));
                    break;
                case 5:
                    sppVar.b(blq.d(bmiVar.c()));
                    break;
                case 6:
                    sppVar.a(blq.d(bmiVar.c()));
                    break;
                case 7:
                    sppVar.c(blq.a(bmiVar.c()));
                    break;
                case 8:
                    sppVar.e(bmiVar);
                    break;
            }
        }
        return sppVar;
    }

    public spm d(byte[] bArr) {
        spm spmVar = new spm();
        List<bmi> e = sph.e(bArr);
        if (e == null || e.isEmpty()) {
            LogUtil.a("HwP2pKitManager", "parseP2pResponse payloadTlv format is invalid");
            return spmVar;
        }
        for (bmi bmiVar : e) {
            int e2 = bli.e(bmiVar.e());
            if (e2 == 1) {
                spmVar.b(sph.b(bmiVar));
            } else if (e2 == 2) {
                spmVar.e(blq.d(bmiVar.c()));
            } else if (e2 == 3) {
                spmVar.b(blq.d(bmiVar.c()));
            } else if (e2 == 4) {
                spmVar.d(blq.d(bmiVar.c()));
            }
        }
        return spmVar;
    }

    public UniteDevice e() {
        ArrayList<UniteDevice> arrayList = new ArrayList(bgl.c().getDeviceList().values());
        if (arrayList.isEmpty()) {
            return null;
        }
        for (UniteDevice uniteDevice : arrayList) {
            DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
            if (deviceInfo.isUsing() && deviceInfo.getDeviceConnectState() == 2 && deviceInfo.getDeviceType() > 0) {
                return uniteDevice;
            }
        }
        return null;
    }

    public UniteDevice d(String str) {
        LogUtil.c("HwP2pKitManager", "getConnectedDevice enter.");
        UniteDevice uniteDevice = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("HwP2pKitManager", "getConnectedDevice identify is Empty.");
            return null;
        }
        ArrayList arrayList = new ArrayList(bgl.c().getDeviceList().values());
        if (arrayList.isEmpty()) {
            LogUtil.a("HwP2pKitManager", "getConnectedDevice deviceList is Empty.");
            return null;
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            UniteDevice uniteDevice2 = (UniteDevice) it.next();
            if (d(uniteDevice2, uniteDevice2.getDeviceInfo(), str)) {
                uniteDevice = uniteDevice2;
                break;
            }
        }
        LogUtil.c("HwP2pKitManager", "getConnectedDevice deviceList end.");
        return uniteDevice;
    }

    private boolean d(UniteDevice uniteDevice, DeviceInfo deviceInfo, String str) {
        if (deviceInfo == null) {
            LogUtil.a("HwP2pKitManager", "deviceInfo is null.");
            return false;
        }
        if (str.equals(uniteDevice.getIdentify()) || str.equals(deviceInfo.getDeviceMac())) {
            return deviceInfo.getDeviceConnectState() == 2 && deviceInfo.getDeviceType() > 0;
        }
        LogUtil.a("HwP2pKitManager", "identify is not equal.");
        return false;
    }

    public static spi d() {
        return d.f17200a;
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static spi f17200a = new spi();
    }
}
