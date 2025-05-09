package defpackage;

/* loaded from: classes7.dex */
public class usy {
    public static byte b(byte b, int i) {
        return (byte) (b | (1 << i));
    }

    public static boolean c(byte b, int i) {
        return (((long) b) & (1 << i)) != 0;
    }

    public static byte e(byte b, int i) {
        return (byte) (b & (~(1 << i)));
    }
}
