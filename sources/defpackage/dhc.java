package defpackage;

import com.huawei.devicesdk.entity.DeviceInfo;

/* loaded from: classes3.dex */
public class dhc extends cxh {
    private String b;
    private DeviceInfo e;

    public dhc(String str, DeviceInfo deviceInfo, String str2) {
        this.b = str;
        if (deviceInfo == null) {
            this.e = new DeviceInfo();
        } else {
            this.e = deviceInfo;
        }
        super.setProductId(str2);
    }

    public String b() {
        return this.b;
    }

    public DeviceInfo a() {
        return this.e;
    }
}
