package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cwl {
    public cwe a(String str) throws cwg {
        return a(str, 0);
    }

    private cwe a(String str, int i) throws cwg {
        cwe cweVar = new cwe();
        if (str == null) {
            LogUtil.h("TlvUtils", "hexString is null");
            return cweVar;
        }
        if (i > 50) {
            throw new cwg();
        }
        int i2 = 0;
        while (i2 != str.length()) {
            try {
                String substring = str.substring(i2, i2 + 2);
                d b = b(str, i2 + substring.length());
                int e = b.e();
                i2 = b.a();
                if (e == 0) {
                    cweVar.e().add(new cwd(substring, e, ""));
                } else {
                    int i3 = (e * 2) + i2;
                    if (i3 > str.length()) {
                        throw new cwg();
                    }
                    String substring2 = str.substring(i2, i3);
                    i2 += substring2.length();
                    if ((jds.c(substring, 16) >>> 7) == 1) {
                        cweVar.a().add(a(substring2, 1 + i));
                    } else {
                        cweVar.e().add(new cwd(substring, e, substring2));
                    }
                }
            } catch (IndexOutOfBoundsException e2) {
                LogUtil.b("TlvUtils", "builderTlvList Exception error,", ExceptionUtils.d(e2));
                throw new cwg();
            }
        }
        return cweVar;
    }

    private d b(String str, int i) throws cwg {
        int i2;
        try {
            int i3 = i + 2;
            int c = jds.c(str.length() >= i3 ? str.substring(i, i3) : null, 16);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (((c >>> 7) & 1) == 1) {
                if (i4 >= 4) {
                    throw new cwg();
                }
                int i7 = i + 2;
                int i8 = c & 127;
                if (i4 == 0) {
                    i5 = i8;
                } else if (i4 != 1) {
                    LogUtil.h("TlvUtils", "getLengthAndPosition default");
                } else {
                    i6 = i8;
                }
                c = jds.c(str.substring(i7, i + 4), 16);
                i4++;
                i = i7;
            }
            if (i4 != 2) {
                if (i4 == 1) {
                    i2 = i5 << 7;
                }
                return new d(c, i + 2);
            }
            i2 = (i5 << 14) | (i6 << 7);
            c |= i2;
            return new d(c, i + 2);
        } catch (IndexOutOfBoundsException unused) {
            throw new cwg();
        }
    }

    static class d {
        private int c;
        private int d;

        private d(int i, int i2) {
            this.d = i;
            this.c = i2;
        }

        public int e() {
            return this.d;
        }

        public int a() {
            return this.c;
        }
    }

    public static boolean b(byte[] bArr) {
        if (bArr != null && bArr.length > 4 && bArr[2] == Byte.MAX_VALUE) {
            String d2 = cvx.d(bArr);
            if (jds.c(d2.substring(8, d2.length()), 16) == 100009) {
                return true;
            }
        }
        return false;
    }
}
