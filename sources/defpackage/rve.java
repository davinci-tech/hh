package defpackage;

import java.util.LinkedList;
import java.util.List;

/* loaded from: classes7.dex */
public class rve {

    /* renamed from: a, reason: collision with root package name */
    private float f16925a;
    private float b;
    private List<c> c = new LinkedList();
    private float[] d;
    private int e;

    public rve(float f, float f2, int i, List<c> list, float[] fArr) {
        this.d = null;
        this.b = f;
        this.f16925a = f2;
        this.e = i;
        if (fArr != null) {
            this.d = (float[]) fArr.clone();
        }
        if (list == null || list.size() <= 0) {
            return;
        }
        this.c.addAll(list);
    }

    public boolean f() {
        List<c> list = this.c;
        return (list == null || list.size() == 0) ? false : true;
    }

    public float d() {
        return this.b;
    }

    public float e() {
        return this.f16925a;
    }

    public int a() {
        return this.e;
    }

    public List<c> b() {
        return this.c;
    }

    public float[] c() {
        float[] fArr = this.d;
        return fArr == null ? new float[0] : (float[]) fArr.clone();
    }

    public static class c {
        private int b;
        private float c;
        private float e;

        public c(float f, float f2, int i) {
            this.c = f;
            this.e = f2;
            this.b = i;
        }

        public float e() {
            return this.c;
        }

        public float c() {
            return this.e;
        }

        public int a() {
            return this.b;
        }
    }
}
