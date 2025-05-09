package defpackage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport;

/* loaded from: classes7.dex */
public class urr {

    /* renamed from: a, reason: collision with root package name */
    private final utf f17519a = new utf();
    private final byte[] c = new byte[8];
    private final byte[] e = new byte[4];

    /* JADX WARN: Removed duplicated region for block: B:10:0x006e A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:10:0x006e, B:11:0x00a9, B:13:0x00b5, B:14:0x00bd, B:17:0x00c9, B:19:0x00cf, B:20:0x00d1, B:22:0x00d9, B:24:0x00de, B:25:0x0103, B:27:0x0109, B:28:0x0159, B:34:0x0086), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00b5 A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:10:0x006e, B:11:0x00a9, B:13:0x00b5, B:14:0x00bd, B:17:0x00c9, B:19:0x00cf, B:20:0x00d1, B:22:0x00d9, B:24:0x00de, B:25:0x0103, B:27:0x0109, B:28:0x0159, B:34:0x0086), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cf A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:10:0x006e, B:11:0x00a9, B:13:0x00b5, B:14:0x00bd, B:17:0x00c9, B:19:0x00cf, B:20:0x00d1, B:22:0x00d9, B:24:0x00de, B:25:0x0103, B:27:0x0109, B:28:0x0159, B:34:0x0086), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d9 A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:10:0x006e, B:11:0x00a9, B:13:0x00b5, B:14:0x00bd, B:17:0x00c9, B:19:0x00cf, B:20:0x00d1, B:22:0x00d9, B:24:0x00de, B:25:0x0103, B:27:0x0109, B:28:0x0159, B:34:0x0086), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00de A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:10:0x006e, B:11:0x00a9, B:13:0x00b5, B:14:0x00bd, B:17:0x00c9, B:19:0x00cf, B:20:0x00d1, B:22:0x00d9, B:24:0x00de, B:25:0x0103, B:27:0x0109, B:28:0x0159, B:34:0x0086), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0109 A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:10:0x006e, B:11:0x00a9, B:13:0x00b5, B:14:0x00bd, B:17:0x00c9, B:19:0x00cf, B:20:0x00d1, B:22:0x00d9, B:24:0x00de, B:25:0x0103, B:27:0x0109, B:28:0x0159, B:34:0x0086), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0086 A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0005, B:5:0x0060, B:10:0x006e, B:11:0x00a9, B:13:0x00b5, B:14:0x00bd, B:17:0x00c9, B:19:0x00cf, B:20:0x00d1, B:22:0x00d9, B:24:0x00de, B:25:0x0103, B:27:0x0109, B:28:0x0159, B:34:0x0086), top: B:2:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(defpackage.usu r11, defpackage.usq r12, java.io.OutputStream r13, java.nio.charset.Charset r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 366
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.urr.b(usu, usq, java.io.OutputStream, java.nio.charset.Charset):void");
    }

    public void c(usq usqVar, OutputStream outputStream) throws IOException {
        if (usqVar == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot write extended local header");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.f17519a.d((OutputStream) byteArrayOutputStream, (int) HeaderSignature.EXTRA_DATA_RECORD.getValue());
            this.f17519a.e(this.c, 0, usqVar.getCrc());
            byteArrayOutputStream.write(this.c, 0, 4);
            if (usqVar.e()) {
                this.f17519a.d(byteArrayOutputStream, usqVar.getCompressedSize());
                this.f17519a.d(byteArrayOutputStream, usqVar.getUncompressedSize());
            } else {
                this.f17519a.e(this.c, 0, usqVar.getCompressedSize());
                byteArrayOutputStream.write(this.c, 0, 4);
                this.f17519a.e(this.c, 0, usqVar.getUncompressedSize());
                byteArrayOutputStream.write(this.c, 0, 4);
            }
            outputStream.write(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void a(usu usuVar, OutputStream outputStream, Charset charset) throws IOException {
        if (usuVar == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot finalize zip file");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            e(usuVar, outputStream);
            long a2 = a(usuVar);
            b(usuVar, byteArrayOutputStream, this.f17519a, charset);
            int size = byteArrayOutputStream.size();
            if (usuVar.h() || a2 >= 4294967295L || usuVar.e().d().size() >= 65535) {
                if (usuVar.f() == null) {
                    usuVar.c(new ust());
                }
                if (usuVar.b() == null) {
                    usuVar.e(new usr());
                }
                usuVar.b().b(size + a2);
                if (b(outputStream)) {
                    int a3 = a(outputStream);
                    usuVar.b().c(a3);
                    usuVar.b().b(a3 + 1);
                } else {
                    usuVar.b().c(0);
                    usuVar.b().b(1);
                }
                ust c = c(usuVar, size, a2);
                usuVar.c(c);
                e(c, byteArrayOutputStream, this.f17519a);
                d(usuVar.b(), byteArrayOutputStream, this.f17519a);
            }
            c(usuVar, size, a2, byteArrayOutputStream, this.f17519a, charset);
            c(usuVar, outputStream, byteArrayOutputStream.toByteArray(), charset);
            byteArrayOutputStream.close();
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void a(usm usmVar, usu usuVar, usa usaVar) throws IOException {
        usa usaVar2;
        boolean z;
        String str;
        String str2;
        if (usmVar == null || usuVar == null) {
            throw new ZipException("invalid input parameters, cannot update local file header");
        }
        if (usmVar.b() != usaVar.getCurrentSplitFileCounter()) {
            String parent = usuVar.j().getParent();
            String e = uta.e(usuVar.j().getName());
            if (parent != null) {
                str = parent + System.getProperty("file.separator");
            } else {
                str = "";
            }
            z = true;
            if (usmVar.b() < 9) {
                str2 = str + e + ".z0" + (usmVar.b() + 1);
            } else {
                str2 = str + e + ".z" + (usmVar.b() + 1);
            }
            usaVar2 = new usa(new File(str2));
        } else {
            usaVar2 = usaVar;
            z = false;
        }
        long filePointer = usaVar2.getFilePointer();
        usaVar2.e(usmVar.e() + 14);
        this.f17519a.e(this.c, 0, usmVar.getCrc());
        usaVar2.write(this.c, 0, 4);
        c(usaVar2, usmVar);
        if (z) {
            usaVar2.close();
        } else {
            usaVar.e(filePointer);
        }
    }

    private void c(usa usaVar, usm usmVar) throws IOException {
        if (usmVar.getUncompressedSize() >= 4294967295L) {
            this.f17519a.e(this.c, 0, 4294967295L);
            usaVar.write(this.c, 0, 4);
            usaVar.write(this.c, 0, 4);
            int fileNameLength = usmVar.getFileNameLength() + 8;
            if (usaVar.b(fileNameLength) != fileNameLength) {
                throw new ZipException("Unable to skip " + fileNameLength + " bytes to update LFH");
            }
            this.f17519a.d(usaVar, usmVar.getUncompressedSize());
            this.f17519a.d(usaVar, usmVar.getCompressedSize());
            return;
        }
        this.f17519a.e(this.c, 0, usmVar.getCompressedSize());
        usaVar.write(this.c, 0, 4);
        this.f17519a.e(this.c, 0, usmVar.getUncompressedSize());
        usaVar.write(this.c, 0, 4);
    }

    private boolean b(OutputStream outputStream) {
        if (outputStream instanceof usa) {
            return ((usa) outputStream).b();
        }
        if (outputStream instanceof urx) {
            return ((urx) outputStream).e();
        }
        return false;
    }

    private int a(OutputStream outputStream) {
        if (outputStream instanceof usa) {
            return ((usa) outputStream).getCurrentSplitFileCounter();
        }
        return ((urx) outputStream).getCurrentSplitFileCounter();
    }

    private void c(usu usuVar, OutputStream outputStream, byte[] bArr, Charset charset) throws IOException {
        if (bArr == null) {
            throw new ZipException("invalid buff to write as zip headers");
        }
        if ((outputStream instanceof urx) && ((urx) outputStream).d(bArr.length)) {
            a(usuVar, outputStream, charset);
        } else {
            outputStream.write(bArr);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void e(usu usuVar, OutputStream outputStream) throws IOException {
        int i;
        if (outputStream instanceof OutputStreamWithSplitZipSupport) {
            OutputStreamWithSplitZipSupport outputStreamWithSplitZipSupport = (OutputStreamWithSplitZipSupport) outputStream;
            usuVar.d().c(outputStreamWithSplitZipSupport.getFilePointer());
            i = outputStreamWithSplitZipSupport.getCurrentSplitFileCounter();
        } else {
            i = 0;
        }
        if (usuVar.h()) {
            if (usuVar.f() == null) {
                usuVar.c(new ust());
            }
            if (usuVar.b() == null) {
                usuVar.e(new usr());
            }
            usuVar.f().b(usuVar.d().d());
            usuVar.b().c(i);
            usuVar.b().b(i + 1);
        }
        usuVar.d().d(i);
        usuVar.d().e(i);
    }

    private void b(usu usuVar, ByteArrayOutputStream byteArrayOutputStream, utf utfVar, Charset charset) throws ZipException {
        if (usuVar.e() == null || usuVar.e().d() == null || usuVar.e().d().size() <= 0) {
            return;
        }
        Iterator<usm> it = usuVar.e().d().iterator();
        while (it.hasNext()) {
            e(usuVar, it.next(), byteArrayOutputStream, utfVar, charset);
        }
    }

    private void e(usu usuVar, usm usmVar, ByteArrayOutputStream byteArrayOutputStream, utf utfVar, Charset charset) throws ZipException {
        byte[] bArr;
        if (usmVar == null) {
            throw new ZipException("input parameters is null, cannot write local file header");
        }
        try {
            byte[] bArr2 = {0, 0};
            boolean b = b(usmVar);
            utfVar.d((OutputStream) byteArrayOutputStream, (int) usmVar.getSignature().getValue());
            utfVar.c(byteArrayOutputStream, usmVar.d());
            utfVar.c(byteArrayOutputStream, usmVar.getVersionNeededToExtract());
            byteArrayOutputStream.write(usmVar.getGeneralPurposeFlag());
            utfVar.c(byteArrayOutputStream, usmVar.getCompressionMethod().getCode());
            utfVar.e(this.c, 0, usmVar.getLastModifiedTime());
            byteArrayOutputStream.write(this.c, 0, 4);
            utfVar.e(this.c, 0, usmVar.getCrc());
            byteArrayOutputStream.write(this.c, 0, 4);
            if (b) {
                utfVar.e(this.c, 0, 4294967295L);
                byteArrayOutputStream.write(this.c, 0, 4);
                byteArrayOutputStream.write(this.c, 0, 4);
                usuVar.d(true);
                bArr = bArr2;
            } else {
                bArr = bArr2;
                utfVar.e(this.c, 0, usmVar.getCompressedSize());
                byteArrayOutputStream.write(this.c, 0, 4);
                utfVar.e(this.c, 0, usmVar.getUncompressedSize());
                byteArrayOutputStream.write(this.c, 0, 4);
            }
            byte[] bArr3 = new byte[0];
            if (utd.b(usmVar.getFileName())) {
                bArr3 = urq.d(usmVar.getFileName(), charset);
            }
            utfVar.c(byteArrayOutputStream, bArr3.length);
            byte[] bArr4 = new byte[4];
            if (b) {
                utfVar.e(this.c, 0, 4294967295L);
                System.arraycopy(this.c, 0, bArr4, 0, 4);
            } else {
                utfVar.e(this.c, 0, usmVar.e());
                System.arraycopy(this.c, 0, bArr4, 0, 4);
            }
            utfVar.c(byteArrayOutputStream, d(usmVar, b));
            String c = usmVar.c();
            byte[] bArr5 = new byte[0];
            if (utd.b(c)) {
                bArr5 = urq.d(c, charset);
            }
            utfVar.c(byteArrayOutputStream, bArr5.length);
            if (b) {
                utfVar.a(this.e, 0, 65535);
                byteArrayOutputStream.write(this.e, 0, 2);
            } else {
                utfVar.c(byteArrayOutputStream, usmVar.b());
            }
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(usmVar.a());
            byteArrayOutputStream.write(bArr4);
            if (bArr3.length > 0) {
                byteArrayOutputStream.write(bArr3);
            }
            if (b) {
                usuVar.d(true);
                utfVar.c(byteArrayOutputStream, (int) HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue());
                utfVar.c(byteArrayOutputStream, 28);
                utfVar.d(byteArrayOutputStream, usmVar.getUncompressedSize());
                utfVar.d(byteArrayOutputStream, usmVar.getCompressedSize());
                utfVar.d(byteArrayOutputStream, usmVar.e());
                utfVar.d((OutputStream) byteArrayOutputStream, usmVar.b());
            }
            if (usmVar.getAesExtraDataRecord() != null) {
                use aesExtraDataRecord = usmVar.getAesExtraDataRecord();
                utfVar.c(byteArrayOutputStream, (int) aesExtraDataRecord.getSignature().getValue());
                utfVar.c(byteArrayOutputStream, aesExtraDataRecord.b());
                utfVar.c(byteArrayOutputStream, aesExtraDataRecord.a().getVersionNumber());
                byteArrayOutputStream.write(urq.d(aesExtraDataRecord.c(), charset));
                byteArrayOutputStream.write(new byte[]{(byte) aesExtraDataRecord.d().getRawCode()});
                utfVar.c(byteArrayOutputStream, aesExtraDataRecord.e().getCode());
            }
            a(usmVar, byteArrayOutputStream);
            if (bArr5.length > 0) {
                byteArrayOutputStream.write(bArr5);
            }
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    private int d(usm usmVar, boolean z) {
        int i = z ? 32 : 0;
        if (usmVar.getAesExtraDataRecord() != null) {
            i += 11;
        }
        if (usmVar.getExtraDataRecords() != null) {
            for (usp uspVar : usmVar.getExtraDataRecords()) {
                if (uspVar.b() != HeaderSignature.AES_EXTRA_DATA_RECORD.getValue() && uspVar.b() != HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue()) {
                    i += uspVar.e() + 4;
                }
            }
        }
        return i;
    }

    private void a(usm usmVar, OutputStream outputStream) throws IOException {
        if (usmVar.getExtraDataRecords() == null || usmVar.getExtraDataRecords().size() == 0) {
            return;
        }
        for (usp uspVar : usmVar.getExtraDataRecords()) {
            if (uspVar.b() != HeaderSignature.AES_EXTRA_DATA_RECORD.getValue() && uspVar.b() != HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue()) {
                this.f17519a.c(outputStream, (int) uspVar.b());
                this.f17519a.c(outputStream, uspVar.e());
                if (uspVar.e() > 0 && uspVar.c() != null) {
                    outputStream.write(uspVar.c());
                }
            }
        }
    }

    private void e(ust ustVar, ByteArrayOutputStream byteArrayOutputStream, utf utfVar) throws IOException {
        utfVar.d((OutputStream) byteArrayOutputStream, (int) ustVar.getSignature().getValue());
        utfVar.d(byteArrayOutputStream, ustVar.d());
        utfVar.c(byteArrayOutputStream, ustVar.h());
        utfVar.c(byteArrayOutputStream, ustVar.g());
        utfVar.d((OutputStream) byteArrayOutputStream, ustVar.e());
        utfVar.d((OutputStream) byteArrayOutputStream, ustVar.c());
        utfVar.d(byteArrayOutputStream, ustVar.j());
        utfVar.d(byteArrayOutputStream, ustVar.i());
        utfVar.d(byteArrayOutputStream, ustVar.a());
        utfVar.d(byteArrayOutputStream, ustVar.b());
    }

    private void d(usr usrVar, ByteArrayOutputStream byteArrayOutputStream, utf utfVar) throws IOException {
        utfVar.d((OutputStream) byteArrayOutputStream, (int) HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR.getValue());
        utfVar.d((OutputStream) byteArrayOutputStream, usrVar.e());
        utfVar.d(byteArrayOutputStream, usrVar.c());
        utfVar.d((OutputStream) byteArrayOutputStream, usrVar.a());
    }

    private void c(usu usuVar, int i, long j, ByteArrayOutputStream byteArrayOutputStream, utf utfVar, Charset charset) throws IOException {
        byte[] bArr = new byte[8];
        utfVar.d((OutputStream) byteArrayOutputStream, (int) HeaderSignature.END_OF_CENTRAL_DIRECTORY.getValue());
        utfVar.c(byteArrayOutputStream, usuVar.d().c());
        utfVar.c(byteArrayOutputStream, usuVar.d().b());
        long size = usuVar.e().d().size();
        long a2 = usuVar.g() ? a(usuVar.e().d(), usuVar.d().c()) : size;
        if (a2 > 65535) {
            a2 = 65535;
        }
        utfVar.c(byteArrayOutputStream, (int) a2);
        if (size > 65535) {
            size = 65535;
        }
        utfVar.c(byteArrayOutputStream, (int) size);
        utfVar.d((OutputStream) byteArrayOutputStream, i);
        if (j > 4294967295L) {
            utfVar.e(bArr, 0, 4294967295L);
            byteArrayOutputStream.write(bArr, 0, 4);
        } else {
            utfVar.e(bArr, 0, j);
            byteArrayOutputStream.write(bArr, 0, 4);
        }
        String e = usuVar.d().e();
        if (utd.b(e)) {
            byte[] d = urq.d(e, charset);
            utfVar.c(byteArrayOutputStream, d.length);
            byteArrayOutputStream.write(d);
            return;
        }
        utfVar.c(byteArrayOutputStream, 0);
    }

    private long a(List<usm> list, int i) throws ZipException {
        if (list == null) {
            throw new ZipException("file headers are null, cannot calculate number of entries on this disk");
        }
        Iterator<usm> it = list.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (it.next().b() == i) {
                i2++;
            }
        }
        return i2;
    }

    private boolean b(usm usmVar) {
        return usmVar.getCompressedSize() >= 4294967295L || usmVar.getUncompressedSize() >= 4294967295L || usmVar.e() >= 4294967295L || usmVar.b() >= 65535;
    }

    private long a(usu usuVar) {
        if (usuVar.h() && usuVar.f() != null && usuVar.f().b() != -1) {
            return usuVar.f().b();
        }
        return usuVar.d().d();
    }

    private ust c(usu usuVar, int i, long j) throws ZipException {
        ust ustVar = new ust();
        ustVar.setSignature(HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_RECORD);
        ustVar.d(44L);
        if (usuVar.e() != null && usuVar.e().d() != null && usuVar.e().d().size() > 0) {
            usm usmVar = usuVar.e().d().get(0);
            ustVar.e(usmVar.d());
            ustVar.a(usmVar.getVersionNeededToExtract());
        }
        ustVar.c(usuVar.d().c());
        ustVar.d(usuVar.d().b());
        long size = usuVar.e().d().size();
        ustVar.c(usuVar.g() ? a(usuVar.e().d(), usuVar.d().c()) : size);
        ustVar.a(size);
        ustVar.e(i);
        ustVar.b(j);
        return ustVar;
    }
}
