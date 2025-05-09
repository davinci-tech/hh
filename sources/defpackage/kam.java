package defpackage;

import android.os.StatFs;
import android.text.TextUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class kam {
    public static boolean d(List<String> list, String str, String str2) {
        boolean z = list == null || list.isEmpty();
        boolean z2 = TextUtils.isEmpty(str) || TextUtils.isEmpty(str2);
        if (z || z2) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "FileUtils", "invalid parameters: vcards = " + list + ", vcard dir = " + str + ", vcardFileSuffixName = " + str2);
            return false;
        }
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            boolean d = d(list.get(i2), str + (i2 + str2), false);
            StringBuilder sb = new StringBuilder("write file result: ");
            sb.append(d);
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "FileUtils", sb.toString());
            if (d) {
                i++;
            }
        }
        return i == size;
    }

    public static boolean d(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "FileUtils", "invalid parameters: originDirectory = " + str + ", destDirectory = " + str2 + ", fileName = " + str3);
            return false;
        }
        msp.b(str, str2, str3);
        return true;
    }

    public static boolean a(List<String> list, String str, String str2) {
        if (list == null || list.isEmpty() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "FileUtils", "invalid parameters: uuidList = " + list + ", csvFileDir = " + str + ", fileName = " + str2);
            return false;
        }
        String str3 = str + str2;
        int i = 0;
        for (String str4 : list) {
            if (!TextUtils.isEmpty(str4)) {
                boolean d = d(str4 + ",", str3, true);
                StringBuilder sb = new StringBuilder("write file result: ");
                sb.append(d);
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "FileUtils", sb.toString());
                if (d) {
                    i++;
                }
            }
        }
        return i == list.size();
    }

    public static void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("03", 1, "FileUtils", "dir path is null or empty. ");
        } else {
            e(new File(str));
        }
    }

    private static void e(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2 != null) {
                if (file2.isFile()) {
                    LogUtil.a("03", 1, "FileUtils", "deleteDirWithFile isDeleted=", Boolean.valueOf(file2.delete()));
                } else if (file2.isDirectory()) {
                    e(file2);
                } else {
                    LogUtil.a("03", 1, "FileUtils", "file type error");
                }
            }
        }
        LogUtil.c("FileUtils", "deleteDirWithFile result=", Boolean.valueOf(file.delete()));
    }

    private static boolean d(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        File file = new File(str2);
        if (file.exists() && !z && !file.delete()) {
            LogUtil.a("03", 1, "FileUtils", "writeToFile: file delete failed.");
        }
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            if (!parentFile.exists() && !parentFile.mkdirs()) {
                LogUtil.a("03", 1, "FileUtils", "writeToFile: failed to create file's parent.");
                return false;
            }
            if (e(parentFile.getPath()) < str.length()) {
                LogUtil.a("03", 1, "FileUtils", "writeToFile: have no space to write the file.");
                return false;
            }
        }
        return d(file, str.getBytes(StandardCharsets.UTF_8), z);
    }

    private static boolean d(File file, byte[] bArr, boolean z) {
        try {
            FileOutputStream e = e(file, z);
            try {
                e.write(bArr);
                e.flush();
                if (e != null) {
                    e.close();
                }
                return true;
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.b("03", 1, "FileUtils", "writeToFile: failed to write the file.");
            return false;
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("03", 1, "FileUtils", "IllegalArgumentException: failed to write the file .");
            return false;
        }
    }

    private static long e(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (IllegalArgumentException e) {
            LogUtil.b("03", 1, "FileUtils", "getFreeSpaceOfPath IllegalArgumentException." + e.getMessage());
            return 0L;
        }
    }

    private static FileOutputStream e(File file, boolean z) throws IOException, IllegalArgumentException {
        return FileUtils.openOutputStream(file, z);
    }
}
