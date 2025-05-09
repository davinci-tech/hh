package com.huawei.hms.network.embedded;

import androidx.core.view.PointerIconCompat;
import com.google.flatbuffers.reflection.BaseType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public class aa {
    public static final int[] b = {8184, 8388568, 268435426, 268435427, 268435428, 268435429, 268435430, 268435431, 268435432, 16777194, 1073741820, 268435433, 268435434, 1073741821, 268435435, 268435436, 268435437, 268435438, 268435439, 268435440, 268435441, 268435442, 1073741822, 268435443, 268435444, 268435445, 268435446, 268435447, 268435448, 268435449, 268435450, 268435451, 20, 1016, 1017, 4090, 8185, 21, 248, 2042, 1018, 1019, 249, 2043, 250, 22, 23, 24, 0, 1, 2, 25, 26, 27, 28, 29, 30, 31, 92, 251, 32764, 32, 4091, PointerIconCompat.TYPE_GRAB, 8186, 33, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 252, 115, 253, 8187, 524272, 8188, 16380, 34, 32765, 3, 35, 4, 36, 5, 37, 38, 39, 6, 116, 117, 40, 41, 42, 7, 43, 118, 44, 8, 9, 45, 119, 120, 121, 122, 123, 32766, 2044, 16381, 8189, 268435452, 1048550, 4194258, 1048551, 1048552, 4194259, 4194260, 4194261, 8388569, 4194262, 8388570, 8388571, 8388572, 8388573, 8388574, 16777195, 8388575, 16777196, 16777197, 4194263, 8388576, 16777198, 8388577, 8388578, 8388579, 8388580, 2097116, 4194264, 8388581, 4194265, 8388582, 8388583, 16777199, 4194266, 2097117, 1048553, 4194267, 4194268, 8388584, 8388585, 2097118, 8388586, 4194269, 4194270, 16777200, 2097119, 4194271, 8388587, 8388588, 2097120, 2097121, 4194272, 2097122, 8388589, 4194273, 8388590, 8388591, 1048554, 4194274, 4194275, 4194276, 8388592, 4194277, 4194278, 8388593, 67108832, 67108833, 1048555, 524273, 4194279, 8388594, 4194280, 33554412, 67108834, 67108835, 67108836, 134217694, 134217695, 67108837, 16777201, 33554413, 524274, 2097123, 67108838, 134217696, 134217697, 67108839, 134217698, 16777202, 2097124, 2097125, 67108840, 67108841, 268435453, 134217699, 134217700, 134217701, 1048556, 16777203, 1048557, 2097126, 4194281, 2097127, 2097128, 8388595, 4194282, 4194283, 33554414, 33554415, 16777204, 16777205, 67108842, 8388596, 67108843, 134217702, 67108844, 67108845, 134217703, 134217704, 134217705, 134217706, 134217707, 268435454, 134217708, 134217709, 134217710, 134217711, 134217712, 67108846};
    public static final byte[] c = {13, 23, 28, 28, 28, 28, 28, 28, 28, 24, 30, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 28, 6, 10, 10, BaseType.Double, 13, 6, 8, BaseType.Float, 10, 10, 8, BaseType.Float, 8, 6, 6, 6, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 8, BaseType.Obj, 6, BaseType.Double, 10, 13, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 13, BaseType.MaxBaseType, 13, BaseType.Vector, 6, BaseType.Obj, 5, 6, 5, 6, 5, 6, 6, 6, 5, 7, 7, 6, 6, 6, 5, 6, 7, 6, 5, 5, 6, 7, 7, 7, 7, 7, BaseType.Obj, BaseType.Float, BaseType.Vector, 13, 28, 20, 22, 20, 20, 22, 22, 22, 23, 22, 23, 23, 23, 23, 23, 24, 23, 24, 24, 22, 23, 24, 23, 23, 23, 23, 21, 22, 23, 22, 23, 23, 24, 22, 21, 20, 22, 22, 23, 23, 21, 23, 22, 22, 24, 21, 22, 23, 23, 21, 21, 22, 21, 23, 22, 23, 23, 20, 22, 22, 22, 23, 22, 22, 23, 26, 26, 20, BaseType.MaxBaseType, 22, 23, 22, 25, 26, 26, 26, 27, 27, 26, 24, 25, BaseType.MaxBaseType, 21, 26, 27, 27, 26, 27, 24, 21, 21, 26, 26, 28, 27, 27, 27, 20, 24, 20, 21, 22, 21, 21, 23, 22, 22, 25, 25, 24, 24, 26, 23, 26, 27, 26, 26, 27, 27, 27, 27, 27, 28, 27, 27, 27, 27, 27, 26};
    public static final aa d = new aa();

    /* renamed from: a, reason: collision with root package name */
    public final a f5161a = new a();

    public byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a aVar = this.f5161a;
        int i = 0;
        int i2 = 0;
        for (byte b2 : bArr) {
            i2 = (i2 << 8) | (b2 & 255);
            i += 8;
            while (i >= 8) {
                aVar = aVar.f5162a[(i2 >>> (i - 8)) & 255];
                if (aVar.f5162a == null) {
                    byteArrayOutputStream.write(aVar.b);
                    i -= aVar.c;
                    aVar = this.f5161a;
                } else {
                    i -= 8;
                }
            }
        }
        while (i > 0) {
            a aVar2 = aVar.f5162a[(i2 << (8 - i)) & 255];
            if (aVar2.f5162a != null || aVar2.c > i) {
                break;
            }
            byteArrayOutputStream.write(aVar2.b);
            i -= aVar2.c;
            aVar = this.f5161a;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public void a(eb ebVar, cb cbVar) throws IOException {
        long j = 0;
        int i = 0;
        for (int i2 = 0; i2 < ebVar.j(); i2++) {
            int a2 = ebVar.a(i2) & 255;
            int i3 = b[a2];
            byte b2 = c[a2];
            j = (j << b2) | i3;
            i += b2;
            while (i >= 8) {
                i -= 8;
                cbVar.writeByte((int) (j >> i));
            }
        }
        if (i > 0) {
            cbVar.writeByte((int) ((j << (8 - i)) | (255 >>> i)));
        }
    }

    public int a(eb ebVar) {
        long j = 0;
        for (int i = 0; i < ebVar.j(); i++) {
            j += c[ebVar.a(i) & 255];
        }
        return (int) ((j + 7) >> 3);
    }

    public static aa b() {
        return d;
    }

    private void a(int i, int i2, byte b2) {
        a aVar = new a(i, b2);
        a aVar2 = this.f5161a;
        while (b2 > 8) {
            b2 = (byte) (b2 - 8);
            int i3 = (i2 >>> b2) & 255;
            a[] aVarArr = aVar2.f5162a;
            if (aVarArr == null) {
                throw new IllegalStateException("invalid dictionary: prefix not unique");
            }
            if (aVarArr[i3] == null) {
                aVarArr[i3] = new a();
            }
            aVar2 = aVar2.f5162a[i3];
        }
        int i4 = 8 - b2;
        int i5 = (i2 << i4) & 255;
        for (int i6 = i5; i6 < (1 << i4) + i5; i6++) {
            aVar2.f5162a[i6] = aVar;
        }
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final a[] f5162a;
        public final int b;
        public final int c;

        public a(int i, int i2) {
            this.f5162a = null;
            this.b = i;
            int i3 = i2 & 7;
            this.c = i3 == 0 ? 8 : i3;
        }

        public a() {
            this.f5162a = new a[256];
            this.b = 0;
            this.c = 0;
        }
    }

    private void a() {
        int i = 0;
        while (true) {
            byte[] bArr = c;
            if (i >= bArr.length) {
                return;
            }
            a(i, b[i], bArr[i]);
            i++;
        }
    }

    public aa() {
        a();
    }
}
