package defpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

/* loaded from: classes7.dex */
public class usx {
    public static long c(File file, ProgressMonitor progressMonitor) throws IOException {
        if (file == null || !file.exists() || !file.canRead()) {
            throw new ZipException("input file is null or does not exist or cannot read. Cannot calculate CRC for the file");
        }
        byte[] bArr = new byte[16384];
        CRC32 crc32 = new CRC32();
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            try {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    crc32.update(bArr, 0, read);
                    if (progressMonitor != null) {
                        progressMonitor.e(read);
                        if (progressMonitor.d()) {
                            progressMonitor.b(ProgressMonitor.Result.CANCELLED);
                            progressMonitor.b(ProgressMonitor.State.READY);
                            fileInputStream.close();
                            return 0L;
                        }
                    }
                } else {
                    long value = crc32.getValue();
                    fileInputStream.close();
                    return value;
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }
}
