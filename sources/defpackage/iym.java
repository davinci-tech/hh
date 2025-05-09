package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import okio.Utf8;

/* loaded from: classes5.dex */
public class iym {
    private static int b = 0;
    private static boolean c = true;

    public static String e(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 32) {
            return null;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManagerHelp", "Start to construct key info.");
        String substring = str.substring(32, str.length());
        String str2 = blq.b(6) + blq.b(substring.length() / 2) + substring;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManagerHelp", "Start to construct iv info.");
        return str2;
    }

    public static byte[] c(Context context, byte[] bArr, DeviceInfo deviceInfo, byte[] bArr2) {
        String a2 = a(context, deviceInfo, bArr2);
        if (a2 == null) {
            LogUtil.a("BTHandshakeManagerHelp", "key is null.");
            return null;
        }
        if (!TextUtils.isEmpty(a2)) {
            bArr = iyx.c(1, bArr, blq.a(a2), bArr2);
        }
        blt.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, bArr, 0, "BTHandshakeManagerHelp", " mDevice: ", iyl.d().e(deviceInfo.getDeviceIdentify()), "encryptTLVs After encrypt result:");
        return bArr;
    }

    private static String a(Context context, DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManagerHelp", "randIv is null");
            return null;
        }
        String e = iyq.b(context).e(context, deviceInfo);
        if (e == null) {
            iyq.b(BaseApplication.e()).d(deviceInfo.getDeviceIdentify(), "", "btsdk_sharedpreferences_name4", BaseApplication.e());
        }
        return e;
    }

    public static void a(Context context, int i, String str, DeviceCapability deviceCapability, boolean z) {
        boolean z2;
        int i2;
        if (context == null || i == 0 || deviceCapability == null) {
            LogUtil.a("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManagerHelp", "parseCommandIDInfo with parameter is not correct.");
            return;
        }
        List<Integer> list = ble.d(z).get(Integer.valueOf(i));
        if (list == null || list.size() <= 0 || str == null) {
            return;
        }
        int i3 = 0;
        while (i3 < str.length()) {
            int i4 = i3 + 2;
            try {
                z2 = Integer.parseInt(str.substring(i3, i4), 16) == 1;
                i2 = i3 / 2;
            } catch (NumberFormatException unused) {
                LogUtil.e("BTHandshakeManagerHelp", "parseCommandIDInfo exception");
            }
            if (list.size() <= i2) {
                return;
            }
            iyz.c(i, list.get(i2).intValue(), z2, deviceCapability);
            i3 = i4;
        }
    }

    public static String c(String str, String str2) {
        Object[] objArr = new Object[4];
        objArr[0] = 1;
        objArr[1] = "BTHandshakeManagerHelp";
        objArr[2] = "Phone Mac Address is:";
        objArr[3] = Boolean.valueOf(str == null);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, objArr);
        if (str == null || str.length() == 0) {
            return str2;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManagerHelp", "Phone Mac Address is:", iyl.d().e(str));
        return str;
    }

    public static izf c(byte[] bArr) {
        if (bArr == null) {
            return new izf();
        }
        izf izfVar = new izf();
        izfVar.e(bArr.length);
        izfVar.e(bArr);
        izfVar.b(1);
        izfVar.e(true);
        izfVar.i(1);
        izfVar.d(15);
        return izfVar;
    }

    public static izf e(byte[] bArr) {
        if (bArr == null) {
            return new izf();
        }
        izf izfVar = new izf();
        izfVar.e(bArr.length);
        izfVar.e(bArr);
        izfVar.b(1);
        izfVar.e(true);
        izfVar.i(1);
        izfVar.d(14);
        return izfVar;
    }

    public static izf e(int i, int i2) {
        izf izfVar = new izf();
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManagerHelp", " btType: ", Integer.valueOf(i));
        if (i == 0 || i == 5) {
            a(izfVar);
        } else {
            c(i2, izfVar);
        }
        izfVar.b(1);
        izfVar.e(true);
        izfVar.i(1);
        izfVar.d(7);
        return izfVar;
    }

    private static void c(int i, izf izfVar) {
        byte[] bArr = {1, 7, 1, 0, 2, 0, 7, 0, 9, 0, 10, 0, BaseType.Array, 0, BaseType.Vector64, 0, 22, 0, 26, 0, 29, 0, 30, 0, 31, 0, 32, 0, PublicSuffixDatabase.i, 0, 34, 0};
        if (bku.b(i)) {
            bArr = new byte[]{1, 7, 1, 0, 2, 0, 7, 0, 9, 0};
        }
        izfVar.e(bArr.length);
        izfVar.e(bArr);
        blt.d("BTHandshakeManagerHelp", bArr, "dataContent: ");
    }

    private static void a(izf izfVar) {
        byte[] bArr = {1, 7, 1, 0, 2, 0, 3, 0, 7, 0, 9, 0, 10, 0, BaseType.Double, 0, BaseType.Array, 0};
        izfVar.e(18);
        izfVar.e(bArr);
    }

    public static izf b(int i) {
        izf izfVar = new izf();
        a();
        c(true);
        if (i == 0) {
            izfVar.e(12);
            izfVar.e(new byte[]{1, 1, 1, 0, 2, 0, 3, 0, 4, 0, 6, 0});
        } else {
            izfVar.e(10);
            izfVar.e(new byte[]{1, 1, 1, 0, 2, 0, 3, 0, 4, 0});
        }
        izfVar.b(1);
        izfVar.e(true);
        izfVar.i(1);
        izfVar.d(1);
        return izfVar;
    }

    public static izf a(byte[] bArr) {
        izf izfVar = new izf();
        izfVar.e(bArr.length);
        izfVar.e(bArr);
        izfVar.b(2);
        izfVar.i(1);
        izfVar.d(5);
        return izfVar;
    }

    public static izf b(byte[] bArr) {
        if (bArr == null) {
            return new izf();
        }
        izf izfVar = new izf();
        izfVar.e(bArr.length);
        izfVar.e(bArr);
        izfVar.b(1);
        izfVar.e(true);
        izfVar.i(1);
        izfVar.d(5);
        return izfVar;
    }

    public static izf c(int i, String str, String str2, DeviceInfo deviceInfo) {
        if (str2 == null) {
            return new izf();
        }
        izf izfVar = new izf();
        byte[] a2 = blq.a(blq.b(1) + blq.b(19) + (blq.b(1) + blq.b(str.length() / 2) + str) + (blq.b(2) + blq.b((str2.length() / 2) + 2) + blq.c(i) + str2));
        izfVar.e(a2.length);
        izfVar.e(a2);
        izfVar.b(1);
        izfVar.e(true);
        izfVar.i(1);
        izfVar.d(19);
        return izfVar;
    }

    public static izf d(byte[] bArr) {
        if (bArr == null) {
            return new izf();
        }
        izf izfVar = new izf();
        izfVar.e(bArr.length);
        izfVar.e(bArr);
        izfVar.b(1);
        izfVar.e(true);
        izfVar.i(1);
        izfVar.d(2);
        return izfVar;
    }

    public static izf b(String str) {
        byte[] a2 = blq.a(str);
        izf izfVar = new izf();
        izfVar.e(a2.length);
        izfVar.e(a2);
        izfVar.b(1);
        izfVar.e(true);
        izfVar.i(1);
        izfVar.d(3);
        return izfVar;
    }

    public static izf j() {
        izf izfVar = new izf();
        izfVar.i(1);
        izfVar.d(18);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put((byte) 1);
        allocate.put(BaseType.Vector64);
        allocate.put((byte) -127);
        allocate.put((byte) 0);
        izfVar.e(allocate.array().length);
        izfVar.e(allocate.array());
        izfVar.b(1);
        izfVar.e(true);
        return izfVar;
    }

    public static izf h() {
        izf izfVar = new izf();
        izfVar.i(2);
        izfVar.d(5);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put((byte) 2);
        allocate.put((byte) 5);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        izfVar.e(allocate.array().length);
        izfVar.e(allocate.array());
        izfVar.b(1);
        izfVar.e(true);
        return izfVar;
    }

    public static izf e() {
        izf izfVar = new izf();
        izfVar.e(4);
        izfVar.e(new byte[]{1, 22, 1, 0});
        izfVar.b(1);
        izfVar.e(true);
        izfVar.i(1);
        izfVar.d(22);
        return izfVar;
    }

    public static izf d(int i) {
        izf izfVar = new izf();
        byte b2 = (1 != i && i == 0) ? (byte) 0 : (byte) 1;
        izfVar.e(5);
        izfVar.e(new byte[]{1, 22, 2, 1, b2});
        izfVar.b(1);
        izfVar.e(true);
        izfVar.i(1);
        izfVar.d(22);
        return izfVar;
    }

    public static izf i() {
        izf izfVar = new izf();
        izfVar.i(1);
        izfVar.d(20);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put((byte) 1);
        allocate.put((byte) 20);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        izfVar.e(allocate.array().length);
        izfVar.e(allocate.array());
        izfVar.b(1);
        izfVar.e(true);
        return izfVar;
    }

    public static izf a(String str) {
        izf izfVar = new izf();
        String b2 = blq.b(26);
        String b3 = blq.b(5);
        String b4 = blq.b(str);
        byte[] a2 = blq.a(b2 + b3 + (blq.b(1) + blq.a(blq.a(b4).length) + b4));
        izfVar.e(a2.length);
        izfVar.e(a2);
        izfVar.i(26);
        izfVar.d(5);
        return izfVar;
    }

    public static izf c() {
        izf izfVar = new izf();
        izfVar.i(38);
        izfVar.d(1);
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put((byte) 38);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        allocate.put((byte) 2);
        allocate.put((byte) 0);
        izfVar.e(allocate.array().length);
        izfVar.e(allocate.array());
        izfVar.b(1);
        izfVar.e(true);
        return izfVar;
    }

    public static izf f() {
        izf izfVar = new izf();
        izfVar.i(1);
        izfVar.d(49);
        ByteBuffer allocate = ByteBuffer.allocate(14);
        allocate.put((byte) 1);
        allocate.put((byte) 49);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        allocate.put((byte) 2);
        allocate.put((byte) 0);
        allocate.put((byte) 3);
        allocate.put((byte) 0);
        allocate.put((byte) 4);
        allocate.put((byte) 0);
        allocate.put((byte) 5);
        allocate.put((byte) 0);
        allocate.put((byte) 6);
        allocate.put((byte) 0);
        izfVar.e(allocate.array().length);
        izfVar.e(allocate.array());
        return izfVar;
    }

    public static izf b() {
        izf izfVar = new izf();
        izfVar.i(1);
        izfVar.d(53);
        ByteBuffer allocate = ByteBuffer.allocate(5);
        allocate.put((byte) 1);
        allocate.put((byte) 53);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        izfVar.e(allocate.array().length);
        izfVar.e(allocate.array());
        return izfVar;
    }

    public static izf j(byte[] bArr) {
        izf izfVar = new izf();
        izfVar.i(1);
        izfVar.d(63);
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 4);
        allocate.put((byte) 1);
        allocate.put(Utf8.REPLACEMENT_BYTE);
        allocate.put((byte) 1);
        allocate.put(blq.g(bArr.length));
        allocate.put(bArr);
        izfVar.e(allocate.array().length);
        izfVar.e(allocate.array());
        return izfVar;
    }

    public static izf g() {
        izf izfVar = new izf();
        izfVar.i(1);
        izfVar.d(55);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put((byte) 1);
        allocate.put((byte) 55);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        izfVar.e(allocate.array().length);
        izfVar.e(allocate.array());
        return izfVar;
    }

    public static izf m() {
        TimeZone b2 = bhh.b();
        int offset = b2.getOffset(System.currentTimeMillis()) / 3600;
        int i = offset / 1000;
        int abs = (Math.abs(offset % 1000) * 60) / 1000;
        if (i < 0) {
            i = Math.abs(i) + 128;
        }
        int i2 = (i << 8) + abs;
        String str = blq.b(2) + blq.b(2) + blq.b(i2 >> 8) + blq.b(i2 & 255);
        String b3 = blq.b(1);
        String b4 = blq.b(50);
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        if (((int) (System.currentTimeMillis() % 1000)) >= 500) {
            currentTimeMillis++;
        }
        String str2 = blq.b(1) + blq.b(4) + blq.b(currentTimeMillis >> 24) + blq.b((currentTimeMillis >> 16) & 255) + blq.b((currentTimeMillis >> 8) & 255) + blq.b(currentTimeMillis & 255);
        String id = b2.getID();
        byte[] a2 = blq.a(b3 + b4 + str2 + str + (blq.b(3) + blq.b(blq.b(id).length() / 2) + blq.b(id)));
        blt.d("BTHandshakeManagerHelp", a2, "dataContent: ");
        return g(a2);
    }

    private static izf g(byte[] bArr) {
        if (bArr == null) {
            return new izf();
        }
        izf izfVar = new izf();
        izfVar.e(bArr.length);
        izfVar.e(bArr);
        izfVar.b(1);
        izfVar.i(1);
        izfVar.d(50);
        return izfVar;
    }

    public static byte[] n() {
        try {
            byte[] a2 = iyx.a(16);
            if (a2 != null) {
                return a2;
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManagerHelp", "generateRandomBytes fail.");
            return null;
        } catch (NoSuchAlgorithmException e) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTHandshakeManagerHelp", "generateRandomBytes exception with info: ", ExceptionUtils.d(e));
            return null;
        }
    }

    public static int c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BTHandshakeManagerHelp", "deviceName is empty.");
            return -1;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        if (upperCase.contains("AW70".toUpperCase(Locale.ENGLISH)) || upperCase.contains("HUAWEI Band 3e-".toUpperCase(Locale.ENGLISH))) {
            return 23;
        }
        if (upperCase.contains("HONOR AW70".toUpperCase(Locale.ENGLISH)) || upperCase.contains("honor Band 4R-".toUpperCase(Locale.ENGLISH))) {
            return 24;
        }
        LogUtil.c("BTHandshakeManagerHelp", "getProductTypeOnlyAw70 default action.");
        return -1;
    }

    public static boolean l() {
        return c;
    }

    public static void c(boolean z) {
        c = z;
    }

    public static int o() {
        return b;
    }

    public static void d() {
        b++;
    }

    public static void a() {
        b = 0;
    }
}
