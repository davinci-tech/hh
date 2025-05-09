package defpackage;

import androidx.core.view.MotionEventCompat;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import okio.Utf8;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vbk {

    /* renamed from: a, reason: collision with root package name */
    private static final Logger f17645a = vha.a((Class<?>) vbk.class);
    private static final byte[] b = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] c = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, Utf8.REPLACEMENT_BYTE, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, BaseType.Float, BaseType.Double, 13, BaseType.Vector, BaseType.Obj, BaseType.Union, BaseType.Array, BaseType.Vector64, BaseType.MaxBaseType, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, PublicSuffixDatabase.i, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] g = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] j = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, BaseType.Float, BaseType.Double, 13, BaseType.Vector, BaseType.Obj, BaseType.Union, BaseType.Array, BaseType.Vector64, BaseType.MaxBaseType, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, Utf8.REPLACEMENT_BYTE, -9, 26, 27, 28, 29, 30, 31, 32, PublicSuffixDatabase.i, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] e = {45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
    private static final byte[] d = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, BaseType.Float, BaseType.Double, 13, BaseType.Vector, BaseType.Obj, BaseType.Union, BaseType.Array, BaseType.Vector64, BaseType.MaxBaseType, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, PublicSuffixDatabase.i, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, Utf8.REPLACEMENT_BYTE, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    private static final byte[] e(int i) {
        if ((i & 16) == 16) {
            return g;
        }
        if ((i & 32) == 32) {
            return e;
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] b(int i) {
        if ((i & 16) == 16) {
            return j;
        }
        if ((i & 32) == 32) {
            return d;
        }
        return c;
    }

    private vbk() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] e(byte[] bArr, byte[] bArr2, int i, int i2) {
        c(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    private static byte[] c(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] e2 = e(i4);
        int i5 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 2 ? (bArr[i + 2] << 24) >>> 24 : 0);
        if (i2 == 1) {
            bArr2[i3] = e2[i5 >>> 18];
            bArr2[i3 + 1] = e2[(i5 >>> 12) & 63];
            if ((i4 & 64) == 0) {
                bArr2[i3 + 2] = 61;
                bArr2[i3 + 3] = 61;
            }
            return bArr2;
        }
        if (i2 == 2) {
            bArr2[i3] = e2[i5 >>> 18];
            bArr2[i3 + 1] = e2[(i5 >>> 12) & 63];
            bArr2[i3 + 2] = e2[(i5 >>> 6) & 63];
            if ((i4 & 64) == 0) {
                bArr2[i3 + 3] = 61;
            }
            return bArr2;
        }
        if (i2 != 3) {
            return bArr2;
        }
        bArr2[i3] = e2[i5 >>> 18];
        bArr2[i3 + 1] = e2[(i5 >>> 12) & 63];
        bArr2[i3 + 2] = e2[(i5 >>> 6) & 63];
        bArr2[i3 + 3] = e2[i5 & 63];
        return bArr2;
    }

    public static String a(byte[] bArr) {
        try {
            return c(bArr, 0, bArr.length, 0);
        } catch (IOException unused) {
            return null;
        }
    }

    public static String b(byte[] bArr, int i) throws IOException {
        return c(bArr, 0, bArr.length, i);
    }

    public static String c(byte[] bArr, int i, int i2, int i3) throws IOException {
        byte[] e2 = e(bArr, i, i2, i3);
        try {
            return new String(e2, "US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            return new String(e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.io.OutputStream, vbk$c] */
    public static byte[] e(byte[] bArr, int i, int i2, int i3) throws IOException {
        int i4;
        GZIPOutputStream gZIPOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        GZIPOutputStream gZIPOutputStream2;
        ?? cVar;
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i);
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + i2);
        }
        if (i + i2 > bArr.length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)));
        }
        if ((i3 & 2) != 0) {
            GZIPOutputStream gZIPOutputStream3 = null;
            try {
                byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    cVar = new c(byteArrayOutputStream2, i3 | 1);
                    try {
                        gZIPOutputStream2 = new GZIPOutputStream(cVar);
                    } catch (IOException e2) {
                        e = e2;
                        gZIPOutputStream2 = null;
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (IOException e3) {
                    e = e3;
                    gZIPOutputStream2 = null;
                } catch (Throwable th2) {
                    th = th2;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    gZIPOutputStream = null;
                }
            } catch (IOException e4) {
                e = e4;
                gZIPOutputStream = null;
                byteArrayOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                gZIPOutputStream = null;
                byteArrayOutputStream = null;
            }
            try {
                gZIPOutputStream2.write(bArr, i, i2);
                gZIPOutputStream2.close();
                try {
                    gZIPOutputStream2.close();
                } catch (Exception unused) {
                }
                try {
                    cVar.close();
                } catch (Exception unused2) {
                }
                try {
                    byteArrayOutputStream2.close();
                } catch (Exception unused3) {
                }
                return byteArrayOutputStream2.toByteArray();
            } catch (IOException e5) {
                e = e5;
                gZIPOutputStream3 = cVar;
                byteArrayOutputStream = byteArrayOutputStream2;
                gZIPOutputStream = gZIPOutputStream3;
                gZIPOutputStream3 = gZIPOutputStream2;
                try {
                    throw e;
                } catch (Throwable th4) {
                    th = th4;
                    try {
                        gZIPOutputStream3.close();
                    } catch (Exception unused4) {
                    }
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception unused5) {
                    }
                    try {
                        byteArrayOutputStream.close();
                        throw th;
                    } catch (Exception unused6) {
                        throw th;
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                gZIPOutputStream3 = gZIPOutputStream2;
                byteArrayOutputStream = byteArrayOutputStream2;
                gZIPOutputStream = cVar;
                gZIPOutputStream3.close();
                gZIPOutputStream.close();
                byteArrayOutputStream.close();
                throw th;
            }
        }
        boolean z = (i3 & 8) != 0;
        int i5 = (i2 / 3) * 4;
        int i6 = i2 % 3;
        if ((i3 & 64) == 0) {
            i4 = i5 + (i6 > 0 ? 4 : 0);
        } else {
            i4 = i5 + (i6 > 0 ? i6 + 1 : 0);
        }
        if (z) {
            i4 += i4 / 76;
        }
        int i7 = i4;
        byte[] bArr2 = new byte[i7];
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i8 < i2 - 2) {
            c(bArr, i8 + i, 3, bArr2, i9, i3);
            int i11 = i10 + 4;
            if (!z || i11 < 76) {
                i10 = i11;
            } else {
                bArr2[i9 + 4] = 10;
                i9++;
                i10 = 0;
            }
            i8 += 3;
            i9 += 4;
        }
        if (i8 < i2) {
            c(bArr, i8 + i, i2 - i8, bArr2, i9, i3);
            i9 += 4;
        }
        int i12 = i9;
        if (i12 > i7 - 1) {
            return bArr2;
        }
        byte[] bArr3 = new byte[i12];
        System.arraycopy(bArr2, 0, bArr3, 0, i12);
        return bArr3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int e(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4;
        int i5;
        if (bArr == null) {
            throw new NullPointerException("Source array was null.");
        }
        if (bArr2 == null) {
            throw new NullPointerException("Destination array was null.");
        }
        if (i < 0 || (i4 = i + 3) >= bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i)));
        }
        if (i2 < 0 || (i5 = i2 + 2) >= bArr2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(bArr2.length), Integer.valueOf(i2)));
        }
        byte[] b2 = b(i3);
        byte b3 = bArr[i + 2];
        if (b3 == 61) {
            bArr2[i2] = (byte) ((((b2[bArr[i + 1]] & 255) << 12) | ((b2[bArr[i]] & 255) << 18)) >>> 16);
            return 1;
        }
        byte b4 = bArr[i4];
        if (b4 == 61) {
            int i6 = ((b2[bArr[i + 1]] & 255) << 12) | ((b2[bArr[i]] & 255) << 18) | ((b2[b3] & 255) << 6);
            bArr2[i2] = (byte) (i6 >>> 16);
            bArr2[i2 + 1] = (byte) (i6 >>> 8);
            return 2;
        }
        int i7 = ((b2[bArr[i + 1]] & 255) << 12) | ((b2[bArr[i]] & 255) << 18) | ((b2[b3] & 255) << 6) | (b2[b4] & 255);
        bArr2[i2] = (byte) (i7 >> 16);
        bArr2[i2 + 1] = (byte) (i7 >> 8);
        bArr2[i5] = (byte) i7;
        return 3;
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) throws IOException {
        int i4;
        if (bArr == null) {
            throw new NullPointerException("Cannot decode null source array.");
        }
        if (i < 0 || (i4 = i + i2) > bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        if (i2 == 0) {
            return vbj.c;
        }
        if (i2 < 4) {
            throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + i2);
        }
        byte[] b2 = b(i3);
        byte[] bArr2 = new byte[(i2 * 3) / 4];
        byte[] bArr3 = new byte[4];
        int i5 = 0;
        int i6 = 0;
        while (i < i4) {
            byte b3 = bArr[i];
            byte b4 = b2[b3 & 255];
            if (b4 < -5) {
                throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", Integer.valueOf(b3 & 255), Integer.valueOf(i)));
            }
            if (b4 >= -1) {
                int i7 = i6 + 1;
                bArr3[i6] = b3;
                if (i7 > 3) {
                    i5 += e(bArr3, 0, bArr2, i5, i3);
                    if (bArr[i] == 61) {
                        break;
                    }
                    i6 = 0;
                } else {
                    i6 = i7;
                }
            }
            i++;
        }
        byte[] bArr4 = new byte[i5];
        System.arraycopy(bArr2, 0, bArr4, 0, i5);
        return bArr4;
    }

    public static byte[] e(String str) throws IOException {
        return b(str, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r6v11, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v9 */
    public static byte[] b(String str, int i) throws IOException {
        byte[] bytes;
        ?? r6;
        ?? r1;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        ?? r4;
        ?? r3;
        ByteArrayOutputStream byteArrayOutputStream3;
        if (str == null) {
            throw new NullPointerException("Input string was null.");
        }
        try {
            bytes = str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        byte[] a2 = a(bytes, 0, bytes.length, i);
        boolean z = (i & 4) != 0;
        if (a2 != null && a2.length >= 4 && !z && 35615 == ((a2[0] & 255) | ((a2[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
            byte[] bArr = new byte[2048];
            ByteArrayOutputStream byteArrayOutputStream4 = null;
            try {
                byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    r3 = new ByteArrayInputStream(a2);
                    try {
                        r4 = new GZIPInputStream(r3);
                        while (true) {
                            try {
                                int read = r4.read(bArr);
                                if (read < 0) {
                                    break;
                                }
                                byteArrayOutputStream2.write(bArr, 0, read);
                            } catch (IOException e2) {
                                e = e2;
                                byteArrayOutputStream4 = r3;
                                byteArrayOutputStream3 = r4;
                                byteArrayOutputStream = byteArrayOutputStream4;
                                byteArrayOutputStream4 = byteArrayOutputStream3;
                                try {
                                    f17645a.info(e.getMessage(), (Throwable) e);
                                    try {
                                        byteArrayOutputStream2.close();
                                    } catch (Exception unused2) {
                                    }
                                    r4 = byteArrayOutputStream4;
                                    r3 = byteArrayOutputStream;
                                    r4.close();
                                    r3.close();
                                    return a2;
                                } catch (Throwable th) {
                                    th = th;
                                    r6 = byteArrayOutputStream4;
                                    byteArrayOutputStream4 = byteArrayOutputStream2;
                                    r1 = byteArrayOutputStream;
                                    try {
                                        byteArrayOutputStream4.close();
                                    } catch (Exception unused3) {
                                    }
                                    try {
                                        r6.close();
                                    } catch (Exception unused4) {
                                    }
                                    try {
                                        r1.close();
                                        throw th;
                                    } catch (Exception unused5) {
                                        throw th;
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                byteArrayOutputStream4 = r4;
                                r6 = byteArrayOutputStream4;
                                byteArrayOutputStream4 = byteArrayOutputStream2;
                                r1 = r3;
                                byteArrayOutputStream4.close();
                                r6.close();
                                r1.close();
                                throw th;
                            }
                        }
                        a2 = byteArrayOutputStream2.toByteArray();
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception unused6) {
                        }
                    } catch (IOException e3) {
                        e = e3;
                        r4 = 0;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (IOException e4) {
                    e = e4;
                    byteArrayOutputStream3 = null;
                } catch (Throwable th4) {
                    th = th4;
                    r3 = 0;
                }
            } catch (IOException e5) {
                e = e5;
                byteArrayOutputStream = null;
                byteArrayOutputStream2 = null;
            } catch (Throwable th5) {
                th = th5;
                r6 = 0;
                r1 = 0;
            }
            try {
                r4.close();
            } catch (Exception unused7) {
            }
            try {
                r3.close();
            } catch (Exception unused8) {
            }
        }
        return a2;
    }

    public static class c extends FilterOutputStream {

        /* renamed from: a, reason: collision with root package name */
        private byte[] f17646a;
        private int b;
        private byte[] c;
        private byte[] d;
        private boolean e;
        private int f;
        private boolean g;
        private boolean h;
        private int i;
        private int j;

        public c(OutputStream outputStream, int i) {
            super(outputStream);
            this.e = (i & 8) != 0;
            boolean z = (i & 1) != 0;
            this.g = z;
            int i2 = z ? 3 : 4;
            this.b = i2;
            this.d = new byte[i2];
            this.i = 0;
            this.j = 0;
            this.h = false;
            this.f17646a = new byte[4];
            this.f = i;
            this.c = vbk.b(i);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            if (this.h) {
                this.out.write(i);
                return;
            }
            if (this.g) {
                byte[] bArr = this.d;
                int i2 = this.i;
                int i3 = i2 + 1;
                this.i = i3;
                bArr[i2] = (byte) i;
                if (i3 >= this.b) {
                    this.out.write(vbk.e(this.f17646a, this.d, this.b, this.f));
                    int i4 = this.j + 4;
                    this.j = i4;
                    if (this.e && i4 >= 76) {
                        this.out.write(10);
                        this.j = 0;
                    }
                    this.i = 0;
                    return;
                }
                return;
            }
            byte b = this.c[i & 127];
            if (b <= -5) {
                if (b != -5) {
                    throw new IOException("Invalid character in Base64 data.");
                }
                return;
            }
            byte[] bArr2 = this.d;
            int i5 = this.i;
            int i6 = i5 + 1;
            this.i = i6;
            bArr2[i5] = (byte) i;
            if (i6 >= this.b) {
                this.out.write(this.f17646a, 0, vbk.e(bArr2, 0, this.f17646a, 0, this.f));
                this.i = 0;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.h) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }

        public void d() throws IOException {
            if (this.i > 0) {
                if (this.g) {
                    this.out.write(vbk.e(this.f17646a, this.d, this.i, this.f));
                    this.i = 0;
                    return;
                }
                throw new IOException("Base64 input not properly padded.");
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            d();
            super.close();
            this.d = null;
            this.out = null;
        }
    }
}
