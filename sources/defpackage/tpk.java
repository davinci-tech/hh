package defpackage;

import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorDataCallback;
import java.util.Objects;

/* loaded from: classes9.dex */
public class tpk {

    /* renamed from: a, reason: collision with root package name */
    private MonitorDataCallback f17325a;
    private int b;
    private String c;
    private Device d;
    private int e;

    public tpk(int i, int i2, MonitorDataCallback monitorDataCallback, Device device, String str) {
        this.b = i;
        this.e = i2;
        this.f17325a = monitorDataCallback;
        this.d = device;
        this.c = str;
    }

    public int b() {
        return this.b;
    }

    public int a() {
        return this.e;
    }

    public Device c() {
        return this.d;
    }

    public String e() {
        return this.c;
    }

    public MonitorDataCallback d() {
        return this.f17325a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof tpk) {
            tpk tpkVar = (tpk) obj;
            return this.b == tpkVar.b && this.e == tpkVar.e;
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.b), Integer.valueOf(this.e));
    }
}
