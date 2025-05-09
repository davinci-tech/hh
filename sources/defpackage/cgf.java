package defpackage;

import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class cgf {
    private static int[] e = {0, 4129, 8258, 12387, 16516, 20645, 24774, 28903, 33032, 37161, 41290, 45419, 49548, 53677, 57806, 61935, 4657, 528, 12915, 8786, 21173, 17044, 29431, 25302, 37689, 33560, 45947, 41818, 54205, 50076, 62463, 58334, 9314, 13379, 1056, 5121, 25830, 29895, 17572, 21637, 42346, 46411, 34088, 38153, 58862, 62927, 50604, 54669, 13907, 9842, 5649, 1584, 30423, 26358, 22165, 18100, 46939, 42874, 38681, 34616, 63455, 59390, 55197, 51132, 18628, 22757, 26758, 30887, 2112, 6241, 10242, 14371, 51660, 55789, 59790, 63919, 35144, 39273, 43274, 47403, 23285, 19156, 31415, 27286, 6769, 2640, 14899, 10770, 56317, 52188, 64447, 60318, 39801, 35672, 47931, 43802, 27814, 31879, 19684, 23749, 11298, 15363, 3168, 7233, 60846, 64911, 52716, 56781, 44330, 48395, 36200, 40265, 32407, 28342, 24277, 20212, 15891, 11826, 7761, 3696, 65439, 61374, 57309, 53244, 48923, 44858, 40793, 36728, 37256, 33193, 45514, 41451, 53516, 49453, 61774, 57711, 4224, MachineControlPointResponse.OP_CODE_EXTENSION_SET_TOTAL_ENERGY, 12482, 8419, 20484, 16421, 28742, 24679, 33721, 37784, 41979, 46042, 49981, 54044, 58239, 62302, 689, 4752, 8947, 13010, 16949, 21012, 25207, 29270, 46570, 42443, 38312, 34185, 62830, 58703, 54572, 50445, 13538, 9411, 5280, 1153, 29798, 25671, 21540, 17413, 42971, 47098, 34713, 38840, 59231, 63358, 50973, 55100, 9939, 14066, 1681, 5808, 26199, 30326, 17941, 22068, 55628, 51565, 63758, 59695, 39368, 35305, 47498, 43435, 22596, 18533, 30726, 26663, 6336, 2273, 14466, 10403, 52093, 56156, 60223, 64286, 35833, 39896, 43963, 48026, 19061, 23124, 27191, 31254, GLMapStaticValue.AM_PARAMETERNAME_RENDER_COMPLETE, 6864, 10931, 14994, 64814, 60687, 56684, 52557, 48554, 44427, 40424, 36297, 31782, 27655, 23652, 19525, 15522, 11395, 7392, 3265, 61215, 65342, 53085, 57212, 44955, 49082, 36825, 40952, 28183, 32310, 20053, 24180, 11923, 16050, 3793, 7920};

    public static byte[] a(byte[] bArr) {
        short s = 0;
        for (byte b : bArr) {
            s = (short) ((s << 8) ^ e[(b ^ (s >> 8)) & 255]);
        }
        byte[] bArr2 = {0, 0};
        bArr2[1] = (byte) s;
        bArr2[0] = (byte) (s >> 8);
        return bArr2;
    }

    private static ArrayList<byte[]> d(byte[] bArr, boolean z) {
        if (bArr == null) {
            LogUtil.h("BleInstructionDataUtil", "getFrameBytes payload is null");
            return new ArrayList<>();
        }
        return e(bArr, z);
    }

    private static ArrayList<byte[]> e(byte[] bArr, boolean z) {
        int i;
        byte[] bArr2;
        ArrayList<byte[]> arrayList = new ArrayList<>();
        int a2 = a((bArr.length + (a(a(bArr.length, 15), 16) * 2)) - 2, 15);
        int a3 = a(a2, 16);
        if (a2 == 0) {
            a2 = 1;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < a2; i3++) {
            int length = (bArr.length + (a3 * 2)) + (-2) < i2 + 15 ? bArr.length - i2 : 15;
            int i4 = i3 % 16;
            if (i4 == 0 && i3 != 0 && length <= 13) {
                i = length + 7;
                bArr2 = new byte[i];
            } else {
                i = length + 5;
                bArr2 = new byte[i];
            }
            bArr2[0] = z ? (byte) -36 : (byte) -37;
            bArr2[1] = (byte) (i - 2);
            int i5 = i3 / 16;
            c(a2, i5, i3, bArr2);
            if (i4 != 0 || i5 == 0) {
                for (int i6 = 0; i6 < length; i6++) {
                    int i7 = i6 + 3;
                    int i8 = i2 + i6;
                    if (i7 < bArr2.length && i8 < bArr.length) {
                        bArr2[i7] = bArr[i8];
                    }
                }
                i2 += length;
            } else {
                bArr2[3] = (byte) (a3 - 1);
                bArr2[4] = (byte) i5;
                i2 = b(bArr, i2, length, bArr2);
            }
            b(bArr2, i);
            LogUtil.a("BleInstructionDataUtil", "getFrameBytes totalFrame ", Integer.valueOf(bArr2.length), " dealBytes = ", Integer.valueOf(i2));
            arrayList.add(bArr2);
        }
        return arrayList;
    }

    private static void b(byte[] bArr, int i) {
        byte[] a2 = a(bArr);
        bArr[i - 2] = a2[1];
        bArr[i - 1] = a2[0];
    }

    private static int b(byte[] bArr, int i, int i2, byte[] bArr2) {
        int i3 = 0;
        if (i2 <= 13) {
            while (i3 < i2) {
                bArr2[i3 + 5] = bArr[i + i3];
                i3++;
            }
            return i + i2;
        }
        while (true) {
            int i4 = i2 - 2;
            if (i3 >= i4) {
                return i + i4;
            }
            bArr2[i3 + 5] = bArr[i + i3];
            i3++;
        }
    }

    private static void c(int i, int i2, int i3, byte[] bArr) {
        byte b;
        int i4 = i - (i2 * 16);
        if (i4 >= 1 && i4 <= 16) {
            b = (byte) ((i4 - 1) & 15);
        } else if (i4 > 16) {
            b = 15;
        } else {
            LogUtil.h("BleInstructionDataUtil", "setSubcontractingNum default");
            b = 0;
        }
        bArr[2] = (byte) (((byte) (b << 4)) + ((byte) (15 & i3)));
    }

    private static int a(int i, int i2) {
        LogUtil.a("BleInstructionDataUtil", "num = ", Integer.valueOf(i));
        if (i2 == 0) {
            return 0;
        }
        int i3 = i / i2;
        return i % i2 > 0 ? i3 + 1 : i3;
    }

    public static ArrayList<byte[]> b(byte[] bArr, boolean z) {
        if (bArr == null) {
            LogUtil.h("BleInstructionDataUtil", "genSendFrameS playload is null");
            return new ArrayList<>();
        }
        int length = bArr.length / 15;
        if (bArr.length % 15 > 0) {
            length++;
        }
        if (length > 16) {
            LogUtil.a("BleInstructionDataUtil", "totalFrameNum > MAX_PLAYLOAD");
            return d(bArr, z);
        }
        ArrayList<byte[]> arrayList = new ArrayList<>();
        if (length == 0) {
            length = 1;
        }
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            arrayList.add(c(bArr, z, length, i, i2));
            i += 15;
        }
        if (bArr.length == 240) {
            arrayList.add(c());
        }
        return arrayList;
    }

    public static ArrayList<byte[]> c(byte[] bArr, boolean z) {
        ArrayList<byte[]> arrayList = new ArrayList<>();
        if (bArr == null) {
            return arrayList;
        }
        int length = bArr.length / 15;
        if (bArr.length % 15 > 0) {
            length++;
        }
        if (length == 0) {
            length = 1;
        }
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            arrayList.add(c(bArr, z, length, i, i2));
            i += 15;
        }
        return arrayList;
    }

    private static byte[] c() {
        LogUtil.a("BleInstructionDataUtil", "getEmptyFrameBytes ");
        byte[] bArr = {-37, (byte) 5, 0, 1, 1, 0, 0};
        b(bArr, 7);
        return bArr;
    }

    private static byte[] c(byte[] bArr, boolean z, int i, int i2, int i3) {
        LogUtil.a("BleInstructionDataUtil", "totalFrameNum:", Integer.valueOf(i));
        int length = bArr.length < i2 + 15 ? bArr.length - i2 : 15;
        LogUtil.a("BleInstructionDataUtil", "fpllen:", Integer.valueOf(length));
        int i4 = length + 5;
        byte[] bArr2 = new byte[i4];
        if (z) {
            bArr2[0] = -36;
        } else {
            bArr2[0] = -37;
        }
        bArr2[1] = (byte) (length + 3);
        bArr2[2] = (byte) (((byte) (((byte) ((i - 1) & 15)) << 4)) + ((byte) (i3 & 15)));
        for (int i5 = 0; i5 < length; i5++) {
            bArr2[i5 + 3] = bArr[i2 + i5];
        }
        b(bArr2, i4);
        LogUtil.a("BleInstructionDataUtil", "genSendFrameS totalFrame", cvx.d(bArr2));
        return bArr2;
    }

    public static cgb d(byte[] bArr) {
        LogUtil.a("BleInstructionDataUtil", "frameGetPlayLoad frame", cvx.d(bArr));
        if (bArr == null || bArr.length < 5) {
            return null;
        }
        byte b = bArr[0];
        if (b != -67 && b != -51) {
            return null;
        }
        cgb cgbVar = new cgb();
        cgbVar.d(bArr[0]);
        cgbVar.a(bArr[1] - 3);
        cgbVar.b(bArr[2] & BaseType.Obj);
        cgbVar.e((bArr[2] >> 4) & 15);
        byte[] bArr2 = new byte[cgbVar.b()];
        for (int i = 0; i < cgbVar.b(); i++) {
            int i2 = i + 3;
            if (i2 < bArr.length) {
                bArr2[i] = bArr[i2];
            } else {
                ReleaseLogUtil.e("R_BleInstructionDataUtil", "error data length");
            }
        }
        cgbVar.e(bArr2);
        return cgbVar;
    }

    public static int b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += (bArr[i + i4] & 255) << (i4 * 8);
        }
        return i3;
    }
}
