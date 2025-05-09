package com.huawei.health.h5pro.jsbridge.system.storage;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import java.io.File;
import java.io.IOException;

/* loaded from: classes3.dex */
public class StorageUtil {
    public static String webAppUriToNativePath(Context context, String str, String str2) {
        if (!GeneralUtil.isSafePath(str) || !GeneralUtil.isSafePath(str2)) {
            LogUtil.w("H5PRO_StorageUtil", "webAppUriToNativePath: invalid appId or path");
            return "";
        }
        if (isFilesDir(str2)) {
            return transferFilePath(context, str, str2);
        }
        if (isCacheDir(str2)) {
            return transferCachePath(context, str, str2);
        }
        throw new IllegalArgumentException("url can't transfer to native path:" + str2);
    }

    public static String transferFilePath(Context context, String str, String str2) {
        String nativeFilePath = getNativeFilePath(context, str);
        if (TextUtils.isEmpty(nativeFilePath)) {
            return "";
        }
        return nativeFilePath + str2.replaceFirst("internal://files/", "");
    }

    public static String transferCachePath(Context context, String str, String str2) {
        String nativeCachePath = getNativeCachePath(context, str);
        if (TextUtils.isEmpty(nativeCachePath)) {
            return "";
        }
        return nativeCachePath + str2.replaceFirst("internal://cache/", "");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:5:0x00f2  */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r1v11, types: [android.database.Cursor, java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.io.Closeable, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.io.Closeable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.health.h5pro.jsbridge.system.storage.FileMeta parseUriToFileMeta(android.net.Uri r12, android.content.Context r13) {
        /*
            Method dump skipped, instructions count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil.parseUriToFileMeta(android.net.Uri, android.content.Context):com.huawei.health.h5pro.jsbridge.system.storage.FileMeta");
    }

    public static boolean isFilesDir(String str) {
        return str.startsWith("internal://files/");
    }

    public static boolean isCacheDir(String str) {
        return str.startsWith("internal://cache/");
    }

    public static Uri getUriFromFile(Context context, File file) {
        return FileProvider.getUriForFile(context, CommonUtil.getProviderAuthority(context), file);
    }

    public static String getNativeFilePath(Context context, String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.w("H5PRO_StorageUtil", "getNativeFilePath: invalid appId");
            return "";
        }
        String nativeFilePath = getNativeFilePath(context);
        if (TextUtils.isEmpty(nativeFilePath)) {
            return "";
        }
        return nativeFilePath + str + File.separator;
    }

    public static String getNativeFilePath(Context context) {
        if (context == null) {
            LogUtil.w("H5PRO_StorageUtil", "getNativeFilePath: context is null");
            return "";
        }
        File externalFilesDir = context.getApplicationContext().getExternalFilesDir("");
        if (externalFilesDir == null) {
            LogUtil.w("H5PRO_StorageUtil", "getNativeFilePath: externalFilesDir is null");
            return "";
        }
        return externalFilesDir.getCanonicalPath() + "/h5pro/files/";
    }

    public static String getNativeCachePath(Context context, String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.w("H5PRO_StorageUtil", "getNativeCachePath: invalid appId");
            return "";
        }
        String nativeCachePath = getNativeCachePath(context);
        if (TextUtils.isEmpty(nativeCachePath)) {
            return "";
        }
        return nativeCachePath + str + File.separator;
    }

    public static String getNativeCachePath(Context context) {
        if (context == null) {
            LogUtil.w("H5PRO_StorageUtil", "getNativeCachePath: context is null");
            return "";
        }
        File externalCacheDir = context.getApplicationContext().getExternalCacheDir();
        if (externalCacheDir == null) {
            LogUtil.w("H5PRO_StorageUtil", "getNativeCachePath: externalCacheDir is null");
            return "";
        }
        return externalCacheDir.getCanonicalPath() + "/h5pro/files/";
    }

    public static String getImageCachePath(Context context, String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.w("H5PRO_StorageUtil", "getImageCachePath: invalid appId");
            return "";
        }
        if (Build.VERSION.SDK_INT >= 30) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getCanonicalPath() + File.separator;
        }
        String nativeCachePath = getNativeCachePath(context);
        if (TextUtils.isEmpty(nativeCachePath)) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getCanonicalPath() + File.separator;
        }
        return nativeCachePath + str + File.separator;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x006b, code lost:
    
        if (r10 != null) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getFilePathFromUri(android.content.Context r9, android.net.Uri r10) {
        /*
            java.lang.String r0 = "file"
            java.lang.String r1 = r10.getScheme()
            boolean r0 = r0.equalsIgnoreCase(r1)
            java.lang.String r1 = ""
            if (r0 == 0) goto L13
            java.lang.String r0 = r10.getPath()
            goto L14
        L13:
            r0 = r1
        L14:
            java.lang.String r2 = "content"
            java.lang.String r3 = r10.getScheme()
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto L70
            android.content.ContentResolver r3 = r9.getContentResolver()
            java.lang.String r9 = "_data"
            java.lang.String[] r5 = new java.lang.String[]{r9}
            r6 = 0
            r7 = 0
            r8 = 0
            r4 = r10
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)
            if (r10 == 0) goto L6b
            boolean r2 = r10.moveToFirst()     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46 java.lang.IllegalArgumentException -> L48
            if (r2 == 0) goto L6b
            int r9 = r10.getColumnIndexOrThrow(r9)     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46 java.lang.IllegalArgumentException -> L48
            java.lang.String r9 = r10.getString(r9)     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46 java.lang.IllegalArgumentException -> L48
            r0 = r9
            goto L6b
        L44:
            r9 = move-exception
            goto L67
        L46:
            r9 = move-exception
            goto L49
        L48:
            r9 = move-exception
        L49:
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L44
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L44
            java.lang.String r4 = "getFilePathFromUri: exception -> "
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L44
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L44
            r3.append(r9)     // Catch: java.lang.Throwable -> L44
            java.lang.String r9 = r3.toString()     // Catch: java.lang.Throwable -> L44
            r3 = 0
            r2[r3] = r9     // Catch: java.lang.Throwable -> L44
            java.lang.String r9 = "H5PRO_StorageUtil"
            com.huawei.health.h5pro.utils.LogUtil.e(r9, r2)     // Catch: java.lang.Throwable -> L44
            goto L6d
        L67:
            r10.close()
            throw r9
        L6b:
            if (r10 == 0) goto L70
        L6d:
            r10.close()
        L70:
            if (r0 != 0) goto L73
            goto L74
        L73:
            r1 = r0
        L74:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil.getFilePathFromUri(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static String getAppFilePath(Context context, H5ProInstance h5ProInstance) {
        try {
            return getNativeFilePath(context, CommonUtil.getAppId(h5ProInstance));
        } catch (IOException unused) {
            return "";
        }
    }

    public static boolean ensureFileExists(File file) {
        if (file.exists()) {
            return true;
        }
        try {
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                return !parentFile.exists() ? parentFile.mkdirs() && file.createNewFile() : file.createNewFile();
            }
            LogUtil.e("H5PRO_StorageUtil", "get parent dir fail");
            return false;
        } catch (IOException unused) {
            LogUtil.e("H5PRO_StorageUtil", "ensureFileExists fail");
            return false;
        }
    }

    public static boolean ensureDirExists(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }
}
