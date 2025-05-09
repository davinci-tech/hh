package defpackage;

import com.github.promeg.pinyinhelper.PinyinDict;
import com.github.promeg.pinyinhelper.SegmentationSelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class pf {
    static uxc b;
    static SegmentationSelector d;
    static List<PinyinDict> e;

    public static void c(b bVar) {
        if (bVar == null) {
            e = null;
            b = null;
            d = null;
        } else if (bVar.b()) {
            e = Collections.unmodifiableList(bVar.d());
            b = pk.e(bVar.d());
            d = bVar.e();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static b a() {
        return new b(null);
    }

    public static String d(String str, String str2) {
        return pg.c(str, b, e, str2, d);
    }

    public static String d(char c) {
        if (c(c)) {
            return c == 12295 ? "LING" : pj.f16153a[a(c)];
        }
        return String.valueOf(c);
    }

    public static boolean c(char c) {
        return (19968 <= c && c <= 40869 && a(c) > 0) || 12295 == c;
    }

    private static int a(char c) {
        int i = c - 19968;
        if (i >= 0 && i < 7000) {
            return b(pi.b, pi.f16142a, i);
        }
        if (7000 <= i && i < 14000) {
            return b(ph.c, ph.e, c - 26968);
        }
        return b(pl.b, pl.d, c - 33968);
    }

    private static short b(byte[] bArr, byte[] bArr2, int i) {
        short s = (short) (bArr2[i] & 255);
        return (bArr[i / 8] & pj.e[i % 8]) != 0 ? (short) (s | 256) : s;
    }

    /* loaded from: classes8.dex */
    public static final class b {
        SegmentationSelector b;
        List<PinyinDict> c;

        private b(List<PinyinDict> list) {
            if (list != null) {
                this.c = new ArrayList(list);
            }
            this.b = new pe();
        }

        public b c(PinyinDict pinyinDict) {
            if (pinyinDict != null) {
                List<PinyinDict> list = this.c;
                if (list == null) {
                    ArrayList arrayList = new ArrayList();
                    this.c = arrayList;
                    arrayList.add(pinyinDict);
                } else if (!list.contains(pinyinDict)) {
                    this.c.add(pinyinDict);
                }
            }
            return this;
        }

        boolean b() {
            return (d() == null || e() == null) ? false : true;
        }

        SegmentationSelector e() {
            return this.b;
        }

        List<PinyinDict> d() {
            return this.c;
        }
    }
}
