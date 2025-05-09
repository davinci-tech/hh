package defpackage;

import defpackage.usv;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;

/* loaded from: classes7.dex */
public class ura implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    private int f17511a;
    private Charset b;
    private urr c;
    private List<InputStream> d;
    private ExecutorService e;
    private ProgressMonitor f;
    private char[] g;
    private boolean h;
    private boolean i;
    private ThreadFactory j;
    private usu n;
    private File o;

    public ura(File file) {
        this(file, null);
    }

    public ura(File file, char[] cArr) {
        this.c = new urr();
        this.b = null;
        this.f17511a = 4096;
        this.d = new ArrayList();
        this.i = true;
        if (file == null) {
            throw new IllegalArgumentException("input zip file parameter is null");
        }
        this.o = file;
        this.g = cArr;
        this.h = false;
        this.f = new ProgressMonitor();
    }

    public void d(File file, ZipParameters zipParameters, boolean z, long j) throws ZipException {
        if (file == null) {
            throw new ZipException("folderToAdd is null, cannot create zip file from folder");
        }
        if (zipParameters == null) {
            throw new ZipException("input parameters are null, cannot create zip file from folder");
        }
        if (this.o.exists()) {
            throw new ZipException("zip file: " + this.o + " already exists. To add files to existing zip file use addFolder method");
        }
        b();
        this.n.c(z);
        if (z) {
            this.n.d(j);
        }
        a(file, zipParameters, false);
    }

    private void a(File file, ZipParameters zipParameters, boolean z) throws ZipException {
        a();
        usu usuVar = this.n;
        if (usuVar == null) {
            throw new ZipException("internal error: zip model is null");
        }
        if (z && usuVar.g()) {
            throw new ZipException("This is a split archive. Zip file format does not allow updating split/spanned files");
        }
        new usv(this.n, this.g, this.c, d()).execute(new usv.c(file, zipParameters, c()));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Iterator<InputStream> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().close();
        }
        this.d.clear();
    }

    private void a() throws ZipException {
        if (this.n != null) {
            return;
        }
        if (!this.o.exists()) {
            b();
            return;
        }
        if (!this.o.canRead()) {
            throw new ZipException("no read access for the input zip file");
        }
        try {
            RandomAccessFile e = e();
            try {
                usu c = new url().c(e, c());
                this.n = c;
                c.c(this.o);
                if (e != null) {
                    e.close();
                }
            } finally {
            }
        } catch (ZipException e2) {
            throw e2;
        } catch (IOException e3) {
            throw new ZipException(e3);
        }
    }

    private void b() {
        usu usuVar = new usu();
        this.n = usuVar;
        usuVar.c(this.o);
    }

    private RandomAccessFile e() throws IOException {
        if (uta.a(this.o)) {
            urw urwVar = new urw(this.o, RandomAccessFileMode.READ.getValue(), uta.e(this.o));
            urwVar.c();
            return urwVar;
        }
        return new RandomAccessFile(this.o, RandomAccessFileMode.READ.getValue());
    }

    private AsyncZipTask.c d() {
        if (this.h) {
            if (this.j == null) {
                this.j = Executors.defaultThreadFactory();
            }
            this.e = Executors.newSingleThreadExecutor(this.j);
        }
        return new AsyncZipTask.c(this.e, this.h, this.f);
    }

    public String toString() {
        return this.o.toString();
    }

    private usn c() {
        return new usn(this.b, this.f17511a, this.i);
    }
}
