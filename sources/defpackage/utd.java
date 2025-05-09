package defpackage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Calendar;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AbstractFileHeader;
import net.lingala.zip4j.model.enums.CompressionMethod;

/* loaded from: classes7.dex */
public class utd {
    public static boolean d(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean b(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static long d(long j) {
        if (j < 0) {
            return 2162688L;
        }
        long e = e(j);
        if (e != 2162688) {
            return e + ((j % 2000) << 32);
        }
        return 2162688L;
    }

    private static long e(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (calendar.get(1) < 1980) {
            return 2162688L;
        }
        int i = (calendar.get(2) + 1) << 21;
        return i | ((r10 - 1980) << 25) | (calendar.get(5) << 16) | (calendar.get(11) << 11) | (calendar.get(12) << 5) | (calendar.get(13) >> 1);
    }

    public static long a(long j) {
        return b(j) + (j >> 32);
    }

    private static long b(long j) {
        int i = (int) ((j << 1) & 62);
        int i2 = (int) ((j >> 5) & 63);
        int i3 = (int) ((j >> 11) & 31);
        int i4 = (int) ((j >> 16) & 31);
        int i5 = (int) (((j >> 21) & 15) - 1);
        int i6 = (int) (((j >> 25) & 127) + 1980);
        Calendar calendar = Calendar.getInstance();
        calendar.set(i6, i5, i4, i3, i2, i);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }

    public static byte[] e(char[] cArr, boolean z) {
        if (z) {
            return e(cArr);
        }
        return d(cArr);
    }

    public static CompressionMethod b(AbstractFileHeader abstractFileHeader) throws ZipException {
        if (abstractFileHeader.getCompressionMethod() != CompressionMethod.AES_INTERNAL_ONLY) {
            return abstractFileHeader.getCompressionMethod();
        }
        if (abstractFileHeader.getAesExtraDataRecord() == null) {
            throw new ZipException("AesExtraDataRecord not present in local header for aes encrypted data");
        }
        return abstractFileHeader.getAesExtraDataRecord().e();
    }

    public static int c(InputStream inputStream, byte[] bArr) throws IOException {
        int read = inputStream.read(bArr);
        if (read == -1) {
            throw new IOException("Unexpected EOF reached when trying to read stream");
        }
        if (read == bArr.length || (read = b(inputStream, bArr, read)) == bArr.length) {
            return read;
        }
        throw new IOException("Cannot read fully into byte buffer");
    }

    public static int d(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("Negative offset");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Negative length");
        }
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        if (i + i2 > bArr.length) {
            throw new IllegalArgumentException("Length greater than buffer size");
        }
        while (i3 != i2) {
            int read = inputStream.read(bArr, i + i3, i2 - i3);
            if (read == -1) {
                if (i3 == 0) {
                    return -1;
                }
                return i3;
            }
            i3 += read;
        }
        return i3;
    }

    private static int b(InputStream inputStream, byte[] bArr, int i) throws IOException {
        if (i < 0) {
            throw new IOException("Invalid readLength");
        }
        int i2 = 0;
        if (i == 0) {
            return 0;
        }
        int length = bArr.length - i;
        for (int i3 = 1; i < bArr.length && i2 != -1 && i3 < 15; i3++) {
            i2 = inputStream.read(bArr, i, length);
            if (i2 > 0) {
                i += i2;
                length -= i2;
            }
        }
        return i;
    }

    private static byte[] e(char[] cArr) {
        try {
            ByteBuffer encode = usw.e.encode(CharBuffer.wrap(cArr));
            byte[] bArr = new byte[encode.limit()];
            encode.get(bArr);
            return bArr;
        } catch (Exception unused) {
            return d(cArr);
        }
    }

    private static byte[] d(char[] cArr) {
        byte[] bArr = new byte[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }
}
