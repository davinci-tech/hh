package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class dcr {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<dcu> f11591a;
    private String b;
    private ArrayList<dcu> c;
    private HealthDevice.HealthDeviceKind e;

    public dcr(HealthDevice.HealthDeviceKind healthDeviceKind, String str, String str2) {
        this.e = healthDeviceKind;
        this.b = str;
        LogUtil.c("ProductGroup", "icon=", str2);
        this.f11591a = new ArrayList<>();
        this.c = new ArrayList<>();
    }

    public HealthDevice.HealthDeviceKind c() {
        return (HealthDevice.HealthDeviceKind) cpt.d(this.e);
    }

    public String e() {
        return (String) cpt.d(this.b);
    }

    public ArrayList<dcu> d() {
        return this.f11591a;
    }

    public void c(dcu dcuVar) {
        this.f11591a.add(dcuVar);
    }

    public ArrayList<dcu> b() {
        return this.c;
    }

    public void b(dcu dcuVar) {
        this.c.add(dcuVar);
    }
}
