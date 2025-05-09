package defpackage;

/* loaded from: classes7.dex */
public class km {
    public static byte[] a(int i) {
        return new byte[]{(byte) ((i >> 24) % 256), (byte) ((i >> 16) % 256), (byte) ((i >> 8) % 256), (byte) (i % 256)};
    }
}
