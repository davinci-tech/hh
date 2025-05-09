package com.huawei.watchface.mvp.model.crypt;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.huawei.libcore.io.ExternalStorageFile;
import com.huawei.libcore.io.ExternalStorageFileInputStream;
import com.huawei.libcore.io.ExternalStorageFileOutputStream;
import com.huawei.libcore.io.ExternalStorageRandomAccessFile;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceProvider;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.HwSfpPolicyManagerHelper;
import com.huawei.watchface.utils.WatchFaceBitmapUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* loaded from: classes7.dex */
public final class PversionSdUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f11078a;

    static {
        f11078a = Build.VERSION.SDK_INT >= 28 && CommonUtils.x();
    }

    private PversionSdUtils() {
    }

    public static RandomAccessFile a(File file, String str) throws FileNotFoundException {
        if (file == null || TextUtils.isEmpty(str)) {
            HwLog.e("PversionSdUtils", "file or model is null or empty");
            return null;
        }
        if (!f11078a) {
            return new RandomAccessFile(file, str);
        }
        return new ExternalStorageRandomAccessFile(file, str);
    }

    public static FileOutputStream a(File file) throws FileNotFoundException {
        if (file == null) {
            HwLog.e("PversionSdUtils", "file is null");
            return null;
        }
        if (!f11078a) {
            return new FileOutputStream(file);
        }
        return new ExternalStorageFileOutputStream(file);
    }

    public static FileInputStream a(String str) throws FileNotFoundException {
        String filterFilePath = CommonUtils.filterFilePath(str);
        if (filterFilePath == null) {
            HwLog.e("PversionSdUtils", "filePath is null");
            return null;
        }
        if (!f11078a) {
            return new FileInputStream(filterFilePath);
        }
        return new ExternalStorageFileInputStream(filterFilePath);
    }

    public static FileInputStream b(File file) throws FileNotFoundException {
        if (file == null) {
            HwLog.e("PversionSdUtils", "filePath is null");
            return null;
        }
        if (!f11078a) {
            return new FileInputStream(file);
        }
        return new ExternalStorageFileInputStream(file);
    }

    public static File getFile(String str) {
        String filterFilePath = CommonUtils.filterFilePath(str);
        if (filterFilePath == null) {
            HwLog.e("PversionSdUtils", "filePath is null");
            return null;
        }
        if (!f11078a) {
            return new File(filterFilePath);
        }
        return new ExternalStorageFile(filterFilePath);
    }

    public static File b(File file, String str) {
        String filterFilePath = CommonUtils.filterFilePath(str);
        if (filterFilePath == null) {
            HwLog.e("PversionSdUtils", "childPath is null");
            return null;
        }
        if (file == null) {
            HwLog.e("PversionSdUtils", "parent is null");
            return null;
        }
        if (!f11078a) {
            return new File(file, filterFilePath);
        }
        return new ExternalStorageFile(file, filterFilePath);
    }

    public static long a() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(CommonUtils.b((Context) null).getPath());
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        } catch (IllegalArgumentException unused) {
            HwLog.e("PversionSdUtils", "getAvailCount Exception");
            return -1L;
        }
    }

    public static void copyFile(String str, String str2) {
        StringBuilder sb;
        FileInputStream fileInputStream = null;
        try {
            try {
                fileInputStream = a(str);
                a(fileInputStream, str2);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e) {
                        e = e;
                        sb = new StringBuilder("PversionSdUtilscopyFile fis Exception:");
                        sb.append(HwLog.printException(e));
                        HwLog.e("PversionSdUtils", sb.toString());
                    }
                }
            } catch (Exception e2) {
                HwLog.e("PversionSdUtils", "PversionSdUtilscopyFile fis Exception:" + HwLog.printException(e2));
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e3) {
                        e = e3;
                        sb = new StringBuilder("PversionSdUtilscopyFile fis Exception:");
                        sb.append(HwLog.printException(e));
                        HwLog.e("PversionSdUtils", sb.toString());
                    }
                }
            }
        } catch (Throwable th) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e4) {
                    HwLog.e("PversionSdUtils", "PversionSdUtilscopyFile fis Exception:" + HwLog.printException(e4));
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0063 A[Catch: all -> 0x00a1, Exception -> 0x00a5, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x00a5, all -> 0x00a1, blocks: (B:15:0x0047, B:20:0x0063, B:33:0x008a, B:48:0x0051), top: B:14:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008a A[Catch: all -> 0x00a1, Exception -> 0x00a5, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x00a5, all -> 0x00a1, blocks: (B:15:0x0047, B:20:0x0063, B:33:0x008a, B:48:0x0051), top: B:14:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ee A[Catch: Exception -> 0x00ea, TRY_LEAVE, TryCatch #4 {Exception -> 0x00ea, blocks: (B:75:0x00e6, B:68:0x00ee), top: B:74:0x00e6 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00e6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void copyFileFromMedia(java.lang.String r5, android.net.Uri r6, java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.mvp.model.crypt.PversionSdUtils.copyFileFromMedia(java.lang.String, android.net.Uri, java.lang.String):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(InputStream inputStream, String str) {
        Object obj;
        FileOutputStream fileOutputStream;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        File file = getFile(str + "_tmp");
        if (file.exists()) {
            HwLog.i("PversionSdUtils", "PversionSdUtilscopyFile:" + file.delete());
        }
        Object obj2 = null;
        try {
            try {
                fileOutputStream = a(file);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
            fileOutputStream = null;
            bufferedInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            obj = null;
            fileOutputStream = null;
        }
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            bufferedInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            obj = null;
            a(obj2, obj, fileOutputStream);
            throw th;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    break;
                } else {
                    bufferedOutputStream.write(bArr, 0, read);
                }
            }
            bufferedOutputStream.flush();
            File file2 = getFile(str);
            if (file2.exists()) {
                HwLog.i("PversionSdUtils", "PversionSdUtilscopyFile:" + file2.delete());
            }
            boolean renameTo = file.renameTo(file2);
            HwSfpPolicyManagerHelper.setDefaultCeLabel(file2);
            StringBuilder sb = new StringBuilder();
            obj2 = "PversionSdUtilsrename:";
            sb.append("PversionSdUtilsrename:");
            sb.append(renameTo);
            HwLog.i("PversionSdUtils", sb.toString());
            a(bufferedInputStream, bufferedOutputStream, fileOutputStream);
        } catch (IOException e4) {
            e = e4;
            obj2 = bufferedOutputStream;
            HwLog.i("PversionSdUtils", "PversionSdUtilscopyFile Exception:" + file.delete() + HwLog.printException((Exception) e));
            a(bufferedInputStream, obj2, fileOutputStream);
        } catch (Throwable th4) {
            th = th4;
            obj2 = bufferedOutputStream;
            obj = obj2;
            obj2 = bufferedInputStream;
            a(obj2, obj, fileOutputStream);
            throw th;
        }
    }

    public static void a(Closeable... closeableArr) {
        if (closeableArr == null) {
            return;
        }
        try {
            for (Closeable closeable : closeableArr) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        } catch (IOException e) {
            HwLog.e("PversionSdUtils", "closeIO IOException " + HwLog.printException((Exception) e));
        }
    }

    public static boolean a(Context context, String str, String str2, String str3) {
        Bitmap f = CommonUtils.f(str);
        if (f == null) {
            HwLog.i("PversionSdUtils", "isSaveImg bitmap == null ");
            return false;
        }
        String latonaBackgroundSavedPath = str3.equals("1") ? LatonaWatchFaceProvider.getInstance(context).getLatonaBackgroundSavedPath() : null;
        WatchFaceBitmapUtil.saveBitmapToFile(f, Bitmap.CompressFormat.PNG, latonaBackgroundSavedPath, str2);
        f.recycle();
        if (getFile(latonaBackgroundSavedPath + str2).exists()) {
            HwLog.i("PversionSdUtils", "image save success");
            return true;
        }
        HwLog.i("PversionSdUtils", "image save failed");
        return false;
    }
}
