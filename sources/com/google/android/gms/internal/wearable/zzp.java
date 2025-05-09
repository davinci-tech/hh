package com.google.android.gms.internal.wearable;

/* loaded from: classes2.dex */
public final class zzp implements Cloneable {
    private static final zzq zzhe = new zzq();
    private int mSize;
    private boolean zzhf;
    private int[] zzhg;
    private zzq[] zzhh;

    zzp() {
        this(10);
    }

    private zzp(int i) {
        this.zzhf = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzhg = new int[idealIntArraySize];
        this.zzhh = new zzq[idealIntArraySize];
        this.mSize = 0;
    }

    final zzq zzo(int i) {
        zzq zzqVar;
        int zzq = zzq(i);
        if (zzq < 0 || (zzqVar = this.zzhh[zzq]) == zzhe) {
            return null;
        }
        return zzqVar;
    }

    final void zza(int i, zzq zzqVar) {
        int zzq = zzq(i);
        if (zzq >= 0) {
            this.zzhh[zzq] = zzqVar;
            return;
        }
        int i2 = ~zzq;
        int i3 = this.mSize;
        if (i2 < i3) {
            zzq[] zzqVarArr = this.zzhh;
            if (zzqVarArr[i2] == zzhe) {
                this.zzhg[i2] = i;
                zzqVarArr[i2] = zzqVar;
                return;
            }
        }
        if (i3 >= this.zzhg.length) {
            int idealIntArraySize = idealIntArraySize(i3 + 1);
            int[] iArr = new int[idealIntArraySize];
            zzq[] zzqVarArr2 = new zzq[idealIntArraySize];
            int[] iArr2 = this.zzhg;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            zzq[] zzqVarArr3 = this.zzhh;
            System.arraycopy(zzqVarArr3, 0, zzqVarArr2, 0, zzqVarArr3.length);
            this.zzhg = iArr;
            this.zzhh = zzqVarArr2;
        }
        int i4 = this.mSize - i2;
        if (i4 != 0) {
            int[] iArr3 = this.zzhg;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4);
            zzq[] zzqVarArr4 = this.zzhh;
            System.arraycopy(zzqVarArr4, i2, zzqVarArr4, i5, this.mSize - i2);
        }
        this.zzhg[i2] = i;
        this.zzhh[i2] = zzqVar;
        this.mSize++;
    }

    final int size() {
        return this.mSize;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    final zzq zzp(int i) {
        return this.zzhh[i];
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzp)) {
            return false;
        }
        zzp zzpVar = (zzp) obj;
        int i = this.mSize;
        if (i != zzpVar.mSize) {
            return false;
        }
        int[] iArr = this.zzhg;
        int[] iArr2 = zzpVar.zzhg;
        int i2 = 0;
        while (true) {
            if (i2 < i) {
                if (iArr[i2] != iArr2[i2]) {
                    break;
                }
                i2++;
            } else {
                zzq[] zzqVarArr = this.zzhh;
                zzq[] zzqVarArr2 = zzpVar.zzhh;
                int i3 = this.mSize;
                for (int i4 = 0; i4 < i3; i4++) {
                    if (zzqVarArr[i4].equals(zzqVarArr2[i4])) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzhg[i2]) * 31) + this.zzhh[i2].hashCode();
        }
        return i;
    }

    private static int idealIntArraySize(int i) {
        int i2 = i << 2;
        int i3 = 4;
        while (true) {
            if (i3 >= 32) {
                break;
            }
            int i4 = (1 << i3) - 12;
            if (i2 <= i4) {
                i2 = i4;
                break;
            }
            i3++;
        }
        return i2 / 4;
    }

    private final int zzq(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzhg[i4];
            if (i5 < i) {
                i3 = i4 + 1;
            } else {
                if (i5 <= i) {
                    return i4;
                }
                i2 = i4 - 1;
            }
        }
        return ~i3;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzp zzpVar = new zzp(i);
        System.arraycopy(this.zzhg, 0, zzpVar.zzhg, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            zzq zzqVar = this.zzhh[i2];
            if (zzqVar != null) {
                zzpVar.zzhh[i2] = (zzq) zzqVar.clone();
            }
        }
        zzpVar.mSize = i;
        return zzpVar;
    }
}
