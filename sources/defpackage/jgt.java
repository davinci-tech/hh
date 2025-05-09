package defpackage;

/* loaded from: classes5.dex */
public class jgt {
    public static int a(byte[] bArr) {
        int i = ((bArr[0] & 255) << 24) + ((bArr[1] & 255) << 16) + ((bArr[2] & 255) << 8) + (bArr[3] & 255);
        return i < 0 ? i + 256 : i;
    }
}
