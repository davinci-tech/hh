package defpackage;

import android.text.TextUtils;
import android.util.Base64;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.WhiteBoxManager;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class msp {
    private static byte[] e(int i) {
        byte[] bArr = new byte[i];
        e().nextBytes(bArr);
        return bArr;
    }

    public static SecureRandom e() {
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            LogUtil.b("EzPlugin_EzFileHelper", "getSecureRandom exception ", e);
            return new SecureRandom();
        }
    }

    private static String b() {
        WhiteBoxManager d = WhiteBoxManager.d();
        return new String(d.a(Base64.decode(d.d(1, 6) + d.d(1, 1006) + d.d(1, 2006), 0)), StandardCharsets.UTF_8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.io.Closeable, java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.Closeable, javax.crypto.CipherInputStream] */
    public static boolean a(String str, String str2) {
        FileInputStream fileInputStream;
        Closeable closeable;
        FileInputStream fileInputStream2;
        ?? cipherInputStream;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("EzPlugin_EzFileHelper", "decryptFile param is empty.");
            return false;
        }
        ?? file = new File(str);
        File file2 = new File(str2);
        if (file.exists() && file.isFile()) {
            FileInputStream fileInputStream3 = null;
            try {
                try {
                    a(file2, true, false);
                    fileInputStream2 = new FileInputStream((File) file);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e = e;
                file = 0;
                fileInputStream2 = null;
                LogUtil.b("EzPlugin_EzFileHelper", "decryptFile Exception ", e.getClass().getName());
                IoUtils.e(fileInputStream2);
                IoUtils.e((Closeable) file);
                IoUtils.e(fileInputStream3);
                return false;
            } catch (SecurityException e2) {
                e = e2;
                file = 0;
                fileInputStream2 = null;
                LogUtil.b("EzPlugin_EzFileHelper", "decryptFile Exception ", e.getClass().getName());
                IoUtils.e(fileInputStream2);
                IoUtils.e((Closeable) file);
                IoUtils.e(fileInputStream3);
                return false;
            } catch (GeneralSecurityException e3) {
                e = e3;
                file = 0;
                fileInputStream2 = null;
                LogUtil.b("EzPlugin_EzFileHelper", "decryptFile Exception ", e.getClass().getName());
                IoUtils.e(fileInputStream2);
                IoUtils.e((Closeable) file);
                IoUtils.e(fileInputStream3);
                return false;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                closeable = null;
                IoUtils.e(fileInputStream3);
                IoUtils.e(closeable);
                IoUtils.e(fileInputStream);
                throw th;
            }
            try {
                file = new FileOutputStream(file2);
                try {
                    cipherInputStream = new CipherInputStream(fileInputStream2, d());
                } catch (IOException e4) {
                    e = e4;
                } catch (SecurityException e5) {
                    e = e5;
                } catch (GeneralSecurityException e6) {
                    e = e6;
                }
                try {
                    byte[] bArr = new byte[4096];
                    int i = 0;
                    for (int read = cipherInputStream.read(bArr); read != -1; read = cipherInputStream.read(bArr)) {
                        i += read;
                        if (i > 524288000) {
                            LogUtil.b("EzPlugin_EzFileHelper", "decryptFile sourceFile too big, so decrypt failure.");
                            IoUtils.e(fileInputStream2);
                            IoUtils.e((Closeable) file);
                            IoUtils.e((Closeable) cipherInputStream);
                            return false;
                        }
                        file.write(bArr, 0, read);
                        file.flush();
                    }
                    LogUtil.a("EzPlugin_EzFileHelper", "decryptFile decrypt is accomplished.");
                    IoUtils.e(fileInputStream2);
                    IoUtils.e((Closeable) file);
                    IoUtils.e((Closeable) cipherInputStream);
                    return true;
                } catch (IOException e7) {
                    e = e7;
                    fileInputStream3 = cipherInputStream;
                    LogUtil.b("EzPlugin_EzFileHelper", "decryptFile Exception ", e.getClass().getName());
                    IoUtils.e(fileInputStream2);
                    IoUtils.e((Closeable) file);
                    IoUtils.e(fileInputStream3);
                    return false;
                } catch (SecurityException e8) {
                    e = e8;
                    fileInputStream3 = cipherInputStream;
                    LogUtil.b("EzPlugin_EzFileHelper", "decryptFile Exception ", e.getClass().getName());
                    IoUtils.e(fileInputStream2);
                    IoUtils.e((Closeable) file);
                    IoUtils.e(fileInputStream3);
                    return false;
                } catch (GeneralSecurityException e9) {
                    e = e9;
                    fileInputStream3 = cipherInputStream;
                    LogUtil.b("EzPlugin_EzFileHelper", "decryptFile Exception ", e.getClass().getName());
                    IoUtils.e(fileInputStream2);
                    IoUtils.e((Closeable) file);
                    IoUtils.e(fileInputStream3);
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream3 = cipherInputStream;
                    fileInputStream = fileInputStream3;
                    fileInputStream3 = fileInputStream2;
                    closeable = file;
                    IoUtils.e(fileInputStream3);
                    IoUtils.e(closeable);
                    IoUtils.e(fileInputStream);
                    throw th;
                }
            } catch (IOException e10) {
                e = e10;
                file = 0;
                LogUtil.b("EzPlugin_EzFileHelper", "decryptFile Exception ", e.getClass().getName());
                IoUtils.e(fileInputStream2);
                IoUtils.e((Closeable) file);
                IoUtils.e(fileInputStream3);
                return false;
            } catch (SecurityException e11) {
                e = e11;
                file = 0;
                LogUtil.b("EzPlugin_EzFileHelper", "decryptFile Exception ", e.getClass().getName());
                IoUtils.e(fileInputStream2);
                IoUtils.e((Closeable) file);
                IoUtils.e(fileInputStream3);
                return false;
            } catch (GeneralSecurityException e12) {
                e = e12;
                file = 0;
                LogUtil.b("EzPlugin_EzFileHelper", "decryptFile Exception ", e.getClass().getName());
                IoUtils.e(fileInputStream2);
                IoUtils.e((Closeable) file);
                IoUtils.e(fileInputStream3);
                return false;
            } catch (Throwable th4) {
                th = th4;
                file = 0;
            }
        }
        return false;
    }

    private static void a(File file, boolean z, boolean z2) throws IOException, SecurityException {
        if (z && file.isDirectory()) {
            LogUtil.a("EzPlugin_EzFileHelper", "initializeDestFile deleteDestFile as isDeleteFile :", Boolean.valueOf(file.delete()));
        }
        if (z2 && file.exists()) {
            LogUtil.a("EzPlugin_EzFileHelper", "initializeDestFile deleteDestFile isDeleteFile :", Boolean.valueOf(file.delete()));
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            LogUtil.a("EzPlugin_EzFileHelper", "initializeDestFile isMkdirFile :", Boolean.valueOf(parentFile.mkdirs()));
        }
        if (file.isFile()) {
            return;
        }
        LogUtil.c("EzPlugin_EzFileHelper", "initializeDestFile isCreateNewFile :", Boolean.valueOf(file.createNewFile()));
    }

    private static Cipher d() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException {
        SecureRandom instanceStrong = SecureRandom.getInstanceStrong();
        instanceStrong.setSeed(SecurityUtils.a(b()));
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256, instanceStrong);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(e(16));
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(2, secretKeySpec, ivParameterSpec);
        return cipher;
    }

    public static int c(String str, String str2) {
        ZipInputStream zipInputStream;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("EzPlugin_EzFileHelper", "unzip filename or destDirectory is empty.");
            return -1;
        }
        LogUtil.a("EzPlugin_EzFileHelper", "unzip filename :", str, ", destDir :", str2);
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("EzPlugin_EzFileHelper", "unzip safeFilePath is empty.");
            return -1;
        }
        ZipInputStream zipInputStream2 = null;
        try {
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(c));
            } catch (Throwable th) {
                th = th;
                zipInputStream = null;
            }
        } catch (IOException unused) {
        }
        try {
            int a2 = a(zipInputStream, str2, null, true);
            IoUtils.e(zipInputStream);
            return a2;
        } catch (IOException unused2) {
            zipInputStream2 = zipInputStream;
            LogUtil.b("EzPlugin_EzFileHelper", "unzip IOException.");
            a(c);
            IoUtils.e(zipInputStream2);
            return -1;
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(zipInputStream);
            throw th;
        }
    }

    private static int a(ZipInputStream zipInputStream, String str, String str2, boolean z) throws IOException {
        if (zipInputStream == null || TextUtils.isEmpty(str)) {
            LogUtil.h("EzPlugin_EzFileHelper", "unzip zipInputStream or destDirectory is empty.");
            return -1;
        }
        File c = c(str);
        boolean z2 = !TextUtils.isEmpty(str2);
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            byte[] bArr = new byte[4096];
            int i = 0;
            int i2 = 0;
            while (true) {
                if (nextEntry == null) {
                    break;
                }
                if (z2 && !str2.equals(nextEntry.getName())) {
                    nextEntry = zipInputStream.getNextEntry();
                } else {
                    File d = d(nextEntry, c);
                    if (nextEntry.isDirectory()) {
                        LogUtil.c("EzPlugin_EzFileHelper", "unzip zipEntry isDirectoryMade :", Boolean.valueOf(d.mkdirs()));
                        nextEntry = zipInputStream.getNextEntry();
                    } else {
                        a(d, false, true);
                        i2 = e(d, zipInputStream, i2, bArr);
                        if (i2 > 104857600) {
                            LogUtil.b("EzPlugin_EzFileHelper", "unzip this zip file too big, unzip failure.");
                            throw new IOException("unzip this zip file too big.");
                        }
                        i++;
                        if (z2) {
                            zipInputStream.closeEntry();
                            break;
                        }
                        nextEntry = zipInputStream.getNextEntry();
                    }
                }
            }
            LogUtil.a("EzPlugin_EzFileHelper", "unzip accomplished amount:", Integer.valueOf(i));
            return i;
        } catch (IOException e) {
            if (z) {
                d(z2, null, c);
            }
            throw e;
        }
    }

    private static void a(String str) {
        File file = new File(str);
        if (file.exists()) {
            LogUtil.a("EzPlugin_EzFileHelper", "deleteUnzipSourceFile zipFile:", Boolean.valueOf(file.delete()));
        }
    }

    private static void d(boolean z, File file, File file2) {
        if (z) {
            if (file != null && file.exists() && file.isFile()) {
                FileUtils.b(file);
                return;
            }
            return;
        }
        if (file2.exists() && file2.isDirectory()) {
            FileUtils.b(file2);
        }
    }

    private static File c(String str) throws IOException {
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("EzPlugin_EzFileHelper", "unzip dest dir path is empty.");
            throw new IOException("unzip this dest dir path is illegal.");
        }
        return new File(c);
    }

    private static File d(ZipEntry zipEntry, File file) throws IOException {
        String d = d(zipEntry.getName(), file);
        LogUtil.c("EzPlugin_EzFileHelper", "getExtractFile:", d);
        String c = CommonUtil.c(d);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("EzPlugin_EzFileHelper", "unzip this zip file dest path is illegal, unzip failure.");
            throw new IOException("unzip this zip file dest path is illegal.");
        }
        return new File(c);
    }

    private static int e(File file, ZipInputStream zipInputStream, int i, byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        while (true) {
            try {
                try {
                    int read = zipInputStream.read(bArr);
                    if (read <= -1 || (i = i + read) > 104857600) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                } catch (IOException e) {
                    LogUtil.b("EzPlugin_EzFileHelper", "unzipSingleFile IOException.");
                    throw e;
                }
            } finally {
                IoUtils.e(fileOutputStream);
            }
        }
        return i;
    }

    private static String d(String str, File file) throws IOException {
        String canonicalPath = new File(file, str).getCanonicalPath();
        if (canonicalPath.startsWith(file.getCanonicalPath())) {
            return canonicalPath;
        }
        throw new IOException("File is outside extraction target directory.");
    }

    public static void b(String str, String str2, String str3) {
        File file = new File(str2);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            LogUtil.c("EzPlugin_EzFileHelper", "zip mkdirs isResult :", Boolean.valueOf(mkdirs));
            if (!mkdirs && !file.exists()) {
                LogUtil.h("EzPlugin_EzFileHelper", "file mkdirs error");
                return;
            }
        }
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("EzPlugin_EzFileHelper", "zip safePath is empty.");
            return;
        }
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file + File.separator + str3));
            try {
                File file2 = new File(c);
                if (file2.isFile()) {
                    b(zipOutputStream, file2, "");
                } else {
                    File[] listFiles = file2.listFiles();
                    if (listFiles != null) {
                        for (File file3 : listFiles) {
                            b(zipOutputStream, file3, "");
                        }
                    }
                }
                zipOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.b("EzPlugin_EzFileHelper", "zip occur IOException.");
        }
    }

    private static void b(ZipOutputStream zipOutputStream, File file, String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                if (!file.isDirectory()) {
                    String c = CommonUtil.c(file.getCanonicalPath());
                    if (TextUtils.isEmpty(c)) {
                        LogUtil.h("EzPlugin_EzFileHelper", "zipFileOrDirectory safePath is empty.");
                        IoUtils.e((Closeable) null);
                        return;
                    }
                    fileInputStream = new FileInputStream(c);
                    try {
                        zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
                        byte[] bArr = new byte[4096];
                        for (int read = fileInputStream.read(bArr); read != -1; read = fileInputStream.read(bArr)) {
                            zipOutputStream.write(bArr, 0, read);
                        }
                        zipOutputStream.closeEntry();
                        fileInputStream2 = fileInputStream;
                    } catch (IOException unused) {
                        fileInputStream2 = fileInputStream;
                        LogUtil.b("EzPlugin_EzFileHelper", "zipFileOrDirectory occur IOException.");
                        IoUtils.e(fileInputStream2);
                    } catch (Throwable th) {
                        th = th;
                        IoUtils.e(fileInputStream);
                        throw th;
                    }
                } else {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (File file2 : listFiles) {
                            b(zipOutputStream, file2, str + file.getName() + File.separator);
                        }
                    }
                }
            } catch (IOException unused2) {
            }
            IoUtils.e(fileInputStream2);
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }
}
