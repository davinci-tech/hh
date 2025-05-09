package defpackage;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public class tsa {
    private tsa() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.io.BufferedInputStream, java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.Closeable] */
    public static File ffn_(String str, ParcelFileDescriptor parcelFileDescriptor) {
        Closeable closeable;
        BufferedOutputStream bufferedOutputStream;
        synchronized (tsa.class) {
            tov.b("WearEngineFileUtil", "begin createFileFromPfd, fileName: " + str);
            Closeable closeable2 = null;
            if (!ffo_(str, parcelFileDescriptor)) {
                return null;
            }
            String b = b(str);
            ?? isEmpty = TextUtils.isEmpty(b);
            try {
                if (isEmpty != 0) {
                    tov.d("WearEngineFileUtil", "createFileFromPfd path is invalid");
                    return null;
                }
                try {
                    File c = c(b);
                    if (c == null) {
                        tov.d("WearEngineFileUtil", "createFileFromPfd dest file is null");
                        e(null);
                        e(null);
                        e(parcelFileDescriptor);
                        return null;
                    }
                    FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                    if (fileDescriptor != null && fileDescriptor.valid()) {
                        isEmpty = new BufferedInputStream(new FileInputStream(fileDescriptor));
                        try {
                            byte[] bArr = new byte[2048];
                            int read = isEmpty.read(bArr);
                            if (read == -1) {
                                tov.c("WearEngineFileUtil", "inputStream is empty, do not write file");
                                e(isEmpty);
                                e(null);
                                e(parcelFileDescriptor);
                                return c;
                            }
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(c));
                            while (read != -1) {
                                try {
                                    bufferedOutputStream.write(bArr, 0, read);
                                    read = isEmpty.read(bArr);
                                } catch (IOException unused) {
                                    tov.d("WearEngineFileUtil", "createFileFromPfd IOException");
                                    e(isEmpty);
                                    e(bufferedOutputStream);
                                    e(parcelFileDescriptor);
                                    return null;
                                } catch (Exception unused2) {
                                    tov.d("WearEngineFileUtil", "createFileFromPfd Exception");
                                    e(isEmpty);
                                    e(bufferedOutputStream);
                                    e(parcelFileDescriptor);
                                    return null;
                                }
                            }
                            bufferedOutputStream.flush();
                            e(isEmpty);
                            e(bufferedOutputStream);
                            e(parcelFileDescriptor);
                            return c;
                        } catch (IOException unused3) {
                            bufferedOutputStream = null;
                        } catch (Exception unused4) {
                            bufferedOutputStream = null;
                        } catch (Throwable th) {
                            th = th;
                            closeable = null;
                            closeable2 = isEmpty;
                            e(closeable2);
                            e(closeable);
                            e(parcelFileDescriptor);
                            throw th;
                        }
                    }
                    tov.c("WearEngineFileUtil", "fd is invalid");
                    e(null);
                    e(null);
                    e(parcelFileDescriptor);
                    return c;
                } catch (IOException unused5) {
                    isEmpty = 0;
                    bufferedOutputStream = null;
                } catch (Exception unused6) {
                    isEmpty = 0;
                    bufferedOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    closeable = null;
                    e(closeable2);
                    e(closeable);
                    e(parcelFileDescriptor);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    private static File c(String str) throws IOException {
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            tov.d("WearEngineFileUtil", "createFileFromPfd parentFile is invalid");
            return null;
        }
        if (!parentFile.exists()) {
            tov.b("WearEngineFileUtil", "createFileFromPfd isFileMkdir:" + parentFile.mkdirs());
        }
        if (!file.exists()) {
            tov.b("WearEngineFileUtil", "createFileFromPfd isFileCreate:" + file.createNewFile());
        }
        return file;
    }

    private static boolean ffo_(String str, ParcelFileDescriptor parcelFileDescriptor) {
        if (TextUtils.isEmpty(str)) {
            tov.d("WearEngineFileUtil", "createFileFromPfd fileName is invalid");
            return false;
        }
        if (parcelFileDescriptor != null) {
            return true;
        }
        tov.d("WearEngineFileUtil", "createFileFromPfd fileDescriptor is null");
        return false;
    }

    private static String b(String str) {
        Context c = trr.c();
        if (c == null) {
            tov.d("WearEngineFileUtil", "getCheckedFilePath context is null");
            return "";
        }
        try {
            String str2 = c.getFilesDir().getCanonicalPath() + File.separator + "WearEngine" + File.separator + str;
            if (TextUtils.isEmpty(str2)) {
                tov.d("WearEngineFileUtil", "createFileFromPfd path is invalid");
                return "";
            }
            try {
                return new File(str2).getCanonicalPath();
            } catch (IOException unused) {
                tov.d("WearEngineFileUtil", "getCheckedFilePath IOException");
                return null;
            }
        } catch (IOException unused2) {
            tov.d("WearEngineFileUtil", "getCanonicalPath IOException");
            return "";
        }
    }

    private static void e(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                tov.d("WearEngineFileUtil", "closeQuietly IOException");
            }
        }
    }
}
