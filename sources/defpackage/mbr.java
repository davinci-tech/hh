package defpackage;

import android.text.TextUtils;

/* loaded from: classes9.dex */
public class mbr {
    public mbp a(String str) throws mbl {
        mbp mbpVar = new mbp();
        if (TextUtils.isEmpty(str)) {
            return mbpVar;
        }
        int i = 0;
        while (i != str.length()) {
            try {
                String substring = str.substring(i, i + 2);
                a d = d(str, i + substring.length());
                int d2 = d.d();
                i = d.c();
                if (d2 == 0) {
                    mbpVar.c().add(new mbn(substring, d2, ""));
                } else {
                    int i2 = (d2 * 2) + i;
                    if (i2 > str.length()) {
                        throw new mbl();
                    }
                    String substring2 = str.substring(i, i2);
                    i += substring2.length();
                    if ((Integer.parseInt(substring, 16) >>> 7) == 1) {
                        mbpVar.b().add(a(substring2));
                    } else {
                        mbpVar.c().add(new mbn(substring, d2, substring2));
                    }
                }
            } catch (IndexOutOfBoundsException | NumberFormatException unused) {
                throw new mbl();
            }
        }
        return mbpVar;
    }

    private a d(String str, int i) throws mbl {
        int i2;
        try {
            int parseInt = Integer.parseInt(str.substring(i, i + 2), 16);
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (((parseInt >>> 7) & 1) == 1) {
                if (i3 >= 4) {
                    throw new mbl();
                }
                int i6 = i + 2;
                int i7 = parseInt & 127;
                if (i3 == 0) {
                    i4 = i7;
                } else if (i3 == 1) {
                    i5 = i7;
                }
                parseInt = Integer.parseInt(str.substring(i6, i + 4), 16);
                i3++;
                i = i6;
            }
            if (i3 != 2) {
                if (i3 == 1) {
                    i2 = i4 * 128;
                }
                return new a(parseInt, i + 2);
            }
            i2 = (i4 * 16384) + (i5 * 128);
            parseInt += i2;
            return new a(parseInt, i + 2);
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
            throw new mbl();
        }
    }

    static class a {
        private int b;
        private int e;

        private a(int i, int i2) {
            this.e = i;
            this.b = i2;
        }

        public int d() {
            return this.e;
        }

        public int c() {
            return this.b;
        }
    }
}
