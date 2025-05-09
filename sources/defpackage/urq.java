package defpackage;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import net.lingala.zip4j.exception.ZipException;

/* loaded from: classes7.dex */
public class urq {
    public static usm e(usu usuVar, String str) throws ZipException {
        usm a2 = a(usuVar, str);
        if (a2 != null) {
            return a2;
        }
        String replaceAll = str.replaceAll("\\\\", "/");
        usm a3 = a(usuVar, replaceAll);
        return a3 == null ? a(usuVar, replaceAll.replaceAll("/", "\\\\")) : a3;
    }

    public static String e(byte[] bArr, boolean z, Charset charset) {
        if (charset != null) {
            return new String(bArr, charset);
        }
        if (z) {
            return new String(bArr, usw.e);
        }
        try {
            return new String(bArr, "Cp437");
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }

    public static byte[] d(String str, Charset charset) {
        if (charset == null) {
            return str.getBytes(usw.c);
        }
        return str.getBytes(charset);
    }

    public static long a(usu usuVar) {
        if (usuVar.h()) {
            return usuVar.f().b();
        }
        return usuVar.d().d();
    }

    private static usm a(usu usuVar, String str) throws ZipException {
        if (usuVar == null) {
            throw new ZipException("zip model is null, cannot determine file header with exact match for fileName: " + str);
        }
        if (!utd.b(str)) {
            throw new ZipException("file name is null, cannot determine file header with exact match for fileName: " + str);
        }
        if (usuVar.e() == null) {
            throw new ZipException("central directory is null, cannot determine file header with exact match for fileName: " + str);
        }
        if (usuVar.e().d() == null) {
            throw new ZipException("file Headers are null, cannot determine file header with exact match for fileName: " + str);
        }
        if (usuVar.e().d().size() == 0) {
            return null;
        }
        for (usm usmVar : usuVar.e().d()) {
            String fileName = usmVar.getFileName();
            if (utd.b(fileName) && str.equals(fileName)) {
                return usmVar;
            }
        }
        return null;
    }
}
