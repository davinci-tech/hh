package defpackage;

import android.text.TextUtils;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.common.os.FileUtils;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/* loaded from: classes8.dex */
final class yh {
    private String c;
    private final String e = AppBundleBuildConfig.e();
    private final File d = new File(yw.a().c(), "module_info_version");

    yh() {
        e();
    }

    private void e() {
        yj a2 = a();
        if (a2 == null) {
            LogUtil.c("Bundle_InfoVersionMgr", "No new module info version, just use default version.");
            this.c = this.e;
            return;
        }
        String e = a2.e();
        if (TextUtils.isEmpty(e)) {
            LogUtil.a("Bundle_InfoVersionMgr", "No old module info version, just use default version.");
            e = this.e;
        }
        String b = a2.b();
        if (e.equals(b)) {
            LogUtil.c("Bundle_InfoVersionMgr", "Module have been updated, so we use new module info version ", b);
            this.c = b;
        } else {
            if (ProcessUtil.d()) {
                if (b(new yj(b, b))) {
                    this.c = b;
                    ProcessUtil.c();
                    LogUtil.c("Bundle_InfoVersionMgr", "Module have been updated, start to kill other processes!");
                    return;
                } else {
                    this.c = e;
                    LogUtil.a("Bundle_InfoVersionMgr", "Failed to update new module info version:", b);
                    return;
                }
            }
            this.c = e;
        }
    }

    private boolean b(yj yjVar) {
        yf yfVar;
        yf yfVar2 = null;
        try {
            try {
                yfVar = new yf(this.d);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            yfVar = yfVar2;
        }
        try {
            boolean e2 = yfVar.e(yjVar);
            FileUtils.d(yfVar);
            return e2;
        } catch (IOException e3) {
            e = e3;
            yfVar2 = yfVar;
            LogUtil.e("Bundle_InfoVersionMgr", "updateVersionData ex=", LogUtil.a(e));
            FileUtils.d(yfVar2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            FileUtils.d(yfVar);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.Closeable] */
    private yj a() {
        Throwable th;
        IOException e;
        yf yfVar;
        ?? exists = this.d.exists();
        Closeable closeable = null;
        yj yjVar = null;
        try {
            if (exists == 0) {
                return null;
            }
            try {
                yfVar = new yf(this.d);
                try {
                    yjVar = yfVar.e();
                    exists = yfVar;
                } catch (IOException e2) {
                    e = e2;
                    LogUtil.e("Bundle_InfoVersionMgr", "readVersionData ex=", LogUtil.a(e));
                    exists = yfVar;
                    FileUtils.d((Closeable) exists);
                    return yjVar;
                }
            } catch (IOException e3) {
                e = e3;
                yfVar = null;
            } catch (Throwable th2) {
                th = th2;
                FileUtils.d(closeable);
                throw th;
            }
            FileUtils.d((Closeable) exists);
            return yjVar;
        } catch (Throwable th3) {
            closeable = exists;
            th = th3;
        }
    }

    boolean c(String str, File file) {
        if (!this.d.exists() && !this.d.mkdirs()) {
            LogUtil.a("Bundle_InfoVersionMgr", "Failed to make dir for module info file!");
            return false;
        }
        File file2 = new File(this.d, "bundle_" + str + ".json");
        try {
            FileUtils.d(file2);
            FileUtils.d(file, file2);
            FileUtils.f(file2);
            FileUtils.d(file);
            if (!b(new yj(this.c, str))) {
                return false;
            }
            LogUtil.c("Bundle_InfoVersionMgr", "Success to update module info version, current version ", this.c, ", new version ", str);
            return true;
        } catch (IOException e) {
            LogUtil.e("Bundle_InfoVersionMgr", "Failed to rename file:", file.getPath(), ", ex=", LogUtil.a(e));
            return false;
        }
    }

    String d() {
        return this.e;
    }

    String b() {
        return this.c;
    }

    File c() {
        return this.d;
    }
}
