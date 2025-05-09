package defpackage;

import java.io.ByteArrayOutputStream;

/* loaded from: classes8.dex */
public class eye {
    public static byte[] b(byte[] bArr) {
        byte[] bArr2 = new byte[0];
        ByteArrayOutputStream a2 = a(bArr);
        return a2 != null ? a2.toByteArray() : bArr2;
    }

    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0031: MOVE (r5 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:28:0x0031 */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0051 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.io.ByteArrayOutputStream a(byte[] r8) {
        /*
            java.lang.String r0 = "getDecompressByteArray close zip IOException"
            java.lang.String r1 = "CompressUtil"
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream
            r2.<init>()
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream
            r3.<init>(r8)
            r8 = 4096(0x1000, float:5.74E-42)
            byte[] r8 = new byte[r8]
            r4 = 0
            r5 = 0
            java.util.zip.GZIPInputStream r6 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35
        L19:
            int r3 = r6.read(r8)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L36
            r7 = -1
            if (r3 == r7) goto L24
            r2.write(r8, r4, r3)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L36
            goto L19
        L24:
            r6.close()     // Catch: java.io.IOException -> L28
            goto L4e
        L28:
            java.lang.Object[] r8 = new java.lang.Object[]{r0}
            defpackage.eym.c(r1, r8)
            goto L4e
        L30:
            r8 = move-exception
            r5 = r6
            goto L4f
        L33:
            r8 = move-exception
            goto L4f
        L35:
            r6 = r5
        L36:
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L30
            java.lang.String r2 = "getDecompressByteArray IOException"
            r8[r4] = r2     // Catch: java.lang.Throwable -> L30
            defpackage.eym.c(r1, r8)     // Catch: java.lang.Throwable -> L30
            if (r6 == 0) goto L4d
            r6.close()     // Catch: java.io.IOException -> L46
            goto L4d
        L46:
            java.lang.Object[] r8 = new java.lang.Object[]{r0}
            defpackage.eym.c(r1, r8)
        L4d:
            r2 = r5
        L4e:
            return r2
        L4f:
            if (r5 == 0) goto L5c
            r5.close()     // Catch: java.io.IOException -> L55
            goto L5c
        L55:
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            defpackage.eym.c(r1, r0)
        L5c:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eye.a(byte[]):java.io.ByteArrayOutputStream");
    }
}
