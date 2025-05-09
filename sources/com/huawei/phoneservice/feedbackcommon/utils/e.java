package com.huawei.phoneservice.feedbackcommon.utils;

import android.graphics.Bitmap;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes5.dex */
public final class e {
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00d5, code lost:
    
        if (r6 != null) goto L92;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00bb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0106 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.BufferedOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.io.File cfp_(android.content.Context r6, android.net.Uri r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 275
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedbackcommon.utils.e.cfp_(android.content.Context, android.net.Uri, java.lang.String):java.io.File");
    }

    public static File cfo_(Bitmap bitmap, String str) {
        BufferedOutputStream bufferedOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        File file = new File(str);
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                try {
                    file.createNewFile();
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
            }
        } catch (IOException e2) {
            com.huawei.phoneservice.faq.base.util.i.c("BitmapUtils", e2.getMessage());
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            byte[] bArr = new byte[102400];
            while (true) {
                int read = byteArrayInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e3) {
            e = e3;
            bufferedOutputStream2 = bufferedOutputStream;
            com.huawei.phoneservice.faq.base.util.i.c("BitmapUtils", e.getMessage());
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            return file;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e4) {
                    com.huawei.phoneservice.faq.base.util.i.c("BitmapUtils", e4.getMessage());
                }
            }
            throw th;
        }
        return file;
    }
}
