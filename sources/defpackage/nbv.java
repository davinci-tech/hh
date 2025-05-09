package defpackage;

import android.os.Build;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice;

/* loaded from: classes6.dex */
public class nbv extends AbsPrimaryDevice {
    private String b;
    private String e;

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice
    public int getPrimaryIDType() {
        return 0;
    }

    public void b(String str) {
        this.e = (String) jdy.d(str);
    }

    public String c() {
        return (String) jdy.d(this.e);
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice
    public String getPrimaryID() {
        return c();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice
    public String getTerminalId() {
        return b();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice
    public String getTerminalVendor() {
        return Build.MANUFACTURER;
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice
    public String getTerminalModel() {
        return Build.MODEL;
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice
    public String getTerminalSwVersion() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }
}
