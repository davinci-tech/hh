package com.amap.api.col.p0003sl;

import androidx.core.view.MotionEventCompat;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import okio.Utf8;

/* loaded from: classes2.dex */
public class id {

    /* renamed from: a, reason: collision with root package name */
    static final /* synthetic */ boolean f1174a = true;
    private static final byte[] b = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] c = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, Utf8.REPLACEMENT_BYTE, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, BaseType.Float, BaseType.Double, 13, BaseType.Vector, BaseType.Obj, BaseType.Union, BaseType.Array, BaseType.Vector64, BaseType.MaxBaseType, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, PublicSuffixDatabase.i, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] d = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] e = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, BaseType.Float, BaseType.Double, 13, BaseType.Vector, BaseType.Obj, BaseType.Union, BaseType.Array, BaseType.Vector64, BaseType.MaxBaseType, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, Utf8.REPLACEMENT_BYTE, -9, 26, 27, 28, 29, 30, 31, 32, PublicSuffixDatabase.i, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] f = {45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
    private static final byte[] g = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, BaseType.Float, BaseType.Double, 13, BaseType.Vector, BaseType.Obj, BaseType.Union, BaseType.Array, BaseType.Vector64, BaseType.MaxBaseType, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, PublicSuffixDatabase.i, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, Utf8.REPLACEMENT_BYTE, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    private id() {
    }

    public static String a(byte[] bArr) {
        String str;
        try {
            str = a(bArr, bArr.length);
        } catch (IOException e2) {
            if (!f1174a) {
                throw new AssertionError(e2.getMessage());
            }
            str = null;
        }
        if (f1174a || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    private static byte[] b(byte[] bArr, int i) throws IOException {
        int i2;
        int i3;
        if (bArr == null) {
            throw new NullPointerException("Cannot decode null source array.");
        }
        if (i > bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", Integer.valueOf(bArr.length), 0, Integer.valueOf(i)));
        }
        if (i == 0) {
            return new byte[0];
        }
        if (i < 4) {
            throw new IllegalArgumentException("Base64Util-encoded string must have at least four characters, but length specified was ".concat(String.valueOf(i)));
        }
        byte[] bArr2 = c;
        int i4 = (i * 3) / 4;
        byte[] bArr3 = new byte[i4];
        byte[] bArr4 = new byte[4];
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < i; i7++) {
            byte b2 = bArr[i7];
            byte b3 = bArr2[b2 & 255];
            if (b3 < -5) {
                throw new IOException(String.format("Bad Base64Util input character decimal %d in array position %d", Integer.valueOf(b2 & 255), Integer.valueOf(i7)));
            }
            if (b3 >= -1) {
                int i8 = i6 + 1;
                bArr4[i6] = b2;
                if (i8 <= 3) {
                    i6 = i8;
                } else {
                    if (i5 < 0 || (i2 = i5 + 2) >= i4) {
                        throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(i4), Integer.valueOf(i5)));
                    }
                    byte[] bArr5 = c;
                    byte b4 = bArr4[2];
                    if (b4 == 61) {
                        bArr3[i5] = (byte) ((((bArr5[bArr4[0]] & 255) << 18) | ((bArr5[bArr4[1]] & 255) << 12)) >>> 16);
                        i3 = 1;
                    } else {
                        byte b5 = bArr4[3];
                        if (b5 == 61) {
                            int i9 = ((bArr5[bArr4[0]] & 255) << 18) | ((bArr5[bArr4[1]] & 255) << 12) | ((bArr5[b4] & 255) << 6);
                            bArr3[i5] = (byte) (i9 >>> 16);
                            bArr3[i5 + 1] = (byte) (i9 >>> 8);
                            i3 = 2;
                        } else {
                            int i10 = ((bArr5[bArr4[0]] & 255) << 18) | ((bArr5[bArr4[1]] & 255) << 12) | ((bArr5[b4] & 255) << 6) | (bArr5[b5] & 255);
                            bArr3[i5] = (byte) (i10 >> 16);
                            bArr3[i5 + 1] = (byte) (i10 >> 8);
                            bArr3[i2] = (byte) i10;
                            i3 = 3;
                        }
                    }
                    i5 += i3;
                    if (bArr[i7] == 61) {
                        break;
                    }
                    i6 = 0;
                }
            }
        }
        byte[] bArr6 = new byte[i5];
        System.arraycopy(bArr3, 0, bArr6, 0, i5);
        return bArr6;
    }

    public static byte[] a(String str) throws IOException {
        return b(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.util.zip.GZIPInputStream] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    private static byte[] b(String str) throws IOException {
        byte[] bytes;
        ?? r4;
        ?? r5;
        ByteArrayOutputStream byteArrayOutputStream;
        ?? r52;
        ByteArrayOutputStream byteArrayOutputStream2;
        ByteArrayOutputStream byteArrayOutputStream3;
        ?? r42;
        if (str == null) {
            throw new NullPointerException("Input string was null.");
        }
        try {
            bytes = str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        byte[] b2 = b(bytes, bytes.length);
        if (b2.length >= 4 && 35615 == ((b2[0] & 255) | ((b2[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
            byte[] bArr = new byte[2048];
            ByteArrayOutputStream byteArrayOutputStream4 = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    r42 = new ByteArrayInputStream(b2);
                    try {
                        r52 = new GZIPInputStream(r42);
                        while (true) {
                            try {
                                int read = r52.read(bArr);
                                if (read < 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            } catch (IOException e2) {
                                e = e2;
                                byteArrayOutputStream4 = r42;
                                r52 = r52;
                                try {
                                    e.printStackTrace();
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (Exception unused2) {
                                    }
                                    r42 = byteArrayOutputStream4;
                                    r52.close();
                                    r42.close();
                                    return b2;
                                } catch (Throwable th) {
                                    th = th;
                                    byteArrayOutputStream3 = byteArrayOutputStream4;
                                    byteArrayOutputStream2 = r52;
                                    byteArrayOutputStream4 = byteArrayOutputStream;
                                    r4 = byteArrayOutputStream3;
                                    r5 = byteArrayOutputStream2;
                                    try {
                                        byteArrayOutputStream4.close();
                                    } catch (Exception unused3) {
                                    }
                                    try {
                                        r5.close();
                                    } catch (Exception unused4) {
                                    }
                                    try {
                                        r4.close();
                                        throw th;
                                    } catch (Exception unused5) {
                                        throw th;
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                byteArrayOutputStream4 = r52;
                                byteArrayOutputStream2 = byteArrayOutputStream4;
                                byteArrayOutputStream3 = r42;
                                byteArrayOutputStream4 = byteArrayOutputStream;
                                r4 = byteArrayOutputStream3;
                                r5 = byteArrayOutputStream2;
                                byteArrayOutputStream4.close();
                                r5.close();
                                r4.close();
                                throw th;
                            }
                        }
                        b2 = byteArrayOutputStream.toByteArray();
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused6) {
                        }
                    } catch (IOException e3) {
                        e = e3;
                        r52 = 0;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (IOException e4) {
                    e = e4;
                    r52 = 0;
                } catch (Throwable th4) {
                    th = th4;
                    r42 = 0;
                }
            } catch (IOException e5) {
                e = e5;
                byteArrayOutputStream = null;
                r52 = 0;
            } catch (Throwable th5) {
                th = th5;
                r4 = 0;
                r5 = 0;
                byteArrayOutputStream4.close();
                r5.close();
                r4.close();
                throw th;
            }
            try {
                r52.close();
            } catch (Exception unused7) {
            }
            try {
                r42.close();
            } catch (Exception unused8) {
            }
        }
        return b2;
    }

    private static byte[] a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        byte[] bArr3 = b;
        int i4 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 2 ? (bArr[i + 2] << 24) >>> 24 : 0);
        if (i2 == 1) {
            bArr2[i3] = bArr3[i4 >>> 18];
            bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
            bArr2[i3 + 2] = 61;
            bArr2[i3 + 3] = 61;
            return bArr2;
        }
        if (i2 == 2) {
            bArr2[i3] = bArr3[i4 >>> 18];
            bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
            bArr2[i3 + 2] = bArr3[(i4 >>> 6) & 63];
            bArr2[i3 + 3] = 61;
            return bArr2;
        }
        if (i2 != 3) {
            return bArr2;
        }
        bArr2[i3] = bArr3[i4 >>> 18];
        bArr2[i3 + 1] = bArr3[(i4 >>> 12) & 63];
        bArr2[i3 + 2] = bArr3[(i4 >>> 6) & 63];
        bArr2[i3 + 3] = bArr3[i4 & 63];
        return bArr2;
    }

    private static String a(byte[] bArr, int i) throws IOException {
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Cannot have length offset: ".concat(String.valueOf(i)));
        }
        if (i > bArr.length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", 0, Integer.valueOf(i), Integer.valueOf(bArr.length)));
        }
        int i2 = ((i / 3) * 4) + (i % 3 > 0 ? 4 : 0);
        byte[] bArr2 = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i - 2) {
            a(bArr, i3, 3, bArr2, i4);
            i3 += 3;
            i4 += 4;
        }
        if (i3 < i) {
            a(bArr, i3, i - i3, bArr2, i4);
            i4 += 4;
        }
        if (i4 <= i2 - 1) {
            byte[] bArr3 = new byte[i4];
            System.arraycopy(bArr2, 0, bArr3, 0, i4);
            bArr2 = bArr3;
        }
        try {
            return new String(bArr2, "US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr2);
        }
    }
}
