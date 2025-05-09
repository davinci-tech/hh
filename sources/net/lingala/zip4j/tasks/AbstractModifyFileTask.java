package net.lingala.zip4j.tasks;

import defpackage.urq;
import defpackage.usm;
import defpackage.usu;
import defpackage.uta;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;

/* loaded from: classes7.dex */
public abstract class AbstractModifyFileTask<T> extends AsyncZipTask<T> {
    public AbstractModifyFileTask(AsyncZipTask.c cVar) {
        super(cVar);
    }

    protected File getTemporaryFile(String str) {
        SecureRandom secureRandom = new SecureRandom();
        File file = new File(str + secureRandom.nextInt(10000));
        while (file.exists()) {
            file = new File(str + secureRandom.nextInt(10000));
        }
        return file;
    }

    protected void updateOffsetsForAllSubsequentFileHeaders(List<usm> list, usu usuVar, usm usmVar, long j) throws ZipException {
        int indexOfFileHeader = getIndexOfFileHeader(list, usmVar);
        if (indexOfFileHeader == -1) {
            throw new ZipException("Could not locate modified file header in zipModel");
        }
        while (true) {
            indexOfFileHeader++;
            if (indexOfFileHeader >= list.size()) {
                return;
            }
            usm usmVar2 = list.get(indexOfFileHeader);
            usmVar2.c(usmVar2.e() + j);
            if (usuVar.h() && usmVar2.getZip64ExtendedInfo() != null && usmVar2.getZip64ExtendedInfo().e() != -1) {
                usmVar2.getZip64ExtendedInfo().a(usmVar2.getZip64ExtendedInfo().e() + j);
            }
        }
    }

    protected void cleanupFile(boolean z, File file, File file2) throws ZipException {
        if (z) {
            restoreFileName(file, file2);
        } else if (!file2.delete()) {
            throw new ZipException("Could not delete temporary file");
        }
    }

    public long copyFile(RandomAccessFile randomAccessFile, OutputStream outputStream, long j, long j2, ProgressMonitor progressMonitor, int i) throws IOException {
        uta.b(randomAccessFile, outputStream, j, j + j2, progressMonitor, i);
        return j2;
    }

    protected List<usm> cloneAndSortFileHeadersByOffset(List<usm> list) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, new Comparator<usm>() { // from class: net.lingala.zip4j.tasks.AbstractModifyFileTask.1
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(usm usmVar, usm usmVar2) {
                if (usmVar.getFileName().equals(usmVar2.getFileName())) {
                    return 0;
                }
                return usmVar.e() < usmVar2.e() ? -1 : 1;
            }
        });
        return arrayList;
    }

    protected long getOffsetOfNextEntry(List<usm> list, usm usmVar, usu usuVar) throws ZipException {
        int indexOfFileHeader = getIndexOfFileHeader(list, usmVar);
        if (indexOfFileHeader == list.size() - 1) {
            return urq.a(usuVar);
        }
        return list.get(indexOfFileHeader + 1).e();
    }

    private void restoreFileName(File file, File file2) throws ZipException {
        if (file.delete()) {
            if (!file2.renameTo(file)) {
                throw new ZipException("cannot rename modified zip file");
            }
            return;
        }
        throw new ZipException("cannot delete old zip file");
    }

    private int getIndexOfFileHeader(List<usm> list, usm usmVar) throws ZipException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(usmVar)) {
                return i;
            }
        }
        throw new ZipException("Could not find file header in list of central directory file headers");
    }
}
