package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.common.os.FileFilterUtils;
import com.huawei.haf.common.os.FileLockHelper;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.dynamic.DynamicApkValidator;
import health.compact.a.LogUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/* loaded from: classes8.dex */
final class ys implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    private final File f17765a;
    private final File c;
    private final FileLockHelper d;

    ys(File file, File file2) throws IOException {
        this.c = file2;
        this.f17765a = file;
        this.d = FileLockHelper.b(new File(file, "ModuleCopier.lock"));
    }

    boolean d(Context context, yi yiVar, boolean z) throws IOException {
        if (!this.d.c()) {
            throw new IllegalStateException("FileCheckerAndCopier was closed");
        }
        if (yiVar.k()) {
            b(context, yiVar, z);
            return true;
        }
        if (this.c.exists()) {
            LogUtil.b("Bundle_Preprocessor", "module ", yiVar.f(), " is downloaded");
            return e(context, yiVar, z);
        }
        LogUtil.b("Bundle_Preprocessor", "module ", yiVar.f(), " is not downloaded");
        return false;
    }

    private void b(Context context, yi yiVar, boolean z) throws IOException {
        if (!this.c.exists()) {
            c(context, yiVar, z);
        } else {
            if (e(context, yiVar, z)) {
                return;
            }
            c(context, yiVar, z);
        }
    }

    private void c(Context context, yi yiVar, boolean z) throws IOException {
        if (yiVar.g().startsWith("assets://")) {
            c(context, yiVar);
        }
        if (!e(context, yiVar, z)) {
            throw new IOException(String.format(Locale.ENGLISH, "Failed to check built-in module %s, it may be corrupted", yiVar.f()));
        }
    }

    private boolean e(Context context, yi yiVar, boolean z) {
        boolean c = FileUtils.c(this.c);
        if (z) {
            c = DynamicApkValidator.d(context, this.c, yiVar.f());
        }
        if (c) {
            c = d(yiVar);
        }
        if (!c) {
            LogUtil.a("Bundle_Preprocessor", "Oops! Failed to check module ", yiVar.f(), " signature and/or sha-256");
            e();
        }
        return c;
    }

    private boolean d(yi yiVar) {
        if (yiVar.h() != this.c.length()) {
            return false;
        }
        String c = SecurityUtils.c(this.c);
        if (TextUtils.isEmpty(c)) {
            LogUtil.a("Bundle_Preprocessor", "checkModuleSha256, get ", yiVar.f(), " sha-256 fail.");
            return true;
        }
        return yiVar.j().equals(c);
    }

    private void e() {
        File[] listFiles = this.f17765a.listFiles(new FileFilterUtils.FileNotEqualsCollectFilter("ModuleCopier.lock"));
        if (CollectionUtils.b(listFiles)) {
            return;
        }
        for (File file : listFiles) {
            FileUtils.b(file);
        }
    }

    private void c(Context context, yi yiVar) throws IOException {
        String str = "bundle_plugins/" + yiVar.f() + ".zip";
        File createTempFile = File.createTempFile("tmp-" + yiVar.f(), ".apk", yw.a().d());
        boolean z = false;
        int i = 0;
        while (!z && i < 3) {
            i++;
            try {
                InputStream open = context.getAssets().open(str);
                try {
                    FileUtils.a(open, createTempFile);
                    if (createTempFile.renameTo(this.c)) {
                        z = true;
                    } else {
                        LogUtil.a("Bundle_Preprocessor", "Failed to rename ", yw.d(createTempFile), " to ", yw.d(this.c));
                    }
                    if (open != null) {
                        open.close();
                    }
                } catch (Throwable th) {
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException unused) {
                LogUtil.a("Bundle_Preprocessor", "Failed to copy built-in module apk, attempts times : ", Integer.valueOf(i));
            }
            Object[] objArr = new Object[6];
            objArr[0] = "Copy built-in module ";
            objArr[1] = z ? "succeeded" : "failed";
            objArr[2] = " '";
            objArr[3] = yw.d(this.c);
            objArr[4] = "': length=";
            objArr[5] = Long.valueOf(this.c.length());
            LogUtil.c("Bundle_Preprocessor", objArr);
            if (!z) {
                FileUtils.i(this.c);
                if (this.c.exists()) {
                    LogUtil.a("Bundle_Preprocessor", "Failed to delete copied module apk which has been corrupted ", yw.d(this.c));
                }
            }
        }
        FileUtils.i(createTempFile);
        if (!z) {
            throw new IOException(String.format(Locale.ENGLISH, "Failed to copy built-in file %s to path %s", str, yw.d(this.c)));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.d.close();
    }
}
