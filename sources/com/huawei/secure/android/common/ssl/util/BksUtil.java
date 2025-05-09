package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes6.dex */
public class BksUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8631a = "BksUtil";
    private static final String b = "com.huawei.hwid";
    private static final String c = "com.huawei.hwid";
    private static final String d = "com.huawei.hms";
    private static final String e = "com.huawei.hwid.tv";
    private static final String g = "files/hmsrootcas.bks";
    private static final String h = "4.0.2.300";
    private static final String i = "aegis";
    private static final String j = "hmsrootcas.bks";
    private static final long k = 604800000;
    private static final String l = "last_update_time";
    private static final String m = "B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05";
    private static final String n = "3517262215D8D3008CBF888750B6418EDC4D562AC33ED6874E0D73ABA667BC3C";
    private static final String o = "E49D5C2C0E11B3B1B96CA56C6DE2A14EC7DAB5CCC3B5F300D03E5B4DBA44F539";
    private static final String q = "";
    private static final String r = "bks_hash";
    private static final Uri f = Uri.parse("content://com.huawei.hwid");
    private static final String[] p = {"B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05", "E49D5C2C0E11B3B1B96CA56C6DE2A14EC7DAB5CCC3B5F300D03E5B4DBA44F539"};

    private BksUtil() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    private static void a(InputStream inputStream, Context context) {
        FileOutputStream fileOutputStream;
        if (inputStream == null || context == null) {
            return;
        }
        String a2 = a(context);
        if (!new File(a2).exists()) {
            a(a2);
        }
        File file = new File(a2, "hmsrootcas.bks");
        if (file.exists()) {
            file.delete();
        }
        ?? r7 = 0;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                d.c(f8631a, "write output stream ");
                fileOutputStream = new FileOutputStream(file);
                r7 = 2048;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = r7;
            }
        } catch (IOException unused) {
        }
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int read = inputStream.read(bArr, 0, 2048);
                if (read == -1) {
                    break;
                } else {
                    fileOutputStream.write(bArr, 0, read);
                }
            }
            c.a((OutputStream) fileOutputStream);
        } catch (IOException unused2) {
            fileOutputStream2 = fileOutputStream;
            d.b(f8631a, " IOException");
            c.a((OutputStream) fileOutputStream2);
            r7 = fileOutputStream2;
        } catch (Throwable th2) {
            th = th2;
            c.a((OutputStream) fileOutputStream);
            throw th;
        }
    }

    private static boolean a(int i2) {
        return i2 >= 40002300;
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x00a4: MOVE (r2 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:43:0x00a3 */
    private static void b(Context context) {
        InputStream inputStream;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        ByteArrayInputStream byteArrayInputStream2;
        ByteArrayOutputStream byteArrayOutputStream3 = null;
        r2 = null;
        ByteArrayInputStream byteArrayInputStream3 = null;
        try {
            try {
                inputStream = context.getContentResolver().openInputStream(Uri.withAppendedPath(f, g));
            } catch (Throwable th) {
                th = th;
                byteArrayInputStream = null;
                byteArrayOutputStream3 = byteArrayOutputStream;
            }
        } catch (Exception e2) {
            e = e2;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        try {
            byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= -1) {
                        break;
                    } else {
                        byteArrayOutputStream2.write(bArr, 0, read);
                    }
                }
                byteArrayOutputStream2.flush();
                byteArrayInputStream2 = new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
            } catch (Exception e3) {
                e = e3;
            }
            try {
                String a2 = f.a(r, "", context);
                String b2 = b(byteArrayOutputStream2.toByteArray());
                if (d(context) && a2.equals(b2)) {
                    d.c(f8631a, "bks not update");
                    c.a(inputStream);
                    c.a((OutputStream) byteArrayOutputStream2);
                    c.a((InputStream) byteArrayInputStream2);
                }
                d.c(f8631a, "update bks and sp");
                a(byteArrayInputStream2, context);
                f.b(r, b2, context);
                c.a(inputStream);
                c.a((OutputStream) byteArrayOutputStream2);
                c.a((InputStream) byteArrayInputStream2);
            } catch (Exception e4) {
                e = e4;
                byteArrayInputStream3 = byteArrayInputStream2;
                d.b(f8631a, "Get bks from HMS_VERSION_CODE exception : No content provider" + e.getMessage());
                c.a(inputStream);
                c.a((OutputStream) byteArrayOutputStream2);
                c.a((InputStream) byteArrayInputStream3);
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream3 = byteArrayOutputStream2;
                byteArrayInputStream = byteArrayInputStream2;
                c.a(inputStream);
                c.a((OutputStream) byteArrayOutputStream3);
                c.a((InputStream) byteArrayInputStream);
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            byteArrayOutputStream2 = null;
            d.b(f8631a, "Get bks from HMS_VERSION_CODE exception : No content provider" + e.getMessage());
            c.a(inputStream);
            c.a((OutputStream) byteArrayOutputStream2);
            c.a((InputStream) byteArrayInputStream3);
        } catch (Throwable th4) {
            th = th4;
            byteArrayInputStream = null;
            c.a(inputStream);
            c.a((OutputStream) byteArrayOutputStream3);
            c.a((InputStream) byteArrayInputStream);
            throw th;
        }
    }

    private static String c(Context context) {
        return a(context) + File.separator + "hmsrootcas.bks";
    }

    private static boolean d(Context context) {
        return new File(a(context) + File.separator + "hmsrootcas.bks").exists();
    }

    public static InputStream getBksFromTss(Context context) {
        synchronized (BksUtil.class) {
            d.c(f8631a, "get bks from tss begin");
            if (context != null) {
                ContextUtil.setContext(context);
            }
            Context contextUtil = ContextUtil.getInstance();
            if (contextUtil == null) {
                d.b(f8631a, "context is null");
                return null;
            }
            if (!b(e.a("com.huawei.hwid")) && !b(e.a("com.huawei.hms"))) {
                d.b(f8631a, "hms version code is too low : " + e.a("com.huawei.hwid"));
                return null;
            }
            if (!c(contextUtil, "com.huawei.hwid") && !b(contextUtil, "com.huawei.hms")) {
                d.b(f8631a, "hms sign error");
                return null;
            }
            b(contextUtil);
            return getFilesBksIS(contextUtil);
        }
    }

    public static InputStream getFilesBksIS(Context context) {
        if (!d(context)) {
            return null;
        }
        d.c(f8631a, "getFilesBksIS ");
        try {
            return new FileInputStream(c(context));
        } catch (FileNotFoundException unused) {
            d.b(f8631a, "FileNotFoundExceptio: ");
            return null;
        }
    }

    private static boolean c(Context context, String str) {
        byte[] a2 = a(context, str);
        for (String str2 : p) {
            if (str2.equalsIgnoreCase(c(a2))) {
                return true;
            }
        }
        return false;
    }

    private static String c(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            try {
                return a(MessageDigest.getInstance("SHA-256").digest(bArr));
            } catch (NoSuchAlgorithmException e2) {
                Log.e(f8631a, "NoSuchAlgorithmException" + e2.getMessage());
            }
        }
        return "";
    }

    private static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        File file = new File(str);
        if (file.exists()) {
            d.e(f8631a, "The directory  has already exists");
            return 1;
        }
        if (file.mkdirs()) {
            d.a(f8631a, "create directory  success");
            return 0;
        }
        d.b(f8631a, "create directory  failed");
        return -1;
    }

    private static String a(Context context) {
        return context.createDeviceProtectedStorageContext().getFilesDir() + File.separator + i;
    }

    private static byte[] a(Context context, String str) {
        PackageInfo packageInfo;
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null && (packageInfo = packageManager.getPackageInfo(str, 64)) != null) {
                    return packageInfo.signatures[0].toByteArray();
                }
            } catch (PackageManager.NameNotFoundException e2) {
                Log.e(f8631a, "PackageManager.NameNotFoundException : " + e2.getMessage());
            } catch (Exception e3) {
                Log.e(f8631a, "get pm exception : " + e3.getMessage());
            }
            return new byte[0];
        }
        Log.e(f8631a, "packageName is null or context is null");
        return new byte[0];
    }

    private static boolean b(String str) {
        int parseInt;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        d.c(f8631a, "hms version code is : " + str);
        String[] split = str.split("\\.");
        String[] split2 = h.split("\\.");
        int length = split.length;
        int length2 = split2.length;
        int max = Math.max(length, length2);
        int i2 = 0;
        while (i2 < max) {
            if (i2 < length) {
                try {
                    parseInt = Integer.parseInt(split[i2]);
                } catch (Exception e2) {
                    d.b(f8631a, " exception : " + e2.getMessage());
                    return i2 >= length2;
                }
            } else {
                parseInt = 0;
            }
            int parseInt2 = i2 < length2 ? Integer.parseInt(split2[i2]) : 0;
            if (parseInt < parseInt2) {
                return false;
            }
            if (parseInt > parseInt2) {
                return true;
            }
            i2++;
        }
        return true;
    }

    private static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private static boolean b(Context context, String str) {
        return "E49D5C2C0E11B3B1B96CA56C6DE2A14EC7DAB5CCC3B5F300D03E5B4DBA44F539".equalsIgnoreCase(c(a(context, str)));
    }

    private static String b(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            d.b(f8631a, "inputstraem exception");
            return "";
        }
    }
}
