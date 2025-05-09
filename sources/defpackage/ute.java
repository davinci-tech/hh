package defpackage;

import net.lingala.zip4j.headers.VersionMadeBy;
import net.lingala.zip4j.headers.VersionNeededToExtract;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

/* loaded from: classes7.dex */
public class ute {
    public static int c(ZipParameters zipParameters, utf utfVar) {
        byte[] bArr = {VersionMadeBy.SPECIFICATION_VERSION.getCode(), VersionMadeBy.UNIX.getCode()};
        if (uta.b() && !zipParameters.t()) {
            bArr[1] = VersionMadeBy.WINDOWS.getCode();
        }
        return utfVar.c(bArr, 0);
    }

    public static VersionNeededToExtract c(ZipParameters zipParameters) {
        VersionNeededToExtract versionNeededToExtract = VersionNeededToExtract.DEFAULT;
        if (zipParameters.a() == CompressionMethod.DEFLATE) {
            versionNeededToExtract = VersionNeededToExtract.DEFLATE_COMPRESSED;
        }
        if (zipParameters.j() > 4294967295L) {
            versionNeededToExtract = VersionNeededToExtract.ZIP_64_FORMAT;
        }
        return (zipParameters.l() && zipParameters.h().equals(EncryptionMethod.AES)) ? VersionNeededToExtract.AES_ENCRYPTED : versionNeededToExtract;
    }
}
