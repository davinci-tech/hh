package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes6.dex */
public class pvu {
    public static boolean d(String str) {
        return str != null && new File(str).exists();
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0158 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0133 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(android.content.Context r11, java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 381
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pvu.b(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public static void e(String str, String str2) {
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            LogUtil.a("FileHelperUtils", "createFile create dir ", Boolean.valueOf(file.mkdirs()));
        }
        LogUtil.a("FileHelperUtils", "new File ");
        File file2 = new File(file + "/" + str2);
        LogUtil.a("FileHelperUtils", "new File end ");
        if (file2.exists()) {
            LogUtil.a("FileHelperUtils", "file.deleteOnExit();");
            if (file2.delete()) {
                LogUtil.a("FileHelperUtils", "createFile delete File");
            } else {
                LogUtil.h("FileHelperUtils", "createFile delete Fail");
            }
        }
        if (!file2.exists()) {
            try {
                LogUtil.a("FileHelperUtils", "try");
                if (file2.createNewFile()) {
                    LogUtil.a("FileHelperUtils", "create File");
                } else {
                    LogUtil.h("FileHelperUtils", "create fail");
                }
            } catch (IOException e) {
                LogUtil.b("FileHelperUtils", "IOException  " + e.getMessage());
            }
        }
        LogUtil.a("FileHelperUtils", "file Path " + file2.getPath());
    }
}
