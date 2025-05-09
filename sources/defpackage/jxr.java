package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jxr {

    /* renamed from: a, reason: collision with root package name */
    private List<e> f14174a = new ArrayList(16);
    private int e;

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private int f14175a;
        private int b;
        private int e;

        public int d() {
            return this.f14175a;
        }

        public void e(int i) {
            this.f14175a = i;
        }

        public int c() {
            return this.e;
        }

        public void b(int i) {
            this.e = i;
        }

        public int a() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public String toString() {
            return "DesFrame{, startTime=" + this.f14175a + ", endTime=" + this.e + ", sportType=" + this.b + '}';
        }
    }

    public List<e> d() {
        return this.f14174a;
    }

    public void e(int i) {
        this.e = i;
    }

    public String toString() {
        return "DesFrame{index=" + this.e + ", datas=" + this.f14174a + '}';
    }
}
