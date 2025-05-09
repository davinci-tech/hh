package defpackage;

import android.text.TextUtils;
import android.util.Base64;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.haf.common.utils.HexUtils;
import com.huawei.health.device.kit.hwch.ch18.bean.WeightUnitEnum;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cgg {

    /* renamed from: a, reason: collision with root package name */
    private static byte[] f699a;
    private static byte[] e;

    public static void c(String str) {
        if (str != null && !"".equals(str)) {
            String[] split = str.split(":");
            f699a = new byte[6];
            for (int i = 0; i < split.length; i++) {
                try {
                    f699a[i] = (byte) Integer.parseInt(split[i], 16);
                } catch (NumberFormatException e2) {
                    LogUtil.b("DataParser", "initialize parseInt NumberFormatException ", e2.getMessage());
                }
            }
            return;
        }
        LogUtil.h("DataParser", "mac is null");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static cgn a(byte[] bArr) {
        byte[] i;
        cgn cgnVar = new cgn();
        if (bArr != null) {
            if (bArr.length >= 1 && (i = i(bArr)) != null && i.length >= 2) {
                LogUtil.a("DataParser", "data is not null");
                byte b = i[0];
                if (i.length >= 2 && (b & 255) == 189 && i.length == (i[1] & 255) + 3) {
                    int i2 = i[2] & 255;
                    a(i2, i, cgnVar);
                    e(i2, i, cgnVar);
                    switch (i2) {
                        case 38:
                            cgnVar.c(18);
                            cgnVar.e(Boolean.valueOf(i[3] == 1));
                            break;
                        case 39:
                            cgnVar.c(19);
                            break;
                        case 40:
                            if (i[3] == 1) {
                                cgnVar.c(20);
                                break;
                            } else {
                                cgnVar.c(21);
                                break;
                            }
                    }
                } else {
                    LogUtil.a("DataParser", "scale cmd not parse");
                }
            }
        }
        return cgnVar;
    }

    private static void e(int i, byte[] bArr, cgn cgnVar) {
        if (i == 25) {
            LogUtil.c("DataParser", "scale history uploaded done.");
            cgnVar.c(17);
            return;
        }
        if (i != 32) {
            switch (i) {
                case 19:
                    if ((bArr[3] & 64) > 0) {
                        LogUtil.b("DataParser", "weight over load");
                        cgnVar.c(11);
                        break;
                    } else {
                        LogUtil.b("DataParser", "weight not over load");
                        break;
                    }
                case 20:
                    LogUtil.c("DataParser", "receive device low power message.");
                    cgnVar.c(12);
                    break;
                case 21:
                    LogUtil.c("DataParser", "receive fat measure error message.");
                    cgnVar.c(13);
                    cgnVar.e(Integer.valueOf(bArr[3] & 255));
                    break;
                case 22:
                    LogUtil.c("DataParser", "receive set clock ack.");
                    cgnVar.c(14);
                    break;
                case 23:
                    LogUtil.c("DataParser", "receive OTA upgrade ready.");
                    cgnVar.c(15);
                    break;
            }
            return;
        }
        LogUtil.c("DataParser", "scale response update list.");
        cgnVar.c(3);
    }

    private static void a(int i, byte[] bArr, cgn cgnVar) {
        if (i == 0) {
            LogUtil.c("DataParser", "receive scale wakeup.");
            cgnVar.c(1);
        }
        if (i == 1) {
            LogUtil.c("DataParser", "receive scale sleeped.");
            cgnVar.c(2);
            return;
        }
        if (i == 2) {
            LogUtil.c("DataParser", "receive scale change unit.");
            cgnVar.c(16);
            cgnVar.e(bArr[3] == 1 ? WeightUnitEnum.WEIGHT_UNIT_KG : WeightUnitEnum.WEIGHT_UNIT_POWND);
            return;
        }
        if (i == 8) {
            e(bArr, cgnVar);
            return;
        }
        if (i == 12) {
            j(bArr, cgnVar);
            return;
        }
        switch (i) {
            case 14:
                a(bArr, cgnVar);
                break;
            case 15:
                b(bArr, cgnVar);
                break;
            case 16:
                d(bArr, cgnVar);
                break;
            case 17:
                c(bArr, cgnVar);
                break;
            case 18:
                g(bArr, cgnVar);
                break;
        }
    }

    private static void g(byte[] bArr, cgn cgnVar) {
        int i = bArr[3] & 255;
        byte b = bArr[4];
        LogUtil.c("DataParser", "package upgrade result:", Integer.valueOf(i));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", i);
            jSONObject.put("type", b & 255);
        } catch (JSONException e2) {
            LogUtil.b("DataParser", "dataparse ", e2.getMessage());
        }
        cgnVar.c(10);
        cgnVar.e(jSONObject);
    }

    private static void c(byte[] bArr, cgn cgnVar) {
        int i = (bArr[3] & 255) | ((bArr[4] & 255) << 8);
        LogUtil.c("DataParser", "redevice packageupgradeResponse is:", Integer.valueOf(i));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pkgNo", i);
        } catch (JSONException e2) {
            LogUtil.b("DataParser", "dataparse ", e2.getMessage());
        }
        int i2 = bArr[5] & 255;
        if (i2 <= 1) {
            try {
                jSONObject.put("result", i2 == 0);
            } catch (JSONException e3) {
                LogUtil.b("DataParser", "dataparse ", e3.getMessage());
            }
        } else {
            try {
                jSONObject.put("result", true);
            } catch (JSONException e4) {
                LogUtil.b("DataParser", "dataparse ", e4.getMessage());
            }
        }
        cgnVar.c(9);
        cgnVar.e(jSONObject);
    }

    private static void d(byte[] bArr, cgn cgnVar) {
        LogUtil.c("DataParser", "receive weight measureing data.");
        cgo cgoVar = new cgo();
        cgoVar.c(Float.valueOf((bArr[4] & 255) | ((bArr[5] & 255) << 8)).floatValue() / 10.0f);
        cgoVar.d(Float.valueOf((bArr[6] & 255) | ((bArr[7] & 255) << 8)).floatValue() / 10.0f);
        cgoVar.f((bArr[8] & 255) | ((bArr[9] & 255) << 8));
        cgoVar.b(bArr[10] & 255);
        cgoVar.d(bArr[11] & 255);
        cgoVar.e(bArr[12] & 255);
        cgoVar.c(bArr[13] & 255);
        cgoVar.g(bArr[14] & 255);
        cgoVar.j(bArr[15] & 255);
        cgoVar.a((bArr[16] & 255) | ((bArr[17] & 255) << 8));
        cgoVar.a((bArr[18] & 255) == 170);
        LogUtil.c("DataParser", cgoVar.toString());
        cgnVar.c(8);
        cgnVar.e(cgoVar);
        cgnVar.d(b(new byte[]{-37, 2, BaseType.Float, 1}));
    }

    private static void b(byte[] bArr, cgn cgnVar) {
        LogUtil.c("DataParser", "receive weight measureing data.");
        cgo cgoVar = new cgo();
        cgoVar.c(Float.valueOf((bArr[4] & 255) | ((bArr[5] & 255) << 8)).floatValue() / 10.0f);
        cgoVar.f((bArr[6] & 255) | ((bArr[7] & 255) << 8));
        cgoVar.b(bArr[8] & 255);
        cgoVar.d(bArr[9] & 255);
        cgoVar.e(bArr[10] & 255);
        cgoVar.c(bArr[11] & 255);
        cgoVar.g(bArr[12] & 255);
        cgoVar.j(bArr[13] & 255);
        cgoVar.a(0);
        cgoVar.d(0.0f);
        LogUtil.c("DataParser", cgoVar.toString());
        cgnVar.c(7);
        cgnVar.e(cgoVar);
        cgnVar.d(b(new byte[]{-37, 2, BaseType.MaxBaseType, 0}));
    }

    private static void a(byte[] bArr, cgn cgnVar) {
        LogUtil.c("DataParser", "receive fat result.");
        cgo cgoVar = new cgo();
        byte b = bArr[4];
        byte b2 = bArr[5];
        LogUtil.c("DataParser", "receive fat result.1");
        cgoVar.c(Float.valueOf((b & 255) | ((b2 & 255) << 8)).floatValue() / 10.0f);
        cgoVar.d(Float.valueOf((bArr[6] & 255) | ((bArr[7] & 255) << 8)).floatValue() / 10.0f);
        LogUtil.c("DataParser", "receive fat result.time start");
        cgoVar.f((bArr[8] & 255) | ((bArr[9] & 255) << 8));
        cgoVar.b(bArr[10] & 255);
        LogUtil.c("DataParser", "receive fat result resistance 1");
        cgoVar.d(bArr[11] & 255);
        cgoVar.e(bArr[12] & 255);
        cgoVar.c(bArr[13] & 255);
        cgoVar.g(bArr[14] & 255);
        cgoVar.j(bArr[15] & 255);
        LogUtil.c("DataParser", "receive fat result resistance start");
        cgoVar.a(((bArr[17] & 255) << 8) | (bArr[16] & 255));
        LogUtil.c("DataParser", "receive fat result bean");
        LogUtil.c("DataParser", cgoVar.toString());
        cgnVar.c(7);
        cgnVar.e(cgoVar);
        cgnVar.d(b(new byte[]{-37, 2, BaseType.MaxBaseType, 0}));
    }

    private static void e(byte[] bArr, cgn cgnVar) {
        LogUtil.c("DataParser", "receive scale clock.");
        int i = (bArr[3] & 255) | ((bArr[4] & 255) << 8);
        int i2 = bArr[5] & 255;
        int i3 = bArr[6] & 255;
        int i4 = bArr[7] & 255;
        int i5 = bArr[8] & 255;
        int i6 = bArr[9] & 255;
        int i7 = bArr[10] & 255;
        LogUtil.c("DataParser", "got scale curr clock info.", "year:", Integer.valueOf(i), "month:", Integer.valueOf(i2), "date:", Integer.valueOf(i3), "hour:", Integer.valueOf(i4), "minute:", Integer.valueOf(i5), "second:", Integer.valueOf(i6), "weekOfYear:", Integer.valueOf(i7));
        cgl cglVar = new cgl(new int[]{i, i2, i3}, i4, i5, i6, i7);
        cgnVar.c(6);
        cgnVar.e(cglVar);
    }

    private static void j(byte[] bArr, cgn cgnVar) {
        LogUtil.c("DataParser", "receive scale version info.");
        cgnVar.c(4);
        byte b = bArr[3];
        byte b2 = bArr[4];
        byte b3 = bArr[5];
        byte b4 = bArr[6];
        byte b5 = bArr[7];
        cgnVar.e(new cgp(((b2 & 255) << 8) | (b & 255), ((b4 & 255) << 8) | (b3 & 255), ((bArr[8] & 255) << 8) | (b5 & 255), ((bArr[10] & 255) << 8) | (bArr[9] & 255)));
    }

    private static byte[] i(byte[] bArr) {
        int i = 0;
        int i2 = 3;
        if (bArr != null && bArr.length > 2 && (bArr[0] & 255) == 188) {
            int i3 = bArr[2] & 255;
            if (i3 == 14) {
                LogUtil.a("DataParser", "measure result data 1 :", HexUtils.d(bArr));
                byte[] bArr2 = new byte[16];
                e = bArr2;
                System.arraycopy(bArr, 3, bArr2, 0, Math.min(bArr.length - 3, 16));
            } else if (i3 == 142 && e != null) {
                LogUtil.a("DataParser", "measure result data 2 :", HexUtils.d(bArr));
                bArr = b(bArr, false);
            } else if (i3 == 16) {
                LogUtil.a("DataParser", "history weight data 1:", HexUtils.d(bArr));
                byte[] bArr3 = new byte[16];
                e = bArr3;
                if (bArr.length >= 19) {
                    System.arraycopy(bArr, 3, bArr3, 0, 16);
                } else {
                    ReleaseLogUtil.e("R_DataParser", "error data length");
                }
            } else if (i3 == 144 && e != null) {
                LogUtil.a("DataParser", "history weight data 2:", HexUtils.d(bArr));
                bArr = b(bArr, true);
            } else {
                LogUtil.c("DataParser", "decryptData other ble cmd ");
            }
            bArr = null;
        } else {
            LogUtil.c("DataParser", "decryptData not aes decrypt");
        }
        if (bArr != null && bArr.length > 0) {
            if ((bArr[0] & 255) == 189) {
                byte[] bArr4 = (byte[]) bArr.clone();
                while (i2 < bArr4.length) {
                    bArr[i2] = (byte) (bArr[i2] ^ f699a[i % 6]);
                    i2++;
                    i++;
                }
                LogUtil.a("DataParser", "data deEncrypt is : ", HexUtils.d(bArr));
            }
        } else {
            LogUtil.c("DataParser", "data deEncrypt is not xor");
        }
        return bArr;
    }

    private static byte[] b(byte[] bArr, boolean z) {
        if (bArr.length < 19) {
            LogUtil.h("DataParser", "data length less than valid Data");
            return null;
        }
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 3, bArr2, 0, 16);
        byte[] b = cgi.b(e);
        byte[] b2 = cgi.b(bArr2);
        if (b == null || b2 == null) {
            return null;
        }
        LogUtil.a("DataParser", "parse normal data");
        byte[] bArr3 = new byte[b.length + 3 + b2.length];
        bArr3[0] = -67;
        bArr3[1] = bArr[1];
        if (z) {
            bArr3[2] = BaseType.Union;
        } else {
            bArr3[2] = BaseType.Vector;
        }
        System.arraycopy(b, 0, bArr3, 3, b.length);
        System.arraycopy(b2, 0, bArr3, b.length + 3, b2.length);
        LogUtil.a("DataParser", "measure result decrypt data ：", HexUtils.d(bArr3));
        e = null;
        return bArr3;
    }

    public static byte[] e() {
        LogUtil.c("DataParser", "send sync system time cmd.");
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        int i7 = calendar.get(7);
        return b(new byte[]{-37, 9, 8, (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) (i2 + 1), (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) (i7 == 1 ? 7 : i7 - 1)});
    }

    public static byte[] b(cgu cguVar) {
        LogUtil.c("DataParser", "send user body info cmd.");
        if (cguVar == null) {
            return new byte[0];
        }
        cguVar.a(h(cguVar.b()));
        return b(cguVar.d());
    }

    public static byte[] e(String str) {
        LogUtil.c("DataParser", "send get record cmd, uid is : ", str);
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        byte[] h = h(str);
        return b(new byte[]{-37, 7, BaseType.Float, h[0], h[1], h[2], h[3], h[4], h[5], h[6], 0});
    }

    public static byte[] d() {
        LogUtil.c("DataParser", "send get version cmd.");
        return b(new byte[]{-37, 1, BaseType.Double});
    }

    public static byte[] c(int i, int i2, byte b) {
        LogUtil.c("DataParser", "send ota updarge request cmd. cs:", Byte.valueOf(b));
        return b(new byte[]{-37, 6, 13, (byte) i, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), b, 0});
    }

    public static byte[] b() {
        LogUtil.c("DataParser", "send ota finish cmd.");
        return b(new byte[]{-37, 5, 13, -86, 0, 0, 0});
    }

    public static byte[] a(byte[] bArr, int i) {
        if (bArr == null) {
            return bArr;
        }
        LogUtil.c("DataParser", "send ota package data cmd.");
        int length = bArr.length;
        LogUtil.c("DataParser", "pkg data len:", Integer.valueOf(length), ",data is : ", HexUtils.d(bArr));
        byte[] bArr2 = new byte[20];
        byte b = 0;
        bArr2[0] = -35;
        bArr2[1] = BaseType.Vector64;
        bArr2[2] = (byte) (i & 255);
        bArr2[3] = (byte) ((i >> 8) & 255);
        int i2 = 4;
        int i3 = 0;
        while (i2 < 20) {
            if (i3 >= length) {
                bArr2[i2] = -1;
            } else {
                bArr2[i2] = bArr[i3];
            }
            i2++;
            i3++;
        }
        for (int i4 = 2; i4 < 20; i4++) {
            b = (byte) (bArr2[i4] ^ b);
        }
        bArr2[1] = b;
        return b(bArr2);
    }

    public static byte[] c() {
        return b(new byte[]{-37, 1, 32});
    }

    public static byte[] a() {
        return b(new byte[]{-37, 2, BaseType.Array, 0});
    }

    public static byte[] a(String str) {
        byte[] bArr = new byte[10];
        if (TextUtils.isEmpty(str)) {
            return bArr;
        }
        bArr[0] = -37;
        bArr[1] = 8;
        bArr[2] = 36;
        c(bArr, h(str));
        return b(bArr);
    }

    public static byte[] b(String str) {
        byte[] bArr = new byte[10];
        if (TextUtils.isEmpty(str)) {
            return bArr;
        }
        bArr[0] = -37;
        bArr[1] = 8;
        bArr[2] = 37;
        c(bArr, h(str));
        return b(bArr);
    }

    private static void c(byte[] bArr, byte[] bArr2) {
        bArr[3] = bArr2[0];
        bArr[4] = bArr2[1];
        bArr[5] = bArr2[2];
        bArr[6] = bArr2[3];
        bArr[7] = bArr2[4];
        bArr[8] = bArr2[5];
        bArr[9] = bArr2[6];
    }

    public static byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[19];
        if (bArr == null || bArr.length < 16) {
            return bArr2;
        }
        int i = 0;
        bArr2[0] = -37;
        bArr2[1] = BaseType.Union;
        bArr2[2] = 4;
        int i2 = 3;
        while (i2 < 19) {
            bArr2[i2] = bArr[i];
            i2++;
            i++;
        }
        return b(bArr2);
    }

    public static byte[] d(byte[] bArr) {
        byte[] bArr2 = new byte[19];
        if (bArr == null || bArr.length < 16) {
            return bArr2;
        }
        int i = 0;
        bArr2[0] = -37;
        bArr2[1] = BaseType.Union;
        bArr2[2] = 5;
        int i2 = 3;
        while (i2 < 19) {
            bArr2[i2] = bArr[i];
            i2++;
            i++;
        }
        return b(bArr2);
    }

    public static byte[] b(byte[] bArr) {
        if (bArr != null && bArr.length >= 1) {
            if ((bArr[0] & 255) == 219) {
                byte[] bArr2 = (byte[]) bArr.clone();
                if (f699a != null) {
                    int i = 0;
                    int i2 = 3;
                    while (i2 < bArr2.length) {
                        bArr2[i2] = (byte) (bArr2[i2] ^ f699a[i % 6]);
                        i2++;
                        i++;
                    }
                }
                LogUtil.a("DataParser", "command data before encrypt ", HexUtils.d(bArr2));
                if ((bArr2[2] & 255) == 36) {
                    byte[] bArr3 = new byte[7];
                    System.arraycopy(bArr2, 3, bArr3, 0, 7);
                    LogUtil.c("DataParser", "set auth keyArr:", HexUtils.d(bArr3));
                    cgi.d(bArr3);
                } else {
                    LogUtil.c("DataParser", "data cmd no aes encrypt");
                }
                if (bArr2[2] == 9) {
                    int length = bArr2.length - 3;
                    byte[] bArr4 = new byte[length];
                    System.arraycopy(bArr2, 3, bArr4, 0, length);
                    byte[] c = cgi.c(bArr4);
                    if (c != null) {
                        byte[] bArr5 = new byte[c.length + 3];
                        System.arraycopy(bArr2, 0, bArr5, 0, 3);
                        System.arraycopy(c, 0, bArr5, 3, c.length);
                        bArr5[0] = -36;
                        LogUtil.c("DataParser", "after encrypt ：", HexUtils.d(bArr5));
                        return bArr5;
                    }
                    LogUtil.h("DataParser", "encryptData is null");
                    return bArr2;
                }
                LogUtil.h("DataParser", "cmd is no send user info");
                return bArr2;
            }
            LogUtil.c("DataParser", "data cmd not encrypt");
        }
        return bArr;
    }

    private static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return Base64.encodeToString(e(str.getBytes("utf-8")), 2) + "abcd";
        } catch (UnsupportedEncodingException e2) {
            LogUtil.b("DataParser", "dataparse ", e2.getMessage());
            return "";
        }
    }

    private static byte[] h(String str) {
        int i;
        byte[] bArr = new byte[7];
        if (TextUtils.isEmpty(str)) {
            return bArr;
        }
        String d = d(str);
        if (TextUtils.isEmpty(d)) {
            return bArr;
        }
        LogUtil.c(" bytes is not empty", new Object[0]);
        char[] charArray = d.toCharArray();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < charArray.length && i3 < 7) {
            int i5 = 0;
            int i6 = i2;
            while (true) {
                i = i2 + 8;
                if (i6 < i) {
                    i5 ^= Integer.valueOf(charArray[i6]).intValue();
                    i6++;
                }
            }
            bArr[i3] = (byte) i5;
            i4 ^= i5;
            i3++;
            i2 = i;
        }
        if (i4 == 0) {
            bArr[6] = 1;
        } else {
            bArr[6] = (byte) i4;
        }
        return bArr;
    }

    public static byte[] e(byte[] bArr) {
        try {
            return bArr == null ? new byte[0] : MessageDigest.getInstance("SHA-256").digest(bArr);
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.b("DataParser", "NoSuchAlgorithmException", e2.getMessage());
            return new byte[0];
        }
    }
}
