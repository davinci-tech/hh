package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes5.dex */
public class izx {
    private static final Object d = new Object();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v21, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v22, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v23, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.graphics.Bitmap] */
    public static String bEe_(Context context, String str, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        synchronized (d) {
            String e = jdq.e(context, str);
            File file = new File(Utils.e(context));
            if (!file.exists() && !file.mkdirs()) {
                LogUtil.b("ImageStorageAdapter", "create file error");
            }
            String str2 = Utils.e(context) + File.separator + e;
            File file2 = new File(str2);
            ?? r1 = 0;
            r1 = 0;
            r1 = 0;
            if (bitmap == 0) {
                return null;
            }
            if (file2.exists() && !file2.delete()) {
                LogUtil.b("ImageStorageAdapter", "delete old file error");
            }
            try {
                try {
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = r1;
                }
            } catch (FileNotFoundException unused) {
            } catch (IOException unused2) {
            }
            if (!file2.createNewFile()) {
                return null;
            }
            fileOutputStream = new FileOutputStream(file2);
            try {
                r1 = 70;
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream);
                try {
                    try {
                        fileOutputStream.flush();
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            r1 = "saveImage finally close IOException exception = ";
                            LogUtil.b("ImageStorageAdapter", "saveImage finally close IOException exception = ", e2.getMessage());
                        }
                    } catch (IOException e3) {
                        r1 = "saveImage finally flush IOException exception = ";
                        LogUtil.b("ImageStorageAdapter", "saveImage finally flush IOException exception = ", e3.getMessage());
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            r1 = "saveImage finally close IOException exception = ";
                            LogUtil.b("ImageStorageAdapter", "saveImage finally close IOException exception = ", e4.getMessage());
                        }
                    }
                } finally {
                }
            } catch (FileNotFoundException unused3) {
                r1 = fileOutputStream;
                LogUtil.b("ImageStorageAdapter", "saveImage FileNotFoundException.");
                if (r1 != 0) {
                    try {
                        try {
                            r1.flush();
                        } catch (IOException e5) {
                            LogUtil.b("ImageStorageAdapter", "saveImage finally flush IOException exception = ", e5.getMessage());
                            if (r1 != 0) {
                                try {
                                    r1.close();
                                } catch (IOException e6) {
                                    r1 = "saveImage finally close IOException exception = ";
                                    LogUtil.b("ImageStorageAdapter", "saveImage finally close IOException exception = ", e6.getMessage());
                                }
                            }
                        }
                    } finally {
                    }
                }
                if (r1 != 0) {
                    try {
                        r1.close();
                    } catch (IOException e7) {
                        r1 = "saveImage finally close IOException exception = ";
                        LogUtil.b("ImageStorageAdapter", "saveImage finally close IOException exception = ", e7.getMessage());
                    }
                }
                return str2;
            } catch (IOException unused4) {
                r1 = fileOutputStream;
                LogUtil.b("ImageStorageAdapter", "saveImage Exception");
                if (r1 != 0) {
                    try {
                        try {
                            r1.flush();
                        } catch (IOException e8) {
                            LogUtil.b("ImageStorageAdapter", "saveImage finally flush IOException exception = ", e8.getMessage());
                            if (r1 != 0) {
                                try {
                                    r1.close();
                                } catch (IOException e9) {
                                    r1 = "saveImage finally close IOException exception = ";
                                    LogUtil.b("ImageStorageAdapter", "saveImage finally close IOException exception = ", e9.getMessage());
                                }
                            }
                        }
                    } finally {
                    }
                }
                if (r1 != 0) {
                    try {
                        r1.close();
                    } catch (IOException e10) {
                        r1 = "saveImage finally close IOException exception = ";
                        LogUtil.b("ImageStorageAdapter", "saveImage finally close IOException exception = ", e10.getMessage());
                    }
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                try {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.flush();
                        } catch (IOException e11) {
                            LogUtil.b("ImageStorageAdapter", "saveImage finally flush IOException exception = ", e11.getMessage());
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e12) {
                                    LogUtil.b("ImageStorageAdapter", "saveImage finally close IOException exception = ", e12.getMessage());
                                }
                            }
                            throw th;
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e13) {
                            LogUtil.b("ImageStorageAdapter", "saveImage finally close IOException exception = ", e13.getMessage());
                        }
                    }
                    throw th;
                } finally {
                }
            }
            return str2;
        }
    }
}
