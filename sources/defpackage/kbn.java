package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import java.util.List;

/* loaded from: classes5.dex */
public class kbn {

    /* renamed from: a, reason: collision with root package name */
    private int f14258a;
    private DeviceInfo d;
    private List<kbp> e;

    public int c() {
        return this.f14258a;
    }

    public void c(int i) {
        this.f14258a = i;
    }

    public DeviceInfo b() {
        return this.d;
    }

    public void e(DeviceInfo deviceInfo) {
        this.d = deviceInfo;
    }

    public List<kbp> a() {
        return this.e;
    }

    public void c(List<kbp> list) {
        this.e = list;
    }

    public String toString() {
        return "{mDigitTypeId:" + this.f14258a + this.e + "}";
    }
}
