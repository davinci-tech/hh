package defpackage;

import android.content.Context;
import com.huawei.haf.bundle.extension.BundleExtension;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.haf.dynamic.DynamicApkValidator;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public final class yu {
    private final Context c;
    private final boolean e;

    yu(Context context, boolean z) {
        this.c = context;
        this.e = z;
    }

    public c e(boolean z, yi yiVar) throws d {
        File j = yw.a().j(yiVar);
        try {
            File a2 = yw.a().a(yiVar);
            File e = yw.a().e(yiVar);
            if (!yiVar.k() && !e.exists() && FileUtils.c(j) && !a2.exists() && !j.renameTo(a2)) {
                LogUtil.a("Bundle_Installer", "Failed to rename ", yw.d(j), " to ", yw.d(a2));
            }
            return e(z, yiVar, a2, e);
        } finally {
            FileUtils.d(j);
        }
    }

    private c e(boolean z, yi yiVar, File file, File file2) throws d {
        c(file, yiVar);
        long lastModified = file2.exists() ? file2.lastModified() : System.currentTimeMillis();
        file.setLastModified(lastModified);
        ArrayList arrayList = null;
        File e = yiVar.m() ? e(yiVar, file, lastModified) : null;
        if (yiVar.l()) {
            arrayList = new ArrayList();
            arrayList.add(file.getAbsolutePath());
        }
        if (arrayList != null) {
            yr.d(yiVar, arrayList, e, z);
        }
        b(yiVar, file2, lastModified);
        return new c(yiVar.f(), file, arrayList);
    }

    public void a(yi yiVar) throws d {
        File i = yw.a().i(yiVar);
        if (i.exists()) {
            return;
        }
        try {
            if (yw.a().e(yiVar).exists()) {
                FileUtils.c(i, false);
            }
            String f = yiVar.f();
            BundleExtension.a().updateModuleState(this.c, f, false);
            BundleExtension.a().setAllowDownloadModule(this.c, f, false);
        } catch (IOException e) {
            throw new d(-15, e);
        }
    }

    public void c(File file, yi yiVar) throws d {
        try {
            if (!FileUtils.c(file)) {
                throw new d(-11, new FileNotFoundException("Module apk " + yw.d(file) + " is illegal!"));
            }
            FileUtils.f(file);
            if (this.e) {
                b(file, yiVar.f());
            }
            d(file, yiVar);
        } catch (d e) {
            FileUtils.d(file);
            throw e;
        }
    }

    private void b(File file, String str) throws d {
        if (DynamicApkValidator.d(this.c, file, str)) {
            return;
        }
        throw new d(-12, new SignatureException("Failed to check module apk " + yw.d(file) + " signature!"));
    }

    private void d(File file, yi yiVar) throws d {
        long length = file.length();
        if (length != yiVar.h()) {
            throw new d(-13, new IOException("Failed to check module apk size, expect " + yiVar.h() + " but " + length));
        }
        String c2 = SecurityUtils.c(file);
        if (yiVar.j().equals(c2)) {
            return;
        }
        throw new d(-13, new IOException("Failed to check module apk sha-256, expect " + yiVar.j() + " but " + c2));
    }

    private File e(yi yiVar, File file, long j) throws d {
        try {
            File d2 = yw.a().d(yiVar);
            yx yxVar = new yx(file, d2);
            try {
                try {
                    Iterator<File> it = yxVar.a(yiVar, false).iterator();
                    while (it.hasNext()) {
                        it.next().setLastModified(j);
                    }
                    return d2;
                } catch (IOException e) {
                    LogUtil.a("Bundle_Installer", "Failed to load or extract lib files, ex=", LogUtil.a(e));
                    throw new d(-14, e);
                }
            } finally {
                FileUtils.d(yxVar);
            }
        } catch (IOException e2) {
            throw new d(-14, e2);
        }
    }

    private void b(yi yiVar, File file, long j) throws d {
        if (!file.exists()) {
            try {
                FileUtils.c(file, true);
            } catch (IOException e) {
                throw new d(-15, e);
            }
        }
        file.setLastModified(j);
        BundleExtension.a().updateModuleState(this.c, yiVar.f(), true);
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        final File f17767a;
        final List<String> b;
        final String e;

        c(String str, File file, List<String> list) {
            this.e = str;
            this.f17767a = file;
            this.b = list;
        }
    }

    public static final class d extends Exception {
        private static final long serialVersionUID = 6587079876353699025L;
        private final int d;

        d(int i, Throwable th) {
            super("Module Install Error: " + i, th);
            this.d = i;
        }

        public int c() {
            return this.d;
        }
    }
}
