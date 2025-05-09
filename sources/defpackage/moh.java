package defpackage;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.util.LogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes6.dex */
public class moh {
    public static String e(InputStream inputStream) {
        String str;
        str = "";
        if (inputStream == null) {
            LogUtil.c("Suggestion_FileUtil", "getString, inputStream is null.");
            return "";
        }
        try {
            try {
                byte[] bArr = new byte[inputStream.available()];
                str = inputStream.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LogUtil.e("Suggestion_FileUtil", ExceptionUtils.d(e));
                }
            } catch (IOException e2) {
                LogUtil.e("Suggestion_FileUtil", ExceptionUtils.d(e2));
            }
            return str;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e3) {
                LogUtil.e("Suggestion_FileUtil", ExceptionUtils.d(e3));
            }
        }
    }

    public static String d(Context context, String str) {
        try {
            if (context == null) {
                LogUtil.c("Suggestion_FileUtil", "getStringByAssetManager, context is null.");
                return "";
            }
            AssetManager assets = context.getAssets();
            return assets == null ? "" : e(assets.open(str));
        } catch (IOException e) {
            LogUtil.e("Suggestion_FileUtil", ExceptionUtils.d(e));
            return "";
        }
    }

    public static boolean b(File file) {
        if (file == null) {
            LogUtil.c("Suggestion_FileUtil", "isDeleteFile, file is null.");
            return false;
        }
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (file.isDirectory()) {
            a(file);
        }
        return file.delete();
    }

    private static void a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2 != null) {
                b(file2);
            }
        }
    }

    public static boolean b(String str) {
        return c(new File(str));
    }

    public static boolean c(File file) {
        if (file == null) {
            LogUtil.c("Suggestion_FileUtil", "isDeleteDirSuccess, file is null.");
            return false;
        }
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (file.isDirectory()) {
            d(file);
        }
        return file.delete();
    }

    private static void d(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2 != null) {
                c(file2);
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:8|(2:13|(6:20|(1:22)|23|24|25|26)(3:17|18|19))|27|28|29|30|(2:32|(3:34|35|36)(6:37|(2:38|(1:40)(1:41))|42|43|25|26))(2:44|45)) */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e2, code lost:
    
        r12 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e3, code lost:
    
        r3 = r11;
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ee, code lost:
    
        health.compact.a.util.LogUtil.e("Suggestion_FileUtil", com.huawei.haf.common.exception.ExceptionUtils.d(r12));
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00fd, code lost:
    
        r12 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00fe, code lost:
    
        c(r3, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0101, code lost:
    
        throw r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00dd, code lost:
    
        r12 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00de, code lost:
    
        r3 = r11;
        r11 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r11, java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.moh.a(android.content.Context, java.lang.String, java.lang.String):void");
    }

    private static void c(InputStream inputStream, FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                LogUtil.e("Suggestion_FileUtil", "putAssetsToSdCard fos ", ExceptionUtils.d(e));
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                LogUtil.e("Suggestion_FileUtil", "putAssetsToSdCard mIs ", ExceptionUtils.d(e2));
            }
        }
    }

    public static InputStream e(String str, String str2) {
        if (str != null && str2 != null && !str.isEmpty() && !str2.isEmpty()) {
            LogUtil.d("Suggestion_FileUtil", "getFileInputStream moduleName = ", str, " and fileName = ", str2);
            AssetManager assets = BaseApplication.getContext().getAssets();
            if (assets == null) {
                com.huawei.hwlogsmodel.LogUtil.h("Suggestion_FileUtil", "getFileInputStream assetManager == null");
                return null;
            }
            try {
                for (String str3 : assets.list(str)) {
                    if (str2.equals(str3)) {
                        return assets.open(str + "/" + str2);
                    }
                }
            } catch (IOException e) {
                com.huawei.hwlogsmodel.LogUtil.h("Suggestion_FileUtil", "getFileInputStream IOException " + e.getMessage());
            }
            com.huawei.hwlogsmodel.LogUtil.h("Suggestion_FileUtil", "getFileInputStream folderName or fileName error");
        }
        return null;
    }

    public static long a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("Suggestion_FileUtil", "getFolderSize filename is null");
            return -1L;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            com.huawei.hwlogsmodel.LogUtil.b("Suggestion_FileUtil", "getFolderSize err");
            return -1L;
        }
        return file.length();
    }
}
