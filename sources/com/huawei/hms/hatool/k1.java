package com.huawei.hms.hatool;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.zip.Deflater;

/* loaded from: classes4.dex */
public final class k1 {
    public static byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[1024];
        while (!deflater.finished()) {
            byteArrayOutputStream.write(bArr2, 0, deflater.deflate(bArr2));
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        deflater.end();
        a((OutputStream) byteArrayOutputStream);
        return byteArray;
    }

    public static void a(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.getInputStream().close();
        } catch (Exception unused) {
            v.f("hmsSdk", "closeQuietly(): Exception when connHttp.getInputStream()!,There may be no network, or no INTERNET permission");
        }
        httpURLConnection.disconnect();
        v.a("hmsSdk", " connHttp disconnect");
    }

    private static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
                v.f("hmsSdk", "closeStream(): Exception: close OutputStream error!");
            }
        }
    }

    public static void a(File file, String str) {
        String str2;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(str.getBytes("UTF-8"));
                    fileOutputStream.flush();
                } catch (FileNotFoundException unused) {
                    fileOutputStream2 = fileOutputStream;
                    str2 = "saveInfoToFile(): No files need to be read";
                    fileOutputStream = fileOutputStream2;
                    v.f("hmsSdk", str2);
                    a((Closeable) fileOutputStream);
                } catch (IOException unused2) {
                    fileOutputStream2 = fileOutputStream;
                    str2 = "saveInfoToFile(): io exc from write info to file!";
                    fileOutputStream = fileOutputStream2;
                    v.f("hmsSdk", str2);
                    a((Closeable) fileOutputStream);
                }
            } catch (Throwable th) {
                th = th;
                fileOutputStream2 = fileOutputStream;
                a((Closeable) fileOutputStream2);
                throw th;
            }
        } catch (FileNotFoundException unused3) {
        } catch (IOException unused4) {
        } catch (Throwable th2) {
            th = th2;
            a((Closeable) fileOutputStream2);
            throw th;
        }
        a((Closeable) fileOutputStream);
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                v.f("hmsSdk", "closeQuietly(): Exception when closing the closeable!");
            }
        }
    }

    public static String a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toString("UTF-8");
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } finally {
            a((Closeable) byteArrayOutputStream);
        }
    }

    public static String a(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        }
        try {
            h hVar = new h(1024);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                hVar.a(bArr, read);
            }
            if (hVar.b() == 0) {
                a((Closeable) fileInputStream);
                return "";
            }
            String str = new String(hVar.a(), "UTF-8");
            a((Closeable) fileInputStream);
            return str;
        } catch (FileNotFoundException unused3) {
            fileInputStream2 = fileInputStream;
            v.f("hmsSdk", "getInfoFromFile(): No files need to be read");
            a((Closeable) fileInputStream2);
            return "";
        } catch (IOException unused4) {
            fileInputStream2 = fileInputStream;
            v.f("hmsSdk", "getInfoFromFile(): stream.read or new string exception");
            a((Closeable) fileInputStream2);
            return "";
        } catch (Throwable th2) {
            th = th2;
            a((Closeable) fileInputStream);
            throw th;
        }
    }
}
