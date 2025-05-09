package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.os.StatFs;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.android.hicloud.sync.util.FileUtil;
import com.huawei.health.R;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.openalliance.ad.ho;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public abstract class ae {
    private static void i(File file) {
        if (file == null) {
            return;
        }
        int i = 0;
        while (i < 10 && file != null && !cx.a(h(file))) {
            i++;
            if (file.exists()) {
                ho.a(FileUtil.TAG, "current file exists");
                if (file.isFile()) {
                    a(file);
                    return;
                }
                return;
            }
            File parentFile = file.getParentFile();
            if (parentFile != null && TextUtils.equals(h(parentFile), h(file))) {
                ho.c(FileUtil.TAG, "parent file is the same as current");
                return;
            }
            file = parentFile;
        }
    }

    public static String h(File file) {
        if (file == null) {
            return null;
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            ho.d(FileUtil.TAG, "get path error, " + e.getClass().getSimpleName());
            return null;
        }
    }

    public static boolean g(File file) {
        if (file == null || file.mkdirs()) {
            return true;
        }
        i(file);
        return file.mkdirs();
    }

    public static boolean f(File file) {
        String str;
        if (file == null) {
            return false;
        }
        try {
        } catch (IOException unused) {
            str = "reCreateFile IOException";
            ho.c(FileUtil.TAG, str);
            return false;
        } catch (Exception unused2) {
            str = "reCreateFile Exception";
            ho.c(FileUtil.TAG, str);
            return false;
        }
        if (!file.exists() && file.createNewFile()) {
            return true;
        }
        if (file.delete()) {
            if (file.createNewFile()) {
                return true;
            }
        }
        return false;
    }

    public static void e(File file) {
        String str;
        if (file == null || !file.exists()) {
            ho.a(FileUtil.TAG, "file is null or file in not exists");
            return;
        }
        if (file.isFile()) {
            File file2 = new File(h(file) + "_delete");
            if (file.renameTo(file2)) {
                if (file2.delete()) {
                    return;
                }
            } else if (file.delete()) {
                return;
            }
            str = "fail to delete file";
        } else if (file.delete()) {
            return;
        } else {
            str = "cannot delete file";
        }
        ho.c(FileUtil.TAG, str);
    }

    public static String e(String str) {
        return str.substring(str.lastIndexOf(".")).trim();
    }

    public static boolean d(File file) {
        return file.exists() && file.length() > 0;
    }

    public static Long d(String str) {
        StringBuilder sb;
        try {
            return Long.valueOf(new StatFs(str).getTotalBytes());
        } catch (IllegalArgumentException e) {
            e = e;
            sb = new StringBuilder("getDiskTotalSpace ");
            sb.append(e.getClass().getSimpleName());
            ho.c(FileUtil.TAG, sb.toString());
            return null;
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("getDiskTotalSpace ");
            sb.append(e.getClass().getSimpleName());
            ho.c(FileUtil.TAG, sb.toString());
            return null;
        }
    }

    public static boolean c(Context context, String str, String str2) {
        if (!com.huawei.openalliance.ad.dk.i(str)) {
            return d(new File(str));
        }
        if (cz.b(str2)) {
            str2 = "normal";
        }
        return com.huawei.openalliance.ad.dh.a(context, str2).g(str);
    }

    public static void c(File file) {
        RandomAccessFile randomAccessFile;
        String str;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (Throwable th) {
                th = th;
                randomAccessFile = randomAccessFile2;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        }
        try {
            long length = randomAccessFile.length();
            randomAccessFile.setLength(1 + length);
            randomAccessFile.setLength(length);
            cy.a(randomAccessFile);
        } catch (FileNotFoundException unused3) {
            randomAccessFile2 = randomAccessFile;
            str = "fail to update modify time, file not exist";
            ho.d(FileUtil.TAG, str);
            cy.a(randomAccessFile2);
        } catch (IOException unused4) {
            randomAccessFile2 = randomAccessFile;
            str = "fail to update modify time, read file exception";
            ho.d(FileUtil.TAG, str);
            cy.a(randomAccessFile2);
        } catch (Throwable th2) {
            th = th2;
            cy.a(randomAccessFile);
            throw th;
        }
    }

    public static String c(long j) {
        String b = b(j);
        if (b.endsWith(ExifInterface.GPS_DIRECTION_TRUE)) {
            return b;
        }
        if (cz.b(b) || !b.endsWith("G")) {
            return "";
        }
        try {
            long parseLong = Long.parseLong(b.substring(0, b.length() - 1));
            long pow = (long) Math.pow(2.0d, (int) (Math.log(parseLong) / Math.log(2.0d)));
            if (parseLong > pow) {
                pow = (long) Math.pow(2.0d, r8 + 1);
            }
            return pow + "G";
        } catch (Throwable th) {
            ho.c(FileUtil.TAG, "getStorageSize " + th.getClass().getSimpleName());
            return "";
        }
    }

    public static Long c(String str) {
        StringBuilder sb;
        try {
            StatFs statFs = new StatFs(str);
            return Long.valueOf(statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
        } catch (IllegalArgumentException e) {
            e = e;
            sb = new StringBuilder("getDiskFreeSpace ");
            sb.append(e.getClass().getSimpleName());
            ho.c(FileUtil.TAG, sb.toString());
            return null;
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("getDiskFreeSpace ");
            sb.append(e.getClass().getSimpleName());
            ho.c(FileUtil.TAG, sb.toString());
            return null;
        }
    }

    public static boolean b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return new File(str).exists();
        } catch (Exception unused) {
            ho.d(FileUtil.TAG, "check file exists error");
            return false;
        }
    }

    public static boolean b(File file, long j) {
        return file.exists() && file.length() > 0 && file.length() <= j;
    }

    public static boolean b(Context context, String str) {
        return com.huawei.openalliance.ad.dk.i(str) ? com.huawei.openalliance.ad.dh.a(context, "normal").f(str) : b(str);
    }

    public static void b(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        b(file2);
                    } else {
                        a(file2);
                    }
                }
            }
            a(file);
        }
    }

    public static String b(long j) {
        try {
            if (j < 1024) {
                return j + "B";
            }
            long ceil = (long) Math.ceil((j * 1.0d) / 1024.0d);
            if (ceil < 1024) {
                return ceil + "K";
            }
            long ceil2 = (long) Math.ceil((ceil * 1.0d) / 1024.0d);
            if (ceil2 < 1024) {
                return ceil2 + "M";
            }
            long ceil3 = (long) Math.ceil((ceil2 * 1.0d) / 1024.0d);
            if (ceil3 < 1024) {
                return ceil3 + "G";
            }
            return ((long) Math.ceil((ceil3 * 1.0d) / 1024.0d)) + ExifInterface.GPS_DIRECTION_TRUE;
        } catch (Throwable th) {
            ho.c(FileUtil.TAG, "getFileSize " + th.getClass().getSimpleName());
            return "";
        }
    }

    public static File b(Context context, String str, String str2) {
        if (com.huawei.openalliance.ad.dk.i(str)) {
            if (cz.b(str2)) {
                str2 = "normal";
            }
            str = com.huawei.openalliance.ad.dh.a(context, str2).c(str);
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new File(str);
    }

    public static boolean a(String str, File file) {
        if (cz.b(str)) {
            return false;
        }
        return str.equalsIgnoreCase(cu.a(file));
    }

    public static boolean a(File file, String str) {
        return a(file, new File(str));
    }

    public static boolean a(File file, File file2) {
        e(file2);
        return file.renameTo(file2);
    }

    public static boolean a(File file) {
        try {
            File file2 = new File(file.getCanonicalPath() + System.currentTimeMillis());
            if (file.renameTo(file2)) {
                return file2.delete();
            }
            return false;
        } catch (IOException unused) {
            ho.c(FileUtil.TAG, "deleteSingleFile IOException");
            return false;
        }
    }

    public static boolean a(Context context, File file, String str, String str2) {
        return a(context, file, str, null, str2);
    }

    public static boolean a(Context context, File file, String str, ContentResource contentResource, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!com.huawei.openalliance.ad.dk.i(str)) {
            return a(file, str);
        }
        if (cz.b(str2)) {
            str2 = "normal";
        }
        return com.huawei.openalliance.ad.dh.a(context, str2).a(file, str, contentResource);
    }

    public static boolean a(long j, String str) {
        Long c = c(str);
        return c == null || j <= c.longValue();
    }

    public static void a(String str, long j) {
        File[] listFiles;
        if (cz.b(str)) {
            return;
        }
        File file = new File(str);
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.lastModified() + j < ao.c()) {
                a(file2);
            }
        }
    }

    public static void a(String str) {
        if (cz.b(str)) {
            return;
        }
        b(new File(str));
    }

    public static void a(File file, long j, List<File> list) {
        new z().a(file, j, list);
    }

    public static void a(File file, long j) {
        a(file, j, (List<File>) null);
    }

    public static void a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!com.huawei.openalliance.ad.dk.i(str)) {
            e(new File(str));
            return;
        }
        if (cz.b(str2)) {
            str2 = "normal";
        }
        com.huawei.openalliance.ad.dh.a(context, str2).j(str);
    }

    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a(context, str, "normal");
    }

    public static String a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[4];
            if (inputStream.read(bArr, 0, 4) > 0) {
                return an.a(bArr);
            }
            return null;
        } catch (IOException unused) {
            ho.d(FileUtil.TAG, "fail to read file header");
            return null;
        }
    }

    public static String a(Context context, long j) {
        return context == null ? "" : context.getString(R.string._2130851062_res_0x7f0234f6, a(j));
    }

    static String a(long j) {
        float f = (j * 1.0f) / 1048576.0f;
        if (f < 0.1f) {
            f = 0.1f;
        }
        return String.format(Locale.getDefault(), "%.1f", Float.valueOf(f));
    }
}
