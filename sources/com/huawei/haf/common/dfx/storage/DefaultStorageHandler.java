package com.huawei.haf.common.dfx.storage;

import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.os.FileVisitUtils;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes.dex */
public class DefaultStorageHandler extends DfxBaseHandler implements StorageCallback {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f2092a;

    protected boolean e() {
        return false;
    }

    public DefaultStorageHandler(String str) {
        super(str, "_storage");
    }

    @Override // com.huawei.haf.common.dfx.storage.StorageCallback
    public void checkStorageInfo(boolean z) {
        if (z || e()) {
            ThreadPoolManager.d().execute(new AsyncRunnable(true));
        }
    }

    @Override // com.huawei.haf.common.dfx.storage.StorageCallback
    public void dumpStorageInfo(boolean z) {
        if (z || !f2092a) {
            f2092a = true;
            ThreadPoolManager.d().execute(new AsyncRunnable(false));
        }
    }

    public static long b(File file, Map<String, Long> map, PrintStream printStream) throws IOException {
        if (printStream == null && map.isEmpty()) {
            return e(file, (FileVisitUtils.VisitOperation) null);
        }
        return e(file, new CompoundDumpOperation(file, printStream, map));
    }

    public static long e(File file, FileVisitUtils.VisitOperation visitOperation) throws IOException {
        if (visitOperation == null) {
            return FileUtils.j(file);
        }
        visitOperation.visitRootBegin();
        long d = FileVisitUtils.d(visitOperation);
        visitOperation.visitRootEnd(d);
        return d;
    }

    protected Map<String, Long> b() {
        return Collections.emptyMap();
    }

    protected Map<String, Long> d() {
        return Collections.emptyMap();
    }

    public void b(String str, long j, long j2, Map<String, Long> map, Map<String, Long> map2) {
        LogUtil.c(this.mTag, "internal_total_size=", Long.valueOf(j), ", external_total_size=", Long.valueOf(j2));
    }

    protected void c() {
        LogUtil.c(this.mTag, "preAutoCleanStorage");
    }

    protected void e(Map<String, Long> map, Map<String, Long> map2) {
        LogUtil.c(this.mTag, "postAutoCleanStorage");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z, boolean z2, boolean z3) {
        PrintStream printStream = null;
        try {
            if (z) {
                try {
                    File saveFile = getSaveFile();
                    if (saveFile != null) {
                        printStream = new PrintStream(saveFile);
                    }
                } catch (IOException e) {
                    LogUtil.a(this.mTag, "dumpAllStorageInfo fail, ex=", LogUtil.a(e));
                }
            }
            c(printStream, z2, z3);
        } finally {
            FileUtils.d(printStream);
        }
    }

    private void c(PrintStream printStream, boolean z, boolean z2) throws IOException {
        long j;
        Map<String, Long> b = z ? Collections.EMPTY_MAP : b();
        Map<String, Long> d = z ? Collections.EMPTY_MAP : d();
        if (z2) {
            c();
        }
        String a2 = a(System.currentTimeMillis());
        if (printStream != null) {
            printStream.print(a2);
        }
        long b2 = b(AppInfoUtils.b(null), b, printStream);
        File c = AppInfoUtils.c(null);
        if (c != null) {
            if (printStream != null) {
                printStream.println("--------------------------------");
            }
            j = b(c, d, printStream);
        } else {
            j = 0;
        }
        b(a2, b2, j, b, d);
        if (z2) {
            e(b, d);
        }
    }

    private String a(long j) {
        StringBuilder sb = new StringBuilder(512);
        sb.append("AppFirstInstallTime:");
        sb.append(DfxUtils.a(AppInfoUtils.e()));
        sb.append(", AppLastUpdateTime:");
        sb.append(DfxUtils.a(AppInfoUtils.d()));
        String sb2 = sb.toString();
        sb.delete(0, sb.length());
        fillSaveBaseInfo(sb, null, null, j, sb2);
        return sb.toString();
    }

    class AsyncRunnable implements Runnable {
        private final boolean b;

        AsyncRunnable(boolean z) {
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            DefaultStorageHandler defaultStorageHandler = DefaultStorageHandler.this;
            boolean z = this.b;
            defaultStorageHandler.e(true, !z, z);
        }
    }

    static class CompoundDumpOperation implements FileVisitUtils.VisitOperation {

        /* renamed from: a, reason: collision with root package name */
        private final FileVisitUtils.VisitOperation f2093a;
        private final FileVisitUtils.VisitOperation d;

        CompoundDumpOperation(File file, PrintStream printStream, Map<String, Long> map) {
            this.d = new FileVisitUtils.PrintVisitOperation(file, printStream);
            this.f2093a = new FileVisitUtils.MapVisitOperation(file, map);
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public Path getRootPath() {
            return this.d.getRootPath();
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public void visitRootBegin() {
            this.d.visitRootBegin();
            this.f2093a.visitRootBegin();
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public void visit(Path path, BasicFileAttributes basicFileAttributes, boolean z, int i, long j) {
            this.d.visit(path, basicFileAttributes, z, i, j);
            this.f2093a.visit(path, basicFileAttributes, z, i, j);
        }

        @Override // com.huawei.haf.common.os.FileVisitUtils.VisitOperation
        public void visitRootEnd(long j) {
            this.d.visitRootEnd(j);
            this.f2093a.visitRootEnd(j);
        }
    }
}
