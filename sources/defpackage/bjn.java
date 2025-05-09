package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.hichain.HiChainAuthManager;
import health.compact.a.EmuiBuild;
import health.compact.a.LogUtil;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bjn {

    /* renamed from: a, reason: collision with root package name */
    private static ConcurrentHashMap<String, String> f406a = new ConcurrentHashMap<>();

    public static boolean c(int i) {
        return 3 == i || 1 == i;
    }

    public static String d(String str) {
        for (Map.Entry<String, String> entry : f406a.entrySet()) {
            if (entry.getValue().equals(str)) {
                return entry.getKey();
            }
        }
        return "";
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("HiChainCommandUtil", "getPin: deviceIdentify is empty");
            return "";
        }
        return f406a.get(str);
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.e("HiChainCommandUtil", "setPeerConnDeviceId: params is incorrect");
        } else {
            f406a.put(str, str2);
            LogUtil.c("HiChainCommandUtil", "setPeerConnDeviceId: ", blt.a(str), " ,peerConnDeviceId = ", str2);
        }
    }

    public static boolean c() {
        return EmuiBuild.f13113a >= 25 && !bky.f();
    }

    public static int e(String str, byte[] bArr) {
        List<bmi> b = b(bArr);
        if (b == null) {
            LogUtil.a("HiChainCommandUtil", "resolveAuthenticateTypeData with pinCode is not correct");
            return -1;
        }
        int i = -1;
        int i2 = -1;
        for (bmi bmiVar : b) {
            int e = bli.e(bmiVar.e());
            if (e == 1) {
                int e2 = bli.e(bmiVar.c());
                if (e2 == 1) {
                    i = 100000;
                }
                if (e2 == 4) {
                    i2 = 4;
                }
            } else {
                if (e != 2) {
                    if (e == 5) {
                        a(str, blq.d(bmiVar.c()));
                    } else if (e != 127) {
                        LogUtil.c("HiChainCommandUtil", "handleUserOperationReport default");
                    }
                }
                i = bli.d(bmiVar.c(), 16);
                if (i2 != -1) {
                    i ^= i2;
                }
            }
        }
        return i;
    }

    private static List<bmi> b(byte[] bArr) {
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.e("HiChainCommandUtil", "tlvFather is null");
            return null;
        }
        return e.b();
    }

    public static boolean c(String str) {
        if (!b(str)) {
            return false;
        }
        LogUtil.c("HiChainCommandUtil", "start isHiChainDeviceOn");
        return HiChainAuthManager.d().i(str);
    }

    public static void a(DeviceInfo deviceInfo, boolean z) {
        if (deviceInfo == null || !b(deviceInfo.getDeviceMac())) {
            return;
        }
        LogUtil.c("HiChainCommandUtil", "start setIsHiChainDeviceOn");
        HiChainAuthManager.d().b(deviceInfo.getDeviceMac(), z);
    }

    public static boolean b(String str) {
        if (c() && !TextUtils.isEmpty(str)) {
            return HiChainAuthManager.d().c(str);
        }
        return false;
    }

    public static bjo e(byte[] bArr) {
        bjo bjoVar = new bjo();
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.a("HiChainCommandUtil", "tlvFather is null");
            return bjoVar;
        }
        List<bmi> b = e.b();
        if (b == null) {
            return bjoVar;
        }
        byte[] bArr2 = null;
        for (bmi bmiVar : b) {
            int e2 = bli.e(bmiVar.e());
            if (e2 == 1) {
                try {
                    bArr2 = blq.a(bmiVar.c());
                } catch (NumberFormatException unused) {
                    LogUtil.e("HiChainCommandUtil", "tlv.getValue() NumberFormatException");
                }
                bjoVar.c(bArr2);
            } else if (e2 == 2) {
                bjoVar.a(bli.e(bmiVar.c()));
            } else if (e2 == 3) {
                bjoVar.a(bli.c(bmiVar.c()));
            } else if (e2 == 4) {
                bjoVar.e(bli.e(bmiVar.c()));
            } else {
                LogUtil.a("HiChainCommandUtil", "resolveHiChainData default");
            }
        }
        return bjoVar;
    }

    public static String b(byte[] bArr, String str, String str2) {
        byte[] b;
        String valueOf = String.valueOf(-1);
        List<bmi> b2 = b(bArr);
        if (b2 == null) {
            LogUtil.c("HiChainCommandUtil", "resolvePinCodeData with pincode is not correct");
            return valueOf;
        }
        String str3 = null;
        for (bmi bmiVar : b2) {
            int e = bli.e(bmiVar.e());
            if (e == 1) {
                str3 = bmiVar.c();
                valueOf = blq.d(str3);
            } else if (e == 2) {
                String c = bmiVar.c();
                biw c2 = bjx.a().c(str2);
                if (c2 != null && c2.c() == 1) {
                    LogUtil.c("HiChainCommandUtil", "decrypt by AES_GCM_NO_PADDING_TYPE");
                    b = bgv.b("AES/GCM/NoPadding", blq.a(str3), blq.a(str), blq.a(c));
                } else {
                    b = bgv.b("AES/CBC/PKCS5Padding", blq.a(str3), blq.a(str), blq.a(c));
                }
                valueOf = blq.d(b);
            } else {
                LogUtil.a("HiChainCommandUtil", "resolvePinCodeData default");
            }
        }
        LogUtil.c("HiChainCommandUtil", "resolvePinCodeData pinCode: ", valueOf);
        return valueOf;
    }

    public static int d(byte[] bArr) {
        List<bmi> b = b(bArr);
        if (b == null) {
            LogUtil.a("HiChainCommandUtil", "resolveLostKey is not correct");
            return -1;
        }
        for (bmi bmiVar : b) {
            if (bli.e(bmiVar.e()) == 1) {
                return bli.e(bmiVar.c());
            }
        }
        return -1;
    }
}
