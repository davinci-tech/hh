package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bmn {
    public bmj c(String str) throws bmk {
        return c(str, 0);
    }

    private bmj c(String str, int i) throws bmk {
        bmj bmjVar = new bmj();
        if (str == null) {
            LogUtil.a("TlvUtils", "hexString is null");
            return bmjVar;
        }
        if (i > 50) {
            throw new bmk();
        }
        int i2 = 0;
        while (i2 != str.length()) {
            try {
                String substring = str.substring(i2, i2 + 2);
                a e = e(str, i2 + substring.length());
                int e2 = e.e();
                i2 = e.b();
                if (e2 == 0) {
                    bmjVar.b().add(new bmi(substring, e2, ""));
                } else {
                    int i3 = (e2 * 2) + i2;
                    if (i3 > str.length()) {
                        throw new bmk();
                    }
                    String substring2 = str.substring(i2, i3);
                    i2 += substring2.length();
                    if ((blu.b(substring, 16) >>> 7) == 1) {
                        bmjVar.e().add(c(substring2, 1 + i));
                    } else {
                        bmjVar.b().add(new bmi(substring, e2, substring2));
                    }
                }
            } catch (IndexOutOfBoundsException e3) {
                LogUtil.e("TlvUtils", "builderTlvList Exception error,", ExceptionUtils.d(e3));
                throw new bmk();
            }
        }
        return bmjVar;
    }

    public bmp e(bmp bmpVar, String str, int i) throws bmk {
        if (bmpVar == null || str == null || i < 0) {
            return null;
        }
        int i2 = 0;
        while (i2 != str.length()) {
            try {
                String substring = str.substring(i2, i2 + 2);
                int i3 = (i + i2) / 2;
                a e = e(str, i2 + substring.length());
                int e2 = e.e();
                i2 = e.b();
                if (e2 == 0) {
                    e(substring, i3, bmpVar);
                } else {
                    int i4 = (e2 * 2) + i2;
                    if (i4 > str.length()) {
                        throw new bmk();
                    }
                    String substring2 = str.substring(i2, i4);
                    int length = substring2.length() + i2;
                    c(bmpVar, i3, i + i2, substring, substring2);
                    i2 = length;
                }
            } catch (IndexOutOfBoundsException unused) {
                throw new bmk();
            }
        }
        return bmpVar;
    }

    private void e(String str, int i, bmp bmpVar) {
        String num = Integer.toString(blu.b(str, 16));
        if (num.charAt(0) == '0') {
            num = num.substring(1, num.length());
        }
        bmpVar.c().add(new bmp(i, num, num));
    }

    private void c(bmp bmpVar, int i, int i2, String str, String str2) throws bmk {
        if ((blu.b(str, 16) >>> 7) == 1) {
            String num = Integer.toString(blu.b(str, 16) & 127);
            if (num.charAt(0) == '0') {
                num = num.substring(1, num.length());
            }
            bmpVar.c().add(new bmp(i, num, num));
            e(bmpVar, str2, i2);
            return;
        }
        String num2 = Integer.toString(blu.b(str, 16));
        if (num2.charAt(0) == '0') {
            num2 = num2.substring(1, num2.length());
        }
        bmpVar.c().add(new bmp(i, num2, num2));
    }

    private a e(String str, int i) throws bmk {
        int i2;
        try {
            int i3 = i + 2;
            int b = blu.b(str.length() >= i3 ? str.substring(i, i3) : null, 16);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (((b >>> 7) & 1) == 1) {
                if (i4 >= 4) {
                    throw new bmk();
                }
                int i7 = i + 2;
                int i8 = b & 127;
                if (i4 == 0) {
                    i5 = i8;
                } else if (i4 != 1) {
                    LogUtil.a("TlvUtils", "getLengthAndPosition default");
                } else {
                    i6 = i8;
                }
                b = blu.b(str.substring(i7, i + 4), 16);
                i4++;
                i = i7;
            }
            if (i4 != 2) {
                if (i4 == 1) {
                    i2 = i5 << 7;
                }
                return new a(b, i + 2);
            }
            i2 = (i5 << 14) | (i6 << 7);
            b |= i2;
            return new a(b, i + 2);
        } catch (IndexOutOfBoundsException unused) {
            throw new bmk();
        }
    }

    static class a {
        private int c;
        private int d;

        private a(int i, int i2) {
            this.d = i;
            this.c = i2;
        }

        public int e() {
            return this.d;
        }

        public int b() {
            return this.c;
        }
    }
}
