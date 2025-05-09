package defpackage;

import java.nio.charset.Charset;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

/* loaded from: classes7.dex */
public class urk {
    public usm b(ZipParameters zipParameters, boolean z, int i, Charset charset, utf utfVar) throws ZipException {
        usm usmVar = new usm();
        usmVar.setSignature(HeaderSignature.CENTRAL_DIRECTORY);
        usmVar.c(ute.c(zipParameters, utfVar));
        usmVar.setVersionNeededToExtract(ute.c(zipParameters).getCode());
        if (zipParameters.l() && zipParameters.h() == EncryptionMethod.AES) {
            usmVar.setCompressionMethod(CompressionMethod.AES_INTERNAL_ONLY);
            usmVar.setAesExtraDataRecord(e(zipParameters));
            usmVar.setExtraFieldLength(usmVar.getExtraFieldLength() + 11);
        } else {
            usmVar.setCompressionMethod(zipParameters.a());
        }
        if (zipParameters.l()) {
            if (zipParameters.h() == null || zipParameters.h() == EncryptionMethod.NONE) {
                throw new ZipException("Encryption method has to be set when encryptFiles flag is set in zip parameters");
            }
            usmVar.setEncrypted(true);
            usmVar.setEncryptionMethod(zipParameters.h());
        }
        String a2 = a(zipParameters.o());
        usmVar.setFileName(a2);
        usmVar.setFileNameLength(d(a2, charset));
        if (!z) {
            i = 0;
        }
        usmVar.b(i);
        usmVar.setLastModifiedTime(utd.d(zipParameters.n()));
        boolean d = uta.d(a2);
        usmVar.setDirectory(d);
        usmVar.b(uta.d(d));
        if (zipParameters.u() && zipParameters.j() == -1) {
            usmVar.setUncompressedSize(0L);
        } else {
            usmVar.setUncompressedSize(zipParameters.j());
        }
        if (zipParameters.l() && zipParameters.h() == EncryptionMethod.ZIP_STANDARD) {
            usmVar.setCrc(zipParameters.g());
        }
        usmVar.setGeneralPurposeFlag(c(usmVar.isEncrypted(), zipParameters, charset));
        usmVar.setDataDescriptorExists(zipParameters.u());
        usmVar.b(zipParameters.i());
        return usmVar;
    }

    public usq e(usm usmVar) {
        usq usqVar = new usq();
        usqVar.setSignature(HeaderSignature.LOCAL_FILE_HEADER);
        usqVar.setVersionNeededToExtract(usmVar.getVersionNeededToExtract());
        usqVar.setCompressionMethod(usmVar.getCompressionMethod());
        usqVar.setLastModifiedTime(usmVar.getLastModifiedTime());
        usqVar.setUncompressedSize(usmVar.getUncompressedSize());
        usqVar.setFileNameLength(usmVar.getFileNameLength());
        usqVar.setFileName(usmVar.getFileName());
        usqVar.setEncrypted(usmVar.isEncrypted());
        usqVar.setEncryptionMethod(usmVar.getEncryptionMethod());
        usqVar.setAesExtraDataRecord(usmVar.getAesExtraDataRecord());
        usqVar.setCrc(usmVar.getCrc());
        usqVar.setCompressedSize(usmVar.getCompressedSize());
        usqVar.setGeneralPurposeFlag((byte[]) usmVar.getGeneralPurposeFlag().clone());
        usqVar.setDataDescriptorExists(usmVar.isDataDescriptorExists());
        usqVar.setExtraFieldLength(usmVar.getExtraFieldLength());
        return usqVar;
    }

    private byte[] c(boolean z, ZipParameters zipParameters, Charset charset) {
        byte[] bArr = new byte[2];
        bArr[0] = c(z, zipParameters);
        if (charset == null || usw.e.equals(charset)) {
            bArr[1] = usy.b(bArr[1], 3);
        }
        return bArr;
    }

    private byte c(boolean z, ZipParameters zipParameters) {
        byte b = z ? usy.b((byte) 0, 0) : (byte) 0;
        if (CompressionMethod.DEFLATE.equals(zipParameters.a())) {
            if (CompressionLevel.NORMAL.equals(zipParameters.d())) {
                b = usy.e(usy.e(b, 1), 2);
            } else if (CompressionLevel.MAXIMUM.equals(zipParameters.d())) {
                b = usy.e(usy.b(b, 1), 2);
            } else if (CompressionLevel.FAST.equals(zipParameters.d())) {
                b = usy.b(usy.e(b, 1), 2);
            } else if (CompressionLevel.FASTEST.equals(zipParameters.d()) || CompressionLevel.ULTRA.equals(zipParameters.d())) {
                b = usy.b(usy.b(b, 1), 2);
            }
        }
        return zipParameters.u() ? usy.b(b, 3) : b;
    }

    private String a(String str) throws ZipException {
        if (utd.b(str)) {
            return str;
        }
        throw new ZipException("fileNameInZip is null or empty");
    }

    private use e(ZipParameters zipParameters) throws ZipException {
        use useVar = new use();
        if (zipParameters.b() != null) {
            useVar.a(zipParameters.b());
        }
        if (zipParameters.e() == AesKeyStrength.KEY_STRENGTH_128) {
            useVar.c(AesKeyStrength.KEY_STRENGTH_128);
        } else if (zipParameters.e() == AesKeyStrength.KEY_STRENGTH_192) {
            useVar.c(AesKeyStrength.KEY_STRENGTH_192);
        } else if (zipParameters.e() == AesKeyStrength.KEY_STRENGTH_256) {
            useVar.c(AesKeyStrength.KEY_STRENGTH_256);
        } else {
            throw new ZipException("invalid AES key strength");
        }
        useVar.d(zipParameters.a());
        return useVar;
    }

    private int d(String str, Charset charset) {
        return urq.d(str, charset).length;
    }
}
