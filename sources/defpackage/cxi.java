package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes3.dex */
public class cxi {
    public static boolean b(byte[] bArr, UUID uuid) {
        if (bArr == null) {
            return false;
        }
        String uuid2 = uuid != null ? uuid.toString() : null;
        boolean z = uuid2 == null;
        int length = bArr.length;
        int i = 0;
        boolean z2 = false;
        while (i < length) {
            byte b = bArr[i];
            if (b <= 0) {
                return z2 && z;
            }
            int i2 = i + 1;
            byte b2 = bArr[i2];
            z = b(uuid2, i2, bArr, z, b);
            if (b2 == 1) {
                z2 = (bArr[i + 2] & 3) > 0;
            }
            i = i2 + (b - 1) + 1;
        }
        return z2 && z;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static boolean b(String str, int i, byte[] bArr, boolean z, int i2) {
        byte b = bArr[i];
        if (str != null) {
            switch (b) {
                case 2:
                case 3:
                    for (int i3 = i + 1; i3 < (i + i2) - 1; i3 += 2) {
                        z = z || c(str, bArr, i3);
                    }
                    break;
                case 4:
                case 5:
                    for (int i4 = i + 1; i4 < (i + i2) - 1; i4 += 4) {
                        z = z || a(str, bArr, i4, 4);
                    }
                    break;
                case 6:
                case 7:
                    for (int i5 = i + 1; i5 < (i + i2) - 1; i5 += 16) {
                        z = z || c(str, bArr, i5, 16);
                    }
                    break;
            }
        }
        return z;
    }

    private static boolean c(String str, byte[] bArr, int i) {
        if (e(bArr, i) == -1) {
            return false;
        }
        String substring = str.substring(4, 8);
        String format = String.format(Locale.ENGLISH, "%04x", Integer.valueOf(e(bArr, i)));
        LogUtil.c("ScannerServiceParser", "decodeService16BitUuid theRequiredUuid = ", substring, " theServiceUuid = ", format);
        return format.equalsIgnoreCase(substring);
    }

    private static boolean a(String str, byte[] bArr, int i, int i2) {
        int i3 = (i + i2) - 4;
        if (e(bArr, i3) == -1) {
            return false;
        }
        String substring = str.substring(4, 8);
        String format = String.format(Locale.ENGLISH, "%04x", Integer.valueOf(e(bArr, i3)));
        LogUtil.c("ScannerServiceParser", "decodeService32BitUuid theRequiredUuid = ", substring, " theServiceUuid = ", format);
        return format.equalsIgnoreCase(substring);
    }

    private static boolean c(String str, byte[] bArr, int i, int i2) {
        int i3 = (i + i2) - 4;
        if (e(bArr, i3) == -1) {
            return false;
        }
        return String.format(Locale.ENGLISH, "%04x", Integer.valueOf(e(bArr, i3))).equalsIgnoreCase(str.substring(4, 8));
    }

    private static int e(byte[] bArr, int i) {
        LogUtil.a("ScannerServiceParser", "decodeUuid16 dataByte.length:", Integer.valueOf(bArr.length), " decodeUuid16 startInt:", Integer.valueOf(i));
        int i2 = i + 1;
        if (bArr.length > i2) {
            return (bArr[i] & 255) | ((bArr[i2] & 255) << 8);
        }
        LogUtil.a("ScannerServiceParser", "decodeUuid16 ArrayIndexOutOfBoundsException dataByte.length <= startInt + 1");
        return -1;
    }
}
