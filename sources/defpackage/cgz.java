package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.open.data.HealthDataParser;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class cgz implements HealthDataParser {
    private boolean b = false;

    public static int e(byte b, byte b2) {
        return ((b & 255) << 8) | (b2 & 255);
    }

    @Override // com.huawei.health.device.open.data.HealthDataParser
    public HealthData parseData(byte[] bArr) {
        return null;
    }

    public void c(boolean z) {
        this.b = z;
    }

    public cjh d(byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("HwWspMeasureDataParser", "buffer is null");
            return null;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        if (length > 70) {
            LogUtil.a("HwWspMeasureDataParser", "parseManagerInfoForNew is above 70 bytes");
            ByteBuffer wrap = ByteBuffer.wrap(bArr2);
            byte[] bArr3 = new byte[30];
            wrap.get(bArr3);
            byte[] bArr4 = new byte[40];
            wrap.get(bArr4);
            String str = "";
            String trim = new String(bArr4, StandardCharsets.UTF_8).trim();
            LogUtil.a("HwWspMeasureDataParser", "deviceIdStr:", trim);
            try {
                int i = wrap.get() & 255;
                byte[] bArr5 = new byte[i];
                if (wrap.limit() - wrap.position() >= i) {
                    wrap.get(bArr5);
                }
                str = new String(bArr5, StandardCharsets.UTF_8).trim();
                LogUtil.a("HwWspMeasureDataParser", "accountInfoStr:", str);
            } catch (BufferUnderflowException e) {
                LogUtil.h("HwWspMeasureDataParser", "UnsupportedEncodingException while OutOfBounds info", e.getMessage());
            }
            return new cjh(bArr3, trim, str);
        }
        return a(bArr2);
    }

    public cjh a(byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("HwWspMeasureDataParser", "parsing manager info failed with null data");
            return null;
        }
        int length = bArr.length;
        if (length == 0) {
            LogUtil.h("HwWspMeasureDataParser", "no manager info");
            return new cjh(new byte[0], "", "");
        }
        if (length <= 30) {
            return null;
        }
        byte[] bArr2 = new byte[30];
        System.arraycopy(bArr, 0, bArr2, 0, 30);
        int i = length - 30;
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr, 30, bArr3, 0, i);
        return new cjh(bArr2, new String(bArr3, StandardCharsets.UTF_8).trim(), "");
    }

    public int e(byte[] bArr) {
        byte b;
        if (bArr == null) {
            return -1;
        }
        if (this.b) {
            if (bArr.length <= 0) {
                return -1;
            }
            b = bArr[0];
        } else {
            if (bArr.length < 5) {
                return -1;
            }
            b = bArr[3];
        }
        if (b == 0) {
            return 0;
        }
        if (b == 1) {
            return 1;
        }
        if (b == 2) {
            return 2;
        }
        return b == 3 ? 3 : -1;
    }

    public int f(byte[] bArr) {
        byte b;
        if (bArr == null) {
            return -1;
        }
        if (this.b) {
            if (bArr.length <= 0) {
                return -1;
            }
            b = bArr[0];
        } else {
            if (bArr.length < 5) {
                return -1;
            }
            b = bArr[3];
        }
        try {
            return Integer.parseInt(Integer.toString(b & 255, 16));
        } catch (NumberFormatException e) {
            LogUtil.b("HwWspMeasureDataParser", "parseUnit exception:", e.getMessage());
            return -1;
        }
    }

    public String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[5];
        if (this.b) {
            if (bArr.length < 5) {
                return null;
            }
        } else {
            if (bArr.length < 10) {
                return null;
            }
            System.arraycopy(bArr, 3, bArr2, 0, 5);
            bArr = bArr2;
        }
        return new String(bArr, StandardCharsets.UTF_8);
    }

    public byte[] d(String str) {
        if (str == null) {
            LogUtil.b("HwWspMeasureDataParser", "huid is null");
            return new byte[0];
        }
        byte[] bArr = new byte[30];
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        if (bytes != null) {
            System.arraycopy(bytes, 0, bArr, 0, Math.min(bytes.length, 30));
        }
        return bArr;
    }

    public byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwWspMeasureDataParser", "uid is null");
            return new byte[0];
        }
        byte[] bArr = new byte[32];
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        if (bytes != null) {
            System.arraycopy(bytes, 0, bArr, 0, Math.min(bytes.length, 32));
        }
        return bArr;
    }

    public byte[] e(byte[] bArr, byte[] bArr2, int i, int i2, cfi cfiVar) {
        if (bArr == null || bArr2 == null) {
            return new byte[0];
        }
        if (cfiVar == null) {
            return new byte[0];
        }
        byte[] bArr3 = cfiVar.b() == -1 ? new byte[69] : new byte[70];
        int min = Math.min(bArr.length, 30);
        int min2 = Math.min(bArr2.length, 32);
        System.arraycopy(bArr, 0, bArr3, 0, min);
        System.arraycopy(bArr2, 0, bArr3, 30, min2);
        bArr3[62] = (byte) i;
        bArr3[63] = (byte) cfiVar.a();
        bArr3[64] = (byte) (cfiVar.d() & 255);
        bArr3[65] = (byte) ((cfiVar.d() >> 8) & 255);
        int floatValue = (int) new BigDecimal(Float.toString(cfiVar.m())).multiply(new BigDecimal(String.valueOf(100))).floatValue();
        LogUtil.c("HwWspMeasureDataParser", "weightIntValue = ", Integer.valueOf(floatValue));
        bArr3[66] = (byte) (floatValue & 255);
        bArr3[67] = (byte) ((floatValue >> 8) & 255);
        bArr3[68] = (byte) i2;
        if (cfiVar.b() != -1) {
            bArr3[69] = (byte) cfiVar.b();
        }
        ReleaseLogUtil.e("R_Weight_HwWspMeasureDataParser", Integer.valueOf(String.valueOf(cfiVar.d()).length()), Integer.valueOf(cfiVar.d()), Integer.valueOf(String.valueOf(cfiVar.m()).length()), Float.valueOf(cfiVar.m()), Integer.valueOf(String.valueOf(cfiVar.a()).length()), Integer.valueOf(cfiVar.a()), Integer.valueOf(String.valueOf(cfiVar.b()).length()), Integer.valueOf(cfiVar.b()), ", type is ", Integer.valueOf(i2));
        return bArr3;
    }

    public byte[] d(String str, String str2) {
        byte[] bArr = new byte[70];
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] bytes2 = str2.getBytes(StandardCharsets.UTF_8);
        if (bytes != null && bytes2 != null) {
            int min = Math.min(bytes.length, 30);
            int length = bytes.length <= 40 ? bytes2.length : 40;
            System.arraycopy(bytes, 0, bArr, 0, min);
            System.arraycopy(bytes2, 0, bArr, 30, length);
        }
        return bArr;
    }

    public static byte[] c(List<byte[]> list) {
        byte[] bArr = list.get(0);
        byte[] bArr2 = list.get(1);
        return new byte[]{bArr[0], bArr[1], bArr[2], bArr[4], bArr[5], bArr[6], bArr[7], bArr[8], bArr[9], bArr[10], bArr[12], bArr[13], bArr[14], bArr2[4], bArr2[5]};
    }

    public static cgp c(byte[] bArr) {
        if (bArr == null || bArr.length < 5 || bArr.length > 20) {
            return null;
        }
        byte[] c = c(bArr[1]);
        byte[] c2 = c(bArr[2]);
        byte[] c3 = c(bArr[3]);
        int length = c.length + c2.length + c3.length;
        LogUtil.a("HwWspMeasureDataParser", "count === ", Integer.valueOf(length));
        byte[] bArr2 = new byte[length];
        d(bArr2, c, c2, c3);
        StringBuilder sb = new StringBuilder(16);
        int i = 0;
        while (i < 4) {
            sb.append((int) bArr2[i]);
            i++;
        }
        StringBuilder sb2 = new StringBuilder(16);
        while (i < 8) {
            sb2.append((int) bArr2[i]);
            i++;
        }
        StringBuilder sb3 = new StringBuilder(16);
        while (i < 12) {
            sb3.append((int) bArr2[i]);
            i++;
        }
        StringBuilder sb4 = new StringBuilder(16);
        while (i < 22) {
            sb4.append((int) bArr2[i]);
            i++;
        }
        return e(sb, sb2, sb3, sb4, bArr);
    }

    private static cgp e(StringBuilder sb, StringBuilder sb2, StringBuilder sb3, StringBuilder sb4, byte[] bArr) {
        StringBuilder sb5 = new StringBuilder(16);
        try {
            sb5.append(Integer.parseInt(sb.toString(), 2));
            sb5.append(".");
            sb5.append(Integer.parseInt(sb2.toString(), 2));
            sb5.append(".");
            sb5.append(Integer.parseInt(sb3.toString(), 2));
            sb5.append(".");
            sb5.append(Integer.parseInt(sb4.toString(), 2));
        } catch (NumberFormatException unused) {
            LogUtil.b("HwWspMeasureDataParser", "spliceVersion exception");
        }
        cgp cgpVar = new cgp();
        cgpVar.a(sb5.toString());
        ReleaseLogUtil.e("R_Weight_HwWspMeasureDataParser", "version ", sb5.toString());
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 4, bArr2, 0, bArr.length - 4);
        cgpVar.b(new String(i(bArr2)));
        LogUtil.c("HwWspMeasureDataParser", "dataInfos ==== ", cgpVar.e());
        return cgpVar;
    }

    private static void d(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        System.arraycopy(bArr3, 0, bArr, bArr2.length, bArr3.length);
        System.arraycopy(bArr4, 0, bArr, bArr2.length + bArr3.length, bArr4.length);
    }

    private static byte[] c(byte b) {
        byte[] bArr = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bArr[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return bArr;
    }

    private static char[] i(byte[] bArr) {
        Charset charset = StandardCharsets.US_ASCII;
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length);
        allocate.put(bArr);
        allocate.flip();
        return charset.decode(allocate).array();
    }

    public static String e(byte[] bArr, int i) {
        byte b;
        byte b2;
        byte b3;
        byte b4;
        byte b5;
        if (bArr == null || bArr.length < i + 7 || i < 0) {
            LogUtil.h("HwWspMeasureDataParser", "HwWspMeasureDataParser parseTimeStamp illegal bytes");
            return "";
        }
        int b6 = cgf.b(bArr, i, 2);
        int i2 = i + 2;
        if (b6 < 2000) {
            LogUtil.h("HwWspMeasureDataParser", "HwWspMeasureDataParser parse time Stamp: wrong time");
            b5 = 0;
            b4 = 0;
            b2 = 0;
            b3 = 0;
            b6 = 2000;
            b = 0;
        } else {
            byte b7 = bArr[i2];
            b = bArr[i + 3];
            b2 = bArr[i + 4];
            b3 = bArr[i + 5];
            b4 = bArr[i + 6];
            b5 = b7;
        }
        String format = String.format(Locale.ENGLISH, "%d-%d-%d %d:%d:%d", Integer.valueOf(b6), Integer.valueOf(b5), Integer.valueOf(b), Integer.valueOf(b2), Integer.valueOf(b3), Integer.valueOf(b4));
        LogUtil.c("HwWspMeasureDataParser", "HwWspMeasureDataParser parseTimeStamp ", format);
        return format;
    }

    public static byte[] c(int i) {
        return new byte[]{(byte) i};
    }
}
