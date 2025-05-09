package com.huawei.watchface.videoedit.utils;

import android.content.Context;
import android.os.Environment;
import com.huawei.operation.ble.BleConstants;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.videoedit.dfx.DfxGuard;
import com.huawei.watchface.videoedit.sysc.Optional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes7.dex */
public class FileUtils {
    private static final int BYTE_SIZE = 4096;
    public static final String CACHE_FOLDER = "app_video_project";
    private static final String FORMAT = "HwVideoEditor_%d_%02d_%02d_%02d%02d%02d%03d.mp4";
    private static final String FORMAT_PHOTO = "IMG_%d_%02d_%02d_%02d%02d%02d%03d.jpg";
    public static final String SAVE_FOLDER = "Pictures/VideoEditor";
    public static final File SAVE_PATH = new File(Environment.getExternalStorageDirectory(), SAVE_FOLDER);
    private static final String TAG = "FileUtils";

    private FileUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v13, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v27 */
    /* JADX WARN: Type inference failed for: r8v28 */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v30, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v35 */
    /* JADX WARN: Type inference failed for: r8v44 */
    /* JADX WARN: Type inference failed for: r8v45 */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.io.Closeable] */
    public static Optional<File> copyAssetsResource(Context context, String str) {
        Throwable th;
        ?? r8;
        Exception e;
        String str2;
        RuntimeException e2;
        String str3;
        IOException e3;
        String str4;
        FileNotFoundException e4;
        String str5;
        ?? r82;
        InputStream open;
        ?? r83;
        if (context == null || str == null) {
            HwLog.e(TAG, "context or path is null, can not copy assets resource.");
            return Optional.empty();
        }
        File file = new File(context.getDir(CACHE_FOLDER, 0), str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            DfxGuard.getPolicy().onWriteDisk();
            HwLog.i(TAG, "copyAssetsResource file mkdir:" + parentFile.mkdirs());
        }
        if (!file.exists()) {
            InputStream inputStream = null;
            try {
                try {
                    boolean startsWith = str.startsWith("/");
                    String str6 = str;
                    if (startsWith) {
                        str6 = SafeString.substring(str, 1);
                    }
                    open = context.getAssets().open(str6);
                    try {
                        r83 = new FileOutputStream(file);
                    } catch (FileNotFoundException e5) {
                        e4 = e5;
                        r83 = 0;
                    } catch (IOException e6) {
                        e3 = e6;
                        r83 = 0;
                    } catch (RuntimeException e7) {
                        e2 = e7;
                        r83 = 0;
                    } catch (Exception e8) {
                        e = e8;
                        r83 = 0;
                    } catch (Throwable th2) {
                        th = th2;
                        r83 = 0;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    r8 = str;
                }
            } catch (FileNotFoundException e9) {
                e4 = e9;
                str5 = null;
            } catch (IOException e10) {
                e3 = e10;
                str4 = null;
            } catch (RuntimeException e11) {
                e2 = e11;
                str3 = null;
            } catch (Exception e12) {
                e = e12;
                str2 = null;
            } catch (Throwable th4) {
                th = th4;
                r8 = 0;
            }
            try {
                byte[] bArr = new byte[4096];
                DfxGuard.getPolicy().onWriteDisk();
                while (true) {
                    int read = open.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    r83.write(bArr, 0, read);
                }
                HwLog.d(TAG, "copy resource success ");
                Utils.closeSilently(open);
                r82 = r83;
            } catch (FileNotFoundException e13) {
                e4 = e13;
                inputStream = open;
                str5 = r83;
                HwLog.e("copyAssetsResource:", "FileNotFoundException" + HwLog.printException((Exception) e4));
                str = str5;
                Utils.closeSilently(inputStream);
                r82 = str;
                Utils.closeSilently(r82);
                return Optional.ofNullable(file);
            } catch (IOException e14) {
                e3 = e14;
                inputStream = open;
                str4 = r83;
                HwLog.e("copyAssetsResource:", "IOException" + HwLog.printException((Exception) e3));
                str = str4;
                Utils.closeSilently(inputStream);
                r82 = str;
                Utils.closeSilently(r82);
                return Optional.ofNullable(file);
            } catch (RuntimeException e15) {
                e2 = e15;
                inputStream = open;
                str3 = r83;
                HwLog.e("copyAssetsResource:", "RuntimeException" + HwLog.printException((Exception) e2));
                str = str3;
                Utils.closeSilently(inputStream);
                r82 = str;
                Utils.closeSilently(r82);
                return Optional.ofNullable(file);
            } catch (Exception e16) {
                e = e16;
                inputStream = open;
                str2 = r83;
                HwLog.e(BleConstants.WEIGHT_KEY, "copy Assets resource failed" + HwLog.printException(e));
                str = str2;
                Utils.closeSilently(inputStream);
                r82 = str;
                Utils.closeSilently(r82);
                return Optional.ofNullable(file);
            } catch (Throwable th5) {
                th = th5;
                inputStream = open;
                r8 = r83;
                Utils.closeSilently(inputStream);
                Utils.closeSilently(r8);
                throw th;
            }
            Utils.closeSilently(r82);
        }
        return Optional.ofNullable(file);
    }

    public static String getCacheFilePath() {
        return getCacheFilePath(false);
    }

    public static String getCacheFilePath(boolean z) {
        String str = z ? FORMAT_PHOTO : FORMAT;
        Calendar calendar = Calendar.getInstance();
        String format = String.format(Locale.ROOT, str, Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5)), Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12)), Integer.valueOf(calendar.get(13)), Integer.valueOf(calendar.get(14)));
        HwLog.d(TAG, "getCacheFilePath");
        return format;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getFileSize(java.lang.String r5) {
        /*
            java.lang.String r0 = "FileUtils"
            java.lang.String r1 = "getFileSize---IOException: "
            boolean r2 = android.text.TextUtils.isEmpty(r5)
            r3 = 0
            if (r2 == 0) goto Lc
            return r3
        Lc:
            java.io.File r2 = new java.io.File
            r2.<init>(r5)
            boolean r5 = r2.exists()
            if (r5 == 0) goto L83
            r5 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            int r3 = r4.available()     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e
            r4.close()     // Catch: java.io.IOException -> L25
            goto L83
        L25:
            r5 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            goto L54
        L2c:
            r5 = move-exception
            goto L66
        L2e:
            r5 = move-exception
            r2 = r5
            r5 = r4
            goto L35
        L32:
            r2 = move-exception
            goto L68
        L34:
            r2 = move-exception
        L35:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L63
            java.lang.String r2 = com.huawei.watchface.utils.HwLog.printException(r2)     // Catch: java.lang.Throwable -> L63
            r4.append(r2)     // Catch: java.lang.Throwable -> L63
            java.lang.String r2 = r4.toString()     // Catch: java.lang.Throwable -> L63
            com.huawei.watchface.utils.HwLog.e(r0, r2)     // Catch: java.lang.Throwable -> L63
            if (r5 == 0) goto L83
            r5.close()     // Catch: java.io.IOException -> L4e
            goto L83
        L4e:
            r5 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
        L54:
            java.lang.String r5 = com.huawei.watchface.utils.HwLog.printException(r5)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            com.huawei.watchface.utils.HwLog.e(r0, r5)
            goto L83
        L63:
            r2 = move-exception
            r4 = r5
            r5 = r2
        L66:
            r2 = r5
            r5 = r4
        L68:
            if (r5 == 0) goto L82
            r5.close()     // Catch: java.io.IOException -> L6e
            goto L82
        L6e:
            r5 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            java.lang.String r5 = com.huawei.watchface.utils.HwLog.printException(r5)
            r3.append(r5)
            java.lang.String r5 = r3.toString()
            com.huawei.watchface.utils.HwLog.e(r0, r5)
        L82:
            throw r2
        L83:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.videoedit.utils.FileUtils.getFileSize(java.lang.String):int");
    }

    public static boolean renameTo(String str, String str2) {
        try {
            File file = new File(str);
            File file2 = new File(str2);
            FlavorConfig.safeHwLog(TAG, "rename() old file path:" + file.getCanonicalPath() + ",new file path:" + file2.getCanonicalPath());
            return file.renameTo(file2);
        } catch (Exception e) {
            HwLog.e(TAG, "rename() exception" + HwLog.printException(e));
            return false;
        }
    }

    public static boolean checkZipHasFile(String str, String str2) {
        ZipFile zipFile;
        HwLog.i(TAG, "checkZipHasFile() zipFilePath:" + str + ", targetFileName: " + str2);
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(str);
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            th = th;
            zipFile = zipFile2;
        }
        try {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                if (entries.nextElement().getName().equals(str2)) {
                    HwLog.i(TAG, "checkZipHasFile() has" + str2);
                    try {
                        zipFile.close();
                        return true;
                    } catch (IOException unused2) {
                        HwLog.e(TAG, "checkZipHasFile() IOException zipFile.close()");
                        return true;
                    }
                }
            }
            try {
                zipFile.close();
            } catch (IOException unused3) {
                HwLog.e(TAG, "checkZipHasFile() IOException zipFile.close()");
            }
            return false;
        } catch (IOException unused4) {
            zipFile2 = zipFile;
            HwLog.e(TAG, "checkZipHasFile() " + str2 + " is not exist");
            if (zipFile2 != null) {
                try {
                    zipFile2.close();
                } catch (IOException unused5) {
                    HwLog.e(TAG, "checkZipHasFile() IOException zipFile.close()");
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException unused6) {
                    HwLog.e(TAG, "checkZipHasFile() IOException zipFile.close()");
                }
            }
            throw th;
        }
    }
}
