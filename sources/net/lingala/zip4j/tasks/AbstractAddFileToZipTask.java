package net.lingala.zip4j.tasks;

import defpackage.urq;
import defpackage.urr;
import defpackage.usa;
import defpackage.usd;
import defpackage.usm;
import defpackage.usn;
import defpackage.usu;
import defpackage.usx;
import defpackage.usy;
import defpackage.usz;
import defpackage.uta;
import defpackage.utd;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;

/* loaded from: classes7.dex */
public abstract class AbstractAddFileToZipTask<T> extends AsyncZipTask<T> {
    private final urr headerWriter;
    private final char[] password;
    private final usu zipModel;

    public AbstractAddFileToZipTask(usu usuVar, char[] cArr, urr urrVar, AsyncZipTask.c cVar) {
        super(cVar);
        this.zipModel = usuVar;
        this.password = cArr;
        this.headerWriter = urrVar;
    }

    protected void addFilesToZip(List<File> list, ProgressMonitor progressMonitor, ZipParameters zipParameters, usn usnVar) throws IOException {
        uta.a(list, zipParameters.m());
        byte[] bArr = new byte[usnVar.a()];
        List<File> removeFilesIfExists = removeFilesIfExists(list, zipParameters, progressMonitor, usnVar);
        usa usaVar = new usa(this.zipModel.j(), this.zipModel.a());
        try {
            usd initializeOutputStream = initializeOutputStream(usaVar, usnVar);
            try {
                for (File file : removeFilesIfExists) {
                    verifyIfTaskIsCancelled();
                    ZipParameters cloneAndAdjustZipParameters = cloneAndAdjustZipParameters(zipParameters, file, progressMonitor);
                    progressMonitor.a(file.getAbsolutePath());
                    if (uta.b(file) && addSymlink(cloneAndAdjustZipParameters)) {
                        addSymlinkToZip(file, initializeOutputStream, cloneAndAdjustZipParameters, usaVar);
                        if (ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(cloneAndAdjustZipParameters.m())) {
                        }
                    }
                    addFileToZip(file, initializeOutputStream, cloneAndAdjustZipParameters, usaVar, progressMonitor, bArr);
                }
                if (initializeOutputStream != null) {
                    initializeOutputStream.close();
                }
                usaVar.close();
            } finally {
            }
        } catch (Throwable th) {
            try {
                usaVar.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void addSymlinkToZip(File file, usd usdVar, ZipParameters zipParameters, usa usaVar) throws IOException {
        ZipParameters zipParameters2 = new ZipParameters(zipParameters);
        zipParameters2.e(replaceFileNameInZip(zipParameters.o(), file.getName()));
        zipParameters2.b(false);
        zipParameters2.e(CompressionMethod.STORE);
        usdVar.a(zipParameters2);
        usdVar.write(uta.g(file).getBytes());
        closeEntry(usdVar, usaVar, file, true);
    }

    private void addFileToZip(File file, usd usdVar, ZipParameters zipParameters, usa usaVar, ProgressMonitor progressMonitor, byte[] bArr) throws IOException {
        usdVar.a(zipParameters);
        if (file.exists() && !file.isDirectory()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                try {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    usdVar.write(bArr, 0, read);
                    progressMonitor.e(read);
                    verifyIfTaskIsCancelled();
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            fileInputStream.close();
        }
        closeEntry(usdVar, usaVar, file, false);
    }

    private void closeEntry(usd usdVar, usa usaVar, File file, boolean z) throws IOException {
        usm d = usdVar.d();
        byte[] d2 = uta.d(file);
        if (!z) {
            d2[3] = usy.e(d2[3], 5);
        }
        d.b(d2);
        updateLocalFileHeader(d, usaVar);
    }

    protected long calculateWorkForFiles(List<File> list, ZipParameters zipParameters) throws ZipException {
        long length;
        long j = 0;
        for (File file : list) {
            if (file.exists()) {
                if (zipParameters.l() && zipParameters.h() == EncryptionMethod.ZIP_STANDARD) {
                    length = file.length() * 2;
                } else {
                    length = file.length();
                }
                j += length;
                usm e = urq.e(getZipModel(), uta.e(file, zipParameters));
                if (e != null) {
                    j += getZipModel().j().length() - e.getCompressedSize();
                }
            }
        }
        return j;
    }

    usd initializeOutputStream(usa usaVar, usn usnVar) throws IOException {
        if (this.zipModel.j().exists()) {
            usaVar.e(urq.a(this.zipModel));
        }
        return new usd(usaVar, this.password, usnVar, this.zipModel);
    }

    void verifyZipParameters(ZipParameters zipParameters) throws ZipException {
        if (zipParameters == null) {
            throw new ZipException("cannot validate zip parameters");
        }
        if (zipParameters.a() != CompressionMethod.STORE && zipParameters.a() != CompressionMethod.DEFLATE) {
            throw new ZipException("unsupported compression type");
        }
        if (zipParameters.l()) {
            if (zipParameters.h() == EncryptionMethod.NONE) {
                throw new ZipException("Encryption method has to be set, when encrypt files flag is set");
            }
            char[] cArr = this.password;
            if (cArr == null || cArr.length <= 0) {
                throw new ZipException("input password is empty or null");
            }
            return;
        }
        zipParameters.d(EncryptionMethod.NONE);
    }

    void updateLocalFileHeader(usm usmVar, usa usaVar) throws IOException {
        this.headerWriter.a(usmVar, getZipModel(), usaVar);
    }

    private ZipParameters cloneAndAdjustZipParameters(ZipParameters zipParameters, File file, ProgressMonitor progressMonitor) throws IOException {
        ZipParameters zipParameters2 = new ZipParameters(zipParameters);
        if (file.isDirectory()) {
            zipParameters2.c(0L);
        } else {
            zipParameters2.c(file.length());
        }
        if (zipParameters.n() <= 0) {
            zipParameters2.e(file.lastModified());
        }
        zipParameters2.c(false);
        if (!utd.b(zipParameters.o())) {
            zipParameters2.e(uta.e(file, zipParameters));
        }
        if (file.isDirectory()) {
            zipParameters2.e(CompressionMethod.STORE);
            zipParameters2.d(EncryptionMethod.NONE);
            zipParameters2.b(false);
        } else {
            if (zipParameters2.l() && zipParameters2.h() == EncryptionMethod.ZIP_STANDARD) {
                progressMonitor.c(ProgressMonitor.Task.CALCULATE_CRC);
                zipParameters2.b(usx.c(file, progressMonitor));
                progressMonitor.c(ProgressMonitor.Task.ADD_ENTRY);
            }
            if (file.length() == 0) {
                zipParameters2.e(CompressionMethod.STORE);
            }
        }
        return zipParameters2;
    }

    private List<File> removeFilesIfExists(List<File> list, ZipParameters zipParameters, ProgressMonitor progressMonitor, usn usnVar) throws ZipException {
        ArrayList arrayList = new ArrayList(list);
        if (!this.zipModel.j().exists()) {
            return arrayList;
        }
        for (File file : list) {
            if (!utd.b(file.getName())) {
                arrayList.remove(file);
            }
            usm e = urq.e(this.zipModel, uta.e(file, zipParameters));
            if (e != null) {
                if (zipParameters.s()) {
                    progressMonitor.c(ProgressMonitor.Task.REMOVE_ENTRY);
                    removeFile(e, progressMonitor, usnVar);
                    verifyIfTaskIsCancelled();
                    progressMonitor.c(ProgressMonitor.Task.ADD_ENTRY);
                } else {
                    arrayList.remove(file);
                }
            }
        }
        return arrayList;
    }

    void removeFile(usm usmVar, ProgressMonitor progressMonitor, usn usnVar) throws ZipException {
        new usz(this.zipModel, this.headerWriter, new AsyncZipTask.c(null, false, progressMonitor)).execute(new usz.e(Collections.singletonList(usmVar.getFileName()), usnVar));
    }

    private String replaceFileNameInZip(String str, String str2) {
        if (!str.contains("/")) {
            return str2;
        }
        return str.substring(0, str.lastIndexOf("/") + 1) + str2;
    }

    private boolean addSymlink(ZipParameters zipParameters) {
        return ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(zipParameters.m()) || ZipParameters.SymbolicLinkAction.INCLUDE_LINK_AND_LINKED_FILE.equals(zipParameters.m());
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.ADD_ENTRY;
    }

    protected usu getZipModel() {
        return this.zipModel;
    }
}
