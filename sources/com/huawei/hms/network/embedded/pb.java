package com.huawei.hms.network.embedded;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes9.dex */
public final class pb extends AbstractList<eb> implements RandomAccess {

    /* renamed from: a, reason: collision with root package name */
    public final eb[] f5423a;
    public final int[] b;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f5423a.length;
    }

    @Override // java.util.AbstractList, java.util.List
    public eb get(int i) {
        return this.f5423a[i];
    }

    public static void a(long j, bb bbVar, int i, List<eb> list, int i2, int i3, List<Integer> list2) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        bb bbVar2;
        if (i2 >= i3) {
            throw new AssertionError();
        }
        for (int i9 = i2; i9 < i3; i9++) {
            if (list.get(i9).j() < i) {
                throw new AssertionError();
            }
        }
        eb ebVar = list.get(i2);
        eb ebVar2 = list.get(i3 - 1);
        if (i == ebVar.j()) {
            int i10 = i2 + 1;
            i5 = i10;
            i4 = list2.get(i2).intValue();
            ebVar = list.get(i10);
        } else {
            i4 = -1;
            i5 = i2;
        }
        if (ebVar.a(i) == ebVar2.a(i)) {
            int min = Math.min(ebVar.j(), ebVar2.j());
            int i11 = 0;
            for (int i12 = i; i12 < min && ebVar.a(i12) == ebVar2.a(i12); i12++) {
                i11++;
            }
            long a2 = 1 + j + a(bbVar) + 2 + i11;
            bbVar.writeInt(-i11);
            bbVar.writeInt(i4);
            int i13 = i;
            while (true) {
                i6 = i + i11;
                if (i13 >= i6) {
                    break;
                }
                bbVar.writeInt(ebVar.a(i13) & 255);
                i13++;
            }
            if (i5 + 1 == i3) {
                if (i6 != list.get(i5).j()) {
                    throw new AssertionError();
                }
                bbVar.writeInt(list2.get(i5).intValue());
                return;
            } else {
                bb bbVar3 = new bb();
                bbVar.writeInt((int) ((a(bbVar3) + a2) * (-1)));
                a(a2, bbVar3, i6, list, i5, i3, list2);
                bbVar.write(bbVar3, bbVar3.B());
                return;
            }
        }
        int i14 = 1;
        for (int i15 = i5 + 1; i15 < i3; i15++) {
            if (list.get(i15 - 1).a(i) != list.get(i15).a(i)) {
                i14++;
            }
        }
        long a3 = j + a(bbVar) + 2 + (i14 * 2);
        bbVar.writeInt(i14);
        bbVar.writeInt(i4);
        for (int i16 = i5; i16 < i3; i16++) {
            byte a4 = list.get(i16).a(i);
            if (i16 == i5 || a4 != list.get(i16 - 1).a(i)) {
                bbVar.writeInt(a4 & 255);
            }
        }
        bb bbVar4 = new bb();
        int i17 = i5;
        while (i17 < i3) {
            byte a5 = list.get(i17).a(i);
            int i18 = i17 + 1;
            int i19 = i18;
            while (true) {
                if (i19 >= i3) {
                    i7 = i3;
                    break;
                } else {
                    if (a5 != list.get(i19).a(i)) {
                        i7 = i19;
                        break;
                    }
                    i19++;
                }
            }
            if (i18 == i7 && i + 1 == list.get(i17).j()) {
                bbVar.writeInt(list2.get(i17).intValue());
                i8 = i7;
                bbVar2 = bbVar4;
            } else {
                bbVar.writeInt((int) ((a(bbVar4) + a3) * (-1)));
                i8 = i7;
                bbVar2 = bbVar4;
                a(a3, bbVar4, i + 1, list, i17, i7, list2);
            }
            bbVar4 = bbVar2;
            i17 = i8;
        }
        bb bbVar5 = bbVar4;
        bbVar.write(bbVar5, bbVar5.B());
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b7, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hms.network.embedded.pb a(com.huawei.hms.network.embedded.eb... r11) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.pb.a(com.huawei.hms.network.embedded.eb[]):com.huawei.hms.network.embedded.pb");
    }

    public static int a(bb bbVar) {
        return (int) (bbVar.B() / 4);
    }

    public pb(eb[] ebVarArr, int[] iArr) {
        this.f5423a = ebVarArr;
        this.b = iArr;
    }
}
