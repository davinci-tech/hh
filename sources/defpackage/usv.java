package defpackage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AbstractAddFileToZipTask;
import net.lingala.zip4j.tasks.AbstractZipTaskParameters;
import net.lingala.zip4j.tasks.AsyncZipTask;

/* loaded from: classes7.dex */
public class usv extends AbstractAddFileToZipTask<c> {
    public usv(usu usuVar, char[] cArr, urr urrVar, AsyncZipTask.c cVar) {
        super(usuVar, cArr, urrVar, cVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void executeTask(c cVar, ProgressMonitor progressMonitor) throws IOException {
        List<File> d = d(cVar);
        a(cVar);
        addFilesToZip(d, progressMonitor, cVar.d, cVar.zip4jConfig);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public long calculateTotalWork(c cVar) throws ZipException {
        List<File> d = d(cVar);
        if (cVar.d.q()) {
            d.add(cVar.b);
        }
        return calculateWorkForFiles(d, cVar.d);
    }

    private void a(c cVar) throws IOException {
        String canonicalPath;
        File file = cVar.b;
        if (cVar.d.q()) {
            if (file.getCanonicalFile().getParentFile() == null) {
                canonicalPath = file.getCanonicalPath();
            } else {
                canonicalPath = file.getCanonicalFile().getParentFile().getCanonicalPath();
            }
        } else {
            canonicalPath = file.getCanonicalPath();
        }
        cVar.d.a(canonicalPath);
    }

    private List<File> d(c cVar) throws ZipException {
        List<File> c2 = uta.c(cVar.b, cVar.d);
        if (cVar.d.q()) {
            c2.add(cVar.b);
        }
        return c2;
    }

    public static class c extends AbstractZipTaskParameters {
        private final File b;
        private final ZipParameters d;

        public c(File file, ZipParameters zipParameters, usn usnVar) {
            super(usnVar);
            this.b = file;
            this.d = zipParameters;
        }
    }
}
