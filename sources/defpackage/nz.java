package defpackage;

import com.careforeyou.library.bean.RoleInfo;
import com.google.flatbuffers.reflection.BaseType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes2.dex */
public class nz {
    private static byte a(byte b, byte b2) {
        return (byte) (((byte) (b << 4)) | b2);
    }

    private static byte c(byte b, byte b2) {
        return (byte) (b == 1 ? b2 | 128 : b2 & Byte.MAX_VALUE);
    }

    public static ArrayList<byte[]> c(ArrayList<nh> arrayList) {
        ArrayList<byte[]> arrayList2 = new ArrayList<>();
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        char c = 3;
        if (arrayList.size() == 1) {
            nh nhVar = arrayList.get(0);
            byte[] bArr = new byte[20];
            bArr[0] = -54;
            bArr[1] = BaseType.Array;
            bArr[2] = BaseType.Union;
            bArr[3] = BaseType.Union;
            bArr[4] = a((byte) 1, (byte) 1);
            oc.b(bArr, currentTimeMillis, 5);
            bArr[9] = 0;
            bArr[10] = 0;
            oc.b(bArr, nhVar.c, 11);
            bArr[15] = c(nhVar.e, nhVar.b);
            bArr[16] = nhVar.d;
            oc.d(bArr, nhVar.f15282a, 17);
            bArr[19] = oc.d(bArr, 1, 18);
            arrayList2.add(bArr);
        } else {
            int i = 6;
            int size = (arrayList.size() * 8) + 6;
            byte[] bArr2 = new byte[size];
            oc.b(bArr2, currentTimeMillis, 0);
            bArr2[4] = 0;
            bArr2[5] = 0;
            Iterator<nh> it = arrayList.iterator();
            while (it.hasNext()) {
                nh next = it.next();
                oc.b(bArr2, next.c, i);
                bArr2[i + 4] = c(next.e, next.b);
                bArr2[i + 5] = next.d;
                oc.d(bArr2, next.f15282a, i + 6);
                i += 8;
            }
            int i2 = size / 14;
            int i3 = 14;
            int i4 = size % 14;
            if (i4 > 0) {
                i2++;
            }
            int i5 = 0;
            while (i5 < i2) {
                int i6 = (i5 != i2 + (-1) || i4 == 0) ? i3 : i4;
                byte[] bArr3 = new byte[i6 + 6];
                bArr3[0] = -54;
                bArr3[1] = BaseType.Array;
                bArr3[2] = (byte) (i6 + 2);
                bArr3[c] = BaseType.Union;
                int i7 = i5 + 1;
                bArr3[4] = a((byte) i7, (byte) i2);
                System.arraycopy(bArr2, i5 * 14, bArr3, 5, i6);
                bArr3[i6 + 5] = oc.d(bArr3, 1, i6 + 4);
                arrayList2.add(bArr3);
                i5 = i7;
                i3 = 14;
                c = 3;
            }
        }
        return arrayList2;
    }

    public static byte[] e(int i, byte b, byte b2, int i2) {
        byte[] bArr = new byte[20];
        String[] split = new SimpleDateFormat("yy:MM:dd:HH:mm:ss", Locale.getDefault()).format(new Date(System.currentTimeMillis())).split(":");
        bArr[0] = -54;
        bArr[1] = BaseType.Union;
        bArr[2] = BaseType.Vector;
        bArr[3] = 1;
        bArr[4] = (byte) (Integer.parseInt(split[0]) & 255);
        bArr[5] = (byte) (Integer.parseInt(split[1]) & 255);
        bArr[6] = (byte) (Integer.parseInt(split[2]) & 255);
        bArr[7] = (byte) (Integer.parseInt(split[3]) & 255);
        bArr[8] = (byte) (Integer.parseInt(split[4]) & 255);
        bArr[9] = (byte) (Integer.parseInt(split[5]) & 255);
        oc.b(bArr, i, 10);
        bArr[14] = (byte) (b & 255);
        bArr[15] = (byte) (i2 & 255);
        bArr[16] = (byte) (b2 & 255);
        bArr[17] = oc.d(bArr, 1, 16);
        bArr[18] = 0;
        bArr[19] = 0;
        return bArr;
    }

    public static byte[] a(RoleInfo roleInfo) {
        byte[] bArr = new byte[9];
        byte age = (byte) (roleInfo.getAge() & 255);
        byte sex = (byte) roleInfo.getSex();
        bArr[0] = -96;
        oc.b(bArr, roleInfo.getId(), 1);
        bArr[5] = c(sex, age);
        bArr[6] = (byte) (roleInfo.getHeight() & 255);
        oc.d(bArr, (short) (roleInfo.getWeight() * 10.0f), 7);
        return bArr;
    }
}
