package defpackage;

import com.huawei.health.ecologydevice.connectivity.ScanFilter;

/* loaded from: classes3.dex */
public class csr {

    /* renamed from: a, reason: collision with root package name */
    private ScanFilter f11448a;
    private int[] b;
    private String c;
    private String e;

    public String e() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public ScanFilter c() {
        return this.f11448a;
    }

    public void a(ScanFilter scanFilter) {
        this.f11448a = scanFilter;
    }

    public String a() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public int[] d() {
        int[] iArr = this.b;
        if (iArr == null) {
            return null;
        }
        return (int[]) iArr.clone();
    }

    public void c(int[] iArr) {
        if (iArr != null) {
            this.b = (int[]) iArr.clone();
        }
    }
}
