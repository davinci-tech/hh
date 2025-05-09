package defpackage;

/* loaded from: classes6.dex */
public final class mok {
    public static String c(byte[] bArr) {
        StringBuilder sb = new StringBuilder(128);
        if (bArr != null) {
            for (byte b : bArr) {
                int i = b & 255;
                if (i < 16) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(i));
            }
        }
        return sb.toString();
    }
}
