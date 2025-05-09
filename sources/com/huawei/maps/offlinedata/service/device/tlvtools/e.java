package com.huawei.maps.offlinedata.service.device.tlvtools;

import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class e {
    public static d a(byte[] bArr) throws g {
        return a(bArr, 0);
    }

    private static d a(byte[] bArr, int i) throws g {
        d dVar = new d();
        if (bArr == null || bArr.length == 0) {
            com.huawei.maps.offlinedata.utils.g.c("TlvByteUtils", "hexString is null");
            return dVar;
        }
        if (i > 50) {
            throw new g();
        }
        int i2 = 0;
        while (i2 != bArr.length) {
            try {
                byte b = bArr[i2];
                if (b == 0) {
                    throw new g();
                }
                int i3 = i2 + 1;
                byte[] b2 = b(bArr, i3);
                int length = i3 + b2.length;
                int b3 = b(b2);
                byte[] array = ByteBuffer.allocate(b3).put(bArr, length, b3).array();
                i2 = length + b3;
                if ((b & 128) == 128) {
                    dVar.b().add(a(array, i + 1));
                } else {
                    dVar.a().add(new f(b, b3, array));
                }
            } catch (IndexOutOfBoundsException e) {
                com.huawei.maps.offlinedata.utils.g.d("TlvByteUtils", "parseTlv occur exception " + e.getMessage());
                throw new g();
            }
        }
        return dVar;
    }

    private static byte[] b(byte[] bArr, int i) throws g {
        int i2 = 0;
        int i3 = i;
        while ((bArr[i3] & 128) == 128) {
            i3++;
            i2++;
            if (i2 == 3) {
                throw new g();
            }
        }
        int i4 = i2 + 1;
        ByteBuffer allocate = ByteBuffer.allocate(i4);
        allocate.put(bArr, i, i4);
        return allocate.array();
    }

    private static int b(byte[] bArr) throws g {
        byte b;
        int i;
        int length = bArr.length;
        if (length == 1) {
            byte b2 = bArr[0];
            if ((b2 & 128) != 128) {
                return b2;
            }
            throw new g();
        }
        if (length == 2) {
            byte b3 = bArr[0];
            if ((b3 & 128) != 128) {
                throw new g();
            }
            b = bArr[1];
            if ((b & 128) == 128) {
                throw new g();
            }
            i = (b3 & Byte.MAX_VALUE) << 7;
        } else if (length == 3) {
            byte b4 = bArr[0];
            if ((b4 & 128) != 128) {
                throw new g();
            }
            byte b5 = bArr[1];
            if ((b5 & 128) != 128) {
                throw new g();
            }
            b = bArr[2];
            if ((b & 128) == 128) {
                throw new g();
            }
            i = ((b4 & Byte.MAX_VALUE) << 14) | ((b5 & Byte.MAX_VALUE) << 7);
        } else {
            throw new g();
        }
        return (b & Byte.MAX_VALUE) | i;
    }
}
