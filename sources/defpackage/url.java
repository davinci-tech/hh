package defpackage;

import com.huawei.operation.utils.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.AbstractFileHeader;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

/* loaded from: classes7.dex */
public class url {
    private usu e;
    private final utf d = new utf();
    private final byte[] c = new byte[4];

    public usu c(RandomAccessFile randomAccessFile, usn usnVar) throws IOException {
        if (randomAccessFile.length() == 0) {
            return new usu();
        }
        if (randomAccessFile.length() < 22) {
            throw new ZipException("Zip file size less than minimum expected zip file size. Probably not a zip file or a corrupted zip file");
        }
        usu usuVar = new usu();
        this.e = usuVar;
        try {
            usuVar.a(d(randomAccessFile, this.d, usnVar));
            if (this.e.d().f() == 0) {
                return this.e;
            }
            usu usuVar2 = this.e;
            usuVar2.e(b(randomAccessFile, this.d, usuVar2.d().a()));
            if (this.e.h()) {
                this.e.c(e(randomAccessFile, this.d));
                if (this.e.f() != null && this.e.f().e() > 0) {
                    this.e.c(true);
                } else {
                    this.e.c(false);
                }
            }
            this.e.c(a(randomAccessFile, this.d, usnVar.b()));
            return this.e;
        } catch (ZipException e) {
            throw e;
        } catch (IOException e2) {
            e2.printStackTrace();
            throw new ZipException("Zip headers not found. Probably not a zip file or a corrupted zip file", e2);
        }
    }

    private usk d(RandomAccessFile randomAccessFile, utf utfVar, usn usnVar) throws IOException {
        long c = c(randomAccessFile);
        b(randomAccessFile, 4 + c);
        usk uskVar = new usk();
        uskVar.setSignature(HeaderSignature.END_OF_CENTRAL_DIRECTORY);
        uskVar.d(utfVar.e(randomAccessFile));
        uskVar.e(utfVar.e(randomAccessFile));
        uskVar.a(utfVar.e(randomAccessFile));
        uskVar.c(utfVar.e(randomAccessFile));
        uskVar.b(utfVar.d(randomAccessFile));
        uskVar.d(c);
        randomAccessFile.readFully(this.c);
        uskVar.c(utfVar.d(this.c, 0));
        uskVar.a(c(randomAccessFile, utfVar.e(randomAccessFile), usnVar.b()));
        this.e.c(uskVar.c() > 0);
        return uskVar;
    }

    private usl a(RandomAccessFile randomAccessFile, utf utfVar, Charset charset) throws IOException {
        usl uslVar = new usl();
        ArrayList arrayList = new ArrayList();
        long a2 = urq.a(this.e);
        long c = c(this.e);
        randomAccessFile.seek(a2);
        int i = 2;
        byte[] bArr = new byte[2];
        byte[] bArr2 = new byte[4];
        int i2 = 0;
        int i3 = 0;
        while (i3 < c) {
            usm usmVar = new usm();
            byte[] bArr3 = bArr2;
            if (utfVar.d(randomAccessFile) != HeaderSignature.CENTRAL_DIRECTORY.getValue()) {
                throw new ZipException("Expected central directory entry not found (#" + (i3 + 1) + Constants.RIGHT_BRACKET_ONLY);
            }
            usmVar.setSignature(HeaderSignature.CENTRAL_DIRECTORY);
            usmVar.c(utfVar.e(randomAccessFile));
            usmVar.setVersionNeededToExtract(utfVar.e(randomAccessFile));
            byte[] bArr4 = new byte[i];
            randomAccessFile.readFully(bArr4);
            usmVar.setEncrypted(usy.c(bArr4[i2], i2));
            usmVar.setDataDescriptorExists(usy.c(bArr4[i2], 3));
            usmVar.setFileNameUTF8Encoded(usy.c(bArr4[1], 3));
            usmVar.setGeneralPurposeFlag((byte[]) bArr4.clone());
            usmVar.setCompressionMethod(CompressionMethod.getCompressionMethodFromCode(utfVar.e(randomAccessFile)));
            usmVar.setLastModifiedTime(utfVar.d(randomAccessFile));
            randomAccessFile.readFully(bArr3);
            usmVar.setCrc(utfVar.d(bArr3, i2));
            int i4 = i3;
            usmVar.setCompressedSize(utfVar.b(randomAccessFile, 4));
            usmVar.setUncompressedSize(utfVar.b(randomAccessFile, 4));
            int e = utfVar.e(randomAccessFile);
            usmVar.setFileNameLength(e);
            usmVar.setExtraFieldLength(utfVar.e(randomAccessFile));
            int e2 = utfVar.e(randomAccessFile);
            usmVar.e(e2);
            usmVar.b(utfVar.e(randomAccessFile));
            randomAccessFile.readFully(bArr);
            usmVar.d((byte[]) bArr.clone());
            randomAccessFile.readFully(bArr3);
            usmVar.b((byte[]) bArr3.clone());
            randomAccessFile.readFully(bArr3);
            long j = c;
            byte[] bArr5 = bArr;
            usmVar.c(utfVar.d(bArr3, 0));
            if (e > 0) {
                byte[] bArr6 = new byte[e];
                randomAccessFile.readFully(bArr6);
                usmVar.setFileName(urq.e(bArr6, usmVar.isFileNameUTF8Encoded(), charset));
                usmVar.setDirectory(e(usmVar.a(), usmVar.getFileName()));
                d(randomAccessFile, usmVar);
                b(usmVar, utfVar);
                c(usmVar, utfVar);
                if (e2 > 0) {
                    byte[] bArr7 = new byte[e2];
                    randomAccessFile.readFully(bArr7);
                    usmVar.b(urq.e(bArr7, usmVar.isFileNameUTF8Encoded(), charset));
                }
                if (usmVar.isEncrypted()) {
                    if (usmVar.getAesExtraDataRecord() != null) {
                        usmVar.setEncryptionMethod(EncryptionMethod.AES);
                    } else {
                        usmVar.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
                    }
                }
                arrayList.add(usmVar);
                bArr2 = bArr3;
                i2 = 0;
                i = 2;
                i3 = i4 + 1;
                bArr = bArr5;
                c = j;
            } else {
                throw new ZipException("Invalid entry name in file header");
            }
        }
        uslVar.b(arrayList);
        ush ushVar = new ush();
        if (utfVar.d(randomAccessFile) == HeaderSignature.DIGITAL_SIGNATURE.getValue()) {
            ushVar.setSignature(HeaderSignature.DIGITAL_SIGNATURE);
            ushVar.a(utfVar.e(randomAccessFile));
            if (ushVar.e() > 0) {
                byte[] bArr8 = new byte[ushVar.e()];
                randomAccessFile.readFully(bArr8);
                ushVar.d(new String(bArr8));
            }
        }
        return uslVar;
    }

    private void d(RandomAccessFile randomAccessFile, usm usmVar) throws IOException {
        int extraFieldLength = usmVar.getExtraFieldLength();
        if (extraFieldLength <= 0) {
            return;
        }
        usmVar.setExtraDataRecords(b(randomAccessFile, extraFieldLength));
    }

    private void d(InputStream inputStream, usq usqVar) throws IOException {
        int extraFieldLength = usqVar.getExtraFieldLength();
        if (extraFieldLength <= 0) {
            return;
        }
        usqVar.setExtraDataRecords(b(inputStream, extraFieldLength));
    }

    private List<usp> b(RandomAccessFile randomAccessFile, int i) throws IOException {
        if (i < 4) {
            if (i <= 0) {
                return null;
            }
            randomAccessFile.skipBytes(i);
            return null;
        }
        byte[] bArr = new byte[i];
        randomAccessFile.read(bArr);
        try {
            return d(bArr, i);
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    private List<usp> b(InputStream inputStream, int i) throws IOException {
        if (i < 4) {
            if (i <= 0) {
                return null;
            }
            inputStream.skip(i);
            return null;
        }
        byte[] bArr = new byte[i];
        utd.c(inputStream, bArr);
        try {
            return d(bArr, i);
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    private List<usp> d(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < i) {
            usp uspVar = new usp();
            uspVar.b(this.d.c(bArr, i2));
            int c = this.d.c(bArr, i2 + 2);
            uspVar.d(c);
            int i3 = i2 + 4;
            if (c > 0) {
                byte[] bArr2 = new byte[c];
                System.arraycopy(bArr, i3, bArr2, 0, c);
                uspVar.a(bArr2);
            }
            i2 = i3 + c;
            arrayList.add(uspVar);
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }

    private usr b(RandomAccessFile randomAccessFile, utf utfVar, long j) throws IOException {
        usr usrVar = new usr();
        c(randomAccessFile, j);
        if (utfVar.d(randomAccessFile) == HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR.getValue()) {
            this.e.d(true);
            usrVar.setSignature(HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR);
            usrVar.c(utfVar.d(randomAccessFile));
            usrVar.b(utfVar.c(randomAccessFile));
            usrVar.b(utfVar.d(randomAccessFile));
            return usrVar;
        }
        this.e.d(false);
        return null;
    }

    private ust e(RandomAccessFile randomAccessFile, utf utfVar) throws IOException {
        if (this.e.b() == null) {
            throw new ZipException("invalid zip64 end of central directory locator");
        }
        long c = this.e.b().c();
        if (c < 0) {
            throw new ZipException("invalid offset for start of end of central directory record");
        }
        randomAccessFile.seek(c);
        ust ustVar = new ust();
        if (utfVar.d(randomAccessFile) != HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_RECORD.getValue()) {
            throw new ZipException("invalid signature for zip64 end of central directory record");
        }
        ustVar.setSignature(HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_RECORD);
        ustVar.d(utfVar.c(randomAccessFile));
        ustVar.e(utfVar.e(randomAccessFile));
        ustVar.a(utfVar.e(randomAccessFile));
        ustVar.c(utfVar.d(randomAccessFile));
        ustVar.d(utfVar.d(randomAccessFile));
        ustVar.c(utfVar.c(randomAccessFile));
        ustVar.a(utfVar.c(randomAccessFile));
        ustVar.e(utfVar.c(randomAccessFile));
        ustVar.b(utfVar.c(randomAccessFile));
        long d = ustVar.d() - 44;
        if (d > 0) {
            byte[] bArr = new byte[(int) d];
            randomAccessFile.readFully(bArr);
            ustVar.c(bArr);
        }
        return ustVar;
    }

    private void b(usm usmVar, utf utfVar) {
        uss a2;
        if (usmVar.getExtraDataRecords() == null || usmVar.getExtraDataRecords().size() <= 0 || (a2 = a(usmVar.getExtraDataRecords(), utfVar, usmVar.getUncompressedSize(), usmVar.getCompressedSize(), usmVar.e(), usmVar.b())) == null) {
            return;
        }
        usmVar.setZip64ExtendedInfo(a2);
        if (a2.d() != -1) {
            usmVar.setUncompressedSize(a2.d());
        }
        if (a2.a() != -1) {
            usmVar.setCompressedSize(a2.a());
        }
        if (a2.e() != -1) {
            usmVar.c(a2.e());
        }
        if (a2.c() != -1) {
            usmVar.b(a2.c());
        }
    }

    private void a(usq usqVar, utf utfVar) throws ZipException {
        uss a2;
        if (usqVar == null) {
            throw new ZipException("file header is null in reading Zip64 Extended Info");
        }
        if (usqVar.getExtraDataRecords() == null || usqVar.getExtraDataRecords().size() <= 0 || (a2 = a(usqVar.getExtraDataRecords(), utfVar, usqVar.getUncompressedSize(), usqVar.getCompressedSize(), 0L, 0)) == null) {
            return;
        }
        usqVar.setZip64ExtendedInfo(a2);
        if (a2.d() != -1) {
            usqVar.setUncompressedSize(a2.d());
        }
        if (a2.a() != -1) {
            usqVar.setCompressedSize(a2.a());
        }
    }

    private uss a(List<usp> list, utf utfVar, long j, long j2, long j3, int i) {
        for (usp uspVar : list) {
            if (uspVar != null && HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue() == uspVar.b()) {
                uss ussVar = new uss();
                byte[] c = uspVar.c();
                if (uspVar.e() <= 0) {
                    return null;
                }
                int i2 = 0;
                if (uspVar.e() > 0 && j == 4294967295L) {
                    ussVar.d(utfVar.d(c, 0));
                    i2 = 8;
                }
                if (i2 < uspVar.e() && j2 == 4294967295L) {
                    ussVar.c(utfVar.d(c, i2));
                    i2 += 8;
                }
                if (i2 < uspVar.e() && j3 == 4294967295L) {
                    ussVar.a(utfVar.d(c, i2));
                    i2 += 8;
                }
                if (i2 < uspVar.e() && i == 65535) {
                    ussVar.a(utfVar.e(c, i2));
                }
                return ussVar;
            }
        }
        return null;
    }

    private void c(RandomAccessFile randomAccessFile, long j) throws IOException {
        b(randomAccessFile, j - 20);
    }

    public usq c(InputStream inputStream, Charset charset) throws IOException {
        usq usqVar = new usq();
        byte[] bArr = new byte[4];
        int b = this.d.b(inputStream);
        if (b == HeaderSignature.TEMPORARY_SPANNING_MARKER.getValue()) {
            b = this.d.b(inputStream);
        }
        if (b != HeaderSignature.LOCAL_FILE_HEADER.getValue()) {
            return null;
        }
        usqVar.setSignature(HeaderSignature.LOCAL_FILE_HEADER);
        usqVar.setVersionNeededToExtract(this.d.a(inputStream));
        byte[] bArr2 = new byte[2];
        if (utd.c(inputStream, bArr2) != 2) {
            throw new ZipException("Could not read enough bytes for generalPurposeFlags");
        }
        usqVar.setEncrypted(usy.c(bArr2[0], 0));
        usqVar.setDataDescriptorExists(usy.c(bArr2[0], 3));
        boolean z = true;
        usqVar.setFileNameUTF8Encoded(usy.c(bArr2[1], 3));
        usqVar.setGeneralPurposeFlag((byte[]) bArr2.clone());
        usqVar.setCompressionMethod(CompressionMethod.getCompressionMethodFromCode(this.d.a(inputStream)));
        usqVar.setLastModifiedTime(this.d.b(inputStream));
        utd.c(inputStream, bArr);
        usqVar.setCrc(this.d.d(bArr, 0));
        usqVar.setCompressedSize(this.d.b(inputStream, 4));
        usqVar.setUncompressedSize(this.d.b(inputStream, 4));
        int a2 = this.d.a(inputStream);
        usqVar.setFileNameLength(a2);
        usqVar.setExtraFieldLength(this.d.a(inputStream));
        if (a2 > 0) {
            byte[] bArr3 = new byte[a2];
            utd.c(inputStream, bArr3);
            String e = urq.e(bArr3, usqVar.isFileNameUTF8Encoded(), charset);
            usqVar.setFileName(e);
            if (!e.endsWith("/") && !e.endsWith("\\")) {
                z = false;
            }
            usqVar.setDirectory(z);
            d(inputStream, usqVar);
            a(usqVar, this.d);
            c(usqVar, this.d);
            if (usqVar.isEncrypted() && usqVar.getEncryptionMethod() != EncryptionMethod.AES) {
                if (usy.c(usqVar.getGeneralPurposeFlag()[0], 6)) {
                    usqVar.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD_VARIANT_STRONG);
                } else {
                    usqVar.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
                }
            }
            return usqVar;
        }
        throw new ZipException("Invalid entry name in local file header");
    }

    public usj c(InputStream inputStream, boolean z) throws IOException {
        usj usjVar = new usj();
        byte[] bArr = new byte[4];
        utd.c(inputStream, bArr);
        long d = this.d.d(bArr, 0);
        if (d == HeaderSignature.EXTRA_DATA_RECORD.getValue()) {
            usjVar.setSignature(HeaderSignature.EXTRA_DATA_RECORD);
            utd.c(inputStream, bArr);
            usjVar.a(this.d.d(bArr, 0));
        } else {
            usjVar.a(d);
        }
        if (z) {
            usjVar.b(this.d.d(inputStream));
            usjVar.d(this.d.d(inputStream));
        } else {
            usjVar.b(this.d.b(inputStream));
            usjVar.d(this.d.b(inputStream));
        }
        return usjVar;
    }

    private void c(AbstractFileHeader abstractFileHeader, utf utfVar) throws ZipException {
        use c;
        if (abstractFileHeader.getExtraDataRecords() == null || abstractFileHeader.getExtraDataRecords().size() <= 0 || (c = c(abstractFileHeader.getExtraDataRecords(), utfVar)) == null) {
            return;
        }
        abstractFileHeader.setAesExtraDataRecord(c);
        abstractFileHeader.setEncryptionMethod(EncryptionMethod.AES);
    }

    private use c(List<usp> list, utf utfVar) throws ZipException {
        if (list == null) {
            return null;
        }
        for (usp uspVar : list) {
            if (uspVar != null && uspVar.b() == HeaderSignature.AES_EXTRA_DATA_RECORD.getValue()) {
                byte[] c = uspVar.c();
                if (c == null || c.length != 7) {
                    throw new ZipException("corrupt AES extra data records");
                }
                use useVar = new use();
                useVar.setSignature(HeaderSignature.AES_EXTRA_DATA_RECORD);
                useVar.a(uspVar.e());
                byte[] c2 = uspVar.c();
                useVar.a(AesVersion.getFromVersionNumber(utfVar.c(c2, 0)));
                byte[] bArr = new byte[2];
                System.arraycopy(c2, 2, bArr, 0, 2);
                useVar.b(new String(bArr));
                useVar.c(AesKeyStrength.getAesKeyStrengthFromRawCode(c2[4] & 255));
                useVar.d(CompressionMethod.getCompressionMethodFromCode(utfVar.c(c2, 5)));
                return useVar;
            }
        }
        return null;
    }

    private long c(usu usuVar) {
        if (usuVar.h()) {
            return usuVar.f().i();
        }
        return usuVar.d().f();
    }

    private long c(RandomAccessFile randomAccessFile) throws IOException {
        long length = randomAccessFile.length();
        if (length < 22) {
            throw new ZipException("Zip file size less than size of zip headers. Probably not a zip file.");
        }
        long j = length - 22;
        b(randomAccessFile, j);
        return ((long) this.d.d(randomAccessFile)) == HeaderSignature.END_OF_CENTRAL_DIRECTORY.getValue() ? j : d(randomAccessFile);
    }

    private long d(RandomAccessFile randomAccessFile) throws IOException {
        long length = randomAccessFile.length() - 22;
        for (long length2 = randomAccessFile.length() < 65536 ? randomAccessFile.length() : 65536L; length2 > 0 && length > 0; length2--) {
            length--;
            b(randomAccessFile, length);
            if (this.d.d(randomAccessFile) == HeaderSignature.END_OF_CENTRAL_DIRECTORY.getValue()) {
                return length;
            }
        }
        throw new ZipException("Zip headers not found. Probably not a zip file");
    }

    private void b(RandomAccessFile randomAccessFile, long j) throws IOException {
        if (randomAccessFile instanceof urw) {
            ((urw) randomAccessFile).b(j);
        } else {
            randomAccessFile.seek(j);
        }
    }

    private String c(RandomAccessFile randomAccessFile, int i, Charset charset) {
        if (i <= 0) {
            return null;
        }
        try {
            byte[] bArr = new byte[i];
            randomAccessFile.readFully(bArr);
            if (charset == null) {
                charset = usw.c;
            }
            return urq.e(bArr, false, charset);
        } catch (IOException unused) {
            return null;
        }
    }

    public boolean e(byte[] bArr, String str) {
        byte b = bArr[0];
        if (b != 0 && usy.c(b, 4)) {
            return true;
        }
        byte b2 = bArr[3];
        if (b2 != 0 && usy.c(b2, 6)) {
            return true;
        }
        if (str != null) {
            return str.endsWith("/") || str.endsWith("\\");
        }
        return false;
    }
}
