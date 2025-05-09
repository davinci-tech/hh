package defpackage;

/* loaded from: classes7.dex */
public class ko {

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        public int f14445a;
        public int b;
        public int[] d;

        public c() {
            this.d = new int[256];
        }
    }

    public static byte[] d(byte[] bArr) {
        c c2;
        if (bArr == null || (c2 = c("QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK")) == null) {
            return null;
        }
        return e(bArr, c2);
    }

    public static c c(String str) {
        if (str == null) {
            return null;
        }
        c cVar = new c();
        for (int i = 0; i < 256; i++) {
            cVar.d[i] = i;
        }
        cVar.b = 0;
        cVar.f14445a = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            try {
                i3 = ((str.charAt(i2) + cVar.d[i4]) + i3) % 256;
                int i5 = cVar.d[i4];
                cVar.d[i4] = cVar.d[i3];
                cVar.d[i3] = i5;
                i2 = (i2 + 1) % str.length();
            } catch (Exception unused) {
                return null;
            }
        }
        return cVar;
    }

    public static byte[] e(byte[] bArr, c cVar) {
        if (bArr == null || cVar == null) {
            return null;
        }
        int i = cVar.b;
        int i2 = cVar.f14445a;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) % 256;
            int[] iArr = cVar.d;
            int i4 = iArr[i];
            i2 = (i2 + i4) % 256;
            iArr[i] = iArr[i2];
            iArr[i2] = i4;
            int i5 = iArr[i];
            bArr[i3] = (byte) (iArr[(i5 + i4) % 256] ^ bArr[i3]);
        }
        cVar.b = i;
        cVar.f14445a = i2;
        return bArr;
    }
}
