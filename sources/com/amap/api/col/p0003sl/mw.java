package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public class mw {

    /* renamed from: a, reason: collision with root package name */
    static final /* synthetic */ boolean f1342a = true;

    static abstract class a {

        /* renamed from: a, reason: collision with root package name */
        public byte[] f1343a;
        public int b;

        a() {
        }
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    private static byte[] a(byte[] bArr, int i) {
        b bVar = new b(new byte[(i * 3) / 4]);
        if (!bVar.a(bArr, i)) {
            throw new IllegalArgumentException("bad base-64");
        }
        if (bVar.b == bVar.f1343a.length) {
            return bVar.f1343a;
        }
        byte[] bArr2 = new byte[bVar.b];
        System.arraycopy(bVar.f1343a, 0, bArr2, 0, bVar.b);
        return bArr2;
    }

    static final class b extends a {
        private static final int[] c = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] d = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private int e;
        private int f;
        private final int[] g;

        public b(byte[] bArr) {
            this.f1343a = bArr;
            this.g = c;
            this.e = 0;
            this.f = 0;
        }

        public final boolean a(byte[] bArr, int i) {
            int i2 = this.e;
            if (i2 == 6) {
                return false;
            }
            int i3 = this.f;
            byte[] bArr2 = this.f1343a;
            int[] iArr = this.g;
            int i4 = 0;
            int i5 = 0;
            while (i5 < i) {
                if (i2 == 0) {
                    while (true) {
                        int i6 = i5 + 4;
                        if (i6 > i || (i3 = (iArr[bArr[i5] & 255] << 18) | (iArr[bArr[i5 + 1] & 255] << 12) | (iArr[bArr[i5 + 2] & 255] << 6) | iArr[bArr[i5 + 3] & 255]) < 0) {
                            break;
                        }
                        bArr2[i4 + 2] = (byte) i3;
                        bArr2[i4 + 1] = (byte) (i3 >> 8);
                        bArr2[i4] = (byte) (i3 >> 16);
                        i4 += 3;
                        i5 = i6;
                    }
                    if (i5 >= i) {
                        break;
                    }
                }
                int i7 = iArr[bArr[i5] & 255];
                if (i2 != 0) {
                    if (i2 == 1) {
                        if (i7 < 0) {
                            if (i7 != -1) {
                                this.e = 6;
                                return false;
                            }
                        }
                        i3 = (i3 << 6) | i7;
                    } else if (i2 == 2) {
                        if (i7 < 0) {
                            if (i7 == -2) {
                                bArr2[i4] = (byte) (i3 >> 4);
                                i4++;
                                i2 = 4;
                            } else if (i7 != -1) {
                                this.e = 6;
                                return false;
                            }
                        }
                        i3 = (i3 << 6) | i7;
                    } else if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 == 5 && i7 != -1) {
                                this.e = 6;
                                return false;
                            }
                        } else if (i7 != -2) {
                            if (i7 != -1) {
                                this.e = 6;
                                return false;
                            }
                        }
                    } else if (i7 >= 0) {
                        int i8 = (i3 << 6) | i7;
                        bArr2[i4 + 2] = (byte) i8;
                        bArr2[i4 + 1] = (byte) (i8 >> 8);
                        bArr2[i4] = (byte) (i8 >> 16);
                        i4 += 3;
                        i3 = i8;
                        i2 = 0;
                    } else if (i7 == -2) {
                        bArr2[i4 + 1] = (byte) (i3 >> 2);
                        bArr2[i4] = (byte) (i3 >> 10);
                        i4 += 2;
                        i2 = 5;
                    } else if (i7 != -1) {
                        this.e = 6;
                        return false;
                    }
                    i2++;
                } else if (i7 >= 0) {
                    i2++;
                    i3 = i7;
                } else if (i7 != -1) {
                    this.e = 6;
                    return false;
                }
                i5++;
            }
            if (i2 == 1) {
                this.e = 6;
                return false;
            }
            if (i2 == 2) {
                bArr2[i4] = (byte) (i3 >> 4);
                i4++;
            } else if (i2 == 3) {
                int i9 = i4 + 1;
                bArr2[i4] = (byte) (i3 >> 10);
                i4 += 2;
                bArr2[i9] = (byte) (i3 >> 2);
            } else if (i2 == 4) {
                this.e = 6;
                return false;
            }
            this.e = i2;
            this.b = i4;
            return true;
        }
    }

    private mw() {
    }
}
