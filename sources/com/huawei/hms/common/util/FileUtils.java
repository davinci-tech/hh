package com.huawei.hms.common.util;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/* loaded from: classes4.dex */
public class FileUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4472a = "FileUtils";

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.w(f4472a, "the pathname is empty.");
            return "";
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf <= 0 ? str : str.substring(0, lastIndexOf);
    }

    public static boolean b(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "The Path is null.";
        } else {
            try {
                File file = new File(str);
                if (file.exists()) {
                    return true;
                }
                return file.mkdirs();
            } catch (SecurityException e) {
                str2 = "MakeDirectory exception: " + e.getClass().getSimpleName();
            }
        }
        Logger.e(f4472a, str2);
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x004e, code lost:
    
        if (r5 != false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(java.lang.String r9) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            r1 = 1
            if (r0 == 0) goto Lf
            java.lang.String r9 = "FileUtils"
            java.lang.String r0 = "No need to delete anything."
            com.huawei.hms.common.util.Logger.w(r9, r0)
            return r1
        Lf:
            java.io.File r0 = new java.io.File
            r0.<init>(r9)
            boolean r2 = r0.isDirectory()
            r3 = 0
            if (r2 == 0) goto L50
            java.lang.String[] r2 = r0.list()
            if (r2 == 0) goto L50
            java.lang.String[] r2 = r0.list()
            int r4 = r2.length
            r5 = r1
            r6 = r3
        L28:
            if (r6 >= r4) goto L4e
            r7 = r2[r6]
            if (r5 == 0) goto L4a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r9)
            java.lang.String r8 = java.io.File.separator
            r5.append(r8)
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            boolean r5 = a(r5)
            if (r5 == 0) goto L4a
            r5 = r1
            goto L4b
        L4a:
            r5 = r3
        L4b:
            int r6 = r6 + 1
            goto L28
        L4e:
            if (r5 == 0) goto L57
        L50:
            boolean r9 = r0.delete()
            if (r9 == 0) goto L57
            goto L58
        L57:
            r1 = r3
        L58:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.common.util.FileUtils.a(java.lang.String):boolean");
    }

    public static boolean a(File file) {
        String str;
        if (file == null) {
            str = "targetFile is null.";
        } else {
            try {
                return file.setReadOnly();
            } catch (SecurityException e) {
                str = "SecurityException occur, message: " + e.getMessage();
            }
        }
        Logger.e(f4472a, str);
        return false;
    }

    public static void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
            Logger.e(f4472a, "An exception occurred while closing the closeable.");
        }
    }
}
