package net.lingala.zip4j.tasks;

import defpackage.uru;
import defpackage.usm;
import defpackage.uso;
import defpackage.usq;
import defpackage.usu;
import defpackage.usw;
import defpackage.usy;
import defpackage.utb;
import defpackage.utd;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.regex.Matcher;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;

/* loaded from: classes10.dex */
public abstract class AbstractExtractFileTask<T> extends AsyncZipTask<T> {
    private final uso unzipParameters;
    private final usu zipModel;

    public AbstractExtractFileTask(usu usuVar, uso usoVar, AsyncZipTask.c cVar) {
        super(cVar);
        this.zipModel = usuVar;
        this.unzipParameters = usoVar;
    }

    protected void extractFile(uru uruVar, usm usmVar, String str, String str2, ProgressMonitor progressMonitor, byte[] bArr) throws IOException {
        boolean isSymbolicLink = isSymbolicLink(usmVar);
        if (!isSymbolicLink || this.unzipParameters.e()) {
            if (!str.endsWith(usw.b)) {
                str = str + usw.b;
            }
            File determineOutputFile = determineOutputFile(usmVar, str, str2);
            progressMonitor.a(determineOutputFile.getAbsolutePath());
            assertCanonicalPathsAreSame(determineOutputFile, str, usmVar);
            verifyNextEntry(uruVar, usmVar);
            if (usmVar.isDirectory()) {
                if (!determineOutputFile.exists() && !determineOutputFile.mkdirs()) {
                    throw new ZipException("Could not create directory: " + determineOutputFile);
                }
            } else if (isSymbolicLink) {
                createSymLink(uruVar, usmVar, determineOutputFile, progressMonitor);
            } else {
                checkOutputDirectoryStructure(determineOutputFile);
                unzipFile(uruVar, determineOutputFile, progressMonitor, bArr);
            }
            if (isSymbolicLink) {
                return;
            }
            utb.c(usmVar, determineOutputFile);
        }
    }

    private void assertCanonicalPathsAreSame(File file, String str, usm usmVar) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        if (file.isDirectory() && !canonicalPath.endsWith(usw.b)) {
            canonicalPath = canonicalPath + usw.b;
        }
        String canonicalPath2 = new File(str).getCanonicalPath();
        if (!canonicalPath2.endsWith(usw.b)) {
            canonicalPath2 = canonicalPath2 + usw.b;
        }
        if (canonicalPath.startsWith(canonicalPath2)) {
            return;
        }
        throw new ZipException("illegal file name that breaks out of the target directory: " + usmVar.getFileName());
    }

    private boolean isSymbolicLink(usm usmVar) {
        byte[] a2 = usmVar.a();
        if (a2 == null || a2.length < 4) {
            return false;
        }
        return usy.c(a2[3], 5);
    }

    private void unzipFile(uru uruVar, File file, ProgressMonitor progressMonitor, byte[] bArr) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while (true) {
                try {
                    int read = uruVar.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                        progressMonitor.e(read);
                        verifyIfTaskIsCancelled();
                    } else {
                        fileOutputStream.close();
                        return;
                    }
                } finally {
                }
            }
        } catch (Exception e) {
            if (file.exists()) {
                file.delete();
            }
            throw e;
        }
    }

    private void createSymLink(uru uruVar, usm usmVar, File file, ProgressMonitor progressMonitor) throws IOException {
        String str = new String(readCompleteEntry(uruVar, usmVar, progressMonitor));
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new ZipException("Could not create parent directories");
        }
        try {
            Path path = Paths.get(str, new String[0]);
            if (file.exists() && !file.delete()) {
                throw new ZipException("Could not delete existing symlink " + file);
            }
            Files.createSymbolicLink(file.toPath(), path, new FileAttribute[0]);
        } catch (NoSuchMethodError unused) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(str.getBytes());
                fileOutputStream.close();
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    private byte[] readCompleteEntry(uru uruVar, usm usmVar, ProgressMonitor progressMonitor) throws IOException {
        int uncompressedSize = (int) usmVar.getUncompressedSize();
        byte[] bArr = new byte[uncompressedSize];
        if (uruVar.read(bArr) != uncompressedSize) {
            throw new ZipException("Could not read complete entry");
        }
        progressMonitor.e(uncompressedSize);
        return bArr;
    }

    private void verifyNextEntry(uru uruVar, usm usmVar) throws IOException {
        if (usy.c(usmVar.getGeneralPurposeFlag()[0], 6)) {
            throw new ZipException("Entry with name " + usmVar.getFileName() + " is encrypted with Strong Encryption. Zip4j does not support Strong Encryption, as this is patented.");
        }
        usq a2 = uruVar.a(usmVar, false);
        if (a2 == null) {
            throw new ZipException("Could not read corresponding local file header for file header: " + usmVar.getFileName());
        }
        if (!usmVar.getFileName().equals(a2.getFileName())) {
            throw new ZipException("File header and local file header mismatch");
        }
    }

    private void checkOutputDirectoryStructure(File file) throws ZipException {
        if (file.getParentFile().exists() || file.getParentFile().mkdirs()) {
            return;
        }
        throw new ZipException("Unable to create parent directories: " + file.getParentFile());
    }

    private File determineOutputFile(usm usmVar, String str, String str2) {
        String fileName = usmVar.getFileName();
        if (!utd.b(str2)) {
            str2 = fileName;
        }
        return new File(str, getFileNameWithSystemFileSeparators(str2));
    }

    private String getFileNameWithSystemFileSeparators(String str) {
        return str.replaceAll(":\\\\", "_").replaceAll("[/\\\\]", Matcher.quoteReplacement(usw.b));
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.EXTRACT_ENTRY;
    }

    public usu getZipModel() {
        return this.zipModel;
    }
}
