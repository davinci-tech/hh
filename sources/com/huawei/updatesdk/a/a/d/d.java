package com.huawei.updatesdk.a.a.d;

import android.text.TextUtils;
import com.huawei.android.hicloud.sync.util.FileUtil;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes7.dex */
public abstract class d {
    public static boolean d(String str) {
        return TextUtils.isEmpty(str) || b(str);
    }

    private static boolean c(String str) {
        return str.contains("%00") || str.contains(".\\.\\") || str.contains("./");
    }

    private static boolean b(String str) {
        return a(str) || c(str);
    }

    private static boolean a(String str) {
        return str.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE) || str.contains("..");
    }

    public static boolean a(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                a(file2);
            }
        }
        return file.delete();
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                com.huawei.updatesdk.a.a.c.a.a.a.a(FileUtil.TAG, "Closeable exception", e);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    public static String a(String str, String str2) {
        FileInputStream fileInputStream;
        String str3;
        ?? d = d(str);
        Closeable closeable = null;
        r2 = null;
        r2 = null;
        String str4 = null;
        try {
            if (d != 0) {
                return null;
            }
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str2);
                fileInputStream = new FileInputStream(str);
                try {
                    byte[] bArr = new byte[1024];
                    long j = 0;
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read);
                        j += read;
                    }
                    if (j > 0) {
                        str4 = b.a(messageDigest.digest());
                    }
                } catch (FileNotFoundException unused) {
                    com.huawei.updatesdk.a.a.c.a.a.a.b(FileUtil.TAG, "getFileHashData FileNotFoundException");
                    a(fileInputStream);
                    return str4;
                } catch (IOException e) {
                    e = e;
                    str3 = "getFileHashData IOException";
                    com.huawei.updatesdk.a.a.c.a.a.a.a(FileUtil.TAG, str3, e);
                    a(fileInputStream);
                    return str4;
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    str3 = "getFileHashData IllegalArgumentException";
                    com.huawei.updatesdk.a.a.c.a.a.a.a(FileUtil.TAG, str3, e);
                    a(fileInputStream);
                    return str4;
                } catch (IndexOutOfBoundsException e3) {
                    e = e3;
                    str3 = "getFileHashData IndexOutOfBoundsException";
                    com.huawei.updatesdk.a.a.c.a.a.a.a(FileUtil.TAG, str3, e);
                    a(fileInputStream);
                    return str4;
                } catch (NoSuchAlgorithmException e4) {
                    e = e4;
                    str3 = "getFileHashData NoSuchAlgorithmException";
                    com.huawei.updatesdk.a.a.c.a.a.a.a(FileUtil.TAG, str3, e);
                    a(fileInputStream);
                    return str4;
                }
            } catch (FileNotFoundException unused2) {
                fileInputStream = null;
            } catch (IOException e5) {
                e = e5;
                fileInputStream = null;
            } catch (IllegalArgumentException e6) {
                e = e6;
                fileInputStream = null;
            } catch (IndexOutOfBoundsException e7) {
                e = e7;
                fileInputStream = null;
            } catch (NoSuchAlgorithmException e8) {
                e = e8;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                a(closeable);
                throw th;
            }
            a(fileInputStream);
            return str4;
        } catch (Throwable th2) {
            th = th2;
            closeable = d;
        }
    }
}
