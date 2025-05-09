package com.huawei.hihealth.util;

import health.compact.a.util.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes.dex */
public class HiZipUtil {
    public static String d(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        try {
            gZIPOutputStream.write(str.getBytes("utf-8"));
            return byteArrayOutputStream.toString(r0);
        } finally {
            b(gZIPOutputStream);
            byteArrayOutputStream.toString("ISO-8859-1");
            b(byteArrayOutputStream);
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
                LogUtil.e("HiZipUtil", "An exception occurred while closing the 'Closeable' object.");
            }
        }
    }
}
