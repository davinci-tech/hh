package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jhy {
    private int d;
    private List<a> e = new ArrayList(16);

    public static class a {
        private int c;
        private int d;
        private int e;

        public int c() {
            return this.d;
        }

        public void d(int i) {
            this.d = i;
        }

        public int d() {
            return this.e;
        }

        public void c(int i) {
            this.e = i;
        }

        public int b() {
            return this.c;
        }

        public void a(int i) {
            this.c = i;
        }

        public String toString() {
            return "DesFrame{, startTime=" + this.d + ", endTime=" + this.e + ", sportType=" + this.c + '}';
        }
    }

    public List<a> e() {
        return this.e;
    }

    public void e(int i) {
        this.d = i;
    }

    public String toString() {
        return "DesFrame{index=" + this.d + ", datas=" + this.e + '}';
    }
}
