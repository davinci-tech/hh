package defpackage;

import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.security.SecurityUtils;
import java.io.File;

/* loaded from: classes8.dex */
public final class yw {

    /* renamed from: a, reason: collision with root package name */
    private static final yw f17768a = new yw(null);
    private final File e;

    yw(File file) {
        if (file == null) {
            File dir = AppBundleBuildConfig.c().getDir("bundle", 0);
            file = new File(dir, b(dir));
        }
        this.e = file;
    }

    public static yw a() {
        return f17768a;
    }

    public File c() {
        return FileUtils.a(this.e);
    }

    public static String d(File file) {
        String path = file.getPath();
        String path2 = a().e.getPath();
        return path.startsWith(path2) ? path.substring(path2.length()) : path;
    }

    public File g(yi yiVar) {
        return FileUtils.a(f(yiVar));
    }

    public File b(yi yiVar) {
        return FileUtils.a(h(yiVar));
    }

    public File j(yi yiVar) {
        return new File(b(yiVar), yiVar.f() + ".tmp");
    }

    public File a(yi yiVar) {
        return new File(b(yiVar), yiVar.f() + ".apk");
    }

    public File e(yi yiVar) {
        return new File(b(yiVar), yiVar.j());
    }

    public File i(yi yiVar) {
        return new File(g(yiVar), yiVar.j() + "_uninstall");
    }

    public File c(yi yiVar) {
        File file = new File(h(yiVar), "oat");
        if (!file.exists() && file.mkdirs()) {
            file.setWritable(true);
            file.setReadable(true);
        }
        return file;
    }

    public File d(yi yiVar) {
        return FileUtils.a(new File(h(yiVar), "nativeLib" + File.separator + yiVar.b().d()));
    }

    public File d() {
        return FileUtils.a(new File(this.e, "tmp"));
    }

    private File f(yi yiVar) {
        return new File(this.e, yiVar.f());
    }

    private File h(yi yiVar) {
        return new File(f(yiVar), yiVar.i());
    }

    private String b(File file) {
        long abs = Math.abs(AppBundleBuildConfig.b() - a(file));
        StringBuilder sb = new StringBuilder(64);
        sb.append(AppBundleBuildConfig.a());
        sb.append('_');
        sb.append(abs);
        String c = SecurityUtils.c(sb.toString());
        sb.delete(0, sb.length());
        if (c.length() > 24) {
            sb.append(AppBundleBuildConfig.l());
            sb.append('_');
            sb.append(c.substring(c.length() - 24));
        } else {
            sb.append(AppBundleBuildConfig.a());
            sb.append('_');
            sb.append(Long.toHexString(abs));
        }
        return sb.toString();
    }

    private long a(File file) {
        File file2 = new File(file, "done.lock");
        FileUtils.e(file2, true);
        return file2.lastModified();
    }
}
