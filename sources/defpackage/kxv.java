package defpackage;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes5.dex */
public class kxv {
    private static kxv b;
    private MessageDigest d;

    public kxv() {
        try {
            this.d = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused) {
            LogUtil.b("Md5Calculator", "Md5Calculator NoSuchAlgorithmException error");
        }
    }

    private static kxv d() {
        if (b == null) {
            b = new kxv();
        }
        return b;
    }

    public static String e(String str) {
        kxv d = d();
        if (str == null) {
            LogUtil.h("Md5Calculator", "calculateMd5 filePath is null");
            return null;
        }
        d.b(str);
        byte[] digest = d.d.digest();
        d.d.reset();
        return c(digest, 0, digest.length);
    }

    public static String a(String str) {
        if (str == null) {
            LogUtil.h("Md5Calculator", "getMd5Value value is null");
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(str.getBytes("UTF-8"));
            messageDigest.reset();
            return c(digest, 0, digest.length);
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("Md5Calculator", "getMd5Value UnsupportedEncodingException error");
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            LogUtil.b("Md5Calculator", "getMd5Value NoSuchAlgorithmException error");
            return null;
        }
    }

    private static String c(byte[] bArr, int i, int i2) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder sb = new StringBuilder();
        for (int i3 = i; i3 < i + i2; i3++) {
            byte b2 = bArr[i3];
            sb.append(cArr[(b2 >>> 4) & 15]);
            sb.append(cArr[b2 & BaseType.Obj]);
        }
        return sb.toString();
    }

    private void b(String str) {
        FileInputStream fileInputStream;
        int read;
        FileInputStream fileInputStream2 = null;
        FileInputStream fileInputStream3 = null;
        FileInputStream fileInputStream4 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                read = fileInputStream.read(bArr);
                if (read > 0) {
                    this.d.update(bArr, 0, read);
                } else {
                    try {
                        break;
                    } catch (IOException unused3) {
                        LogUtil.b("Md5Calculator", "digestMd5 IOException fileInputStream close error");
                        fileInputStream2 = read;
                    }
                }
            }
            fileInputStream.close();
            fileInputStream2 = read;
        } catch (FileNotFoundException unused4) {
            fileInputStream3 = fileInputStream;
            LogUtil.b("Md5Calculator", "digestMd5 FileNotFoundException error");
            fileInputStream2 = fileInputStream3;
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                    fileInputStream2 = fileInputStream3;
                } catch (IOException unused5) {
                    LogUtil.b("Md5Calculator", "digestMd5 IOException fileInputStream close error");
                    fileInputStream2 = fileInputStream3;
                }
            }
        } catch (IOException unused6) {
            fileInputStream4 = fileInputStream;
            LogUtil.b("Md5Calculator", "digestMd5 IOException error");
            fileInputStream2 = fileInputStream4;
            if (fileInputStream4 != null) {
                try {
                    fileInputStream4.close();
                    fileInputStream2 = fileInputStream4;
                } catch (IOException unused7) {
                    LogUtil.b("Md5Calculator", "digestMd5 IOException fileInputStream close error");
                    fileInputStream2 = fileInputStream4;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused8) {
                    LogUtil.b("Md5Calculator", "digestMd5 IOException fileInputStream close error");
                }
            }
            throw th;
        }
    }
}
