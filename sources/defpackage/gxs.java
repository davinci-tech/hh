package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gxs {

    /* renamed from: a, reason: collision with root package name */
    private float f12994a;
    private float b;
    private float c;
    private float d;
    private float e;
    private String i;

    public String g() {
        return this.i;
    }

    public void d(String str) {
        this.i = str;
    }

    public float a() {
        return this.b;
    }

    public void e(float f) {
        this.b = f;
    }

    public float b() {
        return this.f12994a;
    }

    public void d(float f) {
        this.f12994a = f;
    }

    public float e() {
        return this.e;
    }

    public void b(float f) {
        this.e = f;
    }

    public float d() {
        return this.c;
    }

    public void a(float f) {
        this.c = f;
    }

    public float c() {
        return this.d;
    }

    public void c(float f) {
        this.d = f;
    }

    public void b(int[] iArr, String str) {
        if (iArr == null) {
            LogUtil.h("Track_PaceZoneStatisticData", "paceZoneArray null");
            return;
        }
        this.i = str;
        this.b = iArr[0];
        this.f12994a = iArr[1];
        this.e = iArr[2];
        this.c = iArr[3];
        this.d = iArr[4];
    }

    public void a(float[] fArr, String str) {
        if (fArr == null) {
            LogUtil.h("Track_PaceZoneStatisticData", "paceZoneArray null");
            return;
        }
        this.i = str;
        this.b = fArr[0];
        this.f12994a = fArr[1];
        this.e = fArr[2];
        this.c = fArr[3];
        this.d = fArr[4];
    }
}
