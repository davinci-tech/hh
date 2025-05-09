package defpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import okio.Okio;
import okio.Sink;
import okio.Source;

/* loaded from: classes5.dex */
public class lqw {
    public static final lqw b = new lqw();

    private lqw() {
    }

    public static lqw a() {
        return b;
    }

    public void c(File file, File file2) throws IOException {
        a(file2);
        if (file.renameTo(file2)) {
            return;
        }
        throw new IOException("failed to rename " + file + " to " + file2);
    }

    public Source b(File file) throws FileNotFoundException {
        return Okio.source(file);
    }

    public Sink d(File file) throws FileNotFoundException {
        try {
            return Okio.sink(file);
        } catch (FileNotFoundException unused) {
            file.getParentFile().mkdirs();
            return Okio.sink(file);
        }
    }

    public void a(File file) throws IOException {
        if (file.delete() || !file.exists()) {
            return;
        }
        throw new IOException("failed to delete " + file);
    }

    public void e(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                e(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete " + file2);
            }
        }
    }
}
