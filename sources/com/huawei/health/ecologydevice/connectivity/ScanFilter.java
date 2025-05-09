package com.huawei.health.ecologydevice.connectivity;

import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cxh;
import defpackage.cxi;
import defpackage.dks;
import java.util.UUID;

/* loaded from: classes3.dex */
public class ScanFilter {

    /* renamed from: a, reason: collision with root package name */
    private String[] f2280a = {"0000180d-0000-1000-8000-00805f9b34fb"};
    private String b;
    private MatchRule c;
    private String[] e;

    public enum MatchRule {
        STRICT,
        INCLUSIVE,
        FRONT,
        REAR
    }

    private ScanFilter(String str, String str2, MatchRule matchRule) {
        if (str != null) {
            this.e = str.split(";");
        }
        this.b = str2;
        this.c = matchRule;
    }

    public boolean QY_(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        try {
            String[] strArr = this.e;
            if (strArr == null || strArr.length <= 0) {
                return !TextUtils.isEmpty(this.b) && this.b.equals(bluetoothDevice.getAddress());
            }
            String name = bluetoothDevice.getName();
            if (name != null) {
                return a(name);
            }
            return false;
        } catch (SecurityException e) {
            LogUtil.b("ScanFilter", "matches SecurityException:", ExceptionUtils.d(e));
            return false;
        }
    }

    public boolean d(cxh cxhVar) {
        if (cxhVar == null) {
            return false;
        }
        String[] strArr = this.e;
        if (strArr == null || strArr.length <= 0) {
            return !TextUtils.isEmpty(this.b) && this.b.equals(cxhVar.getAddress());
        }
        String deviceName = cxhVar.getDeviceName();
        if (deviceName != null) {
            return a(deviceName);
        }
        return false;
    }

    public boolean e(byte[] bArr) {
        String[] strArr = this.e;
        if (strArr != null && bArr.length > 0) {
            for (String str : strArr) {
                if ("moredevice".equals(str) && dks.c(this.b) == HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
                    return cxi.b(bArr, UUID.fromString(this.f2280a[0]));
                }
            }
        }
        return false;
    }

    public boolean a(String str) {
        String[] strArr;
        if (!TextUtils.isEmpty(str) && (strArr = this.e) != null) {
            for (String str2 : strArr) {
                LogUtil.c("ScanFilter", "isDeviceNameMatched ScanFilter target name is ", str2, " and device name is ", str);
                if (this.c == MatchRule.STRICT) {
                    if (str.equals(str2)) {
                        return true;
                    }
                } else if (this.c == MatchRule.FRONT) {
                    if (str.startsWith(str2)) {
                        return true;
                    }
                } else if (this.c == MatchRule.INCLUSIVE) {
                    if (str.contains(str2)) {
                        return true;
                    }
                } else if (this.c == MatchRule.REAR && str.endsWith(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static ScanFilter b(String str, String str2, MatchRule matchRule) {
        return new ScanFilter(str, str2, matchRule);
    }
}
