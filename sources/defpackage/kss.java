package defpackage;

import android.text.TextUtils;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* loaded from: classes5.dex */
public final class kss {
    private static boolean a(String str) {
        return new File(str).exists();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v13, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public static void d(String str, String str2, byte[] bArr) {
        Throwable th;
        FileOutputStream fileOutputStream;
        Exception e;
        FileOutputStream fileOutputStream2;
        File file;
        if (str == 0 || TextUtils.isEmpty(str) || str2 == null || TextUtils.isEmpty(str2) || bArr == null) {
            ksy.c("FileUtils", "parameter is null.", true);
            return;
        }
        try {
            try {
                file = new File((String) str);
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = str;
                a(fileOutputStream, null, null);
                throw th;
            }
        } catch (FileNotFoundException unused) {
            fileOutputStream2 = null;
        } catch (IOException unused2) {
            fileOutputStream2 = null;
        } catch (Exception e2) {
            e = e2;
            fileOutputStream2 = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            a(fileOutputStream, null, null);
            throw th;
        }
        if (!file.exists() && !file.mkdirs()) {
            ksy.c("FileUtils", "file is not existence.", true);
            a(null, null, null);
            return;
        }
        String str3 = ((String) str) + str2;
        if (j(str3)) {
            fileOutputStream2 = new FileOutputStream(new File(str3));
            try {
                fileOutputStream2.write(bArr);
                fileOutputStream2.flush();
                str = fileOutputStream2;
            } catch (FileNotFoundException unused3) {
                ksy.c("FileUtils", "FileNotFoundException", true);
                str = fileOutputStream2;
                a(str, null, null);
            } catch (IOException unused4) {
                ksy.c("FileUtils", "IOException", true);
                str = fileOutputStream2;
                a(str, null, null);
            } catch (Exception e3) {
                e = e3;
                ksy.c("FileUtils", "Exception:" + e.getClass().getSimpleName(), true);
                str = fileOutputStream2;
                a(str, null, null);
            }
            a(str, null, null);
        }
        ksy.c("FileUtils", "file path is invalid.", true);
        str = 0;
        a(str, null, null);
    }

    private static boolean j(String str) throws UnsupportedEncodingException, IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            ksy.c("FileUtils", "filePath is empty.", true);
            return false;
        }
        String decode = URLDecoder.decode(str, "utf-8");
        return (decode.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE) || decode.contains("./") || decode.contains("%00") || decode.contains("..")) ? false : true;
    }

    private static void a(FileOutputStream fileOutputStream, FileInputStream fileInputStream, InputStream inputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                ksy.c("FileUtils", "fileOutputStream IOException" + e.getClass().getSimpleName(), true);
            }
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e2) {
                ksy.c("FileUtils", "fileInputStream IOException" + e2.getClass().getSimpleName(), true);
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e3) {
                ksy.c("FileUtils", "inputStream IOException" + e3.getClass().getSimpleName(), true);
            }
        }
    }

    public static void b(String str) {
        b(new File(str));
    }

    private static void b(File file) {
        if (!file.exists()) {
            ksy.c("FileUtils", "files is not existence.", true);
            return;
        }
        if (!file.isDirectory()) {
            ksy.c("FileUtils", "files is not directory.", true);
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            ksy.c("FileUtils", "fileList is null.", true);
            return;
        }
        for (File file2 : listFiles) {
            boolean delete = file2.delete();
            ksy.c("FileUtils", "result is " + delete, true);
            if (!delete) {
                return;
            }
        }
    }

    public static InputStream d(String str) {
        FileInputStream fileInputStream;
        if (!a(str)) {
            return null;
        }
        File file = new File(str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        try {
            try {
                fileInputStream = new FileInputStream(file);
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                            a(null, fileInputStream, byteArrayInputStream);
                            return byteArrayInputStream;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    } catch (FileNotFoundException unused) {
                        ksy.c("FileUtils", "FileNotFoundException", true);
                        a(null, fileInputStream, null);
                        return null;
                    } catch (IOException unused2) {
                        ksy.c("FileUtils", "IOException", true);
                        a(null, fileInputStream, null);
                        return null;
                    } catch (IndexOutOfBoundsException unused3) {
                        ksy.c("FileUtils", "IndexOutOfBoundsException", true);
                        a(null, fileInputStream, null);
                        return null;
                    }
                }
            } catch (Throwable th) {
                th = th;
                a(null, null, null);
                throw th;
            }
        } catch (FileNotFoundException unused4) {
            fileInputStream = null;
        } catch (IOException unused5) {
            fileInputStream = null;
        } catch (IndexOutOfBoundsException unused6) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            a(null, null, null);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.FileOutputStream] */
    public void c(String str, String str2, String str3) {
        FileOutputStream fileOutputStream;
        File file;
        if (str == null || TextUtils.isEmpty(str) || str2 == 0 || TextUtils.isEmpty(str2) || str3 == 0 || TextUtils.isEmpty(str3)) {
            ksy.c("FileUtils", "parameter is null.", true);
            return;
        }
        try {
            try {
                file = new File((String) str2);
            } catch (Throwable th) {
                th = th;
            }
        } catch (FileNotFoundException unused) {
            str2 = 0;
            fileOutputStream = null;
        } catch (IOException unused2) {
            str2 = 0;
            fileOutputStream = null;
        } catch (IndexOutOfBoundsException unused3) {
            str2 = 0;
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            str2 = 0;
            str3 = 0;
        }
        if (!file.exists() && !file.mkdirs()) {
            ksy.c("FileUtils", "new file is not existence.", true);
            a(null, null, null);
            return;
        }
        File file2 = new File(str);
        if (!file2.exists() && !file2.mkdirs()) {
            ksy.c("FileUtils", "old file is not existence.", true);
            a(null, null, null);
            return;
        }
        File file3 = new File(str + ((String) str3));
        File file4 = new File(((String) str2) + ((String) str3));
        str2 = new FileInputStream(file3);
        try {
            fileOutputStream = new FileOutputStream(file4);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = str2.read(bArr);
                    if (-1 == read) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, read);
                    }
                }
            } catch (FileNotFoundException unused4) {
                ksy.c("FileUtils", "FileNotFoundException", true);
                a(fileOutputStream, null, str2);
            } catch (IOException unused5) {
                ksy.c("FileUtils", "IOException", true);
                a(fileOutputStream, null, str2);
            } catch (IndexOutOfBoundsException unused6) {
                ksy.c("FileUtils", "IndexOutOfBoundsException", true);
                a(fileOutputStream, null, str2);
            }
        } catch (FileNotFoundException unused7) {
            fileOutputStream = null;
        } catch (IOException unused8) {
            fileOutputStream = null;
        } catch (IndexOutOfBoundsException unused9) {
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            str3 = 0;
            a(str3, null, str2);
            throw th;
        }
        a(fileOutputStream, null, str2);
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            ksy.b("FileUtils", "file not exist", true);
            return false;
        }
    }

    public static String c(String str) {
        FileInputStream fileInputStream;
        try {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    int available = fileInputStream.available();
                    byte[] bArr = new byte[available];
                    if (available != fileInputStream.read(bArr)) {
                        ksy.c("FileUtils", "readFile length is error.", true);
                    }
                    String str2 = new String(bArr, "UTF-8");
                    c(fileInputStream, null);
                    return str2;
                } catch (FileNotFoundException e) {
                    e = e;
                    ksy.c("FileUtils", "FileNotFoundException " + e.getClass().getSimpleName(), true);
                    c(fileInputStream, null);
                    return "";
                } catch (UnsupportedEncodingException e2) {
                    e = e2;
                    ksy.c("FileUtils", "UnsupportedEncodingException " + e.getClass().getSimpleName(), true);
                    c(fileInputStream, null);
                    return "";
                } catch (IOException e3) {
                    e = e3;
                    ksy.c("FileUtils", "IOException " + e.getClass().getSimpleName(), true);
                    c(fileInputStream, null);
                    return "";
                } catch (Exception e4) {
                    e = e4;
                    ksy.c("FileUtils", "Exception " + e.getClass().getSimpleName(), true);
                    c(fileInputStream, null);
                    return "";
                }
            } catch (Throwable th) {
                th = th;
                c(null, null);
                throw th;
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileInputStream = null;
        } catch (UnsupportedEncodingException e6) {
            e = e6;
            fileInputStream = null;
        } catch (IOException e7) {
            e = e7;
            fileInputStream = null;
        } catch (Exception e8) {
            e = e8;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            c(null, null);
            throw th;
        }
    }

    public static void c(InputStream inputStream, OutputStream outputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                ksy.c("FileUtils", "close InputStream error!" + e.getClass().getSimpleName(), true);
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e2) {
                ksy.c("FileUtils", "close OutputStream error!" + e2.getClass().getSimpleName(), true);
            }
        }
    }
}
