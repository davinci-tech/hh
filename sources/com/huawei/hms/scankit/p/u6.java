package com.huawei.hms.scankit.p;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.operation.utils.Constants;

/* loaded from: classes9.dex */
public class u6 implements Parcelable {
    public static final Parcelable.Creator<u6> CREATOR = new a();

    /* renamed from: a, reason: collision with root package name */
    private final float f5892a;
    private final float b;
    private int c;
    private boolean d;

    class a implements Parcelable.Creator<u6> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public u6 createFromParcel(Parcel parcel) {
            return new u6(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public u6[] newArray(int i) {
            return new u6[i];
        }

        a() {
        }
    }

    public u6(float f, float f2, int i) {
        this.d = false;
        this.f5892a = f;
        this.b = f2;
        this.c = i;
    }

    public int a() {
        return this.c;
    }

    public final float b() {
        return this.f5892a;
    }

    public final float c() {
        return this.b;
    }

    public boolean d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof u6) {
            u6 u6Var = (u6) obj;
            if (Math.abs(this.f5892a - u6Var.f5892a) < 1.0E-4d && Math.abs(this.b - u6Var.b) < 1.0E-4d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f5892a) * 31) + Float.floatToIntBits(this.b);
    }

    public final String toString() {
        return Constants.LEFT_BRACKET_ONLY + this.f5892a + ',' + this.b + com.huawei.hms.network.embedded.g4.l;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f5892a);
        parcel.writeFloat(this.b);
    }

    public static void a(u6[] u6VarArr) {
        float a2 = a(u6VarArr[0], u6VarArr[1]);
        float a3 = a(u6VarArr[1], u6VarArr[2]);
        float a4 = a(u6VarArr[0], u6VarArr[2]);
        int[] a5 = a(a3, a2, a4);
        int i = a5[0];
        int i2 = a5[1];
        int i3 = a5[2];
        u6 u6Var = u6VarArr[i];
        u6 u6Var2 = u6VarArr[i2];
        u6 u6Var3 = u6VarArr[i3];
        float[] fArr = {a3, a4, a2};
        if (r3.j % 2 == 0 && fArr[i2] / fArr[i] < 1.1d) {
            u6Var = u6VarArr[i];
            u6Var2 = u6VarArr[i2];
            u6Var3 = u6VarArr[i3];
        }
        if (a(u6Var2, u6Var, u6Var3) < 0.0f) {
            u6 u6Var4 = u6Var3;
            u6Var3 = u6Var2;
            u6Var2 = u6Var4;
        }
        u6VarArr[0] = u6Var2;
        u6VarArr[1] = u6Var;
        u6VarArr[2] = u6Var3;
    }

    public u6(float f, float f2) {
        this.c = 0;
        this.d = false;
        this.f5892a = f;
        this.b = f2;
    }

    public u6(float f, float f2, boolean z) {
        this.c = 0;
        this.f5892a = f;
        this.b = f2;
        this.d = z;
    }

    public static float a(u6 u6Var, u6 u6Var2) {
        return s4.a(u6Var.f5892a, u6Var.b, u6Var2.f5892a, u6Var2.b);
    }

    private static float a(u6 u6Var, u6 u6Var2, u6 u6Var3) {
        float f = u6Var2.f5892a;
        float f2 = u6Var2.b;
        return ((u6Var3.f5892a - f) * (u6Var.b - f2)) - ((u6Var3.b - f2) * (u6Var.f5892a - f));
    }

    protected u6(Parcel parcel) {
        this.c = 0;
        this.d = false;
        this.f5892a = parcel.readFloat();
        this.b = parcel.readFloat();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0022, code lost:
    
        r5 = r2;
        r2 = 2;
        r1 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x001b, code lost:
    
        if (r6 > r7) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000d, code lost:
    
        if (r7 > r8) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001d, code lost:
    
        r5 = r3;
        r3 = 2;
        r1 = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int[] a(float r6, float r7, float r8) {
        /*
            int r0 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            r1 = 2
            r2 = 0
            r3 = 1
            if (r0 < 0) goto L10
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 < 0) goto L10
            int r6 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r6 <= 0) goto L1d
            goto L22
        L10:
            int r4 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r4 < 0) goto L26
            int r7 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r7 < 0) goto L26
            r5 = r3
            r3 = r2
            r2 = r5
            if (r0 <= 0) goto L22
        L1d:
            r5 = r3
            r3 = r1
            r1 = r2
        L20:
            r2 = r5
            goto L2e
        L22:
            r5 = r2
            r2 = r1
            r1 = r5
            goto L2e
        L26:
            int r6 = (r6 > r6 ? 1 : (r6 == r6 ? 0 : -1))
            if (r6 <= 0) goto L2b
            goto L2e
        L2b:
            r5 = r3
            r3 = r2
            goto L20
        L2e:
            int[] r6 = new int[]{r1, r2, r3}
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.u6.a(float, float, float):int[]");
    }
}
