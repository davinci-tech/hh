package com.huawei.hms.framework.network.download.internal.utils;

import com.huawei.android.os.BuildEx;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.libcore.io.ExternalStorageFile;
import com.huawei.libcore.io.ExternalStorageFileInputStream;
import com.huawei.libcore.io.ExternalStorageRandomAccessFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes9.dex */
public class CreateFileUtil {
    private static final String BUILDEX_NAME = "com.huawei.android.os.BuildEx";
    private static final String EXTERNAL_FILE_NAME = "com.huawei.libcore.io.ExternalStorageFile";
    private static final String EXTERNAL_INPUTSTREAM_NAME = "com.huawei.libcore.io.ExternalStorageFileInputStream";
    private static final String RANDOM_ACCESS_FILE_NAME = "com.huawei.libcore.io.ExternalStorageRandomAccessFile";
    private static final String TAG = "CreateFileUtil";

    public static boolean isPVersion() {
        try {
            if (checkCompatible(BUILDEX_NAME)) {
                return BuildEx.VERSION.EMUI_SDK_INT >= 17;
            }
            return false;
        } catch (NoClassDefFoundError unused) {
            FLogger.d(TAG, "check PVersion is failed!", new Object[0]);
            return false;
        } catch (Throwable unused2) {
            FLogger.d(TAG, "check PVersion is failed and not NoClassDefFoundError!", new Object[0]);
            return false;
        }
    }

    public static File newFile(String str) {
        if (isPVersion() && checkCompatible(EXTERNAL_FILE_NAME)) {
            try {
                return new ExternalStorageFile(str);
            } catch (Throwable unused) {
                FLogger.w(TAG, "new ExternalStorageFile error", new Object[0]);
            }
        }
        return new File(str);
    }

    public static String getCanonicalPath(String str) {
        try {
            return newFile(str).getCanonicalPath();
        } catch (IOException e) {
            FLogger.w(TAG, "the canonicalPath has IOException", e);
            return str;
        } catch (SecurityException e2) {
            FLogger.w(TAG, "the canonicalPath has securityException", e2);
            return str;
        } catch (Exception e3) {
            FLogger.w(TAG, "the canonicalPath has other Exception", e3);
            return str;
        }
    }

    public static FileInputStream newFileInputStream(String str) throws FileNotFoundException {
        if (str == null) {
            FLogger.e(TAG, "newFileInputStream  file is null");
            throw new FileNotFoundException("file is null");
        }
        if (isPVersion() && checkCompatible(EXTERNAL_INPUTSTREAM_NAME)) {
            try {
                return new ExternalStorageFileInputStream(str);
            } catch (Throwable unused) {
                FLogger.w(TAG, "new ExternalStorageFileInputStream error", new Object[0]);
            }
        }
        return new FileInputStream(str);
    }

    public static RandomAccessFile newRandomAccessFile(String str, String str2) throws IOException {
        if (str == null) {
            FLogger.e(TAG, "newFileOutputStream  file is null");
            throw new IOException("file is null");
        }
        if (isPVersion() && checkCompatible(RANDOM_ACCESS_FILE_NAME)) {
            try {
                return new ExternalStorageRandomAccessFile(str, str2);
            } catch (Throwable unused) {
                FLogger.w(TAG, "new ExternalStorageRandomAccessFile error", new Object[0]);
            }
        }
        return new RandomAccessFile(str, str2);
    }

    private static boolean tryLoadClass(String str) throws ClassNotFoundException {
        ClassLoader classLoader = CreateFileUtil.class.getClassLoader();
        return (classLoader == null || classLoader.loadClass(str) == null) ? false : true;
    }

    private static boolean checkCompatible(String str) {
        try {
            FLogger.i(TAG, "checkCompatible start", new Object[0]);
            boolean tryLoadClass = tryLoadClass(str);
            FLogger.i(TAG, "checkCompatible end" + tryLoadClass, new Object[0]);
            return tryLoadClass;
        } catch (ClassNotFoundException unused) {
            FLogger.w(TAG, str + "ClassNotFoundException", new Object[0]);
            return false;
        } catch (Exception e) {
            FLogger.w(TAG, "checkCompatible exception", e);
            return false;
        }
    }
}
