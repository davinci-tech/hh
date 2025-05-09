package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class kbu {

    /* renamed from: a, reason: collision with root package name */
    private int f14264a;
    private long b;
    private int c;
    private String d;
    private DeviceInfo e;
    private int h;
    private List<kbt> i = new ArrayList(16);

    public void e(long j) {
        this.b = j;
    }

    public int b() {
        return this.f14264a;
    }

    public void a(int i) {
        this.f14264a = i;
    }

    public int c() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public int a() {
        return this.h;
    }

    public void b(int i) {
        this.h = i;
    }

    public void c(String str) {
        this.d = str;
    }

    public List<kbt> e() {
        return this.i;
    }

    public DeviceInfo d() {
        return this.e;
    }

    public void a(DeviceInfo deviceInfo) {
        this.e = deviceInfo;
    }

    public String toString() {
        return "{mDictTypeId:" + this.f14264a + this.i + "}";
    }
}
