package defpackage;

import android.content.Context;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: classes8.dex */
final class yn implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private final Context f17759a;
    private final Collection<File> b;
    private final Collection<yi> e;

    yn(Context context, Collection<yi> collection, Collection<File> collection2) {
        this.f17759a = context;
        this.e = collection == null ? Collections.EMPTY_LIST : collection;
        this.b = collection2 == null ? Collections.EMPTY_LIST : collection2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!this.e.isEmpty()) {
            ProcessUtil.c();
            for (yi yiVar : this.e) {
                LogUtil.c("Bundle_DeleteTask", yiVar.f(), " need uninstalled, try delete its files");
                FileUtils.e(yw.a().g(yiVar));
            }
        }
        Iterator<yi> it = yg.a().a(this.f17759a).iterator();
        while (it.hasNext()) {
            a(it.next());
        }
        for (File file : this.b) {
            LogUtil.c("Bundle_DeleteTask", file.getName(), " is obsolete directory, try delete it.");
            FileUtils.e(file);
        }
    }

    private static void a(yi yiVar) {
        File g = yw.a().g(yiVar);
        final File b = yw.a().b(yiVar);
        final File e2 = yw.a().e(yiVar);
        final String name = g.getName();
        File[] listFiles = g.listFiles(new FileFilter() { // from class: yn.3
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                if (!file.isDirectory() || file.equals(b)) {
                    return false;
                }
                LogUtil.c("Bundle_DeleteTask", name, "_", file.getName(), " has been installed.");
                return e2.exists();
            }
        });
        if (listFiles != null) {
            if (listFiles.length <= 1) {
                return;
            }
            Arrays.sort(listFiles, new e());
            for (int i = 1; i < listFiles.length; i++) {
                LogUtil.c("Bundle_DeleteTask", name, "_", listFiles[i].getName(), " is redundant, try delete it.");
                FileUtils.e(listFiles[i]);
            }
        }
    }

    static Collection<yi> d(Context context) {
        Collection<yi> a2 = yg.a().a(context);
        ArrayList arrayList = new ArrayList(a2.size());
        for (yi yiVar : a2) {
            if (yw.a().i(yiVar).exists() && FileUtils.d(yw.a().e(yiVar))) {
                arrayList.add(yiVar);
            }
        }
        return arrayList;
    }

    static Collection<File> d(String str, Context context, yu yuVar, boolean z) {
        final File c = yw.a().c();
        File[] listFiles = c.getParentFile().listFiles(new FileFilter() { // from class: yn.2
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return file.isDirectory() && !file.equals(c);
            }
        });
        if (CollectionUtils.b(listFiles)) {
            return Collections.emptyList();
        }
        if (z) {
            if (listFiles.length > 1) {
                Arrays.sort(listFiles, new e());
            }
            a(str, context, yuVar, new yw(listFiles[0]));
        }
        return Arrays.asList(listFiles);
    }

    private static void a(String str, Context context, yu yuVar, yw ywVar) {
        yw a2 = yw.a();
        for (yi yiVar : yg.a().a(context)) {
            if (!a2.e(yiVar).exists() && ywVar.e(yiVar).exists() && !ywVar.i(yiVar).exists()) {
                try {
                    yuVar.c(ywVar.a(yiVar), yiVar);
                    File b = a2.b(yiVar);
                    if (b.delete() && ywVar.b(yiVar).renameTo(b)) {
                        LogUtil.c(str, yiVar.f(), File.separator, yiVar.i(), " is verify ok, try reuse it.");
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    static class e implements Comparator<File> {
        private e() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            if (file.lastModified() < file2.lastModified()) {
                return 1;
            }
            return file.lastModified() == file2.lastModified() ? 0 : -1;
        }
    }
}
