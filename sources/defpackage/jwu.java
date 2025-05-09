package defpackage;

import java.util.List;

/* loaded from: classes5.dex */
public class jwu {
    public static byte[] c() {
        return cvx.a(cvx.e(1) + cvx.e(0));
    }

    public static byte[] b(long j, long j2) {
        String str = ("" + cvx.e(1)) + cvx.e(0);
        if (j != 0) {
            String str2 = (str + cvx.e(3) + cvx.e(4)) + cvx.b(j);
            if (j2 != 0) {
                str = (str2 + cvx.e(4) + cvx.e(4)) + cvx.b(j2);
            } else {
                str = str2;
            }
        }
        return cvx.a(str);
    }

    public static byte[] a(int i) {
        return cvx.a(((("" + cvx.e(129)) + cvx.e(4)) + cvx.e(2)) + cvx.e(2) + cvx.a(i));
    }

    public static byte[] e(int i, int i2) {
        return cvx.a(((((("" + cvx.e(129)) + cvx.e(8)) + cvx.e(2)) + cvx.e(2) + cvx.a(i)) + cvx.e(3)) + cvx.e(2) + cvx.a(i2));
    }

    public static byte[] e(long j, long j2) {
        String str = cvx.e(129) + cvx.e(0);
        if (j != 0) {
            String str2 = (str + cvx.e(3) + cvx.e(4)) + cvx.b(j);
            if (j2 != 0) {
                str = (str2 + cvx.e(4) + cvx.e(4)) + cvx.b(j2);
            } else {
                str = str2;
            }
        }
        return cvx.a(str);
    }

    public static byte[] e(int i) {
        return cvx.a(((("" + cvx.e(129)) + cvx.e(4)) + cvx.e(2)) + cvx.e(2) + cvx.a(i));
    }

    public static byte[] d(long j, long j2) {
        String str = cvx.e(129) + cvx.e(0);
        if (j != 0) {
            String str2 = (str + cvx.e(3) + cvx.e(4)) + cvx.b(j);
            if (j2 != 0) {
                str = (str2 + cvx.e(4) + cvx.e(4)) + cvx.b(j2);
            } else {
                str = str2;
            }
        }
        return cvx.a(str);
    }

    public static byte[] d(int i) {
        return cvx.a(((("" + cvx.e(129)) + cvx.e(4)) + cvx.e(2)) + cvx.e(2) + cvx.a(i));
    }

    public static byte[] d(List<jqe> list) {
        StringBuffer stringBuffer = new StringBuffer(16);
        int i = 0;
        for (jqe jqeVar : list) {
            stringBuffer.append(cvx.e(2));
            stringBuffer.append(cvx.e(5));
            stringBuffer.append(cvx.e(jqeVar.d()));
            stringBuffer.append(cvx.e(jqeVar.a()));
            stringBuffer.append(cvx.a(jqeVar.e()));
            stringBuffer.append(cvx.e(jqeVar.c()));
            i += 7;
        }
        return cvx.a(cvx.e(129) + cvx.e(i) + stringBuffer.toString());
    }

    public static byte[] e() {
        return cvx.a(cvx.e(1) + cvx.e(1) + cvx.e(1));
    }
}
