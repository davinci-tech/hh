package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import health.compact.a.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class iyq {
    private static int[] b = {0, 4129, 8258, 12387, 16516, 20645, 24774, 28903, 33032, 37161, 41290, 45419, 49548, 53677, 57806, 61935, 4657, 528, 12915, 8786, 21173, 17044, 29431, 25302, 37689, 33560, 45947, 41818, 54205, 50076, 62463, 58334, 9314, 13379, 1056, 5121, 25830, 29895, 17572, 21637, 42346, 46411, 34088, 38153, 58862, 62927, 50604, 54669, 13907, 9842, 5649, 1584, 30423, 26358, 22165, 18100, 46939, 42874, 38681, 34616, 63455, 59390, 55197, 51132, 18628, 22757, 26758, 30887, 2112, 6241, 10242, 14371, 51660, 55789, 59790, 63919, 35144, 39273, 43274, 47403, 23285, 19156, 31415, 27286, 6769, 2640, 14899, 10770, 56317, 52188, 64447, 60318, 39801, 35672, 47931, 43802, 27814, 31879, 19684, 23749, 11298, 15363, 3168, 7233, 60846, 64911, 52716, 56781, 44330, 48395, 36200, 40265, 32407, 28342, 24277, 20212, 15891, 11826, 7761, 3696, 65439, 61374, 57309, 53244, 48923, 44858, 40793, 36728, 37256, 33193, 45514, 41451, 53516, 49453, 61774, 57711, 4224, MachineControlPointResponse.OP_CODE_EXTENSION_SET_TOTAL_ENERGY, 12482, 8419, 20484, 16421, 28742, 24679, 33721, 37784, 41979, 46042, 49981, 54044, 58239, 62302, 689, 4752, 8947, 13010, 16949, 21012, 25207, 29270, 46570, 42443, 38312, 34185, 62830, 58703, 54572, 50445, 13538, 9411, 5280, 1153, 29798, 25671, 21540, 17413, 42971, 47098, 34713, 38840, 59231, 63358, 50973, 55100, 9939, 14066, 1681, 5808, 26199, 30326, 17941, 22068, 55628, 51565, 63758, 59695, 39368, 35305, 47498, 43435, 22596, 18533, 30726, 26663, 6336, 2273, 14466, 10403, 52093, 56156, 60223, 64286, 35833, 39896, 43963, 48026, 19061, 23124, 27191, 31254, GLMapStaticValue.AM_PARAMETERNAME_RENDER_COMPLETE, 6864, 10931, 14994, 64814, 60687, 56684, 52557, 48554, 44427, 40424, 36297, 31782, 27655, 23652, 19525, 15522, 11395, 7392, 3265, 61215, 65342, 53085, 57212, 44955, 49082, 36825, 40952, 28183, 32310, 20053, 24180, 11923, 16050, 3793, 7920};

    /* renamed from: a, reason: collision with root package name */
    private static iyq f13670a = null;
    private Map<String, String> h = new HashMap(4);
    private Map<String, String> g = new ConcurrentHashMap(4);
    private byte[] d = a(c(), d(), e());
    private byte[] c = a(a(), b(), f());
    private byte[] e = blq.a(j());
    private byte[] f = blq.a(h());

    private iyq(Context context) {
    }

    private static byte[] c() {
        return d(bmo.e(1, 27).toCharArray());
    }

    private static byte[] d() {
        return d(bmo.e(1, 1027).toCharArray());
    }

    private static byte[] e() {
        return d(bmo.e(1, 2027).toCharArray());
    }

    private static byte[] a() {
        return d(bmo.e(1, 28).toCharArray());
    }

    private static byte[] b() {
        return d(bmo.e(1, 1028).toCharArray());
    }

    private static byte[] f() {
        return d(bmo.e(1, 2028).toCharArray());
    }

    private static String j() {
        return bmo.e(1, 29);
    }

    private static String h() {
        return bmo.e(1, 1029);
    }

    private static byte[] d(char[] cArr) {
        byte[] bArr = new byte[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        byte[] bArr5 = null;
        ByteArrayOutputStream byteArrayOutputStream3 = null;
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (IOException unused) {
            bArr4 = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(bArr2);
            byteArrayOutputStream.write(bArr3);
            bArr5 = byteArrayOutputStream.toByteArray();
        } catch (IOException unused2) {
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            blv.d(byteArrayOutputStream2);
            throw th;
        }
        bArr4 = bArr5;
        byteArrayOutputStream3 = byteArrayOutputStream;
        blv.d(byteArrayOutputStream3);
        return bArr4;
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (TextUtils.isEmpty(this.h.get(str))) {
            return c(str, BaseApplication.e());
        }
        return this.h.get(str);
    }

    public static iyq b(Context context) {
        iyq iyqVar;
        synchronized (iyq.class) {
            if (f13670a == null) {
                f13670a = new iyq(context);
            }
            iyqVar = f13670a;
        }
        return iyqVar;
    }

    public String b(int i, String str, Context context, byte[] bArr, DeviceInfo deviceInfo) {
        String str2;
        String c = c(str, context);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "CommandPackage", "createKey() begin");
        byte[] e = e(i, str);
        if (TextUtils.isEmpty(c)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("btsdk_sharedpreferences_name4", 0);
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "createKey() SharedPreferences.size() : ", Integer.valueOf(sharedPreferences.getAll() != null ? sharedPreferences.getAll().size() : 0));
            byte[] i2 = i();
            if (bArr != null) {
                byte[] c2 = iyx.c(1, i2, e, bArr);
                if (c2 != null) {
                    c = blq.d(bArr) + blq.d(c2);
                } else {
                    LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "keysencrypt is null.");
                }
            } else {
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "CommandPackage", "randIV is null");
            }
            str2 = c(c, i2);
        } else {
            c = e(context, c, bArr, e, deviceInfo);
            str2 = "";
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "createKey() finish");
        this.h.put(str, str2);
        this.g.put(str, "");
        return c;
    }

    private byte[] e(int i, String str) {
        byte[] a2 = blq.a(blq.b(str.replace(":", "") + AgdConstant.INSTALL_TYPE_DEFAULT));
        if (i == 3) {
            return b(this.e, this.f, a2);
        }
        return b(this.d, this.c, a2);
    }

    private String e(Context context, String str, byte[] bArr, byte[] bArr2, DeviceInfo deviceInfo) {
        byte[] a2 = blq.a(e(context, str, deviceInfo));
        if (bArr == null) {
            return str;
        }
        byte[] c = iyx.c(1, a2, bArr2, bArr);
        if (c != null) {
            String str2 = blq.d(bArr) + blq.d(c);
            LogUtil.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "processSpecialKeyFromSharePreference key:", str2);
            return str2;
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "keySencrypt is null.");
        return str;
    }

    public String c(String str, Context context) {
        if (str == null || context == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "getKeyFromSharedpreferences with parameter incorrect.");
            return null;
        }
        String string = context.getSharedPreferences("btsdk_sharedpreferences_name4", 0).getString(str, "");
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "getKeyFromSharedpreferences ok");
        return string;
    }

    public String c(String str, Context context, DeviceInfo deviceInfo, byte[] bArr) {
        String str2 = null;
        if (str != null && !str.equals("")) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "decrypt before");
            byte[] a2 = b(context).a(deviceInfo.getAuthVersion());
            byte[] c = b(context).c(deviceInfo.getAuthVersion());
            byte[] a3 = blq.a(blq.b(deviceInfo.getDeviceIdentify().replace(":", "") + AgdConstant.INSTALL_TYPE_DEFAULT));
            if (bArr != null) {
                str2 = blq.d(iyx.a(1, blq.a(str), b(a2, c, a3), bArr));
            } else {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "CommandPackage", "randIV is null");
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "decrypt after ");
        }
        return str2;
    }

    public void d(String str, String str2, String str3, Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str3, 0).edit();
        edit.putString(str, str2);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "putKeyToSharedpreferences()");
        edit.commit();
    }

    public String b(String str, Context context) {
        if (str == null || context == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "getBindIdFromSharedpreferences with parameter incorrect.");
            return null;
        }
        String string = context.getSharedPreferences("btsdk_sharedpreferences_bindid", 0).getString(str, "");
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "getBindIdFromSharedpreferences ok, boidId = " + blq.d(string));
        return string;
    }

    private byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr != null && bArr2 != null && bArr3 != null) {
            byte[] bArr4 = new byte[bArr.length];
            if (bArr.length != bArr2.length) {
                return null;
            }
            for (int i = 0; i < bArr.length; i++) {
                bArr4[i] = (byte) ((bArr[i] << 4) ^ bArr2[i]);
            }
            byte[] c = iyx.c(bArr4);
            String d = blq.d(c);
            int length = c.length;
            byte[] bArr5 = new byte[length];
            if (length == 0 || bArr3.length == 0) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "keydata len or C_3 len = 0.");
            } else {
                for (int i2 = 0; i2 < c.length; i2++) {
                    int i3 = i2 * 2;
                    try {
                        bArr5[i2] = (byte) ((Integer.parseInt(d.substring(i3, i3 + 2), 16) >> 6) ^ bArr3[i2]);
                    } catch (NumberFormatException unused) {
                        LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "NumberFormatException");
                    }
                }
                return iyx.c(bArr5);
            }
        }
        return null;
    }

    public void e(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("btsdk_sharedpreferences_name4", 0).edit();
        edit.clear();
        SharedPreferences.Editor edit2 = context.getSharedPreferences("btsdk_sharedpreferences_bindid", 0).edit();
        edit2.clear();
        edit.commit();
        edit2.commit();
    }

    private byte[] i() {
        try {
            return iyx.a(16);
        } catch (NoSuchAlgorithmException e) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "getRandomNum exception with info:", ExceptionUtils.d(e));
            return null;
        }
    }

    public byte[] a(byte[] bArr) {
        short s = 0;
        for (byte b2 : bArr) {
            s = (short) ((s << 8) ^ b[(b2 ^ (s >> 8)) & 255]);
        }
        byte[] bArr2 = {0, 0};
        bArr2[1] = (byte) s;
        bArr2[0] = (byte) (s >> 8);
        return bArr2;
    }

    public byte[] a(int i) {
        Throwable th;
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th2;
        byte[] bArr2;
        ByteArrayOutputStream byteArrayOutputStream2;
        ByteArrayOutputStream byteArrayOutputStream3 = null;
        byte[] bArr3 = null;
        byte[] bArr4 = null;
        ByteArrayOutputStream byteArrayOutputStream4 = null;
        ByteArrayOutputStream byteArrayOutputStream5 = null;
        ByteArrayOutputStream byteArrayOutputStream6 = null;
        if (3 == i) {
            if (this.e == null) {
                return null;
            }
            try {
                byteArrayOutputStream2 = new ByteArrayOutputStream();
            } catch (IOException unused) {
                bArr2 = null;
            } catch (Throwable th3) {
                th2 = th3;
            }
            try {
                byteArrayOutputStream2.write(this.e);
                bArr4 = byteArrayOutputStream2.toByteArray();
            } catch (IOException unused2) {
            } catch (Throwable th4) {
                th2 = th4;
                byteArrayOutputStream5 = byteArrayOutputStream2;
                blv.d(byteArrayOutputStream5);
                throw th2;
            }
            byte[] bArr5 = bArr4;
            byteArrayOutputStream4 = byteArrayOutputStream2;
            bArr2 = bArr5;
            blv.d(byteArrayOutputStream4);
            return bArr2;
        }
        if (this.d == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (IOException unused3) {
            bArr = null;
        } catch (Throwable th5) {
            th = th5;
        }
        try {
            byteArrayOutputStream.write(this.d);
            bArr3 = byteArrayOutputStream.toByteArray();
        } catch (IOException unused4) {
        } catch (Throwable th6) {
            th = th6;
            byteArrayOutputStream3 = byteArrayOutputStream;
            blv.d(byteArrayOutputStream3);
            throw th;
        }
        byte[] bArr6 = bArr3;
        byteArrayOutputStream6 = byteArrayOutputStream;
        bArr = bArr6;
        blv.d(byteArrayOutputStream6);
        return bArr;
    }

    public byte[] c(int i) {
        Throwable th;
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th2;
        byte[] bArr2;
        ByteArrayOutputStream byteArrayOutputStream2;
        ByteArrayOutputStream byteArrayOutputStream3 = null;
        byte[] bArr3 = null;
        byte[] bArr4 = null;
        ByteArrayOutputStream byteArrayOutputStream4 = null;
        ByteArrayOutputStream byteArrayOutputStream5 = null;
        ByteArrayOutputStream byteArrayOutputStream6 = null;
        if (3 == i) {
            if (this.f == null) {
                return null;
            }
            try {
                byteArrayOutputStream2 = new ByteArrayOutputStream();
            } catch (IOException unused) {
                bArr2 = null;
            } catch (Throwable th3) {
                th2 = th3;
            }
            try {
                byteArrayOutputStream2.write(this.f);
                bArr4 = byteArrayOutputStream2.toByteArray();
            } catch (IOException unused2) {
            } catch (Throwable th4) {
                th2 = th4;
                byteArrayOutputStream5 = byteArrayOutputStream2;
                blv.d(byteArrayOutputStream5);
                throw th2;
            }
            byte[] bArr5 = bArr4;
            byteArrayOutputStream4 = byteArrayOutputStream2;
            bArr2 = bArr5;
            blv.d(byteArrayOutputStream4);
            return bArr2;
        }
        if (this.c == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (IOException unused3) {
            bArr = null;
        } catch (Throwable th5) {
            th = th5;
        }
        try {
            byteArrayOutputStream.write(this.c);
            bArr3 = byteArrayOutputStream.toByteArray();
        } catch (IOException unused4) {
        } catch (Throwable th6) {
            th = th6;
            byteArrayOutputStream3 = byteArrayOutputStream;
            blv.d(byteArrayOutputStream3);
            throw th;
        }
        byte[] bArr6 = bArr3;
        byteArrayOutputStream6 = byteArrayOutputStream;
        bArr = bArr6;
        blv.d(byteArrayOutputStream6);
        return bArr;
    }

    public static String d(String str) {
        return TextUtils.isEmpty(str) ? "" : str.length() > 30 ? str.substring(0, 30) : str;
    }

    private String c(String str, byte[] bArr) {
        String a2 = bmy.a(bArr);
        bgq bgqVar = new bgq();
        bgqVar.b(a2);
        return new Gson().toJson(bgqVar);
    }

    public String e(Context context, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "getWorkKey deviceInfo is null");
            return null;
        }
        String str = this.g.get(deviceInfo.getDeviceIdentify());
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String a2 = a(deviceInfo.getDeviceIdentify());
        if (a2 == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "encryptTLVs null: key");
            return a2;
        }
        LogUtil.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "Encrypt Key ： ", a2);
        String e = e(context, a2, deviceInfo);
        if (!TextUtils.isEmpty(e)) {
            this.g.put(deviceInfo.getDeviceIdentify(), e);
        }
        return e;
    }

    public String e(Context context, String str, DeviceInfo deviceInfo) {
        String d;
        try {
            bgq bgqVar = (bgq) new Gson().fromJson(str, bgq.class);
            if (bgqVar != null && bgqVar.b() == 1) {
                d = blq.d(bmy.c(bgqVar.c()));
                LogUtil.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "decryptWorkKey byteToHex key:", d);
            } else {
                d = d(context, str, deviceInfo);
            }
            return d;
        } catch (JsonSyntaxException unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "low version key");
            return d(context, str, deviceInfo);
        }
    }

    private String d(Context context, String str, DeviceInfo deviceInfo) {
        if (str.length() > 64) {
            return c(str.substring(32, str.length()), context, deviceInfo, blq.a(str.substring(0, 32)));
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "CommandPackage", "key info is incorrect.");
        return null;
    }
}
