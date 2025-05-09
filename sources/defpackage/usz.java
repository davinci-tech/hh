package defpackage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AbstractModifyFileTask;
import net.lingala.zip4j.tasks.AbstractZipTaskParameters;
import net.lingala.zip4j.tasks.AsyncZipTask;

/* loaded from: classes7.dex */
public class usz extends AbstractModifyFileTask<e> {

    /* renamed from: a, reason: collision with root package name */
    private final usu f17537a;
    private final urr b;

    public usz(usu usuVar, urr urrVar, AsyncZipTask.c cVar) {
        super(cVar);
        this.f17537a = usuVar;
        this.b = urrVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void executeTask(e eVar, ProgressMonitor progressMonitor) throws IOException {
        List<usm> list;
        if (this.f17537a.g()) {
            throw new ZipException("This is a split archive. Zip file format does not allow updating split/spanned files");
        }
        List<String> b = b(eVar.b);
        if (b.isEmpty()) {
            return;
        }
        File temporaryFile = getTemporaryFile(this.f17537a.j().getPath());
        try {
            usa usaVar = new usa(temporaryFile);
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(this.f17537a.j(), RandomAccessFileMode.READ.getValue());
                try {
                    List<usm> cloneAndSortFileHeadersByOffset = cloneAndSortFileHeadersByOffset(this.f17537a.e().d());
                    long j = 0;
                    for (usm usmVar : cloneAndSortFileHeadersByOffset) {
                        long offsetOfNextEntry = getOffsetOfNextEntry(cloneAndSortFileHeadersByOffset, usmVar, this.f17537a) - usaVar.getFilePointer();
                        if (a(usmVar, b)) {
                            a(cloneAndSortFileHeadersByOffset, usmVar, offsetOfNextEntry);
                            if (!this.f17537a.e().d().remove(usmVar)) {
                                throw new ZipException("Could not remove entry from list of central directory headers");
                            }
                            j += offsetOfNextEntry;
                            list = cloneAndSortFileHeadersByOffset;
                        } else {
                            list = cloneAndSortFileHeadersByOffset;
                            j += super.copyFile(randomAccessFile, usaVar, j, offsetOfNextEntry, progressMonitor, eVar.zip4jConfig.a());
                        }
                        verifyIfTaskIsCancelled();
                        cloneAndSortFileHeadersByOffset = list;
                    }
                    this.b.a(this.f17537a, usaVar, eVar.zip4jConfig.b());
                    randomAccessFile.close();
                    usaVar.close();
                    cleanupFile(true, this.f17537a.j(), temporaryFile);
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            cleanupFile(false, this.f17537a.j(), temporaryFile);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public long calculateTotalWork(e eVar) {
        return this.f17537a.j().length();
    }

    private List<String> b(List<String> list) throws ZipException {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (urq.e(this.f17537a, str) != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private boolean a(usm usmVar, List<String> list) {
        for (String str : list) {
            if ((str.endsWith("/") && usmVar.getFileName().startsWith(str)) || usmVar.getFileName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void a(List<usm> list, usm usmVar, long j) throws ZipException {
        updateOffsetsForAllSubsequentFileHeaders(list, this.f17537a, usmVar, d(j));
        usk d = this.f17537a.d();
        d.c(d.d() - j);
        d.c(d.f() - 1);
        if (d.j() > 0) {
            d.a(d.j() - 1);
        }
        if (this.f17537a.h()) {
            this.f17537a.f().b(this.f17537a.f().b() - j);
            this.f17537a.f().c(this.f17537a.f().i() - 1);
            this.f17537a.b().b(this.f17537a.b().c() - j);
        }
    }

    private long d(long j) {
        if (j != Long.MIN_VALUE) {
            return -j;
        }
        throw new ArithmeticException("long overflow");
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.REMOVE_ENTRY;
    }

    public static class e extends AbstractZipTaskParameters {
        private final List<String> b;

        public e(List<String> list, usn usnVar) {
            super(usnVar);
            this.b = list;
        }
    }
}
