package defpackage;

import android.os.FileObserver;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.haf.common.os.FileFilterUtils;
import com.huawei.haf.common.os.FileUtils;
import defpackage.yi;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes8.dex */
final class zg extends FileObserver {
    private static final Map<String, zg> e = new ConcurrentHashMap();
    private final yi b;
    private final long d;

    private zg(yi yiVar, List<File> list, long j) {
        super(list, 524);
        this.b = yiVar;
        this.d = j;
    }

    @Override // android.os.FileObserver
    public void onEvent(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.endsWith(".so") || str.endsWith(".apk")) {
            a(new b(i, str), 0L);
        }
    }

    private void a(Runnable runnable, long j) {
        DfxMonitorCenter.b(runnable, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v12, types: [yi] */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
    public void c(String str, int i) {
        IllegalStateException e2;
        File file;
        File e3 = yw.a().e(this.b);
        try {
            file = str.endsWith(".so");
            try {
                if (file != 0) {
                    new File(yw.a().d(this.b), str);
                    d(this.b, this.d, (List<File>) null);
                } else if (str.endsWith(".apk")) {
                    yw.a().a(this.b);
                    c(this.b, this.d, null);
                }
                try {
                    file = this.b;
                    c(file, e3, this.d, 0L);
                } catch (IllegalStateException e4) {
                    e2 = e4;
                    file = e3;
                    d(file, str, i);
                    Map<String, zg> map = e;
                    if (map.isEmpty()) {
                        return;
                    }
                    map.clear();
                    LogUtil.e("Bundle_FileObserver", "Detected attacks and sabotage, ex=", LogUtil.a(e2));
                    FileUtils.d(e3);
                    LogUtil.a("Bundle_FileObserver", "!!! === exit by app kill self === !!!");
                    a(new b(0, null), 1000L);
                }
            } catch (IllegalStateException e5) {
                e2 = e5;
            }
        } catch (IllegalStateException e6) {
            e2 = e6;
            file = 0;
        }
    }

    private void d(File file, String str, int i) {
        if (LogUtil.a()) {
            return;
        }
        if (file.exists()) {
            LogUtil.c("Bundle_FileObserver", "module=", this.b.f(), ", path=", str, ", event=", Integer.valueOf(i), ", write=", Boolean.valueOf(file.canWrite()), ", read=", Boolean.valueOf(file.canRead()), ", execute=", Boolean.valueOf(file.canExecute()), ", length=", Long.valueOf(file.length()), ", time=", Long.valueOf(file.lastModified()));
        } else {
            LogUtil.c("Bundle_FileObserver", "module=", this.b.f(), ", path=", str, ", event=", Integer.valueOf(i));
        }
    }

    static void c(yi yiVar) throws zf {
        Map<String, zg> map = e;
        if (map.containsKey(yiVar.f())) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        long lastModified = yw.a().e(yiVar).lastModified();
        try {
            c(yiVar, lastModified, arrayList);
            d(yiVar, lastModified, arrayList);
            zg zgVar = new zg(yiVar, arrayList, lastModified);
            map.put(yiVar.f(), zgVar);
            zgVar.startWatching();
        } catch (IllegalStateException e2) {
            throw new zf(-27, e2);
        }
    }

    static void d(yi yiVar) {
        zg remove = e.remove(yiVar.f());
        if (remove != null) {
            remove.stopWatching();
        }
    }

    public static boolean e(yi yiVar) {
        File e2 = yw.a().e(yiVar);
        if (!e2.exists()) {
            return false;
        }
        long lastModified = e2.lastModified();
        try {
            c(yiVar, lastModified, null);
            d(yiVar, lastModified, (List<File>) null);
            return true;
        } catch (IllegalStateException e3) {
            LogUtil.a("Bundle_FileObserver", "verify module ", yiVar.f(), " fail. ex=", LogUtil.a(e3));
            FileUtils.d(e2);
            return false;
        }
    }

    private static void c(yi yiVar, long j, List<File> list) {
        c(yiVar, yw.a().a(yiVar), j, yiVar.h());
        if (list != null) {
            list.add(yw.a().b(yiVar));
        }
    }

    private static void d(yi yiVar, long j, List<File> list) {
        if (yiVar.m()) {
            File d = yw.a().d(yiVar);
            List<yi.c.C0336c> b2 = yiVar.b().b();
            File[] listFiles = d.listFiles(new FileFilterUtils.FileNotEqualsCollectFilter("ModuleLib.lock"));
            if (listFiles != null && listFiles.length != b2.size()) {
                throw new IllegalStateException(yiVar.f() + " exist unknown or missing file present.");
            }
            for (yi.c.C0336c c0336c : b2) {
                c(yiVar, new File(d, c0336c.d()), j, c0336c.a());
            }
            if (list != null) {
                list.add(d);
            }
        }
    }

    private static void c(yi yiVar, File file, long j, long j2) {
        if ((j2 <= 0 || !file.canWrite()) && file.length() == j2 && file.lastModified() == j) {
            return;
        }
        throw new IllegalStateException(yiVar.f() + " file '" + file.getName() + "' has been illegally modified or deleted.");
    }

    class b implements Runnable {
        private final int c;
        private final String d;

        b(int i, String str) {
            this.d = str;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = this.d;
            if (str != null) {
                zg.this.c(str, this.c);
            } else {
                Process.killProcess(Process.myPid());
            }
        }
    }
}
