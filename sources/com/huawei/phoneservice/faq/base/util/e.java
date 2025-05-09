package com.huawei.phoneservice.faq.base.util;

import android.content.Context;
import defpackage.uhy;
import java.io.File;
import kotlin.jvm.JvmStatic;

/* loaded from: classes5.dex */
public final class e {
    public static final e b = new e();

    @JvmStatic
    public static final File a(Context context) {
        uhy.e((Object) context, "");
        e eVar = b;
        return eVar.e(eVar.d(context));
    }

    @JvmStatic
    public static final String b(Context context) {
        uhy.e((Object) context, "");
        e eVar = b;
        return eVar.c(eVar.d(context));
    }

    private final File e(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        File file2 = new File(file, "Download" + File.separator + "FeedbackDownload");
        if (file2.isDirectory() || file2.mkdirs()) {
            return file2;
        }
        return null;
    }

    private final String c(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        File file2 = new File(file.toString() + File.separator + "picCache");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return file2.getAbsolutePath();
    }

    private final File d(Context context) {
        return context.getCacheDir();
    }

    private e() {
    }
}
