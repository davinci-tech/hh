package com.huawei.hihealth;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

/* loaded from: classes.dex */
public class HiZipUtil {
    private HiZipUtil() {
    }

    public static void d(String str, OutputStream outputStream) throws IOException {
        if (str == null || str.length() == 0 || outputStream == null) {
            Log.e("HiZipUtil", "uncompress null param");
            return;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
        try {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                byte[] bArr = new byte[256];
                while (true) {
                    int read = gZIPInputStream.read(bArr);
                    if (read >= 0) {
                        outputStream.write(bArr, 0, read);
                    } else {
                        gZIPInputStream.close();
                        byteArrayInputStream.close();
                        return;
                    }
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String a(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        try {
            byte[] bArr = new byte[256];
            while (true) {
                int read = gZIPInputStream.read(bArr);
                if (read >= 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return byteArrayOutputStream.toString(r0);
                }
            }
        } finally {
            b(gZIPInputStream);
            byteArrayOutputStream.toString("utf-8");
            b(byteArrayInputStream);
            b(byteArrayOutputStream);
        }
    }

    private static void b(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                Log.e("HiZipUtil", "An exception occurred while closing the 'Closeable' object.");
            }
        }
    }
}
