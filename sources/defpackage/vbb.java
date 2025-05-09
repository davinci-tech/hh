package defpackage;

import org.apache.commons.io.IOUtils;

/* loaded from: classes7.dex */
public class vbb {
    private static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String d(String str, boolean z) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((charAt == ' ' && z) || "=:#!\\".indexOf(charAt) >= 0) {
                sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                sb.append(charAt);
            } else {
                int indexOf = "\t\n\r\f".indexOf(charAt);
                if (indexOf >= 0) {
                    sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                    sb.append("tnrf".charAt(indexOf));
                } else if (charAt < ' ' || charAt >= 128) {
                    e(charAt, sb);
                } else {
                    sb.append(charAt);
                }
            }
        }
        return sb.toString();
    }

    public static String b(String str) {
        int i;
        char charAt;
        if (str == null) {
            return "#";
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 1);
        sb.append("# ");
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        while (i2 < length) {
            char charAt2 = str.charAt(i2);
            if (charAt2 == '\r' && (i = i2 + 1) < length && (charAt = str.charAt(i)) == '\n') {
                i2 = i;
                charAt2 = charAt;
            }
            if (charAt2 == '\n' || charAt2 == '\r' || (i3 > 64 && Character.isWhitespace(charAt2))) {
                sb.append(vcb.a());
                i3 = 0;
                z = true;
            } else {
                if (z) {
                    if (charAt2 != '#' && charAt2 != '!') {
                        sb.append('#');
                        sb.append(' ');
                    }
                    z = false;
                }
                if (charAt2 < ' ' || charAt2 >= 128) {
                    i3 += 6;
                    e(charAt2, sb);
                } else {
                    i3++;
                    sb.append(charAt2);
                }
            }
            i2++;
        }
        return sb.toString();
    }

    public static void e(char c2, StringBuilder sb) {
        sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
        sb.append('u');
        char[] cArr = c;
        sb.append(cArr[(c2 >> '\f') & 15]);
        sb.append(cArr[(c2 >> '\b') & 15]);
        sb.append(cArr[(c2 >> 4) & 15]);
        sb.append(cArr[c2 & 15]);
    }
}
