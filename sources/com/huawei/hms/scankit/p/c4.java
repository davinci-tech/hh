package com.huawei.hms.scankit.p;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes4.dex */
public final class c4 {
    static final String[] b = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    static final int[][] c = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    private static final int[][] d;
    static final int[][] e;

    /* renamed from: a, reason: collision with root package name */
    private final byte[] f5754a;

    static {
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 5, 256);
        d = iArr;
        iArr[0][32] = 1;
        for (int i = 65; i <= 90; i++) {
            d[0][i] = i - 63;
        }
        d[1][32] = 1;
        for (int i2 = 97; i2 <= 122; i2++) {
            d[1][i2] = i2 - 95;
        }
        d[2][32] = 1;
        for (int i3 = 48; i3 <= 57; i3++) {
            d[2][i3] = i3 - 46;
        }
        int[] iArr2 = d[2];
        iArr2[44] = 12;
        iArr2[46] = 13;
        int[] iArr3 = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (int i4 = 0; i4 < 28; i4++) {
            d[3][iArr3[i4]] = i4;
        }
        int[] iArr4 = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (int i5 = 0; i5 < 31; i5++) {
            int i6 = iArr4[i5];
            if (i6 > 0) {
                d[4][i6] = i5;
            }
        }
        int[][] iArr5 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 6, 6);
        e = iArr5;
        for (int[] iArr6 : iArr5) {
            Arrays.fill(iArr6, -1);
        }
        int[][] iArr7 = e;
        if (w7.a(iArr7, 0) && w7.a(iArr7[0], 4)) {
            iArr7[0][4] = 0;
        }
        if (w7.a(iArr7, 1) && w7.a(iArr7[1], 4)) {
            iArr7[1][4] = 0;
        }
        if (w7.a(iArr7, 1) && w7.a(iArr7[1], 0)) {
            iArr7[1][0] = 28;
        }
        if (w7.a(iArr7, 3) && w7.a(iArr7[3], 4)) {
            iArr7[3][4] = 0;
        }
        if (w7.a(iArr7, 2) && w7.a(iArr7[2], 4)) {
            iArr7[2][4] = 0;
        }
        if (w7.a(iArr7, 2) && w7.a(iArr7[2], 0)) {
            iArr7[2][0] = 15;
        }
    }

    public c4(byte[] bArr) {
        this.f5754a = bArr;
    }

    public r a() {
        int i;
        Collection<b7> singletonList = Collections.singletonList(b7.e);
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f5754a;
            if (i2 >= bArr.length) {
                return ((b7) Collections.min(singletonList, new a())).a(this.f5754a);
            }
            int i3 = i2 + 1;
            byte b2 = i3 < bArr.length ? bArr[i3] : (byte) 0;
            byte b3 = bArr[i2];
            if (b3 == 13) {
                if (b2 == 10) {
                    i = 2;
                }
                i = 0;
            } else if (b3 == 44) {
                if (b2 == 32) {
                    i = 4;
                }
                i = 0;
            } else if (b3 != 46) {
                if (b3 == 58 && b2 == 32) {
                    i = 5;
                }
                i = 0;
            } else {
                if (b2 == 32) {
                    i = 3;
                }
                i = 0;
            }
            if (i > 0) {
                singletonList = a(singletonList, i2, i);
                i2 = i3;
            } else {
                singletonList = a(singletonList, i2);
            }
            i2++;
        }
    }

    private Collection<b7> a(Iterable<b7> iterable, int i) {
        LinkedList linkedList = new LinkedList();
        Iterator<b7> it = iterable.iterator();
        while (it.hasNext()) {
            a(it.next(), i, linkedList);
        }
        return a(linkedList);
    }

    private void a(b7 b7Var, int i, Collection<b7> collection) {
        if (w7.a(this.f5754a, i)) {
            char c2 = (char) (this.f5754a[i] & 255);
            int[][] iArr = d;
            boolean z = w7.a(iArr, b7Var.c()) && w7.a(iArr[b7Var.c()], (int) c2) && iArr[b7Var.c()][c2] > 0;
            b7 b7Var2 = null;
            for (int i2 = 0; i2 <= 4; i2++) {
                int[][] iArr2 = d;
                int i3 = (w7.a(iArr2, i2) && w7.a(iArr2[i2], (int) c2)) ? iArr2[i2][c2] : 0;
                if (i3 > 0) {
                    if (b7Var2 == null) {
                        b7Var2 = b7Var.b(i);
                    }
                    if (!z || i2 == b7Var.c() || i2 == 2) {
                        collection.add(b7Var2.a(i2, i3));
                    }
                    if (!z && e[b7Var.c()][i2] >= 0) {
                        collection.add(b7Var2.b(i2, i3));
                    }
                }
            }
            int[][] iArr3 = d;
            if (w7.a(iArr3, b7Var.c()) && w7.a(iArr3[b7Var.c()], (int) c2)) {
                if (b7Var.a() > 0 || iArr3[b7Var.c()][c2] == 0) {
                    collection.add(b7Var.a(i));
                }
            }
        }
    }

    private static Collection<b7> a(Iterable<b7> iterable, int i, int i2) {
        LinkedList linkedList = new LinkedList();
        Iterator<b7> it = iterable.iterator();
        while (it.hasNext()) {
            a(it.next(), i, i2, linkedList);
        }
        return a(linkedList);
    }

    private static void a(b7 b7Var, int i, int i2, Collection<b7> collection) {
        b7 b2 = b7Var.b(i);
        collection.add(b2.a(4, i2));
        if (b7Var.c() != 4) {
            collection.add(b2.b(4, i2));
        }
        if (i2 == 3 || i2 == 4) {
            collection.add(b2.a(2, 16 - i2).a(2, 1));
        }
        if (b7Var.a() > 0) {
            collection.add(b7Var.a(i).a(i + 1));
        }
    }

    private static Collection<b7> a(Iterable<b7> iterable) {
        LinkedList linkedList = new LinkedList();
        for (b7 b7Var : iterable) {
            Iterator it = linkedList.iterator();
            while (true) {
                if (it.hasNext()) {
                    b7 b7Var2 = (b7) it.next();
                    if (b7Var2.a(b7Var)) {
                        break;
                    }
                    if (b7Var.a(b7Var2)) {
                        it.remove();
                    }
                } else {
                    linkedList.add(b7Var);
                    break;
                }
            }
        }
        return linkedList;
    }

    static class a<State> implements Comparator<b7> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(b7 b7Var, b7 b7Var2) {
            return b7Var.b() - b7Var2.b();
        }

        a() {
        }
    }
}
