package defpackage;

import android.text.TextUtils;
import android.util.Base64;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.google.gson.JsonObject;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bhh {
    private static ConcurrentHashMap<String, String> c = new ConcurrentHashMap<>();

    public static boolean d(byte[] bArr) {
        bmj e = e(bArr);
        if (e == null) {
            LogUtil.a("HandshakeUtils", "checkResponseCode tlvFather is null");
            return false;
        }
        List<bmi> b = e.b();
        if (bkz.e(b) && bkz.e(e.e())) {
            LogUtil.a("HandshakeUtils", "father tlvList is empty and sub tlvFatherList is empty");
            return false;
        }
        if (b.size() <= 0) {
            return true;
        }
        try {
            return c(b, bli.e(b.get(0).e()));
        } catch (NumberFormatException unused) {
            LogUtil.c("HandshakeUtils", "checkResponseCode NumberFormatException");
            return false;
        }
    }

    private static boolean c(List<bmi> list, int i) {
        if (i != 127) {
            return true;
        }
        try {
            int e = bli.e(list.get(0).c());
            if (e == 100000) {
                return true;
            }
            LogUtil.c("HandshakeUtils", "error code = ", Integer.valueOf(e));
            return false;
        } catch (NumberFormatException unused) {
            LogUtil.e("HandshakeUtils", "NumberFormatException");
            return false;
        }
    }

    public static boolean c(DeviceInfo deviceInfo, biu biuVar) {
        if (deviceInfo != null && biuVar != null && biuVar.a() != null) {
            return true;
        }
        LogUtil.c("HandshakeUtils", "Parameter Check Failed");
        return false;
    }

    public static bmj e(byte[] bArr) {
        String d = blq.d(bArr);
        if (d == null) {
            LogUtil.a("HandshakeUtils", "parseDataToTlvFormat dataStrInfo is null");
            return null;
        }
        try {
            return new bmn().c(d.substring(4, d.length()));
        } catch (bmk unused) {
            LogUtil.e("HandshakeUtils", "tlv resolve exception.");
            return null;
        }
    }

    public static void e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.e("HandshakeUtils", "setPin: params is incorrect");
        } else {
            c.put(str, str2);
        }
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("HandshakeUtils", "getPin: deviceIdentify is empty");
            return "";
        }
        return c.get(str);
    }

    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("HandshakeUtils", "getPin: deviceIdentify is empty");
        } else {
            c.remove(str);
        }
    }

    public static TimeZone b() {
        TimeZone timeZone = null;
        for (int i = 0; i < 5; i++) {
            timeZone = TimeZone.getDefault();
            if (Calendar.getInstance(timeZone).get(1) >= 2000) {
                LogUtil.a("HandshakeUtils", "getTimeZone nowYear >= VALID_YEAR:" + i);
                return timeZone;
            }
            if (i < 4) {
                try {
                    Thread.sleep(5L);
                } catch (InterruptedException unused) {
                    LogUtil.e("HandshakeUtils", "Thread sleep InterruptedException");
                }
            }
        }
        return timeZone;
    }

    public static byte[] d() {
        TimeZone b = b();
        if (b == null) {
            LogUtil.e("HandshakeUtils", "timeZone is null");
            return new byte[0];
        }
        int offset = b.getOffset(System.currentTimeMillis()) / 3600;
        int i = offset / 1000;
        int abs = (Math.abs(offset % 1000) * 60) / 1000;
        if (i < 0) {
            i = Math.abs(i) + 128;
        }
        byte[] c2 = c((i << 8) + abs);
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = (int) (currentTimeMillis / 1000);
        if (((int) (currentTimeMillis % 1000)) >= 500) {
            i2++;
        }
        byte[] e = e(i2);
        byte[] bArr = new byte[c2.length + e.length];
        System.arraycopy(e, 0, bArr, 0, e.length);
        System.arraycopy(c2, 0, bArr, e.length, c2.length);
        ReleaseLogUtil.b("DEVMGR_HandshakeUtils", "connect Zone 5.1.5 content:", blq.d(bArr));
        return bArr;
    }

    private static byte[] c(int i) {
        byte[] g = blq.g(i >> 8);
        byte[] g2 = blq.g(i & 255);
        byte[] bArr = new byte[g.length + 2 + g2.length];
        bArr[0] = 2;
        bArr[1] = 2;
        System.arraycopy(g, 0, bArr, 2, g.length);
        System.arraycopy(g2, 0, bArr, g.length + 2, g2.length);
        return bArr;
    }

    private static byte[] e(int i) {
        byte[] g = blq.g(i >> 24);
        byte[] g2 = blq.g((i >> 16) & 255);
        byte[] g3 = blq.g((i >> 8) & 255);
        byte[] g4 = blq.g(i & 255);
        byte[] bArr = new byte[g.length + 2 + g2.length + g3.length + g4.length];
        bArr[0] = 1;
        bArr[1] = 4;
        System.arraycopy(g, 0, bArr, 2, g.length);
        System.arraycopy(g2, 0, bArr, g.length + 2, g2.length);
        System.arraycopy(g3, 0, bArr, g.length + 2 + g2.length, g3.length);
        System.arraycopy(g4, 0, bArr, g.length + 2 + g2.length + g3.length, g4.length);
        return bArr;
    }

    public static String a(String str) {
        LogUtil.c("HandshakeUtils", "Start to construct key info.");
        if (str != null && str.length() > 32) {
            String substring = str.substring(32);
            return blq.b(6) + blq.b(substring.length() / 2) + substring;
        }
        LogUtil.e("HandshakeUtils", "createBondKey is error, key is null");
        return "";
    }

    public static String c(biw biwVar, String str, String str2) {
        LogUtil.c("HandshakeUtils", "Enter getAuthenticTokenValue.");
        if (biwVar != null && biwVar.j() != null && str != null) {
            String j = biwVar.j();
            if (j.length() / 2 == 16 && str.length() / 2 == 16) {
                byte[] a2 = blq.a(j);
                byte[] a3 = blq.a(str);
                byte[] bArr = new byte[32];
                System.arraycopy(a2, 0, bArr, 0, 16);
                System.arraycopy(a3, 0, bArr, 16, 16);
                try {
                    return blq.d(bgv.d(bgv.d(blq.a(b(biwVar.a()) + str2), bArr), bArr));
                } catch (UnsupportedEncodingException unused) {
                    LogUtil.e("HandshakeUtils", "HMac256 occur unsupportedEncodingException");
                    return "";
                } catch (InvalidKeyException unused2) {
                    LogUtil.e("HandshakeUtils", "HMac256 occur InvalidKeyException");
                    return "";
                } catch (NoSuchAlgorithmException unused3) {
                    LogUtil.e("HandshakeUtils", "HMac256 occur noSuchAlgorithmException");
                    return "";
                }
            }
            LogUtil.e("HandshakeUtils", "Authentic Random parameter is incorrect.");
        }
        return "";
    }

    public static String b(int i) {
        LogUtil.c("HandshakeUtils", "getCak enter");
        if (i == 1) {
            return bmx.b(bmo.e(1, 30) + bmo.e(1, GLMapStaticValue.MAP_PARAMETERNAME_SCENIC) + bmo.e(1, 2030));
        }
        if (i != 2) {
            if (i != 3) {
                LogUtil.c("HandshakeUtils", "no support SECRET_KEY", Integer.valueOf(i));
            } else {
                try {
                    byte[] a2 = bmo.a(Base64.decode(bmo.e(1, 23) + bmo.e(1, 1023) + bmo.e(1, 2023), 0));
                    if (a2 != null) {
                        return new String(a2, "UTF-8");
                    }
                    LogUtil.a("HandshakeUtils", "getCak bytes is null.");
                    return "";
                } catch (UnsupportedEncodingException unused) {
                    LogUtil.e("HandshakeUtils", "getCak occur UnsupportedEncodingException");
                }
            }
            LogUtil.c("HandshakeUtils", "getCak exception, the mAuthenticVersion is ", Integer.valueOf(i));
            return "";
        }
        return bmx.b(bmo.e(1, 31) + bmo.e(1, 1031) + bmo.e(1, 2031));
    }

    public static byte[] a(int i) {
        return blq.d(i);
    }

    public static String d(int i) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("identify", "");
        jsonObject.addProperty("isUseSynergy", "");
        jsonObject.addProperty("isCompatibleDevice", "");
        jsonObject.addProperty("pairMode", "");
        if (i == 3) {
            jsonObject.addProperty("isConnectNewPhone", "");
        }
        return jsonObject.toString();
    }

    public static boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.e("HandshakeUtils", "isCompatibleDevice: params is incorrect");
            return false;
        }
        biz i = bjx.a().i(deviceInfo.getDeviceMac());
        if (i == null) {
            LogUtil.e("HandshakeUtils", "isCompatibleDevice: preConnectParameter is null");
            return false;
        }
        return i.d();
    }

    public static void e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.e("HandshakeUtils", "btDeviceInfo is null");
            return;
        }
        int b = blx.b(snu.e().getBrand(deviceInfo.getDeviceType()));
        if (deviceInfo.getDeviceType() < 74 || b != 2) {
            LogUtil.c("HandshakeUtils", "deviceType: ", Integer.valueOf(deviceInfo.getDeviceType()), "manuType: ", Integer.valueOf(b));
            return;
        }
        String deviceMode = deviceInfo.getDeviceMode();
        String d = blz.d("honor_device_model_list", "");
        LogUtil.c("HandshakeUtils", "deviceModel: ", deviceMode, "honorDeviceModelList: ", d);
        if (!TextUtils.isEmpty(d)) {
            if (!d.contains(deviceMode)) {
                deviceMode = d + ";" + deviceMode;
            } else {
                LogUtil.c("HandshakeUtils", "honorDeviceModelList contains deviceModel");
                return;
            }
        }
        blz.a("honor_device_model_list", deviceMode);
        cfc.e().a();
        LogUtil.c("HandshakeUtils", "saveHonorDeviceModel end");
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 4) {
            return "";
        }
        return "ServiceID = " + str.substring(0, 2) + ", CommandId = " + str.substring(2);
    }
}
