package com.alipay.sdk.m.l0;

import java.io.UnsupportedEncodingException;

/* loaded from: classes7.dex */
public class b {
    public static final /* synthetic */ boolean b = true;

    public static abstract class a {

        /* renamed from: a, reason: collision with root package name */
        public byte[] f864a;
        public int b;
    }

    /* renamed from: com.alipay.sdk.m.l0.b$b, reason: collision with other inner class name */
    public static class C0010b extends a {
        public static final /* synthetic */ boolean d = true;
        public int f;
        public final byte[] g;
        public final boolean h;
        public int i;
        public final boolean j;
        public final boolean n;
        public final byte[] o;
        public static final byte[] e = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        public static final byte[] c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

        public C0010b(int i, byte[] bArr) {
            this.f864a = bArr;
            this.j = (i & 1) == 0;
            boolean z = (i & 2) == 0;
            this.h = z;
            this.n = (i & 4) != 0;
            this.o = (i & 8) == 0 ? e : c;
            this.g = new byte[2];
            this.f = 0;
            this.i = z ? 19 : -1;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0092  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00e5 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:46:0x01b7  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x01c4 A[ADDED_TO_REGION] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean b(byte[] r19, int r20, int r21, boolean r22) {
            /*
                Method dump skipped, instructions count: 504
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.m.l0.b.C0010b.b(byte[], int, int, boolean):boolean");
        }
    }

    public static class c extends a {
        public int d;
        public int g;
        public final int[] j;
        public static final int[] e = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        public static final int[] c = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        public c(int i, byte[] bArr) {
            this.f864a = bArr;
            this.j = (i & 8) == 0 ? e : c;
            this.d = 0;
            this.g = 0;
        }

        public boolean b(byte[] bArr, int i, int i2, boolean z) {
            int i3 = this.d;
            if (i3 == 6) {
                return false;
            }
            int i4 = i2 + i;
            int i5 = this.g;
            byte[] bArr2 = this.f864a;
            int[] iArr = this.j;
            int i6 = 0;
            int i7 = i5;
            int i8 = i3;
            int i9 = i;
            while (i9 < i4) {
                if (i8 == 0) {
                    while (true) {
                        int i10 = i9 + 4;
                        if (i10 > i4 || (i7 = (iArr[bArr[i9] & 255] << 18) | (iArr[bArr[i9 + 1] & 255] << 12) | (iArr[bArr[i9 + 2] & 255] << 6) | iArr[bArr[i9 + 3] & 255]) < 0) {
                            break;
                        }
                        bArr2[i6 + 2] = (byte) i7;
                        bArr2[i6 + 1] = (byte) (i7 >> 8);
                        bArr2[i6] = (byte) (i7 >> 16);
                        i6 += 3;
                        i9 = i10;
                    }
                    if (i9 >= i4) {
                        break;
                    }
                }
                int i11 = iArr[bArr[i9] & 255];
                if (i8 != 0) {
                    if (i8 == 1) {
                        if (i11 < 0) {
                            if (i11 != -1) {
                                this.d = 6;
                                return false;
                            }
                        }
                        i11 |= i7 << 6;
                    } else if (i8 == 2) {
                        if (i11 < 0) {
                            if (i11 == -2) {
                                bArr2[i6] = (byte) (i7 >> 4);
                                i6++;
                                i8 = 4;
                            } else if (i11 != -1) {
                                this.d = 6;
                                return false;
                            }
                        }
                        i11 |= i7 << 6;
                    } else if (i8 != 3) {
                        if (i8 != 4) {
                            if (i8 == 5 && i11 != -1) {
                                this.d = 6;
                                return false;
                            }
                        } else if (i11 == -2) {
                            i8++;
                        } else if (i11 != -1) {
                            this.d = 6;
                            return false;
                        }
                    } else if (i11 >= 0) {
                        int i12 = (i7 << 6) | i11;
                        bArr2[i6 + 2] = (byte) i12;
                        bArr2[i6 + 1] = (byte) (i12 >> 8);
                        bArr2[i6] = (byte) (i12 >> 16);
                        i6 += 3;
                        i7 = i12;
                        i8 = 0;
                    } else if (i11 == -2) {
                        bArr2[i6 + 1] = (byte) (i7 >> 2);
                        bArr2[i6] = (byte) (i7 >> 10);
                        i6 += 2;
                        i8 = 5;
                    } else if (i11 != -1) {
                        this.d = 6;
                        return false;
                    }
                    i8++;
                    i7 = i11;
                } else {
                    if (i11 < 0) {
                        if (i11 != -1) {
                            this.d = 6;
                            return false;
                        }
                    }
                    i8++;
                    i7 = i11;
                }
                i9++;
            }
            if (!z) {
                this.d = i8;
                this.g = i7;
                this.b = i6;
                return true;
            }
            if (i8 == 1) {
                this.d = 6;
                return false;
            }
            if (i8 == 2) {
                bArr2[i6] = (byte) (i7 >> 4);
                i6++;
            } else if (i8 == 3) {
                int i13 = i6 + 1;
                bArr2[i6] = (byte) (i7 >> 10);
                i6 += 2;
                bArr2[i13] = (byte) (i7 >> 2);
            } else if (i8 == 4) {
                this.d = 6;
                return false;
            }
            this.d = i8;
            this.b = i6;
            return true;
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        return c(bArr, 0, bArr.length, i);
    }

    public static String c(byte[] bArr, int i) {
        try {
            return new String(d(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] d(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        C0010b c0010b = new C0010b(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!c0010b.j) {
            int i5 = i2 % 3;
            if (i5 == 1) {
                i4 += 2;
            } else if (i5 == 2) {
                i4 += 3;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (c0010b.h && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (c0010b.n ? 2 : 1);
        }
        c0010b.f864a = new byte[i4];
        c0010b.b(bArr, i, i2, true);
        if (b || c0010b.b == i4) {
            return c0010b.f864a;
        }
        throw new AssertionError();
    }

    public static byte[] c(byte[] bArr, int i, int i2, int i3) {
        c cVar = new c(i3, new byte[(i2 * 3) / 4]);
        if (!cVar.b(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        int i4 = cVar.b;
        byte[] bArr2 = cVar.f864a;
        if (i4 == bArr2.length) {
            return bArr2;
        }
        byte[] bArr3 = new byte[i4];
        System.arraycopy(bArr2, 0, bArr3, 0, i4);
        return bArr3;
    }
}
